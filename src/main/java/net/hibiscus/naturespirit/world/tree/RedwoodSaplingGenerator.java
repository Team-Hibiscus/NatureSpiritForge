package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RedwoodSaplingGenerator extends AbstractMegaTreeGrower {

   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
      return NSConfiguredFeatures.LARGE_REDWOOD_TREE;
   }

   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
      return NSConfiguredFeatures.REDWOOD_TREE;
   }
}
