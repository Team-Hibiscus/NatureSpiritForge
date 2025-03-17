package net.hibiscus.naturespirit.blocks.block_entities;

import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NSSignBlockEntity extends SignBlockEntity {
    public NSSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NSBlocks.NS_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return NSBlocks.NS_SIGN.get();
    }
}
