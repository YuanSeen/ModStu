package io.github.YuanSeen.init;

import io.github.YuanSeen.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> LEI = register("lei",
            () -> new Item(new Item.Properties().tab(Main.TUTORIAL_TAB)));
    //我们的第一个物品

    public static final RegistryObject<Item> FAKE = register("fake",
            () -> new Item(new Item.Properties().tab(Main.TUTORIAL_TAB)));
    //第二个物品

    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}
