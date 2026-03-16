package com.example.easyclientv2;

import com.example.easyclientv2.network.EasyPackets;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyClientV2 implements ModInitializer {

    public static final String MOD_ID = "easyclientv2";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        EasyPackets.registerServerReceiver();
        LOGGER.info("[EasyClientV2] Initialized.");
    }
}
