package Extras;

import Utilidades.Format;
import Utilidades.TotemsBar;
import Utilidades.Warn;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import tlldos.tll2.TLL2;

import static Utilidades.Format.format;

public class EventosItems {


    public static void temperatura(Player p) {
      try{
          p.sendMessage(Format.PREFIX, format("&cTEMPERATURA TEST: TIENES 69 GRADOS LOL XD"));
      }catch (Exception e){
          e.printStackTrace();
          Warn.Mutant(e);
      }
    }

    public static void totemrestorerEvent(Player p) {
        try{
            p.sendMessage(Format.PREFIX, format("&cSe Han Reiniciado el Porcentaje de Totems Correctamente!"));
            p.playEffect(EntityEffect.TOTEM_RESURRECT);
            TotemsBar.resetAll();
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 20);
            Bukkit.getScheduler().runTaskLater(TLL2.getPlugin(TLL2.class), () -> {
                p.playEffect(EntityEffect.TOTEM_RESURRECT);
            }, 40);
            TotemsBar.resetAll();
        }catch (Exception e){
            e.printStackTrace();
            Warn.Mutant(e);
        }
    }
}
