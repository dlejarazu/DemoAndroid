package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.IllegalActionException;

import org.junit.rules.ExpectedException;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmpireTest {

	Empire sut = new Empire();
	@Mock Region testRegion;
	
	@Test
	public void testEmpireBuildsFarm_If_ItHasForestAndTwoTribes() throws IllegalActionException {
		given(testRegion.has(Biomes.Farm)).willReturn(false);
		given(testRegion.has(Biomes.Forest)).willReturn(true);

		sut.sendSettler(testRegion);
		sut.sendSettler(testRegion);

		//When
		boolean success = sut.buildFarm(testRegion);

        assertTrue(success);
		then(testRegion).should(times(1)).add(Biomes.Farm);
		then(testRegion).should(times(1)).decimate(Biomes.Forest);
        assertEquals(0,sut.tribesAt(testRegion).size());
	}

    @Test
    public void testEmpireCannotBuildFarmByDefault() throws IllegalActionException
    {
        boolean success = sut.buildFarm(testRegion);
        assertFalse(success);
        then(testRegion).should(never()).add(Biomes.Farm);
    }

	@Test
	public void testSinBosqueNoSePuedeConstruir() throws IllegalActionException {
		//Given
		given(testRegion.has(Biomes.Forest)).willReturn(false);
		//When
		boolean result = sut.canBuildFarmAt(testRegion);
		//Then
		assertFalse(result);
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
	public void testBuildCity(){
		//Given
		int popReg = 4;
		for (int i = 0; i < popReg; i++) sut.sendSettler(testRegion);
		//When
		sut.buildCity(testRegion);
		//Then
        assertPopulation(0, testRegion);
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
