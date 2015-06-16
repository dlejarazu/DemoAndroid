package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;

import org.junit.Test;

import org.junit.Assert;
import org.mockito.Mockito;

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

        Assert.assertEquals("StartGame",sut.getFaseActual());
        player.sendSettler(lilliput);
        int numPhasesToAdvance = 5;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        Assert.assertEquals(0, player.totalPopulation());
        Assert.assertEquals("Growth",sut.getFaseActual());
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
        Assert.assertEquals(1, player.tribesAt(startRegion).size());
        sut.accionPasarSiguienteFase();
        Assert.assertEquals(2, player.tribesAt(startRegion).size());
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        sut.accionPasarSiguienteFase();
        Assert.assertEquals("Upkeep", sut.getFaseActual());
        Assert.assertEquals(1, player.tribesAt(startRegion).size());
    }

    @Test
    public void testEnforceSupportDuringUpkeep() throws IllegalActionException {
        Region lilliput = new Region("Lilliput");
        Region[] tinyEmpire = new Region[]{ lilliput };
        Scenario scenario =  new Scenario(tinyEmpire);
        Empire player = new Empire();
        CivPocketGame game = new CivPocketGame(player,scenario);
        //Given
        player.sendSettler(lilliput);
        lilliput.buildCity();
        game.setPhase(CivPocketGame.GamePhase.Advances);
        //When
        game.nextPhase();
        //Then
        Assert.assertEquals(0,lilliput.getCityLevel());
    }
}
