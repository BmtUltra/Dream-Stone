package com.bmt.dream_stone.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class DreamStoneItem extends Item {

    public DreamStoneItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
                .fireResistant());
    }
}