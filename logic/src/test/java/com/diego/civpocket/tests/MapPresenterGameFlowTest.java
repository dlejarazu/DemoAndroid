package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.MapPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by diego on 12/06/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MapPresenterGameFlowTest extends MapPresenterTester {

    @Test
    public void testIncreasePopulationWhenEnteringGrowth()
     {
         //Given
         Mockito.doReturn(CivPocketGame.GamePhase.Growth).when(testGame).getActualPhase();
         //When
         sut.accionPasarSiguienteFase();
         //then
         verify(testEmpire, Mockito.times(1)).populationGrowth();
         //When

     }

    @Test
    public void testNotIncreasePopulationWhenEnteringGrowth()
    {
        //Given
        Mockito.doReturn(CivPocketGame.GamePhase.Upkeep).when(testGame).getActualPhase();
        //When
        sut.accionPasarSiguienteFase();
        //then
        verify(testEmpire, Mockito.never()).populationGrowth();
    }

    @Test
    public void testPopulationSupportDuringUpkeep()
    {
        //Given
        for(CivPocketGame.GamePhase phase : CivPocketGame.GamePhase.values()) {
            Mockito.doReturn(phase).when(testGame).getActualPhase();
            sut.accionPasarSiguienteFase();
        }
        //then
        verify(testEmpire, Mockito.times(1)).adjustPopulation();
    }

    @Test
    public void testPopulationSupportOnlyDuringUpkeep()
    {
        for(CivPocketGame.GamePhase phase : CivPocketGame.GamePhase.values()) {
            Mockito.doReturn(phase).when(testGame).getActualPhase();
            sut.accionPasarSiguienteFase();
        }
        //then
        verify(testEmpire, Mockito.times(1)).adjustPopulation();
    }

}
