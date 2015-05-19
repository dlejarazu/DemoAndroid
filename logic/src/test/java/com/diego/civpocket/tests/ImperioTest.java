package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Imperio;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.accionIlegalException;

@RunWith(MockitoJUnitRunner.class)
public class ImperioTest {

	@InjectMocks Imperio sut;
	@Mock Region testRegion;
	
	@Test
	public void testConstruirGranja() throws accionIlegalException {
		//Given
		Mockito.doReturn(true).when(testRegion).has(Biomes.Farm);
		//When
		sut.buildFarm(testRegion);
		//Then
		Mockito.verify(testRegion).add(Biomes.Farm);
	}
	@Test
	public void testBuildingFarmDecimatesForest() throws accionIlegalException {
		//Given
		Mockito.doReturn(true).when(testRegion).has(Biomes.Forest);
		//When
		sut.buildFarm(testRegion);
		//Then
		Mockito.verify(testRegion).decimate(Biomes.Forest);
	}

	@Test
	public void testSinBosqueNoSePuedeConstruir() throws accionIlegalException {
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
}
