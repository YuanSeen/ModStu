package io.github.YuanSeen.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MODSTU_TAB_YAO4CAI2 = new CreativeModeTab("yao4cai2") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.HENG2XIN1CAO3.get());
        }
    };
    //创建一个叫做 药材 的物品栏


    public static final CreativeModeTab MODSTU_TAB_QI2TA1 = new CreativeModeTab("qi2ta1") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.CE4SHI4WU4PIN302.get());
        }
    };
    //创建一个叫做 其他 的物品栏
}
