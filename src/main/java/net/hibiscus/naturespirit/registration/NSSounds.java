package net.hibiscus.naturespirit.registration;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

@SuppressWarnings("unused")
public class NSSounds {

  public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);

  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_ASPEN = registerReference("music.overworld.aspen");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_MAPLE = registerReference("music.overworld.maple");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_WISTERIA = registerReference("music.overworld.wisteria");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_REDWOOD = registerReference("music.overworld.redwood");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_DESERT = registerReference("music.overworld.desert");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_ARID = registerReference("music.overworld.arid");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_TROPICAL = registerReference("music.overworld.tropical");
  public static final Supplier<SoundEvent> MUSIC_OVERWORLD_ALPINE = registerReference("music.overworld.alpine");

  private static Supplier<SoundEvent> registerReference(String id) {
    return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, id)));
  }
}
