package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 11/10/2014.
 */

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

	public boolean tieneGranja() {
		// TODO Auto-generated method stub
		return _biomesInRegion.contains(Biomes.Farm);
	}

	public void addGranja() throws accionIlegalException {
		// TODO Auto-generated method stub
		if (!tieneGranja())  {
			_biomesInRegion.add(Biomes.Farm);
		}
		else throw new accionIlegalException();
	}


}
