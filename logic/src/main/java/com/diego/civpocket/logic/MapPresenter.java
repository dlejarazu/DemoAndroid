package com.diego.civpocket.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.diego.civpocket.logic.CivPocketGame.GamePhase;
import com.google.inject.Inject;

/**
 * Model Presenter View for CivPocket
 */
public class MapPresenter {
    private final CivPocketGame _game;
    private final MapUpdater _updater;
    private final Empire _empire;
    private final Scenario _scenario;
    private final Library _library;
    CityBuilder _cityBuilder;

    private Region _selectedRegion = null;
    private boolean _moveMode =false;
    private Region _destination = null;


    @Inject MapPresenter(CivPocketGame newGame, MapUpdater newUpdater, CityBuilder newCityBuilder, Empire newEmpire, Scenario newScenario, Library library)
    {
        _game = newGame;
        _empire = newEmpire;
        _scenario = newScenario;
        _updater = newUpdater;
        _cityBuilder = newCityBuilder;
        _library = library;

        _scenario.setUp(_empire);
        _game.addUpkeepTask(_empire);
        _game.addGrowthTask(_empire);
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
            _destination = _scenario.getRegionByName(name);
            Tribe nomad = _empire.getTribeFrom(_selectedRegion);
            if(nomad != null) nomad.moveTo(_destination);
            actionCancelMove();
        }
        else {
            _selectedRegion = _scenario.getRegionByName(name);
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
                _empire.buildFarm(_selectedRegion);
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
                _empire.canBuildFarmAt(_selectedRegion);
	}

    public List<String> getNombresRegiones() {
        List<String> regionesArray =  new ArrayList<>();
        for (Region regActual : _scenario._map) {
            regionesArray.add(regActual.getName());
        }
        return regionesArray;
    }

    public String emoji(int hexcode)  {
        return new String(new int[] { hexcode }, 0, 1);
    }

    public String regionStatusToString(String nombreRegion){
        Region region = _scenario.getRegionByName(nombreRegion);
        int localPop = _empire.populationAt(region);
        String status = emoji(0x1F603) + Integer.toString(localPop);
        if (_empire.cityAt(region) !=null ) {
            status = status + "\n" + emoji(0x1F3F0) + Integer.toString(_empire.cityAt(region).level());
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

    public void actionPurchaseAdvance(String nameAdvance) {
        _library.acquireCartage(_selectedRegion);
    }

    public boolean canPurchaseCartage() {
        return  _selectedRegion != null &&
                _library.canResearchCartageFrom(_selectedRegion);
    }

    public List<String> getTechnologies() {
        List<String> stringTechs = new ArrayList<>();
        if(_library.hasCartage()) stringTechs.add("Cartage");
        return stringTechs;
    }
}
