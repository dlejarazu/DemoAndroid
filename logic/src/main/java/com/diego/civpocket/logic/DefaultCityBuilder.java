package com.diego.civpocket.logic;

/**
 * Created by diego on 18/06/2015.
 * Start conditions for building cities
 */
public class DefaultCityBuilder implements CityBuilder {

    Empire _owner;

    public DefaultCityBuilder(Empire newOwner)  {
        _owner = newOwner;
    }

    @Override
    public void buildCity(Region region) {
        if (canBuildCityAt(region)){
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            _owner.reduceSettler(region);
            _owner.add(new City(region));
        }
        else{
            throw new IllegalActionException("Requirements for building didn't met");
        }

    }

    @Override
    public boolean canBuildCityAt(Region region) {
        return _owner.tribesAt(region).size() >= 4 &&
                _owner.cityAt(region)==null;
    }
}
