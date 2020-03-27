package com.eu.habbo.buildersessentials.eventloader;

import com.eu.habbo.Emulator;
import com.eu.habbo.buildersessentials.commands.*;
import com.eu.habbo.habbohotel.commands.CommandHandler;

public class LoadPlayerCommands {
    public static void loadPlayerCommands() {
        try {
            CommandHandler.addCommand(new BuildHeightCommand("cmd_buildheight", Emulator.getTexts().getValue("be.cmd_buildheight.keys").split(";")));
            CommandHandler.addCommand(new SetStateCommand("cmd_setstate", Emulator.getTexts().getValue("be.cmd_setstate.keys").split(";")));
            CommandHandler.addCommand(new SetRotationCommand("cmd_setrotation", Emulator.getTexts().getValue("be.cmd_setrotation.keys").split(";")));
        } catch (Exception ex) {
            Emulator.getLogging().logErrorLine(ex);
        }
    }
}
