package com.sous.serverscanner.CONTROL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sous.scanner.BuildConfig;
import com.sous.scanner.MODEL.CREATE_DATABASEScanner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import dsu1.scanner.myapplication.MODEL.SubClassErrors;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceControllerКлиент extends IntentService {
    private SQLiteDatabase sqLiteDatabase;
    private CREATE_DATABASEScanner createDatabaseScanner;
    public LocalBinderСканнер binder = new LocalBinderСканнер();
    private Context context;
    private Activity activity;
    private String TAG;
    private Handler handler;
    private ExecutorService executorServiceСканер;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices=new HashSet<>();

    public ServiceControllerКлиент() {
        super("ServiceControllerКлиент");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            this.createDatabaseScanner = new CREATE_DATABASEScanner(context);
            this.sqLiteDatabase = createDatabaseScanner.getССылкаНаСозданнуюБазу();
            TAG = getClass().getName().toString();
            executorServiceСканер = Executors.newCachedThreadPool();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

    public class LocalBinderСканнер extends Binder {
        public ServiceControllerКлиент getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceControllerКлиент.this;
        }

        public void linkToDeath(DeathRecipient deathRecipient) {
            deathRecipient.binderDied();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return binder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        try {
            if (rootIntent.getAction().equalsIgnoreCase("КлиентЗакрываетСлужбу")) {
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                executorServiceСканер.shutdown();
                stopSelf();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" + "\n" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " + "\n" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            this.context = getApplicationContext();
            // МетодЗапускаОбщиеКоды(getApplicationContext(),intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = newBase;
        super.attachBaseContext(newBase);
    }

    // TODO: 30.11.2022 сервер СКАНИРОВАНИЯ
    public void МетодКлиент(@NonNull Handler handler, @NonNull Activity activity,@NonNull   BluetoothManager     bluetoothManager) {
        this.context = activity;
        this.activity = activity;
        this.handler = handler;
        this.bluetoothManager=bluetoothManager;
        // TODO: 08.12.2022 уснатавливаем настройки Bluetooth

        Log.w(this.getClass().getName(), "   bluetoothManager  "+bluetoothManager+ " bluetoothAdapter " +bluetoothAdapter);
              try{
               МетодЗапускаСканированиеКлиент();
                    Log.w(this.getClass().getName(), "   МетодКлиент  ");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ContentValues valuesЗаписываемОшибки=new ContentValues();
                    valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
                    valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
                    valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
                    valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
                    final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                    Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                    valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
                    new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                }

    }
    // TODO: 08.12.2022 Метод Сервер
        @SuppressLint("MissingPermission")
        private void МетодЗапускаСканированиеКлиент(){
            try{
                Message message = Message.obtain(handler);
                Bundle bundle=new Bundle();
                bundle.putString("КакоеДейтвие","ВыключаемPrograssbar");
                message.setData(bundle);
                message.setAsynchronous(true);
                handler.sendMessageDelayed(message,1000);
                Log.d(TAG, "МетодЗапускаСканированиеКлиент: Запускаем.... Метод Сканирования Для Android");
                Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  "+"\n+" +
                        ""+binder.isBinderAlive()+ " date "+new Date().toString().toString()+"" +
                        "\n"+" POOL "+Thread.currentThread().getName() +
                        "\n" + " ALL POOLS  " +Thread.getAllStackTraces().entrySet().size());
                // TODO: 08.12.2022 сканирование Bluetooth
                bluetoothAdapter = bluetoothManager.getAdapter();
                bluetoothAdapter.enable();
               // bluetoothAdapter.startDiscovery();

                String[] АдресаBluetoothСерверов = {"BC:61:93:E6:F2:EB"};///TODO  служебный xiaomi "BC:61:93:E6:F2:EB", МОЙ XIAOMI FC:19:99:79:D6:D4  //////      "BC:61:93:E6:E2:63","FF:19:99:79:D6:D4"


                for (int i = 0; i < АдресаBluetoothСерверов.length; i++) {
                    pairedDevices.add(bluetoothAdapter.getRemoteDevice(АдресаBluetoothСерверов[i])) ;
                    UUID uuid=        ParcelUuid.fromString("00000000-0000-1000-8000-00805f9b34fb").getUuid();
                    Log.d(this.getClass().getName()," pairedDevices " +pairedDevices  + "uuid "+uuid );
                }
                //  pairedDevices = bluetoothAdapter.getBondedDevices();

                // TODO: 25.01.2023 ПЕРВЫЙ ВАРИАНТ СЕРВЕР gatt
             BluetoothGattCallback bluetoothGattCallback =
                        new BluetoothGattCallback() {
                            @Override
                            public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                                int newState) {
                                switch (newState){
                                    case BluetoothProfile.STATE_CONNECTED :
                                        Log.i(TAG, "Connected to GATT server.");
                                        break;
                                    case BluetoothProfile.STATE_DISCONNECTED :
                                        Log.i(TAG, "Connected to GATT server.");
                                        break;
                                    default:
                                        Log.i(TAG, "Connected to GATT server.");
                                        break;
                                }
                            }
                        };
                Log.d(this.getClass().getName(), "\n"
                        + " pairedDevices.size() " + pairedDevices.size());

                pairedDevices.forEach(new Consumer<BluetoothDevice>() {
                    @Override
                    public void accept(BluetoothDevice bluetoothDevice) {
                        bluetoothDevice.connectGatt(context,false,bluetoothGattCallback,BluetoothDevice.TRANSPORT_AUTO);
                        Log.d(this.getClass().getName(), "\n" + " bluetoothDevice" + bluetoothDevice);
                        int bondstate = bluetoothDevice.getBondState();
                        switch (bondstate){
                            case BluetoothDevice. BOND_NONE://Указывает, что удаленное устройство не связано (сопряжено).
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);//Указывает, что удаленное устройство не связано (сопряжено).
                                break;
                            case BluetoothDevice. BOND_BONDING://Указывает на то, что выполняется связывание (спаривание) с удаленным устройством.
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                            case BluetoothDevice. BOND_BONDED://Указывает, что удаленное устройство связано (сопряжено).
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                            case BluetoothDevice. DEVICE_TYPE_UNKNOWN://Тип устройства Bluetooth, Неизвестно
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                            default:
                                Log.i(TAG, "Disconnected from GATT server. newState "+bondstate);
                                break;
                        }
                    }
                });







           /*     // TODO: 25.01.2023 второй вариант сервер GATT
                pairedDevices.forEach(new Consumer<BluetoothDevice>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void accept(BluetoothDevice bluetoothDevice) {
                        Log.d(this.getClass().getName(), "\n"
                                + " bluetoothDevice.getName()  " + bluetoothDevice.getName() + " bluetoothDevice.getAddress() " +bluetoothDevice.getAddress() );
                        int bondstate = bluetoothDevice.getBondState();


                        switch (bondstate){
                            case BluetoothDevice. BOND_NONE://Указывает, что удаленное устройство не связано (сопряжено).
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);//Указывает, что удаленное устройство не связано (сопряжено).
                                break;
                            case BluetoothDevice. BOND_BONDING://Указывает на то, что выполняется связывание (спаривание) с удаленным устройством.
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                            case BluetoothDevice. BOND_BONDED://Указывает, что удаленное устройство связано (сопряжено).
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                            case BluetoothDevice. DEVICE_TYPE_UNKNOWN://Тип устройства Bluetooth, Неизвестно
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;

                            default:
                                Log.i(TAG, "Disconnected from GATT server. bondstate "+bondstate);
                                break;
                        }
                        if(bondstate ==BluetoothDevice. BOND_NONE || bondstate ==BluetoothDevice.  BOND_BONDED){
                            Log.i(TAG, "Disconnected from GATT server.");
                        }
                        BluetoothGatt gatt = bluetoothDevice.connectGatt(context, true, new BluetoothGattCallback() {
                            @Override
                            public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                                super.onPhyUpdate(gatt, txPhy, rxPhy, status);
                            }

                            @Override
                            public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                                super.onPhyRead(gatt, txPhy, rxPhy, status);
                            }

                            @Override
                            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                                super.onConnectionStateChange(gatt, status, newState);
                                if (newState ==  BluetoothProfile.STATE_CONNECTED) {
                                    // successfully connected to the GATT Server
                                    Log.i(TAG, "Disconnected from GATT server.");
                                } else if (newState ==  BluetoothProfile.STATE_DISCONNECTED) {
                                    // disconnected from the GATT Server
                                    Log.i(TAG, "Disconnected from GATT server.");
                                }
                            }

                            @Override
                            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                                super.onServicesDiscovered(gatt, status);
                                if (status == BluetoothGatt.GATT_SUCCESS) {
                                    List<BluetoothGattService> services = gatt.getServices();
                                    for (BluetoothGattService service : services) {
                                        List<BluetoothGattCharacteristic> characteristics =
                                                service.getCharacteristics();
                                        for (BluetoothGattCharacteristic characteristic : characteristics) {
                                            ///Once you have a characteristic object, you can perform read/write
                                            Log.i(TAG, "Disconnected from GATT server.");
                                            characteristic.setValue("СЛУЖБЕДЫЙ XIAOMI @@@@");
                                            characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
                                            gatt.writeCharacteristic(characteristic);
                                        }
                                    }
                                }

                            }

                            @Override
                            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                                super.onCharacteristicRead(gatt, characteristic, status);
                                byte[] value = characteristic.getValue();
                                gatt.setCharacteristicNotification(characteristic, true);
                                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(
                                        UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                              // mBluetoothGatt.writeDescriptor(descriptor);
                            }

                            @Override
                            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                                super.onCharacteristicWrite(gatt, characteristic, status);
                                gatt.readCharacteristic(characteristic);
                            }

                            @Override
                            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                                super.onCharacteristicChanged(gatt, characteristic);
                                byte[] newValue = characteristic.getValue();

                            }

                            @Override
                            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                                super.onDescriptorRead(gatt, descriptor, status);
                            }

                            @Override
                            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                                super.onDescriptorWrite(gatt, descriptor, status);
                            }

                            @Override
                            public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                                super.onReliableWriteCompleted(gatt, status);
                            }

                            @Override
                            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                                super.onReadRemoteRssi(gatt, rssi, status);
                            }

                            @Override
                            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                                super.onMtuChanged(gatt, mtu, status);
                            }

                            @Override
                            public void onServiceChanged(@NonNull BluetoothGatt gatt) {
                                super.onServiceChanged(gatt);
                            }
                        }, BluetoothDevice.TRANSPORT_AUTO, new Random().nextInt(), handler);
                    }
                });*/
            } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки=new ContentValues();
            valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        }
    }
