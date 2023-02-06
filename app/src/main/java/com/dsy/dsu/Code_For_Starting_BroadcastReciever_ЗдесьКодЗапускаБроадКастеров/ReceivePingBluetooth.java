package com.dsy.dsu.Code_For_Starting_BroadcastReciever_ЗдесьКодЗапускаБроадКастеров;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

public class ReceivePingBluetooth extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        МетодЗапускаПингаУстройст(context,intent);
        Log.d("BroadcastActions", "Bluetooth is off");
    }

    private void МетодЗапускаПингаУстройст(@NonNull  Context c,@NonNull Intent i) {
        try{
        String action = i.getAction();
        Log.d("BroadcastActions", "Action "+action+"received");
        int state;
        BluetoothDevice bluetoothDevice;
        switch(action)
        {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                state = i.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                if (state == BluetoothAdapter.STATE_OFF)
                {
                 //   Toast.makeText(c, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is off");
                }
                else if (state == BluetoothAdapter.STATE_TURNING_OFF)
                {
                    //Toast.makeText(c, "Bluetooth is turning off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is turning off");
                }
                else if(state == BluetoothAdapter.STATE_ON)
                {
                    Log.d("BroadcastActions", "Bluetooth is on");
                }
                break;

            case BluetoothDevice.ACTION_ACL_CONNECTED:
                bluetoothDevice = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        /*        Toast.makeText(c, "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                                + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                                "  bluetoothDevice " +bluetoothDevice.getBluetoothClass(),
                        Toast.LENGTH_SHORT).show();*/
                Log.d("BroadcastActions", "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                        + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                        "  bluetoothDevice " +bluetoothDevice.getBluetoothClass());
                break;

            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                bluetoothDevice = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            /*    Toast.makeText(c, "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                                + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                                "  bluetoothDevice " +bluetoothDevice.getBluetoothClass(),
                        Toast.LENGTH_SHORT).show();*/
                Log.d("BroadcastActions", "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                        + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                        "  bluetoothDevice " +bluetoothDevice.getBluetoothClass());
                break;

            case BluetoothDevice.ACTION_UUID:
                bluetoothDevice = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
             /*   Toast.makeText(c, "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                                + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                                "  bluetoothDevice " +bluetoothDevice.getBluetoothClass(),
                        Toast.LENGTH_SHORT).show();*/
                Log.d("BroadcastActions", "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                        + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                        "  bluetoothDevice " +bluetoothDevice.getBluetoothClass());
                break;
            case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                bluetoothDevice = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            /*    Toast.makeText(c, "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                                + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                                "  bluetoothDevice " +bluetoothDevice.getBluetoothClass(),
                        Toast.LENGTH_SHORT).show();*/
                Log.d("BroadcastActions", "Connected to "+bluetoothDevice.getName() + " bluetoothDevice   "
                        + bluetoothDevice.getUuids()+ " bluetoothDevice " +bluetoothDevice.getAddress()+
                        "  bluetoothDevice " +bluetoothDevice.getBluetoothClass());
                break;
            default:
                Log.d("BroadcastActions", "Bluetooth is on action"+action);
            /*    Toast.makeText(c,  "Bluetooth is on action"+action,
                        Toast.LENGTH_SHORT).show();*/
                Log.d("BroadcastActions", "Bluetooth is on action"+action);
                break;
        }
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(c.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(c).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.d(c.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());

    }
    }
}