package net.kindling.senesence.impl;

import net.fabricmc.api.ModInitializer;

import net.kindling.senesence.impl.index.SenesenceItems;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Senesence implements ModInitializer {
	public static final String MOD_ID = "senesence";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        // init
        SenesenceItems.init();

		LOGGER.info(MOD_ID + " has been initialized successfully");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}