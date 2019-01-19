package com.infinity.bluetooth.bluetooth.services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.infinity.bluetooth.bluetooth.BluetoothApplicationContext;
import com.infinity.bluetooth.bluetooth.events.CloseConnectionEvent;
import com.infinity.bluetooth.bluetooth.events.DeviceDiscoveryEvent;
import com.infinity.bluetooth.bluetooth.events.EnableBluetoothEvent;
import com.infinity.bluetooth.bluetooth.events.PairedDevicesEvent;
import com.infinity.bluetooth.bluetooth.events.SendMessageEvent;
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
                EventBus.getDefault().post(new PairedDevicesEvent());
            }
        }
    }

    @Subscribe
    public void sendingEvent(SendMessageEvent messageEvent){
        OutputStream outputStream;
        try {
            outputStream = applicationContext.getBluetoothSocket().getOutputStream();
            outputStream.write(messageEvent.getMessage());
        } catch (IOException connectException) {
            EventBus.getDefault().post(new CloseConnectionEvent());
        }
        String deviceName = applicationContext.getCurrentDevice().getName();
        Log.i(TAG, "Sending message to device:" + deviceName);
    }

    @Subscribe
    public void closeConnectEvent(CloseConnectionEvent closeConnectionEvent){
        try {
            OutputStream outputStream = applicationContext.getBluetoothSocket().getOutputStream();
            outputStream.close();
            applicationContext.getBluetoothSocket().close();
        } catch (IOException closeException) {
            Log.e(TAG, "Could not close the client socket", closeException);
        }
        String deviceName = applicationContext.getCurrentDevice().getName();
        Log.i(TAG, "Connection to device:" + deviceName + " closed");
    }

    @Subscribe
    public void pairedDevicesEvent(PairedDevicesEvent devicesEvent){
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

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        EventBus.getDefault().unregister(this);
    }
}
