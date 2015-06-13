package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.diego.civpocket.logic.Region.IllegalActionException;

/**
 * Model Presenter View for CivPocket
 */
public class MapPresenter {
    private final CivPocketGame _game;
    private final Empire _player;
    private final  Escenario _actualScenario;
    private final MapUpdater _updater;

    private Region _selectedRegion = null;

    public MapPresenter( CivPocketGame newGame, Escenario newScenario, Empire newPlayer, MapUpdater newUpdater)
    {
        _game = newGame;
        _player = newPlayer;
        _actualScenario = newScenario;
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
        _game.nextPhase();
        synchView();
    }

    public void accionSelectRegion(String nombre)
    {
        _selectedRegion = null;
        if (nombre!=null) {
            _selectedRegion = _actualScenario.getRegionByName(nombre);
        }
        synchView();
    }

    public void accionAddPoblacion()
    {
        if (_selectedRegion!= null) {
            _player.sendSettler(_selectedRegion);
            synchView();
        }
    }

    public void accionRemPoblacion()
    {
        if (_selectedRegion!= null) {
            _player.reduceSettler(_selectedRegion);
            synchView();
        }

    }

    public void accionConstruirCiudad() {
        if (_selectedRegion!= null) {
            _player.buildCity(_selectedRegion);
            synchView();
        }
    }
    

	public void accionConstruirGranja() throws IllegalActionException {
		if (_selectedRegion!= null && _game.getActualPhase() == GamePhase.Advances) {
            _player.buildFarm(_selectedRegion);
            synchView();
        }
	}

    /********
     * INFO PROVIDERS
     ********/

    public boolean isConstruirCiudaPossible() {
        return  _selectedRegion!=null &&
                _game.getActualPhase()== GamePhase.Advances &&
                _player.canBuildCityAt(_selectedRegion);
    }
    public boolean isAddPoblacionActivo() {
        return  _game.getActualPhase()!= GamePhase.Events;
    }
    public boolean isRemPoblacionActivo() {
    	return  _game.getActualPhase()!= GamePhase.Events;
    }
    public boolean isSiguienteFaseActivo() {
        return  true;
    }
    
	public boolean isGranjasActivo() {
		return _selectedRegion!=null &&
			   _game.getActualPhase()== GamePhase.Advances &&
               _player.canBuildFarmAt(_selectedRegion);
	}

    public List<String> getNombresRegiones() {
        List<String> regionesArray =  new ArrayList<>();
        for (Region regActual : _actualScenario.regionesMapa) {
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
        Region region = _actualScenario.getRegionByName(nombreRegion);
        int localPop = _player.populationAt(region).size();
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
        switch (_game.getActualPhase()) {
            case Growth:
                return "Growth";
            case Events:
                return "Events";
            case Advances:
                return "Advances";
            case Upkeep:
                return "Upkeep";
        }
        return "";
    }



}
