package Listeners;


import tlldos.tll2.TLL2;

import java.util.HashMap;

public class Dificultad{
    TLL2 instance;
    HashMap<String, Boolean> dificultadDia = new HashMap<>();

    public Dificultad(TLL2 instance){
        dificultadDia.put("dia6", true);
        dificultadDia.put("dia12", false);
        dificultadDia.put("dia18", false);
        dificultadDia.put("dia24", false);
        dificultadDia.put("dia30", false);
        dificultadDia.put("dia36", false);
    }
}
