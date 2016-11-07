package sp.com;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class EditPreferences extends PreferenceActivity {

	private SharedPreferences prefs = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(onChange);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		prefs.unregisterOnSharedPreferenceChangeListener(onChange);
		super.onPause();
	}
	
	SharedPreferences.OnSharedPreferenceChangeListener onChange = new SharedPreferences.OnSharedPreferenceChangeListener() {
		
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
			
			
		    };
	};
}
		