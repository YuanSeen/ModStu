package io.github.YuanSeen.networking.packet;

import io.github.YuanSeen.ling2li4.PlayerLing2li4;
import io.github.YuanSeen.ling2li4.PlayerLing2li4Provider;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class Hui1fu4ling2li4 {
    public Hui1fu4ling2li4(){

    }

    public Hui1fu4ling2li4(FriendlyByteBuf buf){

    }

    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork( ()->{
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();


            player.sendSystemMessage(Component.literal("成功发包到服务器"));

            player.getMainHandItem().shrink(player.getRandom().nextFloat()<0.5?0:1);
            //随机减少功能实现成功

            player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(playerLing2li4 ->{
                int ling2li4 = playerLing2li4.getLing2li4();
                if (ling2li4 < 20) {
                //可以通过
                player.sendSystemMessage(Component.literal("add Ling2li4 4"));

                playerLing2li4.addling2li4(4);
                    player.sendSystemMessage(Component.literal("剩余灵力"+playerLing2li4.getLing2li4()));
                level.playSound(
                        null,
                        player.getOnPos(),
                        SoundEvents.AMBIENT_CAVE,
                        SoundSource.PLAYERS,
                        0.5f,
                        level.random.nextFloat()*0.09f);
            }
            });
        });
        return true;
    }

}
