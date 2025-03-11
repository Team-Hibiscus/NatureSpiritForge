package net.hibiscus.naturespirit.blocks;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class VinesLeavesBlock extends LeavesBlock implements BonemealableBlock {

  public Block vinePlantBlock;
  public Block vineTipBlock;

  public VinesLeavesBlock(Properties properties, Block vinePlantBlockInput, Block vineTipBlockInput) {
    super(properties);
    vinePlantBlock = vinePlantBlockInput;
    vineTipBlock = vineTipBlockInput;
  }

  @Override
  public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos blockPos, BlockState state, boolean bl) {
    Optional<BlockPos> optional = BlockUtil.getTopConnectedBlock(levelReader, blockPos, vinePlantBlock, Direction.DOWN, vineTipBlock);
    return (optional.isPresent() && levelReader.getBlockState(optional.get().relative(Direction.DOWN)).isAir()) || levelReader.getBlockState(blockPos.relative(Direction.DOWN)).isAir();
  }

  @Override
  public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
    return true;
  }

  @Override
  public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
    Optional<BlockPos> optional = BlockUtil.getTopConnectedBlock(serverLevel, blockPos, vinePlantBlock, Direction.DOWN, vineTipBlock);
    if (optional.isPresent()) {
      BlockState blockState2 = serverLevel.getBlockState(optional.get());
      ((DownwardVineBlock) blockState2.getBlock()).performBonemeal(serverLevel, randomSource, optional.get(), blockState2);
    }
    if (optional.isEmpty()) {
      serverLevel.setBlock(blockPos.below(), vineTipBlock.defaultBlockState(), Block.UPDATE_CLIENTS);
    }
  }
}
