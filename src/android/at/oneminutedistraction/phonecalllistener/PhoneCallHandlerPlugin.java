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
import org.json.JSONObject;

import java.util.List;

import static at.oneminutedistraction.phonecalllistener.Constants.*;


public class PhoneCallHandlerPlugin extends CordovaPlugin {

    private PhoneNumberDatabase phoneNumberDatabase = null;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        phoneNumberDatabase = new PhoneNumberDatabase(getContext());
    }

    private Context getContext() {
        return (this.cordova.getActivity().getApplicationContext());
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Log.d(TAG, "execute: action=" + action + ", args=" + args.toString());

        boolean result = true;

        if (METHOD_ADD_PHONENUMBER.equals(action))
            addPhoneNumber(args, callbackContext);

        else if (METHOD_REMOVE_PHONENUMBER.equals(action))
            removePhoneNumber(args, callbackContext);

        else if (METHOD_GET_ALL_NUMBERS.equals(action))
            getAllNumbers(callbackContext);

        else
            result = false;

        return (result);
    }

    private void getAllNumbers(CallbackContext callbackContext) {
        List<PhoneNumber> numbers = phoneNumberDatabase.getAll();
        try {
            JSONArray result = new JSONArray();
            for (PhoneNumber pn : numbers) {
                JSONObject obj = new JSONObject();
                obj.put(SQL_COLUMN_ID, pn.getId());
                obj.put(SQL_COLUMN_PHONENUMBER, pn.getPhoneNumber());
                obj.put(SQL_COLUMN_NOTES, pn.getNotes());
                result.put(obj);
            }
            callbackContext.success(result);
            Log.i(TAG, "Returned " + numbers.size() + " numbers");
        } catch (JSONException ex) {
            Log.e(TAG, "getAllNumbers", ex);
            callbackContext.error(ex.getMessage());
        }
    }

    private void addPhoneNumber(JSONArray args, CallbackContext callbackContext) {
        String telno;
        try {
            JSONObject obj = args.getJSONObject(0);
            telno = obj.getString(SQL_COLUMN_PHONENUMBER);
            phoneNumberDatabase.addPhoneNumber(obj.getString(SQL_COLUMN_ID),
                    telno,
                    obj.getString(SQL_COLUMN_NOTES), true);
            Log.i(TAG, "Added " + telno);
        } catch (JSONException ex) {
            Log.e(TAG, "addPhoneNumber", ex);
            callbackContext.error(ex.getMessage());
            return;
        }

        callbackContext.success("Registered " + telno);
    }

    private void removePhoneNumber(JSONArray args, CallbackContext callbackContext) {
        PhoneNumber phoneNumber;
        try {
            JSONObject obj = args.getJSONObject(0);
            phoneNumber = phoneNumberDatabase.removePhoneNumber(obj.getString(SQL_COLUMN_ID));
            if (null == phoneNumber) {
                Log.w(TAG, "Not registered " + id);
                callbackContext.error(id + " is not registered");
                return;
            }
            Log.i(TAG, "Removed " + phoneNumber.getPhoneNumber());
        } catch (JSONException ex) {
            Log.e(TAG, "removePhoneNumber", ex);
            callbackContext.error(ex.getMessage());
            return;
        }

        callbackContext.success("Removed " + phoneNumber.getPhoneNumber());
    }
}
