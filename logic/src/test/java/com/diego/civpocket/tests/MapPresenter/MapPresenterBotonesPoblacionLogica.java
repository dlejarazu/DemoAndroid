package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.MapPresenter;
import com.google.inject.Guice;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MapPresenterBotonesPoblacionLogica extends MapPresenterTester {

	@Test
	public void testCheckButtonsStatusDuringPhases() {
		MapPresenter sut = Guice.createInjector(new TestPresenterModule())
				.getInstance(MapPresenter.class);
		//StartGame
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		sut.accionPasarSiguienteFase();
		//Growth
		assertThat(sut.isAddTribeActive(), is(true));
		assertThat(sut.isMoveTribeActive(), is(true));
		sut.accionPasarSiguienteFase();
		//Events
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		sut.accionPasarSiguienteFase();
		//Advances
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));
		sut.accionPasarSiguienteFase();
		//Upkeep
		assertThat(sut.isAddTribeActive(), is(false));
		assertThat(sut.isMoveTribeActive(), is(false));

	}
}
