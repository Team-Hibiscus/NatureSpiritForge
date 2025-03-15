package net.hibiscus.naturespirit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class ParticleLeavesBlock extends LeavesBlock {

  RegistryObject<? extends ParticleOptions> particle;
  int chance;

  public ParticleLeavesBlock(Properties settings, RegistryObject<? extends ParticleOptions> particle, int chance) {
    super(settings);
    this.particle = particle;
    this.chance = chance;
  }

  @Override
  public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
    super.animateTick(state, world, pos, random);
    if (random.nextInt(chance) == 0) {
      BlockPos blockPos = pos.below();
      BlockState blockState = world.getBlockState(blockPos);
      if (!isFaceFull(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
        ParticleUtils.spawnParticleBelow(world, pos, random, particle.get());
      }
    }
  }
}
