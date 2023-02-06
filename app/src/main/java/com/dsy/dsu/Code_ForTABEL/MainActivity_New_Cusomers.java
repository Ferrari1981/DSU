package com.dsy.dsu.Code_ForTABEL;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_From_Name_Date_To_Diginal_Name;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_UUID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_New_Customers_For_Tabels;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity_New_Cusomers extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
    ////todo переменные для новго сотрдуника при создание на  активтик
    private Button КнопкаСозданиеНовогоСотрудника;
    private EditText ЗначениеФИОСозданиеСотрудника,  ЗначениеСНИЛССозданиеСотрудника;
    private TextView  ЗначениеДеньРожденияСозданиеСотрудника;
    private   String НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника;
    private  String UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника;
    private String НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника;
    private   String ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника;
    private  Configuration config;
    private String ПубличноеИМяТабеля;
    private Long УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель=0l;
    private String ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;
    private String UUIDСтарыйСамогоСозданогоТабелявКоторыйИнужноВставлятьНовгоСотрудника = "";
    private  String НовоеЗначениеUUIDДляОбновлениеТабеляКоторыйУжеСозданБЫл = "";
    private  String МесяцТабеляФинал = "";
    private  String UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя = "";
    private String ДепартаментТабеляФинал = "";
    private Long UUIDДанныйПришелПослеВЫбораУжеСуществующегоСотрудника;
    private Button КнопкаНазад;
    private   int ЦифровоеИмяНовгоТабеля;
    private  LinkedHashMap<String,Integer> ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи;
    private  Long НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник=0l;
    private   Activity activity;
    private CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;
    private Context КонтекстДляАктивтиСозданиеНовогоСотрудника;
    private  Spinner СпинерВыборОрганизацииПриСозданииНовогоСотрудника;/////спинеры для создание табеля
    private    String ПолученноеТекущееЗначениеСпинераОрганизация;
    private long РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыФИО;
    private   int Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицыФИО;
    private     Long  НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля=0l;
    private Intent ИнтентПришелДепаартаментТабеля;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private   ProgressDialog progressDialog;
    private      ConstraintLayout constraintLayout;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_new_customers);
        //TODO  ОЧИЩАЕМ ПАМТЬ
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try{

       constraintLayout = (ConstraintLayout)  findViewById(R.id.constraintLayout);

        Log.d(this.getClass().getName(), " constraintLayout   "+constraintLayout);
///////TODO
        Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());

        activity=this;
        ////
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
     //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
        getSupportActionBar().hide(); ///скрывать тул бар
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        КонтекстДляАктивтиСозданиеНовогоСотрудника=this;
        КнопкаСозданиеНовогоСотрудника = findViewById(R.id.КнопкаСозданиеНовогоТабеля);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        /////
        Log.d(this.getClass().getName(), "   ");
        // Locale locale = Locale.ROOT;
        Locale locale = new Locale("rus");
        Locale.setDefault(locale);
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
        ///TODO разное

////TODO ИНИЗАЛИЗАУМЯ ОБЬЕКТОВ НА АКТИВИТИ
        ЗначениеФИОСозданиеСотрудника = findViewById(R.id.ЗначениеЦФОПриСозданииНовогоТабеля);
        ЗначениеДеньРожденияСозданиеСотрудника = findViewById(R.id.ЗначениеДепартаментаПриСоздаенииНовогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        ЗначениеСНИЛССозданиеСотрудника = findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);
        //todo кнопка назад
        КнопкаНазад= findViewById(R.id.imageViewСтрелкаНазадНовыйСотрудник);
                СпинерВыборОрганизацииПриСозданииНовогоСотрудника= findViewById(R.id.значениеИзСпинераОрганизацияДляНовогоСотрудника);
            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
//todo настройки
    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }

    }

    @Override
    protected void onStart() {
        super.onStart();
        try{
            //todo пришили данные из преедедущего активти с названием табеля сСАМО ИМЯ И ЕГО UUID
            МетодПриемКонтентаОтДругихАктивти();
            МетодСозданиеСпинеровОрганизации(Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков);
            МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля();
            МетодПолучениеДатыРожденияЧерезКалендарь();
            МетодВозврещениеНаПредыдущуюАктивтиBACK();
            ///
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }













    protected void МетодСозданиеСпинеровОрганизации(CompletionService МенеджерПотоковВнутри) {
        
        // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
        
        ArrayList<String> ЛистДляАдаптераСпинерОрганизация = new ArrayList<>();


         ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи = new LinkedHashMap<>();

        SQLiteCursor    Курсор_ИщемВсеОрганизации=null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsСозданиеСпинеровОрганизации;

        try{
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            ///
            class_grud_sql_operationsСозданиеСпинеровОрганизации=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsСозданиеСпинеровОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","organization");
            ///////
            class_grud_sql_operationsСозданиеСпинеровОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsСозданиеСпинеровОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," name IS NOT NULL");
                    ///"_id > ?   AND _id< ?"
                    //////
/*
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 ....                  class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalПолученныйUUID);
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
          //  class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ИщемВсеОрганизации= (SQLiteCursor)  class_grud_sql_operationsСозданиеСпинеровОрганизации.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsСозданиеСпинеровОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоковВнутри,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            ////////////

            Log.d(this.getClass().getName(), "GetData " +Курсор_ИщемВсеОрганизации );

/*


// TODO: 07.09.2021    _old
                Курсор_ИщемВсеОрганизации =
                        new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("organization",
                                new String[]{"*"}, " name IS NOT NULL", null, null, null, null, null);//


*/

            // TODO: 07.09.2021  полученный результат

                if(Курсор_ИщемВсеОрганизации.getCount()>0){


                    Курсор_ИщемВсеОрганизации.moveToFirst();

                    ЛистДляАдаптераСпинерОрганизация=new ArrayList<>();

                 ЛистДляАдаптераСпинерОрганизация.add("") ;


// TODO: 07.09.2021 _old по данным
                    do{

                        Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемВсеОрганизации.getCount());


                        int ПолощениеСамаОрганизация=Курсор_ИщемВсеОрганизации.getColumnIndex("name");

                        String          СамаОрганизация =Курсор_ИщемВсеОрганизации.getString(ПолощениеСамаОрганизация);

                        Log.d(this.getClass().getName(), "  СамаОрганизация" +  СамаОрганизация);

                        ЛистДляАдаптераСпинерОрганизация.add(СамаОрганизация) ;

                        // TODO: 02.11.2021   ВтораяЧасть ПолученияID ДЛЯВставка



                        int ПолощениеСамаОрганизацияIDДЛяЗаписи=Курсор_ИщемВсеОрганизации.getColumnIndex("id");

                        Integer         СамаОрганизацияIDЗаписи =Курсор_ИщемВсеОрганизации.getInt(ПолощениеСамаОрганизацияIDДЛяЗаписи);

                        Log.d(this.getClass().getName(), "  СамаОрганизацияIDЗаписи" +  СамаОрганизацияIDЗаписи);


                        ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.put(СамаОрганизация,СамаОрганизацияIDЗаписи);


                        Log.d(this.getClass().getName(), "  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи" +  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.values());

                    } while (Курсор_ИщемВсеОрганизации.moveToNext());
                    ////

                }
            // TODO: 07.09.2021 exit
            Курсор_ИщемВсеОрганизации.close();





// Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> АдаптерДляСпинераОрганизация = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,
                ЛистДляАдаптераСпинерОрганизация);
        // Определяем разметку для использования при выборе элемента
        АдаптерДляСпинераОрганизация.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setAdapter(АдаптерДляСпинераОрганизация);

        //
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setHorizontalScrollBarEnabled(true);
        ////что быврали
        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //////СПИНЕР ДЕПАРТАМЕНТ
                if (position>0) {///ставим ограничкния если выбрано не 0 позиция то запонимаеним

                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                    ПолученноеТекущееЗначениеСпинераОрганизация = parent.getItemAtPosition(position).toString();

                    Log.d(this.getClass().getName(), "ПолученноеТекущееЗначениеСпинераОрганизация " + ПолученноеТекущееЗначениеСпинераОрганизация);
                        /*Toast toast = Toast.makeText(getApplicationContext(),
                                "Ваш выбор Раздел : " + ПолученноеЗначениеИзСпинераРаздел + " " + position, Toast.LENGTH_SHORT);
                        toast.show();*/

                }else if (position==0){
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                    ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines);
                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                    ((TextView) parent.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                    ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    ((TextView) parent.getChildAt(0)).setHint("Выберете Организацию".toUpperCase(Locale.ROOT));
                    ((TextView) parent.getChildAt(0)).setHintTextColor(Color.parseColor("#675757"));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }















    @Override
    protected void onDestroy() {
        super.onDestroy();

        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }






    @Override
    protected void onPause() {
        super.onPause();
        try {

          ///  МетодЗапускаЛокальнойСинхронизации();
            ///////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void МетодПриемКонтентаОтДругихАктивти() {
        try{
         ИнтентПришелДепаартаментТабеля = getIntent();
        ///TODO ИМЯ ТАБЕЛЯ
        НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника = ИнтентПришелДепаартаментТабеля.getStringExtra("ДепартаментТабеляФинал");
        Log.d(this.getClass().getName(), " ДепартаментТабеляФинал :  " + НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника);
        //TODO UUID ТАБЕЛЯ
        UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника = ИнтентПришелДепаартаментТабеля.getStringExtra("UUIDТабеляФинал");
        Log.d(this.getClass().getName(), " UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника :  " + UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);
        //TODO имя самого месяца например июль 2020  ТАБЕЛЯ'
        НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника = ИнтентПришелДепаартаментТабеля.getStringExtra("МесяцТабеляФинал");
        Log.d(this.getClass().getName(), " НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника:  " + НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника);
/////////////
//TODO имя ПЕРЕД СОЗЖДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕРДАЕМ ИМЯ ДЕПАРТЕМЕНТА
        ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника = ИнтентПришелДепаартаментТабеля.getStringExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника");
        Log.d(this.getClass().getName(), " НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника:  " + ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника);
//////
        ПубличноеИМяТабеля = ИнтентПришелДепаартаментТабеля.getStringExtra("СгенерированныйНазваниеНовогоТабеля");
        Log.d(this.getClass().getName(), " ПубличноеИМяТабеля  " + ПубличноеИМяТабеля);


        if( UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника==null){
            UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника= ИнтентПришелДепаартаментТабеля.getStringExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника");
            Log.d(this.getClass().getName(), " UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника :  " + UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);

        }


        //todo код работает после  подбора уже существующего сотрудника
        if (UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника == null) {
            UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника = UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя;
        }


        if (УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель== null) {
            УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель = Long.parseLong(UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника.trim());
        }

        if (УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель==null) {
            //TODO UUID ТАБЕЛЯ
           Long УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабельДополнительный = ИнтентПришелДепаартаментТабеля.getLongExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника",0l);

            УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель= УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабельДополнительный;
        }


        /////
        ///TODO цифровоеимя табеля
        Intent Интент_ПришлиДанныеДляПосикаУжеСуществующегоСотрудникаДляСозданияТабеля = getIntent();
        if (ЦифровоеИмяНовгоТабеля==0) {
            ЦифровоеИмяНовгоТабеля = Интент_ПришлиДанныеДляПосикаУжеСуществующегоСотрудникаДляСозданияТабеля.getIntExtra("ЦифровоеИмяНовгоТабеля",0);
        }

        if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
            ПолноеИмяТабеляПослеСозданиеНовогоСотрудника= ИнтентПришелДепаартаментТабеля.getStringExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника");
        }



        //todo код работает после  подбора уже существующего сотрудника
        if (UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника.length()==0 ) {
            UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника=
                    String.valueOf(ИнтентПришелДепаартаментТабеля.getLongExtra("ГлавныйУниверсальныйUUIDУжеСозданогоТабелявКоторвыйИНУжноВставитьСотрудника",0l));
        }



        //todo код работает после  подбора уже существующего сотрудника
        if (УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель==0 ) {
            УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель= ИнтентПришелДепаартаментТабеля.getLongExtra("РодительскийUUDТаблицыТабель",0l);
        }
        Log.d(this.getClass().getName(), " ЦифровоеИмяНовгоТабеля :  " + ЦифровоеИмяНовгоТабеля+ " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника "
                +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника+
                 "  УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель " +УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель +
                 "  UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " +UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);

    } catch (Exception e) {
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }
    }









///todo метод который возврящаем с текущего активити на предыдущий
///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
private void МетодВозврещениеНаПредыдущуюАктивтиBACK() {
    КнопкаНазад.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
            ///todo код которыц возврящет предыдущий актвитики кнопка back
            //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ

            МетодПослеУспешнгоСозданиеНовгоСотрудникаПереходимВТабеля();
            //////

        }
    });
}





///todo метод получение ДАТЫ РОЖДЕНИЯ ИЗ  КАЛЕНЛАРЯ
    private void МетодПолучениеДатыРожденияЧерезКалендарь() {
        ЗначениеДеньРожденияСозданиеСотрудника.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты();
            }
        });
    }
    ///поймать ошибку
    private void МетодВытаскиваемИзКалендаряДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        DatePickerDialog ДатаДляКалендаря=new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) this,
                GregorianCalendar.getInstance().get(Calendar.YEAR),
                GregorianCalendar.getInstance().get(Calendar.MONTH),
                GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
        ДатаДляКалендаря.setIcon(R.drawable.icon_dsu1_new_customer7 );
        ДатаДляКалендаря.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuffer БуферПолученаяДатаРожденияСотрудника=new StringBuffer();
        //todo make offset
        switch (month){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                month=month+1;
                break;
                /////todo яныварь
            case 0:
                month=month+1;
                break;
        }
//todo выравниваем длину месяца
        String МесяцФинал=String.valueOf(month);
        if (МесяцФинал.length()==1) {
            МесяцФинал = "0" + МесяцФинал;
        }
        ////todo  выравниваем длину день
        String ДеньФинал=String.valueOf(dayOfMonth);
        if (ДеньФинал.length()==1) {
            ДеньФинал = "0" + ДеньФинал;
        }
        БуферПолученаяДатаРожденияСотрудника.append(ДеньФинал).append(".").append(МесяцФинал).append(".").append(year);
        Log.d(this.getClass().getName(), " stringBuffer  "+ БуферПолученаяДатаРожденияСотрудника.toString());
        //TODO ЗАПОЛНЯЕМ
        ЗначениеДеньРожденияСозданиеСотрудника.setText(БуферПолученаяДатаРожденияСотрудника.toString());
    }














    ///todo данный метод начальный для создание нового сотрудника с кнопки
       void МетодЗапускаКодаПоСозданиюНовогоСотрудникаДляДвухТаблицФиоиДатаТабеля()  throws  InterruptedException{
            ///TODO НАЧИНАЕМ СОЗДАВАТЬ НОВОГО СОТРУДУНИКА ЕСЛИ ПРИШИЛИ НАЗВАНИЕ ТАБЕЛЯ И ЕГО UUID
           try{
            ////todo кнопка по нажатию на которуюи создеться табель
            КнопкаСозданиеНовогоСотрудника.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " ЗначениеФИОСозданиеСотрудника  "+ ЗначениеФИОСозданиеСотрудника+
                            " ЗначениеДеньРожденияСозданиеСотрудника  " + ЗначениеДеньРожденияСозданиеСотрудника +
                            " ЗначениеСНИЛССозданиеСотрудника  " +ЗначениеСНИЛССозданиеСотрудника);
                    // TODO: 09.08.2022 создаем новго сотрудника
                    int ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника=СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getSelectedItemPosition();
                    ПолученноеТекущееЗначениеСпинераОрганизация=( СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника).toString());
                    ReentrantLock reentrantLock=new ReentrantLock();
                    Condition condition= reentrantLock.newCondition();
                    Log.d(this.getClass().getName(), " ПолученноеТекущееЗначениеСпинераОрганизация  "+ ПолученноеТекущееЗначениеСпинераОрганизация);
                        final Long[] ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО = {0l};
                        final Long[]   ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля = {0l};

                        Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Throwable {
/////TODO перед созданием определяем не пустые ли значения
                                if (ЗначениеФИОСозданиеСотрудника.length() > 4
                                        && ЗначениеДеньРожденияСозданиеСотрудника.length() > 4
                                        && ЗначениеСНИЛССозданиеСотрудника.length()==11 &&
                                        ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника!=0 &&
                                        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника).toString()!=null &&
                                        СпинерВыборОрганизацииПриСозданииНовогоСотрудника.getItemAtPosition(ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника).toString()!="") {
                                    //TODO внешний вид
                                    activity.runOnUiThread(()->{
                                        progressDialog=new ProgressDialog(activity  );
                                        progressDialog.setCancelable(false);
                                        progressDialog.setTitle("Создание сотрудника");
                                        progressDialog.setMessage("в процессе...");
                                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                        progressDialog.setMax(1);
                                        progressDialog.setProgress(0);
                                        progressDialog.setIndeterminate(true);
                                        progressDialog.show();
                                        constraintLayout.setClickable(false);
                                    });

                                    reentrantLock.lock();

                                    // TODO: 23.09.2021  получение из даты месяц и год
                                    Log.d(this.getClass().getName(), " НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника  "
                                            + НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника);
                                    int МесяцТекущегоТабеля=0;//
                                    int ГодТекущегоТабеля=0;
                                    Class_Generation_From_Name_Date_To_Diginal_Name class_generation_from_name_date_to_diginal_nameперерводимИзНАзваниеДатуВЦифру=
                                            new Class_Generation_From_Name_Date_To_Diginal_Name(getApplicationContext());
                                    МесяцТекущегоТабеля=           class_generation_from_name_date_to_diginal_nameперерводимИзНАзваниеДатуВЦифру.
                                            МетодПолучениниеМесяцПриСозданииНовогоСОтрудника(НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника);
                                    ////
                                    Log.d(this.getClass().getName(), " МесяцТекущегоТабеля  "+ МесяцТекущегоТабеля);
                                    ГодТекущегоТабеля = class_generation_from_name_date_to_diginal_nameперерводимИзНАзваниеДатуВЦифру.
                                            МетодПолучениниеГОдПриСозданииНовогоСОтрудника(НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника);
                                    Log.d(this.getClass().getName(), " ГодТекущегоТабеля  "+ ГодТекущегоТабеля);
                                    ///TODO создание нового сотрудника
                                    // TODO: 22.09.2021 обработка ТАБЛИЦА ФИО
                                    // TODO: 24.09.2021
                                    ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0] = new Class_Generator_New_Customer_In_Table_Fio().
                                            МетодСозданиеНовогоСотрудникаДля_Таблицы_ФИО(ТекущаяПозицияСпинерВыборОрганизацииПриСозданииНовогоСотрудника);
                                    // TODO: 22.09.2021 ПОСЛЕ ДВУХ ОБРАБОТКАХ  ФИО И ДАТА_ТАБЕЛЬ ПЕРЕРХОДИМ НА ДРГОЕ АКТИВТИ
                                    Log.d(this.getClass().getName(), " ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО  "
                                            + ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]);
                                    // TODO: 22.09.2021 обработка ТАБЛИЦА ДАТА_ТАБЕЛЯ
                                    Log.d(this.getClass().getName(), " УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель  "
                                            +УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель  +
                                            "  ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО "+ ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]);

                                    if (ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0] >0) {
                                        condition.await(100, TimeUnit.MILLISECONDS);
                                        condition.signal();
                                    }

                                    //todo код работает после  подбора уже существующего сотрудника
                                    if (УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель==0 ) {
                                        УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель= ИнтентПришелДепаартаментТабеля.getLongExtra("РодительскийUUDТаблицыТабель",0l);
                                    }
                                    if (ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0] >0 && УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель>0) {
                                        // TODO: 22.09.2021 обработка ТАБЛИЦА ДАТА_ТАБЕЛЯ  вторая часть ПРОИЗВОДИМ ВСТАВКУ ВО ТРОРУЮ ТАБЛИЦУ ПОСЛЕ ТАБЛИЦЫ ФИО
                                        ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля[0] = new Class_Generator_New_Customer_In_Table_Data_Tables().
                                                МетодСозданиеНовогоСотрудника_вТаблицу_Дата_Табеля(НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник,
                                                        Long.valueOf(УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель),
                                                        МесяцТекущегоТабеля,
                                                        ГодТекущегоТабеля);
                                        // TODO: 22.09.2021 ПОСЛЕ ДВУХ ОБРАБОТКАХ  ФИО И ДАТА_ТАБЕЛЬ ПЕРЕРХОДИМ НА ДРГОЕ АКТИВТИ
                                        Log.d(this.getClass().getName(), " ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля  "+ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля);
                                        if (  ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля[0]  >0) {
                                            condition.await(100, TimeUnit.MILLISECONDS);
                                            condition.signal();
                                        }

                                    }else {
                                        Snackbar.make(v, "Сотрунидник не был создан ", Snackbar.LENGTH_LONG).show();
                                    }
                                    Log.d(this.getClass().getName(), " ЗАВЕРШИЛИ ВСТАВКИ НОВОГО СОТРУДНИКА СРАЗУ В ДВЕ ТАБЛИЦЫ ФИО И ДАТА ТАБЕЛЬС ");

                                    reentrantLock.unlock();

                                } else {
                                    activity.runOnUiThread(()->{
                                        Snackbar.make(v, "Заполните данные (СНИЛС от 11 знаков) ", Snackbar.LENGTH_LONG).show();
                                        Log.i(this.getClass().getName(), " Не все поля заполены (снилс от 10 знаков) ");
                                    });

                                }
                            }
                        })     .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread()).
                                doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ
                                        if (ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]>0  && ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_Дата_Табеля[0]>0) {
                                            ////////////
                                            constraintLayout.forceLayout();
                                            progressDialog.setProgress(1);

                                            Log.d(this.getClass().getName(), " ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]  "+ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]);
                                            ///////
                                            МетодПослеУспешнгоСозданиеНовгоСотрудникаПереходимВТабеля();
                                        }
                                    }
                                })
                                .onErrorComplete(new Predicate<Throwable>() {
                                    @Override
                                    public boolean test(Throwable throwable) throws Throwable {
                                        ///метод запись ошибок в таблицу
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        ///////
                                        return false;
                                    }
                                })
                                .doOnTerminate(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        if (progressDialog!=null) {
                                            progressDialog.setIndeterminate(false);
                                            progressDialog.dismiss();
                                            constraintLayout.setClickable(true);
                                        }
                                        Log.d(this.getClass().getName(), " Ошибка не создаен сотрудник (в таблице дата_табеля) !!!  " + ФинальныйРезультатВставкиНовгоСотрудникаВТаюлицу_ФИО[0]);
                                    }
                                }).subscribe();
                }
            });
       } catch (Exception e) {
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }
    }















































































































































// TODO: 22.09.2021  ОБРАБОТКА ТАБЛИЦЫ ДАТА_ТАБЕЛЯ











































/////TODO ДАННЫЙ МЕТОД ОПРЕДЕЛЯЕТ ЧТОБЫ МЫ БУДЕМ ДЕЛАТЬ ВСТАВЛЯТЬ НОВОГО СОТРУДНИКА КАК НОВОГО ИЛИ В БАЗЕ УЖЕ ЕСТЬ ХОТЬ ОДНА ЗАПИСЬ И ЭТО БУДЕТ НЕ ПЕРВЫЙ СОТРУДНИКА В ДАННОМ ТАБЕЛЕ


    private String МетодКоторыйОпределетЧТоБУдемДелатьОбновлятьИлиВставлятьНовгСОтрудника()
            throws ExecutionException, InterruptedException, TimeoutException {
        try{



        Cursor Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем=null;


         Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =

                МетодПроверяетПустойЛиТабельПервыйЗапускТабеляЧтоДелатьОбновлятьИлиВставлять();

        if (Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.getCount() > 0) { //TODO ЕСЛИ ДАННЫЙ UUID НЕ ПУСТОЙ ЭТО ЗНАЧИТ ЧТО ЭТОТ ТАБЕЛЬ УЖЕ СУЩЕТСВЕТ И НАМ НАДО ОБНОВИТЬ

            ////TODO ТАБЕЛЬ УЖЕ ЕСТЬ И МЫ ЕГО ОБНОЫЛЕНИЯ ПубличноеИмяНовогоТабеля
            Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.moveToFirst();

            Log.d(this.getClass().getName(), " Курсор_ПонятьМыВставляемВПУстойТабельСотрудникаИЛиОбновлеемЕго.getString(1) " +

                    Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.getString(0));


            НовоеЗначениеUUIDДляОбновлениеТабеляКоторыйУжеСозданБЫл =

                    Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.getString(0);

            ////TODO ИЩЕМ СТРАЙ UUID САМОГО ТАБЕЛЯ КОТОРЫЙ УЖЕ СОЗДАН


            //todo close cursor
            Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.close();
        }



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



        return НовоеЗначениеUUIDДляОбновлениеТабеляКоторыйУжеСозданБЫл;
    }
















    @NotNull
    private Cursor МетодПроверяетПустойЛиТабельПервыйЗапускТабеляЧтоДелатьОбновлятьИлиВставлять()
            throws ExecutionException, InterruptedException, TimeoutException {

        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем;
        ///


        //
        SQLiteCursor Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =null;
        try{


        //todo табель еще есть м ыв уже сущетсвещющеуй табель не всталяем  а обнолвяем
////TODO КУРСОР ПРОВЕЯЕТ ПЕРВЫЙ ЭТО ЗАПУСК ИЛИ НЕТ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем=
                    new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","tabels");
            ///////
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","fio");
            //
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=? ");
                    ///"_id > ?   AND _id< ?"
                    //////
            class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
                    ///
        /*            class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
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
      /*      class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");*/
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            //
       Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =null;

   /////
            Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем
                    = (SQLiteCursor)  class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  +Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем);




/*

            // TODO: 07.09.2021   _old
 Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем =
                new Class_MODEL_synchronized(this).КурсорУниверсальныйДляБазыДанных("tabels",
                        new String[]{"fio"}, "uuid=?", new String[]{УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель}, null, null, null, null);//"SuccessLogin", "date_update","id=","1",null,null,null,null
        ///TODO УДАЛЕМ ПАМЯТЬ*/


//todo определяем есть uuid в строчке или нет
        Log.d(this.getClass().getName(), "Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем " +
                Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем.getCount());
        /////s



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }

        return Курсор_КоторыйПроверяетЭтоПустаяЯчейкаUUIDЕслиПустоеНоЭтоНовыйТабельБезСотрудниковиМыНеВставляемАОбновлем;
    }

    ////////todo































    //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ВОЗВРАЩАЕМСЯ ОБРАТНО В ТАБЕЛЬ КУДА ДОБАВЛЯЛИ СОТРУЛНИКА


    private void МетодПослеУспешнгоСозданиеНовгоСотрудникаПереходимВТабеля() {
        try{
            Intent ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника = new Intent();
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.setClass(this, MainActivity_List_Peoples.class); // ТУТ ЗАПВСКАЕТЬСЯ ВЫБОР ПРИЛОЖЕНИЯ КОТОРЫЕ ЕСТЬ FACE APP НА ДАННЫЙ МОМЕТНТ РАЗРАБОТНАО ТАБЕЛЬНЫЙ УЧЁТ
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника",   ПолноеИмяТабеляПослеСозданиеНовогоСотрудника );
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника",   ПолноеИмяТабеляПослеСозданиеНовогоСотрудника );
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника", УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("РодительскийUUDТаблицыТабель", УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            Log.d(this.getClass().getName(), "  УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель " + УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника",  ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель",
                УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("UUIDТабеляПослеПодбораУниверсальный",
                УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("UUIDТабеляКнопкаBACKУниверсальный",
                UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);
        ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
            Log.d(this.getClass().getName(), "  УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель " + УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtra("РодительскийUUDТаблицыТабель", УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника.putExtras(data);
        startActivity(ИнтентФиналПослеУспешногоСозданиеНовгоСотрудника);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }





    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСообщаетОСоздаенииНовогоСотрудника(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        int ФлагЗнака;
        if (статус) {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_success;
        } else {
            ФлагЗнака = R.drawable.icon_dsu1_new_customer_error;
        }

        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(КонтекстДляАктивтиСозданиеНовогоСотрудника)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("ОК", null)
                    .setIcon(ФлагЗнака)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");

                    if (статус) {
                        ///todo после как мы либо создали новогосо остружника или обновли его в табел то обнуляем



                        //todo обнуляем ПОСЛЕ ВСТАВКИ НОВГО СОТРУДНИКА

                        ЗначениеФИОСозданиеСотрудника=null;

                        ЗначениеДеньРожденияСозданиеСотрудника=null;

                        ЗначениеСНИЛССозданиеСотрудника=null;

///TODO метод запуска формы после вставки
                        //TODO ПОСЛЕ УСПЕШНОЙ СОЗДАНИЕ НОВОГО СОТРУДНИКА ПЕРЕХОДИМ В ТАБЕЛЯ



                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////////todo проверика подключение к wi fi












































    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE FIO


    class  Class_Generator_New_Customer_In_Table_Fio   {
        /////////
        public Class_Generator_New_Customer_In_Table_Fio() {


        }

        // TODO: 22.09.2021   обработка ТАБЛИЦЫ ФИО



        // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ПЕРВОЕ  ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ФИО



      protected Long МетодСозданиеНовогоСотрудникаДля_Таблицы_ФИО(int ПолученноеТекущееЗначениеСпинераОрганизацияЦифра) throws InterruptedException {


            Log.d(this.getClass().getName(), " МетодСозданиеНовогоСотрудникаДля_Таблицы_ФИО");

            Long РезультаВставкиДАнныхНовогоСОтрудникаВТаблицу_ФИО=0l;


            try {
                // TODO: 22.09.2021  начинаем заполнять табдицу ФИО


                ContentValues АдаптерДляСозданиеНовогоСотрудаТАблицаФИО = new ContentValues();////контрейнер для нового табеля


                ////TODO
                String НазваниеФИО=ЗначениеФИОСозданиеСотрудника.getText().toString();
                //
                String ЗначениеДеньРождения=ЗначениеДеньРожденияСозданиеСотрудника.getText().toString();
                //

                Object  ПолученныйСНИЛСНовогоСотрудникаПереход=ЗначениеСНИЛССозданиеСотрудника.getText().toString().replaceAll("[^0-9]","").trim();


                Long ПолученныйСНИЛСНовогоСотрудника=Long.parseLong(ПолученныйСНИЛСНовогоСотрудникаПереход.toString());


                // TODO: 22.09.2021

                Log.w(getApplicationContext().getClass().getName(), "    НазваниеФИО    " + НазваниеФИО +
                        " ЗначениеДеньРождения "+ЗначениеДеньРождения+
                        "  ПолученныйСНИЛСНовогоСотрудника "+ПолученныйСНИЛСНовогоСотрудника);

                // TODO: 25.03.2021 начинаем заполнятьконтейнеры информацией

                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("name",НазваниеФИО);


                // TODO: 23.09.2021  дополнительное заполения трех полей фио

        Integer ЕслиПробел=        НазваниеФИО.indexOf(" ");

                Log.w(getApplicationContext().getClass().getName(), "    ЕслиПробел    " +ЕслиПробел);


                if (ЕслиПробел>=0) {

              /*      long occurrencesCount =0;

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        ////
                       occurrencesCount = НазваниеФИО.chars().filter(ch -> ch == ',').count();
                    }*/

                    int КоличествоПробелов = НазваниеФИО.length() - НазваниеФИО.replace(" ", "").length();

                    Log.w(getApplicationContext().getClass().getName(), "    occurrencesCount    " +КоличествоПробелов);

                    ////
                    String s1[]=НазваниеФИО.split("\\s+");


                    // TODO: 02.11.2021
                    //
                    if (КоличествоПробелов==1) {
                        if (s1[0]!=null) {
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("f",s1[0]);
                        }
                        // TODO: 02.11.2021
                        if (s1[1]!=null) {
                            АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("n",s1[1]);
                        }

                    }else {

                        if (КоличествоПробелов>=2) {
                            /////
                            if (s1[0]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("f",s1[0]);
                            }
                            //////////
                            if (s1[1]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("n",s1[1]);
                            }

                            if (s1[2]!=null) {
                                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("o",s1[2]);
                            }
                        }
                    }
                    //


                }
                Log.d(this.getClass().getName(), "  АдаптерДляСозданиеНовогоСотрудаТАблицаФИО " + АдаптерДляСозданиеНовогоСотрудаТАблицаФИО);

                // TODO: 23.09.2021  повыщаем верисю

            Long    РезультатВычисляемВреисюДанных=
                    new Class_GRUD_SQL_Operations(getApplicationContext()).new ChangesVesionData(getApplicationContext()).
                            МетодПовышаемВерсииCurrentTable("fio", getApplicationContext(),Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            ///
                Log.d(this.getClass().getName(), "  current_table УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ ВНУТРИ ТАБЛИЦЫ  РезультатВычисляемВреисюДанных " + РезультатВычисляемВреисюДанных);
                ////
                // TODO: 23.09.2021  повышаем верисю таблицы фио
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("current_table",РезультатВычисляемВреисюДанных);
                //
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("BirthDate",ЗначениеДеньРождения);
                ///
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("snils",ПолученныйСНИЛСНовогоСотрудника);




                // TODO: 01.11.2021  ПРОВЛДИМ АНАЛИЗ НЕТ ЛИ СЛУЧАЙНО СОЗДАВАЕМОГО СОТРУДНИКА В ТАБЛИЦЕ ФИО

                Class_GRUD_SQL_Operations class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника=new Class_GRUD_SQL_Operations( getApplicationContext());

                ///
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "fio");
                ///////
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "snils");
                //
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",
                        " snils=?  ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолученныйСНИЛСНовогоСотрудника);
                ///
                // TODO: 01.11.2021

                //////

                  /*  class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

                    ////TODO другие поля*/

                ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                ////
                //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                ////
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                ////
                class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
                ////

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                SQLiteCursor Курсор_ИщемПроверяемНетЛиСлучайноУжеУказаногСОтрудникаВТАблицеФИО = null;


                Курсор_ИщемПроверяемНетЛиСлучайноУжеУказаногСОтрудникаВТАблицеФИО = (SQLiteCursor) class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsИщемВТАблицеФИОНЕтЛИСлучайноТАковожеСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Курсор_ИщемПроверяемНетЛиСлучайноУжеУказаногСОтрудникаВТАблицеФИО " + Курсор_ИщемПроверяемНетЛиСлучайноУжеУказаногСОтрудникаВТАблицеФИО+ " ПолученныйСНИЛСНовогоСотрудника " +ПолученныйСНИЛСНовогоСотрудника);

                // TODO: 01.11.2021

                if( Курсор_ИщемПроверяемНетЛиСлучайноУжеУказаногСОтрудникаВТАблицеФИО.getCount()==0){










                ///todo перед СОЗДАНИЕ/ДОБАЛВЕНИМ НОВГО СОТРУДНИКА ОПЕРДЕЛЯЕМ UUID ЧИТАЕМ ЕГО ИЗ ТАБЛИЦЫ НА КОНКТЕРНЫЙ ТЕКУЩИЙ ТАБЕЛЬ ИЛИ ТАБЕЛЬ ТОЛЬКО СДЕЛАЛИ И UUID СЧИТЫВАЕМ С ПЕРЕМЕННОЙ
                ///todo ДАННОЕ ЗНАЧЕНИЕ НУЖНО УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель ТОЛЬКО ДЛЯ ОБНОЛВЕНИЕ ЗАПИСИ ЧТОБЫ ПОНЯТЬ В UPDATE КАКУЮ СТОРЧКУ ОБНОВЕЯТЬ



                Log.d(this.getClass().getName(), "  УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель " + УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);


/////TODO ГЕНЕРИРУЕМ НОВЫЙ UUID ДЛЯ ТАБЕЛЯ ПРИ СОЗДАНИИ НОВОГО СОТРУДНИКА ТУТ НОВЫЙ UUID ТОЛЬКО СОЗДАННЫЙ

    /*            /////
                UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника =  (String)  new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());

                Log.d(this.getClass().getName(), " UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника    " + UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);
*/


                ////todo создаение UUID
               НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник=0l;
                /////
                 НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник= (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                ///

                Log.d(this.getClass().getName(), " НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник " + НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник);

                ///
                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("uuid",НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник);




/////TODO ГЕНЕРИРУЕМ НОВЫЙ UUID ДЛЯ ТАБЕЛЯ ПРИ СОЗДАНИИ НОВОГО СОТРУДНИКА
                /////

                Log.d(this.getClass().getName(), " UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " + UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника);



                String ДатаПРиСозданииНовогоСотрудника=null;



                ////TODO ДАТА
                String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();

                //////////////////////

                ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;

                Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);

                АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("date_update",ДатаПРиСозданииНовогоСотрудника);



                ////todo месяц и год нового сотрудника



                if (ЦифровоеИмяНовгоТабеля>0) {

                    // TODO: 25.03.2021 текущая оргниазция дтя аьдицы ФИО

                 //   ПолученноеТекущееЗначениеСпинераОрганизация

                         //   ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи


                Integer ПолученныйIDОрганизации=  (Integer)  ЛистДляАдаптераСпинерОрганизацияСамоЗначениеIDДляЗаписи.get(ПолученноеТекущееЗначениеСпинераОрганизация);

                    Log.d(this.getClass().getName(), "ПолученныйIDОрганизации "+ПолученныйIDОрганизации   + " ПолученноеТекущееЗначениеСпинераОрганизацияЦифра " +ПолученноеТекущееЗначениеСпинераОрганизацияЦифра);

                    // TODO: 02.11.2021

                    АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("current_organization",ПолученныйIDОрганизации); ///1

                    // TODO: 22.04.2021  srart JOBschedele
                    Log.d(this.getClass().getName(), "ПолученноеТекущееЗначениеСпинераОрганизация "+ПолученноеТекущееЗначениеСпинераОрганизация +
                            "  ПолученныйIDОрганизации " +ПолученныйIDОрганизации);

                    // TODO: 25.03.2021 заполяем  user_update

                    // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                    int ПолученныйID = 0;
                    Class_GRUD_SQL_Operations class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике;
                    class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике=new Class_GRUD_SQL_Operations(getApplicationContext());
                    class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                    ///////
                    class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
                    class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","id IS NOT NULL");
                    class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
                    // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИ
                    SQLiteCursor Курсор_ИщемПУбличныйIDКогдаегоНетВстатике = null;
                    Курсор_ИщемПУбличныйIDКогдаегоНетВстатике= (SQLiteCursor)  class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике.
                            new GetData(getApplicationContext()).getdata(class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                            ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    ////////
                    Log.d(this.getClass().getName(), "GetData "  +Курсор_ИщемПУбличныйIDКогдаегоНетВстатике);
                    // TODO: 07.09.2021  результат
                    if (Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount() > 0) {
                        /////////////
                        Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.moveToFirst();
                        ////
                        Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount());
                        ПолученныйID = Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getInt(0);
                    }
                    Log.d(this.getClass().getName(), "   ПолученныйID " + ПолученныйID);
                    if (ПолученныйID > 0) {
                        /////
                        АдаптерДляСозданиеНовогоСотрудаТАблицаФИО.put("user_update", ПолученныйID);
                    }
///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО-------СКОПИРОВАН КОД ИЗ УДАЕННОГО МЕТО
                    // TODO: 25.03.2021 получеам текущую организацию
                    // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                    final int[] ТекущуюОрганизацию = {0};
                    final Cursor[] Курсор_ИщемТекущуюОрганизациюКоторуюВыбраСОтрудник = {null};
                    Log.d(this.getClass().getName(),"ТекущуюОрганизацию[0] " + ТекущуюОрганизацию[0] );
                    //// TODO  СамаВставка нового сотрудника в новый табель
                }else{
                    Log.e(this.getClass().getName(), " нет данных из предцдуещго табеля");
                }
                // TODO: 23.09.2021  повышаем верисю таблицы фио
                // TODO: 08.092021  метод после заполения данными
                РезультаВставкиДАнныхНовогоСОтрудникаВТаблицу_ФИО=       new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                        МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_ФИО(АдаптерДляСозданиеНовогоСотрудаТАблицаФИО,activity);
                Log.w(this.getClass().getName(), " РезультаВставкиДАнныхНовогоСОтрудникаВТаблицу_ФИО  "+РезультаВставкиДАнныхНовогоСОтрудникаВТаблицу_ФИО);
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка данный сотрудник уже есть в таблице СНИЛС : "+ПолученныйСНИЛСНовогоСотрудника , Toast.LENGTH_LONG).show();
                }
//TODO ОКОНЧИАЕМ ВСТАВКУ ДАННЫХ
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            /////
            return  РезультаВставкиДАнныхНовогоСОтрудникаВТаблицу_ФИО;

        }












        // TODO: 22.09.2021  МЕТОД НЕПОСТРЕДВСТЕННОЙ ЗАПИСИ ДАННЫХ В ТАБЛИЦУ ФИО




    }

// TODO: 22.09.2021








































































    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels
    // TODO: 22.09.2021   KLASS GENERATOR NEW CUSTOMERS  IN TABLE data_tabels


    class   Class_Generator_New_Customer_In_Table_Data_Tables   {
        ///////


        public Class_Generator_New_Customer_In_Table_Data_Tables() {


        }

    // TODO: 22.09.2021  ТАБЛИЦА ДАТА_ТАБЕЛЬ







    // TODO: 22.09.2021  ЗАПИСЬ НОВОГО СОТДУНИКА ВТОРОЕ ДЕЙСТИЕ ЗАПИСЬВ ТАБЛИЦУ ДАТА_ТАБЕЛЯ





    //TODO метод записи нового сотрудника в базу
   protected Long МетодСозданиеНовогоСотрудника_вТаблицу_Дата_Табеля(Long НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник,
                                                                     Long УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель,
                                                                     int ГодПриВставкеНовогоСотрудника,
                                                                   int  МЕсяцПриВставкеНовогоСотрудника) throws InterruptedException {



        Long РезультатВставкиВтаблицу_Дата_табеля=0l;


        Log.d(this.getClass().getName(), " МетодСозданиеНовогоСотрудника_вТаблицу_Дата_Табеля    ");

        ContentValues  АдаптерДляСозданиеНовогоСотрудаТАблицаТабель=new ContentValues();

        try {

            ///todo перед СОЗДАНИЕ/ДОБАЛВЕНИМ НОВГО СОТРУДНИКА ОПЕРДЕЛЯЕМ UUID ЧИТАЕМ ЕГО ИЗ ТАБЛИЦЫ НА КОНКТЕРНЫЙ ТЕКУЩИЙ ТАБЕЛЬ ИЛИ ТАБЕЛЬ ТОЛЬКО СДЕЛАЛИ И UUID СЧИТЫВАЕМ С ПЕРЕМЕННОЙ
            ///todo ДАННОЕ ЗНАЧЕНИЕ НУЖНО УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель ТОЛЬКО ДЛЯ ОБНОЛВЕНИЕ ЗАПИСИ ЧТОБЫ ПОНЯТЬ В UPDATE КАКУЮ СТОРЧКУ ОБНОВЕЯТЬ



            long UUIDTabelзначениеСамогоПустогоТабеляБезСотрудников = 0;
            ///




            /////TODO ТАБЕЛЬ УЖЕ СУЩЕСТВАОЛАЛ И МЫ ЧИТАЕМ UUID  ТАБЕЛЯ ИЗ БАЗЫ

            //todo  из текста в цифру UUID ---ВНИМАНИЕ ЭТО СТАРЫЙ UUID СУЩЕСТВУЮЩЕГО ТАБЕЛЯ

          //  UUIDTabelзначениеСамогоПустогоТабеляБезСотрудников = Long.parseLong(УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);

            Log.d(this.getClass().getName(), "  УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель " + УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель
                    + " new MainActivity_New_Tabely().СгенерированныйUUIDДляНовогоТабеля   "  + UUIDTabelзначениеСамогоПустогоТабеляБезСотрудников);


/////TODO ГЕНЕРИРУЕМ НОВЫЙ UUID ДЛЯ ТАБЕЛЯ ПРИ СОЗДАНИИ НОВОГО СОТРУДНИКА ТУТ НОВЫЙ UUID ТОЛЬКО СОЗДАННЫЙ




        /*    /////
          Long  UUID_НовыйСгенерированыйтолькоДЛяТАблицыДатаТАбель =  (Long)  new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());

            Log.d(this.getClass().getName(), " UUID_НовыйСгенерированыйтолькоДЛяТАблицыДатаТАбель    " + UUID_НовыйСгенерированыйтолькоДЛяТАблицыДатаТАбель);
*/



            // TODO: 25.03.2021 вписываем сгенерированный UUID ДАННЫЙ UUID  ПРИШЕЛ ОТ ТПБЛИЦЫ ФИО

            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("fio",НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник);

            Log.d(this.getClass().getName(), " НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник    " + НовыйСгенерированныйUUIDДляТаблицыФИОКогдаСоздаетьсяНовыйСотрудник);




            // TODO: 25.03.2021 вписываем сгенерированный UUID  ПРИШЕЛ ОТ РОДИТЕЛЬСКОЙ ТАБЛИЦЫ таьбель
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid_tabel",УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            Log.d(this.getClass().getName(), " УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель    " + УниверсальныйUUIDДляСОзданиеНовогоСотрудникаНаКонкретныйТабель);
            // TODO: 06.10.2021 СТАТУС ПРОВЕДЕНИЯ    ПО УМОЛЧПНИЮ
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.putNull("_id");
            // TODO: 06.10.2021 СТАТУС ПРОВЕДЕНИЯ    ПО УМОЛЧПНИЮ
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_carried_out", "False");
            // TODO: 23.09.2021  повыщаем верисю
            Long    РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля=
                    new Class_GRUD_SQL_Operations(getApplicationContext()).new ChangesVesionData(getApplicationContext()).
                            МетодПовышаемВерсииCurrentTable("data_tabels", getApplicationContext()
                                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), " current_table УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ ВНУТРИ ТАБЛИЦЫ  РезультатУвеличинаяВерсияДАныхЧата  " +
                    " РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля " + РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля);
            ////

            // TODO: 23.09.2021  повышаем верисю таблицы фио

            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("current_table",РезультатПовышаемВреисюДанныхТаблицы_Дата_Табеля);












            ////TODO ДАТА

            String ДатаПРиСозданииНовогоСотрудника=null;


            String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
            ДатаПРиСозданииНовогоСотрудника = СгенерированованныйДатаДляДаннойОперации;
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("date_update",ДатаПРиСозданииНовогоСотрудника);
            // TODO: 23.09.2021
            Log.d(this.getClass().getName(), " ДатаПРиСозданииНовогоСотрудника" + ДатаПРиСозданииНовогоСотрудника);
            // TODO: 23.09.2021  uuid
        НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля=0l;
            /////
          НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля =  (Long)  new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
            Log.d(this.getClass().getName(), " НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля    " + НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля);
            // TODO: 23.09.2021  новый UUID  для таблицы Дата_Табель
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("uuid",НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля);
                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
            Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
                // TODO: 07.09.2021
                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике;
                class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике=new Class_GRUD_SQL_Operations(getApplicationContext());
                class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
                class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","id IS NOT NULL");
                class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
                SQLiteCursor Курсор_ИщемПУбличныйIDКогдаегоНетВстатике = null;
                Курсор_ИщемПУбличныйIDКогдаегоНетВстатике= (SQLiteCursor)  class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsИщемПУбличныйIDКогдаегоНетВстатике. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                ////////
                Log.d(this.getClass().getName(), "GetData "  +Курсор_ИщемПУбличныйIDКогдаегоНетВстатике);
                // TODO: 07.09.2021  результат
                if (Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount() > 0) {
                    /////////////
                    Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.moveToFirst();
                    ////
                    Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount());

                    ПубличноеIDПолученныйИзСервлетаДляUUID = Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getInt(0);
                }
                Log.d(this.getClass().getName(), "   ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                if (ПубличноеIDПолученныйИзСервлетаДляUUID > 0) {
                    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель .put("user_update", ПубличноеIDПолученныйИзСервлетаДляUUID);
                }

            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеIDПолученныйИзСервлетаДляUUID" + ПубличноеIDПолученныйИзСервлетаДляUUID);
///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО-------СКОПИРОВАН КОД ИЗ УДАЕННОГО МЕТОДА
                // TODO: 25.03.2021 получеам текущую организацию
                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                final int[] ТекущуюОрганизацию = {0};
                final Cursor[] Курсор_ИщемТекущуюОрганизациюКоторуюВыбраСОтрудник = {null};
            // TODO: 23.09.2021  повышаем верисю таблицы фио
            АдаптерДляСозданиеНовогоСотрудаТАблицаТабель.put("status_send", " ");
            Log.d(this.getClass().getName(),"ТекущуюОрганизацию[0] " + ТекущуюОрганизацию[0] );
            // TODO: 08.09.2021  метод после заполения данными
            РезультатВставкиВтаблицу_Дата_табеля=   new Class_Generations_New_Customers_For_Tabels(getApplicationContext()).
                    МетодЗаписиСозданогоСотрудникаВБазуПоТаблицы_Дата_Табеля(
                    АдаптерДляСозданиеНовогоСотрудаТАблицаТабель
                    ,activity,
                    ГодПриВставкеНовогоСотрудника,
                    МЕсяцПриВставкеНовогоСотрудника, НовыйСгенерированныйUUIDДляТаблицы_ДатаТабеля);/////TODO НОВЫЙ НУЖЕН НЕ ДЛЯ ВСТАВКИ А ДЛЯ ВТОРОГО ДЕЙСТВИЯ ЗАПОЛЕНИЯ ВЫХОДНЫМИ ЕСЛИ НВ НАСТРОЙКАХ ЕСТЬ КАКАЯ ФУЕНКЦИЯ И ОНА ВКЛЮЧЕН

            Log.e(this.getClass().getName(), " РезультатВставкиВтаблицу_Дата_табеля" + РезультатВставкиВтаблицу_Дата_табеля);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатВставкиВтаблицу_Дата_табеля;

    }

    // TODO: 22.09.2021   метод записи дата_табельс



    }

}