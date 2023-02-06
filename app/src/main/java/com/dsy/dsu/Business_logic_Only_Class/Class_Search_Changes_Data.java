package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

public class Class_Search_Changes_Data {

    Context contextДляКлассаИщемБылеИзменениевБазе;

    ///////TODO
    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;

    ////
    public Class_Search_Changes_Data(Context context) {

        contextДляКлассаИщемБылеИзменениевБазе=context;



///////TODO
           Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(contextДляКлассаИщемБылеИзменениевБазе);
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ


    public boolean МетодВычислемБылиИзменениВДанныхВДанныхПоДатам(String КакиеТаблицаОбработкиЧерезДату) {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        boolean РезультатБылиБЫИзмегнениевБазе = false;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель = new Class_GRUD_SQL_Operations(contextДляКлассаИщемБылеИзменениевБазе);
        try {
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "localversionandroid_version,versionserveraandroid_version");
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "name=?  ");
        ///"_id > ?   AND _id< ?"
        //////
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",КакиеТаблицаОбработкиЧерезДату.trim());
        ///
              /*      class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

        ////TODO другие поля

        ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            /*////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");*/
        ////
        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(contextДляКлассаИщемБылеИзменениевБазе);
            SQLiteCursor    Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = (SQLiteCursor) class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.
                new GetData(contextДляКлассаИщемБылеИзменениевБазе).getdata(class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
        Log.d(this.getClass().getName(), "GetData " + Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель);
        if (Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getCount() > 0) {
            Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.moveToFirst();
            Long ЛокальнаяДатаИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getLong(0);
            Long СервернаяДатаИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getLong(1);
            Log.d(this.getClass().getName(), "СервернаяДатаИзменений " + СервернаяДатаИзменений + "  ЛокальнаяДатаИзменений " + ЛокальнаяДатаИзменений);
            Log.d(this.getClass().getName(), "СервернаяДатаИзменений " + СервернаяДатаИзменений + "  ЛокальнаяДатаИзменений " + ЛокальнаяДатаИзменений);
            if (ЛокальнаяДатаИзменений > СервернаяДатаИзменений) {
                РезультатБылиБЫИзмегнениевБазе=true;
                Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " +РезультатБылиБЫИзмегнениевБазе);
            }else {
                РезультатБылиБЫИзмегнениевБазе=false;
                Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " + РезультатБылиБЫИзмегнениевБазе);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(contextДляКлассаИщемБылеИзменениевБазе).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  РезультатБылиБЫИзмегнениевБазе;
    }








    // TODO: 26.09.2021  проверка если изменения через верисс

    boolean МетодВычислемБылиИзменениВДанныхВДанныхЧерезВерсииДанных(String КакиеТаблицаОбработкиЧерезДату) {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        boolean РезультатБылиБЫИзмегнениевБазе=false;

        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных = new Class_GRUD_SQL_Operations(contextДляКлассаИщемБылеИзменениевБазе);

        try{


            ///
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
            ///////
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "localversionandroid_version,versionserveraandroid_version");
            //
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "name=?  ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",КакиеТаблицаОбработкиЧерезДату.trim());
            ///
              /*      class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            /*////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");*/
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            SQLiteCursor Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = null;



            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (contextДляКлассаИщемБылеИзменениевБазе);

            ///

            Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = (SQLiteCursor) class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.
                    new GetData(contextДляКлассаИщемБылеИзменениевБазе).
                    getdata(class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            //////////////


            Log.d(this.getClass().getName(), "GetData " + Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель);




/*

            // TODO: 09.09.2021  ___old

           Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель=
                    new Class_MODEL_synchronized(Контекст).КурсорУниверсальныйБазыДанных("SELECT localversionandroid,versionserveraandroid FROM MODIFITATION_Client WHERE name = 'tabels' ");


*/


///TODO РЕЗУЛЬТАТ
            if (Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getCount() > 0) {
                ///
                Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.moveToFirst();

                Integer ЛокальнаяВерсияИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getInt(0);
                //

                Integer СервернаяВерсияИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getInt(1);
                /////////////
                Log.d(this.getClass().getName(), "ЛокальнаяВерсияИзменений " + ЛокальнаяВерсияИзменений + "  СервернаяВерсияИзменений " + СервернаяВерсияИзменений);


                ////

                if (ЛокальнаяВерсияИзменений>СервернаяВерсияИзменений) {

                    РезультатБылиБЫИзмегнениевБазе=true;

                    /////////////
                    Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " +РезультатБылиБЫИзмегнениевБазе);
                }else {

                    РезультатБылиБЫИзмегнениевБазе=false;

                    /////////////
                    Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " + РезультатБылиБЫИзмегнениевБазе);


                }
            }


            //

            //////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            /////////
            new   Class_Generation_Errors(contextДляКлассаИщемБылеИзменениевБазе).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

         //   Log.e(contextДляКлассаИщемБылеИзменениевБазе.getClass().getName(), " Стоп СЛУЖБА MyWork_Async_Синхронизация_Общая из FaceApp в MyWork_Async_Синхронизация_Общая Exception  ошибка в классе MyWork_Async_Синхронизация_Общая" + e.toString());


        }
        return  РезультатБылиБЫИзмегнениевБазе;
    }

}
