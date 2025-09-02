package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NSTags {

  public static class Items {

    public static final TagKey<Item> PIZZA_TOPPINGS = createTag("pizza_toppings");
    public static final TagKey<Item> DISABLED_PIZZA_TOPPINGS = createTag("disabled_pizza_toppings");
    public static final TagKey<Item> CHEESE_MAKER = createTag("cheese_maker");
    public static final TagKey<Item> EVERGREEN_LEAVES = createTag("evergreen_leaves");
    public static final TagKey<Item> XERIC_LEAVES = createTag("xeric_leaves");
    public static final TagKey<Item> COCONUT_ITEMS = createTag("coconut_items");
    public static final TagKey<Item> SUCCULENTS = createTag("succulents");
    public static final TagKey<Item> STRIPPED_LOGS = createTag("stripped_logs");
    public static final TagKey<Item> ALLUAUDIA_BLOCKS = createTag("alluaudia_blocks");
    public static final TagKey<Item> KAOLIN = createTag("kaolin");
    public static final TagKey<Item> KAOLIN_STAIRS = createTag("kaolin_stairs");
    public static final TagKey<Item> KAOLIN_SLABS = createTag("kaolin_slabs");
    public static final TagKey<Item> KAOLIN_BRICKS = createTag("kaolin_bricks");
    public static final TagKey<Item> KAOLIN_BRICK_STAIRS = createTag("kaolin_brick_stairs");
    public static final TagKey<Item> KAOLIN_BRICK_SLABS = createTag("kaolin_brick_slabs");
    public static final TagKey<Item> CHALK = createTag("chalk");
    public static final TagKey<Item> CHALK_STAIRS = createTag("chalk_stairs");
    public static final TagKey<Item> CHALK_SLABS = createTag("chalk_slabs");
    public static final TagKey<Item> COCONUT_HALVES = createTag("coconut_halves");
    public static final TagKey<Item> OLIVES = createTag("olives");
      public static final TagKey<Item> FORGE_AXES = createForgeTag("tools/axes");

    private static TagKey<Item> createTag(String name) {
      return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name));
    }
      private static TagKey<Item> createForgeTag(String name) {
          return TagKey.create(Registries.ITEM, new ResourceLocation("forge", name));
      }
  }

  public static class EntityTypes {

    public static final TagKey<EntityType<?>> CANT_SUCCULENT_SLOWED = createTag("cant_succulent_slowed");
    public static final TagKey<EntityType<?>> IMPERMEABLE_TO_AZOLLA = createTag("impermeable_to_azolla");

    private static TagKey<EntityType<?>> createTag(String name) {
      return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name));
    }
  }

  public static class Blocks {

    public static final TagKey<Block> TURNIP_STEM_GROWS_ON = createTag("turnip_stem_grows_on");
    public static final TagKey<Block> TURNIP_ROOT_REPLACEABLE = createTag("turnip_root_replaceable");
    public static final TagKey<Block> SHIITAKE_MUSHROOM_GROW_BLOCK = createTag("shiitake_mushroom_grow_block");
    public static final TagKey<Block> KAOLIN = createTag("kaolin");
    public static final TagKey<Block> KAOLIN_STAIRS = createTag("kaolin_stairs");
    public static final TagKey<Block> KAOLIN_SLABS = createTag("kaolin_slabs");
    public static final TagKey<Block> KAOLIN_BRICKS = createTag("kaolin_bricks");
    public static final TagKey<Block> KAOLIN_BRICK_STAIRS = createTag("kaolin_brick_stairs");
    public static final TagKey<Block> KAOLIN_BRICK_SLABS = createTag("kaolin_brick_slabs");
    public static final TagKey<Block> CHALK = createTag("chalk");
    public static final TagKey<Block> CHALK_STAIRS = createTag("chalk_stairs");
    public static final TagKey<Block> CHALK_SLABS = createTag("chalk_slabs");
    public static final TagKey<Block> STRIPPED_LOGS = createTag("stripped_logs");
    public static final TagKey<Block> ALLUAUDIA_BLOCKS = createTag("alluaudia_blocks");
    public static final TagKey<Block> SUCCULENTS = createTag("succulents");
    public static final TagKey<Block> SUCCULENT_VERTICAL_PLACEMENT_OVERRIDE = createTag("succulent_vertical_placement_override");
    public static final TagKey<Block> SUCCULENT_HORIZONTAL_PLACEMENT_OVERRIDE = createTag("succulent_horizontal_placement_override");

    private static TagKey<Block> createTag(String name) {
      return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(NatureSpirit.MOD_ID, name));
    }
  }
}
