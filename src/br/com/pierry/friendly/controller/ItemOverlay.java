package br.com.pierry.friendly.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ItemOverlay extends Overlay {

	private GeoPoint geo;
	private Paint paint = new Paint();;
	private int image;	
	
	public ItemOverlay (GeoPoint geo, int image){
		this.geo = geo;
		this.image = image;
	}
	
	public void draw(Canvas canvas, MapView map, boolean boolOverlay){
		super.draw(canvas, map, boolOverlay);
		if(geo != null){			
			Point point = map.getProjection().toPixels(geo, null);
			Bitmap bitmap = BitmapFactory.decodeResource(map.getResources(), this.image);
			RectF rectf = new RectF(point.x, point.y, point.x+bitmap.getWidth(), point.y+bitmap.getHeight());
			canvas.drawBitmap(bitmap, null, rectf, paint);
		}
	}
	
}
