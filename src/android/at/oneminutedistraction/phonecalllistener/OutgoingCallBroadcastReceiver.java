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

        PhoneNumber pn = result.get(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Do not call")
                .setMessage(pn.getNotes())
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResultData(phone);
                    }
                })
                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResultData("");
                    }
                });
    }
}
