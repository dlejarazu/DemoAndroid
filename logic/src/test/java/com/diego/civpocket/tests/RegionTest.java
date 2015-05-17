package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.accionIlegalException;

public class RegionTest {

	@Test
	public void testAddBiome() throws accionIlegalException {
		//Given
		Region sut = new Region("");
		//When
		sut.add(Biomes.Farm);
		//Then
		boolean resultado = sut.has(Biomes.Farm);
		assertTrue(resultado);
	}

	@Test
	public void testDecimate() throws accionIlegalException {
		//Given
		Region sut = new Region("");
		sut.add(Biomes.Farm);
		//When
		sut.decimate(Biomes.Farm);
		//Then
		boolean result = sut.has(Biomes.Farm);
		assertFalse(result);
	}

	@Test (expected = accionIlegalException.class)
	public void testNoDuplicateBiomes() throws accionIlegalException {
		//Given
		Region sut = new Region("");
		sut.add(Biomes.Farm);
		//When
		sut.add(Biomes.Farm);
		//Then Exception thrown
	}

}
