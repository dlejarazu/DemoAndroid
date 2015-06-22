package com.diego.civpocket.tests.MapPresenter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by diego on 20/06/2015.
 * TDD for refactoring the dependency injection with the Guice framework
 */
public class MapPresenterInjectionTest extends MapPresenterTester{

    @Test
    public void testClassesAreInjectedProperlyIntoMapPresenter(){
        assertThat(sut, notNullValue());
        sut.actionSelectRegion("testRegion");
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        assertThat(sut.isConstruirCiudaPossible(),is(true));
    }

}
