package com.apnasapnamoney.videostatus.interfaces;

import android.content.Context;

import java.util.Date;

/**
 * Created by JINDAL on 23-05-2017.
 */

public interface CallListener {
      //Derived classes should override these to respond to specific events of interest
        void onIncomingCallStarted(Context ctx, String number, Date start);
        void onOutgoingCallStarted(Context ctx, String number, Date start);
        void onIncomingCallEnded(Context ctx, String number, Date start, Date end);
        void onOutgoingCallEnded(Context ctx, String number, Date start, Date end);
        void onMissedCall(Context ctx, String number, Date start);
}