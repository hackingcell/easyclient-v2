package com.example.easyclientv2.network;

import com.example.easyclientv2.EasyClientV2;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Custom C2S payload.
 * Carries a string "message" typed by the player in the GUI.
 */
public record EasyMessagePayload(String message) implements CustomPayload {

    public static final CustomPayload.Id<EasyMessagePayload> ID =
            new CustomPayload.Id<>(Identifier.of(EasyClientV2.MOD_ID, "send_message"));

    public static final PacketCodec<PacketByteBuf, EasyMessagePayload> CODEC =
            PacketCodec.tuple(
                    PacketCodecs.STRING, EasyMessagePayload::message,
                    EasyMessagePayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
