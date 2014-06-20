package at.oneminutedistraction.phonecalllistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.Semaphore;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {

    public OutgoingCallBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        Log.d(TAG, "Phonenumber: " + phone);

        Intent phoneIntent = new Intent(context, PhoneCallHandlerActivity.class);
        phoneIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(phoneIntent);
        /*
        Semaphore sem = new Semaphore(1);
        phoneIntent.putExtra(PARAMS_SEMAPHORE, sem);

        try {
            sem.acquire();
        } catch (InterruptedException e) {
            Log.e(TAG, "sem.acquire()", e);
        } */


    }
}
