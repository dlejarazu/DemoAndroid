package com.diego.civpocket.tests.MapPresenter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MapPresenterInformersTest extends MapPresenterTester {

	@Test
	public void testIsSelectedCorrectoDevuelveTrue() {
		//When
		sut.actionSelectRegion("testRegion");
		//Then
		assertThat(sut.isSelected("testRegion"), is(true));
	}
	
	@Test
	public void testIsSelectedConRegionIncorrectaDevuelveFalse() {
		//When
		sut.actionSelectRegion("testRegion");
		//When
		assertThat(sut.isSelected("other"), is(false));
	}

}
