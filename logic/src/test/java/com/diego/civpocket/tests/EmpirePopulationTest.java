package com.diego.civpocket.tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.IllegalActionException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmpirePopulationTest {
    Empire sut = new Empire();
    @Mock Region testRegion;
    @Mock Region testRegion2;

    @Test
    public void testCreateAndDestriyTribes() throws IllegalActionException {
        assertPopulation(0,testRegion);
        sut.sendSettlerTo(testRegion);
        assertPopulation(1,testRegion);
        assertEquals(1, sut.populationAt(testRegion));
        sut.reduceSettler(testRegion);
        assertPopulation(0, testRegion);
        assertEquals(0,  sut.populationAt(testRegion));
    }


    @Test (expected = IllegalActionException.class)
    public void testDecimateRegionWithNoTribe() throws IllegalActionException{
        //Given
        //Emtpy region
        //When
       sut.reduceSettler(testRegion);
        //Then throws exception
    }

    @Test
    public void testAdjustPopulationNoSupport() throws IllegalActionException {
        //Given
        //empty region
        sut.sendSettlerTo(testRegion);
        //When
        sut.adjustPopulation();
        //Then
        assertPopulation(0,testRegion);
    }

    @Test
    public void testEmpireZeroInitialPop() {
        //Given
        //When
        int population = sut.totalPopulation();
        //Then
        assertEquals(0, population);
    }

    private void assertPopulation(int expectedPop,Region asserted) {
        int newPop =  sut.populationAt(testRegion);
        assertEquals(expectedPop, newPop);
    }

    @Test
    public void testAdjustPopulationSupportEqualsPopulation() throws IllegalActionException{
        //Given
        int support = 1;
        Mockito.doReturn(support).when(testRegion).support();
        for (int i = 0; i < support; i++) sut.sendSettlerTo(testRegion);
        //When
        sut.adjustPopulation();
        //Then
        assertPopulation(support, testRegion);
    }

    @Test
    public void testAdjustPopulationSupportLowThanPopulation() throws IllegalActionException{
        //Given
        int support = 2;
        int population = 5;
        Mockito.doReturn(support).when(testRegion).support();
        for (int i = 0; i < population; i++) sut.sendSettlerTo(testRegion);
        //When
        sut.adjustPopulation();
        //Then
        assertPopulation(support,testRegion);
    }
}
