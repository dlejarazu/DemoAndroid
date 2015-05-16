package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 */
public interface IColonizable {
    void AddPoblacion();
    void AddPoblacion(int cantidad);
    void EliminaPoblacion();
    int getPoblacion();
    void ConstruirCiudad();
    int getNivelCiudad();
    void EliminaPoblacion(int cantidad);
}
