package com.bmt.dream_stone.event;

import com.bmt.dream_stone.DreamStone;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DreamStone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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

        if (left.getTag() != null && left.getTag().getBoolean("Unbreakable")) {
            return;
        }

        ItemStack output = left.copy();
        output.getOrCreateTag().putBoolean("Unbreakable", true);

        if (event.getName() != null && !event.getName().isEmpty()) {
            output.setHoverName(Component.literal(event.getName()));
        }

        event.setOutput(output);
        event.setCost(30);
        event.setMaterialCost(1);
    }
}