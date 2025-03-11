package net.hibiscus.naturespirit.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

@SuppressWarnings("unused")
public class NSSounds {

  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_ASPEN = registerReference("music.overworld.aspen");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_MAPLE = registerReference("music.overworld.maple");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_WISTERIA = registerReference("music.overworld.wisteria");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_REDWOOD = registerReference("music.overworld.redwood");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_DESERT = registerReference("music.overworld.desert");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_ARID = registerReference("music.overworld.arid");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_TROPICAL = registerReference("music.overworld.tropical");
  public static final Holder.Reference<SoundEvent> MUSIC_OVERWORLD_ALPINE = registerReference("music.overworld.alpine");

  private static Holder.Reference<SoundEvent> registerReference(String id) {
    return registerReference(new ResourceLocation(MOD_ID, id));
  }

  private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id) {
    return registerReference(id, id);
  }

  private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id, ResourceLocation soundId) {
    return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
  }

  public static void registerSounds() {
  }
}
