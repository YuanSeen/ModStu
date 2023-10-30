package io.github.YuanSeen.networking.packet;

import io.github.YuanSeen.ling2li4.PlayerLing2li4;
import io.github.YuanSeen.ling2li4.PlayerLing2li4Provider;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.FriendlyByteBuf;
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
        Player player = context.getSender();
        Level level = player.getLevel();

        player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(playerLing2li4 ->{
            if (playerLing2li4.getLing2li4()>0) {
                playerLing2li4.addling2li4(4);
                level.playSound(
                        null,
                        player.getOnPos(),
                        SoundEvents.AMBIENT_CAVE,
                        SoundSource.PLAYERS,
                        0.5f,
                        level.random.nextFloat()*0.09f);
            }
        });
        return true;
    }
}
