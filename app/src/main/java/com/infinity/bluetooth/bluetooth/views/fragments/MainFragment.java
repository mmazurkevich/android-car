package com.infinity.bluetooth.bluetooth.views.fragments;

import android.app.Fragment;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.infinity.bluetooth.bluetooth.BluetoothApplicationContext;
import com.infinity.bluetooth.bluetooth.R;
import com.infinity.bluetooth.bluetooth.services.BluetoothService;
import com.infinity.bluetooth.bluetooth.views.adapters.BluetoothDeviceAdapter;
import com.infinity.bluetooth.bluetooth.views.listener.ListViewClickListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView deviceList = (ListView) view.findViewById(R.id.device_list);
        BluetoothDeviceAdapter deviceAdapter = new BluetoothDeviceAdapter(getActivity(), new ArrayList<BluetoothDevice>());
        deviceList.setAdapter(deviceAdapter);
        BluetoothApplicationContext.getInstance().setDevicesAdapter(deviceAdapter);
        deviceList.setOnItemClickListener(new ListViewClickListener(this));
        BluetoothService bluetoothService = new BluetoothService();
        bluetoothService.enablingBluetooth();
        return view;
    }
}
