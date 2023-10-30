package io.github.YuanSeen.event;

import io.github.YuanSeen.Main;
import io.github.YuanSeen.item.ModItem;
import io.github.YuanSeen.ling2li4.PlayerLing2li4;
import io.github.YuanSeen.ling2li4.PlayerLing2li4Provider;
import io.github.YuanSeen.villager.ModVillager;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class ModEvent {

    @SubscribeEvent
    public static void addTrades (VillagerTradesEvent event){

        //当职业是药师时
        if (event.getType() == ModVillager.YAO4SHI1.get()){
            //获得交易列表，用一个集合来存储
            Int2ObjectMap< List <VillagerTrades.ItemListing>> trades =event.getTrades();
            //获得一个物品格子，里面有5个恒心草
            ItemStack stack = new ItemStack(ModItem.HENG2XIN1CAO3.get(),5);
            //添加一个物品格子，里面有青冥丹一颗
            ItemStack stack1 = new ItemStack(ModItem.QIN1MING2DAN1.get(),1);

            int villagerLevel =1 ;
            //添加一个新交易，提供绿宝石5个,获得的物品stack,最大交易10次，返回经验8，价格浮动0.02
            trades.get(villagerLevel).add((trader,rand) -> new MerchantOffer(new ItemStack(Items.EMERALD,5),
                    stack,10,8,0.02f));
            //添加一个新交易，提供五个绿宝石、五个恒心草，获得物品stack1，最大交易10此，返回经验8，价格浮动0.02
            trades.get(2).add((trader,rand) -> new MerchantOffer(new ItemStack(Items.EMERALD,5),stack,
                    stack1,10,8,0.02f));

        }
    }
    @SubscribeEvent
    public static void onAttackCapabilitiesPlayer(AttachCapabilitiesEvent event){
        if (event.getObject() instanceof Player player){
            if (player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).isPresent()){
                event.addCapability(new ResourceLocation(Main.MOD_ID,"properties"),new PlayerLing2li4Provider());
            }

        }

    }

    @SubscribeEvent
    public static void onPlayClone(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getOriginal().getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(newStore->{
                    newStore.copyFrom(oldStore);
                    //如果报错，改回getOriginal()
                });
            });
        }

    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerLing2li4.class);
    }

    @SubscribeEvent
    public static void onPlayTick(TickEvent.PlayerTickEvent event){
        if (event.side == LogicalSide.SERVER){
            event.player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(ling2li4 -> {
                if (ling2li4.getLing2li4() > 0 && event.player.getRandom().nextFloat() < 0.005f){

                }

            }
            );
        }
    }
}

//    public static void onPlayTick(TickEvent.PlayerTickEvent event){
//        if (event.side == LogicalSide.SERVER) {
//            event.player.getCapability(PlayerLing2li4Provider.PLAYER_LING2LI4).ifPresent(ling2li4 -> {
//                if (ling2li4.getLing2li4() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // 平均10s
//                    ling2li4.subling2li4(1);
//                    event.player.sendSystemMessage(Component.literal("Subtracted ling2li4"));
//                }
//            });
//        }
//    }
