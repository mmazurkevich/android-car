package com.infinity.bluetooth.bluetooth.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.bluetooth.bluetooth.R;
import com.infinity.bluetooth.bluetooth.events.CloseConnectionEvent;
import com.infinity.bluetooth.bluetooth.events.SendMessageEvent;

import org.greenrobot.eventbus.EventBus;

import io.github.controlwear.virtual.joystick.android.JoystickView;

/**
 * Created by infinity on 05.03.17.
 */

public class ControlFragment extends Fragment{

    private static final String TAG = "ControlFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        JoystickView joystick = (JoystickView) view.findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                Log.d(TAG, "Angle: " + angle + " Strength: " + strength);
                if (angle == 0) {
                    EventBus.getDefault().post(new SendMessageEvent(0));
                } else {
                    EventBus.getDefault().post(new SendMessageEvent(angle/2));
                }
                EventBus.getDefault().post(new SendMessageEvent(strength));
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        EventBus.getDefault().post(new CloseConnectionEvent());
        super.onPause();
    }
}
