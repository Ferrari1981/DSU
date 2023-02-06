package com.dsy.dsu.Code_For_Tasks_КодДля_Задания;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.R;

import java.util.Date;

import javax.inject.Inject;


public class MainActivity_Tasks extends FragmentActivity {

    // TODO: 28.02.2022
    private Activity activity;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_дляЗадачиПерваяКнопка;
    @Inject
   private Class_Generations_PUBLIC_CURRENT_ID class_generations_public_current_id;
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";

    // TODO: 02.08.2022  для биндинга одноразовая синхрониазция
    private Service_ДляЗапускаодноразовойСинхронизации service_дляЗапускаодноразовойСинхронизации;
 //    private ServiceConnection connectionДляОдноразовойСинхронизации;
     private  SubClass_Only_ActivyMain_Buccess_Logic subClass_only_activyMain_buccess_logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            activity=this;
            Log.d(this.getClass().getName(), " service_дляЗапускаодноразовойСинхронизации " + service_дляЗапускаодноразовойСинхронизации);
            subClass_only_activyMain_buccess_logic=new SubClass_Only_ActivyMain_Buccess_Logic(getApplicationContext(),activity);

            subClass_only_activyMain_buccess_logic.     МетодЗапускаетБиндингСлужбыДляЗапускаОдноразовойСинхронизаци(getApplicationContext());

            class_generations_public_current_id      =new Class_Generations_PUBLIC_CURRENT_ID();
            /*   setContentView(R.layout.activity_main_fragment1_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks*/
            setContentView(R.layout.activity_main_fisrt_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks
            // TODO: 27.04.2021 формируем внешний вид Чата через фрагменты
            activity = this;
            // TODO: 28.02.2022
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            // TODO: 27.07.2022
            if (!WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая=   WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get() .get(0);
            if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
// TODO: 02.03.2022
                Integer ПубличныйIDДляФрагмента = class_generations_public_current_id.ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                subClass_only_activyMain_buccess_logic. МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);

               // class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаетОДНОРАЗОВУЮСинхронизациюПослкеУспешнойПроведеннойОперации(ПубличныйIDДляФрагмента, getApplicationContext());

            }         }

// TODO: 03.03.2022  ЗАПУСКАЕМ БИЗНЕС ЛОГИКУ НА АКТИВТИ ДЛЯ ФРАГМЕНТОВ ДЛЯ ЗАДАНИЯ

          new SubClass_Only_ActivyMain_Buccess_Logic(getApplicationContext(),activity).МетодЗапускФрагментаЗадачиСАктивти();

            Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);

            // TODO: 01.03.2022*/
            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
   try{
        // TODO: 18.06.2022
       try {
           unbindService(    subClass_only_activyMain_buccess_logic.connectionДляОдноразовойСинхронизации);
       } catch (Exception e) {
          // e.printStackTrace();
       }
       /////////////

    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

// TODO: 10.03.2022




    private class SubClass_Only_ActivyMain_Buccess_Logic {
        public SubClass_Only_ActivyMain_Buccess_Logic(Context context,Activity activity) {
            // TODO: 10.03.2022
        }

        // TODO: 10.03.2022

    private void МетодЗапускФрагментаЗадачиСАктивти() {
        try{
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ");
            fragmentManagerДляЗадачи = getSupportFragmentManager();
            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
            // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляЗадачи  " + fragmentTransactionляЗадачи);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView   ");
            // TODO: 09.03.2022
            fragment_дляЗадачиПерваяКнопка = new Fragment1_One_Tasks();
            fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_дляЗадачиПерваяКнопка);//.layout.activity_for_fragemtb_history_tasks
            // TODO: 10.03.2022
           // fragmentTransactionляЗадачи.addToBackStack(null);
            fragmentTransactionляЗадачи.commit();
            fragmentManagerДляЗадачи.executePendingTransactions();
            fragmentTransactionляЗадачи.show(fragment_дляЗадачиПерваяКнопка);
            Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

        // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
        public void МетодЗапускаетБиндингСлужбыДляЗапускаОдноразовойСинхронизаци(@NonNull Context context) {
            try {
                //TODO start broad caset receiver
                service_дляЗапускаодноразовойСинхронизации=new Service_ДляЗапускаодноразовойСинхронизации();
                Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(context, Service_ДляЗапускаодноразовойСинхронизации.class);
                // TODO: 26.06.2022
                context. bindService(intentЗапускСлужюыыСинхрониазцииБиндинг, connectionДляОдноразовойСинхронизации, Context.BIND_AUTO_CREATE);
                // context.startService(intentЗапускСлужюыыСинхрониазцииЧерезСлужбу);
                // TODO: 27.06.2022  запуск
            } catch (Exception e) {
                //  Block of code to handle errors
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 11.05.2021 запись ошибок
            }
        }
        private ServiceConnection connectionДляОдноразовойСинхронизации = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try{
                    // We've bound to LocalService, cast the IBinder and get LocalService instance
                    Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binder = (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) service;
                    service_дляЗапускаодноразовойСинхронизации = binder.getService();
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            +"    onServiceDisconnected  service_дляЗапускаодноразовойСинхронизации" +service_дляЗапускаодноразовойСинхронизации);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 11.05.2021 запись ошибок
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                try{
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date()+"\n+" +
                            " Класс в процессе... " +  this.getClass().getName()+"\n"+
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            +"    onServiceDisconnected  service_дляЗапускаодноразовойСинхронизации" +service_дляЗапускаодноразовойСинхронизации);
                } catch (Exception e) {
                    //  Block of code to handle errors
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());

                    // TODO: 11.05.2021 запись ошибок

                }
            }
        };


        // TODO: 02.08.2022
        void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
           try{
               Log.d(getApplicationContext().getClass().getName(), "\n"
                       + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака",ПубличныйIDДляФрагмента);
            Intent intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle = new Intent(getApplicationContext(), Service_ДляЗапускаодноразовойСинхронизации.class);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(), " ПРОШЕЛ ЗАПУСК  метода МетодПовторногоЗапускаВсехWorkManager__ОДНОРАЗОВОЙСинхрониазцииданных()   " +
                    "   ПубличныйIDДляФрагмента "+
                    ПубличныйIDДляФрагмента);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            service_дляЗапускаодноразовойСинхронизации.МетодЗапускаОдноразовойСинхронизацииИзСлужбы(getApplicationContext(),intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle);
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
        }
        // TODO: 02.08.2022  metod start bing async
    }
}





    // TODO: 28.02.2022 конец класса

// TODO: 28.02.2022  old test code













