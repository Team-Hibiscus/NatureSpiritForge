package net.hibiscus.naturespirit.registration.sets;

import net.hibiscus.naturespirit.blocks.LargeFlowerBlock;
import net.hibiscus.naturespirit.blocks.MidFlowerBlock;
import net.hibiscus.naturespirit.registration.NSRegistryHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static net.hibiscus.naturespirit.registration.NSRegistryHelper.*;

public class FlowerSet {

  private final String name;
  private final Item dyeColor;
  private final MobEffect statusEffect;
  private final Item itemBefore;
  private final FlowerPreset preset;
  private RegistryObject<Block> flowerBlock;
  private RegistryObject<Block> pottedFlowerBlock;

  public FlowerSet(String name, Item dyeColor, MobEffect statusEffect, Item itemBefore, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = dyeColor;
    this.statusEffect = statusEffect;
    this.itemBefore = itemBefore;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, Item dyeColor, Item itemBefore, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = dyeColor;
    this.statusEffect = null;
    this.itemBefore = itemBefore;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, MobEffect statusEffect, Item itemBefore, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = null;
    this.statusEffect = statusEffect;
    this.itemBefore = itemBefore;
    this.preset = preset;
    this.registerFlower();
  }

  public FlowerSet(String name, Item itemBefore, FlowerPreset preset) {
    this.name = name;
    this.dyeColor = null;
    this.statusEffect = null;
    this.itemBefore = itemBefore;
    this.preset = preset;
    this.registerFlower();
  }

  private void registerFlower() {
    if (isTall()) {
      flowerBlock = registerTallPlantBlock(name,
              () -> new TallFlowerBlock(BlockBehaviour.Properties
              .of()
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .ignitedByLava()
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)),
          itemBefore,
          0.4f
      );

    } else if (preset == FlowerPreset.BIG_SMALL) {
      flowerBlock = registerPlantBlock(name, () -> new LargeFlowerBlock(
          statusEffect,
          7,
          BlockBehaviour.Properties
              .of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ), itemBefore, 0.4f);
    } else if (preset == FlowerPreset.SMALL) {
      flowerBlock = registerPlantBlock(name, () -> new FlowerBlock(
          statusEffect,
          7,
          BlockBehaviour.Properties
              .of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ), itemBefore, 0.3f);
    } else if (preset == FlowerPreset.MID_SMALL) {
      flowerBlock = registerPlantBlock(name, () -> new MidFlowerBlock(
          statusEffect,
          7,
          BlockBehaviour.Properties
              .of()
              .mapColor(MapColor.PLANT)
              .noCollission()
              .instabreak()
              .sound(SoundType.GRASS)
              .offsetType(BlockBehaviour.OffsetType.XZ)
              .pushReaction(PushReaction.DESTROY)
      ), itemBefore, 0.3f);
    }
    if (hasFlowerPot()) {
      pottedFlowerBlock = registerTransparentBlockWithoutTab("potted_" + name,
              () -> new FlowerPotBlock(flowerBlock.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    }
    NSRegistryHelper.FlowerHashMap.put(name, this);
  }

  public int getDyeNumber() {
    switch (preset) {
      case TALL, BIG_SMALL -> {
        return 2;
      }
      case BIG_TALL -> {
        return 4;
      }
      default -> {
        return 1;
      }
    }
  }

  public FlowerPreset getPreset() {
    return preset;
  }

  public boolean hasFlowerPot() {
    return getPreset() == FlowerPreset.SMALL || getPreset() == FlowerPreset.BIG_SMALL || getPreset() == FlowerPreset.MID_SMALL;
  }

  public boolean isTall() {
    return getPreset() == FlowerPreset.TALL || getPreset() == FlowerPreset.BIG_TALL;
  }

  public Item getDyeColor() {
    return dyeColor;
  }

  public RegistryObject<Block> getFlowerBlock() {
    return flowerBlock;
  }

  public RegistryObject<Block> getPottedFlowerBlock() {
    return pottedFlowerBlock;
  }

  public String getName() {
    return name;
  }

  public enum FlowerPreset {
    SMALL, MID_SMALL,
    TALL,
    BIG_SMALL,
    BIG_TALL
  }
}
