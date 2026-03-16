package com.example.easyclientv2.network;

import com.example.easyclientv2.EasyClientV2;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.Text;

public class EasyPackets {

    /**
     * Registers the payload type and the server-side receiver.
     * Called from EasyClientV2#onInitialize (runs on both sides).
     */
    public static void registerServerReceiver() {
        // Tell the networking layer how to encode/decode this packet
        PayloadTypeRegistry.playC2S().register(
                EasyMessagePayload.ID,
                EasyMessagePayload.CODEC
        );

        // Handle the packet when it arrives on the server
        ServerPlayNetworking.registerGlobalReceiver(
                EasyMessagePayload.ID,
                (payload, context) -> {
                    String msg = payload.message();
                    context.server().execute(() -> {
                        EasyClientV2.LOGGER.info(
                                "[EasyClientV2] {} sent packet: {}",
                                context.player().getName().getString(), msg
                        );

                        // ── Put your custom server logic here ──
                        // Example: echo back to the player
                        context.player().sendMessage(
                                Text.literal("§b[EasyClientV2] §fPacket received: §e" + msg),
                                false
                        );
                    });
                }
        );
    }
}
