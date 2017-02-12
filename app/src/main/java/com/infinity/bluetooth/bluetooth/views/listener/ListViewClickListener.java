package com.infinity.bluetooth.bluetooth.views.listener;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;

import com.infinity.bluetooth.bluetooth.connectors.BluetoothDeviceConnector;

public class ListViewClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = (BluetoothDevice)parent.getAdapter().getItem(position);
        BluetoothDeviceConnector thread = new BluetoothDeviceConnector(device);
        thread.run();
    }
}
