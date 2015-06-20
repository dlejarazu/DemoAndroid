package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
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
 */
public class MapPresenterInjectionTest {

    @Test
    public void testClassesAreInjectedProperlyIntoMapPresenter(){
        Injector sut = Guice.createInjector(
                new AbstractModule(){
                    @Override
                    public void configure() {
                        bind(MapUpdater.class).toInstance(mock(MapUpdater.class));
                    }
                    @Provides Scenario provideTestScenario()
                    {
                        return new Scenario(
                                new Region[]{
                                        new Region("testTegion")});
                    }
                });

       assertThat(sut.getInstance(MapPresenter.class), notNullValue());
    }

}
