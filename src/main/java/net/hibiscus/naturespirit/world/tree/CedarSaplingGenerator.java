package net.hibiscus.naturespirit.world.tree;

import net.hibiscus.naturespirit.datagen.NSConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CedarSaplingGenerator extends AbstractTreeGrower {
   @Override protected ResourceKey <ConfiguredFeature <?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
      return NSConfiguredFeatures.CEDAR_TREE;
   }
}
