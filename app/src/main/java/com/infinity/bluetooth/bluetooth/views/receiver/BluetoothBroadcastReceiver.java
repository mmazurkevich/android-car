package com.infinity.bluetooth.bluetooth.views.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.infinity.bluetooth.bluetooth.BluetoothApplicationContext;
import com.infinity.bluetooth.bluetooth.views.adapters.BluetoothDeviceAdapter;


public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "BluetoothBroadcastReceiver";

    /**
     * Discovery has found a device. Get the BluetoothDevice
     * object and its info from the Intent.
     *
     * @param context global information about an application environment.
     * @param intent an intent is an abstract description of an operation to be performed
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        BluetoothDeviceAdapter devicesAdapter = BluetoothApplicationContext.getInstance().getDevicesAdapter();
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            devicesAdapter.add(device);
            String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress();
            Log.i(TAG, "Founded nearest device name: " + deviceName + "  mac: " + deviceHardwareAddress);
        }
    }
}
