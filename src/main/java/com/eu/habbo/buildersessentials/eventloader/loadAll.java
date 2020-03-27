package com.eu.habbo.buildersessentials.eventloader;

import com.eu.habbo.Emulator;
import com.eu.habbo.buildersessentials.BuildersEssentials;
import com.eu.habbo.buildersessentials.room.SetBuildHeight;
import com.eu.habbo.buildersessentials.room.SetRotation;
import com.eu.habbo.buildersessentials.room.SetState;

public class loadAll {
    public static void loadAll() throws Exception {
        LoadPlayerCommands.loadPlayerCommands();
        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, new SetState());
        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, new SetBuildHeight());
        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, new SetRotation());
        LoadTexts.loadTexts();
        LoadConfig.loadConfig();

    }
}
