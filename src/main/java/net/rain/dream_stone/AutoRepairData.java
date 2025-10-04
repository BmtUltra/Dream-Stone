package net.rain.dream_stone.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record AutoRepairData(boolean enabled, int repairInterval, int repairAmount) {
    // Codec 用于数据持久化（保存到磁盘）
    public static final Codec<AutoRepairData> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.BOOL.fieldOf("enabled").forGetter(AutoRepairData::enabled),
            Codec.INT.fieldOf("repair_interval").forGetter(AutoRepairData::repairInterval),
            Codec.INT.fieldOf("repair_amount").forGetter(AutoRepairData::repairAmount)
        ).apply(instance, AutoRepairData::new)
    );

    // StreamCodec 用于网络同步
    public static final StreamCodec<net.minecraft.network.FriendlyByteBuf, AutoRepairData> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.BOOL, AutoRepairData::enabled,
        ByteBufCodecs.INT, AutoRepairData::repairInterval,
        ByteBufCodecs.INT, AutoRepairData::repairAmount,
        AutoRepairData::new
    );

    // 默认实例
    public static final AutoRepairData DEFAULT = new AutoRepairData(true, 1, 1);
}