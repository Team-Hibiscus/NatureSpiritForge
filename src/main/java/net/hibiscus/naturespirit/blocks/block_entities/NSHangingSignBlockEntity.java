package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NSHangingSignBlockEntity extends HangingSignBlockEntity {
    public NSHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NSBlocks.NS_HANGING_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return NSBlocks.NS_HANGING_SIGN.get();
    }
}
