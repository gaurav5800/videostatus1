package com.apnasapnamoney.videostatus.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apnasapnamoney.videostatus.R;
import com.apnasapnamoney.videostatus.api.GetRestAdapter;
import com.apnasapnamoney.videostatus.interfaces.Core;
import com.apnasapnamoney.videostatus.interfaces.Disposable;
import com.apnasapnamoney.videostatus.utils.PreferenceKeys;
import com.apnasapnamoney.videostatus.utils.ProgressUtility;
import com.apnasapnamoney.videostatus.utils.SharedPrefsHelper;
import com.apnasapnamoney.videostatus.utils.Utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * This class is used to
 * To initialize Toolbar,views and set ToolBar title
 *
 * @author jindaldipanshu
 * @version 1.0
 */

public abstract class BaseActivity extends AppCompatActivity implements Disposable, Core {
    private ProgressDialog mDialog;
    private InputMethodManager inputMethodManager;
    public FrameLayout mFrameLayout, mSearchFramelayout;
    protected RelativeLayout mPagerLayout;
    public TextView mTitleTextView;
    public ImageButton mSearchBtn;
    private boolean isDualSim;
    private String mUserId;
    protected SharedPrefsHelper mSharedPrefsHelper;
    private File tempDir;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setMessage(getResources().getString(R.string.please_wait_meassge));
        File SDCardRoot = Environment.getExternalStorageDirectory();
        //create a new file, specifying the path, and the filename
        //which we want to save the file as.
        tempDir = new File(SDCardRoot + "/Howdee");
        if (!tempDir.exists())
            tempDir.mkdirs();
        onCreate();
    }

    /**
     * This method is used to hide keyboard
     */
    protected void hideKeyboard() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    /**
     * This method is used to get string value from strings file
     *
     * @param resId :resource id
     * @return string
     */
    public String getStringMessage(int resId) {
        return getResources().getString(resId);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void showDialog() {
        try {
            if (!isFinishing())
                ProgressUtility.showProgress(this, getStringValue(R.string.please_wait_meassge));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideDialog() {
        ProgressUtility.dismissProgress();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void onCreate() {
        initViews();
    }

    @Override
    public void showToast(int resId) {
        Toast.makeText(getApplicationContext(), getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public String getStringValue(int resId) {
        return getResources().getString(resId);
    }

    /**
     * To get Layout
     *
     * @return integer
     */
    protected abstract int getLayoutId();

    /**
     * To initialize Views
     */
    protected abstract void initViews();


    /**
     * @return toolbar title name
     */
    //  protected abstract String getHeaderTitle();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        inputMethodManager = null;
        mDialog = null;
    }

    /**
     * To get android phone id
     *
     * @return String
     */
    protected String getDeviceId() {
        String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId;
    }


    /**
     * This method is used to replace fragment .
     *
     * @param newFragment :replace an existing fragment with new fragment.
     * @param args        :pass bundle data fron one fragment to another fragment
     */
    public void replaceFragment(int frameLayout, Fragment newFragment, Bundle args) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        newFragment.setArguments(args);
        FragmentTransaction transaction = manager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(frameLayout, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }


    /**
     * This method is used to set toolbar header title
     *
     * @param id:title of tool bar
     */
    public void setHeaderTitle(String id) {
       /* mTitleTextView = (TextView) findViewById(R.id.tool_bar_title);
        mTitleTextView.setText(id);*/
    }

    public String getUserId() {
        if (mSharedPrefsHelper == null)
            mSharedPrefsHelper = new SharedPrefsHelper(this);
        String userId = mSharedPrefsHelper.get(PreferenceKeys.PREF_USER_ID);
        return userId;
    }

    protected void setAdapter(RecyclerView recyclerView, List<?> mList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }



    private final static String simSlotName[] = {
            "extra_asus_dial_use_dualsim",
            "com.android.phone.extra.slot",
            "slot",
            "simslot",
            "sim_slot",
            "subscription",
            "Subscription",
            "phone",
            "com.android.phone.DialingMode",
            "simSlot",
            "slot_id",
            "simId",
            "simnum",
            "phone_type",
            "slotId",
            "slotIdx"
    };


    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;


    public String Download(String Url, File file) {
        String filepath = null;
        try {
            //set the download URL, a url that points to a file on the internet
            //this is the file to be downloaded
            URL url = new URL(Url);
            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            //and connect!
            urlConnection.connect();
            //set the path where we want to save the file
            //in this cas   e, going to save it on the root directory of the
            //sd card.


            //String filename = "685821.mp3";   // you can download to any type of file ex:.jpeg (image) ,.txt(text file),.mp3 (audio file)
            //Log.i("Local filename:", "" + filename);

            //file = new File(tempDir, filename);
            if (file.createNewFile()) {
                file.createNewFile();
            }

            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(file);

            byte data[] = new byte[1024];
            long total = 0;
            int count = 0;
            while ((count = input.read(data)) != -1) {
                total++;
                Log.e("while", "A" + total);
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            Log.i("while", "Finished" + total);
            //catch some possible errors...
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("Malformed exception--", e.getLocalizedMessage());
        } catch (IOException e) {
            filepath = null;
            Log.e("IOException exception--", e.getLocalizedMessage());
            e.printStackTrace();
        }
        Log.i("filepath:", " " + filepath);
        return filepath;

    }


    protected void callFinish() {
    }

    public boolean isDualSim() {
        return isDualSim;
    }

    public void setDualSim(boolean dualSim) {
        isDualSim = dualSim;
    }
}
