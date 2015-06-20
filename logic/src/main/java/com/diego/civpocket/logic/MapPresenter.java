package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.List;

import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.google.inject.Inject;

/**
 * Model Presenter View for CivPocket
 */
public class MapPresenter {
    private final CivPocketGame _game;
    private final MapUpdater _updater;
    CityBuilder _cityBuilder;

    private Region _selectedRegion = null;
    private boolean _moveMode =false;
    private Region _destination = null;

    public MapPresenter( CivPocketGame newGame,  MapUpdater newUpdater)
    {
        _game = newGame;
        _updater = newUpdater;
        _cityBuilder = new DefaultCityBuilder(_game.getEmpire());
    }

    @Inject MapPresenter( CivPocketGame newGame,  MapUpdater newUpdater, CityBuilder newCityBuilder)
    {
        _game = newGame;
        _updater = newUpdater;
        _cityBuilder = newCityBuilder;
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

    public void actionSelectRegion(String name)
    {
        if(_moveMode){
            _destination = _game.getScenario().getRegionByName(name);
            List<Tribe> group = _game.getEmpire().tribesAt(_selectedRegion);
            if(group.size() > 0) group.get(0).moveTo(_destination);
            actionCancelMove();
        }
        else {
            _selectedRegion = _game.getScenario().getRegionByName(name);
        }
        synchView();
    }

    public void actionCancelMove()
    {
        if(_moveMode){
            _moveMode = false;
        }
        synchView();
    }

    public void actionMoveTribe()
    {
        if (_game.getActualPhase()==GamePhase.Growth){
            _moveMode = true;
        }
        synchView();
    }

    public void actionBuildCity() throws IllegalActionException {
        if (_selectedRegion!= null) {
            _cityBuilder.buildCity(_selectedRegion);
            synchView();
        }
    }

	public void accionConstruirGranja() {
		if (_selectedRegion!= null && _game.getActualPhase() == GamePhase.Advances) {
            try {
                _game.getEmpire().buildFarm(_selectedRegion);
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
                _cityBuilder.canBuildCityAt(_selectedRegion);
    }

    private boolean populationButtonStatus() {
        return  _game.getActualPhase()== GamePhase.Growth && _selectedRegion != null;
    }

    public boolean isAddTribeActive() {
        return populationButtonStatus() && _destination != null;
    }
    public boolean isMoveTribeActive() {
        return populationButtonStatus() && !_moveMode;
    }

    public boolean isNextPhaseActive() {
        return  !_moveMode;
    }
    
	public boolean isGranjasActivo() {
		return _selectedRegion!=null &&
			   _game.getActualPhase()== GamePhase.Advances &&
                _game.getEmpire().canBuildFarmAt(_selectedRegion);
	}

    public List<String> getNombresRegiones() {
        List<String> regionesArray =  new ArrayList<>();
        for (Region regActual : _game.getScenario()._map) {
            regionesArray.add(regActual.getName());
        }
        return regionesArray;
    }

    public String emoji(int hexcode)  {
        return new String(new int[] { hexcode }, 0, 1);
    }

    public String regionStatusToString(String nombreRegion){
        Region region = _game.getScenario().getRegionByName(nombreRegion);
        int localPop = _game.getEmpire().tribesAt(region).size();
        String status = emoji(0x1F603) + Integer.toString(localPop);
        if (_game.getEmpire().cityAt(region) !=null ) {
            status = status + "\n" + emoji(0x1F3F0) + Integer.toString(_game.getEmpire().cityAt(region).level());
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

    public boolean isSelectedAsDestination(String nameCheck) {
        return _destination != null &&
                nameCheck.equals(_destination.getName());
    }

    public boolean isSelected(String nameCheck) {
        return _selectedRegion != null &&
                nameCheck.equals(_selectedRegion.getName());
    }
}
