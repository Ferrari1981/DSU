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
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_WorkManagers.MyWork_Notifocations_Уведомления_Для_Чата;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BroadcastReceiver_Sous_Notificatioons_For_Chats extends BroadcastReceiver {

    //WorkManager workManagerДЛяСлужбПроекта;


    String ИмяСлужбыУведомленияДляЧатаОдноразовая = "WorkManager NOtofocationForChats";

    public BroadcastReceiver_Sous_Notificatioons_For_Chats() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ  " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА" +
                "" + new Date() + "\n" +
                " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ////
        Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ " +
                "  public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА"
                +(intent.getAction().toString())+" время запуска  " +new Date()+"\n"+
                " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());

        try {

            Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ   public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА" +(intent.getAction().toString())+" время запуска  " +new Date());

            // TODO: 18.04.2021 запувскает широковещатель



                    // TODO: 29.09.2021     ЗАПУСК BROADCAST УВЕДОМЕЛНИЯ  ТОЛЬКО ДЛЯ ЧАТА



                        // TODO: 03.01.2022

                        МетодЗапускаWorkManager_УведомленияДляЧата_ОДНОРАЗОВЫЙ(context);

                        Log.i(this.getClass().getName(), "ПОСЛЕ ВЫХПОЛЕНИЯ МЕТОДА              " +
                                "МетодЗапускаWorkManager_УведомленияДляЧата DESTROY (context); Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::"
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
            Log.e(context.getClass().getName(), " ОШИБКА В BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ СЛУЖБА  public void onReceive  "+" ОШИБКА ::"+e.toString());


        }

    }




















    // TODO: 02.04.2021 метод запуска службы из BroadCastas


    private void МетодЗапускаWorkManager_УведомленияДляЧата_ОДНОРАЗОВЫЙ(@NotNull Context context ) {




        try{


            Log.i(context.getClass().getName(), "ЗАПУСК из BroadcastReceiver СЛУЖБА  УВЕДОМЛЕНИЯ одноразовые   ДЛя Чата doWork   время "+"\n"
                    +new Date() + " СТАТУС WORKMANAGER" + WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДляЧатаОдноразовая));


            Constraints constraintsУведомленияДляЧата= new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
///


            OneTimeWorkRequest periodicWorkRequestУведомленияДляЧатаОдноразовая = new OneTimeWorkRequest.Builder(MyWork_Notifocations_Уведомления_Для_Чата.class)///  PeriodicWorkRequest.MIN_BACKOFF_MILLIS
                    .setConstraints(constraintsУведомленияДляЧата)
                    .addTag(ИмяСлужбыУведомленияДляЧатаОдноразовая)
                    .setBackoffCriteria(
                            BackoffPolicy.LINEAR,
                            PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                            TimeUnit.MILLISECONDS)

                    //.setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                    .build();



// Queue the work
            WorkManager.getInstance(context.getApplicationContext()).enqueueUniqueWork(ИмяСлужбыУведомленияДляЧатаОдноразовая, ExistingWorkPolicy.APPEND_OR_REPLACE,
                    periodicWorkRequestУведомленияДляЧатаОдноразовая);


            //////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;

            Log.e(context.getClass().getName(), "ОШИБКА CATCH  из BroadcastReceiver  СЛУЖБА УВЕДОМЛЕНИЯ для ЧАТА в BroadCasrReciver    private void MyWork_Notifocations_Уведомления_Для_Чата" + e.toString());


        }


    }























}