package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.diego.civpocket.logic.CivPocketGame.FaseJuego;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterBotonesPoblacionLogica extends MapPresenterTester {
	
	@Before
	public void setUp() throws Exception {
		//sut = new MapPresenter(testGame, testEscenario, testImperio, testView);
	}

	@Test
	public void testBtnsAddPoblacionActivoEnFaseCrecimiento() {
		//Given
		faseActual(FaseJuego.Crecimiento);
		//Then
		boolean isBtnAddPopActivo = sut.isAddPoblacionActivo();
		assertTrue(isBtnAddPopActivo);
	}
	@Test
	public void testBtnsRemPoblacionActivoEnFaseCrecimiento() {
		//Given
		faseActual(FaseJuego.Crecimiento);
		//Then
		boolean isBtnRemPopActivo = sut.isAddPoblacionActivo();
		assertTrue(isBtnRemPopActivo);
	}
	@Test
	public void testBtnsAddPoblacionInactivoEnFaseEventos() {
		//Given
		faseActual(FaseJuego.Evento);
		//Then
		boolean isBtnAddPopActivo = sut.isAddPoblacionActivo();
		assertFalse(isBtnAddPopActivo);
	}
	@Test
	public void testBtnsRemPoblacionInactivoEnFaseEventos() {
		//Given
		faseActual(FaseJuego.Evento);
		//Then
		boolean isBtnRemPopActivo = sut.isRemPoblacionActivo();
		assertFalse(isBtnRemPopActivo);
	}
}
