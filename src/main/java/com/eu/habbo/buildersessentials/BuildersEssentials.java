package com.eu.habbo.buildersessentials;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.eu.habbo.Emulator.ANSI_BLUE;
import static com.eu.habbo.Emulator.ANSI_WHITE;
import static com.eu.habbo.buildersessentials.eventloader.loadAll.loadAll;

/* Builders Essentials
   A collection of must have commands for room builders, such as setstate and floorheight.

   #Go Go Team Krews. Love for Harmony, Beny, Alejandro, ArpyAge, Layne, Bill, Ridge and Cronk.
 */
public class BuildersEssentials extends HabboPlugin implements EventListener {
    public static BuildersEssentials INSTANCE = null;

    @Override
    public void onEnable() {
        INSTANCE = this;
        Emulator.getPluginManager().registerEvents(this, this);
            this.checkDatabase();
        }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }

    private boolean registerPermission(String name, String options, String defaultValue, boolean defaultReturn)
    {
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection())
        {
            try (PreparedStatement statement = connection.prepareStatement("ALTER TABLE  `permissions` ADD  `" + name +"` ENUM(  " + options + " ) NOT NULL DEFAULT  '" + defaultValue + "'"))
            {
                statement.execute();
                return true;
            }
        }
        catch (SQLException e)
        {}

        return defaultReturn;
    }

    @EventHandler
    public static void onEmulatorLoaded(EmulatorLoadedEvent event) throws Exception {
        INSTANCE.checkDatabase();
        loadAll();
        System.out.println("[" + ANSI_BLUE + "OFFICIAL PLUGIN" + ANSI_WHITE + "] " + "Builders Essentials (1.0.0) has official loaded!");
    }

    public void checkDatabase() {
        boolean reloadPermissions = false;
        reloadPermissions = registerPermission("cmd_setstate", "'0', '1', '2'", "1", reloadPermissions);
        reloadPermissions = registerPermission("cmd_buildheight", "'0', '1', '2'", "1", reloadPermissions);
        reloadPermissions = registerPermission("cmd_setrotation", "'0', '1', '2'", "1", reloadPermissions);

        if (reloadPermissions)
        {
            Emulator.getGameEnvironment().getPermissionsManager().reload();
        }

    }

    public static void main(String[] args)
    {
        System.out.println("Don't run this separately");
    }
}