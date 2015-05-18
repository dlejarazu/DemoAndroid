package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

public class Region implements IColonizable{

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

    @Override
    public void AddPoblacion() {
        AddPoblacion(1);
    }

    @Override
    public void AddPoblacion(int cantidad) {
        _poblacion = _poblacion + cantidad;
    }

    @Override
    public void EliminaPoblacion() {
        EliminaPoblacion(1);
    }
    @Override
    public void EliminaPoblacion(int cantidad) {
        _poblacion = _poblacion - cantidad;
        if (_poblacion < 0) {
            _poblacion = 0;
        }
    }

    @Override
    public int getPoblacion() {
        return _poblacion;
    }

    @Override
    public void ConstruirCiudad() {
        _nivelCiudad = 1;
    }

    @Override
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
