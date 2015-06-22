package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Tribe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.*;

public class MapPresenterMovementTest extends MapPresenterTester{

    Region origin;

    @Before
    public void setup(){
        //Given
        super.setup();
        origin = addRegionToScenario("origin");
        setGamePhase(CivPocketGame.GamePhase.Growth);
    }

    @Test
    public void testMoveTribe() throws IllegalActionException {
        //Given
        Tribe nomad = new Tribe();
        given(testEmpire.tribesAt(origin)).willReturn(Collections.singletonList(nomad));
        sut.actionSelectRegion(origin.getName());
        sut.actionMoveTribe();
        //When
        Region destination = addRegionToScenario("destination");
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

        Region another = addRegionToScenario("another");
        sut.actionSelectRegion(another.getName());
        Assert.assertTrue(sut.isSelected(another.getName()));
    }

    @Test
    public void testDisableNextPhaseWhenMoving() /*throws IllegalActionException*/ {
        //Given
        sut.actionSelectRegion(origin.getName());
        sut.actionMoveTribe();
        //When
        boolean isActive = sut.isNextPhaseActive();
        //Then
        Assert.assertFalse(isActive);
    }
}
