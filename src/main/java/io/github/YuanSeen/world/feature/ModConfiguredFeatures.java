package io.github.YuanSeen.world.feature;

import com.google.common.base.Suppliers;
import io.github.YuanSeen.Main;
import io.github.YuanSeen.block.ModBlock;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    //注册世界特征文件的注册者
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY,Main.MOD_ID);

    //一个特征：按照STONE的规则替换为灵石矿
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVER_LING2SHI2KUANG4_PLACED =
            Suppliers.memoize(() -> List.of(
                    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.LING2SHI2KUANG4.get().defaultBlockState())
            ));

    //一个特征：按照自定义的规则：替换花岗岩，替换为灵石矿
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVER_LING2SHI2KUANG4_PLACED_TE4 =
            Suppliers.memoize(() -> List.of(
                    OreConfiguration.target(new BlockMatchTest(Blocks.GRANITE),ModBlock.LING2SHI2KUANG4.get().defaultBlockState())
            ));

    //注册特征：以矿物方式注册，配置表中重点为矿脉64格大小
    public static final RegistryObject<ConfiguredFeature<?,?>> OVER_LING2SHI2KUANG4 = CONFIGURED_FEATURE.register("over_ling2shi2kuang4",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OVER_LING2SHI2KUANG4_PLACED.get(),64)));

    //注册特征：以矿物方式注册，配置表中重点为矿脉8格大小
    public static final RegistryObject<ConfiguredFeature<?,?>> OVER_LING2SHI2KUANG4_TE4 = CONFIGURED_FEATURE.register("over_ling2shi2kuang4_te",
            () -> new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OVER_LING2SHI2KUANG4_PLACED_TE4.get(),8)));

    //在世界总线中注册
    public static void regsiter(IEventBus modEvent){
        CONFIGURED_FEATURE.register(modEvent);
    }
}
