package sp.com;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class SmsList extends ListActivity {

	private Cursor model = null;
	private SmsAdapter adapter = null;
	private SmsHelper helper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_list);
		
		helper = new SmsHelper(this);
		
		model = helper.getAll();
        startManagingCursor(model);
        adapter = new SmsAdapter(model);
        setListAdapter(adapter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		helper.close();
	}
	
	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		// TODO Auto-generated method stub
		model.moveToPosition(position);
		String findSender = helper.getSender(model);
		onIntent(findSender);
	}
	
	public void onIntent(String findSender){
        String sender = findSender;
		
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("address", sender);
		startActivity(smsIntent);
	}
	
	class SmsAdapter extends CursorAdapter {
		SmsAdapter(Cursor c) {
			super(SmsList.this, c);
    }
	
		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			// TODO Auto-generated method stub
            SmsHolder holder = (SmsHolder) row.getTag();
    		holder.populateFrom(c, helper);
		}
		
		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = getLayoutInflater();
    		View row = inflater.inflate(R.layout.row, parent, false);
    		SmsHolder holder = new SmsHolder(row);
    		row.setTag(holder);
    		return (row);
		}
	}
		
		static class SmsHolder {
			private TextView time = null;
			private TextView sender = null;
			private TextView message = null;
			
			SmsHolder(View row){
				time = (TextView) row.findViewById(R.id.time);
				sender = (TextView) row.findViewById(R.id.sender);
				message = (TextView) row.findViewById(R.id.message);
			}
			
			void populateFrom(Cursor c, SmsHelper helper){
				time.setText(helper.getTime(c));
				sender.setText(helper.getSender(c));
				message.setText(helper.getMessage(c));
					
			}
	}
}
