package at.oneminutedistraction.phonecalllistener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import android.widget.*;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class PhoneCallHandlerActivity extends Activity {

    private boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Creating " + this.getClass().getName());

		setContentView(new LinearLayout(getApplicationContext()));

        PhoneNumber pn = (PhoneNumber)getIntent().getExtras().getSerializable(VALUE_PHONENUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Do not call")
                .setMessage(pn.getNotes())
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getIntent().putExtra(VALUE_RESULT, true);
                    }
                })
                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getIntent().putExtra(VALUE_RESULT, false);
                    }
                });
        builder.show();
    }

    public boolean isResult() {
        return (result);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "New intent");
    }
}
