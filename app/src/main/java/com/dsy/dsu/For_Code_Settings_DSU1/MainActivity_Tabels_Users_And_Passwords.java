package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.preference.PreferenceManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Encryption_Decryption_Login_Password;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.Class_Type_Connenction_Tel;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassWriterPUBLICIDtoDatabase;
import com.dsy.dsu.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;

public class MainActivity_Tabels_Users_And_Passwords extends AppCompatActivity {
    ////todo аунтификация
   private int ПодсчетОтрицательныйРезультатовАунтификации; ////подсчитываем количество негативныйх попыток долеее 5 послываем программу в спячку
    private Button КнопкаВходавСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private ProgressBar ПрогрессБарДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private TextInputEditText ИмяДляВходаСистему,ПарольДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private   Configuration config;
    private Activity activity;
    private String КакойРежимСинхрониазции=new String();
    private Context КонтекстСинхроДляАунтификации;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private String ПубличноеИмяПользовательДлСервлета=new String();
    private String  ПубличноеПарольДлСервлета=new String();
    private   Integer ПубличноеIDПолученныйИзСервлетаДляUUID;
    private String СтрокаСвязиСсервером=new String();
    private  CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;
    private  StringBuffer   БуферПолученнниеДанныхПолученияIDотСервера=new StringBuffer();
    private SharedPreferences preferences;
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            КонтекстСинхроДляАунтификации = this;
            activity = this;
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new  PUBLIC_CONTENT(getApplicationContext());
            Intent ИнтентКакаяПоСчетуСинхронизация = getIntent();
             КакойРежимСинхрониазции = ИнтентКакаяПоСчетуСинхронизация.getStringExtra("РежимЗапускаСинхронизации");
            Log.d(this.getClass().getName(), " КакойРежимСинхрониазции "+КакойРежимСинхрониазции);
               Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            setContentView(R.layout.activity_main__authentication);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "   ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config =
                    getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);
            Log.d(getPackageName().getClass().getName(), " onCreate(Bundle savedInstanceState)  MainActivity_Tabels_Users_And_Passwords ");
            КнопкаВходавСистему = (Button) findViewById(R.id.КнопкаВходаВПриложение);/////кнопка входа на сервер
            КнопкаВходавСистему.setVisibility(View.VISIBLE);
            ПрогрессБарДляВходаСистему = (ProgressBar) findViewById(R.id.progressBarДляWIFI); ////програссбар при аунтификации при входе в системму
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// по умолчанию прогресс бар делаем не видеым
            ИмяДляВходаСистему = (TextInputEditText) findViewById(R.id.ИмяДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            ПарольДляВходаСистему = (TextInputEditText) findViewById(R.id.ПарольДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            String[] permissions = new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_MEDIA_LOCATION,
                    Manifest.permission.INSTALL_PACKAGES,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(this, permissions, 1);
           // preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
        } catch (Exception e) {
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ПодсчетОтрицательныйРезультатовАунтификации "
                    + ПодсчетОтрицательныйРезультатовАунтификации);
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            МетодПодготовкиДляАунтификации(); ////МЕТОД ПРЕДВАРИТЕЛЬНОГО ПОДГОТОВКИ К АУНТИФИКАЦИИ ПОЛЬЗОВАТЛЕЯ
        } catch (Exception e) {
            ПодсчетОтрицательныйРезультатовАунтификации++;///подсчитываем ошибки для точго чтобы приложение пошло спать
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ПодсчетОтрицательныйРезультатовАунтификации " + ПодсчетОтрицательныйРезультатовАунтификации);
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    ////////TODO КОТОРЫЙ НАЧИНАЕМ ТОЛЬКО ЕСЛИ ЕСТЬ ИМЯ И ПАРОЛЬ НАЧИНАЕТЬСЯ ТОЛЬКО С НЯЖАТИЕ КНОПКИ ВХОД
    private void МетодПодготовкиДляАунтификации() {
        try {

            КнопкаВходавСистему.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(50);
                    }
                    ПубличноеИмяПользовательДлСервлета = ИмяДляВходаСистему.getText().toString().trim();///получаем из формы имя для того чтобы постучаться на сервер
                    Log.d(getPackageName().getClass().getName(), "ПубличноеИмяПользовательДлСервлета " + ПубличноеИмяПользовательДлСервлета);
                 ПубличноеПарольДлСервлета = ПарольДляВходаСистему.getText().toString().trim();///////получаем из формы пароль для того чтобы постучаться на сервер
                    Log.d(getPackageName().getClass().getName(), "ПубличноеПарольДлСервлета " +ПубличноеПарольДлСервлета);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    if (ПубличноеИмяПользовательДлСервлета.length() > 0 &&  ПубличноеПарольДлСервлета.length() > 0) {
                        boolean РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию =
                                new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                        //TODO ФУТУРЕ ЗАВЕРШАЕМ
                        Log.d(this.getClass().getName(), "  РезультатПроВеркиУстановкиПользователяРежимРаботыСети "
                                + РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию);
                        Log.d(this.getClass().getName(), " РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию  "
                                +РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию);
                        if (РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию == true) {
                            ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                            //TODO запукаем метод аунтификции
                            МетодАунтификацииПользователяПриВходевПрограммуДСУ1(v);//// данный метод в будущем будет запускаться с  кнопк
                        } else {
                            Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                            Snackbar.make(v, "Нет связи с с сервером !!! ", Snackbar.LENGTH_LONG).show();
                        }////end проверки если сеть или нет TRUE
                    } else {
                        Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                        Snackbar.make(v, " Вы не заполнили Логин/Пароль ", Snackbar.LENGTH_LONG).show();

                    }
                }
            });

        } catch (Exception e) {
            ///метод запись ошибок в таблицу
            ПодсчетОтрицательныйРезультатовАунтификации++;///подсчитываем ошибки для точго чтобы приложение пошло спать
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ПодсчетОтрицательныйРезультатовАунтификации " + ПодсчетОтрицательныйРезультатовАунтификации);
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ////TODO САМ МЕТОД АУНТИФИКАЦИИ С СЕРВЕРОМ
    private void МетодАунтификацииПользователяПриВходевПрограммуДСУ1(View v) {

        Class_GRUD_SQL_Operations class_grud_sql_operationsАунтификацияПользователя=new Class_GRUD_SQL_Operations(getApplicationContext());

        try {
            //////
            //////TODO Запуск асинхроного ЛОУДОРА ДЛЯ АУНТИФТИКАЦИИ ПОЛЬЗОВАТЕЛЯ
            class_grud_sql_operationsАунтификацияПользователя.asyncTaskLoaderАунтификацияПользователя = new AsyncTaskLoader(КонтекстСинхроДляАунтификации) {
                HttpURLConnection ПодключениекСерверуДляАунтификацииПользователяПриВходе = null;
                String ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе = null;
                @Override
                protected void onStartLoading() {
                    super.onStartLoading();
                   // ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// по умолчанию прогресс бар делаем не видеым
                    Log.d(this.getClass().getName(), " onStartLoading() asyncTaskLoaderАунтификацияПользователя ");
                    ////TODO запускаем BACkground
                    forceLoad();
                }

                @Nullable
                @Override
                public Object loadInBackground() {
                    StringBuffer ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя = new StringBuffer();
                    BufferedReader БуферПодключениеJSONВерсияSQlserver = null;
                    try {
                        Log.d(this.getClass().getName(), " loadInBackground() asyncTaskLoaderАунтификацияПользователя ");
                        Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных =
                                new Class_Connections_Server(getApplicationContext()).         МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                        PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(getApplicationContext());
                        String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                        Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                        String ИмменоКакойСерверПодкючения ="http://"+ИмяСерверИзХранилица+":"+ПортСерверИзХранилица+"/";
                            //////TODO --операции
                            СтрокаСвязиСсервером = ИмменоКакойСерверПодкючения +new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервера()+ "/DSU1JsonServlet" + "?"///      String СтрокаСвязиСсервером=PUBLIC_CONTENT.ПубличныйАдресGlassFish + "dsu1.glassfish/DSU1JsonServlet" + "?"
                                    + "ЗаданиеДляСервлетаВнутриПотока=Хотим Получить ID для Генерации  UUID";
                            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
                            URL Adress = new URL(СтрокаСвязиСсервером); //
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе = null;
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("Content-Type", "application/text; charset=UTF-8");
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("Connection", "Keep-Alive");
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("Accept-Language", "ru-RU");
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestMethod("GET"); ////GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ С АНДРОЙДА НА SQL SERVER
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setReadTimeout(5000); //todo чтение потока до 5 секунд
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setConnectTimeout(2000);//todo таймайт подключение к самому серверу если вообще подключения
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setUseCaches(false);
                            ////////
                            Log.d(this.getClass().getName(), " ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя  " + ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя);
                            // TODO: 11.11.2021  ПЕРЕДОТПРАВКОЙ ШИФРУЕМ ДАННЫЕ \
                            String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(getApplicationContext()).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                            Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                            // TODO: 12.11.2021 ППЕРОБРАЗОВАНИЯ ПАРОЛЬЯ ЧЕРЕЗ BASE64 ПАРОЛЯ ВАРИАНТ 1
                            String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(getApplicationContext()).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                            Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);
                            /////// TODO set login pasword
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("p_identifier",
                                    ЗашифрованныйПароль);  //"dsu1getsession"
                            ////Dalvik/2.1.0 (Linux; U; Android 7.0; Android SDK built for x86 Build/NYC)
                            ///////посылаем сашифрованные хэдэры
                            ПодключениекСерверуДляАунтификацииПользователяПриВходе.setRequestProperty("identifier",
                                    ЗашифрованныйЛогин  );  //"dsu1getsession"   ПубличноеИмяПользовательДлСервлета
                            Log.d(this.getClass().getName(), "  ПубличноеИмяПользовательДлСервлета  " + ПубличноеИмяПользовательДлСервлета + "\n" +
                                    " ПубличноеПарольДлСервлета    " + ПубличноеПарольДлСервлета);
                            if (ПубличноеИмяПользовательДлСервлета.length()>0 && ПубличноеПарольДлСервлета.length()>0) {
                                try {
                                    ПодключениекСерверуДляАунтификацииПользователяПриВходе.connect(); /////////////ТОЛЬКО СОЕДИНЕНИЕ
                                    ///todo ping
                                    ПодключениекСерверуДляАунтификацииПользователяПриВходе.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА
                                    Log.d(this.getClass().getName(), "ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе "
                                            + ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе = e.toString();
                                    ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                                    Log.d(this.getClass().getName(), "ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе "
                                            + ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе);
                                }
                            }

                        Log.d(this.getClass().getName(), "ПодключениекСерверуДляАунтификацииПользователяПриВходе.getContentLength() "
                                + ПодключениекСерверуДляАунтификацииПользователяПриВходе.getHeaderField("stream_size"));
                        Long РазмерПришедшегоПотока = Long.parseLong(ПодключениекСерверуДляАунтификацииПользователяПриВходе.getHeaderField("stream_size"));
                        Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
                            /////ПОЛУЧАЕМ ЦИФРОВУЮ ВЕРСИЮ  ИМЕНИ ПОЛЬЗЛВАТЕЛЯ ВВ ИДЕ ЦИРЫ С SQL SERVERs
                            if (ПодключениекСерверуДляАунтификацииПользователяПриВходе.getResponseCode() == 200 && РазмерПришедшегоПотока > 0) {/////ЗАХОДИМ В ФАЙЛ ТОЛЬКО КОГДА НЕТ ОШИБКОВ В ПОТОКА ОТ SQL SEVER
                                ////TODO буфера проверки пользователя
                                БуферПодключениеJSONВерсияSQlserver =
                                        new BufferedReader(new InputStreamReader(new GZIPInputStream(ПодключениекСерверуДляАунтификацииПользователяПриВходе.getInputStream()), StandardCharsets.UTF_16));
                                ///TODO ПЕРВЫЙ ВАРАНТ РАСПАРСИВАНИЯ ПРИШЕДШЕГО JSON ПОТОКА С СЕРВРА
                            БуферПолученнниеДанныхПолученияIDотСервера = БуферПодключениеJSONВерсияSQlserver.lines()
                                     .collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                        StringBuffer::append);
                                    Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASKПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя "
                                            + Thread.currentThread().getName().toUpperCase() + " ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя "
                                            + ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя.toString()+
                                            " БуферПолученнниеДанныхПолученияIDотСервера " +БуферПолученнниеДанныхПолученияIDотСервера.toString());
                                }
                                /////////////САМ ЦИКЛ ЗАПОЛЕНИЯ ИЗ JSON В СТРО
                                if (БуферПолученнниеДанныхПолученияIDотСервера.length() > 0) {
                                    Log.d(this.getClass().getName(), " ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя"
                                            + ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя);
                                    ////TODO ДАННЫЙ КОД ОПРЕДЕЛЯЕТ ПРИСЛАЛ ЛИ ОШИБКУ СЕРВЕР НА НЕПРАВИЛЬНЫЙ И ПОЛНОСТЬ ОТСУТВУЮЩИЕ ДАННЫЕ ПОЛЬЗОВАТЕЛЯ, КОТОРЫЙ ХОТЕЛ ЗАЙТИ В  СИСТЕМУ
                                    if (!БуферПолученнниеДанныхПолученияIDотСервера.toString().trim().equalsIgnoreCase("Server Running...... Don't Login and Password") ) {
                                        // TODO: 21.04.2021 успешная унфтикация
                                       ПубличноеIDПолученныйИзСервлетаДляUUID =Integer.parseInt(БуферПолученнниеДанныхПолученияIDотСервера.toString());
                                        ///ИЗ ОТВЕТА ПОЛУЧАЕМ ID ПОЛЬЗОВАТЕЛЯ ДЛЯ ГЕНЕРАЦИИ  UUID//
                                        Log.d(this.getClass().getName(), "  ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя "
                                                + БуферПолученнниеДанныхПолученияIDотСервера + "  ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);
                                        Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу=Integer.parseInt(БуферПолученнниеДанныхПолученияIDотСервера.toString());
                                        ////////
                                        Log.d(this.getClass().getName(), " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                                        //todo метод после успешной аунтифтфикации записываем саупешное получение данных в базу после успешного вписаниеи пароля и логина
                                        МетодПослеУспешнойАунтификацииЗаписиваемИзменияВБАзуВДвеТаблицы_successlogin_И_Дополнительно_И_таблицуsettings_tabels(ПолученинныйПубличныйIDДлчЗаписиВБАзу);


                                        //TODO не прошёл аунтификайию

                                    }else{

                                        ПрогрессБарДляВходаСистему.setIndeterminate(false);
                                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                                        Snackbar snackbar = Snackbar.make(v, " " +"Логин и Пароль не правильный !!!" , Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                        //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                                        МетодВизуализацииПоложительныхИлиОтрицательныхПопытокАунтификации(v);


                                    }
                                }
                        // TODO: 03.10.2021   close
                    } catch (Exception e) {
                        Log.w(this.getClass().getName(), "ПодключениекСерверуДляАунтификацииПользователяПриВходе.getResponseCode() "
                                + "       ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе"+     ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе );
                        ///метод запись ошибок в таблицу
                        ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе = e.toString();
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                        //////////TODO когда ошибка то увеличичваем счетчик ошибок
                        ПодсчетОтрицательныйРезультатовАунтификации++;///подсчитываем ошибки для точго чтобы приложение пошло спать
                        // TODO: 01.09.2021  метод оправки ошибок на почту
                        try{
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ПрогрессБарДляВходаСистему.setIndeterminate(false);
                                    Snackbar snackbar;
                                    ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                                    if (ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе.matches("(.*)For input string(.*)")) {
                                        snackbar = Snackbar.make(v, "Логин/Пароль не правильный !!! " +
                                                "(" + "\n" + ПубличноеИмяПользовательДлСервлета.trim()
                                                + "  " + ПубличноеПарольДлСервлета.trim() + ") ", Snackbar.LENGTH_LONG);
                                    }else {
                                      snackbar = Snackbar.make(v, "Server выкл !!! " +
                                                "(" + "\n" + ПубличноеИмяПользовательДлСервлета.trim()
                                                + "  " + ПубличноеПарольДлСервлета.trim() + ") ", Snackbar.LENGTH_LONG);
                                    }


                                        snackbar.show();
                                        ПрогрессБарДляВходаСистему.setIndeterminate(false);
                                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                                        //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                                        МетодВизуализацииПоложительныхИлиОтрицательныхПопытокАунтификации(v);

                                }
                            });
                            ///todo публикум название таблицы или цифру его
                    } catch (Exception ex) {
                        //  Block of code to handle errors
                        ex.printStackTrace();
                            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }



                        /////todo данный код когда имя и пароль не правильны
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ПодсчетОтрицательныйРезультатовАунтификации " + ПодсчетОтрицательныйРезультатовАунтификации);
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    /////todo ВЫХОД ПОСЛЕ РАБОТЫ BACKGRAUNG
                    /////
                    return БуферПолученнниеДанныхПолученияIDотСервера;  ///ПодсчетОтрицательныйРезультатовАунтификации
                }










                private void МетодПослеУспешнойАунтификацииЗаписиваемИзменияВБАзуВДвеТаблицы_successlogin_И_Дополнительно_И_таблицуsettings_tabels(Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу )
                        throws ExecutionException, InterruptedException {
                    //// todo после успешного получение имени и пароля записываем их в базу ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
                    try{
                       //todo ЗАПИСЬ ="SUCCENLOGIN";
                        Long результатЗаписиНовогоПароляПользователявБазцуsuccesslogin=
                                new SubClassWriterPUBLICIDtoDatabase().
                                        МетодЗапипиВБАзуПубличногоID(getApplicationContext(),ПолученинныйПубличныйIDДлчЗаписиВБАзу,ПубличноеИмяПользовательДлСервлета,ПубличноеПарольДлСервлета);
                    if (результатЗаписиНовогоПароляПользователявБазцуsuccesslogin>0) {
                        /// TODO: 22.02.2022 ЗАПИСЬ ="settings_tabels";
                        Long РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels=
                                new Class_MODEL_synchronized(getApplicationContext()).  МетодЗАписиПолученогоОтСервреаIDПубличногоВТАблицу_settings_tabels(
                                        ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                        // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ
                        Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера "
                                +БуферПолученнниеДанныхПолученияIDотСервера.toString() +
                                " УСПЕШАЯ ЗАПИСЬ ПУБЛИЧНОГО id SUCCEESS !!!!  " +
                                "ТАБЛИЦА settings_tabels  РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels "
                                + РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels+
                                " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);


                        МетодВходаВПриложениеПослеУспешногоЛогирования();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                }

                private void МетодВходаВПриложениеПослеУспешногоЛогирования() {
                    try{
                    Log.d(this.getClass().getName(), " stopLoading() asyncTaskLoaderАунтификацияПользователя ");
                    Intent Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации = new Intent();
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеIDПолученныйИзСервлетаДляUUID",ПубличноеIDПолученныйИзСервлетаДляUUID);
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеИмяПользовательДлСервлета",ПубличноеИмяПользовательДлСервлета);
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеПарольДлСервлета",ПубличноеПарольДлСервлета);
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("СтрокаСвязиСсервером",СтрокаСвязиСсервером);
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setClass(getApplication(), MainActivity_Visible_Async.class);
                    Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/// FLAG_ACTIVITY_SINGLE_TOP
                        // TODO: 01.12.2022 записываем режим синъронизации
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
                        editor.commit();
                        startActivity(Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }


                ///todo метод визуализацци успешных и не успешных аунтифиуаци пользоватле
                private void МетодВизуализацииПоложительныхИлиОтрицательныхПопытокАунтификации(View v) {
                    MainActivity_Tabels_Users_And_Passwords.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Log.d(this.getClass().getName(), " handlerВизуализацияАунтификации ");
                            try {
                                Log.d(this.getClass().getName(), " ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе " + ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе
                                        + " БуферПолученнниеДанныхПолученияIDотСервера" + БуферПолученнниеДанныхПолученияIDотСервера.length());
                                if (ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе != null
                                        && БуферПолученнниеДанныхПолученияIDотСервера.length() == 0) {////полученный результат обрабатываем для принятия решения прошел ли пользователь аунтификацию
///TODO КОГДА 4 ПОПЫТКИ ПРОШЛИ НЕ УСПЕШНО И МЫ ЗАСЫВАЕМ ПРИЛОЖЕНИЯ НА 30 СЕКУНД
                                    if (ПодсчетОтрицательныйРезультатовАунтификации > 4) {////ПОПЫТКИ НЕ УДАЧНОГО ВХОДА В ПРОГРАММУ СВЫШЕ 5  СООБШАЕМ ПОЛЬЗОВАТЛЮ ЧТО ЕГО ИММ ЯИ ИЛИ ПАРОЛЬ НЕ ПРАВИЛЬНЫЙ И ПРИЛОЖЕНИЕ ОПРАЫЛЕМ В СОН
                                        ПодсчетОтрицательныйРезультатовАунтификации = 0;
                                                System.out.println("Another thread was executed");
                                                //TODO ЗАПУСКАЕМ ФУТУРЕ
                                                MainActivity_Tabels_Users_And_Passwords.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                                                        Snackbar.make(v, " Сон на 10 секунд.....", Snackbar.LENGTH_LONG).show();
                                                        КнопкаВходавСистему.setEnabled(false);
                                                        КнопкаВходавСистему.setClickable(false);
                                                        КнопкаВходавСистему.setBackgroundColor(Color.GRAY);
                                                            ((Activity) КонтекстСинхроДляАунтификации).runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    ПрогрессБарДляВходаСистему.postDelayed(()->{
                                                                        ПрогрессБарДляВходаСистему .setVisibility(View.INVISIBLE);
                                                                        КнопкаВходавСистему.setEnabled(true);
                                                                        КнопкаВходавСистему.setClickable(true);
                                                                        КнопкаВходавСистему.setBackgroundColor(Color.parseColor("#00ACC1"));
                                                                    },10000);// по умолчанию прогресс бар делаем не видеым

                                                                    // TODO: 13.10.2021
                                                                }
                                                            });
                                                    }
                                                });
                                    }
                                }
                            } catch (Exception e) {
                                ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });
                }

            };
            ///TODO запускаем asyncTaskLoader для акнтификации пользователя проверки этот ли пользователь
            class_grud_sql_operationsАунтификацияПользователя.asyncTaskLoaderАунтификацияПользователя    .startLoading();

            ///////TODO КОНЕЦ ASYNCTASKLOADER АУНТИФИКАЦИИЯ
        } catch (Exception e) {
            e.printStackTrace();
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    boolean МетодГлавныйСинхронизацииДанныхКлиентСервер(Context КонтекстКоторыйДляСинхронизации) throws ExecutionException, InterruptedException, TimeoutException {
        /////=----СИНХРОНИЗАЦИЯ
        ///TODO СИНХРОНИЗАЦИЯ ///TODO СИНХРОНИЗАЦИЯ при запуске прилиложения
//TODO ПРОВРЕМ WIFI ПОДКЛЮЧЕН ЛИ
        boolean РезультатПРоверкиПодключениеWIFI = false;
        String Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile = new String();
        try {
            /////todo тут МЫ ПОЛУЧАЕМ В КАКОЙ МОМЕНТ ТИП ПОДКЛЮЧЕНИЯ НА ТЕЛЕФОНЕ МОБИЛЯ ИЛИ  WIFI  И В ЗАВИСИМОСТИ ЧТОБЫ ПОНЯТЬ ЧЕ ЗА ДЕЛА
            Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile = new Class_Type_Connenction_Tel(getApplicationContext()).МетодОпределяемКакойТипПодключениеWIFIилиMobile();
            //////
            Log.d(this.getClass().getName(), "   РезутьтатПроверкиТипПодключениякИнтернету " + Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile);
                ////
          Class_GRUD_SQL_Operations       class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер = new Class_GRUD_SQL_Operations(getApplicationContext());
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "mode_connection");
                SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью = (SQLiteCursor) class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "GetData "+КурсорУзнаемСохраненыйРежимРаботыССетью);
                String РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile = new String();
                Log.d(getApplicationContext().getClass().getName(), " КурсорУзнаемСохраненыйРежимРаботыССетью  " + "--" + КурсорУзнаемСохраненыйРежимРаботыССетью);/////
                //////
                if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                    КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                    РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                    Log.d(getApplicationContext().getClass().getName(), " РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile  " + "--" + РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile);/////
                }
                // TODO: 03.10.2021  результата
                if (  (Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.trim().equalsIgnoreCase(РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile.trim()))
                || Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.equalsIgnoreCase("Mobile")   || Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.equalsIgnoreCase("WIFI")){
                    ///
                    Log.d(getApplicationContext().getClass().getName(), " Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile  " + "--" + Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile);/////
                    ////
                    РезультатПРоверкиПодключениеWIFI=true;
                }else {
                    ///
                    Log.d(getApplicationContext().getClass().getName(), " РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile  " + "--" + РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile);/////
                    //
                    РезультатПРоверкиПодключениеWIFI=false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        //TODO ВОЗВРАЩЯЕМ НУЖНО ПОДКЛЮЧАТЬ АУНТИВИКАУИЮ ИЛИ НЕТ
        return РезультатПРоверкиПодключениеWIFI;
    }

}








