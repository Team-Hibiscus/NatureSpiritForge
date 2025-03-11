package net.hibiscus.naturespirit.registration;

import com.mojang.serialization.Codec;
import net.hibiscus.naturespirit.world.carver.ReplaceableCaveCarver;
import net.hibiscus.naturespirit.world.carver.ReplaceableCaveCarverConfig;
import net.hibiscus.naturespirit.world.carver.ReplaceableRavineCarver;
import net.hibiscus.naturespirit.world.carver.ReplaceableRavineCarverConfig;
import net.hibiscus.naturespirit.world.feature.*;
import net.hibiscus.naturespirit.world.foliage_placer.*;
import net.hibiscus.naturespirit.world.tree_decorator.*;
import net.hibiscus.naturespirit.world.trunk.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSWorldGen {

  public static final TrunkPlacerType<WisteriaTrunkPlacer> WISTERIA_TRUNK_PLACER = registerTrunkPlacer("wisteria_trunk_placer", WisteriaTrunkPlacer.CODEC);
  public static final TrunkPlacerType<BushTrunkPlacer> BUSH_TRUNK_PLACER = registerTrunkPlacer("bush_trunk_placer", BushTrunkPlacer.CODEC);
  public static final TrunkPlacerType<SugiTrunkPlacer> SUGI_TRUNK_PLACER = registerTrunkPlacer("sugi_trunk_placer", SugiTrunkPlacer.CODEC);
  public static final TrunkPlacerType<OliveTrunkPlacer> OLIVE_TRUNK_PLACER = registerTrunkPlacer("olive_trunk_placer", OliveTrunkPlacer.CODEC);
  public static final TrunkPlacerType<PaloVerdeTrunkPlacer> PALO_VERDE_TRUNK_PLACER = registerTrunkPlacer("palo_verde_trunk_placer", PaloVerdeTrunkPlacer.CODEC);
  public static final TrunkPlacerType<BanyanTrunkPlacer> BANYAN_TRUNK_PLACER = registerTrunkPlacer("banyan_trunk_placer", BanyanTrunkPlacer.CODEC);
  public static final TrunkPlacerType<SaxaulTrunkPlacer> SAXAUL_TRUNK_PLACER = registerTrunkPlacer("saxaul_trunk_placer", SaxaulTrunkPlacer.CODEC);
  public static final TrunkPlacerType<GhafTrunkPlacer> GHAF_TRUNK_PLACER = registerTrunkPlacer("ghaf_trunk_placer", GhafTrunkPlacer.CODEC);
  public static final TrunkPlacerType<MapleTrunkPlacer> MAPLE_TRUNK_PLACER = registerTrunkPlacer("maple_trunk_placer", MapleTrunkPlacer.CODEC);
  public static final TrunkPlacerType<MahoganyTrunkPlacer> MAHOGANY_TRUNK_PLACER = registerTrunkPlacer("mahogany_trunk_placer", MahoganyTrunkPlacer.CODEC);
  public static final TrunkPlacerType<MegaSugiTrunkPlacer> MEGA_SUGI_TRUNK_PLACER = registerTrunkPlacer("mega_sugi_trunk_placer", MegaSugiTrunkPlacer.CODEC);
  public static final TrunkPlacerType<CoconutTrunkPlacer> COCONUT_TRUNK_PLACER = registerTrunkPlacer("coconut_trunk_placer", CoconutTrunkPlacer.CODEC);
  public static final TrunkPlacerType<RedwoodTrunkPlacer> REDWOOD_TRUNK_PLACER = registerTrunkPlacer("redwood_trunk_placer", RedwoodTrunkPlacer.CODEC);


  public static final TreeDecoratorType<WisteriaVinesTreeDecorator> WISTERIA_VINES_TREE_DECORATOR = registerTreeDecorator("wisteria_vines_tree_decorator",
      WisteriaVinesTreeDecorator.CODEC
  );
  public static final TreeDecoratorType<MapleGroundTreeDecorator> MAPLE_GROUND_TREE_DECORATOR = registerTreeDecorator("maple_ground_tree_decorator",
      MapleGroundTreeDecorator.CODEC);
  public static final TreeDecoratorType<CoconutTreeDecorator> COCONUT_TREE_DECORATOR = registerTreeDecorator("coconut_tree_decorator", CoconutTreeDecorator.CODEC);
  public static final TreeDecoratorType<RedwoodBranchTreeDecorator> REDWOOD_BRANCH_DECORATOR = registerTreeDecorator("redwood_branch_decorator", RedwoodBranchTreeDecorator.CODEC);
  public static final TreeDecoratorType<OliveBranchTreeDecorator> OLIVE_BRANCH_DECORATOR = registerTreeDecorator("olive_branch_decorator", OliveBranchTreeDecorator.CODEC);
  public static final TreeDecoratorType<SnowTreeDecorator> SNOW_DECORATOR = registerTreeDecorator("snow_decorator", SnowTreeDecorator.CODEC);
  public static final TreeDecoratorType<PolyporeTreeDecorator> POLYPORE_DECORATOR = registerTreeDecorator("polypore_decorator", PolyporeTreeDecorator.CODEC);


  public static final FoliagePlacerType<WisteriaFoliagePlacer> WISTERIA_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("wisteria_foliage_placer", WisteriaFoliagePlacer.CODEC);
  public static final FoliagePlacerType<SugiFoliagePlacer> SUGI_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("sugi_foliage_placer", SugiFoliagePlacer.CODEC);
  public static final FoliagePlacerType<AspenFoliagePlacer> ASPEN_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("aspen_foliage_placer", AspenFoliagePlacer.CODEC);
  public static final FoliagePlacerType<FirFoliagePlacer> FIR_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("fir_foliage_placer", FirFoliagePlacer.CODEC);
  public static final FoliagePlacerType<LarchFoliagePlacer> LARCH_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("larch_foliage_placer", LarchFoliagePlacer.CODEC);
  public static final FoliagePlacerType<RedwoodFoliagePlacer> REDWOOD_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("redwood_foliage_placer", RedwoodFoliagePlacer.CODEC);
  public static final FoliagePlacerType<CypressFoliagePlacer> CYPRESS_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("cypress_foliage_placer", CypressFoliagePlacer.CODEC);
  public static final FoliagePlacerType<MapleFoliagePlacer> MAPLE_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("maple_foliage_placer", MapleFoliagePlacer.CODEC);
  public static final FoliagePlacerType<CoconutFoliagePlacer> COCONUT_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("coconut_foliage_placer", CoconutFoliagePlacer.CODEC);
  public static final FoliagePlacerType<BirchFoliagePlacer> BIRCH_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("birch_foliage_placer", BirchFoliagePlacer.CODEC);
  public static final FoliagePlacerType<GroundedBushFoliagePlacer> GROUNDED_BUSH_PLACER_TYPE = registerFoliagePlacer("grounded_bush_foliage_placer", GroundedBushFoliagePlacer.CODEC);


  public static final WorldCarver<ReplaceableCaveCarverConfig> REPLACEABLE_CAVE_CARVER = registerCaveCarver("replaceable_cave",
      new ReplaceableCaveCarver(ReplaceableCaveCarverConfig.CAVE_CODEC));
  public static final WorldCarver<ReplaceableRavineCarverConfig> REPLACEABLE_RAVINE_CARVER = registerCaveCarver("replaceable_canyon",
      new ReplaceableRavineCarver(ReplaceableRavineCarverConfig.RAVINE_CODEC));

  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_PILLAR = ResourceKey.create(Registries.NOISE, new ResourceLocation(MOD_ID, "sugi_pillar"));
  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_PILLAR_ROOF = ResourceKey.create(Registries.NOISE,
      new ResourceLocation(MOD_ID, "sugi_pillar_roof"));
  public static final ResourceKey<NormalNoise.NoiseParameters> SUGI_SURFACE = ResourceKey.create(Registries.NOISE, new ResourceLocation(MOD_ID, "sugi_surface"));

  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_PILLAR = ResourceKey.create(Registries.NOISE,
      new ResourceLocation(MOD_ID, "stratified_desert_pillar"));
  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_PILLAR_ROOF = ResourceKey.create(Registries.NOISE,
      new ResourceLocation(MOD_ID, "stratified_desert_pillar_roof"));
  public static final ResourceKey<NormalNoise.NoiseParameters> STRATIFIED_DESERT_SURFACE = ResourceKey.create(Registries.NOISE,
      new ResourceLocation(MOD_ID, "stratified_desert_surface"));

  public static final Feature<DeltaFeatureConfiguration> HIBISCUS_DELTA_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "water_delta_feature"),
      new NSDeltaFeature(DeltaFeatureConfiguration.CODEC)
  );
  public static final Feature<OreConfiguration> HIBISCUS_PUMPKIN_PATCH_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "pumpkin_patch_feature"),
      new PumpkinPatchFeature(OreConfiguration.CODEC)
  );
  public static final Feature<BlockPileConfiguration> HIBISCUS_LARGE_PUMPKIN_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "large_pumpkin_feature"),
      new LargePumpkinFeature(BlockPileConfiguration.CODEC)
  );
  public static final Feature<TurnipRootFeatureConfig> HIBISCUS_TURNIP_ROOT_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "turnip_root_feature"),
      new TurnipRootFeature(TurnipRootFeatureConfig.CODEC)
  );
  public static final Feature<NoneFeatureConfiguration> JOSHUA_TREE_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "joshua_tree_feature"),
      new JoshuaTreeFeature(NoneFeatureConfiguration.CODEC)
  );
  public static final Feature<NoneFeatureConfiguration> ALLUAUDIA_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "alluaudia_feature"),
      new AlluaudiaFeature(NoneFeatureConfiguration.CODEC)
  );
  public static final Feature<HugeMushroomFeatureConfiguration> HUGE_SHIITAKE_MUSHROOM_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "huge_shiitake_mushroom_feature"),
      new HugeShiitakeMushroomFeature(HugeMushroomFeatureConfiguration.CODEC)
  );
  public static final Feature<HugeMushroomFeatureConfig> HUGE_RED_MUSHROOM_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "huge_red_mushroom_feature"),
      new HugeRedMushroomFeature(HugeMushroomFeatureConfig.CODEC)
  );
  public static final Feature<HugeMushroomFeatureConfig> HUGE_BROWN_MUSHROOM_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "huge_brown_mushroom_feature"),
      new HugeBrownMushroomFeature(HugeMushroomFeatureConfig.CODEC)
  );
  public static final Feature<NoneFeatureConfiguration> POLYPORE_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "polypore_feature"),
      new PolyporeFeature(NoneFeatureConfiguration.CODEC)
  );
  public static final Feature<NoneFeatureConfiguration> LOTUS_PLANT_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "lotus_plant_feature"),
      new LotusPlantFeature(NoneFeatureConfiguration.CODEC)
  );
  public static final Feature<RandomPatchConfiguration> LEVELED_RANDOM_PATCH_FEATURE = Registry.register(BuiltInRegistries.FEATURE,
      new ResourceLocation(MOD_ID, "leveled_random_patch_feature"),
      new LeveledRandomPatch(RandomPatchConfiguration.CODEC)
  );

  private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String id, Codec<P> codec) {
    return (FoliagePlacerType) Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, new ResourceLocation(MOD_ID, id), new FoliagePlacerType(codec));
  }

  private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, Codec<P> codec) {
    return (TrunkPlacerType) Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, new ResourceLocation(MOD_ID, id), new TrunkPlacerType(codec));
  }

  private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, Codec<P> codec) {
    return (TreeDecoratorType) Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, new ResourceLocation(MOD_ID, id), new TreeDecoratorType(codec));
  }

  private static <C extends CarverConfiguration, F extends WorldCarver<C>> WorldCarver<C> registerCaveCarver(String id, F carver) {
    return Registry.register(BuiltInRegistries.CARVER, new ResourceLocation(MOD_ID, id), carver);
  }

  public static void registerWorldGen() {
//            BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SANDY), GenerationStep.Feature.VEGETAL_DECORATION, NSPlacedFeatures.ROOTED_DESERT_TURNIP);
  }

}
