package com.infinity.bluetooth.bluetooth.views.listener;

import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by infinity on 05.03.17.
 */

public class SeekBarOnChangeListener implements SeekBar.OnSeekBarChangeListener{

    private TextView progressTextView;

    public SeekBarOnChangeListener(TextView textView) {
        this.progressTextView = textView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressTextView.setText(progress + "/" + seekBar.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
