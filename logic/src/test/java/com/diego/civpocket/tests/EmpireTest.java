package com.diego.civpocket.tests;

import static org.junit.Assert.*;

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

@RunWith(MockitoJUnitRunner.class)
public class EmpireTest {

	@InjectMocks Empire sut;
	@Mock Region testRegion;
	
	@Test
	public void testConstruirGranja() throws IllegalActionException {
		//Given
		Mockito.doReturn(true).when(testRegion).has(Biomes.Farm);
		//When
		sut.buildFarm(testRegion);
		//Then
		Mockito.verify(testRegion).add(Biomes.Farm);
	}
	@Test
	public void testBuildingFarmDecimatesForest() throws IllegalActionException {
		//Given
		Mockito.doReturn(true).when(testRegion).has(Biomes.Forest);
		//When
		sut.buildFarm(testRegion);
		//Then
		Mockito.verify(testRegion).decimate(Biomes.Forest);
	}

	@Test
	public void testSinBosqueNoSePuedeConstruir() throws IllegalActionException {
		//Given
		Mockito.doReturn(false).when(testRegion).has(Biomes.Farm);
		//When
		boolean resultado = sut.canBuildFarmAt(testRegion);
		//Then
		assertFalse(resultado);
	}

	@Test
	public void testSiYaHayGranjaNoSePuedeConstruir(){
		//Given
		Mockito.doReturn(true).when(testRegion).has(Biomes.Farm);
		//When
		boolean resultado = sut.canBuildFarmAt(testRegion);
		//Then
		assertFalse(resultado);
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
        int newPop = sut.populationAt(asserted).size();
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
