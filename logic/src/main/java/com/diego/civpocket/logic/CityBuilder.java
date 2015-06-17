package com.diego.civpocket.logic;

/**
 * Created by diego on 18/06/2015.
 */
public interface CityBuilder {
    City buildCity(Region region);
    boolean canBuildCity(Region testRegion);
}
