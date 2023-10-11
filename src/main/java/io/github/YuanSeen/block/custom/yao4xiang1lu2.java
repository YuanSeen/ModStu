package io.github.YuanSeen.block.custom;

import io.github.YuanSeen.item.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;


/**
 * 下一个目标
 * 燃烧草药后，香炉燃烧一定时间，在这时间里，右键香炉都可以获得buff
 */
public class yao4xiang1lu2 extends Block {
    //设定方向的变量
    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    //设定一个叫做使用的量，当可以用时，USE为true
    public static final BooleanProperty USE = BooleanProperty.create("use");

    //将变量注册进列表
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(USE,FACING);
        super.createBlockStateDefinition(blockBlockStateBuilder);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(hand.MAIN_HAND);
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            if(blockState.getValue(USE)){
                player.addEffect(new MobEffectInstance(MobEffects.LUCK,2000));
                level.setBlock(blockPos,blockState.setValue(USE,Boolean.valueOf(false)),3);
            }else if (stack.getItem() == ModItem.HENG2XIN1CAO3.get()) {
                stack.shrink(1);
                level.setBlock(blockPos,blockState.setValue(USE,Boolean.valueOf(true)),3);
            }
        }
        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }


    public yao4xiang1lu2(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(USE, Boolean.valueOf(false)));
    }


}
