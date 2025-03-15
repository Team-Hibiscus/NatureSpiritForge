package net.hibiscus.naturespirit.registration.compat;

import com.google.common.base.Supplier;
import net.hibiscus.naturespirit.registration.NSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSArtsAndCraftsCompat {

    public static final DeferredRegister<Block> ANC_BLOCKS = DeferredRegister.create(Registries.BLOCK, MOD_ID);
    public static final DeferredRegister<Item> ANC_ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    public static final RegistryObject<Block> BLEACHED_CHALK = registerBlock(
            "bleached_chalk",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),
            NSBlocks.PINK_KAOLIN_BRICK_SLAB.get(),
            CreativeModeTabs.COLORED_BLOCKS
    );
    public static final RegistryObject<Block> BLEACHED_CHALK_STAIRS = registerBlock(
            "bleached_chalk_stairs",
            () -> new StairBlock(BLEACHED_CHALK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),
            NSBlocks.PINK_CHALK.get(),
            CreativeModeTabs.COLORED_BLOCKS
    );
    public static final RegistryObject<Block> BLEACHED_CHALK_SLAB = registerBlock(
            "bleached_chalk_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.5F)),
            NSBlocks.PINK_CHALK_STAIRS.get(),
            CreativeModeTabs.COLORED_BLOCKS
    );
    public static void registerBlocks() {}
    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block, ItemLike blockBefore, ResourceKey<CreativeModeTab> secondaryTab) {

        RegistryObject<Item> item1 = ANC_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
//    ItemGroupEvents.modifyEntriesEvent(secondaryTab).register(entries -> entries.addAfter(blockBefore, block1.asItem()));
        return  ANC_BLOCKS.register(name, block);
    }
}
