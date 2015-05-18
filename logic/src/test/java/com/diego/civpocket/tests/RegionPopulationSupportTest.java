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
        sut.support();

    }

}
