package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.accionIlegalException;

public class RegionTest {

	@Test
	public void testPlantarGranja() throws accionIlegalException {
		//Given
		Region sut = new Region("");
		//When
		sut.addGranja();
		//Then
		boolean resultado = sut.hasFarm();
		assertTrue(resultado);
	}
	
	@Test (expected = accionIlegalException.class)
	public void testExcepcionAlReplantarGranja() throws accionIlegalException {
		//Given 
		Region sut = new Region("");
		sut.addGranja();
		//When
		sut.addGranja();
		//Then exception raises
	}

	@Test
	public void testGrowthForest()
	{
		//Given
		Region sut = new Region("");
		//When
		sut.growForest();
		//Then
		boolean result = sut.hasForest();
		assertTrue(result);
	}

	@Test
	public void testDecimate()
	{
		//Given
		Region sut = new Region("");
		sut.growForest();
		//When
		sut.decimate(Biomes.Forest);
		//Then
		boolean result = sut.hasForest();
		assertFalse(result);
	}
}
