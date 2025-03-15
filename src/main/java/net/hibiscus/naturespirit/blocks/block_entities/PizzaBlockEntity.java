package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.hibiscus.naturespirit.registration.NSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PizzaBlockEntity extends BlockEntity {
  public ArrayList<String> toppings = new ArrayList<String>();
  public int topping_number = toppings != null ? toppings.size() : 0;
  public int bites = 0;
  public PizzaBlockEntity(BlockPos pos, BlockState state) {
    super(NSBlocks.PIZZA_BLOCK_ENTITY_TYPE.get(), pos, state);
  }
  @Override
  public void saveAdditional(CompoundTag nbt) {
    ListTag nbtElement = new ListTag();
    for(String string : toppings) {
      nbtElement.add(StringTag.valueOf(string));
    }
    nbt.put("topping_types", nbtElement);
    nbt.putInt("toppings_number", this.topping_number);
    nbt.putInt("pizza_bites", this.bites);
    this.setChanged();

    super.saveAdditional(nbt);
  }
  @Override
  public void load(CompoundTag nbt) {
    ListTag nbt2 = ((ListTag)nbt.get("topping_types"));
    if (nbt2 != null) {
      toppings.clear();
      for(int i = 0; i < nbt2.size(); i++) {
        toppings.add(i, nbt2.getString(i));
      }
    }
    this.topping_number = nbt.getInt("toppings_number");
    this.bites = nbt.getInt("pizza_bites");

    super.load(nbt);
  }
  public boolean checkTopping(ItemStack itemStack) {
    return itemStack.is(NSTags.Items.PIZZA_TOPPINGS);
  }
  public boolean canPlaceTopping(ItemStack itemStack, PizzaBlockEntity pizzaBlockEntity) {
    boolean pizzaTopping = checkTopping(itemStack);
    String itemId = BuiltInRegistries.ITEM.getKey(itemStack.getItem()).toString();
    boolean bl = pizzaBlockEntity.bites == 0 && pizzaBlockEntity.topping_number < 4 && !(itemStack.is(NSTags.Items.DISABLED_PIZZA_TOPPINGS)) && pizzaTopping && !toppings.contains(itemId);
    if (bl) {
      toppings.add(itemId);
    }
    this.setChanged();
    return bl;
  }
  @Nullable
  @Override
  public Packet <ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag() {
    return saveWithoutMetadata();
  }
}