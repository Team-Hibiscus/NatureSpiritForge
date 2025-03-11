package net.hibiscus.naturespirit.blocks;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ProjectileLeavesBlock extends LeavesBlock {

  private final Block END_BLOCK;

  public ProjectileLeavesBlock(Properties settings, Block block) {
    super(settings);
    this.END_BLOCK = block;
  }

  @Override
  public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
    if (projectile instanceof Snowball) {
      Entity entity = projectile.getOwner();
      if (entity instanceof ServerPlayer serverPlayerEntity) {
        serverPlayerEntity.awardStat(Stats.TARGET_HIT);
      }
      world.setBlockAndUpdate(hit.getBlockPos(), this.END_BLOCK.withPropertiesOf(state));
    }
  }
}
