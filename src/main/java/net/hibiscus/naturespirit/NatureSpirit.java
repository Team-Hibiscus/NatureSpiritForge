package net.hibiscus.naturespirit;

import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.*;
import net.hibiscus.naturespirit.registration.compat.NSArtsAndCraftsCompat;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.hibiscus.naturespirit.terrablender.*;
import net.hibiscus.naturespirit.blocks.NSCauldronBehavior;
import net.hibiscus.naturespirit.registration.NSVillagers;
import net.hibiscus.naturespirit.world.NSSurfaceRules;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.entity.npc.VillagerType.DESERT;
import static net.minecraft.world.entity.npc.VillagerType.registerBiomeType;


@Mod(NatureSpirit.MOD_ID)
public class NatureSpirit {

    public static final String MOD_ID = "natures_spirit";

    public NatureSpirit() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        NSBlocks.BLOCKS.register(modEventBus);
        NSBlocks.ITEMS.register(modEventBus);
        NSBlocks.BLOCK_ENTITIES.register(modEventBus);
        NSEntityTypes.ENTITIES.register(modEventBus);
        NSItemGroups.CREATIVE_MODE_TABS.register(modEventBus);
        NSParticleTypes.PARTICLES.register(modEventBus);
        NSStatTypes.CUSTOM_STATS.register(modEventBus);
        NSSounds.SOUND_EVENTS.register(modEventBus);
        NSWorldGen.FOLIAGE_PLACERS.register(modEventBus);
        NSWorldGen.TREE_DECORATORS.register(modEventBus);
        NSWorldGen.TRUNK_PLACERS.register(modEventBus);
        NSWorldGen.CARVERS.register(modEventBus);
        NSWorldGen.FEATURES.register(modEventBus);
        NSVillagers.VILLAGER_TYPES.register(modEventBus);
        modEventBus.addListener(this::creativeInventory);
        NSCriteria.registerCriteria();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::wandererTrades);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NSConfig.SPEC);
        modEventBus.addListener(this::addPackFinders);


        if(ModList.get().isLoaded("arts_and_crafts")) {
            NSArtsAndCraftsCompat.ANC_BLOCKS.register(modEventBus);
            NSArtsAndCraftsCompat.ANC_ITEMS.register(modEventBus);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Regions.register(new TerraFeraxRegion(new ResourceLocation(MOD_ID, "terra_ferax"), NSConfig.terraFeraxWeight));
        Regions.register(new TerraSolarisRegion(new ResourceLocation(MOD_ID, "terra_solaris"), NSConfig.terraSolarisWeight));
        Regions.register(new TerraFlavaRegion(new ResourceLocation(MOD_ID, "terra_flava"), NSConfig.terraFlavaWeight));
        Regions.register(new TerraMaterRegion(new ResourceLocation(MOD_ID, "terra_mater"), NSConfig.terraMaterWeight));
        Regions.register(new TerraLaetaRegion(new ResourceLocation(MOD_ID, "terra_laeta"), NSConfig.terraLaetaWeight));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSSurfaceRules.makeRules());

        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SCORCHED_GRASS.getId(), NSBlocks.POTTED_SCORCHED_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BEACH_GRASS.getId(), NSBlocks.POTTED_BEACH_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SEDGE_GRASS.getId(), NSBlocks.POTTED_SEDGE_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FLAXEN_FERN.getId(), NSBlocks.POTTED_FLAXEN_FERN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.OAT_GRASS.getId(), NSBlocks.POTTED_OAT_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MELIC_GRASS.getId(), NSBlocks.POTTED_MELIC_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.LUSH_FERN.getId(), NSBlocks.POTTED_LUSH_FERN);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FRIGID_GRASS.getId(), NSBlocks.POTTED_FRIGID_GRASS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.GREEN_BEARBERRIES.getId(), NSBlocks.POTTED_GREEN_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RED_BEARBERRIES.getId(), NSBlocks.POTTED_RED_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_BEARBERRIES.getId(), NSBlocks.POTTED_PURPLE_BEARBERRIES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ORNATE_SUCCULENT.getId(), NSBlocks.POTTED_ORNATE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.DROWSY_SUCCULENT.getId(), NSBlocks.POTTED_DROWSY_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.AUREATE_SUCCULENT.getId(), NSBlocks.POTTED_AUREATE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SAGE_SUCCULENT.getId(), NSBlocks.POTTED_SAGE_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FOAMY_SUCCULENT.getId(), NSBlocks.POTTED_FOAMY_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.IMPERIAL_SUCCULENT.getId(), NSBlocks.POTTED_IMPERIAL_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.REGAL_SUCCULENT.getId(), NSBlocks.POTTED_REGAL_SUCCULENT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SHIITAKE_MUSHROOM.getId(), NSBlocks.POTTED_SHIITAKE_MUSHROOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MARIGOLD.getFlowerBlock().getId(), NSBlocks.MARIGOLD.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLUEBELL.getFlowerBlock().getId(), NSBlocks.BLUEBELL.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.TIGER_LILY.getFlowerBlock().getId(), NSBlocks.TIGER_LILY.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().getId(), NSBlocks.PURPLE_WILDFLOWER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().getId(), NSBlocks.YELLOW_WILDFLOWER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RED_HEATHER.getFlowerBlock().getId(), NSBlocks.RED_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WHITE_HEATHER.getFlowerBlock().getId(), NSBlocks.WHITE_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PURPLE_HEATHER.getFlowerBlock().getId(), NSBlocks.PURPLE_HEATHER.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ANEMONE.getFlowerBlock().getId(), NSBlocks.ANEMONE.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().getId(), NSBlocks.DWARF_BLOSSOMS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PROTEA.getFlowerBlock().getId(), NSBlocks.PROTEA.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.HIBISCUS.getFlowerBlock().getId(), NSBlocks.HIBISCUS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLUE_IRIS.getFlowerBlock().getId(), NSBlocks.BLUE_IRIS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.BLACK_IRIS.getFlowerBlock().getId(), NSBlocks.BLACK_IRIS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().getId(), NSBlocks.RUBY_BLOSSOMS.getPottedFlowerBlock());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.REDWOOD.getSapling().getId(), NSBlocks.REDWOOD.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SUGI.getSapling().getId(), NSBlocks.SUGI.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getPurpleSapling().getId(), NSBlocks.WISTERIA.getPottedPurpleSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getWhiteSapling().getId(), NSBlocks.WISTERIA.getPottedWhiteSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getBlueSapling().getId(), NSBlocks.WISTERIA.getPottedBlueSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WISTERIA.getPinkSapling().getId(), NSBlocks.WISTERIA.getPottedPinkSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.FIR.getSapling().getId(), NSBlocks.FIR.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.WILLOW.getSapling().getId(), NSBlocks.WILLOW.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.ASPEN.getSapling().getId(), NSBlocks.ASPEN.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getRedSapling().getId(), NSBlocks.MAPLE.getPottedRedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getOrangeSapling().getId(), NSBlocks.MAPLE.getPottedOrangeSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAPLE.getYellowSapling().getId(), NSBlocks.MAPLE.getPottedYellowSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.CYPRESS.getSapling().getId(), NSBlocks.CYPRESS.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.OLIVE.getSapling().getId(), NSBlocks.OLIVE.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.JOSHUA.getSapling().getId(), NSBlocks.JOSHUA.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.GHAF.getSapling().getId(), NSBlocks.GHAF.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.PALO_VERDE.getSapling().getId(), NSBlocks.PALO_VERDE.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.CEDAR.getSapling().getId(), NSBlocks.CEDAR.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.LARCH.getSapling().getId(), NSBlocks.LARCH.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.MAHOGANY.getSapling().getId(), NSBlocks.MAHOGANY.getPottedSapling());
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NSBlocks.SAXAUL.getSapling().getId(), NSBlocks.SAXAUL.getPottedSapling());

            registerBiomeType(NSBiomes.WISTERIA_FOREST, NSVillagers.WISTERIA.get());
            registerBiomeType(NSBiomes.CYPRESS_FIELDS, NSVillagers.CYPRESS.get());
            registerBiomeType(NSBiomes.CARNATION_FIELDS, NSVillagers.CYPRESS.get());
            registerBiomeType(NSBiomes.LAVENDER_FIELDS, NSVillagers.CYPRESS.get());
            registerBiomeType(NSBiomes.STRATIFIED_DESERT, NSVillagers.ADOBE.get());
            registerBiomeType(NSBiomes.LIVELY_DUNES, NSVillagers.ADOBE.get());
            registerBiomeType(NSBiomes.BLOOMING_DUNES, NSVillagers.ADOBE.get());
            registerBiomeType(NSBiomes.DRYLANDS, DESERT);
            registerBiomeType(NSBiomes.WOODED_DRYLANDS, DESERT);
            registerBiomeType(NSBiomes.TROPICAL_SHORES, NSVillagers.COCONUT.get());

            ComposterBlock.COMPOSTABLES.put(NSBlocks.RED_MOSS_BLOCK.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.RED_MOSS_CARPET.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LOTUS_FLOWER_ITEM.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LOTUS_STEM.get(), 0.2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.AZOLLA_ITEM.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.HELVOLA_FLOWER_ITEM.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.HELVOLA_PAD_ITEM.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.DESERT_TURNIP.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.DESERT_TURNIP_BLOCK.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WHOLE_PIZZA.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.THREE_QUARTERS_PIZZA.get(), .85F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.HALF_PIZZA.get(), .5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.QUARTER_PIZZA.get(), .35F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.AUREATE_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.DROWSY_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FOAMY_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SAGE_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.IMPERIAL_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ORNATE_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.REGAL_SUCCULENT_ITEM.get(), .65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_THATCH.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_THATCH_CARPET.get(), 0.1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_THATCH_STAIRS.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_THATCH_SLAB.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.XERIC_THATCH.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.XERIC_THATCH_CARPET.get(), 0.1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.XERIC_THATCH_STAIRS.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.XERIC_THATCH_SLAB.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.EVERGREEN_THATCH.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.EVERGREEN_THATCH_CARPET.get(), 0.1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.EVERGREEN_THATCH_STAIRS.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.EVERGREEN_THATCH_SLAB.get(), 0.3F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.REDWOOD.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SUGI.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPurpleSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getWhiteSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getBlueSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPinkSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FIR.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WILLOW.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ASPEN.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getRedSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getOrangeSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getYellowSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CYPRESS.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.OLIVE.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.JOSHUA.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GHAF.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PALO_VERDE.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CEDAR.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LARCH.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAHOGANY.getSapling().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SAXAUL.getSapling().get(), .3F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.REDWOOD.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.REDWOOD.getFrostyLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SUGI.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPurpleLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getWhiteLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getBlueLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPinkLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPartPurpleLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPartWhiteLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPartBlueLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPartPinkLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FIR.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FIR.getFrostyLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WILLOW.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ASPEN.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ASPEN.getYellowLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getRedLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getOrangeLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAPLE.getYellowLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CYPRESS.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.OLIVE.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.JOSHUA.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GHAF.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PALO_VERDE.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CEDAR.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LARCH.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MAHOGANY.getLeaves().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SAXAUL.getLeaves().get(), .3F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPurpleVines().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getWhiteVines().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getBlueVines().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WISTERIA.getPinkVines().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WILLOW.getVines().get(), .3F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.LAVENDER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BLEEDING_HEART.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BLUE_BULBS.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CARNATION.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GARDENIA.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SNAPDRAGON.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FOXGLOVE.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BEGONIA.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MARIGOLD.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BLUEBELL.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TIGER_LILY.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.RED_HEATHER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.WHITE_HEATHER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PURPLE_HEATHER.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ANEMONE.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PROTEA.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.HIBISCUS.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BLUE_IRIS.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BLACK_IRIS.getFlowerBlock().get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SILVERBUSH.getFlowerBlock().get(), .4F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.CATTAIL.get(), .4F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_FRIGID_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FRIGID_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_SCORCHED_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SCORCHED_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_BEACH_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.BEACH_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_SEDGE_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SEDGE_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LARGE_FLAXEN_FERN.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.FLAXEN_FERN.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_OAT_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.OAT_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LARGE_LUSH_FERN.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.LUSH_FERN.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.TALL_MELIC_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.MELIC_GRASS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GREEN_BEARBERRIES.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.RED_BEARBERRIES.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PURPLE_BEARBERRIES.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GREEN_BITTER_SPROUTS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.RED_BITTER_SPROUTS.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.PURPLE_BITTER_SPROUTS.get(), .3F);

            ComposterBlock.COMPOSTABLES.put(NSBlocks.OLIVES.get(), .3F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.OLIVE_BRANCH.get(), .5F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_BLOCK.get(), .2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_SPROUT.get(), .2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_SHELL.get(), .1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.COCONUT_HALF.get(), .1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.SHIITAKE_MUSHROOM.get(), .1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.GRAY_POLYPORE.get(), .1F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ALLUAUDIA.get(), .2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.ALLUAUDIA_BUNDLE.get(), .2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.STRIPPED_ALLUAUDIA.get(), .2F);
            ComposterBlock.COMPOSTABLES.put(NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get(), .2F);

            NSCauldronBehavior.registerBehavior();
        });
    }

    public void wandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.RED_MOSS_BLOCK.get(), 2), 5, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.HIBISCUS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.BLUE_IRIS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.BLACK_IRIS.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.ANEMONE.getFlowerBlock().get(), 2), 12, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(NSBlocks.LOTUS_FLOWER_ITEM.get(), 2), 12, 1, 0.5F));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.REDWOOD.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.SUGI.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getPurpleSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getWhiteSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getBlueSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.WISTERIA.getPinkSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.FIR.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.WILLOW.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.ASPEN.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getRedSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getOrangeSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.MAPLE.getYellowSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.CYPRESS.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.OLIVE.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.JOSHUA.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.GHAF.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.PALO_VERDE.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.CEDAR.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.LARCH.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.MAHOGANY.getSapling().get(), 5), 8, 1, 0.5F));
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(NSBlocks.SAXAUL.getSapling().get(), 5), 8, 1, 0.5F));
    }

    public void creativeInventory(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            ArrayList<ItemLike> wood = new ArrayList<>();
            wood.add(Items.BAMBOO_BUTTON);
            for(WoodSet woodset: NSBlocks.getWoodSets()) {
                wood.add(woodset.getLog().get());
                if (woodset.getWoodPreset() == WoodSet.WoodPreset.JOSHUA) {
                    wood.addAll(List.of(woodset.getBundle().get(), woodset.getStrippedLog().get(), woodset.getStrippedBundle().get(), woodset.getPlanks().get()));
                } else if (!woodset.hasBark()) {
                    wood.addAll(List.of(woodset.getStrippedLog().get(), woodset.getPlanks().get()));
                } else {
                    wood.addAll(List.of(woodset.getWood().get(), woodset.getStrippedLog().get(), woodset.getStrippedWood().get(), woodset.getPlanks().get()));
                }
                if (woodset.hasMosaic()) {
                    wood.addAll(List.of(woodset.getMosaic().get(), woodset.getStairs().get(), woodset.getMosaicStairs().get(), woodset.getSlab().get(), woodset.getMosaicSlab().get(),
                            woodset.getFence().get(), woodset.getFenceGate().get(),
                            woodset.getDoor().get(), woodset.getTrapDoor().get(),
                            woodset.getPressurePlate().get(), woodset.getButton().get()));
                } else {
                    wood.addAll(List.of(woodset.getStairs().get(), woodset.getSlab().get(),
                            woodset.getFence().get(), woodset.getFenceGate().get(),
                            woodset.getDoor().get(), woodset.getTrapDoor().get(),
                            woodset.getPressurePlate().get(), woodset.getButton().get()));
                }
            }
            addAllAfterFirst(event.getEntries(), wood, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyCobbled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyCobbledStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyCobbledSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyCobbledWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getPolished().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyBricks().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getMossyBricksWall().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getTilesWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBaseStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBaseSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getChiseled().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getPolished().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getPolishedStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getPolishedSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getPolishedWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getCrackedBricks().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBricksStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBricksSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getBricksWall().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getCrackedTiles().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getTilesStairs().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHERT.getTilesSlab().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getTilesWall().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_SANDSTONE_WALL.get().asItem().getDefaultInstance(), NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CHISELED_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SMOOTH_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SMOOTH_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CUT_PINK_SANDSTONE.get().asItem().getDefaultInstance(), NSBlocks.CUT_PINK_SANDSTONE_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.getEntries().putAfter(Items.CHERRY_LOG.getDefaultInstance(), NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getLog().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.SUGI.getLog().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getLog().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.FIR.getLog().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.WILLOW.getLog().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.ASPEN.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.MAPLE.getLog().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getLog().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.OLIVE.getLog().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getLog().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.GHAF.getLog().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getLog().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.COCONUT.getLog().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.CEDAR.getLog().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.LARCH.getLog().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getLog().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLog().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.getEntries().putAfter(Items.GOLD_ORE.getDefaultInstance(), NSBlocks.CHERT_GOLD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.IRON_ORE.getDefaultInstance(), NSBlocks.CHERT_IRON_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.COAL_ORE.getDefaultInstance(), NSBlocks.CHERT_COAL_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.LAPIS_ORE.getDefaultInstance(), NSBlocks.CHERT_LAPIS_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.DIAMOND_ORE.getDefaultInstance(), NSBlocks.CHERT_DIAMOND_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.REDSTONE_ORE.getDefaultInstance(), NSBlocks.CHERT_REDSTONE_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.EMERALD_ORE.getDefaultInstance(), NSBlocks.CHERT_EMERALD_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.COPPER_ORE.getDefaultInstance(), NSBlocks.CHERT_COPPER_ORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.ANDESITE.getDefaultInstance(), NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.TRAVERTINE.getBase().get().asItem().getDefaultInstance(), NSBlocks.CHERT.getBase().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.getEntries().putAfter(Items.RED_SANDSTONE.getDefaultInstance(), NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_SAND.get().asItem().getDefaultInstance(), NSBlocks.PINK_SANDSTONE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

            event.getEntries().putAfter(Items.BAMBOO.getDefaultInstance(), NSBlocks.OLIVE_BRANCH.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.SWEET_BERRIES.getDefaultInstance(), NSBlocks.COCONUT_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.RED_MUSHROOM.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SHIITAKE_MUSHROOM.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.RED_MUSHROOM_BLOCK.getDefaultInstance(), NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SHIITAKE_MUSHROOM_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_POLYPORE_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.SHROOMLIGHT.getDefaultInstance(), NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.DESERT_TURNIP_ROOT_BLOCK.get().asItem().getDefaultInstance(), NSBlocks.DESERT_TURNIP_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.VINE.getDefaultInstance(), NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getWhiteVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getBlueVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPinkVines().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleVines().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.CACTUS.getDefaultInstance(), NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.STRIPPED_ALLUAUDIA.get().asItem().getDefaultInstance(), NSBlocks.STRIPPED_ALLUAUDIA_BUNDLE.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.LILY_PAD.getDefaultInstance(), NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.HELVOLA_PAD_ITEM.get().asItem().getDefaultInstance(), NSBlocks.HELVOLA_FLOWER_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.HELVOLA_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_FLOWER_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LOTUS_FLOWER_ITEM.get().asItem().getDefaultInstance(), NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LOTUS_STEM.get().asItem().getDefaultInstance(), NSBlocks.AZOLLA_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.FARMLAND.getDefaultInstance(), NSBlocks.SANDY_SOIL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.MOSS_BLOCK.getDefaultInstance(), NSBlocks.RED_MOSS_BLOCK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.MOSS_CARPET.getDefaultInstance(), NSBlocks.RED_MOSS_CARPET.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.PITCHER_PLANT.getDefaultInstance(), NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORNATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.DROWSY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.AUREATE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SAGE_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FOAMY_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.IMPERIAL_SUCCULENT_ITEM.get().getDefaultInstance(), NSBlocks.REGAL_SUCCULENT_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.PEONY.getDefaultInstance(), NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LAVENDER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLEEDING_HEART.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_BULBS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CARNATION.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GARDENIA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SNAPDRAGON.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FOXGLOVE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BEGONIA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.LILY_OF_THE_VALLEY.getDefaultInstance(), NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MARIGOLD.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUEBELL.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TIGER_LILY.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_WILDFLOWER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_HEATHER.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ANEMONE.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.DWARF_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PROTEA.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.HIBISCUS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_IRIS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RUBY_BLOSSOMS.getFlowerBlock().get().asItem().getDefaultInstance(), NSBlocks.SILVERBUSH.getFlowerBlock().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.LARGE_FERN.getDefaultInstance(), NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CATTAIL.get().asItem().getDefaultInstance(), NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_FRIGID_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARGE_FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARGE_LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.TALL_MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_BEARBERRIES.get().asItem().getDefaultInstance(), NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_BITTER_SPROUTS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_BITTER_SPROUTS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.FERN.getDefaultInstance(), NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BEACH_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SCORCHED_GRASS.get().asItem().getDefaultInstance(), NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SEDGE_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FLAXEN_FERN.get().asItem().getDefaultInstance(), NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OAT_GRASS.get().asItem().getDefaultInstance(), NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LUSH_FERN.get().asItem().getDefaultInstance(), NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MELIC_GRASS.get().asItem().getDefaultInstance(), NSBlocks.FRIGID_GRASS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.CHERRY_LEAVES.getDefaultInstance(), NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPartPurpleLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPartWhiteLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPartBlueLeaves().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPartPinkLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getFrostyLeaves().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getRedLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getOrangeLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getYellowLeaves().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getLeaves().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getLeaves().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.CHERRY_SAPLING.getDefaultInstance(), NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPurpleSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getWhiteSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getBlueSapling().get().asItem().getDefaultInstance(), NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getPinkSapling().get().asItem().getDefaultInstance(), NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getSapling().get().asItem().getDefaultInstance(), NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getRedSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getOrangeSapling().get().asItem().getDefaultInstance(), NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getYellowSapling().get().asItem().getDefaultInstance(), NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getSapling().get().asItem().getDefaultInstance(), NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getSapling().get().asItem().getDefaultInstance(), NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getSapling().get().asItem().getDefaultInstance(), NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getSapling().get().asItem().getDefaultInstance(), NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getSapling().get().asItem().getDefaultInstance(), NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getSapling().get().asItem().getDefaultInstance(), NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getSapling().get().asItem().getDefaultInstance(), NSBlocks.SAXAUL.getSapling().get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.getEntries().putAfter(Items.BAMBOO_HANGING_SIGN.getDefaultInstance(), NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getSignItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getSignItem().get().getDefaultInstance(), NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getSignItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.FIR.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getSignItem().get().getDefaultInstance(), NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getSignItem().get().getDefaultInstance(), NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getHangingSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getSignItem().get().getDefaultInstance(), NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getSignItem().get().getDefaultInstance(), NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getSignItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getHangingSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getSignItem().get().getDefaultInstance(), NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getSignItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getHangingSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getSignItem().get().getDefaultInstance(), NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getHangingSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getSignItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getHangingSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT.getSignItem().get().getDefaultInstance(), NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT.getHangingSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getSignItem().get().getDefaultInstance(), NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getHangingSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getSignItem().get().getDefaultInstance(), NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getHangingSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getSignItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getHangingSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SAXAUL.getSignItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getHangingSignItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.SOUL_LANTERN.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
            event.getEntries().putAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);

        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(Items.BAMBOO_CHEST_RAFT.getDefaultInstance(), NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getBoatItem().get().getDefaultInstance(), NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.REDWOOD.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getBoatItem().get().getDefaultInstance(), NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SUGI.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getBoatItem().get().getDefaultInstance(), NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WISTERIA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getBoatItem().get().getDefaultInstance(), NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.FIR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getBoatItem().get().getDefaultInstance(), NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WILLOW.getChestBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getBoatItem().get().getDefaultInstance(), NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ASPEN.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getBoatItem().get().getDefaultInstance(), NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAPLE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getBoatItem().get().getDefaultInstance(), NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYPRESS.getChestBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getBoatItem().get().getDefaultInstance(), NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.OLIVE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getBoatItem().get().getDefaultInstance(), NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.JOSHUA.getChestBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getBoatItem().get().getDefaultInstance(), NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GHAF.getChestBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getBoatItem().get().getDefaultInstance(), NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PALO_VERDE.getChestBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT.getBoatItem().get().getDefaultInstance(), NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT.getChestBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getBoatItem().get().getDefaultInstance(), NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CEDAR.getChestBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getBoatItem().get().getDefaultInstance(), NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LARCH.getChestBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getBoatItem().get().getDefaultInstance(), NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAHOGANY.getChestBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.SAXAUL.getBoatItem().get().getDefaultInstance(), NSBlocks.SAXAUL.getChestBoatItem().get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS){
            event.getEntries().putAfter(Items.PINK_TERRACOTTA.getDefaultInstance(), NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN_SLAB.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN_BRICKS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN_BRICK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_KAOLIN_BRICK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_CHALK.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_CHALK.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_CHALK.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_CHALK.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_CHALK.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_CHALK.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PINK_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_CHALK_SLAB.get().asItem().getDefaultInstance(), NSBlocks.PINK_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            
            event.getEntries().putAfter(Items.PINK_CANDLE.getDefaultInstance(), NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.WHITE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GRAY_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLACK_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BROWN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.RED_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.ORANGE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.YELLOW_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIME_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.GREEN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.CYAN_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.LIGHT_BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.BLUE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.PURPLE_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.MAGENTA_PAPER_LANTERN.get().asItem().getDefaultInstance(), NSBlocks.PINK_PAPER_LANTERN.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


            if(ModList.get().isLoaded("arts_and_crafts")) {
                event.getEntries().putBefore(NSBlocks.WHITE_CHALK.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putBefore(NSBlocks.WHITE_CHALK_STAIRS.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_STAIRS.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putBefore(NSBlocks.WHITE_CHALK_SLAB.get().asItem().getDefaultInstance(), NSArtsAndCraftsCompat.BLEACHED_CHALK_SLAB.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.AMETHYST_SHARD.getDefaultInstance(), NSBlocks.CALCITE_SHARD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.HONEYCOMB.getDefaultInstance(), NSBlocks.CHALK_POWDER.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.BOWL.getDefaultInstance(), NSBlocks.COCONUT_SHELL.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.getEntries().putAfter(Items.BEETROOT.getDefaultInstance(), NSBlocks.COCONUT_HALF.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.COCONUT_HALF.get().getDefaultInstance(), NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(NSBlocks.DESERT_TURNIP.get().getDefaultInstance(), NSBlocks.OLIVES.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.BREAD.getDefaultInstance(), NSBlocks.WHOLE_PIZZA.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.MILK_BUCKET.getDefaultInstance(), NSBlocks.CHEESE_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
    public static void addAllAfterFirst(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ArrayList<ItemLike> itemStacks, CreativeModeTab.TabVisibility tabVisibility) {
        for (int i = 1; i < itemStacks.size(); i++) {
            entries.putAfter(itemStacks.get(i-1).asItem().getDefaultInstance(), itemStacks.get(i).asItem().getDefaultInstance(), tabVisibility);
        }
    }

    public void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource((packConsumer) -> {
                if(ModList.get().isLoaded("arts_and_crafts")) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/arts_and_crafts_res",
                                    Component.translatable("pack.natures_spirit.arts_and_crafts"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/arts_and_crafts_res")),
                                    PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                packConsumer.accept(
                        Pack.readMetaAndCreate(
                                "builtin/plank_consistency",
                                Component.translatable("pack.natures_spirit.plank_consistency"),
                                false,
                                (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/plank_consistency")),
                                PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                );
                packConsumer.accept(
                        Pack.readMetaAndCreate(
                                "builtin/emissive_ores_compatibility",
                                Component.translatable("pack.natures_spirit.emissive_ores_compatibility"),
                                false,
                                (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/emissive_ores_compatibility")),
                                PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                );

            });
        }
        if (event.getPackType() == PackType.SERVER_DATA) {
            event.addRepositorySource((packConsumer) -> {
                if(ModList.get().isLoaded("arts_and_crafts")) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/arts_and_crafts_dat",
                                    Component.translatable("pack.natures_spirit.arts_and_crafts"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/arts_and_crafts_dat")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.badlandsToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_badlands",
                                    Component.translatable("pack.natures_spirit.modified_badlands"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_badlands")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.birchForestToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_birch_forest",
                                    Component.translatable("pack.natures_spirit.modified_birch_forest"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_birch_forest")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.darkForestToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_dark_forest",
                                    Component.translatable("pack.natures_spirit.modified_dark_forest"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_dark_forest")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.desertToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_desert",
                                    Component.translatable("pack.natures_spirit.modified_desert"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_desert")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.flowerForestToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_flower_forest",
                                    Component.translatable("pack.natures_spirit.modified_flower_forest"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_flower_forest")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.jungleToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_jungle",
                                    Component.translatable("pack.natures_spirit.modified_jungle"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_jungle")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.mountainBiomesToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_mountain_biomes",
                                    Component.translatable("pack.natures_spirit.modified_mountain_biomes"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_mountain_biomes")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.savannaToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_savannas",
                                    Component.translatable("pack.natures_spirit.modified_savannas"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_savannas")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.swampToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_swamp",
                                    Component.translatable("pack.natures_spirit.modified_swamp"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_swamp")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.vanillaTreesToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_vanilla_trees",
                                    Component.translatable("pack.natures_spirit.modified_vanilla_trees"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_vanilla_trees")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }
                if (NSConfig.windsweptHillsToggle) {
                    packConsumer.accept(
                            Pack.readMetaAndCreate(
                                    "builtin/modified_windswept_hills",
                                    Component.translatable("pack.natures_spirit.modified_windswept_hills"),
                                    true,
                                    (path) -> new PathPackResources(path, false, ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/modified_windswept_hills")),
                                    PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN)
                    );
                }

            });
        }
    }

}