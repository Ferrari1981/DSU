package com.dsy.dsu.For_Code_Settings_DSU1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Send_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


//import static com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT.ПУбличныйИмяТаблицыОтАндройда;
//import static com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT.ИменаТаблицыОтАндройда;

public class MainActivity_Face_Start extends AppCompatActivity {

    private    ProgressBar ПрогрессБарНаFace;
    private  String    ПубличноеИмяПользовательДлСервлета=         new String();
    private  String      ПубличноеПарольДлСервлета=         new String();
    private  CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;
    private  Context КонтекстДляFAceapp;
    private  Activity activity;
    private  int ПубличныйIDТекущегоПользователя=0;
    private Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private Class_GRUD_SQL_Operations classGrudSqlOperations;
    private   SQLiteCursor Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней=null;
    private  boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции =false;
    private SQLiteDatabase sqLiteDatabaseСамаБазы;
    // TODO: 24.02.202
    private   Handler HandlerДляПоказаПользователюЗагрузки ;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
try{
    setContentView(R.layout.activity_main__face);
    Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
    sqLiteDatabaseСамаБазы=Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу();
activity=this;
        КонтекстДляFAceapp=this;
        ((Activity) КонтекстДляFAceapp) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////todo настрока экрана
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(null);
       Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
        ПрогрессБарНаFace =(ProgressBar)  findViewById(R.id.progressBarFace); ////програссбар при аунтификации при входе в системму
        ///TODO попытка открыть экран как full screan
        getSupportActionBar().hide(); ///скрывать тул бар
      /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ((Activity) КонтекстДляFAceapp) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    HandlerДляПоказаПользователюЗагрузки = new Handler();
    preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    // TODO: 24.02.2022
    МетодФинальноеВизаульноеПрогрессБар();
    Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString());

    // TODO: 07.12.2022  test code
} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
            Thread.currentThread().getStackTrace()[2].getLineNumber());

    // TODO: 11.05.2021 запись ошибок
    new Class_Send_Generation_Errors(getApplicationContext(), e.toString(), activity);
    Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
    }

    }


    private void МетодФинальноеВизаульноеПрогрессБар() {
        /////TODO присваиваем наверх факсическое значение идущего цикла
        try {
        HandlerДляПоказаПользователюЗагрузки = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // TODO: 24.02.2022
                // TODO: 24.02.2022
                ПрогрессБарНаFace.setProgress(msg.what);
                // TODO: 24.02.2022
                ПрогрессБарНаFace.setVisibility(View.VISIBLE);// по умолчанию прогресс бар делаем не видеым

                Log.d(this.getClass().getName(), "  ИндексПрогрессБара нижний msg.what " +msg.what);

            };
        };

    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок
        new Class_Send_Generation_Errors(getApplicationContext(), e.toString(), activity);
        Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
    }
    }


    //TODO метод пользовательской дополнительной настройки автозапуса

    private void МетодДополнительнойНастрокиАвтоЗапуска() {

try{


    final Intent[] AUTO_START_INTENTS = {
            new Intent().setComponent(new ComponentName("com.samsung.android.lool",
                    "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(
                    Uri.parse("mobilemanager://function/entry/AutoStart"))
    };



    for (Intent intent : AUTO_START_INTENTS) {
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            startActivity(intent);
           // break;
        }

    }


} catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


















    @Override
    protected void onStart() {
        super.onStart();
        try{

            МетодСозданиеПрограссБара();////ЗАПУСКАЕМ МЕТОД ОПРЕДЕЛЕНИЯ ЗАХОДИЛ ЛИ ПОЛЬЗОВАТЕЛЬ В БАЗУ НЕДЕЛЮ НАЗАД ИЛИ СТАРШЕ И ПЛЮС ПРОГРЕССБАР

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }








/////////////////ЗАПУСКАЕМ ПРОГРЕСС БАР


    private void МетодСозданиеПрограссБара() throws ExecutionException, InterruptedException {

        try {

            ///TODO Создаем Пул потоков Собственого
            //////TODO организация работы потоков на данном активити
           // ПрогрессБарНаFace.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
            ПрогрессБарНаFace.getProgressDrawable().setColorFilter(  Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            Thread ПотокДляПрогрессБараПриВходе =    new Thread(new Runnable() {
                public void run() {
                    //////TODO код в потоке  ВИЗУАЛИЗАЦИЯ
                    long ФиналПолучаемРазницуМеждуДатами=0l;
                        try {
                            РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции=         МетодПингаКСереруЗапущенЛиСерерИлиНет();
                            //TODO ФУТУРЕ ЗАВЕРШАЕМ
                            Log.d(this.getClass().getName(), "  РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции " 
                                    + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции + " ПубличныйIDТекущегоПользователя " +ПубличныйIDТекущегоПользователя);
                            HandlerДляПоказаПользователюЗагрузки.sendEmptyMessage(ПубличныйIDТекущегоПользователя);

                            //TODO ФУТУРЕ ЗАВЕРШАЕМ
                            Log.d(this.getClass().getName(), "  HandlerДляПоказаПользователюЗагрузки.getLooper() "
                                    +HandlerДляПоказаПользователюЗагрузки.getLooper());
                            HandlerДляПоказаПользователюЗагрузки.removeMessages(1);
                            //TODO ФУТУРЕ ЗАВЕРШАЕМ
                            Log.d(this.getClass().getName(), "  РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции "
                                    + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции + " ПубличныйIDТекущегоПользователя " +ПубличныйIDТекущегоПользователя);
                        } catch (InterruptedException | ExecutionException | TimeoutException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                            //  Block of code to handle errors
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        /////TODO присваиваем наверх факсическое значение идущего цикла
                        ////

                    ////TODO ПОСЛЕ ВСЕГО ПРОГРАСС БАРА ВЫХОДИМ ИЗ ПРИЛОЖЕНИЯ





                            Log.d(this.getClass().getName(), "МетодПроыеркиАунтификацииНа7Дней");

                            try {



                                Log.d(this.getClass().getName(), "ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);





                                //TODO КОД КОГДА ПОЛЬЗОВАТЕЛЬ ВСЕ РАБОТАЕТ
                                МетодОпределениеКогдаПоследнийРазЗаходилПользователь();////ЗАПУСКАЕМ






                                //TODO ПЕРВАЯ ЧАСТЬ  СИХРОНИЗАЦИИ  ПОЛУЧАЕМ ID
                        Integer РезультатПолученныйПубличныйID=     МетодЗаполенениеПубличногоIDПриРаботеОфлайн();

                        /////
                                Log.d(this.getClass().getName(), "РезультатПолученныйПубличныйID " +РезультатПолученныйПубличныйID);


                                ///////TODO ПОСЛЕ ОПРЕДЕЛЯ КОГДА СОТРУДНИКА ЗАХОДИЛ ИДЕТ НА ДВА ПУТИ ПОЛЬЗОВАТЛЬ ПОДТРВЕРЖАЕТ СОВЕ ИМЯ И ПАРОЛЬИ ИЛИ МЫ СРРАЗУ ЗАРПУСКАМ ПРОГРАММУ
                                МетодВизуальногоПодтвержденияКогдаКтоВходит(ФиналПолучаемРазницуМеждуДатами);
                                ////


                                /////
                                Log.d(this.getClass().getName(), "РезультатПолученныйПубличныйID " + РезультатПолученныйПубличныйID);


                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

                            }

                    ///TODO тест
                }

                //////todo
            });
            ///
            ПотокДляПрогрессБараПриВходе.start();
            /////TODO   конец комадны после Handler
            ////TODO  КОНЕЦ КОД ДВА В ОДНОМ ДЛЯ ЗАМЕНЫ ASYNTASKОВ

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

// TODO: 24.02.2022




    private Boolean МетодПингаКСереруЗапущенЛиСерерИлиНет()
            throws ExecutionException, InterruptedException,
            TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции=false;


        try{
            // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
            РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции =
                    new Class_Connections_Server(getApplicationContext()).
                            МетодПингаСервераРаботаетИлиНет(getApplicationContext());

            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "  РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции "
                    + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции;
    }





















    /////// МЕТОД КОГДА ЗАХОДИЛ ПОСЛЬДНИЙ РАЗ ПОЛЬЗОВАТЛЬ
    private void  МетодОпределениеКогдаПоследнийРазЗаходилПользователь() throws ExecutionException, InterruptedException {

        ///TODO ЗАПУСКАЕМ  ПуллПамяти

        SQLiteCursor Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели = null;
 ///
        Integer ФиналПолучаемРазницуМеждуДатами =0;


                try{


/////САМ КОД КОТОРЫЙ ОПРЕДЕЛЯЕТ КОГДА ЧЕЛОВЕК ПОСЛЕДНИЙ РВЗ ЗАЗХОДИЛ

                      /////
                    System.out.println("КАКАЯ ТАБЛИЦА КОНКРЕТНАЯ ПОЛУЧИТ JSON ::::   " + "SuccessLogin");


            Class_GRUD_SQL_Operations             classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь =new Class_GRUD_SQL_Operations(getApplicationContext());
                        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                        ///
                    classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                        ///////
                    classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь
                            . concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","date_update");
                        ///////
                    classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");



                        //
                 /*       classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",null);"_id = ?  OR _id = ?  OR _id = ? "
                        ///"_id > ?   AND _id< ?"
                        //////
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","11");
                        ///
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                        //
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

                        ////TODO другие поля

            /*classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита",null);*/
                        ////

                        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ



                    // TODO: 12.10.2021  Ссылка Менеджер Потоков

                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());
                    ///////TODO



                        Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели= (SQLiteCursor)  classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь.
                                new GetData(getApplicationContext()).getdata(classGrudSqlOperationsОпределениеКогдаПоследнийРазЗаходилПользователь.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabaseСамаБазы);

                        Log.d(this.getClass().getName(), "GetData "+Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели  );

                        ////
                        // TODO: 30.08.2021  КОНЕЦ КОД ПОЛУЧЕНИЕ ДАННЫХ   ЧЕРЕЗ


                        ///ДАТА ПОСЛЕДНЕЙ АУНТИФИКАЦИИ ПОЛЬЗОВАТЕЛЯ
                        if (Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.getCount() > 0) {/////ПРОВЕРЯЕМ ЕСЛИ ПО ДАННОМУ ID UUID ЗАПОЛНЕ ЛИ ОН
                            ///
                            Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.moveToFirst();

                            ////
                            String ПолеСуществетЛиДата = Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.getColumnName(0);

                            ///////
                            if (ПолеСуществетЛиДата.trim().equalsIgnoreCase("date_update")) {

                                String СамаПолученнаяДатаИзТАлблицыВерсияДанных = Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.getString(0);

                                Log.d(this.getClass().getName(), "  ПолеСуществетЛиДата  " +ПолеСуществетЛиДата + "      СамаПолученнаяДатаИзТАлблицыВерсияДанных  " +СамаПолученнаяДатаИзТАлблицыВерсияДанных);

                                if (СамаПолученнаяДатаИзТАлблицыВерсияДанных != null) { //еслия дата в базе андройд вооще существет то начинаем сравниея ,если нет по не стравниваем и точно идем на ауттификацию приложения



                                    Date ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя=null;

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        try {
                                            ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя =
                                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(СамаПолученнаяДатаИзТАлблицыВерсияДанных);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            // TODO: 02.08.2021
                                            ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя =
                                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru")).parse(СамаПолученнаяДатаИзТАлблицыВерсияДанных);
                                        }

                                    }else {

                                        try {
                                            ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя =
                                                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(СамаПолученнаяДатаИзТАлблицыВерсияДанных);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            // TODO: 02.08.2021
                                            ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя =
                                                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru")).parse(СамаПолученнаяДатаИзТАлблицыВерсияДанных);
                                        }


                                    }

                                    Log.d(this.getClass().getName()," ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя "
                                            +ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя.toString());


                                    /////Дата (сегодня) с которй нужно сравнить полученную дату из базы андройда
                                    Date ДатаСегодня = Calendar.getInstance().getTime();

                                    DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));//"yyyy-MM-dd'T'HH:mm:ss'Z'

                                    String ДатСегоднявВидеТекста = dateFormat.format(ДатаСегодня);



                                    Date ДатаСегодняДляПроверки =null;

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                        try {
                                            ДатаСегодняДляПроверки = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(ДатСегоднявВидеТекста);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            // TODO: 02.08.2021
                                            ДатаСегодняДляПроверки = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru")).parse(ДатСегоднявВидеТекста);
                                        }

                                    }else {

                                        try {
                                            ДатаСегодняДляПроверки = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru")).parse(ДатСегоднявВидеТекста);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            // TODO: 02.08.2021
                                            ДатаСегодняДляПроверки = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("ru")).parse(ДатСегоднявВидеТекста);
                                        }


                                    }

                                    Log.d(this.getClass().getName()," ДатаВерсииДанныхНаSqlServer " +ДатаСегодняДляПроверки.toString());




                                    ////TODO само сравнивание дат на 7 дней назад
                                    long ОтнимаемОтОднойДатыОтДатыСегодня = ДатаСегодняДляПроверки.getTime() - ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя.getTime(); //локальное сравнение дата из базы андройда и дат сегодня
                                   ///////////
                                    ФиналПолучаемРазницуМеждуДатами = Integer.parseInt("" + (TimeUnit.DAYS.convert(ОтнимаемОтОднойДатыОтДатыСегодня, TimeUnit.MILLISECONDS)));


                                   /////////////
                                    Log.d(this.getClass().getName(), " ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя " + ДатаПолученнаяИзТаблицыВерсияДанныхАндройдаДляПроверкиПользователя +
                                            " ДатаСегодняДляПроверки " + ДатаСегодняДляПроверки
                                            + " Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.getCount() " + Курсор_ДляОпрелеленияЗаходилЛиПользовательДольшеНедели.getCount() +
                                            "  ФиналПолучаемРазницуМеждуДатами : " + ФиналПолучаемРазницуМеждуДатами);
                                    //// конец само сравнивание дат на 7 дней назад
                                } else {
                                    // TODO: 07.09.2021
                                    ///
                                    Log.d(this.getClass().getName(), " Сотрдник не захол никогда в программу   в таблице SuccessLogin  пообще отсуттвует дата NULL СамаПолученнаяДатаИзТАлблицыВерсияДанных " +
                                            new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу().getVersion());
                                } } }


                    //TODO ЗАПУСКАЕМ ФУТУРЕ

                    МетодВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней(ФиналПолучаемРазницуМеждуДатами);


                    ///


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


    }














    //todo ВЫТАСКИВАЕМ ДАННЫЕ ДЛЯ АУНТИФИКАЦИИ МЕНЕЕ 7 ДНЕЙ

    private void МетодВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней(  @NotNull long ФиналПолучаемРазницуМеждуДатами ) {/////МЕТОД ПОЛУЧЕНИЕ ИЗ БАЗЫ ИМЯ И ПАРОЛЬ ДЛЯ АУНТИФИКАЦИИ МЕНЕЕ 7 ДНЕЙ


        ////
     Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней=null;


        try {



          Class_GRUD_SQL_Operations   classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней =new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id,success_users,success_login");
            ///////
            classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");



            //
                 /*       classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",null);"_id = ?  OR _id = ?  OR _id = ? "
                        ///"_id > ?   AND _id< ?"
                        //////
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","11");
                        ///
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                        //
                        classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /*classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки",null);
            ////
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита",null);*/
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней=null;


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());


            /////

            Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней= (SQLiteCursor)  classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней.
                    new GetData(getApplicationContext()).getdata(classGrudSqlOperationsВытаскиемДанныеИзКурсораДляАунтификацииМенне7Дней. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,sqLiteDatabaseСамаБазы);

            Log.d(this.getClass().getName(), "GetData " +Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней );

            //////



            //TODO  ПУБЛИЧНЫЙ ЛОГИН и ПАРОЛЬ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(getApplicationContext());
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

          Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());


            ///////
            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,sqLiteDatabaseСамаБазы);

            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                ////
                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                /////
                ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();

                /////
                ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();



                Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета=         " + ПубличноеИмяПользовательДлСервлета+ " ПубличноеПарольДлСервлета" +ПубличноеПарольДлСервлета);
            }


            ///"SELECT id,success_users,success_login   FROM SuccessLogin  WHERE id=?", "1"
                ///////////////////////х( "SuccessLogin", "date_update","id","=","1",null,null,null,null );
                if (Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.getCount() > 0) {/////ПРОВЕРЯЕМ ЕСЛИ ПО ДАННОМУ ID UUID ЗАПОЛНЕ ЛИ ОН
                    ///
                    Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.moveToFirst();

                    /////
                    ПубличноеИмяПользовательДлСервлета = Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.getString(1).trim();
                    /////////////////////////

                  ПубличноеПарольДлСервлета = Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.getString(2).trim();
                    ////////////////////////////////
                    Log.d(this.getClass().getName(), " Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней  " + Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.getCount() +
                            " ПубличноеИмяПользовательДлСервлета " + ПубличноеИмяПользовательДлСервлета +
                            "  ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);
                }




            /////////////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }










    ///////todo ФИНАЛЬНЫЙ МЕТОД КТО ВХОДИЛ ДО 7 ДНЕЙ ИЛИ ПОСЫЛАЕМ НА АУНТИФИКАЦИЮ
    private void МетодВизуальногоПодтвержденияКогдаКтоВходит(long ФиналПолучаемРазницуМеждуДатами ) {


          try{
        // Toast.makeText(getApplicationContext(), "Конец Загрузки", Toast.LENGTH_SHORT).show();
        Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "+
              ПубличноеИмяПользовательДлСервлета+" ПубличноеПарольДлСервлета " +ПубличноеПарольДлСервлета+
                " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);
// todo ПРОВЕРЯЕМ РЕЗУЛЬТАТ ПРОВЕРКИ НА ДАТУ КОГДА ПОСЛДЕНИЙ РАЗ ЗАХОДИЛ ПОЛЬЗОВАТЕЛЬ В ПРОГАММУ ЕСЛИ МЕНЕЕ НЕДЕЛИ НАЗАД ТО НЕ  ПРОВОДИМ АУТНИФИКАЦИЮ ( ИМЯ И ПАРОЛЬ НА СЕРВРЕР)
        if ((long)  ФиналПолучаемРазницуМеждуДатами <7
                && Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.getCount()>0 ) { //ЕСЛИ МЕНЬШЕ СЕМИ ИЛИ РАВНО ТО НЕ оригинально <7
            // ПРОВОДИМ АУНТИФИКАЦИЮ //// if ((long) o<=7) {   ЦИФРА 7 ПОКАЗЫВАЕТ ОГРАНИЧЕНИЯ ЧТО ДЕЛАТЬ ПРОХОДИТИ АУНТИФИКАЦИЮ
/////TODO запускам СРАЗУ СИНХРНИЗАЦИЮ НЕ ПЕРВЫЙ ЗАПУСК ПРИЛДОЖЕНИЯ
            /////TODO запускам ПРОВЕРКУ ПОЛЬЗОВАТЕЛЯ И ПАРОЛЬ  ДЛЯ ВХОДА В СИСТЕМУ
            Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "+
                   ПубличноеИмяПользовательДлСервлета+" ПубличноеПарольДлСервлета " +ПубличноеПарольДлСервлета+
                    " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);
                Intent Интент_ЗапускаетFaceApp= new Intent();
                /////данные с потока
                Интент_ЗапускаетFaceApp.putExtra("РежимЗапускаСинхронизации","ПовторныйЗапускСинхронизации");
            // TODO: 01.12.2022   Записываем дама
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации","ПовторныйЗапускСинхронизации");
            editor.commit();
                /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
            if (РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции==true) {
                //ПрогрессБарНаFace.getIndeterminateDrawable().setColorFilter(Color.parseColor("#00ACC1"), android.graphics.PorterDuff.Mode.MULTIPLY);
                ПрогрессБарНаFace.getProgressDrawable().setColorFilter(  Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                ПрогрессБарНаFace.setMax(100);
                /////TODO ПОСЛЕ СИНХРОНИЗАЦИИ УСТАНАВЛИВАЕ НА ГОРИЗОНТАЛЬНЫЙ PROGRASBAR ЗНАЧЕНИЯ
                ПрогрессБарНаFace.setProgress(100);
                Интент_ЗапускаетFaceApp.setClass(getApplicationContext(),  MainActivity_Visible_Async.class); //MainActivity_Visible_Async //MainActivity_Face_App
                Log.d(this.getClass().getName(), " Нет Связи с сервером  Face_Start "
                        + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции);
            } else {
                ПрогрессБарНаFace.getProgressDrawable().setColorFilter(  Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);
                ПрогрессБарНаFace.setMax(100);
                /////TODO ПОСЛЕ СИНХРОНИЗАЦИИ УСТАНАВЛИВАЕ НА ГОРИЗОНТАЛЬНЫЙ PROGRASBAR ЗНАЧЕНИЯ
                ПрогрессБарНаFace.setProgress(100);
                Интент_ЗапускаетFaceApp.setClass(getApplicationContext(),  MainActivity_Face_App.class); //MainActivity_Visible_Async //MainActivity_Face_App
                Log.d(this.getClass().getName(), " Нет Связи с сервером  Face_Start "
                        + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции);
                МетодСообщениеПользоватлюЧтоНЕтИнтренета("Режим: (офлайн)");

            }
                Интент_ЗапускаетFaceApp.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_FROM_BACKGROUND);//////FLAG_ACTIVITY_SINGLE_TOP
                startActivity(Интент_ЗапускаетFaceApp);
                ////TODO ДАННАЯ КОМАНДА ПЕРЕКРЫВАЕТ НЕ ЗАПУСКАЕМОЕ АКТИВТИ А АКТИВТИ КОТОРЕ ЕГО ЗАПУСТИЛО
///todo КОГДА ПЕРВЫЙ ЗАПУСК ПРОГРАММЫ ИЛИ ПОЛЬЗОВАТЕЛЬ СНАЧАЛА АУНТИФТИКАЦИЯ  И ЕСЛИ ОНА УСПЕШНО ТО ТОГДА САМО ПРИЛОЖЕНИЕ
        }else{////ПРОВОДИМ АУНТИФИКАЦИЮ ПОЛЬЗОВАТЕЛЯ


/////TODO запускам ПРОВЕРКУ ПОЛЬЗОВАТЕЛЯ И ПАРОЛЬ  ДЛЯ ВХОДА В СИСТЕМУ  ПЕРВЫЙ ВХОД
            ///TODO принудительно устанвливаем редим работы синхронизации
            Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "+ПубличноеИмяПользовательДлСервлета+" ПубличноеПарольДлСервлета " +ПубличноеПарольДлСервлета+
                    " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);


            Log.d(this.getClass().getName(), " Нет Связи с сервером  Face_Start "
                    + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции);

            //// TODO ЗАПУСКАЕМ СНАЧАЛА АУНТИФКАУИЮ И ЕСЛИ УСПЕШНО ЗАПУСКАМ ДАННЫЕ   -----ЭТО ПЕРВЫЙ ЗАПУСК ПРИЛОЖЕНИЯ
            Intent Интент_ЗапускПроверкиАунтификацииЕслиПользоваьельЗаходилДавновПрограмму=new Intent();
            Интент_ЗапускПроверкиАунтификацииЕслиПользоваьельЗаходилДавновПрограмму.putExtra("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
            Интент_ЗапускПроверкиАунтификацииЕслиПользоваьельЗаходилДавновПрограмму.setClass(getApplicationContext(), MainActivity_Tabels_Users_And_Passwords.class);/////
            Интент_ЗапускПроверкиАунтификацииЕслиПользоваьельЗаходилДавновПрограмму.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_FROM_BACKGROUND);///

            // TODO: 01.12.2022   Записываем дама
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
            editor.commit();
            startActivity(Интент_ЗапускПроверкиАунтификацииЕслиПользоваьельЗаходилДавновПрограмму);
            ////TODO ДАННАЯ КОМАНДА ПЕРЕКРЫВАЕТ НЕ ЗАПУСКАЕМОЕ АКТИВТИ А АКТИВТИ КОТОРЕ ЕГО ЗАПУСТИЛО
                Log.d(this.getClass().getName(), " Нет Связи с сервером  Face_Start "
                        + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции);

                МетодСообщениеПользоватлюЧтоНЕтИнтренета("Первый запукск...");

        }

              Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.close();

              finish();

              ///КОНЕЦ ЗАПОЛЕНИЯ ТАБЕЛЯ ИЗ ДАННЫХ
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void МетодСообщениеПользоватлюЧтоНЕтИнтренета(String КакойРежимОтоброжать) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ///

                ПрогрессБарНаFace.setIndeterminate(false);

                Toast toast=       Toast.makeText(getApplicationContext(),КакойРежимОтоброжать , Toast.LENGTH_LONG);

                toast.setGravity(Gravity.BOTTOM,0,40);
                toast.show();


            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ////*/
        if (Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней!=null) {
            /////////
            if (!Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.isClosed()) {
                //////
                Курсор_ДляПолучениеИМяИПарольДЛяПодключениеКСерверуБолееСемиДней.close();
            }
        }
    }











/////TODO НУДЖНЫЙ метод начало получение ID С Сервера Для ДОЛЬНЕЙШЕГО ЗАПУСКА СИНХРОНИЗАЦИИИ  ПУБЛИЧНЫЙ ID ОТ СЕРВЕРА

    Integer МетодЗаполенениеПубличногоIDПриРаботеОфлайн() throws ExecutionException, InterruptedException {
        SQLiteCursor Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн = null;
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        try{
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");
            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());
            SQLiteCursor            Курсор_ПолучаемПубличныйID= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,sqLiteDatabaseСамаБазы);
            if(Курсор_ПолучаемПубличныйID.getCount()>0){
                Курсор_ПолучаемПубличныйID.moveToFirst();
                ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемПубличныйID.getInt(0);
                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
            }
            //TODO ЕСЛИ ПУБЛИЧНОГО ID  НЕТ
            Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);
            if (ПубличноеIDПолученныйИзСервлетаДляUUID>0) {
                classGrudSqlOperations =new Class_GRUD_SQL_Operations(getApplicationContext());
                classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
                classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());
                Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                        new GetData(getApplicationContext()).getdata(classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        ,sqLiteDatabaseСамаБазы);
                Log.d(this.getClass().getName(), "GetData " +Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн );
                if (Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн.getCount() > 0) {
                    Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн.moveToFirst();
                    do {
                        Integer СамоЗначениеIDПриработаетОфлайн = Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн.getInt(0);
                        Log.d(this.getClass().getName(), " СамоЗначениеIDПриработаетОфлайн " + СамоЗначениеIDПриработаетОфлайн);
                       ПубличноеIDПолученныйИзСервлетаДляUUID = СамоЗначениеIDПриработаетОфлайн;
                        Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                        if(СамоЗначениеIDПриработаетОфлайн!=null){
                            break;
                        }
                    } while (Курсор_ВытаскиваемЗначениеПубличногоIDкогдаРабатаемОфлайн.moveToNext());
                }
            }
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПубличноеIDПолученныйИзСервлетаДляUUID;
    }
















}


