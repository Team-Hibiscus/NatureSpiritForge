package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerParticleType;

public class NSParticleTypes {

  public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NatureSpirit.MOD_ID);
  public static final RegistryObject<SimpleParticleType> RED_MAPLE_LEAVES_PARTICLE = registerParticleType("red_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> ORANGE_MAPLE_LEAVES_PARTICLE = registerParticleType("orange_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> YELLOW_MAPLE_LEAVES_PARTICLE = registerParticleType("yellow_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> MILK_PARTICLE = registerParticleType("milk", () -> new SimpleParticleType(false));

  public static void registerParticleTypes() {
  }
}
