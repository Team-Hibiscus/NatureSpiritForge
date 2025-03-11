package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class NSEntityTypes {

  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, NatureSpirit.MOD_ID);

  private static final EntityType.Builder<CheeseArrowEntity> CHEESE_ARROW_ENTITY_BUILDER = EntityType.Builder.of(CheeseArrowEntity::new, MobCategory.MISC);
  public static final RegistryObject<EntityType<CheeseArrowEntity>> CHEESE_ARROW = ENTITY_TYPES.register("cheese_arrow", () -> CHEESE_ARROW_ENTITY_BUILDER.sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(20).build(null));

}
