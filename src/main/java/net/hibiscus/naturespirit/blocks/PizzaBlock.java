package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSStatTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PizzaBlock extends Block implements EntityBlock {
  public static final int DEFAULT_COMPARATOR_OUTPUT;
  protected static final VoxelShape[] BITES_TO_SHAPE;
  public static final IntegerProperty BITES = IntegerProperty.create("pizza_bites", 0, 3);

  static {
    DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
    BITES_TO_SHAPE = new VoxelShape[]{
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D), Shapes.or(
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 8.0D),
            Block.box(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D)
    ), Block.box(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D), Block.box(1.0D, 0.0D, 8.0D, 8.0D, 3.0D, 15.0D)
    };
  }

  public PizzaBlock(Properties settings) {
    super(settings);
    this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0));
  }

  protected static InteractionResult tryEat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
    if(player.canEat(false)) {
      Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
      if(optionalPizzaBlockEntity.isPresent()) {
        PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
        player.awardStat(NSStatTypes.EAT_PIZZA_SLICE.get());
        int foodAmount = 2;
        float saturationModifier = 0.2F;
        for(String string: pizzaBlockEntity.toppings) {
          foodAmount++;
          saturationModifier = saturationModifier + 0.1F;
        }

        player.getFoodData().eat(foodAmount, saturationModifier);

        int i = pizzaBlockEntity.bites;
        world.gameEvent(player, GameEvent.EAT, pos);
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        if(i < 3) {
          pizzaBlockEntity.bites++;
          if (!world.isClientSide()) {
            world.getServer().overworld().sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
          }
          world.setBlock(pos, state.setValue(BITES, state.getValue(BITES) + 1), 2);
        }
        else {
          world.removeBlock(pos, false);
          world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return InteractionResult.SUCCESS;
      }

    } return InteractionResult.PASS;
  }

  public static int getComparatorOutput(int bites) {
    return (7 - bites) * 2;
  }

  @Override public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      int BITE_STATE = pizzaBlockEntity.bites;
      Item item = BITE_STATE == 0 ? NSBlocks.WHOLE_PIZZA.get() : BITE_STATE == 1 ? NSBlocks.THREE_QUARTERS_PIZZA.get() : BITE_STATE == 2 ? NSBlocks.HALF_PIZZA.get() : NSBlocks.QUARTER_PIZZA.get();
      ItemStack itemStack = new ItemStack(item);

      CompoundTag nbtCompound = itemStack.getOrCreateTagElement("BlockEntityTag");
      assert nbtCompound != null;
      pizzaBlockEntity.saveAdditional(nbtCompound);
      pizzaBlockEntity.setChanged();
      return itemStack;
    }
    return super.getCloneItemStack(world, pos, state);
  }

  public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      int BITE_STATE = pizzaBlockEntity.bites;
      return BITES_TO_SHAPE[BITE_STATE];
    }
    return BITES_TO_SHAPE[0];
  }

  public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      ItemStack itemStack = player.getItemInHand(hand);
      Item item = itemStack.getItem();
      if(pizzaBlockEntity.canPlaceTopping(itemStack, pizzaBlockEntity)) {
        if(!player.isCreative()) {
          itemStack.shrink(1);
        }
        if (!world.isClientSide()) {
          world.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        }

        world.playSound(null, pos, SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        pizzaBlockEntity.topping_number++;
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.awardStat(Stats.ITEM_USED.get(item));

        return InteractionResult.SUCCESS;
      }
      if(world.isClientSide) {
        if(tryEat(world, pos, state, player).consumesAction()) {
          return InteractionResult.SUCCESS;
        }

        if(itemStack.isEmpty()) {
          return InteractionResult.CONSUME;
        }
      }

      return tryEat(world, pos, state, player);
    }
    return super.use(state, world, pos, player, hand, hit);
  }

  public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
    return direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
  }
  protected void createBlockStateDefinition(StateDefinition.Builder <Block, BlockState> builder) {
    builder.add(BITES);
  }
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    BlockPos pos1 = pos.below();
    return world.getBlockState(pos1).isRedstoneConductor(world, pos1);
  }

  public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
    Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get());
    if (optionalPizzaBlockEntity.isPresent()) {
      PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
      int BITE_STATE = pizzaBlockEntity.bites;
      return getComparatorOutput(BITE_STATE);
    }
    return getComparatorOutput(0);
  }

  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
    return false;
  }

  @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new PizzaBlockEntity(pos, state);
  }
}