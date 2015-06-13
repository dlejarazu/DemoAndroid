package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;

import com.diego.civpocket.logic.*;
import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.diego.civpocket.logic.Region.IllegalActionException;

public class MapPresenterGranjasTester extends MapPresenterTester {

	@Test
	public void testBuildFarmDuringAdvancePhase() throws IllegalActionException {
		//Given
		faseActual(GamePhase.Advances);
		allowBuldingFarms();
		Region selected = mockSelectRegion();
		//When
		sut.accionConstruirGranja();
		//Then
		Mockito.verify(testEmpire).buildFarm(selected);
	}

	protected void allowBuldingFarms() {
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
		allowBuldingFarms();
		Region seleccionada = mockSelectRegion();
		sut.accionSelectRegion(seleccionada.getName());
		//Then
		boolean isBtnGranjaActivo = sut.isGranjasActivo();
		assertTrue(isBtnGranjaActivo);
	}	
	

}
