package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.tests.FakeCityBuilder;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by diego on 20/06/2015.
 * TDD for refactoring the dependency injection with the Guice framework
 */
public class MapPresenterInjectionTest {

    @Test
    public void testClassesAreInjectedProperlyIntoMapPresenter(){
        Injector sut = Guice.createInjector(
                new AbstractModule(){
                    @Override
                    public void configure() {
                        bind(MapUpdater.class).toInstance(mock(MapUpdater.class));
                        //bind(CityBuilder.class).to(FakeCityBuilder.class);
                    }
                    @Provides CityBuilder provideFakeBuilder(Empire empire){
                        return new FakeCityBuilder(empire);
                    }

                    @Provides Scenario provideTestScenario()
                    {
                        return new Scenario(
                                new Region[]{
                                        new Region("testRegion")});
                    }
                });
        MapPresenter presenter = sut.getInstance(MapPresenter.class);
        assertThat(presenter, notNullValue());
        presenter.actionSelectRegion("testRegion");
        presenter.accionPasarSiguienteFase();
        presenter.accionPasarSiguienteFase();
        presenter.accionPasarSiguienteFase();
        assertThat(presenter.isConstruirCiudaPossible(),is(true));
    }

}
