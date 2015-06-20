package com.diego.civpocket.logic;

/**
 * Created by diego on 14/06/2015.
 * Used when some discrepancy with the rules is detected.
 * Means something was poorly programed and made the state of the game end in an unpredicteble state
 */
public class IllegalActionException extends RuntimeException {
    private static final long serialVersionUID = -5044052553914379459L;

    public IllegalActionException(String s) {
        super(s);
    }
}
