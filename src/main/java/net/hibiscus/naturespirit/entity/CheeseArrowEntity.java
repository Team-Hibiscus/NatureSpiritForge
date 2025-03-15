package net.hibiscus.naturespirit.entity;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSEntityTypes;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CheeseArrowEntity extends AbstractArrow {


  public CheeseArrowEntity(EntityType<? extends CheeseArrowEntity> entityType, Level world) {
    super(entityType, world);
  }

  public CheeseArrowEntity(Level world, LivingEntity owner) {
    super(NSEntityTypes.CHEESE_ARROW.get(), owner, world);
  }

  public CheeseArrowEntity(Level world, double x, double y, double z) {
    super(NSEntityTypes.CHEESE_ARROW.get(), x, y, z, world);
  }

  public void tick() {
    super.tick();
    if (this.level().isClientSide && !this.inGround) {
      this.level().addParticle(NSParticleTypes.MILK_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 0D, 0D, 0D);
    }

  }

  @Override
  protected ItemStack getPickupItem() {
    return new ItemStack(NSBlocks.CHEESE_ARROW.get());
  }

  protected void doPostHurtEffects(LivingEntity target) {
    super.doPostHurtEffects(target);
    target.removeAllEffects();
  }

  public void readAdditionalSaveData(CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
  }

  public void addAdditionalSaveData(CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
  }
}
