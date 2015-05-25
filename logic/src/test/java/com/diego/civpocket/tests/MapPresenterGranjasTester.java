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
import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.diego.civpocket.logic.Region.IllegalActionException;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterGranjasTester extends MapPresenterTester {
	
	@Before
	public void setUp() throws Exception {		
	}
	
	@Test
	public void testConstruirGranjaEnLaFaseDeAvances() throws IllegalActionException {
		//Given
		faseActual(GamePhase.Advances);
		permitirPuedeConstruirGranja();
		Region seleccionada = mockSelectRegion();
		//When
		sut.accionConstruirGranja();
		//Then
		Mockito.verify(testEmpire).buildFarm(seleccionada);
	}

	protected void permitirPuedeConstruirGranja() {
		Mockito.doReturn(true).when(testEmpire).canBuildFarmAt(Mockito.any(Region.class));
	}

	@Test
	public void testStatusRegionCambiaAlConstruirGranja() throws IllegalActionException {
		//Given
		faseActual(GamePhase.Advances);
		Region seleccionada = mockSelectRegion();
		String statusPrevio = sut.regionStatusToString(seleccionada.getName());
		seleccionada.add(Biomes.Farm);
		//When
		String statusDespues = sut.regionStatusToString(seleccionada.getName());
		//Then
		assertThat(statusPrevio,not(equalTo(statusDespues)));
	}
		
	@Test
	public void testBtnPlantarActivoEnLaFaseAvances(){
		//Given
		faseActual(GamePhase.Advances);
		permitirPuedeConstruirGranja();
		Region seleccionada = mockSelectRegion();
		sut.accionSelectRegion(seleccionada.getName());
		//Then
		boolean isBtnGranjaActivo = sut.isGranjasActivo();
		assertTrue(isBtnGranjaActivo);
	}	
	

}
