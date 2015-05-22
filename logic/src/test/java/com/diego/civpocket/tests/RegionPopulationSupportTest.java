package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;

public class RegionPopulationSupportTest {

    @Test
    public void testCitySupport(){
        //Given
        Region sut = new Region("");
        sut.buildCity();
        //When
        int support = sut.support();
        //Then
        assertEquals(1,support);
    }

    @Test
    public void testSupportEmptyRegion(){
        //Given
        Region sut = new Region("");
        //When
        int support = sut.support();
        //Then
        assertEquals(0, support);
    }

    @Test
    public void testSupportBiomesPlusCity() throws Region.IllegalActionException {
        //Given
        Region sut = new Region("");
        sut.buildCity();
        sut.add(Biomes.Farm);
        //When
        int support = sut.support();
        //Then
        assertEquals(2,support);
    }
}
