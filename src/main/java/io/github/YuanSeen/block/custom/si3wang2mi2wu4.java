package io.github.YuanSeen.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

/**
 * 最终目标需要写一个同细雪一样的方块，同时可以被桶装起来
 *
 * 基本目标：写一个方块，踩上去后获得debuff
 *
 * 中间目标：方块同细雪一样
 *
 * 最后：可以被桶装起来
 */
public class si3wang2mi2wu4 extends Block {

    public si3wang2mi2wu4(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER,20));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20));
        }
        if(!level.isClientSide() && entity instanceof Player player ){
            player.sendSystemMessage(Component.literal("你感受到了恐惧。"));
        }
        //目前问题：当玩家踩上去后会不断的发送信息，而不是只发送一次。

        super.stepOn(level, blockPos, blockState, entity);
    }
}
