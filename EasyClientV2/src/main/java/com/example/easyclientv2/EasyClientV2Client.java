package com.example.easyclientv2;

import com.example.easyclientv2.client.screen.EasyScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class EasyClientV2Client implements ClientModInitializer {

    private boolean ctrlWasPressed = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Only trigger when in-game and no other screen is open
            if (client.player == null || client.currentScreen != null) {
                ctrlWasPressed = false;
                return;
            }

            long window = client.getWindow().getHandle();
            boolean ctrlNow = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS;

            // Rising-edge detection so holding doesn't spam-open
            if (ctrlNow && !ctrlWasPressed) {
                MinecraftClient.getInstance().setScreen(new EasyScreen());
            }

            ctrlWasPressed = ctrlNow;
        });
    }
}
