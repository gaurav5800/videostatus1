package com.apnasapnamoney.videostatus.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.apnasapnamoney.videostatus.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/*import testdroid.example.com.loneworker.Controls.Alerts.ForbiddenAreaAlert;*/


/**
 * This class define common utils which are used throughout the
 * Application like Dialogs, Toasts, Internet checks etc.
 *
 * @author jindaldipanshu
 * @version 1.0
 */
public class Utilities {

    public static boolean isDialogShowing = false;
    public static Dialog distressDialog;
    public static CountDownTimer distressTimer = null;
    public static Dialog alertDialog;


    public static String ratingReviews = "Rating & Reviews";
    public static ArrayList<String> timingSlots;

    /**
     * This method is used to round off a value to one decimal place.
     *
     * @param doubleValue_str - Value to round-off.
     * @return Rounded-off value in double.
     */
    public static double getRoundValue(String doubleValue_str) {
        double returnValue;
        try {
            BigDecimal bigDecimal = new BigDecimal(doubleValue_str);
            bigDecimal = bigDecimal.divide(new BigDecimal("1"), 1,
                    BigDecimal.ROUND_HALF_UP);
            returnValue = Double.parseDouble(bigDecimal.toString());
        } catch (Exception e) {
            returnValue = 0.0;
        }
        return returnValue;
    }

    public static void dismissDialog(Dialog dialog) {

        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

    /**
     * This method is used to round off a value to one decimal place.
     *
     * @param doubleValue_str - Value to round-off.
     * @return Rounded-off value in double.
     */
    public static float getRoundValueOfTwoDigit(String doubleValue_str) {
        float returnValue;
        try {
            BigDecimal bigDecimal = new BigDecimal(doubleValue_str);
            bigDecimal = bigDecimal.divide(new BigDecimal("1"), 2,
                    BigDecimal.ROUND_HALF_UP);
            returnValue = Float.parseFloat(bigDecimal.toString());
        } catch (Exception e) {
            returnValue = 0.0f;
        }
        return returnValue;
    }


    /**
     * This method checks if the device has an active internet
     * connection or not.
     *
     * @param activity - Activity where this dialog will be shown
     * @return Returns true if there is internet connectivity
     */
    public static Boolean checkInternet(Context activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else if (netInfo != null && (netInfo.getState() == NetworkInfo.State.DISCONNECTED || netInfo.getState() == NetworkInfo.State.DISCONNECTING || netInfo.getState() == NetworkInfo.State.SUSPENDED || netInfo.getState() == NetworkInfo.State.UNKNOWN)) {
            return false;
        } else {
            return false;
        }
    }


    /**
     * Display a small toast message to the user.
     *
     * @param activity - Activity where this toast will be shown
     * @param message  - Message to be shown when short toast is shown.
     */
    public static void showSmallToast(Activity activity, String message) {
        Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Display a small toast message to the user.
     *
     * @param mContext - Activity where this toast will be shown
     * @param message  - Message to be shown when long toast is shown.
     */
    public static void showlongToast(Context mContext, String message) {
        Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Display a small toast message to the user.
     *
     * @param activity - Activity where this toast will be shown
     */
    public static void showNoConnectivityToast(Activity activity) {
    }

    public static boolean isEmptyOrWhitespace(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Display an login dialog to the user.
     *
     * @param context - Context of the activity
     */
    @SuppressWarnings("deprecation")
    public static void showNoInternetMsg(Context context) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        AlertDialog a = b.create();
        // a.setIcon(R.drawable.camera_icon);
        a.setTitle(context.getResources().getString(R.string.app_name));
        //TODO    a.setMessage(context.getResources().getString(R.string.no_internet_msg));
        a.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        try {
            a.show();
        } catch (Exception e) {
        }

    }

    /**
     * @param context - Context of the activity
     * @param title   - Title which will be shown on top of Alert Dialog
     * @param message - Message to be shown when Alert dialog is shown
     */
    @SuppressWarnings("deprecation")
    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        AlertDialog a = b.create();
        // a.setIcon(R.drawable.camera_icon);
        a.setTitle(title);
        a.setMessage(message);
        a.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        try {
            a.show();
        } catch (Exception e) {
        }
    }


    /**
     * Verify an email address.
     *
     * @param email - Email Address that need to be verified
     * @return Returns true if email is valid.
     */
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    //public static GoogleCloudMessaging gcm;
    public static String regid;

    public static void showMessage(final Context context, String message) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        AlertDialog a = b.create();
        // a.setIcon(R.drawable.camera_icon);
        //TODO   a.setTitle(context.getString(R.string.message_title));
        a.setMessage(message);
        a.setTitle("Rconnekt");
        a.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        try {
            a.show();
        } catch (Exception e) {
        }

    }

    public static int getScreenOrientation(Activity activity) {

        int SCREEN_ORIENTATION = 0;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                SCREEN_ORIENTATION = 0;
                break;
            case Surface.ROTATION_90:
                SCREEN_ORIENTATION = 1;
                break;
            case Surface.ROTATION_180:
                SCREEN_ORIENTATION = 0;
                break;
            case Surface.ROTATION_270:
                SCREEN_ORIENTATION = 3;
                break;
        }

        return SCREEN_ORIENTATION;
    }


    public static void hideKeypad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * This method is used to  hideKeyboard Automatically
     *
     * @param activity :context of activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public static void vibratePhone(Activity activity) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 200 milliseconds
        v.vibrate(2000);
    }

    public static void showDialog(Dialog dialog) {
        try {
            dialog.show();
        } catch (Exception e) {
            Log.e("dialog Show exception--", e + "");
        }
    }

    public static void turnGPSOn(Context ctx) {
        try {
            Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
            intent.putExtra("enabled", true);
            ctx.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            ctx.sendBroadcast(poke);
        }
    }

    // automatic turn off the gps
    public void turnGPSOff(Context ctx) {
        String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.contains("gps")) { //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            ctx.sendBroadcast(poke);
        }
    }
}
