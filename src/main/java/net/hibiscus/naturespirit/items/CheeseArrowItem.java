package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CheeseArrowItem extends ArrowItem {
  public CheeseArrowItem(Properties settings) {
    super(settings);
  }

  public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter) {
    return new CheeseArrowEntity(world, shooter);
  }
}