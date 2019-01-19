package com.infinity.bluetooth.bluetooth.events;

import android.util.Log;

/**
 * Created by infinity on 05.03.17.
 */

public class SendMessageEvent {

    private final String TAG = "SendMessageEvent";
    private int message;

    public SendMessageEvent(int message) {
        this.message = message;
        Log.i(TAG, "Sending message: " + message + " to device");

    }

    public int getMessage() {
        return message;
    }
}
