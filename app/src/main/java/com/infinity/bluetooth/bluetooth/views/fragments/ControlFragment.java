package com.infinity.bluetooth.bluetooth.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.infinity.bluetooth.bluetooth.R;
import com.infinity.bluetooth.bluetooth.events.CloseConnectionEvent;
import com.infinity.bluetooth.bluetooth.events.SendMessageEvent;
import com.infinity.bluetooth.bluetooth.views.listener.SeekBarOnChangeListener;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by infinity on 05.03.17.
 */

public class ControlFragment extends Fragment{

    public static final String UP = "0";
    public static final String DOWN = "1";
    public static final String LEFT = "2";
    public static final String RIGHT = "3";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        TextView speedProgress = (TextView) view.findViewById(R.id.speed_progress);
        TextView delayProgress = (TextView) view.findViewById(R.id.delay_progress);
        final SeekBar speedSeekBar = (SeekBar) view.findViewById(R.id.speed_seekBar);
        final SeekBar delaySeekBar = (SeekBar) view.findViewById(R.id.delay_seekBar);
        speedProgress.setText("0/"+String.valueOf(speedSeekBar.getMax()));
        delayProgress.setText("0/"+String.valueOf(delaySeekBar.getMax()));
        speedSeekBar.setOnSeekBarChangeListener(new SeekBarOnChangeListener(speedProgress));
        delaySeekBar.setOnSeekBarChangeListener(new SeekBarOnChangeListener(delayProgress));

        FloatingActionButton upButton =  (FloatingActionButton)view.findViewById(R.id.up_arrow_button);
        FloatingActionButton downButton =  (FloatingActionButton)view.findViewById(R.id.down_arrow_button);
        FloatingActionButton leftButton =  (FloatingActionButton)view.findViewById(R.id.left_arrow_button);
        FloatingActionButton rightButton =  (FloatingActionButton)view.findViewById(R.id.right_arrow_button);
        Button saveConfiguration = (Button) view.findViewById(R.id.save_configuration);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SendMessageEvent(UP));
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SendMessageEvent(DOWN));
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SendMessageEvent(LEFT));
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SendMessageEvent(RIGHT));
            }
        });
        saveConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault()
                        .post(new SendMessageEvent(speedSeekBar.getProgress() + "/" + delaySeekBar.getProgress()));
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
