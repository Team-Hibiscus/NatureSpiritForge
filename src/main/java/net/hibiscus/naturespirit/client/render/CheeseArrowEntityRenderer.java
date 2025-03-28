package net.hibiscus.naturespirit.client.render;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.entity.CheeseArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CheeseArrowEntityRenderer extends ArrowRenderer<CheeseArrowEntity> {

  public static final ResourceLocation TEXTURE = new ResourceLocation(NatureSpirit.MOD_ID, "textures/entity/projectiles/cheese_arrow.png");

  public CheeseArrowEntityRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(CheeseArrowEntity cheeseArrowEntity) {
    return TEXTURE;
  }
}
