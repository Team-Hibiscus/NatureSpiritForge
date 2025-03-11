package net.hibiscus.naturespirit.registration;

import com.google.common.base.Suppliers;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.hibiscus.naturespirit.items.AzollaItem;
import net.hibiscus.naturespirit.items.CheeseArrowItem;
import net.hibiscus.naturespirit.items.DesertTurnipItem;
import net.hibiscus.naturespirit.items.PizzaItem;
import net.hibiscus.naturespirit.registration.sets.FlowerSet;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
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

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class NSMiscBlocks {

  public static final DeferredRegister<Block> MISC_BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
  public static final DeferredRegister<Item> MISC_ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

  public static final RegistryObject<Block> RED_MOSS_BLOCK = registerBlock("red_moss_block",
          () -> new RedMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.DESTROY)),
      Items.MOSS_BLOCK, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> RED_MOSS_CARPET = registerBlock("red_moss_carpet",
          () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)),
      Items.MOSS_CARPET, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> SANDY_SOIL = registerBlock("sandy_soil",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).sound(SoundType.GRAVEL)),
      Blocks.FARMLAND, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> PINK_SAND = registerBlock("pink_sand", () -> new SandBlock(14331784,
      BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)),
      Blocks.RED_SANDSTONE, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> PINK_SANDSTONE = registerBlock("pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)),
      PINK_SAND.get(), CreativeModeTabs.NATURAL_BLOCKS, Blocks.CUT_RED_SANDSTONE_SLAB, CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> PINK_SANDSTONE_STAIRS = registerBlock("pink_sandstone_stairs",
          () -> new StairBlock(PINK_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(PINK_SANDSTONE.get())),
      PINK_SANDSTONE.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> PINK_SANDSTONE_SLAB = registerBlock("pink_sandstone_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)),
      PINK_SANDSTONE_STAIRS.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> PINK_SANDSTONE_WALL = registerBlock("pink_sandstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(PINK_SANDSTONE.get()).forceSolidOn()),
      PINK_SANDSTONE_SLAB.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> CHISELED_PINK_SANDSTONE = registerBlock("chiseled_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)),
      PINK_SANDSTONE_WALL.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE = registerBlock("smooth_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)),
      CHISELED_PINK_SANDSTONE.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE_STAIRS = registerBlock("smooth_pink_sandstone_stairs",
          () -> new StairBlock(SMOOTH_PINK_SANDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_PINK_SANDSTONE.get())),
      SMOOTH_PINK_SANDSTONE.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> SMOOTH_PINK_SANDSTONE_SLAB = registerBlock("smooth_pink_sandstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SMOOTH_PINK_SANDSTONE.get())), SMOOTH_PINK_SANDSTONE_STAIRS.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> CUT_PINK_SANDSTONE = registerBlock("cut_pink_sandstone",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)),
      SMOOTH_PINK_SANDSTONE_SLAB.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> CUT_PINK_SANDSTONE_SLAB = registerBlock("cut_pink_sandstone_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)),
      CUT_PINK_SANDSTONE.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> TALL_FRIGID_GRASS = registerTallPlantBlock("tall_frigid_grass", () -> new SemiTallGrassBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      Blocks.LARGE_FERN, 0.3f);

  public static final RegistryObject<Block> FRIGID_GRASS = registerPlantBlock("frigid_grass", () -> new NSFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), (DoublePlantBlock) TALL_FRIGID_GRASS.get()),
      Blocks.FERN, 0.3f);

  public static final RegistryObject<Block> TALL_SCORCHED_GRASS = registerTallPlantBlock("tall_scorched_grass", () -> new TallLargeDesertFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      Blocks.LARGE_FERN, 0.3f);

  public static final RegistryObject<Block> SCORCHED_GRASS = registerPlantBlock("scorched_grass", () -> new LargeDesertFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY), (DoublePlantBlock) TALL_SCORCHED_GRASS.get()),
      Blocks.FERN, 0.3f);

  public static final RegistryObject<Block> TALL_BEACH_GRASS = registerTallPlantBlock("tall_beach_grass", () -> new TallLargeDesertFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      Blocks.LARGE_FERN, 0.3f);

  public static final RegistryObject<Block> BEACH_GRASS = registerPlantBlock("beach_grass", () -> new LargeDesertFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY), (DoublePlantBlock) TALL_BEACH_GRASS.get()),
      Blocks.FERN, 0.3f);

  public static final RegistryObject<Block> TALL_SEDGE_GRASS = registerTallPlantBlock("tall_sedge_grass", () -> new TallSedgeGrassBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      TALL_SCORCHED_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> SEDGE_GRASS = registerPlantBlock("sedge_grass", () -> new SedgeGrassBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY)),
      SCORCHED_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> LARGE_FLAXEN_FERN = registerTallPlantBlock("large_flaxen_fern", () -> new DoublePlantBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      TALL_SEDGE_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> FLAXEN_FERN = registerPlantBlock("flaxen_fern", () -> new NSFernBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY), (DoublePlantBlock) LARGE_FLAXEN_FERN.get()),
      SEDGE_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> TALL_OAT_GRASS = registerTallPlantBlock("tall_oat_grass", () -> new SemiTallGrassBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
          .pushReaction(PushReaction.DESTROY)), LARGE_FLAXEN_FERN.get(), 0.3f);

  public static final RegistryObject<Block> OAT_GRASS = registerPlantBlock("oat_grass", () -> new NSFernBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.SAND).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY), (DoublePlantBlock) TALL_OAT_GRASS.get()),
      FLAXEN_FERN.get(), 0.3f);

  public static final RegistryObject<Block> LARGE_LUSH_FERN = registerTallPlantBlock("large_lush_fern", () -> new DoublePlantBlock(
      BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
          .pushReaction(PushReaction.DESTROY)), TALL_OAT_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> LUSH_FERN = registerPlantBlock("lush_fern", () -> new NSFernBlock(
      BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
          .pushReaction(PushReaction.DESTROY), (DoublePlantBlock) LARGE_LUSH_FERN.get()), OAT_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> TALL_MELIC_GRASS = registerTallPlantBlock("tall_melic_grass", () -> new DoublePlantBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
          .pushReaction(PushReaction.DESTROY)), LARGE_LUSH_FERN.get(), 0.3f);

  public static final RegistryObject<Block> MELIC_GRASS = registerPlantBlock("melic_grass", () -> new NSFernBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
          .pushReaction(PushReaction.DESTROY), (DoublePlantBlock) TALL_MELIC_GRASS.get()), LUSH_FERN.get(), 0.3f);

  public static final RegistryObject<Block> GREEN_BEARBERRIES = registerPlantBlock("green_bearberries", () -> new BearberryBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY)),
      MELIC_GRASS.get(), 0.3f);

  public static final RegistryObject<Block> RED_BEARBERRIES = registerPlantBlock("red_bearberries", () -> new BearberryBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY)),
      GREEN_BEARBERRIES.get(), 0.3f);

  public static final RegistryObject<Block> PURPLE_BEARBERRIES = registerPlantBlock("purple_bearberries", () -> new BearberryBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
              .pushReaction(PushReaction.DESTROY)),
      RED_BEARBERRIES.get(), 0.3f);

  public static final RegistryObject<Block> GREEN_BITTER_SPROUTS = registerPlantBlock("green_bitter_sprouts", () -> new LargeSproutsBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      PURPLE_BEARBERRIES.get(), 0.3f);

  public static final RegistryObject<Block> RED_BITTER_SPROUTS = registerPlantBlock("red_bitter_sprouts", () -> new LargeSproutsBlock(
          BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
      GREEN_BITTER_SPROUTS.get(), 0.3f);

  public static final RegistryObject<Block> PURPLE_BITTER_SPROUTS = registerPlantBlock("purple_bitter_sprouts", () -> new LargeSproutsBlock(
      BlockBehaviour.Properties.of().noCollission().replaceable().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
          .pushReaction(PushReaction.DESTROY)), RED_BITTER_SPROUTS.get(), 0.3f);

  public static final RegistryObject<Block> CATTAIL = registerPlantBlock("cattail", () -> new CattailBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)),
      Blocks.LARGE_FERN, 0.4f);

  public static final RegistryObject<Block> AZOLLA = NSRegistryHelper.registerTransparentBlockWithoutTab("azolla", () -> new AzollaBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().noCollission()
          .sound(SoundType.LILY_PAD)));

  public static final RegistryObject<Item> AZOLLA_ITEM = registerItem("azolla", () -> new AzollaItem(AZOLLA.get(), new Item.Properties()), Items.LILY_PAD, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> ORNATE_SUCCULENT = registerTransparentBlockWithoutTab("ornate_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> DROWSY_SUCCULENT = registerTransparentBlockWithoutTab("drowsy_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> AUREATE_SUCCULENT = registerTransparentBlockWithoutTab("aureate_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> SAGE_SUCCULENT = registerTransparentBlockWithoutTab("sage_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> FOAMY_SUCCULENT = registerTransparentBlockWithoutTab("foamy_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> IMPERIAL_SUCCULENT = registerTransparentBlockWithoutTab("imperial_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> REGAL_SUCCULENT = registerTransparentBlockWithoutTab("regal_succulent",
          () -> new SucculentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollission().instabreak()));

  public static final RegistryObject<Block> ORNATE_WALL_SUCCULENT = registerTransparentBlockWithoutTab("ornate_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(ORNATE_SUCCULENT.get())));

  public static final RegistryObject<Block> DROWSY_WALL_SUCCULENT = registerTransparentBlockWithoutTab("drowsy_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(DROWSY_SUCCULENT.get())));

  public static final RegistryObject<Block> AUREATE_WALL_SUCCULENT = registerTransparentBlockWithoutTab("aureate_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(AUREATE_SUCCULENT.get())));

  public static final RegistryObject<Block> SAGE_WALL_SUCCULENT = registerTransparentBlockWithoutTab("sage_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(SAGE_SUCCULENT.get())));

  public static final RegistryObject<Block> FOAMY_WALL_SUCCULENT = registerTransparentBlockWithoutTab("foamy_wall_succulent", () -> new SucculentWallBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(FOAMY_SUCCULENT.get())));

  public static final RegistryObject<Block> IMPERIAL_WALL_SUCCULENT = registerTransparentBlockWithoutTab("imperial_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(IMPERIAL_SUCCULENT.get())));

  public static final RegistryObject<Block> REGAL_WALL_SUCCULENT = registerTransparentBlockWithoutTab("regal_wall_succulent",
          () -> new SucculentWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.GRASS).noCollission().instabreak().dropsLike(REGAL_SUCCULENT.get())));

  public static final RegistryObject<Item> ORNATE_SUCCULENT_ITEM = registerItem("ornate_succulent",
          () -> new StandingAndWallBlockItem(ORNATE_SUCCULENT.get(), ORNATE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), Items.PITCHER_PLANT, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> DROWSY_SUCCULENT_ITEM = registerItem("drowsy_succulent",
          () -> new StandingAndWallBlockItem(DROWSY_SUCCULENT.get(), DROWSY_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), ORNATE_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> AUREATE_SUCCULENT_ITEM = registerItem("aureate_succulent",
          () -> new StandingAndWallBlockItem(AUREATE_SUCCULENT.get(), AUREATE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), DROWSY_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> SAGE_SUCCULENT_ITEM = registerItem("sage_succulent",
          () -> new StandingAndWallBlockItem(SAGE_SUCCULENT.get(), SAGE_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), AUREATE_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> FOAMY_SUCCULENT_ITEM = registerItem("foamy_succulent",
          () -> new StandingAndWallBlockItem(FOAMY_SUCCULENT.get(), FOAMY_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), SAGE_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> IMPERIAL_SUCCULENT_ITEM = registerItem("imperial_succulent",
          () -> new StandingAndWallBlockItem(IMPERIAL_SUCCULENT.get(), IMPERIAL_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), FOAMY_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> REGAL_SUCCULENT_ITEM = registerItem("regal_succulent",
          () -> new StandingAndWallBlockItem(REGAL_SUCCULENT.get(), REGAL_WALL_SUCCULENT.get(), new Item.Properties(), Direction.DOWN), IMPERIAL_SUCCULENT_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> SHIITAKE_MUSHROOM = registerPlantBlock("shiitake_mushroom", () -> new ShiitakeMushroomPlantBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
          .hasPostProcess(NSRegistryHelper::always).pushReaction(PushReaction.DESTROY), NSConfiguredFeatures.HUGE_SHIITAKE_MUSHROOM), Blocks.RED_MUSHROOM, 0.1F);

  public static final RegistryObject<Block> SHIITAKE_MUSHROOM_BLOCK = registerBlock("shiitake_mushroom_block",
          () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()),

      Blocks.RED_MUSHROOM_BLOCK, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> GRAY_POLYPORE = registerPlantBlock("gray_polypore", () -> new PolyporeBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1)
          .hasPostProcess(NSRegistryHelper::always).pushReaction(PushReaction.DESTROY), NSConfiguredFeatures.GRAY_POLYPORE), SHIITAKE_MUSHROOM.get(), 0.1F);

  public static final RegistryObject<Block> GRAY_POLYPORE_BLOCK = registerBlock("gray_polypore_block", () -> new HugeMushroomBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WOOD).ignitedByLava()),
      SHIITAKE_MUSHROOM_BLOCK.get(), CreativeModeTabs.NATURAL_BLOCKS);


  public static final RegistryObject<Block> POTTED_SCORCHED_GRASS = registerTransparentBlockWithoutTab("potted_scorched_grass",
          () -> new FlowerPotBlock(SCORCHED_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_BEACH_GRASS = registerTransparentBlockWithoutTab("potted_beach_grass",
          () -> new FlowerPotBlock(BEACH_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_SEDGE_GRASS = registerTransparentBlockWithoutTab("potted_sedge_grass",
          () -> new FlowerPotBlock(SEDGE_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_FLAXEN_FERN = registerTransparentBlockWithoutTab("potted_flaxen_fern",
          () -> new FlowerPotBlock(FLAXEN_FERN.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_OAT_GRASS = registerTransparentBlockWithoutTab("potted_oat_grass",
          () -> new FlowerPotBlock(OAT_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_MELIC_GRASS = registerTransparentBlockWithoutTab("potted_melic_grass",
          () -> new FlowerPotBlock(MELIC_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_LUSH_FERN = registerTransparentBlockWithoutTab("potted_lush_fern",
          () -> new FlowerPotBlock(LUSH_FERN.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_FRIGID_GRASS = registerTransparentBlockWithoutTab("potted_frigid_grass",
          () -> new FlowerPotBlock(FRIGID_GRASS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_GREEN_BEARBERRIES = registerTransparentBlockWithoutTab("potted_green_bearberries",
          () -> new FlowerPotBlock(GREEN_BEARBERRIES.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_RED_BEARBERRIES = registerTransparentBlockWithoutTab("potted_red_bearberries",
          () -> new FlowerPotBlock(RED_BEARBERRIES.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_PURPLE_BEARBERRIES = registerTransparentBlockWithoutTab("potted_purple_bearberries",
          () -> new FlowerPotBlock(PURPLE_BEARBERRIES.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_ORNATE_SUCCULENT = registerTransparentBlockWithoutTab("potted_ornate_succulent",
          () -> new FlowerPotBlock(ORNATE_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_DROWSY_SUCCULENT = registerTransparentBlockWithoutTab("potted_drowsy_succulent",
          () -> new FlowerPotBlock(DROWSY_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_AUREATE_SUCCULENT = registerTransparentBlockWithoutTab("potted_aureate_succulent",
          () -> new FlowerPotBlock(AUREATE_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_SAGE_SUCCULENT = registerTransparentBlockWithoutTab("potted_sage_succulent",
          () -> new FlowerPotBlock(SAGE_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_FOAMY_SUCCULENT = registerTransparentBlockWithoutTab("potted_foamy_succulent",
          () -> new FlowerPotBlock(FOAMY_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_IMPERIAL_SUCCULENT = registerTransparentBlockWithoutTab("potted_imperial_succulent",
          () -> new FlowerPotBlock(IMPERIAL_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_REGAL_SUCCULENT = registerTransparentBlockWithoutTab("potted_regal_succulent",
          () -> new FlowerPotBlock(REGAL_SUCCULENT.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

  public static final RegistryObject<Block> POTTED_SHIITAKE_MUSHROOM = registerTransparentBlockWithoutTab("potted_shiitake_mushroom",
          () -> new FlowerPotBlock(SHIITAKE_MUSHROOM.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


  public static final FlowerSet LAVENDER = new FlowerSet("lavender", Items.PURPLE_DYE, Items.PEONY, FlowerSet.FlowerPreset.BIG_TALL);

  public static final FlowerSet BLEEDING_HEART = new FlowerSet("bleeding_heart", Items.PINK_DYE, LAVENDER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_TALL);

  public static final FlowerSet BLUE_BULBS = new FlowerSet("blue_bulbs", Items.BLUE_DYE, BLEEDING_HEART.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_TALL);

  public static final FlowerSet CARNATION = new FlowerSet("carnation", Items.RED_DYE, BLUE_BULBS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_TALL);

  public static final FlowerSet GARDENIA = new FlowerSet("gardenia", Items.WHITE_DYE, CARNATION.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.TALL);

  public static final FlowerSet SNAPDRAGON = new FlowerSet("snapdragon", Items.PINK_DYE, GARDENIA.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.TALL);

  public static final FlowerSet FOXGLOVE = new FlowerSet("foxglove", Items.PURPLE_DYE, SNAPDRAGON.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.TALL);
  public static final FlowerSet BEGONIA = new FlowerSet("begonia", Items.ORANGE_DYE, FOXGLOVE.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.TALL);

  public static final FlowerSet MARIGOLD = new FlowerSet("marigold", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, Items.LILY_OF_THE_VALLEY, FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet BLUEBELL = new FlowerSet("bluebell", Items.BLUE_DYE, MobEffects.DIG_SPEED, MARIGOLD.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet TIGER_LILY = new FlowerSet("tiger_lily", Items.ORANGE_DYE, MobEffects.FIRE_RESISTANCE, BLUEBELL.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet PURPLE_WILDFLOWER = new FlowerSet("purple_wildflower", Items.PURPLE_DYE, MobEffects.SLOW_FALLING, TIGER_LILY.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet YELLOW_WILDFLOWER = new FlowerSet("yellow_wildflower", Items.YELLOW_DYE, MobEffects.SLOW_FALLING, PURPLE_WILDFLOWER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet RED_HEATHER = new FlowerSet("red_heather", Items.RED_DYE, MobEffects.FIRE_RESISTANCE, YELLOW_WILDFLOWER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet WHITE_HEATHER = new FlowerSet("white_heather", Items.WHITE_DYE, MobEffects.FIRE_RESISTANCE, RED_HEATHER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet PURPLE_HEATHER = new FlowerSet("purple_heather", Items.PURPLE_DYE, MobEffects.FIRE_RESISTANCE, WHITE_HEATHER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet ANEMONE = new FlowerSet("anemone", Items.MAGENTA_DYE, MobEffects.DAMAGE_RESISTANCE, PURPLE_HEATHER.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.MID_SMALL);

  public static final FlowerSet DWARF_BLOSSOMS = new FlowerSet("dwarf_blossoms", Items.PINK_DYE, MobEffects.DAMAGE_RESISTANCE, ANEMONE.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.MID_SMALL);

  public static final FlowerSet PROTEA = new FlowerSet("protea", Items.PINK_DYE, MobEffects.WATER_BREATHING, DWARF_BLOSSOMS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.MID_SMALL);

  public static final FlowerSet HIBISCUS = new FlowerSet("hibiscus", Items.RED_DYE, MobEffects.LUCK, PROTEA.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.SMALL);

  public static final FlowerSet BLUE_IRIS = new FlowerSet("blue_iris", Items.LIGHT_BLUE_DYE, MobEffects.DAMAGE_BOOST, HIBISCUS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.SMALL);

  public static final FlowerSet BLACK_IRIS = new FlowerSet("black_iris", Items.BLACK_DYE, MobEffects.DAMAGE_BOOST, BLUE_IRIS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.SMALL);

  public static final FlowerSet RUBY_BLOSSOMS = new FlowerSet("ruby_blossoms", Items.RED_DYE, MobEffects.JUMP, BLACK_IRIS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_SMALL);

  public static final FlowerSet SILVERBUSH = new FlowerSet("silverbush", RUBY_BLOSSOMS.getFlowerBlock().get().asItem(), FlowerSet.FlowerPreset.BIG_TALL);


  public static final RegistryObject<Block> HELVOLA = registerTransparentBlockWithoutTab("helvola", () -> new WaterFlowerbedBlock(
      BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().friction(0.8F).sound(SoundType.LILY_PAD)));

  public static final RegistryObject<Item> HELVOLA_PAD_ITEM = registerItem("helvola_pad", () -> new PlaceOnWaterBlockItem(HELVOLA.get(), new Item.Properties()),
      Items.LILY_PAD, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Item> HELVOLA_FLOWER_ITEM = registerItem("helvola_flower", () -> new Item(new Item.Properties()),
      HELVOLA_PAD_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> LOTUS_FLOWER = registerTransparentBlockWithoutTab("lotus_flower", () -> new LotusFlowerBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion().instabreak().sound(SoundType.LILY_PAD)));

  public static final RegistryObject<Item> LOTUS_FLOWER_ITEM = registerItem("lotus_flower", () -> new PlaceOnWaterBlockItem(LOTUS_FLOWER.get(), new Item.Properties()),
      HELVOLA_FLOWER_ITEM.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> LOTUS_STEM = registerPlantBlock("lotus_stem", () -> new LotusStemBlock(
      BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).pushReaction(PushReaction.DESTROY).noCollission().noOcclusion().instabreak().sound(SoundType.LILY_PAD), LOTUS_FLOWER.get()), LOTUS_FLOWER.get(), 0.2F);

  public static final RegistryObject<Block> ALLUAUDIA = registerPlantBlock("alluaudia", () -> new GrowingBranchingTrunkBlock(
      BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff()), Items.CACTUS, .2f);

  public static final RegistryObject<Block> STRIPPED_ALLUAUDIA = registerPlantBlock("stripped_alluaudia", () -> new GrowingBranchingTrunkBlock(
      BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.5f).strength(.5f).forceSolidOff()), NSMiscBlocks.ALLUAUDIA.get(), .2f);

  public static final RegistryObject<Block> STRIPPED_ALLUAUDIA_BUNDLE = registerPlantBlock("stripped_alluaudia_bundle",
          () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f)){
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
          }, STRIPPED_ALLUAUDIA.get(), .2f);

  public static final RegistryObject<Block> ALLUAUDIA_BUNDLE = registerPlantBlock("alluaudia_bundle",
          () -> new StrippableLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noOcclusion().sound(SoundType.VINE).destroyTime(.6f).strength(.6f), STRIPPED_ALLUAUDIA_BUNDLE.get()), STRIPPED_ALLUAUDIA.get(), .2f);

  public static final FoodProperties OLIVE_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.4F).build();

  public static final RegistryObject<Item> OLIVES = registerPlantItem("olives", () -> new Item(new Item.Properties().food(OLIVE_COMPONENT)), Items.BEETROOT, CreativeModeTabs.FOOD_AND_DRINKS, 0.3F);

  public static final RegistryObject<Block> DESERT_TURNIP_ROOT_BLOCK = registerBlock("desert_turnip_root_block",
          () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.PODZOL).strength(1.0F).sound(SoundType.ROOTS)),
      Blocks.SHROOMLIGHT, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> DESERT_TURNIP_BLOCK = registerBlock("desert_turnip_block", () -> new DesertTurnipBlock(
      BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.ICE).strength(1.0F).sound(SoundType.ROOTS)),
      DESERT_TURNIP_ROOT_BLOCK.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> DESERT_TURNIP_STEM = registerTransparentBlockWithoutTab("desert_turnip_stem",
          () -> new DesertTurnipStemBlock((DesertTurnipBlock) DESERT_TURNIP_BLOCK.get(), DESERT_TURNIP_ROOT_BLOCK.get(),
          BlockBehaviour.Properties.of().noCollission().instabreak().randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

  public static final FoodProperties DESERT_TURNIP_FOOD_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).fast().build();

  public static final RegistryObject<Item> DESERT_TURNIP = registerItem("desert_turnip", () -> new DesertTurnipItem(DESERT_TURNIP_STEM.get(), (new Item.Properties()).food(DESERT_TURNIP_FOOD_COMPONENT)), Items.BEETROOT, CreativeModeTabs.FOOD_AND_DRINKS);

  public static final RegistryObject<Item> CHALK_POWDER = registerItem("chalk_powder", () -> new Item((new Item.Properties())), Items.HONEYCOMB, CreativeModeTabs.INGREDIENTS);

  public static final RegistryObject<Block> CHEESE_BLOCK = registerBlockWithoutTab("cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(2.0F, 1.0F).sound(SoundType.AZALEA_LEAVES)));

  public static final RegistryObject<Item> CHEESE_BUCKET = registerItem("cheese_bucket", () -> new SolidBucketItem(CHEESE_BLOCK.get(), SoundEvents.BUCKET_EMPTY, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)), Items.MILK_BUCKET, CreativeModeTabs.FOOD_AND_DRINKS);

  public static final RegistryObject<Item> CHEESE_ARROW =  registerItem("cheese_arrow", () -> new CheeseArrowItem(new Item.Properties()), Items.SPECTRAL_ARROW, CreativeModeTabs.COMBAT);

//  public static final RegistryObject<Block> MILK_CAULDRON = registerBlockWithoutTab("milk_cauldron", () -> new MilkCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

//  public static final RegistryObject<Block> CHEESE_CAULDRON = registerBlockWithoutTab("cheese_cauldron", () -> new CheeseCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).dropsLike(Blocks.CAULDRON)));

  public static final FoodProperties STANDARD_PIZZA_COMPONENT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).build();

  public static final RegistryObject<Block> PIZZA_BLOCK = registerBlockWithoutTab("pizza_block", () -> new PizzaBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

  public static final RegistryObject<BlockEntityType<PizzaBlockEntity>> PIZZA_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("pizza_block_entity", () -> BlockEntityType.Builder.of(PizzaBlockEntity::new, PIZZA_BLOCK.get()).build(null));

  public static final RegistryObject<Item> WHOLE_PIZZA = registerItem("whole_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)), Items.BREAD, CreativeModeTabs.FOOD_AND_DRINKS);

  public static final RegistryObject<Item> THREE_QUARTERS_PIZZA = registerItem("three_quarters_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));

  public static final RegistryObject<Item> HALF_PIZZA = registerItem("half_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));

  public static final RegistryObject<Item> QUARTER_PIZZA = registerItem("quarter_pizza", () -> new PizzaItem(PIZZA_BLOCK.get(), new Item.Properties().stacksTo(1).food(STANDARD_PIZZA_COMPONENT)));

  public static final RegistryObject<Item> CALCITE_SHARD = registerItem("calcite_shard", () -> new Item(new Item.Properties()), Items.AMETHYST_SHARD, CreativeModeTabs.INGREDIENTS);

  public static final RegistryObject<Block> CALCITE_CLUSTER = registerBlock("calcite_cluster", () -> new AmethystClusterBlock(7, 3,
      BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).forceSolidOn().noOcclusion().randomTicks().sound(SoundType.CALCITE).strength(1.5F)
          .pushReaction(PushReaction.DESTROY)), Blocks.AMETHYST_CLUSTER, CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> LARGE_CALCITE_BUD = registerBlock("large_calcite_bud",
          () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY)),
      CALCITE_CLUSTER.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final RegistryObject<Block> SMALL_CALCITE_BUD = registerBlock("small_calcite_bud",
          () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(CALCITE_CLUSTER.get()).sound(SoundType.CALCITE).forceSolidOn().pushReaction(PushReaction.DESTROY)),
      LARGE_CALCITE_BUD.get(), CreativeModeTabs.NATURAL_BLOCKS);

  public static final StoneSet TRAVERTINE = new StoneSet(new ResourceLocation(MOD_ID, "travertine"), MapColor.COLOR_LIGHT_GRAY, Items.POLISHED_ANDESITE_SLAB, Items.ANDESITE, 1.5F, true, true,
      true, true);

  public static final StoneSet CHERT = new StoneSet(new ResourceLocation(MOD_ID, "chert"), MapColor.WOOD, TRAVERTINE.getTilesSlab().get().asItem(), TRAVERTINE.getBase().get().asItem(), .9F, false,
      true, false, true, true);

  public static final RegistryObject<Block> CHERT_GOLD_ORE = registerBlock("chert_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).mapColor(MapColor.WOOD).strength(.9f), ConstantInt.of(0)), Items.GOLD_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_IRON_ORE = registerBlock("chert_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).mapColor(MapColor.WOOD).strength(.9f), ConstantInt.of(0)), Items.IRON_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_COAL_ORE = registerBlock("chert_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(0, 2)), Items.COAL_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_LAPIS_ORE = registerBlock("chert_lapis_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(2, 5)), Items.LAPIS_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_DIAMOND_ORE = registerBlock("chert_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.WOOD).strength(.9f), UniformInt.of(3, 7)), Items.DIAMOND_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_REDSTONE_ORE = registerBlock("chert_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).mapColor(MapColor.WOOD).strength(.6f)), Items.REDSTONE_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_EMERALD_ORE = registerBlock("chert_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).mapColor(MapColor.WOOD).strength(.6f), UniformInt.of(3, 7)), Items.EMERALD_ORE, CreativeModeTabs.NATURAL_BLOCKS);
  public static final RegistryObject<Block> CHERT_COPPER_ORE = registerBlock("chert_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE).mapColor(MapColor.WOOD).strength(.6f), ConstantInt.of(0)), Items.COPPER_ORE, CreativeModeTabs.NATURAL_BLOCKS);


  public static final Supplier<BlockSetType> PAPER_BLOCK_SET = Suppliers.memoize(() -> BlockSetType.register(new BlockSetType("paper", true,SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)));

  public static final Supplier<WoodType> PAPER_WOOD_TYPE = Suppliers.memoize(() -> WoodType.register(new WoodType("paper", PAPER_BLOCK_SET.get())));

  public static final RegistryObject<Block> PAPER_BLOCK = registerBlock("paper_block", () -> new Block(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get())){
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
  }, Items.WARPED_BUTTON, CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> PAPER_PANEL = registerBlock("paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get()).noOcclusion()){
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
  }, PAPER_BLOCK.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> PAPER_DOOR = registerTransparentBlock("paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
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
  }, PAPER_PANEL.get(), CreativeModeTabs.BUILDING_BLOCKS);

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
  }, PAPER_DOOR.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> FRAMED_PAPER_BLOCK = registerBlock("framed_paper_block", () -> new Block(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get())){
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
  }, PAPER_TRAPDOOR.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> FRAMED_PAPER_PANEL = registerBlock("framed_paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get()).noOcclusion()){
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
  }, FRAMED_PAPER_BLOCK.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> FRAMED_PAPER_DOOR = registerTransparentBlock("framed_paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
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
  }, FRAMED_PAPER_PANEL.get(), CreativeModeTabs.BUILDING_BLOCKS);

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
  }, FRAMED_PAPER_DOOR.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> BLOOMING_PAPER_BLOCK = registerBlock("blooming_paper_block", () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get())){
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
  }, FRAMED_PAPER_TRAPDOOR.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> BLOOMING_PAPER_PANEL = registerBlock("blooming_paper_panel", () -> new IronBarsBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getPlanks().get()).noOcclusion()){
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
  }, BLOOMING_PAPER_BLOCK.get(), CreativeModeTabs.BUILDING_BLOCKS);

  public static final RegistryObject<Block> BLOOMING_PAPER_DOOR = registerTransparentBlock("blooming_paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getDoor().get()), PAPER_BLOCK_SET.get()){
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
  }, BLOOMING_PAPER_PANEL.get(), CreativeModeTabs.BUILDING_BLOCKS);

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
  }, BLOOMING_PAPER_DOOR.get(), CreativeModeTabs.BUILDING_BLOCKS);


  public static final RegistryObject<Block> PAPER_SIGN = registerBlockWithoutTab("paper_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getSign().get()), PAPER_WOOD_TYPE.get()));

  public static final RegistryObject<Block> PAPER_WALL_SIGN = registerBlockWithoutTab("paper_wall_sign",
          () -> new WallSignBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getSign().get()).dropsLike(PAPER_SIGN.get()), PAPER_WOOD_TYPE.get()));

  public static final RegistryObject<Block> PAPER_HANGING_SIGN = registerBlockWithoutTab("paper_hanging_sign",
          () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getHangingSign().get()), PAPER_WOOD_TYPE.get()));

  public static final RegistryObject<Block> PAPER_WALL_HANGING_SIGN = registerBlockWithoutTab("paper_wall_hanging_sign",
          () -> new WallHangingSignBlock(BlockBehaviour.Properties.copy(NSWoods.SUGI.getHangingSign().get()).dropsLike(PAPER_HANGING_SIGN.get()), PAPER_WOOD_TYPE.get()));

  public static final RegistryObject<Item> PAPER_SIGN_ITEM = registerItem("paper_sign", () -> new SignItem(new Item.Properties().stacksTo(16), PAPER_SIGN.get(), PAPER_WALL_SIGN.get()), Items.WARPED_SIGN, CreativeModeTabs.FUNCTIONAL_BLOCKS);

  public static final RegistryObject<Item> PAPER_HANGING_SIGN_ITEM = registerItem("paper_hanging_sign",
          () -> new HangingSignItem(PAPER_HANGING_SIGN.get(), PAPER_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)), PAPER_SIGN_ITEM.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);

  public static void registerMiscBlocks() {
    NSColoredBlocks.registerColoredBlocks();
  }
}
