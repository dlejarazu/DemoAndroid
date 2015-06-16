package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by diego on 16/06/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CitiesTest {

    Empire sut = new Empire();
    @Mock
    Region testRegion;

    @Test
    public void testBuildCity(){
        //Given
        int popReg = 4;
        for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
        //When
        sut.buildCity(testRegion);
        //Then
        assertEquals(0, sut.tribesAt(testRegion).size());
    }


    @Test
    public void testNoCitySupport(){
        //Given
         Region sut = new Region("");
        sut.buildCity();
        //When
        sut.supportCity();
        //Then
        boolean hasCity = (sut.getCityLevel() > 0);
        assertFalse(hasCity);
    }

    @Test
    public void testCitySupport() throws IllegalActionException {
        //Given
        Region sut = new Region("");
        sut.buildCity();
        sut.add(Biomes.Farm);
        //When
        sut.supportCity();
        //Then
        boolean hasCity = (sut.getCityLevel() > 0);
        assertTrue(hasCity);
    }
}
