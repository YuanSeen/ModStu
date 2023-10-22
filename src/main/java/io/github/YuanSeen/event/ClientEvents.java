package io.github.YuanSeen.event;

import io.github.YuanSeen.Main;
import io.github.YuanSeen.networking.ModMessages;
import io.github.YuanSeen.networking.packet.ExampleC2SPacket;
import io.github.YuanSeen.networking.packet.FlyInBlock;
import io.github.YuanSeen.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
                ModMessages.sendToServer(new FlyInBlock());
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("??"));
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
