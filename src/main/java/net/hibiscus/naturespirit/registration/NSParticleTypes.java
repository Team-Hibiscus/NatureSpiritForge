package net.hibiscus.naturespirit.registration;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NSParticleTypes {

  public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NatureSpirit.MOD_ID);
  public static final RegistryObject<SimpleParticleType> RED_MAPLE_LEAVES_PARTICLE = registerParticleType("red_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> ORANGE_MAPLE_LEAVES_PARTICLE = registerParticleType("orange_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> YELLOW_MAPLE_LEAVES_PARTICLE = registerParticleType("yellow_maple_leaves", () -> new SimpleParticleType(false));
  public static final RegistryObject<SimpleParticleType> MILK_PARTICLE = registerParticleType("milk", () -> new SimpleParticleType(false));

  public static <T extends ParticleType> RegistryObject<T> registerParticleType(String name, Supplier<T> particleType) {
    return NSParticleTypes.PARTICLES.register(name, particleType);
  }

}
