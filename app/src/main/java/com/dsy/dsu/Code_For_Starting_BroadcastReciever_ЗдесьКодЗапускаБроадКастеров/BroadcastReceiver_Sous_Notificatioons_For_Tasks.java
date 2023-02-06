package com.dsy.dsu.Code_For_Starting_BroadcastReciever_ЗдесьКодЗапускаБроадКастеров;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_WorkManagers.MyWork_Notifocations_Уведомления_Для_Задачи;
import com.dsy.dsu.Code_For_WorkManagers.MyWork_Notifocations_Уведомления_Для_Задачи_ЛокальныйОтказВЫполнилПриСменеСтатуса;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BroadcastReceiver_Sous_Notificatioons_For_Tasks extends BroadcastReceiver {



    public BroadcastReceiver_Sous_Notificatioons_For_Tasks() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР  BroadcastReceiver_Sous_Notificatioons_For_Tasks   " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_For_Tasks" +
                "  (intent.getAction()   СЛУЖБА" + new Date() + "\n" +
                " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ////
        Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_For_Tasks  " +
                "  public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_For_Tasks  (intent.getAction()   СЛУЖБА"
                + (intent.getAction().toString()) + " время запуска  " + new Date() + "\n" +
                " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());

        try {

            Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_For_Tasks    public void onReceive(Context context, Intent intent) ..." +
                    "..... СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_For_Tasks  (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " + new Date());

            // TODO: 18.04.2021 запувскает широковещатель

            ///
            Log.i(this.getClass().getName(), " Внутри Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::" + (intent.getAction().toString()) + "\n" +
                    " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());





                    // TODO: 29.09.2021     ЗАПУСК BROADCAST УВЕДОМЕЛНИЯ  ТОЛЬКО ДЛЯ ЗАДАЧА

            МетодЗапускаWorkManager_УведомленияДляЗадача(context);
            // TODO: 29.09.2021     ЗАПУСК BROADCAST УВЕДОМЕЛНИЯ  ТОЛЬКО ДЛЯ ЗАДАЧА   ДОПОЛНИТЕЛЬНО ПРИ СМЕНЕ СТАУСА ЗАДАЧИ ОТКАЗ ИЛИ ВЫПОЛЕНЕО


                    Log.i(this.getClass().getName(), "ПОСЛЕ ВЫХПОЛЕНИЯ МЕТОДА              " +
                            "МетодЗапускаWorkManager_УведомленияДляЧата(context); Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::"
                            +(intent.getAction().toString())+"\n"+
                            " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;
            Log.e(context.getClass().getName(), " ОШИБКА В public class BroadcastReceiver_Sous_Notificatioons_For_Tasks extends BroadcastReceiver { " + " ОШИБКА ::" + e.toString());


        }


    }



    // TODO: 02.04.2021 метод запуска службы из BroadCastas


    private void МетодЗапускаWorkManager_УведомленияДляЗадача(@NotNull Context context ) {


         String ИмяСлужбыУведомленияДляЗадача = "WorkManager NOtofocationForTasks";


        try{


            Log.i(context.getClass().getName(), "ЗАПУСК из BroadcastReceiver СЛУЖБА  УВЕДОМЛЕНИЯ  ДЛя Чата doWork   время "+"\n"
                    +new Date() + " СТАТУС WORKMANAGER" + WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДляЗадача));


            Constraints constraintsУведомленияДляЧата= new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
///


            PeriodicWorkRequest periodicWorkRequestУведомленияДляЧата = new PeriodicWorkRequest.Builder(MyWork_Notifocations_Уведомления_Для_Задачи.class,

                    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)//MIN_PERIODIC_FLEX_MILLIS
                    .setConstraints(constraintsУведомленияДляЧата)
                    .addTag(ИмяСлужбыУведомленияДляЗадача)
                    .setBackoffCriteria(
                            BackoffPolicy.LINEAR,
                            PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                            TimeUnit.MILLISECONDS)
                    //.setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                    .build();

// Queue the work
            WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыУведомленияДляЗадача, ExistingPeriodicWorkPolicy.REPLACE,
                    periodicWorkRequestУведомленияДляЧата);

            //////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;

            Log.e(context.getClass().getName(), "ОШИБКА CATCH  из BroadcastReceiver  СЛУЖБА УВЕДОМЛЕНИЯ для ЧАТА в BroadCasrReciver    private void МетодЗапускаWorkManager_УведомленияДляЧата "+e.toString());


        }


    }






    // TODO: 02.04.2021 метод запуска службы из BroadCastas


}