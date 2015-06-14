package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.IllegalActionException;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegionBiomesTest {

	@Parameterized.Parameters
	public static Collection<Object[]> supportValues() {
		return Arrays.asList(new Object[][] {
						{Biomes.Farm,1},
						{Biomes.Forest,1},
						{Biomes.Mountain,1},
						{Biomes.Volcano,1},
						{Biomes.Dessert,0}
				}
		);
	}

	@Parameterized.Parameter(value = 0)
	public Biomes biomeTested;
	@Parameterized.Parameter(value = 1)
	public int supportProvided;

	@Test
	public void testAddBiome() throws IllegalActionException {
		//Given
		Region sut = new Region("");
		//When
		sut.add(biomeTested);
		//Then
		boolean resultado = sut.has(biomeTested);
		assertTrue(resultado);
	}

	@Test
	public void testDecimate() throws IllegalActionException {
		//Given
		Region sut = new Region("");
		sut.add(biomeTested);
		//When
		sut.decimate(biomeTested);
		//Then
		boolean result = sut.has(biomeTested);
		assertFalse(result);
	}

	@Test (expected = IllegalActionException.class)
	public void testNoDuplicateBiomes() throws IllegalActionException {
		//Given
		Region sut = new Region("");
		sut.add(biomeTested);
		//When
		sut.add(biomeTested);
		//Then Exception thrown
	}

	@Test (expected = IllegalActionException.class)
	public void testThrowExceptionWhenDecimatingUnexistingBiome() throws IllegalActionException {
		//Given
		Region sut = new Region("");
		//When
		sut.decimate(biomeTested);
		//Then Exception thrown
	}

	@Test
	public void testSupportNumberBiomes() throws IllegalActionException {
		//Given
		Region sut = new Region("");
		//When
		sut.add(biomeTested);
		//Then
		int support = sut.support();
		assertEquals(supportProvided, support);
	}
}
