package br.com.pierry.friendly.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class Itemized extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;
	private Activity activity;
	private MapView map;
	private LocationInfo info;

	public Itemized(Drawable defaultMarker, MapView mapView, Activity activity, LocationInfo info) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
		this.activity = activity;
		this.map = mapView;
		this.info = info;
	}

	public void addOverlay(OverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		double latfinal = microDegreesToDegrees(item.getPoint().getLatitudeE6());
		double lgfinal = microDegreesToDegrees(item.getPoint().getLongitudeE6());
		this.CriarRota(latfinal, lgfinal);
		return true;
	}
	
	public static double microDegreesToDegrees(int microDegrees) {
	    return microDegrees / 1E6;
	}
	
    void CriarRota(double latfinal, double longfinal){   
    	int i = map.getOverlays().size();    	
    	if (i > 5){
    		map.getOverlays().remove(i-1);
    	}
    	new RotaAsyncTask(map).execute(Double.valueOf(String.valueOf(info.lastLat)), Double.valueOf(String.valueOf(info.lastLong)), latfinal, longfinal);
    	
    }
}
