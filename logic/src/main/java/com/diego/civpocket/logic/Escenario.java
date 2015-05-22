package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 * several info related with the scenario
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
                if (reg.getName().equals(name)) return reg;
             }
        }
        return null;
    }
}
