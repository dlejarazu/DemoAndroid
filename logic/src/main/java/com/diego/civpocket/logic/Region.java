package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

public class Region {

    List<Biomes> _biomesInRegion;
    private String _name;

    public Region(String newRegion)
    {
        _biomesInRegion = new ArrayList<>();
        setName(newRegion);
    }

    public String getName() {
        return _name;
    }

    private void setName(String _name) {
        this._name = _name;
    }

	public boolean has(Biomes biome) {
		return _biomesInRegion.contains(biome);
	}

    public void add(Biomes biome) throws IllegalActionException {
        if (_biomesInRegion.contains(biome)){
            throw new IllegalActionException("Tried to add same Biome twice");
        }
        _biomesInRegion.add(biome);
    }

    public void decimate(Biomes decimatedBiome) throws IllegalActionException {
        if (!_biomesInRegion.contains(decimatedBiome)){
            throw new IllegalActionException("Tried to decimate unexisting Biome");
        }
        _biomesInRegion.remove(decimatedBiome);
    }

    public int support() {
        int biomesSupport = 0;
        for(Biomes b : _biomesInRegion)
        {
            if (b != Biomes.Dessert) biomesSupport++;
        }

        return biomesSupport;
    }
}
