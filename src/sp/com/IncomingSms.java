package sp.com;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
    
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    
    public void onReceive(Context context, Intent intent) {
     
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
 
        try {
             
        	SmsHelper add = new SmsHelper(context);
        	
            if (bundle != null) {
                
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                 
                for (int i = 0; i < pdusObj.length; i++) {
                     
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    String sender = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    
                    Log.i("SmsReceiver", "senderNum: "+ sender + "; message: " + message);

                   // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, 
                                 "Received SMS from: "+ sender + "\n" + "Message: " + message, duration);
                    toast.show();
                    
                    ContentValues values = new ContentValues();
                    values.put("address", sender);
                    values.put("body", message);
                    values.put("status", SmsManager.STATUS_ON_ICC_UNREAD);
                    context.getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
                    
                    add.insert(time, sender, message);
                    
                    abortBroadcast();
                    
                } // end for loop
                
              } // bundle is null
 
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
             
        }
    }    
}
