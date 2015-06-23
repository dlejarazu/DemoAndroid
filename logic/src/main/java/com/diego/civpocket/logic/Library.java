package com.diego.civpocket.logic;

/**
 * Created by diego on 23/06/2015.
 * Manages the purchase and management of advances
 */
public class Library {
    boolean hasCartage = false;

    public void addCartage() {
        hasCartage = true;
    }

    public boolean has(String nameAdvance) {
        return hasCartage;
    }
}
