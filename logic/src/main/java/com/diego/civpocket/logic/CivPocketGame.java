package com.diego.civpocket.logic;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 13/10/2014.
 * Controls the flow of the game
 */


public class CivPocketGame {

    final Scenario _scenario;
    final Empire _empire;
    GamePhase _gamePhase = GamePhase.StartGame;

    public interface UpkeepDuties {
        void carryOutUpkeep();
    }

    private List<UpkeepDuties> listeners = new ArrayList<UpkeepDuties>();
    public void addListener(UpkeepDuties listener) { listeners.add(listener);}
    public void triggerUpkeep()
    {
        for (UpkeepDuties upkeepduties : listeners)
            upkeepduties.carryOutUpkeep();
    }

    @Inject
    public CivPocketGame(Empire newEmpire,Scenario scenario)
    {
        _empire = newEmpire;
        _scenario = scenario;
        _scenario.setUp(_empire);

        addListener(_empire);
    }

    public EventCard drawEventCard() {
        return new EventCard();
    }

    public GamePhase getActualPhase(){
        return _gamePhase;
    }

    public void nextPhase() throws IllegalActionException {
        _gamePhase = _gamePhase.getNext();
        if ( _gamePhase == GamePhase.Growth) _empire.populationGrowth();
        else  if (_gamePhase == GamePhase.Upkeep) {
            triggerUpkeep();
        }
    }
    public Scenario getScenario() { return _scenario; }
    public Empire getEmpire() {
        return _empire;
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
