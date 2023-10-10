package io.github.YuanSeen.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


/**
 * 鉴于内容复杂，下次再做
 *
 * 懒ing
 */
public class gao1ji2ling2shi2kuang4 extends Block {

    //创建一个变量用于确定其是否亮
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    //将变量注册进列表
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(LIT);
        super.createBlockStateDefinition(blockBlockStateBuilder);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Override
    public void attack(BlockState p_60499_, Level p_60500_, BlockPos p_60501_, Player p_60502_) {
        super.attack(p_60499_, p_60500_, p_60501_, p_60502_);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }



    //抄过来的用于调用粒子效果和设置亮度的代码
    private static void interact(BlockState blockState, Level level, BlockPos blockPos) {
        spawnParticles(level, blockPos);
        //调用生成粒子效果的的方法。
        if (!blockState.getValue(LIT)) {
            level.setBlock(blockPos, blockState.setValue(LIT, Boolean.valueOf(true)), 3);
            //如果不亮，就变亮。
        }

    }

    //抄过来的生成粒子效果的代码
    private static void spawnParticles(Level level, BlockPos blockPos) {
        double d0 = 0.5625D;
        RandomSource randomsource = level.random;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = blockPos.relative(direction);
            if (!level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)randomsource.nextFloat();
                level.addParticle(DustParticleOptions.REDSTONE, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }

    }
    //完善构造方法
    public gao1ji2ling2shi2kuang4(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT,Boolean.valueOf(false)));
    }
}
