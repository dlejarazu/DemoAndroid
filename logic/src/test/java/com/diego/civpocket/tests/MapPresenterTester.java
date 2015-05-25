package com.diego.civpocket.tests;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Escenario;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;
import com.diego.civpocket.logic.Region;

public class MapPresenterTester {

	@InjectMocks
	protected MapPresenter sut;
	@Mock
	protected Escenario testEscenario;
	@Mock
	protected Empire testEmpire;
	@Mock
	protected MapUpdater testView;
	@Mock
	protected CivPocketGame testGame;

	public MapPresenterTester() {
		
	}

	protected Region mockSelectRegion() {
		Region regSeleccionada = new Region("test");
		Mockito.doReturn(regSeleccionada).when(testEscenario).getRegionByName("test");
		sut.accionSelectRegion("test");
		return regSeleccionada;
	}

	protected void faseActual(CivPocketGame.GamePhase fase) {
		Mockito.doReturn(fase).when(testGame).getActualPhase();
	}

}