package net.hibiscus.naturespirit.registration;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.config.NSConfig;
import net.hibiscus.naturespirit.registration.sets.FlowerSet;
import net.hibiscus.naturespirit.registration.sets.StoneSet;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class NSRegistryHelper {

  public static HashMap<String, FlowerSet> FlowerHashMap = new HashMap<>();
  public static HashMap<String, StoneSet> StoneHashMap = new HashMap<>();

  public static HashMap<String, RegistryObject<Block>> RenderLayerHashMap = new HashMap<>();


  public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
    return false;
  }
  public static Boolean never(BlockState state, BlockGetter world, BlockPos pos) {
    return false;
  }
  public static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
    return true;
  }

  public static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
    return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
  }

  public static RegistryObject<Block> registerBlockWithoutTab(String name, Supplier<Block> block) {
    return NSMiscBlocks.MISC_BLOCKS.register(name, block);
  }

  public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
    registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
    return registerBlockWithoutTab(name, block);
  }

  public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block, ItemLike blockBefore, ResourceKey<CreativeModeTab> secondaryTab) {
    RegistryObject<Block> block1 = registerBlock(name, block);
//    ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, block1.asItem()));
    return block1;
  }

  public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block, ItemLike blockBefore, ResourceKey<CreativeModeTab> secondaryTab, ItemLike blockBefore2, ResourceKey<CreativeModeTab> thirdTab) {
    RegistryObject<Block> block1 = registerBlock(name, block, blockBefore, secondaryTab);
//    ItemGroupEvents.modifyEntriesEvent(thirdTab).register(entries -> entries.addAfter(blockBefore2, block1.asItem()));
    return block1;
  }


  public static RegistryObject<Block> registerTransparentBlockWithoutTab(String name, Supplier<Block> block) {
    RegistryObject<Block> block1 = registerBlockWithoutTab(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }

  public static RegistryObject<Block> registerTransparentBlock(String name, Supplier<Block> block) {
    RegistryObject<Block> block1 = registerBlock(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }

  public static RegistryObject<Block> registerTransparentBlock(String name, Supplier<Block> block, ItemLike itemBefore, ResourceKey<CreativeModeTab> secondaryTab) {
    RegistryObject<Block> block1 = registerBlock(name, block, itemBefore, secondaryTab);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }

  public static RegistryObject<Block> registerTransparentBlock(String name, Supplier<Block> block, ItemLike itemBefore, ResourceKey<CreativeModeTab> secondaryTab, ItemLike blockBefore2, ResourceKey<CreativeModeTab> thirdTab) {
    RegistryObject<Block> block1 = registerBlock(name, block, itemBefore, secondaryTab, blockBefore2, thirdTab);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }


  public static RegistryObject<Block> registerPlantBlock(String name, Supplier<Block> block, ItemLike itemBefore, float compost) {
    RegistryObject<Block> plant = registerTransparentBlock(name, block, itemBefore, CreativeModeTabs.NATURAL_BLOCKS);
    ComposterBlock.COMPOSTABLES.put(block.get(), compost);
    return plant;
  }

  public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
    RegistryObject<Item> item1 = NSMiscBlocks.MISC_ITEMS.register(name, item);
//    if (NSConfig.creativeTab) {
//      ItemGroupEvents.modifyEntriesEvent(NSItemGroups.NS_ITEM_GROUP).register(entries -> entries.accept(item1));
//    }
    return item1;
  }

  public static RegistryObject<Item> registerItem(String name, Supplier<Item> item, ItemLike itemBefore, ResourceKey<CreativeModeTab> secondaryTab) {
//    ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(itemBefore, item));
    return registerItem(name, item);
  }


  public static RegistryObject<Item> registerPlantItem(String name, Supplier<Item> item, ItemLike itemBefore, ResourceKey<CreativeModeTab> secondaryTab, float compost) {
    ComposterBlock.COMPOSTABLES.put(item.get(), compost);
    return registerItem(name, item, itemBefore, secondaryTab);
  }

  public static <T extends ParticleType> RegistryObject<T> registerParticleType(String name, Supplier<T> particleType) {
    return NSParticleTypes.PARTICLE_TYPES.register(name, particleType);
  }

  public static RegistryObject<Block> registerTallPlantBlock(String name, Supplier<Block> block, ItemLike itemBefore, float compost) {
    RegistryObject<Block> plant = registerBlockWithoutTab(name, block);
    registerItem(name, () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
//    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> entries.addAfter(itemBefore, plant.asItem()));
    RenderLayerHashMap.put(name, plant);
    ComposterBlock.COMPOSTABLES.put(block.get(), compost);
    return plant;
  }
}
