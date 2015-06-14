package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;

import org.junit.Test;

import org.junit.Assert;
import org.mockito.Mockito;

public class IntegrationTests {
    MapUpdater mockUpdater = Mockito.mock(MapUpdater.class) ;

    Region lilliput = new Region("Lilliput");
    Region[] tinyEmpire = new Region[]{ lilliput };
    Scenario scenario =  new Scenario(tinyEmpire);
    Empire player = new Empire();
    CivPocketGame game = new CivPocketGame(player);

    MapPresenter sut =
            new MapPresenter(game,scenario, mockUpdater);

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire() throws Region.IllegalActionException {
        lilliput.add(Biomes.Forest);
        player.sendSettler(lilliput);
        int numPhasesToAdvance = 4;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        Assert.assertEquals(1, player.totalPopulation());
    }
}
