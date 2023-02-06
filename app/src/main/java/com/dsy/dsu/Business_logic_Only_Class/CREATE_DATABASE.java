
//////КНАЧАЛО  КЛАССА ПО СОЗДАНИЮ СХЕМЫ ДАННЫХ
package com.dsy.dsu.Business_logic_Only_Class;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicDouble;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

//этот класс создает базу данных SQLite
public class CREATE_DATABASE extends SQLiteOpenHelper{ ///SQLiteOpenHelper
     static final int VERSION =      964 ;//ПРИ ЛЮБОМ ИЗМЕНЕНИЕ В СТРУКТУРЕ БАЗЫ ДАННЫХ НУЖНО ДОБАВИТЬ ПЛЮС ОДНУ ЦИФРУ К ВЕРСИИ 1=1+1=2 ИТД.1
   private   Context context;
    private      SQLiteDatabase ССылкаНаСозданнуюБазу;
    private     CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда;

    public SQLiteDatabase getССылкаНаСозданнуюБазу() {
        Log.d(this.getClass().getName()," get () БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫТА ССылкаНаСозданнуюБазу.isOpen()  " +ССылкаНаСозданнуюБазу);
        return ССылкаНаСозданнуюБазу;
    }
    ///////КОНСТРУКТОР главного класса по созданию базы данных
    public CREATE_DATABASE( @NotNull Context context) {/////КОНСТРУКТОР КЛАССА ПО СОЗДАНИЮ БАЗЫ ДАННЫХ
        super(context, "Database DSU-1.db", null, VERSION ); // определяем имя базы данных  и ее версию
        try{
            this.context =context;
            if (ССылкаНаСозданнуюБазу==null  ) {
                ССылкаНаСозданнуюБазу = this.getWritableDatabase(); //ссылка на схему базы данных;//ссылка на схему базы данных ГЛАВНАЯ ВСТАВКА НА БАЗУ ДСУ-1
                Log.d(this.getClass().getName()," БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫВАЕМ  ССылкаНаСозданнуюБазу==null   "
                        +ССылкаНаСозданнуюБазу.isOpen());
            }else{
                //TODO connection  else is onen false
                if (!ССылкаНаСозданнуюБазу.isOpen()) {
                    ССылкаНаСозданнуюБазу = this.getWritableDatabase(); //ссылка на схему базы данных;//ссылка на схему базы данных ГЛАВНАЯ ВСТАВКА НА БАЗУ ДСУ-1
                    Log.d(this.getClass().getName()," БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫВАЕМ  ССылкаНаСозданнуюБазу.isOpen()  "
                            +ССылкаНаСозданнуюБазу.isOpen());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    //  Cоздание ТАблиц
    @Override
    public void onCreate(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try {
            Log.d(this.getClass().getName(), "сработала ... НАЧАЛО  СОЗДАНИЯ ТАЛИЦ ");
            // TODO: 24.10.2022 Генерируем Список Таблиц
            ИменаТаблицыОтАндройда=    new SubClassCreatingMainAllTables(context).  МетодТОлькоЗаполенияНазваниямиТаблицДляОмена(context);

            // TODO: 12.10.2022  создание TABLE
            МетодСозданиеСистемнойТаблицыMODIFITATION_Client(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыОшибок(ССылкаНаСозданнуюБазу);
            МетодСозданияСистемнойТаблицыСФО(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыЛогинов(ССылкаНаСозданнуюБазу);
            МетодСозданиеОрганизацииТаблицы(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыДепартамент(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыФИО(ССылкаНаСозданнуюБазу);
            МетодСозданияТаблицыРегион(ССылкаНаСозданнуюБазу);
            МетодСоздания_ТаблицыТабель(ССылкаНаСозданнуюБазу);
            МетодСоздания_ТаблицыДатаТабель(ССылкаНаСозданнуюБазу);
            МетодСозданиеМетокТабеля(ССылкаНаСозданнуюБазу);
            МетодСозданиеSettingTabels(ССылкаНаСозданнуюБазу);
            МетодСозданиеУведомленийИлиЗадания(ССылкаНаСозданнуюБазу);
            МетодСозданииТаблицыДатаФорк(ССылкаНаСозданнуюБазу);
            МетодСозданияТаблицыТемплейШаблон(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыFio_TEmplay(ССылкаНаСозданнуюБазу);
            МетодСозданияТаблицаChats(ССылкаНаСозданнуюБазу);
            МетодСозданияТаблицыData_Chat(ССылкаНаСозданнуюБазу);
            МетодСозданияПольЗовательДЛяЧата(ССылкаНаСозданнуюБазу);
            МетодСозданиеУведомленийИлиДАТАЗадания(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицыСогласование(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицаДанныеПолученныхМатериалов(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицаType_materials(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицаСправочиникNomen_vesov(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицаДанныеТрак(ССылкаНаСозданнуюБазу);
            МетодСозданиеТаблицаДанныеКомпания(ССылкаНаСозданнуюБазу);

            // TODO: 12.10.2022  создание VIEW
            МетодСозданияВидаЧатаViewChat(ССылкаНаСозданнуюБазу);
            МетодСозданияВидаЗадания(ССылкаНаСозданнуюБазу);
            МетодСозданиеview_onesignal(ССылкаНаСозданнуюБазу);
            МетодСозданияViewТабеля(ССылкаНаСозданнуюБазу);
            МетодСозданиеViewПолученныхМатериалов(ССылкаНаСозданнуюБазу);
            МетодСозданиеViewПолученныхМатериаловGroup(ССылкаНаСозданнуюБазу);

            МетодСозданиеТрирераМодификаценКлиент(ССылкаНаСозданнуюБазу,ИменаТаблицыОтАндройда);
            Log.d(this.getClass().getName(), " сработала ... КОНЕЦ СОЗДАНИЯ ТАБЛИЦ " +new Date().toGMTString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }}

    private void МетодСозданияТаблицыТемплейШаблон(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists templates");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'templates'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    templates  (" +
                "id  INTEGER ," +
                " name_templates TEXT  ," +
                " user_update INTEGER ,"+
                " date_update NUMERIC,"+
                " uuid  NUMERIC UNIQUE  ," +
                " status_send  TEXT," +
                "  current_table NUMERIC UNIQUE   ) ");
    }

    private void МетодСозданииТаблицыДатаФорк(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists date_work");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'date_work'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    date_work  (" +
                "id  INTEGER  ," +
                "date_work NUMERIC,"+
                " date_update NUMERIC)");
        /////todo встака данных по умолчанию
        /////todo встака данных по умолчанию
        // ССылкаНаСозданнуюБазу.execSQL("INSERT INTO date_work (id) VALUES('1')");
        ////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы date_work");
    }

    private void МетодСозданияТаблицыРегион(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        //таблица  region     //таблица   cfo     //таблица cfo      //таблица   cfo
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists  region");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'region'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists   region (" +
                "id  INTEGER     ," +
                " name TEXT ," +
                " date_update NUMERIC  ," +
                " user_update INTEGER ," +
                "  current_table NUMERIC UNIQUE , " +
                " uuid NUMERIC  UNIQUE "+
                " )");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы  region");
    }

    private void МетодСозданияСистемнойТаблицыСФО(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists   cfo");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'cfo'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists   cfo (" +
                "_id  INTEGER     ," +
                " name TEXT ," +
                " region  INTEGER ," +
                " boss  INTEGER ," +
                " kod TEXT  ," +
                " date_update NUMERIC  ," +
                " user_update INTEGER  ," +
                " closed INTEGER  ," +
                "  current_table NUMERIC UNIQUE ," +
                " organization INTEGER DEFAULT 1 ," +
                " uuid NUMERIC UNIQUE  ,"+
                "FOREIGN KEY(boss) REFERENCES fio   (_id)  ON UPDATE CASCADE)");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы   cfo");
    }

    private void МетодСозданиеТаблицыДепартамент(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        //таблица depatment         //таблица depatment         //таблица depatment         //таблица depatment
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists depatment");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'depatment'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists depatment (" +
                "id  INTEGER    ," +
                " name TEXT  ," +
                " organization INTEGER ," +
                " date_update NUMERIC," +
                " user_update TEXT," +
                "  current_table NUMERIC UNIQUE   ," +
                " uuid NUMERIC UNIQUE  ,"+
                "FOREIGN KEY(organization) REFERENCES organization  (id)  ON UPDATE CASCADE)");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы depatment");
    }

    private void МетодСозданиеОрганизацииТаблицы(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        //таблица organization            //таблица organization            //таблица organization            //таблица organization
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists organization");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0' ,versionserveraandroid_version='0' WHERE name =  'organization'");//test
        /////
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists organization (" +
                "id  INTEGER    ," +
                " name TEXT  ," +
                " fullname  TEXT ," +
                " inn TEXT ," +
                " kpp  TEXT  ," +
                " date_update  NUMERIC," +
                "  user_update   INTEGER ," +
                " chosen_organization INTEGER ," +
                "  current_table NUMERIC UNIQUE , " +
                " uuid NUMERIC UNIQUE " +
                ")");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы organization");

        //TODO


    }

    private void МетодСозданиеУведомленийИлиЗадания(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // todo таблица Notifications
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists notifications");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'notifications'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    notifications  (" +
                "id  INTEGER ," +
                " date_update NUMERIC,"+
                " user_update INTEGER ,"+
                "uuid  NUMERIC UNIQUE ,"+
                "current_table  NUMERIC UNIQUE  ," +
                " id_user INTEGER  )");

        /////todo встака данных по умолчанию

        ////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Notifications");
    }
    private void МетодСозданиеТаблицыСогласование(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // todo таблица Notifications
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists data_pay");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'data_pay'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    data_pay  (" +
                "id  INTEGER ," +
                " cfo  TEXT ," +
                " organisazia TEXT ," +
                " kontragent TEXT ," +
                " status  TEXT," +
                " symma NUMERIC ,"+
                " statyadds TEXT  )");
              /* ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    data_pay  (" +
                "id  INTEGER )");
*/

        /////todo встака данных по умолчанию

        ////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы data_pay");
    }

    private void МетодСозданиеУведомленийИлиДАТАЗадания(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // todo таблица Notifications
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists data_notification");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'data_notification'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    data_notification  (" +
                "id  INTEGER ," +
                " message  TEXT ," +
                " date_start NUMERIC ," +
                " clock NUMERIC," +
                " date_update NUMERIC," +
                " rights INTEGER   ," +
                " uuid  NUMERIC UNIQUE ," +
                " current_table  NUMERIC  UNIQUE  ," +
                " status_write INTEGER DEFAULT 0 ," +
                " uuid_notifications NUMERIC , " +
                " type_tasks TEXT ," +
                " head_message  TEXT," +
                " callsback_note_task  TEXT ," +
                " alreadyshownnotifications  INTEGER )");
        /////todo встака данных по умолчаниюalreadyshownnotifications
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Notifications");
    }
///TODO согласования












    private void МетодСозданиеТаблицыFio_TEmplay(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists fio_template");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'fio_template'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    fio_template  (" +
                "id  INTEGER ," +
                " uuid  NUMERIC UNIQUE," +
                " fio_template NUMERIC ," +
                "fio_uuid  NUMERIC   ,"+
                " date_update NUMERIC,"+
                " user_update INTEGER," +
                "  current_table NUMERIC UNIQUE    ," +
                " status_send  TEXT ," +
                "FOREIGN KEY(fio_uuid  ) REFERENCES fio (uuid)  ON UPDATE CASCADE," +
                "FOREIGN KEY( fio_template  ) REFERENCES templates (uuid)  ON UPDATE CASCADE ," +
                "FOREIGN KEY( fio_template  ) REFERENCES templates (uuid)   ON DELETE CASCADE," +
                "PRIMARY KEY(fio_template,fio_uuid )) ");

        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Fio_Template");
        /////todo встака данных по умолчанию
    }

    private void МетодСозданиеSettingTabels(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // todo таблица  settings_tabels
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists  settings_tabels");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0' ,versionserveraandroid_version='0' WHERE name =  'settings_tabels'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    settings_tabels  (" +
                "id  INTEGER  ," +
                " date_update NUMERIC  ," +
                " user_update INTEGER  ," +
                " version_dsu1 INTEGER ," +
                "organizations  INTEGER DEFAULT '1' ," +
                " uuid  NUMERIC UNIQUE ," +
                "   current_table NUMERIC UNIQUE   , "+
                "  onesignal TEXT   )");

        //////////
        Log.d(this.getClass().getName(), " сработала ... settings_tabels");
        /////todo встака данных по умолчанию
    }



    private void МетодСозданиеview_onesignal(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // todo таблица  settings_tabels
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists  view_onesignal");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'view_onesignal'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    view_onesignal  (" +
                "id  INTEGER  ," +
                "uuid  NUMERIC  UNIQUE ," +
                " onesignal TEXT ," +
                "  current_table NUMERIC UNIQUE    ," +
                " user_update  INTEGER, "+
                "  date_update NUMERIC  )");
        //////////
        Log.d(this.getClass().getName(), " сработала ... view_onesignal");
        /////todo встака данных по умолчанию
    }

















    private void МетодСозданиеСистемнойТаблицыMODIFITATION_Client(SQLiteDatabase ССылкаНаСозданнуюБазу) throws InterruptedException {
        try {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists MODIFITATION_Client");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table  if not exists MODIFITATION_Client (" +
                "name  TEXT  NOT NULL UNIQUE DEFAULT '1901-01-10 00:00:00' " +
                ",  localversionandroid NUMERIC NOT NULL  DEFAULT '1901-01-10 00:00:00' , " +
                "versionserveraandroid NUMERIC NOT NULL  DEFAULT '1901-01-10 00:00:00' ," +
                " localversionandroid_version    NUMERIC  NOT NULL DEFAULT '0' , " +
                "versionserveraandroid_version   NUMERIC NOT NULL  DEFAULT '0'  )");
        //////////
        Log.d(this.getClass().getName(), " сработала ... INSERT  INTO MODIFITATION_Client");
          ИменаТаблицыОтАндройда.forEach(new Consumer<String>() {
                @Override
                public void accept(String НазваниеТаблицыДляЗаполения) {
                    String ФиналНазваниеТаблицыДляЗаполения =
                            new StringBuffer().append("'").append(НазваниеТаблицыДляЗаполения).append("'").toString();
                    ССылкаНаСозданнуюБазу.execSQL("INSERT INTO MODIFITATION_Client(name)  VALUES(  " + ФиналНазваниеТаблицыДляЗаполения + ")");
                    Log.d(this.getClass().getName(), " сработала вставка таблицы ... ФиналНазваниеТаблицыДляЗаполения " + ФиналНазваниеТаблицыДляЗаполения);
                }
        });
        Log.d(this.getClass().getName(), " сработала ... создание тригера MODIFITATION_Client");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    // TODO: 19.01.2023  тригер создание для
    private void МетодСозданиеТрирераМодификаценКлиент( @NotNull SQLiteDatabase ССылкаНаСозданнуюБазу,@NotNull CopyOnWriteArrayList ИменаТаблицыОтАндройда) {//BEFORE   INSERT , UPDATE , DELETE
        try{
            ИменаТаблицыОтАндройда.forEach(new Consumer() {
                @Override
                public void accept(Object НазваниеТаблицыДляТригера) {
                    String ФиналНазваниеТаблицыДляЗаполения =
                            new StringBuffer().append("'").append(НазваниеТаблицыДляТригера).append("'").toString();
                    //    String ДАТА = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

                    //TODO INSERT
                    ССылкаНаСозданнуюБазу.execSQL("  drop TRIGGER  if exists MODIFITATION_ClientTrigerInserorganizationINSERT" + НазваниеТаблицыДляТригера + "");
                    //TODO INSERT
                    ССылкаНаСозданнуюБазу.execSQL(" CREATE TRIGGER IF NOT EXISTS MODIFITATION_ClientTrigerInserorganizationINSERT" + НазваниеТаблицыДляТригера + "" +
                            "  AFTER INSERT   ON " + НазваниеТаблицыДляТригера +
                            " WHEN new.current_table >0 " +
                            " BEGIN " +
                            " UPDATE MODIFITATION_Client SET  localversionandroid_version=(SELECT MAX(current_table) FROM " + НазваниеТаблицыДляТригера + "),localversionandroid= datetime() " +
                            " WHERE name = " + ФиналНазваниеТаблицыДляЗаполения + "   AND localversionandroid_version < new.current_table  " + "; " +
                            " END ;");//test
                    // TODO: 03.06.2022
                    Log.d(this.getClass().getName(), " сработала ... создание тригера MODIFITATION_Client   TODO INSERT ФиналНазваниеТаблицыДляЗаполения " + ФиналНазваниеТаблицыДляЗаполения);
                    //TODO UPDATE
                    ССылкаНаСозданнуюБазу.execSQL("  drop TRIGGER  if exists MODIFITATION_ClientTrigerInserorganizationUPDATE" + НазваниеТаблицыДляТригера + "");
                    //TODO INSERT
                    ССылкаНаСозданнуюБазу.execSQL(" CREATE TRIGGER IF NOT EXISTS MODIFITATION_ClientTrigerInserorganizationUPDATE" + НазваниеТаблицыДляТригера + "" +
                            "  AFTER UPDATE   ON " + НазваниеТаблицыДляТригера +
                            " WHEN new.current_table > old.current_table " +
                            " BEGIN " +
                            " UPDATE MODIFITATION_Client SET  localversionandroid_version=(SELECT MAX(current_table) FROM " + НазваниеТаблицыДляТригера + "),localversionandroid= datetime() " +
                            " WHERE name = " + ФиналНазваниеТаблицыДляЗаполения + " AND localversionandroid_version < new.current_table  " + "; " +
                            " END ;");//test
                    Log.d(this.getClass().getName(), " сработала ... создание тригера MODIFITATION_Client   TODO UPDATE  ФиналНазваниеТаблицыДляЗаполения " + ФиналНазваниеТаблицыДляЗаполения);
                    //TODO END UPDATE

                    //TODO DELETE#1
                    ССылкаНаСозданнуюБазу.execSQL("  drop TRIGGER  if exists MODIFITATION_ClientTrigerInserorganizationDELETE" + НазваниеТаблицыДляТригера + "");
                    ССылкаНаСозданнуюБазу.execSQL(" CREATE TRIGGER IF NOT EXISTS MODIFITATION_ClientTrigerInserorganizationDELETE" + НазваниеТаблицыДляТригера + "" +
                            "  AFTER DELETE   ON " + НазваниеТаблицыДляТригера +
                            " WHEN OLD.current_table >0 " +
                            " BEGIN " +
                            " UPDATE MODIFITATION_Client SET  localversionandroid_version=(SELECT MAX(current_table) FROM " + НазваниеТаблицыДляТригера + " ), localversionandroid= datetime() " +
                            " WHERE name = " + ФиналНазваниеТаблицыДляЗаполения + "" +
                            "   AND (SELECT COUNT(current_table)>0 FROM " + НазваниеТаблицыДляТригера + "   ) " +
                            ";" +
                            " END;   BEGIN  " +
                            " UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0' , localversionandroid= datetime() , " +
                            " versionserveraandroid= datetime()  WHERE name = " + ФиналНазваниеТаблицыДляЗаполения + "" +
                            " AND (SELECT COUNT(current_table)=0 FROM " + НазваниеТаблицыДляТригера + "   ) " +
                            "; " +
                            " END;");//test
                    Log.d(this.getClass().getName(), " сработала ... создание тригера MODIFITATION_Client   TODO DELETE#1  ФиналНазваниеТаблицыДляЗаполения " + ФиналНазваниеТаблицыДляЗаполения);

                    //TODO DELETE#2
                    ССылкаНаСозданнуюБазу.execSQL("  drop TRIGGER  if exists MODIFITATION_ClientTrigerInserorganizationDELETETWO" + НазваниеТаблицыДляТригера + "");
                    ССылкаНаСозданнуюБазу.execSQL(" CREATE TRIGGER IF NOT EXISTS MODIFITATION_ClientTrigerInserorganizationDELETETWO" + НазваниеТаблицыДляТригера + "" +
                            "  AFTER DELETE   ON " + НазваниеТаблицыДляТригера +
                            " WHEN OLD.current_table >0 " +
                            "   BEGIN  " +
                            " UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0' , localversionandroid= datetime() , " +
                            " versionserveraandroid= datetime()  WHERE name = " + ФиналНазваниеТаблицыДляЗаполения + "" +
                            " AND (SELECT COUNT(current_table)=0 FROM " + НазваниеТаблицыДляТригера + "   ) " +
                            "; " +
                            " END;");//test
                    Log.d(this.getClass().getName(), " сработала ... создание тригера MODIFITATION_Client   TODO DELETE#2  ФиналНазваниеТаблицыДляЗаполения " + ФиналНазваниеТаблицыДляЗаполения);
                }
                // TODO: 22.11.2022 end all triger
            });
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }}


    private void МетодСозданияПольЗовательДЛяЧата(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists chat_users");//test

        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'chat_users'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    chat_users  (" +
                "_id  INTEGER ," +
                " name TEXT  ," +
                " rights INTEGER ,"+
                " telephone TEXT,"+
                " date_update  NUMERIC," +
                " current_table NUMERIC UNIQUE     ," +
                " locked INTEGER , " +
                " uuid NUMERIC  UNIQUE "+
                " )  ");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO chat_users (name,_id,rights,date_update,current_table) VALUES('Выбор ФИО ?','1','2','2022-02-21 16:17:44.26','1')");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Chat_Users");
    }







    private void МетодСозданияТаблицыData_Chat(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists data_chat");//test

        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'data_chat'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    data_chat  (" +
                "_id  INTEGER   ," +
                " uuid  NUMERIC UNIQUE," +
                " message TEXT ," +
                " image_chat BLOB ," +
                " status_write NUMERIC DEFAULT 0 ," +
                "chat_uuid  NUMERIC   ,"+
                " user_update INTEGER ,"+
                " date_update NUMERIC,"+
                " current_table NUMERIC UNIQUE   ," +
                "alreadyshownnotifications   INTEGER ," +
                "FOREIGN KEY(chat_uuid  ) REFERENCES chats (uuid_parent)  ON UPDATE CASCADE ON DELETE CASCADE)");

        /////todo встака данных по умолчанию

        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Data_Chat");




    }








    private void МетодСозданияТаблицаChats(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ////
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists chats");//test
        //////
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0' ,versionserveraandroid_version='0' WHERE name =  'chats'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    chats  (" +
                "_id  INTEGER   ," +
                " user_update INTEGER ,"+
                " date_update NUMERIC,"+
                " uuid  NUMERIC UNIQUE," +
                " id_user INTEGER  ," +
                "name TEXT  ," +
                " current_table NUMERIC UNIQUE   ," +
                "  uuid_parent NUMERIC)");

        /////todo встака данных по умолчанию

        Log.d(this.getClass().getName(), " сработала ...  создание таблицы Code_For_Chats_КодДля_Чата");// "UNIQUE(id_user,current_chat) )");


    }









    private void МетодСозданияВидаЧатаViewChat(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // TODO: 09.02.2022  view
        ССылкаНаСозданнуюБазу.execSQL("drop view  if exists viewchat");//test
        //ВИД View_TABEL
        ССылкаНаСозданнуюБазу.execSQL("CREATE VIEW if not exists viewchat AS   SELECT DISTINCT \n" +
                "                          data_chat._id,  data_chat.message,  data_chat.image_chat,  data_chat.status_write," +
                "  data_chat.chat_uuid,  data_chat.user_update,  data_chat.date_update, " +
                " data_chat.current_table, \n" +
                "                          data_chat.uuid,chats.id_user ,alreadyshownnotifications " +
                "FROM             chats LEFT OUTER JOIN\n" +
                "                          data_chat ON  chats.uuid_parent =  data_chat.chat_uuid \n" );
        ////
        Log.d(this.getClass().getName(), " сработала ...  создание вид  viewchat");
    }




    private void МетодСозданияВидаЗадания(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        // TODO: 09.02.2022  view
        ССылкаНаСозданнуюБазу.execSQL("drop view  if exists view_tasks");//test
        //ВИД View_TABEL
        ССылкаНаСозданнуюБазу.execSQL("CREATE VIEW if not exists view_tasks AS  SELECT     " +
                "    data_notification.id,  data_notification.uuid_notifications," +
                "  data_notification.current_table,  data_notification.uuid, " +
                " data_notification.rights,  data_notification.date_update," +
                "  data_notification.clock, \n" +
                "                          data_notification.date_start, " +
                " data_notification.message,  notifications.id_user," +
                "  data_notification.status_write,  notifications.user_update," +
                "  data_notification.type_tasks, \n" +
                "                          data_notification.head_message , data_notification.callsback_note_task,alreadyshownnotifications \n" +
                "FROM             notifications LEFT OUTER JOIN\n" +
                "                          data_notification" +
                " ON  notifications.uuid =  data_notification.uuid_notifications\n" +
                "WHERE        ( data_notification.message IS NOT NULL)");
        ////
        Log.d(this.getClass().getName(), " сработала ...  создание вид  view_tasks");
    }





    private void МетодСозданияViewТабеля(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        //

        // TODO: 26.08.2021 старый view табель

        //ВИД View_TABEL

        ССылкаНаСозданнуюБазу.execSQL("drop view  if exists viewtabel");//test
        //ВИД View_TABEL
        ССылкаНаСозданнуюБазу.execSQL("CREATE VIEW if not exists viewtabel AS  SELECT             data_tabels._id,  tabel.cfo," +
                "  tabel.month_tabels,  tabel.year_tabels,  data_tabels.fio,  data_tabels.d1,  data_tabels.d2, " +
                " data_tabels.d3,  data_tabels.d4,  data_tabels.d5,  data_tabels.d6, \n" +
                "                          data_tabels.d7,  data_tabels.d8,  data_tabels.d9,  data_tabels.d10," +
                "  data_tabels.d11,  data_tabels.d12,  data_tabels.d13,  data_tabels.d14,  data_tabels.d15,  data_tabels.d16, \n" +
                "                          data_tabels.d17,  data_tabels.d18,  data_tabels.d20,  data_tabels.d19," +
                "  data_tabels.d21,  data_tabels.d22,  data_tabels.d23,  data_tabels.d25,  data_tabels.d24,  data_tabels.d26, \n" +
                "                          data_tabels.d27,  data_tabels.d28,  data_tabels.d29,  data_tabels.d30," +
                "  data_tabels.d31,  data_tabels.date_update,  data_tabels.uuid,  data_tabels.uuid_tabel,  data_tabels.user_update, \n" +
                "                          data_tabels.current_table,  data_tabels.status_send,  data_tabels.status_carried_out\n" +
                "FROM             tabel LEFT OUTER JOIN\n" +
                "                          data_tabels ON  tabel.uuid =  data_tabels.uuid_tabel\n" +
                "WHERE        ( data_tabels.fio IS NOT NULL) ");



        Log.d(this.getClass().getName(), " сработала ...  создание вид  viewtabel");





    }







    private void МетодСоздания_ТаблицыТабель(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists tabel");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'tabel'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists tabel(" +
                "_id  INTEGER    ," +
                "cfo NUMERIC ," +
                " month_tabels TEXT check(length(month_tabels) <13 ) ," +
                "year_tabels NUMERIC," +
                "date_update  NUMERIC   ," +
                " uuid  NUMERIC UNIQUE ," +
                " status_send  TEXT ," +
                " user_update INTEGER ," +
                "current_table  NUMERIC  UNIQUE     ,"+
                " UNIQUE (user_update, month_tabels,year_tabels,cfo),"+
                "FOREIGN KEY(cfo ) REFERENCES cfo (_id)  ON UPDATE CASCADE   )");//   "PRIMARY KEY(_id)) ");
        ///
        ////////////////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы   новая tabel");//"FOREIGN KEY(organizations) REFERENCES SuccessLogin (organizations)  ON UPDATE CASCADE ,"+
    }




    //TODO новые таблицы для табеля  НОВАЯ ТАБЛИЦА   ДАТА ТАБЕЛЬ



    private void МетодСоздания_ТаблицыДатаТабель(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists data_tabels");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0' ,versionserveraandroid_version='0' WHERE name ='data_tabels'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists data_tabels(" +
                "_id  INTEGER     ," +
                "fio NUMERIC ," +
                "d1 INTEGER  DEFAULT 0   ," +
                "d2 INTEGER  DEFAULT 0    ," +
                "d3 INTEGER     DEFAULT 0    ," +
                "d4 INTEGER    DEFAULT 0     ," +
                "d5 INTEGER    DEFAULT 0   ," +
                "d6 INTEGER   DEFAULT 0    ," +
                "d7 INTEGER   DEFAULT 0      ," +
                "d8 INTEGER    DEFAULT 0     ," +
                "d9 INTEGER   DEFAULT 0   ," +
                "d10 INTEGER    DEFAULT 0  ," +
                "d11 INTEGER   DEFAULT 0     ," +
                "d12 INTEGER   DEFAULT 0   ," +
                "d13 INTEGER   DEFAULT 0     ," +
                "d14 INTEGER  DEFAULT 0    ," +
                "d15 INTEGER    DEFAULT 0    ," +
                "d16 INTEGER    DEFAULT 0    ," +
                "d17 INTEGER   DEFAULT 0   ," +
                "d18 INTEGER    DEFAULT 0    ," +
                "d19 INTEGER  DEFAULT 0    ," +
                "d20 INTEGER  DEFAULT 0      ," +
                "d21 INTEGER   DEFAULT 0  ," +
                "d22 INTEGER   DEFAULT 0    ," +
                "d23 INTEGER   DEFAULT 0    ," +
                "d24 INTEGER   DEFAULT 0    ," +
                "d25 INTEGER    DEFAULT 0   ," +
                "d26 INTEGER   DEFAULT 0    ," +
                "d27 INTEGER   DEFAULT 0   ," +
                "d28 INTEGER   DEFAULT 0    ," +
                "d29 INTEGER   DEFAULT 0    ," +
                "d30 INTEGER   DEFAULT 0    ," +
                "d31 INTEGER   DEFAULT 0    ," +
                "date_update  NUMERIC   ," +
                " uuid_tabel  NUMERIC  ," +
                " current_table  NUMERIC    UNIQUE  ," +
                " uuid NUMERIC UNIQUE ," +
                " user_update INTEGER  ," +
                "status_send TEXT ," +
                "status_carried_out   INTEGER DEFAULT 0   ,"+
                " UNIQUE (uuid_tabel,fio,user_update),"+
                "FOREIGN KEY(fio ) REFERENCES fio (uuid)  ON UPDATE CASCADE ) ");//  "PRIMARY KEY(fio,uuid_tabel)) ");
        ///             "FOREIGN KEY(uuid_tabel ) REFERENCES tabel (uuid)  ON UPDATE CASCADE  ON DELETE CASCADE," +
        ////////////////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы   новая data_tabels");//"FOREIGN KEY(organizations) REFERENCES SuccessLogin (organizations)  ON UPDATE CASCADE ,"+
    }





















































    private void МетодСозданиеТаблицыФИО(SQLiteDatabase ССылкаНаСозданнуюБазу) {

        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists   fio");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'fio'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists   fio (" +
                "_id  INTEGER   ," +
                " name TEXT  ," +
                " f TEXT ," +
                " n TEXT," +
                " o TEXT  ," +
                " BirthDate NUMERIC," +
                " snils TEXT," +
                " date_update NUMERIC," +
                " user_update INTEGER," +
                " uuid  NUMERIC UNIQUE," +
                " current_organization INTEGER," +
                " current_table  NUMERIC UNIQUE   ," +
                "FOREIGN KEY(user_update) REFERENCES users  (id)  ON UPDATE CASCADE," +
                "FOREIGN KEY(current_organization) REFERENCES organization  (id)  ON UPDATE CASCADE)");

        Log.d(this.getClass().getName(), " сработала ...  создание таблицы   fio");
    }












    private void МетодСозданиеМетокТабеля(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists  metki_tabel");//test
        ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0' ,versionserveraandroid_version='0' WHERE name =  'metki_tabel'");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table if not exists    metki_tabel  (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " metka TEXT ," +
                " fullname_metka TEXT ," +
                " date_update NUMERIC  ," +
                " user_update INTEGER )");
        /////todo встака данных по умолчанию
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('В','Выходной')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('О','Отпуск')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('Б','Больничный')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('А','Отпуск без оплаты')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('П','Прогулы')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('Р','Ремонт')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('Д','Дежурство')");
        ССылкаНаСозданнуюБазу.execSQL("INSERT INTO metki_tabel (metka,fullname_metka) VALUES('К','Командировка')");

        ////////
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы metki_tabel");
    }

    private void МетодСозданиеТаблицыЛогинов(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        ССылкаНаСозданнуюБазу.execSQL("drop table  if exists successlogin");//test
        ССылкаНаСозданнуюБазу.execSQL("Create table  if not exists successlogin (" +
                " id   INTEGER ," +
                " success_users  TEXT  ," +
                " success_login  TEXT ,  " +
                " date_update  NUMERIC," +
                " mode_weekend  TEXT DEFAULT 'Включить'," +///Включить   //  Выключить
                "mode_connection TEXT DEFAULT 'Mobile' )");
        //ССылкаНаСозданнуюБазу.execSQL("INSERT INTO SuccessLogin (id) VALUES('')");
        Log.d(this.getClass().getName(), " сработала ...  создание таблицы  successlogin");
    }





    // TODO: 08.06.2021 перед созданием делем Old копию базы данных













    private void МетодСозданиеТаблицыОшибок(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists errordsu1 ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists errordsu1 (" +
                    "ID_Table_ErrorDSU1 INTEGER PRIMARY KEY AUTOINCREMENT  ," +
                    " Error TEXT    NOT NULL  ," +
                    "Klass TEXT NOT NULL ," +
                    "Metod TEXT NOT NULL," +
                    "LineError INTEGER NOT NULL ," +
                    "Data_Operazii_E NUMERIC NOT NULL ,"+
                    "whose_error INTEGER NOT NULL )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы ErrorDSU1 ");

        } catch (SQLException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }
    // TODO: 27.09.2022  таблица для Получение Материалов


    private void МетодСозданиеТаблицаПолучениеМатериалов(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists get_material ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL("DELETE FROM MODIFITATION_Client   WHERE name =  'get_material'");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
         //   ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'get_material'");//test
         /*   ССылкаНаСозданнуюБазу.execSQL("Create table if not exists get_material (" +
                    "id INTEGER   ," +
                    "cfo  INTEGER ," +
                    "date_update TEXT ," +
                    "uuid NUMERIC UNIQUE," +
                    "user_update  INTEGER, " +
                    " current_table NUMERIC UNIQUE ," +
                    "status_send TEXT ," +
                    " FOREIGN KEY(cfo) REFERENCES cfo  (id)  ON UPDATE CASCADE )");*/
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы get_material ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    private void МетодСозданиеТаблицаДанныеПолученныхМатериалов(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists get_materials_data ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'get_materials_data'");//test
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists get_materials_data (" +
                    "id INTEGER   ," +
                    "type_material  INTEGER ," +
                    "nomen_vesov INTEGER," +
                    "tracks  INTEGER ," +
                    "companys INTEGER," +
                    "count NUMERIC ," +
                    "date_update TEXT ," +
                    "uuid NUMERIC UNIQUE," +
                    "user_update INTEGER," +
                    "cfo INTEGER," +
                    " current_table NUMERIC UNIQUE   ," +
                    " status_send TEXT ," +
                    " ttn TEXT check(length(ttn) <= 20 ) ," +
                    " datattn TEXT ," +
                    "FOREIGN KEY(type_material) REFERENCES type_materials  (_id)  ON UPDATE CASCADE ," +
                    "FOREIGN KEY(cfo) REFERENCES cfo  (_id)  ON UPDATE CASCADE ," +
                    "FOREIGN KEY(tracks) REFERENCES track  (_id)  ON UPDATE CASCADE ," +
                    "FOREIGN KEY(companys) REFERENCES company  (_id)  ON UPDATE CASCADE ," +
                     "FOREIGN KEY(nomen_vesov) REFERENCES nomen_vesov  (_id)  ON UPDATE CASCADE)");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы get_materials_data ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодСозданиеТаблицаДанныеКомпания(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists company ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'company'");//test
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists company (" +
                    "_id INTEGER   ," +
                    "name  TEXT ," +
                    "fullname TEXT," +
                    "inn  TEXT ," +
                    "kpp TEXT," +
                    "date_update TEXT ," +
                    "user_update INTEGER," +
                    "uuid NUMERIC UNIQUE," +
                    " current_table NUMERIC UNIQUE )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы company ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодСозданиеТаблицаДанныеТрак(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists track ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'track'");//test
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists track (" +
                    "_id INTEGER   ," +
                    "name  TEXT ," +
                    "fullname TEXT," +
                    "date_update TEXT ," +
                    "user_update INTEGER," +
                    "dir  INTEGER ," +
                    "uuid NUMERIC UNIQUE," +
                    " current_table NUMERIC UNIQUE )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы track ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    private void МетодСозданиеViewПолученныхМатериалов(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop view  if exists view_taterials");//test
            ССылкаНаСозданнуюБазу.execSQL("CREATE VIEW if not exists view_taterials AS SELECT         get_materials_data.id," +
                    "  get_materials_data.type_material,  get_materials_data.nomen_vesov,  get_materials_data.count," +
                    "  get_materials_data.date_update,  get_materials_data.uuid, \n" +
                    "                          get_materials_data.user_update,  get_materials_data.cfo," +
                    "  get_materials_data.current_table,  get_materials_data.status_send,  type_materials.name AS typematerial, \n" +
                    "                          nomen_vesov.name AS nomenvesov,  cfo.name AS name_cfo , nomen_vesov.type_material AS filters   \n" +
                    "FROM             get_materials_data LEFT OUTER JOIN\n" +
                    "                          type_materials ON  get_materials_data.type_material = " +
                    " type_materials._id LEFT OUTER JOIN\n" +
                    "                          nomen_vesov ON  get_materials_data.nomen_vesov =" +
                    "  nomen_vesov._id AND  get_materials_data.nomen_vesov =  nomen_vesov._id LEFT OUTER JOIN\n" +
                    "                          cfo ON  get_materials_data.cfo =  cfo._id" );
           Log.d(this.getClass().getName(), " сработала ...  создание view  view_taterials ");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодСозданиеViewПолученныхМатериаловGroup(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop view  if exists view_taterials_group");//test
            ССылкаНаСозданнуюБазу.execSQL("CREATE VIEW if not exists view_taterials_group AS" +
                    " SELECT        get_mater.nomen_vesov AS nomenvesov_zifra, get_mater.nomenvesov, SUM(get_mater.count) AS moneys," +
                    " COUNT(get_mater.nomen_vesov) AS kolichstvo, get_mater.cfo, get_mater.typematerial\n" +
                    "FROM             view_taterials AS get_mater LEFT OUTER JOIN\n" +
                    "                          nomen_vesov AS t_m ON get_mater.nomen_vesov = t_m._id\n" +
                    "WHERE        (get_mater.status_send <> 'Удаленная')\n" +
                    "GROUP BY t_m.name, get_mater.cfo, get_mater.nomenvesov, get_mater.nomen_vesov, get_mater.typematerial" );
            Log.d(this.getClass().getName(), " сработала ...  создание view  view_taterials_group ");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private void МетодСозданиеТаблицаСправочиникNomen_vesov(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists nomen_vesov ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'nomen_vesov'");//test
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists nomen_vesov (" +
                    "_id INTEGER   ," +
                    "name  TEXT ," +
                    "namefull  TEXT ," +
                    "articul TEXT," +
                    "koeff NUMERIC ," +
                    "type_material INTEGER,"+
                    "date_update TEXT ," +
                    "user_update  INTEGER, " +
                    "edizm INTEGER,"+
                    "uuid NUMERIC UNIQUE," +
                    " current_table NUMERIC UNIQUE   )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы nomen_vesov ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    private void МетодСозданиеТаблицаType_materials(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists type_materials ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL(" UPDATE MODIFITATION_Client SET  localversionandroid_version='0',versionserveraandroid_version='0'  WHERE name =  'type_materials'");//test
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists type_materials (" +
                    "_id INTEGER   ," +
                    "name  TEXT ," +
                    "date_update TEXT ," +
                    "user_update  INTEGER, " +
                    "uuid NUMERIC UNIQUE," +
                    " current_table NUMERIC UNIQUE    )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы type_materials ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }








    @Override
    public void onUpgrade(SQLiteDatabase ССылкаНаСозданнуюБазу, int oldVersion, int newVersion) {
        try{
            Log.d(this.getClass().getName(), " до СЛУЖБА  содание базы newVersion==  652   (например)  " +
                    " " + new Date()+  " newVersion " +newVersion);
            ИменаТаблицыОтАндройда=    new SubClassCreatingMainAllTables(context).  МетодТОлькоЗаполенияНазваниямиТаблицДляОмена(context);
            Log.d(this.getClass().getName()," ИменаТаблицыОтАндройда " +ИменаТаблицыОтАндройда); // TODO: 28.09.2022 таблицы
            Log.d(this.getClass().getName(), " после СЛУЖБА  содание базы newVersion==  652   (например)   " + new Date() + " newVersion " + newVersion);

            
        /*    if(newVersion ==   962){
               //TODO table создание
                МетодСозданиеТаблицаДанныеКомпания(ССылкаНаСозданнуюБазу);
                        МетодСозданиеТаблицаДанныеТрак(ССылкаНаСозданнуюБазу);
                МетодСозданиеТаблицаДанныеПолученныхМатериалов(ССылкаНаСозданнуюБазу);

                //   МетодСозданиеТаблицаДанныеПолученныхМатериалов(ССылкаНаСозданнуюБазу);
                *//*
                   МетодСозданиеViewПолученныхМатериалов(ССылкаНаСозданнуюБазу);
                МетодСозданиеViewПолученныхМатериаловGroup(ССылкаНаСозданнуюБазу);

                МетодСозданиеТаблицаДанныеПолученныхМатериалов(ССылкаНаСозданнуюБазу);
                // TODO: 03.06.2022  создаение тригера
                МетодСозданиеТрирераМодификаценКлиент(ССылкаНаСозданнуюБазу,ИменаТаблицыОтАндройда);*//*
                  // TODO: 03.06.2022  создаение тригера
            МетодСозданиеТрирераМодификаценКлиент(ССылкаНаСозданнуюБазу,ИменаТаблицыОтАндройда);
                   }else*/
            if (newVersion > oldVersion) {
                   // TODO: 08.06.2021 создание Базы Данных
                   onCreate(ССылкаНаСозданнуюБазу);
                   Log.d(this.getClass().getName(), " СЛУЖБА  содание базы newVersion > oldVersion   " + new Date());
               }
            Log.d(this.getClass().getName(), " сработала ... КОНЕЦ СОЗДАНИЯ ТАБЛИЦ ВИЮ ТРИГЕР " +new Date().toGMTString());
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase ССылкаНаСозданнуюБазу, int oldVersion, int newVersion) {
        onCreate(ССылкаНаСозданнуюБазу);
    }

}// конец public class CREATE_DATABASE extends SQLiteOpenHelper





































































































