package com.dsy.dsu.Code_For_Services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;


import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.Class_Visible_Processing_Async;
import com.dsy.dsu.Business_logic_Only_Class.Class__Generation_Genetal_Tables;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Service_For_Remote_Async extends IntentService {
    public LocalBinderДляТабеля binder = new LocalBinderДляТабеля();
    private Context context;
    private    Messenger messengerCallBacks;
    private AsyncTaskLoader<Integer> asyncTaskLoaderВизуальнаяСинхронизация;
    private  Integer МаксималноеКоличествоСтрочекJSON;
    private SharedPreferences preferences;
    private   String Проценты;
    private Integer ИндексВизуальнойДляPrograssBar=0;
    public Service_For_Remote_Async() {
        super("Service_For_Remote_Async");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
     context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }

    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            try{
                  Bundle data =msg.getData();
                  messengerCallBacks=msg.replyTo;
                  Intent intentДляФоновойСинхронизации=new Intent("ЗапусAsyncBackground");
                  intentДляФоновойСинхронизации.putExtras(data);
                  onHandleIntent(intentДляФоновойСинхронизации);
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        }
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
   public class LocalBinderДляТабеля extends Binder {
        public Service_For_Remote_Async getService() {
            // Return this instance of LocalService so clients can call public methods
            //   return Service_For_Remote_Async.this;
            return Service_For_Remote_Async.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
            super.linkToDeath(recipient, flags);
        }

        @Override
        public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
            return super.unlinkToDeath(recipient, flags);
        }
    }
    final Messenger messenger = new Messenger(new IncomingHandler());
/*
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return binder;
    }*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return messenger.getBinder();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context =getApplicationContext();
        // TODO: 28.09.2022  запускаем службу табелей
            МетодAsyncИзСлужбы(context, intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }



    public Integer МетодAsyncИзСлужбы(@NonNull Context context, @NonNull Intent intent) {
        Integer ФинальныйРезультатAsyncBackgroud=0;
        try {
            if( this.context==null){
                this.context=context;
            }
                    // TODO: 05.11.2022 запуск синхрониазции 
                          ФинальныйРезультатAsyncBackgroud=       МетодЗапускаAsyncBackgronud(context);
                          Log.w(this.getClass().getName(), "   ФинальныйРезультатAsyncBackgroud " + ФинальныйРезультатAsyncBackgroud);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return ФинальныйРезультатAsyncBackgroud;
    }

    // TODO: 22.12.2022  запск СИНХРОНИЗАЦИИ АСИНХРОНННО
    public Integer МетодЗапускаAsyncBackgronud(@NonNull Context context)
            throws NoSuchPaddingException, NoSuchAlgorithmException, RemoteException, InvalidKeyException {
        Integer РезультатAsyncTaskСинхронизации=0;
        try{
        // TODO: 11.10.2022  запускаем главную фоновую синхрониазцию
            asyncTaskLoaderВизуальнаяСинхронизация =new AsyncTaskLoader(context) {
               @Override
               public void commitContentChanged() {
                   super.commitContentChanged();
               }
               @Nullable
                @Override
                public Integer loadInBackground() {
                Integer  ФинальныйРезультатAsyncBackgroud=0;
                    try{
                        ФинальныйРезультатAsyncBackgroud = new Class_Engine_SQL(context).МетодЗАпускаФоновойСинхронизации(context);
                        Log.d(context.getClass().getName(), "\n"
                                + "   ФинальныйРезультатAsyncBackgroud " +   ФинальныйРезультатAsyncBackgroud);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                }
                    commitContentChanged();
                    return   ФинальныйРезультатAsyncBackgroud;
                }
            };
                asyncTaskLoaderВизуальнаяСинхронизация.startLoading();
                asyncTaskLoaderВизуальнаяСинхронизация.forceLoad();
                asyncTaskLoaderВизуальнаяСинхронизация.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Integer>() {
                    @Override
                    public void onLoadComplete(@NonNull Loader<Integer> loader, @Nullable Integer РезультатAsyncTaskСинхронизации) {
                        try{
                        МетодПослеAsyncTaskЗавершающий(РезультатAsyncTaskСинхронизации, context);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                    }
                    }
                });
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }/*finally {
            РезультатAsyncTaskСинхронизации=asyncTaskLoaderВизуальнаяСинхронизация.loadInBackground();
            // TODO: 08.11.2022 запускаем как синхрониазция прошла
            МетодПослеAsyncTaskЗавершающий(РезультатAsyncTaskСинхронизации, context);
        }*/
        return РезультатAsyncTaskСинхронизации;
    }
    // TODO: 22.12.2022  запск СИНХРОНИЗАЦИИ сИНХРОННО длЯ WORK MANAGER
    public Integer МетодЗапускаAsyncBackgronudДляWorkManager(@NonNull Context context)
            throws NoSuchPaddingException, NoSuchAlgorithmException, RemoteException, InvalidKeyException {
        Integer РезультатAsyncTaskСинхронизации=0;
        try{
            // TODO: 11.10.2022  запускаем главную фоновую синхрониазцию
            asyncTaskLoaderВизуальнаяСинхронизация =new AsyncTaskLoader(context) {
                @Override
                public void commitContentChanged() {
                    super.commitContentChanged();
                }
                @Nullable
                @Override
                public Integer loadInBackground() {
                    Integer  ФинальныйРезультатAsyncBackgroud=0;
                    try{
                        ФинальныйРезультатAsyncBackgroud = new Class_Engine_SQL(context).МетодЗАпускаФоновойСинхронизации(context);
                        Log.d(context.getClass().getName(), "\n"
                                + "   ФинальныйРезультатAsyncBackgroud " +   ФинальныйРезультатAsyncBackgroud);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                    }
                    return   ФинальныйРезультатAsyncBackgroud;
                }
            };
            asyncTaskLoaderВизуальнаяСинхронизация.startLoading();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            РезультатAsyncTaskСинхронизации=asyncTaskLoaderВизуальнаяСинхронизация.loadInBackground();
            // TODO: 08.11.2022 запускаем как синхрониазция прошла
            МетодПослеAsyncTaskЗавершающий(РезультатAsyncTaskСинхронизации, context);
        }
        return РезультатAsyncTaskСинхронизации;
    }

    private void МетодПослеAsyncTaskЗавершающий(@Nullable Integer data, @NonNull Context context)
            throws RemoteException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {
        try{
        asyncTaskLoaderВизуальнаяСинхронизация.reset();
        // TODO: 05.11.2022  после  ВЫПОЛЕНИЯ СИНЗХРОНИАЗИИ СООБЩАЕМ ОБ ОКОНЧАТИИ СИХРОНИАЗЦИИ ВИЗУАЛЬТА
        new Class_Engine_SQL(context).МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,МаксималноеКоличествоСтрочекJSON,
                Проценты,null,"ФинишВыходИзAsyncBackground",false,false);
        Log.d(context.getClass().getName(), "\n" + " МаксималноеКоличествоСтрочекJSON: " +МаксималноеКоличествоСтрочекJSON );
        // TODO: 25.09.2022  Удаление удаленного статуса записейdata;
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


































// TODO: 10.10.2022 КЛАСС ГЛАВНОЙ СИНХРОНИАЗЦИИИ

//////-------TODO  ЭТО ПЕРВЫЙ КОНТРОЛЛЕР КОТОРЫЙ ВИЗУАЛИЗИРУЕТ СИНХРОНИЗАЦИЮ С ПРОГРАССБАРОМ---------------------------------------------------------------


///TODO------------------------------------------------------ ЭТО  ВТОРОЙ КОНТРОЛЛЕР КОТОРЫЙ ЗАПУСКАЕТ СИНХРОНИЗАЦИЮ В ФОНЕ  (ВНУТРИ ПРИЛОЖЕНИЕ)---------------------------------------------------


    protected class Class_Engine_SQL extends Class_MODEL_synchronized {
        // TODO: 28.07.2022  переменые
        public    Context context;
        private    Integer ПубличныйРезультатОтветаОтСерврераУспешно=0;
        private CopyOnWriteArrayList<String> ГлавныеТаблицыСинхронизации =new CopyOnWriteArrayList();
        private  SQLiteDatabase СсылкаНаБазуSqlite =null;
        private  PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации;
        private boolean ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА = false;
        private  String ФлагКакуюЧастьСинхронизацииЗапускаем =new String();
        private  Long ВерсияДанныхРеальнаяНаСейчасНаSqlServer =0l;
        private   ContentValues ТекущийАдаптерДляВсего =null;
        private ArrayList<ContentValues> АдаптерДляВставкиИОбновления = new ArrayList<>();
        private  Integer ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы=0;
        // TODO: 28.07.2022
        public Class_Engine_SQL(@NotNull Context context)
                throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            super(context);
            this.context=context;
            public_contentДатыДляГлавныхТаблицСинхронизации=new PUBLIC_CONTENT(context);
            СсылкаНаБазуSqlite =new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу();
            Log.w(context.getClass().getName(), "Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы" + СсылкаНаБазуSqlite);
        }

        // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
        public Integer МетодЗАпускаФоновойСинхронизации(@NotNull Context context) throws InterruptedException {
            Integer      РезультатаСинхронизации = 0;
           
            try{this.context=context;
                ГлавныеТаблицыСинхронизации = 
                        new Class__Generation_Genetal_Tables(context).МетодЗаполеннияТаблицДЛяРаботыиСинхрониазции();
                Log.d(this.getClass().getName(), "  ГлавныеТаблицыСинхрониазции " + ГлавныеТаблицыСинхронизации.size());
                // TODO: 28.09.2022 запускам синхрогниазцию
                РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации(ГлавныеТаблицыСинхронизации);
                Log.w(this.getClass().getName(), " ФОНОВАЯ СИНХОРОНИЗАЦИИИ ИДЁТ... СЛУЖБА "+РезультатаСинхронизации);

                if (РезультатаСинхронизации==0){
                    РезультатаСинхронизации=ПубличныйРезультатОтветаОтСерврераУспешно;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатаСинхронизации;
        }
        // TODO: 25.09.2021 ВТОРАЯ ВЕСРИЯ ЗАПУСКА СИНХРОНМИАЗЦИИИ С ТАБЕЛЯ


// TODO: 19.08.2021  ДАННЫЙ МЕТОД ЗАПУСКАЕТ СИНХРОНИЗЦИ ДЛЯ ЧАТА

// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
















        ///TODO САМ ФОНОВЫЙ ПОТОК МЕТОД

        Integer МетодСамогоФоновойСинхронизации(@NonNull  CopyOnWriteArrayList ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат) {
            String ТекущаяТаблицаДляОБменаДанными = null;
            Integer ФинальныйРезультаФоновойСинхрониазции=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsМетодСамогоФоновойСинхронизации=new Class_GRUD_SQL_Operations(context);
            try {
                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Synchronizasiy_Data " + new Date() +
                        "\n" + " Build.BRAND " + Build.BRAND.toString()+" СколькоСтрочекJSON " );
                //////TODO ШАГ ТРЕТИЙ  ЗАПУСКАЕМ САМУ СИНХРОНИЗАЦИЮ  сама синхронизация в фоне
                ФинальныйРезультаФоновойСинхрониазции =            МетодНачалоСихронизациивФоне(context); ////Получение Версии Данных Сервера для дальнейшего анализа

                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                        ПубличныйРезультатОтветаОтСерврераУспешно +  "  ФинальныйРезультаФоновойСинхрониазции " +ФинальныйРезультаФоновойСинхрониазции);
                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                        ФинальныйРезультаФоновойСинхрониазции);
                if(ФинальныйРезультаФоновойСинхрониазции==0)
                {
                    ФинальныйРезультаФоновойСинхрониазции=   ПубличныйРезультатОтветаОтСерврераУспешно;
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ФинальныйРезультаФоновойСинхрониазции;
        }


























//////ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET

        Integer МетодНачалоСихронизациивФоне(@NotNull Context context) throws InterruptedException, ExecutionException, TimeoutException, JSONException {
            Integer результатСинхрониазции=0;
            try {
                результатСинхрониазции=     МетодПолучениеIDотСервераДляГеренированиеUUID(); ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
                Log.d(this.getClass().getName(), " результатСинхрониазции " + результатСинхрониазции);
                if(результатСинхрониазции==null){
                    результатСинхрониазции=0;
                }
                if (результатСинхрониазции==0){
                    результатСинхрониазции=      ПубличныйРезультатОтветаОтСерврераУспешно;
                }
                Log.d(this.getClass().getName(), " результатСинхрониазции" + результатСинхрониазции);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return    результатСинхрониазции;
        }













        // TODO: 13.10.2021 нАЧАЛО сИНХРОНИАЗЦИ
        Integer МетодПолучениеIDотСервераДляГеренированиеUUID() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
            String ДанныеПришёлЛиIDДЛяГенерацииUUID = new String();
            Integer РезультатСинхрониазции=0;
            ПубличноеIDПолученныйИзСервлетаДляUUID=0;
            try {
                Log.d(this.getClass().getName(), " public   void МетодПолучениеIDОтСервераДляГеренированиеUUID ()" + " ДанныеПришёлЛиIDДЛяГенерацииUUID "
                        + ДанныеПришёлЛиIDДЛяГенерацииUUID +
                        " ДанныеПришёлЛиIDДЛяГенерацииUUID.length()  " + ДанныеПришёлЛиIDДЛяГенерацииUUID.length());
                Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(context);
                PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИ
                SQLiteCursor     Курсор_ВычисляемПУбличныйID= (SQLiteCursor)  class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,public_contentменеджер.МенеджерПотоков, СсылкаНаБазуSqlite);
                Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );
                StringBuffer БуферПолучениеДанных = new StringBuffer();
                if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                    Курсор_ВычисляемПУбличныйID.moveToFirst();
                    ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);
                    Log.w(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                }
                Log.w(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                Курсор_ВычисляемПУбличныйID.close();
                // TODO: 09.09.2022  запускаем синхрониазцию
                if (ПубличноеIDПолученныйИзСервлетаДляUUID > 0) {
                    ////TODO создаем списко таблиц запускаем слуд.ющий метод получение версии базы данных
                    РезультатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID));//получаем ID для генерирования UUID
                    if (РезультатСинхрониазции == null) {
                        РезультатСинхрониазции = 0;
                    }
                    Log.d(this.getClass().getName(), " Результат  РезультатСинхрониазции  " + РезультатСинхрониазции);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатСинхрониазции;
        }

        ///////////////////метод получение ОТ СЕРВЕРА КОНКРЕТНЫЙ СПИСОК ТАДОИЦЦ ДЛЯ ОБМЕНА

























        ////////////МЕТОД ПОЛУЧЕНИЕ  ВЕРСИИ ДАННЫХ
        Integer МетодПолучениеСпискаТаблицДляОбменаДанными( String ДанныеПришёлЛиIDДЛяГенерацииUUID)
                throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ

            Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID" + ДанныеПришёлЛиIDДЛяГенерацииUUID);
            String ДанныеПришлаСпискаТаблицДляОбмена = new String();
            StringBuffer БуферПолученияСпискаТАблицДляОбмена = new StringBuffer();
            Integer РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=0;
            try {
                preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                if (preferences==null) {
                    Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных =
                            new Class_Connections_Server(context).         МетодПингаСервераРаботаетИлиНет(context);
                    preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                }
                PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                // TODO: 10.11.2022 Получение Список Таблиц
                БуферПолученияСпискаТАблицДляОбмена = УниверсальныйБуферПолучениеДанныхсСервера("view_data_modification",
                        "", "",
                        "application/gzip",//application/json
                        "Хотим Получить Версию Данных Сервера",
                        0l,
                        ДанныеПришёлЛиIDДЛяГенерацииUUID, 10000,
                        null,
                        0l, ИмяСерверИзХранилица ,ПортСерверИзХранилица);   //// БуферПолученнниеДанныхОтМетодаGET.mark(1000); // save the data we are about to readБуферПолученнниеДанныхОтМетодаGET.reset(); // jump back to the marked position
                Log.d(this.getClass().getName(), " БуферПолученияСпискаТАблицДляОбмена.toString().toCharArray().length "
                        + БуферПолученияСпискаТАблицДляОбмена.toString().toCharArray().length);
                // TODO: 03.09.2021
                if (БуферПолученияСпискаТАблицДляОбмена != null) {
                    if (БуферПолученияСпискаТАблицДляОбмена.toString().toCharArray().length > 3) {
                        Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID +
                                " БуферПолученияСпискаТАблицДляОбмена " + БуферПолученияСпискаТАблицДляОбмена.toString());
                        JSONObject ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента = new JSONObject(БуферПолученияСпискаТАблицДляОбмена.toString());///упаковываем в j
                        Log.d(this.getClass().getName(), "  ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента  " +
                                ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента);
                        JSONArray МассивJSONТаблиц = ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.names();
                        String НазваниеИзПришедшихТаблицДляКлиента;
                        String СодержимоеИзПришедшихТаблицДляКлиента;
                        String JSONСтрочка;
                        String JSONНазваниеСтолбика;
                        String JSONСодержимоеСтолика;
                        Long JSONСодержимоеСтоликаДляХэша=0l;
                        public_contentДатыДляГлавныхТаблицСинхронизации.     ДатыТаблицыВерсииДанныхОтСервера= Collections.synchronizedMap(new LinkedHashMap<String, Long>());
                        public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.clear();
                        for (int ИндексТаблицыДляДанногоКлиента = 0; ИндексТаблицыДляДанногоКлиента < ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.names().length(); ИндексТаблицыДляДанногоКлиента++) {
                            НазваниеИзПришедшихТаблицДляКлиента = МассивJSONТаблиц.getString(ИндексТаблицыДляДанногоКлиента);
                            СодержимоеИзПришедшихТаблицДляКлиента = ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.getString(НазваниеИзПришедшихТаблицДляКлиента); // Here's
                            JSONObject ОбьектJSON = new JSONObject(СодержимоеИзПришедшихТаблицДляКлиента);
                            JSONСтрочка = String.valueOf(ОбьектJSON.names());
                            /////ЦИКЛ КОТРЫЙ БЕЖИТ ПО СТОЛБЦАМ  ПРИГЕДШЕГО JSON ФАЙЛА И НАХОДИМ НАЩШИ ТАЮЛИЦЫ ДЛЯ УКАЗАННОГО ПОЛЬЗОВАТСЯ
                            for (int ИндексТаблицыДляДанногоКлиентаСтолбцы = 0; ИндексТаблицыДляДанногоКлиентаСтолбцы < ОбьектJSON.length(); ИндексТаблицыДляДанногоКлиентаСтолбцы++) {
                                JSONНазваниеСтолбика = String.valueOf(ОбьектJSON.names().get(ИндексТаблицыДляДанногоКлиентаСтолбцы));
                                JSONСодержимоеСтолика = ОбьектJSON.getString(JSONНазваниеСтолбика);
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ИМЯ ИЗ МОДИФИКАЦИИ СЕРВЕР")) {////&& !JSONСодержимоеСтолика.equalsIgnoreCase("fio")///&& !JSONСодержимоеСтолика.equalsIgnoreCase("fio")
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.add(JSONСодержимоеСтолика); //////ЗАПОЛЯНЕМ АРАЙЛИСТ НАЗВАНИЕМ ТОЛЬКО ТАБЛИЦ КОТОРЫ ПРИШИ ДЛЯ КОНКТНОГО ПОЛЬЗОВАТЕЛЯ
                                    Log.d(this.getClass().getName(), " JSONСодержимоеСтолика " + JSONСодержимоеСтолика);
                                }
                                /////А ТУТ МЫ ПРОСТО ЗАПОМИНАЕМ НАЗВАНИЕ ТАБЛИЦ С СЕРВЕРА  И ПЛЮС ИХ ДАТЫ ПОСЛЕДНЕГО ИЗМЕНЕНИЕ ДАННЫХ НА ДАННЫХ ТАБЛИЦАХ НА СЕРВЕРЕ
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ИМЯ ИЗ МОДИФИКАЦИИ СЕРВЕР")) {
                                    JSONСодержимоеСтоликаДляХэша = ОбьектJSON.getLong("ТЕКУЩАЯ ВЕРСИЯ  ТАБЛИЦЫ");/////ТОЛЬКО ДЛЯ HSMAP///"ДАТА ВЕРСИИ СЕРВЕРА"
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.put(JSONСодержимоеСтолика,JSONСодержимоеСтоликаДляХэша); ///// ЗАПОЛНЯЕМ ХЭШМАП ДЛЯ КРНКРЕТНОГО ПОЛЬЗОВАТЕЛЯ ТАБЛИЦ ДЛЯ ТОЛЬКО СЕСИИ
                                    Log.d(this.getClass().getName(), " JSONСодержимоеСтолика " + JSONСодержимоеСтолика + "  JSONСодержимоеСтоликаДляХэша  " + JSONСодержимоеСтоликаДляХэша+
                                            "   PUBLIC_CONTENT.ДатыТаблицыВерсииДанныхОтСервера.size() " + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size());
                                }
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ПРОЕКТЫ")) {
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.add(JSONСодержимоеСтолика); //////ЗАПОЛЯНЕМ АРАЙЛИСТ НАЗВАНИЕМ ТОЛЬКО ТАБЛИЦ КОТОРЫ ПРИШИ ДЛЯ КОНКТНОГО ПОЛЬЗОВАТЕЛЯ
                                    Log.d(this.getClass().getName(), " ИменаПроектовОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.toString());
                                }
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ТЕКУЩАЯ ВЕРСИЯ  ТАБЛИЦЫ")) {
                                    Log.d(this.getClass().getName(), " ИменаПроектовОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.toString()+  "  JSONНазваниеСтолбика " +JSONНазваниеСтолбика);
                                }
                            }
                        }
                    } else {////ОШИБКА В ПОЛУЧЕНИИ С СЕРВЕРА ТАБЛИУЦЫ МОДИФИКАЦИИ ДАННЫХ СЕРВЕРА
                        Log.d(this.getClass().getName(), " Данных нет c сервера  ");
                    }
                }
                Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                        + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString() +
                        " ДатыТаблицыВерсииДанныхОтСервера " +public_contentДатыДляГлавныхТаблицСинхронизации.toString() +
                        "  ДанныеПришлаСпискаТаблицДляОбмена " +ДанныеПришлаСпискаТаблицДляОбмена);
                if ( public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size() > 0
                        && public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size()>0) {//ЕСЛИ МЫ ПОЛУЧИЛИ ID  и СОЗДАЛИ НА ЕГО БАЗЕ UUID ТО ПРОХОДИИМ К СЛЕДУЮЩЕМУ КОДУ ПОЛУЧАЕМ ВЕРСИЮ ДАННЫХ СЕРВВЕРА
                    //// TODO запускам если ОТ СЕРВЕРА ПРИШЛИ  ДАННЫЕ СПИСОК ТАБЛИЦ ДЛЯ СОЗДАНИЕ СПИСК ДЛЯ ПОЛЬЗОВАТЕЯД
                    Log.i(this.getClass().getName(), " ДанныеПришлаСпискаТаблицДляОбмена " + ДанныеПришлаСпискаТаблицДляОбмена+ "  PUBLIC_CONTENT.ДатыТаблицыВерсииДанныхОтСервера.size() " +
                            public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size());
                    Log.i(this.getClass().getName(), "  ГЛАВНЫЙ ЦИКЛ НАЧИНАЕТСЯ.............. РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
                    ////TODO ТОЛЬКО НЕ ДЛЯ АКТИВТИ АНОНИМНЫЙ ОБМЕН БЕЗ ВИЗУАЛИЗАЦИИ СИНХРОНИЗАЦИИ
                    РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ= МетодГлавныхЦиклТаблицДляСинхронизации(ДанныеПришёлЛиIDДЛяГенерацииUUID);

                    if(РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ==null){
                        РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=0;
                    }
                    Log.i(this.getClass().getName(), "  ГЛАВНЫЙ ЦИКЛ ПРОШЕЛ .............. РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String ОшибкаКоторуюПропускам=e.fillInStackTrace().getMessage().toString();
                if (!ОшибкаКоторуюПропускам.equalsIgnoreCase("null")) {
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            Log.i(this.getClass().getName(), " РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
            return  РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ;
        }












        /////TODO МЕТОД ЗАПУСКА ЦИКЛА ПО ПОЛУЧЕННЫСМ ТАБЛИЦ С СЕРВЕРА ДАННЫХ ЦИКЛ FOR

        /////TODO МЕТОД ЗАПУСКА ЦИКЛА ПО ПОЛУЧЕННЫСМ ТАБЛИЦ С СЕРВЕРА ДАННЫХ ЦИКЛ FOR


// TODO: 10.09.2021  запускаем метод обработки по таблицам

        Integer МетодЗапускаСинхрониазцииПоАТблицам(String данныеПришёлЛиIDДЛяГенерацииUUID,
                                                    String текущаяТаблицаДляОБменаДанными,
                                                    CompletionService МенеджерПотоковВнутрений,
                                                    PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации) {
            Log.d(this.getClass().getName(), " ТекущаяТаблицаДляОБменаДанными " + текущаяТаблицаДляОБменаДанными);
            boolean ОтветЕслиТакаяТаблицаВнутриОбработкиДляПринятияРешениеНачинатьОбрабткуИлиНет = false;
            Integer   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
            try {
                //////TODO метод обрабтки п таюлицам
                РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=
                        МетодДляАнализаВерсийДанныхПолучаемДатыСервера(текущаяТаблицаДляОБменаДанными, данныеПришёлЛиIDДЛяГенерацииUUID,
                                МенеджерПотоковВнутрений,public_contentДатыДляГлавныхТаблицСинхронизации); ////Получение Версии Данных Сервера для дальнейшего анализа
                if(РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера==null){
                    РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
                }
                Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера "
                        + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера;
        }
























/*
        // TODO: 12.08.2021  метода повышает ВЕРСИЮ ДАННЫ Х ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ТАБЛИЦЫ


        Integer МетодПовышаемВерсиюПоДвумПолям(
                @NotNull  String текущаяТаблицаДляОБменаДанными
                , @NotNull  String  РежимПовышенияВерсииЛокальнаяСервернаяИлиОба,
                @NotNull    CompletionService МенеджерПотоковВнутрений, @NotNull Object АнализВерсияДанныхПослеСинхрониазацииДляЗаписи) {
            // TODO: 27.10.2022
            Integer    Результат_ПовышенаяВерсия=0;
            Long ВерсияДанныхПослеСинхрониазацииДляЗаписи=Long.parseLong(АнализВерсияДанныхПослеСинхрониазацииДляЗаписи.toString());
            try{
                Log.i(this.getClass().getName(), "   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ");
                // TODO: 07.02.2022  увеличиваем верисю данных
                Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнных=new Class_GRUD_SQL_Operations(context);
                    classGrudSqlOperationsПовышаемВерсиюДАнных.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",текущаяТаблицаДляОБменаДанными.trim());
                    classGrudSqlOperationsПовышаемВерсиюДАнных.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                    РежимПовышенияВерсииЛокальнаяСервернаяИлиОба.trim());///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                    classGrudSqlOperationsПовышаемВерсиюДАнных.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ДополнительныйФлагДляСинхЧАТАТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                    ВерсияДанныхПослеСинхрониазацииДляЗаписи);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ПовышенаяВерсия= (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнных.
                            new ChangesVesionData(context).
                            МетодВыравниваемДанныеВТаблицеModificationClient(classGrudSqlOperationsПовышаемВерсиюДАнных.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                if(Результат_ПовышенаяВерсия==null){
                    Результат_ПовышенаяВерсия=0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  Результат_ПовышенаяВерсия;

        }*/
        ///TODO вычисляем если такая таблиЦА ВНУТРИ БАЗЫ
        private boolean МетодВЫчисляемВсеТаблицыВнутриКлинета(String ТекущаяТаблицаДляОБменаДанными,CompletionService МенеджерПотоковВнутрений) {
            ////
            boolean ЕслиТАкаяТаблица = false;
            ///
            Class_GRUD_SQL_Operations class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета;
            ///
            SQLiteCursor КурсорВсехТаблицВнутри =null;

            try {
                ///////
                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета=new Class_GRUD_SQL_Operations(context);


                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета=new Class_GRUD_SQL_Operations(context);

                ///
                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","sqlite_master");
                ///////
                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                //
                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  type =  ?  ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","table");
                ///
        /*            class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля*/

                ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                ////
                //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                ////
                //// class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
                ////
                /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                ////



                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

                КурсорВсехТаблицВнутри= (SQLiteCursor)  class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета.
                        new GetData(context).getdata(class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);

                Log.d(this.getClass().getName(), "GetData   " +КурсорВсехТаблицВнутри );


/*

            // TODO: 06.09.2021  _old

            Cursor КурсорВсехТаблицВнутри = ССылкаНаСозданнуюБазу.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'", null);






*/

                if (КурсорВсехТаблицВнутри.getCount() > 0) {
                    ///
                    КурсорВсехТаблицВнутри.moveToFirst();
                    ////
                    Log.d(this.getClass().getName(), "  КурсорВсехТаблицВнутри." + КурсорВсехТаблицВнутри.getCount());

                    do {
                        ////
                        String ТаблицаИзБазыТекущей=КурсорВсехТаблицВнутри.getString(0);
                        ///
                        Log.d(this.getClass().getName(), "  ТаблицаИзБазыТекущей." +ТаблицаИзБазыТекущей);
                        //////
                        if (ТекущаяТаблицаДляОБменаДанными.equals(ТаблицаИзБазыТекущей)) {
                            Log.d(this.getClass().getName(), "  ТекущаяТаблицаДляОБменаДанными." + ТекущаяТаблицаДляОБменаДанными +
                                    "  КурсорВсехТаблицВнутри.getString(0)) " + КурсорВсехТаблицВнутри.getString(0));

                            ЕслиТАкаяТаблица = true;

                            break;
                        }


                        Log.d(this.getClass().getName(), "  ТекущаяТаблицаДляОБменаДанными." + ТекущаяТаблицаДляОБменаДанными +
                                "  КурсорВсехТаблицВнутри.getString(0)) " + КурсорВсехТаблицВнутри.getString(0));


                    } while (КурсорВсехТаблицВнутри.moveToNext());
                    ////////
                    КурсорВсехТаблицВнутри.close();

                } else {
                    Log.d(this.getClass().getName(), "  КурсорВсехТаблицВнутри." + КурсорВсехТаблицВнутри.getCount());
                    ЕслиТАкаяТаблица = false;
                }


                ///todo публикум название таблицы или цифру его
            } catch (Exception e) {
                //  Block of code to handle errors
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

////
            return ЕслиТАкаяТаблица;
        }






















        /////////////////////ИЩЕМ ДАТУ СЕРВЕРВА
        Integer МетодДляАнализаВерсийДанныхПолучаемДатыСервера(@NonNull  String ТекущаяТаблицаДляОБменаДанными,
                                                               @NonNull  String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                               @NonNull  CompletionService МенеджерПотоковВнутрений,
                                                               @NonNull   PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации)
                throws JSONException, InterruptedException, ExecutionException, TimeoutException {
            final Integer[] РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера = {0};
///TODO принудительн устанвливаем редим работы синхронизации
            Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID  " + ДанныеПришёлЛиIDДЛяГенерацииUUID + " ТекущаяТаблицаДляОБменаДанными "
                    + ТекущаяТаблицаДляОБменаДанными +
                    " public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера);
            try {
/////ТУТ -- КОД АНАЛИЗА ДАННЫХ SQL SERVER  ПРИШЕДШЕЙ ТЕКУЩЕЙ ТАБЛИЦЕ ПОЛУЧАЕМ НАЗВАНИЕ БАЗЫ И К НЕЙ ПОЛУЧАЕМ ДАТУ Е НЕЙ
                public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.entrySet().forEach(new Consumer<Map.Entry<String, Long>>() {
                    @Override
                    public void accept(Map.Entry<String, Long> ХэшДляАнализаТекущейТаблицыВерсииДанных) {
                        try{
                        Long Полученная_ВерсияДанныхсSqlServer = 0l;
                        JSONObject ОбьектыJSONФайлJSONсСервераВерсияSQlserver = new JSONObject();
                        String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = "";
                        String ИмитацияВремяДляПроверки;
                        Date ИмитациДатыДляПроверки = null;
                        String ТесктДатыSqlServer = null;
                        System.out.println(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey() + " - " + ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                        if (ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey().equalsIgnoreCase(ТекущаяТаблицаДляОБменаДанными)) {///ищем в текущей строчке текущуе название таблицы например CFO==CFO
                            ОбьектыJSONФайлJSONсСервераВерсияSQlserver.put(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey(), ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                            Log.d(this.getClass().getName(), " ОбьектыJSONФайлJSONсСервераВерсияSQlserver " + ОбьектыJSONФайлJSONсСервераВерсияSQlserver.toString());
                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey();
                            Log.d(this.getClass().getName(), " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных" + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                            Полученная_ВерсияДанныхсSqlServer = ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue();
                            if (Полученная_ВерсияДанныхсSqlServer == null) {
                                Полученная_ВерсияДанныхсSqlServer = 0l;
                            }
                            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer   " + Полученная_ВерсияДанныхсSqlServer);
                            Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                    "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                            /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
                            РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] =
                                    МетодДляВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером(ОбьектыJSONФайлJSONсСервераВерсияSQlserver,
                                            Полученная_ВерсияДанныхсSqlServer, ТекущаяТаблицаДляОБменаДанными,
                                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                            МенеджерПотоковВнутрений);////метод получение даты версии данных из андройда
                            //
                            Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                    "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                        }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0];
        }










        /////////////////////TODO метод ВЫРАВНИВАНИЯ ТАБЛИЦ МЕЖДУ КЛИЕНТОМ И СЕРВЕРОМ КОЛИЧЕСТВО ТАБЛИЦ ДОЛЖНО БЫТЬ ОДИНАКОВЫМ
        Integer МетодДляВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером(JSONObject ФайлJSONcВерсиейДанныхСервера, Long Полученная_ВерсияДанныхсSqlServer,
                                                                                 String ИмяТаблицыОтАндройда_Локальноая,
                                                                                 String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                                                 String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                                 CompletionService МенеджерПотоковВнутрений)
                throws InterruptedException, ExecutionException, TimeoutException {
            //////////
            Integer РезультатУспешнойВсатвкиИлиОбновлениясСервреа=null;
            Log.d(this.getClass().getName(), " ФайлJSONcВерсиейДанныхСервера " + ФайлJSONcВерсиейДанныхСервера.toString());
            JSONObject ОбьектыJSONvalue;
            JSONArray КлючJSONПолей = null;
            Cursor КурсорДляАнализаВерсииДанныхАндройда;
            String ТекстВерсииБазыАндрод = "";
            String ДатаВерсииДанныхНаАндройдеЛокальногоОбновленияДляМетодаGET = null;
            Date ДатаВерсииДанныхНаАндройдеЛокальногоОбновления = null;
            String ДатаВерсииДанныхНаАндройдеДляМетодаGET = null;
            Date ДатаВерсииДанныхНаАндройде = null;
            try {
                Class_GRUD_SQL_Operations class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером=new Class_GRUD_SQL_Operations(context);
                Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsСамаОперация= class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.new GetData(context);
                //// #1 РАСПАРСИВАЕМ ПРИШЕДШИЙ JSON С СЕРВРЕА ОТ SQL SERVER
                JSONArray КлючиJSONПолей = ФайлJSONcВерсиейДанныхСервера.names();
                // TODO: 15.02.2022 ЦИКЛ ВЫЧИСЛЕМ ВЕРСИИ ТАБЛИЦ ИХ ВЕРСИЯЯ С СЕРВЕРА С КЛИЕНТОМ
                for (int ИндексПолучениеВерсииДанныхАндройда = 0; ИндексПолучениеВерсииДанныхАндройда < ФайлJSONcВерсиейДанныхСервера.names().length(); ИндексПолучениеВерсииДанныхАндройда++) {
                    String ИмяПоляДляВставкиВАндйрод = КлючиJSONПолей.getString(ИндексПолучениеВерсииДанныхАндройда); // Here's your key
                    Log.d(this.getClass().getName(), " ИмяПоляДляВставкиВАндйрод " + ИмяПоляДляВставкиВАндйрод);
                    String СодержимоеПоляДляВставкиВАндйрод = ФайлJSONcВерсиейДанныхСервера.getString(ИмяПоляДляВставкиВАндйрод); // Here's your value
                    Log.d(this.getClass().getName(), " ЗначениеСтолбикаНазваниеТаблицНаСервере " + ИмяПоляДляВставкиВАндйрод + " ЗначениеСтолбикаВерсииТаблицНаСервере   " +
                            СодержимоеПоляДляВставкиВАндйрод);
                    class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
                    class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                    class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  name=? ");
                    class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяПоляДляВставкиВАндйрод);
                    // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                    КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsСамаОперация
                            .getdata(class_grud_sql_operationsВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                    Log.d(this.getClass().getName(), "GetData  КурсорДляАнализаВерсииДанныхАндройда  " +КурсорДляАнализаВерсииДанныхАндройда );
                    // TODO: 05.10.2021 ЗНАЧЕНИЕ КУРСОРА МИНУС 1 КОВОРИТ ОТ ТОМ ЧТО ТАБЛИЦЫ КОТОРАЯ ЕСТЬ НА СЕРВЕРА ПОЧЕМУ ТО ОТСУТСТУЕТ НА КЛИЕНТЕ И НАМ ЕЕ НАДО ДОБАВИТЬ
                    //ОЧЕНЬ ВАЖНО ЕСЛИ ЭТОТ КУРСОР ВЕРНЕТЬ ПОЛОЖИТЕЛЬНО ЦИФРУ ЭТО ЗНАЧИИТ ЧТО ТАКАЯ ТАБЛИЦУ УЖЕ ЕСТЬ НА АНДРОЙДЕ И ВСТАВЛЯЕТЬ ЕЕ НЕ НАДО
                    if (КурсорДляАнализаВерсииДанныхАндройда.getCount() < 1) {/////ЕСЛИ КУРСОР ВОЗВРЯЩАЕТ ЦИФРУ 1 ТО ТОГДА ДАННАЯ ТАБОИЦА УЖЕ ЕСТЬ В ТАБЛИЦЕ ВЕРСИЙ ДАНЫХ АНДРОЙДА
                        Log.d(this.getClass().getName(), " КурсорДляАнализаВерсииДанныхАндройда.getCount() " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                        ContentValues КонтейнерВствкаНовыхИменТаблицМодифика = new ContentValues();
                        КонтейнерВствкаНовыхИменТаблицМодифика.put("name", ИмяПоляДляВставкиВАндйрод);////ЗАПОЛЯНЕМ КОНТЕРЙНЕР ИМЯ ТАБЛИЦЫ КОТОРОЙ НЕТ ИЗ  СЕРВЕРА
                        КонтейнерВствкаНовыхИменТаблицМодифика.put("localversionandroid","1900-01-10 00:00:00");
                        КонтейнерВствкаНовыхИменТаблицМодифика.put("versionserveraandroid","1900-01-10 00:00:00");
                        КонтейнерВствкаНовыхИменТаблицМодифика.put("localversionandroid_version",0);
                        КонтейнерВствкаНовыхИменТаблицМодифика.put("versionserveraandroid_version",0);
                        // TODO: 15.02.2022  создаем не лостающую таблицу ан клиенте
                        Long РезультатВставкиНовойТаблицыКотройНетВЛокальнойБазе = ВставкаДанныхЧерезКонтейнерУниверсальная("MODIFITATION_Client",
                                КонтейнерВствкаНовыхИменТаблицМодифика,
                                ИмяТаблицыОтАндройда_Локальноая,
                                "",
                                false,
                                0,
                                false,
                                context,
                                МенеджерПотоковВнутрений,
                                СсылкаНаБазуSqlite,
                                0); ////false  не записывать изменениея в таблице модификавет версия
                        Log.d(this.getClass().getName(), " РезультатВставкиНовойТаблицыКотройНетВЛокальнойБазе " + РезультатВставкиНовойТаблицыКотройНетВЛокальнойБазе);
                    }
                    else {
                        if(КурсорДляАнализаВерсииДанныхАндройда.getCount()>0){
                            КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();
                        }
                        Log.i(this.getClass().getName(), " НазваниеТаблицНаСервере  " + ИмяПоляДляВставкиВАндйрод + " ФайлJSONcВерсиейДанныхСервера.names().length() "
                                + ФайлJSONcВерсиейДанныхСервера.names().length() + " КурсорДляАнализаВерсииДанныхАндройда.getCount()  " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                    }
                    break;
//внутри цикла
                }
                //////
                Log.w(this.getClass().getName(), " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + " Полученная_ВерсияДанныхсSqlServer "
                        + Полученная_ВерсияДанныхсSqlServer + " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных "
                        + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                //// ПОЛУЧИЛИ ДАТУ ОТ SQL SERVER  ДЛЯ ОПРЕЕЛЕННОЙ ТАБЛИЦЫ И ЗАПОЛНИЛИ ТАБЛИЦУ МОДИФИКАЦИЯ ДАНЫХ НА КЛИЕНТЕ  И ИДЕМ УЖЕ АНАЛИЗИРОВАТЬ ИХ НИЖЕ
                if (Полученная_ВерсияДанныхсSqlServer>= 0) {//TODO          if (Полученная_ВерсияДанныхсSqlServer> 0) {
                    //////
                    Log.d(this.getClass().getName(), " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + " Полученная_ВерсияДанныхсSqlServer "
                            + Полученная_ВерсияДанныхсSqlServer + " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных "
                            + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                    //////////метод анализа данных
                    РезультатУспешнойВсатвкиИлиОбновлениясСервреа=
                            МетодАнализаВресииДАнныхКлиента(ИмяТаблицыОтАндройда_Локальноая,
                                    Полученная_ВерсияДанныхсSqlServer,
                                    ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных
                                    , ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                    МенеджерПотоковВнутрений);
                    /////////
                    Log.i(this.getClass().getName(), " РезультатУспешнойВсатвкиИлиОбновлениясСервреа  " + РезультатУспешнойВсатвкиИлиОбновлениясСервреа);

                    // TODO: 15.02.2022  версия данных  НА СЕРВЕР РАВНА 0 И ОБМЕН ЕН НУЖЕН
                }else{
                    // TODO: 15.02.2022  версия данных  НА СЕРВЕР РАВНА 0 И ОБМЕН ЕН НУЖЕН
                    //////
                    Log.d(this.getClass().getName(), " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + " НА сервер  ВЕРСИЯ 0 И ОБМЕНЕ НУЖЕН Полученная_ВерсияДанныхсSqlServer "
                            + Полученная_ВерсияДанныхсSqlServer + " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных "
                            + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }
            return  РезультатУспешнойВсатвкиИлиОбновлениясСервреа;
        }










        ////////////////////////////ДАННЫЙ МЕТОД ПОСЛЕ ВЫШЕ СТОЯШЕГО ВЫРАВНИЯНИЯ НАЗВАНИЙ ТАБЛИЦ ПРИСТУПАЕТ К САМОМУ АНАЛИЗУ ДАННЫХ ВЕРСИИ ДАННЫХ НАХОДЯЩИХСЯ НА АНДРОЙДЕ
        Integer МетодАнализаВресииДАнныхКлиента(String ИмяТаблицыОтАндройда_Локальноая,
                                                Long Полученная_ВерсияДанныхсSqlServer,
                                                String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                CompletionService МенеджерПотоковВнутрений) {

            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
            SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = null;
            Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная = 0l;
            Long ВерсииДанныхНаАндройдеСерверная = 0l;
            Integer РезультатУспешнойВсатвкиИлиОбвовлениясСервера=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаВресииДАнныхКлиента;
            try {
                class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);
                Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","name=? ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsgetdata
                        .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,
                                СсылкаНаБазуSqlite);
                Log.d(this.getClass().getName(), "GetData "+КурсорДляАнализаВерсииДанныхАндройда  );
                /////
                if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {////ВЫЖНОЕ УСЛОВИЕ ЕСЛИ КУРСОР ВЕРНУЛ БОЛЬШЕ НУЛЯ  ДАННАЕ ТОЛЬКО ТОГДА НАЧИНАЕМ АНАЛИЗ ВЕРСИИ ДАННЫХ НА АНДРОЙДЕ
                    КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();
                    Log.d(this.getClass().getName(), "  Курсор_УзнаемВерсиюБазыНаАдройде.getCount() " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                    // TODO: 05.10.2021  получаем верию данных лолькано    --- локльную
                    ВерсииДанныхНаАндройдеЛокальнаяЛокальная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));
                    Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная+" ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 05.10.2021  получаем верию данных лолькано  - ерверную
                    ВерсииДанныхНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));
                    Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+"  ИмяТаблицыОтАндройда_Локальноая  "+ИмяТаблицыОтАндройда_Локальноая);
                    ///////////ОПРЕДЕЛЯЕМ ДАТУ АНДРОЙДА ДЛЯ СОСТЫКОВКИ С ДАТОЙ SQ; SERVER//// ПОЛУЧАЕМ ДАТУ НА АНДРОЙДЕ ПОЛСЕДНЕЕ ИЗМЕНЕНИЯ ПРИШЕДЩИЕ ДАННЫЕ С СЕРВЕРА
                } else {
                    Log.d(this.getClass().getName(), "  НЕт такой таблицы и нет Данных КурсорДляАнализаВерсииДанныхАндройда.getCount()" + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                }
                // TODO: 05.10.2021  КОГДА ВСЕ ДАННЫЕ ЕСТЬ ТРИ ПЕРЕМЕННЫЕ ПОЛУЧЕНИЕ ПЕРЕХОИМ ДАЛЬШЕ ПОЛЯ ЛОКАЛЬНАЯ ВЕРСИЯ ДАННЫХ, СЕРВЕНАЯ ВЕРСИЯ ДАННЫХ, И ТЕРТЬЯ ВЕРИСЯ С СЕРВЕРА ПО ДАННОЙ ТАБЕЛИЦВ
                Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+
                        "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                        +"   Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
                // TODO: 05.10.2021 ПРИ НАЛИЧИИ ВСЕХ ТРЕХ ПОЗИЦИЙ ЛОКАЛЬНАЯ ВЕРСИЯ С АНДРОЙДА   И СЕРВРНАЯ ВЕРСИЯ С АНДРОЙДА И  ПРИШЕДШЕЯ ВЕРСИЯ С СЕРВЕРА
                ///
                if (ВерсииДанныхНаАндройдеЛокальнаяЛокальная !=null  && ВерсииДанныхНаАндройдеСерверная!=null && Полученная_ВерсияДанныхсSqlServer!=null) {
                    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       МетодПринятияРешенияПолучитьДанныесСервераИлиОтправитьДанныесКлиента(
                            ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                            ВерсииДанныхНаАндройдеСерверная,
                            Полученная_ВерсияДанныхсSqlServer,
                            ИмяТаблицыОтАндройда_Локальноая,
                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                            МенеджерПотоковВнутрений);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    Log.d(this.getClass().getName(), "   РезультатУспешнойВсатвкиИлиОбвовлениясСервера " +РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                }else{

                    // TODO: 15.02.2022  НЕТ ДАННЫ Х ДЛЯ ОДМЕНА ПО ТАБЛИЦЫЕ ТЕКУЩЕЙ

                    new Handler(     context.getMainLooper()).post(()->{

                        Toast.makeText(context, "Нет данных для обмена текущие таблицы:  "+ИмяТаблицыОтАндройда_Локальноая , Toast.LENGTH_LONG).show();


                    });

                    Log.e(this.getClass().getName(), "   Нет данных для обмена текущие таблицы " +ИмяТаблицыОтАндройда_Локальноая);


                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }
            return  РезультатУспешнойВсатвкиИлиОбвовлениясСервера;
        }














        //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
        Integer МетодПринятияРешенияПолучитьДанныесСервераИлиОтправитьДанныесКлиента(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                                     Long ВерсииДанныхНаАндройдеСерверная,
                                                                                     Long Полученная_ВерсияДанныхсSqlServer,
                                                                                     String ИмяТаблицыОтАндройда_Локальноая,
                                                                                     String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                                                     String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                                     CompletionService МенеджерПотоковВнутрений) {
            Integer Результат_ПосылаемНа_Сервер=0;//РезультатУспешнойВставкиИлИОбвновленияССервера
            Integer Результат_СсервераПолучаем_Сервер=0;//РезультатУспешнойВставкиИлИОбвновленияССервера
            try {
                if (ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных.equalsIgnoreCase(ИмяТаблицыОтАндройда_Локальноая)) {//////ОБЯЗАТОЛЬНОЕ УСЛОВИЕ НАЗВАНИЕ ТАБЛИЦ ДОЛЖНО БЫТЬ ОДИНАКОВЫМ НАПРИМЕР  CFO==CFO
                    Log.d(this.getClass().getName(), " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА"
                            + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА
                            + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 12.08.2021 СЕРВЕРАНАЯ ДАТА ЛОКАЛЬНАЯ
                    Long    ВерсияДанныхПришлаПослеУспешнойСинхронизации =
                            МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "versionserveraandroid_version",
                                    context, ИмяТаблицыОтАндройда_Локальноая);
                    ВерсияДанныхПришлаПослеУспешнойСинхронизации=      Optional.ofNullable(ВерсияДанныхПришлаПослеУспешнойСинхронизации).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(),
                            " ВерсияДанныхПришлаПослеУспешнойСинхронизации" + ВерсияДанныхПришлаПослеУспешнойСинхронизации);
                    // TODO: 12.08.2021 ЛОКАЛЬНАЯ ДАТА ЛОКАЛЬНАЯ
                    Long          ВерсияДанныхСозданноеНаAndroid =
                            МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "localversionandroid_version",
                                    context, ИмяТаблицыОтАндройда_Локальноая);
                    ВерсияДанныхСозданноеНаAndroid=      Optional.ofNullable(ВерсияДанныхСозданноеНаAndroid).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(), " РезультаПолученаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее"
                                    + ВерсияДанныхСозданноеНаAndroid+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 26.10.2022 устанвливаем версию данных на самом sqlserver Сейчас котрорая
                    ВерсияДанныхРеальнаяНаСейчасНаSqlServer = Полученная_ВерсияДанныхсSqlServer;
                    ВерсияДанныхРеальнаяНаСейчасНаSqlServer=      Optional.ofNullable(ВерсияДанныхРеальнаяНаСейчасНаSqlServer).map(Long::new).orElse(0l);
                    // TODO: 05.10.2021  POST()-->
                    if (ВерсияДанныхСозданноеНаAndroid > ВерсияДанныхПришлаПослеУспешнойСинхронизации
                            && !ИмяТаблицыОтАндройда_Локальноая.matches("(.*)view(.*)")) {
                        Log.d(this.getClass().getName(),
                                " ВерсияДанныхСозданноеНаAndroid  " + ВерсияДанныхСозданноеНаAndroid +
                                        "  ВерсияДанныхПришлаПослеУспешнойСинхронизации " + ВерсияДанныхПришлаПослеУспешнойСинхронизации
                                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая "
                                        +ИмяТаблицыОтАндройда_Локальноая);
                        // TODO: 30.06.2022  конец встаялеммого кода с задержкой
                        Результат_ПосылаемНа_Сервер = МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(ИмяТаблицыОтАндройда_Локальноая,
                                МенеджерПотоковВнутрений, ВерсияДанныхПришлаПослеУспешнойСинхронизации);
                        Log.d(this.getClass().getName(),
                                " ВерсияДанныхСозданноеНаAndroid  "
                                        + ВерсияДанныхСозданноеНаAndroid +
                                        "  ВерсияДанныхПришлаПослеУспешнойСинхронизации "
                                        + ВерсияДанныхПришлаПослеУспешнойСинхронизации
                                        + " Результат_ПосылаемНа_Сервер " + Результат_ПосылаемНа_Сервер);
                        if(Результат_ПосылаемНа_Сервер>0 ){
                            ПубличныйРезультатОтветаОтСерврераУспешно=    Результат_ПосылаемНа_Сервер ;
                            // TODO: 19.11.2022  версия данных синхронизируемс таблицей modificatin client
                         Integer РезультатПовышенииВерсииДанных =new Class_GRUD_SQL_Operations(context) .new ClassRuntimeExeGRUDOpertions(context)
                                      .МетодУвеличиваемДанныхБазы(ИмяТаблицыОтАндройда_Локальноая,
                                              "Серверный",new PUBLIC_CONTENT(context).МенеджерПотоков,"Анализ");
                            Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                        }
                    } else {
                        // TODO: 19.10.2021   GET()->
                        if (ВерсияДанныхРеальнаяНаСейчасНаSqlServer > ВерсияДанныхПришлаПослеУспешнойСинхронизации) {
                            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                            Результат_СсервераПолучаем_Сервер = МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                    ИмяТаблицыОтАндройда_Локальноая, ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                    МенеджерПотоковВнутрений, Результат_СсервераПолучаем_Сервер,
                                    ВерсияДанныхПришлаПослеУспешнойСинхронизации );
                            // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                            if(Результат_СсервераПолучаем_Сервер>0 ){
                                ПубличныйРезультатОтветаОтСерврераУспешно=Результат_СсервераПолучаем_Сервер;
                                // TODO: 19.11.2022  версия данных синхронизируемс таблицей modificatin client
                                Integer РезультатПовышенииВерсииДанных =new Class_GRUD_SQL_Operations(context) .new ClassRuntimeExeGRUDOpertions(context)
                                        .МетодУвеличиваемДанныхБазы(ИмяТаблицыОтАндройда_Локальноая,
                                                "Серверный",new PUBLIC_CONTENT(context).МенеджерПотоков,"Анализ");
                                Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                            }
                            Log.d(this.getClass().getName(),
                                    " РезультатВерсииДанныхЧатаНаСервере  " + ВерсияДанныхРеальнаяНаСейчасНаSqlServer +
                                            "  ВерсияДанныхПришлаПослеУспешнойСинхронизации "
                                            + ВерсияДанныхПришлаПослеУспешнойСинхронизации
                                            + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                            "  Результат_СсервераПолучаем_Сервер " +Результат_СсервераПолучаем_Сервер );
                        }
                    }
                }
                Log.i(this.getClass().getName(), "   Результат_ПосылаемНа_Сервер"
                        + Результат_ПосылаемНа_Сервер + "  ПубличныйРезультатОтветаОтСерврераУспешно " +ПубличныйРезультатОтветаОтСерврераУспешно+
                        "  ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая+
                        " Результат_СсервераПолучаем_Сервер " +Результат_СсервераПолучаем_Сервер+
                        "  ПубличныйРезультатОтветаОтСерврераУспешно " +ПубличныйРезультатОтветаОтСерврераУспешно);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ПубличныйРезультатОтветаОтСерврераУспешно;
        }



        private Integer МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(String ИмяТаблицыОтАндройда_Локальноая,
                                                                       CompletionService МенеджерПотоковВнутрений
                , Long ВерсияДанныхПришлаПослеУспешнойСинхронизации) {
            Integer РезультатОтправкиДанныхНаСервер=0;
            try{
                Log.d(this.getClass().getName(), "  ВерсияДанныхПришлаПослеУспешнойСинхронизации   "
                                + ВерсияДанныхПришлаПослеУспешнойСинхронизации + "ВерсияДанныхРеальнаяНаСейчасНаSqlServer "+ВерсияДанныхРеальнаяНаСейчасНаSqlServer);
                // TODO: 04.11.202
                ////// todo МЕТОД POST
  /*              Результат_ПосылаемНа_Сервер =
                        МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                МенеджерПотоковВнутрений);*/
                ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
                РезультатОтправкиДанныхНаСервер =
                        МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, ВерсияДанныхПришлаПослеУспешнойСинхронизации,
                                МенеджерПотоковВнутрений);
                ////// todo МЕТОД POST() в фоне
                Log.i(this.getClass().getName(), "   РезультатОтправкиДанныхНаСервер" + РезультатОтправкиДанныхНаСервер+
                        " ВерсияДанныхПришлаПослеУспешнойСинхронизации "+ВерсияДанныхПришлаПослеУспешнойСинхронизации);
             /*   if (РезультатОтправкиДанныхНаСервер > 0 ) {
                    // TODO: 18.11.2022  После Синхрониащзции ПОДНИМАМ ВЕРИСЮ ДАННЫХ POST()
                    Integer РезультатПовышенияВерсииДанныхДатыиВерсии = МетодПовышаемВерсиюПоДвумПолям( ИмяТаблицыОтАндройда_Локальноая,
                            "ЛокальныйСерверныйОба", МенеджерПотоковВнутрений,РезультатОтправкиДанныхНаСервер);
                    Log.i(this.getClass().getName(), "   РезультатПовышенияВерсииДанныхДатыиВерсии" + РезультатПовышенияВерсииДанныхДатыиВерсии+
                            " РезультатОтправкиДанныхНаСервер "+РезультатОтправкиДанныхНаСервер);
                }*/
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОтправкиДанныхНаСервер;
        }

        @NonNull
        private Integer МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                         String ИмяТаблицыОтАндройда_Локальноая,
                                                                         String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                         CompletionService МенеджерПотоковВнутрений,
                                                                         Integer Результат_СсервераПолучаем_Сервер,
                                                                         Long РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера) {
            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
            try{
                Log.d(this.getClass().getName(), " НА SQL SERVER  ДАТА больше версия" +
                        "  ЛОКАЛЬНАЯ ВЕРСИЯ (последнего серверного обновления) ЧАТ  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                        " и  ТЕКУЩАЯ СЕРВЕРНАЯ ВЕРСИЯ  ЧАТ РезультатВерсииДанныхЧатаНаСервере " + ВерсияДанныхРеальнаяНаСейчасНаSqlServer + ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 19.08.2021 уменьшаемм для повторгого повторной отправки
                //////////TODO МЕТОД get
                Результат_СсервераПолучаем_Сервер =
                        МетодПолучаемДаннныесСервера(ИмяТаблицыОтАндройда_Локальноая,
                                РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                МенеджерПотоковВнутрений);/// ЗАПУСКАМ МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА    МЕТОД GET


                Log.d(this.getClass().getName(), " ПОСЛЕ УСПЕШНОЙ ОТПАРВКИ ДАННЫХ НА СЕРВЕР" +
                        " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                        "  РезультатВерсииДанныхЧатаНаСервере" + ВерсияДанныхРеальнаяНаСейчасНаSqlServer
                        + "  ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + "\n" +
                        "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                        + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера+
                        " ВерсииДанныхНаАндройдеЛокальнаяЛокальная " +
                        ВерсииДанныхНаАндройдеЛокальнаяЛокальная);
                /////В ДАНОМ СЛУЧАЕ ДАННЫЕ СИНХРОНИЗИРОВАТЬ НЕ НАДО ВЕСРИЯ ДАННЫХ НА СЕРВРЕР И НА КЛИЕНТЕ ОДИНАКОВЫ
                // TODO: 17.11.2021
         /*       ////TODO КОГДА ДАТЫ РАВНЫ И НЕ ПОЛУЧАТЬ ДАННЫЕ И ОТСЫЛАТЬ НЕ НАДО GET() И POST() ОБА НЕ СРАБОТАЛИMODIFITATION_Client//
                if (Результат_СсервераПолучаем_Сервер > 0 ) {
                    Log.d(this.getClass().getName(), "РезультатВерсииДанныхЧатаНаСервере  " + ВерсияДанныхРеальнаяНаСейчасНаSqlServer);
                    Log.d(this.getClass().getName(), "КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал  " + КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);
                    // TODO: 12.08.2021 код повышает или уменьшает верисю данных
                           // TODO: 18.11.2022  После Синхрониащзции ПОДНИМАМ ВЕРИСЮ ДАННЫХ POST()
                           Integer РезультатПовышенияВерсииДанныхДатыиВерсии = МетодПовышаемВерсиюПоДвумПолям( ИмяТаблицыОтАндройда_Локальноая,
                                   "ЛокальныйСерверныйОба", МенеджерПотоковВнутрений,ВерсияДанныхРеальнаяНаСейчасНаSqlServer);
                    Log.i(this.getClass().getName(), "   ИмяТаблицыОтАндройда_Локальноая"
                            + ИмяТаблицыОтАндройда_Локальноая + " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                            "  РезультатПовышенияВерсииДанныхДатыиВерсии " + РезультатПовышенияВерсииДанныхДатыиВерсии);
                }*/
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            return Результат_СсервераПолучаем_Сервер;
        }
        // TODO: 19.08.2021 Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
        class ClassCalculateInFieldIDNULLMeanDataValueNotyetsent  {
            ////

            private     String  ТекущаяТаблицаГдеЕстьвIdПолеNULL;

            public ClassCalculateInFieldIDNULLMeanDataValueNotyetsent(Context context,String  ТекущаяТаблицаГдеЕстьвIdПолеNULL) {


                Log.d(this.getClass().getName(), "ТекущаяТаблицаГдеЕстьвIdПолеNULL "
                        +ТекущаяТаблицаГдеЕстьвIdПолеNULL);
            }

            public String getТекущаяТаблицаГдеЕстьвIdПолеNULL() {
                return ТекущаяТаблицаГдеЕстьвIdПолеNULL;
            }

            public void setТекущаяТаблицаГдеЕстьвIdПолеNULL(String текущаяТаблицаГдеЕстьвIdПолеNULL) {
                ТекущаяТаблицаГдеЕстьвIdПолеNULL = текущаяТаблицаГдеЕстьвIdПолеNULL;
            }

            // TODO: 19.08.2021 МЕТОД ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
            private Long МетодВычисляемЕщенеОтправленныеСообщенияНаСервер(CompletionService МенеджерПотоковВнутрений) {
                Long ЕслиВПолеIdЗначениеNUll=0l;
                SQLiteCursor Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID=null;
                Class_GRUD_SQL_Operations class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер;
                try{
                    Log.d(this.getClass().getName(), "ТекущаяТаблицаГдеЕстьвIdПолеNULL "
                            +ТекущаяТаблицаГдеЕстьвIdПолеNULL);
                    class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер=new Class_GRUD_SQL_Operations(context);
                    switch (ТекущаяТаблицаГдеЕстьвIdПолеNULL.trim()){
                        case "tabels":
                        case "chats":
                        case "data_chat":
                        case "chat_users":
                        case "fio":
                        case "tabel":
                        case "data_tabels":
                            class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТекущаяТаблицаГдеЕстьвIdПолеNULL);
                            class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","uuid");
                            class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  _id  IS  NULL    ");
                            Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID= (SQLiteCursor)  class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер.
                                    new GetData(context).getdata(class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                            Log.d(this.getClass().getName(), "GetData "+Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID  );
                            if(Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.getCount()>0){
                                Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.moveToFirst();
                                ЕслиВПолеIdЗначениеNUll = Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.getLong(0);
                                Log.d(this.getClass().getName(), "  СЛУЖБА ДА ДА ДА Сработала !!!!  в таблице ЧАТА chats and data_chat   " +
                                        "есть NULL (не отправленные сообщения на сервер ) ФиоКтоНАписалСообщение  " + ЕслиВПолеIdЗначениеNUll  + "\n"+
                                        "  ТекущаяТаблицаГдеЕстьвIdПолеNULL " +ТекущаяТаблицаГдеЕстьвIdПолеNULL);
                            }
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                return  ЕслиВПолеIdЗначениеNUll;
            }

        }





        // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
        // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА

        /////МЕТОД КОГДА НА СЕРВЕРЕ ВЕРСИЯ ДАННЫХ ВЫШЕ И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВРА
        Integer МетодПолучаемДаннныесСервера(String имяТаблицыОтАндройда_локальноая,
                                             Long ВерсииДанныхНаАндройдеСерверная,
                                             String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                             Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                ,Long  РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                                             CompletionService МенеджерПотоковВнутрений ) {

            Integer РезультатФоновнойСинхронизации=0;
            StringBuffer БуферПолученныйJSON = null;
            try {
                Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  имяТаблицыОтАндройда_локальноая" + имяТаблицыОтАндройда_локальноая);
                StringBuffer БуферПолучениеДанных = new StringBuffer();
                try {
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                            ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                            Проценты,"ПроцессеAsyncBackground",false,false);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 10.11.2022  Получение JSON-потока
                    БуферПолучениеДанных = УниверсальныйБуферПолучениеДанныхсСервера(имяТаблицыОтАндройда_локальноая, "",
                            "", "application/gzip", "Хотим Получить  JSON"
                            ,ВерсииДанныхНаАндройдеСерверная,//    ВерсииДанныхНаАндройдеСерверная,//37262l
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,2000000,null,
                            РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер, ИмяСерверИзХранилица ,ПортСерверИзХранилица);//TODO "http://192.168.254.40:8080/"      /      // TODO     "http://tabel.dsu1.ru:8888/"   original     "tabel.dsu1.ru", 8888);
                    Log.d(this.getClass().getName(), "  БУФЕР получаем даннные БуферПолучениеДанных.toString() " + БуферПолучениеДанных.toString());
                    if(БуферПолучениеДанных==null){
                        БуферПолучениеДанных = new StringBuffer();
                    }
                    Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  БуферПолучениеДанных" + БуферПолучениеДанных.toString()+"\n"
                            + "  БуферПолучениеДанных.length()" + БуферПолучениеДанных.length());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                if ( БуферПолучениеДанных.toString().toCharArray().length > 3) {
                    БуферПолученныйJSON = new StringBuffer();
                    Log.d(this.getClass().getName(), "  БуферПолучениеДанных.toString()) " + БуферПолучениеДанных.toString());

                    БуферПолученныйJSON.append(БуферПолучениеДанных.toString());
                    ////////Присылаем количестов строчек обработанных на сервлете
                    Log.d(this.getClass().getName(), " БуферПолученныйJSON.length()  " + БуферПолученныйJSON.length());
                    int Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы = 0;
                    Log.i(this.getClass().getName(), "   Результат_ПриписиИзменнийВерсииДанныхВФоне:"
                            + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы + " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    //////TODO запускаем метод распарстивая JSON
                    РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(БуферПолученныйJSON, имяТаблицыОтАндройда_локальноая);
                    Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации  "  +РезультатФоновнойСинхронизации);
                } else {////ОШИБКА В ПОЛУЧЕНИИ С СЕРВЕРА ТАБЛИУЦЫ МОДИФИКАЦИИ ДАННЫХ СЕРВЕРА
                    Log.d(this.getClass().getName(), " Данных нет c сервера сам файл JSON   пришел от сервера БуферПолучениеДанных   "+БуферПолучениеДанных);
                }
                Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации "+РезультатФоновнойСинхронизации);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }
            return РезультатФоновнойСинхронизации;
        }
        /////// TODO МЕТОД ПАСРИНГА ПРИШЕДШЕГО  С СЕРВЕРА ВНУТРИ ASYNSTASK В ФОНЕ
        Integer МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  StringBuffer БуферПолученныйJSON,
                                                   @NonNull  String имяТаблицыОтАндройда_локальноая) throws InterruptedException, JSONException {
            try {
                Log.d(this.getClass().getName(), " БуферПолученныйJSON " + БуферПолученныйJSON.toString());
              //  JsonObject     ПришелJsonОтСервера = new PUBLIC_CONTENT(context).gson.fromJson(БуферПолученныйJSON.toString(), JsonObject.class);
                JsonArray     ПришелJsonОтСервера = new PUBLIC_CONTENT(context).gson.fromJson(БуферПолученныйJSON.toString(), JsonArray.class);
                        МаксималноеКоличествоСтрочекJSON = ПришелJsonОтСервера.size();
                Log.d(this.getClass().getName(), " МаксималноеКоличествоСтрочекJSON:::  "
                        + МаксималноеКоличествоСтрочекJSON+ " ПришелJsonОтСервера " +ПришелJsonОтСервера);
                // TODO: 11.10.2022 callback метод обратно в актвити #1
                МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON, ИндексВизуальнойДляPrograssBar, имяТаблицыОтАндройда_локальноая,
                        Проценты, "ПроцессеAsyncBackground",false,false);
                ИндексВизуальнойДляPrograssBar=0;
                // TODO: 04.12.2022 ПАРСИНГ ПО НОВОМУ ОТ JAKSON

            Flowable.fromIterable(ПришелJsonОтСервера)
                        .onBackpressureBuffer(true)
                        .buffer(500)
                                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).doOnNext(new io.reactivex.rxjava3.functions.Consumer<List<JsonElement>>() {
                        @Override
                        public void accept(List<JsonElement> jsonElementsИзБуфера) throws Throwable {
                            // TODO: 13.01.2023  ОБРАБОТКА ИЗ БУФЕРА
                            АдаптерДляВставкиИОбновления = new ArrayList<>();//JSON_ПерваяЧасть.names().length()
                            Flowable.fromIterable(jsonElementsИзБуфера)
                            .onBackpressureBuffer(true)
                           .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Throwable {
                                   Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                           Thread.currentThread().getStackTrace()[2].getMethodName() +
                                           " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                   new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                           this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                           Thread.currentThread().getStackTrace()[2].getLineNumber());
                               }
                           })
                                    .onErrorComplete(new Predicate<Throwable>() {
                                        @Override
                                        public boolean test(Throwable throwable) throws Throwable {
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            return false;
                                        }
                                    })
                                    .doOnComplete(new Action() {
                                        @Override
                                        public void run() throws Throwable {
                                            // TODO: 14.11.2022  Заполем  Обновление ЧЕРЕЗ КОНТЕКТ ПРОВАЙДЕР
                                            // TODO: 04.12.2022 end buffer  вставка или обновленИЕ СРАЗУ 500 СТРОК
                                            Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                                    + ТекущийАдаптерДляВсего.size());
                                            // TODO: 09.11.2022 ПОСЛЕ ОБРАБОТКИ НАЧИНАЕМ ВСТАКУ ДАННЫХ ЧЕРЕЗ BULK INSERT
                                            МетодBulkUPDATE(имяТаблицыОтАндройда_локальноая, context);
                                            Log.d(this.getClass().getName(), " Конец  ПАРСИНГА ОБРАБОАТЫВАЕМОМЙ ТАБЛИЦЫ МетодBulkUPDATE   ::::: "
                                                    + имяТаблицыОтАндройда_локальноая+" АдаптерДляВставкиИОбновления.size() " +АдаптерДляВставкиИОбновления.size());
                                        }
                                    })
                            .forEach(new io.reactivex.rxjava3.functions.Consumer<JsonElement>() {
                                @Override
                                public void accept(JsonElement jsonElement) throws Throwable {
                                    try {
                                        ТекущийАдаптерДляВсего = new ContentValues();
                                        // TODO: 06.10.2022  ВНУТрений СТрочка обработки данных сами Столбикки
                                        JsonObject jsonObjectСамаСтрочка = jsonElement.getAsJsonObject();
                                        Log.d(this.getClass().getName(),  " jsonObjectСамаСтрочка  "  +jsonObjectСамаСтрочка   +new Date().toGMTString().toString());
                                        // TODO: 14.11.2022  ОБРАБОТКА ВТОРОЙ ДАНЫХ САМИ СТОЛБИКИ
                                        Flowable.fromIterable(jsonObjectСамаСтрочка.entrySet())
                                                .onBackpressureBuffer( true)
                                                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                                    @Override
                                                    public void accept(Throwable throwable) throws Throwable {
                                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    }
                                                })
                                                .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Map.Entry<String, JsonElement>>() {
                                                    @Override
                                                    public void accept(Map.Entry<String, JsonElement> stringJsonElementEntryВнутриJSONСтрочки) throws Throwable {
                                                        String ПолеОтJSONKEY = stringJsonElementEntryВнутриJSONСтрочки.getKey().toString().trim();
                                                        switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                                                            case "tabels":
                                                            case "chats":
                                                            case "data_chat":
                                                            case "chat_users":
                                                            case "fio":
                                                            case "tabel":
                                                            case "cfo":
                                                            case "data_tabels":
                                                            case "nomen_vesov":
                                                            case "type_materials":
                                                            case "company":
                                                            case "track":
                                                                System.out.println("  ПолеОтJSONKEY  " + ПолеОтJSONKEY);
                                                                if (stringJsonElementEntryВнутриJSONСтрочки.getKey().contentEquals("id") == true) {
                                                                    ПолеОтJSONKEY = "_id";
                                                                }
                                                                break;
                                                        }
                                                        // TODO: 27.10.2022 Дополнительна Обработка
                                                        String ПолеЗначениеJson = stringJsonElementEntryВнутриJSONСтрочки.getValue().toString()
                                                                .replace("\"", "").replace("\\n", "")
                                                                .replace("\\r", "").replace("\\", "")
                                                                .replace("\\t", "").trim();//todo .replaceAll("[^A-Za-zА-Яа-я0-9]", "")
                                                        if (ПолеОтJSONKEY.equalsIgnoreCase("status_carried_out") ||
                                                                ПолеОтJSONKEY.equalsIgnoreCase("closed") ||
                                                                ПолеОтJSONKEY.equalsIgnoreCase("locked")) {
                                                            if (ПолеЗначениеJson.equalsIgnoreCase("false") ||
                                                                    ПолеЗначениеJson.equalsIgnoreCase("0")) {
                                                                ПолеЗначениеJson = "False";
                                                            }
                                                            if (ПолеЗначениеJson.equalsIgnoreCase("true") ||
                                                                    ПолеЗначениеJson.equalsIgnoreCase("1")) {
                                                                ПолеЗначениеJson = "True";
                                                            }
                                                        }
                                                        Log.d(this.getClass().getName(), " ПолеОтJSONKEY " + ПолеОтJSONKEY + " ПолеЗначениеJson" + ПолеЗначениеJson);
                                                        // TODO: 27.10.2022  UUID есть Обновление
                                                        ТекущийАдаптерДляВсего.put(ПолеОтJSONKEY, ПолеЗначениеJson);//
                                                    }
                                                })
                                                .onErrorComplete(new Predicate<Throwable>() {
                                                    @Override
                                                    public boolean test(Throwable throwable) throws Throwable {
                                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        return false;
                                                    }
                                                })
                                                .doOnComplete(new Action() {
                                                    @Override
                                                    public void run() throws Throwable {
                                                        // TODO: 14.11.2022  Заполем  Обновление ЧЕРЕЗ КОНТЕКТ ПРОВАЙДЕР
                                                        МетодContentProvoderForUpdateJrInsert(context,  имяТаблицыОтАндройда_локальноая);
                                                        Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                                                + ТекущийАдаптерДляВсего.size());
                                                    }
                                                }).blockingSubscribe();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }

                                }
                            }).dispose();
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 11.10.2022 ПОСЛЕ ОПЕРАЦИИ ВИЗАУЛИЗИРУЕМ КОНЕЦ ОПЕРАЦИИ ПОЛЬЗОВАТЕЛЮ
                            МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                                    Проценты,"ПроцессеAsyncBackground",false,false);
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .blockingSubscribe();





/*                Flowable.fromIterable(ПришелJsonОтСервера.entrySet())
                        .onBackpressureBuffer( true)
                        .buffer(500)
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .doOnNext(new io.reactivex.rxjava3.functions.Consumer<List<Map.Entry<String, JsonElement>>>() {
                            @Override
                            public void accept(List<Map.Entry<String, JsonElement>> entriesbuffer) throws Throwable {
                                Log.d(this.getClass().getName(),  " date "+ "jsonObjects "  +new Date().toGMTString().toString());
                                try{
                                    // TODO: 12.12.2022
                                    // TODO: 08.12.2022 ДЛЯ БУФЕРА
                                    АдаптерДляВставкиИОбновления = new ArrayList<>();//JSON_ПерваяЧасть.names().length()
                                    Flowable.fromIterable(entriesbuffer)
                                            .onBackpressureBuffer( true)
                                            .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                                @Override
                                                public void accept(Throwable throwable) throws Throwable {
                                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                            }).doOnNext(new io.reactivex.rxjava3.functions.Consumer<Map.Entry<String, JsonElement>>() {
                                                @Override
                                                public void accept(Map.Entry<String, JsonElement> stringJsonElementEntry) throws Throwable {
                                                    Log.d(this.getClass().getName(), " БуферПолученныйJSON " + stringJsonElementEntry.getKey() +
                                                            "stringJsonElementEntry" + stringJsonElementEntry.getValue());
                                                    try {
                                                        ///TODO  ДЛЯ СТРОКИ
                                                        ТекущийАдаптерДляВсего = new ContentValues();
                                                        // TODO: 06.10.2022  ВНУТрений СТрочка обработки данных сами Столбикки
                                                        JsonObject jsonObjectСамаСтрочка = stringJsonElementEntry.getValue().getAsJsonObject();
                                                        Log.d(this.getClass().getName(), " POOL THREAD ROWS JSON " + Thread.currentThread().getName());
                                                        // TODO: 14.11.2022  ОБРАБОТКА ВТОРОЙ ДАНЫХ САМИ СТОЛБИКИ
                                                        Flowable.fromIterable(jsonObjectСамаСтрочка.entrySet())
                                                                .onBackpressureBuffer( true)
                                                                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                                                            @Override
                                                                            public void accept(Throwable throwable) throws Throwable {
                                                                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                                                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                            }
                                                                        })
                                                                                .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Map.Entry<String, JsonElement>>() {
                                                                                    @Override
                                                                                    public void accept(Map.Entry<String, JsonElement> stringJsonElementEntryВнутриJSONСтрочки) throws Throwable {
                                                                                        String ПолеОтJSONKEY = stringJsonElementEntryВнутриJSONСтрочки.getKey().toString().trim();
                                                                                        switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                                                                                            case "tabels":
                                                                                            case "chats":
                                                                                            case "data_chat":
                                                                                            case "chat_users":
                                                                                            case "fio":
                                                                                            case "tabel":
                                                                                            case "cfo":
                                                                                            case "data_tabels":
                                                                                            case "nomen_vesov":
                                                                                            case "type_materials":
                                                                                            case "company":
                                                                                            case "track":
                                                                                                System.out.println("  ПолеОтJSONKEY  " + ПолеОтJSONKEY);
                                                                                                if (stringJsonElementEntryВнутриJSONСтрочки.getKey().contentEquals("id") == true) {
                                                                                                    ПолеОтJSONKEY = "_id";
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                        // TODO: 27.10.2022 Дополнительна Обработка
                                                                                        String ПолеЗначениеJson = stringJsonElementEntryВнутриJSONСтрочки.getValue().toString()
                                                                                                .replace("\"", "").replace("\\n", "")
                                                                                                .replace("\\r", "").replace("\\", "")
                                                                                                .replace("\\t", "").trim();//todo .replaceAll("[^A-Za-zА-Яа-я0-9]", "")
                                                                                        if (ПолеОтJSONKEY.equalsIgnoreCase("status_carried_out") ||
                                                                                                ПолеОтJSONKEY.equalsIgnoreCase("closed") ||
                                                                                                ПолеОтJSONKEY.equalsIgnoreCase("locked")) {
                                                                                            if (ПолеЗначениеJson.equalsIgnoreCase("false") ||
                                                                                                    ПолеЗначениеJson.equalsIgnoreCase("0")) {
                                                                                                ПолеЗначениеJson = "False";
                                                                                            }
                                                                                            if (ПолеЗначениеJson.equalsIgnoreCase("true") ||
                                                                                                    ПолеЗначениеJson.equalsIgnoreCase("1")) {
                                                                                                ПолеЗначениеJson = "True";
                                                                                            }
                                                                                        }
                                                                                        Log.d(this.getClass().getName(), " ПолеОтJSONKEY " + ПолеОтJSONKEY + " ПолеЗначениеJson" + ПолеЗначениеJson);
                                                                                        // TODO: 27.10.2022  UUID есть Обновление
                                                                                        ТекущийАдаптерДляВсего.put(ПолеОтJSONKEY, ПолеЗначениеJson);//
                                                                                    }
                                                                                })
                                                                .onErrorComplete(new Predicate<Throwable>() {
                                                                    @Override
                                                                    public boolean test(Throwable throwable) throws Throwable {
                                                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                        return false;
                                                                    }
                                                                })
                                                                .doOnComplete(new Action() {
                                                                    @Override
                                                                    public void run() throws Throwable {
                                                                        // TODO: 14.11.2022  Заполем  Обновление ЧЕРЕЗ КОНТЕКТ ПРОВАЙДЕР
                                                                        МетодContentProvoderForUpdateJrInsert(context,  имяТаблицыОтАндройда_локальноая);
                                                                        Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                                                                + ТекущийАдаптерДляВсего.size());
                                                                    }
                                                                }).blockingSubscribe();
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    }

                                                }
                                            })
                                                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                                        @Override
                                                        public void accept(Throwable throwable) throws Throwable {
                                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        }
                                                    }).doOnComplete(new Action() {
                                                @Override
                                                public void run() throws Throwable {
                                                    // TODO: 04.12.2022 end buffer
                                                    Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                                            + ТекущийАдаптерДляВсего.size());
                                                    // TODO: 09.11.2022 ПОСЛЕ ОБРАБОТКИ НАЧИНАЕМ ВСТАКУ ДАННЫХ ЧЕРЕЗ BULK INSERT
                                                    МетодBulkUPDATE(имяТаблицыОтАндройда_локальноая, context);
                                                    Log.d(this.getClass().getName(), " Конец  ПАРСИНГА ОБРАБОАТЫВАЕМОМЙ ТАБЛИЦЫ МетодBulkUPDATE   ::::: "
                                                            + имяТаблицыОтАндройда_локальноая+" АдаптерДляВставкиИОбновления.size() " +АдаптерДляВставкиИОбновления.size());
                                                    // TODO: 11.10.2022 ПОСЛЕ ОПЕРАЦИИ ВИЗАУЛИЗИРУЕМ КОНЕЦ ОПЕРАЦИИ ПОЛЬЗОВАТЕЛЮ
                                                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                                                            Проценты,"ПроцессеAsyncBackground",false,false);
                                                }
                                            }).blockingSubscribe();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 04.12.2022 end buffer
                                Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                        + ТекущийАдаптерДляВсего.size());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        }).blockingSubscribe();
                                Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                        + ТекущийАдаптерДляВсего.size());*/
                Log.d(this.getClass().getName(),  " date "+ "jsonObjects "  +new Date().toGMTString().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            Log.d(this.getClass().getName(), "  ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы " + ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы);
            return ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы;
        }












//todo МЕТОД ВИЗУАЛЬНОГО ОТВЕТА ИЗ СЛУЖБЫ ОБРАБТНО В activity async
        private void МетодCallBasksВизуальноИзСлужбы(Integer МаксималноеКоличествоСтрочекJSON,
                                                     Integer ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара,
                                                     String имяТаблицыОтАндройда_локальноая,
                                                     String ПроцентыВерхнегоПрограссбара,
                                                     String СтатусAsyncBackground,
                                                     Boolean РеальнаяРаботаВставкаОбновиеContProver,
                                                     Boolean ЧтоДелаемПолучаемДанныеИлиОтправляем)  {
            try {
            Message lMsg = new Message();
            Bundle bundleОтправкаОбратноActivity=new Bundle();
                if (МаксималноеКоличествоСтрочекJSON!=null) {
                    bundleОтправкаОбратноActivity.putInt("МаксималноеКоличествоСтрочекJSON",МаксималноеКоличествоСтрочекJSON);
                }
                if (ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара!=null) {
                    bundleОтправкаОбратноActivity.putInt("ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара",ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара);
                }
                if (имяТаблицыОтАндройда_локальноая!=null) {
                    bundleОтправкаОбратноActivity.putString("имяТаблицыОтАндройда_локальноая",имяТаблицыОтАндройда_локальноая);
                }
                if (ПроцентыВерхнегоПрограссбара!=null) {
                    bundleОтправкаОбратноActivity.putString("ПроцентыВерхнегоПрограссбара",ПроцентыВерхнегоПрограссбара);
                }
                if (СтатусAsyncBackground!=null) {
                    bundleОтправкаОбратноActivity.putString("СтатусРаботыСлужбыСинхронизации",СтатусAsyncBackground);
                }
                if (РеальнаяРаботаВставкаОбновиеContProver!=null) {
                    bundleОтправкаОбратноActivity.putBoolean("РеальнаяРаботаВставкаОбновиеContProver",РеальнаяРаботаВставкаОбновиеContProver);
                }
                if (ЧтоДелаемПолучаемДанныеИлиОтправляем!=null) {
                    bundleОтправкаОбратноActivity.putBoolean("ЧтоДелаемПолучаемДанныеИлиОтправляем",ЧтоДелаемПолучаемДанныеИлиОтправляем);
                }
                lMsg.setData(bundleОтправкаОбратноActivity);

                if (messengerCallBacks!=null) {
                    messengerCallBacks.send(lMsg);
                }
            } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

     /*   private void МетодBulkINSERT(@NonNull String имяТаблицыОтАндройда_локальноая,@NonNull Context context) {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + имяТаблицыОтАндройда_локальноая + "");
            Long результат_ВставкаДаннымисСервера=0l;
            try{
                    if (АдаптерДляМассовойВставкиДанныхсСервер[0]!=null) {
                    if (АдаптерДляМассовойВставкиДанныхсСервер[0].toString().length() > 0) {
                        //TODO  ПОСЛЕ ОБРАБОТКИ ВСЕЙ ТАБЛИЦЫ ТЕСТОВО ЗАПУСКАЕМ ЕТОД МАССОВОЙ ВСТАВКИ ЧЕРЕЗ КОНТЕНТ ПРОВАЙДЕР МЕТОД BurkInset
                        Log.w(context.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length  " + АдаптерДляМассовойВставкиДанныхсСервер.length +
                                "\n" + " АдаптерДляВставкиДанныхсСервер.size()  " + АдаптерДляВставкиДанныхсСервер.size() + " uri  " + uri);/////

                        // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                        МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                                ИндексТекущейОперацииJSONДляВизуальнойОбработки, имяТаблицыОтАндройда_локальноая,
                                Проценты, "ПроцессеAsyncBackground",
                                true,false);

                        ContentResolver contentResolver = context.getContentResolver();
                        int РезультатВставкиМассовой = contentResolver.bulkInsert(uri, АдаптерДляМассовойВставкиДанныхсСервер);
                        // TODO: 27.10.2021
                        if (РезультатВставкиМассовой > 0) {
                            ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;
                            АдаптерДляВставкиДанныхсСервер.clear();
                            АдаптерДляМассовойВставкиДанныхсСервер = null;
                        }
                        результат_ВставкаДаннымисСервера = Long.valueOf(РезультатВставкиМассовой);
                        // TODO: 30.10.2021
                        Log.d(this.getClass().getName(), " Результат_ВставкаДаннымисСервера :::  "
                                + РезультатВставкиМассовой + "\n" +
                                "  имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                        // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }*/

        private void МетодBulkUPDATE(@NonNull String имяТаблицыОтАндройда_локальноая,@NonNull Context context) {
           Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirror/" + имяТаблицыОтАндройда_локальноая + "");
         //   Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + имяТаблицыОтАндройда_локальноая + "");
            Long результат_ОбновлениенымисСервера=0l;
            try{
                    if (АдаптерДляВставкиИОбновления.size()>0) {
                        //TODO  ПОСЛЕ ОБРАБОТКИ ВСЕЙ ТАБЛИЦЫ ТЕСТОВО ЗАПУСКАЕМ ЕТОД МАССОВОЙ ВСТАВКИ ЧЕРЕЗ КОНТЕНТ ПРОВАЙДЕР МЕТОД BurkInset
                        Log.w(context.getClass().getName(), " АдаптерДляВставкиИОбновления.size()  " + АдаптерДляВставкиИОбновления.size()+
                                "\n" + " АдаптерПриОбновленияДанныхсСервера.size()  " + ТекущийАдаптерДляВсего.size()+" uri  " + uri);/////
                        ContentResolver contentResolver  = context.getContentResolver();
                        // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                        МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                                ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                                Проценты,"ПроцессеAsyncBackground",
                                true,false);
                        ContentValues[] contentValuesМассив=new ContentValues[АдаптерДляВставкиИОбновления.size()];
                        contentValuesМассив=АдаптерДляВставкиИОбновления.toArray(contentValuesМассив);
                        int РезультатОбновлениеМассовой = contentResolver.bulkInsert(uri, contentValuesМассив);
                        // TODO: 27.10.2021
                        if (РезультатОбновлениеМассовой>0) {
                            ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;
                            ТекущийАдаптерДляВсего.clear();
                            АдаптерДляВставкиИОбновления.clear();
                        }
                        результат_ОбновлениенымисСервера = Long.valueOf(РезультатОбновлениеМассовой);
                        Log.d(this.getClass().getName(), " РезультатОбновлениеМассовой :::  "
                                + РезультатОбновлениеМассовой+"\n"+
                                "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



       /* private void МетодContentProvoderForBulkInsert(Context context,
                                                       String имяТаблицыОтАндройда_локальноая) {

            try{
                if (АдаптерДляВставкиДанныхсСервер.size() > 0) {
                    АдаптерДляМассовойВставкиДанныхсСервер[ ИндексДляМассовогоВставкаКонтейнер[0]] = new ContentValues();
                    АдаптерДляМассовойВставкиДанныхсСервер[ ИндексДляМассовогоВставкаКонтейнер[0]].putAll(АдаптерДляВставкиДанныхсСервер);
                    Log.d(this.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length :::  "
                            + АдаптерДляМассовойВставкиДанныхсСервер.length);
                        ИндексТекущейОперацииJSONДляВизуальнойОбработки++;
                    ИндексДляМассовогоВставкаКонтейнер[0]++;
                    // TODO: 11.10.2022 callback метод обратно в актвити #6
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,ИндексТекущейОперацииJSONДляВизуальнойОбработки,имяТаблицыОтАндройда_локальноая,
                            Проценты,"ПроцессеAsyncBackground",false,false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
       
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }*/
        private void МетодContentProvoderForUpdateJrInsert(Context context, String имяТаблицыОтАндройда_локальноая) {

            try{
                if (ТекущийАдаптерДляВсего.size() > 0) {
               /*     АдаптерДляВставкиИОбновления[ ИндексДляМассовогоОбшицКонтейнер[0]]=new ContentValues();*/
               /*     АдаптерДляВставкиИОбновления[ ИндексДляМассовогоОбшицКонтейнер[0]].putAll(ТекущийАдаптерДляВсего);*/
                    АдаптерДляВставкиИОбновления.add(ТекущийАдаптерДляВсего);

                    Log.d(this.getClass().getName(), " АдаптерДляВставкиИОбновления  " + АдаптерДляВставкиИОбновления.size()+
                            " ИндексВизуальнойДляPrograssBar " +ИндексВизуальнойДляPrograssBar);
                        ИндексВизуальнойДляPrograssBar++;
                }
                // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                        ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                        Проценты,"ПроцессеAsyncBackground",
                        false,false);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

       /* //////////////TODO метод непосредвственой запись в базу данных json КОТОРЫЙ ПРИШЁЛ С СЕРВЕРА---!!!!! ПЕРВАЯ ОПЕРПЦИЯ ВСТАВКА
        Long МетодаЗаписиВБазуКонтейнераВСТАВКАJSONвФоне(String имяТаблицыОтАндройда_локальноая,
                                                         @NotNull CompletionService МенеджерПотоков,
                                                         SQLiteDatabase getБазаДанныхДЛяОперацийВнутри
                ,Integer СколькоСтрочекJSON,
                                                         Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки) {////запись полученого json   от сервера через контейнер

            Log.d(this.getClass().getName(), " ИндексТекущейОперацииJSONДляВизуальнойОбработки  " +  ИндексТекущейОперацииJSONДляВизуальнойОбработки + " МенеджерПотоков "+ МенеджерПотоков+
                    " имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая+  "СколькоСтрочекJSON " +СколькоСтрочекJSON);
            Long РезультатВставкиЧерезКонтрейнер = 0l;
            try {
                //////////todo ВСТАВКА JSON НА КЛИЕНТА ДАННЫЕ С СЕРВЕРА
                Log.i(this.getClass().getName(), "  АдаптерДляВставкиДанныхсСервер      " + АдаптерДляВставкиДанныхсСервер.size());
                ////////ВЫЗЫВАЕМ ВСТАВКУ ДАННЫХ
                // TODO: 10.09.2021 сама операция всатвки
                РезультатВставкиЧерезКонтрейнер = ВставкаДанныхЧерезКонтейнерУниверсальнаяЧерезContentResolver(имяТаблицыОтАндройда_локальноая,
                        АдаптерДляВставкиДанныхсСервер, имяТаблицыОтАндройда_локальноая,
                        "", true,
                        СколькоСтрочекJSON, true, context, МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри,СколькоСтрочекJSON,
                        ИндексТекущейОперацииJSONДляВизуальнойОбработки);

                Log.d(this.getClass().getName(), "РезультатВставкиЧерезКонтрейнер   " + РезультатВставкиЧерезКонтрейнер);

                /// после вствки в базу обнуляем контейнер данные от сервера
                if (РезультатВставкиЧерезКонтрейнер > 0) {
                    //////
                    //// todo ПРИ УСПЕШНОЙ ВСТАВКИ ДАННЫХ  ПЕРЕДАЕМ СТАТИЧНОМУ СЁЧИКК  ОБНОВЛЕНИЙ ЧТО НАДО УВЕЛИЧИТ ЗНАЧЕНИЕ НА 1+

                    /////TODO ВАЖНО ПОСЛЕ УСПЕШНОЙ ОБРАБОТКИ ПРИСВАИВАЕМ ЗНАЧЕНИЕ присваиваем наверх факсическое значение идущего цикла После Успешного прохода ТАБЛИЦЫ одной ИЗ
                    Log.d(this.getClass().getName(), " РезультатВставкиЧерезКонтрейнер" + РезультатВставкиЧерезКонтрейнер);
                    ///TODO переводим ввобщим в универсальный индификатор
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатВставкиЧерезКонтрейнер;
        }*/


        //////////////TODO метод непосредвственой запись в базу данных json КОТОРЫЙ ПРИШЁЛ С СЕРВЕРА  ВТОРАЯ ОПЕРАЦИЯ ОБНОВЛЕНИЯ !!!!!!!
        Integer МетодаСамоОБНОВЛЕНИЕЧЕРЕЗUUID(@NotNull  String имяТаблицыОтАндройда_локальноая,
                                              @NotNull Long UUIDПолученныйИзПришедшегоJSON,
                                              @NotNull CompletionService МенеджерПотоков,
                                              SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                              Integer СколькоСтрочекJSON,
                                              Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки) {////запись полученого json   от сервера через контейнер
            Log.d(this.getClass().getName(), " UUIDПолученныйИзПришедшегоJSON  " + UUIDПолученныйИзПришедшегоJSON+ "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
            Integer РезультатОбновлениеЧерезКонтрейнер = 0;
            try {
                Log.i(this.getClass().getName(), "  АдаптерДляОбновленияПриВставкиДанныхсСервера " + ТекущийАдаптерДляВсего.size());
                ///TODO когда есть только UUID
                if (UUIDПолученныйИзПришедшегоJSON>0) {
                    //////todo UUID UPDATE
                    РезультатОбновлениеЧерезКонтрейнер =
                            ОбновлениеДанныхЧерезКонтейнерУниверсальная(имяТаблицыОтАндройда_локальноая,
                                    ТекущийАдаптерДляВсего,
                            String.valueOf(UUIDПолученныйИзПришедшегоJSON),
                            СколькоСтрочекJSON, true, context,
                            "uuid", МенеджерПотоков,
                                    getБазаДанныхДЛяОперацийВнутри,СколькоСтрочекJSON,
                                    ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                    Log.d(this.getClass().getName(), "РезультатОбновлениеЧерезКонтрейнер"
                            + РезультатОбновлениеЧерезКонтрейнер);
                }
                if (РезультатОбновлениеЧерезКонтрейнер > 0) {
                    Log.d(this.getClass().getName(), " КоличествоУспешныхОбновлений JSON РезультатОбновлениеЧерезКонтрейнер " + РезультатОбновлениеЧерезКонтрейнер);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОбновлениеЧерезКонтрейнер;
        }

        //////////////TODO метод непосредвственой запись в базу данных json КОТОРЫЙ ПРИШЁЛ С СЕРВЕРА  ВТОРАЯ ОПЕРАЦИЯ ОБНОВЛЕНИЯ !!!!!!!
        Integer МетодаСамоОБНОВЛЕНИЕЧЕРЕЗID(@NotNull  String имяТаблицыОтАндройда_локальноая,
                                            @NotNull    Long IDИзПришедшегоJSON,
                                            @NotNull CompletionService МенеджерПотоков,
                                            SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                            Integer СколькоСтрочекJSON,
                                            Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки) {////запись полученого json   от сервера через контейнер
            Log.d(this.getClass().getName(), " IDИзПришедшегоJSON  " + IDИзПришедшегоJSON+ "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
            Integer РезультатОбновлениеЧерезКонтрейнер = 0;
            try {
                Log.i(this.getClass().getName(), "  АдаптерДляОбновленияПриВставкиДанныхсСервера " + ТекущийАдаптерДляВсего.size());
                // TODO: 08.04.2021 НЕТ UUID И ОБНОВЛЕМ ПО ID
                if (IDИзПришедшегоJSON >0) {
                    ///todo
// TODO: 08.04.2021 НЕТ UUID И ОБНОВЛЕМ ПО ID
                    String ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID = null;
                    ////TODO в обратную сторону обмена из _id в таблице tabels на id меняем ы фон
                    switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                        case "tabels":
                        case "chats":
                        case "data_chat":
                        case "chat_users":
                        case "fio":
                        case "tabel":
                        case "data_tabels":
                            System.out.println("  ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID  " + ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID +
                                    " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                            ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID = "_id";
                            break;
                        default:
                            ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID = "id";
                            System.out.println("  ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID  " + ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID +
                                    " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    }
                    //////todo ID UPDATE
                    РезультатОбновлениеЧерезКонтрейнер =
                            ОбновлениеДанныхЧерезКонтейнерУниверсальная(имяТаблицыОтАндройда_локальноая,
                                    ТекущийАдаптерДляВсего,
                            String.valueOf(IDИзПришедшегоJSON),
                            СколькоСтрочекJSON,
                            true, context,
                            ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID,МенеджерПотоков,
                                    СсылкаНаБазуSqlite,СколькоСтрочекJSON,
                                    ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                    Log.d(this.getClass().getName(), "РезультатОбновлениеЧерезКонтрейнер" + РезультатОбновлениеЧерезКонтрейнер);
                }
                if (РезультатОбновлениеЧерезКонтрейнер > 0) {
                    Log.d(this.getClass().getName(), " КоличествоУспешныхОбновлений JSON РезультатОбновлениеЧерезКонтрейнер " + РезультатОбновлениеЧерезКонтрейнер);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОбновлениеЧерезКонтрейнер;
        }






















































        ///----------- ТУТ КОД УЖЕ ПОСЫЛАНИЕ ДАННЫХ НА СЕРВЕР МЕТОДУ POST (данные андройда посылаються на сервер)


        /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST
        Integer МетодПосылаемДанныеНаСервервФоне(String имяТаблицыОтАндройда_локальноая,
                                                 Long РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                                 CompletionService МенеджерПотоковВнутрений) {

            Integer РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление=0;
            Log.d(this.getClass().getName(), "  имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
            Long Версия_ДанныхАндройДляОтправкиДанныхНАсервер = 0l;
            int КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки = 0;
            Integer КоличествоКолоноквОтправвляемойТаблице = 0;
            try {
                Log.d(this.getClass().getName(), "  МетодПосылаемДанныеНаСервер в фоне ");
                Class_GRUD_SQL_Operations  class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);
                // TODO: 15.02.2022
                Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsДляВыполенияОперацииGEtData=class_grud_sql_operationsПосылаемДанныеНаСервервФоне.new GetData(context);
                // TODO: 15.02.2022
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "versionserveraandroid_version");
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " name=?   ");
                ///"_id > ?   AND _id< ?"
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", имяТаблицыОтАндройда_локальноая);
                SQLiteCursor КурсорДляАнализаДатыПоследнейОтпракиНаСервер = null;
                ///////
                КурсорДляАнализаДатыПоследнейОтпракиНаСервер = (SQLiteCursor) class_grud_sql_operationsДляВыполенияОперацииGEtData
                        .getdata(class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                Log.d(this.getClass().getName(), "GetData  КурсорДляАнализаДатыПоследнейОтпракиНаСервер "+КурсорДляАнализаДатыПоследнейОтпракиНаСервер );
                if (КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getCount()>0) {
                    ///// todo УДАЛЯЕМ ИЗ ПАМЯТИ  ОТРАБОТАННЫЙ АСИНАТСК
                    Log.i(this.getClass().getName(), " КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getCount() " + КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getCount());
                    КурсорДляАнализаДатыПоследнейОтпракиНаСервер.moveToFirst();
                    Integer ИндексГлдеНаходитьсяСлолбикСВерисеДанныхСервернойЛОкальноНаТелефоне=КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getColumnIndex( "versionserveraandroid_version");
                    Версия_ДанныхАндройДляОтправкиДанныхНАсервер = 0l;
                    // TODO: 05.10.2021
                    Версия_ДанныхАндройДляОтправкиДанныхНАсервер = КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getLong(ИндексГлдеНаходитьсяСлолбикСВерисеДанныхСервернойЛОкальноНаТелефоне);
                    ///TODO ЕСЛИ НЕТ ДАТЫ НЕЧЕГО ОТПРАВЛЯТЬ
                    if (Версия_ДанныхАндройДляОтправкиДанныхНАсервер >=0) {
                        Log.d(this.getClass().getName(), " Версия_ДанныхАндройДляОтправкиДанныхНАсервер " + Версия_ДанныхАндройДляОтправкиДанныхНАсервер +
                                "  имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                        int КоличествоСтрокПолученыеДляОтпарвкиПоДате = КурсорДляАнализаДатыПоследнейОтпракиНаСервер.getCount();
                        Log.d(this.getClass().getName(), " КоличествоСтрокПолученыеДляОтпарвкиПоДате   " + КоличествоСтрокПолученыеДляОтпарвкиПоДате);
                        ///todo
                    }

                }
                // TODO: 06.09.2021  закрываем
                ///todo закрываем куроср
                КурсорДляАнализаДатыПоследнейОтпракиНаСервер.close();
                // TODO: 21.09.2021  ВТОРАЯ ЧАСТЬ    НЕПОСРЕДСТВЕННО ВЫЯСНИВ ЕСЛИ ДАННЫЕ ДЛЯ ОТПРАВКИ ,      ПОЛУЧАЕМ ДАННЫЕ ДЛЯ САМОЙ ОТПРАВКИ ЧРЕЗ КУРСОР ВТОРОЙ   КурсорДляОтправкиДанныхНаСервер
                ///todo закрываем куроср
                Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   " + имяТаблицыОтАндройда_локальноая);
                SQLiteCursor КурсорДляОтправкиДанныхНаСервер = null;
                Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   " + имяТаблицыОтАндройда_локальноая);
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");
                Integer ПубличныйIDДляФрагмента=0;
                // TODO: 31.01.2022 ОПРЕДЕЛЯЕМ ПУБЛИЧНЫЙ id
                ПубличныйIDДляФрагмента = getInteger(имяТаблицыОтАндройда_локальноая, РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне);
                Log.d(this.getClass().getName(), " ПубличныйIDДляФрагмента   " + ПубличныйIDДляФрагмента);
                //////TODO ВКЛЮЧАЕМ ФЛАГ НЕ ПОВТОРАЕМОСТИ СТРОК
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагНепотораяемостиСтрок",true);
                Log.d(this.getClass().getName(), "     class_grud_sql_operationsПосылаемДанныеНаСервервФоне.\n" +
                        "                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций   " +     class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций);
                // TODO: 15.02.2022  код обработка таблиц синхрониазции
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне = МетодТаблицСинхрониазцииОбменаВыбираемДляКаждойТаблицыСвоиКурсоры(имяТаблицыОтАндройда_локальноая,
                        РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                        Версия_ДанныхАндройДляОтправкиДанныхНАсервер, ПубличныйIDДляФрагмента);
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                class_grud_sql_operationsДляВыполенияОперацииGEtData=class_grud_sql_operationsПосылаемДанныеНаСервервФоне.new GetData(context);
                // TODO: 15.02.2022
                КурсорДляОтправкиДанныхНаСервер = null;
                КурсорДляОтправкиДанныхНаСервер = (SQLiteCursor) class_grud_sql_operationsДляВыполенияОперацииGEtData
                        .getdata(class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                Log.d(this.getClass().getName(), "GetData " + КурсорДляОтправкиДанныхНаСервер);
                //////ОЧИСТКА ПАМЯТИ ОТ ASYNSTASK
                Log.d(this.getClass().getName(), "КурсорДляОтправкиДанныхНаСервер.getCount()  ЕСЛИ 0 СТРОЧЕК ТО ДЕЛАЕМ ЕЩЕ ОДИН ПРОВЕРКУ НА null " + КурсорДляОтправкиДанныхНаСервер.getCount());
                /////TODO результаты   количество отправляемой информации на сервера
                if (КурсорДляОтправкиДанныхНаСервер.getCount() > 0) {/////работаем уже в сгенерированных даннных которые мы отправим на сервер
                    КурсорДляОтправкиДанныхНаСервер.moveToFirst();
                    КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки = КурсорДляОтправкиДанныхНаСервер.getCount();////КОЛИЧЕСТВО СТРОК В АНДРОЙДЕ ДАННЫЕ КОТОРЫЕ НУЖНО ПОСЛЛАТЬ
                    КоличествоКолоноквОтправвляемойТаблице = КурсорДляОтправкиДанныхНаСервер.getColumnCount();/////КОЛИЧЕСТВО СТОЛЮЦОВ НА АНДРОДЕ БАЗЕ КОТОРОЫЕ НУЖНО ОТОСЛАТЬ
                    Log.d(this.getClass().getName(), " КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки  " + КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки +
                            "  КоличествоКолоноквОтправвляемойТаблице  " + КоличествоКолоноквОтправвляемойТаблице +
                            "  КурсорДляОтправкиДанныхНаСервер.getCount() " +КурсорДляОтправкиДанныхНаСервер.getCount());
                    ////TODO провеояем чтобы  JSON ФАЙЛ БЫЛ НЕ ПУСТЫМ ДЛЯ ОТПРПВИК ЕГО НЕ СЕРВЕР
                    if (КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки > 0) {
                        РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление = 0;
                        Log.d(this.getClass().getName(), "КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки " + КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки);
                        //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                        РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление =
                                МетодГенеррируемJSONИзНашыхДанныхвФоне(КурсорДляОтправкиДанныхНаСервер, КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки,
                                        КоличествоКолоноквОтправвляемойТаблице, имяТаблицыОтАндройда_локальноая,МенеджерПотоковВнутрений);
                        Log.d(this.getClass().getName(), "РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление " + РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление);

                    }
                }
                ///todo закрываем куроср
                КурсорДляОтправкиДанныхНаСервер.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление;
        }


        // TODO: 15.02.2022 синхрогниазции таблиц
        @NonNull
        private Class_GRUD_SQL_Operations МетодТаблицСинхрониазцииОбменаВыбираемДляКаждойТаблицыСвоиКурсоры(String имяТаблицыОтАндройда_локальноая,
                                                                                                            Long РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                                                                                            Long Версия_ДанныхАндройДляОтправкиДанныхНАсервер,
                                                                                                            Integer ПубличныйIDДляФрагмента) {
            Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне = null;






            // TODO: 31.01.2022  ---ВЫБОР В ЗАВИСИМОСТИ ОТ ТЕКУЩЕЙ ТАБЛИЦЫ БЫБИРАЕМ ПО АКОЙ ТАЛИЦЕ БУДЕТ ПРОИЗВЕДЕНА ВЫБОРКА

            try{

                Log.w(this.getClass().getName(), "имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);

                switch (имяТаблицыОтАндройда_локальноая.trim()) {


                    case "tabels":
                    case "chat_users":
                    case "fio":
                    case "tabel":
                    case "data_tabels":

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для tabels  chat_users  fio  tabel  data_tabels  " + имяТаблицыОтАндройда_локальноая);
                        // TODO: 19.10.2021


                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);


                        // TODO: 01.02.2022 БЛОК КОДА ДЛЯ ВСЕХ ТАБОИЦ БЕЗ ПОД ЗАПРОСОD SUB QUERY
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);


                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   "
                                + имяТаблицыОтАндройда_локальноая + "  РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);


                        // TODO: 31.01.2022

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ПубличныйIDДляФрагмента);



                        // TODO: 19.10.2021

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер1"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +"  AND date_update IS NOT NULL   ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер2","  SELECT * FROM " + имяТаблицыОтАндройда_локальноая +" " +
                                        "  WHERE user_update=" + ПубличныйIDДляФрагмента +"  AND _id IS NULL  AND date_update IS NOT NULL ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"



                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);

                        /////////

                        ///TODO
                        break;







//TOdo две табЛИЦЫ ДЛЯ ЧАТА  1
                    case "chats":

                        // TODO: 19.10.2021

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для chats " + имяТаблицыОтАндройда_локальноая);

                        // TODO: 11.01.2022 ПУБЛИЧНЫЙ ID ТЕКУЩЕГО ПОЛЬЗОВТЕЛЯ
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);


                        ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ПубличныйIDДляФрагмента);

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);
                        // TODO: 18.02.2022


                        // TODO: 01.02.2022 БЛОК КОДА ДЛЯ ВСЕХ ТАБОИЦ БЕЗ ПОД ЗАПРОСОD SUB QUERY
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);



                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   "
                                + имяТаблицыОтАндройда_локальноая + "  РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера ");

                        // TODO: 19.10.2021

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  user_update = ?" +
                                        " AND current_table > ? AND date_update IS NOT NULL  AND _id IS NULL ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

                        /////////

                        ///TODO
                        break;





//TOdo две табЛИЦЫ ДЛЯ ЧАТА  2

                    case "data_chat":

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для data_chat  " + имяТаблицыОтАндройда_локальноая);
                        // TODO: 19.10.2021

              /*  class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(contextСозданиеБАзы);




                //TODO OR oretrtion не мои ЗАПИСИ А ДРУГОВО ПОЛЬЗОВАТЕЛЯ КОТОРЫЙ МЕН НАПИСАЛ

                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ПубличныйIDДляФрагмента);

                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска5",1);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска6",ПубличныйIDДляФрагмента);*/

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА  old version

           /*     ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID(contextСозданиеБАзы).ПолучениеПубличногоТекущегоПользователяID();

                Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПубличныйIDДляФрагмента);

                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);
                // TODO: 19.10.2021

                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ПубличныйIDДляФрагмента);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска5",1);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска6",ПубличныйIDДляФрагмента);
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА*/
                        // TODO: 19.10.2021  old data_chat

      /*          class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                        .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," " +
                        "  current_table > ? AND user_update=?   AND date_update IS NOT NULL" +
                        " OR    current_table > ? AND id_user=?  AND status_write=?  AND date_update IS NOT NULL "+
                        " OR   user_update=?  AND _id IS NULL  AND date_update IS NOT NULL ");
*/
                        // TODO: 19.10.2021

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);
                        // TODO: 19.10.2021
// TODO: 15.02.2022  тут КОД ОТПРАВЛЯЕТ СВОИ СООБЩЕНИЯ НАПИСАННЫЕ САМИМ
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер1"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                                        "  AND user_update= "+ ПубличныйIDДляФрагмента +
                                        " AND date_update IS NOT NULL   ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"

                        // TODO: 15.02.2022  тут сообещния написнные другим пользователем но тоже отпралвем когда мы имзенили статус на прочитанный и с столбике wtire изменил на 1 и отпрадем на сервер как прочитанные

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер2"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                                        "  AND status_write=1 "+
                                        " AND date_update IS NOT NULL   ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "

                        // TODO: 15.02.2022  тут сообещния написанфые мною жополнительное условия где поле ID еще NULL в догонку какбы

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер3","  SELECT * FROM " + имяТаблицыОтАндройда_локальноая +" " +
                                        "  WHERE user_update=" + ПубличныйIDДляФрагмента +"  AND _id IS NULL  AND date_update IS NOT NULL ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"

                        // TODO: 15.02.2022  тут сообещния написанфые мною жополнительное условия где поле ID еще NULL в догонку какбы


                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер4"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                                        "  AND alreadyshownnotifications=1 "+
                                        " AND date_update IS NOT NULL   ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        // TODO: 19.01.2022  old version         class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                        //                            .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," " +
                        //                            " user_update = ?  AND current_table > ?   AND date_update IS NOT NULL "
                        //                            + "  OR  current_table > ?  AND id_user=?  and status_write=?  "
                        //                            + "  OR  user_update = ?  AND _id IS NULL   AND date_update IS NOT NULL   ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        //                    ///"_id > ?   AND _id< ?"
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

                        /////////

                        ///TODO
                        break;













                    case "settings_tabels":

                        // TODO: 15.02.2022
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для settings_tabels  " + имяТаблицыОтАндройда_локальноая);

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА


                        // TODO: 01.02.2022 БЛОК КОДА ДЛЯ ВСЕХ ТАБОИЦ БЕЗ ПОД ЗАПРОСОD SUB QUERY
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);



                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   "
                                + имяТаблицыОтАндройда_локальноая + "  РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера ");
                        // TODO: 19.10.2021

                        // TODO: 19.10.2021

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  current_table > ?   AND date_update IS NOT NULL  ");//AND _id IS NULL//"  current_table > ? OR _id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

                        /////////

                        ///TODO
                        break;





                    default:


                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для          default: " + имяТаблицыОтАндройда_локальноая);

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);

                        // TODO: 19.10.2021

                        //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   "
                                + имяТаблицыОтАндройда_локальноая + "  РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера ");




                        // TODO: 01.02.2022 БЛОК КОДА ДЛЯ ВСЕХ ТАБОИЦ БЕЗ ПОД ЗАПРОСОD SUB QUERY
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);



                        // TODO: 19.10.2021

                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "  current_table > ?   AND date_update IS NOT NULL  ");//AND _id IS NULL///"  current_table > ? OR id IS NULL  AND date_update IS NOT NULL "
                        ///"_id > ?   AND _id< ?"
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   " + имяТаблицыОтАндройда_локальноая);

                        //////
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",
                                Версия_ДанныхАндройДляОтправкиДанныхНАсервер);
                        ///

                        ///////
                        ///TODO
                        break;

                }


                // TODO: 15.02.2022

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }





            return class_grud_sql_operationsПосылаемДанныеНаСервервФоне;
        }














        private Integer getInteger(String имяТаблицыОтАндройда_локальноая, Long РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера, Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне) {
            Integer ПубличныйIDДляФрагмента;
            // TODO: 11.01.2022 ПУБЛИЧНЫЙ ID ТЕКУЩЕГО ПОЛЬЗОВТЕЛЯ

//////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА  old version

            ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

            Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",ПубличныйIDДляФрагмента);
            return ПубличныйIDДляФрагмента;
        }


        // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР








        /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST






        // TODO: 18.10.2021  метод дОВОЛТИЛЬЕНО  ПРОВЕРКИ ЕСЛИ ЗНАЧЕНИ НУЛЛВ ПОЛНЕ ID
        private SQLiteCursor МетодДополнительнойПроверкиНаЗначниеКоторыеЩЕНеОтправленны_СтолбикеID_ЕстьЗначенияNULL(String имяТаблицыОтАндройда_локальноая,
                                                                                                                    CompletionService МенеджерПотоковВнутрений
        ) throws ExecutionException, InterruptedException {
            Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне;

            SQLiteCursor КурсорДляОтправкиДанныхНаСервер=null;

            try{
                //todo ЕСЛИ НЕЧЕГО ОТПРАВЛЯЕТЬ ТО ДОПОЛНТЕЛЬНО ПРОВЕРЯЕМ МОЖЕТ ЕЩЕ ОТПРАВИТЬ НА СЕРВЕР



                Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   " + имяТаблицыОтАндройда_локальноая);

                // TODO: 06.09.2021 ДОПОЛНИТЕЛЬНЫЙ МЕХАНИЗСМ ОТПРВКИ ДАНННЫХС NULL В ID ПОЛЕ ДЛЯ ЧАТА

                class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);


                class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                        .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",
                                имяТаблицыОтАндройда_локальноая);


                // TODO: 12.10.2021 выбор двух вариантов отправки с _id and id


                // TODO: 12.10.2021

                switch (имяТаблицыОтАндройда_локальноая.trim()) {


                    case "tabels":
                    case "chats":
                    case "data_chat":
                    case "chat_users":
                    case "fio":
                    case "tabel":
                    case "data_tabels":



                        Log.d(this.getClass().getName(), "имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                        ///////
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "_id");

                        //
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "  _id IS NULL    AND date_update IS NOT NULL   ");
                        ///"_id > ?   AND _id< ?"
                        break;


                    default:

                        Log.d(this.getClass().getName(), "имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                        ///////
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "id");
                        //
                        class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "  id IS NULL     AND date_update IS NOT NULL   ");
                        ///"_id > ?   AND _id< ?"

                }


                //////
                /// class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", "");
                ///

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

                КурсорДляОтправкиДанныхНаСервер = null;

                КурсорДляОтправкиДанныхНаСервер = (SQLiteCursor) class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                        new GetData(context).getdata(class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);

                Log.d(this.getClass().getName(), "GetData " + КурсорДляОтправкиДанныхНаСервер);


                if(КурсорДляОтправкиДанныхНаСервер.getCount()>0){
                    ////
                    КурсорДляОтправкиДанныхНаСервер.moveToFirst();

                }

                // TODO: 06.09.2021  old
/*
            КурсорДляОтправкиДанныхНаСервер = КурсорУниверсальныйДляБазыДанных(имяТаблицыОтАндройда_локальноая, new String[]{"*"}, " _id IS NULL  OR _id = ? ",
                    new String[]{""}, null, null, null, null);*/
                ///


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }

            return КурсорДляОтправкиДанныхНаСервер;
        }














        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        Integer МетодГенеррируемJSONИзНашыхДанныхвФоне( Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда, int КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки,
                                                        Integer КоличествоКолоноквОтправвляемойТаблице, String имяТаблицыОтАндройда_локальноая,CompletionService МенеджерПотоковВнутрений) {
            ///
            Log.d(this.getClass().getName(), " КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки " + КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки);
            Integer РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = 0;
            final int[] ЕслиUUIDПриЗабросеДанныхНаСервер = {0};
            final int[] ЕслиIDПриЗабросеДанныхНаСервер = {0};
            JSONObject ГенерацияJSONполейФинал = new JSONObject();///генериция финального поля дляJSON;  ////ПОЛЯ  ДЛЯ  JSON
            try {
                 String ПерхнееПолеJSONПоследнаяОперация = null;////ПЕРЕРВОДИМ ИЗ INT TO STRING
                Log.d(this.getClass().getName(), " КурсорДляОтправкиДанныхНаСервер.getCount())   "
                        + КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() + " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                boolean СработалПоворот = false;
                if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToLast();
                    Log.d(context.getClass().getName(), "КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() " + КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());
                }
                final int[] КакаяСтрочкаОбработкиТекущаяя = {1};/////ОСТЛЕЖИВАЕМ ТЕКУЩУЮ СТРОЧКУ ОБРАБОТКИ
                do {/////КРУТИТЬ ДАННЫЕ ЧЕРЕЗ ЦИКЛ ОТВЕТЫ ОТ МЕТОДА POST
                    JSONObject ГенерацияJSONполей = new JSONObject();  ////ПОЛЯ  ДЛЯ  JSON ///ВАЖНО ГЕНЕРАЦИЯ НОВЫХ ОБЬЕКТОВ JSON НУЖНО СТАВИТЬ ВНУТРИ DO WHILE  НО ДО FOR ЦИКЛА МЕЖДУ НИМИ
                    CopyOnWriteArrayList<Integer> linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре=new CopyOnWriteArrayList<Integer>();
                    for (Integer i = 0; i < КоличествоКолоноквОтправвляемойТаблице; i++) {
                        linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.add(i);
                    }
                    Iterator iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре=linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.iterator();
                    //todo бежим по столбцам
                    while (iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.hasNext()) {
                        try {
                            //////////TODO ЭКСПЕРЕМЕНТ С JSON
                            Integer ИндексПоТАблицамДляОтправки= (Integer) iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.next();
                            Log.d(this.getClass().getName(), "  ИндексПоТАблицамДляОтправки " +ИндексПоТАблицамДляОтправки );
                            String КлючJsonСтроки = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnName(ИндексПоТАблицамДляОтправки);
                            System.out.println(" КлючJsonСтроки  " + КлючJsonСтроки);
                            Object ЗначниеJsonСтроки = КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексПоТАблицамДляОтправки);
                            Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                    + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                            //todo
                            if (!КлючJsonСтроки.equalsIgnoreCase("_id") && !КлючJsonСтроки.equalsIgnoreCase("id")) {
                                ЗначниеJsonСтроки=  Optional.ofNullable(ЗначниеJsonСтроки).orElse("");
                                Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                        + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                            }
                            Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                    + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                            //TODO НАЧИНАЕМ ОБРАБАТЫВАТЬ КОГДА ЗНАЧЕНИЕ ПО СТОЛБИКУ ОТРУСТУЕТ VALUE==BULL  #ПЕРВАЯ ЧАСТЬ
                            Log.d(this.getClass().getName(), " КлючJsonСтроки " + КлючJsonСтроки);
                            ////////// TODO КОНКРЕТАНАЯ ГЕНЕРАЦИЯ  JSON СТРОКИ
                            if (КлючJsonСтроки != null && ЗначниеJsonСтроки != null) {//ПРОИЗВОДИМ ВСТАВКИ JSON ПОЛЕЙ ТОЛЬКО ЕСЛИ ОНИ НЕ NULL
                                // TODO: 24.06.2021 меняем местави приотправки на сервер данные однго столика с _id на id
                                switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                                    case "tabels":
                                    case "chats":
                                    case "data_chat":
                                    case "chat_users":
                                    case "fio":
                                    case "tabel":
                                    case "data_tabels":
                                    case "nomen_vesov":
                                    case "type_materials":
                                    case "company":
                                    case "track":
                                        System.out.println("  КлючJsonСтроки  " + КлючJsonСтроки);
                                        if (КлючJsonСтроки.equals("_id")) {
                                            КлючJsonСтроки = "id";
                                            System.out.println("  КлючJsonСтроки  " + КлючJsonСтроки);
                                        }
                                        break;
                                }
                                /////TODO вытаемся отслидить хотябы один заполненый день
                                Log.d(this.getClass().getName(), "КлючJsonСтроки " + "--" + КлючJsonСтроки + " З начниеJsonСтроки " + ЗначниеJsonСтроки);/////
                                ///todo генерация самой строки json ниже ключ к нему после for//   && ЗначниеJsonСтроки.toString().matches("[1-9]"
                                if (КлючJsonСтроки.matches("[d].*") && КлючJsonСтроки.length() <= 3) {
                                    ГенерацияJSONполей.put(КлючJsonСтроки, ЗначниеJsonСтроки); ////заполение полей JSON
                                } else if (ЗначниеJsonСтроки != null && ! ЗначниеJsonСтроки.toString().equalsIgnoreCase("null")) {
                                    ГенерацияJSONполей.put(КлючJsonСтроки, ЗначниеJsonСтроки); ////заполение полей JSON
                                }
                                Log.d(this.getClass().getName(), " КлючJsonСтроки  " + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                                //todo обнуление после вставки
                                КлючJsonСтроки = null;
                                ЗначниеJsonСтроки = null;
                            }
                            //////////TODO  КОНЕЦ ЭКСПЕРЕМЕНТ С JSON
                            ///todo  только uuid
                            ЕслиUUIDПриЗабросеДанныхНаСервер[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid");
                            if (ЕслиUUIDПриЗабросеДанныхНаСервер[0] >= 0) {
                                Log.d(this.getClass().getName(), "ЕслиUUIDИлиIDПриЗабросеДанныхНаСервер " + ЕслиUUIDПриЗабросеДанныхНаСервер[0]);
                                ///////////////ЕСЛИ ID ПОЛЕ ПУСТОЕ ТО ЗАПОЛНЕМЕМ ЕГО ВТОРЫМ ПОЛЕМ
                                int ИндексДвижениеПОПОлямДЛяФОрмированиеID = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid");
                                ////todo
                                ПерхнееПолеJSONПоследнаяОперация = КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексДвижениеПОПОлямДЛяФОрмированиеID);

                            }
                            ///todo  только id
                            ЕслиIDПриЗабросеДанныхНаСервер[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("id");
                            if (ЕслиIDПриЗабросеДанныхНаСервер[0] >= 0 && ПерхнееПолеJSONПоследнаяОперация== null) {
                                Log.d(this.getClass().getName(), "ЕслиUUIDИлиIDПриЗабросеДанныхНаСервер " + ЕслиIDПриЗабросеДанныхНаСервер[0]);
                                ///////////////ЕСЛИ ID ПОЛЕ ПУСТОЕ ТО ЗАПОЛНЕМЕМ ЕГО ВТОРЫМ ПОЛЕМ
                                int ИндексДвижениеПОПОлямДЛяФОрмированиеID = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("id");
                                ////todo
                                ПерхнееПолеJSONПоследнаяОперация= КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексДвижениеПОПОлямДЛяФОрмированиеID);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    ////TODO упаковываем jon если хоть какое поле есть   , ЕСЛИ ЕСТЬ ИЛИ ID ИЛИ UUID
                    if (ПерхнееПолеJSONПоследнаяОперация != null && ГенерацияJSONполей != null && ГенерацияJSONполей.length() > 0) {
                        /// todo МЕЖДУ FOR И WHILE
                        Log.i(this.getClass().getName(), " ПерхнееПолеJSONПоследнаяОперация  :     " + ПерхнееПолеJSONПоследнаяОперация
                                + " ГенерацияJSONполей " + ГенерацияJSONполей.toString());
                        try {
                            //////////todo КОНКРЕТАНАЯ ГЕНЕРАЦИЯ  JSON ВЕРХНЕГО КЛЮЧА
                            ГенерацияJSONполейФинал.put(ПерхнееПолеJSONПоследнаяОперация, ГенерацияJSONполей);////ВСТАВЛЯЕМ ОДИН JSON в ДРУГОЙ JSON ПОЛУЧАЕМ ФИНАЛЬНЫЙ РЕЗУЛЬТАТ JSON"А
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    КакаяСтрочкаОбработкиТекущаяя[0]++;////ЛОВИМ ТЕКУУЩЮ СТРОЧКУ ОБРАБОТКИ
                } while (КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToPrevious());////ДАННЫЕ КРУТИЯТЬСЯ ДО КОНЦА ДАННЫХ И ГЕНЕРИРУЮ JSON
                ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
                Log.d(this.getClass().getName(), " ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                        " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());
                if (ГенерацияJSONполейФинал.toString().length() > 3) {
                    ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
                    Log.d(this.getClass().getName(), " ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                            " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());
                    РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления =
                            new SubClass_SendToServer(context).МетодПосылаетНаСерверСозданныйJSONФайлвФоне(ГенерацияJSONполейФинал, имяТаблицыОтАндройда_локальноая, МенеджерПотоковВнутрений); ////СГЕНЕРИРОВАНЫЙ JSON ФАЙЛ ЕСЛИ БОЛЬШЕ 2 ССИМВОЛОМ В НЕМ ТО ОТПРАВЛЯЕМ
                    //
                    Log.d(this.getClass().getName(), " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления  " + РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления);
                }else {
                    ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
                    Log.d(this.getClass().getName(), " НЕТ ДАННЫХ ДЛЯ ОТПРАВКИ  ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                            " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());

                }
                КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления;
        }





        //////ТУТ БУДЕТ ЗАПИСЫВАТЬСЯ УСПЕШНОЕ ОБНЛВДЕНИ И ВСТАВКИ ДАННЫХ НА СЕРВЕРЕ ДЛЯ КЛИЕНТА


        /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ
        public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                    String ПолучениеЗначениеСтолбикUUID,
                                                    Context КонтексДляЛокальногоОбновления,
                                                    String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
            Integer результатОбновлениеЧерезКонтрейнер = 0;
            try {
                ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
                результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                                КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
                Log.d(this.getClass().getName(),
                        "  результатОбновлениеЧерезКонтрейнер[0] " + результатОбновлениеЧерезКонтрейнер);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

        }

//todo  ПОД КЛАСС  С ГЛАВНМ ЦИКЛОМ ОБМЕНА ДАННЫМИ ТАБЛИ
            Integer МетодГлавныхЦиклТаблицДляСинхронизации(String ДанныеПришёлЛиIDДЛяГенерацииUUID)
                    throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
             Integer  РезультатТаблицыОбмена = 0;
                try {
                    ArrayList<Integer>  ЛистТаблицыОбмена = new ArrayList<>();
                    Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString()
                            + " ДатыТаблицыВерсииДанныхОтСервера "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.toString());
                    ПубличныйРезультатОтветаОтСерврераУспешно = 0;
                    // TODO: 01.12.2022  еще один тест
                    // TODO: 09.11.2022  НАЧИНАЕМ ГЛАВНЫЙ ЦИКЛ СИНХРОНИАЩЗЦИИ ПО ТАБЛИЦАМ
                    Log.d(this.getClass().getName(), " 1ТекущаяТаблицаИзПотока "  + " date " +new Date().toString());
                    Log.d(this.getClass().getName(), " 3ТекущаяТаблицаИзПотока " +" date " +new Date().toString());
                    // TODO: 01.12.2022
                    // TODO: 09.11.2022  НАЧИНАЕМ ГЛАВНЫЙ ЦИКЛ СИНХРОНИАЩЗЦИИ ПО ТАБЛИЦАМ
                           public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.forEach(new Consumer<String>() {
                               @Override
                               public void accept(String ТекущаяТаблицаИзПотока) {
                                    ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы = 0;
                                    try {
                                        Log.d(this.getClass().getName(), " ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока +"  " + new Date().toGMTString());
                                        // TODO: 06.10.2022  вычисляем какую таблицу нужно отоброзить в верхнем ПрограссбАре
                                        Integer ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения = ГлавныеТаблицыСинхронизации.indexOf(ТекущаяТаблицаИзПотока);
                                        Проценты = new Class_Visible_Processing_Async(context).
                                                ГенерируемПРОЦЕНТЫДляAsync(ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения + 1,
                                                        ГлавныеТаблицыСинхронизации.size());
                                        Log.d(this.getClass().getName(), "  ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения " +
                                                ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения + " Проценты " + Проценты);
                                        // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                                        МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                                                ИндексВизуальнойДляPrograssBar,ТекущаяТаблицаИзПотока,
                                                Проценты,"ПроцессеAsyncBackground",false,false);
                                        // TODO: 24.01.2022 сама операция синхрониазции по таблице
                                        ПубличныйРезультатОтветаОтСерврераУспешно= МетодЗапускаСинхрониазцииПоАТблицам(ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                ТекущаяТаблицаИзПотока,
                                                public_contentДатыДляГлавныхТаблицСинхронизации.МенеджерПотоков ,public_contentДатыДляГлавныхТаблицСинхронизации);
                                        Log.d(this.getClass().getName(), " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                " ТАБЛИЦА ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока +"\n"+
                                                " +ПубличныйРезультатОтветаОтСерврераУспешно" +ПубличныйРезультатОтветаОтСерврераУспешно+ "\n"+
                                                " время" +new Date().toGMTString());
                                        ЛистТаблицыОбмена.add(ПубличныйРезультатОтветаОтСерврераУспешно);
                                        ВерсияДанныхРеальнаяНаСейчасНаSqlServer = 0l;
                                      //  Thread.sleep(2000);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        // TODO: 01.09.2021 метод вызова
                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }
                            });
                    if (СсылкаНаБазуSqlite.isOpen()) {
                        СсылкаНаБазуSqlite.close();
                        СсылкаНаБазуSqlite = null;
                    }
                    РезультатТаблицыОбмена = ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b);
                    Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЗАВЫЕРШИЛАСЬ В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ РезультатТаблицыОбмена"
                            +РезультатТаблицыОбмена);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатТаблицыОбмена;
            }

        // TODO: 22.03.2022  ДЛЯ ОТПРАВКИ ДАННЫХ НА СЕРВЕР

        

        private class SubClass_SendToServer  {
            public SubClass_SendToServer(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            }
            // TODO: 22.03.2022

            //////todo МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST
            Integer МетодПосылаетНаСерверСозданныйJSONФайлвФоне(@NonNull JSONObject ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда, @NonNull String имяТаблицыОтАндройда_локальноая,
                                                                CompletionService МенеджерПотоковВнутрений) {
                /////
                Integer РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = 0;
                String ДанныеПришёлВОтветОтМетодаPOST = new String();
                StringBuffer БуферОтправкаДанныхвФоне = new StringBuffer();
                Class_GRUD_SQL_Operations class_grud_sql_operations;
                class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
                try {
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                            ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                            Проценты,"ПроцессеAsyncBackground",false,true);
                    Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
                    // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
                    Log.d(this.getClass().getName(), " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() "
                            + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() +
                            " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length  "
                            + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length +
                            " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
                    БуферОтправкаДанныхвФоне = УниверсальныйБуферОтправкиДанныхНаСервера(ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда,
                            ПубличноеIDПолученныйИзСервлетаДляUUID, имяТаблицыОтАндройда_локальноая,
                            "Получение JSON файла от Андройда",
                            60000,  ИмяСерверИзХранилица ,ПортСерверИзХранилица);
                    ///БУФЕР ОТПРАВКИ ДАННЫХ НА СЕРВЕР  //TODO original "tabel.dsu1.ru", 8888        //TODO "192.168.254.40", 8080
                    Log.d(this.getClass().getName(), "  СЛУЖБА ВЕРНУЛЬСЯ ОТВЕТ ОТ СЕРВЕРА ОБРАТНО АНДРОЙДУ  БуферОтправкаДанных.toString() " + БуферОтправкаДанныхвФоне.toString());
                    if (БуферОтправкаДанныхвФоне == null) {
                        БуферОтправкаДанныхвФоне = new StringBuffer();
                    }
                    if (БуферОтправкаДанныхвФоне.length() > 0) {
                        ПубличныйРезультатОтветаОтСерврераУспешно = 0;
                        ПубличныйРезультатОтветаОтСерврераУспешно = БуферОтправкаДанныхвФоне.length();
                    }
                    Log.d(this.getClass().getName(), "БуферОтправкаДанныхвФоне.length() " + БуферОтправкаДанныхвФоне.length() +
                            " БуферОтправкаДанныхвФоне " + БуферОтправкаДанныхвФоне.toString() );
                    ////TODO  ОТВЕТ ОТ СЕРВЕРА ПОСЛЕ ОТПРАВКИ ДАННЫХ НА СЕРВЕР
                    if (БуферОтправкаДанныхвФоне != null) {
                        if (БуферОтправкаДанныхвФоне.length() > 0) {
                            Log.d(this.getClass().getName(), "  БуферОтправкаДанныхвФоне.toString()  " + БуферОтправкаДанныхвФоне.toString());
                            ДанныеПришёлВОтветОтМетодаPOST = БуферОтправкаДанныхвФоне.toString();
                            Log.d(this.getClass().getName(), "  ДанныеПришёлВОтветОтМетодаPOST  " + ДанныеПришёлВОтветОтМетодаPOST);

                            ////todo дОПОЛНИТЕЛЬНЫЙ КОД ПОСИКА ДВННЫХ ИЗ ОТВЕТА ОТ СЕРВЕРА
                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера =
                                    МетодАнализОтветаОтСервера(
                                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера, БуферОтправкаДанныхвФоне);
                        }
                        ////TODO ответ от сервера РЕЗУЛЬТАТ
                        Log.d(this.getClass().getName(), "Успешный Ответ от сервера ДанныеПришёлВОтветОтМетодаPOST в фоне " + ДанныеПришёлВОтветОтМетодаPOST+"" +
                                " РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера " +РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера);
                        if (ДанныеПришёлВОтветОтМетодаPOST.length() > 5) {
                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера ;//TODO ДанныеПришёлВОтветОтМетодаPOST.length();

                            Log.d(this.getClass().getName(), " СЛУЖБА УСПЕШНЫЙ ОТВКЕТ ОТ СЕРВЕРА ОТВЕТ CALBACKS  ДанныеПришёлВОтветОтМетодаPOST.length()  "
                                    + ДанныеПришёлВОтветОтМетодаPOST.length() + " ДанныеПришёлВОтветОтМетодаPOST " + ДанныеПришёлВОтветОтМетодаPOST.toString()+
                                    " РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера " +РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера);
                        } else {
                            Log.d(this.getClass().getName(), " NULL НОЛЬ ОБНОВЛЕНИЙ ИЛИ ВСТАВОК С СЕРВЕРА  СЛУЖБА УСПЕШНЫЙ ОТВКЕТ ОТ СЕРВЕРА ОТВЕТ CALBACKS  ДанныеПришёлВОтветОтМетодаPOST.length() ");
                        }
                    } else {
                        Log.d(this.getClass().getName(), " Данных нет c сервера  БуферОтправкаДанных.length() в фоне " + БуферОтправкаДанныхвФоне.length());
                    }
                    Log.d(this.getClass().getName(), " ДанныеПришёлВОтветОтМетодаPOST " + ДанныеПришёлВОтветОтМетодаPOST);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера;
            }




            //TODO get max versrsion data server

            @NonNull
            private Integer МетодАнализОтветаОтСервера
                    (Integer РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера, StringBuffer БуферОтправкаДанныхвФоне) {
                try{
                    String ПолучениееыкОтветыОтСервераSQlServerАнализ= БуферОтправкаДанныхвФоне.toString();
                    StringBuffer stringBufferРезульата;
                    ArrayList<Integer> ФинальныСписокЦифр=new ArrayList();
                    String[] words = ПолучениееыкОтветыОтСервераSQlServerАнализ.split("таблица");
                    for (String word : words) {
                        System.out.println(word);
                        Integer ЕслиТакойПоискаОригинальноВерсииДанныхОтСервера = word.indexOf(":::");
                        if (ЕслиТакойПоискаОригинальноВерсииДанныхОтСервера>0) {
                            //TODO
                            Integer КонецПоискаОригинальноВерсииДанныхОтСервера = word.lastIndexOf(":::")+3;
                            stringBufferРезульата=new StringBuffer();
                            stringBufferРезульата.append(word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, word.length()).replace(" ","")  );
                            ФинальныСписокЦифр.add(Integer.parseInt(stringBufferРезульата.toString()));
                            ////TODO ответ от сервера РЕЗУЛЬТАТ
                            Log.d(this.getClass().getName(), " word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, КонецПоискаОригинальноВерсииДанныхОтСервера) "
                                    +  word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, КонецПоискаОригинальноВерсииДанныхОтСервера));
                        }
                    }
                    РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = ФинальныСписокЦифр
                            .stream()
                            .mapToInt(v -> v)
                            .max().orElse(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера;
            }

        }
    }


}