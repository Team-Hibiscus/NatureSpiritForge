/*
package net.hibiscus.naturespirit.util;

import net.hibiscus.naturespirit.blocks.BranchingTrunkBlock;
import net.hibiscus.naturespirit.registration.NSMiscBlocks;
import net.hibiscus.naturespirit.registration.NSWoods;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class NSEvents {

  public static void registerEvents() {
    UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
      BlockPos blockPos = hitResult.getBlockPos();
      BlockState blockState = world.getBlockState(blockPos);
      if (blockState.is(BlockTags.CAULDRONS) && player.getItemInHand(hand).is(Items.MILK_BUCKET) && !blockState.is(NSMiscBlocks.MILK_CAULDRON)) {
        world.playSound(player, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer) {
          CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, player.getItemInHand(hand));
        }

        world.setBlock(blockPos, NSMiscBlocks.MILK_CAULDRON.defaultBlockState(), 11);
        world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, NSMiscBlocks.MILK_CAULDRON.defaultBlockState()));
        if (!player.isCreative() && !player.isSpectator()) {
          player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
      }
      if (blockState.is(BlockTags.CAULDRONS) && player.getItemInHand(hand).is(NSMiscBlocks.CHEESE_BUCKET) && !blockState.is(NSMiscBlocks.CHEESE_CAULDRON)) {
        world.playSound(player, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (player instanceof ServerPlayer) {
          CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockPos, player.getItemInHand(hand));
        }

        world.setBlock(blockPos, NSMiscBlocks.CHEESE_CAULDRON.defaultBlockState(), 11);
        world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, NSMiscBlocks.CHEESE_CAULDRON.defaultBlockState()));
        if (!player.isCreative() && !player.isSpectator()) {
          player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
      }
      return InteractionResult.PASS;
    }));

  }
}
*/
