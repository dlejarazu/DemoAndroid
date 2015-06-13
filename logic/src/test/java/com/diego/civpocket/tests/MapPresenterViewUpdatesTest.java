package com.diego.civpocket.tests;

import org.junit.Test;
import org.mockito.Mockito;

public class MapPresenterViewUpdatesTest extends MapPresenterTester {

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
