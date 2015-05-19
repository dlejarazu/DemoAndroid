package com.diego.civpocket.logic;

import com.diego.civpocket.logic.Region.accionIlegalException;

import java.util.ArrayList;
import java.util.List;


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
        List<Tribe> localPopulation = this.regionPop(destination);
        _population.remove(localPopulation.get(0));
    }

    public List<Tribe> regionPop(Region local) {
        List<Tribe> localPopulation = new ArrayList<Tribe>();
        for (Tribe tribe: _population) {
            if (tribe.get_location() == local) {
                localPopulation.add(tribe);
            }
        }
        return localPopulation;
    }

    public boolean canBuildCityAt(Region region){
        return regionPop(region).size()>=4
        	&& region.getNivelCiudad()==0;
    }

    public void buildCity(Region region) {
        if (canBuildCityAt(region)) {
            region.ConstruirCiudad();
            for(Tribe builders : regionPop(region).subList(0,3)){
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
}
