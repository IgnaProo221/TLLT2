package Eventos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import static Utilidades.Format.format;

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
            return format("&f♥        &6&lBlast Storm &6Tier &7I        &f♥");
        } else if (tierLevel == 2) {

            return format("&f♥        &6&lBlast Storm &6Tier &7II        &f♥");
        } else if (tierLevel == 3) {

            return format("&f♥        &6&lBLAST STORM &6TIER &c&lIII        &f♥");

        } else {
            return "&6&lBlast Storm";
        }

    }

    public void addPotionTiers(Player connected, int tier) throws NullPointerException {
        if (tier == 1 && tier == 2) {
            connected.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 100, 1));
        } else {
            connected.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 100, 1));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
        }
    }

    /*public void tierTime(int tier) throws NullPointerException{
        if(tier == 1){
            String Tormentajaja = Objects.requireNonNull(Bukkit.getWorld("world")).isThundering() ? "weather thunder " + ((Objects.requireNonNull(Bukkit.getWorld("world")).getWeatherDuration() / 20) + (Muerte.Dia() * 900)) : "weather thunder " + (Muerte.Dia() * 900);

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Tormentajaja);
        }else if(tier == 2){
            String Tormentajaja = Objects.requireNonNull(Bukkit.getWorld("world")).isThundering() ? "weather thunder " + ((Objects.requireNonNull(Bukkit.getWorld("world")).getWeatherDuration() / 20) + (Muerte.Dia() * 1800)) : "weather thunder " + (Muerte.Dia() * 1800);

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Tormentajaja);
        }else {
            String Tormentajaja = Objects.requireNonNull(Bukkit.getWorld("world")).isThundering() ? "weather thunder " + ((Objects.requireNonNull(Bukkit.getWorld("world")).getWeatherDuration() / 20) + (Muerte.Dia() * 2700)) : "weather thunder " + (Muerte.Dia() * 2700);

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Tormentajaja);
        }
    }*/

    public String getSubtitleStorm(int tierLevel) {

        if (tierLevel == 1) {


            return format("&8¿Podran Sobrevivir?");

        } else if (tierLevel == 2) {

            return format("&8¡Que Comienze el Fin!");
        } else if (tierLevel == 3) {

            return format("&8Sobrevive y prueba el &7SUFRIMIENTO");

        } else {
            return "&8¿?";
        }

    }

}
