package com.diego.civpocket.logic;

import com.diego.civpocket.logic.Region.accionIlegalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Imperio {

    List<Tribe> _population = new ArrayList<Tribe>();

    public void EnviarColono(Region destination)
    {
        Tribe colono = new Tribe();
        _population.add(colono);
        colono.moveTo(destination);
    }

    public void ReclamarColono(Region destination)
    {
        List<Tribe> localPopulation = this.populationAt(destination);
        _population.remove(localPopulation.get(0));
    }

    public List<Tribe> populationAt(Region local) {
        List<Tribe> localPopulation = new ArrayList<Tribe>();
        for (Tribe tribe: _population) {
            if (tribe.getLocation() == local) {
                localPopulation.add(tribe);
            }
        }
        return localPopulation;
    }

    public boolean canBuildCityAt(Region region){
        return populationAt(region).size()>=4
        	&& region.getNivelCiudad()==0;
    }

    public void buildCity(Region region) {
        if (canBuildCityAt(region)) {
            region.ConstruirCiudad();
            for(Tribe builders : populationAt(region).subList(0, 3)){
                _population.remove(builders);
            }
        }
    }

	public boolean canBuildFarmAt(Region region) {

		return !region.has(Biomes.Farm) && region.has(Biomes.Forest);
	}

	public void buildFarm(Region seleccionada) throws accionIlegalException {
		seleccionada.add(Biomes.Farm );
        seleccionada.decimate(Biomes.Forest);
	}

    public int totalPopulation() {
        return _population.size();
    }

    public void populationGrowth() {
        Map<Region, Integer> census = getEmpireCensus();

        for (Region populated : census.keySet()){
            this.EnviarColono(populated);
        }

        enforceMinimumPop();
    }

    private Map<Region, Integer> getEmpireCensus() {
        Map<Region,Integer> countPop = new HashMap<Region,Integer>();
        for(Tribe tribe : _population){
            Region tribeLoc = tribe.getLocation();
            int oldValue = 0;
            if (countPop.containsKey(tribeLoc)){
                oldValue = countPop.get(tribeLoc);
            }
            countPop.put(tribeLoc,oldValue + 1);
        }
        return countPop;
    }

    public List<Tribe> poolPopulation() {
        List<Tribe> localPopulation = new ArrayList<Tribe>();
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
}
