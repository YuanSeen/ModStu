package io.github.YuanSeen.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String AN4JIAN4 = "key.category.modstu.an4jian4";
    public static final String KEY_NING2XIN1ZHOU4 =  "key.modstu.ning2xin1zhou4";

    public static final String KEY_FLYINBLOCK = "key.modsti.flyInBlock";

    public static final KeyMapping JING4XI1SHU4_KEY = new KeyMapping(
            KEY_NING2XIN1ZHOU4,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            AN4JIAN4);

    public static final KeyMapping FLYINBLOCK_KEY = new KeyMapping(
            KEY_FLYINBLOCK,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            AN4JIAN4);

}