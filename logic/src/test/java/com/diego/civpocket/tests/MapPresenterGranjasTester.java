package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;

import com.diego.civpocket.logic.*;
import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.diego.civpocket.logic.IllegalActionException;

public class MapPresenterGranjasTester extends MapPresenterTester {

	Region testRegion;
	@Before
	public void setup()
	{
		testRegion = addMockRegionToScenario("testRegion");
	}

	@Test
	public void testBuildFarmDuringAdvancePhase() throws IllegalActionException {
		//Given
		faseActual(GamePhase.Advances);
		allowBuldingFarms();
		sut.actionSelectRegion(testRegion.getName());
		//When
		sut.accionConstruirGranja();
		//Then
		Mockito.verify(testEmpire).buildFarm(testRegion);
	}

	protected void allowBuldingFarms() {
		Mockito.doReturn(true).when(testEmpire).canBuildFarmAt(Mockito.any(Region.class));
	}

	@Test
	public void testStatusRegionCambiaAlConstruirGranja() throws IllegalActionException {
		//Given
		faseActual(GamePhase.Advances);
		String statusPrevio = sut.regionStatusToString(testRegion.getName());
		testRegion.add(Biomes.Farm);
		//When
		String statusDespues = sut.regionStatusToString(testRegion.getName());
		//Then
		assertThat(statusPrevio,not(equalTo(statusDespues)));
	}
		
	@Test
	public void testBtnPlantarActivoEnLaFaseAvances(){
		//Given
		faseActual(GamePhase.Advances);
		allowBuldingFarms();
		sut.actionSelectRegion(testRegion.getName());
		//Then
		boolean isBtnGranjaActivo = sut.isGranjasActivo();
		assertTrue(isBtnGranjaActivo);
	}	
	

}
