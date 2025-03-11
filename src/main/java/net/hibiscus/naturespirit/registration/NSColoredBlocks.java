package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.blocks.PaperLanternBlock;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerBlock;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerTransparentBlock;

public class NSColoredBlocks {

  public static final RegistryObject<Block> PAPER_LANTERN = registerTransparentBlock("paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), Items.PINK_CANDLE, CreativeModeTabs.COLORED_BLOCKS, Items.SOUL_LANTERN, CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> WHITE_PAPER_LANTERN = registerTransparentBlock("white_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> LIGHT_GRAY_PAPER_LANTERN = registerTransparentBlock("light_gray_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), WHITE_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, WHITE_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> GRAY_PAPER_LANTERN = registerTransparentBlock("gray_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), LIGHT_GRAY_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, LIGHT_GRAY_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> BLACK_PAPER_LANTERN = registerTransparentBlock("black_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), GRAY_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, GRAY_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> BROWN_PAPER_LANTERN = registerTransparentBlock("brown_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), BLACK_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, BLACK_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> RED_PAPER_LANTERN = registerTransparentBlock("red_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), BROWN_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, BROWN_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> ORANGE_PAPER_LANTERN = registerTransparentBlock("orange_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), RED_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, RED_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> YELLOW_PAPER_LANTERN = registerTransparentBlock("yellow_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), ORANGE_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, ORANGE_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> LIME_PAPER_LANTERN = registerTransparentBlock("lime_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), YELLOW_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, YELLOW_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> GREEN_PAPER_LANTERN = registerTransparentBlock("green_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), LIME_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, LIME_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> BLUE_PAPER_LANTERN = registerTransparentBlock("blue_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), GREEN_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, GREEN_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> LIGHT_BLUE_PAPER_LANTERN = registerTransparentBlock("light_blue_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), BLUE_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, BLUE_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> CYAN_PAPER_LANTERN = registerTransparentBlock("cyan_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), LIGHT_BLUE_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, LIGHT_BLUE_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> PURPLE_PAPER_LANTERN = registerTransparentBlock("purple_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), CYAN_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, CYAN_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> MAGENTA_PAPER_LANTERN = registerTransparentBlock("magenta_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), PURPLE_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, PURPLE_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
  public static final RegistryObject<Block> PINK_PAPER_LANTERN = registerTransparentBlock("pink_paper_lantern", () -> new PaperLanternBlock(
          BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).forceSolidOn().strength(0.5F).sound(SoundType.LANTERN).lightLevel((state) -> 15).noOcclusion()
                  .pushReaction(PushReaction.DESTROY)), MAGENTA_PAPER_LANTERN.get(), CreativeModeTabs.COLORED_BLOCKS, MAGENTA_PAPER_LANTERN.get(), CreativeModeTabs.FUNCTIONAL_BLOCKS);

  public static final RegistryObject<Block> KAOLIN = registerBlock(
      "kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      Blocks.PINK_TERRACOTTA,
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN = registerBlock(
      "white_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN = registerBlock(
      "light_gray_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      WHITE_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN = registerBlock(
      "gray_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      LIGHT_GRAY_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN = registerBlock(
      "black_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      GRAY_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN = registerBlock(
      "brown_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      BLACK_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN = registerBlock(
      "red_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      BROWN_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN = registerBlock(
      "orange_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      RED_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN = registerBlock(
      "yellow_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      ORANGE_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN = registerBlock(
      "lime_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      YELLOW_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN = registerBlock(
      "green_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      LIME_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN = registerBlock(
      "cyan_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      GREEN_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN = registerBlock(
      "light_blue_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      CYAN_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN = registerBlock(
      "blue_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      LIGHT_BLUE_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN = registerBlock(
      "purple_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      BLUE_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN = registerBlock(
      "magenta_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      PURPLE_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN = registerBlock(
      "pink_kaolin",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      MAGENTA_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> KAOLIN_STAIRS = registerBlock(
      "kaolin_stairs",
          () -> new StairBlock(KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      PINK_KAOLIN.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_STAIRS = registerBlock(
      "white_kaolin_stairs",
          () -> new StairBlock(WHITE_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_STAIRS = registerBlock(
      "light_gray_kaolin_stairs",
          () -> new StairBlock(LIGHT_GRAY_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
      ),
      WHITE_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_STAIRS = registerBlock(
      "gray_kaolin_stairs",
          () -> new StairBlock(GRAY_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_GRAY_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_STAIRS = registerBlock(
      "black_kaolin_stairs",
          () -> new StairBlock(BLACK_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GRAY_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_STAIRS = registerBlock(
      "brown_kaolin_stairs",
          () -> new StairBlock(BROWN_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLACK_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN_STAIRS = registerBlock(
      "red_kaolin_stairs",
          () -> new StairBlock(RED_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      BROWN_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_STAIRS = registerBlock(
      "orange_kaolin_stairs",
          () -> new StairBlock(ORANGE_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),
      RED_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_STAIRS = registerBlock(
      "yellow_kaolin_stairs",
          () -> new StairBlock(YELLOW_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      ORANGE_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN_STAIRS = registerBlock(
      "lime_kaolin_stairs",
          () -> new StairBlock(LIME_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      YELLOW_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_STAIRS = registerBlock(
      "green_kaolin_stairs",
          () -> new StairBlock(GREEN_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIME_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_STAIRS = registerBlock(
      "cyan_kaolin_stairs",
          () -> new StairBlock(CYAN_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GREEN_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_STAIRS = registerBlock(
      "light_blue_kaolin_stairs",
          () -> new StairBlock(LIGHT_BLUE_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
      ),

      CYAN_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_STAIRS = registerBlock(
      "blue_kaolin_stairs",
          () -> new StairBlock(BLUE_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_BLUE_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_STAIRS = registerBlock(
      "purple_kaolin_stairs",
          () -> new StairBlock(PURPLE_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLUE_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_STAIRS = registerBlock(
      "magenta_kaolin_stairs",
          () -> new StairBlock(MAGENTA_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PURPLE_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN_STAIRS = registerBlock(
      "pink_kaolin_stairs",
          () -> new StairBlock(PINK_KAOLIN.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      MAGENTA_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> KAOLIN_SLAB = registerBlock(
      "kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PINK_KAOLIN_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_SLAB = registerBlock(
      "white_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_SLAB = registerBlock(
      "light_gray_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      WHITE_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_SLAB = registerBlock(
      "gray_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_GRAY_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_SLAB = registerBlock(
      "black_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GRAY_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_SLAB = registerBlock(
      "brown_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLACK_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN_SLAB = registerBlock(
      "red_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BROWN_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_SLAB = registerBlock(
      "orange_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      RED_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_SLAB = registerBlock(
      "yellow_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      ORANGE_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN_SLAB = registerBlock(
      "lime_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      YELLOW_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_SLAB = registerBlock(
      "green_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIME_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_SLAB = registerBlock(
      "cyan_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GREEN_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_SLAB = registerBlock(
      "light_blue_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      CYAN_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_SLAB = registerBlock(
      "blue_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_BLUE_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_SLAB = registerBlock(
      "purple_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLUE_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_SLAB = registerBlock(
      "magenta_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PURPLE_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN_SLAB = registerBlock(
      "pink_kaolin_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      MAGENTA_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> KAOLIN_BRICKS = registerBlock(
      "kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PINK_KAOLIN_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICKS = registerBlock(
      "white_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICKS = registerBlock(
      "light_gray_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      WHITE_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICKS = registerBlock(
      "gray_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_GRAY_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICKS = registerBlock(
      "black_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GRAY_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICKS = registerBlock(
      "brown_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLACK_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICKS = registerBlock(
      "red_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BROWN_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICKS = registerBlock(
      "orange_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      RED_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICKS = registerBlock(
      "yellow_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      ORANGE_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICKS = registerBlock(
      "lime_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      YELLOW_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICKS = registerBlock(
      "green_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIME_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICKS = registerBlock(
      "cyan_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GREEN_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICKS = registerBlock(
      "light_blue_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      CYAN_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICKS = registerBlock(
      "blue_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_BLUE_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICKS = registerBlock(
      "purple_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLUE_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICKS = registerBlock(
      "magenta_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PURPLE_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICKS = registerBlock(
      "pink_kaolin_bricks",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      MAGENTA_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> KAOLIN_BRICK_STAIRS = registerBlock(
      "kaolin_brick_stairs",
          () -> new StairBlock(KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PINK_KAOLIN_BRICKS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICK_STAIRS = registerBlock(
      "white_kaolin_brick_stairs",
          () -> new StairBlock(WHITE_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
      "light_gray_kaolin_brick_stairs",
          () -> new StairBlock(LIGHT_GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
      ),

      WHITE_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICK_STAIRS = registerBlock(
      "gray_kaolin_brick_stairs",
          () -> new StairBlock(GRAY_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICK_STAIRS = registerBlock(
      "black_kaolin_brick_stairs",
          () -> new StairBlock(BLACK_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GRAY_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICK_STAIRS = registerBlock(
      "brown_kaolin_brick_stairs",
          () -> new StairBlock(BROWN_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLACK_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICK_STAIRS = registerBlock(
      "red_kaolin_brick_stairs",
          () -> new StairBlock(RED_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BROWN_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICK_STAIRS = registerBlock(
      "orange_kaolin_brick_stairs",
          () -> new StairBlock(ORANGE_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      RED_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICK_STAIRS = registerBlock(
      "yellow_kaolin_brick_stairs",
          () -> new StairBlock(YELLOW_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      ORANGE_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICK_STAIRS = registerBlock(
      "lime_kaolin_brick_stairs",
          () -> new StairBlock(LIME_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      YELLOW_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICK_STAIRS = registerBlock(
      "green_kaolin_brick_stairs",
          () -> new StairBlock(GREEN_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIME_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICK_STAIRS = registerBlock(
      "cyan_kaolin_brick_stairs",
          () -> new StairBlock(CYAN_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GREEN_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
      "light_blue_kaolin_brick_stairs",
          () -> new StairBlock(LIGHT_BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)
      ),

      CYAN_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICK_STAIRS = registerBlock(
      "blue_kaolin_brick_stairs",
          () -> new StairBlock(BLUE_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICK_STAIRS = registerBlock(
      "purple_kaolin_brick_stairs",
          () -> new StairBlock(PURPLE_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLUE_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICK_STAIRS = registerBlock(
      "magenta_kaolin_brick_stairs",
          () -> new StairBlock(MAGENTA_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PURPLE_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICK_STAIRS = registerBlock(
      "pink_kaolin_brick_stairs",
          () -> new StairBlock(PINK_KAOLIN_BRICKS.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      MAGENTA_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> KAOLIN_BRICK_SLAB = registerBlock(
      "kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PINK_KAOLIN_BRICK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_KAOLIN_BRICK_SLAB = registerBlock(
      "white_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "light_gray_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      WHITE_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_KAOLIN_BRICK_SLAB = registerBlock(
      "gray_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_GRAY_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_KAOLIN_BRICK_SLAB = registerBlock(
      "black_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GRAY_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_KAOLIN_BRICK_SLAB = registerBlock(
      "brown_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLACK_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_KAOLIN_BRICK_SLAB = registerBlock(
      "red_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BROWN_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_KAOLIN_BRICK_SLAB = registerBlock(
      "orange_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      RED_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_KAOLIN_BRICK_SLAB = registerBlock(
      "yellow_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      ORANGE_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_KAOLIN_BRICK_SLAB = registerBlock(
      "lime_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      YELLOW_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_KAOLIN_BRICK_SLAB = registerBlock(
      "green_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIME_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_KAOLIN_BRICK_SLAB = registerBlock(
      "cyan_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      GREEN_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "light_blue_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      CYAN_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_KAOLIN_BRICK_SLAB = registerBlock(
      "blue_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      LIGHT_BLUE_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_KAOLIN_BRICK_SLAB = registerBlock(
      "purple_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      BLUE_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_KAOLIN_BRICK_SLAB = registerBlock(
      "magenta_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      PURPLE_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_KAOLIN_BRICK_SLAB = registerBlock(
      "pink_kaolin_brick_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)),

      MAGENTA_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_CHALK = registerBlock(
      "white_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PINK_KAOLIN_BRICK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK = registerBlock(
      "light_gray_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      WHITE_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_CHALK = registerBlock(
      "gray_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_GRAY_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_CHALK = registerBlock(
      "black_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GRAY_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_CHALK = registerBlock(
      "brown_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLACK_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_CHALK = registerBlock(
      "red_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BROWN_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_CHALK = registerBlock(
      "orange_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      RED_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_CHALK = registerBlock(
      "yellow_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      ORANGE_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_CHALK = registerBlock(
      "lime_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      YELLOW_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_CHALK = registerBlock(
      "green_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIME_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_CHALK = registerBlock(
      "cyan_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GREEN_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK = registerBlock(
      "light_blue_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      CYAN_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_CHALK = registerBlock(
      "blue_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_BLUE_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_CHALK = registerBlock(
      "purple_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLUE_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_CHALK = registerBlock(
      "magenta_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PURPLE_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_CHALK = registerBlock(
      "pink_chalk",
          () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      MAGENTA_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_CHALK_STAIRS = registerBlock(
      "white_chalk_stairs",
          () -> new StairBlock(WHITE_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PINK_CHALK.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK_STAIRS = registerBlock(
      "light_gray_chalk_stairs",
          () -> new StairBlock(LIGHT_GRAY_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)
      ),

      WHITE_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_CHALK_STAIRS = registerBlock(
      "gray_chalk_stairs",
          () -> new StairBlock(GRAY_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_GRAY_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_CHALK_STAIRS = registerBlock(
      "black_chalk_stairs",
          () -> new StairBlock(BLACK_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GRAY_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_CHALK_STAIRS = registerBlock(
      "brown_chalk_stairs",
          () -> new StairBlock(BROWN_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLACK_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_CHALK_STAIRS = registerBlock(
      "red_chalk_stairs",
          () -> new StairBlock(RED_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BROWN_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_CHALK_STAIRS = registerBlock(
      "orange_chalk_stairs",
          () -> new StairBlock(ORANGE_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      RED_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_CHALK_STAIRS = registerBlock(
      "yellow_chalk_stairs",
          () -> new StairBlock(YELLOW_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      ORANGE_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_CHALK_STAIRS = registerBlock(
      "lime_chalk_stairs",
          () -> new StairBlock(LIME_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      YELLOW_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_CHALK_STAIRS = registerBlock(
      "green_chalk_stairs",
          () -> new StairBlock(GREEN_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIME_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_CHALK_STAIRS = registerBlock(
      "cyan_chalk_stairs",
          () -> new StairBlock(CYAN_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GREEN_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK_STAIRS = registerBlock(
      "light_blue_chalk_stairs",
          () -> new StairBlock(LIGHT_BLUE_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      CYAN_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_CHALK_STAIRS = registerBlock(
      "blue_chalk_stairs",
          () -> new StairBlock(BLUE_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_BLUE_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_CHALK_STAIRS = registerBlock(
      "purple_chalk_stairs",
          () -> new StairBlock(PURPLE_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLUE_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_CHALK_STAIRS = registerBlock(
      "magenta_chalk_stairs",
          () -> new StairBlock(MAGENTA_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PURPLE_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_CHALK_STAIRS = registerBlock(
      "pink_chalk_stairs",
          () -> new StairBlock(PINK_CHALK.get().defaultBlockState(),
          BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      MAGENTA_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> WHITE_CHALK_SLAB = registerBlock(
      "white_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PINK_CHALK_STAIRS.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_GRAY_CHALK_SLAB = registerBlock(
      "light_gray_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      WHITE_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GRAY_CHALK_SLAB = registerBlock(
      "gray_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_GRAY_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLACK_CHALK_SLAB = registerBlock(
      "black_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GRAY_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BROWN_CHALK_SLAB = registerBlock(
      "brown_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLACK_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> RED_CHALK_SLAB = registerBlock(
      "red_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BROWN_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> ORANGE_CHALK_SLAB = registerBlock(
      "orange_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      RED_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> YELLOW_CHALK_SLAB = registerBlock(
      "yellow_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      ORANGE_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIME_CHALK_SLAB = registerBlock(
      "lime_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      YELLOW_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> GREEN_CHALK_SLAB = registerBlock(
      "green_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIME_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> CYAN_CHALK_SLAB = registerBlock(
      "cyan_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      GREEN_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> LIGHT_BLUE_CHALK_SLAB = registerBlock(
      "light_blue_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      CYAN_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> BLUE_CHALK_SLAB = registerBlock(
      "blue_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      LIGHT_BLUE_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PURPLE_CHALK_SLAB = registerBlock(
      "purple_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      BLUE_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> MAGENTA_CHALK_SLAB = registerBlock(
      "magenta_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      PURPLE_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );
  public static final RegistryObject<Block> PINK_CHALK_SLAB = registerBlock(
      "pink_chalk_slab",
          () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),

      MAGENTA_CHALK_SLAB.get(),
      CreativeModeTabs.COLORED_BLOCKS
  );

  public static void registerColoredBlocks() {
  }
}
