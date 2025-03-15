package net.hibiscus.naturespirit.items;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSStatTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaItem extends ItemNameBlockItem {
  public PizzaItem(Block block, Properties settings) {
    super(block, settings);
  }


  public void addBitesToPizza(ItemStack pizza) {
    CompoundTag nbtCompound = pizza.getOrCreateTagElement("BlockEntityTag");
    CompoundTag nbtCompound2 = pizza.getOrCreateTagElement("BlockStateTag");
    assert nbtCompound != null;
    int pizzaSlice = this.asItem() == NSBlocks.WHOLE_PIZZA.get() ? 0 : this.asItem() == NSBlocks.THREE_QUARTERS_PIZZA.get() ? 1 : this.asItem() == NSBlocks.HALF_PIZZA.get() ? 2 : 3;
    nbtCompound.putInt("pizza_bites", pizzaSlice);
    nbtCompound2.putInt("pizza_bites", pizzaSlice);
  }

  public void getAllToppings(ItemStack pizza) {
    CompoundTag nbtCompound = pizza.getOrCreateTagElement("BlockEntityTag");
    assert nbtCompound != null;
    ListTag nbtList = ((ListTag)nbtCompound.get("topping_types"));
    if(nbtList != null) {
      int j = nbtList.size();
      nbtCompound.putInt("toppings_number", j);
    }
  }

  @Override public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
    addBitesToPizza(stack);
    getAllToppings(stack);
  }

  public void appendHoverText(ItemStack stack, @Nullable Level world, List <Component> tooltip, TooltipFlag context) {
    super.appendHoverText(stack, world, tooltip, context);


    CompoundTag nbtCompound = stack.getOrCreateTagElement("BlockEntityTag");
    assert nbtCompound != null;
    ListTag nbtList = ((ListTag)nbtCompound.get("topping_types"));
    if (nbtList != null) {
      int j = nbtList.size();

      for(int i = 0; i < j; ++i) {
        tooltip.add(Component.translatable("block.natures_spirit.pizza." + nbtList.getString(i).replace(":", ".")).withStyle(ChatFormatting.GRAY));
      }
    }
  }

  public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
    ItemStack itemStack = super.finishUsingItem(stack, world, user);
    Item pizzaSlice = this.asItem() == NSBlocks.WHOLE_PIZZA.get() ? NSBlocks.THREE_QUARTERS_PIZZA.get() : this.asItem() == NSBlocks.THREE_QUARTERS_PIZZA.get() ? NSBlocks.HALF_PIZZA.get() : this.asItem() == NSBlocks.HALF_PIZZA.get() ? NSBlocks.QUARTER_PIZZA.get() : Items.AIR;

    Player holder = (Player) user;
    holder.awardStat(NSStatTypes.EAT_PIZZA_SLICE.get());
    int foodAmount = 2;
    float saturationModifier = 0.2F;
    CompoundTag nbtCompound = stack.getOrCreateTagElement("BlockEntityTag");
    assert nbtCompound != null;
    ListTag nbtList = ((ListTag)nbtCompound.get("topping_types"));
    if (nbtList != null) {
      int j = nbtList.size();
      for(int i = 0; i < j; i++) {
        foodAmount++;
        saturationModifier = saturationModifier + 0.1F;
      }
    }
    holder.getFoodData().eat(foodAmount, saturationModifier);

    if(((Player) user).getAbilities().instabuild) {
      return itemStack;
    }
    else {
      assert itemStack.getTag() != null;
      ItemStack itemStack1 = new ItemStack(pizzaSlice, 1);
      itemStack1.setTag(stack.getTag());
      return itemStack1;
    }
  }
}