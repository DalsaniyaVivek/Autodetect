package com.example.vivekdalsaniya.autodetect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vivek Dalsaniya on 5/18/2016.
 */
public class Detect extends BroadcastReceiver {
    static String code="";

    @Override

    public void onReceive(Context context, Intent intent) {
        String senderNum,message;
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    if(phoneNumber.contains("WAYSMS")){
                        code="";
                        for(int j=0;j<4;j++)
                        {
                            code=code+message.charAt(j);
                        }
                        Log.i("asasa","code is:::  " +code);

                    }



                    // Show alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }

    public String otpreturn(){
        return code;
    }
}
