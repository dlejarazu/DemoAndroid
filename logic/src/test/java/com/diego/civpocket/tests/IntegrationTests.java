package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Escenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;

import org.junit.Test;

import org.junit.Assert;
import org.mockito.Mockito;

//TODO: basic test for first turn should end with 1 pop in region with 1 support
/*
public class IntegrationTests {
    MapUpdater mockUpdater = Mockito.mock(MapUpdater.class) ;

    Escenario scenario =  new Escenario(null);
    Empire player = new Empire();
    CivPocketGame game = new CivPocketGame(player);

    MapPresenter sut = new MapPresenter(
            game,scenario, mockUpdater);

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire()
    {
        int numPhasesToAdvance = 4;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        Assert.assertEquals(0, player.totalPopulation());
    }
}
*/