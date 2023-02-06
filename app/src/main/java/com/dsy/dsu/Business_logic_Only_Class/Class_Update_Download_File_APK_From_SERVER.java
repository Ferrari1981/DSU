package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;

import com.dsy.dsu.R;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;



public class Class_Update_Download_File_APK_From_SERVER {
    private   String ТипПодключенияИнтернтаДляСлужбы;
    private Integer СервернаяВерсияПОВнутри = 0;
    private  Integer ЛокальнаяВерсияПО = 0;
    private String PROCESS_ID_UpdateSoft = "19";
    private Context context;
    private  Activity activity;
    private SharedPreferences preferences;
    public Class_Update_Download_File_APK_From_SERVER(Context contextВнутри, Activity activityВнутри) {
    this.    context= contextВнутри;
      this.  activity=activityВнутри;
       // preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }
    // TODO: 02.04.2021 update service po
    public void МетодНачалаЗапускаОбновленияПО(Integer СервернаяВерсияПОВнутриИзСлужбы) throws ExecutionException, InterruptedException {
        Log.w(this.getClass().getName(), "   МетодНачалаЗапускаОбновленияПО СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
        try {
            СервернаяВерсияПОВнутри = СервернаяВерсияПОВнутриИзСлужбы;
                    Log.i(this.getClass().getName(), "СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри +
                            " СервернаяВерсияПОВнутриИзСлужбы" + СервернаяВерсияПОВнутриИзСлужбы);
                    МетодОценкииСетиПередЗагрузкойAPKсСервера();
            if (ТипПодключенияИнтернтаДляСлужбы != null) {
                Log.i(this.getClass().getName(), "ТипПодключенияИнтернтаДляСлужбы " + ТипПодключенияИнтернтаДляСлужбы);
                if (ТипПодключенияИнтернтаДляСлужбы.equals("WIFI")  || ТипПодключенияИнтернтаДляСлужбы.equals("Mobile")  ) {
                    МетодОпределнияВерсийПОСервераКлиентаИПринятиеРешенияНаСкачиваниеОбновлениеПО();
                    Log.i(this.getClass().getName(), "МетодОпределнияВерсийПОСервераКлиентаИПринятиеРешенияНаСкачиваниеОбновлениеПО " );
                }else {
                    Log.e(this.getClass().getName(), "неТ СВЯЗИ ДЛЯ ЗАГРУЗКИ ПО ТипПодключенияИнтернтаДляСлужбы "  +ТипПодключенияИнтернтаДляСлужбы  );
                }
            }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
    }


    public void МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK() {

        try {


/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            /*   File  ФайлыДляОбновлениеПОУдаление = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS + "/" + "*.apk");
*/
            File ФайлыДляОбновлениеПОУдалениеСамФайлJSon = null;

            File[] FilesФайлыУдаления;


            if (Build.VERSION.SDK_INT >= 30) {
                ФайлыДляОбновлениеПОУдалениеСамФайлJSon = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            } else {

                ФайлыДляОбновлениеПОУдалениеСамФайлJSon = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);

            }
            FilenameFilter filenameFilter1 = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // TODO: 31.03.2022
                    if (!name.isEmpty()) {
                        Boolean ЕслиТАкойФайл = name.matches("(.*)update_dsu1(.*)");
                        // TODO: 31.03.2022
                        Log.i(this.getClass().getName(), " fileName" + name);
                        // TODO: 31.03.2022
                        if (ЕслиТАкойФайл) {
                            // TODO: 31.03.2022
                            if (dir.exists()) {
                                // TODO: 03.04.2022
                                dir.delete();
                            }
                            // TODO: 31.03.2022
                            Log.i(this.getClass().getName(), " fileName" + name + "ЕслиТАкойФайл " + ЕслиТАкойФайл);
                            return true;
                        }

                    }
                    return false;
                }
            };

            // TODO: 01.04.2022
            Log.i(this.getClass().getName(), " Files1[i] " + " ФайлыДляОбновлениеПОУдалениеСамФайлJSon " + ФайлыДляОбновлениеПОУдалениеСамФайлJSon);


            // TODO: 01.04.2022 two


            FilenameFilter filenameFilter2 = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // TODO: 31.03.2022
                    if (!name.isEmpty()) {
                        Boolean ЕслиТАкойФайл = name.matches("(.*)app-release(.*)");
                        // TODO: 31.03.2022
                        Log.i(this.getClass().getName(), " fileName" + name);
                        // TODO: 31.03.2022
                        if (ЕслиТАкойФайл) {
                            // TODO: 31.03.2022
                            if (dir.exists()) {
                                // TODO: 03.04.2022
                                dir.delete();
                            }
                            // TODO: 31.03.2022
                            Log.i(this.getClass().getName(), " fileName" + name + "ЕслиТАкойФайл " + ЕслиТАкойФайл);
                            return true;
                        }

                    }
                    return false;
                }
            };
            // TODO: 01.04.2022 удалепние файлов
            // TODO: 01.04.2022
            Log.i(this.getClass().getName(), " Files1[i] " + " ФайлыДляОбновлениеПОУдалениеСамФайлJSon " + ФайлыДляОбновлениеПОУдалениеСамФайлJSon);


            // TODO: 01.04.2022  tree

            // TODO: 01.04.2022 удалепние файлов
            // TODO: 01.04.2022 удалепние файлов
            FilesФайлыУдаления = ФайлыДляОбновлениеПОУдалениеСамФайлJSon.listFiles(filenameFilter1);
            // TODO: 01.04.2022 удалепние файлов
            FilesФайлыУдаления = ФайлыДляОбновлениеПОУдалениеСамФайлJSon.listFiles(filenameFilter2);
            // TODO: 01.04.2022 удалепние файлов
            // TODO: 01.04.2022 удалепние файлов
            if (ФайлыДляОбновлениеПОУдалениеСамФайлJSon.exists() == true) {
                // TODO: 01.04.2022
                // TODO: 01.04.2022
                Log.i(this.getClass().getName(), " Files1[i] " + "  УДЛАЕНИЕ ...  ФайлыДляОбновлениеПОУдалениеСамФайлJSon.length() " + ФайлыДляОбновлениеПОУдалениеСамФайлJSon.length());
                // TODO: 01.04.2022 удалепние файлов
                ФайлыДляОбновлениеПОУдалениеСамФайлJSon.delete();
                // TODO: 01.04.2022 удалепние файлов
                ФайлыДляОбновлениеПОУдалениеСамФайлJSon.deleteOnExit();
            }
            // TODO: 01.04.2022
            for (int i = 0; i < FilesФайлыУдаления.length; i++) {

                // TODO: 01.04.2022
                if (FilesФайлыУдаления[i].exists()) {
                    // TODO: 01.04.2022
                    FilesФайлыУдаления[i].delete();
                    // TODO: 01.04.2022
                    // TODO: 01.04.2022
                    FilesФайлыУдаления[i].deleteOnExit();
                    // TODO: 01.04.2022
                    Log.i(this.getClass().getName(), " Files1[i] " + "  УДЛАЕНИЕ ...  ФайлыДляОбновлениеПОУдалениеСамФайлJSon.length() " + ФайлыДляОбновлениеПОУдалениеСамФайлJSon.length());

                }

            }
// TODO: 02.04.2022  метод для удаленя только json  версии файла



            // TODO: 30.03.2022 СМА УДАЛЕНИЕ ФАЙЛОВ


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            Log.d(this.getClass().getName(), " ОШИБКА work manager Обновление ПО onDestroy() Exception ");


        }
    }


    public void МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО() {

        try {


/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА


            File ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо = null;

            File[] FilesФайлыУдаленияДляФайлаJSONАнализаВерсии;


            if (Build.VERSION.SDK_INT >= 30) {
                ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            } else {

                ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);

            }
            FilenameFilter filenameFilter1 = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // TODO: 31.03.2022
                    if (!name.isEmpty()) {
                        Boolean ЕслиТАкойФайл = name.matches("(.*)output-metadata.json(.*)");
                        // TODO: 31.03.2022
                        Log.i(this.getClass().getName(), " fileName" + name);
                        // TODO: 31.03.2022
                        if (ЕслиТАкойФайл) {
                            // TODO: 31.03.2022
                            if (dir.exists()) {
                                // TODO: 03.04.2022
                                dir.delete();
                            }
                            // TODO: 31.03.2022
                            Log.i(this.getClass().getName(), " fileName" + name + "ЕслиТАкойФайл " + ЕслиТАкойФайл);
                            return true;
                        }

                    }
                    return false;
                }
            };

            // TODO: 01.04.2022
            Log.i(this.getClass().getName(), " Files1[i] " + " ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо " + ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо);


            // TODO: 01.04.2022 two


            FilenameFilter filenameFilter2 = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // TODO: 31.03.2022
                    if (!name.isEmpty()) {
                        Boolean ЕслиТАкойФайл = name.matches("(.*)analysis_version(.*)");
                        // TODO: 31.03.2022
                        Log.i(this.getClass().getName(), " fileName" + name);
                        // TODO: 31.03.2022
                        if (ЕслиТАкойФайл) {
                            // TODO: 31.03.2022
                            if (dir.exists()) {
                                // TODO: 03.04.2022
                                dir.delete();
                            }
                            // TODO: 31.03.2022
                            Log.i(this.getClass().getName(), " fileName" + name + "ЕслиТАкойФайл " + ЕслиТАкойФайл);
                            return true;
                        }

                    }
                    return false;
                }
            };
            // TODO: 01.04.2022 удалепние файлов
            // TODO: 01.04.2022
            Log.i(this.getClass().getName(), " Files1[i] " + " ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо " + ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо);

            // TODO: 01.04.2022 удалепние файлов
            FilesФайлыУдаленияДляФайлаJSONАнализаВерсии = ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.listFiles(filenameFilter1);
            // TODO: 01.04.2022 удалепние файлов
            FilesФайлыУдаленияДляФайлаJSONАнализаВерсии = ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.listFiles(filenameFilter2);
            // TODO: 01.04.2022 удалепние файлов
            // TODO: 01.04.2022 удалепние файлов
            if (ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.exists() == true) {
                // TODO: 01.04.2022
                // TODO: 01.04.2022
                Log.i(this.getClass().getName(), " Files1[i] " + "  УДЛАЕНИЕ ...  ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.length() " + ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.length());
                // TODO: 01.04.2022 удалепние файлов
                ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.delete();
                // TODO: 01.04.2022 удалепние файлов
                ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.deleteOnExit();
            }
            // TODO: 01.04.2022
            if (FilesФайлыУдаленияДляФайлаJSONАнализаВерсии!=null) {
                for (int i = 0; i < FilesФайлыУдаленияДляФайлаJSONАнализаВерсии.length; i++) {

                    // TODO: 01.04.2022
                    if (FilesФайлыУдаленияДляФайлаJSONАнализаВерсии[i].exists()) {
                        // TODO: 01.04.2022
                        FilesФайлыУдаленияДляФайлаJSONАнализаВерсии[i].delete();
                        // TODO: 01.04.2022
                        // TODO: 01.04.2022
                        FilesФайлыУдаленияДляФайлаJSONАнализаВерсии[i].deleteOnExit();
                        // TODO: 01.04.2022
                        Log.i(this.getClass().getName(), " Files1[i] " + "  УДЛАЕНИЕ ...  ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.length() " + ФайлыДляУдаланиеИнформационогоТестовогоФайлаJSON_дляПо.length());

                    }

                }
            }


            // TODO: 30.03.2022 СМА УДАЛЕНИЕ ФАЙЛОВ


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            Log.d(this.getClass().getName(), " ОШИБКА work manager Обновление ПО onDestroy() Exception ");


        }
    }



    ////TODO АНАЛИЗИРУЕМ ПРИШЕДШИЙ ФАЙЛ И ПРИНИМАЕМ РЕШЕНИЕ НА СКАЧИВАНЕИ ФАЙЛА ИЛИ НЕТ



    private void МетодОпределнияВерсийПОСервераКлиентаИПринятиеРешенияНаСкачиваниеОбновлениеПО() {
                try {
///////////TODO ПРИСТУПАЕМ К ЗАПУСКУ ОБНОВЛЕНИЕ ФАЙЛА . APK ТОЛЬКО КОГДА ВЕРСИЯ ДАННЫХ НА СЕРВЕРЕ БОЛЬШЕ ЧЕМ НА КЛИЕНТЕ (Android)
                            МетодЗагрузкиФайлаAPK();
                            Log.d(this.getClass().getName(), "  СЛУЖБА ЗАПУСКАЕМ...  Обновление  ПО .APK " +
                                    " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО + "СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
                } catch (Exception e) {
                    //  Block of code to handle errors
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                           new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }



        //////hedler end
    }


    private void МетодОценкииСетиПередЗагрузкойAPKсСервера() {
        try {
            /////todo тут МЫ ПОЛУЧАЕМ В КАКОЙ МОМЕНТ ТИП ПОДКЛЮЧЕНИЯ НА ТЕЛЕФОНЕ МОБИЛЯ ИЛИ  WIFI  И В ЗАВИСИМОСТИ ЧТОБЫ ПОНЯТЬ ЧЕ ЗА ДЕЛА
                ТипПодключенияИнтернтаДляСлужбы = null;
                ТипПодключенияИнтернтаДляСлужбы = МетодОпределяемКакойТипПодключениеWIFIилиMobileДляСлужбы(context);
                Log.d(this.getClass().getName(), " ТипПодключенияИнтернтаДляСлужбы  " + ТипПодключенияИнтернтаДляСлужбы);
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }











    ////TODO
    protected void МетодЗагрузкиФайлаAPK() {
        try {
            Log.d(this.getClass().getName(), " СЛУЖБА ... МЕТОД ОБНОВЛЕНИЯ ПО РАБОТАЕТ......"+new Date());
            //////TODO вторая часть ЕСЛИ ВЕРСИЯ ПРОГРАМЫ НА СЕРВЕРЕ ВЫШЕ ЧЕМ НА АЕДРОЙДЕ ТО ЗАПУСКАЕМ  ЗАГРУЗКИ ПРИЛОЖЕНИЯ (НАПРИМЕР НА АНДРОЙДЕ 102 , А НА СЕРВЕРЕ 103, 104,итд.)
                File  ФайлыДляОбновлениеПО=null;
                        Log.d(this.getClass().getName(), "    ПУТИ В ФАЙЛУ   " + "\n"
                        + ФайлыДляОбновлениеПО);
                PackageInfo ИнформацияОФайле =null;
            // TODO: 02.04.2022 зпускаем работут по анализу  СКАЧКИ ПРОГРАММЫ ТАБЕЛТНЫЙ УЧЁТ С СЕРВЕРА
                    ///TODO загрузка apk ФАЙЛА
                    МетодНепостредственннойЗагрузкиAPKФайлов(ФайлыДляОбновлениеПО, ИнформацияОФайле);
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                   /// МетодКоторыйЗапускаетОбновлениеПООтложенныйЗапускна10Секунд();
            ///////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }











    private void МетодНепостредственннойЗагрузкиAPKФайлов(File файлыДляОбновлениеПО, PackageInfo info) throws IOException {
        try {
            String Adress_String;
/////TODO  загрузка ФАЙЛ.APK ФАЙЛАv
           /* LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_layout, null);
            toast.setView(view);
            toast.show();*/
            final File[] УниверсальныйБуферAPKФайлаПОсСервераВнутри = {null};
            Observable УниверсальныйБуферAPKФайлаПОсСервера = Observable.fromCallable(new Callable<File>() {
                @Override
                public File call() throws Exception {

                        Handler handlerПО=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                            @Override
                            public boolean handleMessage(@NonNull Message msg) {
                                Toast toast=     Toast.makeText(context, "Загрузка ПО...", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM,0,50);
                    /*    ImageView img=new ImageView(context);
                        // give the drawble resource for the ImageView
                        img.setImageResource(R.drawable.icon_dsu1_download);
                        toast.setView(img);*/
                                toast.show();
                                Log.i(this.getClass().getName(), "Запускаем только начало  Observable УниверсальныйБуферAPKФайлаПОсСервера=Observable.fromCallable............ ");
                                ////TODO НАЧИНАЕМ ЗАГРУЗКИ С ИНТРЕНТА ФАЙЛ А ЕСЛИ ТОЛЬКО ЕГО НЕТ УЖЕ НА КЛИЕНТЕ
                                Log.i(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск     УниверсальныйБуферAPKФайлаПОсСервера.subscribe  " +
                                        "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                                        "\n" + "     УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] " + УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] +
                                        "\n"+ " Thread.currentThread().getName() " +Thread.currentThread().getName());
                                             msg.getTarget().removeCallbacksAndMessages(msg);
                                return true;
                            }
                        });
                    handlerПО.obtainMessage(0,0,0,new Object()).sendToTarget();
                    //todo конец главного потока
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                 String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 19.12.2021  загрузка файда  .apk    УниверсальныйБуферAPKФайлаПОсСервера("dsu1.glassfish/update_android_dsu1/app-release.apk", "update_dsu1.apk",
                    УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] = new Class_MODEL_synchronized(context).
                            УниверсальныйБуферAPKФайлаПОсСервера(new PUBLIC_CONTENT(context).getСсылкаНаРежимСервера()+ "/update_android_dsu1/app-release.apk", "update_dsu1.apk",
                                    context, ИмяСерверИзХранилица ,ПортСерверИзХранилица);
                    Log.i(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск   Observable УниверсальныйБуферAPKФайлаПОсСервера = Observable.fromCallable(new Callable<File>()  " +
                            "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                            "\n" + "     УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] " + УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] +
                            "\n"+ " Thread.currentThread().getName() " +Thread.currentThread().getName());
                    return УниверсальныйБуферAPKФайлаПОсСервераВнутри[0];
                }
            })
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск    onComplete" +
                                            "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                                            "\n" + "     УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] " + УниверсальныйБуферAPKФайлаПОсСервераВнутри[0] +
                                            "\n" + " Thread.currentThread().getName() " + Thread.currentThread().getName());
                                    if (УниверсальныйБуферAPKФайлаПОсСервераВнутри[0].length() > 0) {
                                        try {
// TODO: 25.03.2022 ТУТ МЫ ОТПРВЯЛЕМ ВЕРИСЮ ДАННЫХ И ФАЙЛ ПРИУСТАВНВОЕ по ТАБЕЛЬНЫЙ УЧЁТ
                                            Intent intentДляУстановеПО = new Intent();
                                            intentДляУстановеПО.setAction("CompletePO");
                                            Bundle bundleУстановитьПО = new Bundle();
                                            bundleУстановитьПО.putInt("СервернаяВерсияПОВнутри", СервернаяВерсияПОВнутри);
                                            bundleУстановитьПО.putSerializable("СервернаяВерсияПОCамФайлДляПередачи", УниверсальныйБуферAPKФайлаПОсСервераВнутри[0]);
                                            bundleУстановитьПО.putLong("СервернаяВерсияПОРазмерФайла", УниверсальныйБуферAPKФайлаПОсСервераВнутри[0].length());
                                            intentДляУстановеПО.putExtras(bundleУстановитьПО);
                                            Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск   bundleУстановитьПО  " + bundleУстановитьПО);
                                            LocalBroadcastManager localBroadcastManagerОтправляемНаActivityFaceApp = LocalBroadcastManager.getInstance(context);
                                            localBroadcastManagerОтправляемНаActivityFaceApp.sendBroadcast(intentДляУстановеПО);
                                            Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри localBroadcastManagerОтправляемНаActivityFaceApp " + localBroadcastManagerОтправляемНаActivityFaceApp
                                                    + " СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
                   /*                 Activity activity = null;
                                    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                                    List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                                        String namaa = taskInfo.get(0).topActivity.getClassName().toString();
                                        Class<?> myClass = Class.forName(namaa);
                                        activity = (Activity) myClass.newInstance();*/

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    }
                                }
                            })
                                    .onErrorComplete(new Predicate<Throwable>() {
                                        @Override
                                        public boolean test(Throwable throwable) throws Throwable {
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            return false;
                                        }
                                    });
            УниверсальныйБуферAPKФайлаПОсСервера.subscribe();
            // TODO: 18.12.2021
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    ////TODO метод который отпредеяеть КАКОЙ ТИП ПОДКЛЮЧЕНИ К ИНТРЕНТУ ЧЕРЕЗ WIFI ИЛИ MOBILE
    private String МетодОпределяемКакойТипПодключениеWIFIилиMobileДляСлужбы(Context КонтекстКоторыйДляСинхронизации) {

        try{

        ConnectivityManager cm = (ConnectivityManager) КонтекстКоторыйДляСинхронизации.getSystemService(Context.CONNECTIVITY_SERVICE);
        ////////
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if ( wifiInfo.isConnected()) {

            Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через wifi");

            return "WIFI";
        }else{

            //

            ////////
            NetworkInfo wifiInfoMObile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifiInfoMObile.isConnected()) {

                Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через mobile");

                return "Mobile";
            }
        }



    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return null;
    }

    ///TODO  конец  методы перерд перед  СИНХРОНИЗАЦИЯ ///TODO СИНХРОНИЗАЦИЯ при запуске прилиложения

































}











