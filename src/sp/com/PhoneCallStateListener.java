package sp.com;

import java.lang.reflect.Method;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class PhoneCallStateListener extends PhoneStateListener {    

private Context context;

public PhoneCallStateListener(Context context){
    this.context = context;
}

@Override
public void onCallStateChanged(int state, String incomingNumber) {  

    switch (state) {

        case TelephonyManager.CALL_STATE_RINGING:       

            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
            
            //Turn ON the mute
            audioManager.setStreamMute(AudioManager.STREAM_RING, true);                 
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            
            try {
                Toast.makeText(context, "Incoming Call from "+incomingNumber, Toast.LENGTH_LONG).show();
                Class clazz = Class.forName(telephonyManager.getClass().getName());
                Method method = clazz.getDeclaredMethod("getITelephony");
                method.setAccessible(true);
                ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);    
                
                telephonyService = (ITelephony) method.invoke(telephonyManager);
                telephonyService.endCall();
                
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }
            //Turn OFF the mute     
            audioManager.setStreamMute(AudioManager.STREAM_RING, false);
            break;
            
        case PhoneStateListener.LISTEN_CALL_STATE:

    }
    super.onCallStateChanged(state, incomingNumber);
}}
