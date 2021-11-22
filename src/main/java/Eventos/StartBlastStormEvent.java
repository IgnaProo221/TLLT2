package Eventos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class StartBlastStormEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public String getTitleStorm(int tierLevel) {

        if (tierLevel == 1) {

            return "&6&lBlast Storm &6Tier &8I";

        } else if (tierLevel == 2) {

            return "&6&lBlast Storm &6Tier &7II";
        } else if (tierLevel == 3) {

            return "&6&lBLAST STORM &6TIER &c&lIII";

        } else {
            return "&6&lBlast Storm";
        }

    }

    public String getSubtitleStorm(int tierLevel) {

        if (tierLevel == 1) {

            return "&8¿Podran Sobrevivir?";

        } else if (tierLevel == 2) {

            return "&8¡Que Comienze el Fin!";
        } else if (tierLevel == 3) {

            return "&8Sobrevive y prueba el &7SUFRIMIENTO";

        } else {
            return "&8¿?";
        }

    }

}
