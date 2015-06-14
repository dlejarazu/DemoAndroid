package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;

import org.junit.Assert;
import org.junit.Test;

public class MapPresenterMovementTest extends MapPresenterTester{

    @Test
    public void testSelectDestination(){
        //Given
        faseActual(CivPocketGame.GamePhase.Growth);
        sut.actionSelectRegion("origin");
        Assert.assertTrue(sut.isSelected("origin"));
        sut.accionRemPoblacion();
        //When
        sut.actionSelectRegion("destination");
        //Then
        Assert.assertTrue(sut.isSelected("origin"));
        Assert.assertTrue(sut.isSelectedAsDestination("destination"));
    }
}
