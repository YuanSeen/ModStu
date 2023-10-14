package io.github.YuanSeen.villager;

import com.google.common.collect.ImmutableSet;
import io.github.YuanSeen.Main;
import io.github.YuanSeen.block.ModBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillager {

    //注册工作方块的注册者
    public static final DeferredRegister<PoiType> POI_TYPE
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, Main.MOD_ID);

    //注册职业的注册者
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION
            =DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,Main.MOD_ID);

    //注册了“药香炉”工作方块
    public static final RegistryObject<PoiType> YAO4XIANG1LU2_POI = POI_TYPE.register("yao4xiang1lu2_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlock.YAO4XIANG1LU2.get().getStateDefinition().getPossibleStates()),1,1));

    //和药香炉对应的职业——药师
    public static final RegistryObject<VillagerProfession> YAO4SHI1 = VILLAGER_PROFESSION.register("yao4shi1",
            () -> new VillagerProfession("yao4shi1",
                    x->x.get() == YAO4XIANG1LU2_POI.get(),
                    x->x.get() == YAO4XIANG1LU2_POI.get(),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER
            ));

    //异常解决
    public static void registerPOIs(){
        try{
            // 获得PoiType类的一个registerBlockStates方法,通过invoke调用将工作方块添加到poiType类中。
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates",PoiType.class).invoke(null,YAO4XIANG1LU2_POI.get());
        }catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    //用于在世界总线注册
    public static void register(IEventBus iEventBus){
        POI_TYPE.register(iEventBus);
        VILLAGER_PROFESSION.register(iEventBus);
    }
}
