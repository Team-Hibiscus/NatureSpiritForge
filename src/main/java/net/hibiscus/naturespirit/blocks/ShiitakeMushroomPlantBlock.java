package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ShiitakeMushroomPlantBlock extends MushroomBlock {

  public ShiitakeMushroomPlantBlock(Properties settings, ResourceKey<ConfiguredFeature<?, ?>> featureKey) {
    super(settings, featureKey);
  }

  public static boolean getCompletdCircle(ServerLevel world, BlockPos pos) {

    return isMushroom(pos, world) && isMushroom(pos.west(), world) && isMushroom(pos.west(2), world) && isMushroom(pos.east().north(), world) && isMushroom(pos.east().north(2),
        world) && isMushroom(pos.east().north(3),
        world
    ) && isMushroom(pos.north(4), world) && isMushroom(pos.north(4).west(), world) && isMushroom(pos.north(4).west(2), world) && isMushroom(
        pos.north(3).west(3),
        world
    ) && isMushroom(pos.north(2).west(3), world) && isMushroom(pos.north().west(3), world);
  }

  public static boolean getCompletedPodzol(ServerLevel world, BlockPos pos) {
    pos = pos.below();
    return isPodzol(pos.north(), world) && isPodzol(pos.west().north(), world) && isPodzol(pos.west(2).north(), world) && isPodzol(pos.north(2), world) && isPodzol(
        pos.west().north(2),
        world
    ) && isPodzol(pos.west(2).north(2), world) && isPodzol(pos.north(3), world) && isPodzol(pos.west().north(3), world) && isPodzol(pos.west(2).north(3), world);
  }

  public static boolean getCompletedCoarseDirt(ServerLevel world, BlockPos pos) {
    pos = pos.below();
    return isDirt(pos.north(), world) && isDirt(pos.west().north(), world) && isDirt(pos.west(2).north(), world) && isDirt(pos.north(2), world) && isDirt(pos.west().north(2),
        world
    ) && isDirt(pos.west(2).north(2), world) && isDirt(pos.north(3), world) && isDirt(pos.west().north(3), world) && isDirt(pos.west(2).north(3), world);
  }

  public static boolean isMushroom(BlockPos pos, ServerLevel world) {
    return world.getBlockState(pos).is(NSMiscBlocks.SHIITAKE_MUSHROOM.get());
  }

  public static boolean isPodzol(BlockPos pos, ServerLevel world) {
    return world.getBlockState(pos).is(Blocks.PODZOL) || world.getBlockState(pos).is(BlockTags.DIRT) && (!world.getBlockState(pos).is(Blocks.GRASS_BLOCK)
        && !world.getBlockState(pos).is(
        NSMiscBlocks.SANDY_SOIL.get()));
  }

  public static boolean isDirt(BlockPos pos, ServerLevel world) {
    return world.getBlockState(pos).is(Blocks.COARSE_DIRT) || world.getBlockState(pos).is(NSMiscBlocks.SANDY_SOIL.get());
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
    BlockPos blockPos = pos.below();
    BlockState blockState = world.getBlockState(blockPos);
    if (blockState.is(NSTags.Blocks.SHIITAKE_MUSHROOM_GROW_BLOCK)) {
      return true;
    } else {
      return world.getRawBrightness(pos, 0) < 14 && this.mayPlaceOn(blockState, world, blockPos);
    }
  }

  @Override
  public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
    if (random.nextInt(25) == 0) {
      int i = 5;

      for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
        if (world.getBlockState(blockPos).is(this)) {
          --i;
          if (i <= 0) {
            return;
          }
        }
      }

      BlockPos blockPos2 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

      for (int k = 0; k < 4; ++k) {
        if (world.isEmptyBlock(blockPos2) && state.canSurvive(world, blockPos2)) {
          pos = blockPos2;
        }

        blockPos2 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
      }

      if (world.isEmptyBlock(blockPos2) && state.canSurvive(world, blockPos2)) {
        world.setBlock(blockPos2, state, 2);
      }
    }

    if (getCompletdCircle(world, pos)) {
      BlockPos blockPos = pos.north(3).west(2).below();
      for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
          BlockPos blockPos2 = blockPos.offset(i, 0, j);
          if (world.getBlockState(blockPos2).is(Blocks.GRASS_BLOCK)) {
            if (random.nextInt(25) == 0) {
              world.setBlock(blockPos2, Blocks.PODZOL.defaultBlockState(), Block.UPDATE_CLIENTS);

            }
          }
          if (getCompletedPodzol(world, pos)) {
            if (random.nextInt(25) == 0) {
              world.setBlock(blockPos2, Blocks.COARSE_DIRT.defaultBlockState(), Block.UPDATE_CLIENTS);
            }
          }
          if (getCompletedCoarseDirt(world, pos)) {
            if (random.nextInt(25) == 0) {
              world.setBlock(blockPos2, NSMiscBlocks.SANDY_SOIL.get().defaultBlockState(), Block.UPDATE_CLIENTS);
            }
          }
        }
      }
    }

  }
}
