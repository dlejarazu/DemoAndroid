package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Imperio;
import com.diego.civpocket.logic.Region;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class GrowthPopulationTest {

    @InjectMocks Imperio sut;
    @Mock Region testRegion;
    @Mock Region testRegion2;

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

    @Test
    public void testEmpireGrowthInOneRegion(){
        //Given
        int initialPop = 3;
        for(int i = 0; i < initialPop; i++) sut.EnviarColono(testRegion);
        //When
        sut.populationGrowth();
        //Then
        int newPopulation = sut.populationAt(testRegion).size();
        assertEquals(initialPop+1,newPopulation);
    }
    @Test
    public void testEmpireGrowthInTwoRegion() {
        //Given
        int initialPopReg1 = 1;
        int initialPopReg2 = 4;
        for (int i = 0; i < initialPopReg1; i++) sut.EnviarColono(testRegion);
        for (int i = 0; i < initialPopReg2; i++) sut.EnviarColono(testRegion2);
        //When
        sut.populationGrowth();
        //Then
        int newPopulationReg1 = sut.populationAt(testRegion).size();
        int newPopulationReg2 = sut.populationAt(testRegion2).size();
        assertEquals(initialPopReg1 + 1, newPopulationReg1);
        assertEquals(initialPopReg2 + 1, newPopulationReg2);
    }

    @Test
    public void testEmpireGrowthNoPopulation() {
        //Given
        //Emtpy empire
        int minimumPopRule = 3;
        //When
        sut.populationGrowth();
        //Then
        int newPoolPop = sut.poolPopulation().size();
        assertEquals(minimumPopRule,newPoolPop);
    }

    @Test
    public void testEEnforceMinimumPopWhenGrowth() {

    }
}
