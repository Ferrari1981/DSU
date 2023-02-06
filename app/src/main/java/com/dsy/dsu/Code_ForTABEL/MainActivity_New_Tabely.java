package com.dsy.dsu.Code_ForTABEL;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_UUID;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//класс активити MainActivity_New_Tabely
public class MainActivity_New_Tabely extends AppCompatActivity {
    private MaterialTextView СпинерВыборЦФО,СпинерВыборДата;
    private   String  ПолученноеЗначениеИзСпинераРаздел; ///результат полученный из спенров
    private Button КнопкаСозданиеТабеля;
    private  Button КнопкаНазадПриСозданииНовогоТабеля;
    private  Context Контекст;
    private  CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
   private ArrayAdapter<String> АдаптерДляСпинераЦФО;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private   Cursor CursorДляСпиноровЦФО;
    private  Handler handler;
    private ProgressBar progressBar;
    private AsyncTaskLoader<Cursor>asyncTaskLoader;
    private SharedPreferences preferences;
    private Boolean ФдагЧтоУжеОдинРАзБылПервыйПроход=false;
    private Animation animation;
    private  final ListView[] listViewДляЦФО = new ListView[1];
    private  AlertDialog alertDialog;
    private Intent intentПришелСДанными;

    // TODO: 15.12.2022 методы
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(this.getClass().getName(), "Запуск onCreate..  в MainActivity_New_Tabely ");
                super.onCreate(savedInstanceState);
            setContentView(R.layout.activitymain_createtablesavehistory);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getSupportActionBar().hide(); ///скрывать тул бар
            Контекст=this;
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            ///todo кнопака создание нового пустого табеля без сотрудников
            КнопкаСозданиеТабеля = findViewById(R.id.КнопкаСозданиеНовогоТабеля);
            СпинерВыборЦФО=findViewById(R.id.materialspiritnew_tabel);
            КнопкаНазадПриСозданииНовогоТабеля  =findViewById(R.id.КнопкаНазадСтрелкаСозданиеНовгоТабеля);
            СпинерВыборДата=(MaterialTextView) findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);////СЮДА ДАТА ПРИШЛА ОТ ДРУГОВВА АКТИВ
            progressBar =  findViewById(R.id.ProgressBar);
            progressBar.setVisibility(View.VISIBLE);
            // TODO: 01.11.2022 методы до начало запуска
            Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(getApplicationContext());
            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            МетодHandlerCallBack();
            МетодСозданиеКодBACK();
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_newscanner1);
            intentПришелСДанными=getIntent();
            animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row_newscanner1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
          // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        try{
               МетодПолучениеМатериала("");
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///////
    }
    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            if (!asyncTaskLoader.isStarted()) {
                МетодСпинерЦФО();
                МетодСпинерДаты();
               МетодСозданиеТабеля();
                progressBar.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //////TODO  данный код срабатывает когда произошда ошивка в базе
    }

    @Override
    public void onStop() {
        super.onStop();
        try{
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    /////todo метод создание BACK
    private void МетодСозданиеКодBACK() {
        try{
            КнопкаНазадПриСозданииНовогоТабеля.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                    Intent ИнтентВозврящемсяНазад = new Intent();
                    Bundle data=new Bundle();
                    data.putBinder("binder", binder);
                    ИнтентВозврящемсяНазад.putExtras(data);
                    ИнтентВозврящемсяНазад.setClass(getApplication(), MainActivity_List_Tabels.class); // Т
                    ИнтентВозврящемсяНазад.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(ИнтентВозврящемсяНазад);
                }
            });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
          this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

















































/////TODO Цифроввое имени табеля














    //TODO метод провереям если название табеля в базе
    private boolean МетодПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть(@NotNull  Integer ПолученноЦифровоеОбознаяниеЦФО,
                                                                      @NotNull  Integer МесяцДляАнализаиВставкиЕслиТакогоНет,
                                                                      @NotNull Integer ГодляАнализаиВставкиЕслиТакогоНет,
                                                                      @NotNull  CompletionService МенеджерПотоковВнутри)
            throws InterruptedException, ExecutionException, TimeoutException, ParseException {
        Class_GRUD_SQL_Operations class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть;
        try {
            Log.d(this.getClass().getName(), " МесяцДляАнализаиВставкиЕслиТакогоНет " + МесяцДляАнализаиВставкиЕслиТакогоНет
                    + " ГодляАнализаиВставкиЕслиТакогоНет " + ГодляАнализаиВставкиЕслиТакогоНет);
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть=new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","tabel");
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","cfo, month_tabels ,year_tabels");
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","cfo= ? AND month_tabels=? " +
                    "AND year_tabels=? AND status_send!=? ");
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолученноЦифровоеОбознаяниеЦФО);
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",МесяцДляАнализаиВставкиЕслиТакогоНет);
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",ГодляАнализаиВставкиЕслиТакогоНет);
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4","Удаленная");////УсловиеПоискаv4,........УсловиеПоискаv5 .......
            ////TODO другие поля
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC" );
            class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            SQLiteCursor    Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет= (SQLiteCursor)  class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоковВнутри
                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData "  +Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет);
        if (   Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.getCount() > 0) {
            Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.close();
            return true;
        }else {
            Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.close();
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
          this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return false;
    }















    private void МетодСпинерЦФО() {
        try{
            СпинерВыборЦФО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStart();
                    final MaterialButton[] materialButtonЗакрытьДиалог = new MaterialButton[1];
                     alertDialog = new MaterialAlertDialogBuilder(v.getContext()){
                        @NonNull
                        @Override
                        public MaterialAlertDialogBuilder setView(View view) {
                            listViewДляЦФО[0] =    (ListView) view.findViewById(R.id.SearchViewList);
                            listViewДляЦФО[0].setTextFilterEnabled(true);
                            SearchView searchViewДляНовогоЦФО=    (SearchView) view.findViewById(R.id.searchview_newscanner);
                            searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
                            // TODO: 14.12.2022
                            searchViewДляНовогоЦФО.setDrawingCacheBackgroundColor(Color.GRAY);
                            searchViewДляНовогоЦФО.setDrawingCacheEnabled(true);
                            int id = searchViewДляНовогоЦФО.getContext()
                                    .getResources()
                                    .getIdentifier("android:id/search_src_text", null, null);
                            TextView textView = (TextView) searchViewДляНовогоЦФО.findViewById(id);
                            textView.setTextColor(Color.rgb(0,102,102));
                            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                            // TODO: 14.12.2022
                             materialButtonЗакрытьДиалог[0] =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                            ///TODO ГЛАВНЫЙ АДАПТЕР чата
                            SimpleCursorAdapter   simpleCursorAdapterЦФО= new SimpleCursorAdapter(v.getContext(),   R.layout.simple_newspinner_dwonload_newfiltersearch, CursorДляСпиноровЦФО,
                                    new String[]{ "name","_id"},
                                    new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//R.layout.simple_newspinner_dwonload_cfo2
                            SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder(){

                                @Override
                                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                    switch (view.getId()) {
                                        case android.R.id.text1:
                                            Log.d(this.getClass().getName()," position");
                                            if (cursor.getCount()>0) {
                                                try{
                                                    Integer ИндексНазваниеЦФО = cursor.getColumnIndex("name");///user_update  --old/// uuid
                                                    String НазваниеЦФО = cursor.getString(ИндексНазваниеЦФО);
                                                    // TODO: 13.12.2022  производим состыковку
                                                    Integer ИндексНазваниеЦфоID = cursor.getColumnIndex("_id");///user_update  --old/// uuid
                                                    Integer ПолучаемIDЦфо = cursor.getInt(ИндексНазваниеЦфоID);
                                                    if (ПолучаемIDЦфо>0) {
                                                        Integer UUIDНазваниеЦФО = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                        Long UUIDЦФО = cursor.getLong(UUIDНазваниеЦФО);
                                                        Bundle bundle=new Bundle();
                                                        bundle.putInt("ПолучаемIDЦфо",ПолучаемIDЦфо);
                                                        bundle.putString("НазваниеЦФО",НазваниеЦФО);
                                                        bundle.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
                                                        ((MaterialTextView)view).setTag(bundle);
                                                    }
                                                    Log.d(this.getClass().getName()," НазваниеЦФО"+ НазваниеЦФО+
                                                             " ПолучаемIDЦфо "+ПолучаемIDЦфо);
                                                    // TODO: 17.11.2022  если пользователь уже выбирал
                                                    Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferences.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                                                    if(ФлагВыбиралУжеЦФОИзСпинера && ФдагЧтоУжеОдинРАзБылПервыйПроход ==false){
                                                        String НазваниеВыбраногоЦФО=            preferences.getString("НазваниеВыбраногоЦФО","");
                                                        ((MaterialTextView)view).setText(НазваниеВыбраногоЦФО);
                                                    }
                                                    ФдагЧтоУжеОдинРАзБылПервыйПроход=true;
                                                    // TODO: 20.01.2022
                                                    Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеЦФО);
                                                    boolean ДлинаСтрокивСпиноре = НазваниеЦФО.length() >40;
                                                    if (ДлинаСтрокивСпиноре) {
                                                        StringBuffer sb = new StringBuffer(НазваниеЦФО);
                                                        sb.insert(40, System.lineSeparator());
                                                        НазваниеЦФО = sb.toString();
                                                        Log.d(getApplicationContext().getClass().getName(), " НазваниеЦФО " + "--" + НазваниеЦФО);/////
                                                    }
                                                    ((MaterialTextView)view).setText(НазваниеЦФО);
                                                    // TODO: 13.12.2022 слушатель
                                                    ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            ((MaterialTextView)view).startAnimation(animation);
                                                         Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                      Integer IDЦфоДЛяПередачи=      bundle.getInt("ПолучаемIDЦфо",0);
                                                         String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
                                                         Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
                                                            СпинерВыборЦФО.setTag(bundle);
                                                            СпинерВыборЦФО.setText(НазваниеЦФО);
                                                            СпинерВыборЦФО.refreshDrawableState();
                                                            СпинерВыборЦФО.forceLayout();

                                                            if (  СпинерВыборЦФО.getText().toString().length()==0) {
                                                                          Snackbar.make(view, " Вы не выбрали цфо !!! "
                                                                    , Snackbar.LENGTH_LONG).show();
                                                                СпинерВыборЦФО.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                                СпинерВыборЦФО.setTextColor(Color.GRAY);
                                                                Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                            } else {
                                                                СпинерВыборЦФО.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                                СпинерВыборЦФО.setTextColor(Color.BLACK);
                                                                alertDialog.dismiss();
                                                                alertDialog.cancel();
                                                                Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                            }
                                                            Log.d(this.getClass().getName()," position");
                                                        }
                                                    });
                                                    // TODO: 13.12.2022 филь
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                            this.getClass().getName(),
                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                                return true;
                                            } else {
                                                Log.d(this.getClass().getName()," position");
                                                return false;
                                            }
                                    }
                                    return false;
                                }
                            };
                            simpleCursorAdapterЦФО.setViewBinder(БиндингДляЦФО);
                            listViewДляЦФО[0].setAdapter(simpleCursorAdapterЦФО);
                            simpleCursorAdapterЦФО.notifyDataSetChanged();
                            listViewДляЦФО[0].startAnimation(animation);
                            listViewДляЦФО[0].setSelection(0);
                            listViewДляЦФО[0].forceLayout();
                            // TODO: 13.12.2022  Поиск и его слушель
                            МетодПоискаФильтр(searchViewДляНовогоЦФО,simpleCursorAdapterЦФО);
                            return super.setView(view);
                        }
                    }
                            .setTitle("ЦФО")
                            .setCancelable(false)
                            .setIcon( R.drawable.icon_newscannertwo)
                            .setView(getLayoutInflater().inflate( R.layout.simple_for_new_spinner_searchview, null ))
                            .show();
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                            layoutParams.copyFrom(   alertDialog.getWindow().getAttributes());
                            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                            layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                            layoutParams.gravity = Gravity.CENTER;
                            alertDialog.getWindow().setAttributes(layoutParams);
                    // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
                        materialButtonЗакрытьДиалог[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                Log.d(this.getClass().getName()," position");
                                        Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                                    СпинерВыборЦФО.setText("");
                                СпинерВыборЦФО.refreshDrawableState();
                                СпинерВыборЦФО.forceLayout();
                                alertDialog.dismiss();
                                alertDialog.cancel();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            }
                        });
                }

                private void МетодПоискаФильтр(@NonNull   SearchView searchViewДляНовогоЦФО,
                                               @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО) {
                    try{
                    searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            Log.d(this.getClass().getName()," position");
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            Log.d(this.getClass().getName()," position");
                            Filter filter= simpleCursorAdapterЦФО.getFilter();
                            filter.filter(newText);
                                return true;
                        }
                    });
                        simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                            @Override
                            public Cursor runQuery(CharSequence constraint) {
                                Log.d(this.getClass().getName()," position");
                                    try{
                                        CursorДляСпиноровЦФО=      МетодДляНовогоТабеляПолучаемДанныеИзНовогоПоиска("cfo",constraint.toString());
                                    handler.post(()->{
                                        simpleCursorAdapterЦФО.swapCursor(CursorДляСпиноровЦФО);
                                        simpleCursorAdapterЦФО.notifyDataSetChanged();
                                        listViewДляЦФО[0].setSelection(0);
                                        if (CursorДляСпиноровЦФО.getCount()==0) {
                                            searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                            handler.postDelayed(() -> {
                                                searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                            }, 500);
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                    return CursorДляСпиноровЦФО;
                            }
                        });

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
          this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодСпинерДаты() {
        try{
            СпинерВыборДата.setText(intentПришелСДанными.getStringExtra("ПолученноеЗначениеИзСпинераДата"));
            СпинерВыборДата.forceLayout();
            СпинерВыборДата.refreshDrawableState();
            Log.d(this.getClass().getName()," ПолученноеЗначениеИзСпинераРаздел() "+ПолученноеЗначениеИзСпинераРаздел);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @UiThread
    protected void СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        int Значек = 0;
        if (статус){
            Значек =R.drawable.icon_dsu1_new_create_tabel4;
        }else{
            Значек =R.drawable.icon_dsu1_new_create_tabel5error;
        }
        try{
            final AlertDialog DialogBoxsПростомрДанных = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setNegativeButton("ОК", null)
                    .setIcon( Значек)
                    .show();
            final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = DialogBoxsПростомрДанных.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    DialogBoxsПростомрДанных.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    void МетодПослеСозданеиУспешногоТабеля(@NonNull Bundle bundleПослеСоздаенияНовгоТабелч) {
        try{
            Log.d(this.getClass().getName(), " bundleПослеСоздаенияНовгоТабелч"+bundleПослеСоздаенияНовгоТабелч  );
            Integer ID_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("ПолучаемIDЦфо",0);
            String Название_ЦФО=   bundleПослеСоздаенияНовгоТабелч.getString("НазваниеЦФО","");
            Integer Месяц_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("МесяцВырезалиИзБуфераТабель",0);
            Integer Год_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("ГодВырезалиИзБуфераТабель",0);
            Long Новый_UUID_ЦФО=   bundleПослеСоздаенияНовгоТабелч.getLong("СгенерированныйUUIDДляНовогоТабеля",0l);
            // TODO: 15.12.2022  передаем данные
        Intent Интент_ПослеСозданиеНовогоТабеля=new Intent();
        Интент_ПослеСозданиеНовогоТабеля.setClass(getApplication(),  MainActivity_List_Tabels.class); // ТУТ ЗАПВСКАЕТЬСЯ ВЫБОР ПРИЛОЖЕНИЯ
        Интент_ПослеСозданиеНовогоТабеля.putExtra("ГодВырезалиИзБуфераТабель", Год_ЦФО);
        Интент_ПослеСозданиеНовогоТабеля.putExtra("МесяцВырезалиИзБуфераТабель", Месяц_ЦФО);
        Интент_ПослеСозданиеНовогоТабеля.putExtra("ЦифровоеИмяНовгоТабеля", ID_ЦФО  );
        Интент_ПослеСозданиеНовогоТабеля.putExtra("СгенерированныйUUIDДляНовогоТабеля", Новый_UUID_ЦФО);

            // TODO: 15.12.2022  названеи нового цфо
            Интент_ПослеСозданиеНовогоТабеля.putExtra("ДепартаментТабеляПослеПодбораBACK", Название_ЦФО);
            Интент_ПослеСозданиеНовогоТабеля.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника",  Название_ЦФО);
            Интент_ПослеСозданиеНовогоТабеля.putExtra("ПолученноеТекущееЗначениеСпинераДата", Название_ЦФО);
            Интент_ПослеСозданиеНовогоТабеля.putExtra("СгенерированныйНазваниеНовогоТабеля", Название_ЦФО);
            Log.d(this.getClass().getName(), " Название_ЦФО"+Название_ЦФО);
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            Интент_ПослеСозданиеНовогоТабеля.putExtras(data);
        startActivity(Интент_ПослеСозданиеНовогоТабеля);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }







    ////СООБЩЕНИЕ ИЗ ИТОРИИИ ТАБЛЕЙ ОТПРАВЛЯЕМ СООБЩЕНЕИ И ЗНАЧЕНИЕ В ДУГОЕ АКТИВТИ О СОЗДАНИЮ НОВОГО ТАБЕЛЯ

  /*  Iterator<Entry<String, String>>iterator=map.entrySet();
while(iterator.hasNext()){
        final Entry<String, String>next=iterator.next();
        next.getKey(); next.getValue();
    }*/




























    //TODO метод получени месяа для записи в одну колонку

    private Integer  МетодПолучениниеГодЕслиТАкойТАбельВБазеКлиент() throws ParseException {
        Integer ПолучениыйГодДЛяНовгоТабеля = 0;
        try{
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("y", new Locale("ru")); // Форматирование времени как "день.месяц.год"
            String ПолучаемГодВТексте = dateFormat.format(currentDate);
            ПолучениыйГодДЛяНовгоТабеля=Integer.parseInt(ПолучаемГодВТексте);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return   ПолучениыйГодДЛяНовгоТабеля ;
    }

    // TODO: 20.09.2021  класс по созаднию нового втабеля
    /////////КНОПКА СОЗДАНИЕ НОВОГО ТАБЕЛЯ НА МЕСЯЦ (АКВИТИ СОЗДАНИНЕ НОВГО ТАБЕЛЯ НА МЕСЯЦ)
    private void МетодСозданиеТабеля() {
        try{
            КнопкаСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////TODO после выбора цфо и подразделение создаем табель
                    if (  СпинерВыборЦФО.getText().toString().length()==0) {
                        Snackbar.make(v, " Вы не выбрали цфо !!! "
                                , Snackbar.LENGTH_LONG).show();
                        Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                    } else {
                        // TODO: 14.12.2022 созадем новый табель
                       Bundle bundle=(Bundle)      СпинерВыборЦФО.getTag();
                        Integer IDЦфоДЛяПередачи=      bundle.getInt("ПолучаемIDЦфо",0);
                        String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
                        Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
                        Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                        // TODO: 17.11.2022  запоминаем выбраное ЦФО
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
                        editor.putInt("ПозицияВыбраногоЦФО",IDЦфоДЛяПередачи);
                        editor.putString("НазваниеВыбраногоЦФО",НазваниеЦФО);
                        editor.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
                        editor.commit();
                        /////TODO создаем НОВЫЙ ТАБЕЛЬ
                        МетодФиналСоздаемТабельСПолученнымиДанными(bundle);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //////ДАННЫЙ МЕТОД НЕПОСТРДСТВЕННО ЗАПИСЫВАЕТ ДАННЫХ Е БАЗУ ДАННЫХ В ТАБЛИЦУ СОЗДАНИЕ ТАБЕЛЕЙ
    protected void МетодФиналСоздаемТабельСПолученнымиДанными(@NonNull  Bundle  bundleДляСозданияНовгоТабелч) {
        try {
            // TODO: 14.12.2022 данные для создаени новго табеля 
            Integer IDЦфоДЛяПередачи=      bundleДляСозданияНовгоТабелч.getInt("ПолучаемIDЦфо",0);
            String НазваниеЦФО=   bundleДляСозданияНовгоТабелч.getString("НазваниеЦФО","");
            //TODO ФУНКЙИИ ОПРЕДЕЛЕНИ МЕСЯЦА И ГОДА
            Integer МесяцДляАнализаиВставкиЕслиТакогоНет =  intentПришелСДанными.getIntExtra("ФинальнаяМЕсяцДляНовогоТабеля",0);
            Integer ГодляАнализаиВставкиЕслиТакогоНет = intentПришелСДанными.getIntExtra("ПолученныйГодДляНовогоТабеля",0);
            Log.d(this.getClass().getName()," IDЦфоДЛяПередачи " +IDЦфоДЛяПередачи + "НазваниеЦФО" +  НазваниеЦФО);
            // TODO: 15.12.2022 добадяем в передачу дальше
            bundleДляСозданияНовгоТабелч.putInt("МесяцВырезалиИзБуфераТабель",МесяцДляАнализаиВставкиЕслиТакогоНет);
            bundleДляСозданияНовгоТабелч.putInt("ГодВырезалиИзБуфераТабель",ГодляАнализаиВставкиЕслиТакогоНет);
            //////TODO  Метод определяем елси такое Навание Табеля Проверраем
            boolean РезультатЕслиТакоеНазвание =
                    МетодПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть(IDЦфоДЛяПередачи
                            ,МесяцДляАнализаиВставкиЕслиТакогоНет
                            ,ГодляАнализаиВставкиЕслиТакогоНет
                            ,Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков);
            Log.d(this.getClass().getName(), " РезультатЕслиТакоеНазвание" +РезультатЕслиТакоеНазвание );
            // TODO: 14.12.2022 оперделяем содаес новый табель или нет  
            if (РезультатЕслиТакоеНазвание==true){
                СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля("Создание Табеля",
                        "Такой табель уже есть (выберите другой цфо/дату ) !!! ",false );
            }else {
                // TODO: 14.12.2022 СОЗДАЕМ НОВЫЙ ТАБЕЛЕ ПОСЛЕ ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ СОЗДАЕНИЯ
                ContentValues АдаптерВставкиНовгоТабеля = new ContentValues();
         Long   СгенерированныйUUIDДляНовогоТабеля=  (Long)   new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
            Log.d(this.getClass().getName(), " СгенерированныйUUIDДляНовогоТабеля " + СгенерированныйUUIDДляНовогоТабеля);
            АдаптерВставкиНовгоТабеля.put("uuid", СгенерированныйUUIDДляНовогоТабеля);
                // TODO: 15.12.2022 добадяем в передачу дальше
                bundleДляСозданияНовгоТабелч.putLong("СгенерированныйUUIDДляНовогоТабеля",СгенерированныйUUIDДляНовогоТабеля);
            ////TODO ДАТА
            String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
            АдаптерВставкиНовгоТабеля.put("date_update",СгенерированованныйДатаДляДаннойОперации);

                Class_GRUD_SQL_Operations      class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля=new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
            class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","id IS NOT NULL");
            class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
                SQLiteCursor  Курсор_ИщемПУбличныйIDКогдаегоНетВстатике= (SQLiteCursor)  class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsаполениеБазыДанныхПолученнымиНовымиСведениямиНовогоТабеля.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData "  +Курсор_ИщемПУбличныйIDКогдаегоНетВстатике);
            if(Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount()>0){
                Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.moveToFirst();
                Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getCount());
              Integer  ПользовательскийID =Курсор_ИщемПУбличныйIDКогдаегоНетВстатике.getInt(0);
                // TODO: 14.12.2022  
                АдаптерВставкиНовгоТабеля.put("user_update", ПользовательскийID);
            }
            // TODO: 08.10.2021 повышаем верси
            Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника
                    =new Class_GRUD_SQL_Operations(getApplicationContext());
            Long    РезультатУвеличинаяВерсияДАныхЧата=
                    class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника. new ChangesVesionData(getApplicationContext()).
                    МетодПовышаемВерсииCurrentTable(
                            "tabel", getApplicationContext(),
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                АдаптерВставкиНовгоТабеля.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
                АдаптерВставкиНовгоТабеля.put("cfo",IDЦфоДЛяПередачи);
                АдаптерВставкиНовгоТабеля.put("year_tabels", ГодляАнализаиВставкиЕслиТакогоНет);
                АдаптерВставкиНовгоТабеля.put("month_tabels",  МесяцДляАнализаиВставкиЕслиТакогоНет);
                АдаптерВставкиНовгоТабеля.put("status_send", " ");
                АдаптерВставкиНовгоТабеля.  putNull("_id");

                // TODO: 14.12.2022  само создание нового табеля 
            long   РезультатВставкиНовогоТабеляЧерезКонтрейнер = new Class_MODEL_synchronized(Контекст).
                            ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная("tabel",
                                    АдаптерВставкиНовгоТабеля, "tabel", "");

                Log.d(this.getClass().getName(), "  РезультатВставкиНовогоТабеляЧерезКонтрейнер[0]  "+ РезультатВставкиНовогоТабеляЧерезКонтрейнер);

                if (РезультатВставкиНовогоТабеляЧерезКонтрейнер> 0 ) {
                    АдаптерВставкиНовгоТабеля.clear();
                    // TODO: 31.10.2022 успешное создание нового табеля
                    МетодПослеСозданеиУспешногоТабеля(bundleДляСозданияНовгоТабелч);
                } else {
                    СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля("Создание Табеля", "Табель не создан !!!", false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









    // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
    public void МетодПолучениеМатериала(@NotNull String Фильтр) {
        try {
            asyncTaskLoader=new AsyncTaskLoader<Cursor>(getApplicationContext()) {
                @Nullable
                @Override
                public Cursor loadInBackground() {
                    try{
                        // TODO: 31.10.2022  запускам получение данных и внешний вид
                        CursorДляСпиноровЦФО=            МетодДляНовогоТабеляПолучаемДанные("cfo",Фильтр);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName()
                                , Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return CursorДляСпиноровЦФО;
                }
            };
            asyncTaskLoader.startLoading();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }finally {
            CursorДляСпиноровЦФО=     asyncTaskLoader.loadInBackground();
            asyncTaskLoader.reset();
        }
    }
    // TODO: 02.08.2022
    protected   Cursor МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки){
        Cursor cursor = null;
        try{
         Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                 ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            if (binder!=null) {
                cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    // TODO: 15.10.2022
                        Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }
    // TODO: 02.08.2022
    protected   Cursor МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
        Cursor cursor = null;
        try{
            Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                    ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
            Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            if (binder!=null) {
                cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
               // }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }
    // TODO: 02.08.2022
    protected  Cursor МетодДляНовогоТабеляПолучаемДанныеИзНовогоПоиска(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
        Cursor cursor = null;
        try{
            Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                    ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
            Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            if (binder!=null) {
                cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }
    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
}





