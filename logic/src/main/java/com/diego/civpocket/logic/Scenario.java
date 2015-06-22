package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 * several info related with the scenario
 */
public class Scenario {

    protected Region[] _map = null;
    protected String _name = "";

    public Scenario(Region[] nuevoMapa){
        _map = nuevoMapa;
    }
    public Region[] getMap(){
        return _map;
    }
    public Region getRegionByName(String name)
    {
        if (_map != null){
            for(Region reg : _map){
                if (reg.getName().equals(name)) return reg;
             }
        }
        return null;
    }

    public void setUp(Empire empire){ }

}
