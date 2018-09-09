package com.yaratech.yaratube.ui.login.verification.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSListener extends BroadcastReceiver {

    private static final String TAG = "SmsBroadcastReceiver";
    private String phoneNumber = "+98200049103";
    private static SmsBroadCastListener mSmsBroadCastListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            String smsSender = "";
            String smsBody = "";
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsBody += smsMessage.getMessageBody();
                smsSender = smsMessage.getDisplayOriginatingAddress();
            }

            if (smsSender.equals(phoneNumber)) {

                smsBody = smsBody.replaceAll("\\D+", "");
                mSmsBroadCastListener.onTextReceived(smsBody);


                Toast.makeText(context, "BroadcastReceiver caught conditional SMS: "
                        + smsBody, Toast.LENGTH_LONG).show();
            }

        }


    }

    public static void bindListener(SmsBroadCastListener smsBroadCastListener) {
        mSmsBroadCastListener = smsBroadCastListener;
    }


    public interface SmsBroadCastListener {
        void onTextReceived(String message);
    }
}
