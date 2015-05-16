package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

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
		boolean resultado = sut.tieneGranja();
		assertTrue(resultado);
	}
	
	@Test (expected = accionIlegalException.class)
	public void testExcepcionAlReplantarGranja() throws accionIlegalException {
		//Given 
		Region sut = new Region("");
		sut.addGranja();
		//When
		sut.addGranja();		
	}
}
