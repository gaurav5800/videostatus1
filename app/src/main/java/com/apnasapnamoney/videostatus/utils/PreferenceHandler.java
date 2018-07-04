package com.apnasapnamoney.videostatus.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

/**
 * Common PrefrenceConnector class for storing preference values.
 * 
 * @author jminhas1
 * @version 1.0 Build
 */
public class PreferenceHandler {
	private SharedPreferences prefs;
	public static final String FIREBASE_CLOUD_MESSAGING = "fcm";
	public static final String SET_NOTIFY = "set_notify";
	public static final String PREF_NAME = "SECURINDOOR_PREFERENCES";
	public static final int MODE = Context.MODE_PRIVATE;

	public static final String SLIDE_SHOW_COMPLETE = "complete";
	public static final String GCM_TOKEN = "token";
	public static final String PROFILE_PIC = "pic";
	public static final String USER_ID= "user_id";
	public static final String USER_ID_1= "user_id_1";
	public static final String USER_ID_2= "user_id_2";
	public static final String USER_VERIFIED = "VERIFIED";
	public static final String Fb_Token = "fy";
	public  static  String BDAY_DAY="b'day";
	public  static  String BDAY_MONTH="b'month";
	public  static  String BDAY_YEAR="b'year";

     Context context;
	public PreferenceHandler(Context context){
		this.context = context;
		prefs = context.getSharedPreferences(FIREBASE_CLOUD_MESSAGING, Context.MODE_PRIVATE);
	}
	public static void writeBoolean(Context context, String key, boolean value) {
		getEditor(context).putBoolean(key, value).commit();

	}

	public static boolean readBoolean(Context context, String key,
			boolean defValue) {
		return getPreferences(context).getBoolean(key, defValue);
	}

	public static void writeInteger(Context context, String key, int value) {
		getEditor(context).putInt(key, value).commit();
	}

	public static int readInteger(Context context, String key, int defValue) {
		return getPreferences(context).getInt(key, defValue);
	}

	public static void writeString(Context context, String key, String value) {
		getEditor(context).putString(key, value).commit();
	}

	public static void writeStringSet(Context context, String key, Set value) {
		getEditor(context).putStringSet(key, value).commit();
	}

	public static Set<String> readStringSet(Context context, String key, Set defValue) {

		return getPreferences(context).getStringSet(key,defValue);
	}


	public static String readString(Context context, String key, String defValue) {
		return getPreferences(context).getString(key, defValue);
	}

	public static void writeFloat(Context context, String key, float value) {
		getEditor(context).putFloat(key, value).commit();
	}

	public static float readFloat(Context context, String key, float defValue) {
		return getPreferences(context).getFloat(key, defValue);
	}	
	
	public static void writeLong(Context context, String key, long value) {
		getEditor(context).putLong(key, value).commit();
	}

	public static long readLong(Context context, String key, long defValue) {
		return getPreferences(context).getLong(key, defValue);
	}

	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}
	public void saveNotificationSubscription(boolean value){
		Editor edits = prefs.edit();
		edits.putBoolean(SET_NOTIFY, value);
		edits.apply();
	}
	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}
} 
