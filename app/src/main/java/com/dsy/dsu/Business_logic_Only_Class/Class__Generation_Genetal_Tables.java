package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Class__Generation_Genetal_Tables {
    Context context;
    //TODO
    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    public Class__Generation_Genetal_Tables(Context context) {
        this.context =context;
        Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public CopyOnWriteArrayList<String> МетодЗаполеннияТаблицДЛяРаботыиСинхрониазции() {
        PUBLIC_CONTENT public_contentГлавныеТаблицыПроектаСинхрониазции = new PUBLIC_CONTENT(context);
        try {
            // TODO: 11.03.2022 end test code
            public_contentГлавныеТаблицыПроектаСинхрониазции.ИменаТаблицыОтАндройда=    new PUBLIC_CONTENT(context).  МетодТОлькоЗаполенияНазваниямиТаблицДляОмена(context);
            // TODO: 02.03.2022  --для согласования
            Log.d(context.getClass().getName(), "  PUBLIC_CONTENT.ИменаТаблицыОтАндройда СЛУЖБА  " +
                    public_contentГлавныеТаблицыПроектаСинхрониазции.ИменаТаблицыОтАндройда.size());
            ////todo
            // TODO: 02.09.2021  запускаем по новому работа через GRUD-класс нашех операций
            Class_GRUD_SQL_Operations cachedThreadPoolВизуальнаяСинхронизация = new Class_GRUD_SQL_Operations(context);
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            String  НазваниеТаблицыДляОбработки="MODIFITATION_Client";
            ///
            cachedThreadPoolВизуальнаяСинхронизация. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",НазваниеТаблицыДляОбработки);
            ///////
            cachedThreadPoolВизуальнаяСинхронизация. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
           // TODO: 12.10.2021  Ссылка Менеджер Потоков

           PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);


            //TODO получаем данные сотсояние таблиц на клиенте если огни вообще

            SQLiteCursor sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц= (SQLiteCursor)  cachedThreadPoolВизуальнаяСинхронизация.
                    new GetData(context).getdata(cachedThreadPoolВизуальнаяСинхронизация. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц );


            // TODO: 03.09.2021 ЕСЛИ, В ТАБЛИЦЕ МОДИФИКАШЕНС КЕРСИОН НЕТ ТАБЛИЦ ДЛЯ ОБРАБОТКИ  И ВЕРНУЛЬСЯ КУРСОР 0   ТО НИЖЕ, КОДОМ ВСТАВЛЯЕМ НЕ ДОСТАЮЩТИЕ ТАБЛИЦЫ

            if (sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц.getCount()==0){

                sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц.moveToFirst();
                //
                Log.d(this.getClass().getName()," sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц   "+sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц.getCount());



                Log.d(this.getClass().getName()," sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц   "+sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц.getCount());


                // TODO: 02.09.2021  цикл по данным

                Iterator iteratorТаблицОбработкиСинхронизации =   public_contentГлавныеТаблицыПроектаСинхрониазции.ИменаТаблицыОтАндройда.iterator();
                //
                String ТекущаяТаблицаДляОбработки=new String();
                ////
                while (iteratorТаблицОбработкиСинхронизации.hasNext()) {

                    try {
                        ТекущаяТаблицаДляОбработки= (String) iteratorТаблицОбработкиСинхронизации.next();
                        //
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.d(this.getClass().getName(), "  ТекущаяТаблицаДляОбработки"+ТекущаяТаблицаДляОбработки );


                    // TODO: 02.09.2021  запускаем по новому работа через GRUD-класс нашех операций  ВТОРАЯ ЧАСТЬ ЗАПИСЫВАЕМ ДАННЫЕ В БАЗУ
                    cachedThreadPoolВизуальнаяСинхронизация=new Class_GRUD_SQL_Operations(context);

                    //TODO ТОЛЬКО ТАБЛИЦА ДЛЯ ВСТАВКИ
                    cachedThreadPoolВизуальнаяСинхронизация. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",НазваниеТаблицыДляОбработки);

                    //TODO ДАННЫЕ  ДЛЯ ВСТАВКИ

                    cachedThreadPoolВизуальнаяСинхронизация.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("name",ТекущаяТаблицаДляОбработки);


                    ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ


                    Long     PезультатВставки= (Long)  cachedThreadPoolВизуальнаяСинхронизация.
                            new InsertData(context).insertdata(cachedThreadPoolВизуальнаяСинхронизация. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            cachedThreadPoolВизуальнаяСинхронизация.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//

                    Log.d(this.getClass().getName(), "результатВставки " +PезультатВставки );

                    ////
                    if(PезультатВставки==null){
                        ////
                        PезультатВставки=0l;
                    }
                    /////
                    // TODO: 02.09.2021  результат вставки данных в базу


                    if (PезультатВставки>0) {
                        ///
                        Log.d(this.getClass().getName(), "результатВставки  Успешно " + PезультатВставки);

                    }else{
                        ///
                        Log.d(this.getClass().getName(), "результатВставки Не Успешно  " + PезультатВставки);
                    }
                    // TODO: 11.10.2021 HOLD queue
                }//TODO конец цикла фор

            }else{
                Log.d(this.getClass().getName()," sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц   "+sqLiteCursorПроаерчемЕслиЛиСамиНазванияТаблиц.toString());
            }


            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return      public_contentГлавныеТаблицыПроектаСинхрониазции.ИменаТаблицыОтАндройда;
    }



}
