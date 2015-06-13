package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Escenario;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;

import org.junit.Test;

import org.junit.Assert;
import org.mockito.Mockito;


/**
 * Created by diego on 13/06/2015.
 */
public class IntegrationTests {
    MapUpdater mockUpdater = Mockito.mock(MapUpdater.class) ;
    CivPocketGame game = new CivPocketGame();
    Escenario scenario =  new Escenario(null);
    Empire player = new Empire();

    MapPresenter sut = new MapPresenter(
            game,scenario,player, mockUpdater);

    @Test
    public void testAdvanceSeveralTurnsEmptyEmpire()
    {
        int numPhasesToAdvance = 20;
        for(int i = 0; i < numPhasesToAdvance; i++) {
            sut.accionPasarSiguienteFase();
        }
        Assert.assertEquals(0, player.totalPopulation());
    }
}
