package br.com.pierry.friendly.controller;

import java.util.Locale;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import br.com.pierry.friendly.model.Route;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class RotaAsyncTask extends AsyncTask<Double, Void, Void>{

	private ProgressDialog dialog;
	private MapView mapView;

	public RotaAsyncTask(MapView mapa) {
		mapView = mapa;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(mapView.getContext(), "Aguarde","Calculando rota");
	}

	@Override
	protected Void doInBackground(Double... params) {
		Route route = directions(new GeoPoint((int)(params[0]*1E6), (int)(params[1]*1E6)), new GeoPoint((int)(params[2]*1E6), (int)(params[3]*1E6)));
		RouteOverlay routeOverlay = new RouteOverlay(route, Color.RED);		
		mapView.getOverlays().add(routeOverlay);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

	private Route directions(final GeoPoint start, final GeoPoint dest) {
	// Formatando a URL com a latitude e longitude  
	// de origem e destino.  
		String urlRota = String.format(Locale.US,
				"http://maps.googleapis.com/maps/api/"+
						"directions/json?origin=%f,%f&"+
						"destination=%f,%f&" +
						"sensor=true&mode=driving",   
						start.getLatitudeE6()/1E6, 
						start.getLongitudeE6()/1E6, 
						dest.getLatitudeE6()/1E6, 
						dest.getLongitudeE6()/1E6); 

		GoogleParser parser;
		parser = new GoogleParser(urlRota);
		Route r =  parser.parse();
		return r;
	}
}