package com.eu.habbo.buildersessentials.room;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.furniture.FurnitureMovedEvent;
import com.eu.habbo.plugin.events.furniture.FurniturePlacedEvent;
import com.eu.habbo.plugin.events.users.UserExitRoomEvent;

import static com.eu.habbo.buildersessentials.commands.SetStateCommand.SET_STATE_KEY;

public class SetState implements EventListener {
    @EventHandler
    public static void onUserExitRoomEvent(UserExitRoomEvent event)
    {
        event.habbo.getHabboStats().cache.remove(SET_STATE_KEY);
    }


    @EventHandler
    public static void onFurniturePlaced(final FurniturePlacedEvent event)
    {
        if (event.location != null)
        {
            if (event.habbo.getHabboStats().cache.containsKey(SET_STATE_KEY))
            {
                Emulator.getThreading().run(() -> {
                    event.furniture.setExtradata((String) event.habbo.getHabboStats().cache.get(SET_STATE_KEY));
                    event.furniture.needsUpdate(true);
                    event.habbo.getHabboInfo().getCurrentRoom().updateItem(event.furniture);
                }, 25);
            }
        }
    }

    @EventHandler
    public static void onFurnitureMoved(final FurnitureMovedEvent event)
    {
        if (event.newPosition != null)
        {
            if (event.habbo.getHabboStats().cache.containsKey(SET_STATE_KEY))
            {
                Emulator.getThreading().run(() -> {
                    event.furniture.setExtradata((String) event.habbo.getHabboStats().cache.get(SET_STATE_KEY));
                    event.furniture.needsUpdate(true);
                    event.habbo.getHabboInfo().getCurrentRoom().updateItem(event.furniture);
                }, 0);
            }
        }
    }
}