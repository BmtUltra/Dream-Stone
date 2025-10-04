package net.rain.dream_stone;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.rain.dream_stone.component.AutoRepairData;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rain.dream_stone.DreamStoneItem;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mojang.serialization.Codec;

import java.util.function.Supplier;

@Mod(DreamStone.MODID)
public class DreamStone {
    public static final String MODID = "dream_stone";
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final Supplier<Item> DREAM_STONE;

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "dream_stone");
    
  public static final DeferredRegister.DataComponents COMPONENTS = 
        DeferredRegister.createDataComponents(MODID);
    
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AutoRepairData>> AUTO_REPAIR = 
        COMPONENTS.registerComponentType(
            "auto_repair",
            builder -> builder
                .persistent(AutoRepairData.CODEC)
                .networkSynchronized(AutoRepairData.STREAM_CODEC)
        );
    static{
      DREAM_STONE = ITEMS.register("dream_stone",DreamStoneItem::new);
    }
    public DreamStone(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.dream_stone.main"))
            .icon(() -> DREAM_STONE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(DREAM_STONE.get());
            })
            .build());
        CREATIVE_MODE_TABS.register(modEventBus);
        ITEMS.register(modEventBus);
        COMPONENTS.register(modEventBus);
    }
}