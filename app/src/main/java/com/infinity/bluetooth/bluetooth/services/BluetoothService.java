package com.infinity.bluetooth.bluetooth.services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.infinity.bluetooth.bluetooth.BluetoothApplicationContext;
import com.infinity.bluetooth.bluetooth.events.DeviceDiscoveryEvent;
import com.infinity.bluetooth.bluetooth.events.EnableBluetoothEvent;
import com.infinity.bluetooth.bluetooth.events.PairedDevicesEvent;
import com.infinity.bluetooth.bluetooth.views.adapters.BluetoothDeviceAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;


public class BluetoothService {

    private final String TAG = "BluetoothService";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDeviceAdapter deviceAdapter;
    private BluetoothApplicationContext applicationContext;

    public BluetoothService() {
        EventBus.getDefault().register(this);
        this.applicationContext = BluetoothApplicationContext.getInstance();
        this.bluetoothAdapter = applicationContext.getBluetoothAdapter();
        this.deviceAdapter = applicationContext.getDevicesAdapter();
    }

    public void enablingBluetooth(){
        //check that device doesn't support bluetooth
        if (bluetoothAdapter != null) {
            Log.i(TAG, "Device supported Bluetooth, try to enable it");
            //check that it is enabled
            if (!bluetoothAdapter.isEnabled()) {
                //intent for BT activation callback to onActivityResult()
                Log.i(TAG, "Sending intent to the user on enabling bluetooth");
                EventBus.getDefault().post(new EnableBluetoothEvent());
            } else {
                Log.i(TAG, "Bluetooth already enabled");
                getPairedDevices();
            }
        }
    }

    public void getPairedDevices() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                deviceAdapter.add(device);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i(TAG, "Paired device name: " + deviceName + "  mac: " + deviceHardwareAddress);
            }
        }
        EventBus.getDefault().post(new DeviceDiscoveryEvent());
    }

    public void sendMessage(){
        BluetoothSocket bluetoothSocket = applicationContext.getBluetoothSocket();
        OutputStream outputStream = null;
        try {
            outputStream = bluetoothSocket.getOutputStream();
            String str = "C";
            outputStream.write(str.getBytes());
            str = "0";
            outputStream.write(str.getBytes());
        } catch (IOException connectException) {
            closeConnect(outputStream);
        }finally {
            closeConnect(outputStream);
        }
    }

    private void closeConnect(OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException closeException) {
            Log.e(TAG, "Could not close the client socket", closeException);
        }
    }

    @Subscribe
    public void pairedDevicesEvent(PairedDevicesEvent devicesEvent){
        getPairedDevices();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBus.getDefault().unregister(this);
    }
}
