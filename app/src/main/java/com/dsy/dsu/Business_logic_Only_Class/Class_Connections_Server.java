package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.preference.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Class_Connections_Server  extends  Class_GRUD_SQL_Operations {
    private Context context1;
    private  Class_GRUD_SQL_Operations class_grud_sql_operations=null;
    private SharedPreferences preferences;
    private AsyncTaskLoader<Boolean> asyncTaskLoader;
    public Class_Connections_Server(Context context) {
        super(context);
        context1 =context;
        class_grud_sql_operations=new Class_GRUD_SQL_Operations(context1);
        //preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences =context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }
    ///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ
    public Boolean МетодПингаСервераРаботаетИлиНет(Context КонтекстКоторыйДляСинхронизации) {
        Boolean РезультатПингакСервераРаботаетЛиОНРеально=false;
                            try{
                                РезультатПингакСервераРаботаетЛиОНРеально=  МетодПингаСервераРаботаетИлиНетВнутри(КонтекстКоторыйДляСинхронизации);
                            Log.w(КонтекстКоторыйДляСинхронизации.getClass().getName(), " РезультатПингакСервераРаботаетЛиОНРеально "+РезультатПингакСервераРаботаетЛиОНРеально);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (! e.toString().equalsIgnoreCase("java.util.concurrent.TimeoutException: The source did not signal an event for 5 seconds and has been terminated.")) {
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(КонтекстКоторыйДляСинхронизации).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                            return РезультатПингакСервераРаботаетЛиОНРеально;
                        }
    ///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ
    private Boolean МетодПингаСервераРаботаетИлиНетВнутри(@NotNull Context КонтекстКоторыйДляСинхронизации)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
         Boolean результатПрозвонаСокетом = false;
        try {
            PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context1);
            Map<Integer,String> concurrentHashMapАдресаПодключенияКСерверу   =  new PUBLIC_CONTENT(context1).getМассивПортовСервера();
            for (Map.Entry<Integer,String> entry : concurrentHashMapАдресаПодключенияКСерверу.entrySet()) {
                Integer   ИмяПорта =    entry.getKey();
                String     ИмяСервера=     entry.getValue();
                Log.d(Class_MODEL_synchronized.class.getName()," ИмяСервера"+ ИмяСервера+" ИмяПорта "+ИмяПорта);
                // TODO: 10.11.2022  пинг к сервера
                   Integer  БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer =
                      new Class_MODEL_synchronized(context1).
                              УниверсальныйБуферПолучениеДанныхсСервераТОлькоДляПинга(null, "",
                        "", "application/gzip",
                                      "Хотим Получить Статус Реальной Работы SQL SERVER",
                                      0l, "",3000,"",
                        0l,ИмяСервера, ИмяПорта);//application/gzip
                Log.d(Class_MODEL_synchronized.class.getName(), "  БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer" + БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer);
                    if (БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer==null) {
                        БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer=0;
                        Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer);
                    }
                    // TODO: 16.12.2021  положитльеный результат пинга
                    if (БуферПолучениеДанныхРЕальныйСтатусРАботыSQLServer>0) {
                        результатПрозвонаСокетом = true;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("ИмяСервера",  ИмяСервера);
                        editor.putInt("ИмяПорта",ИмяПорта);
                        editor.commit();
                        Log.e(Class_MODEL_synchronized.class.getName(), " ИмяСервера" + ИмяСервера+ "ИмяПорта " +ИмяПорта+editor);
                        break;
                    }else{
                        результатПрозвонаСокетом = false;
                        Log.e(Class_MODEL_synchronized.class.getName(), " ОШИБКА НЕТ СВЯЗИ С СЕВРЕРОМ  результатПрозвонаСокетом[0] " + результатПрозвонаСокетом);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(КонтекстКоторыйДляСинхронизации).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатПрозвонаСокетом;
    }

}
