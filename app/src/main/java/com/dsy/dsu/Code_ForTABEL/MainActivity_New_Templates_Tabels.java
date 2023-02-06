package com.dsy.dsu.Code_ForTABEL;

import static java.util.Locale.setDefault;

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
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_UUID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Weekend_For_Tabels;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity_New_Templates_Tabels extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner СпинерВыборДату;/////спинеры для создание табеляСпинерТабельДепратамент
    private String ПолученноеЗначениеИзСпинераДата; ///результат полученный из спенров
    private   String КакойКонтекст;
    private ScrollView ScrollНаАктивтиСозданныхТабелей;
    private LinearLayout LinearLayoutСозданныхТабелей;
    private LinearLayout LinearLayoutДляЛинии;
    private  boolean РежимыПросмотраДанныхЭкрана;
    private EditText ПрослойкаМеждуТабелей;
    private Configuration config;
    private String ПослеСозданиеовгоТабеляГОд = "";
    private String ПослеСозданиеовгоТабеляМЕсяц = "";
    private  String ПослеСозданиеовгоТабеляВместеГодИМесяц = "";
    private  String ПолученноеЗначениеИзТолькоСпинераДата = "";
    private  String ПослеСозданияНовогоТабеляЕгоUUID;
    private String ПослеСозданияНовогоТабеляЕгоПолноеНазвание = "";
    private  String ПубличноеИмяКнопкиТабеля;
    private  Long МетодГенерацииUUIDУжеСуществующегоСотрудника = 0l;
    private  View.OnLongClickListener СлушательУдаланиеСамогоТабеля;
    private  Button ШАблонвВидеКнопок = null;
    private  String ПолученныйГодДляНовогоТабеля = "";
    private  String ФинальнаяМЕсяцДляНовогоТабеля = "";
    private  String ПравильныйВозвратИзДруговоАктивтиBACK;
    private  int ЦифровоеИмяНовгоТабеля;
    private  String МесяцТабеляФинал;
    private Long ПолученнаяUUIDНазванияОрганизации;
    private boolean ЗапускШаблоновFaceAppБлокировкаКнопкиДа;
    private  Long РодительскийUUDТаблицыТабель = 0l;
    private String ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;///ДАННЫЙ МАССИВ БУДЕТ ЗАПОЛНЯТЬ СПИНЕР РАЗДЕЛ
    private  LinkedList<String> МассивДляВыбораВСпинерДата = new LinkedList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  Context КонтекстШаблоны;
    private String МесяцТабеляФиналИзВсехСотрудниковВТАбеле;
    private String ГодТабеляФиналИзВсехСотрудниковВТАбел;
    private  Button КнопкаНазадВсеТабеля;
    private LinkedList СодержимоеКурсораUUIDТабеляПриУдалениеиТАбеляилиВместеССотрудником = new LinkedList();
    private Integer ПубличноеIDПолученныйИзСервлетаДляUUID = 0;
    private  Cursor Курсор_ЗагружаетАрайдистЗначенийНовогоШАБЛОНА;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    private  TextView TExtvieeСловоТабельВсегоШАблонов;
    private ProgressDialog progressDialog ;
    private  FloatingActionButton КруглаяКнопкаСозданиеНовогоТабеля;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main__templates_tabely);
        КонтекстШаблоны = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());
        getSupportActionBar().hide(); ///скрывать тул бар
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        /////
        ScrollНаАктивтиСозданныхТабелей = (ScrollView) findViewById(R.id.ScrollViewСамТабеля); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
        LinearLayoutСозданныхТабелей = (LinearLayout) findViewById(R.id.ГлавныйКонтейнерТабель); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
        КнопкаНазадВсеТабеля = findViewById(R.id.КонопкаНазадСтрелкаВсеТабеля);
        TExtvieeСловоТабельВсегоШАблонов = (TextView) findViewById(R.id.textView3СловоТабель);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        ///////TODO
        Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(getApplicationContext());
        // Locale locale = Locale.ROOT;
        Locale locale = new Locale("rus");
        setDefault(locale);
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
        ///////// todo Круглая Кнопка
       КруглаяКнопкаСозданиеНовогоТабеля = findViewById(R.id.КруглаяКнопкаСамТабель);//////КНОПКА СОЗДАНИЕ НОВГО ТАБЕЛЯ ИЗ ИСТОРИИ ВТОРОЙ ШАГ СОЗДАНИЯ ТАБЕЛЯ СНАЧАЛА ИСТРОИЯ ПОТОМ НА БАЗЕ ЕГО СОЗЗДАНИЕ

            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }


        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }}

    private void МетодДляКруглойКнопки() {
        КруглаяКнопкаСозданиеНовогоТабеля.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Log.d(this.getClass().getName(), "МетодСозданияНовогоШАблона()   КруглаяКнопкаСозданиеНовогоТабеля  СОЗДАНИЕ НОВГО ШАБЛОНА" +
                        "  " + ПолученнаяUUIDНазванияОрганизации);
             /*   if (PUBLIC_CONTENT.Отладка==true) {
                    //TODO СОЗДАНИЕ НОВОГО ТАБЕЛЯ
                    СообщениеСпрашиваемПользователяЧтоОнТОчноХочетьСоздатьНовыйШаблон("Шаблоны", "Создать новый Шаблон ?."+"\n", true);
                }*/
                МетодСозданияНовогоШАблона();
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }
        });
    }

    private void МетодВозвратаBACKАктивти() {
        try{
            КнопкаНазадВсеТабеля.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                    ///todo код которыц возврящет предыдущий актвитики кнопка back
                    МетодПереходаПослеУспешногоДобавленияСотрудниклвИзШаблонаВТабель();
                    ////todo запускаем активти
                    Log.d(this.getClass().getName(), " ЦифровоеИмяНовгоТабеля  " + ЦифровоеИмяНовгоТабеля +
                            " МесяцТабеляФинал " + МесяцТабеляФинал + " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " +
                            ПолноеИмяТабеляПослеСозданиеНовогоСотрудника + " РодительскийUUDТаблицыТабель ");

                }
            });



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    void МетодПолучениеДанныхДляДаногоАктивтиИсторияТАбеля() {
        try {
            ////////
            Intent Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода = getIntent();
            ////////
            ПослеСозданиеовгоТабеляГОд = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ГодВырезалиИзБуфераТабель");
            ///
            ПослеСозданиеовгоТабеляМЕсяц = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("МесяцВырезалиИзБуфераТабель");
            ////
            ПослеСозданиеовгоТабеляВместеГодИМесяц = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ПолученноеТекущееЗначениеСпинераДата");
            ////
            ПослеСозданияНовогоТабеляЕгоUUID = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("СгенерированныйUUIDДляНовогоТабеля");
            /////
            ПослеСозданияНовогоТабеляЕгоПолноеНазвание = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("СгенерированныйНазваниеНовогоТабеля");
/////////
            ПравильныйВозвратИзДруговоАктивтиBACK = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ДепартаментТабеляПослеПодбораBACK");


            ЦифровоеИмяНовгоТабеля = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getIntExtra("ЦифровоеИмяНовгоТабеля", 0);
            ////
            ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника");


            //////
            МесяцТабеляФинал = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("МесяцТабеляФинал");


            //////
            ПолученнаяUUIDНазванияОрганизации = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getLongExtra("ПолученнаяUUIDНазванияОрганизации", 0l);


            Log.d(this.getClass().getName(), "ПолученнаяUUIDНазванияОрганизации  " + ПолученнаяUUIDНазванияОрганизации);


            //////
            ЗапускШаблоновFaceAppБлокировкаКнопкиДа =
                    Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getBooleanExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", false);


            Log.d(this.getClass().getName(), "ЗапускШаблоновFaceAppБлокировкаКнопкиДа  " + ЗапускШаблоновFaceAppБлокировкаКнопкиДа);


            String РодительскийUUDТаблицыТабельТекст = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("UUIDТабеляФинал");
            ////

            if (РодительскийUUDТаблицыТабельТекст != null) {
                /////
                if (РодительскийUUDТаблицыТабельТекст.length() > 0) {
                    //////
                    ///////
                    РодительскийUUDТаблицыТабель = Long.parseLong(РодительскийUUDТаблицыТабельТекст.trim());
                }
            }

            if (РодительскийUUDТаблицыТабель == 0) {


                РодительскийUUDТаблицыТабель = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getLongExtra("РодительскийUUDТаблицыТабель", 0l);


            }


            if (РодительскийUUDТаблицыТабель == 0) {


                РодительскийUUDТаблицыТабель = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getLongExtra("UUIDТабеляКнопкаBACKУниверсальный", 0);
            }


            Log.d(this.getClass().getName(), "РодительскийUUDТаблицыТабель  " + РодительскийUUDТаблицыТабель + " РодительскийUUDТаблицыТабельТекст " + РодительскийUUDТаблицыТабельТекст);


            if (РодительскийUUDТаблицыТабель == 0) {


                РодительскийUUDТаблицыТабель = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            }


            Log.d(this.getClass().getName(), "РодительскийUUDТаблицыТабель  " + РодительскийUUDТаблицыТабель + " РодительскийUUDТаблицыТабельТекст " + РодительскийUUDТаблицыТабельТекст);


            //  ЦифровоеИмяНовгоТабеля= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ЦифровоеИмяНовгоТабеля");
            /////
            Log.d(this.getClass().getName(), " ПослеСозданиеовгоТабеляГОд " + ПослеСозданиеовгоТабеляГОд + " ПослеСозданиеовгоТабеляГОд " + ПослеСозданиеовгоТабеляГОд +
                    " ПослеСозданиеовгоТабеляВместеГодИМесяц " + ПослеСозданиеовгоТабеляВместеГодИМесяц + "    ПравильныйВозвратИзДруговоАктивтиBACK " + ПравильныйВозвратИзДруговоАктивтиBACK +
                    " ЦифровоеИмяНовгоТабеля " + ЦифровоеИмяНовгоТабеля + " МесяцТабеляФинал " + МесяцТабеляФинал +
                    " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника" + ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);

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


       /* if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            if (ns!=null) {
                NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
                nMgr.cancel(0);
            }
        }*/
    }


    @Override
    protected void onStart() {
        super.onStart();
        try {
            МетодДляКруглойКнопки();
            МетодПолучениеДанныхДляДаногоАктивтиИсторияТАбеля();
            МетодДляУдалениеШаблонаЕслиВнемНетСотрудников();
            МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля();////метод вызаваем все созжданные ТАБЕДЯ ИЗ БАПЗЫ И ДАЛЕЕ ИХ ЗАПИСЫВАЕМ В ОБМЕН
            МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля();
            МетодВозвратаBACKАктивти();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    //todo метод удаления табля из проекта если внем нет сотрудников
    private void МетодДляУдалениеШаблонаЕслиВнемНетСотрудников() {
        ///todo третий обработчки нажатия
///TODO  удаление ОБРАБОТКА КЛИКА ПО ФИО
        СлушательУдаланиеСамогоТабеля = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    ////todo
                    //МыУжеВКодеУденияСотрудника=true;
                    TextView UUIDУдаляемогоТабеля = (TextView) v;

                    Log.d(this.getClass().getName(), " UUIDУдаляемогоТабеля " + UUIDУдаляемогоТабеля.getTag());

                    ///Toast.makeText(getApplication(), " Удаление Самого Табеля  " +v.getTag(), Toast.LENGTH_SHORT).show();
                    String UUIDУдаляемогоТабеляКАкТекст = (String) UUIDУдаляемогоТабеля.getTag();

                    String НазваниеУдаляемогоТАбеля = (String) UUIDУдаляемогоТабеля.getText();

                    Integer НазваниеУдаляемогоТАбеляВЦифровомФормате = (Integer) UUIDУдаляемогоТабеля.getId();

                    ////
                    Log.d(this.getClass().getName(), " НазваниеУдаляемогоТАбеля " + НазваниеУдаляемогоТАбеля +
                            "НазваниеУдаляемогоТАбеляВЦифровомФормате " + НазваниеУдаляемогоТАбеляВЦифровомФормате);


////todo метод придупрежнает что будет процесс даленис самого табеля




/*
                if (PUBLIC_CONTENT.Отладка==true) {

                        СообщениеПредпреждаетОВыбореУдалениеСамогоТабеля("Оповещение",  "Вы выбрали функцию удаление"+"\n"
                                        +"Шаблон: " +НазваниеУдаляемогоТАбеля+"\n" + "(Выбор Да/Нет на следующем диалогом окне).", "Templates",
                                UUIDУдаляемогоТабеляКАкТекст,НазваниеУдаляемогоТАбеля,НазваниеУдаляемогоТАбеляВЦифровомФормате);
                        //  +"Табеля: " +НазваниеУдаляемогоТАбеля+"\n" + "(Выбор Да/Нет на следующем диалогом окне).", "uuid", UUIDУдаляемогоТабеляКАкТекст,НазваниеУдаляемогоТАбеля,НазваниеУдаляемогоТАбеляВЦифровомФормате);
                    } else {*/

                    /////todo сообщением передохим вв удаления табеля
                    Long СамUUIDТабеляКакLONG = Long.valueOf(UUIDУдаляемогоТабеляКАкТекст);

                    ///todo

                    СообщениеВыборУдлалянияТабеляИзБазы("Удаление Шаблона", "удалить  ? : " + НазваниеУдаляемогоТАбеля,
                            "Templates", СамUUIDТабеляКакLONG, НазваниеУдаляемогоТАбеля, НазваниеУдаляемогоТАбеляВЦифровомФормате);


                    // TODO: 01.02.2022   ОФОРМЛАЯЕМ ПРИ ДОЛГОМ НАЖАТИИ
                    //  ((TextView) v).setBackgroundColor(Color.GRAY);
                    v.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.textlines_tabel_row_color_green_mini_longclick));




                    return true;


/*/////TODO КОД КОТОРЫЙ КОТОРЫ УДАЛЯЮТ СОТРУДНИКА ИЗ ТАБЕЛЯ
                    СообщениеПредпреждаетОВыбореУдалениеСотрудникаИзТабеля("Оповещение",  "Вы выбрали функцию удаление сотрудника: "+"\n" +ФИОДляУдаление.getText() +
                            " из Табеля."+"\n"+"(Выбор Да/Нет на следующем диалогом окне).", "uuid",(String) v.getTag(), (String) ФИОДляУдаление.getText());*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return false;
            }
        };


    }
///todo конецслушателя long для уделанеи самого табеля

    ///todo сообщение
    @UiThread
    void СообщениеПредпреждаетОВыбореУдалениеСамогоТабеля(String ШабкаДиалога, String СообщениеДиалога,
                                                          String СамИндификаторUUID, String UUIDУдаляемогоТабеляКАкТекст,
                                                          String НазваниеУдаляемогоТАбеля, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) {


        Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(R.drawable.icon_dsu1_tabel_info)
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
                /////todo сообщением передохим вв удаления табеля
                Long СамUUIDТабеляКакLONG = Long.valueOf(UUIDУдаляемогоТабеляКАкТекст);
                ///todo
                СообщениеВыборУдлалянияТабеляИзБазы("Удаление Шаблона", "Удалить шаблон ?. " + НазваниеУдаляемогоТАбеля,
                        "Templates", СамUUIDТабеляКакLONG, НазваниеУдаляемогоТАбеля, НазваниеУдаляемогоТАбеляВЦифровомФормате);


            }
        });
    }
//todo  конеч сообщение предупреждения удлаения табеля


    ///todo сообщение
    @UiThread
    protected void СообщениеВыборУдлалянияТабеляИзБазы(String ШабкаДиалога, String СообщениеДиалога, String ИндификаторUUID,
                                                       Long СамоЗначениеUUID, String НазваниеУдаляемогоТАбеля, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_delete_customer)
                    .show();
/////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеля = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУдалениеСотрудникаИзТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "ИндификаторUUID " + ИндификаторUUID + " СамоЗначениеUUID " + СамоЗначениеUUID + "  " + ПолученноеЗначениеИзСпинераДата);
//////todo
                    МетодУдалениеСамогоТабеля(ИндификаторUUID, СамоЗначениеUUID, НазваниеУдаляемогоТАбеля, ПолученноеЗначениеИзСпинераДата, НазваниеУдаляемогоТАбеляВЦифровомФормате); //// TODO передаеюм UUID для Удалание

                }
            });

            /////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеляОтмена = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxУдалениеСотрудникаИзТабеляОтмена.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    //todo метод удаление сотрудника из табеля
    private void МетодУдалениеСамогоТабеля(String ДляУдалениеUUID, Long СамоЗначениеUUID, String НазваниеУдаляемогоТАбеля, String ПолученноеЗначениеИзСпинераДата, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) {

        StringBuffer ПрисутстуетСотрудникВУдаляемомоТабеле = new StringBuffer();

        final Cursor[] Курсор_КоторыйПроверяетПУстойЛиТабеля = {null};
        try {
            Log.d(this.getClass().getName(), " СамоЗначениеUUID " + СамоЗначениеUUID + " ДляУдалениеUUID " + ДляУдалениеUUID);

            /////todo код курсор выясняеть нет вполе fio сотрудник короче если в табеле нет ни одного сотрудника
            long РезультатУдалениеСамогоТАбеля = 0;


            Log.d(this.getClass().getName(), " НазваниеУдаляемогоТАбеля" + НазваниеУдаляемогоТАбеля);

            String НазваниеТаблицыГдеУдалитьШАБЛОН=   "Templates";
//TODO удаление ТАБЕЛЯ ПРИУСЛОВИИ ЧТО В ТАБЕЛЕ НЕТ СОТРУДНИКОВ


//TODO удаление ТАБЕЛЯ ПРИУСЛОВИИ ЧТО В ТАБЕЛЕ НЕТ СОТРУДНИКОВ

            Integer РезультатУдалениеШаблона = МетодУдалениеТабеляПриУсловииЧтоНетСотрудниковВнем(ДляУдалениеUUID,
                    СамоЗначениеUUID, НазваниеУдаляемогоТАбеля, ПрисутстуетСотрудникВУдаляемомоТабеле,
                    Курсор_КоторыйПроверяетПУстойЛиТабеля, НазваниеУдаляемогоТАбеляВЦифровомФормате,
                    НазваниеТаблицыГдеУдалитьШАБЛОН);


            Log.d(this.getClass().getName(), " НазваниеУдаляемогоТАбеля" + НазваниеУдаляемогоТАбеля + "  РезультатУдалениеШаблона " + РезультатУдалениеШаблона);


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }finally {
            // TODO: 01.02.2022  ПОСЛЕ УДАЛЕНИЕ ШАБЛОНА ПЕРЕГРУЖАЕМ ЭКРАН



            /////
            ScrollНаАктивтиСозданныхТабелей.destroyDrawingCache();

            ////
            ScrollНаАктивтиСозданныхТабелей.requestLayout();



            onStart();

        }
    }


    Integer МетодУдалениеТабеляПриУсловииЧтоНетСотрудниковВнем(String ДляУдалениеUUID, Long СамоЗначениеUUID, String НазваниеУдаляемогоТАбеля,
                                                               StringBuffer присутстуетСотрудникВУдаляемомоТабеле,
                                                               Cursor[] курсор_КоторыйПроверяетПУстойЛиТабеля, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате
            ,String НазваниеТаблицыГдеУдалитьШАБЛОН)
            throws ExecutionException, InterruptedException, TimeoutException {
        ///////
        Integer РезультатУдалениеСамогоТАбеля = 0;

        try {
            Class_MODEL_synchronized classModel_synchronizedСсылкаДляУдаленияШаблона =new Class_MODEL_synchronized(getApplicationContext());

            String СодержимоеКурсора = null;

            String СодержимоеКурсораНазваниеТабеля = null;
            //todo если данныз нет то не удаляем Табеля удаляем если нет не одного сотрудника в нутир табеля


//////////////////////////////


            // TODO: 28.01.2022

            РезультатУдалениеСамогоТАбеля = 0;

// TODO: 28.01.2022 удаление  ВО В ВЕРХНЕЙ ТАБЛИЦЕ

            /////TODO КОД ПРИ ОБНОВЛЯЕМ ПРИ ТАБЕЛЯ (ВАРИАНТ ВАРИАНТ УДЯЛЯЕМ А НИЖЕ ПРОСТО ОБНОВЛЯЕМ КОЛОКУ И ВПИСЫВАЕМ уДАЛЕННЫЕ)
            РезультатУдалениеСамогоТАбеля = classModel_synchronizedСсылкаДляУдаленияШаблона.
                    УдалениеТолькоШАблонЧерезКонтейнерУниверсальная(НазваниеТаблицыГдеУдалитьШАБЛОН,
                            "uuid",
                            String.valueOf(СамоЗначениеUUID));
            //todo очищаем память


            Log.d(this.getClass().getName(), " РезультатУдалениеСотрудникаИзТаблея " + РезультатУдалениеСамогоТАбеля + " СамоЗначениеUUID " + СамоЗначениеUUID);










            // TODO: 28.01.2022 удаление  ВО ВТОРОЙ ТАБЛИЦЕ НИЖНЕЙ
            Integer РезультатУдалениеСамогоФИО_Шаблон = 0;





            Log.d(this.getClass().getName(), " РезультатУдалениеСотрудникаИзТаблея " + РезультатУдалениеСамогоТАбеля + " СамоЗначениеUUID " + СамоЗначениеUUID);

            if (РезультатУдалениеСамогоТАбеля > 0) {

                Log.d(this.getClass().getName(), " УСпешное удаление ШАБЛОНА ШАБЛОНА Templates  РезультатУдалениеСамогоФИО_Шаблон " + РезультатУдалениеСамогоФИО_Шаблон + " СамоЗначениеUUID " + СамоЗначениеUUID);



                /////TODO КОД ПРИ ОБНОВЛЯЕМ ПРИ ТАБЕЛЯ (ВАРИАНТ ВАРИАНТ УДЯЛЯЕМ А НИЖЕ ПРОСТО ОБНОВЛЯЕМ КОЛОКУ И ВПИСЫВАЕМ уДАЛЕННЫЕ)
                РезультатУдалениеСамогоФИО_Шаблон = classModel_synchronizedСсылкаДляУдаленияШаблона.УдалениеТолькоШАблонЧерезКонтейнерУниверсальная("fio_template",
                        "fio_template",
                        String.valueOf(СамоЗначениеUUID));
                //todo очищаем память
            }


            Log.d(this.getClass().getName(), " РезультатУдалениеСамогоФИО_Шаблон " + РезультатУдалениеСамогоФИО_Шаблон + " СамоЗначениеUUID " + СамоЗначениеUUID);





            ///TODO СООБЩЕНИЕ О РЕЗУЛЬТАТОВ
            if (РезультатУдалениеСамогоТАбеля > 0) {


                //todo после успешной уданеие обнуляем ерпеменные
                ДляУдалениеUUID = null;

                СамоЗначениеUUID = null;

                РезультатУдалениеСамогоТАбеля = 0;

                ///todo сообещение о успешном удалении
                Long ВстакаРезультата = Long.parseLong(String.valueOf(РезультатУдалениеСамогоТАбеля));

                Log.d(this.getClass().getName(), " УСпешное удаление ШАБЛОНА ШАБЛОНА fio_template  РезультатУдалениеСамогоФИО_Шаблон " + РезультатУдалениеСамогоФИО_Шаблон + " СамоЗначениеUUID " + СамоЗначениеUUID);



            } else {
                СообщениеПослеУдаленияСамогоТАбеля("Шаблоны", "Операция удалание Шаблона не прошла ", false,
                        НазваниеУдаляемогоТАбеляВЦифровомФормате);
            }
            //TODO в табеле присуствют сотрудника



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеСамогоТАбеля;
    }


    ///todo  конец метода удаления третий обработчки нажатия
    ///////МЕТОД СОЗДАННИЕ СПИНЕРА

    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСотрудникаИзТабеля(String ШабкаДиалога, String СообщениеДиалога, boolean Статус, Long СамоЗначениеUUID, String ДляУдалениеUUID,
                                                            Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        int Значек;
        if (Статус) {
            Значек = R.drawable.icon_dsu1_tabel_info;
        } else {
            Значек = R.drawable.icon_dsu1_delete_customer;///
        }
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("Удалить", null)
                .setNegativeButton("Нет", null)
                .setIcon(Значек)
                .show();
/////////кнопка
        final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");

                //TODO  второе действие заполенние контентом  в табеля в TableLyзаполения табеля из базы через элемент TableLauy
                //todo код послеу успешного удаления табеля
                //todo
            }
        });
        /////////////


        final Button MessageBoxUpdateСоздатьТабельВсеравноУдлаитьВместеССотрудникамиТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        MessageBoxUpdateСоздатьТабельВсеравноУдлаитьВместеССотрудникамиТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");

////

                ///
                try {
                    МетодУдалениеВсехСотрудниковВТАбеле(СамоЗначениеUUID, ДляУдалениеUUID, НазваниеУдаляемогоТАбеляВЦифровомФормате);

                    ///todo команда которая удаляет выбранный табель
///todo команда которая удаляет выбранный табель


                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                try {

                    ///todo метод для удаления табеля
                    МетодДляУдалениеШаблонаЕслиВнемНетСотрудников();
                    //////
                    //////

                    ////todo заполение спинера
                    МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля();////метод вызаваем все созжданные ТАБЕДЯ ИЗ БАПЗЫ И ДАЛЕЕ ИХ ЗАПИСЫВАЕМ В ОБМЕН
                    ////todo заполение спинера
                    //////


                    МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля();

                    //
                    //////

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                //TODO  второе действие заполенние контентом  в табеля в TableLyзаполения табеля из базы через элемент TableLauy
                //todo код послеу успешного удаления табеля
                //todo
            }
        });
        /////////////


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void МетодУдалениеВсехСотрудниковВТАбеле(Long СамоЗначениеUUID, String ДляУдалениеUUID, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) throws ExecutionException, InterruptedException, TimeoutException {

        final long[] РезультатУдалениеВсехСотрудниковСамогоТАбеля = {0};
        try {


            String СамоЗначениеUUIDДляУдаланиевсехСотрудников = null;

            Iterator<String> итераторДляУдалениеВсегоТабеля = СодержимоеКурсораUUIDТабеляПриУдалениеиТАбеляилиВместеССотрудником.iterator();


            СамоЗначениеUUIDДляУдаланиевсехСотрудников = итераторДляУдалениеВсегоТабеля.next();
            System.out.println(СамоЗначениеUUIDДляУдаланиевсехСотрудников);


            ///////

            РезультатУдалениеВсехСотрудниковСамогоТАбеля[0] = new Class_MODEL_synchronized(getApplication()).
                    УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная("tabels", "cfo",
                            Long.parseLong(String.valueOf(НазваниеУдаляемогоТАбеляВЦифровомФормате)));

            System.out.println("РезультатУдалениеВсехСотрудниковСамогоТАбеля " + РезультатУдалениеВсехСотрудниковСамогоТАбеля[0]);

            //todo очищаем память

            System.out.println(РезультатУдалениеВсехСотрудниковСамогоТАбеля[0]);


            //  ССылкаНаСозданнуюБазу.close();

            СообщениеПослеУдаленияСамогоТАбеля("Оповещение Табеля", "Успешное удалание Табеля"
                    + "\n" + " (с сотрудниками): "
                    + СодержимоеКурсораUUIDТабеляПриУдалениеиТАбеляилиВместеССотрудником.size(), true, НазваниеУдаляемогоТАбеляВЦифровомФормате);


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
////todo конец фильаного сообщения о удалени самого табеля


    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСамогоТАбеля(String ШабкаДиалога, String СообщениеДиалога, boolean Статус, Integer НазваниеУдаляемогоТАбеляВЦифровомФормате) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ


//////сам вид
        int Значек;
        if (Статус) {
            Значек = R.drawable.icon_dsu1_tabel_info;
        } else {
            Значек = R.drawable.icon_dsu1_delete_customer;
        }
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(Значек)
                .show();
/////////кнопка
        final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");
                //////// todo если успешно удаление табеля то запускаем сообщение
                if (Статус) {

                    ///TODO попытка открыть экран как full screan
                    ////////ЗАПОЛНЯЕМ АРАЙЛИСТ
                    ////////ЗАПОЛНЯЕМ АРАЙЛИСТ
                    try {

                        ///todo метод для удаления табеля
                        МетодДляУдалениеШаблонаЕслиВнемНетСотрудников();
                        //////
                        //////

                        ////todo заполение спинера
                        МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля();////метод вызаваем все созжданные ТАБЕДЯ ИЗ БАПЗЫ И ДАЛЕЕ ИХ ЗАПИСЫВАЕМ В ОБМЕН
                        ////todo заполение спинера
                        //////


                        МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля();

                        //
                        ScrollНаАктивтиСозданныхТабелей.forceLayout();

                        //////

                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                //TODO  второе действие заполенние контентом  в табеля в TableLyзаполения табеля из базы через элемент TableLauy
                //todo код послеу успешного удаления табеля
                //todo
            }
        });
    }
////todo конец фильаного сообщения о удалени самого табеля


    private void МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля() {
        try {

            /////////// todo КОНЕЦ СОЗДАНИЕ КОМПОНЕТА КАЛЕНДАРЬ
            //
            Log.i(this.getClass().getName(), " НАЧИНАЕМ   МетодаСозданиеТабеляИзБазы()  .....  ");

            МетодаСозданиеТабеляИзБазы(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗЫ


            //
            Log.i(this.getClass().getName(), "  ОТРАБОТАЛ  МетодаСозданиеТабеляИзБазы() ");


//поймать ошибку всего классаIOException | MyException e    NumberFormatException
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        /////МЕТОД ЗАГРУКЗИ КОЛИЧЕСТВО ТАБЕЛЕЙ ИЗ БАЗЫ\


    }


    ////TODO Метод Преоразует цифру в  названия месяца
    private String МетодДляПреоразванияЦифрыВНазванияМесяца(Cursor Курсор_ВЫводимМаксимальнуюДатуДляСпинера) {
        String МаксимальнаяМесяцДляСпинера;
        String МаксимальнаяГодДляСпинера;
        String МаксимальнаяНазваниеДляСпинера;
        String ПолученыеМесяцНеОбработанный = null;
        try {


            МаксимальнаяМесяцДляСпинера = Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(0);
            //
            Log.i(this.getClass().getName(), "МаксимальнаяМесяцДляСпинера[0] " + МаксимальнаяМесяцДляСпинера);

            МаксимальнаяГодДляСпинера = Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(1);
            //
            Log.i(this.getClass().getName(), "МаксимальнаяГодДляСпинера[0] " + МаксимальнаяГодДляСпинера);

            МаксимальнаяНазваниеДляСпинера = Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(2);
            //
            Log.i(this.getClass().getName(), " МаксимальнаяНазваниеДляСпинера[0] " + МаксимальнаяНазваниеДляСпинера);


            ///TODO из цифры в НАзвание




                /*            Locale ee=Locale.forLanguageTag("ru");
                            Calendar cal=Calendar.getInstance();
                            cal.set(Calendar.MONTH,Integer.parseInt(МаксимальнаяМесяцДляСпинера));
                            String s=cal.getDisplayName(Calendar.MONTH,Calendar.LONG_FORMAT,new Locale("rus"));
*/


            DateFormat df = new SimpleDateFormat("MM/yyyy");
            Date date = df.parse(МаксимальнаяМесяцДляСпинера + "/" + МаксимальнаяГодДляСпинера);
            System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010




                /*    SimpleDateFormat f = new SimpleDateFormat("MMM", new Locale("ru"));
                    SimpleDateFormat f1 = new SimpleDateFormat("LLL", new Locale("ru"));
                    SimpleDateFormat f2 = new SimpleDateFormat("MMMM", new Locale("ru"));*/
            SimpleDateFormat ПреобразованиеЦифраВНАзваниемесяца = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));

            ПолученыеМесяцНеОбработанный = ПреобразованиеЦифраВНАзваниемесяца.format(date);
            System.out.println(ПолученыеМесяцНеОбработанный);

            ПолученыеМесяцНеОбработанный = (ПолученыеМесяцНеОбработанный.substring(0, 1).toUpperCase() +
                    ПолученыеМесяцНеОбработанный.substring(1).toLowerCase());

            Log.i(this.getClass().getName(), " ПолученыеМесяцНеОбработанный " + ПолученыеМесяцНеОбработанный);


            //поймать ошибку всего классаIOException | MyException e    NumberFormatException
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученыеМесяцНеОбработанный;
    }


////////НАЧАЛО МЕТОД СОЗДАНИЕ ТАБЕЛЯ


    protected void МетодаСозданиеТабеляИзБазы() throws InterruptedException, ExecutionException, TimeoutException, ParseException {
        /////////
        SQLiteCursor Курсор_КоторыйЗагружаетГотовыеШаблоны = null;
        /////////
        SQLiteCursor Курсор_КоторыйЗагружаетГотовыеШаблоныВнутриМаксимальнаяДата = null;

        //

        String МесяцМаскимальнаяДатавТабеляхПоМесецям = null;


        int ПолученнаяUUIDНазванияОрганизации = 0;

        //String    НазваниеТабеля=null;


        ////todo ДАННЫЙ КУРСОР ДЛЯ ПОКАЗА НОВГО ТАБЕЛЯ
        try {

            //// todo ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ БАЗЫ ДАННЫХ

            try {
                //////метод которы из текста которйц было арпдчьадена дата обраьно преобразует в дату для проверки
                LinearLayoutСозданныхТабелей.removeAllViews();

            } catch (Exception e) {
                 /*   e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());*/
            }

            Log.d(this.getClass().getName(), " код загружает все созданные табеля из базы " + КакойКонтекст);
            //////TODO преобразовыываем оборатно из названиея месяц в цифру


            ///TODO СЮДА ЗАХОДИМ ТОЛЬКО КОГДА НОВЫЙ ТАБЕЛЬ


//TODO данный курсор работает когда не новый а старый простмартиваем ТАбель(создаенный ранее)

//////todo загружаем табеоля в начале  ЗАГРУЖАЕМ В СПИНЕР ДАТЫ А НЕ А ТАБЕЛЯ  ДАННЫЕ ДЛЯ СПИПЕР


            ///todo для загрузки для конкретного сотркдника название организации запрос каждому сотрунику организации

            ПолученнаяUUIDНазванияОрганизации = МетодПолучениеОрганизацииНепосрдственодляДанногоСОтрудника();

            ///////
            Log.d(this.getClass().getName(), "ПолученнаяUUIDНазванияОрганизации " + ПолученнаяUUIDНазванияОрганизации);


            ///TODO ГЛАВНЫЙ КУРСОР ЗАГРУЗКИ САМИХ ТАБЕЛЕЙ НА АКТИВТИИ

            // ID


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ = new Class_GRUD_SQL_Operations(getApplicationContext());
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());


            ///////
            SQLiteCursor Курсор_ПолучаемПубличныйID = (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if (Курсор_ПолучаемПубличныйID.getCount() > 0) {
                ///
                Курсор_ПолучаемПубличныйID.moveToFirst();

                /////
                ПубличноеIDПолученныйИзСервлетаДляUUID = Курсор_ПолучаемПубличныйID.getInt(0);
///


                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }


            //////////
            Log.d(this.getClass().getName(), "ПолученнаяUUIDНазванияОрганизации " + ПолученнаяUUIDНазванияОрганизации
                    + " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsСозданиеТабеляИзБазы = new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsСозданиеТабеляИзБазы = new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "Templates");
            ///////
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");
            //
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " status_send !=? AND user_update=? ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", "Удаленная");
            ///
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ПубличноеIDПолученныйИзСервлетаДляUUID);
            ///
                  /*  class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
            ////TODO другие поля

            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки", "name_templates");
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_КоторыйЗагружаетГотовыеШаблоны = (SQLiteCursor) class_grud_sql_operationsСозданиеТабеляИзБазы.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsСозданиеТабеляИзБазы.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            ////////

            Log.d(this.getClass().getName(), "GetData " + Курсор_КоторыйЗагружаетГотовыеШаблоны);





/*
            // TODO: 07.09.2021     __old


                                        Курсор_КоторыйЗагружаетГотовыеШаблоны = new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("Templates",
                                                new String[]{"*"},
                                                "status_send !=? AND user_update=? ",
                                                new String[]{"Удаленная",PUBLIC_CONTENT.ПубличноеIDПолученныйИзСервлетаДляUUID}, "name_templates", null, "date_update DESC", null);///  Удаленная  //  IS NOT NULL AND name_templates!=?  AND

                                        // "SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере*/
            ///////
//
            //////todo работающий NULL в query
            Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount() " + Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount());
            ////todo получаем значение ищем
            ///

            /////TODO курсор для нахождения даты максимальной
            SQLiteCursor Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата = null;

            Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата = МетодКоторыйПоказываетМаксимальнуюДатуИзменения(ПолученнаяUUIDНазванияОрганизации);


            // TODO: 07.09.2021  результат


            if (Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата.getCount() > 0) {
                ////////
                Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата.moveToFirst();


                String МаскимальнаяДатавТабеляхПоМесецям = Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата.getString(0);

                МесяцМаскимальнаяДатавТабеляхПоМесецям = Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата.getString(1);

//                  МаскимальнаяДатавТабеляхПоМесецямДляСравнение = new android.icu.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(МаскимальнаяДатавТабеляхПоМесецям);//

                Log.d(this.getClass().getName(), " МаскимальнаяДатавТабеляхПоМесецям  " + МаскимальнаяДатавТабеляхПоМесецям
                        + " МесяцМаскимальнаяДатавТабеляхПоМесецям  " + МесяцМаскимальнаяДатавТабеляхПоМесецям);

            } else {
                МесяцМаскимальнаяДатавТабеляхПоМесецям = "";
            }

            //TODO закрываем курсор с максимальной датой
            Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата.close();


            //////////////
            if (ПолученнаяUUIDНазванияОрганизации == 0) {
                /////
                МетодКогдаДанныхСамихТабелйНет();
                //Toast.makeText(getApplicationContext(), "Не выбрана организация (в настройках выберети ОРГАНИЗАЦИЮ и зайтите еще раз)", Toast.LENGTH_LONG).show();
            }

            //поймать ошибку всего классаIOException | MyException e    NumberFormatException


            ////
            /////УДАЛЯЕМ ИЗ ПАМЯТИ  ОТРАБОТАННЫЙ АСИНАТСК
            /////
            /////// обнуляем
            ///
            String[] НазваниеТабеля = {""};
            String[] ДатаТабеляИзБАзы = {""};


            ////todo ЗАГРУЖАЕМ КУРСОР ПОЛУЧЕННЫЙ С ГОТОВЫМИ ТАБЕЛЯ ЗА КОНКЕРТНЫЙ МЕСЯЦ
            if (Курсор_КоторыйЗагружаетГотовыеШаблоны != null) {/////ЕСЛИ ЕСТЬХОТЯБЫ ОДИН ТАБЕЛЬ
                Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеШаблоны " + Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount());
            }

            if (Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount() > 0) {/////ЕСЛИ ЕСТЬХОТЯБЫ ОДИН ТАБЕЛЬ

                Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеШаблоны " + Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount());

                ////TODO СТАВИМ КУРСОР НА НУЖНУЮ ПОЗИЦИЮ
                Курсор_КоторыйЗагружаетГотовыеШаблоны.moveToFirst();
                Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеШаблоны " + Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount());
                ///TODO


                final int[] ИндексДляСозданныхОбьектовНаАктивитиТАбель = {0};

                ///todo удалчем элемены перед новой вставкой обтьектов на активти


                try {

                    //
                    LinearLayoutСозданныхТабелей.removeAllViews();/////удалем данные с актиывти
                    LinearLayoutСозданныхТабелей.invalidate();


                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
               /* Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());*/
                }


                String СамСтатусАтбеля;


                // TODO: 31.01.2022 ГЛАВНЫЙ ЦИКЛ


        /*        if (TExtvieeСловоТабельВсегоШАблонов.getText() != null) {

                    TExtvieeСловоТабельВсегоШАблонов.setText(TExtvieeСловоТабельВсегоШАблонов.getText());
                }
*/


                    TExtvieeСловоТабельВсегоШАблонов.setText(TExtvieeСловоТабельВсегоШАблонов.getText());




                ////// todo заргужаем название табелеей ЦИКЛ загружаем на активти  УЖЕ СОЗДАННЫЕ ТАБЕЛЯ И ВИД ИХ ДЕЛАЕМ КАК КНОПКА
                do {
                    Log.d(this.getClass().getName(), " Количество Строчек В табеле " + Курсор_КоторыйЗагружаетГотовыеШаблоны.getCount() + " Количество столбиков в табеле " +
                            Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnCount());
                    //////
                    /* TimeUnit.MILLISECONDS.sleep(30);*/


                    ////// создаем строчку и кнопку

                    /////TODO  ИЗВЛЕКАМ ДАННЫЕ ИЗ КУРСОРА ТАБЕЛЬ И ЕГО НАЗВАНИЕ UUID  ИТД
                    int ИндексНазваниеТабеля = Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnIndex("name_templates");

                    НазваниеТабеля[0] = Курсор_КоторыйЗагружаетГотовыеШаблоны.getString(ИндексНазваниеТабеля);

                    int ИндексДата = Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnIndex("date_update");

                    ДатаТабеляИзБАзы[0] = Курсор_КоторыйЗагружаетГотовыеШаблоны.getString(ИндексДата);
                    //todo перерводим в дату для СРАВНЕНИЯ


                    int ИндексID = Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnIndex("id");

                    Long ЦифровоеНазваниеТабеля = Курсор_КоторыйЗагружаетГотовыеШаблоны.getLong(ИндексID);


                    Log.d(this.getClass().getName(), " НазваниеТабеля " + ЦифровоеНазваниеТабеля);

                    //TODO статус табеля

                    //  int ИндексСтатусаТабеляПроведенИлиНет= Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnIndex("status_carried_out");

                    // СамСтатусАтбеля = Курсор_КоторыйЗагружаетГотовыеШаблоны.getString(ИндексСтатусаТабеляПроведенИлиНет);


//                        Date МаскимальнаяДатавТабеляхПоМесецямДляСравнениеВЦикле = new android.icu.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(ДатаТабеляИзБАзы[0]);


                    Log.d(this.getClass().getName(), " НазваниеТабеля " + НазваниеТабеля[0] + " ДатаТабеляИзБАзы " + ДатаТабеляИзБАзы[0]);
                    ///////// TODO добаляем кнопки
                    ШАблонвВидеКнопок = new Button(КонтекстШаблоны);////СОЗДАЕМ НОВЫЕ КНОПКИ НА АКТИВТИ
                    //////
                    ШАблонвВидеКнопок.setId(ИндексДляСозданныхОбьектовНаАктивитиТАбель[0]);
                    ///TODO определяем для созданого табеля его UUID для последющей работы
                    ///todo не посредственно для табеля
                    int ИндексГдеНаходитьсяUUIDВТАБЕЛЕ = Курсор_КоторыйЗагружаетГотовыеШаблоны.getColumnIndex("uuid");
                    ////
                    String НепостредственоеЗначениеUUIDСозданогоТАбеля = "";
                    ///////
                    if (ИндексГдеНаходитьсяUUIDВТАБЕЛЕ >= 0) {
                        // TODO: 31.01.2022
                        НепостредственоеЗначениеUUIDСозданогоТАбеля = Курсор_КоторыйЗагружаетГотовыеШаблоны.getString(ИндексГдеНаходитьсяUUIDВТАБЕЛЕ).trim();
                    }


                    ////todo когда данные в табелй есть  САМИ ДАННЫЕ ТАБЕЛЕЙ ЗАГРУЖАЮТЬСЯ
// TODO: 31.01.2022
                    ШАблонвВидеКнопок.setTag(НепостредственоеЗначениеUUIDСозданогоТАбеля);
                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setId(Integer.parseInt(String.valueOf(ЦифровоеНазваниеТабеля)));
                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setMinLines(8);
                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setTextSize(13);
                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setHintTextColor(Color.RED);

                    // TODO: 31.01.2022 название
                    Log.w(this.getClass().getName(), " НазваниеТабеля " + НазваниеТабеля[0] + " ДатаТабеляИзБАзы " + ДатаТабеляИзБАзы[0]);

                    if (НазваниеТабеля[0] != null) {

                        // TODO: 31.01.2022
                        ШАблонвВидеКнопок.setText(НазваниеТабеля[0]);
                    }


// TODO: 31.01.2022
                    ШАблонвВидеКнопок.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
// TODO: 31.01.2022
                    ШАблонвВидеКнопок.setTextColor(Color.BLACK);
// TODO: 31.01.2022
                    ШАблонвВидеКнопок.setHintTextColor(Color.RED);

                    /////

// TODO: 31.01.2022
                    Drawable icon = getResources().getDrawable(R.mipmap.icon_dsu1_tabels_sam_tamples);
                    icon.setBounds(10, 0, 80, 60);

                    //  ШАблонвВидеКнопок.setPadding (0,0, 0, 150);

                    // TODO: 31.01.2022
                    ШАблонвВидеКнопок.setCompoundDrawables(icon, null, null, null);


                    ////
                    ШАблонвВидеКнопок.setBackground(getApplication().getResources().getDrawable(R.drawable.textlines_tabel_row_color_green_mini));


                    ШАблонвВидеКнопок.setWidth(100);

                    //////ДОБАЯЛЕМ СТРОЧКУ

//////todo сЛУШАТЕЛЬ ДЛЯ УДАЛЕНИЯ ТАБЕЛЯ
                    ШАблонвВидеКнопок.setOnLongClickListener(СлушательУдаланиеСамогоТабеля);

                    // TODO ТУТ ДАННЫЙ КОД СРАБАТЫВАЕТ ПОСЛЕ ТОГО КАК МЫ СОЗДАЛИ НОВЫЙ ТАБЕЛЬ И КНОПКА С НОВЫМ НАЗВАНИЕМ ТОЖЕ СОЗДААЛСЬ И МЫ ПО НЕЙ ПЕРЕХОДИМ НА НОВЫЙ ТАБЕЛЯ КЛИКНУТ ПО КНОПКЕ ТАБЕЛЬ

                    ////// todo КЛИК ПО КНОПКАМ --СОЗДАННЫМ НОВЫЕ ТАБЕЛЯ   //////КЛИК ПО КНОПКАМ --СОЗДАННЫМ НОВЫЕ ТАБЕЛЯ
                    //////КЛИК ПО КНОПКАМ --СОЗДАННЫМ НОВЫЕ ТАБЕЛЯ   //////КЛИК ПО КНОПКАМ --СОЗДАННЫМ НОВЫЕ ТАБЕЛЯ


                    ////////


////TODO переходим на азаполения созваышемся ШАБЛОНОМ в табель непосрдсвтенно

                    ////////
                    ШАблонвВидеКнопок.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ///
                            try {

                                // Vibrate for 500 milliseconds

                                ////

                                /// ((TextView) v).setBackgroundColor(Color.RED);

                                //  if (ЗапускШаблоновFaceAppБлокировкаКнопкиДа==false) {

                                Button КнопкаНАжалиНАВыбраныйШаблон = (Button) v;

                                // TODO: 31.01.2022 оформления при нажатиии
                                ////


                                try {
                                    SQLiteCursor Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней =
                                            МетодЗаполненияТабеляИзЗаранееСозданогоШабона();


                                    Log.d(this.getClass().getName(), " Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней "
                                            + Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней);


/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
                                    МетодЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель((Button) v,
                                            Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {

                                    // TODO: 31.01.2022


                                    КнопкаНАжалиНАВыбраныйШаблон.setTextColor(Color.BLACK);
// TODO: 31.01.2022
                                    КнопкаНАжалиНАВыбраныйШаблон.setHintTextColor(Color.RED);

                                    /////

// TODO: 31.01.2022
                                    Drawable icon = getResources().getDrawable(R.mipmap.icon_dsu1_tabels_sam_tamples);
                                    icon.setBounds(10, 0, 80, 60);

                                    //  ШАблонвВидеКнопок.setPadding (0,0, 0, 150);

                                    // TODO: 31.01.2022
                                    КнопкаНАжалиНАВыбраныйШаблон.setCompoundDrawables(icon, null, null, null);


                                    КнопкаНАжалиНАВыбраныйШаблон.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.textlines_tabel_row_color_green_mini_longclick));


                                    //КнопкаНАжалиНАВыбраныйШаблон.setBackgroundColor(Color.GRAY);


                                }
                                //      }


                                /////
                                ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            ///  ((TextView) v).setBackgroundColor(Color.parseColor("#F5FFFA"));

                        }


                        ////////////////////////TODO event


                        ////////


                        /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
                        protected void МетодЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель(
                                Button v,
                                SQLiteCursor Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней) {

                            ///
                            Class_GRUD_SQL_Operations class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель;
                            // TODO: 07.09.2021

                            SQLiteCursor Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм = null;
                            try {


                                // TODO: 12.03.2021 первое действие получаем данные данного шаблона
                                // ((TextView) v).setBackgroundColor(Color.RED);


                                Log.d(this.getClass().getName(), " Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней  "
                                        + Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней);

                                // String ПередаемСозданнуюДатуНовогоТабеля = (String) ((TextView) СпинерВыборДату.getChildAt(0)).getText();///дата нового табеля
                                ///////todo ВЫТАСКИЕВАЕМ НАЗВАНИЕ ТАБЕЛЯ
                                Button ИзКнопкиПолучаемНазваниеТабеля = v;

                                String ПередаемСозданнуюНазваниеТабеля = ИзКнопкиПолучаемНазваниеТабеля.getText().toString();

                                Log.d(this.getClass().getName(), " ПередаемСозданнуюНазваниеТабеля  " + ПередаемСозданнуюНазваниеТабеля);

                                ///////todo ВЫТАСКИЕВАЕМ НАЗВАНИЕ ТАБЕЛЯ

                                Button ИзКнопкиПолучаемUUIDТабеля = v;

                                Object ПередаваемыйИзКнопкиПолучаемUUIDТабеля = ИзКнопкиПолучаемНазваниеТабеля.getTag();

                                Log.d(this.getClass().getName(), " ПередаваемыйИзКнопкиПолучаемUUIDТабеля  " + ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString());


                                /////////TODO действие второе
                                // TODO: 12.03.2021 выше полученных курсор с данными   Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм


                                Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм = null;

                                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                                ///
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель = new Class_GRUD_SQL_Operations(getApplicationContext());

                                ///
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "Fio_Template");
                                ///////
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");///fio_uuid
                                //
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "fio_template=? ");
                                ///"_id > ?   AND _id< ?"

                                Long РодительскийUUIDДляПоскаДанных = Long.parseLong(ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString().trim());
                                //////
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РодительскийUUIDДляПоскаДанных);
                                ///
                           /*         class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
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
                                class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                                ////
                                /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                                ////

                                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

                                Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм = (SQLiteCursor) class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                                //////


                                Log.d(this.getClass().getName(), "GetData " + Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм);





                         /*           // TODO: 07.09.2021    _old
                                                Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм =
                                                        new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("Fio_Template",
                                                                new String[]{"fio_uuid"},//     new String[]{"name,id,uuid,BirthDate,snils},
                                                                "fio_template=? ",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                                                                new String[]{ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString()},
                                                                null, null, "date_update DESC", null);
*/





                                StringBuffer stringBuffer = new StringBuffer();

                                int lines = 0;

                                ////todo результат

                                if (Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount() > 0) {

                                    Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.moveToFirst();

                                    Log.d(this.getClass().getName(), "Курсор_ПолчеамДанныеУжеСозданнымШаблоном.getCount() "
                                            + Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount());


                                    Log.d(this.getClass().getName(), "Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount() "
                                            + Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount());


                                    // TODO: 14.03.2021
                                    //
                                    Cursor ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм = null;

                                    // TODO: 26.09.2021  КОПИРУЕМ ОДИН КУРСОР ИЗ ДРУГОВА КУРСОР АДЛЯ ВИЧЧИСЛЕНИЯ ФИО ПО UUID ИЩ РОДИТЕЛЬСКОЙ ТАБЛИЦЫ
                                    ////
                                    ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм = Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм;




                                    ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.moveToFirst();


                                    // TODO: 17.03.2021 запускам даные из курсора

                                    do {


                                        //ССылкаНаСозданнуюБазу.execSQL(" DELETE FROM tabels WHERE fio= -9223372036854775808; ");

                                        //    ССылкаНаСозданнуюБазу.execSQL(" DELETE FROM Fio_Template WHERE fio_uuid= -9223372036854775808; ");


                                        //     int g=                     ССылкаНаСозданнуюБазу.delete("Fio_Template","fio_uuid=?",new String[]{"-9223372036854775808"});


                                        Integer ИндексГдеНАходитьсяUUIDФИО = ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getColumnIndex("fio_uuid");

                                        /////
                                        Long ПолученныйUUIDДЛЯПолучениеДанных = ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getLong(ИндексГдеНАходитьсяUUIDФИО);

                                        Log.d(this.getClass().getName(), "ПолученныйUUIDДЛЯПолучениеДанных "
                                                + ПолученныйUUIDДЛЯПолучениеДанных);


                                        // TODO: 07.09.2021  резуьтат
                                        if (ПолученныйUUIDДЛЯПолучениеДанных > 0) {

                                            Cursor Курсор_ТаблицаФИО = null;
                                            ///


                                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                                            ///
                                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                                            ///
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель = new Class_GRUD_SQL_Operations(getApplicationContext());


                                            ///
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "fio");
                                            ///////
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "name,uuid");
                                            //
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "uuid=? ");
                                            ///"_id > ?   AND _id< ?"
                                            //////
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ПолученныйUUIDДЛЯПолучениеДанных);
                                            ///
                                       /*     class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                                            ///
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                                            //
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

                                            ////TODO другие поля*//*     class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                                            ///
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                                            //
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

                                            ////TODO другие поля*/

                                            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                                            ////
                                            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                                            ////
                                            class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                                            ////
                                            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                                            ////

                                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

                                            Курсор_ТаблицаФИО = (SQLiteCursor) class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗапускаетПереходНаЗаполенияСозданнымШАБЛОНОМВТабель.
                                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                                            //////

                                            Log.d(this.getClass().getName(), "GetData " + Курсор_ТаблицаФИО);




                                   /*         // TODO: 07.09.2021    ___old
                                           Курсор_ТаблицаФИО = new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("fio",
                                        new String[]{"name,uuid"},//     new String[]{"name,id,uuid,BirthDate,snils},
                                        "uuid=?",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                                        new String[]{String.valueOf(ПолученныйUUIDДЛЯПолучениеДанных)},
                                        null, null, "date_update DESC", null);
*/


                                            // TODO: 07.09.2021   resultat

                                            if (Курсор_ТаблицаФИО.getCount() > 0) {
                                                /////
                                                Курсор_ТаблицаФИО.moveToFirst();


                                                String ИмяПолученоИзФИо = Курсор_ТаблицаФИО.getString(0);


                                                stringBuffer.append(ИмяПолученоИзФИо).append("\n");

                                            }

                                        }

                                    } while (ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.moveToNext());
                                    /////


                                  /*  ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.close();

                                    Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.close();*/


                                    //ДляСообщениеКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.close();

                                    String yourInput = "...";

                                    Matcher m = Pattern.compile("\r\n|\r|\n").matcher(stringBuffer.toString());



                                    while (m.find()) {
                                        lines++;
                                    }
                                    lines = lines ;


                                } else {

                                    Snackbar.make(v, "В шаблоне нет сотрудников.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    //Toast.makeText(getApplicationContext(), "в шаблоне нет сотрудников.", Toast.LENGTH_SHORT).show();
                                }









                                ((TextView) v).setBackgroundColor(Color.parseColor("#F5FFFA"));

                                // TODO: 08.10.2021 ПЕРЕХОДИМ НА СОЗДАНЕИ СОТРУДНИКОВ ИЗ ШАБЛОНА


                                // TODO: 14.03.2021 метод записываем сотрудников в табель на базе ранее созданого шаблона
                                СообщениеКотороеСпрашиваетНужноЛиВставлятьСотрудниковИзРанееСозданогоШаблона("Шаблоны", "Заполнить Табель из Шаблона ? :"
                                                + "\n" + "\n" + stringBuffer.toString() + ":" + lines + " кол.", true,
                                        Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней, Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм, ПередаемСозданнуюНазваниеТабеля,
                                        ПередаваемыйИзКнопкиПолучаемUUIDТабеля,lines);


                                /////
                                ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }


                    });


                    ///todo клик по табелби преход на сотрудников
                    /////
/////


                    //////

                    if (МесяцМаскимальнаяДатавТабеляхПоМесецям.trim().equals(НазваниеТабеля[0].trim())) {


                        LinearLayoutСозданныхТабелей.addView(ШАблонвВидеКнопок, 0); /////СОЗДАЕМ НАКШИ КНОПКИ ВНУРИ СКРОЛБАР


                    } else {
                        //TODO  ОЧИЩАЕМ ПАМТЬ


                        ////////унопки распологаем внутири скролбара
                        LinearLayoutСозданныхТабелей.addView(ШАблонвВидеКнопок, ИндексДляСозданныхОбьектовНаАктивитиТАбель[0]); /////СОЗДАЕМ НАКШИ КНОПКИ ВНУРИ СКРОЛБАР


                    }
                    ИндексДляСозданныхОбьектовНаАктивитиТАбель[0]++;///увеличиваем


                    ////ТУТ НАПИСАН  КОД КОТОРЫЙ ЗАПУСКАЕТ САМ ТАБЕЛЬ ПРИ НАЖАТИИ НА СОЗДАННЫЕ КНОПКА-ТАБЕЛЬ


                    /////todo ЦИКЛ ЗАГРУЗКИ ТОЛЬКО НАЗВАНИЙ ТАБЕЛЯ
                } while (Курсор_КоторыйЗагружаетГотовыеШаблоны.moveToNext());
                /////toto ПОСЛЕ ВСТАВКИ ДАННЫХ ПЕРЕОПРЕДЕЛЯЕМ ВНЕГНИЙ ВИДЖ


///todo  когда в табеле нет сотрудников ПУСТОЙ ТАБЕЛЬ
            } else {
                МетодКогдаДанныхСамихТабелйНет();
            }

            ////TODO ПОСЛЕ ЗАПОЛЕНЕИЯ ТАБЕЛЯ В АКТИВИТИ
            LinearLayoutСозданныхТабелей.invalidate();

            LinearLayoutСозданныхТабелей.requestLayout();

            ScrollНаАктивтиСозданныхТабелей.invalidate();

            // TODO: 30.01.2022

            ScrollНаАктивтиСозданныхТабелей.requestLayout();


            ScrollНаАктивтиСозданныхТабелей.fullScroll(View.FOCUS_UP);


            ///TODO удалем из памяти курсор
            /////    Курсор_КоторыйЗагружаетГотовыеШаблоны.close();

            /////
            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 12.03.2021  метод записываем В КОНКРЕНТЫЙ ТАБЕЛЬ СОТРУДНИКОВ ИЗ ЗАРАНЕЕ СОЗДАНОГО ШАБЛОНА

    protected SQLiteCursor МетодЗаполненияТабеляИзЗаранееСозданогоШабона() {


        SQLiteCursor Курсор_НаОсновеПолученогоЭлектроногоНазваниеОрганицииПонемуПолучаемВсеИнформацииОТабеле = null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона = new Class_GRUD_SQL_Operations(getApplicationContext());


        try {

            Log.d(this.getClass().getName(), "МетодЗаполненияТабеляИзЗаранееСозданогоШабона ");


// TODO: 14.03.2021  тут нужно только одна строчка как пример данных для заполнения ДАННЫХ ИЗ КУРСОРА КОТОРЫЙ ТАБЕЛЬ УЖЕ ЕСТЬ


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "tabel");
            ///////
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");
            //
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "status_send!=?  AND cfo=? ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", "Удаленная");
            ///
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ЦифровоеИмяНовгоТабеля);
            ///
          /*          class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......

            ////TODO другие поля*/

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
            ////
            class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
            ////

            Курсор_НаОсновеПолученогоЭлектроногоНазваниеОрганицииПонемуПолучаемВсеИнформацииОТабеле = null;
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_НаОсновеПолученогоЭлектроногоНазваниеОрганицииПонемуПолучаемВсеИнформацииОТабеле = (SQLiteCursor) class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполненияТабеляИзЗаранееСозданогоШабона.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


            ////////

            Log.d(this.getClass().getName(), "GetData " + Курсор_НаОсновеПолученогоЭлектроногоНазваниеОрганицииПонемуПолучаемВсеИнформацииОТабеле);


// TODO: 12.03.2021  мы получили даные на основании ЦИФРОВОГО ИИЕНИ ДАЛЕЕ БУДЕМ ЗАПОЛНЯТЬ ЕГО ДАННЫМИ

            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//
        return Курсор_НаОсновеПолученогоЭлектроногоНазваниеОрганицииПонемуПолучаемВсеИнформацииОТабеле;
    }


    //////TODO вычисляем максимальную дату для LISTVIEW

    SQLiteCursor МетодКоторыйПоказываетМаксимальнуюДатуИзменения(int полученнаяUUIDНазванияОрганизации)
            throws ExecutionException, InterruptedException {
        SQLiteCursor Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения = new Class_GRUD_SQL_Operations(getApplicationContext());
        try {
            class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "Templates");
            class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "date_update , name_templates");
            class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "   date_update = (SELECT MAX(date_update) FROM Templates)  ");
            class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки", "name_templates");
            class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
            Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата = (SQLiteCursor) class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsторыйПоказываетМаксимальнуюДатуИзменения.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData " + Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//

        return Курсор_КоторыйЗагружаетГотовыеШаблоныМаксимальнаяДата;
    }

//////


    //////TODO вычисляем максимальную дату для СПИНЕРА ДЛЯ ВАДАПТЕРА AAARYADAPTER

    Cursor МетодКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера()
            throws ExecutionException, InterruptedException {
        SQLiteCursor Курсор_КоторыйЗагружаетГотовыеШАблоныМаксимальнаяДатаДляСпинера = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера = new Class_GRUD_SQL_Operations(getApplicationContext());
        try {
            class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "Templates");
            class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "name_templates");
            class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " date_update = (SELECT MAX(date_update) FROM Templates) ");
            class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
            class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
            Курсор_КоторыйЗагружаетГотовыеШАблоныМаксимальнаяДатаДляСпинера = (SQLiteCursor) class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData " + Курсор_КоторыйЗагружаетГотовыеШАблоныМаксимальнаяДатаДляСпинера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
///////

        return Курсор_КоторыйЗагружаетГотовыеШАблоныМаксимальнаяДатаДляСпинера;
    }


    private void МетодКогдаДанныхСамихТабелйНет() {
        try {
            ШАблонвВидеКнопок = new Button(this);////СОЗДАЕМ НОВЫЕ КНОПКИ НА АКТИВТИ

            ШАблонвВидеКнопок.setTag(" * В данном месяце нет табеля * ");
            ШАблонвВидеКнопок.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            ШАблонвВидеКнопок.setMinLines(10);

            ШАблонвВидеКнопок.setTextSize(14);

            ШАблонвВидеКнопок.setText("* нет Шаблона  " + "(создайте) *");

            ШАблонвВидеКнопок.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

            ШАблонвВидеКнопок.setTextColor(Color.RED);

            ШАблонвВидеКнопок.setHintTextColor(Color.RED);
            /////

            Drawable icon = getResources().getDrawable(R.mipmap.icon_dsu1_tabels_sam_tamples);
            icon.setBounds(0, 1, 60, 60);
            //  ШАблонвВидеКнопок.setPadding (0,0, 0, 150);
            ШАблонвВидеКнопок.setCompoundDrawables(icon, null, null, null);

            ////
            ШАблонвВидеКнопок.setBackground(this.getResources().getDrawable(R.drawable.textlines_tabel_row));
            //////ДОБАЯЛЕМ СТРОЧКУ
////////унопки распологаем внутири скролбар
            ((Activity) КонтекстШаблоны).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ////////унопки распологаем внутири скролбара
                    LinearLayoutСозданныхТабелей.addView(ШАблонвВидеКнопок); /////СОЗДАЕМ НАКШИ КНОПКИ ВНУРИ СКРОЛБАРА
                }
            });

            ///////
            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ

    private void МетодСозданиеДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        try {
            DatePickerDialog ДатаДляКалендаря = new DatePickerDialog(this, this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            // ДатаДляКалендаря.setTitle("Календарь");

            ДатаДляКалендаря.show();
            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    //////////
//TODO метод который и создает календарь после нажатие на кнопку ОК

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        try {
            String МесяцИзКолендаря = String.valueOf(month + 1);////ТЕКУЩИЙ МЕСЯЦ ИЗ КАЛЕНДАРЯ
            if (МесяцИзКолендаря.length() == 2) {
                ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
                Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
            } else {
                ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + "0" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
                Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
            }

            Date ПрасингДаты = new Date();
            try {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    ПрасингДаты = new SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);

                } else {

                    ПрасингДаты = new java.text.SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);


                }

                Log.e(this.getClass().getName(), " ПрасингДаты " + ПрасингДаты.toString());


            } catch (ParseException e) {
                e.printStackTrace();
            }
            ///////получаем значение месца на руском через метод дата
            ПолученноеЗначениеИзТолькоСпинераДата = МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(ПрасингДаты);
            Log.e(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата);
            /////вАЖНО ЗАПИСЫВАЕМ ОБРАТНО В СПИНЕР НА РАБОЧИЙ СТОЛ АКТИВТИ НАПРИМЕР НОВЫЙ МЕСЯЦ  ОКТЯБРЬ 2020 ГОДА НАПРИМЕР
            Log.e(this.getClass().getName(), "  ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);


/////////////ТУТ КРУТИМ ВЕСЬ КУРСОР  И ПЫТАЕМСЯ НАЙТИ ЗНАЧЕНИЕ ВНЕМ  И ПО РЕЗУЛЬТАТ ЗАПОЛЯЕМ ЕГО В STRINGBUGGER
            ////TODO ТУТ МЫ КРУТИМ ВЕСЬ СПИНЕР В КОТРЫЙ ИЗ БАЗЫ ЗАГРУЗИЛОСЬ ВСЕ СОЗДАННЫЕ МЕСЯЦА ИМЫ ПРОВЕРЕМ ЕЛСИ ТАКОМ МЕСЯЦ ЕЩН ИЛИ НЕТ
            StringBuffer ИщемУжеСозданныйМЕсяц = new StringBuffer();
            for (int ИндексСуществуюЩимМесяц = 0; ИндексСуществуюЩимМесяц < СпинерВыборДату.getCount(); ИндексСуществуюЩимМесяц++) {
                ////todo ДА ПРОСТО ЗАПОЛЯНЕМ БУФЕР УЖЕ СОЗДАННЫМИ МЕСЯЦАМИ В СПИНЕРЕ
                ИщемУжеСозданныйМЕсяц.append(СпинерВыборДату.getItemAtPosition(ИндексСуществуюЩимМесяц).toString()).append("\n");
                Log.d(this.getClass().getName(), " ИщемУжеСозданныйМЕсяц " + ИщемУжеСозданныйМЕсяц.toString() + "\n");

            }


            ///// todo ТУТ ВСТАВЛЯЕМ ММЕСЯЦА УКТОРГНО НЕТ ЕШЕ
            try {
                МетодВставкиНовогоМесяцавТабельКоторогоНет(ИщемУжеСозданныйМЕсяц);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ////TODO СОЗДАНИЯ КАЛЕНДАРЯ С ПОЛУЧЕННЫМИ УЖЕ ДАННЫМИ
    private void МетодВставкиНовогоМесяцавТабельКоторогоНет(StringBuffer ищемУжеСозданныйМЕсяц) throws ParseException {
        try {
            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата);
            StringBuffer МЕсяцСЗакглавнойБуквы = new StringBuffer(ПолученноеЗначениеИзТолькоСпинераДата.toLowerCase());
            ПолученноеЗначениеИзТолькоСпинераДата = МЕсяцСЗакглавнойБуквы.substring(0, 1).toUpperCase() + МЕсяцСЗакглавнойБуквы.substring(1).toLowerCase();
            Log.d(this.getClass().getName(), " МЕсяцСЗакглавнойБуквы " + ПолученноеЗначениеИзТолькоСпинераДата);
            ContentValues АдаптерВставкаНовогоМЕсяцаИзКалендаря = new ContentValues();////контрейнер для нового табеля
            int ДляВставкиНовогоМесяцаНазвание = МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(ФинальнаяМЕсяцДляНовогоТабеля);
            int ДляВставкиНовогоГодНазвание = МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(ПолученныйГодДляНовогоТабеля);
            Intent Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория = new Intent();
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.setClass(getApplication(), MainActivity_New_Tabely.class);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ПолученноеЗначениеИзСпинераДата", ПолученноеЗначениеИзТолькоСпинераДата);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ПолученныйГодДляНовогоТабеля", String.valueOf(ДляВставкиНовогоГодНазвание));
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ФинальнаяМЕсяцДляНовогоТабеля", String.valueOf(ДляВставкиНовогоМесяцаНазвание));
            АдаптерВставкаНовогоМЕсяцаИзКалендаря.clear();//
            ПолученныйГодДляНовогоТабеля = "";
            ФинальнаяМЕсяцДляНовогоТабеля = "";
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtras(data);
            startActivity(Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    //TODO метод получени месяа для записи в одну колонку

    private int МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        int month = 0;
        try {
            System.out.println(" " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " + ДатаКоторуюНадоПеревестиИзТекставЦифру);
            SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
            Date date = formatмесяц.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
            Calendar calendar = Calendar.getInstance(new Locale("ru"));
            calendar.setTime(date);
            Calendar calendar2 = new GregorianCalendar();
            calendar.setTime(date);
            month = calendar.get(Calendar.MONTH) + 1;


            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int МетодПолучениниеГОдДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        int year = 0;
        try {
            System.out.println("ДатаКоторуюНадоПеревестиИзТекставЦифру " + ДатаКоторуюНадоПеревестиИзТекставЦифру);
            SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
            Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
            Calendar calendar = Calendar.getInstance(new Locale("ru"));
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);

            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return year;
    }

////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГОД НОВЫЙ


    private int МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println(" " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " + ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL");
        Date date = formatмесяц.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    private int МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        int year = 0;
        try {
            System.out.println("ДатаКоторуюНадоПеревестиИзТекставЦифру " + ДатаКоторуюНадоПеревестиИзТекставЦифру);
            SimpleDateFormat formatгод = new SimpleDateFormat("yyyy");
            Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
            Calendar calendar = Calendar.getInstance(new Locale("ru"));
            calendar.setTime(date);
            Calendar calendar2 = new GregorianCalendar();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);

            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return year;
    }


    //TODO метод получени года для записи в одну колонку

    private String МетодПолучениниеГодаДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        String Год = null;
        try {
            int ИщемЕслиПробелВДате = ДатаКоторуюНадоПеревестиИзТекставЦифру.indexOf(" ");
            StringBuffer ИщемГод = new StringBuffer(ДатаКоторуюНадоПеревестиИзТекставЦифру);

            Год = ИщемГод.substring(ИщемЕслиПробелВДате, ИщемГод.length());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy", new Locale("ru"));
            Date date = formatter.parse(Год);
            System.out.println(date);
            System.out.println(formatter.format(date));
            ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Год;
    }


    //TODO метод получени года для записи в одну колонку


    //////TODO МЕТОД ПЕРВЫЙ ПРИ ПРОСМОТРЕ КАКЕИ ТАБЕЛЯ ВООБЩЕ ЕСТЬ
    private void МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля() throws InterruptedException, ExecutionException, TimeoutException, ParseException {
        ///////
        Курсор_ЗагружаетАрайдистЗначенийНовогоШАБЛОНА = null;
        try {
            //TODO вытаскиваем текущее название организации для ДАННОГО КОНТРЕТНОГО ПОЛЬЗОВАТЕЛЯ


            int ПолученнаяUUIDОрганизациидДляКурсораСпинераДаты = 0;

            // ID


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ = new Class_GRUD_SQL_Operations(getApplicationContext());
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());


            ///////
            SQLiteCursor Курсор_ПолучаемПубличныйID = (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if (Курсор_ПолучаемПубличныйID.getCount() > 0) {
////
                Курсор_ПолучаемПубличныйID.moveToFirst();

                /////
                ПубличноеIDПолученныйИзСервлетаДляUUID = Курсор_ПолучаемПубличныйID.getInt(0);
///


                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }


            ///
            Log.d(this.getClass().getName(), " ПолученнаяUUIDОрганизациидДляКурсораСпинераДаты " + ПолученнаяUUIDОрганизациидДляКурсораСпинераДаты);

// TODO вытасиваем даные из базы чтобы ЗАполнить спирер готовыми табелями Датами НАПРИМЕР ОКТЯРЬ 2020  ДЕКАБРЬ 2019


//////////TODO
            Курсор_ЗагружаетАрайдистЗначенийНовогоШАБЛОНА = new Class_MODEL_synchronized(getApplicationContext()).
                    МетодЗагружаетЗначенияШаблонов(ПолученнаяUUIDОрганизациидДляКурсораСпинераДаты, getApplicationContext());

//

///////////////todo закрываем курсор для заполения данными АКайлиста
            //  Курсор_ЗагружаетАрайдистЗначенийНовогоШАБЛОНА[0].close();

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void МетодВозвратаПравильногоДатыВСпиноре() {
        try {
            //TODO для првавильного востсзвтра делаем дополнительнй параметр ДВА ВИДА ЗАГРУЗЩКИ ПЕРВОНОЧАЛЬНЫЙ И ВОЗВРАТ ИЗ BACK
            if (ПравильныйВозвратИзДруговоАктивтиBACK != null) {
                Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерДата.size() " + МассивДляВыбораВСпинерДата.size());


                Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерДата " + МассивДляВыбораВСпинерДата + "\n");
                int ИщемСвойМесяцПорядок = МассивДляВыбораВСпинерДата.indexOf(ПравильныйВозвратИзДруговоАктивтиBACK);
                /////TODo данной командой ставим месяц котроый наш всегда в начало Арайлиста
                Collections.swap(МассивДляВыбораВСпинерДата, 0, ИщемСвойМесяцПорядок);
                Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерДата " + МассивДляВыбораВСпинерДата + "\n");

                ///TODO возврат из другой активти
   /*         int РазмерАрайЛиста=МассивДляВыбораВСпинерДата.size();
            МассивДляВыбораВСпинерДата.ensureCapacity(РазмерАрайЛиста);
            for ( int  i = 0; i < РазмерАрайЛиста; i++) {
                Log.d(this.getClass().getName()," МассивДляВыбораВСпинерДата.get(i) "+МассивДляВыбораВСпинерДата.get(i));
                String ИщемСвойМесяцНулевой=МассивДляВыбораВСпинерДата.get(0);
                String ИщемСвойМесяц=МассивДляВыбораВСпинерДата.get(i);
                if (ИщемСвойМесяц.equalsIgnoreCase(ПравильныйВозвратИзДруговоАктивтиBACK)) {
                    //int ИщемСвойМесяцПорядок=МассивДляВыбораВСпинерДата.indexOf(ИщемСвойМесяц);
                    МассивДляВыбораВСпинерДата.set(0,МассивДляВыбораВСпинерДата.get(i));
                    МассивДляВыбораВСпинерДата.set(i,ИщемСвойМесяцНулевой);
                    //МассивДляВыбораВСпинерДата.remove(i);
                    break;
                }
            }*/
                Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерДата.toString() " + МассивДляВыбораВСпинерДата.toString());

                //TODO нормальные режим загрузки ДАты спипернар
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    //TODO метод получени месяа для записи в одну колонку


    ////СООБЩЕНИЕ ИЗ ИТОРИИИ ТАБЛЕЙ ОТПРАВЛЯЕМ СООБЩЕНЕИ И ЗНАЧЕНИЕ В ДУГОЕ АКТИВТИ О СОЗДАНИЮ НОВОГО ТАБЕЛЯ


    ////ВТОРАЯ ФУНКЦИЯ  ДАТЫ НА РУСКИЙ ЯЗЫК МЕСЯЦ
    //функция получающая время операции

    public String МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(Date ПрасингДаты) {
        SimpleDateFormat sdfmt = null;
        SimpleDateFormat sdfmtГод = null;
        String ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        String ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
        sdfmt = new SimpleDateFormat("LLLL", new Locale("ru"));
        ФинальнаяМЕсяцДляНовогоТабеля = sdfmt.format(ПрасингДаты);
        Log.d(this.getClass().getName(), "  ФинальнаяМЕсяцДляНовогоТабеля  " + ФинальнаяМЕсяцДляНовогоТабеля);

        ///////МЕНЯЕМ АГЛИСКИЕ НА РУСКОЕ НАЗВАНИЕМЕСЯЦА
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев = sdfmt.format(ПрасингДаты);////ПЕРВОНОЧАЛЬНОЕ ВИД МЕСЯЦ НА АНГЛИСКОМ
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев = ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        Log.d(this.getClass().getName(), "  ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев  " + ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев);
        ///////Добалявем год
        sdfmtГод = new SimpleDateFormat("yyyy", new Locale("ru"));
        ПолученныйГодДляНовогоТабеля = sdfmtГод.format(ПрасингДаты);
        System.out.println("  операции время :  " + sdfmtГод.format(ПрасингДаты));
        ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт = ФинальнаяМЕсяцДляНовогоТабеля + "  " + ПолученныйГодДляНовогоТабеля;
        System.out.println("  ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт  " + ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт);
////////свич пробегаеться по названиям месяцев и перерделываем их с аглиского на русский
        return ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
    }


    //TODO метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--МЕСЯЦ

    private int МетодПолучениниеКурсораМЕсяцДата(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        String[] ДелимМЕсяцИгод = ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");
        System.out.println(" " + ДелимМЕсяцИгод[0]);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));
        Date date = formatмесяц.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру.trim());
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("MMMM").format(calendar.getTime()));
        return calendar.get(Calendar.MONTH) + 1;
    }

    //TODO метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--ГОД
    private int МетодПолучениниеКурсораГОДДата(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        String[] ДелимМЕсяцИгод = ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");
        System.out.println(" " + ДелимМЕсяцИгод[1]);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру.trim());
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("yyyy").format(calendar.getTime()));
        return calendar.get(Calendar.YEAR);
    }
    //TODO  конец метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--МЕСЯЦ


    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСпрашиваемПользователяЧтоОнТОчноХочетьСоздатьНовыйШаблон(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_tabels_for_new_tamples)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), " создание нового сотрудника ");
                    ///TODO создание нового ТАБЕЛЯ

                    Log.d(this.getClass().getName(), " Переход на  Шаблоны");


                    МетодСозданияНовогоШАблона();


                    ////////МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА


                }


            });

/////////кнопка
            final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
///запуск метода обновления через DIALOGBOX
                }
            });
            /////
            //TODO шаблоны


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void МетодСозданияНовогоШАблона() {
        Intent Интент_ШаблоныызватИлиСоздать = new Intent();
        try {
            Интент_ШаблоныызватИлиСоздать.setClass(getApplicationContext(), MainActivity_Find_Templates.class); // Т
            Интент_ШаблоныызватИлиСоздать.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
            Интент_ШаблоныызватИлиСоздать.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            Интент_ШаблоныызватИлиСоздать.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
            Интент_ШаблоныызватИлиСоздать.putExtra("ПолученнаяUUIDНазванияОрганизации", ПолученнаяUUIDНазванияОрганизации);
            Интент_ШаблоныызватИлиСоздать.putExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", ЗапускШаблоновFaceAppБлокировкаКнопкиДа);
            Интент_ШаблоныызватИлиСоздать.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
            // TODO: 22.09.2021  clear operasion
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
            if (РодительскийUUDТаблицыТабель > 0) {
                Интент_ШаблоныызватИлиСоздать.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
            }
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            Интент_ШаблоныызватИлиСоздать.putExtras(data);
            startActivity(Интент_ШаблоныызватИлиСоздать);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ////todo метод полчение огранизации при запуске программы
    protected int МетодПолучениеОрганизацииНепосрдственодляДанногоСОтрудника()
            throws InterruptedException, ExecutionException, TimeoutException {

        /////
        int названиеорганизациидлясотркдника = 0;
        ///
        SQLiteCursor Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника;
        try {
////TODO КУРСОР ПРОВЕЯЕТ ПЕРВЫЙ ЭТО ЗАПУСК ИЛИ НЕТ


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            ///
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника = new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
            ///////
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "id");
            //
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "id IS NOT NULL");
            ///"_id > ?   AND _id< ?"
          /*          //////
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
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
            ////
            class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего = null;
            ////

            Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего = (SQLiteCursor) new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПолучениеОрганизацииНепосрдственодляДанногоСОтрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " + Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего);



/*

            // TODO: 07.09.2021  _old

                        Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего =
                                new Class_MODEL_synchronized(getApplicationContext()).КурсорУниверсальныйДляБазыДанных("SuccessLogin",
                                        new String[]{"id"}, "id IS NOT NULL", null,
                                        null, null, "date_update DESC", "1");//"settings_tabels", "date_update","id=","1",null,null,null,null


*/


            // TODO: 07.09.2021  полченный результат

            if (Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего.getCount() > 0) { //TODO ЕСЛИ ДАННЫЙ UUID НЕ ПУСТОЙ ЭТО ЗНАЧИТ ЧТО ЭТОТ ТАБЕЛЬ УЖЕ СУЩЕТСВЕТ И НАМ НАДО ОБНОВИТЬ

                ////TODO ТАБЕЛЬ УЖЕ ЕСТЬ И МЫ ЕГО ОБНОЫЛЕНИЯ ПубличноеИмяНовогоТабеля
                Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего.moveToFirst();


                //////todo полученео навзаение
                названиеорганизациидлясотркдника = Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего.getInt(0);
                //

                Log.d(this.getClass().getName(), " названиеорганизациидлясотркдника " +
                        названиеорганизациидлясотркдника);

            }


            ///todo вырубаем курсор
            Курсор_КоторыйВЫгружемНазваниеОрганизацииДляЭтогоСотркдникаТекущего.close();
            //////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return названиеорганизациидлясотркдника;
    }


    // TODO: 14.03.2021 метод который генерирует датц для встаки


    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеКотороеСпрашиваетНужноЛиВставлятьСотрудниковИзРанееСозданогоШаблона(String ШабкаДиалога,
                                                                                                final String СообщениеДиалога,
                                                                                                boolean статус,
                                                                                                SQLiteCursor Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней,
                                                                                                SQLiteCursor Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм,
                                                                                                String ПередаемСозданнуюНазваниеТабеля,
                                                                                                Object ПередаваемыйИзКнопкиПолучаемUUIDТабеля
            , int lines) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setNeutralButton("Ещё", null)
                    .setIcon(R.drawable.icon_dsu1_tabels_for_new_tamples)
                    .show();


            Log.d(this.getClass().getName(),
                    " ЗапускШаблоновFaceAppБлокировкаКнопкиДа " + ЗапускШаблоновFaceAppБлокировкаКнопкиДа
                            + " lines " +lines);
            ///TODO создание нового ТАБЕЛЯ




            if (ЗапускШаблоновFaceAppБлокировкаКнопкиДа == true   || lines==0) {
                // Initially disable the button
                ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setVisibility(View.GONE);
                //  ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_NEUTRAL).setVisibility(View.GONE);
            }
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();


                    Log.d(this.getClass().getName(), " создание нового сотрудникаКурсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм " + Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount());
                    ///TODO создание нового ТАБЕЛЯ

                    Log.d(this.getClass().getName(), " Переход на  Шаблоны");

                    try {


                        // TODO: 17.03.2021 запусить добадение сотрудниковвиз шабона


                        МетодСамойЗаписиСотрудниковИзРанееСозданногШаблона(Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней,
                                Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм);

                        ///


                        Log.d(this.getClass().getName(), " Переход на  Шаблоны"
                                + " Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней "
                                + Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней +
                                "  Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм " + Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм);


                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }
            });

/////////кнопка
            final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
///запуск метода обновления через DIALOGBOX
                }
            });
            /////
            //TODO шаблоны


            /////////кнопка
            final Button MessageBoxUpdateЕщеДобавитьКШАблонуСорудников = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
            MessageBoxUpdateЕщеДобавитьКШАблонуСорудников.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), " создание нового сотрудника ");
                    ///TODO создание нового ТАБЕЛЯ
                    Log.d(this.getClass().getName(), " Переход на  Шаблоны");
                    try {
                        Log.d(this.getClass().getName(), " Переход на  Шаблоны");
                        Intent Интент_ШаблоныызватИлиСоздать = new Intent();
                        Интент_ШаблоныызватИлиСоздать.setClass(getApplicationContext(), MainActivity_Find_Templates.class); // Т
                        Интент_ШаблоныызватИлиСоздать.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
                        Интент_ШаблоныызватИлиСоздать.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                        Интент_ШаблоныызватИлиСоздать.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
                        Интент_ШаблоныызватИлиСоздать.putExtra("ПолученнаяUUIDНазванияОрганизации", ПолученнаяUUIDНазванияОрганизации);
                        Интент_ШаблоныызватИлиСоздать.putExtra("ПередаемСозданнуюНазваниеТабеля", ПередаемСозданнуюНазваниеТабеля);
                        Интент_ШаблоныызватИлиСоздать.putExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля", Long.parseLong(ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString()));
                        Интент_ШаблоныызватИлиСоздать.putExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", ЗапускШаблоновFaceAppБлокировкаКнопкиДа);
                        Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
                        Интент_ШаблоныызватИлиСоздать.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
                        Log.d(this.getClass().getName(), "ЦифровоеИмяНовгоТабеля" + ЦифровоеИмяНовгоТабеля + " ПередаемСозданнуюНазваниеТабеля " + ПередаемСозданнуюНазваниеТабеля +
                                "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
                        Интент_ШаблоныызватИлиСоздать.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle data=new Bundle();
                        data.putBinder("binder", binder);
                        Интент_ШаблоныызватИлиСоздать.putExtras(data);
                        startActivity(Интент_ШаблоныызватИлиСоздать);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    void МетодСамойЗаписиСотрудниковИзРанееСозданногШаблона(@NonNull SQLiteCursor Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней,
                                                            @NonNull  SQLiteCursor Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм) throws InterruptedException {

     
        try {
            // TODO: 29.06.2022 Данные Пришли ДЛля Вставки Данных В шаблон Из ШАБЛОНА ГТовго 
            Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.moveToFirst();
            Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.moveToFirst();
            Integer Count = Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getCount();

            // TODO: 29.06.2022  запускаем ПрогрессБарСОТпображениеОперациий Вставки данных В ТАбель Из шаблона  
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(" Из Шаблона");
            progressDialog.setMessage("Добавление...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(Count);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setProgress(0);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


// TODO: 29.06.2022 блокировки
            ReentrantLock locker = new ReentrantLock(); // создаем блокировку
            Condition condition = locker.newCondition(); // получаем условие, связанное с блокировкойм

// TODO: 29.06.2022  Запускам Добалвение ШАблона в Табель

            Observable observableВставкаИзШаблонаВТабкель=    Observable.fromAction(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.d(this.getClass().getName(), "   observableВставкаИзШаблонаВТабкель  " );
                                int ТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел = 0;
                                Long finalРезультатВставкиСотрудниковИзШаблона1 = 0l;
                            // TODO: 29.06.2022

                                try {
                                    do {
                                        locker.lock();

                                        try {
                                            ContentValues АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель = new ContentValues();
                                            ///todo из заполянем адапрет из курсора
                                            АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель =
                                                    МетодЗаполенияДаннымиДляЗаполенияТабеляИзГотовогоШаблона(Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней,
                                                            Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм);
                                            Log.d(this.getClass().getName(), "АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель[0]" + АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.valueSet().toString());

                                            String ТаблицаОбработкиДорбалвенИзШаблона = "data_tabels";
                                            finalРезультатВставкиСотрудниковИзШаблона1 = 0l;
                                            // TODO: 03.10.2021  сама вставка
                                            if (АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.size() > 0) {
                                                ///////////////////////////////
                                                finalРезультатВставкиСотрудниковИзШаблона1 = new Class_MODEL_synchronized(getApplicationContext()).
                                                        ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(ТаблицаОбработкиДорбалвенИзШаблона,
                                                                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель, ТаблицаОбработкиДорбалвенИзШаблона, "");//////TODO когда true -это значет применяеться только не вобмене  и говорит что плюс записываем изменению версии джанных
                                            }else{
                                                //todo
                                                Toast.makeText(getApplicationContext(), "  Нет данных для заполнения из шаблона !!! " , Toast.LENGTH_SHORT).show();
                                            }
                                            Log.d(this.getClass().getName(), "   finalРезультатВставкиСотрудниковИзШаблона1[0]  " + finalРезультатВставкиСотрудниковИзШаблона1);
// TODO: 24.05.2021 вставка если пользователь разреил атоматическую вставку выходных дней
                                            // TODO: 24.05.2021  месяц
                                            if (finalРезультатВставкиСотрудниковИзШаблона1 > 0) {
                                                int ИндексМесяц = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getColumnIndex("month_tabels");
                                                int Месяц = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getInt(ИндексМесяц);
                                                // TODO: 24.05.2021  год
                                                int ИндексГод = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getColumnIndex("year_tabels");
                                                int Год = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getInt(ИндексГод);
                                                Integer РезультатВставкаВыходныхДНей =
                                                        new Class_Generation_Weekend_For_Tabels(getApplicationContext())
                                                                .МетодТретийАвтоматическаяВставкаВыходныхДней(МетодГенерацииUUIDУжеСуществующегоСотрудника, Год, Месяц);
                                                Log.d(this.getClass().getName(), "   РезультатВставкаВыходныхДНей  " + РезультатВставкаВыходныхДНей);
                                                // TODO: 28.01.2022 ПОВЫШАЕМ ВЕРСИЮ  В ТАБЛИЦЕ МОДИФИКАЦИИ КЛИЕНТ
                                            }

                                            if (finalРезультатВставкиСотрудниковИзШаблона1 > 0) {
                                                Log.w(this.getClass().getName(), " finalРезультатВставкиСотрудниковИзШаблона1 " + finalРезультатВставкиСотрудниковИзШаблона1);
                                                ТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел++;
                                                int finalТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел = ТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел;
                                                // TODO: 29.06.2022
                                                condition.await(200,TimeUnit.MILLISECONDS);
                                                condition.signal();

                                                ((Activity) КонтекстШаблоны).runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progressDialog.setIndeterminate(false);
                                                        progressDialog.setProgress((int) finalТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел);
                                                        progressDialog.setMessage("Добавление сотрудника/ов..." + finalТекущаяОперацияВставкиИзШаблонаСотрудниковВТабел + " (" + Count + ")");
                                                    }
                                                });

                                            } else  {
                                                ((Activity) КонтекстШаблоны).runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progressDialog.setIndeterminate(false);
                                                        progressDialog.setMessage(" Данный сотрудник уже в  табеле !!!");
                                                    }
                                                });
                                            }
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                                        }finally {
                                            locker.unlock();
                                        }
                                        ////////
                                        ///todo КРУИТЬ ЗАПИСИ СКОЛЬКО В ШАБЛОНЕ ДЛ ЭТГО БЫЛО УЖЕ ЗАПОНЕНО СКОЛЬКО И БУДЕТ  ПОВТОРЕНИЙ
                                    } while (Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.moveToNext());
                                    // TODO: 29.06.2022 close cursor
                                    Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.close();
                                    Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ///метод запись ошибок в таблицу
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                        }
                    }).subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "ERROR    observableВставкаИзШаблонаВТабкель  throwable  "+throwable.getMessage().toString() );
                            return false;
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.d(this.getClass().getName(), "   observableВставкаИзШаблонаВТабкель  " );
                            progressDialog.setIndeterminate(true);
                            progressDialog.dismiss();
                            progressDialog.cancel();
                            ///////todo ПОСЛЕ САТВКИ УСПЕШНОЙ ЕПРЕХОДИМ НА ДРУГУЮ АКТИВТИ
                            МетодПереходаПослеУспешногоДобавленияСотрудниклвИзШаблонаВТабель();
                            // TODO: 26.03.2021 ДОПОЛНИТЕЛЬНО ОБНУЛЯЕМ ВСЕ ТАБЕЛЯ С NULL В ФИО ЧТО БЫ ОБМЕН НЕ РУГАЛЬСЯ

                            Log.w(this.getClass().getName(), " Не был вставлен СОТРУДНИК ИЗ ШАБЛОНА КАК ТАК ОН УЖЕ ЕСТЬ В ЭТОМ ТАБЕЛЕ o  ");
                        }
                    });

            observableВставкаИзШаблонаВТабкель.subscribe(new Consumer() {
                @Override
                public void accept(Object o) throws Throwable {
                    Log.d(this.getClass().getName(), "   observableВставкаИзШаблонаВТабкель  "+o.toString() );

                }
            });
            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 29.06.2022 ПЕРЕНОСТИМ СЮДА МЕТОД ЗАПОЕНИЯ кОНТЕЙНЕРА С ДАННЫМИ ИХЗ aSYTASK



    // TODO: 26.09.2021  ЗАПОЛНЯЕМ КОНТЕЙНЕР ДЛЯ ВСТАВКИ ИЗ ГОТОВГО ШАБЛОНА  В ТАБЕЛЬ
    private ContentValues МетодЗаполенияДаннымиДляЗаполенияТабеляИзГотовогоШаблона( @NonNull SQLiteCursor Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней,
                                                                                      @NonNull  SQLiteCursor Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм) {
        ContentValues АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель = new ContentValues();////контрейнер для нового табеля
        try {
            Long ФИОИзАТблицы = 0l;
            Class_GRUD_SQL_Operations class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля = new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 26.09.2021   # 1
            Integer Индекс = Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getColumnIndex("fio_uuid");
            ФИОИзАТблицы = Курсор_СДаннымиИзШаблонаДАнныхСозданныйПользовательм.getLong(Индекс);
            int ИндексМесяц = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getColumnIndex("month_tabels");
            int Месяц = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getInt(ИндексМесяц);
            // TODO: 24.05.2021  год
            int ИндексГод = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getColumnIndex("year_tabels");
            int Год = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getInt(ИндексГод);
            // TODO: 24.05.2021  год
            int ИндексЦФО = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getColumnIndex("cfo");
            int ЦФО = Курсор_КотрыйПолученИзТаблицыТабельТолькоДляПолученияНаОсновеСФОляВставкиВыходныхДней.getInt(ИндексЦФО);
            // TODO: 25.10.2021  ВЫЧИСЛЯЕМ ЕСЛИ ТАКОЙ УЖЕ СОТРУДНИК В ТАБЛИЦЕ DATA_TBELS
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "viewtabel");
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "fio");
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",
                    " fio=?   AND month_tabels=? AND year_tabels=? AND cfo=?  AND status_send!=?  ");
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ФИОИзАТблицы);
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", Месяц);
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3", Год);
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4", ЦФО);
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска5", "Удаленная");
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
            class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            SQLiteCursor  Курсор_ИщемПроверяемЕслиТакойСотрудникУжеЕстьВТабеле = (SQLiteCursor) class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData " + Курсор_ИщемПроверяемЕслиТакойСотрудникУжеЕстьВТабеле);
            // TODO: 25.10.2021  производим ВСТАВКУ ЕСЛИ ТАЕОКГО СОТРУДНИКА ЕЩЕ НЕТ ЕСДИ КУРСОР НЕ ППУСТОЙ
            if (Курсор_ИщемПроверяемЕслиТакойСотрудникУжеЕстьВТабеле.getCount() == 0) {
                Log.d(this.getClass().getName(), "ФИОИзАТблицы" + ФИОИзАТблицы);
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("fio", ФИОИзАТблицы);
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("status_send", " ");
                String СгенерированованныйДатаДляВставки = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("date_update", СгенерированованныйДатаДляВставки);
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("uuid_tabel", РодительскийUUDТаблицыТабель);
                Log.d(this.getClass().getName(), "РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
                // TODO: 08.10.2021 повышаем версию
                Class_GRUD_SQL_Operations class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника = new Class_GRUD_SQL_Operations(getApplicationContext());
                // TODO: 28.01.2022  вычиляем повышения версии данных
                Long     РезультатУвеличинаяВерсияДАныхЧата = class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника.new ChangesVesionData(getApplicationContext()).
                        МетодПовышаемВерсииCurrentTable("data_tabels",getApplicationContext(), Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " current_table УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ ВНУТРИ ТАБЛИЦЫ  РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.putNull("_id");
                Log.d(this.getClass().getName(), "РодительскийUUDТаблицыТабель" + РодительскийUUDТаблицыТабель);
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля = new Class_GRUD_SQL_Operations(getApplicationContext());
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "id");
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " id IS NOT NULL ");
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                SQLiteCursor     Курсор_ИщемПУбличныйIDКогдаегоНетВстатике = (SQLiteCursor) class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsЗаполенияДаннымиПередВставкойНовогоТабеля.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "GetData " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике);
                int  ПолученныйID=0;
                // TODO: 07.09.2021  Реузльтат
                if (Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount() > 0) {
                    Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.moveToFirst();
                    ПолученныйID = Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getInt(0);
                    Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount()+
                            "  ПолученныйID " +ПолученныйID);
                }
                Log.d(this.getClass().getName(), " String.valueOf(ПолученныйID[0]) " + String.valueOf(ПолученныйID));
                Integer ПолучениПубличныйID = ПолученныйID;
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("user_update", ПолучениПубличныйID);
                МетодГенерацииUUIDУжеСуществующегоСотрудника = 0l;
                МетодГенерацииUUIDУжеСуществующегоСотрудника = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                Log.d(this.getClass().getName(), " МетодГенерацииUUIDУжеСуществующегоСотрудника " + МетодГенерацииUUIDУжеСуществующегоСотрудника);
                АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель.put("uuid", МетодГенерацииUUIDУжеСуществующегоСотрудника);
                Log.d(this.getClass().getName(), " current_table УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ ВНУТРИ ТАБЛИЦЫ  РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);
            }
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return АдаптерДляВставкиИзГотоваШаблонаВТаблицуТабель;
    }





















    @UiThread
    void СообщениеСооьщаетПользовательЧТоСоздалитьНовыеСотрудниккиУспешноИлиНет(String ШабкаДиалога, String СообщениеДиалога, boolean Статус) {

        Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника ");
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ

        int Значек;

        if (Статус == true) {
            Значек = R.drawable.icon_dsu1_tabel_info;

        } else {
            Значек = R.drawable.icon_dsu1_create_coums_rom_temples;
        }


//////сам вид
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(Значек)
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


                // TODO: 12.03.2021 отпрравляем данные в предыдущее активти
                ///////
                МетодПереходаПослеУспешногоДобавленияСотрудниклвИзШаблонаВТабель();


            }


        });
    }


    private void МетодПереходаПослеУспешногоДобавленияСотрудниклвИзШаблонаВТабель() {
        Intent ИнтентЗапускаемСуществующийТабель = new Intent();//getApplicationContext()
        try {
            if (ЦифровоеИмяНовгоТабеля > 0) {
                ИнтентЗапускаемСуществующийТабель = new Intent(MainActivity_New_Templates_Tabels.this, MainActivity_List_Peoples.class);//getApplicationContext()
            }else {
                ИнтентЗапускаемСуществующийТабель.setClass(getApplicationContext(), MainActivity_Face_App.class); // Т
            }
            ИнтентЗапускаемСуществующийТабель.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
            ИнтентЗапускаемСуществующийТабель.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            if (РодительскийUUDТаблицыТабель > 0) {
                ИнтентЗапускаемСуществующийТабель.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
            }
            if (РодительскийUUDТаблицыТабель > 0) {
                ИнтентЗапускаемСуществующийТабель.putExtra("UUIDТабеляПослеПодбора", РодительскийUUDТаблицыТабель);
            }
            Log.d(this.getClass().getName(), " ЦифровоеИмяНовгоТабеля  " + ЦифровоеИмяНовгоТабеля +
                    " МесяцТабеляФинал " + МесяцТабеляФинал + " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " +
                    ПолноеИмяТабеляПослеСозданиеНовогоСотрудника + " РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
            ИнтентЗапускаемСуществующийТабель.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            ИнтентЗапускаемСуществующийТабель.putExtra("UUIDТабеляКнопкаBACKУниверсальный", РодительскийUUDТаблицыТабель);
            ИнтентЗапускаемСуществующийТабель.putExtra("UUIDТабеляПослеПодбора", РодительскийUUDТаблицыТабель);
            ИнтентЗапускаемСуществующийТабель.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
            Log.d(this.getClass().getName(), "UUIDТабеляКнопкаBACKУниверсальный " + РодительскийUUDТаблицыТабель);
            ИнтентЗапускаемСуществующийТабель.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            ИнтентЗапускаемСуществующийТабель.putExtras(data);
            startActivity(ИнтентЗапускаемСуществующийТабель);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}