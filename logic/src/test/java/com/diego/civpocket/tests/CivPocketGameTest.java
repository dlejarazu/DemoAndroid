package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Scenario;
import com.google.inject.Guice;
import com.google.inject.Inject;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import  static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CivPocketGameTest {

    CivPocketGame sut;

    @Before
    public void setup(){
        sut = Guice.createInjector().getInstance(CivPocketGame.class);
    }

    @Test
    public void testGamePhasesSequence() throws IllegalActionException {
        assertEquals(CivPocketGame.GamePhase.StartGame,sut.getActualPhase());
        sut.nextPhase();
        assertEquals(CivPocketGame.GamePhase.Growth, sut.getActualPhase());
        sut.nextPhase();
        assertEquals(CivPocketGame.GamePhase.Events, sut.getActualPhase());
        sut.nextPhase();
        assertEquals(CivPocketGame.GamePhase.Advances, sut.getActualPhase());
        sut.nextPhase();
        assertEquals(CivPocketGame.GamePhase.Upkeep, sut.getActualPhase());
        sut.nextPhase();
        assertEquals(CivPocketGame.GamePhase.Growth, sut.getActualPhase());
    }
}
