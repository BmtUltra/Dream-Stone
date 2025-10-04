package net.rain.dream_stone.event;

import net.rain.dream_stone.DreamStone;
import net.rain.dream_stone.component.AutoRepairData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = DreamStone.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        
        if (player.level().isClientSide()) {
            return;
        }
        
        ItemStack mainHandItem = player.getMainHandItem();
        
        if (!mainHandItem.isEmpty() && 
            mainHandItem.isDamageableItem() && 
            mainHandItem.isDamaged()) {
            
            AutoRepairData repairData = mainHandItem.get(DreamStone.AUTO_REPAIR);
            
            if (repairData != null && repairData.enabled()) {
                if (player.tickCount % repairData.repairInterval() == 0) {
                    repairItem(mainHandItem, repairData.repairAmount());
                }
            }
        }
    }
    
    private static void repairItem(ItemStack stack, int repairAmount) {
        // 回复耐久度
        int currentDamage = stack.getDamageValue();
        if (currentDamage > 0) {
            int newDamage = Math.max(0, currentDamage - repairAmount);
            stack.setDamageValue(newDamage);
        }
    }
}