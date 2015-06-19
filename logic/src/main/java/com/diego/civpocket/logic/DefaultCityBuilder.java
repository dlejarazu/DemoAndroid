package com.diego.civpocket.logic;

/**
 * Created by diego on 18/06/2015.
 */
public class DefaultCityBuilder implements CityBuilder {

    Empire _owner;

    DefaultCityBuilder(Empire newOwner)  {
        _owner = newOwner;
    }

    @Override
    public City buildCity(Region region) {
        try {
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            return new City(region);
        }
        catch (IllegalActionException ex){
            throw new RuntimeException("Requirements for building didn't met",ex);
        }

    }

    @Override
    public boolean canBuildCity(Region region) {
        return !region.has(Biomes.Farm) &&
                region.has(Biomes.Forest) &&
                _owner.tribesAt(region).size() >= 2;
    }
}
