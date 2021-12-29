package Extras;

import Eventos.Muerte;
import Utilidades.Warn;
import org.bukkit.entity.Player;

import static Utilidades.Format.format;
import static Utilidades.Format.prefix;

public class EventosItems {

    public static void temperatura(Player p) {
      try{
          p.sendMessage(prefix(), format("&cTEMPERATURA TEST: TIENES 69 GRADOS LOL XD"));
      }catch (Exception e){
          e.printStackTrace();
          Warn.Mutant(e);
      }
    }
}
