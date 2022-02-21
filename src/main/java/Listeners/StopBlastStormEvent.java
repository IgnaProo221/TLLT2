package Listeners;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class StopBlastStormEvent extends Event {

    private final Cause cause;
    public StopBlastStormEvent(Cause c){
        cause = c;
    }

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public Cause getCause() {
        return cause;
    }



    public enum Cause {
        NATURAL,
        COMMAND,
        UNKNOWN
        ;
    }
}
