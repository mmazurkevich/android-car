package com.infinity.bluetooth.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.infinity.bluetooth.bluetooth.views.adapters.BluetoothDeviceAdapter;

import java.util.List;
import java.util.UUID;

/**
 * Created by mmazurke on 06.02.2017.
 */
public class BluetoothApplicationContext {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private List<BluetoothDevice> deviceList;
    private BluetoothDevice currentDevice;
    private BluetoothDeviceAdapter devicesAdapter;
    private UUID bluetoothUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static BluetoothApplicationContext ourInstance = new BluetoothApplicationContext();

    private BluetoothApplicationContext() {
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter == null ? BluetoothAdapter.getDefaultAdapter() : bluetoothAdapter;
    }

    public UUID getBluetoothUUID() {
        return bluetoothUUID;
    }

    public BluetoothDevice getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(BluetoothDevice currentDevice) {
        this.currentDevice = currentDevice;
    }

    public List<BluetoothDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<BluetoothDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }

    public void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
        this.bluetoothSocket = bluetoothSocket;
    }

    public static BluetoothApplicationContext getInstance() {
        return ourInstance;
    }

    public BluetoothDeviceAdapter getDevicesAdapter() {
        return devicesAdapter;
    }

    public void setDevicesAdapter(BluetoothDeviceAdapter devicesAdapter) {
        this.devicesAdapter = devicesAdapter;
    }
}
