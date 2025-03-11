package net.hibiscus.naturespirit.registration;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.blocks.CoconutBlock;
import net.hibiscus.naturespirit.blocks.OliveBranchBlock;
import net.hibiscus.naturespirit.blocks.SproutingCoconutBlock;
import net.hibiscus.naturespirit.items.CoconutHalfItem;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet.WoodPreset;
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class NSWoods {

  public static final WoodSet REDWOOD = new WoodSet(
      new ResourceLocation(MOD_ID, "redwood"),
      MapColor.TERRACOTTA_BROWN,
      MapColor.COLOR_RED,
      Blocks.CHERRY_LEAVES,
      Blocks.CHERRY_LOG,
      Blocks.BAMBOO_HANGING_SIGN,
      Items.BAMBOO_CHEST_RAFT,
      Blocks.BAMBOO_BUTTON,
      Blocks.CHERRY_SAPLING,
      () -> NSBoatTypes.REDWOOD,
      WoodPreset.FROSTABLE,
      false,
      new RedwoodSaplingGenerator()
  );

  public static final WoodSet SUGI = new WoodSet(
      new ResourceLocation(MOD_ID, "sugi"),
      MapColor.DEEPSLATE,
      MapColor.DIRT,
      REDWOOD.getLeaves().get(),
      REDWOOD.getLog().get(),
      REDWOOD.getHangingSign().get(),
      REDWOOD.getChestBoatItem().get(),
      REDWOOD.getButton().get(),
      REDWOOD.getSapling().get(),
      () -> NSBoatTypes.SUGI,
      WoodPreset.FANCY,
      true,
      new SugiSaplingGenerator()
  );

  public static final WoodSet WISTERIA = new WoodSet(
      new ResourceLocation(MOD_ID, "wisteria"),
      MapColor.COLOR_GRAY,
      MapColor.TERRACOTTA_WHITE,
      SUGI.getLeaves().get(),
      SUGI.getLog().get(),
      SUGI.getHangingSign().get(),
      SUGI.getChestBoatItem().get(),
      SUGI.getButton().get(),
      SUGI.getSapling().get(),
      () -> NSBoatTypes.WISTERIA,
      WoodPreset.WISTERIA,
      true,
      new WhiteWisteriaSaplingGenerator()
  );

  public static final WoodSet FIR = new WoodSet(
      new ResourceLocation(MOD_ID, "fir"),
      MapColor.COLOR_GRAY,
      MapColor.DIRT,
      WISTERIA.getPurpleLeaves().get(),
      WISTERIA.getLog().get(),
      WISTERIA.getHangingSign().get(),
      WISTERIA.getChestBoatItem().get(),
      WISTERIA.getButton().get(),
      WISTERIA.getPurpleSapling().get(),
      () -> NSBoatTypes.FIR,
      WoodPreset.FROSTABLE,
      false,
      new FirSaplingGenerator()
  );

  public static final WoodSet WILLOW = new WoodSet(
      new ResourceLocation(MOD_ID, "willow"),
      MapColor.TERRACOTTA_BLACK,
      MapColor.TERRACOTTA_BROWN,
      FIR.getLeaves().get(),
      FIR.getLog().get(),
      FIR.getHangingSign().get(),
      FIR.getChestBoatItem().get(),
      FIR.getButton().get(),
      FIR.getSapling().get(),
      () -> NSBoatTypes.WILLOW,
      WoodPreset.WILLOW,
      false,
      new WillowSaplingGenerator()
  );

  public static final WoodSet ASPEN = new WoodSet(
      new ResourceLocation(MOD_ID, "aspen"),
      MapColor.WOOL,
      MapColor.SAND,
      WILLOW.getLeaves().get(),
      WILLOW.getLog().get(),
      WILLOW.getHangingSign().get(),
      WILLOW.getChestBoatItem().get(),
      WILLOW.getButton().get(),
      WILLOW.getSapling().get(),
      () -> NSBoatTypes.ASPEN,
      WoodPreset.ASPEN,
      false,
      new AspenSaplingGenerator()
  );
  public static final WoodSet MAPLE = new WoodSet(
      new ResourceLocation(MOD_ID, "maple"),
      MapColor.PODZOL,
      MapColor.COLOR_ORANGE,
      ASPEN.getLeaves().get(),
      ASPEN.getLog().get(),
      ASPEN.getHangingSign().get(),
      ASPEN.getChestBoatItem().get(),
      ASPEN.getButton().get(),
      ASPEN.getSapling().get(),
      () -> NSBoatTypes.MAPLE,
      WoodPreset.MAPLE,
      false,
      new RedMapleSaplingGenerator()
  );

  public static final WoodSet CYPRESS = new WoodSet(
      new ResourceLocation(MOD_ID, "cypress"),
      MapColor.PODZOL,
      MapColor.WOOD,
      MAPLE.getYellowLeaves().get(),
      MAPLE.getLog().get(),
      MAPLE.getHangingSign().get(),
      MAPLE.getChestBoatItem().get(),
      MAPLE.getButton().get(),
      MAPLE.getYellowSapling().get(),
      () -> NSBoatTypes.CYPRESS,
      WoodPreset.DEFAULT,
      false,
      new CypressSaplingGenerator()
  );

  public static final WoodSet OLIVE = new WoodSet(
      new ResourceLocation(MOD_ID, "olive"),
      MapColor.SAND,
      MapColor.GRASS,
      CYPRESS.getLeaves().get(),
      CYPRESS.getLog().get(),
      CYPRESS.getHangingSign().get(),
      CYPRESS.getChestBoatItem().get(),
      CYPRESS.getButton().get(),
      CYPRESS.getSapling().get(),
      () -> NSBoatTypes.OLIVE,
      WoodPreset.DEFAULT,
      false,
      new OliveSaplingGenerator()
  );

  public static final RegistryObject<Block> OLIVE_BRANCH = registerPlantBlock("olive_branch", () -> new OliveBranchBlock(BlockBehaviour.Properties.of().instabreak().noCollission().randomTicks().sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)){
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
  }, OLIVE.getLog().get(), 0.5F);

  public static final WoodSet JOSHUA = new WoodSet(
      new ResourceLocation(MOD_ID, "joshua"),
      MapColor.GRASS,
      MapColor.DEEPSLATE,
      OLIVE.getLeaves().get(),
      OLIVE.getLog().get(),
      OLIVE.getHangingSign().get(),
      OLIVE.getChestBoatItem().get(),
      OLIVE.getButton().get(),
      OLIVE.getSapling().get(),
      () -> NSBoatTypes.JOSHUA,
      WoodPreset.JOSHUA,
      true,
      new JoshuaSaplingGenerator()
  );

  public static final WoodSet GHAF = new WoodSet(
      new ResourceLocation(MOD_ID, "ghaf"),
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_BROWN,
      JOSHUA.getLeaves().get(),
      JOSHUA.getLog().get(),
      JOSHUA.getHangingSign().get(),
      JOSHUA.getChestBoatItem().get(),
      JOSHUA.getButton().get(),
      JOSHUA.getSapling().get(),
      () -> NSBoatTypes.GHAF,
      WoodPreset.SANDY,
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
  public static final RegistryObject<Block> XERIC_THATCH_STAIRS = registerBlock("xeric_thatch_stairs", () -> new StairBlock(XERIC_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(XERIC_THATCH.get())){
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
      new ResourceLocation(MOD_ID, "palo_verde"),
      MapColor.COLOR_YELLOW,
      MapColor.GLOW_LICHEN,
      GHAF.getLeaves().get(),
      GHAF.getLog().get(),
      GHAF.getHangingSign().get(),
      GHAF.getChestBoatItem().get(),
      GHAF.getButton().get(),
      GHAF.getSapling().get(),
      () -> NSBoatTypes.PALO_VERDE,
      WoodPreset.SANDY,
      false,
      new PaloVerdeSaplingGenerator()
  );

  public static final WoodSet COCONUT = new WoodSet(
      new ResourceLocation(MOD_ID, "coconut"),
      MapColor.CRIMSON_STEM,
      MapColor.COLOR_BROWN,
      PALO_VERDE.getLeaves().get(),
      PALO_VERDE.getLog().get(),
      PALO_VERDE.getHangingSign().get(),
      PALO_VERDE.getChestBoatItem().get(),
      PALO_VERDE.getButton().get(),
      PALO_VERDE.getSapling().get(),
      () -> NSBoatTypes.COCONUT,
      WoodPreset.NO_SAPLING,
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
  public static final RegistryObject<Block> COCONUT_THATCH_STAIRS = registerBlock("coconut_thatch_stairs", () -> new StairBlock(COCONUT_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(COCONUT_THATCH.get())){
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
  public static final RegistryObject<Block> COCONUT_BLOCK = registerPlantBlock("coconut", () -> new CoconutBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)), Items.SWEET_BERRIES, 0.2F);
  public static final RegistryObject<Block> COCONUT_SPROUT = registerPlantBlock("coconut_sprout", () -> new SproutingCoconutBlock(new CoconutSaplingGenerator(), BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.GRASS).noOcclusion().pushReaction(PushReaction.DESTROY)), PALO_VERDE.getSapling().get(), 0.2F);
  public static final FoodProperties COCONUT_COMPONENT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();

  public static final RegistryObject<Item> COCONUT_SHELL = registerPlantItem("coconut_shell", () -> new Item(new Item.Properties()),
      Items.BOWL,
      CreativeModeTabs.INGREDIENTS,
      0.1F
  );
  public static final RegistryObject<Item> COCONUT_HALF = registerPlantItem("coconut_half",
          () -> new CoconutHalfItem(new Item.Properties().food(COCONUT_COMPONENT), COCONUT_SHELL.get()),
      Items.BEETROOT,
      CreativeModeTabs.FOOD_AND_DRINKS,
      0.1F
  );

  public static final WoodSet CEDAR = new WoodSet(
      new ResourceLocation(MOD_ID, "cedar"),
      MapColor.TERRACOTTA_MAGENTA,
      MapColor.COLOR_GRAY,
      COCONUT.getLeaves().get(),
      COCONUT.getLog().get(),
      COCONUT.getHangingSign().get(),
      COCONUT.getChestBoatItem().get(),
      COCONUT.getButton().get(),
      COCONUT_SPROUT.get(),
      () -> NSBoatTypes.CEDAR,
      WoodPreset.DEFAULT,
      false,
      new CedarSaplingGenerator()
  );

  public static final WoodSet LARCH = new WoodSet(
      new ResourceLocation(MOD_ID, "larch"),
      MapColor.COLOR_BLUE,
      MapColor.COLOR_LIGHT_GRAY,
      CEDAR.getLeaves().get(),
      CEDAR.getLog().get(),
      CEDAR.getHangingSign().get(),
      CEDAR.getChestBoatItem().get(),
      CEDAR.getButton().get(),
      CEDAR.getSapling().get(),
      () -> NSBoatTypes.LARCH,
      WoodPreset.DEFAULT,
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
  public static final RegistryObject<Block> EVERGREEN_THATCH_STAIRS = registerBlock("evergreen_thatch_stairs", () -> new StairBlock(EVERGREEN_THATCH.get().defaultBlockState(), BlockBehaviour.Properties.copy(EVERGREEN_THATCH.get())){
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
      new ResourceLocation(MOD_ID, "mahogany"),
      MapColor.COLOR_BROWN,
      MapColor.COLOR_LIGHT_GRAY,
      LARCH.getLeaves().get(),
      LARCH.getLog().get(),
      LARCH.getHangingSign().get(),
      LARCH.getChestBoatItem().get(),
      LARCH.getButton().get(),
      LARCH.getSapling().get(),
      () -> NSBoatTypes.MAHOGANY, WoodPreset.DEFAULT,
      true,
      new MahoganySaplingGenerator()
  );

  public static final WoodSet SAXAUL = new WoodSet(
      new ResourceLocation(MOD_ID, "saxaul"),
      MapColor.COLOR_LIGHT_GRAY,
      MapColor.COLOR_LIGHT_GRAY,
      MAHOGANY.getLeaves().get(),
      MAHOGANY.getLog().get(),
      MAHOGANY.getHangingSign().get(),
      MAHOGANY.getChestBoatItem().get(),
      MAHOGANY.getButton().get(),
      MAHOGANY.getSapling().get(),
      () -> NSBoatTypes.SAXAUL,
      WoodPreset.SANDY,
      false,
      new SaxaulSaplingGenerator()
  );

//   public static final WoodSet BANYAN = new WoodSet(
//           new Identifier(MOD_ID, "banyan"),
//           MapColor.BROWN,
//           MapColor.LIGHT_GRAY,
//           LARCH.getLeaves(),
//           LARCH.getLog(),
//           LARCH.getHangingSign(),
//           LARCH.getChestBoatItem(),
//           LARCH.getButton(),
//           LARCH.getSapling(),
//           () -> NSBoatTypes.BANYAN,
//           new BanyanSaplingGenerator(),
//           WoodSet.WoodPreset.DEFAULT,
//           false
//   );

  private static final List<WoodSet> WOOD_SETS = ImmutableList.of(
      REDWOOD,
      SUGI,
      WISTERIA,
      FIR,
      WILLOW,
      ASPEN,
      MAPLE,
      CYPRESS,
      OLIVE,
      JOSHUA,
      GHAF,
      PALO_VERDE,
      COCONUT,
      CEDAR,
      LARCH,
      MAHOGANY,
      SAXAUL
  );

  public static List<WoodSet> getWoodSets() {
    return WOOD_SETS;
  }

  static {
    NSBoatTypes.init();
  }

  public static void registerWoods() {}
}
