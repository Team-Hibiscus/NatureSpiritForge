
package net.hibiscus.naturespirit.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSVillagers {

  public static final DeferredRegister<VillagerType> VILLAGER_TYPES = DeferredRegister.create(Registries.VILLAGER_TYPE, MOD_ID);
  public static final RegistryObject<VillagerType> WISTERIA = VILLAGER_TYPES.register( "wisteria", () -> new VillagerType("wisteria"));
  public static final RegistryObject<VillagerType> CYPRESS = VILLAGER_TYPES.register( "cypress", () -> new VillagerType("cypress"));
  public static final RegistryObject<VillagerType> ADOBE = VILLAGER_TYPES.register( "adobe", () -> new VillagerType("adobe"));
  public static final RegistryObject<VillagerType> COCONUT = VILLAGER_TYPES.register( "coconut", () -> new VillagerType("coconut"));

}

