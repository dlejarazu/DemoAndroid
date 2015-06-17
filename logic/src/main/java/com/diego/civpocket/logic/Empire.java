package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empire implements CivPocketGame.UpkeepDuties{

    List<Tribe> _population = new ArrayList<>();

    public void sendSettler(Region destination)
    {
        Tribe settler = new Tribe();
        _population.add(settler);
        settler.moveTo(destination);
    }

    public void reduceSettler(Region destination) throws IllegalActionException {
        List<Tribe> localPopulation = this.tribesAt(destination);
        if (localPopulation.isEmpty())
            throw new IllegalActionException("No tribes to decimate at destination");
        else {
            _population.remove(localPopulation.get(0));
        }
    }

    public List<Tribe> tribesAt(Region local) {
        List<Tribe> localPopulation = new ArrayList<>();
        for (Tribe tribe: _population) {
            if (tribe.getLocation() == local) {
                localPopulation.add(tribe);
            }
        }
        return localPopulation;
    }

    public boolean canBuildCityAt(Region region){
        return tribesAt(region).size()>=4
        	&& region.getCityLevel()==0;
    }

    public void buildCity(Region region) {
        if (canBuildCityAt(region)) {
            region.buildCity();
            for(Tribe builders : tribesAt(region).subList(0, 4)){
                _population.remove(builders);
            }
        }
    }

	public boolean canBuildFarmAt(Region region) {

		return !region.has(Biomes.Farm) &&
                region.has(Biomes.Forest) &&
                tribesAt(region).size() >= 2;
	}

	public boolean buildFarm(Region selected) throws IllegalActionException {
        if(canBuildFarmAt(selected)) {
            selected.add(Biomes.Farm);
            selected.decimate(Biomes.Forest);
            reduceSettler(selected);
            reduceSettler(selected);
            return true;
        }
        else
            return false;
	}

    public int totalPopulation() {
        return _population.size();
    }

    public void populationGrowth() {
        Map<Region, Integer> census = getEmpireCensus();

        for (Region populated : census.keySet()) {
            this.sendSettler(populated);
        }
//      enforceMinimumPop();
    }

    private Map<Region, Integer> getEmpireCensus() {
        Map<Region,Integer> countPop = new HashMap<>();
        for(Tribe tribe : _population){
            if (!tribe.isAtPool()) {
                Region tribeLoc = tribe.getLocation();
                int oldValue = 0;
                if (countPop.containsKey(tribeLoc)) {
                    oldValue = countPop.get(tribeLoc);
                }
                countPop.put(tribeLoc, oldValue + 1);
            }
        }
        return countPop;
    }

    public List<Tribe> poolPopulation() {
        List<Tribe> localPopulation = new ArrayList<>();
        for (Tribe tribe: _population) {
            if (tribe.isAtPool()) {
                localPopulation.add(tribe);
            }
        }
        return localPopulation;
    }

    private void enforceMinimumPop() {
        while(totalPopulation() < 3){
            _population.add(new Tribe());
        }
    }

    public void adjustPopulation() throws IllegalActionException {
        Map<Region, Integer>  census = this.getEmpireCensus();
        for (Region regSettled : census.keySet()){
            int tribesToReduce = census.get(regSettled) - regSettled.support();
            for (int i = 0;i < tribesToReduce; i++) this.reduceSettler(regSettled);
        }
    }

    @Override
    public void carryOutUpkeep() {
        try {
            adjustPopulation();
        } catch (IllegalActionException ex) {
            throw new RuntimeException(ex);
        }
    }
}
