package com.infinity.bluetooth.bluetooth.connectors;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.infinity.bluetooth.bluetooth.BluetoothApplicationContext;
import com.infinity.bluetooth.bluetooth.events.OpenConnectionEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class BluetoothDeviceConnector extends Thread{

    private static final String TAG = "BluetoothDeviceConnector";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;

    public BluetoothDeviceConnector(BluetoothDevice device) {

        bluetoothAdapter = BluetoothApplicationContext.getInstance().getBluetoothAdapter();
        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(BluetoothApplicationContext
                    .getInstance().getBluetoothUUID());
            BluetoothApplicationContext.getInstance().setBluetoothSocket(bluetoothSocket);
            BluetoothApplicationContext.getInstance().setCurrentDevice(device);
        } catch (IOException e) {
            Log.e(TAG, "Socket's create failed", e);
        }
    }

    @Override
    public void run() {
        bluetoothAdapter.cancelDiscovery();
        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            bluetoothSocket.connect();
            BluetoothDevice device = BluetoothApplicationContext.getInstance().getCurrentDevice();
            Log.i(TAG, "Connected to device:" + device.getName());
            EventBus.getDefault().post(new OpenConnectionEvent());
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            try {
                bluetoothSocket.close();
            } catch (IOException closeException) {
                Log.e(TAG, "Could not close the client socket", closeException);
            }
        }
    }
}
