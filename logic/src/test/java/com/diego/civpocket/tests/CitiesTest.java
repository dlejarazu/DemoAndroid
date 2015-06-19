package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.City;
import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.FakeCityBuilder;
import com.diego.civpocket.logic.IllegalActionException;
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
 */
@RunWith(MockitoJUnitRunner.class)
public class CitiesTest {

    Empire sut = new Empire();
    @Mock
    Region testRegion;

    @Test
    public void testNumberOfCitiesBuilt() throws IllegalActionException
    {
        //Given
        int popReg = 4;
        for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
        //When
        sut.buildCity(testRegion);
        //Then
        assertThat(sut.getNumCities(),is(1));
    }

    @Test
    public void testBuildCity()throws IllegalActionException {
        //Given
        int popReg = 4;
        for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
        //When
        sut.buildCity(testRegion);
        //Then
        assertEquals(0, sut.tribesAt(testRegion).size());
        assertThat(sut.cityAt(testRegion),notNullValue());
    }


    @Test
    public void testNoCitySupport() throws IllegalActionException {
        //Given
        int popReg = 4;
        for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
        given(testRegion.has(Biomes.Farm)).willReturn(false);
        sut.buildCity(testRegion);
        //When
        sut.supportCities();
        //Then
        assertThat(sut.cityAt(testRegion), nullValue());
    }

    @Test
    public void testCitySupport() throws IllegalActionException {
        //Given
        int popReg = 4;
        for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
        sut.buildCity(testRegion);
        given(testRegion.has(Biomes.Farm)).willReturn(true);
        //When
        sut.supportCities();
        //Then
        assertThat(sut.cityAt(testRegion), notNullValue());
    }

    @Test
    public void testFakeBuilder() throws IllegalActionException {
        CityBuilder fake = new FakeCityBuilder();
        City city = fake.buildCity(testRegion);
        assertThat(city, notNullValue());
    }

    @Test
     public void testEmpireUsingBuilder() throws IllegalActionException {
        CityBuilder fake = mock(CityBuilder.class);
        sut.setBuilder(fake);
        sut.buildCity(testRegion);
        then(fake).should(times(1)).buildCity(testRegion);
    }

    @Test
    public void testFakeBuilderShouldAlwaysBeAbleToBuild() throws IllegalActionException{
        CityBuilder fake = new FakeCityBuilder();
        sut.setBuilder(fake);
        assertThat(fake.canBuildCity(testRegion),is(true));
        sut.buildCity(testRegion);
        assertThat(sut.cityAt(testRegion), notNullValue());
    }
}
