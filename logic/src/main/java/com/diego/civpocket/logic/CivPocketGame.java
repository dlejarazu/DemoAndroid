package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 * Controls the flow of the game
 */


public class CivPocketGame {

    Empire _player;
    GamePhase _gamePhase = GamePhase.StartGame;

    public CivPocketGame(Empire newEmpire)
    {
        _player = newEmpire;
    }

    public EventCard drawEventCard() {
        return new EventCard();
    }

    public GamePhase getActualPhase(){
        return _gamePhase;
    }

    public void nextPhase(){
        _gamePhase = _gamePhase.getNext();
        if ( _gamePhase == GamePhase.Growth) _player.populationGrowth();
        else  if (_gamePhase == GamePhase.Upkeep) _player.adjustPopulation();
    }

    public Empire getPlayer() {
        return _player;
    }

    public enum GamePhase {
        StartGame,Growth, Events, Advances, Upkeep;
        public GamePhase getNext() {
            return values()[(ordinal() + 1) % values().length];
        }
    }
}
