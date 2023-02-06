package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Class_Send_Generation_Errors {

    Context contextДляКлассаПосылаемОшибкиНАСервер;
    //
    Activity activity;
    //
    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;

    public Class_Send_Generation_Errors(Context context, String СамаОшибка, Activity activityВнутри) {

        contextДляКлассаПосылаемОшибкиНАСервер=context;

        activity=   activityВнутри;


        StringBuffer БуферОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе=new StringBuffer(СамаОшибка);



        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(contextДляКлассаПосылаемОшибкиНАСервер);
        ///
        class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");

        Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;

        // TODO: 12.10.2021  Ссылка Менеджер Потоков

        PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(contextДляКлассаПосылаемОшибкиНАСервер);


        ///////
        SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
        try {
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(contextДляКлассаПосылаемОшибкиНАСервер).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
            ////
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

            /////
            ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);
///


            Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


        }





    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 21.10.2021


}
