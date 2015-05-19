package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;

import com.diego.civpocket.logic.*;
import com.diego.civpocket.logic.CivPocketGame.FaseJuego;
import com.diego.civpocket.logic.Region.accionIlegalException;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterGranjasTester extends MapPresenterTester {
	
	@Before
	public void setUp() throws Exception {		
	}
	
	@Test
	public void testConstruirGranjaEnLaFaseDeAvances() throws accionIlegalException {
		//Given
		faseActual(FaseJuego.Avances);
		permitirPuedeConstruirGranja();
		Region seleccionada = mockSelectRegion();
		//When
		sut.accionConstruirGranja();
		//Then
		Mockito.verify(testImperio).buildFarm(seleccionada);
	}

	protected void permitirPuedeConstruirGranja() {
		Mockito.doReturn(true).when(testImperio).canBuildFarmAt(Mockito.any(Region.class));
	}

	@Test
	public void testStatusRegionCambiaAlConstruirGranja() throws accionIlegalException {
		//Given
		faseActual(FaseJuego.Avances);
		Region seleccionada = mockSelectRegion();
		String statusPrevio = sut.regionStatusToString(seleccionada.getNombre());
		seleccionada.add(Biomes.Farm);
		//When
		String statusDespues = sut.regionStatusToString(seleccionada.getNombre());
		//Then
		assertThat(statusPrevio,not(equalTo(statusDespues)));
	}
		
	@Test
	public void testBtnPlantarActivoEnLaFaseAvances(){
		//Given
		faseActual(FaseJuego.Avances);
		permitirPuedeConstruirGranja();
		Region seleccionada = mockSelectRegion();
		sut.accionSelectRegion(seleccionada.getNombre());
		//Then
		boolean isBtnGranjaActivo = sut.isGranjasActivo();
		assertTrue(isBtnGranjaActivo);
	}	
	

}
