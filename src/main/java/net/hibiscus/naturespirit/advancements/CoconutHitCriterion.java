package net.hibiscus.naturespirit.advancements;

import com.google.gson.JsonObject;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;

public class CoconutHitCriterion extends SimpleCriterionTrigger <CoconutHitCriterion.Conditions> {
  static final ResourceLocation ID = new ResourceLocation(NatureSpirit.MOD_ID, "coconut_hit");

  public CoconutHitCriterion() {
  }

  public ResourceLocation getId() {
    return ID;
  }

  public Conditions createInstance(JsonObject jsonObject, ContextAwarePredicate lootContextPredicate, DeserializationContext advancementEntityPredicateDeserializer) {
    ContextAwarePredicate lootContextPredicate2 = EntityPredicate.fromJson(jsonObject, "projectile", advancementEntityPredicateDeserializer);
    return new Conditions(lootContextPredicate, lootContextPredicate2);
  }

  public void trigger(ServerPlayer player, Entity projectile) {
    LootContext lootContext = EntityPredicate.createContext(player, projectile);
    this.trigger(player, (conditions) -> {
      return conditions.test(lootContext);
    });
  }

  public static class Conditions extends AbstractCriterionTriggerInstance {
    private final ContextAwarePredicate projectile;

    public Conditions(ContextAwarePredicate player, ContextAwarePredicate projectile) {
      super(CoconutHitCriterion.ID, player);
      this.projectile = projectile;
    }

    public static Conditions create(ContextAwarePredicate projectile) {
      return new Conditions(ContextAwarePredicate.ANY, projectile);
    }

    public JsonObject serializeToJson(SerializationContext predicateSerializer) {
      JsonObject jsonObject = super.serializeToJson(predicateSerializer);
      jsonObject.add("projectile", this.projectile.toJson(predicateSerializer));
      return jsonObject;
    }

    public boolean test(LootContext projectileContext) {
      return this.projectile.matches(projectileContext);
    }
  }
}