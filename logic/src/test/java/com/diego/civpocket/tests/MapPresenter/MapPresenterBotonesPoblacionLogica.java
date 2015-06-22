package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.CivPocketGame;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Test;

public class MapPresenterBotonesPoblacionLogica extends MapPresenterTester {

	@Test
	public void testCheckButtonsStatusDuringPhases() {
		//StartGame
		sut.actionSelectRegion("testRegion");
		given(testGame.getActualPhase()).willReturn(CivPocketGame.GamePhase.StartGame);
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		//Growth
		given(testGame.getActualPhase()).willReturn(CivPocketGame.GamePhase.Growth);
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(true));
		//Events
		given(testGame.getActualPhase()).willReturn(CivPocketGame.GamePhase.Events);
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		//Advances
		given(testGame.getActualPhase()).willReturn(CivPocketGame.GamePhase.Advances);
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		//Upkeep
		given(testGame.getActualPhase()).willReturn(CivPocketGame.GamePhase.Upkeep);
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));

	}
}
