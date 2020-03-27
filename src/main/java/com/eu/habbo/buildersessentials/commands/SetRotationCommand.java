package com.eu.habbo.buildersessentials.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.buildersessentials.BuildersEssentials;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.plugin.EventListener;

public class SetRotationCommand extends Command implements EventListener
{
    public static String SET_ROTATION_KEY = "be.set_rotation";
    public SetRotationCommand(String permission, String[] keys) {
        super(permission, keys);
        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, this);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception {
        if (strings.length == 2) {
            int rotation = -1;

            try {
                rotation = Integer.valueOf(strings[1]);
            }
            catch (Exception e) {

            }

            if(rotation > 7 || rotation < -1) {
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setrotation.invalid_state"));
                return true;
            }

            gameClient.getHabbo().getHabboStats().cache.put(SET_ROTATION_KEY, rotation);
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setrotation.changed").replace("%rot%", rotation + ""));
        }
        else
        {
            if(gameClient.getHabbo().getHabboStats().cache.containsKey(SET_ROTATION_KEY))
            {
                gameClient.getHabbo().getHabboStats().cache.remove(SET_ROTATION_KEY);
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setrotation.disabled"));
                return true;
            }
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setrotation.not_specified"));
        }
        return true;
    }

}