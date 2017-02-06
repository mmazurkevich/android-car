package com.infinity.bluetooth.bluetooth;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by mmazurke on 06.02.2017.
 */
public class BluetoothApplicationContext {

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothApplicationContext() {
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter == null ? BluetoothAdapter.getDefaultAdapter() : bluetoothAdapter;
    }

    private static BluetoothApplicationContext ourInstance = new BluetoothApplicationContext();

    public static BluetoothApplicationContext getInstance() {
        return ourInstance;
    }

}
