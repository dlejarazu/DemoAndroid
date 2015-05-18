package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Imperio;
import com.diego.civpocket.logic.Region;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class GrowthPopulationTest {

    @InjectMocks Imperio sut;
    @Mock Region testRegion;

    @Test
    public void testEmpireTotalPopulation() {
        //Given
        sut.EnviarColono(testRegion);
        //When
        int population = sut.totalPopulation();
        //Then
        assertEquals(1,population);
    }

    @Test
    public void testEmpireZeroInitialPop() {
        //Given
        //When
        int population = sut.totalPopulation();
        //Then
        assertEquals(0,population);
    }

    @Test
    public void testEmpireReducePopulation() {
        //Given
        sut.EnviarColono(testRegion);
        sut.EnviarColono(testRegion);
        sut.ReclamarColono(testRegion);
        //When
        int population = sut.totalPopulation();
        //Then
        assertEquals(1,population);
    }
}
