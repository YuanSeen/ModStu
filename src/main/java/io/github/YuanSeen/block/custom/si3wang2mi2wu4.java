package io.github.YuanSeen.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 最终目标需要写一个同细雪一样的方块，同时可以被桶装起来
 *
 * 基本目标：写一个方块，踩上去后获得debuff √
 *
 * 中间目标：方块同细雪一样               √
 *
 * 最后：可以被桶装起来
 */
public class si3wang2mi2wu4 extends Block {


    // 一些常量和变量的定义
    private static final float HORIZONTAL_PARTICLE_MOMENTUM_FACTOR = 0.083333336F;
    //描述： 水平粒子动量因子的常量值。
    //用途： 用于控制生成粒子效果时水平方向的动量。
    private static final float IN_BLOCK_HORIZONTAL_SPEED_MULTIPLIER = 0.9F;
    //描述： 在方块内部水平速度乘数的常量值。
    //用途： 用于在粉雪块内部控制实体水平移动的速度。
    private static final float IN_BLOCK_VERTICAL_SPEED_MULTIPLIER = 1.5F;
    //描述： 在方块内部垂直速度乘数的常量值。
    //用途： 用于在粉雪块内部控制实体垂直移动的速度。
    private static final float NUM_BLOCKS_TO_FALL_INTO_BLOCK = 2.5F;
    //描述： 掉落到方块中所需的方块数的常量值。
    //用途： 控制实体掉落到粉雪块中时的条件，实体必须从超过这个高度掉落到方块内部。
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.9F, 1.0D);
    //描述： 掉落时的碰撞体积的常量值，表示掉落时实体所占的空间。
    //用途： 用于检测实体掉落到粉雪块中时的碰撞。
    private static final double MINIMUM_FALL_DISTANCE_FOR_SOUND = 4.0D;
    //描述： 播放大声音的最小掉落距离的常量值。
    //用途： 当实体从高度大于等于这个值的地方掉落到粉雪块时，播放大声音。
    private static final double MINIMUM_FALL_DISTANCE_FOR_BIG_SOUND = 7.0D;
    //描述： 掉落时的碰撞体积的常量值，表示掉落时实体所占的空间。
    //用途： 用于检测实体掉落到粉雪块中时的碰撞。

    public si3wang2mi2wu4(Properties properties) {
        super(properties);
    }
    // 重写了父类的方法，用于判断是否跳过渲染
    public boolean skipRendering(BlockState blockState, BlockState blockState1, Direction direction) {
        return blockState1.is(this) ? true : super.skipRendering(blockState, blockState1, direction);
    }

    // 重写了父类的方法，返回一个空的VoxelShape对象
    public VoxelShape getOcclusionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return Shapes.empty();
    }

    // 实现了entityInside方法，处理实体进入方块内部的逻辑
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {

        // 实体为LivingEntity且站在该方块上或实体的脚下方块是迷雾
        if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
            // 使实体陷入方块内部
            entity.makeStuckInBlock(blockState, new Vec3((double)0.9F, 1.5D, (double)0.9F));
            if(!level.isClientSide && entity instanceof LivingEntity living){
                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200));
                living.addEffect(new MobEffectInstance(MobEffects.WITHER,200));
            }
            if (level.isClientSide) {
                RandomSource randomsource = level.getRandom();
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && randomsource.nextBoolean()) {
                    // 在方块上生成粒子效果
                    level.addParticle(ParticleTypes.ASH, entity.getX(), (double)(blockPos.getY() + 1), entity.getZ(),
                            (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }

//        if (!level.isClientSide) {
//            // 如果实体正在燃烧，且游戏规则允许实体破坏方块，或者实体为玩家，则销毁方块
//            if (entity.isOnFire() && (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(level, blockPos)) {
//                level.destroyBlock(blockPos, false);
//            }
//
//            // 将实体的共享标志位“着火”设置为false
//            entity.setSharedFlagOnFire(false);
//        }
    }

    // 实现了getCollisionShape方法，返回碰撞体积的形状，处理实体与方块的碰撞逻辑
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        // 如果CollisionContext是EntityCollisionContext类型
        if (context instanceof EntityCollisionContext entitycollisioncontext) {
            Entity entity = entitycollisioncontext.getEntity();
            if (entity != null) {
                // 如果实体的掉落距离大于2.5，返回方块的碰撞形状FALLING_COLLISION_SHAPE
                if (entity.fallDistance > 2.5F) {

                    return FALLING_COLLISION_SHAPE;
                }
            }
        }

        // 返回空的碰撞体积形状
        return Shapes.empty();
    }

    // 实现了getVisualShape方法，返回视觉形状的碰撞体积，用于渲染逻辑
    public VoxelShape getVisualShape(BlockState p_154276_, BlockGetter p_154277_, BlockPos p_154278_, CollisionContext p_154279_) {
        // 返回空的碰撞体积形状
        return Shapes.empty();
    }

    @Override
    public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        if(!level.isClientSide()){
            player.sendSystemMessage(Component.literal("你感受到了恐惧."));
        }
        //当玩家试图破坏迷雾，就会发出信息/
        super.attack(blockState, level, blockPos, player);
    }
}
