package com.dsy.dsu.Business_logic_Only_Class;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.Log;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.dsy.dsu.Code_For_WorkManagers.MyWork_Notifocations_Уведомления_Для_Задачи_ЛокальныйОтказВЫполнилПриСменеСтатуса;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public  class SubClass_Notificatioons_For_Tasks_Cancel_AndComplete  {
    Context context;

    public SubClass_Notificatioons_For_Tasks_Cancel_AndComplete( @NotNull Context context){

        this.context=context;

        Log.i(context.getClass().getName(), "ЗАПУСК из BroadcastReceiver СЛУЖБА  УВЕДОМЛЕНИЯ  ДЛя Чата doWork " +
                "  ИмяСлужбыУведомленияДля_ЗадачиТолькоПриСменеСтатусаОтказИлиВыполено время " + "\n");
    }

    public void МетодЗапускаWorkManager_УведомленияДляЗадачаТОлькоПослеСменыСтатусаОтказИлиВыпоелнеа() {


        try {
            String ИмяСлужбыУведомленияДляЗадачСменаСтатуса = "WorkManager NOtofocationForTasksOnlyDissenOrCompliete";


            Log.i(context.getClass().getName(), "ЗАПУСК из BroadcastReceiver СЛУЖБА  УВЕДОМЛЕНИЯ  ДЛя Чата doWork   ИмяСлужбыУведомленияДля_ЗадачиТолькоПриСменеСтатусаОтказИлиВыполено время " + "\n"
                    + new Date() + " СТАТУС WORKMANAGER" +
                    WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДляЗадачСменаСтатуса));


            Constraints constraintsУведомленияДляЧата = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
///


            OneTimeWorkRequest periodicWorkRequestУведомленияДляЧатаОдноразовая =
                    new OneTimeWorkRequest.Builder(MyWork_Notifocations_Уведомления_Для_Задачи_ЛокальныйОтказВЫполнилПриСменеСтатуса.class)///  PeriodicWorkRequest.MIN_BACKOFF_MILLIS
                            .setConstraints(constraintsУведомленияДляЧата)
                            .addTag(ИмяСлужбыУведомленияДляЗадачСменаСтатуса)
                            .setBackoffCriteria(
                                    BackoffPolicy.LINEAR,
                                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                                    TimeUnit.MILLISECONDS)

                            //.setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                            .build();

// Queue the wor
            WorkManager.getInstance(context.getApplicationContext()).enqueueUniqueWork(ИмяСлужбыУведомленияДляЗадачСменаСтатуса, ExistingWorkPolicy.APPEND_OR_REPLACE,
                    periodicWorkRequestУведомленияДляЧатаОдноразовая);


            Log.w(context.getClass().getName(), " observeForever observeForever \" +\"\\n\"+" +
                    " ПОСЛЕ ОТРАБОТКИ МЕТОДА ....Внутри  BroadcastReceiver_Sous_Notificatioons_For_Tasks Бродкастер ИмяСлужбыУведомленияДляЗадачСменаСтатуса " +
                    " " + ИмяСлужбыУведомленияДляЗадачСменаСтатуса + "\n");

            // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата         // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата
            //////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), "ОШИБКА CATCH  SubClass_Notificatioons_For_Tasks_Cancel_AndComplete " + e.toString());


        }


    }
}
