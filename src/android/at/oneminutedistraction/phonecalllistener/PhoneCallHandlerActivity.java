package at.oneminutedistraction.phonecalllistener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.net.Uri;

import android.widget.*;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class PhoneCallHandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Creating " + this.getClass().getName());

        final PhoneNumber pn = (PhoneNumber)getIntent().getExtras().getSerializable(VALUE_PHONENUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do not call")
                .setMessage(pn.getNotes())
                .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:" + pn.getPhoneNumber() + VALUE_SUFFIX));
						startActivity(callIntent);
						finish();
                    }
                })
                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
        builder.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "New intent");
    }
}
