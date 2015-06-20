package com.diego.civpocket.tests.MapPresenter;

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
		given(testGame.getEmpire()).willReturn(testEmpire);
		given(testGame.getScenario()).willReturn(testEscenario);
		sut = new MapPresenter(testGame, testView);
	}

	protected Region addMockRegionToScenario(String name) {
		Region regionSelected = new Region(name);
		Mockito.doReturn(regionSelected).when(testEscenario).getRegionByName(name);
		return regionSelected;
	}

	protected void faseActual(CivPocketGame.GamePhase fase) {
		given(testGame.getActualPhase()).willReturn(fase);
	}

}