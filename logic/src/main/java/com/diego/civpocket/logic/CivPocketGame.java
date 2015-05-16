package com.diego.civpocket.logic;

/**
 * Created by diego on 13/10/2014.
 */


public class CivPocketGame {

    public enum FaseJuego {
        Crecimiento, Evento, Avances, Mantenimiento;
        public FaseJuego getNext() {
            return values()[(ordinal() + 1) % values().length];
        }
    }

    FaseJuego _faseActual = FaseJuego.Crecimiento;

    public FaseJuego getFaseActual(){
        return _faseActual;
    }

    public void SiguienteFase(){
        _faseActual = _faseActual.getNext();
    }
}
