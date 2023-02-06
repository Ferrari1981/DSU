package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SubClassGET_FIO {

    Context context;

    public SubClassGET_FIO(Context context) {

        this.context = context;

        Log.d(context.getClass().getName(), "context " + context);
    }

    public String МетодПолучениеФИОНАОснованииIDВыбранногоСотрудника(int ПуличныйIdДляВычисленияКтоНаписал) {


        Cursor КурсорДанныеДляКонтактовФИОЧата = null;
        ////
        StringBuffer ПолученыйФИОIDДляЧата = new StringBuffer();
        /////
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть;

        PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков=new PUBLIC_CONTENT(context);

        SQLiteDatabase sqLiteDatabaseКлонДляПоискаФИО=new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу();

        Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsФрагментМСообщения;

        try {


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть = new Class_GRUD_SQL_Operations(context);
            // TODO: 14.02.2022
            class_grud_sql_operationsФрагментМСообщения= class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.new GetData(context);

            ///
            class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "Chat_Users");
            ///////
            class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "name");
            //
            class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "_id =? ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ПуличныйIdДляВычисленияКтоНаписал);
            ///
         /*       class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                ///
                class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                //
                class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");*/
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            КурсорДанныеДляКонтактовФИОЧата = null;

            ////////

            КурсорДанныеДляКонтактовФИОЧата = (SQLiteCursor)class_grud_sql_operationsФрагментМСообщения
                    .getdata(class_grud_sql_operationsПолучениеФИОНАОснованииIDВыбранногоСотрудникаЧетвертаяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, sqLiteDatabaseКлонДляПоискаФИО);

            Log.d(context.getClass().getName(), "GetData " + КурсорДанныеДляКонтактовФИОЧата);










       /*     // TODO: 08.09.2021  _old

            КурсорДанныеДляКонтактовФИОЧата= new MODEL(getActivity()).
                    МетодПолучениеДанныхДляФрагментаСообщенияЧата(" SELECT name  FROM  Chat_Users   WHERE _id =" + ПуличныйIdДляВычисленияКтоНаписал + ""); ///Chat_Users /fio


*/
            //////TODO resultat
            if (КурсорДанныеДляКонтактовФИОЧата.getCount() > 0) {
                ///
                КурсорДанныеДляКонтактовФИОЧата.moveToFirst();
                ///
                int ИндексФИо = КурсорДанныеДляКонтактовФИОЧата.getColumnIndex("name");
                ////

                // TODO: 08.09.2021  цикл
                do {
                    ////

                    //
                    ПолученыйФИОIDДляЧата.append(КурсорДанныеДляКонтактовФИОЧата.getString(ИндексФИо).trim()).append(",").append("\n");
                    ;

                    Log.d(context.getClass().getName(), "ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);


                    // TODO: 30.06.2021 выход

                    if (ПолученыйФИОIDДляЧата.length() > 0) {
                        /////

                        Log.d(context.getClass().getName(), "ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);
                        ///
                        break;
                    }

                } while (КурсорДанныеДляКонтактовФИОЧата.moveToNext());
                // TODO: 22.07.2021  заполнили всеми фио

                ПолученыйФИОIDДляЧата.setLength(ПолученыйФИОIDДляЧата.length() - 2);
                ////

///////////////
            }

            Log.d(context.getClass().getName(), "ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);
            /////////

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), context.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }

        return ПолученыйФИОIDДляЧата.toString();
    }
}
