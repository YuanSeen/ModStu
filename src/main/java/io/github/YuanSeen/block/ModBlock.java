package io.github.YuanSeen.block;

import io.github.YuanSeen.Main;
import io.github.YuanSeen.item.ModCreativeModeTab;
import io.github.YuanSeen.item.ModItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    //创建方块注册者

    public static final RegistryObject<Block> LING2SHI2KUANG4 = BLOCK.register("ling2shi2kuang4",
            ()->new DropExperienceBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    , UniformInt.of(3,7)
            ));

    public static final RegistryObject<Item> LINGSHI2KUANG4 = ModItem.ITEM.register("ling2shi2kuang4",
            ()->new BlockItem(LING2SHI2KUANG4.get(), new Item.Properties().tab(ModCreativeModeTab.MODSTU_TAB_QI2TA1)));

    public static void register(IEventBus modEventBus){
        BLOCK.register(modEventBus);
    }
    //用于在世界总线中注册物品
}
