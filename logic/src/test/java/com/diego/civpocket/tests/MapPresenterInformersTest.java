package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.*;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterInformersTest extends MapPresenterTester {

	@Before
	public void setUp() throws Exception {
		
	}

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
