package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.hibiscus.naturespirit.NatureSpirit.MOD_ID;

public class NSItemGroups {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NatureSpirit.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NS_ITEM_GROUP = CREATIVE_MODE_TABS.register("ns_item_group",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(NSBlocks.REDWOOD.getSapling().get()))
                    .title(Component.translatable("itemGroup.natures_spirit.item_group"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.acceptAll(NSBlocks.ITEMS.getEntries().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
                    })
                    .build());

}
