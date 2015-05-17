package com.diego.civpocket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diego.civpocket.R;
import com.diego.civpocket.logic.*;
import com.diego.civpocket.logic.Region.accionIlegalException;

//prueba
//TODO: Crear mapa dibujable con canvas extendiendo clase View

public class MainMapView extends Activity implements MapUpdater {

    MapPresenter presenter;
    MapDrawView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        Region[] regionesMapa;

        //TODO las regiones se deberian leer de mappresenter
        regionesMapa = new Region[7];
        regionesMapa[0] = new Region("1");
        regionesMapa[1] = new Region("2");
        regionesMapa[2] = new Region("3");
        regionesMapa[3] = new Region("4");
        regionesMapa[4] = new Region("5");
        regionesMapa[5] = new Region("7");
        regionesMapa[6] = new Region("8");

        presenter = new MapPresenter( new CivPocketGame(), new Escenario(regionesMapa), new Imperio(), this);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getNombresRegiones());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
    public void BotonConstruyeGranja(View view) throws accionIlegalException {
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}