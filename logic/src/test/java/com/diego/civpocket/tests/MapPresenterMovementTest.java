package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Region;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.BDDMockito.*;

public class MapPresenterMovementTest extends MapPresenterTester{

    Region origin;

    @Before
    public void setup(){
        //Given
        origin = addMockRegionToScenario("origin");
        faseActual(CivPocketGame.GamePhase.Growth);
        sut.actionSelectRegion(origin.getName());
        sut.accionRemPoblacion();
    }

    @Test
    public void testSelectDestination(){
        //When
        Region destination = addMockRegionToScenario("destination");
        sut.actionSelectRegion(destination.getName());
        //Then
        Assert.assertTrue(sut.isSelected(origin.getName()));
        Assert.assertTrue(sut.isSelectedAsDestination(destination.getName()));
    }

    @Test
    public void testSelectingOriginAsTargetToReturnTribes(){
        //Given
        Region destination = addMockRegionToScenario("destination");
        sut.actionSelectRegion(destination.getName());
        sut.actionSelectRegion(origin.getName());
        //When
        sut.accionAddPoblacion();
        //Then
        Assert.assertTrue(sut.isSelected(origin.getName()));
        Assert.assertTrue(sut.isSelectedAsDestination(origin.getName()));
    }

    @Test
    public void testReturnFromMoveMode(){
        //Given
        Region another = addMockRegionToScenario("another");
        //When
        sut.accionAddPoblacion();
        sut.actionSelectRegion(another.getName());
        //Then
        Assert.assertTrue(sut.isSelected(another.getName()));
        Assert.assertFalse(sut.isSelectedAsDestination(origin.getName()));
        Assert.assertFalse(sut.isSelectedAsDestination(another.getName()));
    }
}
