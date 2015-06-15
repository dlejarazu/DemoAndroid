package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Tribe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.*;

public class MapPresenterMovementTest extends MapPresenterTester{

    Region origin;

    @Before
    public void setup(){
        //Given
        origin = addMockRegionToScenario("origin");
        faseActual(CivPocketGame.GamePhase.Growth);
    }

    @Test
    public void testMoveTribe() throws IllegalActionException {
        //Given
        Tribe nomad = new Tribe();
        given(testEmpire.tribesAt(origin)).willReturn(Arrays.asList(nomad));
        sut.actionSelectRegion(origin.getName());
        sut.actionMoveTribe();
        //When
        Region destination = addMockRegionToScenario("destination");
        sut.actionSelectRegion(destination.getName());
        //Then
        Assert.assertTrue(sut.isSelected(origin.getName()));
        Assert.assertEquals(destination, nomad.getLocation());
        Assert.assertTrue(sut.isSelectedAsDestination(destination.getName()));
    }

    @Test
    public void testDisableButtonsWithNoRegionSelected()
    {
        Assert.assertFalse(sut.isAddTribeActive());
        Assert.assertFalse(sut.isMoveTribeActive());
    }
    @Test
    public void testCancelMoveTribe() throws IllegalActionException {
        //Given
        sut.actionSelectRegion(origin.getName());
        sut.actionMoveTribe();
        //When
        sut.actionCancelMove();
        //Then
        Assert.assertTrue(sut.isSelected(origin.getName()));
        then(testEmpire).should(never()).reduceSettler(origin);

        Region another = addMockRegionToScenario("another");
        sut.actionSelectRegion(another.getName());
        Assert.assertTrue(sut.isSelected(another.getName()));
    }

/*
    @Test
    public void testEnableRemPopButtonWhenSelectingRegion() {
        Region destination = addMockRegionToScenario("destination");

        sut.actionSelectRegion(origin.getName());

        Assert.assertFalse(sut.isAddTribeActive());
        Assert.assertTrue(sut.isMoveTribeActive());

        sut.actionMoveTribe();

        Assert.assertFalse(sut.isAddTribeActive());
        Assert.assertFalse(sut.isMoveTribeActive());

        sut.actionSelectRegion(destination.getName());

        Assert.assertTrue(sut.isAddTribeActive());
        Assert.assertFalse(sut.isMoveTribeActive());

        sut.actionCancelMove();

        Assert.assertFalse(sut.isAddTribeActive());
        Assert.assertTrue(sut.isMoveTribeActive());
    }
*/
}
