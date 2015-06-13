package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Escenario;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by diego on 12/06/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameFlowTest  {

    @Mock Empire testEmpire;
    @Mock Escenario testScenario;
    @InjectMocks  CivPocketGame sut;

    @Test
    public void testIncreasePopulationWhenEnteringGrowth()
     {
         Assert.assertEquals(CivPocketGame.GamePhase.Growth,sut.getActualPhase());
         sut.nextPhase();   //Events
         sut.nextPhase();   //Advances
         sut.nextPhase();   //Upkeep
         Mockito.verify(testEmpire,never()).populationGrowth();
         sut.nextPhase();   //Growth
         Mockito.verify(testEmpire,times(1)).populationGrowth();
     }
}
