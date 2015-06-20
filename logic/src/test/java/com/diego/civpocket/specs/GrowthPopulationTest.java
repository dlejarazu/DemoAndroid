package com.diego.civpocket.specs;

import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.tests.FakeCityBuilder;
import com.diego.civpocket.logic.IllegalActionException;
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
    public void testEmpireGrowthInOneRegion(){
        //Given
        int initialPop = 3;
        for(int i = 0; i < initialPop; i++) sut.sendSettlerTo(testRegion);
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
        for (int i = 0; i < initialPopReg1; i++) sut.sendSettlerTo(testRegion);
        for (int i = 0; i < initialPopReg2; i++) sut.sendSettlerTo(testRegion2);
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
        for(int i = 0; i < initialPop; i++) sut.sendSettlerTo(testRegion);
        //When
        sut.populationGrowth();
        //Then
        int newPopulation = sut.tribesAt(testRegion).size();
        assertEquals(initialPop+1,newPopulation);
        assertEquals(initialPop+1,sut.totalPopulation());
    }

    @Test
    public void testMinimumPopulationGrowthWithCity() throws IllegalActionException {
        //Given
        (new FakeCityBuilder(sut)).buildCity(testRegion);
        //When
        sut.populationGrowth();
        //Then
        assertEquals(3,sut.totalPopulation());
        assertEquals(3,sut.poolPopulation().size());
    }
}
