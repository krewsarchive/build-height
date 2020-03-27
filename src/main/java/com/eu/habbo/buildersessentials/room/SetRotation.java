package com.eu.habbo.buildersessentials.room;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.furniture.FurnitureMovedEvent;
import com.eu.habbo.plugin.events.furniture.FurniturePlacedEvent;
import com.eu.habbo.plugin.events.users.UserExitRoomEvent;

import static com.eu.habbo.buildersessentials.commands.SetRotationCommand.SET_ROTATION_KEY;

public class SetRotation implements EventListener {
    @EventHandler
    public static void onUserExitRoomEvent(UserExitRoomEvent event)
    {
        event.habbo.getHabboStats().cache.remove(SET_ROTATION_KEY);
    }


    @EventHandler
    public static void onFurniturePlaced(final FurniturePlacedEvent event)
    {
        if (event.location != null)
        {
            if (event.habbo.getHabboStats().cache.containsKey(SET_ROTATION_KEY))
            {
                Emulator.getThreading().run(new Runnable() {
                    public void run() {
                        event.furniture.setRotation((int) event.habbo.getHabboStats().cache.get(SET_ROTATION_KEY));
                        event.furniture.needsUpdate(true);
                        event.habbo.getHabboInfo().getCurrentRoom().updateItem(event.furniture);
                    }
                }, 25);
            }
        }
    }

    @EventHandler
    public static void onFurnitureMoved(final FurnitureMovedEvent event)
    {
        if (event.newPosition != null)
        {
            if (event.habbo.getHabboStats().cache.containsKey(SET_ROTATION_KEY))
            {
                Emulator.getThreading().run(new Runnable() {
                    public void run() {
                        event.furniture.setRotation((int) event.habbo.getHabboStats().cache.get(SET_ROTATION_KEY));
                        event.furniture.needsUpdate(true);
                        event.habbo.getHabboInfo().getCurrentRoom().updateItem(event.furniture);
                    }
                }, 0);
            }
        }
    }
}