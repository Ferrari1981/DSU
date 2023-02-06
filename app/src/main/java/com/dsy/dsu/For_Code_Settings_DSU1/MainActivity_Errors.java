package com.dsy.dsu.For_Code_Settings_DSU1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Sendiing_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Date;


//вывод данных на Автивити
public class MainActivity_Errors extends AppCompatActivity  {

//назначение полец и ссылки на другие классы
//определяем дату вставки
///create ProgressBar
    /**
     * todo данное активти показываем ошибки
     *
     */
///////TODO
   private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    /////ЯЧЕЙКИ
    private  TextView КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения;//
    ////////БИЛДЕР
    private  StringBuffer БуерДляОшибок;
    ///

    private   Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
//// для вставки данных

    private  MaterialButton materialButtonОтправкаОшибокНАпочту;
    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(this.getClass().getName(), "Запущен.... метод  onCreate в классе MainActivity_Settings  ; ");

                super.onCreate(savedInstanceState);
                //TODO


            setContentView(R.layout.activitymain_viewlogin); ///activitymain_viewlogin  /// fragment_dashboard
/////
         //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
         //   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            //ссылка на схему базы данных;//ссылка на схему базы данных
          //  ССылкаНаСозданнуюБазу = new CREATE_DATABASE(this).ССылкаНаСозданнуюБазу;//ссылка на схему базы данных;//ссылка на схему базы данных
            //заполение TextView
            КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения = (TextView) findViewById(R.id.textViewDATA);
//////
            ////
            //создаем билдер
            БуерДляОшибок = new StringBuffer();
            //////
            //////todo настрока экрана
        //    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
           /* JSONObject jsonObject=new JSONObject();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Stream<JSONObject> stream=Stream.of(jsonObject);
                stream.parallel().forEachOrdered((Data)->{

                });
            }*/

///////TODO
             Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());

            //////todo  конец настрока экрана


            /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            getSupportActionBar().hide(); ///скрывать тул бар




            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

            //////todo настрока экрана




                        materialButtonОтправкаОшибокНАпочту   = (MaterialButton) findViewById(R.id.button3SendErrorEmal);


            Log.d(this.getClass().getName(), " materialButtonОтправкаОшибокНАпочту  " + materialButtonОтправкаОшибокНАпочту);


            //////todo  конец настрока экрана

///////errors
        } catch (Exception e) {
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    } // конец    protected void onCreate(Bundle savedInstanceState)


    @Override
    protected void onStart() {
        super.onStart();

try{


    materialButtonОтправкаОшибокНАпочту.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TODO полывоаем ошибки на почту

            Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v2.vibrate(50);
            }
            МетодПосылаемОшибкиНапочту();

        }
    });

        //////errors
    } catch (Exception e) {
        e.printStackTrace();
///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    protected void МетодПосылаемОшибкиНапочту() {


       try{






        if (БуерДляОшибок.length()>300) {

            Log.d(this.getClass().getName(), "   БуерДляОшибок"+БуерДляОшибок.toString());



            // ID


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(getApplicationContext());
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC  LIMIT 50;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());


            ///////
            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){//
                ////

                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                /////
                ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);
///


                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }



                     БуерДляОшибок.append("\n")
                    .append(" текущий пользователь : ").append("\n")
                    .append(ПубличноеIDПолученныйИзСервлетаДляUUID).append("\n")
                    .append(" время отправки: ").append("\n")
                    .append(new Date())
                             .append("\n");

            // TODO: 01.09.2021 ПОСЫЛАЕМ ОШИБКИ СИСТМЕНУМО АДМИНИСТАТОРУ

            new Class_Sendiing_Errors(this).МетодПослываемОшибкиАдминистаторуПо(БуерДляОшибок,this,ПубличноеIDПолученныйИзСервлетаДляUUID);

        }

        ///////errors
    } catch (Exception e) {
        e.printStackTrace();
///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}







    //////////////////////////////////////////////////////////////
    @Override
    public void onResume() {
        super.onResume();
// put your code here...
//метод по  выводу даным на Ативити из SQLITE
        try {
            ///
            МетодПросмотраОшибокПриложения();
//метод по  выводу даным на Ативити из SQLITE

///поймать ошибку
        } catch (Exception e) {
//  Block of code to handle errors
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }

    /////////////
//запуск метода вставки данных logins
    protected void МетодПросмотраОшибокПриложения() {

        ///////
        int ЕстьСтроки = 0;
        //

        Cursor Курсор_СамиДанные_Error = null;

        try {

//сам код
            Log.d(this.getClass().getName(), "Запущен.... метод  Просмотра ошибок MainActivity_Errors  ; ");

///////////
                ArrayList<EditText> editTextArrayList = new ArrayList<>();

                StringBuffer stringBuffer = new StringBuffer();


///////////////////////////  TODO курсор для ПОКАЗА ОШИБОК УЖЕ СОЗДАННЫХ END AsyncTask-1
                    Курсор_СамиДанные_Error = null;

                    ////TODO ЗАПУСКАЕМ  МеханизмУправлениеПотокамиОграничеваемИхУжеСозданными

                            System.out.println("Another thread was executed");

                Class_GRUD_SQL_Operations class_grud_sql_operationsПросмотраОшибокПриложения
                        =new Class_GRUD_SQL_Operations(getApplicationContext());



            ///
            class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","ErrorDSU1");
            ///////
            class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            /*        class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=?    AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
                    ///"_id > ?   AND _id< ?"
                    //////
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","ID_Table_ErrorDSU1 DESC");
            ////
            class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","50");
            ////



            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_СамиДанные_Error= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "+Курсор_СамиДанные_Error  );


            class_grud_sql_operationsПросмотраОшибокПриложения. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.clear();


        /*    // TODO: 07.09.2021  _old
                                ///////
                                Курсор_СамиДанные_Error = ССылкаНаСозданнуюБазу.rawQuery("SELECT " +
                                        "ID_Table_ErrorDSU1 AS [ID ТАБЛИЦЫ Ошибки ДСУ-1]," +
                                        "Error AS [Ошибка]," +
                                        "Klass AS [Класс]," +
                                        "Metod AS [Метод]," +
                                        "LineError AS [номер строки]," +
                                        "Data_Operazii_E AS [Дата Ошибки]," +
                                        "whose_error AS [Версия программы]" +
                                        "FROM  ErrorDSU1 ORDER BY  ID_Table_ErrorDSU1 DESC ", new String[]{});

                                /////
*/
///запкск потока


//////////
            if (Курсор_СамиДанные_Error!=null) {
                ///
                ЕстьСтроки = Курсор_СамиДанные_Error.getCount();
            }

//////
                if (ЕстьСтроки > 0) {
                    Log.d(this.getClass().getName(), "В курсоре даннные есть::: ");
/////обязательная строчка надо курсор поставить на first значения чтобы buder смог его прочитать
///////рефакторинг
              
/////ЗАПУСКАЕМ МЕТОД СОЗДАНИЕ ОЩИБОК
                        МетодЗапускаAsynTaskОшибки(ЕстьСтроки, Курсор_СамиДанные_Error);
                        //////

       

/////////
/////////// end AsyncTask-2
/////ОШИБКА НЕТ В КУРСОРЕ ДАННЫХ
                } else {
                    Log.d(this.getClass().getName(), "Ошибка Курсор нет данных  Logins  ");
                  //  МетодСозданиеДиалогаКлассЛогин("Ошибки ПО ТАбельный учёт", "Ошибок нет.");

                    /////ЗАПУСКАЕМ МЕТОД СОЗДАНИЕ ОЩИБОК
                    МетодЗапускаAsynTaskОшибки(ЕстьСтроки, Курсор_СамиДанные_Error);

                }
/////БАЗА НЕ ОТКРЫТА

//поймать ошибку
        } catch (Exception e) {
//  Block of code to handle errors
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    protected void МетодЗапускаAsynTaskОшибки(int естьСтроки, Cursor Курсор_СамиДанные_Error) {
        try {
////

            String ИнфоТелефон = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();

///проветка если ссылка на класс
            Log.d(this.getClass().getName(), "естьСтроки "+естьСтроки);
            //

///////////
/////////// start AsyncTask-1
///////////////////////////end  AsyncTask-

            БуерДляОшибок.setLength(0);
////проверка если в курсоре строчки
            Integer ИндексДляПрограссбара = null;
            if (естьСтроки > 0) {
                ////ЕСЛИ ДАННЫЕ ЕСТЬ СТАВИМ КУРСОР НА ПЕРВОЕ МЕСТО
                Курсор_СамиДанные_Error.moveToFirst();
                /////////количество строчекв курсоре
                int КоличествоСтрочекКурсоре = Курсор_СамиДанные_Error.getCount();
                Log.d(this.getClass().getName(), "количество записей в курсоре : " + КоличествоСтрочекКурсоре);


                //////индекс для перемещение по програсбару
                ИндексДляПрограссбара = 0;
                // //ЦИКЛ ЗАПОЛЕНИЕ СТРОК
                do {
                    ///иницализирует програмбар

                    //получение полей из таблицы соотвертвующего типа данных
                    Integer Столбик_ID_Table_ErrorDSU1 = Курсор_СамиДанные_Error.getInt(0);
                    String Столбик_Error = Курсор_СамиДанные_Error.getString(1);
                    String Столбик_Klass = Курсор_СамиДанные_Error.getString(2);
                    String Столбик_Metod = Курсор_СамиДанные_Error.getString(3);
                    Integer LineError_LineError = Курсор_СамиДанные_Error.getInt(4);
                    String LineError_Data_Operazii_E = Курсор_СамиДанные_Error.getString(5);
                    int СтолбикКтоСделалОшибку = Курсор_СамиДанные_Error.getInt(6);
                    //конец  получение полей из таблицы соотвертвующего типа данных
                    //вставляем в билде
                    БуерДляОшибок.append(" #---------Ошибки ПО Табельный Учёт--------------#" + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(0).toUpperCase() + "  : " + Столбик_ID_Table_ErrorDSU1 + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(1).toLowerCase() + "  : " + Столбик_Error.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(2).toLowerCase() + "  : " + Столбик_Klass.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(3).toLowerCase() + "  : " + Столбик_Metod.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(4).toLowerCase() + "  : " + LineError_LineError + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(5).toLowerCase() + "  : " + LineError_Data_Operazii_E + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(6).toLowerCase() + "  : " + СтолбикКтоСделалОшибку + "\n" + "\n" +
                            "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                            "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                            Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                                    "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                            "   " + "-----------------------------------------" + "\n"+  "\n" );
                    //показать
                    Log.d(this.getClass().getName(), " Содержимое Курсора  :  " + Столбик_ID_Table_ErrorDSU1 + " ");
                    //Toast.makeText(getApplicationContext(), "  Содержимое Курсора  : "+Столбик_Logins_id+" " , Toast.LENGTH_SHORT).show();
                    /////уввкеличиваем для програссбара
                    ИндексДляПрограссбара++;
                    ////////
                } while (Курсор_СамиДанные_Error.moveToNext());

// Build.BRAND.toUpperCase()






//привязваем адаптер
                Курсор_СамиДанные_Error.close();

            }else {


                //вставляем в билде


                //вставляем в билде
                БуерДляОшибок.append(    "---------------Ошибок Нет.-----------"+"\n"+"\n"+
                        "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                        "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                        Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                        "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                        "   " + "-----------------------------------------" + "\n"+  "\n" );





                Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());


            }

//////лоим ошибки
            Log.d(this.getClass().getName(), " БуерДляОшибок.toString() "+БуерДляОшибок.toString());


            ////ПОКАЗЫВАЕМ ДАННЫЕ УЖЕ НА АКТИВТИ ОШИБКИ
            if (БуерДляОшибок.length()>0) {
                //////////////////////
                КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения.setText(БуерДляОшибок.toString());
            }
            /////////////////////////////////


            Курсор_СамиДанные_Error.close();









///поймать ошибку
        } catch (Exception e) {
//  Block of code to handle errors
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }


    //////ОБЩИЙ МЕТОД СОЗДАНИЕ КЛАССИЧЕСКОГО ДИАЛОГА С КНОПКОЙ ЗАКРЫТЬ
    @UiThread
    public void МетодСозданиеДиалогаКлассЛогин(String ШабкаДиалога, String СообщениеДиалога) {
        try {
///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
            final AlertDialog DialogBoxsПростомрДанных = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Закрыть", null)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateПростомрДанных = DialogBoxsПростомрДанных.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateПростомрДанных.setOnClickListener(new View.OnClickListener() {

                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
///запуск метода обновления через DIALOGBOX
                    try {
//удаляем с экрана Диалог
                        DialogBoxsПростомрДанных.dismiss();
//соообщение
// Toast.makeText(getApplicationContext(), "Запускаем обновление данных " , Toast.LENGTH_LONG).show();
///////запуск главного меню после того как поняли что в азе нет логинов
                        Intent Интент_Меню_ТолькоПростотДанных;
                        Интент_Меню_ТолькоПростотДанных = new Intent(getApplicationContext(), MainActivity_Face_App.class);
///// Toast.makeText(getApplicationContext(), "Выбран пунк меню Главный Экран" , Toast.LENGTH_LONG).show();
                        startActivity(Интент_Меню_ТолькоПростотДанных);
                       // finish();
////ПОСЛЕ ОПЕРАЦИИ ОБНОВЛЕНИЕ ЗАПУСКАМ ГЛАВНЦЮ ФОРМУ ПРОСМОТРА ДАННЫХ
////


//ловим ошибки

                    } catch (Exception e) {
                        e.printStackTrace();
///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                         // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }

                }
            });

// /поймать ошибку
        } catch (Exception e) {
//  Block of code to handle errors
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}//конец public class MainActivity_Recyclerview extends AppCompatActivity {
