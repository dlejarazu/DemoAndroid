package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.DefaultCityBuilder;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

/**
 * Created by diego on 16/06/2015.
 * Tests for the city building logic and support
 */
@RunWith(MockitoJUnitRunner.class)
public class CitiesTest {

    Empire empire = new Empire();
    CityBuilder builder = new DefaultCityBuilder(empire);
    @Mock
    Region testRegion;

    @Test
    public void testNumberOfCitiesBuilt()
    {
        //Given
        empire.sendSettlerTo(testRegion,4);
        //When
        builder.buildCity(testRegion);
        //Then
        assertThat(empire.getNumCities(),is(1));
    }

    @Test
    public void testBuildCity() {
        //Given
        empire.sendSettlerTo(testRegion,4);
        //When
        builder.buildCity(testRegion);
        //Then
        assertEquals(0, empire.populationAt(testRegion));
        assertThat(empire.cityAt(testRegion),notNullValue());
    }


    @Test
    public void testNoCitySupport()  {
        //Given
        empire.sendSettlerTo(testRegion,4);
        given(testRegion.has(Biomes.Farm)).willReturn(false);
        builder.buildCity(testRegion);
        //When
        empire.supportCities();
        //Then
        assertThat(empire.cityAt(testRegion), nullValue());
    }

    @Test
    public void testCitySupport()  {
        //Given
        empire.sendSettlerTo(testRegion,4);
        builder.buildCity(testRegion);
        given(testRegion.has(Biomes.Farm)).willReturn(true);
        //When
        empire.supportCities();
        //Then
        assertThat(empire.cityAt(testRegion), notNullValue());
    }
}
