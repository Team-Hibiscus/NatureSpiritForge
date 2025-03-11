package net.hibiscus.naturespirit.registration.sets;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.blocks.*;
import net.hibiscus.naturespirit.registration.NSBoatTypes;
import net.hibiscus.naturespirit.registration.NSParticleTypes;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.hibiscus.naturespirit.world.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class WoodSet {

  private final ItemLike leavesBefore;
  private final ItemLike saplingBefore;
  private final ItemLike logBefore;
  private final ItemLike signBefore;
  private final ItemLike boatBefore;
  private final ItemLike buttonBefore;
  private final List<RegistryObject<Block>> registeredBlocksList = new ArrayList<>();
  private final List<RegistryObject<Item>> registeredItemsList = new ArrayList<>();
  private final ResourceLocation name;
  private final MapColor sideColor;
  private final MapColor topColor;
  private final WoodPreset woodPreset;
  private Supplier<BlockSetType> blockSetType;
  private Supplier<WoodType> woodType;
  private RegistryObject<Block> log;
  private RegistryObject<Block> strippedLog;
  private RegistryObject<Block> bundle;
  private RegistryObject<Block> strippedBundle;
  private RegistryObject<Block> wood;
  private RegistryObject<Block> strippedWood;
  private RegistryObject<Block> leaves;
  private RegistryObject<Block> frostyLeaves;
  private RegistryObject<Block> sapling;
  private RegistryObject<Block> pottedSapling;
  private RegistryObject<Block> redLeaves;
  private RegistryObject<Block> redSapling;
  private RegistryObject<Block> pottedRedSapling;
  private RegistryObject<Block> orangeLeaves;
  private RegistryObject<Block> orangeSapling;
  private RegistryObject<Block> pottedOrangeSapling;
  private RegistryObject<Block> yellowLeaves;
  private RegistryObject<Block> yellowSapling;
  private RegistryObject<Block> pottedYellowSapling;
  private RegistryObject<Block> blueLeaves;
  private RegistryObject<Block> partBlueLeaves;
  private RegistryObject<Block> blueSapling;
  private RegistryObject<Block> pottedBlueSapling;
  private RegistryObject<Block> purpleLeaves;
  private RegistryObject<Block> partPurpleLeaves;
  private RegistryObject<Block> purpleSapling;
  private RegistryObject<Block> pottedPurpleSapling;
  private RegistryObject<Block> pinkLeaves;
  private RegistryObject<Block> partPinkLeaves;
  private RegistryObject<Block> pinkSapling;
  private RegistryObject<Block> pottedPinkSapling;
  private RegistryObject<Block> whiteLeaves;
  private RegistryObject<Block> partWhiteLeaves;
  private RegistryObject<Block> whiteSapling;
  private RegistryObject<Block> pottedWhiteSapling;
  private RegistryObject<Block> vines;
  private RegistryObject<Block> vinesPlant;
  private RegistryObject<Block> blueVines;
  private RegistryObject<Block> purpleVines;
  private RegistryObject<Block> pinkVines;
  private RegistryObject<Block> whiteVines;
  private RegistryObject<Block> blueVinesPlant;
  private RegistryObject<Block> purpleVinesPlant;
  private RegistryObject<Block> pinkVinesPlant;
  private RegistryObject<Block> whiteVinesPlant;
  private RegistryObject<Block> planks;
  private RegistryObject<Block> stairs;
  private RegistryObject<Block> slab;
  private RegistryObject<Block> mosaic;
  private RegistryObject<Block> mosaicStairs;
  private RegistryObject<Block> mosaicSlab;
  private RegistryObject<Block> fence;
  private RegistryObject<Block> fenceGate;
  private RegistryObject<Block> pressurePlate;
  private RegistryObject<Block> button;
  private RegistryObject<Block> door;
  private RegistryObject<Block> trapDoor;
  private RegistryObject<Block> sign;
  private RegistryObject<Block> wallSign;
  private RegistryObject<Block> hangingSign;
  private RegistryObject<Block> hangingWallSign;
  private RegistryObject<Item> signItem;
  private RegistryObject<Item> hangingSignItem;
  private RegistryObject<Item> boatItem;
  private RegistryObject<Item> chestBoatItem;
  private final Supplier<Boat.Type> boatTypeSupplier;
  private final AbstractTreeGrower saplingGenerator;
  private final boolean hasMosaic;
  private final List<RegistryObject<Block>> leavesList = new ArrayList<>();
  private final List<RegistryObject<Block>> saplingList = new ArrayList<>();

  private void registerWood() {
    blockSetType = createBlockSetType();
    woodType = Suppliers.memoize(() -> new WoodType(getNameID().getPath(), blockSetType.get()));

    log = woodPreset == WoodPreset.JOSHUA ? createJoshuaLog() : createLog();
    strippedLog = woodPreset == WoodPreset.JOSHUA ? createStrippedJoshuaLog() : createStrippedLog();
    bundle = createBundle();
    strippedBundle = createStrippedBundle();


    if (woodPreset != WoodPreset.BAMBOO && woodPreset != WoodPreset.JOSHUA) {
      wood = createWood();
      strippedWood = createStrippedWood();
    }

    if (this.hasDefaultLeaves()) {
      leaves = createLeaves();
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, leaves));

      if (this.hasDefaultSapling()) {
        sapling = this.isSandy() ? createSandySapling(saplingGenerator) : createSapling(saplingGenerator);
        pottedSapling = createPottedSapling(sapling);
//        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
//        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new VillagerTrades.ItemsForEmeralds(sapling, 5, 1, 8, 1)));
      }
    }

    if (woodPreset == WoodPreset.FROSTABLE) {
      frostyLeaves = createLeaves("frosty_");
      leaves = createFrostableLeaves();
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, leaves, frostyLeaves));
      sapling = createSapling(saplingGenerator);
      pottedSapling = createPottedSapling(sapling);
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
//      TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new VillagerTrades.ItemsForEmeralds(sapling, 5, 1, 8, 1)));

    }

    if (woodPreset == WoodPreset.WILLOW) {
      vines = createVines(getVinesPlant());
      vinesPlant = createVinesPlant(vines);

      leaves = createVinesLeavesBlock(vinesPlant, vines);
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, leaves.asItem()));

      sapling = createSapling(saplingGenerator);
      pottedSapling = createPottedSapling(sapling);
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(saplingBefore, sapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(Blocks.VINE, vines.asItem()));
//      TradeOfferHelper.registerWanderingTraderOffers(1, factories -> factories.add(new VillagerTrades.ItemsForEmeralds(sapling, 5, 1, 8, 1)));
    }
    if (woodPreset == WoodPreset.WISTERIA) {
      whiteVines = createVines("white_", getWhiteVinesPlant());
      blueVines = createVines("blue_", getBlueVinesPlant());
      pinkVines = createVines("pink_", getPinkVinesPlant());
      purpleVines = createVines("purple_", getPurpleVinesPlant());
      whiteVinesPlant = createVinesPlant("white_", whiteVines);
      blueVinesPlant = createVinesPlant("blue_", blueVines);
      pinkVinesPlant = createVinesPlant("pink_", pinkVines);
      purpleVinesPlant = createVinesPlant("purple_", purpleVines);
      whiteLeaves = createVinesLeavesBlock("white_", whiteVinesPlant, whiteVines);
      blueLeaves = createVinesLeavesBlock("blue_", blueVinesPlant, blueVines);
      pinkLeaves = createVinesLeavesBlock("pink_", pinkVinesPlant, pinkVines);
      purpleLeaves = createVinesLeavesBlock("purple_", purpleVinesPlant, purpleVines);
      partWhiteLeaves = createVinesLeavesBlock("part_white_", whiteVinesPlant, whiteVines);
      partBlueLeaves = createVinesLeavesBlock("part_blue_", blueVinesPlant, blueVines);
      partPinkLeaves = createVinesLeavesBlock("part_pink_", pinkVinesPlant, pinkVines);
      partPurpleLeaves = createVinesLeavesBlock("part_purple_", purpleVinesPlant, purpleVines);
      whiteSapling = createSapling("white_", new WhiteWisteriaSaplingGenerator());
      blueSapling = createSapling("blue_", new BlueWisteriaSaplingGenerator());
      pinkSapling = createSapling("pink_", new PinkWisteriaSaplingGenerator());
      purpleSapling = createSapling("purple_", new PurpleWisteriaSaplingGenerator());
      pottedWhiteSapling = createPottedSapling("white_", whiteSapling);
      pottedBlueSapling = createPottedSapling("blue_", blueSapling);
      pottedPinkSapling = createPottedSapling("pink_", pinkSapling);
      pottedPurpleSapling = createPottedSapling("purple_", purpleSapling);
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, whiteLeaves.asItem(), partWhiteLeaves, blueLeaves, partBlueLeaves, pinkLeaves, partPinkLeaves, purpleLeaves, partPurpleLeaves));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(Blocks.VINE, whiteVines.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(whiteVines, blueVines.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blueVines, pinkVines.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(pinkVines, purpleVines.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(saplingBefore, whiteSapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(whiteSapling, blueSapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(blueSapling, pinkSapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(pinkSapling, purpleSapling.asItem()));
//      TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
//        factories.add(new VillagerTrades.ItemsForEmeralds(whiteSapling, 5, 1, 8, 1));
//        factories.add(new VillagerTrades.ItemsForEmeralds(blueSapling, 5, 1, 8, 1));
//        factories.add(new VillagerTrades.ItemsForEmeralds(purpleSapling, 5, 1, 8, 1));
//        factories.add(new VillagerTrades.ItemsForEmeralds(pinkSapling, 5, 1, 8, 1));
//      });
    }
    if (woodPreset == WoodPreset.MAPLE) {
      redLeaves = createParticleLeaves("red_", NSParticleTypes.RED_MAPLE_LEAVES_PARTICLE.get(), 100);
      orangeLeaves = createParticleLeaves("orange_", NSParticleTypes.ORANGE_MAPLE_LEAVES_PARTICLE.get(), 100);
      yellowLeaves = createParticleLeaves("yellow_", NSParticleTypes.YELLOW_MAPLE_LEAVES_PARTICLE.get(), 100);
      redSapling = createSapling("red_", new RedMapleSaplingGenerator());
      orangeSapling = createSapling("orange_", new OrangeMapleSaplingGenerator());
      yellowSapling = createSapling("yellow_", new YellowMapleSaplingGenerator());
      pottedRedSapling = createPottedSapling("red_", redSapling);
      pottedOrangeSapling = createPottedSapling("orange_", orangeSapling);
      pottedYellowSapling = createPottedSapling("yellow_", yellowSapling);
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, redLeaves.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(redLeaves, orangeLeaves.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(orangeLeaves, yellowLeaves.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(saplingBefore, redSapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(redSapling, orangeSapling.asItem()));
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(orangeSapling, yellowSapling.asItem()));
//      TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
//        factories.add(new VillagerTrades.ItemsForEmeralds(redSapling, 5, 1, 8, 1));
//        factories.add(new VillagerTrades.ItemsForEmeralds(orangeSapling, 5, 1, 8, 1));
//        factories.add(new VillagerTrades.ItemsForEmeralds(yellowSapling, 5, 1, 8, 1));
//      });
    }
    if (woodPreset == WoodPreset.ASPEN) {
      yellowLeaves = createLeaves("yellow_");
//      ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(leavesBefore, yellowLeaves.asItem()));
    }
    if (this.hasMosaic()) {
      mosaic = createMosaic();
      mosaicStairs = createMosaicStairs();
      mosaicSlab = createMosaicSlab();
    }
    planks = createPlanks();
    stairs = createStairs();
    slab = createSlab();
    fence = createFence();
    fenceGate = createFenceGate();
    pressurePlate = createPressurePlate();
    button = createButton();
    door = createDoor();
    trapDoor = createTrapDoor();
    sign = createSign();
    wallSign = createWallSign();
    hangingSign = createHangingSign();
    hangingWallSign = createWallHangingSign();
    signItem = createSignItem();
    hangingSignItem = createHangingSignItem();
    Boat.Type boatType = boatTypeSupplier.get();
    String boatTypeName = boatType.getName().replace("natures_spirit_", "");
    boatItem = createItem(boatTypeName + "_boat", () -> new BoatItem(false, boatType, new Item.Properties().stacksTo(1)));
    chestBoatItem = createItem(boatTypeName + "_chest_boat", () -> new BoatItem(true, boatType, new Item.Properties().stacksTo(1)));
    NSBoatTypes.addBoatTypeItems(boatType, boatItem.get(), chestBoatItem.get());
    NSBoatTypes.setBoatTypeBaseItem(boatType, planks.get());
//    addToBuildingTab(buttonBefore, logBefore, signBefore, boatBefore, this);
  }

  public WoodSet(
          ResourceLocation name,
          MapColor sideColor,
          MapColor topColor,
          ItemLike leavesBefore,
          ItemLike logBefore,
          ItemLike signBefore,
          ItemLike boatBefore,
          ItemLike buttonBefore,
          ItemLike saplingBefore,
          Supplier<Boat.Type> boatType,
          WoodPreset woodPreset,
          boolean hasMosaic,
          AbstractTreeGrower saplingGenerator
          ) {
    this.woodPreset = woodPreset;
    this.name = name;
    this.sideColor = sideColor;
    this.topColor = topColor;
    this.leavesBefore = leavesBefore;
    this.logBefore = logBefore;
    this.signBefore = signBefore;
    this.boatBefore = boatBefore;
    this.buttonBefore = buttonBefore;
    this.saplingBefore = saplingBefore;
    this.boatTypeSupplier = boatType;
      this.saplingGenerator = saplingGenerator;
      this.hasMosaic = hasMosaic;
    registerWood();
  }

  public ResourceLocation getNameID() {
    return name;
  }

  public String getName() {
    return name.getPath();
  }

  public Supplier<BlockSetType> getBlockSetType() {
    return this.blockSetType;
  }

  public WoodPreset getWoodPreset() {
    return woodPreset;
  }

  public MapColor getTopColor() {
    return topColor;
  }

  public Supplier<WoodType> getWoodType() {
    return woodType;
  }

  public RegistryObject<Block> getButton() {
    return button;
  }

  public RegistryObject<Block> getFence() {
    return fence;
  }

  public RegistryObject<Block> getPlanks() {
    return planks;
  }

  public RegistryObject<Block> getSlab() {
    return slab;
  }

  public RegistryObject<Block> getFenceGate() {
    return fenceGate;
  }

  public RegistryObject<Block> getStairs() {
    return stairs;
  }

  public RegistryObject<Block> getDoor() {
    return door;
  }

  public RegistryObject<Block> getHangingSign() {
    return hangingSign;
  }

  public RegistryObject<Block> getHangingWallSign() {
    return hangingWallSign;
  }

  public RegistryObject<Block> getPressurePlate() {
    return pressurePlate;
  }

  public RegistryObject<Block> getSign() {
    return sign;
  }

  public RegistryObject<Block> getTrapDoor() {
    return trapDoor;
  }

  public RegistryObject<Block> getWallSign() {
    return wallSign;
  }

  public RegistryObject<Item> getHangingSignItem() {
    return hangingSignItem;
  }

  public RegistryObject<Item> getSignItem() {
    return signItem;
  }

  public RegistryObject<Item> getBoatItem() {
    return boatItem;
  }

  public RegistryObject<Item> getChestBoatItem() {
    return chestBoatItem;
  }

  public RegistryObject<Block> getLog() {
    return log;
  }

  public RegistryObject<Block> getStrippedLog() {
    return strippedLog;
  }

  public RegistryObject<Block> getBundle() {
    return bundle;
  }

  public RegistryObject<Block> getStrippedBundle() {
    return strippedBundle;
  }

  public RegistryObject<Block> getWood() {
    return wood;
  }

  public RegistryObject<Block> getStrippedWood() {
    return strippedWood;
  }

  public RegistryObject<Block> getMosaic() {
    return mosaic;
  }

  public RegistryObject<Block> getMosaicStairs() {
    return mosaicStairs;
  }

  public RegistryObject<Block> getMosaicSlab() {
    return mosaicSlab;
  }

  public RegistryObject<Block> getLeaves() {
    return leaves;
  }

  public RegistryObject<Block> getFrostyLeaves() {
    return frostyLeaves;
  }

  public RegistryObject<Block> getSapling() {
    return sapling;
  }

  public RegistryObject<Block> getPottedSapling() {
    return pottedSapling;
  }

  public RegistryObject<Block> getVines() {
    return vines;
  }

  public RegistryObject<Block> getVinesPlant() {
    return vinesPlant;
  }

  public RegistryObject<Block> getRedLeaves() {
    return redLeaves;
  }

  public RegistryObject<Block> getOrangeLeaves() {
    return orangeLeaves;
  }

  public RegistryObject<Block> getYellowLeaves() {
    return yellowLeaves;
  }

  public RegistryObject<Block> getBlueLeaves() {
    return blueLeaves;
  }

  public RegistryObject<Block> getPurpleLeaves() {
    return purpleLeaves;
  }

  public RegistryObject<Block> getPinkLeaves() {
    return pinkLeaves;
  }

  public RegistryObject<Block> getWhiteLeaves() {
    return whiteLeaves;
  }

  public RegistryObject<Block> getPottedRedSapling() {
    return pottedRedSapling;
  }

  public RegistryObject<Block> getPottedOrangeSapling() {
    return pottedOrangeSapling;
  }

  public RegistryObject<Block> getPottedYellowSapling() {
    return pottedYellowSapling;
  }

  public RegistryObject<Block> getPottedBlueSapling() {
    return pottedBlueSapling;
  }

  public RegistryObject<Block> getPottedPurpleSapling() {
    return pottedPurpleSapling;
  }

  public RegistryObject<Block> getPottedPinkSapling() {
    return pottedPinkSapling;
  }

  public RegistryObject<Block> getPottedWhiteSapling() {
    return pottedWhiteSapling;
  }

  public RegistryObject<Block> getRedSapling() {
    return redSapling;
  }

  public RegistryObject<Block> getOrangeSapling() {
    return orangeSapling;
  }

  public RegistryObject<Block> getYellowSapling() {
    return yellowSapling;
  }

  public RegistryObject<Block> getBlueSapling() {
    return blueSapling;
  }

  public RegistryObject<Block> getPurpleSapling() {
    return purpleSapling;
  }

  public RegistryObject<Block> getPinkSapling() {
    return pinkSapling;
  }

  public RegistryObject<Block> getWhiteSapling() {
    return whiteSapling;
  }

  public RegistryObject<Block> getBlueVines() {
    return blueVines;
  }

  public RegistryObject<Block> getPurpleVines() {
    return purpleVines;
  }

  public RegistryObject<Block> getPinkVines() {
    return pinkVines;
  }

  public RegistryObject<Block> getWhiteVines() {
    return whiteVines;
  }

  public RegistryObject<Block> getBlueVinesPlant() {
    return blueVinesPlant;
  }

  public RegistryObject<Block> getPurpleVinesPlant() {
    return purpleVinesPlant;
  }

  public RegistryObject<Block> getPinkVinesPlant() {
    return pinkVinesPlant;
  }

  public RegistryObject<Block> getWhiteVinesPlant() {
    return whiteVinesPlant;
  }

  public RegistryObject<Block> getPartBlueLeaves() {
    return partBlueLeaves;
  }

  public RegistryObject<Block> getPartPurpleLeaves() {
    return partPurpleLeaves;
  }

  public RegistryObject<Block> getPartPinkLeaves() {
    return partPinkLeaves;
  }

  public RegistryObject<Block> getPartWhiteLeaves() {
    return partWhiteLeaves;
  }

  private String getWoodName() {
    String name;
    if (woodPreset == WoodPreset.NETHER) {
      name = getName() + "_hyphae";
    } else {
      name = getName() + "_wood";
    }
    return name;
  }

  private String getLogName() {
    String name;
    if (woodPreset == WoodPreset.BAMBOO) {
      name = getName() + "_block";
    } else if (woodPreset == WoodPreset.NETHER) {
      name = getName() + "_stem";
    } else {
      name = getName() + "_log";
    }
    return name;
  }

  private Block getBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_PLANKS;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_PLANKS;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_PLANKS;
    } else {
      base = Blocks.OAK_PLANKS;
    }
    return base;
  }

  private Block getSignBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_SIGN;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_SIGN;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_SIGN;
    } else {
      base = Blocks.OAK_SIGN;
    }
    return base;
  }

  private Block getHangingSignBase() {
    Block base;
    if (woodPreset == WoodPreset.BAMBOO) {
      base = Blocks.BAMBOO_HANGING_SIGN;
    } else if (woodPreset == WoodPreset.FANCY) {
      base = Blocks.CHERRY_HANGING_SIGN;
    } else if (woodPreset == WoodPreset.NETHER) {
      base = Blocks.CRIMSON_HANGING_SIGN;
    } else {
      base = Blocks.OAK_HANGING_SIGN;
    }
    return base;
  }


  public Boat.Type getBoatType() {
    return boatTypeSupplier.get();
  }

  public List<RegistryObject<Block>> getRegisteredBlocksList() {
    return ImmutableList.copyOf(registeredBlocksList);
  }

  public List<RegistryObject<Item>> getRegisteredItemsList() {
    return ImmutableList.copyOf(registeredItemsList);
  }

  public List<RegistryObject<Block>> getLeavesList() {
    return ImmutableList.copyOf(leavesList);
  }

  public List<RegistryObject<Block>> getsaplingList() {
    return ImmutableList.copyOf(saplingList);
  }

  private RegistryObject<Block> createBlockWithItem(String blockID, Supplier<Block> block) {
    RegistryObject<Block> listBlock = registerBlock(blockID, block);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  private RegistryObject<Block> createBlockWithoutItem(String blockID, Supplier<Block> block) {
    RegistryObject<Block> listBlock = registerBlockWithoutTab(blockID, block);
    registeredBlocksList.add(listBlock);
    return listBlock;
  }

  public RegistryObject<Item> createItem(String blockID, Supplier<Item> item) {
    RegistryObject<Item> listItem = registerItem(blockID, item);
    registeredItemsList.add(listItem);
    return listItem;
  }

  private RegistryObject<Block> createLog() {
    return createBlockWithItem(getLogName(), () -> log(topColor, sideColor, strippedLog.get()));
  }

  private RegistryObject<Block> createStrippedLog() {
    return createBlockWithItem("stripped_" + getLogName(), () -> strippedLog(topColor, sideColor));
  }

  private RegistryObject<Block> createBundle() {
    return createBlockWithItem(getName() + "_bundle", () -> log(topColor, sideColor, strippedBundle.get()));
  }

  private RegistryObject<Block> createStrippedBundle() {
    return createBlockWithItem("stripped_" + getName() + "_bundle", () -> strippedLog(topColor, sideColor));
  }
  
  private static RotatedPillarBlock log(MapColor p_285370_, MapColor p_285126_, Block strippedLog) {
    return new StrippableLogBlock(Properties.of().mapColor((p_152624_) -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(), strippedLog);
  }
  private static RotatedPillarBlock strippedLog(MapColor p_285370_, MapColor p_285126_) {
    return new RotatedPillarBlock(Properties.of().mapColor((p_152624_) -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    };
  }
  
  private RegistryObject<Block> createJoshuaLog() {
    return createBlockWithItem(getLogName(), () -> new BranchingTrunkBlock(
        Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
  }

  private RegistryObject<Block> createStrippedJoshuaLog() {
    return createBlockWithItem("stripped_" + getLogName(), () -> new BranchingTrunkBlock(
        Properties.of().ignitedByLava().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD)));
  }

  private RegistryObject<Block> createWood() {
    return createBlockWithItem(getWoodName(), () -> new RotatedPillarBlock(
        Properties.of().mapColor(sideColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
  }

  private RegistryObject<Block> createStrippedWood() {
    return createBlockWithItem("stripped_" + getWoodName(), () -> new RotatedPillarBlock(Properties.of().mapColor(topColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
  }

  private RegistryObject<Block> createLeaves() {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_leaves", () -> new LeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never)){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createLeaves(String prefix) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_leaves", () -> new LeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never)){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createFrostableLeaves() {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_leaves",
            () -> new ProjectileLeavesBlock(Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), frostyLeaves.get()){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createFrostableLeaves(String prefix) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_leaves",
            () -> new ProjectileLeavesBlock(Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), frostyLeaves.get()){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createParticleLeaves(ParticleOptions particle, int chance) {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_leaves", () -> new ParticleLeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), particle, chance){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createParticleLeaves(String prefix, ParticleOptions particle, int chance) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_leaves", () -> new ParticleLeavesBlock(
        Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), particle, chance){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
      }
    });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createVinesLeavesBlock(RegistryObject<Block> vinesPlantBlock, RegistryObject<Block> vinesTipBlock) {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_leaves",
            () -> new VinesLeavesBlock(Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava().pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(NSRegistryHelper::never), vinesPlantBlock.get(), vinesTipBlock.get()){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createVinesLeavesBlock(String prefix, RegistryObject<Block> vinesPlantBlock, RegistryObject<Block> vinesTipBlock) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_leaves",
            () -> new VinesLeavesBlock(Properties.of().strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()
            .isValidSpawn(NSRegistryHelper::ocelotOrParrot).isSuffocating(NSRegistryHelper::never).isViewBlocking(NSRegistryHelper::never).ignitedByLava()
            .pushReaction(PushReaction.DESTROY).isRedstoneConductor(NSRegistryHelper::never), vinesPlantBlock.get(), vinesTipBlock.get()){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
              }
            });
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName(), block);
    leavesList.add(block);
    return block;
  }

  private RegistryObject<Block> createVines(RegistryObject<Block> vinesPlantBlock) {
    RegistryObject<Block> vinesBlock = createBlockWithItem(getName() + "_vines",
            () -> new DownwardVineBlock(Properties
            .of()
            .pushReaction(PushReaction.DESTROY)
            .randomTicks()
            .noCollission()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.WEEPING_VINES), vinesPlantBlock.get()));
    RenderLayerHashMap.put(getName() + "_vines", vinesBlock);
    ComposterBlock.COMPOSTABLES.put(vinesBlock.get().asItem(), .3F);
    return vinesBlock;
  }

  private RegistryObject<Block> createVines(String prefix, RegistryObject<Block> vinesPlantBlock) {
    RegistryObject<Block> vinesBlock = createBlockWithItem(prefix + getName() + "_vines",
            () -> new DownwardVineBlock(Properties
            .of()
            .pushReaction(PushReaction.DESTROY)
            .randomTicks()
            .noCollission()
            .noOcclusion()
            .instabreak()
            .sound(SoundType.WEEPING_VINES), vinesPlantBlock.get()));
    RenderLayerHashMap.put(prefix + getName() + "_vines", vinesBlock);
    ComposterBlock.COMPOSTABLES.put(vinesBlock.get().asItem(), .3F);
    return vinesBlock;
  }

  private RegistryObject<Block> createVinesPlant(RegistryObject<Block> vines) {
    RegistryObject<Block> vinesPlant = registerBlockWithoutTab(getName() + "_vines_plant", () -> new DownwardsVinePlantBlock(Properties
        .of()
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
        .instabreak()
        .sound(SoundType.WEEPING_VINES)
        .dropsLike(vines.get()), vines.get()));
    RenderLayerHashMap.put(getName() + "_vines_plant", vinesPlant);
    return vinesPlant;
  }

  private RegistryObject<Block> createVinesPlant(String prefix, RegistryObject<Block> vines) {
    RegistryObject<Block> vinesPlant = registerBlockWithoutTab(prefix + getName() + "_vines_plant", () -> new DownwardsVinePlantBlock(Properties
        .of()
        .pushReaction(PushReaction.DESTROY)
        .noCollission()
        .noOcclusion()
        .instabreak()
        .sound(SoundType.WEEPING_VINES)
        .dropsLike(vines.get()), vines.get()));
    RenderLayerHashMap.put(prefix + getName() + "_vines_plant", vinesPlant);
    return vinesPlant;
  }

  private RegistryObject<Block> createPlanks() {
    return createBlockWithItem(getName() + "_planks", () -> new Block(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private RegistryObject<Block> createStairs() {
    return createBlockWithItem(getName() + "_stairs",
            () -> new StairBlock(getBase().defaultBlockState(), Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 20;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 5;
              }
            });
  }

  private RegistryObject<Block> createSlab() {
    return createBlockWithItem(getName() + "_slab", () -> new SlabBlock(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private RegistryObject<Block> createMosaic() {
    RegistryObject<Block> block = createBlockWithoutItem(getName() + "_mosaic", () -> new Block(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
    NSRegistryHelper.registerItem(getName(), () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 300;
      }
    });
    return block;
  }

  private RegistryObject<Block> createMosaicStairs() {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_mosaic_stairs",
            () -> new StairBlock(getBase().defaultBlockState(), Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
              @Override
              public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
              }

              @Override
              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 20;
              }

              @Override
              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 5;
              }
            });
    NSRegistryHelper.registerItem(getName(), () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 300;
      }
    });
    return block;
  }

  private RegistryObject<Block> createMosaicSlab() {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_mosaic_slab", () -> new SlabBlock(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
    NSRegistryHelper.registerItem(getName(), () -> new BlockItem(block.get(), new Item.Properties()){
      @Override
      public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 150;
      }
    });
    return block;
  }

  private RegistryObject<Block> createFence() {
    return createBlockWithItem(getName() + "_fence", () -> new FenceBlock(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).mapColor(getTopColor())){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private RegistryObject<Block> createFenceGate() {
    return createBlockWithItem(getName() + "_fence_gate", () -> new FenceGateBlock(
        Properties.of().mapColor(getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), getWoodType().get()){
      @Override
      public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
      }

      @Override
      public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
      }

      @Override
      public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
      }
    });
  }

  private RegistryObject<Block> createPressurePlate() {
    return createBlockWithItem(getName() + "_pressure_plate", () -> new PressurePlateBlock( PressurePlateBlock.Sensitivity.EVERYTHING,
        Properties.of().mapColor(this.getBase().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava()
            .pushReaction(PushReaction.DESTROY), getBlockSetType().get()));
  }

  private RegistryObject<Block> createButton() {
    return createBlockWithItem(getName() + "_button",
            () -> new ButtonBlock(Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), getBlockSetType().get(), 30, true));
  }

  private RegistryObject<Block> createDoor() {
    RenderLayerHashMap.put(getName() + "_door", door);
    return createBlockWithItem(getName() + "_door",
            () -> new DoorBlock(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor()), getBlockSetType().get()));
  }

  private RegistryObject<Block> createTrapDoor() {
    RenderLayerHashMap.put(getName() + "_trapdoor", trapDoor);
    return createBlockWithItem(getName() + "_trapdoor",
            () -> new TrapDoorBlock(Properties.copy(getBase()).sound(getBlockSetType().get().soundType()).noOcclusion().mapColor(getTopColor()), getBlockSetType().get()));
  }

  private RegistryObject<Block> createSign() {
    return registerBlockWithoutTab(getName() + "_sign", () -> new StandingSignBlock(Properties.copy(getSignBase()).mapColor(this.getTopColor()), getWoodType().get()));
  }

  private RegistryObject<Block> createWallSign() {
    return registerBlockWithoutTab(getName() + "_wall_sign", () -> new WallSignBlock(Properties.copy(getSignBase()).mapColor(this.getTopColor()).dropsLike(sign.get()), getWoodType().get()));
  }

  private RegistryObject<Block> createHangingSign() {
    return registerBlockWithoutTab(getName() + "_hanging_sign", () -> new CeilingHangingSignBlock(Properties.copy(getHangingSignBase()).mapColor(this.getTopColor()), getWoodType().get()));
  }

  private RegistryObject<Block> createWallHangingSign() {
    return registerBlockWithoutTab(getName() + "_wall_hanging_sign",
            () -> new WallHangingSignBlock(Properties.copy(getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(hangingSign.get()), getWoodType().get()));
  }

  public RegistryObject<Block> createSapling(AbstractTreeGrower saplingGenerator) {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_sapling", () -> new SaplingBlock(saplingGenerator, Properties.copy(Blocks.SPRUCE_SAPLING)));
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName() + "_sapling", block);
    saplingList.add(block);
    return block;
  }

  public RegistryObject<Block> createSandySapling(AbstractTreeGrower saplingGenerator) {
    RegistryObject<Block> block = createBlockWithItem(getName() + "_sapling", () -> new SandySaplingBlock(saplingGenerator, Properties.copy(Blocks.SPRUCE_SAPLING)));
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(getName() + "_sapling", block);
    saplingList.add(block);
    return block;
  }

  public RegistryObject<Block> createPottedSapling(RegistryObject<Block> sapling) {
    return registerTransparentBlockWithoutTab("potted_" + getName() + "_sapling",
            () -> new FlowerPotBlock(sapling.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  }

  public RegistryObject<Block> createSapling(String prefix, AbstractTreeGrower saplingGenerator) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_sapling", () -> new SaplingBlock(saplingGenerator, Properties.copy(Blocks.SPRUCE_SAPLING)));
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
    saplingList.add(block);
    return block;
  }

  public RegistryObject<Block> createSandySapling(String prefix, AbstractTreeGrower saplingGenerator) {
    RegistryObject<Block> block = createBlockWithItem(prefix + getName() + "_sapling", () -> new SandySaplingBlock(saplingGenerator, Properties.copy(Blocks.SPRUCE_SAPLING)));
    ComposterBlock.COMPOSTABLES.put(block.get().asItem(), .3F);
    RenderLayerHashMap.put(prefix + getName() + "_sapling", block);
    saplingList.add(block);
    return block;
  }

  public RegistryObject<Block> createPottedSapling(String prefix, RegistryObject<Block> sapling) {
    return registerTransparentBlockWithoutTab("potted_" + prefix + getName() + "_sapling",
            () -> new FlowerPotBlock(sapling.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
  }

  private RegistryObject<Item> createSignItem() {
    return createItem(getName() + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), sign.get(), wallSign.get()));
  }

  private RegistryObject<Item> createHangingSignItem() {
    return createItem(getName() + "_hanging_sign", () -> new HangingSignItem(hangingSign.get(), hangingWallSign.get(), new Item.Properties().stacksTo(16)));
  }

  private Supplier<BlockSetType> createBlockSetType() {
    if (this.woodPreset == WoodPreset.BAMBOO) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(getNameID().getPath(), true, SoundType.BAMBOO_WOOD, SoundEvents.BAMBOO_WOOD_DOOR_CLOSE, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON)));
    } else if (woodPreset == WoodPreset.FANCY) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(getNameID().getPath(), true, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON)));
    } else if (this.woodPreset == WoodPreset.NETHER) {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(getNameID().getPath(), true, SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON)));
    } else {
      return Suppliers.memoize(() -> BlockSetType.register(new BlockSetType(getNameID().getPath())));
    }
  }

  public boolean isSandy() {
    return woodPreset == WoodPreset.JOSHUA || woodPreset == WoodPreset.SANDY;
  }

  public boolean hasDefaultLeaves() {
    return woodPreset == WoodPreset.DEFAULT || woodPreset == WoodPreset.WISTERIA || woodPreset == WoodPreset.FANCY || woodPreset == WoodPreset.JOSHUA
        || woodPreset == WoodPreset.NO_SAPLING || woodPreset == WoodPreset.SANDY || woodPreset == WoodPreset.ASPEN;
  }

  public boolean hasDefaultSapling() {
    return woodPreset != WoodPreset.NO_SAPLING && woodPreset != WoodPreset.WISTERIA;
  }

  public boolean hasBark() {
    return woodPreset != WoodPreset.JOSHUA && woodPreset != WoodPreset.BAMBOO;
  }

  public boolean hasMosaic() {
    return this.hasMosaic;
  }

  public enum WoodPreset {
    DEFAULT, MAPLE, ASPEN, FROSTABLE, JOSHUA, SANDY, NO_SAPLING, WISTERIA, WILLOW, FANCY, NETHER, BAMBOO
  }

//  public static void addToBuildingTab(ItemLike proceedingItem, ItemLike logPlacement, ItemLike signPlacement, ItemLike boatPlacement, WoodSet woodset) {
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
//      entries.addAfter(proceedingItem, woodset.getLog());
//      if (woodset.getWoodPreset() == WoodPreset.JOSHUA) {
//        entries.addAfter(woodset.getLog(), woodset.getBundle(), woodset.getStrippedLog(), woodset.getStrippedBundle(), woodset.getPlanks());
//      } else if (!woodset.hasBark()) {
//        entries.addAfter(woodset.getLog(), woodset.getStrippedLog(), woodset.getPlanks());
//      } else {
//        entries.addAfter(woodset.getLog(), woodset.getWood());
//        entries.addAfter(woodset.getWood(), woodset.getStrippedLog(), woodset.getStrippedWood(), woodset.getPlanks());
//      }
//      entries.addAfter(woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
//          woodset.getFence(), woodset.getFenceGate(),
//          woodset.getDoor(), woodset.getTrapDoor(),
//          woodset.getPressurePlate(), woodset.getButton());
//
//      if (woodset.hasMosaic()) {
//        entries.addAfter(woodset.getPlanks(), woodset.getMosaic());
//        entries.addAfter(woodset.getStairs(), woodset.getMosaicStairs());
//        entries.addAfter(woodset.getSlab(), woodset.getMosaicSlab());
//      }
//    });
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(logPlacement, woodset.getLog().asItem()));
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> entries.addAfter(signPlacement, woodset.getSignItem(), woodset.getHangingSignItem()));
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> entries.addAfter(boatPlacement, woodset.getBoatItem(), woodset.getChestBoatItem()));
//  }

}
