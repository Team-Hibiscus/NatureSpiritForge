package net.hibiscus.naturespirit.registration;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.registration.sets.WoodSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;

public class NSRegistryHelper {
  public static HashMap<String, RegistryObject<Block>> RenderLayerHashMap = new HashMap<>();
  public static HashMap<String, RegistryObject<Block>> LeavesHashMap = new HashMap<>();


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

  public static RegistryObject<Block> registerBlockWithoutItem(String name, Supplier<Block> block) {
    return NSBlocks.BLOCKS.register(name, block);
  }

  public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
    RegistryObject<Block> block1 = registerBlockWithoutItem(name, block);
    registerItem(name, () -> new BlockItem(block1.get(), new Item.Properties()));
    return block1;
  }
  public static RegistryObject<Block> registerTransparentBlockWithoutItem(String name, Supplier<Block> block) {
    RegistryObject<Block> block1 = registerBlockWithoutItem(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }
  public static RegistryObject<Block> registerTransparentBlock(String name, Supplier<Block> block) {
    RegistryObject<Block> block1 = registerBlock(name, block);
    RenderLayerHashMap.put(name, block1);
    return block1;
  }
  public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
      return NSBlocks.ITEMS.register(name, item);
  }
  public static RegistryObject<Block> registerTallPlantBlock(String name, Supplier<Block> block) {
    RegistryObject<Block> plant = registerBlockWithoutItem(name, block);
    registerItem(name, () -> new DoubleHighBlockItem(plant.get(), new Item.Properties()));
    RenderLayerHashMap.put(name, plant);
    return plant;
  }
}
