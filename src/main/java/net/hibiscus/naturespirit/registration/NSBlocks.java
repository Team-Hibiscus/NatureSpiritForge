package net.hibiscus.naturespirit.registration;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.blocks.block_entities.NSHangingSignBlockEntity;
import net.hibiscus.naturespirit.blocks.block_entities.NSSignBlockEntity;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.hibiscus.naturespirit.entity.NSBoatEntity;
import net.hibiscus.naturespirit.items.*;
import net.hibiscus.naturespirit.registration.sets.FlowerSet;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

@SuppressWarnings("unused")
public class NSBlocks {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

  public static final RegistryObject<Block> RED_MOSS_BLOCK = registerBlock("red_moss_block", () -> new RedMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> RED_MOSS_CARPET = registerBlock("red_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> SANDY_SOIL = registerBlock("sandy_soil", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).sound(SoundType.GRAVEL)));

  public static final RegistryObject<Block> PINK_SAND = registerBlock("pink_sand", () -> new SandBlock(14331784, BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

  public static final RegistryObject<Block> PINK_SANDSTONE = registerBlock("pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F))
  );

  public static final RegistryObject<Block> PINK_SANDSTONE_STAIRS = registerBlock("pink_sandstone_stairs",
          () -> new StairBlock(() -> PINK_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(PINK_SANDSTONE.get()))
  );

  public static final RegistryObject<Block> PINK_SANDSTONE_SLAB = registerBlock("pink_sandstone_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F))
  );

  public static final RegistryObject<Block> PINK_SANDSTONE_WALL = registerBlock("pink_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PINK_SANDSTONE.get()).forceSolidOn())
  );

  public static final RegistryObject<Block> CHISELED_PINK_SANDSTONE = registerBlock("chiseled_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F))
  );

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE = registerBlock("smooth_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F))
  );

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE_STAIRS = registerBlock("smooth_pink_sandstone_stairs",
          () -> new StairBlock(() -> SMOOTH_PINK_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_PINK_SANDSTONE.get()))
  );

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE_SLAB = registerBlock("smooth_pink_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SMOOTH_PINK_SANDSTONE.get())));

  public static final RegistryObject<Block> CUT_PINK_SANDSTONE = registerBlock("cut_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F))
  );

  public static final RegistryObject<Block> CUT_PINK_SANDSTONE_SLAB = registerBlock("cut_pink_sandstone_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F))
  );

  public static final RegistryObject<Block> TALL_FRIGID_GRASS = registerTallPlantBlock("tall_frigid_grass", () -> new SemiTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> FRIGID_GRASS = registerTransparentBlock("frigid_grass", () -> new NSFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), TALL_FRIGID_GRASS));
  public static final RegistryObject<Block> TALL_SCORCHED_GRASS = registerTallPlantBlock("tall_scorched_grass", () -> new TallLargeDesertFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> SCORCHED_GRASS = registerTransparentBlock("scorched_grass", () -> new LargeDesertFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), TALL_SCORCHED_GRASS));
  public static final RegistryObject<Block> TALL_BEACH_GRASS = registerTallPlantBlock("tall_beach_grass", () -> new TallLargeDesertFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> BEACH_GRASS = registerTransparentBlock("beach_grass", () -> new LargeDesertFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), TALL_BEACH_GRASS));
  public static final RegistryObject<Block> TALL_SEDGE_GRASS = registerTallPlantBlock("tall_sedge_grass", () -> new TallSedgeGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> SEDGE_GRASS = registerTransparentBlock("sedge_grass", () -> new SedgeGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LARGE_FLAXEN_FERN = registerTallPlantBlock("large_flaxen_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> FLAXEN_FERN = registerTransparentBlock("flaxen_fern", () -> new NSFernBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), LARGE_FLAXEN_FERN));
  public static final RegistryObject<Block> TALL_OAT_GRASS = registerTallPlantBlock("tall_oat_grass", () -> new SemiTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> OAT_GRASS = registerTransparentBlock("oat_grass", () -> new NSFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), TALL_OAT_GRASS));
  public static final RegistryObject<Block> LARGE_LUSH_FERN = registerTallPlantBlock("large_lush_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LUSH_FERN = registerTransparentBlock("lush_fern", () -> new NSFernBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), LARGE_LUSH_FERN));
  public static final RegistryObject<Block> TALL_MELIC_GRASS = registerTallPlantBlock("tall_melic_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> MELIC_GRASS = registerTransparentBlock("melic_grass", () -> new NSFernBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), TALL_MELIC_GRASS));
  public static final RegistryObject<Block> GREEN_BEARBERRIES = registerTransparentBlock("green_bearberries", () -> new BearberryBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> RED_BEARBERRIES = registerTransparentBlock("red_bearberries", () -> new BearberryBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> PURPLE_BEARBERRIES = registerTransparentBlock("purple_bearberries", () -> new BearberryBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> GREEN_BITTER_SPROUTS = registerTransparentBlock("green_bitter_sprouts", () -> new LargeSproutsBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> RED_BITTER_SPROUTS = registerTransparentBlock("red_bitter_sprouts", () -> new LargeSproutsBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> PURPLE_BITTER_SPROUTS = registerTransparentBlock("purple_bitter_sprouts", () -> new LargeSproutsBlock(BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> CATTAIL = registerTransparentBlock("cattail", () -> new CattailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> AZOLLA = NSRegistryHelper.registerTransparentBlockWithoutItem("azolla", () -> new AzollaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().noCollission().sound(SoundType.LILY_PAD)));
  public static final RegistryObject<Item> AZOLLA_ITEM = registerItem("azolla", () -> new AzollaItem(AZOLLA.get(), new Item.Properties()));

  public static final RegistryObject<Block> ORNATE_SUCCULENT = registerTransparentBlockWithoutItem("ornate_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> DROWSY_SUCCULENT = registerTransparentBlockWithoutItem("drowsy_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> AUREATE_SUCCULENT = registerTransparentBlockWithoutItem("aureate_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> SAGE_SUCCULENT = registerTransparentBlockWithoutItem("sage_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> FOAMY_SUCCULENT = registerTransparentBlockWithoutItem("foamy_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> IMPERIAL_SUCCULENT = registerTransparentBlockWithoutItem("imperial_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> REGAL_SUCCULENT = registerTransparentBlockWithoutItem("regal_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollission().instabreak()));
  public static final RegistryObject<Block> ORNATE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("ornate_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(ORNATE_SUCCULENT.get())));
  public static final RegistryObject<Block> DROWSY_WALL_SUCCULENT = registerTransparentBlockWithoutItem("drowsy_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(DROWSY_SUCCULENT.get())));
  public static final RegistryObject<Block> AUREATE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("aureate_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(AUREATE_SUCCULENT.get())));
  public static final RegistryObject<Block> SAGE_WALL_SUCCULENT = registerTransparentBlockWithoutItem("sage_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(SAGE_SUCCULENT.get())));
  public static final RegistryObject<Block> FOAMY_WALL_SUCCULENT = registerTransparentBlockWithoutItem("foamy_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(FOAMY_SUCCULENT.get())));
  public static final RegistryObject<Block> IMPERIAL_WALL_SUCCULENT = registerTransparentBlockWithoutItem("imperial_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(IMPERIAL_SUCCULENT.get())));
  public static final RegistryObject<Block> REGAL_WALL_SUCCULENT = registerTransparentBlockWithoutItem("regal_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(REGAL_SUCCULENT.get())));
  public static final RegistryObject<Item> ORNATE_SUCCULENT_ITEM = registerItem("ornate_succulent", () -> new StandingAndWallBlockItem(ORNATE_SUCCULENT.get(), ORNATE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> DROWSY_SUCCULENT_ITEM = registerItem("drowsy_succulent", () -> new StandingAndWallBlockItem(DROWSY_SUCCULENT.get(), DROWSY_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> AUREATE_SUCCULENT_ITEM = registerItem("aureate_succulent", () -> new StandingAndWallBlockItem(AUREATE_SUCCULENT.get(), AUREATE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> SAGE_SUCCULENT_ITEM = registerItem("sage_succulent", () -> new StandingAndWallBlockItem(SAGE_SUCCULENT.get(), SAGE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> FOAMY_SUCCULENT_ITEM = registerItem("foamy_succulent", () -> new StandingAndWallBlockItem(FOAMY_SUCCULENT.get(), FOAMY_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> IMPERIAL_SUCCULENT_ITEM = registerItem("imperial_succulent", () -> new StandingAndWallBlockItem(IMPERIAL_SUCCULENT.get(), IMPERIAL_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));
  public static final RegistryObject<Item> REGAL_SUCCULENT_ITEM = registerItem("regal_succulent", () -> new StandingAndWallBlockItem(REGAL_SUCCULENT.get(), REGAL_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN));

  public static final RegistryObject<Block> SHIITAKE_MUSHROOM = registerTransparentBlock("shiitake_mushroom", () -> new ShiitakeMushroomPlantBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
                  .hasPostProcess(NSRegistryHelper::always).pushReaction(PushReaction.DESTROY), NSConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM));
  public static final RegistryObject<Block> SHIITAKE_MUSHROOM_BLOCK = registerBlock("shiitake_mushroom_block",
          () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava())
  );

  public static final RegistryObject<Block> GRAY_POLYPORE = registerTransparentBlock("gray_polypore", () -> new PolyporeBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
                  .hasPostProcess(NSRegistryHelper::always).pushReaction(PushReaction.DESTROY), NSConfiguredFeatures.GRAY_POLYPORE));
  public static final RegistryObject<Block> GRAY_POLYPORE_BLOCK = registerBlock("gray_polypore_block", () -> new HugeMushroomBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava())
  );


  public static final RegistryObject<Block> POTTED_SCORCHED_GRASS = registerTransparentBlockWithoutItem("potted_scorched_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), SCORCHED_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_BEACH_GRASS = registerTransparentBlockWithoutItem("potted_beach_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), BEACH_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_SEDGE_GRASS = registerTransparentBlockWithoutItem("potted_sedge_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), SEDGE_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_FLAXEN_FERN = registerTransparentBlockWithoutItem("potted_flaxen_fern", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), FLAXEN_FERN, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_OAT_GRASS = registerTransparentBlockWithoutItem("potted_oat_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), OAT_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_MELIC_GRASS = registerTransparentBlockWithoutItem("potted_melic_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), MELIC_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_LUSH_FERN = registerTransparentBlockWithoutItem("potted_lush_fern", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), LUSH_FERN, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_FRIGID_GRASS = registerTransparentBlockWithoutItem("potted_frigid_grass", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), FRIGID_GRASS, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_GREEN_BEARBERRIES = registerTransparentBlockWithoutItem("potted_green_bearberries", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), GREEN_BEARBERRIES, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_RED_BEARBERRIES = registerTransparentBlockWithoutItem("potted_red_bearberries", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), RED_BEARBERRIES, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_PURPLE_BEARBERRIES = registerTransparentBlockWithoutItem("potted_purple_bearberries", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), PURPLE_BEARBERRIES, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_ORNATE_SUCCULENT = registerTransparentBlockWithoutItem("potted_ornate_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), ORNATE_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_DROWSY_SUCCULENT = registerTransparentBlockWithoutItem("potted_drowsy_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), DROWSY_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_AUREATE_SUCCULENT = registerTransparentBlockWithoutItem("potted_aureate_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), AUREATE_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_SAGE_SUCCULENT = registerTransparentBlockWithoutItem("potted_sage_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), SAGE_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_FOAMY_SUCCULENT = registerTransparentBlockWithoutItem("potted_foamy_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), FOAMY_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_IMPERIAL_SUCCULENT = registerTransparentBlockWithoutItem("potted_imperial_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), IMPERIAL_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_REGAL_SUCCULENT = registerTransparentBlockWithoutItem("potted_regal_succulent", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), REGAL_SUCCULENT, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> POTTED_SHIITAKE_MUSHROOM = registerTransparentBlockWithoutItem("potted_shiitake_mushroom", () -> new FlowerPotBlock( (() -> (FlowerPotBlock) Blocks.FLOWER_POT), SHIITAKE_MUSHROOM, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


  public static final FlowerSet LAVENDER = new FlowerSet("lavender", Items.PURPLE_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet BLEEDING_HEART = new FlowerSet("bleeding_heart", Items.PINK_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet BLUE_BULBS = new FlowerSet("blue_bulbs", Items.BLUE_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet CARNATION = new FlowerSet("carnation", Items.RED_DYE, FlowerSet.FlowerPreset.BIG_TALL);
  public static final FlowerSet GARDENIA = new FlowerSet("gardenia", Items.WHITE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet SNAPDRAGON = new FlowerSet("snapdragon", Items.PINK_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet FOXGLOVE = new FlowerSet("foxglove", Items.PURPLE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet BEGONIA = new FlowerSet("begonia", Items.ORANGE_DYE, FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet MARIGOLD = new FlowerSet("marigold", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet BLUEBELL = new FlowerSet("bluebell", Items.BLUE_DYE, MobEffects.DIG_SPEED, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet TIGER_LILY = new FlowerSet("tiger_lily", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet PURPLE_WILDFLOWER = new FlowerSet("purple_wildflower", Items.PURPLE_DYE, MobEffects.SLOW_FALLING, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet YELLOW_WILDFLOWER = new FlowerSet("yellow_wildflower", Items.YELLOW_DYE, MobEffects.SLOW_FALLING, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet RED_HEATHER = new FlowerSet("red_heather", Items.RED_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet WHITE_HEATHER = new FlowerSet("white_heather", Items.WHITE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet PURPLE_HEATHER = new FlowerSet("purple_heather", Items.PURPLE_DYE, MobEffects.FIRE_RESISTANCE, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet ANEMONE = new FlowerSet("anemone", Items.MAGENTA_DYE, MobEffects.DAMAGE_RESISTANCE, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet DWARF_BLOSSOMS = new FlowerSet("dwarf_blossoms", Items.PINK_DYE, MobEffects.DAMAGE_RESISTANCE, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet PROTEA = new FlowerSet("protea", Items.PINK_DYE, MobEffects.WATER_BREATHING, FlowerSet.FlowerPreset.MID_SMALL);
  public static final FlowerSet HIBISCUS = new FlowerSet("hibiscus", Items.RED_DYE, MobEffects.LUCK, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet BLUE_IRIS = new FlowerSet("blue_iris", Items.LIGHT_BLUE_DYE, MobEffects.DAMAGE_BOOST, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet BLACK_IRIS = new FlowerSet("black_iris", Items.BLACK_DYE, MobEffects.DAMAGE_BOOST, FlowerSet.FlowerPreset.SMALL);
  public static final FlowerSet RUBY_BLOSSOMS = new FlowerSet("ruby_blossoms", Items.RED_DYE, MobEffects.JUMP, FlowerSet.FlowerPreset.BIG_SMALL);
  public static final FlowerSet SILVERBUSH = new FlowerSet("silverbush", FlowerSet.FlowerPreset.BIG_TALL);


  public static final RegistryObject<Block> HELVOLA = registerTransparentBlockWithoutItem("helvola", () -> new WaterFlowerbedBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().friction(0.8F).sound(SoundType.LILY_PAD)));
  public static final RegistryObject<Item> HELVOLA_PAD_ITEM = registerItem("helvola_pad", () -> new PlaceOnWaterBlockItem(HELVOLA.get(), new Item.Properties()));
  public static final RegistryObject<Item> HELVOLA_FLOWER_ITEM = registerItem("helvola_flower", () -> new Item(new Item.Properties()));

  public static final RegistryObject<Block> LOTUS_FLOWER = registerTransparentBlockWithoutItem("lotus_flower", () -> new LotusFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().sound(SoundType.LILY_PAD)));
  public static final RegistryObject<Item> LOTUS_FLOWER_ITEM = registerItem("lotus_flower", () -> new PlaceOnWaterBlockItem(LOTUS_FLOWER.get(), new Item.Properties()));
  public static final RegistryObject<Block> LOTUS_STEM = registerTransparentBlock("lotus_stem", () -> new LotusStemBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).noCollission().noOcclusion().instabreak().sound(SoundType.LILY_PAD), LOTUS_FLOWER.get()));

  public static final RegistryObject<Block> ALLUAUDIA = registerTransparentBlock("alluaudia", () -> new GrowingBranchingTrunkBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff()));
  public static final RegistryObject<Block> STRIPPED_ALLUAUDIA = registerTransparentBlock("stripped_alluaudia", () -> new GrowingBranchingTrunkBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff()));
  public static final RegistryObject<Block> STRIPPED_ALLUAUDIA_BUNDLE = registerTransparentBlock("stripped_alluaudia_bundle", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f)) {
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> ALLUAUDIA_BUNDLE = registerTransparentBlock("alluaudia_bundle", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f)) {

      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
          return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
          return 5;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
          return 5;
      }
  });

  public static final FoodProperties OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.4F).build();
  public static final RegistryObject<Item> OLIVES = registerItem("olives", () -> new Item(new Item.Properties().food(OLIVE_COMPONENT)));

  public static final RegistryObject<Block> DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
          () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.PODZOL).strength(1.0F).sound(SoundType.ROOTS))
  );
  public static final RegistryObject<Block> DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block", () -> new DesertTurnipBlock(
      BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.ICE).strength(1.0F).sound(SoundType.ROOTS))
  );
  public static final RegistryObject<Block> DESERT_TURNIP_STEM = registerTransparentBlockWithoutItem("desert_turnip_stem",
          () -> new DesertTurnipStemBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK.get(), DESERT_TURNIP_ROOT_BLOCK.get(),
          BlockBehaviour.Properties.of().noCollission().instabreak().randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
  public static final FoodProperties DESERT_TURNIP_FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();
  public static final RegistryObject<Item> DESERT_TURNIP = registerItem("desert_turnip", () -> new DesertTurnipItem(DESERT_TURNIP_STEM.get(), (new Item.Properties()).food(DESERT_TURNIP_FOOD_COMPONENT)));

  public static final RegistryObject<Block> CHEESE_BLOCK = registerBlockWithoutItem("cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(2.0F, 1.0F).sound(SoundType.AZALEA_LEAVES)));
  public static final RegistryObject<Item> CHEESE_BUCKET = registerItem("cheese_bucket", () -> new SolidBucketItem(CHEESE_BLOCK.get(), SoundEvents.BUCKET_EMPTY, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
  public static final RegistryObject<Item> CHEESE_ARROW =  registerItem("cheese_arrow", () -> new CheeseArrowItem(new Item.Properties()));
  public static final RegistryObject<Block> MILK_CAULDRON = registerBlockWithoutItem("milk_cauldron", () -> new MilkCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));
  public static final RegistryObject<Block> CHEESE_CAULDRON = registerBlockWithoutItem("cheese_cauldron", () -> new CheeseCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

  public static final FoodProperties STANDARD_PIZZA_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).build();
  public static final RegistryObject<Block> PIZZA_BLOCK = registerBlockWithoutItem("pizza_block", () -> new PizzaBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
  public static final RegistryObject<BlockEntityType<PizzaBlockEntity>> PIZZA_BLOCK_ENTITY_TYPE = BLOCK_ENTITIES.register("pizza_block_entity", () -> BlockEntityType.Builder.of(PizzaBlockEntity::new, PIZZA_BLOCK.get()).build(null));
  public static final RegistryObject<Item> WHOLE_PIZZA = registerItem("whole_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));
  public static final RegistryObject<Item> THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));
  public static final RegistryObject<Item> HALF_PIZZA = registerItem("half_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));
  public static final RegistryObject<Item> QUARTER_PIZZA = registerItem("quarter_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));

  public static final RegistryObject<Item> CHALK_POWDER = registerItem("chalk_powder", () -> new Item((new Item.Properties())));
  public static final RegistryObject<Item> CALCITE_SHARD = registerItem("calcite_shard", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Block> CALCITE_CLUSTER = registerBlock("calcite_cluster", () -> new AmethystClusterBlock(7, 3,
      BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).forceSolidOn().noOcclusion().randomTicks().sound(SoundType.CALCITE).strength(1.5F)
          .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LARGE_CALCITE_BUD = registerBlock("large_calcite_bud",
          () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY))
  );
  public static final RegistryObject<Block> SMALL_CALCITE_BUD = registerBlock("small_calcite_bud",
          () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY))
  );

  public static final StoneSet TRAVERTINE = new StoneSet("travertine", MapColor.COLOR_LIGHT_GRAY, 1.5F, true, true, true, true);
  public static final StoneSet CHERT = new StoneSet("chert", MapColor.WOOD, .9F, false, true, false, true, true);
  public static final RegistryObject<Block> CHERT_GOLD_ORE = registerBlock("chert_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).mapColor(MapColor.WOOD).strength(.9f), ConstantInt.of(0)));
  public static final RegistryObject<Block> CHERT_IRON_ORE = registerBlock("chert_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).mapColor(MapColor.WOOD).strength(.9f), ConstantInt.of(0)));
  public static final RegistryObject<Block> CHERT_COAL_ORE = registerBlock("chert_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(0, 2)));
  public static final RegistryObject<Block> CHERT_LAPIS_ORE = registerBlock("chert_lapis_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(2, 5)));
  public static final RegistryObject<Block> CHERT_DIAMOND_ORE = registerBlock("chert_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(3, 7)));
  public static final RegistryObject<Block> CHERT_REDSTONE_ORE = registerBlock("chert_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).mapColor(MapColor.WOOD).strength(.6f)));
  public static final RegistryObject<Block> CHERT_EMERALD_ORE = registerBlock("chert_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).mapColor(MapColor.WOOD).strength(.6f), UniformInt.of(3, 7)));
  public static final RegistryObject<Block> CHERT_COPPER_ORE = registerBlock("chert_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE).mapColor(MapColor.WOOD).strength(.6f), ConstantInt.of(0)));

  public static final WoodSet REDWOOD = new WoodSet(
          "redwood",
          MapColor.TERRACOTTA_BROWN,
          MapColor.COLOR_RED,
          () -> NSBoatEntity.Type.REDWOOD,
          WoodSet.WoodPreset.FROSTABLE,
          false,
          new RedwoodSaplingGenerator()
  );

  public static final WoodSet SUGI = new WoodSet(
          "sugi",
          MapColor.DEEPSLATE,
          MapColor.DIRT,
          () -> NSBoatEntity.Type.SUGI,
          WoodSet.WoodPreset.FANCY,
          true,
          new SugiSaplingGenerator()
  );

  public static final Supplier<BlockSetType> PAPER_BLOCK_SET = Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(MOD_ID + ":paper", true,SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)));
  public static final Supplier<WoodType> PAPER_WOOD_TYPE = Suppliers.memoize(() -> WoodType.register(new WoodType(MOD_ID + ":paper", PAPER_BLOCK_SET.get())));
  public static final RegistryObject<Block> PAPER_BLOCK = registerBlock("paper_block", () -> new Block(BlockBehaviour.Properties.copy(SUGI.getPlanks().get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> PAPER_PANEL = registerBlock("paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(SUGI.getPlanks().get()).noOcclusion()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> PAPER_DOOR = registerTransparentBlock("paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> PAPER_TRAPDOOR = registerTransparentBlock("paper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion(), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> FRAMED_PAPER_BLOCK = registerBlock("framed_paper_block", () -> new Block(BlockBehaviour.Properties.copy(SUGI.getPlanks().get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> FRAMED_PAPER_PANEL = registerBlock("framed_paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(SUGI.getPlanks().get()).noOcclusion()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> FRAMED_PAPER_DOOR = registerTransparentBlock("framed_paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> FRAMED_PAPER_TRAPDOOR = registerTransparentBlock("framed_paper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion(), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> BLOOMING_PAPER_BLOCK = registerBlock("blooming_paper_block", () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.copy(SUGI.getPlanks().get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> BLOOMING_PAPER_PANEL = registerBlock("blooming_paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(SUGI.getPlanks().get()).noOcclusion()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> BLOOMING_PAPER_DOOR = registerTransparentBlock("blooming_paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> BLOOMING_PAPER_TRAPDOOR = registerTransparentBlock("blooming_paper_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(3.0f).sound(SoundType.WOOD).isValidSpawn(NSRegistryHelper::never).noOcclusion(), PAPER_BLOCK_SET.get()){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> PAPER_SIGN = registerBlockWithoutItem("paper_sign", () -> new NSStandingSignBlock(BlockBehaviour.Properties.copy(SUGI.getSign().get()), PAPER_WOOD_TYPE.get()));
  public static final RegistryObject<Block> PAPER_WALL_SIGN = registerBlockWithoutItem("paper_wall_sign", () -> new NSWallSignBlock(BlockBehaviour.Properties.copy(SUGI.getSign().get()).dropsLike(PAPER_SIGN.get()), PAPER_WOOD_TYPE.get()));
  public static final RegistryObject<Block> PAPER_HANGING_SIGN = registerBlockWithoutItem("paper_hanging_sign", () -> new NSHangingSignBlock(BlockBehaviour.Properties.copy(SUGI.getHangingSign().get()), PAPER_WOOD_TYPE.get()));
  public static final RegistryObject<Block> PAPER_WALL_HANGING_SIGN = registerBlockWithoutItem("paper_wall_hanging_sign", () -> new NSWallHangingSignBlock(BlockBehaviour.Properties.copy(SUGI.getHangingSign().get()).dropsLike(PAPER_HANGING_SIGN.get()), PAPER_WOOD_TYPE.get()));
  public static final RegistryObject<Item> PAPER_SIGN_ITEM = registerItem("paper_sign", () -> new SignItem(new Item.Properties().stacksTo(16), PAPER_SIGN.get(), PAPER_WALL_SIGN.get()));
  public static final RegistryObject<Item> PAPER_HANGING_SIGN_ITEM = registerItem("paper_hanging_sign", () -> new HangingSignItem(PAPER_HANGING_SIGN.get(), PAPER_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

  public static final WoodSet WISTERIA = new WoodSet(
      "wisteria",
      MapColor.COLOR_GRAY,
      MapColor.TERRACOTTA_WHITE,
      () -> NSBoatEntity.Type.WISTERIA,
      WoodSet.WoodPreset.WISTERIA,
      true,
      new WhiteWisteriaSaplingGenerator()
  );
  public static final WoodSet FIR = new WoodSet(
      "fir",
      MapColor.COLOR_GRAY,
      MapColor.DIRT,
      () -> NSBoatEntity.Type.FIR,
      WoodSet.WoodPreset.FROSTABLE,
      false,
      new FirSaplingGenerator()
  );
  public static final WoodSet WILLOW = new WoodSet(
      "willow",
      MapColor.TERRACOTTA_BLACK,
      MapColor.TERRACOTTA_BROWN,
      () -> NSBoatEntity.Type.WILLOW,
      WoodSet.WoodPreset.WILLOW,
      false,
      new WillowSaplingGenerator()
  );
  public static final WoodSet ASPEN = new WoodSet(
      "aspen",
      MapColor.WOOL,
      MapColor.SAND,
      () -> NSBoatEntity.Type.ASPEN,
      WoodSet.WoodPreset.ASPEN,
      false,
      new AspenSaplingGenerator()
  );
  public static final WoodSet MAPLE = new WoodSet(
      "maple",
      MapColor.PODZOL,
      MapColor.COLOR_ORANGE,
      () -> NSBoatEntity.Type.MAPLE,
      WoodSet.WoodPreset.MAPLE,
      false,
      new RedMapleSaplingGenerator()
  );
  public static final WoodSet CYPRESS = new WoodSet(
      "cypress",
      MapColor.PODZOL,
      MapColor.WOOD,
      () -> NSBoatEntity.Type.CYPRESS,
      WoodSet.WoodPreset.DEFAULT,
      false,
      new CypressSaplingGenerator()
  );
  public static final WoodSet OLIVE = new WoodSet(
      "olive",
      MapColor.SAND,
      MapColor.GRASS,
      () -> NSBoatEntity.Type.OLIVE,
      WoodSet.WoodPreset.DEFAULT,
      false,
      new OliveSaplingGenerator()
  );
  public static final RegistryObject<Block> OLIVE_BRANCH = registerTransparentBlock("olive_branch", () -> new OliveBranchBlock(BlockBehaviour.Properties.of().instabreak().noCollission().randomTicks().sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)) {
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final WoodSet JOSHUA = new WoodSet(
      "joshua",
      MapColor.GRASS,
      MapColor.DEEPSLATE,
      () -> NSBoatEntity.Type.JOSHUA,
      WoodSet.WoodPreset.JOSHUA,
      true,
      new JoshuaSaplingGenerator()
  );
  public static final WoodSet GHAF = new WoodSet(
      "ghaf",
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_BROWN,
      () -> NSBoatEntity.Type.GHAF,
      WoodSet.WoodPreset.SANDY,
      false,
      new GhafSaplingGenerator()
  );
  public static final RegistryObject<Block> XERIC_THATCH = registerBlock("xeric_thatch", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.4F).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> XERIC_THATCH_STAIRS = registerBlock("xeric_thatch_stairs", () -> new StairBlock(() -> XERIC_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(XERIC_THATCH.get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> XERIC_THATCH_SLAB = registerBlock("xeric_thatch_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).strength(0.4f)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> XERIC_THATCH_CARPET = registerBlock("xeric_thatch_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final WoodSet PALO_VERDE = new WoodSet(
      "palo_verde",
      MapColor.COLOR_YELLOW,
      MapColor.GLOW_LICHEN,
      () -> NSBoatEntity.Type.PALO_VERDE,
      WoodSet.WoodPreset.SANDY,
      false,
      new PaloVerdeSaplingGenerator()
  );
  public static final WoodSet COCONUT = new WoodSet(
      "coconut",
      MapColor.CRIMSON_STEM,
      MapColor.COLOR_BROWN,
      () -> NSBoatEntity.Type.COCONUT,
      WoodSet.WoodPreset.NO_SAPLING,
      true,
      new CoconutSaplingGenerator()
  );
  public static final RegistryObject<Block> COCONUT_THATCH = registerBlock("coconut_thatch", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.4F).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> COCONUT_THATCH_STAIRS = registerBlock("coconut_thatch_stairs", () -> new StairBlock(() -> COCONUT_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(COCONUT_THATCH.get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> COCONUT_THATCH_SLAB = registerBlock("coconut_thatch_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GRASS).strength(0.4f)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> COCONUT_THATCH_CARPET = registerBlock("coconut_thatch_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> COCONUT_BLOCK = registerTransparentBlock("coconut", () -> new CoconutBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> COCONUT_SPROUT = registerTransparentBlock("coconut_sprout", () -> new SproutingCoconutBlock(new CoconutSaplingGenerator(), BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)));
  public static final FoodProperties COCONUT_COMPONENT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
  public static final RegistryObject<Item> COCONUT_SHELL = registerItem("coconut_shell", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> COCONUT_HALF = registerItem("coconut_half", () -> new CoconutHalfItem(new Item.Properties().food(COCONUT_COMPONENT), COCONUT_SHELL.get()));
  public static final WoodSet CEDAR = new WoodSet(
      "cedar",
      MapColor.TERRACOTTA_MAGENTA,
      MapColor.COLOR_GRAY,
      () -> NSBoatEntity.Type.CEDAR,
      WoodSet.WoodPreset.DEFAULT,
      false,
      new CedarSaplingGenerator()
  );
  public static final WoodSet LARCH = new WoodSet(
      "larch",
      MapColor.COLOR_BLUE,
      MapColor.COLOR_LIGHT_GRAY,
      () -> NSBoatEntity.Type.LARCH,
      WoodSet.WoodPreset.DEFAULT,
      false,
      new LarchSaplingGenerator()
  );
  public static final RegistryObject<Block> EVERGREEN_THATCH = registerBlock("evergreen_thatch", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.4F).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> EVERGREEN_THATCH_STAIRS = registerBlock("evergreen_thatch_stairs", () -> new StairBlock(() -> EVERGREEN_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(EVERGREEN_THATCH.get())){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> EVERGREEN_THATCH_SLAB = registerBlock("evergreen_thatch_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.GRASS).strength(0.4f)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final RegistryObject<Block> EVERGREEN_THATCH_CARPET = registerBlock("evergreen_thatch_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0F).pushReaction(PushReaction.DESTROY).sound(SoundType.GRASS)){
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
      return 5;
    }
  });
  public static final WoodSet MAHOGANY = new WoodSet(
      "mahogany",
      MapColor.COLOR_BROWN,
      MapColor.COLOR_LIGHT_GRAY,
      () -> NSBoatEntity.Type.MAHOGANY, WoodSet.WoodPreset.DEFAULT,
      true,
      new MahoganySaplingGenerator()
  );
  public static final WoodSet SAXAUL = new WoodSet(
      "saxaul",
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_LIGHT_GRAY,
      () -> NSBoatEntity.Type.SAXAUL,
      WoodSet.WoodPreset.SANDY,
      false,
      new SaxaulSaplingGenerator()
  );

  public static final RegistryObject<BlockEntityType<NSSignBlockEntity>> NS_SIGN =
          BLOCK_ENTITIES.register("sign", () ->
                  BlockEntityType.Builder.of(NSSignBlockEntity::new,
                          NSBlocks.REDWOOD.getSign().get(),
                          NSBlocks.REDWOOD.getWallSign().get(),
                          NSBlocks.SUGI.getSign().get(),
                          NSBlocks.SUGI.getWallSign().get(),
                          NSBlocks.WISTERIA.getSign().get(),
                          NSBlocks.WISTERIA.getWallSign().get(),
                          NSBlocks.FIR.getSign().get(),
                          NSBlocks.FIR.getWallSign().get(),
                          NSBlocks.WILLOW.getSign().get(),
                          NSBlocks.WILLOW.getWallSign().get(),
                          NSBlocks.ASPEN.getSign().get(),
                          NSBlocks.ASPEN.getWallSign().get(),
                          NSBlocks.MAPLE.getSign().get(),
                          NSBlocks.MAPLE.getWallSign().get(),
                          NSBlocks.CYPRESS.getSign().get(),
                          NSBlocks.CYPRESS.getWallSign().get(),
                          NSBlocks.OLIVE.getSign().get(),
                          NSBlocks.OLIVE.getWallSign().get(),
                          NSBlocks.JOSHUA.getSign().get(),
                          NSBlocks.JOSHUA.getWallSign().get(),
                          NSBlocks.GHAF.getSign().get(),
                          NSBlocks.GHAF.getWallSign().get(),
                          NSBlocks.PALO_VERDE.getSign().get(),
                          NSBlocks.PALO_VERDE.getWallSign().get(),
                          NSBlocks.COCONUT.getSign().get(),
                          NSBlocks.COCONUT.getWallSign().get(),
                          NSBlocks.CEDAR.getSign().get(),
                          NSBlocks.CEDAR.getWallSign().get(),
                          NSBlocks.LARCH.getSign().get(),
                          NSBlocks.LARCH.getWallSign().get(),
                          NSBlocks.MAHOGANY.getSign().get(),
                          NSBlocks.MAHOGANY.getWallSign().get(),
                          NSBlocks.SAXAUL.getSign().get(),
                          NSBlocks.SAXAUL.getWallSign().get(),
                          PAPER_SIGN.get(),
                          PAPER_WALL_SIGN.get()
                  ).build(null));

  public static final RegistryObject<BlockEntityType<NSHangingSignBlockEntity>> NS_HANGING_SIGN =
          BLOCK_ENTITIES.register("hanging_sign", () ->
                  BlockEntityType.Builder.of(NSHangingSignBlockEntity::new,
                          NSBlocks.REDWOOD.getHangingSign().get(),
                          NSBlocks.REDWOOD.getHangingWallSign().get(),
                          NSBlocks.SUGI.getHangingSign().get(),
                          NSBlocks.SUGI.getHangingWallSign().get(),
                          NSBlocks.WISTERIA.getHangingSign().get(),
                          NSBlocks.WISTERIA.getHangingWallSign().get(),
                          NSBlocks.FIR.getHangingSign().get(),
                          NSBlocks.FIR.getHangingWallSign().get(),
                          NSBlocks.WILLOW.getHangingSign().get(),
                          NSBlocks.WILLOW.getHangingWallSign().get(),
                          NSBlocks.ASPEN.getHangingSign().get(),
                          NSBlocks.ASPEN.getHangingWallSign().get(),
                          NSBlocks.MAPLE.getHangingSign().get(),
                          NSBlocks.MAPLE.getHangingWallSign().get(),
                          NSBlocks.CYPRESS.getHangingSign().get(),
                          NSBlocks.CYPRESS.getHangingWallSign().get(),
                          NSBlocks.OLIVE.getHangingSign().get(),
                          NSBlocks.OLIVE.getHangingWallSign().get(),
                          NSBlocks.JOSHUA.getHangingSign().get(),
                          NSBlocks.JOSHUA.getHangingWallSign().get(),
                          NSBlocks.GHAF.getHangingSign().get(),
                          NSBlocks.GHAF.getHangingWallSign().get(),
                          NSBlocks.PALO_VERDE.getHangingSign().get(),
                          NSBlocks.PALO_VERDE.getHangingWallSign().get(),
                          NSBlocks.COCONUT.getHangingSign().get(),
                          NSBlocks.COCONUT.getHangingWallSign().get(),
                          NSBlocks.CEDAR.getHangingSign().get(),
                          NSBlocks.CEDAR.getHangingWallSign().get(),
                          NSBlocks.LARCH.getHangingSign().get(),
                          NSBlocks.LARCH.getHangingWallSign().get(),
                          NSBlocks.MAHOGANY.getHangingSign().get(),
                          NSBlocks.MAHOGANY.getHangingWallSign().get(),
                          NSBlocks.SAXAUL.getHangingSign().get(),
                          NSBlocks.SAXAUL.getHangingWallSign().get(),
                          PAPER_HANGING_SIGN.get(),
                          PAPER_WALL_HANGING_SIGN.get()
                  ).build(null));

  public static final RegistryObject<Block> PAPER_LANTERN = registerTransparentBlock("paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> WHITE_PAPER_LANTERN = registerTransparentBlock("white_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LIGHT_GRAY_PAPER_LANTERN = registerTransparentBlock("light_gray_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> GRAY_PAPER_LANTERN = registerTransparentBlock("gray_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> BLACK_PAPER_LANTERN = registerTransparentBlock("black_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> BROWN_PAPER_LANTERN = registerTransparentBlock("brown_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> RED_PAPER_LANTERN = registerTransparentBlock("red_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> ORANGE_PAPER_LANTERN = registerTransparentBlock("orange_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> YELLOW_PAPER_LANTERN = registerTransparentBlock("yellow_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LIME_PAPER_LANTERN = registerTransparentBlock("lime_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> GREEN_PAPER_LANTERN = registerTransparentBlock("green_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> BLUE_PAPER_LANTERN = registerTransparentBlock("blue_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> LIGHT_BLUE_PAPER_LANTERN = registerTransparentBlock("light_blue_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> CYAN_PAPER_LANTERN = registerTransparentBlock("cyan_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> PURPLE_PAPER_LANTERN = registerTransparentBlock("purple_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> MAGENTA_PAPER_LANTERN = registerTransparentBlock("magenta_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));
  public static final RegistryObject<Block> PINK_PAPER_LANTERN = registerTransparentBlock("pink_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> KAOLIN = registerBlock(
      "kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> WHITE_KAOLIN = registerBlock(
      "white_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN = registerBlock(
      "light_gray_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> GRAY_KAOLIN = registerBlock(
      "gray_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BLACK_KAOLIN = registerBlock(
      "black_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BROWN_KAOLIN = registerBlock(
      "brown_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> RED_KAOLIN = registerBlock(
      "red_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN = registerBlock(
      "orange_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN = registerBlock(
      "yellow_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIME_KAOLIN = registerBlock(
      "lime_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> GREEN_KAOLIN = registerBlock(
      "green_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> CYAN_KAOLIN = registerBlock(
      "cyan_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN = registerBlock(
      "light_blue_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BLUE_KAOLIN = registerBlock(
      "blue_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN = registerBlock(
      "purple_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN = registerBlock(
      "magenta_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> PINK_KAOLIN = registerBlock(
      "pink_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );

  public static final RegistryObject<Block> KAOLIN_STAIRS = registerBlock(
          "kaolin_stairs",
          () -> new StairBlock(() -> KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_STAIRS = registerBlock(
          "white_kaolin_stairs",
          () -> new StairBlock(() -> WHITE_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_STAIRS = registerBlock(
          "light_gray_kaolin_stairs",
          () -> new StairBlock(() -> LIGHT_GRAY_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_STAIRS = registerBlock(
          "gray_kaolin_stairs",
          () -> new StairBlock(() -> GRAY_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_STAIRS = registerBlock(
          "black_kaolin_stairs",
          () -> new StairBlock(() -> BLACK_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_STAIRS = registerBlock(
          "brown_kaolin_stairs",
          () -> new StairBlock(() -> BROWN_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> RED_KAOLIN_STAIRS = registerBlock(
          "red_kaolin_stairs",
          () -> new StairBlock(() -> RED_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_STAIRS = registerBlock(
          "orange_kaolin_stairs",
          () -> new StairBlock(() -> ORANGE_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_STAIRS = registerBlock(
          "yellow_kaolin_stairs",
          () -> new StairBlock(() -> YELLOW_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIME_KAOLIN_STAIRS = registerBlock(
          "lime_kaolin_stairs",
          () -> new StairBlock(() -> LIME_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_STAIRS = registerBlock(
          "green_kaolin_stairs",
          () -> new StairBlock(() -> GREEN_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_STAIRS = registerBlock(
          "cyan_kaolin_stairs",
          () -> new StairBlock(() -> CYAN_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_STAIRS = registerBlock(
          "light_blue_kaolin_stairs",
          () -> new StairBlock(() -> LIGHT_BLUE_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_STAIRS = registerBlock(
          "blue_kaolin_stairs",
          () -> new StairBlock(() -> BLUE_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_STAIRS = registerBlock(
          "purple_kaolin_stairs",
          () -> new StairBlock(() -> PURPLE_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_STAIRS = registerBlock(
          "magenta_kaolin_stairs",
          () -> new StairBlock(() -> MAGENTA_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );
  public static final RegistryObject<Block> PINK_KAOLIN_STAIRS = registerBlock(
          "pink_kaolin_stairs",
          () -> new StairBlock(() -> PINK_KAOLIN.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))
  );

  public static final RegistryObject<Block> KAOLIN_SLAB = registerBlock(
      "kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> WHITE_KAOLIN_SLAB = registerBlock(
      "white_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_SLAB = registerBlock(
      "light_gray_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GRAY_KAOLIN_SLAB = registerBlock(
      "gray_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLACK_KAOLIN_SLAB = registerBlock(
      "black_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BROWN_KAOLIN_SLAB = registerBlock(
      "brown_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> RED_KAOLIN_SLAB = registerBlock(
      "red_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_SLAB = registerBlock(
      "orange_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_SLAB = registerBlock(
      "yellow_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIME_KAOLIN_SLAB = registerBlock(
      "lime_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GREEN_KAOLIN_SLAB = registerBlock(
      "green_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> CYAN_KAOLIN_SLAB = registerBlock(
      "cyan_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_SLAB = registerBlock(
      "light_blue_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLUE_KAOLIN_SLAB = registerBlock(
      "blue_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_SLAB = registerBlock(
      "purple_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_SLAB = registerBlock(
      "magenta_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PINK_KAOLIN_SLAB = registerBlock(
      "pink_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> KAOLIN_BRICKS = registerBlock(
      "kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICKS = registerBlock(
      "white_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICKS = registerBlock(
      "light_gray_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICKS = registerBlock(
      "gray_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICKS = registerBlock(
      "black_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICKS = registerBlock(
      "brown_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICKS = registerBlock(
      "red_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICKS = registerBlock(
      "orange_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICKS = registerBlock(
      "yellow_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICKS = registerBlock(
      "lime_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICKS = registerBlock(
      "green_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICKS = registerBlock(
      "cyan_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICKS = registerBlock(
      "light_blue_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICKS = registerBlock(
      "blue_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICKS = registerBlock(
      "purple_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICKS = registerBlock(
      "magenta_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICKS = registerBlock(
      "pink_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> KAOLIN_BRICK_STAIRS = registerBlock(
          "kaolin_brick_stairs",
          () -> new StairBlock(() -> KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICK_STAIRS = registerBlock(
          "white_kaolin_brick_stairs",
          () -> new StairBlock(() -> WHITE_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
          "light_gray_kaolin_brick_stairs",
          () -> new StairBlock(() -> LIGHT_GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
          "gray_kaolin_brick_stairs",
          () -> new StairBlock(() -> GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICK_STAIRS = registerBlock(
          "black_kaolin_brick_stairs",
          () -> new StairBlock(() -> BLACK_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICK_STAIRS = registerBlock(
          "brown_kaolin_brick_stairs",
          () -> new StairBlock(() -> BROWN_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICK_STAIRS = registerBlock(
          "red_kaolin_brick_stairs",
          () -> new StairBlock(() -> RED_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICK_STAIRS = registerBlock(
          "orange_kaolin_brick_stairs",
          () -> new StairBlock(() -> ORANGE_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICK_STAIRS = registerBlock(
          "yellow_kaolin_brick_stairs",
          () -> new StairBlock(() -> YELLOW_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICK_STAIRS = registerBlock(
          "lime_kaolin_brick_stairs",
          () -> new StairBlock(() -> LIME_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICK_STAIRS = registerBlock(
          "green_kaolin_brick_stairs",
          () -> new StairBlock(() -> GREEN_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICK_STAIRS = registerBlock(
          "cyan_kaolin_brick_stairs",
          () -> new StairBlock(() -> CYAN_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
          "light_blue_kaolin_brick_stairs",
          () -> new StairBlock(() -> LIGHT_BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
          "blue_kaolin_brick_stairs",
          () -> new StairBlock(() -> BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICK_STAIRS = registerBlock(
          "purple_kaolin_brick_stairs",
          () -> new StairBlock(() -> PURPLE_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICK_STAIRS = registerBlock(
          "magenta_kaolin_brick_stairs",
          () -> new StairBlock(() -> MAGENTA_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICK_STAIRS = registerBlock(
          "pink_kaolin_brick_stairs",
          () -> new StairBlock(() -> PINK_KAOLIN_BRICKS.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> KAOLIN_BRICK_SLAB = registerBlock(
      "kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICK_SLAB = registerBlock(
      "white_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "light_gray_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "gray_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICK_SLAB = registerBlock(
      "black_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICK_SLAB = registerBlock(
      "brown_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICK_SLAB = registerBlock(
      "red_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICK_SLAB = registerBlock(
      "orange_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICK_SLAB = registerBlock(
      "yellow_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICK_SLAB = registerBlock(
      "lime_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICK_SLAB = registerBlock(
      "green_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICK_SLAB = registerBlock(
      "cyan_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "light_blue_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "blue_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICK_SLAB = registerBlock(
      "purple_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICK_SLAB = registerBlock(
      "magenta_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICK_SLAB = registerBlock(
      "pink_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F))

  );
  public static final RegistryObject<Block> WHITE_CHALK = registerBlock(
      "white_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK = registerBlock(
      "light_gray_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GRAY_CHALK = registerBlock(
      "gray_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLACK_CHALK = registerBlock(
      "black_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BROWN_CHALK = registerBlock(
      "brown_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> RED_CHALK = registerBlock(
      "red_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> ORANGE_CHALK = registerBlock(
      "orange_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> YELLOW_CHALK = registerBlock(
      "yellow_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIME_CHALK = registerBlock(
      "lime_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GREEN_CHALK = registerBlock(
      "green_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> CYAN_CHALK = registerBlock(
      "cyan_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK = registerBlock(
      "light_blue_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLUE_CHALK = registerBlock(
      "blue_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PURPLE_CHALK = registerBlock(
      "purple_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> MAGENTA_CHALK = registerBlock(
      "magenta_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PINK_CHALK = registerBlock(
      "pink_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> WHITE_CHALK_STAIRS = registerBlock(
          "white_chalk_stairs",
          () -> new StairBlock(() -> WHITE_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK_STAIRS = registerBlock(
          "light_gray_chalk_stairs",
          () -> new StairBlock(() -> LIGHT_GRAY_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GRAY_CHALK_STAIRS = registerBlock(
          "gray_chalk_stairs",
          () -> new StairBlock(() -> GRAY_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLACK_CHALK_STAIRS = registerBlock(
          "black_chalk_stairs",
          () -> new StairBlock(() -> BLACK_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BROWN_CHALK_STAIRS = registerBlock(
          "brown_chalk_stairs",
          () -> new StairBlock(() -> BROWN_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> RED_CHALK_STAIRS = registerBlock(
          "red_chalk_stairs",
          () -> new StairBlock(() -> RED_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> ORANGE_CHALK_STAIRS = registerBlock(
          "orange_chalk_stairs",
          () -> new StairBlock(() -> ORANGE_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> YELLOW_CHALK_STAIRS = registerBlock(
          "yellow_chalk_stairs",
          () -> new StairBlock(() -> YELLOW_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIME_CHALK_STAIRS = registerBlock(
          "lime_chalk_stairs",
          () -> new StairBlock(() -> LIME_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GREEN_CHALK_STAIRS = registerBlock(
          "green_chalk_stairs",
          () -> new StairBlock(() -> GREEN_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> CYAN_CHALK_STAIRS = registerBlock(
          "cyan_chalk_stairs",
          () -> new StairBlock(() -> CYAN_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK_STAIRS = registerBlock(
          "light_blue_chalk_stairs",
          () -> new StairBlock(() -> LIGHT_BLUE_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLUE_CHALK_STAIRS = registerBlock(
          "blue_chalk_stairs",
          () -> new StairBlock(() -> BLUE_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PURPLE_CHALK_STAIRS = registerBlock(
          "purple_chalk_stairs",
          () -> new StairBlock(() -> PURPLE_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> MAGENTA_CHALK_STAIRS = registerBlock(
          "magenta_chalk_stairs",
          () -> new StairBlock(() -> MAGENTA_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PINK_CHALK_STAIRS = registerBlock(
          "pink_chalk_stairs",
          () -> new StairBlock(() -> PINK_CHALK.get().defaultBlockState(),
                  BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> WHITE_CHALK_SLAB = registerBlock(
      "white_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK_SLAB = registerBlock(
      "light_gray_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GRAY_CHALK_SLAB = registerBlock(
      "gray_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLACK_CHALK_SLAB = registerBlock(
      "black_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BROWN_CHALK_SLAB = registerBlock(
      "brown_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> RED_CHALK_SLAB = registerBlock(
      "red_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> ORANGE_CHALK_SLAB = registerBlock(
      "orange_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> YELLOW_CHALK_SLAB = registerBlock(
      "yellow_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIME_CHALK_SLAB = registerBlock(
      "lime_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> GREEN_CHALK_SLAB = registerBlock(
      "green_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> CYAN_CHALK_SLAB = registerBlock(
      "cyan_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK_SLAB = registerBlock(
      "light_blue_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> BLUE_CHALK_SLAB = registerBlock(
      "blue_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PURPLE_CHALK_SLAB = registerBlock(
      "purple_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> MAGENTA_CHALK_SLAB = registerBlock(
      "magenta_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );
  public static final RegistryObject<Block> PINK_CHALK_SLAB = registerBlock(
      "pink_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F))

  );

  private static final List<WoodSet> WOOD_SETS = ImmutableList.of(
          NSBlocks.REDWOOD,
          NSBlocks.SUGI,
          NSBlocks.WISTERIA,
          NSBlocks.FIR,
          NSBlocks.WILLOW,
          NSBlocks.ASPEN,
          NSBlocks.MAPLE,
          NSBlocks.CYPRESS,
          NSBlocks.OLIVE,
          NSBlocks.JOSHUA,
          NSBlocks.GHAF,
          NSBlocks.PALO_VERDE,
          NSBlocks.COCONUT,
          NSBlocks.CEDAR,
          NSBlocks.LARCH,
          NSBlocks.MAHOGANY,
          NSBlocks.SAXAUL
  );

  public static List<WoodSet> getWoodSets() {
    return WOOD_SETS;
  }

}
