package io.github.YuanSeen;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "modstu";
    //修改模组名称
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        bus.addListener(this::commonSetup);
        bus.addListener(this::setup);

    }
    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }


}
