package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

public class Region implements CivPocketGame.UpkeepDuties{

    List<Biomes> _biomesInRegion;
    private String _name;
    private int _cityLevel = 0;

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

    public void buildCity() {
        _cityLevel = 1;
    }

    public int getCityLevel() {
        return _cityLevel;
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
        int totalSupport = 0;
        for(Biomes b : _biomesInRegion)
        {
            if (b != Biomes.Dessert) totalSupport++;
        }

        totalSupport += _cityLevel;
        return totalSupport;
    }

    public void supportCity() {
        if (!this.has(Biomes.Farm)) _cityLevel = 0;
    }

    @Override
    public void carryOutUpkeep() {
        supportCity();
    }
}
