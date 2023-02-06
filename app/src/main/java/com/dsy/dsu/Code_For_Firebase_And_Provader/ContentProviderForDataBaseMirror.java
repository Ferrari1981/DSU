package com.dsy.dsu.Code_For_Firebase_And_Provader;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.CursorLoader;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassCreatingMainAllTables;
import com.google.android.gms.common.util.ArrayUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ContentProviderForDataBaseMirror extends ContentProvider {
  private   UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;
    private SQLiteDatabase Create_Database_СамаБАзаSQLite;
    private  PUBLIC_CONTENT public_contentМенеджерПотоковМассвойОперацииВставки;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ТекущаяСтрокаПриДОбавлениииURL=0;
    private SharedPreferences preferences;
    public ContentProviderForDataBaseMirror() throws InterruptedException {
        try{

        CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда=
                new SubClassCreatingMainAllTables(getContext()).
                        МетодТОлькоЗаполенияНазваниямиТаблицДляОмена(getContext());
        Log.d(this.getClass().getName(), " ИменаТаблицыОтАндройда "+ИменаТаблицыОтАндройда );
     uriMatcherДЛяПровайдераКонтентБазаДанных=new UriMatcher(ИменаТаблицыОтАндройда.size());
            ИменаТаблицыОтАндройда.forEach(new Stream.Builder() {
         @Override
         public void accept(Object ЭлементТаблица) {
             uriMatcherДЛяПровайдераКонтентБазаДанных.addURI("com.dsy.dsu.providerdatabasemirror",ЭлементТаблица.toString(),ТекущаяСтрокаПриДОбавлениииURL);
             Log.d(this.getClass().getName(), " ЭлементТаблица "+ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " +ТекущаяСтрокаПриДОбавлениииURL);
             ТекущаяСтрокаПриДОбавлениииURL++;
         }
         @Override
         public Stream.Builder add(Object o) {
             return Stream.Builder.super.add(o);
         }

         @Override
         public Stream build() {
             return null;
         }
     });
        Log.d(this.getClass().getName(),  " uriMatcherДЛяПровайдераКонтентБазаДанных" +uriMatcherДЛяПровайдераКонтентБазаДанных );


        handler=          new Handler(Looper.getMainLooper(),new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull android.os.Message  msg) {
                try{
                    Log.d(this.getClass().getName(), " msg  "+msg);
                    Bundle bundle=        msg.getData();
                    Log.d(this.getClass().getName(), " bundle  "+bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
                    ///метод запись ошибок в таблицу
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
        // TODO: 04.10.2022
    } catch (Exception e) {
        e.printStackTrace();
        Create_Database_СамаБАзаSQLite.endTransaction();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
    public boolean onCreate() {
        try {
        if (Create_Database_СамаБАзаSQLite==null) {
            Log.w(this.getClass().getName(), "Create_Database_СамаБАзаSQLite " + Create_Database_СамаБАзаSQLite);
            Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазу();
            Log.w(this.getClass().getName(), "Create_Database_СамаБАзаSQLite " + Create_Database_СамаБАзаSQLite + " getContext()) " +getContext());
            preferences =getContext(). getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Create_Database_СамаБАзаSQLite.endTransaction();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        if (Create_Database_СамаБАзаSQLite!=null) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдалениеСтатуса=0;
        try{
          //  Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазу();
        if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
            Create_Database_СамаБАзаSQLite.beginTransaction();
        }
        Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  = Create_Database_СамаБАзаSQLite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
               String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                if (РезультатУдаления> 0) {
                    if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                        Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                        getContext().getContentResolver().notifyChange(uri, null);
                        // TODO: 22.09.2022 увеличивает версию данных
                    }
                }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                Create_Database_СамаБАзаSQLite.endTransaction();
            }
    } catch (Exception e) {
        e.printStackTrace();
        Create_Database_СамаБАзаSQLite.endTransaction();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
      return РезультатУдалениеСтатуса;
    }

    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try{
        Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://com.dsy.dsu.providerdatabasemirror/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
    } catch (Exception e) {
        e.printStackTrace();
        Create_Database_СамаБАзаSQLite.endTransaction();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri  ОтветВставкиДанных = null;
        try {
            //Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазу();
            if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
                Create_Database_СамаБАзаSQLite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);


            Long   РезультатВставкиДанных  = Create_Database_СамаБАзаSQLite.insertOrThrow(table, null, values);
            // TODO: 30.10.2021
            Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////

            ОтветВставкиДанных  = Uri.parse("content://"+РезультатВставкиДанных.toString());
            if (РезультатВставкиДанных> 0) {
                if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                    Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                    // TODO: 22.09.2022 увеличивает версию данных
                }
            }
            if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                Create_Database_СамаБАзаSQLite.endTransaction();
            }
            // TODO: 30.10.2021
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
            Create_Database_СамаБАзаSQLite.endTransaction();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОтветВставкиДанных;
    }






    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        Cursor cursor=null;
       try{
           asyncTaskLoader=new AsyncTaskLoader<Object>(getContext()) {
               @Nullable
               @Override
               public Object loadInBackground() {
                   Cursor cursor=null;
        //   Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазу();
           if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
               Create_Database_СамаБАзаSQLite.beginTransaction();
           }
        Integer  ПубличныйIDДляФрагмента=queryArgs.getInt("ПубличныйIDДляФрагмента",0);
        Integer  ТекущаяЦФО=queryArgs.getInt("ТекущаяЦФО",0);
        Integer  ТекущаяНомерМатериала=queryArgs.getInt("ТекущаяНомерМатериала",0);
        String  Таблица=queryArgs.getString("Таблица","");
        String  ФлагКакиеДанныеНужныПолучениеМатериалов=queryArgs.getString("ФлагКакиеДанныеНужныПолучениеМатериалов","");
           SQLiteQueryBuilder          SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder();
           SQLBuilder_Для_GRUD_Операций.setTables(Таблица);
           // TODO: 07.11.2022  данные курсор для групировки
           switch (ФлагКакиеДанныеНужныПолучениеМатериалов.trim()) {
               case "ПолучениеНомерМатериала":
               cursor=     SQLBuilder_Для_GRUD_Операций.query(Create_Database_СамаБАзаSQLite,new String[]{"*"},
                       null,null
                       ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО, null,null);
               break;
               case "ПолучениеСгрупированныеСамиДанные":
                   cursor=     SQLBuilder_Для_GRUD_Операций.query(Create_Database_СамаБАзаSQLite,new String[]{"*"},
                           null,null
                           ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО+" AND nomenvesov_zifra="+ТекущаяНомерМатериала , null,null);
                   break;
           }

           Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
           if (Create_Database_СамаБАзаSQLite.inTransaction()) {
               Create_Database_СамаБАзаSQLite.endTransaction();
           }
           commitContentChanged();
                   return cursor;
               }
           };
           asyncTaskLoader.startLoading();
           cursor= (Cursor) asyncTaskLoader.loadInBackground();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursor;
    }







    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(authority, operations);
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        return super.call(authority, method, arg, extras);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
     //   Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазу();
        Log.w(this.getClass().getName(), "  КОНТЕНТ ПРОВАЙДЕР update  uri " +uri + " getContext()) " +getContext());
        return Integer.parseInt("1");
    }

    // TODO: 22.11.2022  UPDATE
// TODO: 22.11.2022  UPDATE
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        ArrayList<Integer> РезультатОперацииBurkUPDATE = new ArrayList<>();
        try {
            LinkedBlockingQueue<ContentValues>      ДанныеДляВторогоЭтапаBulkINSERT=new LinkedBlockingQueue<ContentValues>(Arrays.asList(values));
            String     table = МетодОпределяемТаблицу(uri);
            String ФлагКакойСинхронизацияПерваяИлиНет=         preferences.getString("РежимЗапускаСинхронизации", "");
            if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                    table.equalsIgnoreCase("settings_tabels") ||  table.equalsIgnoreCase("view_onesignal") ) {
                      if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
                    Create_Database_СамаБАзаSQLite.beginTransaction();
                }
                Log.w(this.getClass().getName(), "count  bulkInsert  values.length "
                        + values.length+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                        " FUTURE FUTURE SIZE  Task "+"\n"+
                        "  isParallel isParallel isParallel" );
                // TODO: 01.12.2022 тест код UPDATE
                // TODO: 08.12.2022 UPDATE
                Flowable.fromArray(values)
                        .onBackpressureBuffer(true)
                        .filter(filter->filter.size()>0)
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).
                                        МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .doOnNext(new Consumer<ContentValues>() {
                            @Override
                            public void accept(ContentValues contentValuesInsert) throws Throwable {
                                try{
                                     String СтолбикСравнения="uuid";
                                    Integer     ОперацияUPDATE=0;
                                    Object UUID= contentValuesInsert.get(СтолбикСравнения);
                                    System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);
                                        ОперацияUPDATE  = Create_Database_СамаБАзаSQLite.update(table, contentValuesInsert,     СтолбикСравнения +"=?"
                                                ,new String[]{(String) UUID});
                                    Log.w(this.getClass().getName(), " Вставка массовая через contentValuesInsert  burkInsert   ОперацияUPDATE " +  ОперацияUPDATE);
                                    if (ОперацияUPDATE>0) {
                                        РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияUPDATE.toString()));
                                        // TODO: 24.11.2022  удаление с последующей вставкой
                                        ДанныеДляВторогоЭтапаBulkINSERT.remove(contentValuesInsert);
                                    }else{
                                           Cursor cursor=        Create_Database_СамаБАзаSQLite.rawQuery(" select "+
                                                    СтолбикСравнения+" from "+ table +" WHERE  "+СтолбикСравнения+" =?  ",new String[]{UUID.toString()});
                                        if (cursor!=null) {
                                            if ( cursor.getCount() > 0) {
                                                Log.d(this.getClass().getName(), "cursor.getCount()" + cursor.getCount());
                                                // TODO: 24.11.2022  удаление с последующей вставкой
                                                ДанныеДляВторогоЭтапаBulkINSERT.remove(contentValuesInsert);
                                            }
                                        }
                                        cursor.close();
                                    }//todo конец анализ
                                    Log.w(this.getClass().getName(), "count  bulkInsert  РезультатОперацииBurkUPDATE.size() "
                                            + РезультатОперацииBurkUPDATE.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                            " FUTURE FUTURE SIZE  Task "+"\n"+
                                            "  isParallel isParallel isParallel" + " ДанныеДляВторогоЭтапаBulkINSERT ДанныеДляВторогоЭтапаBulkINSERT.size()  "
                                            + ДанныеДляВторогоЭтапаBulkINSERT.size() );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Create_Database_СамаБАзаSQLite.endTransaction();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                            Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
                                if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                                    Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                                    Create_Database_СамаБАзаSQLite.endTransaction();
                                }
                                // TODO: 07.12.2022 дополнительно повышаем версию данных внтури контерйнера
                                if(РезультатОперацииBurkUPDATE.size()>0){
                                    Integer РезультатПовышенииВерсииДанных =new Class_GRUD_SQL_Operations(getContext()) .new ClassRuntimeExeGRUDOpertions(getContext())
                                            .МетодУвеличиваемДанныхБазы(table,
                                                    "Серверный",new PUBLIC_CONTENT(getContext()).МенеджерПотоков,
                                                    "Анализ");
                                    Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                                }
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .blockingSubscribe();
            }
            // TODO: 19.01.2023  ВТОРАЯ ОПЕРАЦИЯ  INSERT ПОСЛЕ UPDATE
            // TODO: 10.11.2022 ПЕРЕХОД КО ВТОРОМУ МЕТОДУ ПРОВАЙДЕРУ INSERT
            ContentValues[] contentValuesТОлькоДЛяОпрацииInsert=new ContentValues[ДанныеДляВторогоЭтапаBulkINSERT.size()];
            contentValuesТОлькоДЛяОпрацииInsert=ДанныеДляВторогоЭтапаBulkINSERT.toArray(contentValuesТОлькоДЛяОпрацииInsert);
            // TODO: 24.11.2022 вторая операция посл обновля пытаемся вставить данные
             uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + table + "");
            ContentResolver contentResolver = getContext().getContentResolver();
            int РезультатВставкиМассовой = contentResolver.bulkInsert(uri, contentValuesТОлькоДЛяОпрацииInsert);
            РезультатОперацииBurkUPDATE.add(РезультатВставкиМассовой);
            // TODO: 08.12.2022 финальный результат двух операций
            Integer ФинальныйРезультаUpdateAndInsert=  РезультатОперацииBurkUPDATE.stream().reduce(0, (a, b) -> a + b);
            // TODO: 10.11.2022 получаем ответ данные
            Log.w(this.getClass().getName(), " BULK insert updaet ibsetyРезультатВнутренаяbulkЗеркало.size()" + РезультатОперацииBurkUPDATE.size());
            Log.w(this.getClass().getName(), " BULK insert updaet ФинальныйРезультаUpdateAndInsert"
                    +ФинальныйРезультаUpdateAndInsert);
        } catch (Exception e) {
            e.printStackTrace();
            Create_Database_СамаБАзаSQLite.endTransaction();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return    РезультатОперацииBurkUPDATE.stream().reduce(0, (a, b) -> a + b);
    }



}

