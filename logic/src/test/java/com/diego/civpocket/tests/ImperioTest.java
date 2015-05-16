package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Imperio;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Region.accionIlegalException;

@RunWith(MockitoJUnitRunner.class)
public class ImperioTest {

	@InjectMocks Imperio sut;
	@Mock Region testRegion;
	
	@Test
	public void testConstruirGranja() throws accionIlegalException {
		//When
		sut.ConstruirGranja(testRegion);
		//Then
		Mockito.verify(testRegion).addGranja();
	}
	@Test
	public void testSiYaHayGranjaNoSePuedeConstruir(){
		//Given
		Mockito.doReturn(true).when(testRegion).tieneGranja();
		//When
		boolean resultado = sut.puedeGranjaEn(testRegion);
		//Then
		assertFalse(resultado);
	}
}
