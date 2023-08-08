package io.github.YuanSeen.block;

import io.github.YuanSeen.Main;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    //创建方块注册者



    public static void register(IEventBus modEventBus){
        BLOCK.register(modEventBus);
    }
    //用于在世界总线中注册物品
}
