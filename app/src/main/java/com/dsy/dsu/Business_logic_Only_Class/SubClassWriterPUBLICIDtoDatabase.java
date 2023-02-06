package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;

public class SubClassWriterPUBLICIDtoDatabase {

    Context context;

    public SubClassWriterPUBLICIDtoDatabase() {
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Long МетодЗапипиВБАзуПубличногоID(Context context,Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу,String ПубличноеИмяПользовательДлСервлета,String ПубличноеПарольДлСервлета) {
        Long результатЗаписиНовогоПароляПользователявБазцуsuccesslogin = 0l;
        try{
       ContentValues contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin=new ContentValues();
       contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin.put("id", ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin.put("success_users", ПубличноеИмяПользовательДлСервлета);
       ///
       contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin.put("success_login",ПубличноеПарольДлСервлета);
       Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "
               + ПолученинныйПубличныйIDДлчЗаписиВБАзу +
               " ПубличноеПарольДлСервлета" + ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       ////TODO ДАТ
       String ДатаДЛяОчисткиИВстсвкиИмениИПароль=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
       contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin.put("date_update", ДатаДЛяОчисткиИВстсвкиИмениИПароль);
       // TODO: 29.12.2021  запись в  полученого публичного клюяа в две таблицы suceess login  и таблицу настроеки   ПЕРВАЯ ЧАСТЬ ОПЕРАЦИИ
       //// todo после успешного получение имени и пароля записываем их в базу ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
        результатЗаписиНовогоПароляПользователявБазцуsuccesslogin = (Long)
               new Class_Begin_Update_End_Insert_Data_Project(context).
                       МетодСначалоПытаемсяОбновлитьЕслиНеВышлоВставляемДанныхПроекта("SuccessLogin",
                               contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin,
                               ПолученинныйПубличныйIDДлчЗаписиВБАзу ,"id");
       ////ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
       // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ
       Log.d(this.getClass().getName(),
               " УСПЕШАЯ ЗАПИСЬ ПУБЛИЧНОГО id SUCCEESS !!!!  ТАБЛИЦА successlogin  " +
               "результатЗаписиНовогоПароляПользователявБазцуsuccesslogin " + результатЗаписиНовогоПароляПользователявБазцуsuccesslogin+
               " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       // TODO: 29.12.2021  ВТОРАЯ ЗАПИСЬ В ДРУГУЮ ТАБЛИЦУ ТАБЛИЦА НАСТРОЕК ВТОРАЯ ЧАСТЬ ОПЕРАЦИИ
       contentValuesДляУспешнойВставкиПароляБолее7ДнейЗаписываемВБАзуВТаблицу_successlogin.clear();
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатЗаписиНовогоПароляПользователявБазцуsuccesslogin;
    }
}
