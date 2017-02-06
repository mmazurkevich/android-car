package com.infinity.bluetooth.bluetooth.events;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;

import com.infinity.bluetooth.bluetooth.services.BluetoothCommunicationService;

/**
 * Created by infinity on 04.02.17.
 */

public class ListViewClickListenerEvent implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(view);
        BluetoothDevice device = (BluetoothDevice)parent.getAdapter().getItem(position);
        BluetoothCommunicationService thread = new BluetoothCommunicationService(device);
        thread.run();
    }
}
