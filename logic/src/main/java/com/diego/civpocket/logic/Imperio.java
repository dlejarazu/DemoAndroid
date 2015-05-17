package com.diego.civpocket.logic;

import com.diego.civpocket.logic.Region.accionIlegalException;

/**
 * Created by diego on 13/10/2014.
 */
public class Imperio {
 
    public void EnviarColono(IColonizable destino)
    {
        destino.AddPoblacion();
    }

    public void ReclamarColono(IColonizable destino)
    {
        destino.EliminaPoblacion();
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

		return !region.hasFarm() && region.hasForest();
	}

	public void ConstruirGranja(Region seleccionada) throws accionIlegalException {
		seleccionada.addGranja();
        seleccionada.decimate(Biomes.Forest);
	}
}
