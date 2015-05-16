package com.diego.civpocket.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.*;

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterViewUpdatesTest extends MapPresenterTester {

	@Before
	public void setUp() throws Exception {
		sut = new MapPresenter(testGame, testEscenario, testImperio, testView);		
	}
	
	private void verifyUpdatesEnLaVista()
	{
		Mockito.verify(testView,Mockito.atLeastOnce()).UpdateControls();
		Mockito.verify(testView,Mockito.atLeastOnce()).UpdateMap();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnAddPoblacion() {
		//Given
		mockSelectRegion();
		//When
		sut.accionAddPoblacion();
		//Then
		verifyUpdatesEnLaVista();
	}
	@Test
	public void testActualizarVistaAlLlamarBtnRemPoblacion() {
		//Given
		mockSelectRegion();
		//When
		sut.accionRemPoblacion();
		//Then
		verifyUpdatesEnLaVista();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnConstruirCiudad() {
		//Given
		mockSelectRegion();
		//When
		sut.accionRemPoblacion();
		//Then
		verifyUpdatesEnLaVista();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnSiguienteFase() {
		//Given
		mockSelectRegion();
		//When
		sut.accionPasarSiguienteFase();
		//Then
		verifyUpdatesEnLaVista();
	}
	
}
