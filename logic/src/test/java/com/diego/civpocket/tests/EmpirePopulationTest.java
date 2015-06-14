package com.diego.civpocket.tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.IllegalActionException;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmpirePopulationTest {
    Empire sut = new Empire();
    @Mock Region testRegion;

    @Test
    public void testCreateAndDestriyTribes()
    {
        assertPopulation(0,testRegion);
        sut.sendSettler(testRegion);
        assertPopulation(1,testRegion);
        assertEquals(1, sut.tribesAt(testRegion).size());
        sut.reduceSettler(testRegion);
        assertPopulation(0, testRegion);
        assertEquals(0, sut.tribesAt(testRegion).size());
    }


    @Test
    public void testDecimateRegionWithNoTribe(){
        //Given
        //Emtpy region
        //When
        boolean success = sut.reduceSettler(testRegion);
        //Then
        assertFalse(success);
    }

    @Test
    public void testAdjustPopulationNoSupport(){
        //Given
        //empty region
        sut.sendSettler(testRegion);
        //When
        sut.adjustPopulation();
        //Then
        assertPopulation(0,testRegion);
    }

    private void assertPopulation(int expectedPop,Region asserted) {
        int newPop = sut.tribesAt(asserted).size();
        assertEquals(expectedPop, newPop);
    }

    @Test
    public void testAdjustPopulationSupportEqualsPopulation() throws IllegalActionException{
        //Given
        int support = 1;
        Mockito.doReturn(support).when(testRegion).support();
        for (int i = 0; i < support; i++) sut.sendSettler(testRegion);
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
        for (int i = 0; i < population; i++) sut.sendSettler(testRegion);
        //When
        sut.adjustPopulation();
        //Then
        assertPopulation(support,testRegion);
    }
}
