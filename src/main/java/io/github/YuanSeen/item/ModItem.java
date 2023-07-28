package io.github.YuanSeen.item;

import io.github.YuanSeen.Main;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> CE4SHI4WU4PIN3 = ITEM.register("ce4shi4wu4pin3",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.MODSTU_TAB_YAO4CAI2)));
    //创建一个叫做 测试物品（奇怪的食物） 的物品，放在了 药材 物品栏


    public static final RegistryObject<Item> HENG2XIN1CAO3 = ITEM.register("heng2xin1cao3",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.MODSTU_TAB_YAO4CAI2)));
    //创建了一个叫做 恒心草 的物品，放在了 药材 物品栏


    public static final RegistryObject<Item> CE4SHI4WU4PIN302 = ITEM.register("ce4shi4wu4pin302",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.MODSTU_TAB_QI2TA1)));
    //创建了一个叫做 测试物品02（奇怪的袋子） 的物品，放在了 其他 物品栏


    public static void register(IEventBus modEventBus){
        ITEM.register(modEventBus);
    }



}
