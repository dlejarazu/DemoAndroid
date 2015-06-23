package com.diego.civpocket.logic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 13/10/2014.
 * Controls the flow of the game
 */
@Singleton
public class CivPocketGame {


    GamePhase _gamePhase = GamePhase.StartGame;

    public interface UpkeepDuties {
        void carryOutUpkeep();
    }

    public interface GrowthDuties {
        void carryOutGrowth();
    }

    private List<UpkeepDuties> listeners = new ArrayList<UpkeepDuties>();
    public void addUpkeepTask(UpkeepDuties listener) { listeners.add(listener);}
    public void triggerUpkeep()
    {
        for (UpkeepDuties upkeepduties : listeners)
            upkeepduties.carryOutUpkeep();
    }

    private List<GrowthDuties> listenersGrowth = new ArrayList<GrowthDuties>();
    public void addGrowthTask(GrowthDuties listener) { listenersGrowth.add(listener);}
    public void triggerGrowth()
    {
        for (GrowthDuties growthDuties : listenersGrowth)
            growthDuties.carryOutGrowth();
    }

    public EventCard drawEventCard() {
        return new EventCard();
    }

    public GamePhase getActualPhase(){
        return _gamePhase;
    }

    public void nextPhase() throws IllegalActionException {
        _gamePhase = _gamePhase.getNext();
        if ( _gamePhase == GamePhase.Growth) triggerGrowth();
        else  if (_gamePhase == GamePhase.Upkeep) {
            triggerUpkeep();
        }
    }

    public void setPhase(GamePhase newPhase) {
        _gamePhase = newPhase;
    }



    public enum GamePhase {
        StartGame,Growth, Events, Advances, Upkeep;
        public GamePhase getNext() {
            GamePhase nextPhase = values()[(ordinal() + 1) % values().length];

            if (nextPhase == StartGame) nextPhase = Growth;

            return nextPhase;
        }
    }
}
