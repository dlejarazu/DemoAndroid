package com.diego.civpocket.specs;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.City;
import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;

import org.junit.Test;

import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IntegrationTests {
    MapUpdater mockUpdater = Mockito.mock(MapUpdater.class) ;

    private void goToPhase(CivPocketGame game, CivPocketGame.GamePhase phase) throws IllegalActionException {
        while(game.getActualPhase() != phase) game.nextPhase();
    }

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Region[] tinyEmpire = new Region[]{ lilliput };
        Scenario scenario =  new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);

        MapPresenter sut =
                new MapPresenter(game, mockUpdater);

        assertEquals("StartGame", sut.getFaseActual());
        player.sendSettler(lilliput);
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
        Scenario scenario =  new Scenario("A New World");
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);

        MapPresenter sut =
                new MapPresenter(game, mockUpdater);

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
        Region[] tinyEmpire = new Region[]{ lilliput };
        Scenario scenario =  new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);
        //Given
        player.buildCity(lilliput);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        assertThat(player.cityAt(lilliput), nullValue());
    }

    @Test
    public void testEnforceSupportDuringUpkeepWithFarm() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Region[] tinyEmpire = new Region[]{ lilliput };
        Scenario scenario =  new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);
        //Given
        player.buildCity(lilliput);
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
        Region[] tinyEmpire = new Region[]{ lilliput };
        Scenario scenario =  new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);
        //Given
        player.sendSettler(lilliput);
        player.sendSettler(lilliput);
        player.sendSettler(lilliput);
        player.buildCity(lilliput);
        lilliput.add(Biomes.Mountain);
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        assertThat(player.tribesAt(lilliput).size(),is(2));
        assertThat(player.cityAt(lilliput), nullValue());
    }
}
