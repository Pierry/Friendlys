package br.com.pierry.friendly.model;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class Route {
	  private final List<GeoPoint> points;
	  private String polyline;

	  public Route() {
	    points = new ArrayList<GeoPoint>();
	  }

	  public void addPoints(final List<GeoPoint> points) {
	    this.points.addAll(points);
	  }

	  public List<GeoPoint> getPoints() {
	    return points;
	  }

	  public void setPolyline(String polyline) {
	    this.polyline = polyline;
	  }

	  public String getPolyline() {
	    return polyline;
	  }
	}
