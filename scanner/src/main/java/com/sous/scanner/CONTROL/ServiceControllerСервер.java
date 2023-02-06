package com.sous.scanner.CONTROL;

import android.app.Activity;
import android.app.IntentService;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sous.scanner.BuildConfig;
import com.sous.scanner.MODEL.CREATE_DATABASEScanner;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import dsu1.scanner.myapplication.MODEL.SubClassErrors;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceControllerСервер extends IntentService {
    private SQLiteDatabase sqLiteDatabase;
    private CREATE_DATABASEScanner createDatabaseScanner;
    public LocalBinderСканнер binder = new LocalBinderСканнер();
    private Context context;
    private  String TAG;
    private  Handler handler;
    private ExecutorService executorServiceСканерСервер;
    public ServiceControllerСервер() {
        super("ServiceControllerКлиент");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        try{
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
       this. createDatabaseScanner =new CREATE_DATABASEScanner(context);
        this.sqLiteDatabase= createDatabaseScanner.getССылкаНаСозданнуюБазу();
        TAG=getClass().getName().toString();
        executorServiceСканерСервер =Executors.newCachedThreadPool();
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
    public class LocalBinderСканнер extends Binder {
        public ServiceControllerСервер getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceControllerСервер.this;
        }
        public void linkToDeath(DeathRecipient deathRecipient) {
             deathRecipient.binderDied();
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return   binder;

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
        try{
        if (rootIntent.getAction().equalsIgnoreCase("СерверЗакрываетСлужбу")) {
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            executorServiceСканерСервер.shutdown();
            stopSelf();
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=getApplicationContext();
       // МетодЗапускаОбщиеКоды(getApplicationContext(),intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
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


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }
    // TODO: 30.11.2022 сервер СКАНИРОВАНИЯ
    public void МетодКлиент(@NonNull Handler handler, @NonNull Activity activity){
            this.context=activity;
            this.handler=handler;
            executorServiceСканерСервер.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
              try{
                    SubClassЗапускаСканирования subClassЗапускаСканирования=new SubClassЗапускаСканирования();
                    subClassЗапускаСканирования.МетодЗапускаСканиваронияДляАндройд();
                    Log.w(this.getClass().getName(), "   loadInBackground  ");
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
                    return null;
                }
            });
    }
    // TODO: 30.11.2022 клиент СКАНИРОВАНИЯ
    public void МетодСерверАндройд(@NonNull Handler handler, @NonNull Context context){
            this.context=context;
            this.handler=handler;
            executorServiceСканерСервер.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                   try{
                    SubClassЗапускаСканирования subClassЗапускаСканирования=new SubClassЗапускаСканирования();
                    subClassЗапускаСканирования.МетодЗапускаСканиваронияДляАндройд();
                    Log.w(this.getClass().getName(), "   loadInBackground  ");
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
                    return null;
                }
            });
    }

    // TODO: 30.11.2022 клиент СКАНИРОВАНИЯ
    public void МетодСерверОбщий(@NonNull Handler handler, @NonNull Context context){
            this.context=context;
            this.handler=handler;
            executorServiceСканерСервер.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try{
                    SubClassЗапускаСканирования subClassЗапускаСканирования=new SubClassЗапускаСканирования();
                    subClassЗапускаСканирования.МетодЗапускаСканиваронияДляАндройд();
                    Log.w(this.getClass().getName(), "   loadInBackground  ");
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
                    return null;
                }
            });

    }

    // TODO: 25.09.2022 класс ДЛя Сканирование
  public    class SubClassReadAndWriteBluetooltЗаписьЧтениеДляСканнера extends AsyncQueryHandler {
      private   Uri uri;
      private String  Таблица;
        public SubClassReadAndWriteBluetooltЗаписьЧтениеДляСканнера(@NonNull Context context) {
            super(context.getContentResolver());
        }
        public SubClassReadAndWriteBluetooltЗаписьЧтениеДляСканнера(ContentResolver cr) {
            super(cr);
        }
        // TODO: 25.09.2022 запуск метода
        public Object МетодВставкаОбновлениеДляСканнер(@NonNull Intent intent){
            Object object = null;
            try{
                Integer Toren=new Random().nextInt();
                AsyncQueryHandler asyncQueryHandler=new AsyncQueryHandler(context.getContentResolver()) {
                    @Override
                    protected Handler createHandler(Looper looper) {
                        Log.w(this.getClass().getName(), "   createHandler  ");
                        return super.createHandler(looper);
                    }

                    @Override
                    public void startQuery(int token, Object cookie, Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
                        super.startQuery(token, cookie, uri, projection, selection, selectionArgs, orderBy);
                        Log.w(this.getClass().getName(), "   startQuery  ");
                    }

                    @Override
                    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                        super.onQueryComplete(token, cookie, cursor);
                        Log.w(this.getClass().getName(), "   onQueryComplete  ");
                    }

                    @Override
                    protected void onInsertComplete(int token, Object cookie, Uri uri) {
                        super.onInsertComplete(token, cookie, uri);
                        Log.w(this.getClass().getName(), "   onInsertComplete  ");
                    }

                    @Override
                    protected void onUpdateComplete(int token, Object cookie, int result) {
                        super.onUpdateComplete(token, cookie, result);
                        Log.w(this.getClass().getName(), "   onUpdateComplete  ");
                    }

                    @Override
                    protected void onDeleteComplete(int token, Object cookie, int result) {
                        super.onDeleteComplete(token, cookie, result);
                        Log.w(this.getClass().getName(), "   onDeleteComplete  "+Thread.currentThread().getName().toString()+ " result " +result);
                    }

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.w(this.getClass().getName(), "   handleMessage  "+msg+" " +Thread.currentThread().getName().toString());
                    }
                };
                // TODO: 29.11.2022 запуск операиции  Сканнер
                switch (intent.getAction()){
                    case  "Данные":
                        uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                        asyncQueryHandler.startDelete( Toren,new Object(),uri,"status_send=?",new String[]{"Удаленная"});
                        break;
                    case  "Вставка":
                        uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                        asyncQueryHandler.startDelete( Toren,new Object(),uri,"status_send=?",new String[]{"Удаленная"});
                        break;
                    case  "Обновление":
                        uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                        asyncQueryHandler.startDelete( Toren,new Object(),uri,"status_send=?",new String[]{"Удаленная"});
                        break;
                    case  "Удаление":
                        uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                        asyncQueryHandler.startDelete( Toren,new Object(),uri,"status_send=?",new String[]{"Удаленная"});
                        break;
                    default:
                        break;
                }

                        Log.w(this.getClass().getName(), "   Таблица  " +Таблица+ " intent.getAction() " +intent.getAction());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                ContentValues valuesЗаписываемОшибки=new ContentValues();
                valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы","ErrorDSU1");
                valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
                valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
                valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
                valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
                final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
                new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
            }

            return  object;
        }

    }

    private class SubClassЗапускаСканирования {
        public SubClassЗапускаСканирования() {
        }
        private void МетодЗапускаСканиваронияДляАндройд(){
            try{
                Message message = Message.obtain(handler);
                Bundle bundle=new Bundle();
                bundle.putString("КакоеДейтвие","ВыключаемPrograssbar");
                message.setData(bundle);
                message.setAsynchronous(true);
                handler.sendMessageDelayed(message,1000);
                Log.d(TAG, "МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android");
                Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  "+"\n+" +
                        ""+binder.isBinderAlive()+ " date "+new Date().toString().toString()+"" +
                        "\n"+" POOL "+Thread.currentThread().getName() +
                        "\n" + " ALL POOLS  " +Thread.getAllStackTraces().entrySet().size());

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
}