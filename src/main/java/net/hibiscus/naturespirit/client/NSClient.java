package net.hibiscus.naturespirit.client;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.client.render.CheeseArrowEntityRenderer;
import net.hibiscus.naturespirit.client.render.PizzaBlockEntityRenderer;
import net.hibiscus.naturespirit.client.render.PizzaToppingModel;
import net.hibiscus.naturespirit.registration.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NSClient {

  public static final ModelLayerLocation PIZZA_TOPPING = new ModelLayerLocation(new ResourceLocation(NatureSpirit.MOD_ID, "pizza"), "toppings");

  @SubscribeEvent
  public void registerBlockColors(RegisterColorHandlersEvent.Block event) {
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockState.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? blockPos.below() : blockPos
            ) : -1, NSMiscBlocks.CATTAIL.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSWoods.SUGI.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSWoods.MAHOGANY.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSWoods.LARCH.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSWoods.ASPEN.getLeaves().get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSMiscBlocks.LOTUS_STEM.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSMiscBlocks.LARGE_LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSMiscBlocks.LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSMiscBlocks.POTTED_LUSH_FERN.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    blockPos
            ) : -1, NSMiscBlocks.LOTUS_STEM.get());
    event.register(
            (blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter,
                    new BlockPos(blockPos.getX(), -64, blockPos.getZ())
            ) : -1, NSMiscBlocks.LOTUS_FLOWER.get());
  }

  @SubscribeEvent
  public void registerItemColors(RegisterColorHandlersEvent.Item event) {
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSWoods.SUGI.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSWoods.LARCH.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSWoods.MAHOGANY.getLeaves().get());
    event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), NSWoods.ASPEN.getLeaves().get());
    event.register((stack, tintIndex) -> GrassColor.getDefaultColor(), NSMiscBlocks.LUSH_FERN.get());
    event.register((stack, tintIndex) -> GrassColor.getDefaultColor(), NSMiscBlocks.LARGE_LUSH_FERN.get());
  }

  @SubscribeEvent
  public void registerClient(FMLClientSetupEvent event) {
    ItemBlockRenderTypes.setRenderLayer(NSMiscBlocks.PIZZA_BLOCK.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSMiscBlocks.LARGE_CALCITE_BUD.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSMiscBlocks.SMALL_CALCITE_BUD.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSMiscBlocks.CALCITE_CLUSTER.get(), RenderType.cutout());

    for (RegistryObject<Block> block : NSRegistryHelper.RenderLayerHashMap.values()) {
      ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
    }

    ItemBlockRenderTypes.setRenderLayer(NSWoods.COCONUT_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.COCONUT_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.COCONUT_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.COCONUT_THATCH_STAIRS.get(), RenderType.cutout());

    ItemBlockRenderTypes.setRenderLayer(NSWoods.EVERGREEN_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.EVERGREEN_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.EVERGREEN_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.EVERGREEN_THATCH_STAIRS.get(), RenderType.cutout());

    ItemBlockRenderTypes.setRenderLayer(NSWoods.XERIC_THATCH_CARPET.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.XERIC_THATCH_SLAB.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.XERIC_THATCH.get(), RenderType.cutout());
    ItemBlockRenderTypes.setRenderLayer(NSWoods.XERIC_THATCH_STAIRS.get(), RenderType.cutout());
  }

  @SubscribeEvent
  public void registerParticles(RegisterParticleProvidersEvent event) {
    event.registerSpriteSet(NSParticleTypes.RED_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.ORANGE_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.YELLOW_MAPLE_LEAVES_PARTICLE.get(),
            ((spriteProvider) -> (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new MapleLeavesParticle(world, x, y, z, spriteProvider))
    );
    event.registerSpriteSet(NSParticleTypes.MILK_PARTICLE.get(), SuspendedTownParticle.ComposterFillProvider::new);
  }

  @SubscribeEvent
  public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(NSMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE.get(), PizzaBlockEntityRenderer::new);
    event.registerEntityRenderer(NSEntityTypes.CHEESE_ARROW.get(), CheeseArrowEntityRenderer::new);
  }
  @SubscribeEvent
  public void registerEntityRenderLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(PIZZA_TOPPING, PizzaToppingModel::getTexturedModelData);
  }

}

