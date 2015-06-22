package com.diego.civpocket.specs;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.FirstScenario;
import com.diego.civpocket.tests.FakeCityBuilder;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import org.junit.Test;

import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IntegrationTests {

    class simpleTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(MapUpdater.class).toInstance( Mockito.mock(MapUpdater.class));
        }
    }

    private void goToPhase(CivPocketGame game, CivPocketGame.GamePhase phase) throws IllegalActionException {
        while(game.getActualPhase() != phase) game.nextPhase();
    }

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame();

        MapPresenter sut = Guice.createInjector(new simpleTestModule())
                .getInstance(MapPresenter.class);

        assertEquals("StartGame", sut.getFaseActual());
        player.sendSettlerTo(lilliput);
        int numPhasesToAdvance = 5;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        assertEquals(0, player.totalPopulation());
        assertEquals("Growth", sut.getFaseActual());
    }

    @Test
    public void testFirstScenario()
    {
        Scenario scenario =  new FirstScenario();
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame();

        MapPresenter sut = Guice.createInjector(new simpleTestModule())
                .getInstance(MapPresenter.class);

        Region startRegion = scenario.getRegionByName("5");
        assertEquals(1, player.tribesAt(startRegion).size());
        sut.accionPasarSiguienteFase();
        assertEquals(2, player.tribesAt(startRegion).size());
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        assertEquals("Upkeep", sut.getFaseActual());
        assertEquals(1, player.tribesAt(startRegion).size());
    }

    @Test
    public void testEnforceSupportDuringUpkeepWithoutFarmNoCity() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame();
        //Given
        (new FakeCityBuilder(player)).buildCity(lilliput);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        assertThat(player.cityAt(lilliput), nullValue());
    }

    @Test
    public void testEnforceSupportDuringUpkeepWithFarm() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame();
        //Given
        (new FakeCityBuilder(player)).buildCity(lilliput);
        lilliput.add(Biomes.Farm);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        assertThat(player.cityAt(lilliput),notNullValue());
    }

    @Test
    public void test_Order_for_Upkeep_first_population_then_city_support() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame();
        //Given
        player.sendSettlerTo(lilliput,3);
        (new FakeCityBuilder(player)).buildCity(lilliput);
        lilliput.add(Biomes.Mountain);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        assertThat(player.tribesAt(lilliput).size(), is(2));
        assertThat(player.cityAt(lilliput), nullValue());
    }
/*
    @Test
    public void test_support_city_from_different_region_with_Cartage() throws IllegalActionException {
        Region urbanites = new Region("urbanites");
        Region countryside = new Region("countryside");
        Region[] tinyEmpire = new Region[]{urbanites,countryside};
        Scenario scenario = new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player, scenario);
        player.setBuilder(new FakeCityBuilder());

        MapPresenter sut =
                new MapPresenter(game, mockUpdater);

        //Given
        countryside.add(Biomes.Farm);
        player.buildCity(urbanites);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        player.addAdvance("Cartage");
        //When
        sut.accionPasarSiguienteFase();
        //Then
        assertThat(player.cityAt(urbanites), notNullValue());
    }
    */
}
