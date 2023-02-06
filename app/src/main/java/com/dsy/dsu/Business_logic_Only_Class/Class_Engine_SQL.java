/*
package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;
import com.dsy.dsu.R;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


;


//////-------TODO  ЭТО ПЕРВЫЙ КОНТРОЛЛЕР КОТОРЫЙ ВИЗУАЛИЗИРУЕТ СИНХРОНИЗАЦИЮ С ПРОГРАССБАРОМ---------------------------------------------------------------


///TODO------------------------------------------------------ ЭТО  ВТОРОЙ КОНТРОЛЛЕР КОТОРЫЙ ЗАПУСКАЕТ СИНХРОНИЗАЦИЮ В ФОНЕ  (ВНУТРИ ПРИЛОЖЕНИЕ)---------------------------------------------------


public class Class_Engine_SQL extends Class_MODEL_synchronized {
    // TODO: 28.07.2022  переменые
    public    Context context;
    private    Activity activity;
    private    Integer ПубличныйРезультатОтветаОтСерврераУспешно=0;
    private  Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID;
    private  Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаUUIDобваID;
    private  Integer ГлавноеКоличестовТаблицОбрабатываемВГлавномЦиклеОбмена;
    private  Float ФиналПроценты = 0f;
    private int УспешноеКоличествоВставокДанныхсСервера;
    private int УспешноеКоличествоОбновлениеДанныхсСервера;
    private  CopyOnWriteArrayList<String> ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат=new CopyOnWriteArrayList();
    private  SQLiteDatabase  Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы=null;
    private  PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации;
    private   String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал=new String();
    private boolean ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА = false;
    private  String ФлагКакуюЧастьСинхронизацииЗапускаем =new String();
    private  Long РезультатВерсииДанныхЧатаНаСервере=0l;
    private   ContentValues АдаптерПриОбновленияДанныхсСервера=null;
    private ContentValues   АдаптерДляВставкиДанныхсСервер=null;
    private ContentValues[] АдаптерДляМассовойВставкиДанныхсСервер=null;
    private Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки=0;
    private  Integer ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы=0;
    private ProgressBar progressBar3ГоризонтальныйСинхронизации;
    private      TextView   ТекущаяТаблицыИлиГод;
    private   Long ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде=0l;
    private Handler handler;
    // TODO: 28.07.2022
    public Class_Engine_SQL(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        super(context);
        this.context=context;
        public_contentДатыДляГлавныхТаблицСинхронизации=new PUBLIC_CONTENT(context);
        Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы=new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу();
        Log.w(context.getClass().getName(), "Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы" + Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
    }

    // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
    public Integer МетодЗАпускаФоновойСинхронизации(@NotNull Context context,
                                                    String ФлагКакуюЧастьСинхронизацииЗапускаемВнутри,
                                                    boolean ФлагКакуюЧастьСинхронизацииЗапускаем,
                                                    @Nullable Activity activity,
                                                    CopyOnWriteArrayList ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат,
                                                    String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал,
                                                    Integer СколькоСтрочекJSON) throws InterruptedException {
        Integer      РезультатаСинхронизации = 0;
        try{
            this.  context = context;
            this.activity =activity;
             this.   ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат=ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат;
            // TODO: 28.09.2022 запускам синхрогниазцию
            РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации(КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал,
                    ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат,СколькоСтрочекJSON);
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

    Integer МетодСамогоФоновойСинхронизации(String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная,
                                            CopyOnWriteArrayList ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат,Integer СколькоСтрочекJSONВнутри) {
        String ТекущаяТаблицаДляОБменаДанными = null;
        Integer ФинальныйРезультаФоновойСинхрониазции=0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsМетодСамогоФоновойСинхронизации=new Class_GRUD_SQL_Operations(context);
        try {
            ГлавноеКоличестовТаблицОбрабатываемВГлавномЦиклеОбмена =  СколькоСтрочекJSONВнутри;
            Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Synchronizasiy_Data " + new Date() +
                    "\n" + " Build.BRAND " + Build.BRAND.toString()+"  СколькоСтрочекJSONВнутри " +СколькоСтрочекJSONВнутри+ " СколькоСтрочекJSON " + ГлавноеКоличестовТаблицОбрабатываемВГлавномЦиклеОбмена);
            //////TODO ШАГ ТРЕТИЙ  ЗАПУСКАЕМ САМУ СИНХРОНИЗАЦИЮ  сама синхронизация в фоне
         ФинальныйРезультаФоновойСинхрониазции =            МетодНачалоСихронизациивФоне(context,КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная); ////Получение Версии Данных Сервера для дальнейшего анализа

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

    Integer МетодНачалоСихронизациивФоне(@NotNull Context context, String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная) throws InterruptedException, ExecutionException, TimeoutException, JSONException {
        Integer результатСинхрониазции=0;
        try {
            результатСинхрониазции=     МетодПолучениеIDотСервераДляГеренированиеUUID(КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная); ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
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
    Integer МетодПолучениеIDотСервераДляГеренированиеUUID(String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная) throws JSONException, InterruptedException, ExecutionException, TimeoutException {
        String ДанныеПришёлЛиIDДЛяГенерацииUUID = new String();
        Integer РезультатСинхрониазции=0;
        ПубличноеIDПолученныйИзСервлетаДляUUID=0;
        try {
            Log.d(this.getClass().getName(), " public   void МетодПолучениеIDОтСервераДляГеренированиеUUID ()" + " ДанныеПришёлЛиIDДЛяГенерацииUUID " + ДанныеПришёлЛиIDДЛяГенерацииUUID +
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
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,public_contentменеджер.МенеджерПотоков,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
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
                РезультатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID),
                        КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная);//получаем ID для генерирования UUID
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
    Integer МетодПолучениеСпискаТаблицДляОбменаДанными( String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                        String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная)
            throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ

        Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID" + ДанныеПришёлЛиIDДЛяГенерацииUUID);
        String ДанныеПришлаСпискаТаблицДляОбмена = new String();
        StringBuffer БуферПолученияСпискаТАблицДляОбмена = new StringBuffer();
        Integer РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=0;
        try {
            PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
            БуферПолученияСпискаТАблицДляОбмена = УниверсальныйБуферПолучениеДанныхсСервера("view_data_modification",
                    "", "",
                    "application/gzip",//application/json
                    "Хотим Получить Версию Данных Сервера",
                    0l,
                    ДанныеПришёлЛиIDДЛяГенерацииUUID, 10000,
                    null,
                    0l,public_content.getАдресСервера() , public_content.getПортСервера());   //// БуферПолученнниеДанныхОтМетодаGET.mark(1000); // save the data we are about to readБуферПолученнниеДанныхОтМетодаGET.reset(); // jump back to the marked position
            Log.d(this.getClass().getName(), " БуферПолученияСпискаТАблицДляОбмена.toString().toCharArray().length " + БуферПолученияСпискаТАблицДляОбмена.toString().toCharArray().length);
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
                    public_contentДатыДляГлавныхТаблицСинхронизации.     ДатыТаблицыВерсииДанныхОтСервера=Collections.synchronizedMap(new LinkedHashMap<String, Long>());
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
                Class_Engine_SQL_SubClassMainLoopAsyncTables_КлассСГлавнымЦикломСинхрониазцииТАблиц
                        Class_Engine_SQL_subClassMainLoopAsyncTables_классСГлавнымЦикломСинхрониазцииТАблиц
                        =new Class_Engine_SQL_SubClassMainLoopAsyncTables_КлассСГлавнымЦикломСинхрониазцииТАблиц(context,
                        Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы
                        ,  public_contentДатыДляГлавныхТаблицСинхронизации, ГлавноеКоличестовТаблицОбрабатываемВГлавномЦиклеОбмена, activity);
                ////TODO ТОЛЬКО НЕ ДЛЯ АКТИВТИ АНОНИМНЫЙ ОБМЕН БЕЗ ВИЗУАЛИЗАЦИИ СИНХРОНИЗАЦИИ
                РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=
                        Class_Engine_SQL_subClassMainLoopAsyncTables_классСГлавнымЦикломСинхрониазцииТАблиц.
                                МетодГлавныхЦиклТаблицДляСинхронизации(ДанныеПришёлЛиIDДЛяГенерацииUUID, КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная);
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

    Integer МетодЗапускаСинхрониазцииПоАТблицам(String данныеПришёлЛиIDДЛяГенерацииUUID,  String текущаяТаблицаДляОБменаДанными,String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная,
                                                CompletionService МенеджерПотоковВнутрений,
                                                Integer СколькоСтрочекJSON ,
                                                PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации,Activity ActivityДляСинхронизацииОбмена) {



        Log.d(this.getClass().getName(), " ТекущаяТаблицаДляОБменаДанными " + текущаяТаблицаДляОБменаДанными);

        boolean ОтветЕслиТакаяТаблицаВнутриОбработкиДляПринятияРешениеНачинатьОбрабткуИлиНет = false;
        //todo sleeep
        Integer   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;



        try {


            ///TODO сон
            РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
            //////TODO метод обрабтки п таюлицам
            РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=
                    МетодДляАнализаВерсийДанныхПолучаемДатыСервера(текущаяТаблицаДляОБменаДанными, данныеПришёлЛиIDДЛяГенерацииUUID,
                            КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная,
                            МенеджерПотоковВнутрений,СколькоСтрочекJSON,public_contentДатыДляГлавныхТаблицСинхронизации,
                            ActivityДляСинхронизацииОбмена); ////Получение Версии Данных Сервера для дальнейшего анализа
            ///todo публикум название таблицы или цифру его
            ///TODO увеличиваем таюлицу оработки
            if(РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера==null){
                ////
                РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
            }


            Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера "
                    + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера);




            // TODO: 12.08.2021 код повышает или уменьшает верисю данных ПОСЛЕ ОБРАБОТКИ ТАБЛИЦЫ И ДАТЫ



            /////////////////
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


// TODO: 23.05.2021


        return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера;
    }

























    // TODO: 12.08.2021  метода повышает ВЕРСИЮ ДАННЫ Х ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ТАБЛИЦЫ


    Integer МетодВерсияДанныхМеняемВнутриЦиклаТаблицСинхронизации(
            @NotNull Object ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера,
            @NotNull  String текущаяТаблицаДляОБменаДанными
            ,String  РежимПовышенияВерсииЛокальнаяСервернаяИлиОба
            ,Long РезультатВерсииДанныхЧатаНаСервере,
            CompletionService МенеджерПотоковВнутрений) {
        Integer    Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;
        try{
            Long   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ=
                    Long.parseLong(String.valueOf(ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера));
            Log.i(this.getClass().getName(), "   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ"
                    +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ  +"  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере );
///todo ТО ЧТО ПРОШЛА КАКАЯ ТО  ОПЕРАЦИЯ УСПЕШНО ПО ТАБЛИЦ
            if (ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ>0  &&  РезультатВерсииДанныхЧатаНаСервере>0) {
                Log.i(this.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервераПовышаемДатыИВерсииДанных"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера+
                        " ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера );
                Log.i(context.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        + ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера +  "  текущаяТаблицаДляОБменаДанными "+  текущаяТаблицаДляОБменаДанными+
                        "  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере);
                Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнных;
                classGrudSqlOperationsПовышаемВерсиюДАнных=new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",текущаяТаблицаДляОБменаДанными.trim());
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РежимПовышенияВерсииЛокальнаяСервернаяИлиОба.trim());///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ДополнительныйФлагДляСинхЧАТАТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РезультатВерсииДанныхЧатаНаСервере);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы= (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнных.
                        new ChangesVesionData(context).
                        МетодВыравниваемДанныеВТаблицеModificationClient(classGrudSqlOperationsПовышаемВерсиюДАнных.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
                Log.d(context.getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации " +Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы );
                // TODO: 11.08.2021  доаолнительные запись ДАННЫХ СЕРВРЕА ПОСЛЕ УСТАВКИ НОВЫХ ДАННЫХ С СЕРВРЕА
            }
            if(Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы==null){
                ////
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы;

    }
    // TODO: 12.08.2021  метода повышает ВЕРСИЮ ДАННЫ Х ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ТАБЛИЦЫ







    Integer МетодПовышаемВерсиюПослеОчисткиДанных(
            @NotNull Object ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера,
            @NotNull  String текущаяТаблицаДляОБменаДанными
            ,String  РежимПовышенияВерсииЛокальнаяСервернаяИлиОба
            ,Long РезультатВерсииДанныхЧатаНаСервере,
            CompletionService МенеджерПотоковВнутрений) {

        Integer    Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;


        try{
            // TODO: 05.09.2021

            Long   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ=
                    Long.parseLong(String.valueOf(ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера));



            Log.i(this.getClass().getName(), "   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ"
                    +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ  +"  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере );

///todo ТО ЧТО ПРОШЛА КАКАЯ ТО  ОПЕРАЦИЯ УСПЕШНО ПО ТАБЛИЦЕ


            if (ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ>0 ) {


                Log.i(this.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервераПовышаемДатыИВерсииДанных"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера+
                        " ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера );


                Log.i(context.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        + ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера +  "  текущаяТаблицаДляОБменаДанными "+  текущаяТаблицаДляОБменаДанными+
                        "  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере);


                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнных;
                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                //////
                classGrudSqlOperationsПовышаемВерсиюДАнных=new Class_GRUD_SQL_Operations(context);
                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",текущаяТаблицаДляОБменаДанными.trim());
                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РежимПовышенияВерсииЛокальнаяСервернаяИлиОба.trim());///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                ///



                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ДополнительныйФлагДляСинхЧАТАТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РезультатВерсииДанныхЧатаНаСервере);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                ///



                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы= (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнных.
                        new ChangesVesionData(context).
                        МетодВыравниваемДанныеВТаблицеModificationClient(classGrudSqlOperationsПовышаемВерсиюДАнных.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
//

                Log.d(context.getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации " +Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы );
                ////

                // TODO: 03.09.2021



                ///todo  конец  ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ


                Log.w(context.getClass().getName(), "   Результат_ПриписиИзменнийВерсииДанныхВФоне:"
                        + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы + " ТекущаяТаблицаДляОБменаДанными " + текущаяТаблицаДляОБменаДанными +
                        " Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы " + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы);


                // TODO: 11.08.2021  доаолнительные запись ДАННЫХ СЕРВРЕА ПОСЛЕ УСТАВКИ НОВЫХ ДАННЫХ С СЕРВРЕА
            }


            if(Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы==null){
                ////
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;
            }



// TODO: 15.10.2021    Увеличиваем обработунаю таюдиц дял отоброжения в прогресс баре если только таблица была успешно обработана

             */
/*    if(Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы>0){

                     ИндексТекущейОперацииJSONДляВизуальнойОбработки++;

                     Log.w(contextСозданиеБАзы.getClass().getName(), "   ИндексТекущейОперацииJSONДляВизуальнойОбработки:"
                             + ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                 }
*//*





            ///TODO конец повышение версиии
            /////////////////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы;

    }

    // TODO: 12.08.2021  МЕТОД ПРОСТОГО ПОВЫШЕНИЯ ВЕРСИИ ДАННЫХ В ПРОЕКТЕ


    public Integer МетодПовышаемВерсиюДанныхПроектавТаблицеMODIFITATION_Client(
            @NotNull Object ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера,
            @NotNull String текущаяТаблицаДляОБменаДанными
            , String РежимПовышенияВерсииЛокальнаяСервернаяИлиОба
            , Long ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать,
            CompletionService МенеджерПотоковВнутрений) {

        Integer    Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;


        try{
            // TODO: 05.09.2021

            Long   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ=
                    Long.parseLong(String.valueOf(ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера));



            Log.i(this.getClass().getName(), "   ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ"
                    +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ  +"  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере +
                    "  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать " + ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);

///todo ТО ЧТО ПРОШЛА КАКАЯ ТО  ОПЕРАЦИЯ УСПЕШНО ПО ТАБЛИЦЕ


            if (ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССерверафИНАЛ>0  &&  ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать>0) {


                Log.i(this.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервераПовышаемДатыИВерсииДанных"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера+
                        " ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        +ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера );


                Log.i(context.getClass().getName(), "   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера"
                        + ВходящийПараметрПослеУспешнойВсатвкиИлиобвнлденияДаннымиССервера +  "  текущаяТаблицаДляОБменаДанными "+  текущаяТаблицаДляОБменаДанными+
                        "  РезультатВерсииДанныхЧатаНаСервере " +РезультатВерсииДанныхЧатаНаСервере);


                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнных;
                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                //////
                classGrudSqlOperationsПовышаемВерсиюДАнных=new Class_GRUD_SQL_Operations(context);
                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",текущаяТаблицаДляОБменаДанными.trim());
                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РежимПовышенияВерсииЛокальнаяСервернаяИлиОба.trim());///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                ///


                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба",
                                РежимПовышенияВерсииЛокальнаяСервернаяИлиОба);

                ///
                classGrudSqlOperationsПовышаемВерсиюДАнных.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",
                                ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать);



                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы= (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнных.
                        new ChangesVesionData(context).
                        changesvesiondata(classGrudSqlOperationsПовышаемВерсиюДАнных.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
//

                Log.d(context.getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации "
                        +Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы );
                ////

                // TODO: 03.09.2021



                ///todo  конец  ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ


                Log.w(context.getClass().getName(), "   Результат_ПриписиИзменнийВерсииДанныхВФоне:"
                        + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы + " ТекущаяТаблицаДляОБменаДанными " + текущаяТаблицаДляОБменаДанными +
                        " Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы " + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы);


                // TODO: 11.08.2021  доаолнительные запись ДАННЫХ СЕРВРЕА ПОСЛЕ УСТАВКИ НОВЫХ ДАННЫХ С СЕРВРЕА
            }


            if(Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы==null){
                ////
                Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=0;
            }



// TODO: 15.10.2021    Увеличиваем обработунаю таюдиц дял отоброжения в прогресс баре если только таблица была успешно обработана

             */
/*    if(Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы>0){

                     ИндексТекущейОперацииJSONДляВизуальнойОбработки++;

                     Log.w(contextСозданиеБАзы.getClass().getName(), "   ИндексТекущейОперацииJSONДляВизуальнойОбработки:"
                             + ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                 }
*//*





            ///TODO конец повышение версиии
            /////////////////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы;

    }








































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
        */
/*            class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля*//*


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
                    new GetData(context).getdata(class_grud_sql_operationsВЫчисляемВсеТаблицыВнутриКлинета. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

            Log.d(this.getClass().getName(), "GetData   " +КурсорВсехТаблицВнутри );


*/
/*

            // TODO: 06.09.2021  _old

            Cursor КурсорВсехТаблицВнутри = ССылкаНаСозданнуюБазу.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'", null);






*//*


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
    Integer МетодДляАнализаВерсийДанныхПолучаемДатыСервера(String ТекущаяТаблицаДляОБменаДанными,
                                                           String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                           @NotNull String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная,
                                                           CompletionService МенеджерПотоковВнутрений,
                                                           Integer СколькоСтрочекJSON,
                                                           PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации
            ,Activity ActivityДляСинхронизацииОбмена)
            throws JSONException, InterruptedException, ExecutionException, TimeoutException {
        //
        final Integer[] РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера = {0};
        //
        КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал=КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная;

        if (КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал.length()==0) {
            ///

            КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал="ПовторныйЗапускСинхронизации";
        }


///TODO принудительно устанвливаем редим работы синхронизации
        Log.d(this.getClass().getName(), " КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал "+КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);


        Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID  " + ДанныеПришёлЛиIDДЛяГенерацииUUID + " ТекущаяТаблицаДляОБменаДанными "
                + ТекущаяТаблицаДляОБменаДанными +
                " public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера " +public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера);


        try {
/////ТУТ -- КОД АНАЛИЗА ДАННЫХ SQL SERVER  ПРИШЕДШЕЙ ТЕКУЩЕЙ ТАБЛИЦЕ ПОЛУЧАЕМ НАЗВАНИЕ БАЗЫ И К НЕЙ ПОЛУЧАЕМ ДАТУ Е НЕЙ
            Observable observableшДляАнализаТекущейТаблицыВерсииДанных=Observable.fromIterable(public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.entrySet())
                    .subscribeOn(Schedulers.trampoline())
                    .flatMapStream(new Function<Map.Entry<String, Long>, Stream<?>>() {
                        @Override
                        public Stream<?> apply(Map.Entry<String, Long> ХэшДляАнализаТекущейТаблицыВерсииДанных) throws Throwable {
                            // TODO: 15.02.2022
                            Long Полученная_ВерсияДанныхсSqlServer = 0l;
                            JSONObject ОбьектыJSONФайлJSONсСервераВерсияSQlserver = new JSONObject();
                            String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = "";
                            String ИмитацияВремяДляПроверки;
                            Date ИмитациДатыДляПроверки = null;
                            ///////
                            String ТесктДатыSqlServer = null;
                            //////
                            System.out.println(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey() + " - " + ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                            if (ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey().equalsIgnoreCase(ТекущаяТаблицаДляОБменаДанными)) {///ищем в текущей строчке текущуе название таблицы например CFO==CFO
                                ////записываем в json  получену.юю текущаю названеи табиуви к ней дата ВЕРСИЯ ДАННЫ
                                ОбьектыJSONФайлJSONсСервераВерсияSQlserver.put(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey(), ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                                ////
                                Log.d(this.getClass().getName(), " ОбьектыJSONФайлJSONсСервераВерсияSQlserver " + ОбьектыJSONФайлJSONсСервераВерсияSQlserver.toString());
                                ////////
                                // TODO: 15.02.2022
                                ////ПЕРЕРДАЕМ ИМЯ ТАБЛИЦЫ ОТ SQL SERVER
                                ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey();
                                // TODO: 15.02.2022
                                Log.d(this.getClass().getName(), " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных" + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                                // TODO: 15.02.2022
                                //// ПЕРЕДАЕМ ДАТУ ИЗ ТАБЛИЦЫ ОТ SQL SERVER
                                Полученная_ВерсияДанныхсSqlServer = ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue();
                                // TODO: 15.02.2022
                                if(Полученная_ВерсияДанныхсSqlServer==null){
                                    // TODO: 15.02.2022  Полученная_ВерсияДанныхсSqlServer
                                    Полученная_ВерсияДанныхсSqlServer=0l;
                                }
                                Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer   " + Полученная_ВерсияДанныхсSqlServer);
                                ////////
                                // TODO: 05.10.2021
                                Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                        "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                                /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
                                РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] =
                                        МетодДляВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером(ОбьектыJSONФайлJSONсСервераВерсияSQlserver,
                                                Полученная_ВерсияДанныхсSqlServer, ТекущаяТаблицаДляОБменаДанными,
                                                ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                МенеджерПотоковВнутрений,
                                                СколькоСтрочекJSON
                                                ,ActivityДляСинхронизацииОбмена);////метод получение даты версии данных из андройда
                                //
                                Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                        "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                                // TODO: 05.10.2021 ЕСЛИ ЛОКАЛЬНАЯ ТАБЛИЦА РАВНА С ТАБЛИЦЕЙ С ССЕРВЕРА ПО НАЗВАНИЮ

                            }
                            return Observable.fromArray(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey()).blockingStream().filter(РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри->
                                            РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри.equalsIgnoreCase(ТекущаяТаблицаДляОБменаДанными))
                                    .peek(РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри
                                            ->System.out.println(РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри));
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "   doOnError  ОШИБКА В  цикле ublic_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.entrySet() "
                                    + throwable.getStackTrace().toString());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "   onErrorComplete  ОШИБКА В  цикле ublic_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.entrySet() "
                                    + throwable.getStackTrace().toString());
                            return false;
                        }
                    });
            // TODO: 15.02.2022
// TODO: 14.01.2022 подписка на данные гланого цикла
            observableшДляАнализаТекущейТаблицыВерсииДанных.blockingSubscribe(System.out::println);

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
        return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0];
    }










    /////////////////////TODO метод ВЫРАВНИВАНИЯ ТАБЛИЦ МЕЖДУ КЛИЕНТОМ И СЕРВЕРОМ КОЛИЧЕСТВО ТАБЛИЦ ДОЛЖНО БЫТЬ ОДИНАКОВЫМ
    Integer МетодДляВырвниванияНазванийТаблицВВерсияДанныхНаКлиентеСсервером(JSONObject ФайлJSONcВерсиейДанныхСервера, Long Полученная_ВерсияДанныхсSqlServer,
                                                                             String ИмяТаблицыОтАндройда_Локальноая,
                                                                             String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                                             String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                             CompletionService МенеджерПотоковВнутрений,
                                                                             Integer ГавноеКоличестоОбработываемТаблицвОбменаНаддынхЦикле
            ,Activity  ActivityДляСинхронизацииОбмена)
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
            if (ActivityДляСинхронизацииОбмена!=null) {
                this.context =    ActivityДляСинхронизацииОбмена;
            }

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
                                МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
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
                            ActivityДляСинхронизацииОбмена,
                            МенеджерПотоковВнутрений,
                            Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы,
                            ГавноеКоличестоОбработываемТаблицвОбменаНаддынхЦикле,
                            0,
                            ФиналПроценты); ////false  не записывать изменениея в таблице модификавет версия
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



                //
                РезультатУспешнойВсатвкиИлиОбновлениясСервреа=0;
                //////////метод анализа данных
                РезультатУспешнойВсатвкиИлиОбновлениясСервреа=
                        МетодАнализаВресииДАнныхКлиента(ИмяТаблицыОтАндройда_Локальноая,
                                Полученная_ВерсияДанныхсSqlServer,
                                ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных
                                , ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                МенеджерПотоковВнутрений,ActivityДляСинхронизацииОбмена,ГавноеКоличестоОбработываемТаблицвОбменаНаддынхЦикле);



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
                                            CompletionService МенеджерПотоковВнутрений
            ,Activity ActivityДляСинхронизацииОбмена,Integer СколькоСтрочекJSON) {

        Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);

        SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = null;

        ////////////////

        Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная = 0l;

        Long ВерсииДанныхНаАндройдеСерверная = 0l;

        Integer РезультатУспешнойВсатвкиИлиОбвовлениясСервера=0;

        // String ДатаВерсииДанныхНаАндройдеЛокальногоОбновленияДляМетодаGET = null;


        Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаВресииДАнныхКлиента;


        try {

///
            class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);

            // TODO: 15.02.2022
            Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);

            ///
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
            ///////
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
            //
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","name=? ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);
            ///
*/
/*            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);*//*

            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            // class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsgetdata
                    .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

            Log.d(this.getClass().getName(), "GetData "+КурсорДляАнализаВерсииДанныхАндройда  );




            ///// todo
            // УДАЛЯЕМ ИЗ ПАМЯТИ  ОТРАБОТАННЫЙ АСИНАТСК
            /////////ВАЖНО ЕСЛИ БОЛЬШЕ НУЛ ЗНАЧИТ В АНДРОЙДЕ ТАБЛИЦА С ТАКИМ НАЗВАНИЕМ УЖЕ ЕСТЬ

            /////
            if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {////ВЫЖНОЕ УСЛОВИЕ ЕСЛИ КУРСОР ВЕРНУЛ БОЛЬШЕ НУЛЯ  ДАННАЕ ТОЛЬКО ТОГДА НАЧИНАЕМ АНАЛИЗ ВЕРСИИ ДАННЫХ НА АНДРОЙДЕ

                КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();

                Log.d(this.getClass().getName(), "  Курсор_УзнаемВерсиюБазыНаАдройде.getCount() " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                //////////////


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


                // TODO: 05.10.2021

                РезультатУспешнойВсатвкиИлиОбвовлениясСервера=0;
                ///////////////


                //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       МетодПринятияРешенияПолучитьДанныесСервераИлиОтправитьДанныесКлиента(
                        ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                        ВерсииДанныхНаАндройдеСерверная,
                        Полученная_ВерсияДанныхсSqlServer,
                        ИмяТаблицыОтАндройда_Локальноая,
                        ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                        ДанныеПришёлЛиIDДЛяГенерацииUUID,
                        МенеджерПотоковВнутрений
                        ,ActivityДляСинхронизацииОбмена
                        ,СколькоСтрочекJSON);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР

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
                                                                                 CompletionService МенеджерПотоковВнутрений
            ,Activity ActivityДляСинхронизацииОбмена
            ,Integer СколькоСтрочекJSON) {
        //
        Log.d(this.getClass().getName(), " ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная +
                " ВерсииДанныхНаАндройдеСерверная " + ВерсииДанныхНаАндройдеСерверная
                + " Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer+
                "  ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая+
                " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных " +ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных+
                "  ДанныеПришёлЛиIDДЛяГенерацииUUID " +ДанныеПришёлЛиIDДЛяГенерацииUUID
                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                "  ActivityДляСинхронизацииОбмена " +ActivityДляСинхронизацииОбмена+ " СколькоСтрочекJSON "+СколькоСтрочекJSON);
        // TODO: 05.10.2021
        Integer Результат_ПосылаемНа_Сервер=0;//РезультатУспешнойВставкиИлИОбвновленияССервера
        Integer Результат_СсервераПолучаем_Сервер=0;//РезультатУспешнойВставкиИлИОбвновленияССервера
        try {
            if (ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных.equalsIgnoreCase(ИмяТаблицыОтАндройда_Локальноая)) {//////ОБЯЗАТОЛЬНОЕ УСЛОВИЕ НАЗВАНИЕ ТАБЛИЦ ДОЛЖНО БЫТЬ ОДИНАКОВЫМ НАПРИМЕР  CFO==CFO
                Log.d(this.getClass().getName(), " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА"
                        + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА
                        + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 12.08.2021 СЕРВЕРАНАЯ ДАТА ЛОКАЛЬНАЯ
                Long    РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера =
                        МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "versionserveraandroid_version",
                                context, ИмяТаблицыОтАндройда_Локальноая);
                Log.d(this.getClass().getName(),
                        " РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера" + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);
                // TODO: 12.08.2021 ЛОКАЛЬНАЯ ДАТА ЛОКАЛЬНАЯ
                Long          РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее =
                        МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "localversionandroid_version",
                                context, ИмяТаблицыОтАндройда_Локальноая);
                Log.d(this.getClass().getName(),
                        " РезультаПолученаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее"
                                + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 05.10.2021  ПЕРЕМЕННЫЕ ТОЛЬКО ДЛЯ ВТОРОГО МЕТОДА ПОЛУЧЕНИЯ ДАННЫХ С СЕРВРЕА
                РезультатВерсииДанныхЧатаНаСервере = 0l;
                Log.d(this.getClass().getName(),
                        " Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer +
                                "  РезультатВерсииДанныхЧатаНаСервере " + РезультатВерсииДанныхЧатаНаСервере+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                РезультатВерсииДанныхЧатаНаСервере = Полученная_ВерсияДанныхсSqlServer;
                // TODO: 11.08.2021 НОВЫЙ ОБМЕН ДАННЫМИС СЕРВЕРРОМ НА ОСНОВЕ ВЕРСИЙ ДАННЫХ  ПОКА ТОЛЬКО ДЛЯ ЧАТА
                Log.d(this.getClass().getName(),
                        " РезультатВерсииДанныхЧатаНаСервере  " + РезультатВерсииДанныхЧатаНаСервере +
                                "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);

                // TODO: 05.10.2021  ПЕРВОЕ ДЕЙСТВИЕ  ДЕЙСТВИЕ ЕСЛИ НЕТ ОТПРАВЛЕНИЕ ВЫШЕ ДАННЫХ      // TODO: 05.10.2021  ПЕРВОЕ ДЕЙСТВИЕ  ДЕЙСТВИЕ ЕСЛИ НЕТ ОТПРАВЛЕНИЕ ВЫШЕ ДАННЫХ     // TODO: 05.10.2021  ПЕРВОЕ ДЕЙСТВИЕ  ДЕЙСТВИЕ ЕСЛИ НЕТ ОТПРАВЛЕНИЕ ВЫШЕ ДАННЫХ
                ////// TODO запуск отпарвки  ЛОКАЛЬНАЯ ПРОВЕРКА ВЕРСИИ ДАНННЫХ  #1 НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ПОСЛЕДНАЯЯ НАДО С АНДРОЙДА ПОСЛАТЬ ДАННЫЕ НА СЕРВЕР
                if (РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее > // TODO original РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее
                        РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                        && !ИмяТаблицыОтАндройда_Локальноая.matches("(.*)view(.*)")) {  //  && ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("view_onesignal")  ///  && ФлагКакуюЧастьСинхронизацииЗапускаем.equalsIgnoreCase("Запускаем Только Отправления Синхронизации")//ПРОВЕРЯЕМ ДАТЫ КАКАЯ БОЛЬШЕ МЕНЬШЕ тут больше что слева sql server
                    //////  НАЧАЛО  ЛОКАЛЬНАЯ ПРОВЕРКА ВНУТИРИ АНДРОЙДА ПО ДАТАМ МЕЖДУ СОБОЙ
                    Log.d(this.getClass().getName(),
                            " РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее  " + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее +
                                    "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                                    + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 30.06.2022  старкт встаялеммого кода с задержкой
                    // TODO: 30.06.2022  конец встаялеммого кода с задержкой
                    Результат_ПосылаемНа_Сервер = МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(ИмяТаблицыОтАндройда_Локальноая,
                            МенеджерПотоковВнутрений, Результат_ПосылаемНа_Сервер,
                            Результат_СсервераПолучаем_Сервер,
                            РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее);//TODO
                    //   РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                    Log.d(this.getClass().getName(),
                            " РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее  "
                                    + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее +
                                    "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                                    + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                                    + " Результат_ПосылаемНа_Сервер " + Результат_ПосылаемНа_Сервер);

                    if(Результат_ПосылаемНа_Сервер>0 ){
                        ПубличныйРезультатОтветаОтСерврераУспешно=    Результат_ПосылаемНа_Сервер ;
                    }
                } else {
                    // TODO: 19.10.2021   get()->
                    // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                    // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                    // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                    if (РезультатВерсииДанныхЧатаНаСервере > РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера) { // С СЕРВЕРА//&& ФлагКакуюЧастьСинхронизацииЗапускаем.equalsIgnoreCase("Запускаем Только Получения Синхронизации")
                        //////
                        Log.d(this.getClass().getName(),
                                " РезультатВерсииДанныхЧатаНаСервере  " + РезультатВерсииДанныхЧатаНаСервере +
                                        "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                        // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                        Результат_СсервераПолучаем_Сервер = МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                ИмяТаблицыОтАндройда_Локальноая, ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                МенеджерПотоковВнутрений, Результат_СсервераПолучаем_Сервер,
                                РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера ,ActivityДляСинхронизацииОбмена,СколькоСтрочекJSON);
                        // TODO: 05.10.2021  ДЕЙСТИВЕ ТРЕТЬЕ ОТПРАВЛЯЕМ ЕСЛИ ЕСТЬ В ТАБЛИЦЕ ТЕКУЩЕЙ NULL ПОЛЕ В СТОЛИЬКЕ ID
                        // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                        if(Результат_СсервераПолучаем_Сервер>0 ){
                            ПубличныйРезультатОтветаОтСерврераУспешно=Результат_СсервераПолучаем_Сервер;
                        }
                        Log.d(this.getClass().getName(),
                                " РезультатВерсииДанныхЧатаНаСервере  " + РезультатВерсииДанныхЧатаНаСервере +
                                        "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                                        + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
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
            ///////
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
                                                                   CompletionService МенеджерПотоковВнутрений,
                                                                   Integer Результат_ПосылаемНа_Сервер,
                                                                   Integer Результат_СсервераПолучаем_Сервер,
                                                                   Long РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
            , Long РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее) {
        try{
            Log.d(this.getClass().getName(),
                    " ЛОКАЛЬНАЯ ВЕРСИЯ (последнего серверного обновления) ЧАТ   РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера   "
                            + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
                            + "  ЛОКАЛЬНАЯ ЛОКАЛЬНАЯ ВЕРСИЯ (локальные обновления созданые на андройде) ЧАТ   РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее   "
                            + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее +
                            " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем
                            + "РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера  "
                            + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                            "  ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая +
                            "  ПОСЛЕДНИЙ УСПЕШНЫЙ ОБНОВЛЕНИЕ С СЕРВРЕА " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера + "\n" +
                            "  ПОСЛЕДНИЙ УСПЕШНЫЙ ДОБАЛВЕНИЯ НОВОГО СООБЩЕНИЯ " + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее);
            // TODO: 04.11.202
            ////// todo МЕТОД POST
            Результат_ПосылаемНа_Сервер =
                    МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            МенеджерПотоковВнутрений);
            ////// todo МЕТОД POST() в фоне

            ////// todo ВНИМАНИЕ ТУТ КОД НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ БОЛЬШЕ  ЧЕМ НА СЕРВЕРЕ   ,,,,,,, И МЫ ДОЛЖНЫ С ТЕЛЕФОНА ОТОСЛАТЬ ДАННЫЕ НА СЕВРЕР SQL SEVER   POST ()
            ///
            Log.d(this.getClass().getName(),
                    "    Результат_ПосылаемНа_Сервер  " + Результат_ПосылаемНа_Сервер +
                            " РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                            + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                            "  РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее "
                            + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
            // TODO: 04.11.2021
            Log.d(this.getClass().getName(),
                    "    Результат_ПосылаемНа_Сервер  " + Результат_ПосылаемНа_Сервер +
                            " РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                            + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                            "  РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее "
                            + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее +
                            " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + "  РезультатВерсииДанныхЧатаНаСервере " + РезультатВерсииДанныхЧатаНаСервере
                            + " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер);

            //////// TODO  В ДАННОМ СЛУЧАЕ НА СЕРВРЕР ВЕРСИЯ  ДАННЫХ СТАРШЕ ЧЕМ НА АНДЙРОДЕ , И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВЕРА  GET()

            if (Результат_ПосылаемНа_Сервер > 0 && РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее>0) {
                // TODO: 04.11.2021
                // TODO: 03.11.2021  УВЕЛИЧЕНИЕ ПОСЛЕ ВСТАВКИ
                Log.i(this.getClass().getName(), "   РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее"
                        + РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее +
                        "  Результат_ПосылаемНа_Сервер " +Результат_ПосылаемНа_Сервер);
                // TODO: 12.08.2021 код повышает или уменьшает верисю данных
                Integer РезультатПовышенияВерсииДанныхДатыиВерсии =
                        МетодВерсияДанныхМеняемВнутриЦиклаТаблицСинхронизации(Результат_ПосылаемНа_Сервер,
                                ИмяТаблицыОтАндройда_Локальноая, "ЛокальныйСерверныйОба",//"Серверный" "ЛокальныйСерверныйОба"
                                РезультаПолученаяЛокальнаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее,
                                МенеджерПотоковВнутрений);//ЛокальныйСерверныйОба /// "Серверный"
                Log.i(this.getClass().getName(), "   ИмяТаблицыОтАндройда_Локальноая"
                        + ИмяТаблицыОтАндройда_Локальноая + " Результат_ПосылаемНа_Сервер " + Результат_ПосылаемНа_Сервер +
                        "  РезультатПовышенияВерсииДанныхДатыиВерсии " + РезультатПовышенияВерсииДанныхДатыиВерсии);
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
        // TODO: 12.08.2021 код повышает или уменьшает верисю данных при отправке данных
        return Результат_ПосылаемНа_Сервер;
    }

    @NonNull
    private Integer МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                     String ИмяТаблицыОтАндройда_Локальноая,
                                                                     String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                     CompletionService МенеджерПотоковВнутрений,
                                                                     Integer Результат_СсервераПолучаем_Сервер,
                                                                     Long РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
            ,Activity ActivityДляСинхронизацииОбмена
            ,Integer СколькоСтрочекJSON) {
        // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
        // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
        try{

            ///////
            Log.d(this.getClass().getName(), " НА SQL SERVER  ДАТА больше версия" +
                    "  ЛОКАЛЬНАЯ ВЕРСИЯ (последнего серверного обновления) ЧАТ  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                    " и  ТЕКУЩАЯ СЕРВЕРНАЯ ВЕРСИЯ  ЧАТ РезультатВерсииДанныхЧатаНаСервере " + РезультатВерсииДанныхЧатаНаСервере + ИмяТаблицыОтАндройда_Локальноая);

            // TODO: 19.08.2021 уменьшаемм для повторгого повторной отправки

            // TODO: 04.11.2021


            // TODO: 03.11.2021

            //   Integer НесколькоПопытокПолучитьДанные = 3;
            // TODO: 04.11.2021


            //////////TODO МЕТОД get
            Результат_СсервераПолучаем_Сервер =
                    МетодПолучаемДаннныесСервера(ИмяТаблицыОтАндройда_Локальноая,
                            РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                            ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                            РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            МенеджерПотоковВнутрений,ActivityДляСинхронизацииОбмена, СколькоСтрочекJSON);/// ЗАПУСКАМ МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА    МЕТОД GET


            Log.d(this.getClass().getName(), " ПОСЛЕ УСПЕШНОЙ ОТПАРВКИ ДАННЫХ НА СЕРВЕР" +
                    " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                    "  РезультатВерсииДанныхЧатаНаСервере" + РезультатВерсииДанныхЧатаНаСервере
                    + "  ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + "\n" +
                    "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                    + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера+
                    " ВерсииДанныхНаАндройдеЛокальнаяЛокальная " +
                    ВерсииДанныхНаАндройдеЛокальнаяЛокальная);
            /////В ДАНОМ СЛУЧАЕ ДАННЫЕ СИНХРОНИЗИРОВАТЬ НЕ НАДО ВЕСРИЯ ДАННЫХ НА СЕРВРЕР И НА КЛИЕНТЕ ОДИНАКОВЫ
            // TODO: 17.11.2021


            ////TODO КОГДА ДАТЫ РАВНЫ И НЕ ПОЛУЧАТЬ ДАННЫЕ И ОТСЫЛАТЬ НЕ НАДО GET() И POST() ОБА НЕ СРАБОТАЛИMODIFITATION_Client

            if (Результат_СсервераПолучаем_Сервер > 0 && РезультатВерсииДанныхЧатаНаСервере>0) {

                // TODO: 04.11.2021
                ///РезультатВерсииДанныхЧатаНаСервере=РезультатВерсииДанныхЧатаНаСервере+1;

                // TODO: 16.07.2021
                Log.d(this.getClass().getName(), "РезультатВерсииДанныхЧатаНаСервере  " + РезультатВерсииДанныхЧатаНаСервере);

                Integer РезультатПовышенияВерсииДанныхДатыиВерсии = 0;


                // TODO: 16.07.2021
                Log.d(this.getClass().getName(), "КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал  " + КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);


                // TODO: 12.08.2021 код повышает или уменьшает верисю данных
                РезультатПовышенияВерсииДанныхДатыиВерсии =
                        МетодВерсияДанныхМеняемВнутриЦиклаТаблицСинхронизации(Результат_СсервераПолучаем_Сервер,
                                ИмяТаблицыОтАндройда_Локальноая,
                                "ЛокальныйСерверныйОба",//"Серверный"  //"ЛокальныйСерверныйОба"
                                РезультатВерсииДанныхЧатаНаСервере,
                                МенеджерПотоковВнутрений);//ЛокальныйСерверныйОба ////"Серверный"


                // TODO: 04.11.2021

                Log.i(this.getClass().getName(), "   ИмяТаблицыОтАндройда_Локальноая"
                        + ИмяТаблицыОтАндройда_Локальноая + " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                        "  РезультатПовышенияВерсииДанныхДатыиВерсии " + РезультатПовышенияВерсииДанныхДатыиВерсии);





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




        return Результат_СсервераПолучаем_Сервер;
    }

    ///TODO дополнительные метод после отправки данных

    @NonNull
    private Integer МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер_ДополнительныйПослеОтпарвкиДанных(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                                                       String ИмяТаблицыОтАндройда_Локальноая,
                                                                                                       String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                                                       CompletionService МенеджерПотоковВнутрений,
                                                                                                       Integer Результат_СсервераПолучаем_Сервер,
                                                                                                       Long РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера
            ,Activity ActivityДляСинхронизацииОбмена
            ,Integer СколькоСтрочекJSON) {
        // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
        // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
        try{

            ///////
            Log.d(this.getClass().getName(), " НА SQL SERVER  ДАТА больше версия" +
                    "  ЛОКАЛЬНАЯ ВЕРСИЯ (последнего серверного обновления) ЧАТ  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера " + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                    " и  ТЕКУЩАЯ СЕРВЕРНАЯ ВЕРСИЯ  ЧАТ РезультатВерсииДанныхЧатаНаСервере " + РезультатВерсииДанныхЧатаНаСервере + ИмяТаблицыОтАндройда_Локальноая);

            // TODO: 19.08.2021 уменьшаемм для повторгого повторной отправки

            // TODO: 04.11.2021


            // TODO: 03.11.2021

            //   Integer НесколькоПопытокПолучитьДанные = 3;
            // TODO: 04.11.2021


            //////////TODO МЕТОД get
            Integer    Результат_СсервераПолучаем_Сервер_Дополнительный =
                    МетодПолучаемДаннныесСервера(ИмяТаблицыОтАндройда_Локальноая,
                            РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                            ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                            РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                            МенеджерПотоковВнутрений,ActivityДляСинхронизацииОбмена, СколькоСтрочекJSON);/// ЗАПУСКАМ МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА    МЕТОД GET


            Log.d(this.getClass().getName(), " ПОСЛЕ УСПЕШНОЙ ОТПАРВКИ ДАННЫХ НА СЕРВЕР" +
                    " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                    "  РезультатВерсииДанныхЧатаНаСервере" + РезультатВерсииДанныхЧатаНаСервере
                    + "  ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + "\n" +
                    "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                    + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера+
                    " ВерсииДанныхНаАндройдеЛокальнаяЛокальная " +
                    ВерсииДанныхНаАндройдеЛокальнаяЛокальная);
            /////В ДАНОМ СЛУЧАЕ ДАННЫЕ СИНХРОНИЗИРОВАТЬ НЕ НАДО ВЕСРИЯ ДАННЫХ НА СЕРВРЕР И НА КЛИЕНТЕ ОДИНАКОВЫ
            // TODO: 17.11.2021


            ////TODO КОГДА ДАТЫ РАВНЫ И НЕ ПОЛУЧАТЬ ДАННЫЕ И ОТСЫЛАТЬ НЕ НАДО GET() И POST() ОБА НЕ СРАБОТАЛИ

            if (Результат_СсервераПолучаем_Сервер_Дополнительный > 0 &&  Long.parseLong(Результат_СсервераПолучаем_Сервер.toString())>0) {

                // TODO: 04.11.2021
                ///РезультатВерсииДанныхЧатаНаСервере=РезультатВерсииДанныхЧатаНаСервере+1;

                // TODO: 16.07.2021
                Log.d(this.getClass().getName(), "РезультатВерсииДанныхЧатаНаСервере  " + РезультатВерсииДанныхЧатаНаСервере  +
                        " Результат_СсервераПолучаем_Сервер " +Результат_СсервераПолучаем_Сервер+ "  Результат_СсервераПолучаем_Сервер_Дополнительный "+Результат_СсервераПолучаем_Сервер_Дополнительный);

                Integer РезультатПовышенияВерсииДанныхДатыиВерсии = 0;


                // TODO: 16.07.2021
                Log.d(this.getClass().getName(), "КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал  " + КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);


                // TODO: 12.08.2021 код повышает или уменьшает верисю данных
                РезультатПовышенияВерсииДанныхДатыиВерсии =
                        МетодВерсияДанныхМеняемВнутриЦиклаТаблицСинхронизации(Результат_СсервераПолучаем_Сервер,
                                ИмяТаблицыОтАндройда_Локальноая,
                                "ЛокальныйСерверныйОба",//"Серверный"  //"ЛокальныйСерверныйОба"
                                Long.parseLong(Результат_СсервераПолучаем_Сервер.toString()),
                                МенеджерПотоковВнутрений);//ЛокальныйСерверныйОба ////"Серверный"


                // TODO: 04.11.2021

                Log.i(this.getClass().getName(), "   ИмяТаблицыОтАндройда_Локальноая"
                        + ИмяТаблицыОтАндройда_Локальноая + " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер +
                        "  РезультатПовышенияВерсииДанныхДатыиВерсии " + РезультатПовышенияВерсииДанныхДатыиВерсии);





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

            //
            SQLiteCursor Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID=null;
            ////
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








                        //    ИщемЗначенияNULLВТаблицахЧат.appendWhere("_id  IS NULL ");//_id =  ?


                   */
/*  sqLiteCursorКурсорсоЗначениемФИО=
                                       КтоНаписалСообщениеФИО.query(ССылкаНаСозданнуюБазу,new String[]{"*"},"_id",new String[]{String.valueOf(ПолученноеФИОКемБылоНаписаноСообщение)},null,null,null,null);*//*

                        ///
                        class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТекущаяТаблицаГдеЕстьвIdПолеNULL);
                        ///////
                        class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","uuid");
                        //
                        class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","  _id  IS  NULL    ");
                        ///"_id > ?   AND _id< ?"
                        //////
                        ///  class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",0);
                        ///
                        //////
                        // class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",0);



*/
/*            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);*//*

                        ////
                        //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                        ////
                        // class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
                        ////
                        /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                        ////

                        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

                        Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID= (SQLiteCursor)  class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер.
                                new GetData(context).getdata(class_grud_sql_operationsВычисляемЕщенеОтправленныеСообщенияНаСервер.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

                        Log.d(this.getClass().getName(), "GetData "+Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID  );


*/
/*


            // TODO: 06.09.2021  _old
            sqLiteCursorКурсорсоЗначениемФИО=
                    (SQLiteCursor)    ИщемЗначенияNULLВТаблицахЧат.query(ССылкаНаСозданнуюБазу,new String[]{"uuid" }
                            ,null,null,null,null,null);
*//*



                        if(Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.getCount()>0){
                            //
                            Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.moveToFirst();
                            /////
                            ЕслиВПолеIdЗначениеNUll = Курсор_ЗначениемФИО_ВообщеЕстьЛиНеОтправленныеСтрочкиСNULLЗначениямивСтолбикеID.getLong(0);
                            //

                            Log.d(this.getClass().getName(), "  СЛУЖБА ДА ДА ДА Сработала !!!!  в таблице ЧАТА chats and data_chat   " +
                                    "есть NULL (не отправленные сообщения на сервер ) ФиоКтоНАписалСообщение  " + ЕслиВПолеIdЗначениеNUll  + "\n"+
                                    "  ТекущаяТаблицаГдеЕстьвIdПолеNULL " +ТекущаяТаблицаГдеЕстьвIdПолеNULL);
                        }

                        /////УДАЛЯЕМ ИЗ ПАМЯТИ  ОТРАБОТАННЫЙ АСИНАТСК






                        //
                        break;

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
                                         CompletionService МенеджерПотоковВнутрений ,
                                         Activity activity
            ,Integer СколькоСтрочекJSON) {
        Integer РезультатФоновнойСинхронизации=0;
        StringBuffer БуферПолученныйJSON = null;
        try {
            Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  имяТаблицыОтАндройда_локальноая" + имяТаблицыОтАндройда_локальноая);
            StringBuffer БуферПолучениеДанных = new StringBuffer();
            try {
                PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                ////////////////
                БуферПолучениеДанных = УниверсальныйБуферПолучениеДанныхсСервера(имяТаблицыОтАндройда_локальноая, "",
                        "", "application/gzip", "Хотим Получить  JSON"
                        ,ВерсииДанныхНаАндройдеСерверная,//    ВерсииДанныхНаАндройдеСерверная,//37262l
                        ДанныеПришёлЛиIDДЛяГенерацииUUID,120000,null,
                        РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                        public_content.getАдресСервера() , public_content.getПортСервера());//TODO "http://192.168.254.40:8080/"      /      // TODO     "http://tabel.dsu1.ru:8888/"   original     "tabel.dsu1.ru", 8888);
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
                    РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(БуферПолученныйJSON,
                            имяТаблицыОтАндройда_локальноая,МенеджерПотоковВнутрений,activity);/////ЗАПУСК МЕТОДА ПАСРИНГА JSON
                    Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации  "  +РезультатФоновнойСинхронизации);
                /////////
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
    Integer МетодПарсингJSONФайлаОтСервреравФоне(StringBuffer БуферПолученныйJSON,
                                                 String имяТаблицыОтАндройда_локальноая,
                                                 CompletionService МенеджерПотоковВнутрений,
                                                 Activity activity) throws InterruptedException, JSONException {
        ///
        Integer РезулттаВставкиИЛИОбвлоенияФФоне = 0;
        try {
            this.activity=activity;
            boolean СработалПоворот = false;
            HashMap<String, Integer> ХэшIDУведомленияДляПриняитиеРешениеУлалениеЛишнихСтрочек = new HashMap<>();
            Integer[] Результат_ОбновлениеДаннымисСервера = {0};///////после того как
            Long[] Результат_ВставкаДаннымисСервера = {0l};///////после того как
            Integer ИндексТекущееСтрочкиДляКонтейнераВставки = 0;
            Long СколькоСтрочек = 0l;
            ИндексТекущейОперацииJSONДляВизуальнойОбработки = 0;
            // TODO: 05.10.2022
            Log.d(this.getClass().getName(), " БуферПолученныйJSON " + БуферПолученныйJSON.toString());
            // TODO: 07.10.2022 ПОЛУЧАЕМ json через gson
            JsonObject jsonArray =new PUBLIC_CONTENT(context).gson.fromJson(БуферПолученныйJSON.toString(),JsonObject.class);
            Spliterator<Map.Entry<String, JsonElement>> spliteratorВерхний=    jsonArray.entrySet().parallelStream().spliterator();
            spliteratorВерхний.hasCharacteristics(Spliterator.IMMUTABLE | Spliterator.DISTINCT);
            int МаксималноеКоличествоСтрочекJSON =   jsonArray.size();
            Log.d(this.getClass().getName(), " МаксималноеКоличествоСтрочекJSON:::  " + МаксималноеКоличествоСтрочекJSON);
            if (activity!=null) {
                activity.runOnUiThread(()->{
                    progressBar3ГоризонтальныйСинхронизации = activity.findViewById(R.id.progressBar3ГоризонтальныйСинхронизации);
                    if (progressBar3ГоризонтальныйСинхронизации != null) {
                        progressBar3ГоризонтальныйСинхронизации.setMax(МаксималноеКоличествоСтрочекJSON);
                    ТекущаяТаблицыИлиГод = (TextView) activity.findViewById(R.id.TExtViewНазваниеКонторыСнизуСинх); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
                    }
                    Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);
                });
            }
            // TODO: 05.10.2022  запукка Callback
                МетодProseccingAsyncProgrssBar();
            if (handler!=null) {
                handler.obtainMessage(ИндексТекущейОперацииJSONДляВизуальнойОбработки,0,0,имяТаблицыОтАндройда_локальноая).sendToTarget();
            }

            CopyOnWriteArrayList<String> ЗаполненыеСистемныеТаблицыДляПроВизуализацииПрогрессБара = new Class__Generation_Genetal_Tables(context).МетодЗаполеннияТаблицДЛяРаботыиСинхрониазции();

            final Integer[] ИндексДляМассовогоКонтейнер = {0};
            // TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON// ///TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON// TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                АдаптерДляМассовойВставкиДанныхсСервер = null;
                АдаптерДляМассовойВставкиДанныхсСервер = new ContentValues[МаксималноеКоличествоСтрочекJSON];//JSON_ПерваяЧасть.names().length()
                for (int i = 0; i < АдаптерДляМассовойВставкиДанныхсСервер.length; i++) {
                    АдаптерДляМассовойВставкиДанныхсСервер[i] = new ContentValues();
                }
            }


            // TODO: 06.10.2022  вычисляем какую таблицу нужно отоброзить в верхнем ПрограссбАре
           Integer ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения= 
                   ЗаполненыеСистемныеТаблицыДляПроВизуализацииПрогрессБара.indexOf(имяТаблицыОтАндройда_локальноая);
            if (activity!=null) {
                new Class_Visible_Processing_Async(context).ГенерируемПРОЦЕНТЫДляAsync(true,
                        (Activity) activity, ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения ,
                        ЗаполненыеСистемныеТаблицыДляПроВизуализацииПрогрессБара.size());
            }
            Log.d(this.getClass().getName(), "  ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения " + ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения);


            // TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON// TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON// TODO: 18.10.2021  ГЛАВНЫЙ ЦИКЛ РАСПАРИСВАНИЯ JSON
            spliteratorВерхний.forEachRemaining(new java.util.function.Consumer<Map.Entry<String, JsonElement>>() {
                @Override
                public void accept(Map.Entry<String, JsonElement> stringJsonElementEntry) {
                    Log.d(this.getClass().getName(), " БуферПолученныйJSON " + stringJsonElementEntry.getKey() + "stringJsonElementEntry" + stringJsonElementEntry.getValue());
                    try{
                    ///TODO  INSERT КОНТЕЙНЕРЫ    //todo обнуляем данные укакзатели перед каждой НОВОЙ СТРЧКОЙ
                    АдаптерДляВставкиДанныхсСервер = new ContentValues();
                    АдаптерПриОбновленияДанныхсСервера=new ContentValues();
                    ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде = 0l;
                    class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаUUIDобваID = new Class_GRUD_SQL_Operations(context);
                        // TODO: 06.10.2022  ВНУТрений СТрочка обработки данных сами Столбикки
                    JsonObject jsonObjectСамаСтрочка = stringJsonElementEntry.getValue().getAsJsonObject();

                        Long UUIDИлиIDПолученныйИзПришедшегоJSON = 0l;
                        if (jsonObjectСамаСтрочка.has("uuid") == true) {
                            String UUIDБуфер=jsonObjectСамаСтрочка.get("uuid").getAsString();
                            UUIDИлиIDПолученныйИзПришедшегоJSON = Long.parseLong(UUIDБуфер);
                            Log.d(this.getClass().getName(), "  ID  UUIDИлиIDПолученныйИзПришедшегоJSON " + UUIDИлиIDПолученныйИзПришедшегоJSON);
                            МетодАнализаЕстьТакойUUIDИлиIDДАННЫЕВБАЗЕ(имяТаблицыОтАндройда_локальноая,
                                    UUIDИлиIDПолученныйИзПришедшегоJSON,  МенеджерПотоковВнутрений, "uuid","uuid");
                            // TODO: 14.10.2021 ТОЛЬКО ID КОГДА НЕТ ID
                        } else {
                            if (jsonObjectСамаСтрочка.has("id") == true) {
                                String IDБуфер=jsonObjectСамаСтрочка.get("id").getAsString();
                                UUIDИлиIDПолученныйИзПришедшегоJSON = Long.parseLong(IDБуфер);
                                Log.d(this.getClass().getName(), " UUIDИлиIDПолученныйИзПришедшегоJSON " + UUIDИлиIDПолученныйИзПришедшегоJSON+ " IDБуфер "+IDБуфер);
                                МетодАнализаЕстьТакойUUIDИлиIDДАННЫЕВБАЗЕ(имяТаблицыОтАндройда_локальноая,
                                        UUIDИлиIDПолученныйИзПришедшегоJSON,  МенеджерПотоковВнутрений, "id","_id");
                            }
                        }// TODO: 06.10.2022 Анализ Если ID и UUID

                        Log.d(this.getClass().getName(), " ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде " +ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде);
                        Spliterator<Map.Entry<String, JsonElement>> spliteratorНижний=        jsonObjectСамаСтрочка.entrySet().parallelStream().spliterator();
                        spliteratorНижний.hasCharacteristics(Spliterator.IMMUTABLE | Spliterator.DISTINCT);
                        spliteratorНижний.forEachRemaining(new java.util.function.Consumer<Map.Entry<String, JsonElement>>() {
                            @Override
                            public void accept(Map.Entry<String, JsonElement> stringJsonElementEntryВнутриJSONСтрочки) {
                        String ПолеОтJSONKEY=stringJsonElementEntryВнутриJSONСтрочки.getKey();
                                switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                                    case "tabels":
                                    case "chats":
                                    case "data_chat":
                                    case "chat_users":
                                    case "fio":
                                    case "tabel":
                                    case "data_tabels":
                                        System.out.println("  ПолеОтJSONKEY  " + ПолеОтJSONKEY);
                                        if (stringJsonElementEntryВнутриJSONСтрочки.getKey().contentEquals("id")==true) {
                                            ПолеОтJSONKEY="_id";
                                        }
                                        break;
                                }

                                // TODO: 18.08.2022  поиск uuid или id столбиков
                if(ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде==0){///todo КОГДА  ЭТО ВСТАВКА ДАННЫХ INSERT
                    Log.d(this.getClass().getName(), " ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде "
                            + ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде
                            + " stringJsonElementEntryВнутриJSONСтрочки.getValue().toString() " +stringJsonElementEntryВнутриJSONСтрочки.getValue().toString() );
                    АдаптерДляВставкиДанныхсСервер.put(ПолеОтJSONKEY, String.valueOf(stringJsonElementEntryВнутриJSONСтрочки.getValue()).replace("\"",""));
                }else {
                    Log.d(this.getClass().getName(), " ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде " + ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде
                    + " stringJsonElementEntryВнутриJSONСтрочки.getValue().toString() " +stringJsonElementEntryВнутриJSONСтрочки.getValue().toString() );
                    АдаптерПриОбновленияДанныхсСервера.put(ПолеОтJSONKEY, String.valueOf(stringJsonElementEntryВнутриJSONСтрочки.getValue()).replace("\"",""));//
                }

                            }});

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        // TODO: 17.10.2021  ONLY INSERT ROW    МАССОВАЯ ВСТАВКА ДЛЯ МЕТОДА BULRINSERT         // TODO: 17.10.2021  ONLY INSERT ROW
                        МетодContentValuesBulk(activity, ИндексДляМассовогоКонтейнер, имяТаблицыОтАндройда_локальноая);
                        //////// TODO МЕТОД ПЕРОСРЕДСТВЕНОЙ ЗАПИСЬ В  БАЗУ АНДРОЙДА ДАННЫМИС SQL SERVER метод конкретной записо заполеного контерйнра json в  базуу
                        Log.d(this.getClass().getName(), " массова вставка burkinsert АдаптерДляВставкиДанныхсСервер.size() :::  " + АдаптерДляВставкиДанныхсСервер.size());
                    } else {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                            // TODO: 17.10.2021  ONLY INSERT ROW     ОБЫЧНАЯ ВСТАВКА           // TODO: 17.10.2021  ONLY INSERT ROW
                            МетодContectValuesINSERT(имяТаблицыОтАндройда_локальноая,
                                    МенеджерПотоковВнутрений,
                                    activity,
                                    МаксималноеКоличествоСтрочекJSON,
                                    Результат_ВставкаДаннымисСервера);
                            Log.d(this.getClass().getName(), " обычная ВСТВКА  АдаптерПриОбновленияДанныхсСервера.size()  :::  " + АдаптерПриОбновленияДанныхсСервера.size() +
                                    "  КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал " + КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);
                        }
                    }
                    // TODO: 17.10.2021  ONLY UPDATE ROW     // TODO: 17.10.2021  ONLY UPDATE ROW
                    // TODO: 17.10.2021  ONLY UPDATE ROW     // TODO: 17.10.2021  ONLY UPDATE ROW
                    // TODO: 17.10.2021  ONLY UPDATE ROW     // TODO: 17.10.2021  ONLY UPDATE ROW
                    // TODO: 17.10.2021  ONLY UPDATE ROW     // TODO: 17.10.2021  ONLY UPDATE ROW

                    МетодContenerValuesUPDATES(имяТаблицыОтАндройда_локальноая, МенеджерПотоковВнутрений,
                            activity, МаксималноеКоличествоСтрочекJSON,
                            Результат_ОбновлениеДаннымисСервера,
                            UUIDИлиIDПолученныйИзПришедшегоJSON,jsonObjectСамаСтрочка);
                        } catch (Exception e) {
                         e.printStackTrace();
                         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                 " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                         new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                 Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                }// TODO: 06.10.2022 коней верхнего Сплитератора СТрочка
                        // TODO: 25.08.2021 выкидываем из памяти
                    });// TODO: 17.10.2021  END end  LOOP WHILE JSON TABELS
            // TODO: 30.10.2021  ПОСЛЕ ЦИКЛА DO  WHILE

            МетодBulkINSERT(имяТаблицыОтАндройда_локальноая, activity, МаксималноеКоличествоСтрочекJSON, " Результат_ОбновлениеДаннымисСервера :::  " + Результат_ОбновлениеДаннымисСервера[0]
                            + "  Результат_ВставкаДаннымисСервера "
                            + Результат_ВставкаДаннымисСервера[0]
                            + " ИндексТекущейОперацииJSONДляВизуальнойОбработки  "
                            + ИндексТекущейОперацииJSONДляВизуальнойОбработки, Результат_ВставкаДаннымисСервера);

            Log.d(this.getClass().getName(), " Конец  ПАРСИНГА ОБРАБОАТЫВАЕМОМЙ ТАБЛИЦЫ  ::::: "
                    + имяТаблицыОтАндройда_локальноая+" ИндексТекущейОперацииJSONДляВизуальнойОбработки " +ИндексТекущейОперацииJSONДляВизуальнойОбработки);

            //  МетодProseccingAsyncProgrssBar(ActivityДляСинхронизацииОбмена, ИндексТекущейОперацииJSONДляВизуальнойОбработки);
            if (handler!=null) {
                handler.obtainMessage(ИндексТекущейОперацииJSONДляВизуальнойОбработки,0,0,имяТаблицыОтАндройда_локальноая).sendToTarget();
            }
            Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);

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















    protected void МетодProseccingAsyncProgrssBar() {
        try {
 handler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
     @Override
     public boolean handleMessage(@NonNull  Message msg) {
         Log.d(this.getClass().getName(), "  msg " + msg.what + " msg.obj" +msg.obj);
             if (progressBar3ГоризонтальныйСинхронизации != null) {
                 if (progressBar3ГоризонтальныйСинхронизации.getMax()>msg.what) {
                     progressBar3ГоризонтальныйСинхронизации.setVisibility(View.VISIBLE);
                 } else {
                     progressBar3ГоризонтальныйСинхронизации.setVisibility(View.INVISIBLE);
                 }
                 progressBar3ГоризонтальныйСинхронизации.setProgress(msg.what,true);
                 progressBar3ГоризонтальныйСинхронизации.setSecondaryProgress(progressBar3ГоризонтальныйСинхронизации.getProgress()*2);
                 progressBar3ГоризонтальныйСинхронизации.setIndeterminate(false);
                 // TODO: 27.07.2022  показываем процесс синхрониазации когда точное сть данные
                 //progressBar3ГоризонтальныйСинхронизации.getIndeterminateDrawable().setColorFilter(Color.parseColor("#00ACC1"), android.graphics.PorterDuff.Mode.MULTIPLY);
                 progressBar3ГоризонтальныйСинхронизации.getProgressDrawable().setColorFilter(  Color.parseColor("#00ACC1"), android.graphics.PorterDuff.Mode.SRC_IN);
                 Log.w(this.getClass().getName(), "НАсрояка progressBar3ГоризонтальныйСинхронизации " + progressBar3ГоризонтальныйСинхронизации  +
                         " ИндексТекущейОперацииJSONДляВизуальнойОбработки " +ИндексТекущейОперацииJSONДляВизуальнойОбработки+ " msg.arg1 " +msg.arg1);
                 progressBar3ГоризонтальныйСинхронизации.forceLayout();
                 progressBar3ГоризонтальныйСинхронизации.refreshDrawableState();
                 // TODO: 07.10.2022  показывем текущую таюлицу оюраюотки
                 if (ТекущаяТаблицыИлиГод!=null){
                     ТекущаяТаблицыИлиГод.setHint(msg.obj.toString());
                     ТекущаяТаблицыИлиГод.forceLayout();
                 }
             }
         msg.getTarget().removeCallbacksAndMessages(msg);
         return true;
     }
 });
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }

    }

    private void МетодBulkINSERT
            (String имяТаблицыОтАндройда_локальноая,
             Activity ActivityДляСинхронизацииОбмена,
             Integer ВерхнениеЗначениеПришедшегоJSONСтрочек, String msg,
             Long[] результат_ВставкаДаннымисСервера) {
        Log.d(this.getClass().getName(), msg);


        try{


            if (АдаптерДляМассовойВставкиДанныхсСервер!=null) {
//todO ВНИМАНИЕ ТОЛЬКО РАБОАТЕТ BULKiNSERT ТОЛЬКО НА api ВЫШЕ ю 29  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                Log.w(context.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length  " + АдаптерДляМассовойВставкиДанныхсСервер.length
                        + " Arrays.stream(АдаптерДляМассовойВставкиДанныхсСервер).count() "+
                        Arrays.stream(АдаптерДляМассовойВставкиДанныхсСервер).count());/////
            }

            if (АдаптерДляВставкиДанныхсСервер!=null) {
                //todO ВНИМАНИЕ ТОЛЬКО РАБОАТЕТ BULKiNSERT ТОЛЬКО НА api ВЫШЕ ю 29  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                Log.w(context.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length  "+
                        "\n" + " АдаптерДляВставкиДанныхсСервер.size()  " +АдаптерДляВставкиДанныхсСервер.size());/////
            }


            // TODO: 17.10.2021  ONLY INSERT ROW              // TODO: 17.10.2021  ONLY INSERT ROW
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R )  {

                if (Arrays.stream(АдаптерДляМассовойВставкиДанныхсСервер).count() > 0 && АдаптерДляВставкиДанныхсСервер.size()>0 ) {

                    //TODO  ПОСЛЕ ОБРАБОТКИ ВСЕЙ ТАБЛИЦЫ ТЕСТОВО ЗАПУСКАЕМ ЕТОД МАССОВОЙ ВСТАВКИ ЧЕРЕЗ КОНТЕНТ ПРОВАЙДЕР МЕТОД BurkInset
                    Log.w(context.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length  " + АдаптерДляМассовойВставкиДанныхсСервер.length+
                            "\n" + " АдаптерДляВставкиДанныхсСервер.size()  " +АдаптерДляВставкиДанныхсСервер.size());/////
                    // TODO: 17.10.2021  ONLY INSERT ROW

                    // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА  // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА   // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА
                    результат_ВставкаДаннымисСервера[0] = 0l;
                    //////////////

 */
/*       Результат_ВставкаДаннымисСервера = МетодаЗаписиВБазуКонтейнераВСТАВКАJSONвФоне(имяТаблицыОтАндройда_локальноая,
                МенеджерПотоковВнутрений,
                Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы, СколькоСтрочекJSON,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки, ФиналПроценты);*//*

                    ///

                    //TODO ВАЖНО ЗАПИСЫВАЕМ ВСЕ ЗАПИСИ ПОНОВОМУ ОДНИМ МЕТОДОМ BULKiNSETY  ТОЛЬКО РАБОТАЕТ ОТ >  29 Api

                    //  Uri uri=Uri.parse("content://com.dsy.dsu.providerdatabase/tabel");
                    Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + имяТаблицыОтАндройда_локальноая + "");////organization ///data_tabels

                    Log.w(context.getClass().getName(), " uri  " + uri);/////


                    ContentResolver contentResolver;
                    // TODO: 17.11.2021
                    if (ActivityДляСинхронизацииОбмена!=null) {
                        // TODO: 17.11.2021
                        contentResolver = ActivityДляСинхронизацииОбмена.getContentResolver();
                    } else {
                        // TODO: 17.11.2021
                        contentResolver = context.getContentResolver();
                    }


                    int РезультатВставкиМассовой = contentResolver.bulkInsert(uri, АдаптерДляМассовойВставкиДанныхсСервер);
                    // TODO: 27.10.2021

                    if (РезультатВставкиМассовой>0) {
                        // TODO: 14.12.2021
                        ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;


                        //
                        АдаптерДляВставкиДанныхсСервер.clear();

                        // TODO: 15.12.2021
                        АдаптерДляМассовойВставкиДанныхсСервер=null;
                    }

                    Log.w(context.getClass().getName(), " РезультатВставкиМассовой contentResolver.bulkInsert   " + РезультатВставкиМассовой+"\n"+
                            "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая);/////

                    результат_ВставкаДаннымисСервера[0] = Long.valueOf(РезультатВставкиМассовой);

                    // TODO: 30.10.2021
                    Log.d(this.getClass().getName(), " Результат_ВставкаДаннымисСервера :::  " + результат_ВставкаДаннымисСервера[0] + " ВерхнениеЗначениеПришедшегоJSONСтрочек "
                            + ВерхнениеЗначениеПришедшегоJSONСтрочек);


                    Log.d(this.getClass().getName(), " КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал :::  " + КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал);


                    //// TODO не повтрорный запуск синхрониазци
                    if (!КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал.equalsIgnoreCase("ПовторныйЗапускСинхронизации")) {

                        // TODO: 12.01.2022
                        if (ActivityДляСинхронизацииОбмена != null) {
                            ///
                            Long finalРезультат_ВставкаДаннымисСервера = результат_ВставкаДаннымисСервера[0];


                            ActivityДляСинхронизацииОбмена.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ///TODO ОБВНОВЛЕНИЕ false это првидбно начит это не первый запуск и нужно и обновление

                                    // TODO: 30.10.2021
                                    Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации+
                                            " finalРезультат_ВставкаДаннымисСервера " +finalРезультат_ВставкаДаннымисСервера+
                                            " ВерхнениеЗначениеПришедшегоJSONСтрочек " +ВерхнениеЗначениеПришедшегоJSONСтрочек);


// TODO: 15.12.2021 ловим ошибку когда не равно количество строчек пришедщих и количесвто строчек вставленных

                                    if (finalРезультат_ВставкаДаннымисСервера < ВерхнениеЗначениеПришедшегоJSONСтрочек) {
                                        // TODO: 30.10.2021
                                        Long РазницаНЕдовставленныхДанных = ВерхнениеЗначениеПришедшегоJSONСтрочек - finalРезультат_ВставкаДаннымисСервера;
                                        // TODO: 30.10.2021
                                        Toast.makeText(context, "  Вставка (ошибка) данных произошла не полностью   (кол строк:) " + РазницаНЕдовставленныхДанных+"\n"+
                                                        "  Текущая Таблица Обработки " +имяТаблицыОтАндройда_локальноая
                                                , Toast.LENGTH_LONG).show();
                                        // TODO: 30.10.2021
                                        Log.e(this.getClass().getName(), " ОШИБКА ПРИ МАССОВОЙ ЗАПИСИ НЕ СООТВЕТЫВИКИЕ ПРИШЕДЩИХ СТОЧКЕ И ВСТАВЛНОГО КОЛИЧЕСТВА   " + progressBar3ГоризонтальныйСинхронизации+
                                                " finalРезультат_ВставкаДаннымисСервера " +finalРезультат_ВставкаДаннымисСервера+
                                                " ВерхнениеЗначениеПришедшегоJSONСтрочек " +ВерхнениеЗначениеПришедшегоJSONСтрочек+
                                                "  имяТаблицыОтАндройда_локальноая  " +имяТаблицыОтАндройда_локальноая );
                                    }
                                }
                            });
                        }
                    }
                } else {
                    Log.w(this.getClass().getName(), "Контейнер для Массвой вставки пуст ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодContenerValuesUPDATES
            (String имяТаблицыОтАндройда_локальноая,
             CompletionService МенеджерПотоковВнутрений,
             Activity ActivityДляСинхронизацииОбмена,
             Integer СколькоСтрочекJSON,
             Integer[] Результат_ОбновлениеДаннымисСервера,
             Long UUIDПолученныйИзПришедшегоJSON,
             JsonObject jsonObjectСамаСтрочка ) {
        try {
            if (АдаптерПриОбновленияДанныхсСервера.size() > 0 ) {
                if (jsonObjectСамаСтрочка.has("uuid") == true) {
                    Результат_ОбновлениеДаннымисСервера[0] = МетодаСамоОБНОВЛЕНИЕЧЕРЕЗUUID(имяТаблицыОтАндройда_локальноая,
                            UUIDПолученныйИзПришедшегоJSON, МенеджерПотоковВнутрений,
                            Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы, СколькоСтрочекJSON,
                            ИндексТекущейОперацииJSONДляВизуальнойОбработки, ФиналПроценты);
                    // TODO: 14.10.2021 ТОЛЬКО ID КОГДА НЕТ ID
                } else {
                    if (jsonObjectСамаСтрочка.has("id") == true) {
                        Результат_ОбновлениеДаннымисСервера[0] = МетодаСамоОБНОВЛЕНИЕЧЕРЕЗID(имяТаблицыОтАндройда_локальноая,
                                UUIDПолученныйИзПришедшегоJSON, МенеджерПотоковВнутрений,
                                Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы, СколькоСтрочекJSON,
                                ИндексТекущейОперацииJSONДляВизуальнойОбработки, ФиналПроценты);
                    }
                }// TODO: 06.10.2022 Анализ Если ID и UUID
                Log.d(this.getClass().getName(), " Результат_ОбновлениеДаннымисСервера  :: " + Результат_ОбновлениеДаннымисСервера[0]);
                if (Результат_ОбновлениеДаннымисСервера[0] >0) {
                    ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;
                }
                if (Результат_ОбновлениеДаннымисСервера[0] > 0) {
                    АдаптерПриОбновленияДанныхсСервера.clear();
                    ИндексТекущейОперацииJSONДляВизуальнойОбработки++;
                }
                if (ActivityДляСинхронизацииОбмена != null) {
                 //   МетодProseccingAsyncProgrssBar(ActivityДляСинхронизацииОбмена, ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                    if (handler!=null) {
                        handler.obtainMessage(ИндексТекущейОперацииJSONДляВизуальнойОбработки,0,0,имяТаблицыОтАндройда_локальноая).sendToTarget();
                    }
                    Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);
                }
                Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодContectValuesINSERT(String имяТаблицыОтАндройда_локальноая,
                                          CompletionService МенеджерПотоковВнутрений,
                                          Activity ActivityДляСинхронизацииОбмена,
                                          Integer СколькоСтрочекJSON, Long[]
                                                                    Результат_ВставкаДаннымисСервера) {

        try {


            if (АдаптерДляВставкиДанныхсСервер.size() > 0) {


                // TODO: 17.10.2021  ONLY INSERT ROW

                // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА  // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА   // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ ВСТАВКА
                Результат_ВставкаДаннымисСервера[0] = 0l;
                //////////////

                Результат_ВставкаДаннымисСервера[0] = МетодаЗаписиВБазуКонтейнераВСТАВКАJSONвФоне(имяТаблицыОтАндройда_локальноая,
                        МенеджерПотоковВнутрений,
                        Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы, СколькоСтрочекJSON,
                        ИндексТекущейОперацииJSONДляВизуальнойОбработки, ФиналПроценты);
                ///


                Результат_ВставкаДаннымисСервера[0]++;


                if (Результат_ВставкаДаннымисСервера[0] >0) {
                    // TODO: 14.12.2021
                    ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;
                }


                Log.d(this.getClass().getName(), " Результат_ВставкаДаннымисСервера :::  " + Результат_ВставкаДаннымисСервера[0]);
            }

            // TODO: 17.10.2021  clear
            if (Результат_ВставкаДаннымисСервера[0] > 0) {
                //
                АдаптерДляВставкиДанныхсСервер.clear();
                /////////
                ИндексТекущейОперацииJSONДляВизуальнойОбработки++;
            }
            // TODO: 17.10.2021  ПОСЛЕ ОТРАОБОТКИ ПОКАЗЫВАЕМ ПРОЦЕНТЫ
            if (ActivityДляСинхронизацииОбмена != null) {
                // TODO: 03.08.2022  метод визуального отображения хода операции
             //   МетодProseccingAsyncProgrssBar(ActivityДляСинхронизацииОбмена, ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                if (handler!=null) {
                    handler.obtainMessage(ИндексТекущейОперацииJSONДляВизуальнойОбработки,0,0,имяТаблицыОтАндройда_локальноая).sendToTarget();
                }
                Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);

            }
            ///todo текущая таблица





            //todo end for

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }
    }

    private void МетодContentValuesBulk(Activity ActivityДляСинхронизацииОбмена, Integer[] ИндексДляМассовогоКонтейнер, String имяТаблицыОтАндройда_локальноая) {

        try{
            // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ   ДЛя МАССОВГО КОНТЕЙНЕРА А   ОБНОВЛНИЕ     // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ    ОБНОВЛНИЕ
            if (АдаптерДляВставкиДанныхсСервер.size() > 0) {
                // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ   ДЛя МАССОВГО КОНТЕЙНЕРА А   ОБНОВЛНИЕ     // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ    ОБНОВЛНИЕ
                АдаптерДляМассовойВставкиДанныхсСервер[ИндексДляМассовогоКонтейнер[0]].putAll(АдаптерДляВставкиДанныхсСервер);

                // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ    ОБНОВЛНИЕ     // TODO: 09.09.2021 ДВЕ ОПЕРАЦИИ    ОБНОВЛНИЕ
                Log.d(this.getClass().getName(), " АдаптерДляМассовойВставкиДанныхсСервер.length :::  "
                        + АдаптерДляМассовойВставкиДанныхсСервер.length);
                ///TODO повышаем индекс для массовой вставки
                ИндексДляМассовогоКонтейнер[0]++;
                // TODO: 17.10.2021  clear
                if (ИндексДляМассовогоКонтейнер[0] > 0) {
                    ИндексТекущейОперацииJSONДляВизуальнойОбработки++;
                }
                if (ActivityДляСинхронизацииОбмена != null) {
                    // TODO: 03.08.2022  метод визуального отображения хода операции
                  //  МетодProseccingAsyncProgrssBar(ActivityДляСинхронизацииОбмена, ИндексТекущейОперацииJSONДляВизуальнойОбработки);
                    if (handler!=null) {
                        handler.obtainMessage(ИндексТекущейОперацииJSONДляВизуальнойОбработки,0,0,имяТаблицыОтАндройда_локальноая).sendToTarget();
                    }
                    Log.d(this.getClass().getName(), " progressBar3ГоризонтальныйСинхронизации:::  " + progressBar3ГоризонтальныйСинхронизации);
                }
                ///todo текущая таблица
            }
            //todo end for
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }
    }

    //////////////TODO метод непосредвственой запись в базу данных json КОТОРЫЙ ПРИШЁЛ С СЕРВЕРА---!!!!! ПЕРВАЯ ОПЕРПЦИЯ ВСТАВКА
    Long МетодаЗаписиВБазуКонтейнераВСТАВКАJSONвФоне(String имяТаблицыОтАндройда_локальноая,
                                                     @NotNull CompletionService МенеджерПотоков,
                                                     SQLiteDatabase getБазаДанныхДЛяОперацийВнутри
            ,Integer СколькоСтрочекJSON,
                                                     Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                                                     Float ФиналПроценты ) {////запись полученого json   от сервера через контейнер

        Log.d(this.getClass().getName(), " ИндексТекущейОперацииJSONДляВизуальнойОбработки  " +  ИндексТекущейОперацииJSONДляВизуальнойОбработки + " МенеджерПотоков "+ МенеджерПотоков
                + "  ФиналПроценты " +ФиналПроценты+ " имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая+  "СколькоСтрочекJSON " +СколькоСтрочекJSON);
        Long РезультатВставкиЧерезКонтрейнер = 0l;
        try {
            //////////todo ВСТАВКА JSON НА КЛИЕНТА ДАННЫЕ С СЕРВЕРА
            Log.i(this.getClass().getName(), "  АдаптерДляВставкиДанныхсСервер      " + АдаптерДляВставкиДанныхсСервер.size());
            ////////ВЫЗЫВАЕМ ВСТАВКУ ДАННЫХ
            // TODO: 10.09.2021 сама операция всатвки
            РезультатВставкиЧерезКонтрейнер = ВставкаДанныхЧерезКонтейнерУниверсальнаяЧерезContentResolver(имяТаблицыОтАндройда_локальноая,
                    АдаптерДляВставкиДанныхсСервер, имяТаблицыОтАндройда_локальноая,
                    "", true,
                    СколькоСтрочекJSON, true, context, activity,МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри,СколькоСтрочекJSON,
                    ИндексТекущейОперацииJSONДляВизуальнойОбработки,ФиналПроценты);

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
    }


    //////////////TODO метод непосредвственой запись в базу данных json КОТОРЫЙ ПРИШЁЛ С СЕРВЕРА  ВТОРАЯ ОПЕРАЦИЯ ОБНОВЛЕНИЯ !!!!!!!
    Integer МетодаСамоОБНОВЛЕНИЕЧЕРЕЗUUID(@NotNull  String имяТаблицыОтАндройда_локальноая,
                                          @NotNull Long UUIDПолученныйИзПришедшегоJSON,
                                          @NotNull CompletionService МенеджерПотоков,
                                          SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                          Integer СколькоСтрочекJSON,
                                          Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                                          Float ФиналПроценты) {////запись полученого json   от сервера через контейнер
        Log.d(this.getClass().getName(), " UUIDПолученныйИзПришедшегоJSON  " + UUIDПолученныйИзПришедшегоJSON+ "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
        try {
            Log.i(this.getClass().getName(), "  АдаптерДляОбновленияПриВставкиДанныхсСервера " + АдаптерПриОбновленияДанныхсСервера.size());
            ///TODO когда есть только UUID
            if (UUIDПолученныйИзПришедшегоJSON>0) {
                //////todo UUID UPDATE
                РезультатОбновлениеЧерезКонтрейнер = ОбновлениеДанныхЧерезКонтейнерУниверсальная(имяТаблицыОтАндройда_локальноая, АдаптерПриОбновленияДанныхсСервера,
                        String.valueOf(UUIDПолученныйИзПришедшегоJSON),
                        СколькоСтрочекJSON, true, context,
                        "uuid", activity,МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри,СколькоСтрочекJSON,ИндексТекущейОперацииJSONДляВизуальнойОбработки,ФиналПроценты);
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
                                          Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                                          Float ФиналПроценты) {////запись полученого json   от сервера через контейнер
        Log.d(this.getClass().getName(), " IDИзПришедшегоJSON  " + IDИзПришедшегоJSON+ "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
        try {
            Log.i(this.getClass().getName(), "  АдаптерДляОбновленияПриВставкиДанныхсСервера " + АдаптерПриОбновленияДанныхсСервера.size());
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
                РезультатОбновлениеЧерезКонтрейнер = ОбновлениеДанныхЧерезКонтейнерУниверсальная(имяТаблицыОтАндройда_локальноая, АдаптерПриОбновленияДанныхсСервера,
                        String.valueOf(IDИзПришедшегоJSON),
                        СколькоСтрочекJSON,
                        true, context, ВзависимостиТакаяТабицаЧерезЭтотИнфдификатораИОбновлеяемДляTables__ID, activity,МенеджерПотоков,
                        Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы,СколькоСтрочекJSON,ИндексТекущейОперацииJSONДляВизуальнойОбработки,ФиналПроценты);
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
        ////

        Integer РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление=0;
        ///
        Log.d(this.getClass().getName(), "  имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
        ////
        Long Версия_ДанныхАндройДляОтправкиДанныхНАсервер = 0l;
        ////
        int КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки = 0;
        //////
        Integer КоличествоКолоноквОтправвляемойТаблице = 0;
        ////


        // TODO: 06.09.2021
        ///// Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне;

        try {
            Log.d(this.getClass().getName(), "  МетодПосылаемДанныеНаСервер в фоне ");


            Class_GRUD_SQL_Operations  class_grud_sql_operationsПосылаемДанныеНаСервервФоне = new Class_GRUD_SQL_Operations(context);


            // TODO: 15.02.2022

            Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsДляВыполенияОперацииGEtData=class_grud_sql_operationsПосылаемДанныеНаСервервФоне.new GetData(context);




            // TODO: 15.02.2022
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
            ///////
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "versionserveraandroid_version");
            //
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " name=?   ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", имяТаблицыОтАндройда_локальноая);
            ///
*/
/*            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
            class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);*//*

            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            // class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            SQLiteCursor КурсорДляАнализаДатыПоследнейОтпракиНаСервер = null;
            ///////
            КурсорДляАнализаДатыПоследнейОтпракиНаСервер = (SQLiteCursor) class_grud_sql_operationsДляВыполенияОперацииGEtData
                    .getdata(class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

            Log.d(this.getClass().getName(), "GetData  КурсорДляАнализаДатыПоследнейОтпракиНаСервер "+КурсорДляАнализаДатыПоследнейОтпракиНаСервер );




     */
/*       // TODO: 06.09.2021  _old
            КурсорДляАнализаДатыПоследнейОтпракиНаСервер= КурсорУниверсальныйДляБазыДанных("MODIFITATION_Client", new String[]{"versionserveraandroid"},
                    "name=?", new String[]
                            {имяТаблицыОтАндройда_локальноая}, null, null, null, null);
            ////////
*//*

            //////ОЧИСТКА ПАМЯТИ ОТ ASYNSTASK

            /////////todo  result

            //// КУРСОР ПО ПОИСКУ ДАТФ ПОСЛЕДНЕЙ ОТПРАВКИ НА СЕРВЕР
            ////////
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
            ///////
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");
            //


     */
/*       //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

            Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая   "
                            + имяТаблицыОтАндройда_локальноая+ "  РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера ");*//*


            // TODO: 12.10.2021

            Integer ПубличныйIDДляФрагмента=0;


            // TODO: 31.01.2022 ОПРЕДЕЛЯЕМ ПУБЛИЧНЫЙ id
            ПубличныйIDДляФрагмента = getInteger(имяТаблицыОтАндройда_локальноая, РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера, class_grud_sql_operationsПосылаемДанныеНаСервервФоне);




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
                            МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

            Log.d(this.getClass().getName(), "GetData " + КурсорДляОтправкиДанныхНаСервер);




            //////ОЧИСТКА ПАМЯТИ ОТ ASYNSTASK
            Log.d(this.getClass().getName(), "КурсорДляОтправкиДанныхНаСервер.getCount()  ЕСЛИ 0 СТРОЧЕК ТО ДЕЛАЕМ ЕЩЕ ОДИН ПРОВЕРКУ НА null " + КурсорДляОтправкиДанныхНаСервер.getCount());
            /////TODO результаты   количество отправляемой информации на сервера

            if (КурсорДляОтправкиДанныхНаСервер.getCount() > 0) {/////работаем уже в сгенерированных даннных которые мы отправим на сервер
                /////
                КурсорДляОтправкиДанныхНаСервер.moveToFirst();
                ////
                КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки = КурсорДляОтправкиДанныхНаСервер.getCount();////КОЛИЧЕСТВО СТРОК В АНДРОЙДЕ ДАННЫЕ КОТОРЫЕ НУЖНО ПОСЛЛАТЬ
                ///
                КоличествоКолоноквОтправвляемойТаблице = КурсорДляОтправкиДанныхНаСервер.getColumnCount();/////КОЛИЧЕСТВО СТОЛЮЦОВ НА АНДРОДЕ БАЗЕ КОТОРОЫЕ НУЖНО ОТОСЛАТЬ
                ////
                Log.d(this.getClass().getName(), " КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки  " + КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки +
                        "  КоличествоКолоноквОтправвляемойТаблице  " + КоличествоКолоноквОтправвляемойТаблице +
                        "  КурсорДляОтправкиДанныхНаСервер.getCount() " +КурсорДляОтправкиДанныхНаСервер.getCount());


                // TODO: 06.09.2021  полчено отправляем


                ////TODO провеояем чтобы  JSON ФАЙЛ БЫЛ НЕ ПУСТЫМ ДЛЯ ОТПРПВИК ЕГО НЕ СЕРВЕР
                if (КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки > 0) {

                    РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление = 0;

                    Log.d(this.getClass().getName(), "КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки " + КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки);



                    //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()

                    РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление =
                            МетодГенеррируемJSONИзНашыхДанныхвФоне(КурсорДляОтправкиДанныхНаСервер, КоличествоСтрокПолученыеДляОтпарвкиПоДатеОтпарвки,
                                    КоличествоКолоноквОтправвляемойТаблице, имяТаблицыОтАндройда_локальноая,МенеджерПотоковВнутрений);
                    //

                    Log.d(this.getClass().getName(), "РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление " + РезультатОтветаОтСервераУспешнаяВставкаИлиОбновление);



                }

                // TODO: 21.09.2021 close cursor



                // TODO: 27.09.2021     Нет данных для отправки  И ДЕЛАЕМ ЕЩЕ ОДНУ ПОПЫТКУ ОТПРАЛЯЕМ NULL ЗНАЧЕНИЯ
            }



            ///todo закрываем куроср
            КурсорДляОтправкиДанныхНаСервер.close();

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

              */
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
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска6",ПубличныйIDДляФрагмента);*//*


                    //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА  old version

           */
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
                //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА*//*

                    // TODO: 19.10.2021  old data_chat

      */
/*          class_grud_sql_operationsПосылаемДанныеНаСервервФоне
                        .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," " +
                        "  current_table > ? AND user_update=?   AND date_update IS NOT NULL" +
                        " OR    current_table > ? AND id_user=?  AND status_write=?  AND date_update IS NOT NULL "+
                        " OR   user_update=?  AND _id IS NULL  AND date_update IS NOT NULL ");
*//*

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
                    МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

            Log.d(this.getClass().getName(), "GetData " + КурсорДляОтправкиДанныхНаСервер);


            if(КурсорДляОтправкиДанныхНаСервер.getCount()>0){
                ////
                КурсорДляОтправкиДанныхНаСервер.moveToFirst();

            }

            // TODO: 06.09.2021  old
*/
/*
            КурсорДляОтправкиДанныхНаСервер = КурсорУниверсальныйДляБазыДанных(имяТаблицыОтАндройда_локальноая, new String[]{"*"}, " _id IS NULL  OR _id = ? ",
                    new String[]{""}, null, null, null, null);*//*

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
///////метод создание josn из наших данных на отправку
            ////
            final String[] ПерхнееПолеJSONПоследнаяОперация = {null};////ПЕРЕРВОДИМ ИЗ INT TO STRING


            Log.d(this.getClass().getName(), " КурсорДляОтправкиДанныхНаСервер.getCount())   "
                    + КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() + " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);

            boolean СработалПоворот = false;

            ///// ДО DO WHILE ОБЯЗАТЕЛЬНО

            if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                ///////////
                КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToLast();
                // TODO: 06.09.2021
                Log.d(context.getClass().getName(), "КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() " + КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());


            }

            final int[] КакаяСтрочкаОбработкиТекущаяя = {1};/////ОСТЛЕЖИВАЕМ ТЕКУЩУЮ СТРОЧКУ ОБРАБОТКИ
            ///TODO ЗАПУСКАЕМ  ПуллПамяти
            ////TODO ЗАДЕРЖКА ИТЕРАЦИЯ ДЛЯ СИНРОНИЗАЦИИ В ФОНЕ отправка данных

            //todo бежим по строчкам json
            do {/////КРУТИТЬ ДАННЫЕ ЧЕРЕЗ ЦИКЛ ОТВЕТЫ ОТ МЕТОДА POST

                /// ////TODO ЗАДЕРЖКА ИТЕРАЦИЯ ДЛЯ СИНРОНИЗАЦИИ В ФОНЕ
                ////TODO ЗАДЕРЖКА ИТЕРАЦИЯ ДЛЯ СИНРОНИЗАЦИИ В ФОНЕ отправка данных
                /// ////TODO ЗАДЕРЖКА ИТЕРАЦИЯ ДЛЯ СИНРОНИЗАЦИИ В ФОНЕ


                /////////////////
                JSONObject ГенерацияJSONполей = new JSONObject();  ////ПОЛЯ  ДЛЯ  JSON ///ВАЖНО ГЕНЕРАЦИЯ НОВЫХ ОБЬЕКТОВ JSON НУЖНО СТАВИТЬ ВНУТРИ DO WHILE  НО ДО FOR ЦИКЛА МЕЖДУ НИМИ

                /////
                CopyOnWriteArrayList<Integer> linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре=new CopyOnWriteArrayList<Integer>();
                //

                for (Integer i = 0; i < КоличествоКолоноквОтправвляемойТаблице; i++) {
                    ////
                    linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.add(i);
                }


                ///
                Iterator iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре=linkedBlockingDequeГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.iterator();



                //todo бежим по столбцам
                while (iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.hasNext()) {
                    try {

                        //////////TODO ЭКСПЕРЕМЕНТ С JSON

                        Integer ИндексПоТАблицамДляОтправки= (Integer) iteratorГенерируемJSONИзНашихДанныхДляОтправкиНаСервре.next();


                        Log.d(this.getClass().getName(), "  ИндексПоТАблицамДляОтправки " +ИндексПоТАблицамДляОтправки );




                        //////ПОЛЯ ДЛЯ ВСТВКИ В JSON  ДЛЯ ОТПРАВКИ ЕГО НА СЕРВЕЛТ
                        String КлючJsonСтроки = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnName(ИндексПоТАблицамДляОтправки);


                        //TODO сомо имя json
                        System.out.println(" КлючJsonСтроки  " + КлючJsonСтроки);


                        /////////
                        Object ЗначниеJsonСтроки = КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексПоТАблицамДляОтправки);

                        Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);

                        //todo

                        if (!КлючJsonСтроки.equalsIgnoreCase("_id") && !КлючJsonСтроки.equalsIgnoreCase("id")) {
                            //TODO

                            ЗначниеJsonСтроки=  Optional.ofNullable(ЗначниеJsonСтроки).orElse("");
                            //TODO
                            Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                    + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                        }

                        Log.d(this.getClass().getName(), " КлючJsonСтроки ::    "
                                + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);


                        //TODO НАЧИНАЕМ ОБРАБАТЫВАТЬ КОГДА ЗНАЧЕНИЕ ПО СТОЛБИКУ ОТРУСТУЕТ VALUE==BULL  #ПЕРВАЯ ЧАСТЬ
                        Log.d(this.getClass().getName(), " КлючJsonСтроки " + КлючJsonСтроки);

                        ////////// TODO КОНКРЕТАНАЯ ГЕНЕРАЦИЯ  JSON СТРОКИ
                        if (КлючJsonСтроки != null && ЗначниеJsonСтроки != null) {//ПРОИЗВОДИМ ВСТАВКИ JSON ПОЛЕЙ ТОЛЬКО ЕСЛИ ОНИ НЕ NULL


                            ////TODO в обратную сторону обмена из _id в таблице tabels на id меняем ы фоне


                            // TODO: 24.06.2021 меняем местави приотправки на сервер данные однго столика с _id на id

                            switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {

                                case "tabels":
                                case "chats":
                                case "data_chat":
                                case "chat_users":
                                case "fio":
                                case "tabel":
                                case "data_tabels":
                                    //
                                    System.out.println("  КлючJsonСтроки  " + КлючJsonСтроки);

                                    if (КлючJsonСтроки.equals("_id")) {

                                        КлючJsonСтроки = "id";
                                        //
                                        System.out.println("  КлючJsonСтроки  " + КлючJsonСтроки);
                                    }

                                    break;
                            }


                            //////

                            /////TODO вытаемся отслидить хотябы один заполненый день
                            Log.d(this.getClass().getName(), "КлючJsonСтроки " + "--" + КлючJsonСтроки + " З начниеJsonСтроки " + ЗначниеJsonСтроки);/////


                            ///todo генерация самой строки json ниже ключ к нему после for//   && ЗначниеJsonСтроки.toString().matches("[1-9]"
                            if (КлючJsonСтроки.matches("[d].*") && КлючJsonСтроки.length() <= 3) {
                                ///////
                                ГенерацияJSONполей.put(КлючJsonСтроки, ЗначниеJsonСтроки); ////заполение полей JSON

                            } else if (ЗначниеJsonСтроки != null && ! ЗначниеJsonСтроки.toString().equalsIgnoreCase("null")) {
                                ///////
                                ГенерацияJSONполей.put(КлючJsonСтроки, ЗначниеJsonСтроки); ////заполение полей JSON
                            }


                            ///
                            Log.d(this.getClass().getName(), " КлючJsonСтроки  " + КлючJsonСтроки + "  ЗначниеJsonСтроки " + ЗначниеJsonСтроки);
                            //todo обнуление после вставки
                            КлючJsonСтроки = null;
                            ЗначниеJsonСтроки = null;
                        }
                        ///////////////ДОБАЛВЕНИЕ ВЕПХНЕГО ID ПО ВЕРХ JSON ПОЛЕЙ



                        //////////TODO  КОНЕЦ ЭКСПЕРЕМЕНТ С JSON
                        ///todo  только uuid
                        ЕслиUUIDПриЗабросеДанныхНаСервер[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid");

                        if (ЕслиUUIDПриЗабросеДанныхНаСервер[0] >= 0) {

                            Log.d(this.getClass().getName(), "ЕслиUUIDИлиIDПриЗабросеДанныхНаСервер " + ЕслиUUIDПриЗабросеДанныхНаСервер[0]);
                            ///////////////ЕСЛИ ID ПОЛЕ ПУСТОЕ ТО ЗАПОЛНЕМЕМ ЕГО ВТОРЫМ ПОЛЕМ
                            int ИндексДвижениеПОПОлямДЛяФОрмированиеID = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid");
                            ////todo
                            ПерхнееПолеJSONПоследнаяОперация[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексДвижениеПОПОлямДЛяФОрмированиеID);

                        }


                        ///todo  только id
                        ЕслиIDПриЗабросеДанныхНаСервер[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("id");

                        if (ЕслиIDПриЗабросеДанныхНаСервер[0] >= 0 && ПерхнееПолеJSONПоследнаяОперация[0] == null) {

                            Log.d(this.getClass().getName(), "ЕслиUUIDИлиIDПриЗабросеДанныхНаСервер " + ЕслиIDПриЗабросеДанныхНаСервер[0]);
                            ///////////////ЕСЛИ ID ПОЛЕ ПУСТОЕ ТО ЗАПОЛНЕМЕМ ЕГО ВТОРЫМ ПОЛЕМ
                            int ИндексДвижениеПОПОлямДЛяФОрмированиеID = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("id");
                            ////todo
                            ПерхнееПолеJSONПоследнаяОперация[0] = КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексДвижениеПОПОлямДЛяФОрмированиеID);

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




                    ////todo выкидываем из очереди отработнаную строчку


                }
                ///future


                ////TODO упаковываем jon если хоть какое поле есть   , ЕСЛИ ЕСТЬ ИЛИ ID ИЛИ UUID
                if (ПерхнееПолеJSONПоследнаяОперация[0] != null && ГенерацияJSONполей != null && ГенерацияJSONполей.length() > 0) {


                    /// todo МЕЖДУ FOR И WHILE
                    Log.i(this.getClass().getName(), " ПерхнееПолеJSONПоследнаяОперация  :     " + ПерхнееПолеJSONПоследнаяОперация[0]
                            + " ГенерацияJSONполей " + ГенерацияJSONполей.toString());


                    /////////
                    try {
                        //////////todo КОНКРЕТАНАЯ ГЕНЕРАЦИЯ  JSON ВЕРХНЕГО КЛЮЧА
                        ГенерацияJSONполейФинал.put(ПерхнееПолеJSONПоследнаяОперация[0], ГенерацияJSONполей);////ВСТАВЛЯЕМ ОДИН JSON в ДРУГОЙ JSON ПОЛУЧАЕМ ФИНАЛЬНЫЙ РЕЗУЛЬТАТ JSON"А

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

                }


                КакаяСтрочкаОбработкиТекущаяя[0]++;////ЛОВИМ ТЕКУУЩЮ СТРОЧКУ ОБРАБОТКИ


                //todo ////


                //todo  ////

                ////// todo  КОНЕЦ МЕЖДУ FOR И WHILE
                ///todo идем по сторчкам  json
            } while (КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToPrevious());////ДАННЫЕ КРУТИЯТЬСЯ ДО КОНЦА ДАННЫХ И ГЕНЕРИРУЮ JSON
            ///todo




            ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
            Log.d(this.getClass().getName(), " ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                    " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());



            /////////
            if (ГенерацияJSONполейФинал.toString().length() > 3) {

                ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
                Log.d(this.getClass().getName(), " ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                        " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());


                //TODO ЗАКРЫВАЕМ КУРСОР
                РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = 0;
                ///todo МЫ СОЗДАЛИ ФАЙЛ JSON  И ПОСЫЛАЕМ ЕГО НА СЕРВЕР


                SubClass_Klacc_Otprabki_класс_ОтправкиДанных subClass_klacc_otprabki_класс_отправкиДанных = new SubClass_Klacc_Otprabki_класс_ОтправкиДанных(context);


                РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления =
                        subClass_klacc_otprabki_класс_отправкиДанных.МетодПосылаетНаСерверСозданныйJSONФайлвФоне(ГенерацияJSONполейФинал, имяТаблицыОтАндройда_локальноая, МенеджерПотоковВнутрений); ////СГЕНЕРИРОВАНЫЙ JSON ФАЙЛ ЕСЛИ БОЛЬШЕ 2 ССИМВОЛОМ В НЕМ ТО ОТПРАВЛЯЕМ
                //

                Log.d(this.getClass().getName(), " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления  " + РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления);
            }else {
                ///////// TODO ФИНАЛ ПРОСМАТРИВАЕМ СГЕНЕРИРОВАНЫЙ JSON  ФАЙЛ ПОСЛЕ ЦИКЛА DO WHILE СОЗДАИНИЕ НА СТОРОНЕ АНДРОЙДА JSON ПОЛЕЙ
                Log.d(this.getClass().getName(), " НЕТ ДАННЫХ ДЛЯ ОТПРАВКИ  ГенерацияJSONполейФинал  " + ГенерацияJSONполейФинал + " ГенерацияJSONполейФинал " + ГенерацияJSONполейФинал.toString() +
                        " ГенерацияJSONполейФинал.length() " + ГенерацияJSONполейФинал.length());

            }





            /////todo exit cursor
            КурсорДляОтправкиДанныхНаСерверОтАндройда.close();


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

        //
        return  РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления;
    }


    ///TODO ОТТВЕТ ОТ СЕРВЕРАР ОТ МЕТОДА POST() С РЕЗУЛЬТАТАМИ ВСТАВКИ И ЛИ ОБНОВЛЕНЕИ ДАННЫХ


    /////// данные код анализирует успешные и/ил обновление данных на серврер кторые ему присла пользователь
    void МетодАнализаОтветаОтСервераУспешныеВставкиИлиОбновлениевФоне(String ДанныеПришёлВОтветОтМетодаPOST, String имяТаблицыОтАндройда_локальноая,CompletionService МенеджерПотоковВнутрений) {
        //////

        Log.d(this.getClass().getName(), "  ДанныеПришёлВОтветОтМетодаPOST " + ДанныеПришёлВОтветОтМетодаPOST);


        StringBuffer ОтветОтСервераДляВставки = new StringBuffer();


        try {
            Log.d(this.getClass().getName(), " ДанныеПришёлВОтветОтМетодаPOST             " + ДанныеПришёлВОтветОтМетодаPOST);
/////// данные код анализирует успешные и/ил обновление данных на серврер кторые ему присла пользователь

            ////todo обновление ответной от сервера
            int УспешноеОбновлениеНаСерверe = 0;

            int УспешноеВставкаНаСервере = 0;

            String ВытащилиUUIDИзPOST = "";

            //TODO ОБНОВЛНИЕ
            int УспешныеЛиОтветыОтСервераИлиНет = ДанныеПришёлВОтветОтМетодаPOST.indexOf("::");///TODO если в теле ответа от сервера POSt вообще ::

            if (УспешныеЛиОтветыОтСервераИлиНет > 0) {
                ///////
                for (String ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвера : ДанныеПришёлВОтветОтМетодаPOST.split("::")) {
                    /////
                    if (УспешноеОбновлениеНаСерверe == 0) {

                        УспешноеОбновлениеНаСерверe = ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвера.indexOf("Обновление");
                    }

                    if (УспешноеВставкаНаСервере == 0) {

                        УспешноеВставкаНаСервере = ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвера.indexOf("Вставка");
                    }
                    //////////
                    for (String ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвераВнутрений : ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвера.split(" ")) {

                        Log.d(this.getClass().getName(), "  ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвераВнутрений " + ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвераВнутрений);

                        boolean РезультатЯвляетьсяЦифройВесьТекст = МетодОпределениеВселиЦифрыВстроке(ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвераВнутрений);

                        Log.d(this.getClass().getName(), " РезультатЯвляетьсяЦифройВесьТекст            " + РезультатЯвляетьсяЦифройВесьТекст);

                        if (РезультатЯвляетьсяЦифройВесьТекст == true) {

                            ВытащилиUUIDИзPOST = ЧтоВнутриПришедшегоПоложитльеногоОбновлениеСесвераВнутрений;
                            ////
                            long РезультатПослеОбновлениеЧерезКонтрейнер = 0;


                            РезультатПослеОбновлениеЧерезКонтрейнер = ОбновлениеДанныхЧерезКонтейнерВозвращениеРезультатаОтСервераУниверсальная(имяТаблицыОтАндройда_локальноая,
                                    Long.parseLong(ВытащилиUUIDИзPOST));
                            /// после обновление  в базу обнуляем контейнер  данные от сервера


/////TODO РЕЗУЛЬТАТА ВСТАВКИ ОТВЕТА ОТ СЕРВРЕ НА КЛИНЕТ ПРИ УСПЕШНОЙ ОБНОВЛЕНИИ ИЛИ ВСТАВКЕ
                            if (РезультатПослеОбновлениеЧерезКонтрейнер > 0) {
                                //todo обявлем успешное встаку
                                if (УспешноеОбновлениеНаСерверe > 0) {


                                    Log.d(this.getClass().getName(), " УспешноеОбновлениеНаСерверe "+ УспешноеОбновлениеНаСерверe);
                                    ///////
                                    УспешноеОбновлениеНаСерверe = 0;

                                    ////TODOECGTI
                                } else if (УспешноеВставкаНаСервере > 0) {

                                    //////
                                    Log.d(this.getClass().getName(), " УспешноеОбновлениеНаСерверe "+ УспешноеОбновлениеНаСерверe);
                                    ////todo после успешной операции обнуяем
                                    УспешноеВставкаНаСервере = 0;
                                }
                                ////TODO метод POST ответ от сервера
                                Log.d(this.getClass().getName(), " УспешноеОбновлениеНаСерверe "+ УспешноеОбновлениеНаСерверe);
                            }
                        }
                    }
                    ////// todo Удаляем из памяти Асинтаск

                }
            }


            //////
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
    }


    ///TODO подсчет часов


    //////ТУТ БУДЕТ ЗАПИСЫВАТЬСЯ УСПЕШНОЕ ОБНЛВДЕНИ И ВСТАВКИ ДАННЫХ НА СЕРВЕРЕ ДЛЯ КЛИЕНТА


    /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ

    public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                String ПолучениеЗначениеСтолбикUUID,
                                                Context КонтексДляЛокальногоОбновления,
                                                String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
        //////


        Integer результатОбновлениеЧерезКонтрейнер = 0;
        ////TODO ДАТА

        try {
            ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
            результатОбновлениеЧерезКонтрейнер = new Class_Engine_SQL(КонтексДляЛокальногоОбновления).
                    ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
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


    ///////todo АНАЛИХ ID и UUID
    Long МетодАнализаЕстьТакойUUIDИлиIDДАННЫЕВБАЗЕ(String ИмяТаблицы,
                                                      Long СамоЗначениеUUIDилиIDДЛяСравения,
                                                      CompletionService МенеджерПотоковВнутрений,
                                                      String СтолбикСравнения,
                                                      String ИзменяемыйСтлобикСравенения)
            throws ExecutionException, InterruptedException, TimeoutException {
        try {
            switch (ИмяТаблицы.trim().toLowerCase()) {
                case "tabels":
                case "chats":
                case "data_chat":
                case "chat_users":
                case "fio":
                case "tabel":
                case "data_tabels":
                    System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);
                    СтолбикСравнения = ИзменяемыйСтлобикСравенения;
                    break;
            }
            System.out.println("  СтолбикСравнения  " + СтолбикСравнения + " ИзменяемыйСтлобикСравенения " + ИзменяемыйСтлобикСравенения);
            SQLiteCursor   SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID=null;
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ИмяТаблицы);
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки",СтолбикСравнения);
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",СтолбикСравнения+"=? ");
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",СамоЗначениеUUIDилиIDДЛяСравения);
            if(ИмяТаблицы.equalsIgnoreCase("settings_tabels")){
                SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID = (SQLiteCursor) class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID.
                        new GetData(context).getdata(class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций, МенеджерПотоковВнутрений, Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
            }else{
                if (КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторнаяФинал.equalsIgnoreCase("ПовторныйЗапускСинхронизации")) {
                    SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID = (SQLiteCursor) class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID.
                            new GetData(context).getdata(class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций, МенеджерПотоковВнутрений, Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);
                }
                Log.d(this.getClass().getName(), "GetData "+SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID   );
            }
            if (SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID!=null) {
                if ( SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID.getCount() > 0) {
                    SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID.moveToFirst();
                    ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде = SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID.getLong(0);
                    Log.d(this.getClass().getName(), "ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде" + ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде);
                }
                SQLiteCursorКурсор_УзнатьЕслиНаАндройдеТакойID.close();
            }
            class_grud_sql_operationsАнализаUUIDСинхрониазциявФонеДЛяАнализаID.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.clear();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ДействительноЛиIDКоторыйПришелсСервераУжеЕстьНаАндройде;
    }




































    // TODO: 13.09.2021 МЕТОД ОЧИСТКИ ТАБЛИЦ
    public void МетодОчищаемИзБазыNULLЗначенияя(CompletionService МенеджерПотоковВнутрений) {


        Class_GRUD_SQL_Operations class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя;

        Long РезультатУдалениеОчисткиТаблиц=0l;
        try {

            // TODO: 26.03.2021 ДОПОЛНИТЕЛЬНО ОБНУЛЯЕМ ВСЕ ТАБЕЛЯ С NULL В ФИО ЧТО БЫ ОБМЕН НЕ РУГАЛЬСЯ
            //  ССылкаНаСозданнуюБазу.

            ////
            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя=new Class_GRUD_SQL_Operations(context);
            ///
            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя=new Class_GRUD_SQL_Operations(context);


            CopyOnWriteArrayList<String> CopyOnWriteArrayListДляУдалениеТаблиц= new CopyOnWriteArrayList();

            CopyOnWriteArrayListДляУдалениеТаблиц.add("fio");
            ////
            CopyOnWriteArrayListДляУдалениеТаблиц.add("cfo");
            ////
            CopyOnWriteArrayListДляУдалениеТаблиц.add("tabels");
            ////
            CopyOnWriteArrayListДляУдалениеТаблиц.add("fio");
            ////
            CopyOnWriteArrayListДляУдалениеТаблиц.add("tabels");
            ////
            Iterator<String> iteratorДляУдалениеНазваниеТаблиц
                    =CopyOnWriteArrayListДляУдалениеТаблиц.iterator();

            ////
            ArrayList<String> arrayListСтобцыДляУдаления=new ArrayList();


            while (iteratorДляУдалениеНазваниеТаблиц.hasNext()){
                ////
                String ТекущаяНазваниеТаблицыДляУдаления=iteratorДляУдалениеНазваниеТаблиц.next();
                //
                Log.d(this.getClass().getName(), "ТекущаяНазваниеТаблицыДляУдаления"+
                        ТекущаяНазваниеТаблицыДляУдаления);

                //
                switch (ТекущаяНазваниеТаблицыДляУдаления.trim()){

                    case "tabels":
                        //

                        arrayListСтобцыДляУдаления.add("fio");//
                        //
                        arrayListСтобцыДляУдаления.add("cfo");//
                        ///
                        arrayListСтобцыДляУдаления.add("nametabel_typename");//
                        ////
                        arrayListСтобцыДляУдаления.add("uuid");//




                        ///TODO ОБНОЛВЕНИЕ
                        for (int i = 0; i < arrayListСтобцыДляУдаления.size(); i++) {


                            // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","tabels");
                            //

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеУдаление", arrayListСтобцыДляУдаления.get(i)+" IS NULL");
                            ///

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагУдаление","=");


                            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


                            РезультатУдалениеОчисткиТаблиц= (Long)  class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.
                                    new DeleteData(context).deletedata(class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

                            ///
                            ///
                            Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + РезультатУдалениеОчисткиТаблиц);/////

                        }

                        // TODO: 06.09.2021  clsear
                        arrayListСтобцыДляУдаления.clear();

           */
/*                 // TODO: 06.09.2021  _old

                            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM tabels   WHERE fio IS NULL");

                            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM tabels   WHERE cfo IS NULL");

                            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM tabels   WHERE nametabel_typename IS NULL");

                            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM tabels   WHERE uuid IS NULL");*//*



                        break;
///////
                    case "fio":
                        //
                        arrayListСтобцыДляУдаления.add("uuid");//
                        //
                        ///TODO ОБНОЛВЕНИЕ
                        for (int i = 0; i < arrayListСтобцыДляУдаления.size(); i++) {


                            // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","fio");
                            //

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеУдаление", arrayListСтобцыДляУдаления.get(i)+" IS NULL");
                            ///

                            class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагУдаление","=");


                            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


                            РезультатУдалениеОчисткиТаблиц= (Long) class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя.
                                    new DeleteData(context).deletedata(class_grud_sql_operationsОчищаемИзБазыNULLЗначенияя. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы);

                            ///
                            ///
                            Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + РезультатУдалениеОчисткиТаблиц);/////

                        }

                        // TODO: 06.09.2021  clsear
                        arrayListСтобцыДляУдаления.clear();
                        // TODO: 06.09.2021   _old
*/
/*
                            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM fio   WHERE uuid IS NULL");*//*


                        break;
///////

                }


                // TODO: 13.09.2021 выкидываем из очереди после обработки


            }


            Log.d(this.getClass().getName(), "Удаление даных перед Синхронизацией NULL значения ");

            ///////todo\
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


//todo  ПОД КЛАСС  С ГЛАВНМ ЦИКЛОМ ОБМЕНА ДАННЫМИ ТАБЛИЦЫ

    private class Class_Engine_SQL_SubClassMainLoopAsyncTables_КлассСГлавнымЦикломСинхрониазцииТАблиц extends Class_Engine_SQL {


        SQLiteDatabase  Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы=null;

        PUBLIC_CONTENT  public_contentДатыДляГлавныхТаблицСинхронизации;

        Integer ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером;

        Activity ActivityДляСинхронизацииОбмена;

        public Class_Engine_SQL_SubClassMainLoopAsyncTables_КлассСГлавнымЦикломСинхрониазцииТАблиц(@NotNull Context context,
                                                                                                   SQLiteDatabase Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы
                , PUBLIC_CONTENT  public_contentДатыДляГлавныхТаблицСинхронизации,
                                                                                                   Integer ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером
                , Activity ActivityДляСинхронизацииОбмена)
                throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            super(context);

            this.Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы=Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы;

            // TODO: 23.01.2022
            this.public_contentДатыДляГлавныхТаблицСинхронизации= public_contentДатыДляГлавныхТаблицСинхронизации;

            this.ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером = ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером;


            this.ActivityДляСинхронизацииОбмена=ActivityДляСинхронизацииОбмена;


            /// ТУТ МЫ ЗАПУСКАЕМ ЦИКЛ С ПОЛУЧЕНЫМИ ДО  ЭТГО ТАБЛИЦАП КОНКРЕТНО ДЛЯ  ЭТОГО ПОДЛЬЗОВАТЛЯ
            Log.i(this.getClass().getName(), " Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы " + Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы
                    + " public_contentДатыДляГлавныхТаблицСинхронизации= "
                    + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда+
                    " СколькоСтрочекJSON " + ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером +" ActivityДляСинхронизацииОбмена" +ActivityДляСинхронизацииОбмена);

        }

        Integer МетодГлавныхЦиклТаблицДляСинхронизации(String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                       String КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная) throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
            final Integer[] РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри = {0};
            try {
                Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString()
                        + " ДатыТаблицыВерсииДанныхОтСервера "
                        + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.toString()
                        + " СколькоСтрочекJSON " + ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером);
                final Integer[] КоличествоТаблицВОбработке = {1};//
                ПубличныйРезультатОтветаОтСерврераУспешно = 0;
                Observable observableГлавныйЦиклСинхрониазацииПрограммыТабельныйУчёт =
                        Observable.fromIterable(public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда)
                        .subscribeOn(Schedulers.trampoline())
                        .flatMapStream(new Function<String, Stream<?>>() {
                            @Override
                            public Stream<?> apply(String ТекущаяТаблицаИзПотока) throws Throwable {
                                Log.i(this.getClass().getName(), " public_contentМенеджерПотоковВнутрений.МенеджерПотоков " + public_contentДатыДляГлавныхТаблицСинхронизации.МенеджерПотоков + "\n" +
                                        " ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока);
                                ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы = 0;
                                Log.w(this.getClass().getName(), " ПЕРЕД ОБРАБОТКЙОЙ ВСЕХ ТАБЛИЦ ДОЛЖНА БЫТЬ ОТКРЫТОЙ ОБРАБОТКИ ВСЕХ ТАБЛИЦ Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.isOpen() "
                                        + Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.isOpen());
                                try {
                                    Log.d(this.getClass().getName(), " ИменаТаблицыОтАндройда " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size() +
                                            " ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока + " СколькоСтрочекJSON  " + ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером);
                                    //////
                                    String ТекущаяТаблицаДляОБменаДанными = (String) ТекущаяТаблицаИзПотока;
                                    Log.d(this.getClass().getName(), " ТекущаяТаблицаДляОБменаДанными главный цикл " + ТекущаяТаблицаДляОБменаДанными + "\n" +
                                            " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем + "\n" +
                                            "\n" + " Thread.currentThread().getName() " + Thread.currentThread().getName() + "\n" + "############################################################" + "\n");
                                    // TODO: 24.01.2022
                                    if(ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером ==null){
                                        ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером =0;
                                    }

                                    РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0] = МетодЗапускаСинхрониазцииПоАТблицам(ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                            ТекущаяТаблицаДляОБменаДанными, КакойРежимСинхрониазцииПерваяСинхронизациИлиПовторная,
                                            public_contentДатыДляГлавныхТаблицСинхронизации.МенеджерПотоков,
                                            ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером,public_contentДатыДляГлавныхТаблицСинхронизации
                                            ,ActivityДляСинхронизацииОбмена);
                                    Log.d(this.getClass().getName(), " ТекущаяТаблицаДляОБменаДанными " + ТекущаяТаблицаДляОБменаДанными +
                                            "  ЗАКОНЧИЛИ ОБРАБОТКУ ОТДЕЛЬНОЙ ТАЛИЦЫ  ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА " +
                                            ТекущаяТаблицаДляОБменаДанными + " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА "
                                            + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА + "  РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри "
                                            + РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0] + " СколькоСтрочекJSON " + ГлавноеКоличествоОбрабоатываемыхТаблицОбменаССервером);
                                    Log.i(this.getClass().getName(), "  PUBLIC_CONTENT.ИменаТаблицыОтАндройда.size()   " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size() +
                                            " РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри " + РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0]);
                                    ПубличныйРезультатОтветаОтСерврераУспешно = РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0];
                                    Log.d(this.getClass().getName(), " Не допушена к Обработке РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри "
                                            + РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0] +
                                            " ПубличныйРезультатОтветаОтСерврераУспешно " + ПубличныйРезультатОтветаОтСерврераУспешно);
                                    РезультатВерсииДанныхЧатаНаСервере = 0l;
                                    Log.d(this.getClass().getName(), " public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда "
                                            + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда);


                                    // TODO: 25.09.2022  Удаление удаленного статуса записей
                                    МетодУдадениеЗаписейСоСтатусомУДАЛЕННЫЙ(ТекущаяТаблицаДляОБменаДанными, РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    // TODO: 01.09.2021 метод вызова
                                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                return  Observable.fromIterable(public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда).blockingStream().peek(emmer->System.out.println(emmer));
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "   doOnError  ОШИБКА В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ .111!!!!!!!!!throwable "
                                        + throwable.getStackTrace().toString());
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                Log.i(this.getClass().getName(), " doOnTerminate  CLOSE ЗАКРЫВАЕМ БАЗУ ПОСЛЕ ОБРАБОТКИ ВСЕХ ТАБЛИЦ Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.isOpen() "
                                        + Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.isOpen());
                                /// todo после обработки
                                if (Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.isOpen()) {
                                    Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы.close();
                                    Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы = null;
                                }

                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "   onErrorComplete  ОШИБКА В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ .111!!!!!!!!!throwable "
                                        + throwable.getStackTrace().toString());
                                return false;
                            }
                        });
                Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЗАВЫЕРШИЛАСЬ В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ ");
                observableГлавныйЦиклСинхрониазацииПрограммыТабельныйУчёт.blockingSubscribe(System.out::println);
                Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЗАВЫЕРШИЛАСЬ В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0];
        }

        private void МетодУдадениеЗаписейСоСтатусомУДАЛЕННЫЙ(String ТекущаяТаблицаДляОБменаДанными, Integer[] РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри) {
            try{
            if(   РезультатОбработкиТекущейтаблицыСинхрониазцииВнутриЦиклаВнутри[0]>0){
                Intent intentУдалениеСтатусаУдаленияТабеля=new Intent();
                if( ТекущаяТаблицаДляОБменаДанными.equalsIgnoreCase("data_tabels")){
                    intentУдалениеСтатусаУдаленияТабеля.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                    intentУдалениеСтатусаУдаленияТабеля.putExtra("Таблица","data_tabels");
                    // TODO: 26.09.2022
                    intentУдалениеСтатусаУдаленияТабеля.setClass(context, Service_For_Remote_Async.class);
                    context.  startService(intentУдалениеСтатусаУдаленияТабеля);
                }
                if( ТекущаяТаблицаДляОБменаДанными.equalsIgnoreCase("tabel")){
                    intentУдалениеСтатусаУдаленияТабеля.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                    intentУдалениеСтатусаУдаленияТабеля.putExtra("Таблица","tabel");
                    // TODO: 26.09.2022
                    intentУдалениеСтатусаУдаленияТабеля.setClass(context, Service_For_Remote_Async.class);
                    context.  startService(intentУдалениеСтатусаУдаленияТабеля);
                }
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
        }


    }


    // TODO: 22.03.2022  ДЛЯ ОТПРАВКИ ДАННЫХ НА СЕРВЕР


    private class SubClass_Klacc_Otprabki_класс_ОтправкиДанных extends Class_Engine_SQL {
        public SubClass_Klacc_Otprabki_класс_ОтправкиДанных(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            super(context);
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
                Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
                // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
                Log.d(this.getClass().getName(), " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() "
                        + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() +
                        " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length  "
                        + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length +
                        " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
                    БуферОтправкаДанныхвФоне = УниверсальныйБуферОтправкиДанныхНаСервера(ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда,
                            ПубличноеIDПолученныйИзСервлетаДляUUID, имяТаблицыОтАндройда_локальноая,
                            "Получение JSON файла от Андройда",
                            60000, public_content.getАдресСервера() , public_content.getПортСервера());
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
                                МетодПолучениеОтветаотСервераПослеОперацийВставкиИлиОбновленияИПолучениеМаксиальнойВерсии(
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
                if (ДанныеПришёлВОтветОтМетодаPOST.length() > 0) {
                    МетодАнализаОтветаОтСервераУспешныеВставкиИлиОбновлениевФоне(ДанныеПришёлВОтветОтМетодаPOST, имяТаблицыОтАндройда_локальноая, МенеджерПотоковВнутрений);
                }
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
        private Integer МетодПолучениеОтветаотСервераПослеОперацийВставкиИлиОбновленияИПолучениеМаксиальнойВерсии
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

                //TODO
                // then

                РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = ФинальныСписокЦифр
                        .stream()
                        .mapToInt(v -> v)
                        .max().orElse(0);



                //TODO

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }
            return РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера;
        }

    }
}
*/
