package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;

import java.util.concurrent.ExecutionException;

public class Class_Begin_Update_End_Insert_Data_Project {

    Context contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить;
    //todo

    PUBLIC_CONTENT public_contentcГдеНаходитьсяМенеджерПотоков=null;

    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;

    public Class_Begin_Update_End_Insert_Data_Project(Context context) {

        contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить = context;
        // TODO: 13.10.2021
        public_contentcГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить);
        //

        Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить);

    }

    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    /////TODO МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
    public Object МетодСначалоПытаемсяОбновлитьЕслиНеВышлоВставляемДанныхПроекта(String ОбработываемаяТаблица,
                                                                                 ContentValues КонтейнерДляВставкиИлиОбвноления,
                                                                                 Integer ЗначениеДляОбновления,
                                                                                 String ПолеЧерезКотороеНужноОбновлять) {//"SuccessLogin"/////МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
        ///
        Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций = null;

        Object Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;

        try {
            ////НАЧАЛО ВСТКИ И ОЧИСТКИ ДАННЫХ ПО ВСТКАЕ ИМЕНИ  И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ ПОЛЬЗОВАТЕЛЯ БОЛЕЕ 7 ДНЕЙ
            ////

            ///
            // /// для очистки

            ////TODO ДАТА
            String ДатаДЛяОчисткиИВстсвкиИмениИПароль=
                    new Class_Generation_Data(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить).ГлавнаяДатаИВремяОперацийСБазойДанных();
            //////////////////////////////////////////////////////////////

            // TODO: 30.08.2021    КОД ВСТАВКИ  ДАННЫХ   ЧЕРЕЗ

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций =
                    new Class_GRUD_SQL_Operations(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить);


            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ОбработываемаяТаблица );////"SuccessLogin"
            ///
            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением", ПолеЧерезКотороеНужноОбновлять);//"id"
            ///
            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления", ЗначениеДляОбновления);
            ///

            //

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","="); //или =   или <   >



            //TODO коййтенер


            ///

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиИлиОбвноления);


       /*     ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
            Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = (Integer) concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    new UpdateData(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить).updatedata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    public_contentcГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                    Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//

            Log.d(this.getClass().getName(), "        Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации=0L; " +
                    Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


            ////
            if (Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации == null) {
                ////
                Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;
            }
            /////


            // TODO: 02.09.2021если успешно прошла обновление если нет то ниже вроизвонитм вставку
            if (  (Integer) Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации  > 0) { //ПОДТЕРЖДАЕМ ТОЛЬКО ВСТАВКУ НОВГО ИМЕНИ И ПАРОЛЯ
                // ССылкаНаСозданнуюБазу.

                // TODO: 02.09.2021  успешная обновление данных
                Log.d(this.getClass().getName(), "Запущен.... метод  onCreate в классе Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации  ; " +
                        Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


                //TODO  ессли не удалось обновить то идем вставлять
            } else {*/




                ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
                Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации =
                        (Long) concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                        new InsertData(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить)
                        .insertdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        public_contentcГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//

                Log.d(this.getClass().getName(), "Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации "
                        + Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


                ////
                if (Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации == null) {
                    ////
                    Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;
                }
                /////

                // TODO: 02.09.2021  успешная вставки данных



        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(contextДляКлассДляВставкиДанныхВПроектеСначалаМыПытаемсяОбновитьАПотомВставить).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

        return Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации;
        ////// конец записываем полученный имя и пароль во временную таблицу ДЛЯ ТОГО ЧТОБЫ ЕСЛИ НУЖНО ЧЕРЕЗ  7 ДНЕЙ ПОТРЕБУЕТЬСЯ
    }
}
