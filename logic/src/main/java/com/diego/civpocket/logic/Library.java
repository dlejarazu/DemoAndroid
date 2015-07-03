package com.diego.civpocket.logic;

import com.google.inject.Inject;

/**
 * Created by diego on 23/06/2015.
 * takes care of the purchase and management of advances
 */
public class Library {
    private final Empire _empire;
    boolean hasCartage = false;

    @Inject
    public Library(Empire empire){
        _empire = empire;
    }

    public void acquireCartage(Region labRegion) {
        if (_empire.cityAt(labRegion)==null)
        {
            throw new IllegalActionException("Advances cannot be purchased in regions without cities");
        }
        hasCartage = true;
    }

    public boolean hasCartage() {
        return hasCartage;
    }

    public boolean canResearchCartageFrom(Region researchFrom) {
        return researchFrom.has(Biomes.Mountain) &&
                _empire.cityAt(researchFrom)!=null &&
                _empire.tribesAt(researchFrom) != null &&
                _empire.tribesAt(researchFrom).size()>=2;
    }
}
