package com.diego.civpocket.logic;

/**
 * Created by diego on 17/06/2015.
 */
public class City {

    private final Region _region;

    public City(Region region){
        _region = region;
    }

    public int level() {
        return 1;
    }

    public Region getLocation(){
        return _region;
    }
}
