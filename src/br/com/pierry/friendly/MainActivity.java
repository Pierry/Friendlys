package br.com.pierry.friendly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.ViewById;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	ImageButton ibEntrar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
    }
    
    @Click
    void ibEntrar(){
    	Intent intent = new Intent(this, LocalizacaoActivity_.class);
    	startActivity(intent);
    }
    
}
