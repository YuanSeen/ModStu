package io.github.YuanSeen.event;

import io.github.YuanSeen.Main;
import io.github.YuanSeen.item.ModItem;
import io.github.YuanSeen.villager.ModVillager;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
            trades.get(2).add((trader,rand) -> new MerchantOffer(new ItemStack(Items.EMERALD,5),stack,
                    stack1,10,8,0.02f));

        }
    }
}
