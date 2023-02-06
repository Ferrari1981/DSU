package com.dsy.dsu.Code_For_Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Get_Json_1C;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;

import java.util.Date;
import java.util.HashMap;


public class Service_Notificatios_Для_Согласования extends IntentService {////Service
    ////////
    private Integer PROCESS_IDСогласования;
    private  String ИмяСлужбыУведомленияДляСогласование;
    private  HashMap<String, String> hashMapХэшДляЗапоминиялUUID = new HashMap();
    private   PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    private  String ПолученныйНомерДокументаСогласования;
    private  Integer ПередаемСтатусСогласования;
    private  Bundle bundleДляПришлиВСлужбуСогласования;
    public LocalBinderДляСогласования binder = new LocalBinderДляСогласования();
    private  Context context;
    public Service_Notificatios_Для_Согласования() {
        super("Service_Notificatios_Для_Согласования");
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляСогласования extends Binder {
        public Service_Notificatios_Для_Согласования getService() {
            // Return this instance of LocalService so clients can call public methods
            return Service_Notificatios_Для_Согласования.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(   context.getClass().getName(), "     public IBinder onBind(@NonNull Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());//todo super.onBind(intent)
        return  binder ;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(   context.getClass().getName(), " onCreate СЛУЖБА Service_Notificatios_Для_Согласования "
                + " время: "
                + new Date());
    }

    @Override
    public void onDestroy() {
        Log.i(   context.getClass().getName(), "  \n" +
                "      onDestroy; Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА Service_Notificatios_Для_Согласования   ДЛЯ ЧАТА onDestroy() время " + new Date());
        super.onDestroy();
    }


// TODO: 16.11.2021


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try{
            this.context=   getApplicationContext();
        Log.i(   context.getClass().getName(), "    protected void onHandleWork(@NonNull Intent intent) { " + new Date()+ " intent " +intent+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());

        МетодЗапускаСогласованияВнутриСлужбы(intent,   context);

    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(   context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(   context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования onHandleWork Exception  PROCESS_ID   " + PROCESS_IDСогласования);

    }


    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(   context.getClass().getName(), "    public boolean onUnbind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(   context.getClass().getName(), "     public void onRebind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        this.context=newBase;
        Log.i(context.getClass().getName(), "  attachBaseContext      protected void attachBaseContext(Context newBase) {  " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }
    @Override
    public boolean stopService(Intent name) {

        Log.i(   context.getClass().getName(), "  \n" +
                "        stopService  Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА Service_Notificatios_Для_Согласования   ДЛЯ ЧАТА onDestroy() время " + new Date());

        /*  this.stopSelf();*/
        return super.stopService(name);
    }




    public StringBuffer МетодЗапускаСогласованияВнутриСлужбы(@Nullable Intent intent ,@NonNull Context context) {
        // TODO: 24.03.2022
        StringBuffer БуферРезультатаПолучаемОтветДанных =new StringBuffer();
        try {
            this.context=context;

        bundleДляПришлиВСлужбуСогласования = intent.getExtras();

        Log.d(context.getClass().getName(), " onStartCommand СЛУЖБА bundleДляПришлиВСлужбу ЗАДАЧА  " +
                bundleДляПришлиВСлужбуСогласования + " ntent.getAction() " + intent.getAction()+  "context " +context);

            // TODO: 24.03.2022
        if (intent.getAction().equalsIgnoreCase("ЗапускаемСогласованиеОтказИлилУспешное")) {
            PROCESS_IDСогласования = bundleДляПришлиВСлужбуСогласования.getInt("PROCESS_IDСогласования");
            Log.i(context.getClass().getName(), "" + " PROCESS_IDСогласования" + PROCESS_IDСогласования);
            ПолученныйНомерДокументаСогласования = bundleДляПришлиВСлужбуСогласования.getString("ПолученныйНомерДокументаСогласования", "");
            Log.i(context.getClass().getName(), "" + " ИмяСлужбыУведомленияДляЧата" + ИмяСлужбыУведомленияДляСогласование);
            ПередаемСтатусСогласования = bundleДляПришлиВСлужбуСогласования.getInt("ПередаемСтатусСогласования", 0);  // TODO: 24.03.2022
            Log.i(context.getClass().getName(), "" + " UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ " + ПередаемСтатусСогласования);
            // TODO: 27.03.2022  главный метод СМЕНЫ СТАТУСА СОГЛСОВАНИЯ ОТМЕНЫ
            БуферРезультатаПолучаемОтветДанных=    МетодГлавныйМетодСменыСтатусаСогласованияЧерезСлужбу(intent);
            Log.i(context.getClass().getName(), "" + " intent" + intent+ " БуферРезультатаПолучаемОтветДанных "+БуферРезультатаПолучаемОтветДанных);
            bundleДляПришлиВСлужбуСогласования.clear();
        }
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования onHandleWork Exception  PROCESS_ID   " + PROCESS_IDСогласования);

    }
        return  БуферРезультатаПолучаемОтветДанных;
// TODO: 26.03.2022 запуск код только при ЗАКРЫТИЕ
    }


    private StringBuffer МетодГлавныйМетодСменыСтатусаСогласованияЧерезСлужбу(@NonNull Intent intent) {
        // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        StringBuffer БуферРезультатаПолучаемОтветДанных =new StringBuffer();
        try {
            Log.i(context.getClass().getName(), "Service_Notificatios_Для_Согласования  " +
                    "........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " + new Date() + "\n" +
                    "  intent.getAction() " + intent.getAction());
           Integer ПубличныйIDДляCогласования = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
            // TODO: 07.02.2022  после выполНЕНИЯ ЗАДАЕНИЕ ОДНУЛЯЕМ uuid ДЛЯ СМЕНЫ СТАТУСА
            //TODO метоД СМЕНЫ СТАТУСА ПОЛЬЗОВАТЕЛЕМ КАК ОЗНАКОМЛЕННЫ
            Boolean РезультатСменыСтатусаСогласованийЧерезСлужбу = false;
            Log.i(context.getClass().getName(), "ПолученныйНомерДокументаСогласования " + ПолученныйНомерДокументаСогласования +
                    " ПередаемСтатусСогласования " + ПередаемСтатусСогласования + " ПубличныйIDДляCогласования " + ПубличныйIDДляCогласования);
            //TODO финальный метод СМЕНЫ СТАТУСА СОГЛАСОВАНИЙ
            //TODO тестовый код пинга 1с
             БуферРезультатаПолучаемОтветДанных =
                    new Class_Get_Json_1C(context,
                            "http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments")//TODO old debug "http://192.168.254.145/dsutest/hs/jsonto1c/listofdocuments"
                            .МетодОтправляемJSONОт1С(ПолученныйНомерДокументаСогласования,
                                    ПередаемСтатусСогласования,
                                    "action",ПубличныйIDДляCогласования);
            // TODO  http://192.168.254.145/TestWLS/hs/jsonto1c/get/  "http://192.168.254.145/TestWLS/hs/jsonto1c/Test"*/
            Log.i(context.getClass().getName(), "БуферРезультатаПолучаемОтветДанных " + БуферРезультатаПолучаемОтветДанных);
            //todo метод после получение данных запускам локальный бродкастер
            Log.i(context.getClass().getName(), "РезультатСменыСтатусаСогласованийЧерезСлужбу" + РезультатСменыСтатусаСогласованийЧерезСлужбу+
                    "  БуферРезультатаПолучаемОтветДанных " +БуферРезультатаПолучаемОтветДанных);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования PROCESS_ID   " + PROCESS_IDСогласования);
        }
        ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет
 return  БуферРезультатаПолучаемОтветДанных;

    }


}