package Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;


import static Utilities.Format.format;

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
            return format("&f娜    &6&lBlast Storm &6Tier &7I    &f娜");
        } else if (tierLevel == 2) {

            return format("&f艾    &6&lBlast Storm &6Tier &7II    &f艾");
        } else if (tierLevel == 3) {

            return format("&f卡    &6&lBLAST STORM &6TIER &c&lIII    &f卡");

        } else {
            return "&6&lBlast Storm";
        }

    }

    public void addPotionTiers(Player connected, int tier) throws NullPointerException {
        if (tier == 1) {
            connected.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1200,1,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,600,1,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,400,9,true,false,true));
        }
        if(tier == 2){
            connected.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1200,2,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,600,2,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,600,1,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,600,9,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,400,4,true,false,true));
        }
        if(tier == 3){
            connected.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1200,4,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,600,3,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,600,2,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,1200,9,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,400,9,true,false,true));
            connected.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,600,3,true,false,true));
        }
    }


    public String getSubtitleStorm(int tierLevel) {

        if (tierLevel == 1) {


            return format("&8¿Podrán sobrevivir?");

        } else if (tierLevel == 2) {

            return format("&8¡Que comience el fin!");
        } else if (tierLevel == 3) {

            return format("&8¡Sobrevive y prueba el &7SUFRIMIENTO!");

        } else {
            return "&8¿?";
        }

    }

}
