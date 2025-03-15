package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

@SuppressWarnings("unused")
public class NSSounds {

  public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_ASPEN = registerReference("music.overworld.aspen");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_MAPLE = registerReference("music.overworld.maple");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_WISTERIA = registerReference("music.overworld.wisteria");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_REDWOOD = registerReference("music.overworld.redwood");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_DESERT = registerReference("music.overworld.desert");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_ARID = registerReference("music.overworld.arid");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_TROPICAL = registerReference("music.overworld.tropical");
  public static final RegistryObject<SoundEvent> MUSIC_OVERWORLD_ALPINE = registerReference("music.overworld.alpine");

  private static RegistryObject<SoundEvent> registerReference(String id) {
    return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, id)));
  }
}
