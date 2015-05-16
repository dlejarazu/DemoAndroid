package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 */
public class Escenario {

   protected Region[] regionesMapa = null;

    public Escenario(Region[] nuevoMapa){
        regionesMapa = nuevoMapa;
    }

    public Region getRegionByName(String name)
    {
        if (regionesMapa != null){
            for(Region reg : regionesMapa){
                if (reg.getNombre().equals(name)) return reg;
             }
        }
        return null;
    }
}
