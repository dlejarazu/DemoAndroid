package com.diego.civpocket.specs;

import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Library;
import com.diego.civpocket.logic.MapUpdater;
import com.diego.civpocket.tests.FakeCityBuilder;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.MapPresenter;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class IntegrationTests {

    @Bind Scenario testScenario = Mockito.spy(new Scenario(
            new Region[]{(new  Region("Lilliput"))}));
    @Bind Empire testEmpire = Mockito.spy(new Empire());
    @Bind CivPocketGame testGame = Mockito.spy(new CivPocketGame());
    @Bind CityBuilder fakeBuilder = Mockito.spy(new FakeCityBuilder(testEmpire));
    @Bind Library testLibrary = Mockito.spy(new Library(testEmpire));
    @Bind MapUpdater updater = mock(MapUpdater.class);

    @Inject MapPresenter sut;

    @Before
    public void setup(){
        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
    }

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire() throws IllegalActionException
    {
        assertThat(sut.getFaseActual(), is("StartGame"));
        testEmpire.sendSettlerTo(testScenario.getRegionByName("Lilliput"));
        int numPhasesToAdvance = 5;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        assertThat(testEmpire.totalPopulation(),is(0));
        assertThat(sut.getFaseActual(),is("Growth"));
    }

    @Test
    public void testCityBuilding()
    {
        sut.actionSelectRegion("Lilliput");
        sut.actionBuildCity();

        assertThat(
                testEmpire.cityAt(testScenario.getRegionByName("Lilliput")),
                is(notNullValue()));
    }

    @Test
    public void testPurchaseAdvanceCartageFromCity()
    {
        assertThat(testLibrary.hasCartage(),is(false));

        sut.actionSelectRegion("Lilliput");
        sut.actionBuildCity();
        sut.actionPurchaseAdvance("Cartage");

        assertThat(testLibrary.hasCartage(),is(true));
    }
}
