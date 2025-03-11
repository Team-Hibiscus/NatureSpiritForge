package net.hibiscus.naturespirit.registration.sets;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.blocks.PaperLanternBlock;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.registerTransparentBlock;

public class StoneSet {


  private RegistryObject<Block> cobbled;
  private RegistryObject<Block> cobbledStairs;
  private RegistryObject<Block> cobbledSlab;
  private RegistryObject<Block> cobbledWall;
  private RegistryObject<Block> mossyCobbled;
  private RegistryObject<Block> mossyCobbledStairs;
  private RegistryObject<Block> mossyCobbledSlab;
  private RegistryObject<Block> mossyCobbledWall;
  private RegistryObject<Block> base;
  private RegistryObject<Block> baseStairs;
  private RegistryObject<Block> baseSlab;
  private RegistryObject<Block> polished;
  private RegistryObject<Block> polishedStairs;
  private RegistryObject<Block> polishedSlab;
  private RegistryObject<Block> polishedWall;
  private RegistryObject<Block> tiles;
  private RegistryObject<Block> tilesStairs;
  private RegistryObject<Block> tilesSlab;
  private RegistryObject<Block> tilesWall;
  private RegistryObject<Block> bricks;
  private RegistryObject<Block> bricksStairs;
  private RegistryObject<Block> bricksSlab;
  private RegistryObject<Block> bricksWall;
  private RegistryObject<Block> chiseled;
  private RegistryObject<Block> crackedBricks;
  private RegistryObject<Block> crackedTiles;
  private RegistryObject<Block> mossyBricks;
  private RegistryObject<Block> mossyBricksStairs;
  private RegistryObject<Block> mossyBricksSlab;
  private RegistryObject<Block> mossyBricksWall;

  private final ResourceLocation name;
  private final MapColor mapColor;
  private final Item itemBefore;
  private final Item item2Before;
  private final boolean hasTiles;
  private final boolean hasCobbled;
  private final boolean hasCracked;
  private final boolean hasMossy;
  private boolean isRotatable;
  private final float hardness;

  private void registerStone() {

    base = isRotatable ? createRotatable(getName(), Blocks.ANDESITE) : createBasic(getName(), Blocks.ANDESITE);
    baseStairs = createStairs(getName(), base.get());
    baseSlab = createSlab(getName(), base.get());
    chiseled = createBasic("chiseled_" + getName(), Blocks.CHISELED_STONE_BRICKS);

    if (hasCobbled()) {
      cobbled = createBasic("cobbled_" + getName(), Blocks.COBBLESTONE);
      cobbledStairs = createStairs("cobbled_" + getName(), cobbled.get());
      cobbledSlab = createSlab("cobbled_" + getName(), cobbled.get());
      cobbledWall = createWall("cobbled_" + getName(), cobbled.get());
    }
    if (hasMossy() && hasCobbled()) {
      mossyCobbled = createBasic("mossy_cobbled_" + getName(), Blocks.MOSSY_COBBLESTONE);
      mossyCobbledStairs = createStairs("mossy_cobbled_" + getName(), mossyCobbled.get());
      mossyCobbledSlab = createSlab("mossy_cobbled_" + getName(), mossyCobbled.get());
      mossyCobbledWall = createWall("mossy_cobbled_" + getName(), mossyCobbled.get());
    }

    polished = createBasic("polished_" + getName(), Blocks.POLISHED_ANDESITE);
    polishedStairs = createStairs("polished_" + getName(), polished.get());
    polishedSlab = createSlab("polished_" + getName(), polished.get());
    polishedWall = createWall("polished_" + getName(), polished.get());

    bricks = createBasic(getName() + "_bricks", Blocks.STONE_BRICKS);
    bricksStairs = createStairs(getName() + "_brick", bricks.get());
    bricksSlab = createSlab(getName() + "_brick", bricks.get());
    bricksWall = createWall(getName() + "_brick", bricks.get());

    if (hasMossy()) {
      mossyBricks = createBasic("mossy_" + getName() + "_bricks", Blocks.MOSSY_STONE_BRICKS);
      mossyBricksStairs = createStairs("mossy_" + getName() + "_brick", bricks.get());
      mossyBricksSlab = createSlab("mossy_" + getName() + "_brick", bricks.get());
      mossyBricksWall = createWall("mossy_" + getName() + "_brick", bricks.get());
    }
    if (hasCracked()) {
      crackedBricks = createBasic("cracked_" + getName() + "_bricks", Blocks.CRACKED_STONE_BRICKS);
    }
    if (hasTiles()) {
      tiles = createBasic(getName() + "_tiles", Blocks.COBBLESTONE);
      tilesStairs = createStairs(getName() + "_tile", polished.get());
      tilesSlab = createSlab(getName() + "_tile", polished.get());
      tilesWall = createWall(getName() + "_tile", polished.get());
      if (hasCracked()) {
        crackedTiles = createBasic("cracked_" + getName() + "_tiles", Blocks.CRACKED_STONE_BRICKS);
      }
    }
//    addToBuildingTab(itemBefore, item2Before, this);
  }

  public StoneSet(ResourceLocation name, MapColor mapColor, Item itemBefore, Item item2Before, float hardness, boolean hasCobbled, boolean hasCracked, boolean hasMossy,
      boolean hasTiles) {
    this.name = name;
    this.mapColor = mapColor;
    this.itemBefore = itemBefore;
    this.item2Before = item2Before;
    this.hardness = hardness;
    this.hasTiles = hasTiles;
    this.hasCobbled = hasCobbled;
    this.hasCracked = hasCracked;
    this.hasMossy = hasMossy;
    registerStone();
  }

  public StoneSet(ResourceLocation name, MapColor mapColor, Item itemBefore, Item item2Before, float hardness, boolean hasCobbled, boolean hasCracked, boolean hasMossy, boolean hasTiles,
      boolean isRotatable) {
    this.name = name;
    this.mapColor = mapColor;
    this.itemBefore = itemBefore;
    this.item2Before = item2Before;
    this.hardness = hardness;
    this.hasTiles = hasTiles;
    this.hasCobbled = hasCobbled;
    this.hasCracked = hasCracked;
    this.hasMossy = hasMossy;
    this.isRotatable = isRotatable;
    registerStone();
  }

  private RegistryObject<Block> createBlockWithItem(String blockID, Supplier<Block> block) {
      return NSRegistryHelper.registerBlock(blockID, block);
  }

  public String getName() {
    return name.getPath();
  }

  private RegistryObject<Block> createBasic(String name, Block template) {
    return createBlockWithItem(name, () -> new Block(BlockBehaviour.Properties.copy(template).destroyTime(hardness).mapColor(getMapColor())));
  }

  private RegistryObject<Block> createRotatable(String name, Block template) {
    return createBlockWithItem(name, () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(template).destroyTime(hardness).mapColor(getMapColor())));
  }

  private RegistryObject<Block> createStairs(String name, Block template) {
    return createBlockWithItem(name + "_stairs", () -> new StairBlock(template.defaultBlockState(), BlockBehaviour.Properties.copy(template)));
  }

  private RegistryObject<Block> createSlab(String name, Block template) {
    return createBlockWithItem(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(template)));
  }

  private RegistryObject<Block> createWall(String name, Block template) {
    return createBlockWithItem(name + "_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(template).forceSolidOn()));
  }

  public boolean hasTiles() {
    return hasTiles;
  }

  public boolean hasCracked() {
    return hasCracked;
  }

  public boolean hasCobbled() {
    return hasCobbled;
  }

  public boolean hasMossy() {
    return hasMossy;
  }

  public boolean isRotatable() {
    return isRotatable;
  }

//  public static void addToBuildingTab(Item proceedingItem, Item naturalStonePlacement, StoneSet stoneSet) {
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
//      entries.addAfter(proceedingItem, stoneSet.getBase(), stoneSet.getBaseStairs(), stoneSet.getBaseSlab(),
//          stoneSet.getChiseled(), stoneSet.getPolished(), stoneSet.getPolishedStairs(), stoneSet.getPolishedSlab(), stoneSet.getPolishedWall(), stoneSet.getBricks(),
//          stoneSet.getBricksStairs(), stoneSet.getBricksSlab(), stoneSet.getBricksWall());
//      if (stoneSet.hasCobbled()) {
//        entries.addAfter(stoneSet.getBaseSlab(), stoneSet.getCobbled(), stoneSet.getCobbledStairs(), stoneSet.getCobbledSlab(), stoneSet.getCobbledWall());
//        if (stoneSet.hasMossy()) {
//          entries.addAfter(stoneSet.getCobbledWall(), stoneSet.getMossyCobbled(), stoneSet.getMossyCobbledStairs(), stoneSet.getMossyCobbledSlab(), stoneSet.getMossyCobbledWall());
//        }
//      }
//      if (stoneSet.hasCracked()) {
//        entries.addAfter(stoneSet.getBricks(), stoneSet.getCrackedBricks());
//      }
//      if (stoneSet.hasTiles()) {
//        entries.addAfter(stoneSet.getBricksWall(), stoneSet.getTiles(), stoneSet.getTilesStairs(), stoneSet.getTilesSlab(), stoneSet.getTilesWall());
//        if (stoneSet.hasCracked()) {
//          entries.addAfter(stoneSet.getTiles(), stoneSet.getCrackedTiles());
//        }
//      }
//      if (stoneSet.hasMossy()) {
//        entries.addAfter(stoneSet.getBricksWall(), stoneSet.getMossyBricks(), stoneSet.getMossyBricksStairs(), stoneSet.getMossyBricksSlab(), stoneSet.getMossyBricksWall());
//      }
//    });
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(naturalStonePlacement, stoneSet.getBase()));
//  }

  public RegistryObject<Block> getCobbled() {
    return cobbled;
  }

  public RegistryObject<Block> getCobbledStairs() {
    return cobbledStairs;
  }

  public RegistryObject<Block> getCobbledSlab() {
    return cobbledSlab;
  }

  public RegistryObject<Block> getCobbledWall() {
    return cobbledWall;
  }

  public RegistryObject<Block> getBase() {
    return base;
  }

  public RegistryObject<Block> getBaseStairs() {
    return baseStairs;
  }

  public RegistryObject<Block> getBaseSlab() {
    return baseSlab;
  }

  public RegistryObject<Block> getPolished() {
    return polished;
  }

  public RegistryObject<Block> getPolishedStairs() {
    return polishedStairs;
  }

  public RegistryObject<Block> getPolishedSlab() {
    return polishedSlab;
  }

  public RegistryObject<Block> getPolishedWall() {
    return polishedWall;
  }

  public RegistryObject<Block> getTiles() {
    return tiles;
  }

  public RegistryObject<Block> getTilesStairs() {
    return tilesStairs;
  }

  public RegistryObject<Block> getTilesSlab() {
    return tilesSlab;
  }

  public RegistryObject<Block> getTilesWall() {
    return tilesWall;
  }

  public RegistryObject<Block> getBricks() {
    return bricks;
  }

  public RegistryObject<Block> getBricksStairs() {
    return bricksStairs;
  }

  public RegistryObject<Block> getBricksSlab() {
    return bricksSlab;
  }

  public RegistryObject<Block> getBricksWall() {
    return bricksWall;
  }

  public RegistryObject<Block> getChiseled() {
    return chiseled;
  }

  public RegistryObject<Block> getCrackedBricks() {
    return crackedBricks;
  }

  public RegistryObject<Block> getCrackedTiles() {
    return crackedTiles;
  }

  public RegistryObject<Block> getMossyBricks() {
    return mossyBricks;
  }

  public RegistryObject<Block> getMossyBricksStairs() {
    return mossyBricksStairs;
  }

  public RegistryObject<Block> getMossyBricksSlab() {
    return mossyBricksSlab;
  }

  public RegistryObject<Block> getMossyBricksWall() {
    return mossyBricksWall;
  }

  public RegistryObject<Block> getMossyCobbled() {
    return mossyCobbled;
  }

  public RegistryObject<Block> getMossyCobbledStairs() {
    return mossyCobbledStairs;
  }

  public RegistryObject<Block> getMossyCobbledSlab() {
    return mossyCobbledSlab;
  }

  public RegistryObject<Block> getMossyCobbledWall() {
    return mossyCobbledWall;
  }

  public MapColor getMapColor() {
    return mapColor;
  }
}
