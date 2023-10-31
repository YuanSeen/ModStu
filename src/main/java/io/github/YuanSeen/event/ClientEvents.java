package io.github.YuanSeen.event;

import io.github.YuanSeen.Main;
import io.github.YuanSeen.item.ModItem;
import io.github.YuanSeen.ling2li4.PlayerLing2li4;
import io.github.YuanSeen.ling2li4.PlayerLing2li4Provider;
import io.github.YuanSeen.networking.ModMessages;
import io.github.YuanSeen.networking.packet.ExampleC2SPacket;
import io.github.YuanSeen.networking.packet.FlyInBlock;
import io.github.YuanSeen.networking.packet.Hui1fu4ling2li4;
import io.github.YuanSeen.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.JTextComponent;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Main.MOD_ID,value = Dist.CLIENT)
    public static class ClientForgeEvent{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if (KeyBinding.JING4XI1SHU4_KEY.consumeClick()){
                Minecraft.getInstance().player.removeEffect(MobEffects.LUCK);
                ModMessages.sendToServer(new ExampleC2SPacket());
            }

            if (KeyBinding.FLYINBLOCK_KEY.consumeClick()){
                Player player = Minecraft.getInstance().player;
                Level level = player.getLevel();

                //player.sendSystemMessage(Component.literal("客户端发包准备完成"));
                //到这里可以运行，第一个if通过

                player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(playerLing2li4 -> {

                    if (player.getMainHandItem().getItem() == ModItem.LING2SHI2.get()) {
                        //第2-1个if通过
                        ModMessages.sendToServer(new Hui1fu4ling2li4());
                    }

                    if (level.getBlockState(player.getOnPos()).getBlock().equals(Blocks.COAL_BLOCK)){
                            double x = player.getBlockX();
                            double y = player.getBlockY();
                            double z = player.getBlockZ();

                            player.setPos(new Vec3(x,y+20,z));

                            level.destroyBlock(new BlockPos(x,y-1,z),false);

                            ModMessages.sendToServer(new FlyInBlock());
                    }

                });
            }
        }

    }
    @Mod.EventBusSubscriber(modid = Main.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.JING4XI1SHU4_KEY);
            event.register(KeyBinding.FLYINBLOCK_KEY);
        }

    }
}
