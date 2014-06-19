package at.oneminutedistraction.phonecalllistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static at.oneminutedistraction.phonecalllistener.Constants.*;

public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {

    public OutgoingCallBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        Log.d(TAG, "Phonenumber: " + phone);

        PhoneCallHandlerPlugin plugin = PhoneCallHandlerPlugin.getInstance();


    }
}
