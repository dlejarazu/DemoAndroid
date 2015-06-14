package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

import com.diego.civpocket.logic.CivPocketGame.GamePhase;

/**
 * Model Presenter View for CivPocket
 */
public class MapPresenter {
    private final CivPocketGame _game;
    private final Empire _player;
    private final Scenario _actualScenario;
    private final MapUpdater _updater;

    private Region _selectedRegion = null;

    public MapPresenter( CivPocketGame newGame, Scenario newScenario, MapUpdater newUpdater)
    {
        _game = newGame;
        _player = _game.getPlayer();
        _actualScenario = newScenario;
        _updater = newUpdater;

        _actualScenario.setUp(_player);
    }
    
    void synchView() {
    	_updater.UpdateControls();
        _updater.UpdateMap();
    }

    /********
     * ACCIONES
     ********/

    public void accionPasarSiguienteFase() {
        try {
            _game.nextPhase();
        } catch (IllegalActionException e) {
            throw new RuntimeException(e);
        }
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
            try {
                _player.reduceSettler(_selectedRegion);
            } catch (IllegalActionException e) {
                throw new RuntimeException(e);
            }
            synchView();
        }

    }

    public void accionConstruirCiudad() {
        if (_selectedRegion!= null) {
            _player.buildCity(_selectedRegion);
            synchView();
        }
    }
    

	public void accionConstruirGranja() {
		if (_selectedRegion!= null && _game.getActualPhase() == GamePhase.Advances) {
            try {
                _player.buildFarm(_selectedRegion);
            } catch (IllegalActionException e) {
                throw new RuntimeException(e);
            }
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

    private boolean populationButtonStatus() {
        return  _game.getActualPhase()== GamePhase.Growth;
    }

    public boolean isAddTribeActive() {
        return populationButtonStatus();
    }
    public boolean isRemTribeActive() {
        return populationButtonStatus();
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
        for (Region regActual : _actualScenario._map) {
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
        int localPop = _player.tribesAt(region).size();
        String status = emoji(0x1F603) + Integer.toString(localPop);
        if (region.getCityLevel() > 0) {
            status = status + "\n" + emoji(0x1F3F0) + Integer.toString(region.getCityLevel());
        }

        status += "\n";
        if (region.has(Biomes.Farm)) {
            status = status + emoji(0x1F33D);
        }
        if (region.has(Biomes.Forest)) {
            status = status + emoji(0x1F332);
        }
        if (region.has(Biomes.Dessert)) {
            status = status + emoji(0x1F335);
        }
        if (region.has(Biomes.Mountain)) {
            status = status + emoji(0x1F48E);
        }
        if (region.has(Biomes.Volcano)) {
            status = status + emoji(0x1F30B);
        }
        return status;
    }

    public String getFaseActual() {
        return _game.getActualPhase().name();
    }



}
