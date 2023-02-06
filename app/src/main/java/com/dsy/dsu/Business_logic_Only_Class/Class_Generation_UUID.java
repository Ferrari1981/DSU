package com.dsy.dsu.Business_logic_Only_Class;

import static java.util.Calendar.getInstance;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Class_Generation_UUID {

    Context contextДляКлассаВремени;

    Integer ПубличноеIDПолученныйИзСервлетаДляUUID=0;


    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;


    public Class_Generation_UUID(Context context) {

        contextДляКлассаВремени=context;

///////TODO
          Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(contextДляКлассаВремени);
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 05.07.2021













    // TODO ГЕНЕРАЦИЯ UUID ВВИДЕ ЦИФРЫ
    public Object МетодГенерацииUUID(Context contextДЛяUUID) {

        Long UUIDФинал = null;
        //
        String UUID=new String();

        SQLiteCursor Курсор_ИщемПУбличныйIDКогдаегоНетВстатике = null;


        try {
            // TODO ГЕНЕРАЦИЯ UUID ВВИДЕ ЦИФРЫ
            //"yyyyMMddHHmmssSSS" //"EEEEE MMMMM yyyy HH:mm:ss.SSSSSSZ"
            Date Дата = getInstance().getTime();
            // DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmssSS", new Locale("ru"));
            DateFormat dateFormat = new SimpleDateFormat("yyddMMHHmmssS", new Locale("ru"));

            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

            String СгенерированоДатаДляUUIDШагПервый = dateFormat.format(Дата);

            Long СгенерированоДатаВТипеLong = Long.parseLong(СгенерированоДатаДляUUIDШагПервый);


            //todo гененируем если есть публичный id


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(contextДЛяUUID);
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(contextДЛяUUID);


            ///////
            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(contextДЛяUUID).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                ////

                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                /////
                ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);
///


                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }





            // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
            final int[] ПолученныйID = {0};
            //////////
            if ( ПубличноеIDПолученныйИзСервлетаДляUUID>0) {


                String  ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный=String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID);


                ////////
                String  СгенерированоДатаВТипеLongПромежуточный=String.valueOf(СгенерированоДатаВТипеLong);

                Log.i(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный " +ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный+
                         " СгенерированоДатаВТипеLongПромежуточный " +СгенерированоДатаВТипеLongПромежуточный);


                    UUID = ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный+СгенерированоДатаВТипеLongПромежуточный;// Integer.parseInt(UUIDФинал.trim());

                    Log.i(this.getClass().getName(), " UUID " + UUID+ "  ПубличноеIDПолученныйИзСервлетаДляUUID "
                            +ПубличноеIDПолученныйИзСервлетаДляUUID + " СгенерированоДатаВТипеLong " +СгенерированоДатаВТипеLong);




            }else{

           /*     Random random=new Random();
           Integer СлучайноеЧисло=     random.nextInt(100);*/

                ////////
                String  СгенерированоДатаВТипеLongПромежуточный=String.valueOf(СгенерированоДатаВТипеLong);

                UUID = "0" + СгенерированоДатаВТипеLongПромежуточный;//
                /////
                Log.e(this.getClass().getName(), "   UUID--сгенерировал uuid ьез пуьличного id " + UUID);

            }


// TODO: 23.09.2021  ФИНАЛЬНОЕ ПОЛУЧЕНИЕ UUID СГЕНЕРИРОВАЫЙ
            Log.d(this.getClass().getName(), "   UUID--сгенерировал " + UUID);


            BigInteger UUIDФиналs= new BigInteger(UUID);

            UUIDФинал=        UUIDФиналs.longValue();

          //  UUIDФинал=Long.parseLong(UUID);

            Log.w(this.getClass().getName(), "   UUIDФинал " + UUID);


            //todo обнуляем
            СгенерированоДатаВТипеLong = null;//////

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextДЛяUUID).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }




        // TODO: 06.09.2021  новый UUID
        return UUIDФинал;
    }

}
