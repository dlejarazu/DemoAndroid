package com.diego.civpocket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diego.civpocket.logic.*;
import com.diego.civpocket.logic.Region.IllegalActionException;

//prueba
//TODO: Crear mapa dibujable con canvas extendiendo clase View

public class MainMapView extends Activity implements MapUpdater {

    MapPresenter presenter;
    MapDrawView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        presenter = new MapPresenter( new CivPocketGame( new Empire()), new Scenario("A New World"), this);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.getNombresRegiones());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //TODO: Duplication. There is no connection between how the view draws the scenario and the scenario itself
        mapa = (MapDrawView) findViewById(R.id.mapScenarioView);
        mapa.mapPModel = presenter;
        mapa.AddRegion("1",235,309);
        mapa.AddRegion("2",432,672);
        mapa.AddRegion("3",420,435);
        mapa.AddRegion("4",485,289);
        mapa.AddRegion("5",180,817);
        mapa.AddRegion("7",73,657);
        mapa.AddRegion("8",263,477);

        //TODO:forma elegante de mapear las regiones a sus statusTxtViews
        UpdateControls();
        UpdateMap();
    }

    @Override
    public void UpdateMap(){
        mapa.invalidate();
    }

    @Override
    public void UpdateControls(){
        (findViewById(R.id.buttonAddPop)).setEnabled(presenter.isAddPoblacionActivo());
        (findViewById(R.id.buttonRemPop)).setEnabled(presenter.isRemPoblacionActivo());
        (findViewById(R.id.buttonConstruirCiudad)).setEnabled(presenter.isConstruirCiudaPossible());
        (findViewById(R.id.nextFaseButton)).setEnabled(presenter.isSiguienteFaseActivo());
        (findViewById(R.id.buttonConstruirGranja)).setEnabled(presenter.isGranjasActivo());
        ((TextView) findViewById(R.id.textViewFaseActual)).setText(presenter.getFaseActual());
    }

    public void BotonAddPoblacion(View view) {
        presenter.accionAddPoblacion();
    }
    public void BotonSubPoblacion(View view) {
        presenter.accionRemPoblacion();
    }
    public void BotonConstruyeCiudad(View view) {
        presenter.accionConstruirCiudad();
    }
    public void BotonSiguienteFase(View view) {
        presenter.accionPasarSiguienteFase();
    }
    public void BotonConstruyeGranja(View view) throws IllegalActionException {
    	presenter.accionConstruirGranja();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings ||
                super.onOptionsItemSelected(item);
    }
}
