package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

import com.diego.civpocket.logic.CivPocketGame.FaseJuego;
import com.diego.civpocket.logic.Region.IllegalActionException;

/**
 * Model Presenter View for CivPocket
 */
public class MapPresenter {
    private final CivPocketGame _juego;
    private final Empire _jugador;
    private final  Escenario _escenarioActual;
    private final MapUpdater _updater;

    private Region _selectedRegion = null;

    public MapPresenter( CivPocketGame newJuego, Escenario newEscenario, Empire newJugador, MapUpdater newUpdater)
    {
        _jugador = newJugador;
        _juego = newJuego;
        _escenarioActual = newEscenario;
        _updater = newUpdater;
    }
    
    void synchView() {
    	_updater.UpdateControls();
        _updater.UpdateMap();
    }

    /********
     * ACCIONES
     ********/

    public void accionPasarSiguienteFase() {
        _juego.SiguienteFase();
        synchView();
    }

    public void accionSelectRegion(String nombre)
    {
        _selectedRegion = null;
        if (nombre!=null) {
            _selectedRegion = _escenarioActual.getRegionByName(nombre);
        }
        synchView();
    }

    public void accionAddPoblacion()
    {
        if (_selectedRegion!= null) {
            _jugador.sendSettler(_selectedRegion);
            synchView();
        }
    }

    public void accionRemPoblacion()
    {
        if (_selectedRegion!= null) {
            _jugador.decimateSettler(_selectedRegion);
            synchView();
        }

    }

    public void accionConstruirCiudad() {
        if (_selectedRegion!= null) {
            _jugador.buildCity(_selectedRegion);
            synchView();
        }
    }
    

	public void accionConstruirGranja() throws IllegalActionException {
		// TODO Auto-generated method stub
		if (_selectedRegion!= null && _juego.getFaseActual() == FaseJuego.Avances) {
            _jugador.buildFarm(_selectedRegion);
            synchView();
        }
	}

    /********
     * INFO PROVIDERS
     ********/

    public boolean isConstruirCiudaPossible() {
        return  _selectedRegion!=null &&
                _juego.getFaseActual()== CivPocketGame.FaseJuego.Avances &&
                _jugador.canBuildCityAt(_selectedRegion);
    }
    public boolean isAddPoblacionActivo() {
        return  _juego.getFaseActual()!=CivPocketGame.FaseJuego.Evento;
    }
    public boolean isRemPoblacionActivo() {
    	return  _juego.getFaseActual()!=CivPocketGame.FaseJuego.Evento;
    }
    public boolean isSiguienteFaseActivo() {
        return  true;
    }
    
	public boolean isGranjasActivo() {
		return _selectedRegion!=null &&
			   _juego.getFaseActual()== CivPocketGame.FaseJuego.Avances &&
               _jugador.canBuildFarmAt(_selectedRegion);
	}

    public List<String> getNombresRegiones() {
        List<String> regionesArray =  new ArrayList<>();
        for (Region regActual : _escenarioActual.regionesMapa) {
            regionesArray.add(regActual.getName());
        }
        return regionesArray;
    }

    public boolean isSelected(String nombreCheck) {
        return _selectedRegion != null &&
               _selectedRegion.getName().equals(nombreCheck);
    }

    public String emoji(int hexcode)  {
        return new String(new int[] { hexcode }, 0, 1);
    }

    public String regionStatusToString(String nombreRegion){
        Region region =_escenarioActual.getRegionByName(nombreRegion);
        int localPop = _jugador.populationAt(region).size();
        String status = emoji(0x1F603) + Integer.toString(localPop);
        if (region.getCityLevel() > 0) {
            status = status + "\n" + emoji(0x1F3F0) + Integer.toString(region.getCityLevel());
        }
        if (region.has(Biomes.Farm)) {
            status = status + "\n" + emoji(0x1F33D);
        }
        return status;
    }

    public String getFaseActual() {
        switch (_juego.getFaseActual()) {
            case Crecimiento:
                return "Crecimiento";
            case Evento:
                return "Eventos";
            case Avances:
                return "Avances";
            case Mantenimiento:
                return "Mantenimiento";
        }
        return "";
    }



}
