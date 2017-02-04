package com.infinity.bluetooth.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by infinity on 04.02.17.
 */

public class ListViewClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(view);
        BluetoothDevice device = (BluetoothDevice)parent.getAdapter().getItem(position);
        ConnectThread thread = new ConnectThread(device);
        thread.run();
    }
}
