package io.github.YuanSeen.item;

import io.github.YuanSeen.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class ModItem {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static void register(IEventBus modEventBus){
        ITEM.register(modEventBus);
    }
}
