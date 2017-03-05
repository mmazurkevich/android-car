package com.infinity.bluetooth.bluetooth.events;

import android.util.Log;

/**
 * Created by infinity on 05.03.17.
 */

public class SendMessageEvent {

    private final String TAG = "SendMessageEvent";
    private String message;

    public SendMessageEvent(String message) {
        this.message = message;
        Log.i(TAG, "Sending message: " + message + " to device");

    }

    public String getMessage() {
        return message;
    }
}
