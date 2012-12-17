package br.com.pierry.friendly;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import br.com.pierry.friendly.controller.ItemOverlay;
import br.com.pierry.friendly.controller.Itemized;
import br.com.pierry.friendly.model.Instituicao;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.slidingmenu.lib.app.SlidingMapActivity;

import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

@Fullscreen
@EActivity(R.layout.localizacao)
public class LocalizacaoActivity extends SlidingMapActivity {

	@ViewById
	MapView mapa;
	
	@ViewById
	ImageButton ibItem01;
	
	@ViewById
	ImageButton ibItem02;
	
	@ViewById
	ImageButton ibItem03;
	
	@ViewById
	ImageButton ibItem04;
	
	@ViewById
	ImageView ivDescricao;
	
	@ViewById
	ImageView ibSatelite;
	
	@ViewById
	ImageView ibMapa;
	
	@ViewById
	ImageView ibCenter;
	
	GeoPoint geoPoint;
	ItemOverlay overlay;
	Itemized item01;
	List<Overlay> mapOverlays;
	LocationInfo info;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.menu);
		getSupportActionBar().setDisplayShowTitleEnabled(false);        
		getSupportActionBar().setHomeButtonEnabled(true);        
		getSupportActionBar().setIcon(R.drawable.logo);
		getSlidingMenu().setBehindOffset(50);
		info = new LocationInfo(getBaseContext());		
		this.CriarInst("Creche e Orfanato Vinde a Mim as Criancinhas", "São José - SC", -27.565416, -48.621232);
		this.CriarInst("Orfanato Paraíso da Criança", "Urussanga - SC", -28.522614, -49.317598);
		this.CriarInst("Lar e Abrigo Bom Pastor", "Camboriú - SC", -27.046573, -48.6389);
		this.CriarInst("Orfanato São Vicente de Paula", "Paranaguá - PR", -25.532393, -48.523956);
		this.CriarMe("Minha localização", "", info.lastLat, info.lastLong);
    }
    
    
    @Click
    void ibItem01(){
    	this.AlterarImagem(1);
    }
    
    @Click
    void ibItem02(){
    	this.AlterarImagem(2);
    }
    
    @Click
    void ibItem03(){
    	this.AlterarImagem(3);
    }
    
    @Click
    void ibItem04(){
    	this.AlterarImagem(4);
    }
    
    @Click
    void ibSatelite(){
    	this.setMapStyle(1);
    }
    
    @Click
    void ibMapa(){
    	this.setMapStyle(0);
    }
    
    @Click
    void ibCenter(){
    	if (geoPoint != null){
    		this.getMapControler(geoPoint);	
    	} else {
    		Crouton.makeText(this, "Sem pontos para centralizar", Style.CONFIRM).show();
    	}
    	
    }
    
    @UiThread
    void AlterarImagem(int i){
    	switch (i) {
		case 1:
			ivDescricao.setBackgroundResource(R.drawable.item01desc);
			ivDescricao.setImageResource(R.drawable.item01desc);
			break;
		case 2:
			ivDescricao.setBackgroundResource(R.drawable.item02desc);
			ivDescricao.setImageResource(R.drawable.item02desc);
			break;
		case 3:
			ivDescricao.setBackgroundResource(R.drawable.item03desc);
			ivDescricao.setImageResource(R.drawable.item03desc);
			break;
		case 4:
			ivDescricao.setBackgroundResource(R.drawable.item04desc);
			ivDescricao.setImageResource(R.drawable.item04desc);
			break;
		default:
			break;
		}
    }
    
    @UiThread
    public void CriarInst(String nome, String cidade, double lat, double lg){
    	Instituicao cvm = new Instituicao(nome, cidade, lat, lg);
		GeoPoint gCvm = setGeoPoint(cvm.getLatitude(), cvm.getLongitude());
		Drawable d = getResources().getDrawable(R.drawable.map_pin_holed_blue_normal);
		item01 = new Itemized(d, mapa, this, info);
		OverlayItem overlayItem = new OverlayItem(gCvm, cvm.getNome(), cvm.getCidade());
		item01.addOverlay(overlayItem);
		mapa.getOverlays().add(item01);
		this.getMapControler(gCvm);		
    }
    
    @UiThread
    public void CriarMe(String nome, String cidade, double lat, double lg){
    	Instituicao cvm = new Instituicao(nome, cidade, lat, lg);
		GeoPoint gCvm = setGeoPoint(cvm.getLatitude(), cvm.getLongitude());
		Drawable d = getResources().getDrawable(R.drawable.map_pin_holed_blue_normalme);
		item01 = new Itemized(d, mapa, this, info);
		OverlayItem overlayItem = new OverlayItem(gCvm, cvm.getNome(), cvm.getCidade());
		item01.addOverlay(overlayItem);
		mapa.getOverlays().add(item01);
		this.getMapControler(gCvm);		
    }
    
	public GeoPoint setGeoPoint(double lat, double lg){
		geoPoint = new GeoPoint((int) (lat * 1E6), (int)(lg * 1E6));
		return geoPoint;
	}
	
	@UiThread
	void getMapControler(GeoPoint geo){
		mapa.getController().setZoom(9);
		mapa.getController().animateTo(geo);		
	}
	
	@UiThread
	void setMapStyle(int n){
		if (n == 1){
			mapa.setSatellite(true);
		} else if (n == 0){
			mapa.setSatellite(false);
		}
	}
	
	public void addOverlay(GeoPoint geoPoint){
		overlay = new ItemOverlay(geoPoint, R.drawable.map_pin_holed_blue_normal);
		mapa.getOverlays().add(overlay);		
	}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		SubMenu subHome = menu.addSubMenu(Menu.NONE, R.id.ID_ACTION_HOME, Menu.NONE, "Home");
		MenuItem subMenuHome = subHome.getItem();
		subMenuHome.setIcon(R.drawable.home);
		subMenuHome.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu subAtualizar = menu.addSubMenu(Menu.NONE, R.id.ID_ACTION_CONTATO, Menu.NONE, "Contato");
		MenuItem subMenuAtualizar = subAtualizar.getItem();
		subMenuAtualizar.setIcon(R.drawable.cnt);
		subMenuAtualizar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);

	}    

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ID_ACTION_HOME:
			getSlidingMenu().showBehind();
			return true;
		case R.id.ID_ACTION_CONTATO:
			Crouton.makeText(this, "pieerry@gmail.com", Style.CONFIRM).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
