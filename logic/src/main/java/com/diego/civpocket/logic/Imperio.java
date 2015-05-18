package com.diego.civpocket.logic;

import com.diego.civpocket.logic.Region.accionIlegalException;


public class Imperio {

    int _totalPopulation;

    public void EnviarColono(IColonizable destino)
    {
        destino.AddPoblacion();
        _totalPopulation++;
    }

    public void ReclamarColono(IColonizable destino)
    {
        destino.EliminaPoblacion();
        _totalPopulation--;
    }

    public boolean puedeCiudadEn(IColonizable region){
        return region.getPoblacion()>=4 
        	&& region.getNivelCiudad()==0;
    }

    public void ConstruirCiudad(IColonizable region) {
        if (puedeCiudadEn(region)) {
            region.ConstruirCiudad();
            region.EliminaPoblacion(4);
        }
    }

	public boolean puedeGranjaEn(Region region ) {

		return !region.has(Biomes.Farm) && region.has(Biomes.Forest);
	}

	public void ConstruirGranja(Region seleccionada) throws accionIlegalException {
		seleccionada.add(Biomes.Farm );
        seleccionada.decimate(Biomes.Forest);
	}

    public int totalPopulation() {
        return _totalPopulation;
    }
}
