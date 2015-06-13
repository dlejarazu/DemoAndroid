package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.diego.civpocket.logic.CivPocketGame.GamePhase;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterBotonesPoblacionLogica extends MapPresenterTester {

	@Test
	public void testBtnsAddPoblacionActivoEnFaseCrecimiento() {
		//Given
		faseActual(GamePhase.Growth);
		//Then
		boolean isBtnAddPopActivo = sut.isAddPoblacionActivo();
		assertTrue(isBtnAddPopActivo);
	}
	@Test
	public void testBtnsRemPoblacionActivoEnFaseCrecimiento() {
		//Given
		faseActual(GamePhase.Growth);
		//Then
		boolean isBtnRemPopActivo = sut.isAddPoblacionActivo();
		assertTrue(isBtnRemPopActivo);
	}
	@Test
	public void testBtnsAddPoblacionInactivoEnFaseEventos() {
		//Given
		faseActual(GamePhase.Events);
		//Then
		boolean isBtnAddPopActivo = sut.isAddPoblacionActivo();
		assertFalse(isBtnAddPopActivo);
	}
	@Test
	public void testBtnsRemPoblacionInactivoEnFaseEventos() {
		//Given
		faseActual(GamePhase.Events);
		//Then
		boolean isBtnRemPopActivo = sut.isRemPoblacionActivo();
		assertFalse(isBtnRemPopActivo);
	}
}
