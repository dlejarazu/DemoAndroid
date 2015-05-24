package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 * Controls the flow of the game
 */


public class CivPocketGame {

    public enum GamePhase {
        Growth, Events, Advances, Upkeep;
        public GamePhase getNext() {
            return values()[(ordinal() + 1) % values().length];
        }
    }

    GamePhase _gamePhase = GamePhase.Growth;

    public GamePhase getActualPhase(){
        return _gamePhase;
    }

    public void nextPhase(){
        _gamePhase = _gamePhase.getNext();
    }
}
