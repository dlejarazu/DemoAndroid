package com.diego.civpocket.tests.MapPresenter;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.CivPocketGame;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * Created by diego on 03/07/2015.
 */
public class MapPresenterButtonsAdvances extends MapPresenterTester {
    @Test
    public void testPurchaseAdvanceButtonInactive()
    {
        setGamePhase(CivPocketGame.GamePhase.StartGame);
        assertThat(sut.canPurchaseCartage(), is(false));
        setGamePhase(CivPocketGame.GamePhase.Growth);
        assertThat(sut.canPurchaseCartage(), is(false));
        setGamePhase(CivPocketGame.GamePhase.Events);
        assertThat(sut.canPurchaseCartage(), is(false));
        setGamePhase(CivPocketGame.GamePhase.Advances);
        assertThat(sut.canPurchaseCartage(), is(false));
        setGamePhase(CivPocketGame.GamePhase.Upkeep);
    }

    @Test
    public void testPurchaseAdvanceButtonActive()
    {
        setGamePhase(CivPocketGame.GamePhase.Advances);
        assertThat(sut.canPurchaseCartage(), is(false));
        sut.actionSelectRegion("testRegion");
        given(testLibrary.canResearchCartageFrom(testRegion)).willReturn(true);

        assertThat(sut.canPurchaseCartage(), is(true));
    }
}
