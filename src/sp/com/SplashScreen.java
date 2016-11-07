package sp.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                
                Intent i = new Intent(SplashScreen.this, Home.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
