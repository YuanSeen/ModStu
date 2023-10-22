package io.github.YuanSeen.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
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
            if (hasFlyAround(player, level)) {
                double x = player.getBlockX();
                double y = player.getBlockY();
                double z = player.getBlockZ();

                player.move(MoverType.PLAYER,new Vec3(x,y+20,z));

                level.destroyBlock(new BlockPos(x,y,z),false);
            }

        });
        return true;
        }

    private boolean hasFlyAround(ServerPlayer player,ServerLevel level){

        return player.getFeetBlockState().is(Blocks.COAL_BLOCK);
    }
}
