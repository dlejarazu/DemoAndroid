package com.diego.civpocket.logic;

/**
 * Created by diego on 5/18/2015.
 * Manage individuals of an empire
 */
public class Tribe {
    Region _location;

    public void moveTo(Region destination){
        _location = destination;
    }

    public Region get_location(){
        return _location;
    }
}
