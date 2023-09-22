package io.github.YuanSeen.item.custom;


import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class qin1ming2dan1 extends Item {

    public qin1ming2dan1(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand.equals(InteractionHand.MAIN_HAND)){
            player.sendSystemMessage(Component.literal("一股神秘力量阻止你服下丹药。"));
            player.sendSystemMessage(Component.literal("但药香让你感受到满足。"));
            player.addEffect(new MobEffectInstance(MobEffects.SATURATION,20));
        }
        return super.use(level, player, hand);
    }



}
