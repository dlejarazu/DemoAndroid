package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Region;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.BDDMockito.*;

public class MapPresenterMovementTest extends MapPresenterTester{

    @Before
    public void setup(){
        //Given
        faseActual(CivPocketGame.GamePhase.Growth);
        sut.actionSelectRegion("origin");
        sut.accionRemPoblacion();
    }

    @Test
    public void testSelectDestination(){
        //When
        sut.actionSelectRegion("destination");
        //Then
        Assert.assertTrue(sut.isSelected("origin"));
        Assert.assertTrue(sut.isSelectedAsDestination("destination"));
    }

    @Test
    public void testSelectingOriginAsTargetToReturnTribes(){
        //Given
        sut.actionSelectRegion("destination");
        sut.actionSelectRegion("origin");
        //When
        sut.accionAddPoblacion();
        //Then
        Assert.assertTrue(sut.isSelected("origin"));
        Assert.assertTrue(sut.isSelectedAsDestination("origin"));
    }

    @Test
    public void testReturnFromMoveMode(){
        //When
        sut.accionAddPoblacion();
        sut.actionSelectRegion("another");
        //Then
        Assert.assertTrue(sut.isSelected("another"));
        Assert.assertFalse(sut.isSelectedAsDestination("origin"));
        Assert.assertFalse(sut.isSelectedAsDestination("another"));
    }
}
