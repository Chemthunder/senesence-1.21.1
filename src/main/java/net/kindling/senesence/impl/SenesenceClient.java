package net.kindling.senesence.impl;

import net.fabricmc.api.ClientModInitializer;
import net.kindling.senesence.impl.index.SenesenceBlocks;
import net.kindling.senesence.impl.index.SenesenceParticles;

public class SenesenceClient implements ClientModInitializer {

    public void onInitializeClient() {
        SenesenceBlocks.clientInit();
        SenesenceParticles.clientInit();
    }
}
