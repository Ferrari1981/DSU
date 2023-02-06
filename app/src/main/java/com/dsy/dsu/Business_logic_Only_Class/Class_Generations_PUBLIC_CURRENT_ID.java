package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class Class_Generations_PUBLIC_CURRENT_ID {
    Context contextДляКлассПУБЛИЧНЫЙID;
    //todo функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer ПолучениеПубличногоТекущегоПользователяID(Context context) {
        ///TODO --первая вставка
        this.contextДляКлассПУБЛИЧНЫЙID=context;
        Integer ПубличныйIDДляФрагмента = 0;
        try{
            if (contextДляКлассПУБЛИЧНЫЙID!=null) {
                SQLiteCursor Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения = null;
                Class_GRUD_SQL_Operations class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть = new Class_GRUD_SQL_Operations(contextДляКлассПУБЛИЧНЫЙID);
                class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                        " SELECT id FROM SuccessLogin ORDER BY date_update DESC LIMIT 1 ");
                Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения = (SQLiteCursor) class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.
                        new GetаFreeData(contextДляКлассПУБЛИЧНЫЙID).getfreedata(class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        new PUBLIC_CONTENT(contextДляКлассПУБЛИЧНЫЙID).МенеджерПотоков
                        , new CREATE_DATABASE(contextДляКлассПУБЛИЧНЫЙID).getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "GetData " + Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения);
                // TODO: 09.09.2021 resultat
                if (Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения.getCount() > 0) {
                    Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения.moveToFirst();
                    ПубличныйIDДляФрагмента = Курсор_ВычисляемПУбличныйIDПриСозданииНовогоСообщения.getInt(0);
                    Log.d(this.getClass().getName(), "  ФИНАЛ ФОНОВАЯ  СИНХРОНИЗАЦИИ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ полученный публичный id ПЕРЕД ВСТАВКОЙ"
                            + " ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextДляКлассПУБЛИЧНЫЙID).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ПубличныйIDДляФрагмента;
    }
}