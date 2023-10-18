package io.github.YuanSeen.world.feature;

import io.github.YuanSeen.Main;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

    //用于注册放置特征的注册者
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Main.MOD_ID);

    //灵石普通矿脉的生成：生成OVER_LING2SHI2KUANG4的特征，在-60~60间，每隔6个区块尝试生成一轮，一轮3次
    public static final RegistryObject<PlacedFeature> PLACED_LING2SHI2KUANG4 = PLACED_FEATURE.register("placed_ling2shi2kuang4",
            () -> new PlacedFeature(ModConfiguredFeatures.OVER_LING2SHI2KUANG4.getHolder().get(),lingshi2kuang4(3,6,-60,60)

            ));

    //灵石普通矿脉的生成：生成OVER_LING2SHI2KUANG4_TE4的特征，在-60~60间，每隔1个区块尝试生成一轮，一轮1次
    public static final RegistryObject<PlacedFeature> PLACED_LING2SHI2KUANG4_TE4 = PLACED_FEATURE.register("placed_ling2shi2kuang4_te4",
            () -> new PlacedFeature(ModConfiguredFeatures.OVER_LING2SHI2KUANG4_TE4.getHolder().get(),lingshi2kuang4(1,1,-60,60)

            ));

    //简便的注册参数填补方法
    private static List<PlacementModifier> lingshi2kuang4(int p_195347_, int p_195350_,int low,int top) {
        return List.of(CountPlacement.of(p_195347_),
                InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(p_195350_),
                BiomeFilter.biome(),
                HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(low),VerticalAnchor.aboveBottom(top)
                ));
    }


    //PlacementModifier用于描述矿物的生成位置和条件。

    //orePlacement方法返回一个列表，其中InSquarePlacement.spread()表示矿物方块形状生成，BiomeFilter.biome()是指在特定的生物群系生成。

    //commonOrePlacement方法，其中CountPlacement.of(p_195344_)指定了每个区块生成多少的矿物。

    //rareOrePlacement方法，RarityFilter.onAverageOnceEvery(p_195350_)是用于指定每隔多少个区块才生成一个矿物的。


    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
    public static void register(IEventBus modEvent){
        PLACED_FEATURE.register(modEvent);
    }
}
