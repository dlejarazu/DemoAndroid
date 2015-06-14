package com.diego.civpocket.tests;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;
import com.diego.civpocket.logic.Region;

import static org.mockito.BDDMockito.*;

public class MapPresenterTester {

	@Mock protected Scenario testEscenario;
	@Mock protected Empire testEmpire;
	@Mock protected MapUpdater testView;
	@Mock protected CivPocketGame testGame;
	protected MapPresenter sut;

	public MapPresenterTester(){
		MockitoAnnotations.initMocks(this);
		given(testGame.getPlayer()).willReturn(testEmpire);
		sut = new MapPresenter(testGame, testEscenario, testView);
	}

	protected Region mockSelectRegion() {
		Region regSeleccionada = new Region("test");
		Mockito.doReturn(regSeleccionada).when(testEscenario).getRegionByName("test");
		sut.actionSelectRegion("test");
		return regSeleccionada;
	}

	protected void faseActual(CivPocketGame.GamePhase fase) {
		given(testGame.getActualPhase()).willReturn(fase);
	}

}