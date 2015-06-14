package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GrowthPopulationTest {

    @InjectMocks
    Empire sut;
    @Mock Region testRegion;
    @Mock Region testRegion2;

    @Test
    public void testEmpireTotalPopulation() {
        //Given
        sut.sendSettler(testRegion);
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
        sut.sendSettler(testRegion);
        sut.sendSettler(testRegion);
        sut.reduceSettler(testRegion);
        //When
        int population = sut.totalPopulation();
        //Then
        assertEquals(1,population);
    }

    @Test
    public void testEmpireGrowthInOneRegion(){
        //Given
        int initialPop = 3;
        for(int i = 0; i < initialPop; i++) sut.sendSettler(testRegion);
        //When
        sut.populationGrowth();
        //Then
        int newPopulation = sut.tribesAt(testRegion).size();
        assertEquals(initialPop+1,newPopulation);
    }
    @Test
    public void testEmpireGrowthInTwoRegion() {
        //Given
        int initialPopReg1 = 1;
        int initialPopReg2 = 4;
        for (int i = 0; i < initialPopReg1; i++) sut.sendSettler(testRegion);
        for (int i = 0; i < initialPopReg2; i++) sut.sendSettler(testRegion2);
        //When
        sut.populationGrowth();
        //Then
        int newPopulationReg1 = sut.tribesAt(testRegion).size();
        int newPopulationReg2 = sut.tribesAt(testRegion2).size();
        assertEquals(initialPopReg1 + 1, newPopulationReg1);
        assertEquals(initialPopReg2 + 1, newPopulationReg2);
    }

    @Test
    public void testEnforceMinimumPopWhenGrowth() {
        //Given
        int initialPop = 1;
        for(int i = 0; i < initialPop; i++) sut.sendSettler(testRegion);
        //When
        sut.populationGrowth();
        //Then
        int newPopulation = sut.tribesAt(testRegion).size();
        assertEquals(initialPop+1,newPopulation);
        assertEquals(initialPop+1,sut.totalPopulation());
    }
}
