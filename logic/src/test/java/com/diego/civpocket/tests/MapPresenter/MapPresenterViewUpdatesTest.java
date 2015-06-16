package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.Region;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MapPresenterViewUpdatesTest extends MapPresenterTester {

	Region testRegion;

	@Before
	public void setup()
	{
		testRegion = addMockRegionToScenario("testRegion");
	}

	private void verifyUpdatesEnLaVista()
	{
		Mockito.verify(testView,Mockito.atLeastOnce()).UpdateControls();
		Mockito.verify(testView,Mockito.atLeastOnce()).UpdateMap();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnAddPoblacion() {
		//Given
		sut.actionSelectRegion(testRegion.getName());
		//When
		sut.actionCancelMove();
		//Then
		verifyUpdatesEnLaVista();
	}
	@Test
	public void testActualizarVistaAlLlamarBtnRemPoblacion() {
		//Given
		sut.actionSelectRegion(testRegion.getName());
		//When
		sut.actionMoveTribe();
		//Then
		verifyUpdatesEnLaVista();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnConstruirCiudad() {
		//Given
		sut.actionSelectRegion(testRegion.getName());
		//When
		sut.actionMoveTribe();
		//Then
		verifyUpdatesEnLaVista();
	}
	
	@Test
	public void testActualizarVistaAlLlamarBtnSiguienteFase() {
		//Given
		sut.actionSelectRegion(testRegion.getName());
		//When
		sut.accionPasarSiguienteFase();
		//Then
		verifyUpdatesEnLaVista();
	}
}
