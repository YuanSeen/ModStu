package io.github.YuanSeen.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket(){

    }

    public ExampleC2SPacket(FriendlyByteBuf buf){

    }

    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            EntityType.ALLAY.spawn(
                    level,
                    null,
                    null,
                    player.blockPosition(),
                    MobSpawnType.COMMAND,
                    true,
                    false
            );

            player.removeEffect(MobEffects.LUCK);
        });
        return true;

    }
}
