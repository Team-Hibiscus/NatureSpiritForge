package net.hibiscus.naturespirit.registration;


import net.hibiscus.naturespirit.advancements.CoconutHitCriterion;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;

public class NSCriteria {

  public static final CoconutHitCriterion COCONUT_HIT_CRITERION = CriteriaTriggers.register(new CoconutHitCriterion());

  public static void registerCriteria() {}
}