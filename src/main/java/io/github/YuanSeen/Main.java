package io.github.YuanSeen;

import com.mojang.logging.LogUtils;
import io.github.YuanSeen.block.ModBlock;
import io.github.YuanSeen.item.ModItem;
import io.github.YuanSeen.villager.ModVillager;
import io.github.YuanSeen.world.feature.ModConfiguredFeatures;
import io.github.YuanSeen.world.feature.ModPlacedFeatures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "modstu";
    private static final Logger LOGGER = LogUtils.getLogger();

    //重要的构造方法
    public Main(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItem.register(modEventBus);
        ModBlock.register(modEventBus);
        ModVillager.register(modEventBus);

        ModConfiguredFeatures.regsiter(modEventBus);
        ModPlacedFeatures.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::setup);

    }
    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(ModVillager::registerPOIs);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }


}
