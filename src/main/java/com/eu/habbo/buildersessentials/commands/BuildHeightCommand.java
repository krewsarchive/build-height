package com.eu.habbo.buildersessentials.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.buildersessentials.BuildersEssentials;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.furniture.FurnitureMovedEvent;
import com.eu.habbo.plugin.events.furniture.FurniturePlacedEvent;
import com.eu.habbo.plugin.events.users.UserExitRoomEvent;

public class BuildHeightCommand extends Command implements EventListener {
    public static String BUILD_HEIGHT_KEY = "be.build_height";

    public BuildHeightCommand(String permission, String[] keys) {
        super(permission, keys);

        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, this);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception {
        if (strings.length == 2) {
            Double height = -1.0;

            try {
                height = Double.valueOf(strings[1]);
            } catch (Exception e) {
            }

            if (height > 40 || height < 0) {
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_buildheight.invalid_height"));
                return true;
            }

            gameClient.getHabbo().getHabboStats().cache.put(BUILD_HEIGHT_KEY, height);
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_buildheight.changed").replace("%height%", height + ""));
        } else {
            if (gameClient.getHabbo().getHabboStats().cache.containsKey(BUILD_HEIGHT_KEY)) {
                gameClient.getHabbo().getHabboStats().cache.remove(BUILD_HEIGHT_KEY);
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_buildheight.disabled"));
                return true;
            }
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_buildheight.not_specified"));
        }
        return true;
    }
}