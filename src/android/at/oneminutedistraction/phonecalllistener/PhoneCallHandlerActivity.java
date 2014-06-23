package at.oneminutedistraction.phonecalllistener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class PhoneCallHandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Creating " + this.getClass().getName());
    }

    @Override
    protected void onNewIntent(Intent intent) {
    }
}
