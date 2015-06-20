package com.diego.civpocket.logic;

/**
 * Created by diego on 18/06/2015.
 * Interface for the class in charge of the logic for creating a new city in a empire
 * build city adds it to the owner empire and canBuildCityAt checks the conditions to do so
 */
public interface CityBuilder {
    void buildCity(Region region);
    boolean canBuildCityAt(Region testRegion);
}
