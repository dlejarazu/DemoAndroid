package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.diego.civpocket.logic.*;

public class MapPresenterInformersTest extends MapPresenterTester {

	@Test
	public void testIsSelectedCorrectoDevuelveTrue() {
		//Given
		Region testRegion = new Region("test");
		Mockito.doReturn(testRegion).when(testEscenario).getRegionByName("test");
		sut.accionSelectRegion("test");
		//When
		boolean resultado = sut.isSelected("test");
		assertTrue(resultado);
	}
	
	@Test
	public void testIsSelectedConRegionIncorrectaDevuelveFalse() {
		//Given
		Region testRegion = new Region("test");
		Mockito.doReturn(testRegion).when(testEscenario).getRegionByName("test");
		sut.accionSelectRegion("test");
		//When
		boolean resultado = sut.isSelected("other");
		assertFalse(resultado);
	}

}
