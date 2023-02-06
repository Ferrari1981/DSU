package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.customtabs.ICustomTabsCallback;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.multiprocess.RemoteCallback;

import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;


import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class MyWork_Async_Синхронизация_Одноразовая extends Worker {
    private  Context context;
    private  String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data Disposable";
 @Inject
   private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal  class_generation_sendBroadcastReceiver_and_firebase_oneSignallass ;
    private   Integer РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне=0;
    private Service_For_Remote_Async serviceForTabelAsync;
    private  Messenger           messengerWorkManager;
    private     IBinder binder;
   private ExecutorService executorService= Executors.newSingleThreadExecutor();
    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Одноразовая(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        this.context = context;
        Log.i(this.context.getClass().getName(), " public  " +
                "MyWork_Async_Синхронизация_Одноразовая(@NonNull Context context, @NonNull WorkerParameters workerParams) " +
                "{  КонтекстОдноразовая "+"\n"+ this.context);
            МетодПодключениекСлубе();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    private void МетодПодключениекСлубе() {
        try{
        Intent intentОбноразоваяСинхронизациия = new Intent(getApplicationContext(), Service_For_Remote_Async.class);
        getApplicationContext().    bindService(intentОбноразоваяСинхронизациия, Context.BIND_AUTO_CREATE,executorService , new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try{
                    messengerWorkManager =new Messenger(service);
                    Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                            + "onServiceConnected  одноразовая messengerActivity  " + messengerWorkManager.getBinder().pingBinder());
                    binder=   messengerWorkManager.getBinder();
                    if (binder.isBinderAlive()) {
                        getTaskExecutor().postToMainThread(()->{
                            serviceForTabelAsync=new Service_For_Remote_Async();
                        });
                    }
                    executorService.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                        + "onServiceConnected  одноразовая  messengerActivity  " );
            }
        });
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(context.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @NonNull
    @Override
    public TaskExecutor getTaskExecutor() {
        return super.getTaskExecutor();
    }

    @NonNull
    @Override
    public Result doWork() {
        Data    myDataОтветОдноразовойСлужбы=null;
 try{
     class_generation_sendBroadcastReceiver_and_firebase_oneSignallass=
             new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext());
     // TODO: 12.10.2022  КТО ЗАПУСТИЛ
      Integer ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle = getInputData().getInt("СообщениеЧатаДляКонктерногоСотрудника",0);

     Log.i(context.getClass().getName(), "ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle"+"\n" + ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);

     if (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle>0) {
         ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                 (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
         String АктивностьЕслиЕстьTOP =null;
         // TODO: 03.02.2022
         List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
         Integer ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
         if (ПубличныйIDДляФрагмента == null) {
             // TODO: 01.01.2022
             ПубличныйIDДляФрагмента = 0;
         }
         Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n" + ПубличныйIDДляФрагмента);
         if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
             // TODO: 24.11.2021
             КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();

         if (КоличествоЗапущенныйПроуессы.size() > 0) {
             // TODO: 01.12.2021
             for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                 if (ТекущаяАктивти!=null) {
                     Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                             "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                             + ТекущаяАктивти.getTaskInfo().numActivities);
                     // TODO: 20.02.2022
                     if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                         АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                     }

                     Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                             " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP +
                             "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                             + ТекущаяАктивти.getTaskInfo().numActivities);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                 }
                 if (АктивностьЕслиЕстьTOP!=null ) {
                     switch (АктивностьЕслиЕстьTOP) {// case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App" :
                    //     case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                         case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Tabels_Users_And_Passwords":
                         case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                             Log.i(context.getClass().getName(), " ВЫХОД  .....ТекущаяАктивтиАктивностьЕслиЕстьTOP" + ТекущаяАктивти +
                                     " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                             break;
                         // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                         default:
                             Log.i(context.getClass().getName(), " СРАБОТАЛО .....ТекущаяАктивти " + ТекущаяАктивти +
                                     " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                             Log.i(context.getClass().getName(), " СРАБОТАЛО .....Текущий Фрагмент  ПроверкаНаАктивностьФрагментаЧата ");////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                             // TODO: 03.02.2022 запуск синхрониазции внутри одноразвого ворк менеджера
                             МетодЗапускаКодаВнутриОдноразовойСинхронизации
                                     (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
                                             ПубличныйIDДляФрагмента,
                                             КоличествоЗапущенныйПроуессы, АктивностьЕслиЕстьTOP);
                             Log.i(context.getClass().getName(), "ЗАПУСК  АКТИВТИТИ ЕСТЬ НО ОПРЕДЕЛЕННАЯ  ВНУТРИ метода        " + "\n" +
                                     "  АктивностьЕслиЕстьTOP " + АктивностьЕслиЕстьTOP +
                                     " public Result doWork()   MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                                     + new Date() +
                                     " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС КоличествоЗапущенныйПроуессы.size() " + КоличествоЗапущенныйПроуессы.size()
                                     + "\n" +
                                     "  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне " + РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне);
                             ///////todo  КОНЕЦ  код запуска уведомлений для чата
                             break;
                     }
                 }else{
                     // TODO: 03.02.2022 запуск синхрониазции внутри одноразвого ворк менеджера  реско не сталь активти
                     МетодЗапускаКодаВнутриОдноразовойСинхронизации
                             (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
                                     ПубличныйIDДляФрагмента,
                                     КоличествоЗапущенныйПроуессы, АктивностьЕслиЕстьTOP);

                     Log.i(context.getClass().getName(), "ЗАПУСК   СТРОГО В ФОНЕ  О ОПРЕДЕЛЕННАЯ  ВНУТРИ метода     РАВНО 0    " + "\n" +
                             " public Result doWork()   MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                             + new Date() +
                             " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС КоличествоЗапущенныйПроуессы.size() " + КоличествоЗапущенныйПроуессы.size()
                             + "\n" +
                             "  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне " + РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне +
                             " \n" +"  АктивностьЕслиЕстьTOP " +АктивностьЕслиЕстьTOP);
                 }
             }
         } else {
             // TODO: 03.02.2022 запуск синхрониазции внутри одноразвого ворк менеджера
             МетодЗапускаКодаВнутриОдноразовойСинхронизации
                     (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
                             ПубличныйIDДляФрагмента,
                             КоличествоЗапущенныйПроуессы, АктивностьЕслиЕстьTOP);
             Log.i(context.getClass().getName(), "ЗАПУСК   СТРОГО В ФОНЕ  О ОПРЕДЕЛЕННАЯ  ВНУТРИ метода     РАВНО 0    " + "\n" +
                     " public Result doWork()   MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                     + new Date() +
                     " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС КоличествоЗапущенныйПроуессы.size() " + КоличествоЗапущенныйПроуессы.size()
                     + "\n" +
                     "  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне " + РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне +
                     " \n" +"  АктивностьЕслиЕстьTOP " +АктивностьЕслиЕстьTOP);
         }
         //TODO ЗАПУСК Ф ОЕН ОДНОРАЗОВОЕ УВЕДОМЕНИЕ
     }else{
             // TODO: 03.02.2022 запуск синхрониазции внутри одноразвого ворк менеджера
             МетодЗапускаКодаВнутриОдноразовойСинхронизации
                     (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
                             ПубличныйIDДляФрагмента,
                             КоличествоЗапущенныйПроуессы, АктивностьЕслиЕстьTOP);
             Log.i(context.getClass().getName(), "ЗАПУСК   СТРОГО В ФОНЕ  О ОПРЕДЕЛЕННАЯ  ВНУТРИ метода     вообще NULL  " + "\n" +
                     " public Result doWork()   MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                     + new Date() +
                     " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС КоличествоЗапущенныйПроуессы.size() " + КоличествоЗапущенныйПроуессы.size()
                     + "\n" +
                     "  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне " + РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне +
                     " \n" +"  АктивностьЕслиЕстьTOP " +АктивностьЕслиЕстьTOP);
         }
     }


     if(РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне==null){
         РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне=0;
     }
     myDataОтветОдноразовойСлужбы = new Data.Builder()
             .putLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                     РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне)
           .putBoolean("Proccesing_MyWork_Async_Синхронизация_Одноразовая",true)
             .build();
     Log.i(context.getClass().getName(), "СИНХРОНИЗАЦИЯ ПРОШЛА ОДНОРАЗОВАЯ workmanager  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне " + "\n"
             + РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне);
 } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
        if (РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне > 0) {
            return Result.success(myDataОтветОдноразовойСлужбы);
        }else {
               return Result.failure(myDataОтветОдноразовойСлужбы);
        }
    }






    private Integer МетодЗапускаКодаВнутриОдноразовойСинхронизации
            (Integer ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
             Integer ПубличныйIDДляФрагмента,
             List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы, String АктивностьЕслиЕстьTOP) {
        try{
                // TODO: 24.11.2021  ЗАПУСК СИНХРОНИАЗХЦИИ СТРОГОВ ФОНЕ БЕЗ АКТИВТИ
                РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне = МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(getApplicationContext());

                Log.i(context.getClass().getName(), " observableДляWorkmanagerОдноразовойСинхрогнизации "+
                        РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
                if (РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне > 0) {
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));

// TODO: 26.06.2022  ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ЗАПУСКАМ ONE SIGNAL  И УВЕДОМЛЕНИЯ
                    МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления
                            (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle, ПубличныйIDДляФрагмента);
                    // TODO: 27.06.2022
                    Log.i(context.getClass().getName(), " observableДляWorkmanagerОдноразовойСинхрогнизации doOnComplete  выход "+
                            РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
                }
            // TODO: 27.06.2022
            Log.i(context.getClass().getName(), " выход observableДляWorkmanagerОдноразовойСинхрогнизации doOnComplete  выход "+
                    РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок


    }
 return  РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне;

    }














    private void МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления
            (@NonNull  Integer ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle, @NonNull Integer ПубличныйIDДляФрагмента) {
        try{
        Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                + ПубличныйIDДляФрагмента + " РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне  "  +РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне+
                " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);


        // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияЧАТА();



            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияДляЗАДАЧ();




            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ  СЛУЖБА ТОЛЬКО ПРИ СМНИ СТТАУСА ОТКАЗИ ИЛ ВЫПОЛНИЛ
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияДляЗАДАЧТолькоПриСменеСтатусаОтказВыполнил();


        Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                + ПубличныйIDДляФрагмента + " РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне  "  +РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне);



        // TODO: 14.11.2021  из оДНОРАЗОВГО ВОРК МЕНЕДЖЕРА ЗАПУСКАЕМ ONE SINGNAL

            String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";

                // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL
                Log.d(this.getClass().getName(), "ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle "
                        + ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle +
                        " ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента);



                if ( ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle.compareTo(ПубличныйIDДляФрагмента)!=0)  {

                    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК Facebase and OneSignal  ///  КлючДляFirebaseNotification
                    class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.
                            МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,
                                    ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);


                    // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL
                    Log.d(this.getClass().getName(), "РезультатCallsBackСинхрониазцииЧата " + "\n" + " МОДЕЛЬ ТЕЛЕФОНА  Build.DEVICE   " + Build.DEVICE +
                            "  ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " + ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle+
                            " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle "+ ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);

                }




            Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                    + ПубличныйIDДляФрагмента + " РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне  "  +РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне+
                    " ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle );

    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок


    }




    }













    // TODO: 24.11.2021  Метод КОТОРЫЕ ОПРЕДЕЛЯЕТ КАК МЫ БУДЕМ ЗАПУСКАТЬ СИНХРОНИЗАЦИЮ В ФОНЕ ИЛИ НЕТ ВЗАВИСИМОСТИ КАКАЯ АКТИВИТИ АКТИВНА

   protected Integer МетодЗапускаемприменяяДваВидаЕстьАктивнаяАктивтиИлиСтроговФоне() throws ExecutionException, InterruptedException {


       Integer РезультатЗапускаФоновойСинхронизации = 0;
        ///////
        boolean ФлагЗапущенолиКакоеннибутьАктивтиИлинет = false;

        try{
        ////////

        ///
        Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация фоновой (внутри потока) " + new Date()+
                "  ФлагЗапущенолиКакоеннибутьАктивтиИлинет "+ФлагЗапущенолиКакоеннибутьАктивтиИлинет );

// TODO: 02.07.2021 не запускать службу синхронизации



            // TODO: 29.09.2021  перед началом СИНХРОНИЗАЦИИ ПРОВЕРЯЕМ УСТАНОВКИ СЕТИ ПОЛЬЗОВАТЕЛЯ НА АКТИВТИ НАСТРОЙКИ

                Log.i(context.getClass().getName(), "ПОСЛЕ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER  внутри WORK MANGER  "
                        + new Date() + " СТАТУС WORKMANAGER  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                        + WorkManager.getInstance(context).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState());


                // TODO: 24.11.2021  ЗАПУСК СИНХРОНИАЗХЦИИ СТРОГОВ ФОНЕ БЕЗ АКТИВТИ
               РезультатЗапускаФоновойСинхронизации=  МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(getApplicationContext());



            Log.i(context.getClass().getName(), "ПОСЛЕ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER  внутри WORK MANGER  "
                    + new Date() + " СТАТУС WORKMANAGER  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER " + WorkManager.getInstance(context).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState()+
                    "   РЕЗУЛЬТАТ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER   РезультатЗапускаФоновойСинхронизации    " +РезультатЗапускаФоновойСинхронизации );



    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
return  РезультатЗапускаФоновойСинхронизации;
}
    // TODO: 24.11.2021  Метод КОТОРЫЕ   ЗАПСУКАЮ СИНХРОНИАЗЦИЮ БЕЗ АКТИВТИ СТРГО В ФОНЕ
    protected  Integer МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(@NonNull Context context) {
         Integer РезультатЗапускаФоновойСинхронизации = 0;
        Bundle data=new Bundle();
     try {
         boolean ФлагРазрешениеРаботысСетьюПользователем = new Class_Find_Setting_User_Network(context).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
         if (ФлагРазрешениеРаботысСетьюПользователем==true) {
                 ///////todo запускем синхронизации ОДНОРАЗОВАНАЯ
                       РезультатЗапускаФоновойСинхронизации = serviceForTabelAsync.МетодЗапускаAsyncBackgronudДляWorkManager(context);
                                 Log.d(context.getClass().getName().toString(), "\n"
                                         + "        MyWork_Async_Синхронизация_Одноразовая     РезультатЗапускаФоновойСинхронизации   " + РезультатЗапускаФоновойСинхронизации+
                                         "  serviceForTabelAsync " + serviceForTabelAsync);

             Log.d(this.getClass().getName(), " MyWork_Async_Синхронизация_Одноразовая ФлагРазрешениеРаботысСетьюПользователем "
                     + ФлагРазрешениеРаботысСетьюПользователем);
         }
     } catch (Exception e) {
         e.printStackTrace();
         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                 + Thread.currentThread().getStackTrace()[2].getLineNumber());
         new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                 Thread.currentThread().getStackTrace()[2].getMethodName(),
                 Thread.currentThread().getStackTrace()[2].getLineNumber());
     }
     return РезультатЗапускаФоновойСинхронизации;
    }
}





























