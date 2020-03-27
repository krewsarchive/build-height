package com.eu.habbo.buildersessentials.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.buildersessentials.BuildersEssentials;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.plugin.EventListener;

public class SetStateCommand extends Command implements EventListener
{
    public static String SET_STATE_KEY = "be.set_state";
    public SetStateCommand(String permission, String[] keys)
    {
        super(permission, keys);
        Emulator.getPluginManager().registerEvents(BuildersEssentials.INSTANCE, this);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception
    {
        if (strings.length == 2)
        {
            String extra_data = "-1.0";

            try
            {
                extra_data = String.valueOf(strings[1]);
            }
            catch (Exception e)
            {

            }
            int rotation = Integer.parseInt(extra_data);
            if(rotation > 40 || rotation < 0) {
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setstate.invalid_state"));
                return true;
            }

            gameClient.getHabbo().getHabboStats().cache.put(SET_STATE_KEY, extra_data);
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setstate.changed").replace("%extra_data%", extra_data + ""));
        }
        else
        {
            if(gameClient.getHabbo().getHabboStats().cache.containsKey(SET_STATE_KEY))
            {
                gameClient.getHabbo().getHabboStats().cache.remove(SET_STATE_KEY);
                gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setstate.disabled"));
                return true;
            }
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("be.cmd_setstate.not_specified"));
        }
        return true;
    }

}