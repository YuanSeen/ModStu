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
            ()->new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> CESHIWUPIN = ITEM.register("ce4shi4wu4pin302",
            ()->new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));


    public static void register(IEventBus modEventBus){
        ITEM.register(modEventBus);
    }






}
