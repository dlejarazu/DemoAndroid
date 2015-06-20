package com.diego.civpocket.tests;

import com.diego.civpocket.logic.City;
import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;

/**
 * Created by diego on 18/06/2015.
 * Fake builder for testing
 */
public class FakeCityBuilder implements CityBuilder {
    Empire _owner;

    public FakeCityBuilder(Empire newOwner)  {
        _owner = newOwner;
    }

    @Override
    public void buildCity(Region region) {
        _owner.add(new City(region));
    }

    @Override
    public boolean canBuildCityAt(Region testRegion) {
        return true;
    }
}
