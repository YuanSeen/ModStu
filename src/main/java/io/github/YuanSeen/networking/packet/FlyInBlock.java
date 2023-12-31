package io.github.YuanSeen.networking.packet;

import io.github.YuanSeen.ling2li4.PlayerLing2li4Provider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlyInBlock {
    public FlyInBlock(){

    }
    public FlyInBlock(FriendlyByteBuf buf){

    }

    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            //if (level.getBlockState(player.getOnPos()).getBlock().equals(Blocks.COAL_BLOCK)) {}
                player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(playerLing2li4 -> {

                    if (playerLing2li4.getLing2li4() > 2) {
                        playerLing2li4.subling2li4(2);

                        double x = player.getBlockX();
                        double y = player.getBlockY();
                        double z = player.getBlockZ();

                        level.destroyBlock(new BlockPos(x,y-1,z),false);

                        player.setPos(new Vec3(x,y+20,z));
                    }

                });
        });
        return true;
        }
}
