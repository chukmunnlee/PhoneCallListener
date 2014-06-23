package at.oneminutedistraction.phonecalllistener;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Semaphore;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class OutgoingCallBroadcastReceiver extends BroadcastReceiver {

    public OutgoingCallBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String phone = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        Log.d(TAG, "Outgoing call: " + phone);

        PhoneNumberDatabase database = new PhoneNumberDatabase(context);
        List<PhoneNumber> result = database.getByPhoneNumber(phone);

        if (result.isEmpty())
            return;

        Intent callHandlerIntent = new Intent(context, PhoneCallHandlerActivity.class);
        callHandlerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        callHandlerIntent.putExtra(VALUE_PHONENUMBER, result.get(0));

        context.startActivity(callHandlerIntent);

        Log.d(TAG, "----> after = " + callHandlerIntent.getBooleanExtra(VALUE_RESULT, false));
    }
}
