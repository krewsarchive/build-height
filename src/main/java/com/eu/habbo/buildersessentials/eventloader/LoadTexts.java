package com.eu.habbo.buildersessentials.eventloader;

import com.eu.habbo.Emulator;

public class LoadTexts {
    public static void loadTexts() {
        try {
            Emulator.getTexts().register("commands.description.cmd_setstate", ":ss");
            Emulator.getTexts().register("be.cmd_setstate.keys","ss;setstate");
            Emulator.getTexts().register("be.cmd_setstate.changed", "Changed state to %extra_data%");
            Emulator.getTexts().register("be.cmd_setstate.disabled", "state removed.");
            Emulator.getTexts().register("be.cmd_setstate.not_specified", "No state set. state must be between 0 - 40.");

            Emulator.getTexts().register("commands.description.cmd_buildheight", ":buildheight [height]");
            Emulator.getTexts().register("be.cmd_buildheight.keys", "buildheight;bh");
            Emulator.getTexts().register("be.cmd_buildheight.invalid_height", "Invalid height! Build height must be between 0 - 40!");
            Emulator.getTexts().register("be.cmd_buildheight.changed", "Changed build height to %height%");
            Emulator.getTexts().register("be.cmd_buildheight.disabled", "Build height removed.");
            Emulator.getTexts().register("be.cmd_buildheight.not_specified", "No buildheight set. Height must be between 0 - 40.");

            Emulator.getTexts().register("commands.description.cmd_setrotation", ":rot;rotation");
            Emulator.getTexts().register("be.cmd_setrotation.keys","rot;setrotation");
            Emulator.getTexts().register("be.cmd_setrotation.changed", "Changed Furni Rotation to %rot%");
            Emulator.getTexts().register("be.cmd_setrotation.disabled", "Furni Rotation removed.");
            Emulator.getTexts().register("be.cmd_setrotation.not_specified", "No rotation set. state must be between 0 - 6.");

        } catch (Exception ex) {
            Emulator.getLogging().logErrorLine(ex);
        }
    }
}
