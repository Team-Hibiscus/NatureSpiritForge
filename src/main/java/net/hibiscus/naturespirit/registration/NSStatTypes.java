package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NSStatTypes {
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, NatureSpirit.MOD_ID);
    public static final RegistryObject<ResourceLocation> EAT_CHEESE = CUSTOM_STATS.register("eat_cheese", () -> new ResourceLocation(NatureSpirit.MOD_ID, "eat_cheese"));
    public static final RegistryObject<ResourceLocation> EAT_PIZZA_SLICE = CUSTOM_STATS.register("eat_pizza_slice", () -> new ResourceLocation(NatureSpirit.MOD_ID, "eat_pizza_slice"));
}
