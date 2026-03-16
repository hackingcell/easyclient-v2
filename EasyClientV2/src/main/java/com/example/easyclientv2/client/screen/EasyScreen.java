package com.example.easyclientv2.client.screen;

import com.example.easyclientv2.network.EasyMessagePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class EasyScreen extends Screen {

    private TextFieldWidget messageField;

    // Panel dimensions
    private static final int PANEL_W = 340;
    private static final int PANEL_H = 110;

    public EasyScreen() {
        super(Text.literal("Easy Client V2 — Send Packet"));
    }

    @Override
    protected void init() {
        int px = (this.width  - PANEL_W) / 2;
        int py = (this.height - PANEL_H) / 2;

        // Message text field
        messageField = new TextFieldWidget(
                this.textRenderer,
                px + 10, py + 28,
                PANEL_W - 20, 20,
                Text.literal("Message")
        );
        messageField.setMaxLength(256);
        messageField.setPlaceholder(Text.literal("Type your packet message…"));
        messageField.setFocused(true);
        this.addSelectableChild(messageField);

        // Send button
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("✈ Send"), button -> trySend())
                        .dimensions(px + 10, py + 58, 155, 20)
                        .build()
        );

        // Cancel button
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("✗ Cancel"), button -> this.close())
                        .dimensions(px + 175, py + 58, 155, 20)
                        .build()
        );
    }

    private void trySend() {
        String msg = messageField.getText().trim();
        if (!msg.isEmpty()) {
            ClientPlayNetworking.send(new EasyMessagePayload(msg));
        }
        this.close();
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx, mouseX, mouseY, delta);

        int px = (this.width  - PANEL_W) / 2;
        int py = (this.height - PANEL_H) / 2;

        // Panel background + border
        ctx.fill(px, py, px + PANEL_W, py + PANEL_H, 0xDD101010);
        ctx.drawBorder(px, py, PANEL_W, PANEL_H, 0xFF44AAFF);

        // Title bar tint
        ctx.fill(px, py, px + PANEL_W, py + 18, 0xFF1A3A5C);

        // Title text
        ctx.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("§b§lEasy Client V2 §7— §fSend Packet"),
                this.width / 2,
                py + 5,
                0xFFFFFF
        );

        // Field label
        ctx.drawTextWithShadow(
                this.textRenderer,
                Text.literal("§7Message:"),
                px + 10, py + 20,
                0xAAAAAA
        );

        // Text field
        messageField.render(ctx, mouseX, mouseY, delta);

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            trySend();
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        // Keep the game running (important for multiplayer)
        return false;
    }
}
