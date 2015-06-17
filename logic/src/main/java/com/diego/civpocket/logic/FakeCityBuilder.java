package com.diego.civpocket.logic;

/**
 * Created by diego on 18/06/2015.
 */
public class FakeCityBuilder implements CityBuilder {
    @Override
    public City buildCity(Region region) {
        return new City(region);
    }

    @Override
    public boolean canBuildCity(Region testRegion) {
        return true;
    }
}
