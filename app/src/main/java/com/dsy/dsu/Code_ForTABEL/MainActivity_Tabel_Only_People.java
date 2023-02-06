package com.dsy.dsu.Code_ForTABEL;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.json.JSONException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

import javax.crypto.NoSuchPaddingException;


public class MainActivity_Tabel_Only_People extends AppCompatActivity  {
    private Spinner СпинерТАбельМЕсяцФинал;/////спинеры для создание табеля
    private Spinner СпинерТАбельДепартаментФинал;/////спинеры для создание табеля
    private ScrollView ScrollСамогоТабеля;
    private  boolean РежимыПросмотраДанныхЭкрана;
    private LinearLayout ГлавныйКонтейнерТабель; ////главный linelayuout
    private  Activity activity;
    private ConstraintLayout ГлавныйВерхнийКонтейнер;
    private ProgressDialog progressDialogДляУдаления;
    private    String ФИОДляТабеляНаАктивти;
    private  int ОбщееКоличествоСОтрудниковДляСкролаПотабелю=0;
    private ImageButton imageButtonДвижениеПоСотрудникамВТАбеле,imageButtonНазадПоСотрудникамВТАбеле;
    private  String   ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;
    private  String  МесяцТабеляФинал;
    private  Configuration config;
    private  ArrayList<String> МассивДляВыбораСпинераДаты= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  ArrayList<String> МассивДляВыбораВСпинерДепартамент= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  String КонтентСпинераНаАктивтиТабель= "";
    private  String ИмяТабеляФинал= "";
    private  String  ДепартаментТабеляФинал= "";
    private  String UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя;
    private   TextView НазваниеДанныхВТабелеДниНедели;
    private   TextView НазваниеДанныхВТабелеСНИЛС;
    private TextView НазваниеДанныхВТабелеФИО;
    private  EditText СамиДанныеТабеля;
    private int ПосикДня;
    private String НазваниеСтолбикаДляЛобкальногоОбноления= "";
    private   String НазваниеТабеля= "";
    private  String НазваниеЗагруженногТАбеля= "";
    private  Boolean    СтаттусТабеля= false;
    private   String ДробимДляТабеляГод,ДробимДляТебеляМесяц;
    private  View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла;
    private Map< Integer,String> ХЭШНазваниеДнейНедели = Collections.synchronizedMap(new LinkedHashMap<>());
    private String СамиДанныеКурсораДляДней;
    private  int ТекущееПоложенияВТабелеДляСкрола;
    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  String МесяцДляЗагрузкиТабелей= "";
    private  String ГодДляЗагрузкиТабелей= "";
    private  String ПубличноеIDЗагрузкиТабелей= "";
    private   int МЕсяцДляКурсораТабелей;
    private   int ГодДляКурсораТабелей;
    private Button КнопкаНазад;
    private Button КнопкаЛеваяПередвиженияПоДанным;
    private Button КнопкаПраваяПередвиженияПоДанным;
    private  TextView ОбщееКоличествоЧасовСотрудникаВТабелеСотудников;
    private   int КоличествоДнейвЗагружаемойМесяце;
    private boolean МыУжеВКодеУденияСотрудника=false;
    private  String ЛимитСоСмещениемДанных= "";
    private int ИндексДвижениеТабеляСкролл=0;
    private int ИндексДвижениеТабеляКнопки=0;
    private  String ОбщееКоличествоЛюдейВТабелеТекущем;
    private   HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбика        =new HashMap<>();;
    private   String ПолучениеЗначениеДоИзменения;
    private   String СамоЗначениеЯчейкиТабеля;
    private   Context context;
    private    int IDЧьиДанныеДляСотрудников;
    private   String  ГодТабеляПослеПодбораУниверсальный;
    private int ЦифровоеИмяНовгоТабеля;
    private int МЕсяцДляКурсораТабелейДЛяПермещения;
    private    TextView СловоТабель;
    private HorizontalScrollView HorizontalScrollViewВТабелеОдинСотрудник;
    private   String НазваниеТабеляПослеУспешногоСозданиеСотрудника= "";
    private  String    UUIDТабеляПослеУспешногоСозданиеСотрудника= "";
    private    Long UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников=0l;
    private   String НазваниеТабеляПришелПослеСоздангоНового= "";
    private   String  ДепартаментПришелПослеСоздангоНового= "";
    private    String ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;
    private  String ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки;
    private    String UUIDТабеляКнопкаBACKУниверсальный= "";
    private   boolean  ПроизошелЛиСфайпПоДаннымСингТабеля;
    private Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private View view2Линия;
    private    String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//
    final private   String ИмяСлужбыОбщейСинхронизацииДляЗадачи = "WorkManager Synchronizasiy_Data";
    private  long РодительскийUUDТаблицыТабель=0l;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{

            super.onCreate(savedInstanceState);
            ////todo запрещает поворот экрана
            setContentView(R.layout.activity_main__tabel_four_colums);
            //     getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
            //////todo настрока экрана
        /*    getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            activity=this;
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new  PUBLIC_CONTENT(getApplicationContext());
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "Внешний вид ДЛЯ ВСЕХ ОСТАЛЬНЫХ "  +Build.BRAND.toString() );
            context =this;
            Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            ((Activity) context) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "  onCreate(Bundle savedInstanceState)   MainActivity_Tabel_Only_People  ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config = getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);
            ((Activity) context) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            СпинерТАбельМЕсяцФинал = (Spinner) findViewById(R.id.СпинерТабельМесяц);
            СпинерТАбельДепартаментФинал = (Spinner) findViewById(R.id.СпинерТабельДепратамент);
            ГлавныйКонтейнерТабель = (LinearLayout) findViewById(R.id.ГлавныйКонтейнерТабель);
            try {
                ГлавныйКонтейнерТабель.removeAllViewsInLayout();
            } catch (Exception e) {
              //  e.printStackTrace();
            }
            /////TODO Скоролл Вид
            ScrollСамогоТабеля=(ScrollView) findViewById(R.id.ScrollViewСамТабеля);
            ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА
            ГлавныйВерхнийКонтейнер=(ConstraintLayout) findViewById(R.id.ГлавныйВерхнийКонтейнер);
            КнопкаЛеваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеЛеваяСтрелка);
            КнопкаПраваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеТабельПраваяСтрелка);
            ОбщееКоличествоЧасовСотрудникаВТабелеСотудников=(TextView) findViewById(R.id.textViewJОбщееКоличествоСотрудниковВТабеле);
            imageButtonДвижениеПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonДвижениеПоСотрудникамВТАбеле);
            imageButtonНазадПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonНазадПоСотрудникамВТАбеле);
            ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
            ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.setPaintFlags( (ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG));
            СловоТабель=(TextView) findViewById(R.id.textView3СловоТабель);
            СловоТабель.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            ЛимитСоСмещениемДанных="0";
            КнопкаНазад=(Button) findViewById(R.id.imageViewСтрелкаВнутриТабеля);
            view2Линия=(View) findViewById(R.id.view2Линия);
            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
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

    @Override
    protected void onStop() {
        super.onStop();
        try{
////TODO после как прошла СИНХРОНИЗАЦИЯ В ФОНЕ  ПЕЕРРИСОВЫВАЕМ КОМПОНЕТЫ РОБОЧЕГО СТОЛА
        WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObservers(null);
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), " onStart() " );
        try{
            // TODO: 29.01.2022 ПРИ ИЗМЕНЕНИ МЕНЯЕМ ДАННЫЕ В БАЗЕ В ТАБЕЛЕ
            МетодПриИзмениеДанныхВБазеМенемВнешнийВидТабеляObserver();
//TODO #1
            МетодПришлиПараметрыОтДругихАктивитиДляРаботыТабеля();
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

    @Override
    protected void onResume() {
        super.onResume();
        try {

            //TODO #2
            МетодПриНАжатииНаКнопкуBACK();
            //TODO #4
            МетолСозданиеТабеляФинал(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);//запускаем метод создание табеля
            //TODO #5
            МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(МесяцТабеляФинал,ТекущееПоложенияВТабелеДляСкрола);   ////раньше стояло 0
            ///TODO   #6   запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
            МетодПослеУспешнойГенерацииТабеля();
            ///TODO   #7  запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
            МетодОбработкиСвайповНаЭкране();
            ///TODO   #8 запускаем метод отработки поднятие клавиатур
            МетодОтработкиПоднятияКлавиатуры();

            ScrollСамогоТабеля.setBackgroundResource(R.drawable.textlines_tabel_row_color_green_mini);
            ScrollСамогоТабеля.requestLayout();
            ScrollСамогоТабеля.pageScroll(View.FOCUS_BACKWARD);
            ГлавныйКонтейнерТабель.requestLayout();
            ГлавныйКонтейнерТабель.forceLayout();

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

// TODO: 24.05.2021  метд обоработки поднятия и апускания клавиатуры

    private void МетодОтработкиПоднятияКлавиатуры() {
        try{


            KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
                @Override
                public void onVisibilityChanged(boolean isOpen) {
                    ///
                    if (isOpen) {
                  /*  Toast.makeText(getApplicationContext(),
                            " Клавиатур поднялась !!!!!!! "    , Toast.LENGTH_SHORT).show();
*/



                        ///////

                        Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника "+ПолноеИмяТабеляПослеСозданиеНовогоСотрудника+"\n"+
                                " ФИОДляТабеляНаАктивти " +ФИОДляТабеляНаАктивти);





                        МетолСозданиеТабеляФинал(ФИОДляТабеляНаАктивти);



                    }else{
                    /*Toast.makeText(getApplicationContext(),
                            " Клавиатур опусталась  "    , Toast.LENGTH_SHORT).show();*/




                        ///////

                        Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника "+ПолноеИмяТабеляПослеСозданиеНовогоСотрудника+"\n"+
                                " ФИОДляТабеляНаАктивти " +ФИОДляТабеляНаАктивти);

                        МетолСозданиеТабеляФинал(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);


                    }

                }
            });




            /////////
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














    // TODO: 28.04.2021 обработка свайпов
    private void МетодОбработкиСвайповНаЭкране() {
        try{

// TODO: 30.04.2021 обрабаотывает TOCUCH   создание свайпов

///
            Log.d(this.getClass().getName(), "   setOnTouchListenera ");


     /*       ScrollСамогоТабеля.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    // horizontal scroll position
                    int scrollX1 = ScrollСамогоТабеля.getScrollX();

                    // vertical scroll position
                    int scrollY1 = ScrollСамогоТабеля.getScrollY();

                    int scrollY = ScrollСамогоТабеля.getHeight();
                    int scrollX = ScrollСамогоТабеля.getWidth();

                    int scrollEND = ScrollСамогоТабеля.getPaddingEnd();
                    int scrollStart= ScrollСамогоТабеля.getPaddingStart();
                    ////
                    int scrollLeft = ScrollСамогоТабеля.getPaddingLeft();
                    int scrollRight= ScrollСамогоТабеля.getPaddingRight();

                    int heightDiff = ScrollСамогоТабеля.getChildAt(0).getPaddingLeft() - ScrollСамогоТабеля.getChildAt(0).getLeft();
                    ///ScrollСамогоТабеля.smoothScrollTo(0, ScrollСамогоТабеля.getPaddingStart());


                    if (scrollRight > 0) {
                        Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                " больше 500");

                      //  МетодСвайпВпередПоДАнным();
                        //

                    }

                }
            });
*/














            // TODO: 19.07.2021 тач

            // TODO: 24.09.2021  ГЛАВНЫЙ ЭЛЕМЕНТ ГОРИЗОНАЛЬНОЙ ПРОКРУТРКИК ПО X

            HorizontalScrollViewВТабелеОдинСотрудник.setOnTouchListener(new OnTouchListener() {


                // TODO: 07.05.2021 Точу

                final float[] downy = {0l};
                //

                final float[] downx = {0l};


                Long  getDownTime=0l;
                Long  getEventTime=0l;

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    Log.d(this.getClass().getName(),"Свайп просто сфайп");

                    try{

                        ////
                        if (downx[0]==0l){
                            downx[0]=event.getX();

                        }


                        if (getDownTime==0) {
                            getDownTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                        }


                        float     upy,upx;

                        float deltaY,deltax;

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: // нажатие
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_DOWN");



                                break;
                            case MotionEvent.ACTION_MOVE: // движение
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_MOVE");
                                break;
                            case MotionEvent.ACTION_UP: // отпускание
                            case MotionEvent.ACTION_CANCEL:
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_UP ACTION_CANCEL ");


                                upy=event.getX();
                                //  deltaY=upy- downy[0];
                                //////
                                upx=event.getX();
                                deltax=upx-downx[0];

                                // TODO: 07.05.2021  формула
                                final int MIN_DISTANCEY=50;
                                ////
                                final int MIN_DISTANCEX=50;

                                getEventTime = TimeUnit.MILLISECONDS.toSeconds( System.currentTimeMillis());


                                float duration = getEventTime - getDownTime;

                                StringBuffer БуферФиналВремениПрошлло=      new StringBuffer(String.valueOf(duration));
                                /////
                                int ФиналСколькоПрошллоВремени=     Integer.parseInt(БуферФиналВремениПрошлло.substring(0,1));
                                // TODO: 19.05.2021 конец прокрутки

                                int КонецПрокрутки=      v.getRight();
                                int НачалоПрокрутки=      v.getLeft();

                                View lastChild = ScrollСамогоТабеля.getChildAt(ScrollСамогоТабеля.getChildCount() - 1);
                                int bottom = lastChild.getBottom() + ScrollСамогоТабеля.getPaddingBottom();
                                int sy = ScrollСамогоТабеля.getScrollX();
                                int sh = ScrollСамогоТабеля.getWidth();
                                int delta = bottom - (sy + sh);


                                // TODO: 08.05.2021  Y

                                if (Math.abs(deltax) > MIN_DISTANCEY ) {
                                    if ( deltax > 0 && delta>10) {
                                        /////

                                        int scrollX = HorizontalScrollViewВТабелеОдинСотрудник.getScrollX();


                                        HorizontalScrollViewВТабелеОдинСотрудник.scrollTo(scrollX,0);
                                        ///
                                        HorizontalScrollViewВТабелеОдинСотрудник.smoothScrollTo(scrollX,0);

                                        // TODO: 24.09.2021 двигаемся вперед




                                        МетодСвайпНазаПоДанным();

                                        ///
                                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпНазаПоДанным ");/////

                 /*       МетодСвайпВпередПоДАнным();



                        ///
                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпВпередПоДАнным ");/////*/

                                        //
                                        downy[0]=0;



                                    }else if  (  deltax<0 && delta>0) {
                                        ///TODO плюс время
                                        int scrollX = HorizontalScrollViewВТабелеОдинСотрудник.getScrollX();
                                        //
                                        HorizontalScrollViewВТабелеОдинСотрудник.scrollTo(scrollX,0);
                                        ///
                                        HorizontalScrollViewВТабелеОдинСотрудник.smoothScrollTo(scrollX,0);


                                        // TODO: 24.09.2021 двигаемся назад

                /*             МетодСвайпНазаПоДанным();

                        ///
                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпНазаПоДанным ");/////*/


                                        МетодСвайпВпередПоДАнным();



                                        ///
                                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпВпередПоДАнным ");/////

                                        ////
                                        downy[0]=0;
                                        //////////////
                                        // TODO: 07.05.2021  обработка горизонта X
                                    }
                                    /////

                                    // TODO: 09.05.2021  при успешном срабоатывании true
                                    return true;



                                }/*else {

                    // TODO: 07.05.2021  обработка горизонта X

                   if (Math.abs(deltax)>MIN_DISTANCEX){
                        if(deltax>0){
                            // TODO: 07.05.2021 вперед по дпнным
                            МетодСвайпВпередПоДАнным();
                            //
                            downx[0]=0;
                        }else if(deltax<0){
                            // TODO: 07.05.2021 движение назад
                            МетодСвайпНазаПоДанным();
                            //
                            downx[0]=0;
                        }
                        // TODO: 09.05.2021  при успешном срабоатывании true
                        return true;

                    }


                }*/

                                // TODO: 19.05.2021 обнуялем
                                getDownTime=0l;

                                break;
                        }





                        /////////
                    } catch (Exception e) {
                        //  Block of code to handle errors
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }


                    return false;
                }




            });































            // TODO: 29.04.2021 код обработки кнопок для перемещения по табелям
            Handler handler=new Handler();
            /////
            imageButtonДвижениеПоСотрудникамВТАбеле .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 28.04.2021 Запускаем Загрузку Следуюзегосотрудника в табель МИНУС


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ///////
                            imageButtonДвижениеПоСотрудникамВТАбеле.setBackgroundColor(Color.parseColor("#D87093"));

                        }
                    },50);
                    /////////////////////

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonДвижениеПоСотрудникамВТАбеле.setBackgroundColor(Color.parseColor("#F5FFFA"));

                            ///todo вырвиваем индексы
                            if (ТекущееПоложенияВТабелеДляСкрола == ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {
                                ////
                                ТекущееПоложенияВТабелеДляСкрола=0;
                                // ТекущееПоложенияВТабелеДляСкрола=  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getPosition();

                            }


                            if (ТекущееПоложенияВТабелеДляСкрола>0) {
                                //
                                ТекущееПоложенияВТабелеДляСкрола--;


                            }
                            if (ТекущееПоложенияВТабелеДляСкрола==1) {

                                /////
                                ТекущееПоложенияВТабелеДляСкрола--;


                            }


                            // TODO: 28.04.2021


                            //TODO #5
                            МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(МесяцТабеляФинал,ТекущееПоложенияВТабелеДляСкрола);

                            ///TODO запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ

                            МетодПослеУспешнойГенерацииТабеля();



                        }
                    },100);




                }///ScrollСамогоТабеля  ///ГлавныйКонтейнерТабель






            });

            // TODO: 28.04.2021

            // TODO: 28.04.2021 Запускаем Загрузку Следуюзегосотрудника  Увеличения в табель ПЛЮС

            imageButtonНазадПоСотрудникамВТАбеле .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////

                    Handler handler=new Handler();
                    // TODO: 28.04.2021 Запускаем Загрузку Следуюзегосотрудника в табель МИНУС

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //////
                            imageButtonНазадПоСотрудникамВТАбеле.setBackgroundColor(Color.parseColor("#D87093"));
                        }
                    },50);

/////////////////////////////

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonНазадПоСотрудникамВТАбеле.setBackgroundColor(Color.parseColor("#F5FFFA"));




                            if (ТекущееПоложенияВТабелеДляСкрола < ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {
                                ///////
                                ТекущееПоложенияВТабелеДляСкрола++;


                            }
                            ///////////////////////

                            if (ТекущееПоложенияВТабелеДляСкрола <= ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {

                                //TODO #5
                                МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(МесяцТабеляФинал, ТекущееПоложенияВТабелеДляСкрола);

                                ///TODO запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ

                                МетодПослеУспешнойГенерацииТабеля();


                            }

                            // TODO: 28.04.2021 ПЕРЕД ПЕРЕДВИЖЕНЕИМ ПРОВЕРЯЕМ ЕСЛИ ЗНАЧЕНИЯ РАВНЫ ТО ОБНУЛЯЕМ ИНДЕКС ПЕРЕМЕЖЕНИЯ












                        }
                        // TODO: 28.04.2021




                    },100);




                }

            });



            /////////
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













    // TODO: 29.01.2022
    // TODO: 22.12.2021
    private void МетодПриИзмениеДанныхВБазеМенемВнешнийВидТабеляObserver() {


        try{
            LifecycleOwner lifecycleOwner =this ;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                                                          @Override
                                                          public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                                                              source.getLifecycle().getCurrentState();
                                                              event.getTargetState().name();
                                                          }
                                                      });
                    // TODO: 29.01.2022
                    // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
                    WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая)
                            .observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                                @Override
                                public void onChanged(List<WorkInfo> workInfos) {
                                    workInfos.stream().filter(statee->statee.getState().compareTo(WorkInfo.State.SUCCEEDED)==0).forEachOrdered(new Consumer<WorkInfo>() {
                                        @Override
                                        public void accept(WorkInfo workInfo) {
                                            Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  workInfo " + workInfos.get(0).getState().name());
                                            Long CallBaskОтWorkManagerОдноразового = workInfos.get(0).getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                            Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " + CallBaskОтWorkManagerОдноразового);
                                            onResume();

                                        }
                                    });}
                            });


            // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыОбщейСинхронизацииДляЗадачи)
                    .observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                        @Override
                        public void onChanged(List<WorkInfo> workInfos) {
                            workInfos.stream().filter(statee->statee.getState().compareTo(WorkInfo.State.ENQUEUED)==0).forEachOrdered(new Consumer<WorkInfo>() {
                                @Override
                                public void accept(WorkInfo workInfo) {
                                    Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  workInfo " + workInfos.get(0).getState().name());
                                    Long CallBaskОтWorkManagerОдноразового = workInfos.get(0).getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                    Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " + CallBaskОтWorkManagerОдноразового);
                                    onResume();
                                }
                            });}
                    });



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
// TODO: 29.01.2022






    private void МетодПришлиПараметрыОтДругихАктивитиДляРаботыТабеля() {
        try {
            Intent ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ =  getIntent();
            РодительскийUUDТаблицыТабель = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюДатуНовогоТабеля");
            }
            Log.d(this.getClass().getName(), " МесяцТабеляФинал :" + МесяцТабеляФинал);
            ИмяТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюНазваниеТабеля");
            Log.d(this.getClass().getName(), " ИмяТабеляФинал :" + ИмяТабеляФинал);
            ДепартаментТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемДепартаментФинал");
            Log.d(this.getClass().getName(), " ДепартаментТабеляФинал :" + ДепартаментТабеляФинал);
            if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеНазваниеТабеляФинал");
                Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            }
            ///TODO ПОЛНОЕ НАЗВАНИЕ ТАБЕЛЯ ПОСЛЕ УСПЕШНОЙ СОЗДАНОГО СОТРУДНИКА И ПЕРЕДВЕМ СЮДА ЕГО ИМЯ ТАБЕЛЯ ПОЛНОЕ
            ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника");
            Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки);
            String ПроверкаUUIDТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля");
            ////todo если не пришел uuid запускаем его с дгурим uuid
            UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля");
            Log.d(this.getClass().getName(), "UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя" + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            НазваниеТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " НазваниеТабеляПослеУспешногоСозданиеСотрудника " + НазваниеТабеляПослеУспешногоСозданиеСотрудника);
            UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " UUIDТабеляФинал:" + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            НазваниеТабеляПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " НазваниеТабеляПришелПослеСоздангоНового  " + НазваниеТабеляПришелПослеСоздангоНового);
            ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            String UUIDТабеляПослеПодбораУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбораУниверсальный");
            Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            ГодТабеляПослеПодбораУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ГодДляЗагрузкиТабелей");
            Log.d(this.getClass().getName(), " ГодТабеляПослеПодбораУниверсальный  " + ГодТабеляПослеПодбораУниверсальный);
            if(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляИзВсехСотрудниковВТАбеле");//ДепартаментПришелПослеСоздангоНового
                Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            }
            if (UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников==0l) {
                UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле", 0);
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника   " + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле");
            }
            Log.d(this.getClass().getName(), "  МесяцТабеляФинал  " + МесяцТабеляФинал);
            if ( UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя==null){
                UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя= UUIDТабеляПослеПодбораУниверсальный;
            }
            if (МесяцТабеляФинал==null){
                МесяцТабеляФинал=НазваниеТабеляПришелПослеСоздангоНового;
            }
            //todo департамент
            if (ДепартаментТабеляФинал==null ){
                ДепартаментТабеляФинал=ДепартаментПришелПослеСоздангоНового;
            }
            if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника ==null){
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки ;
            }
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("МесяцТабеляПослеПодбора");
                Log.d(this.getClass().getName(), "  МесяцТабеляФинал " + МесяцТабеляФинал);
                ////
                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки==null) {
                    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.
                            getStringExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора");
                    Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки);
                }
                if (ДепартаментПришелПослеСоздангоНового==null) {
                    ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляПослеПодбора");
                    Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
                }
                ////
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбораУниверсальный");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбора");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }

                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                    ПолноеИмяТабеляПослеСозданиеНовогоСотрудника= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.
                            getStringExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора");
                    Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                }
///todo если имя табеля пустое полслен созадени нового сотрудника по присваемое ему имя от дургой переменой
                Log.d(this.getClass().getName(), "   МесяцТабеляФинал  " + МесяцТабеляФинал +
                        "    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки   " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки+
                        "  ДепартаментПришелПослеСоздангоНового " + ДепартаментПришелПослеСоздангоНового + "  UUIDТабеляПослеУспешногоСозданиеСотрудника  "
                        + UUIDТабеляПослеУспешногоСозданиеСотрудника );
            }

            if (UUIDТабеляПослеУспешногоСозданиеСотрудника==null){
                UUIDТабеляПослеУспешногоСозданиеСотрудника=UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя;
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            if(UUIDТабеляКнопкаBACKУниверсальный!=null) {
                if (UUIDТабеляКнопкаBACKУниверсальный.length() < 1) {
                    UUIDТабеляКнопкаBACKУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляКнопкаBACKУниверсальный");
                    Log.d(this.getClass().getName(), "  UUIDТабеляКнопкаBACKУниверсальный  " + UUIDТабеляКнопкаBACKУниверсальный);
                }
            }
            if(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null){
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника");
                Log.d(this.getClass().getName(), "  ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            }
//////todo ПРИШЛО НА SINGLE TANEL ПО СОТРУДНИКУ
            IDЧьиДанныеДляСотрудников = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("IDЧьиДанныеДляСотрудников",0);
            Log.d(this.getClass().getName(), "  IDЧьиДанныеДляСотрудников  " +IDЧьиДанныеДляСотрудников);
            //////todo дополнтельные данные которые нужно перебросить далеее в другие актвити и ДАЛЕЕ МЕТКА ТАБЕЛЯ
            if(МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюДатуНовогоТабеля");
            }
            if( ДепартаментПришелПослеСоздангоНового==null) {
                ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемДепартаментФинал");
            }
            if(ДепартаментПришелПослеСоздангоНового==null) {
                ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеНазваниеТабеляФинал");
            }
            if (UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя ==null ) {
                UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra(("ПередаваемыйИзКнопкиПолучаемUUIDТабеля"));

            }
            UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников=ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("UUIDТабеляПослеПодбораУниверсальный",0l);
            Log.d(this.getClass().getName(), " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  "+UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            if (ГодТабеляПослеПодбораУниверсальный == null){
                ГодТабеляПослеПодбораУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ГодТабеляФиналИзВсехСотрудниковВТАбеле");
            }
            ///TODO цифровоеимя табеля
            if (ЦифровоеИмяНовгоТабеля==0) {
                ЦифровоеИмяНовгоТабеля= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("ЦифровоеИмяНовгоТабеля",0);
            }
            //////todo  RКОНЕЦ дополнтельные данные которые нужно перебросить далеее в другие актвити и ДАЛЕЕ МЕТКА ТАБЕЛЯ
            if (ЦифровоеИмяНовгоТабеля==0) {
                /////
                ЦифровоеИмяНовгоТабеля= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("ЦифровоеИмяНовгоТабеляБольШИеБуквыАктивти",0);
            }

            Log.d(this.getClass().getName(), " ЦифровоеИмяНовгоТабеля " + ЦифровоеИмяНовгоТабеля);
            if (ТекущееПоложенияВТабелеДляСкрола==0) {
                ТекущееПоложенияВТабелеДляСкрола = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("setIDСамогоТабеляВКотромМыНаходились", 0);
            }
            Log.d(this.getClass().getName(), " Конец Получение Данных  ЦифровоеИмяНовгоТабеля " +  ЦифровоеИмяНовгоТабеля + "  PUBLIC_CONTENT.ЦифровоеИмяНовгоТабеля "+
                    " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля + "   ТекущееПоложенияВТабелеДляСкрола " +  ТекущееПоложенияВТабелеДляСкрола);
            МЕсяцДляКурсораТабелейДЛяПермещения = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("МЕсяцДляКурсораТабелей", 0);
            ПроизошелЛиСфайпПоДаннымСингТабеля = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getBooleanExtra("ПроизошелЛиСфайпПоДаннымСингТабеля",false);
            Log.d(this.getClass().getName(), "ПроизошелЛиСфайпПоДаннымСингТабеля " +  ПроизошелЛиСфайпПоДаннымСингТабеля);
            ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре");
            Log.d(this.getClass().getName(), "  ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            if (UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников==0l) {
                UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников " + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            if ( РодительскийUUDТаблицыТабель==0l) {
                РодительскийUUDТаблицыТабель = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            }
            if (ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре==null) {
                ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре");
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников "
                    + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников+
                     "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель+
                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " +ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //todo метод возврата к предыдущему активти
    private void МетодПриНАжатииНаКнопкуBACK() {
        try {
            КнопкаНазад.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  onDestroyView");
                    МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



    //TODO хдесь мы запускаем метод создание и обработка самого табеля
    private void МетолСозданиеТабеляФинал( @NonNull  String ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника) {
        try{
            МассивДляВыбораСпинераДаты.clear();
            МассивДляВыбораСпинераДаты=new ArrayList<>();
            МассивДляВыбораСпинераДаты.add(МесяцТабеляФинал);
            Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораСпинераДаты.toString());
            ArrayAdapter<String> АдаптерДляСпинераТабельФинал = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, МассивДляВыбораСпинераДаты);
            АдаптерДляСпинераТабельФинал.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            СпинерТАбельМЕсяцФинал.setAdapter(АдаптерДляСпинераТабельФинал);
            СпинерТАбельМЕсяцФинал.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //////меняем цвет спинера
                    try {
                        if (view!=null) {
                            for (int i=0;i<СпинерТАбельМЕсяцФинал.getCount();i++) {
                                ////
                                ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                                ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                                ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                                //////
                                //////todo полученое выбраное ЗНАЧЕНИЕ ИЗ СПИНЕРПА ПЕРЕДАЕМ ДАЛЬШЕ
                                КонтентСпинераНаАктивтиТабель= String.valueOf(((TextView) parent.getChildAt(0)).getText()); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                                Log.d(this.getClass().getName(), " КонтентСпинераНаАктивтиТабель  " +КонтентСпинераНаАктивтиТабель);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " );
                }
            });

            МетодСпинерДепартамент(ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника, АдаптерДляСпинераТабельФинал);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодСпинерДепартамент(@NonNull String ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника, ArrayAdapter<String> АдаптерДляСпинераТабельФинал) {
        // TODO: 20.11.2022 второй спинер департамент
        try {
        МассивДляВыбораВСпинерДепартамент.clear();
        МассивДляВыбораВСпинерДепартамент=new ArrayList<>();
        МассивДляВыбораВСпинерДепартамент.add(ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника);
        Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораВСпинерДепартамент.toString());
        ArrayAdapter<String> АдаптерДляСпинераТабельФиналДепартамент = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,МассивДляВыбораВСпинерДепартамент);
        АдаптерДляСпинераТабельФинал.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        СпинерТАбельДепартаментФинал.setAdapter(АдаптерДляСпинераТабельФиналДепартамент);
        СпинерТАбельДепартаментФинал.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //////меняем цвет спинера
                try {
                    if (view!=null) {
                        for (int i=0;i<СпинерТАбельДепартаментФинал.getCount();i++) {
                            ///////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#708090"));
                            ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                            ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                            ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL |Gravity.CENTER_HORIZONTAL);
                            ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                            КонтентСпинераНаАктивтиТабель= String.valueOf(((TextView) parent.getChildAt(0)).getText()); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                            Log.d(this.getClass().getName(), " КонтентСпинераНаАктивтиТабель  " +КонтентСпинераНаАктивтиТабель);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                СпинерТАбельДепартаментФинал.setClickable(false);
                СпинерТАбельДепартаментФинал.setFocusable(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " );
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    //TODO заполения табеля из базы через элемент TableLauy

    private void МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(String  МесяцТабеляФинал ,int ЕслиСмещениеВдАнныхДляСкрола) {
        //////


        SQLiteCursor ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество = null;



        try{
            ///TODO курсор который поттягиваемд данные избазы табель
            Log.d(this.getClass().getName(), " МесяцТабеляФинал " + МесяцТабеляФинал);


            /// TimeUnit.MILLISECONDS.sleep(100);



////TODO КУРСОР ЗАГРУЖАЕТ НА АКТИВИТИ ДАННЫЕ УЖЕ СУЩЕТСТУЕЮЩЕИ ДАННЫЕ НА ТАБЕЛЬ
            МесяцДляЗагрузкиТабелей= String.valueOf(МетодПоказатьМесяцДляЗАписивОднуКолонку(МесяцТабеляФинал));

            ГодДляЗагрузкиТабелей= String.valueOf(МетодПоказатьГодДляЗАписивОднуКолонку(МесяцТабеляФинал));



            // ID


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(getApplicationContext());
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());


            ///////
            SQLiteCursor            Курсор_ПолучаемПубличныйID= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if(Курсор_ПолучаемПубличныйID.getCount()>0){
                ///
                Курсор_ПолучаемПубличныйID.moveToFirst();

                /////
                ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемПубличныйID.getInt(0);
///


                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }



            ПубличноеIDЗагрузкиТабелей= String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID);
            ///

            Log.d(this.getClass().getName(), " ПубличноеIDЗагрузкиТабелей  " + ПубличноеIDЗагрузкиТабелей);


            ///todo условие загрузка действуещнго табеля или тот ЖЕ ТАБЕЛЬ ,,,НО ПОСЛЕ СОЗДАНИЯ НА ЭТОТ ТАБЕЛЬ СОТРУДНИКА ДОПОЛНИТЕЛЬНОГО И ДЕЛАЕМ УСЛОВИЕ
            //TODO месяц и год для куросара
            МЕсяцДляКурсораТабелей=МетодПолучениниеМесяцДляКурсора( МесяцТабеляФинал);
            //TODO месяц и год для куросара
            ГодДляКурсораТабелей=МетодПолучениниеГодДляКурсора( МесяцТабеляФинал);
            //////
            Log.d(this.getClass().getName()," МЕсяцДляКурсораТабелей "+МЕсяцДляКурсораТабелей+" ГодДляКурсораТабелей " +ГодДляКурсораТабелей +
                    " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников " +UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников+" МЕсяцДляКурсораТабелейДЛяПермещения " +МЕсяцДляКурсораТабелейДЛяПермещения);
////todo загружем созданные табеля из базы после успешного добавления нового сотрдуника
            ////TODO ЕСДИ ДАННЫХ НЕТ НЕ И НЕЧЕГО НАЧИНАТЬ ВЫБОРКУ ДАННЫХ ИЗ БАЗЫЫ ДепартаментТабеляФинал

            if (МЕсяцДляКурсораТабелейДЛяПермещения==0) {
                //////
                МЕсяцДляКурсораТабелейДЛяПермещения=МЕсяцДляКурсораТабелей;
            }
















///TODO СЮДА ЗАХОДИМ ПРОСТО БЕЗ СОЗДАНИЕ НОВОГО ЗАГРУЖАЕТ УЖЕ СОЗДАННОЕ ТАБЕЛЯ В БАЗЕ "status_send", "Удаленная"

            SQLiteCursor Курсор_ПолучаемКоличествоСотрудниковВАтбеле=null;
            ////////////
            Курсор_ПолучаемКоличествоСотрудниковВАтбеле =
                    new Class_MODEL_synchronized(getApplicationContext())
                            .МетодЗагружетУжеготовыеТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек(context,
                            ЦифровоеИмяНовгоТабеля,МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей);

            ///
            Log.d(this.getClass().getName(), "Курсор_ПолучаемКоличествоСотрудниковВАтбеле " +Курсор_ПолучаемКоличествоСотрудниковВАтбеле);


            ////////
            ОбщееКоличествоСОтрудниковДляСкролаПотабелю=0;
            ///////////////////////////

            if (  Курсор_ПолучаемКоличествоСотрудниковВАтбеле.getCount()>0) {
                //////
                Курсор_ПолучаемКоличествоСотрудниковВАтбеле.moveToFirst();

                ОбщееКоличествоСОтрудниковДляСкролаПотабелю=    Курсор_ПолучаемКоличествоСотрудниковВАтбеле.getCount();


                ///
                Log.d(this.getClass().getName(), "ОбщееКоличествоСОтрудниковДляСкролаПотабелю " + ОбщееКоличествоСОтрудниковДляСкролаПотабелю);



            }





            ///
            Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                    " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  " +  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников+
                    "  ПроизошелЛиСфайпПоДаннымСингТабеля " + ПроизошелЛиСфайпПоДаннымСингТабеля);
            ///


            // TODO: 24.09.2021  При первом старте цифра 1 ровно

            if ( ПроизошелЛиСфайпПоДаннымСингТабеля==false) {
                // TODO: 28.04.2021 движение назад


                // TODO: 07.05.2021 получение UUID дл я струдника из тпбелья

                // TODO: 09.05.2021 получаем uuid





                ///
                Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                        " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  " +  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников+
                        "  ПроизошелЛиСфайпПоДаннымСингТабеля " + ПроизошелЛиСфайпПоДаннымСингТабеля);
                // TODO: 07.05.2021



                ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество=null;

                ////////////////////
                ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество =
                        new Class_MODEL_synchronized(getApplicationContext()).МетодЗагружетУжеготовыеТабеля(context,
                                UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников,МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей);

                ///
                Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getCount());

                ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.moveToFirst();






                ///TODO ЗАПУСКАЕМ  ПуллПамяти


                int ИНдексГдеНахолитьсяКолонкаДляФИО=ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getColumnIndex("fio");////name

                Long ПолученыйUUIDФИОСледующийДляПервогоЗапуска=     ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getLong(ИНдексГдеНахолитьсяКолонкаДляФИО);////  String ФИОСледующий

                Log.d(this.getClass().getName(), "ПолученыйUUIDФИОСледующийДляПервогоЗапуска " + ПолученыйUUIDФИОСледующийДляПервогоЗапуска);



                String ФИОСледующий=null;

                ///
                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля= new Class_GRUD_SQL_Operations(getApplicationContext());
                ///
                class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                        " SELECT name  FROM fio  WHERE  uuid = '" + ПолученыйUUIDФИОСледующийДляПервогоЗапуска + "' ;");
                ///////


                // TODO: 12.10.2021
                SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля.
                        new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО "  +Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО);
                ////
                if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){

                    ///
                    Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();



                    ФИОСледующий=Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0);

                    ///
                    Log.d(  this.getClass().getName(), "ФИОСледующий "
                            +"uuid"+ФИОСледующий);

                }




                // TODO 24.09.2021  результат оплученный ФИО



// TODO: 28.04.2021 вперед




                int ИНдексГдеНаходитьсяUUIDПервыйЗапуск=         ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getColumnIndex("uuid");


                UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getLong(ИНдексГдеНаходитьсяUUIDПервыйЗапуск)    ;

                Log.d(this.getClass().getName(), "UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников " + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);








                // TODO: 24.09.2021  САМО ЗАПОЛЕНЕНИЕ  после получение данных и дем  уже визуально заполнять ТАБЕЛЬ ДНИ Д1,Д2,Д3,Д4,Д5,Д6


                МетодЗаполенияТабелемСотрудникаДнемиИДаннымиКним(ЕслиСмещениеВдАнныхДляСкрола,
                        ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество,
                        ЦифровоеИмяНовгоТабеля);





                ///
                Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                        " ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество  "
                        +  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество+
                        " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля+
                        "  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество "
                        +ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество);


                ///
                ///


                // TODO: 25.09.2021  КОД КОГДА ПРОШЕЛ СФАЙП




/////////////////////////
            } else  if  (  ПроизошелЛиСфайпПоДаннымСингТабеля==true ){



                ///
                Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                        " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  " +  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников +
                        "  ПроизошелЛиСфайпПоДаннымСингТабеля " +ПроизошелЛиСфайпПоДаннымСингТабеля);


                ////////TODO движение вперет


                // TODO: 09.05.2021 получаем uuid








                // TODO: 24.09.2021  _old
                ///
                Log.d(this.getClass().getName(), "UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников "
                        + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);

                if (UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников==0) {

                    //
                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество=null;
                    ////////////
                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество =
                            new Class_MODEL_synchronized(getApplicationContext()).
                                    МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(context,
                                    ЦифровоеИмяНовгоТабеля,МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей);

                    ///
                    Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getCount());
                }else{

                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество=null;

                    ////////////////////
                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество = new Class_MODEL_synchronized(getApplicationContext()).МетодЗагружетУжеготовыеТабеля(context,
                            UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников,МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей);

                    ///
                    Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getCount());

                }


                ///
                Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество
                        + " ТекущееПоложенияВТабелеДляСкрола " +ТекущееПоложенияВТабелеДляСкрола);


                // TODO: 12.05.2021  перемещение по данным вперед

                /// ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.moveToFirst();

                Integer ИндексКоличествоЗначениейВКурсоре=ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getCount();

                if (ЕслиСмещениеВдАнныхДляСкрола <= ИндексКоличествоЗначениейВКурсоре) {

                    // TODO: 25.09.2021 САМО СМЕЩЕНИЕ ДАННЫХ

                    ////
                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.move(ТекущееПоложенияВТабелеДляСкрола);  //ТекущееПоложенияВТабелеДляСкрола


                    ////
                    //     ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.moveToPosition(ТекущееПоложенияВТабелеДляСкрола-1);///          ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.move(ТекущееПоложенияВТабелеДляСкрола);

                    //  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.moveToNext();



                    int ИНдексГдеНаходитсьяКолонкаUUIDТабеляПриСфайпе=ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getColumnIndex("uuid");


                    UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников=     ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getLong(ИНдексГдеНаходитсьяКолонкаUUIDТабеляПриСфайпе);

                    ///
                    Log.d(this.getClass().getName(), "UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  " +UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);



                    int ИНдексГдеНаходитсьяКолонкаНаходимФИОПриСфайпе=ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getColumnIndex("fio");


                    Long ПолученоеЗначениеФИОПриСфайпе=     ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.getLong(ИНдексГдеНаходитсьяКолонкаНаходимФИОПриСфайпе);


                    ///
                    Log.d(this.getClass().getName(), "ПолученоеЗначениеФИОПриСфайпе  " +ПолученоеЗначениеФИОПриСфайпе);


                    String ФИОСледующий=null;


                    // TODO: 24.09.2021  ПОЛУЧСЕАМ ФИО
                    ///


                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля= new Class_GRUD_SQL_Operations(getApplicationContext());
                    ///
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT name  FROM fio  WHERE  uuid = '" + ПолученоеЗначениеФИОПриСфайпе + "' ;");







                    ///////
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО=
                            (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля.
                                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеля.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    Log.d(this.getClass().getName(), "Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО "  +Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО);
                    ////
                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){

                        ///
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();



                        ФИОСледующий=Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0);

                        ///
                        Log.d(  this.getClass().getName(), "ФИОСледующий "
                                +"uuid"+ФИОСледующий);

                    }



                    //////
                    Log.d(this.getClass().getName()," Данные в курсор не загруженны ( отсутвуют:) -- МЕсяцДляКурсораТабелей "+МЕсяцДляКурсораТабелей+" ГодДляКурсораТабелей " +ГодДляКурсораТабелей +
                            " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников " +UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);








                    // TODO: 24.09.2021  САМО ЗАПОЛЕНЕНИЕ  после получение данных и дем  уже визуально заполнять ТАБЕЛЬ ДНИ Д1,Д2,Д3,Д4,Д5,Д6


                    МетодЗаполенияТабелемСотрудникаДнемиИДаннымиКним(ЕслиСмещениеВдАнныхДляСкрола,
                            ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество,
                            ЦифровоеИмяНовгоТабеля);





                    ///
                    Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                            " ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество  "
                            +  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество+
                            " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля+
                            "  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество "
                            +ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество);


                }else{
                    ////

                    Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество
                            + " ТекущееПоложенияВТабелеДляСкрола " +ТекущееПоложенияВТабелеДляСкрола);

                }

                // TODO: 07.05.2021 когда есть id  но молежени выше 1


                // TODO: 21.05.2021 значения равны
            }else{

///TODO В УКАЗАНОМ ТАБЕЛЕ НЕТ НИОДНОГО СОТРУДНИКА СТОРОЧЕК COUNT()* 0 НЕТ СТРОЧЕК В ТАБЕЛЕ
                МетодКогдаНетЗаписейВКурсоре();
                ///
                Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                        " UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников  " +  UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            }




            //// заполения данными

            ///
            Log.d(this.getClass().getName(), "ЕслиСмещениеВдАнныхДляСкрола " + ЕслиСмещениеВдАнныхДляСкрола+
                    " ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество  "
                    +  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество+
                    " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля+
                    "  ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество "
                    +ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество);








            Log.d(this.getClass().getName(), "ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество " +
                    ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество);







            ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайпТОлькоОбшееКоличество.close();






            //поймать ошибку всего классаIOException | MyException e    NumberFormatException
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ////
    }





























    private void МетодЗаполенияТабелемСотрудникаДнемиИДаннымиКним(int еслиСмещениеВдАнныхДляСкрола,
                                                                  SQLiteCursor ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп,
                                                                  int ЦифровоеИмяНовгоТабеля)
            throws ParseException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ExecutionException, InterruptedException {



        try{

            /////TODO ВАЖНО ПОЛУЧИЛИ ДАННЫЕ ИЗ БАЗЫ В КУРСОР СОЗДАННЫХ ТАБЕЛЕЙ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount()>0) {



                ////TODO даты обработки
                Log.d(this.getClass().getName()," ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп "+ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount() );






                // TODO: 28.04.2021  копируем курсор




                ///TODO ищем имя если его не хватает
                String УниверсальноеИмяТабеля= "";
                /////////
                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника!=null) {
                    УниверсальноеИмяТабеля=ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;
                }else if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки!=null) {
                    УниверсальноеИмяТабеля=    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки;
                } else if (ДепартаментПришелПослеСоздангоНового !=null){
                    УниверсальноеИмяТабеля=     ДепартаментПришелПослеСоздангоНового;
                }

                //todo обнуляем
                //TODO заполения табеля из базы через элемент TableLauy
                //
                Log.d(this.getClass().getName()," ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount() " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount()+"\n" +
                        " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                Log.d(this.getClass().getName()," ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnCount() " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnCount()
                        + " ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки "
                        +ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки+ " UUIDТабеляКнопкаBACKУниверсальный " +UUIDТабеляКнопкаBACKУниверсальный);
                /////



                // КонтейнерДляТабеляВнутириСотрудники.removeAllViews();/////удалем данные с актиывти
                ///TODO определяем массив и заполнем его ниже данными из базы массив двухмерный определяем размер массива
                /*   ГлавныйКонтерйнерДляКомпнентовТабеля.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));*/
                //TODO запоминаем название табеля
                try {
                    НазваниеЗагруженногТАбеля =         new Class_MODEL_synchronized(this).  МетодПолучениеНазваниеТабеляНаОснованииСФО(this,ЦифровоеИмяНовгоТабеля);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(this.getClass().getName(), " НазваниеЗагруженногТАбеля" + НазваниеЗагруженногТАбеля);
                ///TODO  проверяем станус проведенный или нет
                int ИндексГдеНаходитьсяСтутсПроведенных = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("status_carried_out");
                СтаттусТабеля =Boolean.parseBoolean( ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ИндексГдеНаходитьсяСтутсПроведенных)); ///строго имя
                Log.d(this.getClass().getName(), " СтаттусТабеля" + СтаттусТабеля);
                if (СтаттусТабеля==true) {
                    Toast.makeText(getApplicationContext(), " Табель Проведен !!!. " + "\n" + "(редактирование запрещено).", Toast.LENGTH_SHORT).show();
                }
                //////TODO цикл заполения Табеля
                final int[] ИндексСтрокКомпонентовТабеля = {0};
                /////
                int ИндексКолонокКомпонентовТабеля = 0;
                ////TODO выжный компонет СОЕДИНЯЕМ ДВЕ АКТИВТИ НЕМЖУ СЕБЯ ЗАГРУЖАЕМ ОДНЦУ АКТИВТИ В ДРУГУЮ
                /////TODO заполение реальными днями в табеле конкретного указаного месяца ДНИ  МЕСЯЦА
                МетодСозданиеМесяцыСокращенно();
                //TODO запускаем цикл загрузки на активити табель данными из базы
                try{
                    ГлавныйКонтейнерТабель.removeAllViews();
                    ///////
                    ГлавныйКонтейнерТабель.removeAllViewsInLayout();
                } catch (Exception e) {
                    // e.printStackTrace();
       /*         ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());*/
                }
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1) " + ХЭШНазваниеДнейНедели.get(1));
                LayoutInflater МеханизмЗагрузкиОдногЛайАутавДругой = getLayoutInflater();
//todo ВЫБИРАЕМ ВЗАВИСИСМОСТИ КАКОЙ ЭКРАН БУДЕТ ЗАГРУЖАТЬСЯ ПОЛЬЗОВАТЕЛЮ В ОДИНОЧНОМ тАБЕЛЕ
                if (Build.BRAND.toString().contains("Samsung") ||Build.BRAND.toString().contains("Galaxy")
                        || Build.BRAND.toString().contains("samsung") ||Build.BRAND.toString().contains("galaxy") ) {
                    Log.d(this.getClass().getName(), "Внешний вид САМСУНГА  "  +Build.BRAND.toString() );
                    КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_samsung,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                            ГлавныйКонтейнерТабель, false);
                    ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();

                    // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ НА HTC
                } else if (Build.BRAND.toString().contains("HTC") ){
                    Log.d(this.getClass().getName(), "Внешний вид HTC "  +Build.BRAND.toString() );
                    КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm_htc,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                            ГлавныйКонтейнерТабель, false);
                    ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();
                    // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ ВСЕ ОСТАЛЬНЫЕ ТЕЛЕФОНЫ
                } else {
                    Log.d(this.getClass().getName(), "Внешний вид ДЛЯ ВСЕХ ОСТАЛЬНЫХ "  +Build.BRAND.toString() );
                    КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                            ГлавныйКонтейнерТабель, false);
                    ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();
                }























                // TODO: 09.05.2021



                HorizontalScrollViewВТабелеОдинСотрудник     =(HorizontalScrollView)     КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ГоризонтльнаяПрокруткаВнутриСамТабель);

  /*          if (!Build.BRAND.toString().contains("Redmi") ) {
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.setPadding(35,20,0,0);
            }else{
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.setPadding(0,20,0,0);
            }

            КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.setForegroundGravity(Gravity.CENTER_HORIZONTAL);*/



                /////TODO ТАБЕЛЬ ФИО создаем textview названия дней понелельник вторик среда четеварг
                ///////// todo фио
                НазваниеДанныхВТабелеФИО = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);

                НазваниеДанныхВТабелеФИО.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                ///todo  снилс

                // НазваниеДанныхВТабелеСНИЛС = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id. КонтейнерКудаЗагружаетьсяСНИЛС);

                ///toDO ПОСИК И ВСТАВКИ ИМСЯ ТАБЕЛЯ
                int РасположениеИмениСотркдника = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("fio");//name



                Long ПолученыйUUIDФИОСледующий=     ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getLong(РасположениеИмениСотркдника);////  String ФИОСледующий


                ////////////////
                ФИОДляТабеляНаАктивти=null;

                ///
                Log.d(this.getClass().getName(), "ПолученыйUUIDФИОСледующий " + ПолученыйUUIDФИОСледующий);

                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеляДва= new Class_GRUD_SQL_Operations(getApplicationContext());
                ///
                class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеляДва. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                        " SELECT name  FROM fio  WHERE  uuid = '" + ПолученыйUUIDФИОСледующий + "' ;");
                ///////
                SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеляДва.
                        new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯДляSingleтабеляДва.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО "  +Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО);
                ////
                if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                    ///
                    Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                    ФИОДляТабеляНаАктивти=Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0);
                    ///
                    Log.d(  this.getClass().getName(), "ФИОСледующий "
                            +"uuid"+ФИОДляТабеляНаАктивти);


                    ///TODO ЕСЛИ НЕ ПУСТОЙ ТАБЕЛЬ
                    ///TODO НАЧИНАЕМ ЗАПОЛНЯТЬЕ ЕСЛИ СОТРУДНИКА ЕСТЬ
                    НазваниеДанныхВТабелеФИО.setText("");
                    НазваниеДанныхВТабелеФИО.setText(ФИОДляТабеляНаАктивти.trim()); ///строго имя
                    Log.d(this.getClass().getName(), " ФИО " + ФИОДляТабеляНаАктивти);
                    // TODO проверяем если в столбике Табеля Есть id поле если поле NULL  значит отн небыло на сервера
                    Integer ПроверкаЕсливСтолбикеID_NULLзначения=ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getInt(0);
                    if(ПроверкаЕсливСтолбикеID_NULLзначения==null){
                        // TODO: 15.11.2021
                        ПроверкаЕсливСтолбикеID_NULLзначения=0;
                    }
                    /////TODO создавние строк из linerlouyto в табеле сколько сткром в базе данных андройда столлько на активити строк
                    Log.d(this.getClass().getName(), " ПроверкаЕсливСтолбикеID_NULLзначения " + ПроверкаЕсливСтолбикеID_NULLзначения);
           /*         if (ПроверкаЕсливСтолбикеID_NULLзначения==0) {
                        // TODO: 15.11.2021
                        view2Линия.setBackgroundColor(Color.GRAY);
                    }else {
// TODO: 15.11.202
                        view2Линия.setBackgroundColor(Color.parseColor("#00ACC1"));
                    }*/
                    ///TODO ГЛАВНЫЙ ЦИКЛ ЗАПОЛЕНИЕНИ ТАБЕЛЕМ СОТРУДНИКАМИ
                    do {
                        /////TODO SLEEP
                        /////TODO создавние строк из linerlouyto в табеле сколько сткром в базе данных андройда столлько на активити строк
                        Log.d(this.getClass().getName(), " ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount() " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getCount());
                        ///todo ПРИСВАИВАЕМ UUID НАЗВАНИЮ ФИО
                        ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("uuid"); ////TODO СЮДА ПОЛЕ UUID
                        НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
                        НазваниеДанныхВТабелеФИО.setTag(ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
                        Log.d(this.getClass().getName(), " UUID пристваем Внутри ФИО  " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
                        ////todo УСТАНАВЛИВАЕМ КЛИК НА ФИО
                        //НазваниеДанныхВТабелеФИО.setOnLongClickListener(СлушательУдаланиеСотрудникаИзТабеля);
                        НазваниеДанныхВТабелеФИО.setOnClickListener(СлушательИнформацияОСотрудника);
                        /////TODO     ПЕРВАЯ СТРОКА
                        ///todo главный МЕТОД ОФОРМЛЕНИЯ ТАБЕЛЯ ДАННЫМИ И ДНЯМИ
                        МетодГлавныйОрмленияТабеляДнямиИЗначениямиДляЭтиДней(ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп,
                                ИндексСтрокКомпонентовТабеля,
                                ХЭШНазваниеДнейНедели,НазваниеДанныхВТабелеДниНедели ,СамиДанныеТабеля,КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла );
/////TODO ФИНАЛ ЗАПОЛЕНИЕ ДАННЫМИ АКТИВИТИ ЧЕРЕЗ ДРУГОЕ АКТВИТИ
                        ГлавныйКонтейнерТабель.addView(КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла);
                        // TODO: 29.04.2021 если то оди
                        if( еслиСмещениеВдАнныхДляСкрола >0){
                            break;
                        }
                    }
                    while (ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.moveToNext()) ;
                    ОбщееКоличествоЛюдейВТабелеТекущем = new Class_MODEL_synchronized(getApplicationContext()).
                            МетодПосчётаЧасовСотрудниковВТабеле(getApplicationContext(),UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников ,МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей );
                    Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем  +
                            "  ТекущееПоложенияВТабелеДляСкрола "  +ТекущееПоложенияВТабелеДляСкрола);
                    

                }else{

                    Toast.makeText(getApplicationContext(), " Ошибка  нет ФИО " , Toast.LENGTH_SHORT).show();

                    ///TODO В УКАЗАНОМ ТАБЕЛЕ НЕТ НИОДНОГО СОТРУДНИКА СТОРОЧЕК COUNT()* 0 НЕТ СТРОЧЕК В ТАБЕЛЕ
                    МетодКогдаНетЗаписейВКурсоре();
                }

            }else{
                ///TODO В УКАЗАНОМ ТАБЕЛЕ НЕТ НИОДНОГО СОТРУДНИКА СТОРОЧЕК COUNT()* 0 НЕТ СТРОЧЕК В ТАБЕЛЕ
                МетодКогдаНетЗаписейВКурсоре();
            }
            // TODO: 17.08.2022  after peossesuinbg
            ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.close();
            ScrollСамогоТабеля.setBackgroundResource(R.drawable.textlines_tabel_row_color_green_mini);
            ScrollСамогоТабеля.requestLayout();
            ScrollСамогоТабеля.pageScroll(View.FOCUS_BACKWARD);
            ГлавныйКонтейнерТабель.requestLayout();
            ГлавныйКонтейнерТабель.forceLayout();



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        /////////




    }


    void МетодПослеУспешнойГенерацииТабеля() {

        try{

            //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
            ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.setText(" (" + " " + ОбщееКоличествоЛюдейВТабелеТекущем + "/часы)  #"+ТекущееПоложенияВТабелеДляСкрола+"");


            // TODO: 25.09.2021
            Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем  +
                    "  ТекущееПоложенияВТабелеДляСкрола "  +ТекущееПоложенияВТабелеДляСкрола);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




























    ///TODO Когда в  КУРСОРЕ ВООБЩЕ НЕТ ДАННЫХ НА УКАЗАННЫЙ МЕСЯЦ
    private void МетодКогдаНетЗаписейВКурсоре() {
        try{
            ГлавныйКонтейнерТабель.removeAllViews();
            ///
            ГлавныйКонтейнерТабель.removeAllViewsInLayout();
        } catch (Exception e) {
            /// e.printStackTrace();
            ///метод запись ошибок в таблицу
/*     Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());*/
        }



        /////TODO создавние строк из linerlouyto в табеле сколько сткром в базе данных андройда столлько на активити строк
        LayoutInflater МеханизмЗагрузкиОдногЛайАутавДругой = getLayoutInflater();
        //КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = new View(this);//activity_main_find_customer_for_tables // activity_main_grid_for_tables
        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns,
                ГлавныйКонтейнерТабель, false);
        //   НазваниеДанныхВТабелеФИО=new TextView(this);
        НазваниеДанныхВТабелеФИО = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);

        /////TODO ТАБЕЛЬ ФИО создаем textview названия дней понелельник вторик среда четеварг
        ///////// todo фио
        ///TODO ЕСЛИ ИМЕНИ НЕТ ПО НА АКТИВИТИ ПОКАЗЫВАЕМТ ЧТО ТАБЕЛЬ ПУСТОЙ
        НазваниеДанныхВТабелеФИО.setTextColor(Color.RED);
        НазваниеДанныхВТабелеФИО.setText("*Табель  пустой (заполните)*"); ///строго имя
        Log.d(this.getClass().getName(), " ФИО " + "*Пустой табель (заполните)*");
        /////TODO ФИНАЛ ЗАПОЛЕНИЕ ДАННЫМИ АКТИВИТИ ЧЕРЕЗ ДРУГОЕ АКТВИТИ
        ГлавныйКонтейнерТабель.addView(КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла);


////todo удаление из памяти
        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.invalidate();
        ГлавныйКонтейнерТабель.invalidate();
        ScrollСамогоТабеля.invalidate();
        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.requestLayout();
        ГлавныйКонтейнерТабель.requestLayout();
        ScrollСамогоТабеля.requestLayout();
        //ScrollСамогоТабеля.fullScroll(View.FOCUS_UP);


        ///todo
    }





///TODO   КОНЕЦ ScrollView






    ///todo коды ЛОКАЛЬНОГО ОБНОВЛЕНИЕ И УДАЛЕНИЕ СОТРУДНИКА


    /////View/////TODO ТУТ ПЕРЕРХОДИМ НА КОД КОТОРЫЙ ДОБАВЛЯЕТ В ТАБЕЛЬ СЛОВА НЕ ЦИФРЫ ВИДЫ В-О-С
    View.OnLongClickListener СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления= new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            /////todo ПЕРЕХОДИМ НА МЕТКИ ТАБЕЛЯ
            Log.d(this.getClass().getName(), " View.OnLongClickListener ");
            HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель= МетодЛовимЗначениеВТАбелеДляДобавленияСловВТабель(v);

            Log.d(this.getClass().getName(), "ХэшЛовимUUIDIDНазваниеСтолбикаЛокальный  " + ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());



            ((TextView) v).setBackgroundColor(Color.GRAY);




            Log.d(this.getClass().getName(), "ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values()  " +ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());
            /////TODO запускаем сообщение которые справишает О ПЕРЕХОДЕ НА АКТИВТИ МЕТЫКИ ТАБЕЛЯ

            МетодПереходаНаМеткиТабеля(ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель);
            /////
            return false;
        }

    };








    /////View/////TODO ТУТ ПОЛУЧАЕМ КУДА ОБНОВЛЯТЬ ДНИ  D1,D2,D3
    View.OnClickListener СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /////todo полученине данных uuid id
            Log.d(this.getClass().getName(), " View.OnClickListener");
            МетодЛовимЗначениеВТАбелеUUIDиIDиНазваниеСтоблиув(v);
        }
    };


    //TODO второй long клик на прозопас
    /////View/////TODO ТУТ ПОЛУЧАЕМ КУДА ОБНОВЛЯТЬ ДНИ  D1,D2,D3
    View.OnFocusChangeListener СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления= new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                Log.d("focus", "focus loosed");
                // Do whatever you want here
            } else {


                Log.d("focus", "focused");


                Log.d(this.getClass().getName(), "  View.OnLongClickListener");
                МетодЛовимЗначениеВТАбелеUUIDиIDиНазваниеСтоблиув(v);

            }
        }
    };











    /////View/////TODO метод uuid id названеи стоблика
    public  HashMap МетодЛовимЗначениеВТАбелеUUIDиIDиНазваниеСтоблиув(View v) {
        //////todo заполение
        String СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = null;//

        String ПолучениеЗначениеСтолбикID= null;//;

        String ПолучениеЗначениеСтолбикUUID= null;//;

        /////TODO  главная очистка перед новой вставкой  в табель АЧИЩАЕМ ПЕРЕРД ЗАПОЛЕНЕНИЕМ



        try{
            /////todo определяем хэшмап для uuid и знпчения для
            Log.d(this.getClass().getName(), "  MotionEvent СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления ");


            EditText ВытаскиваемЗначениеСтолбикаТабеля=null;



            ВытаскиваемЗначениеСтолбикаТабеля = (EditText) v;


            ///////
            String ВытаскиваемЗначениеСтолбикаТабеляДва=null;


            /////todo писваесмиаем d1,d,2,d4
            ВытаскиваемЗначениеСтолбикаТабеляДва = (String) ВытаскиваемЗначениеСтолбикаТабеля.getTag();


            ////todo верхнене значение локального обновления пример D1 , d2
            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва);



            ///todo обнуляем
            //////TODO ВАЖНО ЕСЛИ  не равно ЗНАЧЕНИЕ ДЛЯ НАПРИМЕР D1<>D2 РАЗНЫЕ ПОЛУЧЕН ПРИ КЛИСЕ ТО ТОГДА И ОБНУЛЕМ И ИЗМЕНЯЕМ ЗНАЧЕНИЯ NULLL !!!!!!!!!!!!!!!!!!!!!
            /////todo присваемваем значения новое
            if (ВытаскиваемЗначениеСтолбикаТабеляДва.length()>0) {
                ///////////
                СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = ВытаскиваемЗначениеСтолбикаТабеляДва.trim();
            }

////////////////////////////////////////
            Log.d(this.getClass().getName(), " charSequenceвторойстолбик: " + СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления);

            //TODO залезаем в обьект на активти где находяться данные табеля
            GridLayout GridRow = (GridLayout) v.getParent();

            Log.d(this.getClass().getName(), " GridRow" + GridRow);

            ///TODO UUID и ID ПОСИК ЕГО ВНУТРИ GRIDLAUTY

            int КоличествоСтобиков = GridRow.getChildCount();

            ///////

            for (int ИндексПоВытаскиваниюUUIDИID = 0; ИндексПоВытаскиваниюUUIDИID < КоличествоСтобиков; ИндексПоВытаскиваниюUUIDИID++) {

////TODO сами столбиков
                TextView СтолбикGridRow = (TextView) GridRow.getChildAt(ИндексПоВытаскиваниюUUIDИID);

                //TODO название столбиков uuid id
                String НазваниеКолонкиGridRow = null;

                НазваниеКолонкиGridRow = String.valueOf(СтолбикGridRow.getTag());

                /////TODO ОПРЕДЕЛЯЕМ КАКАЯ СЕЙЧАС ЯЧЕЙКА В ОБОРАБТКИ ID ИЛИ UUID
                Log.d(this.getClass().getName(), " НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);


                //todo выбор
                switch (НазваниеКолонкиGridRow.trim()) {


                    //todo fio какой стодик
                    case "uuid":

                        //TODO получение знчение  UUID
                        Log.d(this.getClass().getName(), "  ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);
                        ////
                        ПолучениеЗначениеСтолбикUUID = String.valueOf(СтолбикGridRow.getText());

                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +"\n"
                                + " ВытаскиваемЗначениеСтолбикаТабеляДва " +ВытаскиваемЗначениеСтолбикаТабеляДва +
                                " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);



                        //TODO при если нет UUID  то цепляемся локльное обновление через ID


                        ////
                        if(ПолучениеЗначениеСтолбикUUID !=null  && ВытаскиваемЗначениеСтолбикаТабеляДва!=null) {

                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ

                            ХэшЛовимUUIDIDНазваниеСтолбика=new HashMap<>();

                            /////TODO ЗАПОЛЯЕМ uuid
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикUUID));



                            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва+
                                    " ПолучениеЗначениеСтолбикUUID " +ПолучениеЗначениеСтолбикUUID);

                            // TODO: 21.05.2021
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;
                            ПолучениеЗначениеСтолбикUUID=null;


                            //////
                        }




                        break;







                    //todo id какой стодик
                    case "_id":

                        //TODO получение знчение ID
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        /////
                        ПолучениеЗначениеСтолбикID = String.valueOf(СтолбикGridRow.getText());

                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);


                        // TODO: 21.05.2021 обновление только через ID
                        if (ПолучениеЗначениеСтолбикID!=null  && ВытаскиваемЗначениеСтолбикаТабеляДва!=null){

                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ

                            ХэшЛовимUUIDIDНазваниеСтолбика=new HashMap<>();

                            /////TODO ЗАПОЛЯЕМ id
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикID));


                            Log.d(this.getClass().getName(), "ПолучениеЗначениеСтолбикUUID" + ПолучениеЗначениеСтолбикUUID
                                    + " ПолучениеЗначениеСтолбикID "+ПолучениеЗначениеСтолбикID);


                            // TODO: 21.05.2021
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;

                            ////////
                            ПолучениеЗначениеСтолбикUUID=null;

///TODO как получили значение выходим BREAK;
                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика.values() " + ХэшЛовимUUIDIDНазваниеСтолбика.values()+"ХэшЛовимUUIDIDНазваниеСтолбика.keySet()  "
                                    +ХэшЛовимUUIDIDНазваниеСтолбика.keySet());

                        }

                        break;



                    default:

                        Log.d(this.getClass().getName(), " NULL значение НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);




                }


            }
            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        /////////
        return  ХэшЛовимUUIDIDНазваниеСтолбика;
    }

    // TODO  конец КОД СЛУШАТЕЛЬ ПРИ НАЖАТИИ НА ТАБЕЛЬ Создаем экземпляр TextWatcher:




    /////TODO метод который добавляетв  табель прокме цифр и буквы

    /////View/////TODO метод uuid id названеи стоблика
    public  HashMap МетодЛовимЗначениеВТАбелеДляДобавленияСловВТабель(View v) {
        //////todo заполение
        String СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = null;//
        String ПолучениеЗначениеСтолбикID= null;//;
        String ПолучениеЗначениеСтолбикUUID= null;//;
        try{
            /////todo определяем хэшмап для uuid и знпчения для
            Log.d(this.getClass().getName(), "  MotionEvent СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления ");
            EditText ВытаскиваемЗначениеСтолбикаТабеля=null;
            ВытаскиваемЗначениеСтолбикаТабеля = (EditText) v;
            ///////
            String ВытаскиваемЗначениеСтолбикаТабеляДва=null;
            /////todo писваесмиаем d1,d,2,d4
            ВытаскиваемЗначениеСтолбикаТабеляДва = (String) ВытаскиваемЗначениеСтолбикаТабеля.getTag();
            ////todo верхнене значение локального обновления пример D1 , d2
            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва);
            ///todo обнуляем
            //////TODO ВАЖНО ЕСЛИ  не равно ЗНАЧЕНИЕ ДЛЯ НАПРИМЕР D1<>D2 РАЗНЫЕ ПОЛУЧЕН ПРИ КЛИСЕ ТО ТОГДА И ОБНУЛЕМ И ИЗМЕНЯЕМ ЗНАЧЕНИЯ NULLL !!!!!!!!!!!!!!!!!!!!!
            /////todo присваемваем значения новое
            СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = ВытаскиваемЗначениеСтолбикаТабеляДва;

////////////////////////////////////////
            Log.d(this.getClass().getName(), " charSequenceвторойстолбик: " + СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления);

            //TODO залезаем в обьект на активти где находяться данные табеля
            GridLayout GridRow = (GridLayout) v.getParent();
            Log.d(this.getClass().getName(), " GridRow" + GridRow);
            ///TODO UUID и ID ПОСИК ЕГО ВНУТРИ GRIDLAUTY
            int КоличествоСтобиков = GridRow.getChildCount();

            for (int ИндексПоВытаскиваниюUUIDИID = 0; ИндексПоВытаскиваниюUUIDИID < КоличествоСтобиков; ИндексПоВытаскиваниюUUIDИID++) {
////TODO сами столбиков
                TextView СтолбикGridRow = (TextView) GridRow.getChildAt(ИндексПоВытаскиваниюUUIDИID);
                //TODO название столбиков uuid id
                String НазваниеКолонкиGridRow = "";
                НазваниеКолонкиGridRow = String.valueOf(СтолбикGridRow.getTag());

                /////TODO ОПРЕДЕЛЯЕМ КАКАЯ СЕЙЧАС ЯЧЕЙКА В ОБОРАБТКИ ID ИЛИ UUID
                Log.d(this.getClass().getName(), " НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);


                //todo выбор
                switch (НазваниеКолонкиGridRow.trim()) {
                    //todo id какой стодик
                    case "_id":
                        //TODO получение знчение ID
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        /////
                        ПолучениеЗначениеСтолбикID = String.valueOf(СтолбикGridRow.getText());

                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);


                        ///todo заполения Hasmap uuid and id
                        if (ВытаскиваемЗначениеСтолбикаТабеляДва!=null  && ПолучениеЗначениеСтолбикID!=null) {
                            ////
                            Log.d(this.getClass().getName(), "      ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика.toString());

                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ

                            ХэшЛовимUUIDIDНазваниеСтолбика.clear();

                            /////TODO ЗАПОЛЯЕМ
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикID));

                            //////todo обнуляем переменные
                            ВытаскиваемЗначениеСтолбикаТабеляДва = null;
                            ПолучениеЗначениеСтолбикID = null;
                            ///////

                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());

                        }


                        break;







                    //todo fio какой стодик
                    case "uuid":

                        //TODO получение знчение  UUID

                        Log.d(this.getClass().getName(), "  ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);
                        ////
                        ПолучениеЗначениеСтолбикUUID = String.valueOf(СтолбикGridRow.getText());



                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +"\n"
                                + " ВытаскиваемЗначениеСтолбикаТабеляДва " +ВытаскиваемЗначениеСтолбикаТабеляДва +
                                " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
                        ///todo заполения Hasmap uuid and id
                        if (ВытаскиваемЗначениеСтолбикаТабеляДва!=null  && ПолучениеЗначениеСтолбикUUID!=null){
                            Log.d(this.getClass().getName(), "      ХэшЛовимUUIDIDНазваниеСтолбика" +    ХэшЛовимUUIDIDНазваниеСтолбика.toString());

                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ

                            ХэшЛовимUUIDIDНазваниеСтолбика.clear();

                            /////TODO ЗАПОЛЯЕМ
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва ,Long.parseLong(ПолучениеЗначениеСтолбикUUID ));

                            //////todo обнуляем переменные
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;
                            ПолучениеЗначениеСтолбикUUID=null;
                            ///////



                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());


                        }

                        break;


                }


            }
            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ХэшЛовимUUIDIDНазваниеСтолбика;




    }

    // TODO  конец КОД СЛУШАТЕЛЬ ПРИ НАЖАТИИ НА ТАБЕЛЬ Создаем экземпляр TextWatcher:


































    // TODO КОД СЛУШАТЕЛЬ ПРИ НАЖАТИИ НА ТАБЕЛЬ Создаем экземпляр TextWatcher:
    private final TextWatcher СлушательПолученияДанных = new TextWatcher() {




        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            try{

            Log.d(this.getClass().getName(), "  beforeTextChanged  " + s.toString());
            ПолучениеЗначениеДоИзменения= "";

            ПолучениеЗначениеДоИзменения=s.toString();

            Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения  " +ПолучениеЗначениеДоИзменения);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

        }
        }









        //TODO второй метод

        public void onTextChanged(CharSequence s, int start, int before, int count) {


            // TODO: 08.02.2022

            try{

            Log.d(this.getClass().getName(),"  onTextChanged  " +s.toString());



// TODO: 08.02.2022  получаем само значениеит новое которое и надо ВСАТВКИТЬ

                    Log.d(this.getClass().getName(),"  afterTextChanged " +s.toString());

                    СамоЗначениеЯчейкиТабеля= null;

                    СамоЗначениеЯчейкиТабеля= s.toString();

                    Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);



                    //TODO ОЦЕНКА ЧТО С ДАННЫМИ

                    if (ПолучениеЗначениеДоИзменения.matches("(.*)[^0-9](.*)" )) {

                        Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения " +ПолучениеЗначениеДоИзменения);

                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                        //////
                        if (СамоЗначениеЯчейкиТабеля.matches("(.*)[0-9](.*)" )) {

                            СамоЗначениеЯчейкиТабеля = СамоЗначениеЯчейкиТабеля.replaceAll("[^0-9]", "");
                        }
                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);


                    }
                    Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения " +ПолучениеЗначениеДоИзменения);



                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля.length()" +  СамоЗначениеЯчейкиТабеля.length());





        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

        }

        }




        //Задаем действия для TextView после смены введенных в EditText символов:
        public void afterTextChanged(Editable s) {
            //TODO ДАННЫЙ КОД НЕПОСТРЕДСТВЕННО ЗАРУСКАЕТ ОБНОВЛЕНИЕ ЛОКАЛЬНОЕ С АКТИВТИИ

            int СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов = 0;

            boolean ФлагЗапускатьллкальноеОбновлениеИлинет=false;

            try{

            // TODO: 08.02.2022  записЬ НОВОГО ЗНАЧЕНИЕ В ЯЧЕЙКУ ПРОИЗВОДИМ С ЗАДЕРЖСКОЙ И С ПРОПУСКАИИ ДАННЫХ В МЕРЕМЕННОЙ ДО ЭТОГО
        /*    Observable observableаЗАПИСЫВАЕМнОВУЮвЕРИСЮдАННЫХвяЧЕЙКУсзАДЕРЖКОЙ=Observable.fromAction(new Action() {
                @Override
                public void run() throws Throwable {*/

            МетодГлавныйЛокальноеОбновлениеЯчейки(СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов, ФлагЗапускатьллкальноеОбновлениеИлинет);


                    Log.d(this.getClass().getName(), " МетодГлавныйЛокальноеОбновлениеЯчейки () Save.. СамоЗначениеЯчейкиТабеля" +  СамоЗначениеЯчейкиТабеля + " s "+s.toString());

          /*      }
            })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .delaySubscription(1,TimeUnit.SECONDS);

            observableаЗАПИСЫВАЕМнОВУЮвЕРИСЮдАННЫХвяЧЕЙКУсзАДЕРЖКОЙ.subscribe(System.out::println);*/


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

        }


        }//TODO НАДО ЛИ ЗАПУСКАТЬ ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ И ЛИ НЕТ

        private void МетодГлавныйЛокальноеОбновлениеЯчейки(int СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов, boolean ФлагЗапускатьллкальноеОбновлениеИлинет) {
            try{


      /*      Log.d(this.getClass().getName(),"  afterTextChanged " +s.toString());
            СамоЗначениеЯчейкиТабеля=new String();

            СамоЗначениеЯчейкиТабеля= s.toString();*/



            Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" +  СамоЗначениеЯчейкиТабеля);



////TODO запускаем запу запуси даных локально


            if (СамоЗначениеЯчейкиТабеля!=null) {
                if (СамоЗначениеЯчейкиТабеля.length()>0 ) {

                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля.length()" +  СамоЗначениеЯчейкиТабеля.length());
                    /////////todo получем значение И СТАВИМ УСЛОВИЕ НЕ БОЕЛЕЕ 24 ЧИЛСА КАК КАК В СУТКА ТОЛЬКО 24 ЧАСА


                    boolean ПереводимВЦифруТОлькоЕслиИзначальноНетБУквыВнутри=         СамоЗначениеЯчейкиТабеля.matches("(.*)[^0-9](.*)");
                    //////
                    if (ПереводимВЦифруТОлькоЕслиИзначальноНетБУквыВнутри==false) {
                        СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов = Integer.parseInt(СамоЗначениеЯчейкиТабеля);
                    }

                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов " + СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов);

                    if (СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов <= 24) {

                        ////TODO саомЗначение
                        // СамоЗначениеЯчейкиТабеля = s.toString();

                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);

                        ФлагЗапускатьллкальноеОбновлениеИлинет =true;


                        /////TODO  НЕ НАДЛО ЗАПУСКАТЬ ЛОКАЛЬНЕО ОБНОВЛЕНИЕ
                    } else {

                        ФлагЗапускатьллкальноеОбновлениеИлинет =false;
                        ////
                        // СамоЗначениеЯчейкиТабеля =new String();

                        Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_LONG);
                        ImageView cc = new ImageView(getBaseContext());
                        cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);//icon_dsu1_synchronisazia_dsu1_success
                        aa.setView(cc);
                        aa.show();
                        ///
                        Toast.makeText(getBaseContext(), "Нет сохранилось"+
                                "\n"+" (Значение не может быть более 24 (часы).) :" +СамоЗначениеЯчейкиТабеля, Toast.LENGTH_SHORT).show();
                    }




                    //////todo МОЖНО ЗАПУСКАТЬ ЛОКЛАЬНЕО ОБНЛВЛДЕНИ

                }else{
                    ////TODO саомЗначение
                    // СамоЗначениеЯчейкиТабеля = s.toString();

                    Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);

                    //////todo МОЖНО ЗАПУСКАТЬ ЛОКЛАЬНЕО ОБНОВЛЕНИЕ
                    ФлагЗапускатьллкальноеОбновлениеИлинет =true;
                }
            }


            Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" +СамоЗначениеЯчейкиТабеля + "  ФлагЗапускатьллкальноеОбновлениеИлинет " + ФлагЗапускатьллкальноеОбновлениеИлинет);



//TODO запускаем локальное обновдене если есть на это все условия

            if( ФлагЗапускатьллкальноеОбновлениеИлинет ==true){

///TODOобнуляем akfu

                ФлагЗапускатьллкальноеОбновлениеИлинет =false;



                Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов" + СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов);

                ////// TODO: 20.01.2021 полученое занчение пытаемся обрезать
                Log.d(this.getClass().getName(),"  СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);

                //TODO ограниваем количество символом не более 3 символом не сохраняет
                if (СамоЗначениеЯчейкиТабеля.length()>=3){
                    СамоЗначениеЯчейкиТабеля = СамоЗначениеЯчейкиТабеля.substring(0, 2);
                }


                Log.d(this.getClass().getName(),"  СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля + " ХэшЛовимUUIDIDНазваниеСтолбика.size() " +ХэшЛовимUUIDIDНазваниеСтолбика.values());

                ///todo получаем хэш с название стольика и uuid
                try {
                    Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбика.size()  " + ХэшЛовимUUIDIDНазваниеСтолбика.size());
                    ////todo проверка ПЕРЕД ЛОКАЛЬНЫЙ ОБНОВЛЕНИЕМ ПРОВЕРМЯЕМ ЕСЛИ ТАМО ЗНАЧЕНИЕ НЕ 0 ОН НЕ ЕСТЬ РАЗМЕР А ХЭША условия начало локального обоновления
                    //if (ХэшЛовимUUIDIDНазваниеСтолбика.size() > 0  && СамоЗначениеЯчейкиТабеля!=null && !СамоЗначениеЯчейкиТабеля.equalsIgnoreCase("0") && СамоЗначениеЯчейкиТабеля.length()<5){


                    ///TODO ОЦЕНИВАЕМ ЗНАЧЕНИЕ ТЕКУЩЕЕ ЗНАЧЕНИЕ ЯЧЕЙКИ СТОИТ ЛИ ЕГО ОБНОВЛЕЯТЬ ИЛИ НЕТ if (ХэшЛовимUUIDIDНазваниеСтолбика.size() > 0  && СамоЗначениеЯчейкиТабеля!=null
                    //                        && СамоЗначениеЯчейкиТабеля.length()<=2 && !СамоЗначениеЯчейкиТабеля.equalsIgnoreCase("0")){



                    Log.d(this.getClass().getName(), " Локальное Обновление ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика+
                            " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
// TODO: 03.06.2021  если вставдяем значене нул то всталвяем в него O

                    if (СамоЗначениеЯчейкиТабеля.length()==0 || СамоЗначениеЯчейкиТабеля==null) {
                        ///////
                        СамоЗначениеЯчейкиТабеля="0";
                    }




                    Log.d(this.getClass().getName(), " Локальное Обновление ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика+
                            " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);


// TODO: 03.06.2021  елси значение нул то мимы из него делаем ноль  ВАЖНО ТУТ УКАЗАНЫ УСЛОВИЯ ПО КОТОРЫМ ПРОИЗВХОДТИ ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ

                    ArrayList<Integer> ЛистСДопустипыЗначениямиДляЛокальноСохранения=new ArrayList();
                    ////
                    for (int ДниДопустимые=0;ДниДопустимые<=24;ДниДопустимые++) {
                        // TODO: 03.06.2021
                        ЛистСДопустипыЗначениямиДляЛокальноСохранения.add(ДниДопустимые);

                    }

                    Log.d(this.getClass().getName(), " ЛистСДопустипыЗначениямиДляЛокальноСохранения" + ЛистСДопустипыЗначениямиДляЛокальноСохранения.toString());

                    boolean РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет=     ЛистСДопустипыЗначениямиДляЛокальноСохранения.contains(Integer.parseInt(СамоЗначениеЯчейкиТабеля));

                    ///
                    Log.d(this.getClass().getName(), " РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет" + РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет);

                    /////
                    if (ХэшЛовимUUIDIDНазваниеСтолбика.size() ==1 && СамоЗначениеЯчейкиТабеля.length()>0
                            && СамоЗначениеЯчейкиТабеля.length() <= 2 && РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет==true) {


                        try {

                            ///todo контейнер
                            ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении = new     ContentValues();
                            // For each loop
                            Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                            // Iterator
                            Iterator iterator = ХэшЛовимUUIDIDНазваниеСтолбика.entrySet().iterator();

                            // TODO: 20.05.2021  начинаем обновлять только по одну запись если более одной пропускаем

                            if (ХэшЛовимUUIDIDНазваниеСтолбика.size()==1) {
                                ////
                                while (iterator.hasNext()) {
                                    ////
                                    // TODO: 21.05.2021  локальное обновление записи

                                    Map.Entry entry = (Map.Entry) iterator.next();
                                    ///
                                    System.out.println(String.format("(%s, %s)", entry.getKey(), entry.getValue()));


                                    String КлючПоляДляЛокальноОбновление=String.valueOf(entry.getKey());

                                    /////todo заполегния контейнера
                                    if (СамоЗначениеЯчейкиТабеля.length()>0) {
                                        КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put(КлючПоляДляЛокальноОбновление, СамоЗначениеЯчейкиТабеля);
                                        ////
                                        КлючПоляДляЛокальноОбновление=null;
                                        СамоЗначениеЯчейкиТабеля=null;

                                    }else if (СамоЗначениеЯчейкиТабеля.length()==0){
                                        КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put(КлючПоляДляЛокальноОбновление, 0);
                                        ////
                                        КлючПоляДляЛокальноОбновление=null;

                                    }
                                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" + СамоЗначениеЯчейкиТабеля+ " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " +КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.toString());



                                    //TODO заполение КОНТЕНЕР для локального обновления--дАТА оПЕРАЦИИ
                                    ////TODO ДАТА
                                    String СгенерированованныйДатаДляВставки=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put("date_update", СгенерированованныйДатаДляВставки);
                                    // TODO: 21.05.2021
                                    Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника=0L;
                                    Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДляЛокальногоОбволенияТабеля=new Class_GRUD_SQL_Operations(getApplicationContext());

                                    РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника=
                                            class_grud_sql_operationsПовышаемВерсиюДляЛокальногоОбволенияТабеля. new ChangesVesionData(getApplicationContext()).
                                                    МетодПовышаемВерсииCurrentTable("data_tabels", getApplicationContext(),Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());///  current_table    ///  localversionandroid_version
                                    Log.d(this.getClass().getName(), "  РезультатУвеличинаяВерсияДАныхЧата " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);

                                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);

                                    Log.d(this.getClass().getName(), "  РезультатУвеличинаяВерсияДАныхЧата " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);

                                    ///////////
                                    System.out.println(String.format("(%s, %s)", entry.getKey(), entry.getValue()));
                                    Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " + СамоЗначениеЯчейкиТабеля+
                                            "  РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника " +РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);

                                    String СодержимоеДляЛокальногоОбновленияВременный = String.valueOf(entry.getValue());

                                    Long СодержимоеДляЛокальногоОбновления = Long.parseLong(СодержимоеДляЛокальногоОбновленияВременный);

                                    Log.d(this.getClass().getName(), "  СодержимоеДляЛокальногоОбновления " + СодержимоеДляЛокальногоОбновления);


                                    if (СодержимоеДляЛокальногоОбновления > 0){


                                        String СодержимоеДляЛокальногоОбновленияФинал=String.valueOf(СодержимоеДляЛокальногоОбновления);

                                        Log.d(this.getClass().getName(), "КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.toString() +
                                                "СодержимоеДляЛокальногоОбновленияФинал " + СодержимоеДляЛокальногоОбновленияФинал);




                                        Log.d(this.getClass().getName(), "  КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.valueSet() + "\n" +
                                                "  String.valueOf(entry.getValue()) " + entry.getValue());



                                        ///TODO ЗАПУСКАЕМ  МЕТОД ЛОКАЛЬНОГО ОБНОВЛЕНИЯ ВНУТИ ТАБЕЛЯ с самого активти
                                        try {

                                            Long РезультатЛокальногоОбновлениеНаТабеле= null;


                                            // TODO: 27.05.2021

                                            if (СодержимоеДляЛокальногоОбновленияФинал.length()>0 && КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.size()>0 ) {


                                                // TODO: 13.01.2022  ГЛАВНЫЙ МЕТОД ЗАПУСКА ЛОКАЛЬНОГО ОБНОВЛЕНИЯ

                                                РезультатЛокальногоОбновлениеНаТабеле =
                                                        МетодЛокальногоОбновлениеЧерезКликвТабеле(КонтейнерЗаполненияДаннымиПриЛокальномОбновлении, СодержимоеДляЛокальногоОбновленияФинал);
                                            }


                                            Log.d(this.getClass().getName(), "  РезультатЛокальногоОбновлениеНаТабеле" + РезультатЛокальногоОбновлениеНаТабеле);
                                            ////////
                                            // TODO: 21.05.2021 Обнуляем после успешной локально вставки

                                            if (РезультатЛокальногоОбновлениеНаТабеле>0) {

                                                КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();


                                                СодержимоеДляЛокальногоОбновленияФинал=null;

                                                ПолучениеЗначениеДоИзменения= null;

                                                СамоЗначениеЯчейкиТабеля=null;



                                                // TODO: 08.09.2021  ПОСЛЕ УСПЕШНОЙ  ВСТАВКИ  ДЕЛАЕМ ВИБРАЦИЮ


                                                // TODO: 01.07.2021  после локальной обнолвения поробуем вотрунть синхронизацию локальную  в фоне и порстмортрим что будет


                                                Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля;
                                                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                                                //////
                                                classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля=new Class_GRUD_SQL_Operations(getApplicationContext());


                                                classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
                                                ///
                                                classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                                                ///
                                                ///
                                                classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put(" " +
                                                        "ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                                                ///







                                                ///TODO РЕЗУЛЬТА изменения версии данных
                                                Integer        Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=
                                                        (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                                new ChangesVesionData(getApplicationContext()).
                                                                changesvesiondata(classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                                                                        ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//
                                                Log.d(getApplicationContext().getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации "
                                                        +Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы );
                                                ////











                                            }else {
                                                ///

                                                // TODO: 24.09.2021

                                                Toast.makeText(getApplicationContext(), " Ошибка  в добавление данных !!! " , Toast.LENGTH_SHORT).show();

                                            }






                                            break;
                                            /////////
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),

                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }







                                        //////


                                    }





                                }//todo end while()
/////////
                            }
                            Log.d(getApplicationContext().getClass().getName(), "  Конец ЛОкальному ОБновлению  внутри табеля  " );

                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }///TODO END IF  ПЕРЕД ЛОКАЛЬЫМ ОБНОВЛЕНИЕМ



                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                ////todo calabel end

            }





        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

        }
        }
    };
    ///////КОНЕЦ  СОЗДАГНИЕ СЛУШАТЕЛЯ ДЛЯ ПЕРЕДАЧИ ЕГО В КОД ОБНОВЛЕНИЯ ПОЛУЧАЕМ ID  СТРОЧКИ И ДЛЯ ПРОВЕРКИ ЗНАЧЕНИЕ ЯЧЕЙКИ














    ///TODO  удаление ОБРАБОТКА КЛИКА ПО ФИО
    View.OnClickListener СлушательИнформацияОСотрудника = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /////
            SQLiteCursor Курсор_ЗагружаемИнформациюПоФИО = null;
            ////
            //

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            SQLiteCursor    Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО=null;/////

            //////
            Class_GRUD_SQL_Operations class_grud_sql_operationsСлушательИнформацияОСотрудника=new Class_GRUD_SQL_Operations(getApplicationContext());

////////////////
            try{
                //
                if (МыУжеВКодеУденияСотрудника==false) {

                    TextView ФИОДляУдаление = (TextView) v;

                    Log.d(this.getClass().getName(), " v " + v.getTag() + " ФИОДляУдаление.getText() " + ФИОДляУдаление.getText() +
                            "  ФИОДляУдаление.getTag() " +ФИОДляУдаление.getTag());


                    //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                    ((TextView) v).setBackgroundColor(Color.GRAY);

                    ///

                    class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
                    ///////
                    class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","fio");//name ,BirthDate ,snils
                    //
                    class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=? ");
                    ///"_id > ?   AND _id< ?"

                    Long ФИОЧТОБЫПОКАЗАТЬВтруриТАбеля=Long.parseLong(ФИОДляУдаление.getTag().toString());
                    //
                    Log.d(this.getClass().getName(), " ФИОЧТОБЫПОКАЗАТЬВтруриТАбеля" +ФИОЧТОБЫПОКАЗАТЬВтруриТАбеля);
                    //////
                    class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ФИОЧТОБЫПОКАЗАТЬВтруриТАбеля);
                    ///
                    //class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
          /*          class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
                    ////TODO другие поля

                    ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                    ////
                    //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                    ////
                    class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update desc");
                    ////
                    /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                    ////

                    // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                    Курсор_ЗагружаемИнформациюПоФИО=null;

                    Курсор_ЗагружаемИнформациюПоФИО= (SQLiteCursor)  class_grud_sql_operationsСлушательИнформацияОСотрудника.
                            new GetData(getApplicationContext()).getdata(class_grud_sql_operationsСлушательИнформацияОСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                            ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаемИнформациюПоФИО);

//TODO  ОЧИЩАЕМ ПАМТЬ Результат Курсора
                    if (Курсор_ЗагружаемИнформациюПоФИО.getCount()>0){
                        /////
                        Курсор_ЗагружаемИнформациюПоФИО.moveToFirst();
                        /////
                        Long UUIDИзТаблицыФИОфинал= Курсор_ЗагружаемИнформациюПоФИО.getLong(0);
                        ////

//////////
                        Log.d(this.getClass().getName(), " UUIDИзТаблицыФИОфинал " + UUIDИзТаблицыФИОфинал);
                        // TODO: 27.09.2021 ВТОРАЯ ЧАСТЬ НАХОДИМ САМУ ИНОРМАЦИЯ

                        Class_GRUD_SQL_Operations     class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО=new Class_GRUD_SQL_Operations(getApplicationContext());
                        ///

                        class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","fio");
                        ///////
                        class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name ,BirthDate ,snils");//name ,BirthDate ,snils
                        //
                        class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=? ");
                        ///"_id > ?   AND _id< ?"

                        //
                        Log.d(this.getClass().getName(), " UUIDИзТаблицыФИО" +UUIDИзТаблицыФИОфинал);
                        //////
                        class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",UUIDИзТаблицыФИОфинал);
                        ///
                        /// class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                        ///
          /*          class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
                        ////TODO другие поля

                        ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                        ////
                        //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                        ////
                        class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update desc");
                        ////
                        /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");

                        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                        Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО=null;

                        Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО= (SQLiteCursor)  class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО.
                                new GetData(getApplicationContext()).getdata(class_grud_sql_operationsСлушательИнформацияОСотрудникаУжеПолучаемДанныеИзФИО.
                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                                ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                        Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО);

                    }



                    //TODO  ОЧИЩАЕМ ПАМТЬ Результат Курсора
                    if (Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.getCount()>0){
                        /////
                        Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.moveToFirst();
                        /////
                        String ФИОИнфо= Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.getString(0);
                        ////
                        String ДеньРОжденияИНФО= Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.getString(1);
                        ///
                        String СНИЛСИНфо= Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.getString(2);
//////////
                        СообщениеИнформацияОФИО("Информация о сотруднике",  "ФИО: " +ФИОИнфо+
                                "\n"+"День рождения: " +ДеньРОжденияИНФО+
                                "\n"+"СНИЛС: " +СНИЛСИНфо+"\n");

                    }
                }
                Курсор_ЗагружаемИнформациюПоФИО.close();
                ///

                Курсор_ЗагружаемИнформациюПоФИОПолучениеДанныхИзФИО.close();

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }};









    ///todo  конец метода удаления третий обработчки нажатия















    //////
    //////TODO локального обнвлени с Активити в базу
    private Long МетодЛокальногоОбновлениеЧерезКликвТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении ,String ПолучениеЗначениеСтолбикUUID)
            throws InterruptedException, ExecutionException,
            NoSuchMethodException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, JSONException {
        String таблицаДляЛокальногоОбонвления="data_tabels";

        Long РезультатЛокальногоОбновления = null;

        try {

            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +
                    " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении  " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении);
            ///TOdo универсальное значение или uuid или id КАКОЕ НЕ NULL
            String УниверсальныйИнлификаторЛокальногоОбновлениеиIDиUUID = "";


////TODO сам метод запуска обновления
            if (КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.size() > 0) {///если в контенер заполнилься то начинаем обновление


                //TODO принимеем решение через чего БУДЕМ ОБНОВЛЯТЬ ЧЕРЕЗ ID ИЛИ UUID, ПРИОРИТЕТ ID ,НО ЕЛСИ ЕГО НЕТ ТО UUID
                /////TODO  локальное ОБНОВЛЕНИЕ ЧЕРЕЗ UUID ПОЛЕ
                if (ПолучениеЗначениеСтолбикUUID != null) {

                    Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);


                    boolean РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет =
                            МетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет(ПолучениеЗначениеСтолбикUUID, "uuid");

                    ////
                    Log.d(this.getClass().getName(), " РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет " + РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет +
                            " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении);


                    ///todo ПРОИЗВОДИМ ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ ,ЕСЛИ В СТАТУСЕ ОТПРАВИК НЕ СТОИТЬ СТАТУС УДАЛЕННЫЙ
                    //////todo производим локальное обновлени когда статус false --, это значит статуса Уданненый нет

                    try {
                        // TODO: 22.03.2021  вставка только если false запись не Имеет статус Удаленная
                        if (РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет == false) {
                            ///TODO ТОЛЬКО ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ НА ТАБЕЛЕ В АКТИВИТИ
                            РезультатЛокальногоОбновления = new Class_MODEL_synchronized(getApplicationContext()).
                                    МетодЛокальноеОбновлениеВТабеле(КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                            ПолучениеЗначениеСтолбикUUID,
                                            getApplicationContext(),таблицаДляЛокальногоОбонвления);
                            Log.d(this.getClass().getName(), " РезультатЛокальногоОбновления " + РезультатЛокальногоОбновления);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }


                }


                /// //TODO РЕЗУЛЬТАТ ФИНАЛЬНЫЙ ПОСЛЕ ЛОКАЛЬНОГО ОБНОВЛЕНИЕ ЕСОИ ВСЕ ХОРОШО ТО ОБНУЛЯЕМ ПЕРЕМЕННЫЕ И НЕТ СООБЩЕНИЙ

                Log.d(this.getClass().getName(), " РезультатЛокальногоОбновления" + РезультатЛокальногоОбновления);

                //TODO ЕЛИ РЕЗУЛЬТАТ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ СРАБОТАЛ ТО И ПОКАЗЫВАЕМ ИЗМЕННЕЯ ЧАСЫ
                if (РезультатЛокальногоОбновления > 0 ) {
                    ///todo после заполнения табелями обнуляем куросры
                    ////////// TODO ПОДСЧЁТ ЧАСОВ ПОСЛЕ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ
                    ОбщееКоличествоЛюдейВТабелеТекущем = new Class_MODEL_synchronized(getApplicationContext()).
                            МетодПосчётаЧасовСотрудниковВТабеле(context,
                                    UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников, МЕсяцДляКурсораТабелейДЛяПермещения, ГодДляКурсораТабелей);
                    // TODO: 07.05.2021 обнуляем UUID после созданеия подтчета часов
                    Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем  +
                            "  ТекущееПоложенияВТабелеДляСкрола "  +ТекущееПоложенияВТабелеДляСкрола);

                    //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                    //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                    ОбщееКоличествоЧасовСотрудникаВТабелеСотудников.setText(" (" + " " + ОбщееКоличествоЛюдейВТабелеТекущем + "/часы)  #"+ТекущееПоложенияВТабелеДляСкрола+"");


                    // TODO: 25.09.2021
                    Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем  +
                            "  ТекущееПоложенияВТабелеДляСкрола "  +ТекущееПоложенияВТабелеДляСкрола);






                    Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID
                            + " ХэшЛовимUUIDIDНазваниеСтолбика.values() " + ХэшЛовимUUIDIDНазваниеСтолбика.values() + " СамоЗначениеЯчейкиТабеля " + СамоЗначениеЯчейкиТабеля);


                    ///todo ДОБАЯЛЕМ ПОЛСЕ УСПЕШНОЙ ЛОКАЛЬНОГО ОБНОЛЕНИЕ

                    //todo после ЛОКАЛЬНОГО ОБНОВЛЕНИЯ ОБНУЛЯЕМ
                    Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID
                            + " ХэшЛовимUUIDIDНазваниеСтолбика.values() " + ХэшЛовимUUIDIDНазваниеСтолбика.values() + " СамоЗначениеЯчейкиТабеля " + СамоЗначениеЯчейкиТабеля);
                    ////TODO сообщаем пользователю при успепешном обньвении
                /*Toast.makeText(this, " Успех локальное обновление  Табеля (новое значение:) " +СамоЗначениеЯчейкиТабеля +"\n"+
                        " Описание : "+ХэшЛовимUUIDIDНазваниеСтолбика.keySet()+" / " +ХэшЛовимUUIDIDНазваниеСтолбика.values() , Toast.LENGTH_SHORT).show();*/
                    /*Toast.makeText(this, " Успех обновление  Табеля"+"\n"+
                            "(новое значение:) " +СамоЗначениеЯчейкиТабеля+"\n"+
                            " кол об/вс: "+PUBLIC_CONTENT.КоличествоУспешныхОбновлений, Toast.LENGTH_SHORT).show()*/
                    ////TODO ПОСЛЕ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ ЗАПУСКАЕМ СИНХРОНИЗАЦИЮ ДАННЫХ




                    /////TODO ОБНУЛЯЕМ ЗНАЧЕНИЕ ID AND UUID ЧТОБЫ НЕ БЫЛО ПОВТОРОНОГО ОБНОЛЕНИЕ НЕ СВОЕГО ХОЗЯИНА UUID
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();


                    СамоЗначениеЯчейкиТабеля = null;

                    ПолучениеЗначениеДоИзменения = null;
                    ////todo УДАЛЕНИЕ ИЗ ПАМЯТИ КУРСОРА
                    ////todo удалем из памяти

                    ///TODO  ОБНУЛЯЕМ ПОСЛЕ УСПЕШНОЕ ОБНОВЛЕНОЕ

                    // ХэшЛовимUUIDIDНазваниеСтолбика.clear();


                    ScrollСамогоТабеля.requestLayout();
                    // TODO: 31.01.2022

                    ГлавныйКонтейнерТабель.requestLayout();


                    ГлавныйКонтейнерТабель.invalidate();

                    // TODO: 31.01.2022
                    ScrollСамогоТабеля.invalidate();



                    ////todo УДАЛЕНИЕ ИЗ ПАМЯТИ КУРСОРА
                    Toast aa = Toast.makeText(context, "OPEN", Toast.LENGTH_SHORT);
                    ImageView cc = new ImageView(getBaseContext());
                    // cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_success);
                    aa.setView(cc);
                    aa.show();



//////////todo ошибка нет локального обновления
                } else {
                    //TODO сообщаем пользователю об не успешном локальном обновленеии на активти
                    /*  Toast.makeText(this, "Ошибка локальное обновление Табеля " +ХэшЛовимUUIDIDНазваниеСтолбика.size()  , Toast.LENGTH_SHORT).show();*/



            /*                Toast aa = Toast.makeText(КонтекстОдногоСотрудикаВТабеле, "OPEN", Toast.LENGTH_SHORT);
                            ImageView cc = new ImageView(getBaseContext());
                            cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);
                            aa.setView(cc);
                            aa.show();*/

                    Toast.makeText(getApplicationContext(), " Ошибка обновление ячейки не произошло !!!!! " , Toast.LENGTH_SHORT).show();

                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                    СамоЗначениеЯчейкиТабеля = null;
                    ПолучениеЗначениеДоИзменения = null;
                    ХэшЛовимUUIDIDНазваниеСтолбика.clear();

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатЛокальногоОбновления;

    }




    //todo метод орпреедгния статутс записи обнолвемой удленнва я или нет
    private boolean МетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет( String ПолучениеЗначениеСтолбикUUID,String ИндификаторСтатус) {

        boolean Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=false;
        //////
        SQLiteCursor Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет = null;
        ///////
        Class_GRUD_SQL_Operations class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет=new Class_GRUD_SQL_Operations(getApplicationContext());

        try{
            Log.d(this.getClass().getName(), "  МетодЗапускаЛокальногоОбновлениеЧерезКликвТабеле : ");


//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            ///
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
            ///////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","status_send");
            //
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",""+ИндификаторСтатус+"=? AND status_send=?");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолучениеЗначениеСтолбикUUID);
            ///
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            ///
/*                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update desc");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=null;

            ////

            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  +Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет);










            /////////////TODO результат
            if (Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет.getCount() > 0) {
                /////
                // TODO: 08.09.2021  полчжительный

                Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=true;

            }else{

                // TODO: 08.09.2021  отрицательный

                Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=false;
            }

///TODO ЗАПУСКАЕМ  ПуллПамяти
            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет.close();


            //////////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        /////
        return Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет;
    }





    //TODO метод получени месяа для записи в одну колонку

    protected int  МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
//
        Calendar calendar =null;
        //////////
        try{
            ///
            String[] ДелимМЕсяцИгод =ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");

            System.out.println( " " + ДелимМЕсяцИгод [0] + " " +ДелимМЕсяцИгод [1]);

            SimpleDateFormat formatмесяц = new SimpleDateFormat("MMMM");

            SimpleDateFormat formatгод = new SimpleDateFormat("yyyy");

            Date date = formatмесяц.parse(ДелимМЕсяцИгод [0]);

            calendar = Calendar.getInstance(new Locale("ru"));

            calendar.setTime(date);

            System.out.println(calendar.get(Calendar.YEAR));

            System.out.println(calendar.get(Calendar.MONTH)+1);

            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

            System.out.println(new SimpleDateFormat("MMMM").format(calendar.getTime()));


            //////////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 08.09.2021   resultat
        return   calendar.get(Calendar.MONTH)+1;
    }




    //TODO метод который обрабатывает дни
    String МетодСопоставлениеДнейТАбеляСКалендарем(String ДатаДляКадендаря){

        Calendar c = GregorianCalendar.getInstance();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyy");

        return null;
    }











    ///todo Метод Который Содаенм НАзвание дней недели сокращенно Пн,01,ВТ,03
    private  void МетодСозданиеМесяцыСокращенно() throws ParseException {
        try {
            int МЕсяцДЛяПоказатаВТАбле=   МетодПоказатьМесяцДляЗАписивОднуКолонку(МесяцТабеляФинал);
            int ГодДЛяПоказатаВТАбле=    МетодПоказатьГодДляЗАписивОднуКолонку(МесяцТабеляФинал);
            Log.d(this.getClass().getName(), " МЕсяцДЛяПоказатаВТАбле " +МЕсяцДЛяПоказатаВТАбле +" ГодДЛяПоказатаВТАбле " +ГодДЛяПоказатаВТАбле);
            int ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодДЛяПоказатаВТАбле,МЕсяцДЛяПоказатаВТАбле);
            SimpleDateFormat СозданияВычисляемВыходные=null;
            ///////TODO сам цикл который заполняет месяцами
            for (int ИндексДней=1;ИндексДней<ПолученоеКоличествоДнейНаКонкретныйМЕсяц+1;ИндексДней++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }else {
                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }
                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (ГодДЛяПоказатаВТАбле+"-"+МЕсяцДЛяПоказатаВТАбле+"-"+ИндексДней );
                String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );
                Integer РезультатДатыДляКонктетногоТабеляТольокЧисло = Integer.parseInt(new SimpleDateFormat("dd", new Locale("ru")).format(ДатаПосикаВыходныеДней ));
                Log.d(this.getClass().getName()," РезультатДатыДляКонктетногоТабеляТолькоЗанвание " +РезультатДатыДляКонктетногоТабеляТолькоЗанвание+
                        " РезультатДатыДляКонктетногоТабеляТольокЧисло " +РезультатДатыДляКонктетногоТабеляТольокЧисло);
                StringBuffer БуферРезультатСокращенноВставкиВТабель=new StringBuffer();
                // TODO: 29.01.2022 append date for tabels
                БуферРезультатСокращенноВставкиВТабель.append(РезультатДатыДляКонктетногоТабеляТолькоЗанвание).append(" ,").append(РезультатДатыДляКонктетногоТабеляТольокЧисло);
                Log.d(this.getClass().getName()," РезультатСокращенноВставкиВТабель " +БуферРезультатСокращенноВставкиВТабель.toString());
                //TODO ДНИ НЕДЕЛИ С ЗАГЛАВНОЙ БУКВЫ
                String  СокращенныйДниМесяцаВТабеле=   БуферРезультатСокращенноВставкиВТабель.substring(0,1).toUpperCase()
                        +БуферРезультатСокращенноВставкиВТабель.substring(1,БуферРезультатСокращенноВставкиВТабель.length()).toLowerCase();
                ///////
                Log.d(this.getClass().getName()," ИндексДней " +ИндексДней+
                        " СокращенныйДниМесяцаВТабеле " +СокращенныйДниМесяцаВТабеле);
                //TODO ЗАПОЛЕНЯЕМ ПОЛУЧЕННЫЕ ДНИ НЕДЕЛИ ЧТОБЫ ВСТАВИИТЬ ЕГО В АКТИВТИ
                ХЭШНазваниеДнейНедели.put(ИндексДней, СокращенныйДниМесяцаВТабеле);
                Log.d(this.getClass().getName()," ХЭШНазваниеДнейНедели " +ХЭШНазваниеДнейНедели.toString()+
                        " ХЭШНазваниеДнейНедели.size()  " +ХЭШНазваниеДнейНедели.size());
            }
            ///todo вычисляем сколько дней в месяце текущем
            КоличествоДнейвЗагружаемойМесяце=МетодПолучениеКоличествоДнейвЗагружаемомМесяце(МЕсяцДЛяПоказатаВТАбле ,ГодДЛяПоказатаВТАбле);
            Log.d(this.getClass().getName()," КоличествоДнейвЗагружаемойМесяце " +КоличествоДнейвЗагружаемойМесяце);
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /**
     *
     */
    private  int МетодПолучениеСколькоДнейВКонкретномМесяце(int Год,int Месяц) {
        Date date = null;
        int КонктетныйМесяцВВидеЦифры;
        Calendar cal;
        cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.MONTH));
        // Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(Год, Месяц, 0);
        // Get the number of days in that month
        int КоличествоДнейНаВыбраныйМесяц = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

        return  КоличествоДнейНаВыбраныйМесяц;
    }






////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГОД НОВЫЙ




    private int МетодПоказатьМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        calendar.setTime(date );
        int month = calendar.get(Calendar.MONTH) + 1;
        return   month;
    }

    private int  МетодПоказатьГодДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        calendar.setTime(date );
        int year = calendar.get(Calendar.YEAR);
        return   year ;
    }











    //////TODO СООБЩЕНИЕ ДЛЯ ВЫБОРА ЧТО ДЕЛАТЬ СОЗЖАВАТЬ НОВОГО ПОЛЬЗОВАТЕЛЕЯ ИЛИ ПОДОБРАТЬ УЖЕ СОЗДАННОГО СОТРУДНИКА
    @UiThread
    public void СообщениеДляВыбораСозданиеНовогоСотрудникаИлиЕгоПодобратьДляТабеля(String ШабкаДиалога,boolean Флаг,String UUIDТабеляФинал ,
                                                                                   String МесяцТабеляФинал, String ДепартаментТабеляФинал ) {//  ////MESSAGEBOX ДЛЯ ИНТЕРНЕТА  ПОДКЛЮЧЕНИЕ не успешное нет связи с итрнтнето и/или файлом json
        try{
            //super.MessageBoxs(ШабкаДиалога, СообщениеДиалога, Выбор);
            int ЦветЗначек;
            String ТекстПродолжениеОбновление;
            final AlertDialog DialogBox = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setPositiveButton( "Новый Сотрудник ?", null)
                    .setNegativeButton("Выбрать Сотрудника ?", null)
                    .setNeutralButton("Закрыть", null)
                    .setIcon(R.drawable.icon_dsu1_new_customer2)
                    .show();

            ///TODO КНОПКА выбор  ДОБАВЛЕНИЕ УЩЕ СУЩЕСТВУЮЩЕГО СОТРУДНИКА
            final Button КнопкаПодобратьНовогоСотрудникаДляТАбеля = DialogBox.getButton(AlertDialog.BUTTON_NEGATIVE);
            DialogBox.getButton(Dialog.BUTTON_NEGATIVE).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаПодобратьНовогоСотрудникаДляТАбеля.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка закрыть");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //TODO ПОДОБРАТЬ СОТРУДНИКА СУЩЕСТЮЩЕГО
                    Log.d(this.getClass().getName(), " " );

                    Intent Интент_ЗапускПодобратьУжеСуществующегоСотрудника = new Intent();

                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.setClass(getApplication(), MainActivity_Find_Customers.class);

                    // передача объекта с ключом "hello" и значением "Hello World"
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ДепартаментТабеляФинал", ДепартаментТабеляФинал);
                    Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + ДепартаментТабеляФинал);
                    //////////todo разница если новый сотрудник и ранее созданный
                    if (UUIDТабеляФинал!=null){
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляФинал);
                        Log.d(this.getClass().getName(), "UUIDТабеляФинал" + UUIDТабеляФинал);
                    }else if (UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя!=null){
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
                        Log.d(this.getClass().getName(), " UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя "
                                + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
                    }else{
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляПослеУспешногоСозданиеСотрудника);
                        Log.d(this.getClass().getName(), "UUIDТабеляПослеУспешногоСозданиеСотрудника" + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                    }

                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
                    Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
                    Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
                    ////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("МЕсяцДляКурсораТабелей", МЕсяцДляКурсораТабелей);
                    Log.d(this.getClass().getName(), "  МЕсяцДляКурсораТабелей  " + МЕсяцДляКурсораТабелей);
                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ГодДляКурсораТабелей", ГодДляКурсораТабелей);
                    Log.d(this.getClass().getName(), "ГодДляКурсораТабелей " + ГодДляКурсораТабелей);


                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    /////
                    startActivity(Интент_ЗапускПодобратьУжеСуществующегоСотрудника);


                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )
                }
            });








/////TODO КНОПКА СОЗДАНИЯ НОВОГО СОТРУДКНИКА
            final Button КнопкаСозданиеНовогоСотрудникаДляТАбеля = DialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
            DialogBox.getButton(Dialog.BUTTON_POSITIVE).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_POSITIVE).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаСозданиеНовогоСотрудникаДляТАбеля.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка добавить нового ");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //////TODO В ДАННОМ КОДЕ МЫ ДОБАВЛЯЕМ УЖЕ СУЩЕСТВУЮЩЕГО СОТРУДКА В ТАБЕЛЬ НОВОГО СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ
                    //////TODO В ДАННОМ КОДЕ МЫ СОЗДАЕМ НОВОГО СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ

                    ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
                    Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
                    Интент_ЗапускСозданиеНовогоСотрудника.setClass(getApplication(), MainActivity_New_Cusomers.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                    // передача объекта с ключом "hello" и значением "Hello World"
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляФинал", ДепартаментТабеляФинал);
                    Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + ДепартаментТабеляФинал);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляФинал);
                    Log.d(this.getClass().getName(), "UUIDТабеляФинал" + UUIDТабеляФинал);
                    /////
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
                    Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
                    /////
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
                    Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);

                    Интент_ЗапускСозданиеНовогоСотрудника.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


                    /////
                    startActivity(Интент_ЗапускСозданиеНовогоСотрудника);


                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )
                }
            });


            ///TODO КНОПКА ЗАКРЫТИЕ СООБЩЕНИЯ
            final Button КнопкаЗакрытьСообщкение = DialogBox.getButton(AlertDialog.BUTTON_NEUTRAL);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTextColor(Color.GRAY);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаЗакрытьСообщкение.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка добавить уже сущетсвующего сотрудника ");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //////TODO В ДАННОМ КОДЕ МЫ добавлем существующего СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ
                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )

                }
            });


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




    private int  МетодПолучениниеМесяцДляКурсора(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
        int month = calendar.get(Calendar.MONTH) + 1;
        return   month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеГодДляКурсора(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return   year ;
    }





    public String ГлавнаяДатаИВремяДляТабеля() {
        Date Дата = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
        //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Log.d(this.getClass().getName(), " ГЛАВНАЯ ДАТА ПРОГРАММЫ ДСУ-1 : " + dateFormat.format(Дата));
        return dateFormat.format(Дата);
    }
    ///todo ОПРЕДЕЛЯЕМ КОЛИЧЕСТВО ДНЕЙ ЗАГРУЖАЕМОЙ МЕСЯЦ
    protected int МетодПолучениеКоличествоДнейвЗагружаемомМесяце(int Месяц ,int месяцДляСокращенно) {
        Date date = null;
        int КонктетныйМесяцВВидеЦифры;
        try {
            String МесяцПлюсНоль=String.valueOf(Месяц);
            if (МесяцПлюсНоль.length() == 1){
                МесяцПлюсНоль="0"+МесяцПлюсНоль;
            }
            date = new SimpleDateFormat("MMyyyy",new Locale("rus")).parse(МесяцПлюсНоль+ месяцДляСокращенно);
            System.out.println(" date " +date);

        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        Calendar  cal = Calendar.getInstance();
        cal.setTime(date);
        КонктетныйМесяцВВидеЦифры=cal.get(Calendar.MONTH);
        System.out.println(cal.get(Calendar.MONTH));
        // Create a calendar object and set year and month
        // Get the number of days in that month
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

        return  daysInMonth;
    }
















    ///todo сообщение информация о ФИО
    @UiThread
    protected void СообщениеИнформацияОФИО(String ШабкаДиалога,  String СообщениеДиалога) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(R.drawable.icon_dsu1_info_customer)
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


                НазваниеДанныхВТабелеФИО.setBackgroundColor(Color.parseColor("#F5FFFA"));


            }
        });
    }




    ///TODOVTNJL ПЕРЕХОДА НА АКТИВТИ МЕТКИ ТАБЕЛЯ

    private void МетодПереходаНаМеткиТабеля(HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель) {
        Intent IntentЗапускаемСправочникДЛяСловТабеля=new Intent();
        try{
            IntentЗапускаемСправочникДЛяСловТабеля.setClass(context, MainActivity_Metki_Tabel.class); // Т
            Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель " +ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("HashMap", ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ДепартаментТабеляПослеПодбора", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляПослеПодбора", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляПослеПодбораУниверсальный",  UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("МесяцТабеляПослеПодбора", МесяцТабеляФинал);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора",ПолноеИмяТабеляПослеСозданиеНовогоСотрудника );
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Log.d(this.getClass().getName(), "  UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("РодительскийUUDТаблицыТабель", UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляКнопкаBACKУниверсальный", UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            Log.d(  this.getClass().getName(), " ПолноеНазваниеЗагруженногТАбеля" +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            ///////TODO отправляем данные через интент в АКТИВТИ  МЕТКА ТАБЕЛЯ
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ДепартаментТабеляИзВсехСотрудниковВТАбеле", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле",UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле", МесяцТабеляФинал);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("IDЧьиДанныеДляСотрудников",IDЧьиДанныеДляСотрудников);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ГодФиналИзВсехСотрудниковВТАбеле",    ГодТабеляПослеПодбораУниверсальный);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ЦифровоеИмяНовгоТабеля",    ЦифровоеИмяНовгоТабеля );
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            // TODO: 20.10.2021 new Значение при движение по кругу месяц сохраняем
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            IntentЗапускаемСправочникДЛяСловТабеля.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////todo запускаем активти
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtras(data);
            startActivity( IntentЗапускаемСправочникДЛяСловТабеля);
            ХэшЛовимUUIDIDНазваниеСтолбика=null;
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСотрудникаИзТабеля(String ШабкаДиалога,  String СообщениеДиалога,boolean Статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        int Значек;
        if (Статус){
            Значек  =R.drawable.icon_dsu1_tabel_info;
        }else{
            Значек  =R.drawable.icon_dsu1_delete_customer;
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

            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");
                //TODO  второе действие заполенние контентом  в табеля в TableLyзаполения табеля из базы через элемент TableLauy
                ///// МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля( МесяцТабеляФинал);





                ///TODO запускаем возврат на предыдущее активывти после успешного удаление сотрудника
                МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника();


                //todo
            }


        });
    }
////todo конец фильаного сообщения о удалени самого табеля


    void МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника() {
        try{
            ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
            Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
            Интент_ЗапускСозданиеНовогоСотрудника.setClass(context, MainActivity_List_Peoples.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляФинал", НазваниеЗагруженногТАбеля);
            Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + НазваниеЗагруженногТАбеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
            Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
            Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляГлавыйРодительскгоТабеля", UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Log.d(this.getClass().getName(), "  UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("РодительскийUUDТаблицыТабель", UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ЦифровоеИмяНовгоТабеляSingle", ЦифровоеИмяНовгоТабеля);
            Log.d(this.getClass().getName(), "  ЦифровоеИмяНовгоТабеля " + ЦифровоеИмяНовгоТабеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Log.d(this.getClass().getName(), "  ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Интент_ЗапускСозданиеНовогоСотрудника.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtras(data);
            startActivity(Интент_ЗапускСозданиеНовогоСотрудника);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }










































    private void МетодСвайпВпередПоДАнным() {
        try {
            Log.d(this.getClass().getName(), "ЗАПУСК  Свайп  moveLeft  СПРАВО НА ЛЕВО    onFling   " + new Date());
            CountDownLatch countDownLatchДляДвиженияПоДАннымВперед=new CountDownLatch(1);
            if (ОбщееКоличествоСОтрудниковДляСкролаПотабелю>1) {
                Log.d(  getApplicationContext().getClass().getName(), " ТекущееПоложенияВТабелеДляСкрола  "+ТекущееПоложенияВТабелеДляСкрола);
                if (ТекущееПоложенияВТабелеДляСкрола == ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {
                    ТекущееПоложенияВТабелеДляСкрола = 1;
                } else {
                    if (ТекущееПоложенияВТабелеДляСкрола <= ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {
                        ТекущееПоложенияВТабелеДляСкрола++;
                    }
                }
                Log.d(  getApplicationContext().getClass().getName(), " ТекущееПоложенияВТабелеДляСкрола  "+ТекущееПоложенияВТабелеДляСкрола);
                countDownLatchДляДвиженияПоДАннымВперед.countDown();
                countDownLatchДляДвиженияПоДАннымВперед.await();
                if (ТекущееПоложенияВТабелеДляСкрола <= ОбщееКоличествоСОтрудниковДляСкролаПотабелю) {
                    Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
                    Интент_ЗапускСозданиеНовогоСотрудника.setClass(getApplicationContext(), MainActivity_Tabel_Only_People.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                    Интент_ЗапускСозданиеНовогоСотрудника.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("setIDСамогоТабеляВКотромМыНаходились", ТекущееПоложенияВТабелеДляСкрола);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МЕсяцДляКурсораТабелей", МЕсяцДляКурсораТабелейДЛяПермещения);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПередаемСозданнуюДатуНовогоТабеля", МесяцТабеляФинал);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолноеНазваниеТабеляФинал", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПроизошелЛиСфайпПоДаннымСингТабеля", true);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
                    Log.d(getPackageName().getClass().getName(), "swipe  переход через сфайп  ТекущееПоложенияВТабелеДляСкрола" +ТекущееПоложенияВТабелеДляСкрола );
                    ХэшЛовимUUIDIDНазваниеСтолбика=null;
                    startActivity(Интент_ЗапускСозданиеНовогоСотрудника);
                    overridePendingTransition( R.transition.svayp_left_down,R.transition.svayp_reght_down);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());


        }
    }









    void МетодСвайпНазаПоДанным() {
        try{
            Log.d(this.getClass().getName(), "ЗАПУСК  moveRight Свайп      onFling   " + new Date());
            CountDownLatch countDownLatchДляДвиженияПоДАннымНазад=new CountDownLatch(1);
            if (ОбщееКоличествоСОтрудниковДляСкролаПотабелю>1) {
                Log.d(  getApplicationContext().getClass().getName(), " ТекущееПоложенияВТабелеДляСкрола  "+ТекущееПоложенияВТабелеДляСкрола);
                if (ТекущееПоложенияВТабелеДляСкрола == 1) {
                    ТекущееПоложенияВТабелеДляСкрола = ОбщееКоличествоСОтрудниковДляСкролаПотабелю;
                }else{
                    if (ТекущееПоложенияВТабелеДляСкрола >1) {
                        ТекущееПоложенияВТабелеДляСкрола--;
                    }
                }
                Log.d(  getApplicationContext().getClass().getName(), " ТекущееПоложенияВТабелеДляСкрола  "+ТекущееПоложенияВТабелеДляСкрола);
                countDownLatchДляДвиженияПоДАннымНазад.countDown();
                countDownLatchДляДвиженияПоДАннымНазад.await();
                Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
                Интент_ЗапускСозданиеНовогоСотрудника.setClass(getApplicationContext(), MainActivity_Tabel_Only_People.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                Интент_ЗапускСозданиеНовогоСотрудника.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("setIDСамогоТабеляВКотромМыНаходились", ТекущееПоложенияВТабелеДляСкрола);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПередаемСозданнуюДатуНовогоТабеля", МесяцТабеляФинал);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолноеНазваниеТабеляФинал", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МЕсяцДляКурсораТабелей", МЕсяцДляКурсораТабелейДЛяПермещения);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ЦифровоеИмяНовгоТабеля", ЦифровоеИмяНовгоТабеля);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПроизошелЛиСфайпПоДаннымСингТабеля", true);
                Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
                startActivity(Интент_ЗапускСозданиеНовогоСотрудника);
                overridePendingTransition( R.transition.svayp_left_up,R.transition.svayp_reght_up);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());


        }
    }



    // TODO: 06.05.2021  созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов


//TODO SUB CLASS с ГЛАВНЫМ МЕПТОДОМ ОФОРМЛЕНИЯ ТАБЕЛЯ


    private void МетодГлавныйОрмленияТабеляДнямиИЗначениямиДляЭтиДней(SQLiteCursor ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп, int[] ИндексСтрокКомпонентовТабеля
            , Map<Integer, String> ХЭШНазваниеДнейНедели,
                                                                      TextView НазваниеДанныхВТабелеДниНедели,
                                                                      EditText СамиДанныеТабеля
            ,View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла) {
        /////todo ПЕРВАЯ СТРОКА НАЗВАНИЯ

        try{
            /////TODO ДЕНЬ ПЕРВЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень1);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(1));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            String regex = "Сб ,(.*)";
            String regex1 = "Вс ,(.*)";
            if (ХЭШНазваниеДнейНедели.get(1).matches(regex) || ХЭШНазваниеДнейНедели.get(1).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ВТОРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень2);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(2));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(2));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(2));
            if (ХЭШНазваниеДнейНедели.get(2).matches(regex) || ХЭШНазваниеДнейНедели.get(2).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СРЕДА
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень3);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(3));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(3).matches(regex) || ХЭШНазваниеДнейНедели.get(3).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТВЕРЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень4);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(4));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(4).matches(regex) || ХЭШНазваниеДнейНедели.get(4).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень5);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(5));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(5).matches(regex) || ХЭШНазваниеДнейНедели.get(5).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень6);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(6));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(6).matches(regex) || ХЭШНазваниеДнейНедели.get(6).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СЕДЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень7);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(7));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(7).matches(regex) || ХЭШНазваниеДнейНедели.get(7).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ВОСЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень8);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(8));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(8).matches(regex) || ХЭШНазваниеДнейНедели.get(8).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕВЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень9);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(9));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(9).matches(regex) || ХЭШНазваниеДнейНедели.get(9).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕСЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень10);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(10));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(10).matches(regex) || ХЭШНазваниеДнейНедели.get(10).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ОДИНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень11);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(11));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(11).matches(regex) || ХЭШНазваниеДнейНедели.get(11).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДВЕНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень12);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(12));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(12).matches(regex) || ХЭШНазваниеДнейНедели.get(12).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ТРЕНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень13);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(13));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(13).matches(regex) || ХЭШНазваниеДнейНедели.get(13).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТЫРНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень14);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(14));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(14).matches(regex) || ХЭШНазваниеДнейНедели.get(14).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЕТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень15);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(15));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(15).matches(regex) || ХЭШНазваниеДнейНедели.get(15).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень16);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(16));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(16).matches(regex) || ХЭШНазваниеДнейНедели.get(16).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            ///TODO  КОНЕЦ ПЕРВОЙ СТРОКИ НАЗВАНИЯ ДАННЫХ (вТ,01,Ср,02)


            ///TODO  НАЧАЛО САМИ ДАННЫЕ ПЕРВОЙ СТРОКИ(1,2,3,4,6,7,8,9)

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень1);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d1");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней = "";
            СамиДанныеКурсораДляДней = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" + СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней != null) {
                СамиДанныеКурсораДляДней = СамиДанныеКурсораДляДней.replaceAll("\\s+", "");
                // СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")) {
                    СамиДанныеКурсораДляДней = "";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }


            }
            // TODO: 24.05.2021 событие при поднятии клавиатуры


            //////

            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener(СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener(СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления


            /////TODO ТАБЕЛЬ вторая строчка вторник
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень2);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d2");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }

                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }




            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка среда
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень3);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d3");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка четверг

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень4);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d4");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }

                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);

            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ третья строчка пятница
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень5);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d5");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления

            /////TODO ТАБЕЛЬ третья строчка суббота
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень6);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d6");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления

            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень7);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d7");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень8);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d8");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень9);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d9");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень10);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d10");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            // TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень11);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d11");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);

            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень12);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d12");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень13);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d13");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            ////////////////////////
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень14);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d14");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ////////////////////////
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования


            if(СтаттусТабеля==true){


                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);

                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень15);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d15");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень16);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d16");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            //TODO  КОНЕЦ Сами Данных Табеля (1,2,3,4,5,6)-->


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO КОНЕЦ ПЕРВОЙ СТРОЧКИ


            ///TODO НАЧАЛО ВТОРОЙ СТРОКИ

            ///TODO НАЗВАНИЕ ДНЕЙ ВТОРОЙ СТРОКИ
            /////TODO ДЕНЬ 17
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень17);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(17));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(17).matches(regex) || ХЭШНазваниеДнейНедели.get(17).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ВТОРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень18);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(18));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(18).matches(regex) || ХЭШНазваниеДнейНедели.get(18).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СРЕДА
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень19);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(19));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(19).matches(regex) || ХЭШНазваниеДнейНедели.get(19).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТВЕРЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень20);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(20));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(20).matches(regex) || ХЭШНазваниеДнейНедели.get(20).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень21);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(21));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(21).matches(regex) || ХЭШНазваниеДнейНедели.get(21).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень22);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(22));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(22).matches(regex) || ХЭШНазваниеДнейНедели.get(22).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СЕДЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень23);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(23));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(23).matches(regex) || ХЭШНазваниеДнейНедели.get(23).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ВОСЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень24);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(24));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(24).matches(regex) || ХЭШНазваниеДнейНедели.get(24).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕВЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень25);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(25));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(25).matches(regex) || ХЭШНазваниеДнейНедели.get(25).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕСЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень26);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(26));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(26).matches(regex) || ХЭШНазваниеДнейНедели.get(26).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ОДИНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень27);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(27));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(27).matches(regex) || ХЭШНазваниеДнейНедели.get(27).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДВЕНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень28);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(28));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(28).matches(regex) || ХЭШНазваниеДнейНедели.get(28).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ТРЕНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень29);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(29));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<29) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else{
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(29).matches(regex) || ХЭШНазваниеДнейНедели.get(29).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            /////TODO ДЕНЬ ЧЕТЫРНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень30);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(30));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<30) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else{
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(30).matches(regex) || ХЭШНазваниеДнейНедели.get(30).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            ///todo загружем если данные дни есть в календаре
            /////TODO ДЕНЬ ПЕТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень31);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(31));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<31) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else {
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(31).matches(regex) || ХЭШНазваниеДнейНедели.get(31).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            ///TODO  КОНЕЦ ВТОРОЙ СТРОКИ НАЗВАНИЯ ДАННЫХ (вТ,01,Ср,02)


            ///TODO  НАЧАЛО САМИ ДАННЫЕ ВТОРОЙ СТРОКИ(10,11,12,13,14,15)

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень17);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d17");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ вторая строчка вторник
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень18);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d18");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);

            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка среда
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень19);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d19");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ вторая строчка четверг

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень20);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d20");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);

            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ третья строчка пятница
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень21);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d21");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);
            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ третья строчка суббота
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень22);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d22");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень23);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d23");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень24);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d24");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень25);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d25");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень26);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d26");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень27);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d27");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень28);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d28");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень29);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d29");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);

            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<29) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }
            ////////////////////////
            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень30);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d30");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            //////
            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<30) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ////////////////////////
            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень31);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("d31");
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            /////todo обработка значения для дней
            СамиДанныеКурсораДляДней= "";
            СамиДанныеКурсораДляДней= ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня);
            Log.d(this.getClass().getName(), " СамиДанныеКурсораДляДней" +СамиДанныеКурсораДляДней);
            if (СамиДанныеКурсораДляДней!=null){
                СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("\\s+","");
                ////  СамиДанныеКурсораДляДней=    СамиДанныеКурсораДляДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (СамиДанныеКурсораДляДней.equals("0")){
                    СамиДанныеКурсораДляДней="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (СамиДанныеКурсораДляДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(СамиДанныеКурсораДляДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ////////////////////////
            this.СамиДанныеТабеля.setOnFocusChangeListener( СлушательLONGПолученияНазваниеСтолбикаДляЛокальногоОбновления);
            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<31) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }
            ///////////////////////

            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляДобавленияСловВТабельначениеСтолбикаДляЛокальногоОбновления);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            //TODO  КОНЕЦ Сами Данных Табеля (1,2,3,4,5,6)-->

            //TODO ТЕХНИЧЕСКИЙ  БЛОК ВЕРХНЕМ СТРОЧКИ ID И UUID-->
            ///TODO uuid не видивая EDITTEXT понадобиться для обновления UUID
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.СтрочкаНевидимаяUUID);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("uuid"); ////TODO СЮДА ПОЛЕ UUID
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            this.СамиДанныеТабеля.setText(ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
            Log.d(this.getClass().getName(), " ВтораяСтрочкаНевидимаяUUID " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
            this.СамиДанныеТабеля.setVisibility(View.GONE);
            ///TODO вытаскиваем UUID
            ///TODO uuid не видивая EDITTEXT понадобиться для обновления ID
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.СтрочкаНевидимаяID);
            ПосикДня = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnIndex("_id"); ////TODO СЮДА ПОЛЕ ID
            НазваниеСтолбикаДляЛобкальногоОбноления = ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеСтолбикаДляЛобкальногоОбноления);
            this.СамиДанныеТабеля.setText(ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
            Log.d(this.getClass().getName(), " ВтораяСтрочкаНевидимаяID " + ГлавныйКурсорКогдаМыКликаемПоСпискуТабелейИлиСфайп.getString(ПосикДня));
            this.СамиДанныеТабеля.setVisibility(View.GONE);

            Log.d(this.getClass().getName(), " ИндексСтрокКомпонентовТабеля " + ИндексСтрокКомпонентовТабеля[0]);
            /////TODO увеличиваем шаг  по строчкам
            ИндексСтрокКомпонентовТабеля[0]++;
            //////TODO заврешаем уикл загрузки данных на ктивити табель
            //TODO  ОЧИЩАЕМ ПАМТЬ

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}



