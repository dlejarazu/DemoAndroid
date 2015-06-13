package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Escenario;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import  static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameFlowTest  {

    @Mock Empire testEmpire;
    @Mock Escenario testScenario;
    @InjectMocks  CivPocketGame sut;

    @Test
    public void testIncreasePopulationWhenEnteringGrowth() {
        assertEquals(CivPocketGame.GamePhase.Growth,sut.getActualPhase());
         sut.nextPhase();   //Events
         sut.nextPhase();   //Advances
         sut.nextPhase();   //Upkeep
         Mockito.verify(testEmpire,never()).populationGrowth();
         sut.nextPhase();   //Growth
         Mockito.verify(testEmpire,times(1)).populationGrowth();
     }

    @Test
    public void testEnforcePopulationSupportWhenEnteringUpkeep() {
        assertEquals(CivPocketGame.GamePhase.Growth,sut.getActualPhase());
        sut.nextPhase();   //Events
        sut.nextPhase();   //Advances
        Mockito.verify(testEmpire,never()).adjustPopulation();
        sut.nextPhase();   //Upkeep
        verify(testEmpire,times(1)).adjustPopulation();
    }
}
