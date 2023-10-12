package io.github.YuanSeen.block.custom;

import io.github.YuanSeen.item.ModItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class heng2xin1cao3_block extends CropBlock {
    //创建一个变量用于储存植物生长状态,范围0-6
    public static final IntegerProperty AGE = IntegerProperty.create("age",0,6);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(AGE);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItem.HENG2XIN1CAO3.get();
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    //构造方法
    public heng2xin1cao3_block(Properties properties) {
        super(properties);
    }
}
