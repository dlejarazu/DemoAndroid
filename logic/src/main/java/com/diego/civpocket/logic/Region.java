package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

public class Region {

    public class accionIlegalException extends Exception {
		private static final long serialVersionUID = -5044052553914379459L; }
	
    private int _poblacion = 0;
    List<Biomes> _biomesInRegion;
    private String Nombre;
    private int _nivelCiudad = 0;

    public Region(String nuevaRegion)
    {
        _biomesInRegion = new ArrayList<Biomes>();
        setNombre(nuevaRegion);
    }

    public String getNombre() {
        return Nombre;
    }

    private void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void ConstruirCiudad() {
        _nivelCiudad = 1;
    }

    public int getNivelCiudad() {
        return _nivelCiudad;
    }

	public boolean has(Biomes biome) {
		return _biomesInRegion.contains(biome);
	}

    public void add(Biomes biome) throws accionIlegalException {
        if (_biomesInRegion.contains(biome)){
            throw new accionIlegalException();
        }
        _biomesInRegion.add(biome);
    }

    public void decimate(Biomes decimatedBiome) {
        _biomesInRegion.remove(decimatedBiome);
    }

    public int support() {
        int totalSupport = 0;
        for(Biomes b : _biomesInRegion)
        {
            if (b != Biomes.Dessert) totalSupport++;
        }

        totalSupport += _nivelCiudad;
        return totalSupport;
    }
}
