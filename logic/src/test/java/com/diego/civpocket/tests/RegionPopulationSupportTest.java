package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import com.diego.civpocket.logic.Region;
/**
 * Created by diego on 5/17/2015.
 */

public class RegionPopulationSupportTest {

    @Test
    public void testCitySupport(){
        //Given
        Region sut = new Region("");
        sut.ConstruirCiudad();
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
}
