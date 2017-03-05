package com.infinity.bluetooth.bluetooth.views.listener;

import android.app.Fragment;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;

import com.infinity.bluetooth.bluetooth.R;
import com.infinity.bluetooth.bluetooth.connectors.BluetoothDeviceConnector;
import com.infinity.bluetooth.bluetooth.views.fragments.ControlFragment;
import com.infinity.bluetooth.bluetooth.views.fragments.MainFragment;

public class ListViewClickListener implements AdapterView.OnItemClickListener {

    private Fragment fragment;

    public ListViewClickListener(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = (BluetoothDevice)parent.getAdapter().getItem(position);
        BluetoothDeviceConnector thread = new BluetoothDeviceConnector(device);
        thread.run();
        ControlFragment deviceControlFragment = new ControlFragment();
        fragment.getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, deviceControlFragment, "CONTROL_FRAGMENT")
                .addToBackStack(null)
                .commit();
    }
}
