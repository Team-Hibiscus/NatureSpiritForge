package net.hibiscus.naturespirit.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class NSFernBlock extends TallGrassBlock {

  RegistryObject<Block> tallPlantBlock;

  public NSFernBlock(Properties properties, RegistryObject<Block> tallPlantBlock) {
    super(properties);
    this.tallPlantBlock = tallPlantBlock;
  }

  @Override
  public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
    if (tallPlantBlock.get().defaultBlockState().canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
      DoublePlantBlock.placeAt(world, tallPlantBlock.get().defaultBlockState(), pos, NSFernBlock.UPDATE_CLIENTS);
    }

  }
}
