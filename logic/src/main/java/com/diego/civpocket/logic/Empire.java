package com.diego.civpocket.logic;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class Empire implements CivPocketGame.UpkeepDuties, CivPocketGame.GrowthDuties{

    //TODO: divide responsabilities for population growth, city support and empire track
    List<Tribe> _population = new ArrayList<>();
    private Map<Region,City> _cities = new HashMap<>();

    public void sendSettlerTo(Region destination)
    {
        Tribe settler = new Tribe();
        _population.add(settler);
        settler.moveTo(destination);
    }

    public void sendSettlerTo(Region destination, int number) {
        for(int i=0; i < number; i++) sendSettlerTo(destination);
    }

    public void reduceSettler(Region destination)
    {
       Tribe tribeToReduce = this.getTribeFrom(destination);
        if (tribeToReduce == null) {
            throw new IllegalActionException("No tribes to decimate at destination");
        }
        else {
            _population.remove(tribeToReduce);
        }
    }

    public int populationAt(Region local)
    {
        List<Tribe> localPopulation = new ArrayList<>();
        for (Tribe tribe: _population) {
            if (tribe.getLocation() == local) {
                localPopulation.add(tribe);
            }
        }
        return localPopulation.size();
    }

    public Tribe getTribeFrom(Region region)
    {
        for (Tribe tribe: _population) {
            if (tribe.getLocation() == region) {
                return tribe;
            }
        }
        return null;
    }

    public int totalPopulation() {
        return _population.size();
    }

    public void populationGrowth() {
        Map<Region, Integer> census = getEmpireCensus();

        for (Region populated : census.keySet()) {
            this.sendSettlerTo(populated);
        }
        enforceMinimumPop();
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
        if (_cities.size()>0) {
            while (totalPopulation() < 3) {
                _population.add(new Tribe());
            }
        }
    }

    public void adjustPopulation(){
        Map<Region, Integer>  census = this.getEmpireCensus();
        for (Region regSettled : census.keySet()){
            int citySupport =
                    (cityAt(regSettled) != null)
                    ?_cities.get(regSettled).level()
                    :0;

            int support = regSettled.support() + citySupport;
            int tribesToReduce = census.get(regSettled) - support;
            for (int i = 0;i < tribesToReduce; i++) this.reduceSettler(regSettled);
        }
    }

    public boolean canBuildFarmAt(Region region) {
        return !region.has(Biomes.Farm) &&
                region.has(Biomes.Forest) &&
                populationAt(region) >= 2;
    }

    public boolean buildFarm(Region selected){
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

    public int getNumCities(){
        return _cities.size();
    }

    public void add(City newCity)
    {
        //TODO: remove duplication. getLocation y Map to cities. Same info in different places
        _cities.put(newCity.getLocation(),newCity);
    }

    public City cityAt(Region region) {
        return _cities.get(region);
    }

    public void supportCities() {
        for(Region cityLocation : _cities.keySet()){
            if (!cityLocation.has(Biomes.Farm)) {
                _cities.remove(cityLocation);
            }
        }
    }


    @Override
    public void carryOutGrowth()
    {
        populationGrowth();
    }

    @Override
    public void carryOutUpkeep()
    {
        adjustPopulation();
        supportCities();
    }
}
