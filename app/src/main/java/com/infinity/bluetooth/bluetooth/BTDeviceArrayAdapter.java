package com.infinity.bluetooth.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by infinity on 26.01.17.
 */

public class BTDeviceArrayAdapter extends ArrayAdapter<BluetoothDevice> {

    private Context context;
    private List<BluetoothDevice> deviceList;

    public BTDeviceArrayAdapter(Context context, List<BluetoothDevice> deviceList) {
        super(context, R.layout.row, deviceList);
        this.context = context;
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        TextView deviceName = (TextView) rowView.findViewById(R.id.deviceName);
        TextView deviceMac = (TextView) rowView.findViewById(R.id.deviceMac);
        deviceName.setText(deviceList.get(position).getName());
        deviceMac.setText(deviceList.get(position).getAddress());
        return rowView;
    }
}
