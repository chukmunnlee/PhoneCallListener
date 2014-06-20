package at.oneminutedistraction.phonecalllistener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;


public class PhoneCallHandlerPlugin extends CordovaPlugin {

    private static PhoneCallHandlerPlugin plugin = null;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        //Initialization goes here
        plugin = this;
    }

    private Context getContext() {
        return (this.cordova.getActivity().getApplicationContext());
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Log.d(Constants.TAG, "execute: action=" + action + ", args=" + args.toString());

        boolean result = true;

        if (Constants.METHOD_STATUS.equals(action)) {
            if (args.length() <= 0)
                isRegistered(callbackContext);
            else
                setRegister(args.getBoolean(0), callbackContext);

        } else
            result = false;

        return (result);
    }

    private void setRegister(boolean e, CallbackContext callbackContext) {
        Context ctx = getContext();
        OutgoingCallBroadcastReceiver receiver = new OutgoingCallBroadcastReceiver();

        if (e)
            try {
                ctx.registerReceiver(receiver, IntentFilter.create(Intent.ACTION_NEW_OUTGOING_CALL, String.class.getName()));
            } catch (Throwable t) {
                //Fail quietly
                Log.i(Constants.TAG, "Receiver re-registered");
            }
        else
            try {
                ctx.unregisterReceiver(receiver);
            } catch (Throwable t) {
                Log.i(Constants.TAG, "Receiver unregistered");
            }
        callbackContext.success(Constants.VALUE_TRUE);
    }

    private void isRegistered(CallbackContext callbackContext) {
        Context ctx = getContext();
        OutgoingCallBroadcastReceiver receiver = new OutgoingCallBroadcastReceiver();
        try {
            getContext().registerReceiver(receiver
                    , IntentFilter.create(Intent.ACTION_NEW_OUTGOING_CALL, String.class.getName()));
        } catch (Throwable t) {
            callbackContext.success(Constants.VALUE_TRUE);
            return;
        }
        getContext().unregisterReceiver(receiver);
        callbackContext.success(Constants.VALUE_FALSE);
    }

    public static PhoneCallHandlerPlugin getInstance() {
        return (plugin);
    }
}
