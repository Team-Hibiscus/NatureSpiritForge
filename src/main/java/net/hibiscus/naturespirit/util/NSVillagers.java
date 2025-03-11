/*
package net.hibiscus.naturespirit.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;
import net.hibiscus.naturespirit.registration.NSBiomes;
import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;
import static net.minecraft.world.entity.npc.VillagerType.BY_BIOME;
import static net.minecraft.world.entity.npc.VillagerType.DESERT;

public class NSVillagers {


  public static final VillagerType WISTERIA = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "wisteria"));
  public static final VillagerType CYPRESS = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "cypress"));
  public static final VillagerType ADOBE = VillagerType.register(new ResourceLocation(MOD_ID, "adobe"));
  public static final VillagerType COCONUT = VillagerTypeHelper.register(new ResourceLocation(MOD_ID, "coconut"));

  public static void registerVillagers() {
    BY_BIOME.put(NSBiomes.WISTERIA_FOREST, WISTERIA);
    BY_BIOME.put(NSBiomes.CYPRESS_FIELDS, CYPRESS);
    BY_BIOME.put(NSBiomes.CARNATION_FIELDS, CYPRESS);
    BY_BIOME.put(NSBiomes.LAVENDER_FIELDS, CYPRESS);
    BY_BIOME.put(NSBiomes.STRATIFIED_DESERT, ADOBE);
    BY_BIOME.put(NSBiomes.LIVELY_DUNES, ADOBE);
    BY_BIOME.put(NSBiomes.BLOOMING_DUNES, ADOBE);
    BY_BIOME.put(NSBiomes.DRYLANDS, DESERT);
    BY_BIOME.put(NSBiomes.WOODED_DRYLANDS, DESERT);
    BY_BIOME.put(NSBiomes.TROPICAL_SHORES, COCONUT);

    TradeOfferHelper.registerWanderingTraderOffers(1,
        factories -> {
          factories.add(new VillagerTrades.ItemsForEmeralds(NSMiscBlocks.RED_MOSS_BLOCK, 1, 2, 5, 1));
          factories.add(new VillagerTrades.ItemsForEmeralds(NSMiscBlocks.BLUEBELL.getFlowerBlock(), 1, 1, 12, 1));
          factories.add(new VillagerTrades.ItemsForEmeralds(NSMiscBlocks.ANEMONE.getFlowerBlock(), 1, 1, 12, 1));
          factories.add(new VillagerTrades.ItemsForEmeralds(NSMiscBlocks.LOTUS_FLOWER_ITEM, 1, 1, 12, 1));
        }
    );

  }

}
*/
