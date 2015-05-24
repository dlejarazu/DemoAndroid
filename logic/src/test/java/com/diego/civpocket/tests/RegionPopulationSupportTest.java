package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;

public class RegionPopulationSupportTest {

    private final Region sut = new Region("");

    @Test
    public void testSupportFromCityLevel(){
        //Given
        sut.buildCity();
        //When
        int support = sut.support();
        //Then
        assertEquals(1,support);
    }

    @Test
    public void testSupportEmptyRegion(){
        //Given
        //Empty Region
        //When
        int support = sut.support();
        //Then
        assertEquals(0, support);
    }

    @Test
    public void testSupportBiomesPlusCity() throws Region.IllegalActionException {
        //Given
        sut.buildCity();
        sut.add(Biomes.Farm);
        //When
        int support = sut.support();
        //Then
        assertEquals(2,support);
    }

    @Test
    public void testNoCitySupport(){
        //Given
        sut.buildCity();
        //When
        sut.supportCity();
        //Then
        boolean hasCity = (sut.getCityLevel() > 0);
        assertFalse(hasCity);
    }

    @Test
    public void testCitySupport() throws Region.IllegalActionException{
        //Given
        sut.buildCity();
        sut.add(Biomes.Farm);
        //When
        sut.supportCity();
        //Then
        boolean hasCity = (sut.getCityLevel() > 0);
        assertTrue(hasCity);
    }
}
