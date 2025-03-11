package net.hibiscus.naturespirit;

import com.mojang.logging.LogUtils;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.terrablender.*;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(NatureSpirit.MOD_ID)
public class NatureSpirit {

    public static final String MOD_ID = "natures_spirit";

    public NatureSpirit() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        NSMiscBlocks.BLOCK_ENTITY_TYPES.register(modEventBus);
        NSMiscBlocks.MISC_BLOCKS.register(modEventBus);
        NSMiscBlocks.MISC_ITEMS.register(modEventBus);
        NSEntityTypes.ENTITY_TYPES.register(modEventBus);
        NSParticleTypes.PARTICLE_TYPES.register(modEventBus);
        NSStatTypes.CUSTOM_STATS.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NSConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        Regions.register(new TerraFeraxRegion(new ResourceLocation(MOD_ID, "terra_ferax"), NSConfig.terraFeraxWeight));
        Regions.register(new TerraSolarisRegion(new ResourceLocation(MOD_ID, "terra_solaris"), NSConfig.terraSolarisWeight));
        Regions.register(new TerraFlavaRegion(new ResourceLocation(MOD_ID, "terra_flava"), NSConfig.terraFlavaWeight));
        Regions.register(new TerraMaterRegion(new ResourceLocation(MOD_ID, "terra_mater"), NSConfig.terraMaterWeight));
        Regions.register(new TerraLaetaRegion(new ResourceLocation(MOD_ID, "terra_laeta"), NSConfig.terraLaetaWeight));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());

        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.RED_MOSS_BLOCK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.RED_MOSS_CARPET.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.LOTUS_FLOWER_ITEM.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.AZOLLA_ITEM.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.HELVOLA_FLOWER_ITEM.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.HELVOLA_PAD_ITEM.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.DESERT_TURNIP.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.DESERT_TURNIP_BLOCK.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.DESERT_TURNIP_ROOT_BLOCK.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.WHOLE_PIZZA.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.THREE_QUARTERS_PIZZA.get(), .85F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.HALF_PIZZA.get(), .5F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.QUARTER_PIZZA.get(), .35F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.AUREATE_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.DROWSY_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.FOAMY_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.SAGE_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.IMPERIAL_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.ORNATE_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSMiscBlocks.REGAL_SUCCULENT_ITEM.get(), .65F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.COCONUT_THATCH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.COCONUT_THATCH_CARPET.get(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.COCONUT_THATCH_STAIRS.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.COCONUT_THATCH_SLAB.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.XERIC_THATCH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.XERIC_THATCH_CARPET.get(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.XERIC_THATCH_STAIRS.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.XERIC_THATCH_SLAB.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.EVERGREEN_THATCH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.EVERGREEN_THATCH_CARPET.get(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.EVERGREEN_THATCH_STAIRS.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(NSWoods.EVERGREEN_THATCH_SLAB.get(), 0.3F);
    }
}