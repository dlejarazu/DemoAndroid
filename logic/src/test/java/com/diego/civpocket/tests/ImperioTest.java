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
		Mockito.doReturn(true).when(testRegion).hasForest();
		//When
		sut.ConstruirGranja(testRegion);
		//Then
		Mockito.verify(testRegion).addGranja();
	}
	@Test
	public void testBuildingFarmDecimatesForest() throws accionIlegalException {
		//Given
		Mockito.doReturn(true).when(testRegion).hasForest();
		//When
		sut.ConstruirGranja(testRegion);
		//Then
		Mockito.verify(testRegion).decimate(Biomes.Forest);
	}

	@Test
	public void testSinBosqueNoSePuedeConstruir() throws accionIlegalException {
		//Given
		Mockito.doReturn(false).when(testRegion).hasFarm();
		//When
		boolean resultado = sut.puedeGranjaEn(testRegion);
		//Then
		assertFalse(resultado);
	}

	@Test
	public void testSiYaHayGranjaNoSePuedeConstruir(){
		//Given
		Mockito.doReturn(true).when(testRegion).hasFarm();
		//When
		boolean resultado = sut.puedeGranjaEn(testRegion);
		//Then
		assertFalse(resultado);
	}
}
