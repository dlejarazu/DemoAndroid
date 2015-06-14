package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Escenario;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import  static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameFlowTest  {

    @Mock Empire testEmpire;
    @Mock Escenario testScenario;
    @InjectMocks  CivPocketGame sut;

    @Test
    public void testIncreasePopulationWhenEnteringGrowth() {
        assertEquals(CivPocketGame.GamePhase.StartGame, sut.getActualPhase());
        sut.nextPhase();   //Growth
        then(testEmpire).should(times(1)).populationGrowth();
        sut.nextPhase();   //Events
        sut.nextPhase();   //Advances
        sut.nextPhase();   //Upkeep
        then(testEmpire).should(times(1)).populationGrowth();
     }

    @Test
    public void testEnforcePopulationSupportWhenEnteringUpkeep() {
        assertEquals(CivPocketGame.GamePhase.StartGame,sut.getActualPhase());
        sut.nextPhase();   //Growth
        sut.nextPhase();   //Events
        sut.nextPhase();   //Advances
        then(testEmpire).should(never()).adjustPopulation();
        sut.nextPhase();   //Upkeep
        then(testEmpire).should(times(1)).adjustPopulation();
    }
}
