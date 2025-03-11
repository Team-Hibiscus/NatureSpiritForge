package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MahoganySaplingGenerator extends AbstractMegaTreeGrower {

   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
      return NSConfiguredFeatures.MAHOGANY_TREE;
   }

   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
      return null;
   }
}
