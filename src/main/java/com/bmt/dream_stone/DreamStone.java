package com.bmt.dream_stone;

import com.bmt.dream_stone.item.DreamStoneItem;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(DreamStone.MOD_ID)
public class DreamStone {
    public static final String MOD_ID = "dream_stone";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<Item> DREAM_STONE = ITEMS.register("dream_stone", DreamStoneItem::new);

    public static final RegistryObject<CreativeModeTab> DREAM_STONE_TAB = CREATIVE_MODE_TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.dream_stone.main"))
                    .icon(() -> new ItemStack(DREAM_STONE.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(DREAM_STONE.get());
                    })
                    .build());

    public DreamStone() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        LOGGER.info("Dream Stone mod initialized!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }
}
