package net.hibiscus.naturespirit.items;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class DesertTurnipItem extends ItemNameBlockItem {
  public DesertTurnipItem(Block block, Properties settings) {
    super(block, settings);
  }
  @Override public int getUseDuration(ItemStack stack) {
    if (stack.getItem().isEdible()) {
      return this.getFoodProperties().isFastFood() ? 24 : 32;
    } else {
      return 0;
    }
  }
}