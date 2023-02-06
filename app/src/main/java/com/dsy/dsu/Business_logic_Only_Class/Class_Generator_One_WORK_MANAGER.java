package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;
import com.dsy.dsu.Code_For_WorkManagers.MyWork_Async_Синхронизация_Одноразовая;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.TimeUnit;


public class Class_Generator_One_WORK_MANAGER extends  Class_GRUD_SQL_Operations {
    Context context;
        Class_GRUD_SQL_Operations class_grud_sql_operations=null;
    public Class_Generator_One_WORK_MANAGER( @NonNull Context context) {
        super(context);
        this.context =context;
        Log.w(this.context.getClass().getName(), "contextДляКлассаОдноразоваяСлужба " + this.context);
    }
    // TODO: 28.12.2021 srart one work manager
    public void МетодОдноразовыйЗапускВоерМенеджера(@NotNull Context context, @NonNull Integer ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника) {
        String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
        // com.dsy.dsu.providerdatabase
        Integer РезультатОноразовойСинхрониазции=0;
        Uri uri = Uri.parse("content://data/data/com.dsy.dsu/databases/Database DSU-1.db");
        try {
            Data myData = new Data.Builder()
                    .putInt("СообщениеЧатаДляКонктерногоСотрудника", ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника)
                    .build();
            Constraints constraintsЗапускСинхОдноразоваяСлужба = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            OneTimeWorkRequest OneTimeWorkЗапускФОновойСинхрониазциииИзНУтриТабеля =
                    new OneTimeWorkRequest.Builder(MyWork_Async_Синхронизация_Одноразовая.class)
                            .setConstraints(constraintsЗапускСинхОдноразоваяСлужба)
                            .setInputData(myData)
                            .addTag(ИмяСлужбыСинхронизацииОдноразовая)
                            .setBackoffCriteria(
                                    BackoffPolicy.LINEAR,
                                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                                    TimeUnit.MILLISECONDS)
                            .build();//      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            WorkManager.getInstance(context).enqueueUniqueWork(ИмяСлужбыСинхронизацииОдноразовая,
                    ExistingWorkPolicy.APPEND_OR_REPLACE, OneTimeWorkЗапускФОновойСинхрониазциииИзНУтриТабеля);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString()
                    , this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}
