package sp.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

	private Button block = null;
	private Button unblock = null;
	private Button view = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		block = (Button) findViewById(R.id.blockEnabled);
		block.setOnClickListener(onBlock);
		unblock = (Button) findViewById(R.id.blockDisabled);
		view = (Button) findViewById(R.id.view);
		view.setOnClickListener(onView);
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
private View.OnClickListener onBlock = new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent(Home.this, EditPreferences.class);
            startActivity(i);
			
	};
	
};

private View.OnClickListener onView = new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent i = new Intent(Home.this, SmsList.class);
        startActivity(i);
		
};

};
}
