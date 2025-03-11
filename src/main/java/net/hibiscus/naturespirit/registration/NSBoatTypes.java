package net.hibiscus.naturespirit.registration;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NSBoatTypes {

  private static final Map<Boat.Type, Tuple<Item, Item>> BOAT_TYPES_TO_ITEMS = new Object2ObjectLinkedOpenHashMap<>();

  public static void addBoatTypeItems(Boat.Type type, Item boatItem, Item chestBoatItem) {
    BOAT_TYPES_TO_ITEMS.put(type, new Tuple<>(boatItem, chestBoatItem));
  }

  public static Optional<Item> getBoatItem(Boat.Type type) {
    return Optional.ofNullable(BOAT_TYPES_TO_ITEMS.getOrDefault(type, new Tuple<>(null, null)).getA());
  }

  public static Optional<Item> getChestBoatItem(Boat.Type type) {
    return Optional.ofNullable(BOAT_TYPES_TO_ITEMS.getOrDefault(type, new Tuple<>(null, null)).getB());
  }

  public static @NotNull List<Item> getAllBoatItems() {
    List<Item> items = new ArrayList<>();
    BOAT_TYPES_TO_ITEMS.forEach((type, pair) -> {
      items.add(pair.getA());
      items.add(pair.getB());
    });
    return items;
  }

  public static void setBoatTypeBaseItem(Boat.Type type, Block baseBlock) {
    type.planks = baseBlock;
  }

  public static Boat.Type REDWOOD;
  public static Boat.Type SUGI;
  public static Boat.Type WISTERIA;
  public static Boat.Type FIR;
  public static Boat.Type WILLOW;
  public static Boat.Type ASPEN;
  public static Boat.Type MAPLE;
  public static Boat.Type CYPRESS;
  public static Boat.Type OLIVE;
  public static Boat.Type JOSHUA;
  public static Boat.Type GHAF;
  public static Boat.Type PALO_VERDE;
  public static Boat.Type COCONUT;
  public static Boat.Type CEDAR;
  public static Boat.Type LARCH;
  public static Boat.Type MAHOGANY;
  public static Boat.Type SAXAUL;

  //CREDIT TO nyuppo/fabric-boat-example ON GITHUB
  static {
    Boat.Type.values();
  }

  public static void init() {
  }
}
