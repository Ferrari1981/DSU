package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClass_Starting_ЗапускДЛяСогласования;
import com.dsy.dsu.R;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MyWork_Notifocations_Уведомления_Для_Согласования extends Worker {
    private Context context;
    private String ИмяСлужбыУведомленияДля_Согласования = "WorkManager NOtofocationForCommitPay";
    private  WorkerParameters workerParams;
    private NotificationManager mNotificationManagerДляЧАТА = null;
    private  WorkInfo ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая;
    private  Integer ОбщееКоличествоНЕпрочитанныхСтрок = 0;
    private  NotificationCompat.Builder builder_Для_Согласования = null;
    private  CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  Class_GRUD_SQL_Operations class_grud_sql_operationsIDпользоввателяДляСлужб;
    private  SimpleDateFormat ФоорматДат ;
    private int     ID_ТаблицаУвендомлений;
    private  Intent ИнтентДляЗапускаСлужбыПолсеАнализа;
    private   Boolean РезультатНужноЗапускатьУведомленияИлиНет=false;
    private String PROCESS_ID_УведомленияДляСогласования ="28";
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private  ArrayList БуферСамиУведомленияДляСогласования;
    private   NotificationCompat.MessagingStyle messagingStyleДля_СогласованияУведомлений;
    private  Person.Builder person;
    private  SubClass_Starting_ЗапускДЛяСогласования subClass_starting_запускДЛяОтказСогласования ;

    public MyWork_Notifocations_Уведомления_Для_Согласования(@NonNull Context context, @NonNull WorkerParameters workerParamsвнутри) {
        super(context, workerParamsвнутри);
        this.context = context;
        workerParams = workerParamsвнутри;
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(context);
        class_grud_sql_operationsIDпользоввателяДляСлужб = new Class_GRUD_SQL_Operations(context);
        messagingStyleДля_СогласованияУведомлений = new NotificationCompat.MessagingStyle(getApplicationContext().getResources().getString(R.string.action_settings))
                .setConversationTitle("Согласование");
        Log.i(this.context.getClass().getName(),
                " messagingStyleДля_ОбщихУведомлений " + "\n" + messagingStyleДля_СогласованияУведомлений.getMessages());
        subClass_starting_запускДЛяОтказСогласования = new SubClass_Starting_ЗапускДЛяСогласования(context);
        // TODO: 27.03.2022  классф нужны при успешно м ил отказе от согласования
    }
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
// TODO: 16.06.2022 old super.getBackgroundExecutor()
        Log.i(context.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(this.getClass().getName(), " onStopped ()  ");
    }
    // TODO: 17.11.2021  ГЛАВНЫЙ МЕТОД КЛАССА WORK MANEGER  ДЛЯ УВЕЛОДОМЛЕНИЯ ТОЛЬКО ДЛЯ ЧАТА
    @NonNull
    @Override
    public Result doWork() {
        Boolean ФинальныйФлагЛюбогоЗапущеногоАктивти = false;
        try {
            Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
            ActivityManager ЗапущенныйПроуессыДляУведомленийЧата = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            if (ЗапущенныйПроуессыДляУведомленийЧата!=null) {
                // TODO: 24.11.2021
                List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессыДляЧата = ЗапущенныйПроуессыДляУведомленийЧата.getAppTasks();
            if (КоличествоЗапущенныйПроуессыДляЧата.size() > 0) {
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                        "public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER  КоличествоЗапущенныйПроуессыДляЧата " + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата.size());
                // TODO: 01.12.2021
                for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессыДляЧата) {
                    String АктивностьЕслиЕстьTOPДляЧата = null;
                    // TODO: 20.02.2022
                    if (ТекущаяАктивти!=null) {
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                        // TODO: 20.02.2022
                        if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                            // TODO: 20.02.2022
                            АктивностьЕслиЕстьTOPДляЧата = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                        }
                        Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                " АктивностьЕслиЕстьTOPДляЧата  " + АктивностьЕслиЕстьTOPДляЧата +
                                "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                + ТекущаяАктивти.getTaskInfo().numActivities);
                    }

                    if (АктивностьЕслиЕстьTOPДляЧата!=null) {
                        switch (АктивностьЕслиЕстьTOPДляЧата) {
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                            case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Tabels_Users_And_Passwords":
                                break;
                            // TODO: 01.12.202 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                            default:
                                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                        "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n");
                                ///////todo код запуска уведомлений для чата
                                МетодЗапускаСлужбыУведомленияДляСогласования();
                                ///////todo код запуска уведомлений для чата
                                Log.i(context.getClass().getName(), "Метод ВНУТРИ РАБОТА... С АКТИВТИ ДЕЙСТВУЩИМ ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                        "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                        + new Date() +
                                        " WorkManager Synchronizasiy_Data  " + " РАБОТАЮЩИЙ ПРОЦЕСС  КоличествоЗапущенныйПроуессыДляЧата.size()"
                                        + КоличествоЗапущенныйПроуессыДляЧата.size() + "\n" +
                                        "   действуещее TOP активти АктивностьЕслиЕстьTOPДляЧата " + АктивностьЕслиЕстьTOPДляЧата);
                                ///////todo  КОНЕЦ  код запуска уведомлений для чата
                                break;
                            // TODO: 24.11.2021
                        }
                    }else{
                        // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА  РЕЗКО НЕ СТАЛО АКТИВТИ
                        МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                        Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                                + КоличествоЗапущенныйПроуессыДляЧата);
                    }
                }
            } else {
                // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА
                МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти РАВНО )" + "\n"
                        + КоличествоЗапущенныйПроуессыДляЧата);
            }
        }else{
            // TODO: 03.12.2021 ПРОСТО ФОНОВАЯ ЗАДАЧА
            МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(ФинальныйФлагЛюбогоЗапущеногоАктивти);
                Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER Зарускает когда Активти воОБЩЕ НЕТ null" );
        }
            // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая = WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДля_Согласования).get().get(0);
                // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата         // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата
            Log.w(context.getClass().getName(), " Внутри метода public Result doWork()   MyWork_Notifocations_Уведомления_Для_Согласования  ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая "
                    + ИмяСлужбыУведомленияДля_Согласования + "\n"
                    + " getState  " +
                    ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая.getState().name() + "\n" +
                    "getTags " +
                    ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая.getTags() + "\n" +
                    "getRunAttemptCount " +
                    ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая.getRunAttemptCount() + "\n" +
                    "getProgress " +
                    ИнформацияОЗапущенойСлужбе_Уведомления_Одноразовая.getProgress().toString() + "\n" +
                    " время : " + new Date());
            // TODO: 25.02.2022 send datas
            Data myDataОтветОбщегоУведомления = new Data.Builder()
                    .putBoolean("ОтветПослеВыполения_MyWork_Notifocations_Уведомления_Для_Согласования",
                            РезультатНужноЗапускатьУведомленияИлиНет)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Стоп СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " +
                    "MyWork_Notifocations_Уведомления_Для_Согласования ошибка  Exception e в классе MyWork_Notifocations_Уведомления_Для_Согласования " + e.toString() + "\n" +
                    " ФинальныйФлагЛюбогоЗапущеногоАктивти " + ФинальныйФлагЛюбогоЗапущеногоАктивти);
        }
            return Result.retry();
    }

















    // TODO: 16.12.2021 Метод ЗАпускает в фоне Уведомелния когда вообще нет нет ни одного Активти NULL

    private void МетодЗапускаетУведомленияКогдаВообщенетНиОдногоАктивтиNULL(Boolean ФинальныйФлагЛюбогоЗапущеногоАктивти) {
        try{

                          Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " + new Date() +
                                  "\n" + " Build.BRAND " + Build.BRAND.toString() + "\n" );

                          ///////todo код запуска уведомлений для чата


                          ///////todo код запуска уведомлений для чата

                          МетодЗапускаСлужбыУведомленияДляСогласования();

                          ///////todo код запуска уведомлений для чата

                          Log.i(context.getClass().getName(), "Метод ВНУТРИ ЧИСТО ФОНОВАЯ ЗАДАЧА  ЧАТА ОТРАБОТАЛ ВНУТРИ метода ЗАПУСКАЕМ БЕЗ activity      " +
                                  "   public Result doWork()  MyWork_Notifocations_Уведомления  внутри WORK MANAGER "
                                  + new Date() +
                                  " WorkManager Synchronizasiy_Data  " );

                          ///////todo  КОНЕЦ  код запуска уведомлений для чата




                          //TODO когда запускам уведомления чата  КОГДА НЕТ АКТИВИТИ И ПРОСТО ЗАПУСКАЕМ


                          //////////
                      } catch (Exception e) {
                      e.printStackTrace();
                      ///метод запись ошибок в таблицу
                      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                              " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                      // TODO: 01.09.2021 метод вызова
                      new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                              this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                              Thread.currentThread().getStackTrace()[2].getLineNumber());

                      Log.e(context.getClass().getName(), " Стоп СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО  ДЛЯ ЧАТА " +
                              "MyWork_Notifocations_Уведомления_Для_Согласования ошибка  Exception e в классе MyWork_Notifocations_Уведомления_Для_Согласования " + e.toString() + "\n" +
                              " ФинальныйФлагЛюбогоЗапущеногоАктивти " + ФинальныйФлагЛюбогоЗапущеногоАктивти);
                      Log.e(getApplicationContext().getClass().getName(), " Ошибка  MyWork_Notifocations_Уведомления ЧАТ   public Result doWork()    ДЛЯ ЧАТА ");
                  }
    }


    // TODO: 17.11.2021










    private void МетодЗапускаСлужбыУведомленияДляСогласования() {


        String ЛокальныйФлагУбратьУведомленияСлужбу=new String();

        // TODO: 15.11.2021


        try{

            Log.i(getApplicationContext().getClass().getName(), "Запуск метода МетодЗапускаСлужбыУведомления СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА ДЛЯ ЧАТА  "+new Date());

            try {
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: 02.08.2021
                ФоорматДат = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
            }
            ФоорматДат.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));


// TODO: 01.04.2021 данное условие запускает соаздание уведомления с начала своего ниже код только реагирует на нажатие кнопки удалить уведомление и саму служюу отстановить кнопка ЗАКРЫТЬ






                    /////////TODO запуск нновую нотификашенс устанолвка
                    МетодЗарускаСозданиеУведомленийДляСогласования();




                    Log.d(getApplicationContext().getClass().getName(), " Запуск по Расписанию СЛУЖБА  Информирования  BroadcastReceiver или  FaceApp " + "  --" + new Date());




            // TODO: 06.04.2021 Определяем рабоает ли Служба КОД ПРОВЕРЯТЕТНЕ ЗАПУЩЕНАЛИ СЛУЖЬБА И ЕСЛИНЕ ЗАПУЩЕНА ТОНЕ НАДО ЕЕ УДАЛЯТЬ ИЗ ПАМЯТИ



            // TODO: 06.04.2021 ПРИНИМАЕМ РЕШЕНИЕ О ЗАКРЫТИЕ СЛУЖБЫ ЧЕРЕ З КНОПКУ







            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

        }

    }


    /////////////////

    private void МетодЗарускаСозданиеУведомленийДляСогласования() {

        try{


            try{


                РезультатНужноЗапускатьУведомленияИлиНет=false;

                ///
                Log.d(getApplicationContext().getClass().getName(), " Внутри МетодЗарускаСозданиеУведомлений"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////


                // TODO: 17.11.2021  ВЫЧИСЛЯЕМ ВООБЩЕ ЕСТЬ СТРОЧКИ В ЧАТЕ НЕ ПРОЧИТАННЫЕ КОТОРЫЕ НАЖДО ПРОЧИТАТЬ -- ВЕРНЁТ TRUE


                РезультатНужноЗапускатьУведомленияИлиНет=МетодВычисляемСтоитСоздаватьИЗапускатьСлужбуНапоминаний();






                ///
                Log.d(getApplicationContext().getClass().getName(), " Внутри Future Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА РезультатНужноЗапускатьУведомленияИлиНет"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////

                String ФлагПолучаемИзНутриПрограммы = null;//=// notificationIntentДляУведомлений .getStringExtra("Флаг");

                if(ФлагПолучаемИзНутриПрограммы==null){


                    ФлагПолучаемИзНутриПрограммы=new String();
                }


                ///
                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы+ "\n"
                        + "ОбщееКоличествоНЕпрочитанныхСтрок   "+ОбщееКоличествоНЕпрочитанныхСтрок);/////


// TODO: 17.11.2021  ЕСЛИ TRUE ТО НАЧИНАЕМ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ



                if (РезультатНужноЗапускатьУведомленияИлиНет==true ) {


                    //////TODO МЕТОД КОТОРЫЙ ЗАПУСКАЕТ УВЕДОМЛЕНИЯ ПОСЛЕ АНАЛИЗА ДАТ


                    МетодКоторыйЗапускаетУвеломленияПослеАнализа(ИнтентДляЗапускаСлужбыПолсеАнализа, РезультатНужноЗапускатьУведомленияИлиНет, ФлагПолучаемИзНутриПрограммы);//  //ФлагКтоЗапустилСлужбу

                    Log.d(this.getClass().getName(), "ЗАПУСК ПОСЛЕ АНАЛИЗА ДАТ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ  СЛУЖБА  Синхронизация   " + " ВРЕМЯ " + new Date()
                            + "\n" + " РезультатНужноЗапускатьУведомленияИлиНет " + РезультатНужноЗапускатьУведомленияИлиНет);








                }





                ///


                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет+  " ФлагПолучаемИзНутриПрограммы " +ФлагПолучаемИзНутриПрограммы);/////


                ///////
            } catch (Exception e) {
                //  Block of code to handle errors
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());



                //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

                NotificationManager notificationManager = (NotificationManager)
                        getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования));
            }







//
            Log.d(this.getClass().getName(), "AsyncРезультатНужноЗапускатьУведомленияИлиНет " );

 /*       //////TODO МЕТОД КОТОРЫЙ ЗАПУСКАЕТ УВЕДОМЛЕНИЯ ПОСЛЕ АНАЛИЗА ДАТ
            МетодКоторыйЗапускаетУвеломленияПослеАнализа(intent, РезультатНужноЗапускатьУведомленияИлиНет);*/


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования));
            // /.cancelAll(); //   mNotificationManagerДляЧАТА.cancelAll();
            //   mNotificationManagerДляЧАТА=null;
            //  builder=null;
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА onDestroy() Exception ");

        }



    }





    private void МетодКоторыйЗапускаетУвеломленияПослеАнализа(Intent intent, boolean результатНужноЗапускатьУведомленияИлиНет, String ФлагКтоЗапустилСлужбу) {
        ///TODO ЕСЛИ TRUE ТО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО КОГДА  TRUE
        Log.d(this.getClass().getName(), "Результат Нужно Запускать Уведомления Или Нет СЛУЖБА  true and false :: " +
                результатНужноЗапускатьУведомленияИлиНет);
        /////TODO ЗАПУСКАЕМ И СОЗДАЕМ СЕРВИС УВЕДОМЛЕНИЯ
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);
            МетодНотификайшенДЛяОбщейСлужбыУведомления(ФлагКтоЗапустилСлужбу);
        Log.d(getApplicationContext().getClass().getName(), " ФлагКтоЗапустилСлужбу " + ФлагКтоЗапустилСлужбу);
    }
/////////TODO запуск нновую нотификашенс устанолвка
    private void МетодНотификайшенДЛяОбщейСлужбыУведомления(String ФлагКтоЗапустилСлужбу) {
        try {
            Log.d(getApplicationContext().getClass().getName(), " Создание Уведомлеения СЛУЖБА СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО ");
            builder_Для_Согласования = null;
            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи
            PendingIntent ЗапускПендингОтказСогласования = subClass_starting_запускДЛяОтказСогласования.
                    МетодЗапускаСменыСтатусаОтказИилиСогласованияЧерезPendingIntent("28", "",
                            1);// TODO: 24.05.2022  1 - цифра 1 отказ
            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи
            // TODO: 03.03.2022 определяем кода для отложеного запуска службы смены статсу условия задачи
            PendingIntent ЗапускПендингУспешноеСогласования = subClass_starting_запускДЛяОтказСогласования.
                    МетодЗапускаСменыСтатусаОтказИилиСогласованияЧерезPendingIntent("28",
                            "",
                            2);// TODO:  - цифра 2 успешно согласования
            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияДляСогласования +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Согласования + " person.build().getUri() " + person.build().getUri());
            PendingIntent ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние = null;

          /*          // TODO: 03.03.2022 ВЬТОРОЙ МЕТОД ДЛЯ ЗАДАНИЕ ПЕРЕХОД ИЗ УВЕДОМЛЕНИЯ В ЗАДАНИЕ
            PendingIntent ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние = subClass_starting_запускДЛяУспешноеСогласования.
                    МетодПриКликеЗапускаСогласованияИзСамогоУведомленияПереход(PROCESS_ID_УведомленияДляСогласования, ИмяСлужбыУведомленияДля_Согласования,
                            person.build().getUri(),
                            0, "", "ИзУведомленияЗадачаПереходимВАктивтиЗадача");*/
            ///////TODO запускаем смены стануса задачи черезе PendingIntent
            Log.d(getApplicationContext().getClass().getName(), "PROCESS_ID_УведомленияПлановая  ПЕРЕЙТИ " + PROCESS_ID_УведомленияДляСогласования +
                    " ИмяСлужбыУведомленияДляЧата " + ИмяСлужбыУведомленияДля_Согласования + " person.build().getUri() " + person.build().getUri());
            // TODO: 27.03.2022 ДЛЯ ЗАДАЧА
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            // TODO: 21.12.2021 В КОДЕ НИЖЕ МЫ ОПРЕДЕЛЯЕМ ЗАПУСКАТЬ НАМ КОД ИЛИ НЕТ
            Boolean СтатустУведомленияДляУведомленияЧАТА=false;
            StatusBarNotification[] statusBarNotificationУведомленияЧата=      notificationManager.getActiveNotifications();
            for(StatusBarNotification statusBarNotification1: statusBarNotificationУведомленияЧата) {
                if (statusBarNotification1.getId() == Integer.parseInt(PROCESS_ID_УведомленияДляСогласования)) {
                    СтатустУведомленияДляУведомленияЧАТА = statusBarNotification1.isClearable();
                    Log.d(this.getClass().getName(), " СтатустУведомленияДляУведомленияЧАТА" + СтатустУведомленияДляУведомленияЧАТА);
                }
            }
            Log.d(this.getClass().getName(), "СтатустУведомленияДляУведомленияЧАТА " +СтатустУведомленияДляУведомленияЧАТА+
                    " БуферСамиУведомленияЛинкСамиУведомления " + БуферСамиУведомленияДляСогласования);
            // TODO: 1.11.2021 НЕПОСТРЕДСТВЕННО СОЗДАНИЕ УВЕДОМЛЕНИЯ ДЛЯ ЧАТА СОЗДАНИЕ И ЗАПОЛЕНИЕ
                if (БуферСамиУведомленияДляСогласования !=null  && СтатустУведомленияДляУведомленияЧАТА==false ) {/// && СтатустУведомленияДляУведомленияЧАТА==false
                    Log.d(this.getClass().getName(), "bigText " + БуферСамиУведомленияДляСогласования + " БуферСамиУведомленияЛинкСамиУведомления.toString() "
                            + БуферСамиУведомленияДляСогласования.toString()+"\n"+
                            "ЗАПУСК......НОВОЕ СООБЩЕНИЕ   bigText " + БуферСамиУведомленияДляСогласования +"\n"+
                            "  время ::" + new Date());
                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
                    notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования));
                    onStopped();
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

// TODO: 21.11.2021  само созданеи уведомления

       /*             Drawable icon = null;
                    icon = getResources().getDrawable(R.drawable.icon_dsu1_for_fragment1_chat2);
                    icon.setBounds(10, 0, 90, 85);*/


         /*           // TODO: 03.02.2022  УВЕДОМЛЕНИЯ ДЛЯ ОБШЕЙ СИНХРОНИАЗЦИИ
                    NotificationCompat.MessagingStyle.Message messagingStyleДля_ОбщихУведомлений =
                            new NotificationCompat.MessagingStyle.Message("Всем привет!", System.currentTimeMillis(), "Ivan");*/

               /*     NotificationCompat.MessagingStyle.Message messagingStyle2 =
                            new NotificationCompat.MessagingStyle.Message("  zЯЯЯ  Всем привет!", System.currentTimeMillis(), (Person) null);*/


                    PendingIntent ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ///"@mipmap/icon_main_tabel_four" ////.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        builder_Для_Согласования = new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияДляСогласования)
                                /////
                                .setContentText(БуферСамиУведомленияДляСогласования.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .setColor(Color.BLUE)
                                .setColorized(true)
                                //.setContentTitle("Задание на выполнение")
                                .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                .setGroup("SousAndroid")
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                        R.drawable.ic_notifications_black_24dp)) // большая картинка
                                //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                .setVibrate(new long[]{0, 250, 100, 250})
                                .setShowWhen(true)
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияДляСогласования
                                        .toString())).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                                .setStyle(messagingStyleДля_СогласованияУведомлений).setColor(Color.parseColor(("#B2222" + new Random().nextInt(1))))
                                .setGroupSummary(true)
                                .setColor(Color.GREEN)
                                .addAction(android.R.drawable.ic_btn_speak_now, "Согласовать", ЗапускПендингУспешноеСогласования)
                                .addAction(android.R.drawable.ic_delete, "Отказать", ЗапускПендингОтказСогласования)
                                .setAutoCancel(false)
                                .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);
                        ////TODO три кнопки действия PUSH-сообщений
                        /// .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)  .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)


                    } else {
                        builder_Для_Согласования =
                                new NotificationCompat.Builder(getApplicationContext(), PROCESS_ID_УведомленияДляСогласования)
                                        //
                                        .setContentText(БуферСамиУведомленияДляСогласования.toString())                 // .setContentText("http://developer.alexanderklimov.ru/android/")
                                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)////builder.setSmallIcon(R.drawable.ic_launcher_background);//R.mipmap.ic_launcher   ///R.drawable.ic_notifications_black_24dp
                                        .setPriority(NotificationCompat.PRIORITY_MAX)
                                        .setColor(Color.BLUE)
                                        .setColorized(true)
                                        //  .setContentTitle("Задание на выполнение")
                                        .setSmallIcon(R.drawable.icon_dsu1_for_fragment1_chat2)
                                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                                R.drawable.ic_notifications_black_24dp)) // большая картинка
                                        //.setTicker("Последнее китайское предупреждение!") // до Lollipop
                                        .setVibrate(new long[]{0, 250, 100, 250})
                                        .setShowWhen(true)
                                        .setGroup("SousAndroid")
                                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                        .setCategory(Notification.CATEGORY_MESSAGE)
                                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.FLAG_AUTO_CANCEL)
                                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                        /* .setStyle(new NotificationCompat.BigTextStyle().bigText(БуферСамиУведомленияЛинкСамиУведомления.toString())
                                         ).setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)*/
                                        .setStyle(messagingStyleДля_СогласованияУведомлений).setColor(Color.parseColor(("#B2222" + new Random().nextInt(1))))
                                        .setGroupSummary(true)
                                        .setColor(Color.GREEN)
                                        .addAction(android.R.drawable.ic_btn_speak_now, "Согласовать", ЗапускПендингУспешноеСогласования)
                                        .addAction(android.R.drawable.ic_delete, "Отказать", ЗапускПендингОтказСогласования)
                                        .setAutoCancel(false)
                                        .setWhen(System.currentTimeMillis()) // автоматически закрыть уведомление после нажатия////.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText) ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                                        .setContentIntent(ЗапускПриКликеКодаИзЗаданияКогдаНадоПерейтисУведомленияНаЗАдачние);// TODO: 27.03.2022    .addAction(android.R.drawable.ic_delete, "Выполнил/на", ЗапускКОдаЧтоПОльзовательОзнаомленсЗаданиемss)

                        // автоматически закрыть уведомление после нажатия
                        // .setContentIntent(ЗапускЗакрытия);
                        ////TODO три кнопки действия PUSH-сообщений



                    }



            // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

            mNotificationManagerДляЧАТА = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            // TODO: 17.11.2021  start
            // === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        PROCESS_ID_УведомленияДляСогласования,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManagerДляЧАТА.createNotificationChannel(channel);
                builder_Для_Согласования.setChannelId(String.valueOf(PROCESS_ID_УведомленияДляСогласования));
                channel.setDescription("Увеомление для версии выше API 25");
                // TODO: 18.11.2021  дополнительые настройки

                builder_Для_Согласования.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_AUTO_CANCEL;
                // startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_INSISTENT;
                // TODO: 02.12.2021
                builder_Для_Согласования.setNumber(1);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                ///TODO Запускаем увидомления
                // TODO: 02.12.2021  сам запуск уведомления
                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования), builder_Для_Согласования.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления
                //mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

   /*             Log.i(this.getClass().getName(), "ЗАПУСК СЛУЖБА  УВЕДОМЛЕНИЯ ДЛЯ ЧАТА САМО ПОЯВЛЕНИЕ  ВАРИАНТ -1  startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()" + new Date()
                        + "\n" + " PROCESS_ID_УведомленияПлановая " + PROCESS_ID_УведомленияПлановая+"\n"
                        +  "  САМИ СООБЩЕНИЯ : "+БуферСамиУведомленияЛинкСамиУведомления.toString());
*/



              //    startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()

            }else{
                ///TODO Запускаем увидомления

                // TODO: 27.11.2021 САМ ЗАПУСК УВЕДОМЛЕНИЯ

                //mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                builder_Для_Согласования.build().flags |= Notification.FLAG_FOREGROUND_SERVICE;
                ///TODO Запускаем увидомления
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_AUTO_CANCEL;
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_SHOW_LIGHTS;
                ///TODO Запускаем увидомления
                builder_Для_Согласования.build().flags |= Notification.FLAG_INSISTENT;
                // TODO: 02.12.2021
                builder_Для_Согласования.setNumber(1);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mNotificationManagerДляЧАТА.getBubblePreference();
                }

                // TODO: 02.12.2021  сам запуск уведомления

                mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования), builder_Для_Согласования.build());////   mNotificationManagerДляЧАТА.notify(Integer.parseInt(PROCESS_ID_УведомленияПлановая), builder.build());
                ///TODO закрытие увидомления

           //     startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()
/*

                Log.i(this.getClass().getName(), "ЗАПУСК СЛУЖБА  УВЕДОМЛЕНИЯ ДЛЯ ЧАТА САМО ПОЯВЛЕНИЕ  ВАРИАНТ -2  startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());/" + new Date()
                        + "\n" + " PROCESS_ID_УведомленияПлановая " + PROCESS_ID_УведомленияПлановая+"\n"
                        +  "  САМИ СООБЩЕНИЯ : "+БуферСамиУведомленияЛинкСамиУведомления.toString());*/
            }
            /// startForeground(Integer.parseInt(PROCESS_ID_УведомленияПлановая),builder.build());//builder.build()


                }else{
                    Log.i(this.getClass().getName(), " ОБЩЕЕ УВЕДОМЛЕНИЯ НЕТ ДАГЫХ ЧТО ЗАПОЛНИТЬ УСЛОВИЯ ПУСТОЙ СЛУЖБЫ БуферСамиУведомленияЛинкСамиУведомления  " + БуферСамиУведомленияДляСогласования.toString());
                }


            //////

            // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            WorkInfo ИнформацияОЗапущенойСлужбе = WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДля_Согласования).get().get(0);

            Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА ....Внутри метода public Result doWork() BroadcastReceiver_Sous_Notificatioons_For_CommitPay " + ИмяСлужбыУведомленияДля_Согласования + "\n"
                    + " getState  " +
                    ИнформацияОЗапущенойСлужбе.getState().name() + "\n" +
                    " isFinished  " +
                    ИнформацияОЗапущенойСлужбе.getState().isFinished() + "\n" +
                    "getTags " +
                    ИнформацияОЗапущенойСлужбе.getTags() + "\n" +
                    "getRunAttemptCount " +
                    ИнформацияОЗапущенойСлужбе.getRunAttemptCount() + "\n" +
                    "getProgress " +
                    ИнформацияОЗапущенойСлужбе.getState().isFinished() + "\n" +
                    " время : " +new Date());




        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();

            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
            Log.d(getApplicationContext().getClass().getName(), " Стоп MyWork_Notifocations_Уведомления_Для_Согласования onDestroy() Exception ");

        }







    }



    ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет

    private boolean МетодВычисляемСтоитСоздаватьИЗапускатьСлужбуНапоминаний() {
        // final boolean[] РезультатВнутриЗапускатьУведомленияИлиНет = {false};
        try {



            ////

            Log.d(this.getClass().getName(), "  СЛУЖБА..... ДЛЯ проверяем нужно ли создвать и запускать службу нпоминаний работа с датами");

            /////todo если не изместен локальная версия являеться null перед проверкой то еще раз применяем выясняем локальную версиюПО для проверки

            long РезультатОбновлениеЛокальнойВерсииПО = 0;
            /////////
            /////TODO когда локальное ПО версию не нашли вытаемся ее увидить в базе

            int ПолучениеПравДляТаблицыПрава=0;

            Integer ПубличноеIDПолученныйИзСервлетаДляUUID=0;

            // TODO: 26.05.2021  перед обработкой уведомлений функция удаления Уведомлениц Сообщений не относящиеся к Пользователю
            ///
            Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 03.11.2021
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(getApplicationContext());

            ///
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
            //

            ////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");



            ////
            SQLiteCursor Курсор_ВычисляемПУбличныйID=null;
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ВычисляемПУбличныйID= (SQLiteCursor)
                    class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,public_contentменеджер.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );

            Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );


            /////
            if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                //////////
                Курсор_ВычисляемПУбличныйID.moveToFirst();

                ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);

                // TODO: 03.11.2021



                Log.w(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }

            // TODO: 02.03.2022

            if(ПубличноеIDПолученныйИзСервлетаДляUUID==null){
                // TODO: 02.03.2022
                ПубличноеIDПолученныйИзСервлетаДляUUID=0;

            }





            // TODO: 26.05.2021 данные по высичлению ID



            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","view_tasks");//old для другой уведомления data_chat
            ///////
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","status_write=?  AND id_user=? " +
                    " AND message IS NOT NULL  ");
            ///"_id > ?   AND _id< ?"


            //////
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",0);
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",  ПубличноеIDПолученныйИзСервлетаДляUUID);


            // TODO: 02.03.2022

            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
            ////
          // class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            ///
            SQLiteCursor   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата=null;


            Курсор_ДляСлужбыУведомлений_ТолькоДляЧата= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );


            ////////

            Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);





            // TODO: 26.05.2021 данные по высичлению ID

            SQLiteCursor Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач = МетодПолучениеТОлЬКоКурсораДЛяПолучнеиеКоличетсовЗадач(ПубличноеIDПолученныйИзСервлетаДляUUID);



            ////////

            Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач);




/*
            // TODO: 09.09.2021   ____old
             Курсор_IDпользоввателяДляСлужб=
                                    CREATE_DATABASE.ССылкаНаСозданнуюБазу.rawQuery("SELECT *  From notification", null);

*/


            ОбщееКоличествоНЕпрочитанныхСтрок=0;


            // TODO: 09.09.2021  resltat

            if ( Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount()>0) {
                //////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧат количествро сттрочек  :::  "
                        +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount());


                Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToFirst();
                // TODO: 02.03.2022

                Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач.moveToFirst();


                РезультатНужноЗапускатьУведомленияИлиНет=true;

                ОбщееКоличествоНЕпрочитанныхСтрок=       Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач.getCount();

                Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                // TODO: 19.11.2021


                // TODO: 19.11.2021
                if(ОбщееКоличествоНЕпрочитанныхСтрок>=1){
                    // TODO: 19.11.2021
                    ОбщееКоличествоНЕпрочитанныхСтрок=ОбщееКоличествоНЕпрочитанныхСтрок-1;
                    // TODO: 19.11.2021

                    Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                }


                // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника

                МетодПолучениеДатыПослеПолучениеПравСотрудникаДля_ОбщихУведомления(Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);




            } else {
                Log.w(this.getClass().getName(), "  СЛУЖБА...  НЕТ СООБЩЕНИ ДЛЯ ОТОБРАЖЕНИЯ В ЧАТЕ НЕТ ПРОПУСКАЕМ ХОЛОСТОЙ ХОД СЛУЖБЫ УВЕДОМЛЕНИЯ ЧАТ " +ПолучениеПравДляТаблицыПрава);

                // TODO: 15.11.2021
                РезультатНужноЗапускатьУведомленияИлиНет=false;

            }




        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

         /*   NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияПлановая))*/;
        }

        // TODO: 20.05.2021  результат
        return РезультатНужноЗапускатьУведомленияИлиНет ;
    }


    // TODO: 02.03.2022

    private SQLiteCursor МетодПолучениеТОлЬКоКурсораДЛяПолучнеиеКоличетсовЗадач(Integer ПубличноеIDПолученныйИзСервлетаДляUUID) throws ExecutionException, InterruptedException {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

        SQLiteCursor   Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач=null;
     try{
        ///
        class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

        ///
        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","view_tasks");//old для другой уведомления data_chat
        ///////
        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
        //
        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","status_write=?  AND id_user=? " +
                " AND message IS NOT NULL  ");
        ///"_id > ?   AND _id< ?"


        //////
        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",0);
        //
        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ПубличноеIDПолученныйИзСервлетаДляUUID);


        // TODO: 02.03.2022

        class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
        ////
        // class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
        ////
        //class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
        ///



        Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );


        ////////

        Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач);


    } catch (Exception e)

    {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
    }


        return Курсор_ДляСлужбыУведомлений_ТолькоДляЧатаТОлькоКоличествоЗадач;
    }


    // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника



    private void МетодПолучениеДатыПослеПолучениеПравСотрудникаДля_ОбщихУведомления(SQLiteCursor  Курсор_ДляСлужбыУведомлений_ТолькоДляЧата) throws ParseException {
        // TODO: 20.05.2021 действие вьторое

        try{

            Log.d(this.getClass().getName(), "   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "+Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);


            ////TODO start do


            do {
                ////

                //TODO перед созданеим

                БуферСамиУведомленияДляСогласования =new ArrayList();

                // TODO: 03.02.2022
                person=new Person.Builder();


                /////
                /////
                Integer ИндексКтоНаписалСообщениеID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("user_update");///id_user
                /////
                Integer КтоНаписалСообщениеID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексКтоНаписалСообщениеID);
                //
                String КтоНаписалСообщениеФИО=new String();

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеID " + КтоНаписалСообщениеID);

                // TODO: 07.02.2022 вытаскием текущий uuid для того чтобы далее установить сатутсс о ознакомлен

                /////
                Integer ИндексКтоНаписалСообщениеUUID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("uuid");///id_user
                /////
                Long КтоНаписалСообщениеUUID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getLong(ИндексКтоНаписалСообщениеUUID);

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеUUID " + КтоНаписалСообщениеUUID);

                // TODO: 16.11.2021 find FIO
                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","chat_users");
                ///////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                //
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","_id=?   AND name IS NOT NULL ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",КтоНаписалСообщениеID);

    /*            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
                ////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","5");*/

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                ///
                SQLiteCursor   Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал=null;


                Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );


                ////////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал "  +Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал);

                if (Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getCount()>0) {
                    //TODO

                    Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.moveToFirst();
                    КтоНаписалСообщениеФИО=      Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getString(0).trim();

                  //  КтоНаписалСообщениеФИО=      Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getString(0).trim();
                }

                ////////

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеФИО "  +КтоНаписалСообщениеФИО);









                // TODO: 15.11.2021
                Integer ИНдексПРочитаногоСообщенияUUID= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("uuid");

                Long UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getLong(ИНдексПРочитаногоСообщенияUUID);


                Log.d(this.getClass().getName(), "UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный "  +UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);





              Integer ИНдексПРочитаногоСообщения = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("date_update");//TODO OLD date_start

                String ВремНеПРочитаногоСообщения= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(ИНдексПРочитаногоСообщения);


                Log.d(this.getClass().getName(), " ВремНеПРочитаногоСообщения  " + ВремНеПРочитаногоСообщения);
                // TODO: 15.11.2021

                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//yyyy-MM-dd HH:mm:ss.SSS  ///TODO yyyy-MM-dd HH:mm
                //

                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                Date date = null;
                date = simpleDateFormat.parse(ВремНеПРочитаногоСообщения);

                simpleDateFormat.applyPattern(" dd EEEE yyyy HH:mm");//dd-MM-yyyy  // EEEE yyyy HH:mm"

                String ФиналВремНеПРочитаногоСообщения = simpleDateFormat.format(date);

                Log.d(this.getClass().getName(), "ФиналВремНеПРочитаногоСообщения " + ФиналВремНеПРочитаногоСообщения+"\n");

                ///TODO заполенения ДАННЫХ УВЕДОМЛЕНИЯ ОДНОРАЗОВАГО ДЛЯ ЧАТА
                // TODO: 03.02.2022

      /*          // TODO: 03.02.2022
                БуферСамиУведомленияЛинкСлужебнаИнформация.add("\n"+"автор:"+КтоНаписалСообщениеФИО);
                // TODO: 03.02.2022
                // TODO: 03.02.2022
                БуферСамиУведомленияЛинкСлужебнаИнформация.add("количество сообщений: +("+ОбщееКоличествоНЕпрочитанныхСтрок+")");*/
                // TODO: 03.02.2022
                //////todoСамиУведомленияВЧатеНеПрочитанный.matches("[а-яА-Я]")///[m-nM-N]

                // TODO: 04.02.2022
                // TODO: 15.11.2021
                Integer ИндексСтатусПрочтенияПриВыданнйоЗадании=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("status_write");

               Integer СамСтатусПрочтенияПриВыданнйоЗадании= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексСтатусПрочтенияПриВыданнйоЗадании);



                Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);



                // TODO: 15.11.2021
                Integer ИндексЗаданияID=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("id");


                Integer СамСтатусIDЗадание=0;

                 СамСтатусIDЗадание= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(ИндексЗаданияID);



                Log.d(this.getClass().getName(), " СамСтатусIDЗадание  " + СамСтатусIDЗадание);





                String СтатусДошлоПользователюЗадача;

                // TODO: 04.02.2022
                if (СамСтатусПрочтенияПриВыданнйоЗадании==0) {

                    СтатусДошлоПользователюЗадача="доставлено";

                    Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);
                } else {
                    СтатусДошлоПользователюЗадача="уже отработано";

                    Log.d(this.getClass().getName(), " СамСтатусПрочтенияПриВыданнйоЗадании  " + СамСтатусПрочтенияПриВыданнйоЗадании);
                }


                // TODO: 16.11.2021  само сообщение

                // TODO: 15.11.2021
                Integer ИндексСамогоЗадании=   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getColumnIndex("message");
                /////
                String СамиУведомленияВЧатеНеПрочитанный = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(ИндексСамогоЗадании).trim();


                Log.d(this.getClass().getName(), " СамиУведомленияВЧатеНеПрочитанный  " + СамиУведомленияВЧатеНеПрочитанный+
                        " СамСтатусIDЗадание " +СамСтатусIDЗадание);






                БуферСамиУведомленияДляСогласования.add("Задание"+"#"+СамСтатусIDЗадание+"\n"
                        +СамиУведомленияВЧатеНеПрочитанный +"\n"+
                        "создано :("+ФиналВремНеПРочитаногоСообщения+")");

                // TODO: 02.03.2022




                МетодПослеЗапоелнияВЦиклеЗадачЗаполянем(КтоНаписалСообщениеФИО, UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);

                Log.d(this.getClass().getName(), " БуферСамиУведомленияЛинкСамиУведомления  " + БуферСамиУведомленияДляСогласования.toString());



                // TODO: 19.11.2021  после обработки одно записи выходим
                //todo while
            } while (Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToNext());







            // TODO: 19.11.2021 после обработки добавляем

            // БуферСамиУведомленияЛинкСамиУведомления.append(" +").append("(").append(ОбщееКоличествоНЕпрочитанныхСтрок) .append(")").append(" сообщений.") ;
            // TODO: 19.11.2021
            Log.d(this.getClass().getName(), "СамиУведомления " + БуферСамиУведомленияДляСогласования.toString() +
                    " БуферСамиУведомленияЛинкСамиУведомления " + БуферСамиУведомленияДляСогласования.size()+
                    "   БуферСамиУведомленияЛинкСамиУведомления " + БуферСамиУведомленияДляСогласования.size());


            ////TODO закрываем курсор

            if (Курсор_ДляСлужбыУведомлений_ТолькоДляЧата!=null) {

                if (!Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.isClosed()) {

                    Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.close();
                }
            }


            // TODO: 19.05.2021  ошибки
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ

            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            notificationManager.cancel(Integer.parseInt(PROCESS_ID_УведомленияДляСогласования));
        }




    }

    private void МетодПослеЗапоелнияВЦиклеЗадачЗаполянем(String КтоНаписалСообщениеФИО,
                                                         Long UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный) {

        // TODO: 02.03.2022


        try{
            Log.d(this.getClass().getName(),"   onOpen БАЗА  ДАННЫХ   ДСУ-1 настройки базы КтоНаписалСообщениеФИО "+КтоНаписалСообщениеФИО+
                    " UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный "+UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);
            // TODO: 02.03.2022

        String СамиУведомленияВЧатеНеПрочитанный;
        // TODO: 04.02.2022

        СамиУведомленияВЧатеНеПрочитанный=null;

        СамиУведомленияВЧатеНеПрочитанный= БуферСамиУведомленияДляСогласования.toString().replace("[","");

        СамиУведомленияВЧатеНеПрочитанный=СамиУведомленияВЧатеНеПрочитанный.replace("]","");


        // TODO: 07.02.2022 заполения сообщения для ОБШЕЙ УВЕДОМЛЕНИЯ

        // TODO: 07.02.2022
        person.setKey(СамиУведомленияВЧатеНеПрочитанный);
        // TODO: 07.02.2022
        person.setBot(true);

        person.setName(КтоНаписалСообщениеФИО);
        // TODO: 07.02.2022 \
        // person.setIcon( IconCompat.createWithResource(Контекст, R.drawable.icon_dsu1_for_fragment1_chat2));
            // TODO: 07.02.2022

            person.setIcon(IconCompat.createWithResource(context, R.drawable.icon_dsu1_for_fragment1_chat2_for_public));

            // TODO: 07.02.2022устанавливаем записываем текущий UUID для того чтобы потом перердатьего для Озванкомления

            HashMap<Integer, Long> hashMapХэшДляЗапоминиялUUID = new HashMap();
            // TODO: 07.02.2022
            hashMapХэшДляЗапоминиялUUID.put(new Random().nextInt(), UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный);

            person.setUri(String.valueOf(UUIDРочитаногоЗаданиеДляКотрогоДалееБудетПроизведенаСменаСтсусаНАОзнакомленный));
            //  person .setUri(String.valueOf(hashMapХэшДляЗапоминиялUUID));
            // TODO: 07.02.2022  person
            person.build();


            // TODO: 03.02.2022  УВЕДОМЛЕНИЯ ДЛЯ ОБШЕЙ СИНХРОНИАЗЦИИ
            messagingStyleДля_СогласованияУведомлений.addMessage(person.build().getKey(), System.currentTimeMillis(), "от:" + person.build().getName()
                    + " +" +
                    "(" + ОбщееКоличествоНЕпрочитанныхСтрок + ")");


        Log.d(this.getClass().getName(), "СамиУведомленияВЧатеНеПрочитанный "
                + СамиУведомленияВЧатеНеПрочитанный +" БуферСамиУведомленияЛинкСамиУведомления.size() "
                + БуферСамиУведомленияДляСогласования.size()+ " messagingStyleДля_ОбщихУведомлений " + messagingStyleДля_СогласованияУведомлений + "  СамиУведомленияВЧатеНеПрочитанный " +СамиУведомленияВЧатеНеПрочитанный);




    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }


    // TODO: 17.11.2021  end classs worl manager

    }






























