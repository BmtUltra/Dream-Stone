package net.rain.dream_stone.event;

import net.rain.dream_stone.DreamStone;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.rain.dream_stone.component.AutoRepairData; 
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DreamStone.MODID, bus = EventBusSubscriber.Bus.GAME)
public class AnvilEventHandler {

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (right.isEmpty() || !right.is(DreamStone.DREAM_STONE.get())) {
            return;
        }

        if (left.isEmpty()) {
            return;
        }

        if (!left.isDamageableItem()) {
            return;
        }

        if (left.get(DataComponents.UNBREAKABLE) != null) {
            return;
        }

        ItemStack output = left.copy();
        
        output.set(DataComponents.UNBREAKABLE, new Unbreakable(true));
        output.set(DreamStone.AUTO_REPAIR, AutoRepairData.DEFAULT);
        
        String name = event.getName();
        if (name != null && !name.trim().isEmpty()) {
            output.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        }

        event.setOutput(output);
        event.setCost(30); 
        event.setMaterialCost(1);
    }
}