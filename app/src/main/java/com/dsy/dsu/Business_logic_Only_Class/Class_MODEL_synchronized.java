package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.NoSuchPaddingException;

import okio.Timeout;


///////Универсальный Класс Обмена Данными  Два Стачичных Метода и Плюс Сттичный Курсор
 public class Class_MODEL_synchronized extends CREATE_DATABASE {
  public     Context context;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private Class_MODEL_synchronized ссылка_MODELsynchronized = null;
    private String  ПубличноеИмяПользовательДлСервлета=      new String();
    private  String  ПубличноеПарольДлСервлета=   new String();
    ////шифрование
/*    public SecretKey ГлавныйКлючДляШифрованиеИРасшифровки;
    ////
    public Cipher ПолитикаШифрование;
    ///////
    public Cipher ПолитикаРасшифровки;*/
    public Integer ПубличноеIDПолученныйИзСервлетаДляUUID = 0;
    public CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    public Class_MODEL_synchronized(  @NotNull Context context) {
        super(context);
       this. context=context;
        //TODO контроль потоков
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(context);
      Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
    }








    //todo #GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET


    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public StringBuffer УниверсальныйБуферПолучениеДанныхсСервера(String ПУбличныйИмяТаблицыОтАндройдаВнутриПотока,
                                                                  String КонкретнаяТаблицаВПотокеВнутриПотока,
                                                                  String МакАдресТелефонаВнутриПотока,
                                                                  String ТипОтветаTEXTилиJSONВнутриПотока,
                                                                  String ЗаданиеДляСервлетаВнутриПотока,
                                                                  Long ВерсииДанныхНаАндройдеСерверная,
                                                                  String IDДляПолучениеКонткртнойНабораТаблиц
            , int ВремяЗакотороеСерверБудетЗагружатьДанные,
                                                                  String ФлагТОлькоДлПолученияСтрочекТаблицыССерера,
                                                                  Long РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                                                                  String ИмяСервера, Integer ИмяПорта) throws IOException,
            ExecutionException, InterruptedException, TimeoutException, NoSuchAlgorithmException,
            KeyManagementException, InvalidKeyException, NoSuchPaddingException {

        StringBuffer БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера = new StringBuffer();
        Integer РазмерПришедшегоОбщееКоличествоСтрочекJSONДляТаблицы=0;
        System.out.println("УниверсальныйБуферПолучениеДанныхсСервера");

        if (ФлагТОлькоДлПолученияСтрочекТаблицыССерера==null) {
            ФлагТОлькоДлПолученияСтрочекТаблицыССерера=new String();
        }
        ////////перевод перменных внутрь Анисакска
        ///////// ПРИ НУЛЕВОМ ЗАПУСКЕ ЛОВИМ ЭТОТ МОМЕНТ ДАТОЙ ИЗ ТАБЛИЦЫ АНДОЙД
        //////КОНЕЦ ЛОВЛИ НУЛОЙ ДАТЫ ТАБЛИЦЫ
        Object ОшибкаТекущегоМетода = new Object();
        GZIPInputStream GZIPПотокОтСЕРВЕРА = null;
        BufferedReader РидерОтСервераМетодаGET = null;
        ///
        try {
            ////
            String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            ///
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Adress_String = new String();
            ////
            String Params = new String();
            //PUBLIC_CONTENT.ПубличныйАдресGlassFish = "http://tabel.dsu1.ru:8888/"; //http://80.66.149.58:8888   //http://tabel.dsu1.ru/
            // Adress_String = "http://192.168.254.40:8080/dsu1.glassfish/DSU1JsonServlet";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58
            Adress_String = СтрокаСвязиСсервером +new PUBLIC_CONTENT(context).getСсылкаНаРежимСервера()+ "/DSU1JsonServlet";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58 /// dsu1.glassfish/DSU1JsonServlet
            Params = "?" + "ИмяТаблицыОтАндройда= " + ПУбличныйИмяТаблицыОтАндройдаВнутриПотока + "&" + "КонкретнаяТаблицаВПотоке=" + КонкретнаяТаблицаВПотокеВнутриПотока + ""
                    + "&" + "МакАдресТелефона=" + МакАдресТелефонаВнутриПотока + "" +
                    "&" + "ЗаданиеДляСервлетаВнутриПотока=" + ЗаданиеДляСервлетаВнутриПотока + "" + "&" + "ДатаНаДанныеВнутриПотока=" + ВерсииДанныхНаАндройдеСерверная + ""
                    + "&" + "IDДляПолучениеКонткртнойНабораТаблиц=" + IDДляПолучениеКонткртнойНабораТаблиц + ""
                    + "&" + "РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер=" + РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер + "";
            Log.d(this.getClass().getName(), " Params" + Params);
            ///////////
            Adress_String = Adress_String + Params;
            Log.d(this.getClass().getName(), "Adress_String " + Adress_String);
            Adress_String = Adress_String.replace(" ", "%20");
            Log.d(this.getClass().getName(), " Adress_String " + Adress_String);
            // TODO: 25.05.2021  адереса
            URL Adress = new URL(Adress_String);
            //TODO
                    HttpURLConnection ПодключениеПолученияДанныхсСервер = null;
                    ПодключениеПолученияДанныхсСервер = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
                   // ПодключениеПолученияДанныхсСервер.setDoInput(true);
                   // ПодключениеПолученияДанныхсСервер.setDoOutput(true);

                    ПодключениеПолученияДанныхсСервер.setRequestProperty("Content-Type", ТипОтветаTEXTилиJSONВнутриПотока + " ;charset=UTF-8");
                    ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                    ПодключениеПолученияДанныхсСервер.setRequestProperty("Connection", "Keep-Alive");
                    ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Language", "ru-RU");
                    ПодключениеПолученияДанныхсСервер.setRequestMethod("GET"); ///GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ
                    ПодключениеПолученияДанныхсСервер.setReadTimeout(ВремяЗакотороеСерверБудетЗагружатьДанные); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);
                    ПодключениеПолученияДанныхсСервер.setConnectTimeout(3000);//todo САМ ПОТОК ДАННЫХ(1000);
                    ПодключениеПолученияДанныхсСервер.setUseCaches(false);
                    //////////
                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                    ///
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");


                    // TODO: 12.10.2021  Ссылка Менеджер Потоков

                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);


                    ///////
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        ///
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                        /////
              ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();

                        /////
                        ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                    }




                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " +ПубличноеИмяПользовательДлСервлета +
                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);


                        // TODO: 11.11.2021  ПЕРЕДОТПРАВКОЙ ШИФРУЕМ ДАННЫЕ \
                        String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                        Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                        // TODO: 12.11.2021 ППЕРОБРАЗОВАНИЯ ПАРОЛЬЯ ЧЕРЕЗ BASE64 ПАРОЛЯ ВАРИАНТ 1
                        String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                        Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);

                        // TODO: 12.11.2021 КАШИРОВАНИЯ ПАРОЛЯ ВАРИАНТ 2

                        /////// TODO set login pasword
                        ПодключениеПолученияДанныхсСервер.setRequestProperty("p_identifier",
                                ЗашифрованныйПароль);  //"dsu1getsession"

                        ПодключениеПолученияДанныхсСервер.setRequestProperty("identifier",
                                ЗашифрованныйЛогин  );  //"dsu1getsession"   ПубличноеИмяПользовательДлСервлета


                    ПодключениеПолученияДанныхсСервер.connect(); /////////////ТОЛЬКО СОЕДИНЕНИЕ
                    //ПодключениеПолученияДанныхсСервер.getResponseCode();///ВАЖНАЯ КОМАНДА  СТУЧИТЬСЯ В СЕРВЛЕТ ECLIPSE СТУЧИМСЯ ВТОРОЙ РАЗ ЧТОБЫ ПОЛУЧИТЬ УЖЕ САМ JSON
                   ПодключениеПолученияДанныхсСервер.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА


            Log.d(this.getClass().getName(), "ПодключениеИнтернетДляОтправкиНаСервер.getContentLength() " + ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));

            Long РазмерПришедшегоПотока = Long.parseLong(ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));

            Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);

            Log.d(this.getClass().getName(), "ФлагТОлькоДлПолученияСтрочекТаблицыССерера " + ФлагТОлькоДлПолученияСтрочекТаблицыССерера);


            ///
         //   HttpURLConnection finalПодключениеПолученияДанныхсСервер = ПодключениеПолученияДанныхсСервер;

            Object finalОшибкаТекущегоМетода = ОшибкаТекущегоМетода;

            Object finalОшибкаТекущегоМетода1 = ОшибкаТекущегоМетода;

            String finalФлагТОлькоДлПолученияСтрочекТаблицыССерера = ФлагТОлькоДлПолученияСтрочекТаблицыССерера;

            Integer finalРазмерПришедшегоОбщееКоличествоСтрочекJSONДляТаблицы = РазмерПришедшегоОбщееКоличествоСтрочекJSONДляТаблицы;
            /////////

                    ////TODO И ЕСЛИ ПРИШЕЛ ОТ СЕРВЕРА ОТВЕТ ПОЛОЖИТЕЛЬНО ТО ТОГДА ЗАПУСКАМ ПРОЧТЕНИЯ ПОТОКА ПРИШЕДШЕГО С СЕРВЕРА
             
                        if (ПодключениеПолученияДанныхсСервер.getResponseCode() == 200 && РазмерПришедшегоПотока > 0) {
                            //TODO шифровани
                            // Log.d(this.getClass().getName(), "  ПолитикаРасшифровки  " +PUBLIC_CONTENT. ПолитикаРасшифровки);


                            Log.d(Class_MODEL_synchronized.class.getName(), "  СЛУЖБА СИНХРОНИЗАЦИИ РАЗМЕР ПОТОКА КОТОРЫЙ ПРИШЁЛ ... " + "\n" +
                                    " ПУбличныйИмяТаблицыОтАндройдаВнутриПотока  ::::::  " + ПУбличныйИмяТаблицыОтАндройдаВнутриПотока + "\n" +
                                    " ПодключениеИнтернетДляОтправкиНаСервер.getContentLength()  " + ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));//  "   ПодключениеИнтернетДляОтправкиНаСервер.getHeaderField(Content-Length "+



                                //////тест шифрование
                                GZIPПотокОтСЕРВЕРА = new GZIPInputStream(ПодключениеПолученияДанныхсСервер.getInputStream());
                                ///// todo получаем данные с сервера
                                //GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(ПодключениеИнтернетДляОтправкиНаСервер[0].getInputStream(),Deflater.BEST_COMPRESSION);///byte[] data = new byte[512];
                                РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//


                            ///
                            Log.d(this.getClass().getName(), "finalФлагТОлькоДлПолученияСтрочекТаблицыССерера " + finalФлагТОлькоДлПолученияСтрочекТаблицыССерера+
                                    "  КонкретнаяТаблицаВПотокеВнутриПотока " +КонкретнаяТаблицаВПотокеВнутриПотока);


                            // TODO: 14.09.2021  через движок

                            BufferedReader finalРидерОтСервераМетодаGET = РидерОтСервераМетодаGET;
                            /////

                                            // TODO: 14.05.2021 получаем данные с сервера
                                    /////НАЗВАНИЕ ПОТОКА
                                    Log.i(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);

                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {



                                        ///TODO ПЕРВЫЙ ВАРАНТ РАСПАРСИВАНИЯ ПРИШЕДШЕГО JSON ПОТОКА С СЕРВРА
                                        БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера
                                            = finalРидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                            StringBuffer::append);



                                            /////НАЗВАНИЕ ПОТОКА
                                            Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase() + " БуферПолученнниеДанныхОтМетодаGET "
                                                    + БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString()+"\n"+
                                                     "  БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.length() "
                                                    +БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.length());


                                    }else {
                                        ///TODO ПЕРВЫЙ ВАРАНТ РАСПАРСИВАНИЯ ПРИШЕДШЕГО JSON ПОТОКА С СЕРВРА
                                        БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.append(finalРидерОтСервераМетодаGET.readLine());


                                        /////НАЗВАНИЕ ПОТОКА
                                        Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase() + " БуферПолученнниеДанныхОтМетодаGET "
                                                + БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString()+"\n"+
                                                "  БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.length() "
                                                +БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.length());

                                    }

                                    ///
                                    Log.d(this.getClass().getName(), " БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString()"
                                            + БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString());/////



                                    if (БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера != null &&
                                            БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString().length() > 0) {
                                        ///
                                        /////НАЗВАНИЕ ПОТОКА
                                        Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase() + " БуферПолученнниеДанныхОтМетодаGET "
                                                + БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString());
                                    }



                            /////НАЗВАНИЕ ПОТОКА
                            Log.i(this.getClass().getName(), "БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера" +
                                    БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера.toString());

                            ////TODO нет ответа от сервер или поток нулевой
                        } else {
                            Log.i(this.getClass().getName(), "ПОТОК ПРИШЕЛ НУЛОВОЙ ОТ СЕРВЕРА  " + ПодключениеПолученияДанныхсСервер.getInputStream().available());
                        }
                        ////////

                    // TODO: 04.08.2021  end
            if (GZIPПотокОтСЕРВЕРА!=null) {
                GZIPПотокОтСЕРВЕРА.close();
            }
            ////
            if (РидерОтСервераМетодаGET!=null) {
                РидерОтСервераМетодаGET.close();
            }


            ПодключениеПолученияДанныхсСервер.disconnect();
                        ////

        } catch (IOException ex) {
            ex.printStackTrace();
            ///метод запись ошибок в таблицу
            ОшибкаТекущегоМетода = ex.toString();

            if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        ////////
        // TODO: 21.10.2021
        //// todo get ASYNtASK
        return (StringBuffer) БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера;

    }

    // TODO: 04.08.2021 HEAD HEAD


    //todo #GET     //#GET  только для ПИНГА     //#GET  только для ПИНГА  //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА


    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public Integer УниверсальныйБуферПолучениеДанныхсСервераТОлькоДляПинга(String ПУбличныйИмяТаблицыОтАндройдаВнутриПотока,
                                                                           String КонкретнаяТаблицаВПотокеВнутриПотока,
                                                                           String МакАдресТелефонаВнутриПотока,
                                                                           String ТипОтветаTEXTилиJSONВнутриПотока,
                                                                           String ЗаданиеДляСервлетаВнутриПотока,
                                                                           Long ВерсииДанныхНаАндройдеСерверная,
                                                                           String IDДляПолучениеКонткртнойНабораТаблиц
            , int ВремяЗакотороеСерверБудетЗагружатьДанные,
                                                                           String ФлагТОлькоДлПолученияСтрочекТаблицыССерера,
                                                                           Long РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                                                                           String ИмяСервера, Integer ИмяПорта) throws IOException,
            ExecutionException, InterruptedException, TimeoutException, NoSuchAlgorithmException,
            KeyManagementException, InvalidKeyException, NoSuchPaddingException {

        ////// TODO созданеи шифрование
   /*     if (ГлавныйКлючДляШифрованиеИРасшифровки == null) {

            //TODO ключ для шифрование и расщифровки

            byte[] CipherKey = "[C@3841f624[B@6415a86b[B@143c678".getBytes();

            ГлавныйКлючДляШифрованиеИРасшифровки =
                    new SecretKeySpec(CipherKey,
                            "AES");
            ПолитикаШифрование = Cipher.getInstance("AES");

            ПолитикаШифрование.init(Cipher.ENCRYPT_MODE, ГлавныйКлючДляШифрованиеИРасшифровки);
            ///////
            ПолитикаРасшифровки = Cipher.getInstance("AES");

            ПолитикаРасшифровки.init(Cipher.DECRYPT_MODE, ГлавныйКлючДляШифрованиеИРасшифровки);
            ///// конец шифрование
        }*/
        Integer РазмерПришедшегоПотока = 0;
        Integer РазмерПришедшегоОбщееКоличествоСтрочекJSONДляТаблицы=0;
        System.out.println("УниверсальныйБуферПолучениеДанныхсСервера");
        if (ФлагТОлькоДлПолученияСтрочекТаблицыССерера==null) {
            ФлагТОлькоДлПолученияСтрочекТаблицыССерера=new String();
        }
        Object ОшибкаТекущегоМетода = new Object();
        try {
            String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Adress_String = new String();
            ////
            String Params = new String();
            //PUBLIC_CONTENT.ПубличныйАдресGlassFish = "http://tabel.dsu1.ru:8888/"; //http://80.66.149.58:8888   //http://tabel.dsu1.ru/
            // Adress_String = "http://192.168.254.40:8080/dsu1.glassfish/DSU1JsonServlet";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58
            Adress_String = СтрокаСвязиСсервером +new PUBLIC_CONTENT(context).getСсылкаНаРежимСервера()+ "/DSU1JsonServlet";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58 /// dsu1.glassfish/DSU1JsonServlet
            Params = "?" + "ИмяТаблицыОтАндройда= " + ПУбличныйИмяТаблицыОтАндройдаВнутриПотока + "&" + "КонкретнаяТаблицаВПотоке=" + КонкретнаяТаблицаВПотокеВнутриПотока + ""
                    + "&" + "МакАдресТелефона=" + МакАдресТелефонаВнутриПотока + "" +
                    "&" + "ЗаданиеДляСервлетаВнутриПотока=" + ЗаданиеДляСервлетаВнутриПотока + "" + "&" + "ДатаНаДанныеВнутриПотока=" + ВерсииДанныхНаАндройдеСерверная + ""
                    + "&" + "IDДляПолучениеКонткртнойНабораТаблиц=" + IDДляПолучениеКонткртнойНабораТаблиц + ""
                    + "&" + "РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер=" + РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер + "";
            Log.d(this.getClass().getName(), " Params" + Params);
            Adress_String = Adress_String + Params;
            Log.d(this.getClass().getName(), "Adress_String " + Adress_String);
            Adress_String = Adress_String.replace(" ", "%20");
            Log.d(this.getClass().getName(), " Adress_String " + Adress_String);
            URL Adress = new URL(Adress_String);
            HttpURLConnection ПодключениеПолученияДанныхсСервер = null;
            ПодключениеПолученияДанныхсСервер = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
            ПодключениеПолученияДанныхсСервер.setRequestProperty("Content-Type", ТипОтветаTEXTилиJSONВнутриПотока + " ;charset=UTF-8");
            ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            ПодключениеПолученияДанныхсСервер.setRequestProperty("Connection", "Keep-Alive");
            ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Language", "ru-RU");
            ПодключениеПолученияДанныхсСервер.setRequestMethod("GET"); ///GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ
            ПодключениеПолученияДанныхсСервер.setReadTimeout(ВремяЗакотороеСерверБудетЗагружатьДанные); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);
            ПодключениеПолученияДанныхсСервер.setConnectTimeout(ВремяЗакотороеСерверБудетЗагружатьДанные);//todo САМ ПОТОК ДАННЫХ(1000);
            ПодключениеПолученияДанныхсСервер.setUseCaches(false);
            /////////
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
            }
            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " +ПубличноеИмяПользовательДлСервлета +
                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);
                String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);
                /////// TODO set login pasword
                ПодключениеПолученияДанныхсСервер.setRequestProperty("p_identifier",
                        ЗашифрованныйПароль);
                ПодключениеПолученияДанныхсСервер.setRequestProperty("identifier",
                        ЗашифрованныйЛогин  );
                ПодключениеПолученияДанныхсСервер.connect(); /////////////ТОЛЬКО СОЕДИНЕНИЕ
                ПодключениеПолученияДанныхсСервер.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА
                РазмерПришедшегоПотока  = Integer.parseInt(ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));
                Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
                //todo дополнительный прозвон
            GZIPInputStream GZIPПотокОтСЕРВЕРА = null;
                BufferedReader РидерОтСервераМетодаGET = null;
                //////тест шифрование
                GZIPПотокОтСЕРВЕРА =new GZIPInputStream(ПодключениеПолученияДанныхсСервер.getInputStream());
                ///// todo получаем данные с сервера
                //GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(ПодключениеИнтернетДляОтправкиНаСервер[0].getInputStream(),Deflater.BEST_COMPRESSION);///byte[] data = new byte[512];
                РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                ///TODO ПЕРВЫЙ ВАРАНТ РАСПАРСИВАНИЯ ПРИШЕДШЕГО JSON ПОТОКА С СЕРВРА
            StringBuffer    БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера
                        = РидерОтСервераМетодаGET.lines() .collect(StringBuffer::new, (sb, i) -> sb.append(i),
                        StringBuffer::append);
                Log.d(this.getClass().getName(), "БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера " + БуферПолученнниеДанныхОтМетодаGETУниверсальныйБуферПолучениеДанныхсСервера);
            РидерОтСервераМетодаGET.close();
            GZIPПотокОтСЕРВЕРА.close();
            ПодключениеПолученияДанныхсСервер.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
            ///метод запись ошибок в таблицу
            ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        return РазмерПришедшегоПотока;
    }
















































///todo #POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST


    ///////метод ОТПРАВКИ ДАННЫХ НА СЕРВЕР
    public StringBuffer УниверсальныйБуферОтправкиДанныхНаСервера(@NonNull JSONObject СгенерированыйФайлJSONДляОтправкиНаСервер,
                                                                  Integer ПУбличныйИмяТаблицыМетодPOST,
                                                                  String ПубличноеИмяПользовательМетодPOST,
                                                                  String ЗаданиеДляСервлета,
                                                                  int ВремяЗакотороеСерверБудетЗагружатьДанные,
                                                                  String ИмяСервера, Integer ИмяПорта

    ) throws IOException, ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Log.d(Class_MODEL_synchronized.class.getName(), " СгенерированыйФайлJSONДляОтправкиНаСервер.length() " + СгенерированыйФайлJSONДляОтправкиНаСервер.toString().length());
        StringBuffer БуферПолученнниеДанныхОтМетодаPOST = new StringBuffer();
       HttpURLConnection ПодключениеИнтернетДляОтправкиНаСервер =null;
        String ОшибкаТекущегоМетода = new String();
                try {
                    String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
                    String Adress_String = new String();
                    // String Adress_String = "http://192.168.254.40:8080/dsu1.glassfish/DSU1JsonServlet";
                    Adress_String = СтрокаСвязиСсервером +new PUBLIC_CONTENT(context).getСсылкаНаРежимСервера()+ "/DSU1JsonServlet";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58 /// dsu1.glassfish/DSU1JsonServlet
                    String Params = new String();
                    Params = "?" + "ИмяТаблицыОтАндройда=" + ПубличноеИмяПользовательМетодPOST + "&" + "СотрудникТаблицыОтАндройда=" + ПУбличныйИмяТаблицыМетодPOST +
                            "&" + "ЗаданиеДляСервлетаВнутриПотока=" + ЗаданиеДляСервлета + "";
                    Adress_String = Adress_String + Params;
                    Adress_String = Adress_String.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " Adress_String " + Adress_String);
                    URL Adress = new URL(Adress_String);
                    Log.d(this.getClass().getName(), " Adress  " + Adress);
                    Class_GRUD_SQL_Operations class_grud_sql_operations;
                    class_grud_sql_operations=new Class_GRUD_SQL_Operations(context);
                            ПодключениеИнтернетДляОтправкиНаСервер= (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("Content-Type", "application/json ;charset=UTF-8");
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("Connection", "Keep-Alive");
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("Accept-Language", "ru-RU");
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestMethod("POST"); ///GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ
                            ПодключениеИнтернетДляОтправкиНаСервер.setReadTimeout(ВремяЗакотороеСерверБудетЗагружатьДанные); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);
                            ПодключениеИнтернетДляОтправкиНаСервер.setConnectTimeout(2000);//todo САМ ПОТОК ДАННЫХ(1000);
                            ПодключениеИнтернетДляОтправкиНаСервер.setUseCaches(false);
                            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                            ///
                            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                                    new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Log.i(this.getClass().getName(), " PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета " + ПубличноеИмяПользовательДлСервлета +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета   " +ПубличноеПарольДлСервлета);
                            String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                            Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                            String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(context).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                            Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("p_identifier",
                                    ЗашифрованныйПароль);
                            ПодключениеИнтернетДляОтправкиНаСервер.setRequestProperty("identifier",
                                    ЗашифрованныйЛогин  );
                            ПодключениеИнтернетДляОтправкиНаСервер.connect();
                    //////TODO ОТПРАВЛЕМ ДАННЫЕ НА СЕРВЕР ЕЛСИ ДАННЫЕ НОВЫЕ КАК ОТПАРВЯЕМ ТО ОБНЦЛЕМ БУФЕР С JSON АГА !!!!


                    try ( BufferedWriter БуферПосылаемМетодуPOSTJSONФайл = new BufferedWriter(new OutputStreamWriter(
                            new GZIPOutputStream(ПодключениеИнтернетДляОтправкиНаСервер.getOutputStream()), StandardCharsets.UTF_16));){
                        // TODO: 09.09.2022  отправляем
                        БуферПосылаемМетодуPOSTJSONФайл.write(СгенерированыйФайлJSONДляОтправкиНаСервер.toString());/// ЗАПИСЫВАЕМ В ПОТОК
                        БуферПосылаемМетодуPOSTJSONФайл.flush();///ПРОТАЛКИВАЕМ О
                        БуферПосылаемМетодуPOSTJSONФайл.close();
                        ПодключениеИнтернетДляОтправкиНаСервер.getResponseCode();///ЩЕЛКАЕМ КОННЕКТ ЧТОБЫ ОБРАТНО ПРИШЕЛ ОТВЕТ ОТ СЕРВЕРА
                        ПодключениеИнтернетДляОтправкиНаСервер.connect();
                        ПодключениеИнтернетДляОтправкиНаСервер.getContent();
                        // TODO: 09.09.2022  получаем
                        // TODO: 15.06.2021  вторая часть метода отпарвки данных
                        Log.d(this.getClass().getName(), "ПодключениеИнтернетДляОтправкиНаСервер.getContentLength() " + ПодключениеИнтернетДляОтправкиНаСервер.getHeaderField("stream_size"));
                        Long РазмерПришедшегоПотока = Long.parseLong(ПодключениеИнтернетДляОтправкиНаСервер.getHeaderField("stream_size"));
                        Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
                        ///////TODO ФИНАЛЬНАЯ ОПЕРАЦИЯ ОТВЕТ ТТ СЕРВЕЛТА С ПОЛОЖИТЕЛЬНЫМИ И ЛИИ ВСТВКАМИ ИЛИ ОБНОВЛЕНИЕ ОТ СЕРВЕРА ДЛЯ АНАЛИЗА В  ВСТАВКИ В БАЗУ КАК СТАТУС ОТПРАВЛЕННЫЙ
                        if (ПодключениеИнтернетДляОтправкиНаСервер.getResponseCode() == 200 && РазмерПришедшегоПотока > 0) {
                            Log.i(this.getClass().getName(), " ПодключениеИнтернетДляОтправкиНаСервер[0].getResponseCode() " + ПодключениеИнтернетДляОтправкиНаСервер.getResponseMessage() + "\n" +
                                    " \"ПодключениеИнтернетДляОтправкиНаСервер.getContentLength() " + ПодключениеИнтернетДляОтправкиНаСервер.getHeaderField("stream_size"));
                            BufferedReader         БуферОтветОтМетодаPOSTЕслиОбновлениеИлиВставка =
                                    new BufferedReader(new InputStreamReader(new GZIPInputStream(ПодключениеИнтернетДляОтправкиНаСервер.getInputStream()),
                                            StandardCharsets.UTF_16));
                            БуферОтветОтМетодаPOSTЕслиОбновлениеИлиВставка.lines().forEachOrdered((ДанныеИзСтрима)-> {
                                        БуферПолученнниеДанныхОтМетодаPOST.append(ДанныеИзСтрима);
                                        Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase() + " БуферПолученнниеДанныхОтМетодаPOST "
                                                + БуферПолученнниеДанныхОтМетодаPOST.toString());
                                    }
                            );
                            Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase() + " БуферПолученнниеДанныхОтМетодаPOST "
                                    + БуферПолученнниеДанныхОтМетодаPOST.toString());
                            БуферОтветОтМетодаPOSTЕслиОбновлениеИлиВставка.close();
                            ПодключениеИнтернетДляОтправкиНаСервер.disconnect();
                        }
                        Log.i(this.getClass().getName(), "  БуферПолученнниеДанныхОтМетодаPOST " + БуферПолученнниеДанныхОтМетодаPOST.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
        return (StringBuffer) БуферПолученнниеДанныхОтМетодаPOST;
    }








































//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR//# UNIVERSAL CURSOR


/*    //УНИВЕРСАЛЬНЫЙ КУРСОР С ПАРАМЕТРАМИ
    Cursor КурсорУниверсальныйДляБазыДанных(String таблица, String[] колонки, String фильтр, String[] условияфильтра, String групировка, String хэвинг, String ордербай, String лимиты)
            throws ExecutionException, InterruptedException, TimeoutException {
////




        ////
        Cursor Курсор_Универсальный = null;
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Log.i(this.getClass().getName(), "  колонки " + колонки.length);
        try {
            ////TODO сам курсор универсальный
            Курсор_Универсальный = ССылкаНаСозданнуюБазу.query(true, таблица, колонки, фильтр, условияфильтра, групировка, хэвинг, ордербай, лимиты); //todo включен dictict
            /////НАЗВАНИЕ ПОТОКА

            Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase()
                    + " Курсор_Универсальный строчки :   " + Курсор_Универсальный.getCount() + " Курсор_Универсальный колонки  : "
                    + Курсор_Универсальный.getColumnCount() + "  таблица " + таблица);
////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextСозданиеБАзы).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



        //// todo get ASYNtASK
        return Курсор_Универсальный;
    }*/






























/*
    //TODO УНИВЕРСАЛЬНЫЙ КУРСОР  БЕЗ ПАРАМЕТРАМИ


    Cursor КурсорУниверсальныйБазыДанных(String ПишемЧистыйSQL) throws ExecutionException, InterruptedException, TimeoutException {
        ////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        ////



        System.out.println(" КурсорУниверсальныйБазыДанных , null");
        Cursor Курсор_Универсальный = null;
        String ОшибкаТекущегоМетода;
        //  Cursor cursor = db.rawQuery("SELECT name, amount FROM accounts WHERE name = ?", new String[]{"Lucy"});
        try {
            Курсор_Универсальный = ССылкаНаСозданнуюБазу.rawQuery(ПишемЧистыйSQL, null);
            /////НАЗВАНИЕ ПОТОКА
            Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase());
            /////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            ОшибкаТекущегоМетода = e.toString();
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextСозданиеБАзы).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        //// todo get ASYNtASK
        return Курсор_Универсальный;

    }
*/




























    ////--- TODO ТУТ НАХОДЯТЬСЯ КОНТЕРЙНЕРЫ ДЛЯ ВСТАВКИ И ОБНОВЛЕНИИ ДАННЫХ  ЧЕРЕЗ КОНТЕЙНЕРЫ


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаВставляем,
                                                            ContentValues КонтейнерДляВставки,
                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                            boolean ФлагОбновлятьДатуВерсииДанных,
                                                            int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                            boolean СинхронизациюВизуализировать,
                                                            Context КонтекстСинхроДляКонтроллера,
                                                            @NotNull CompletionService МенеджерПотоков,
                                                            @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                            Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(context);
///

/////
        try {
            //

                try {

                    // TODO: 06.09.2021 параметры для вствки
                    class_grud_sql_operationsВставка.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                    ////



                    // TODO: 06.09.2021 контейнер для вставки
                    class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставка. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            КонтейнерДляВставки,
                            МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                    //////
                    if (Результат_ВставкиДанных > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                        ///
                        КонтейнерДляВставки.clear();
                        ////
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                                +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  );

                        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                        ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                        //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                    }



                    /////
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ////// начало запись в файл

                }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальнаяЧерезContentResolver(String ТаблицаКудаВставляем,
                                                                                ContentValues КонтейнерДляВставки,
                                                                                String ИмяТаблицыОтАндройда_Локальноая,
                                                                                String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                boolean ФлагОбновлятьДатуВерсииДанных,
                                                                                int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                                boolean СинхронизациюВизуализировать,
                                                                                Context context,
                                                                                @NotNull CompletionService МенеджерПотоков,
                                                                                @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                                Integer СколькоСтрочекJSON,
                                                                                Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(this.context);
///

/////
        try {
            //

            try {

                // TODO: 06.09.2021 параметры для вствки
                class_grud_sql_operationsВставка.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                ////



                // TODO: 06.09.2021 контейнер для вставки
                // class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                        new InsertData(this.context).insertdata(class_grud_sql_operationsВставка. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        КонтейнерДляВставки,
                        МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                //////
                if (Результат_ВставкиДанных > 0) {
                    ////успешная вставка данных
                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                    ///
                    КонтейнерДляВставки.clear();
                    ////
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                            +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  +  "  СколькоСтрочекJSON " +СколькоСтрочекJSON);

                    //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                    ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                    //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                }



                /////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }

    //////TODO метод пребразует цифры из цикла в проценты


    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    protected Integer ОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                  ContentValues КонтейнерДляОбновления,
                                                                  String UUIDДляСостыковПриОбновления,
                                                                  int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                  boolean СинхронизациюВизуализировать,
                                                                  Context КонтекстСинхроДляКонтроллера,
                                                                  String ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID,
                                                                  CompletionService МенеджерПотоков,
                                                                  SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                  Integer СколькоСтрочекJSON,
                                                                  Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Integer Результат_ОбновлениеДанных = 0;
        int Результат_ПриписиИзменнийВерсииДанных = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsОбновление;
        try {
            class_grud_sql_operationsОбновление=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
            class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновления);
                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            Результат_ОбновлениеДанных= (Integer)  class_grud_sql_operationsОбновление.
                    new UpdateData(context).updatedata(class_grud_sql_operationsОбновление. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);
            Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                        if (Результат_ОбновлениеДанных > 0) {
                            Log.d(this.getClass().getName(), " Результат_ВставкиДанных   "
                                    + Результат_ОбновлениеДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице " + ИндексТекущейОперацииJSONДляВизуальнойОбработки+
                                     "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
                        }
            Log.d(this.getClass().getName(), "ИндексТекущейОперацииJSONДляВизуальнойОбработки   " +ИндексТекущейОперацииJSONДляВизуальнойОбработки+ " СколькоСтрочекJSON " +СколькоСтрочекJSON);
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ОбновлениеДанных;
    }




    ///-------TODO ДАННЫЕЙ МЕТОД ОБНОЛДЯЕТЬ ДАННЫЕ ТОЛЬКО ВЕРСИИ ДАННЫХ ИЗМЕНЯЕТЬ ПРОТО  ДАТЫ В MODIFICATION CLIENT



    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ






    // TODO: 11.08.2021  запись изменения ТОЛЬКО ДЛЯ ЧАТА СПИМИНЕНИЕМ ВЕРСИИ ДАННЫХ


    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ


    /////////TODO КОНТЕЙНЕР ЛОКАЛЬНОГО ОБНОВЛЕНИЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                        @NonNull ContentValues КонтейнерДляЛокальногоОбновления,
                                                                         Long UUIDДляСостыковПриОбновления,
                                                                         String ФлагЧерезЧегоОбновляетьсяIDилиUUID) throws ExecutionException, InterruptedException, TimeoutException {
        //////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_ЛокальногоОбновлениеДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");

        String ОшибкаТекущегоМетода;


        Class_GRUD_SQL_Operations class_grud_sql_operationsЛокальноеОбновление;

            try {



                    class_grud_sql_operationsЛокальноеОбновление=new Class_GRUD_SQL_Operations(context);


                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ФлагЧерезЧегоОбновляетьсяIDилиUUID);
                    /////

                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);

//

                    class_grud_sql_operationsЛокальноеОбновление.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");

                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ ЛОКАЛЬНОГОМ ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляЛокальногоОбновления);


                            ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ



                    Результат_ЛокальногоОбновлениеДанных= (Integer) class_grud_sql_operationsЛокальноеОбновление.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЛокальноеОбновление. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " + Результат_ЛокальногоОбновлениеДанных);


                    if(Результат_ЛокальногоОбновлениеДанных==null){
                        ///
                        Результат_ЛокальногоОбновлениеДанных=0;
                    }





                    if (Результат_ЛокальногоОбновлениеДанных > 0) {
                        ///todo первое сохранение транзакции
                        //ССылкаНаСозданнуюБазу.
                        Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);
                    }

                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);


                    ///


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                ОшибкаТекущегоМетода = e.toString();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }


        //   publishProgress(Результат_ПриписиИзменнийВерсииДанных);

        return Результат_ЛокальногоОбновлениеДанных;
    }
    //////метод записывает данные о времени в таблицу модификации ерсии данных


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиНовогоСотрудника,
                                                                                          String ИмяТаблицыОтАндройда_Локальноая,
                                                                                          String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        //AsyncTask AsyncTaskУнивермальныйДляОбмена=null;

        Long Результат_ВставкиДанныхТолькоДляСотрудникаНового = 0l;
        ////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;



        Long ВытаскиваемПОвышенуюВерисюДанныхВнутриПоля_CurrenTable=КонтейнерДляВставкиНовогоСотрудника.getAsLong("current_table");

        System.out.println(" Вставка Данных Через Контейнер Универсальная  ВытаскиваемПОвышенуюВерисюДанныхВнутриПоля_CurrenTable "+ ВытаскиваемПОвышенуюВерисюДанныхВнутриПоля_CurrenTable);


        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаСотрудника;
        ////TOdo начинаем таранзакццию

            try {


                    class_grud_sql_operationsВставкаСотрудника=new Class_GRUD_SQL_Operations(context);

                    /////TODO параменты для вставки


                    class_grud_sql_operationsВставкаСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);


                    /////TODO контейнер для вставки

                    class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиНовогоСотрудника);


                    // TODO: 06.09.2021  сама вставка нового сотрукдника


                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхТолькоДляСотрудникаНового= (Long)  class_grud_sql_operationsВставкаСотрудника.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                    /////
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанныхТолькоДляСотрудникаНового  "+Результат_ВставкиДанныхТолькоДляСотрудникаНового);


                    if(Результат_ВставкиДанныхТолькоДляСотрудникаНового==null){
                        ///
                        Результат_ВставкиДанныхТолькоДляСотрудникаНового=0l;
                    }
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ВставкиДанныхТолькоДляСотрудникаНового;
    }


    // TODO: 10.07.2021  для чата


















  public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиДляЧата,
                                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                            boolean ФлагОбновлятьДатуВерсииДанных) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

      Long Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата = 0l;
        /////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" Вставка Данных Через Контейнер Универсальная");

        ////TOdo начинаем таранзакццию
      Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаЧата;



            try {
                try{

                    ////
                    class_grud_sql_operationsВставкаЧата=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА

                    class_grud_sql_operationsВставкаЧата.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);



                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА


                    class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиДляЧата);

                    // TODO: 12.10.2021  Ссылка Менеджер Потоков



                    // TODO: 06.09.2021 САМА ВСТАВКА ДЛЯ ЧАТА ЧАТА

                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата= (Long)  class_grud_sql_operationsВставкаЧата.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);

         /*                  ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }else {

                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =     ССылкаНаСозданнуюБазу.insert(ТаблицаКудаВставляем,null,КонтейнерДляВставки);

                        }

*/

                    //////////
                    if (Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);
                    }
                    ///
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                }
                //////



        /*        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                //////ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС
                if (ФлагОбновлятьДатуВерсииДанных == true && Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {/////ПРИ УКАЗАНОЙ ДАТЕ ПОДТВЕРЖДАТЬ ВРЕМЯ НЕ НАДО
                    /////запускаем втроую транзакцию


                    ///todo ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    ///ПослеУспешнойОперациии записать в табблицу версии данных на клиенте
                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    Class_GRUD_SQL_Operations  classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
                    // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                    //////
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата=new Class_GRUD_SQL_Operations(contextСозданиеБАзы);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ИмяТаблицыОтАндройда_Локальноая);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");
                    ///

                    ///TODO РЕЗУЛЬТА изменения версии данных
                       Результат_ПриписиИзменнийВерсииДанных= (Integer) classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата.
                            new ChangesVesionData(contextСозданиеБАзы).changesvesiondata(classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций);
//

                    ////
                    if(Результат_ПриписиИзменнийВерсииДанных==null){
                        ////
                        Результат_ПриписиИзменнийВерсииДанных=0;
                    }
                    // TODO: 03.09.2021



                    ///todo  конец  ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    if (Результат_ПриписиИзменнийВерсииДанных > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.w(this.getClass().getName(), "  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }else{

                        Log.e(this.getClass().getName(), " ОШибка  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }


                }*/


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///

            }

        //// todo get ASYNtASK
        return Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
    }











    // TODO: 10.07.2021  для чата


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерОрганизацияДляТекущегоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника,
                                                                                         String ИмяТаблицыОтАндройда_Локальноая,
                                                                                         String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                         boolean ФлагОбновлятьДатуВерсииДанных, int ПубличныйIDДляорганизацции,
                                                                                         String ДатаДляОбновлениеОргназации) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

          Long Результат_ОбновленияДанныхОрганизация = 0l;
        ///
        Long Результат_ВставкиДанныхОрганизация = 0l;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;

        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации;

            try {
                //

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);

                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","user_update");


                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",ПубличныйIDДляорганизацции);


//

                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника);


                    // TODO: 06.09.2021 САМА ОПРАЦЙИЯ ОБНОВЛЕНИЯ ПЕРЫЙ ШАГ ЕСЛИ ВЫЙДЕТ, ТО ПОСЛЕДНИЙ

                // TODO: 12.10.2021  Ссылка Менеджер Потоков




                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ОбновленияДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                            new UpdateData(context).updatedata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);

           /*                ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхОрганизация  =             ПриписиИзменнийВерсииДанных.
                                update(ССылкаНаСозданнуюБазу,КонтейнерДляВставки, "user_update=?",
                                        new String[]{String.valueOf(ПубличныйIDДляорганизацции)});
                    }*/


                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    Log.d(this.getClass().getName(), " Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);


                    ///////TODO  ЕСЛИ ОПЕРАЦИЯ ОБНОВЛЕНИЯ НЕ ПРОШЛА ТО НИЖЕ ПРОИЗВОДИМ ВСТАВКИ УЖЕ ДАННЫХ -- ЭТО НУЖНО КОГДА ПЕРВЫЙ ЗАПЦУСК ПРОГРАММЫ И НЕТ ЫВООБЩЕ ДАННЫХ В БАЗЕ И ПРОИЗВХОДИТ РАЗНИЦА ВСТАВКА ИЛИ ОБНОВЛЕНИЕ
                    if (Результат_ОбновленияДанныхОрганизация > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    } else {




                        ///TODO ВТОРАЯ ЧАТЬ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ ПРОИХВОДИМ УЖЕ ВСТАВКУ ПОСЛЕ НЕ УДАЧНОГО ОБНВЛЕНИЯ





                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                        // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);




                        Результат_ВставкиДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                                new InsertData(context).insertdata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                        Log.d(this.getClass().getName(), "Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
/*
                               ВставкиДанных=new    ();
                           ВставкиДанных.setTables(ТаблицаКудаВставляем);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхОрганизация =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }
*/

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
                    }


                    if (Результат_ВставкиДанныхОрганизация > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ


                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    }


                //////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //////

            }

        return Результат_ВставкиДанныхОрганизация;
    }




















// TODO: 10.07.2021  только для чата






    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиПубличныйID)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        long Результат_ВставкиДанныхОрганизация = 0;
        ///////
        int Результат_ПриписиИзменнийВерсииДанных = 0;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолученныйПубличныйID;



        try {
            //////
            class_grud_sql_operationsПолученныйПубличныйID=new Class_GRUD_SQL_Operations(context);


            // TODO: 06.09.2021  ПАРАМЕТРЫ ВСТАВКИ ПУБЛИЧНОГО  ID
            class_grud_sql_operationsПолученныйПубличныйID.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
            /////////////



            // TODO: 06.09.2021  КОНТЕЙНЕР  ВСТАВКИ ПУБЛИЧНОГО  ID

                    ///////
            class_grud_sql_operationsПолученныйПубличныйID.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиПубличныйID);


            // TODO: 06.09.2021  сама вствка данных
            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            ///TODO РЕЗУЛЬТА изменения версии данных
            Результат_ВставкиДанныхОрганизация= (Long)  class_grud_sql_operationsПолученныйПубличныйID.
                    new InsertData(context).insertdata(class_grud_sql_operationsПолученныйПубличныйID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    class_grud_sql_operationsПолученныйПубличныйID.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                    Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


            Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

/*

                           ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхОрганизация =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                    }
*/



                    if (Результат_ВставкиДанныхОрганизация > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    }


            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу

            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //////
        }
        return Результат_ВставкиДанныхОрганизация;
    }





































    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ОбновлениеДанныхЧерезКонтейнерТолькоПриСозданииСУниверсальная(String ТаблицаКудаОбновляем,
                                                                                      ContentValues КонтейнерДляОбновленияСозданииНовогоСотрудника,
                                                                                      String UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        long Результат_ОбновлениеДанныхОбновлениеСозданииНового = 0;
        ///////
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");
//
Class_GRUD_SQL_Operations class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника;
            try {
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника=new Class_GRUD_SQL_Operations(context);


// TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","uuid");
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                ///
//

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                // TODO: 06.09.2021  КОНТЕ  НЕР ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновленияСозданииНовогоСотрудника);


                // TODO: 12.10.2021  Ссылка Менеджер Потоков


                // TODO: 06.09.2021 CАМО ОБНОВЛЕНИЕ


                Результат_ОбновлениеДанныхОбновлениеСозданииНового= (Long)  class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        new UpdateData(context).updatedata(class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ОбновлениеДанныхОбновлениеСозданииНового   " + Результат_ОбновлениеДанныхОбновлениеСозданииНового);
/*
                           ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаОбновляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =             ПриписиИзменнийВерсииДанных.
                                update(contextСозданиеБАзы, КонтейнерДляОбновления, "uuid= ?",
                                        new String[]{UUIDДляСостыковПриОбновления});
                    }else{

                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =        ССылкаНаСозданнуюБазу.update(ТаблицаКудаОбновляем, КонтейнерДляОбновления, "uuid= ?",
                                new String[]{UUIDДляСостыковПриОбновления});

                    }*/


                    Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);




                    ///////
                    if (Результат_ОбновлениеДанныхОбновлениеСозданииНового > 0) {
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);
                    }


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            ////
        return Результат_ОбновлениеДанныхОбновлениеСозданииНового;
    }
































    ///////// todo КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ ТОЛЬКО ДЛЯ ЗАПИСИ ОШИБКИ
    Long ВставкаДанныхЧерезКонтейнерУниверсальнаяТолькоДляЗаписиОшибки(String ТаблицаКудаВставляем,
                                                                       ContentValues КонтейнерДляВставкиЗаписиОшибки)
            throws ExecutionException, InterruptedException, TimeoutException {
        ////////////////////////////////////////////////////////////////////////

        long Результат_ВставкиДанныхДляЗаписиОшибки = 0;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ВставкаДанныхЧерезКонтейнерУниверсальная");

        Class_GRUD_SQL_Operations class_grud_sql_operationsДляВставкиОшибок=new Class_GRUD_SQL_Operations(context);


            try {
                // TODO: 06.09.2021  ПАРАСМЕТИРЫ ВСТАВКИ ОШИБКИ

                class_grud_sql_operationsДляВставкиОшибок.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);


                // TODO: 06.09.2021  КОНТЕЙНЕР ВСТАВКИ ОШИБКИ
                class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиЗаписиОшибки);


                // TODO: 06.09.2021 сама операция ВСТАВКИ ОШИБКИ
                // TODO: 12.10.2021  Ссылка Менеджер Потоков



                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанныхДляЗаписиОшибки= (Long)  class_grud_sql_operationsДляВставкиОшибок.
                        new InsertData(context).insertdata(class_grud_sql_operationsДляВставкиОшибок. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ВставкиДанныхДляЗаписиОшибки   " + Результат_ВставкиДанныхДляЗаписиОшибки);
              /*      ///////

                           ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхДляЗаписиОшибки =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                    }

*/

                    if (Результат_ВставкиДанныхДляЗаписиОшибки > 0) {
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхДляЗаписиОшибки  " + Результат_ВставкиДанныхДляЗаписиОшибки);

                    }
                //////
                /////НАЗВАНИЕ ПОТОКА
                Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase());
                /////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ВставкиДанныхДляЗаписиОшибки;
    }


    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                             String ЧерезКакоеПолеУдлаяемФлаг,
                                                             String UUIDДляСостыковПриОбновления, String ПолеКудаИзменятьСтатус, String ЗапоЗначенияУсменыСтатуса) throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_УдалениеДанных = 0;
Class_GRUD_SQL_Operations classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная;
            try {
                //
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА ПолеКудаИзменятьСтатус  "
                        + ПолеКудаИзменятьСтатус + " ЗапоЗначенияУсменыСтатуса " + ЗапоЗначенияУсменыСтатуса);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная
                        . concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","="); //или =   или <   >
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций
                        .put(ПолеКудаИзменятьСтатус, ЗапоЗначенияУсменыСтатуса);//todo status_write   status_send
                ///
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника  "
                        + " ТаблицаОткудаУдлаяемЗапись " + ТаблицаОткудаУдлаяемЗапись);
                // TODO: 30.01.2022
                Long РезультатУвеличинаяВерсияДАныхЧата =
                        classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.new ChangesVesionData(context).
                                МетодПовышаемВерсииCurrentTable
                                        (ТаблицаОткудаУдлаяемЗапись,  context, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("current_table",
                        РезультатУвеличинаяВерсияДАныхЧата);

                ///TODO РЕЗУЛЬТА ОБНОВЛЕНИЯ
                Результат_УдалениеДанных = (Integer) classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        new UpdateData(context).updatedata(classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс
                                .getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "Результат_УдалениеДанных " + Результат_УдалениеДанных);
                ///
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Результат_УдалениеДанных;
    }






    /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ
    public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                String ПолучениеЗначениеСтолбикUUID,
                                                Context КонтексДляЛокальногоОбновления,
                                                String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
        Integer результатОбновлениеЧерезКонтрейнер = 0;
        try {
            ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
            результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                    Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
            Log.d(this.getClass().getName(),
                    "  результатОбновлениеЧерезКонтрейнер[0] " + результатОбновлениеЧерезКонтрейнер);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

    }




















    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                          String ЧерезКакоеПолеУдлаяемФлаг,
                                                                          Long UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_ОбновлениеДанных = 0;
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
        Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
        // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕ
            try {
                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеСнаДанных",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагСнаДанных",UUIDДляСостыковПриОбновления);
                // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника=new Class_GRUD_SQL_Operations(context);
                Long РезультатУвеличинаяВерсияПриудалениеСотрудника=0L;
                РезультатУвеличинаяВерсияПриудалениеСотрудника=         class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника. new ChangesVesionData(context).
                        МетодПовышаемВерсииCurrentTable(
                                ТаблицаОткудаУдлаяемЗапись, context,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                Log.w(this.getClass().getName(), "РезультатУвеличинаяВерсияПриудалениеСотрудника   " +РезультатУвеличинаяВерсияПриудалениеСотрудника );
                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияПриудалениеСотрудника);
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);
                Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления );
                    if (UUIDДляСостыковПриОбновления > 0) {
                        Результат_ОбновлениеДанных= (Integer)  classGrudSqlOperationsДляУдаленияСотрудника.
                                new SleepData(context).sleepdata(classGrudSqlOperationsДляУдаленияСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                        Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                    }
                    Log.d(this.getClass().getName(), " Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ОбновлениеДанных;
    }
























    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоШАблонЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                   String ЧерезКакоеПолеУдлаяемФлаг,
                                                                   String UUIDДляСостыковПриОбновления) throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_УдалениеТолькоШАблон = 0;
        ////////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

        ///todo начинаем транзакцию

            try {
                ///
                /////
                System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                //////

                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);


                    ///todo ПРИ УДАЛЕНИ И СОТРУДНИКА ОЧИЩАЕМ ПОЛЯ ЧТОБЫ НЕБЛОЫ СВЯКЗКИ С ТАБЕЛЕМ

                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ УДАЛЕНИЯ

                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                    /////////

                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                /////////



                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                    ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                    //
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                    //////
                    ////TODO ДАТА
                    String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

                    АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                    ///
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.putNull("id");///ПОКА НЕ ОТКЛЮЧИЛИ

                Long РезультатУвеличинаяВерсияДАныхЧата=0L;

                РезультатУвеличинаяВерсияДАныхЧата=         classGrudSqlOperationsДляУдаленияСотрудника. new ChangesVesionData(context).
                        МетодПовышаемВерсииCurrentTable(ТаблицаОткудаУдлаяемЗапись, context,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);

                ///

                    classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);


                    Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления  + " РезультатУвеличинаяВерсияДАныхЧата " +РезультатУвеличинаяВерсияДАныхЧата );


                    if (Long.parseLong(UUIDДляСостыковПриОбновления) > 0) {


                        // TODO: 06.09.2021 сама операция обновления через новый движок  удаление пустого табеля
                        // TODO: 12.10.2021  Ссылка Менеджер Потоков



                        ///TODO РЕЗУЛЬТА изменения версии данных
                        Результат_УдалениеТолькоШАблон= (Integer)   classGrudSqlOperationsДляУдаленияСотрудника.
                                new UpdateData(context).updatedata(classGrudSqlOperationsДляУдаленияСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                        Log.d(this.getClass().getName(), "Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
//////////////////////////////////////////////////////////////////////

                        if(Результат_УдалениеТолькоШАблон==null){

                            //
                            Результат_УдалениеТолькоШАблон=0;
                        }



             /*                  ПриписиИзменнийВерсииДанных=new    ();
                           ПриписиИзменнийВерсииДанных.setTables(ТаблицаОткудаУдлаяемЗапись);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ОбновлениеДанных  =             ПриписиИзменнийВерсииДанных.
                                    update(ССылкаНаСозданнуюБазу,АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная,
                                            ЧерезКакоеПолеУдлаяемФлаг + "= ?", new String[]{String.valueOf(UUIDДляСостыковПриОбновления)});
                        }
*/

                    }
                    Log.d(this.getClass().getName(), " Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
                } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_УдалениеТолькоШАблон;
    }






///todo записываем выбраную  ОРГАНИЗАЦИЮ В БАЗУ

    public Integer МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile(String ПередаваемыйРежимИнтрентета,
                                                                               Context КонтекстWIFI,
                                                                               String Таблица,
                                                                               String Поля) {
/////todo КОД ЗАПОЛЕНЕИЯ ДАННЫМИ В СПИНЕР ЦФО ДЕПАРТАМЕНТ МЕСЯЦ
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
//
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil;

                try {

                    Log.d(this.getClass().getName(), " ПередаваемыйРежимИнтрентета  " + ПередаваемыйРежимИнтрентета);

                    ////TODO ОБНУЛЯЕМ КАКУЮ ОРГАНИЗАЦИЮ ВЫБРАЛИ ОЧИЩАЕМ ВСТАВЛЕМ ППАРАМЕТР WIF-FI


                    ///////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);


                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                    ///
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


                    // TODO: 12.10.2021  Ссылка Менеджер Потоков

                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);


                    ///////
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        //
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                        /////
                        ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);


                        Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                    }



                    // TODO: 06.09.2021 ВТОРОЕ ДЕЙСТИВЕ ПОСЛЕ ОЧИСТКИ ВСТАВЛЯЕМ  ПОЛУЧЕНЫЙ СТАТУС   ДЛЯ WIFI MOBILE

                    ///////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);


                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ

                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",Таблица);

                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","id");
                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",ПубличноеIDПолученныйИзСервлетаДляUUID);

//

                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","="); //или =   или <   >

                    // TODO: 06.09.2021  КОНТЕРЙНЕР ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ

                    ContentValues ВставляемВБАзуВыбранныйРежимИнтренета=new ContentValues();

            ВставляемВБАзуВыбранныйРежимИнтренета = new ContentValues();





                    ВставляемВБАзуВыбранныйРежимИнтренета.put("id", ПубличноеIDПолученныйИзСервлетаДляUUID);

                    ВставляемВБАзуВыбранныйРежимИнтренета.put(Поля, ПередаваемыйРежимИнтрентета);


                    ////TODO ДАТА
                    String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();


                    ВставляемВБАзуВыбранныйРежимИнтренета.put("date_update", СгенерированованныйДатаДляДаннойОперации);

                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(ВставляемВБАзуВыбранныйРежимИнтренета);


                    // TODO: 06.09.2021  САМА ОПАРАЦИИЯ ОБНОВЛЕНИЯ СТАТУ WIFI ИЛИ MOBILE
                    // TODO: 12.10.2021  Ссылка Менеджер Потоков


                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


                    РезультатОбновлениеЧерезКонтрейнер= (Integer)  class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                    Log.d(this.getClass().getName(), "РезультатОбновлениеЧерезКонтрейнер   " + РезультатОбновлениеЧерезКонтрейнер);

                    ///поймать ошибку
                } catch (Exception e) {
                    //  Block of code to handle errors
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ///////
                }

        return РезультатОбновлениеЧерезКонтрейнер;
    }
























    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеИмяСистемыДляСменыПользователя(Context КонтекстДляРежимаИнтрента) {
        //
        String ИмяУспешноВошедегоПользователья = new String();
        ///

        SQLiteCursor Курсор_ПолучениеИмяСистемы = null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучениеИмяСистемы;
        ////
        try {

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПолучениеИмяСистемы=new Class_GRUD_SQL_Operations(context);

            ///
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","success_users");
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
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
           /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ПолучениеИмяСистемы= (SQLiteCursor)  class_grud_sql_operationsПолучениеИмяСистемы.
                    new GetData(context).getdata(class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  +Курсор_ПолучениеИмяСистемы);


/*

            Cursor Курсор_ЗагружаетДанныеПриСозданииТабеля = new Class_MODEL_synchronized(contextСозданиеБАзы).КурсорУниверсальныйДляБазыДанных("SuccessLogin", new String[]
                            {"success_users"}, null,
                    null, null, null, "date_update", null);///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
*/

            //////todo ПОЛУЧЕНИЕ ДАННЫХ
            if (Курсор_ПолучениеИмяСистемы.getCount() > 0) {
                //////
                Курсор_ПолучениеИмяСистемы.moveToFirst();

                //
                ИмяУспешноВошедегоПользователья = Курсор_ПолучениеИмяСистемы.getString(0);
                /////
                Log.d(this.getClass().getName(), "ИмяУспешноВошедегоПользователья  " + ИмяУспешноВошедегоПользователья);

            }

            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
      return  ИмяУспешноВошедегоПользователья;

    }





















    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеля(int IDЧьиДанныеДляСотрудников, Long полученнаяUUIDНазванияОрганизации, String finalУниверсальноеИмяТабеля, Context контекстLIstView,
                                                   int МЕсяцДляКурсораТабелей, int ГодДляКурсораТабелей, String ЦифровоеИмяНовгоТабеля) {
        ////


        SQLiteCursor Курсор_ДляЗагрузкиСотрудниковНепостредственнов = null;

         Class_GRUD_SQL_Operations class_grud_sql_operationsСотрудниковListViewТабел;

        try {
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            class_grud_sql_operationsСотрудниковListViewТабел=new Class_GRUD_SQL_Operations(контекстLIstView);

            ///
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name,uuid,BirthDate,snils,_id,status_carried_out");
            //
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update= ?  AND  month_tabels=?  AND year_tabels=?" +
                    " AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL");
            ////
            // TODO: 06.09.2021  значнеия для where

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",IDЧьиДанныеДляСотрудников);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",МЕсяцДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",ГодДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4", finalУниверсальноеИмяТабеля.trim());

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска5",полученнаяUUIDНазванияОрганизации);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска6","Удаленная");

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска7",ЦифровоеИмяНовгоТабеля);

            // TODO: 06.09.2021 УСЛОВИЕ ДЛЯ СОРТИРОВКИ

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");

            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ДляЗагрузкиСотрудниковНепостредственнов= (SQLiteCursor)  class_grud_sql_operationsСотрудниковListViewТабел.
                    new GetData(контекстLIstView).getdata(class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  );

            ////


/*
            Курсор_ДляЗагрузкиСотрудниковНепостредственнов = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"name,uuid,BirthDate,snils,_id,status_carried_out"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    " user_update= ?  AND  month_tabels=?  AND year_tabels=? AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{String.valueOf(IDЧьиДанныеДляСотрудников), String.valueOf(МЕсяцДляКурсораТабелей), String.valueOf(ГодДляКурсораТабелей),
                            finalУниверсальноеИмяТабеля.trim(), String.valueOf(полученнаяUUIDНазванияОрганизации), "Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля)}, null, null, "date_update DESC", null);



*/




            //////todo полученный
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount() > 0) {
                ////////
                Курсор_ДляЗагрузкиСотрудниковНепостредственнов.moveToFirst();




                Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount());
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE


        return Курсор_ДляЗагрузкиСотрудниковНепостредственнов;

    }












// TODO: 12.03.2021 Метод который получает данные при возврате из ШАБЛОНОВ

/*    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеляПриВозвратеИЗШаблона(Context контекстLIstView, String ЦифровоеИмяНовгоТабеля, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {
        ////




// TODO: 07.05.2021  ГЛАВНЫЙ КУРСОР СОГРУЗКИ СОТУРДНИКОВ В ТАБЕЛЬ

        Cursor Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = null;
        try {
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND cfo=? AND fio !=?  AND month_tabels=? AND  year_tabels =?  AND fio IS NOT NULL AND name IS NOT NULL",//  nametabel_typename  AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{"Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля), "", String.valueOf(месяцДляПермещенияПоТабелю), String.valueOf(годДляПермещенияПоТабелю)},
                    "name", null, "name", null);

            // TODO: 07.05.2021  данный курсор с датой показывает какой сотрудника изменили такой и сверху
*//*
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND nametabel_typename=? AND uuid !=? AND uuid IS NOT NULL AND name IS NOT NULL",// AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{ "Удаленная",String.valueOf(ЦифровоеИмяНовгоТабеля),""},
                    "name", null, "date_update DESC", null);*//*

            //////
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount() > 0) {
                Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.moveToFirst();
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE
        Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблонаListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount());

        return Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона;

    }*/

//TODO МЕТОД ЗАГРУЗНИ НОВОГО СОТРУДНИКА
    public Cursor МетодЗагружаетЗначенияНовгоСотрудника(Context КонтекстДЛяСотрудника) {
                    SQLiteCursor            Курсор_ЗагружаетСФОИМесяц = null;
                    try {
                        Class_GRUD_SQL_Operations    class_grud_sql_operationsЗначенияНовгоСотрудник=new Class_GRUD_SQL_Operations(context);
                  String[] ТаблицыОбработки=new String[]{"tabel","viewtabel"};///"viewtabel",
                        for (int i = 0; i < ТаблицыОбработки.length; i++) {
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицыОбработки[i]);
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","month_tabels,year_tabels,cfo");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","status_send!=?  " +
                                    " AND month_tabels IS NOT NULL  AND year_tabels IS NOT NULL");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","Удаленная");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","month_tabels,year_tabels");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","year_tabels DESC ,month_tabels DESC , date_update DESC" );
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","6");


                            Курсор_ЗагружаетСФОИМесяц= (SQLiteCursor)  class_grud_sql_operationsЗначенияНовгоСотрудник.new GetData(context).getdata(class_grud_sql_operationsЗначенияНовгоСотрудник.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            if(Курсор_ЗагружаетСФОИМесяц.getCount()>0){
                                break;
                            }
                            Log.d(this.getClass().getName(), "Курсор_ЗагружаетСФОИМесяц " +Курсор_ЗагружаетСФОИМесяц );
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
        return Курсор_ЗагружаетСФОИМесяц;
    }

















//TODO МЕТОД ЗАГРУЗНИ НОВОГО шаблона

    public Cursor МетодЗагружаетЗначенияШаблонов(int полученнаяUUIDОрганизациидДляКурсораСпинераДаты, Context КонтекстДЛяСотрудника) {
     /*   Cursor asyncTaskLoader= (Cursor) new AsyncTaskLoader(КонтекстДЛяСотрудника) {
            @Override
            public Object loadInBackground() {*/

        SQLiteCursor Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗначенияШаблонов;
        try {
// TODO: 06.09.2021

            class_grud_sql_operationsЗначенияШаблонов=new Class_GRUD_SQL_Operations(context);


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","Templates");
            ///////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update=?");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",полученнаяUUIDОрганизациидДляКурсораСпинераДаты);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            //class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","month_tabels,year_tabels");
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","date_update DESC");
            ////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
            ////
            /// class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри= (SQLiteCursor)  class_grud_sql_operationsЗначенияШаблонов.
                    new GetData(context).getdata(class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри );


        /*    // TODO: 06.09.2021  old
            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = new Class_MODEL_synchronized(КонтекстДЛяСотрудника).КурсорУниверсальныйДляБазыДанных("Templates", new String[]
                            {"*"}, "user_update=?",
                    new String[]{String.valueOf(полученнаяUUIDОрганизациидДляКурсораСпинераДаты)},
                    null, null, "date_update DESC", null);
            ////
*/

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри;
    }


    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеля(Context КонтекстДляЗагружемыхТАбелей, Long UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {


        //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////
        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        //////
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеля;
        try {

            class_grud_sql_operationsУжеготовыеТабеля = new Class_GRUD_SQL_Operations(context);


                /*    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = new Class_MODEL_synchronized(КонтекстДляРежимаИнтрента).КурсорУниверсальныйДляБазыДанных("cfo", new String[]
                                    {"name"}, "id=?",
                            new String[]{String.valueOf(ТекущееСФО)}, null, null, "date_update DESC", "1");///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
                    // TODO: 02.09.2021
*/


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," uuid=?    " +
                    "AND status_send !=?" +
                    " AND month_tabels=?" +
                    " AND  year_tabels =? " +
                    "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            ///
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","date_update DESC");
            ////
        class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","uuid");//date_update
            ////
          class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеля.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }










    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(Context КонтекстДляЗагружемыхТАбелей,
                                                                                     int ЦифровоеИмяНовгоТабеля,
                                                                                     int месяцДляПермещенияПоТабелю,
                                                                                     int годДляПермещенияПоТабелю) {
        /*Cursor asyncTaskLoaderЗагружаемТабеляСозданный = (Cursor) new AsyncTaskLoader(КонтекстДляЗагружемыхТАбелей) {
            @Override
            public Object loadInBackground() {*/
//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////

        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю;
        try {


            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю=new Class_GRUD_SQL_Operations(context);




            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","cfo=?  " +
                    "AND status_send !=? " +
                    " AND month_tabels=?  " +
                    " AND  year_tabels=?" +
                    " AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","fio");
            ////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","uuid");
            ////
     ////  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////


            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );


            ///////todo\
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }














    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек(Context КонтекстДляЗагружемыхТАбелей, int ЦифровоеИмяНовгоТабеля, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {
        /*Cursor asyncTaskLoaderЗагружаемТабеляСозданный = (Cursor) new AsyncTaskLoader(КонтекстДляЗагружемыхТАбелей) {
            @Override
            public Object loadInBackground() {*/
//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////

        SQLiteCursor Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти = null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти;

        try {

            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти=new Class_GRUD_SQL_Operations(context);




            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",
                    "cfo=? " +
                    "AND status_send !=? AND" +
                    " month_tabels=? AND" +
                    "  year_tabels=? " +
                            "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            ///
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","date_update ");
            ////
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update ");
            ////
            // class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти=null;
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти= (SQLiteCursor)
                    class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                    new GetData(context).getdata(class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "+Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти  );



            ///////todo\
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти;
    }



















    public Long МетодЗАписиПолученогоОтСервреаIDПубличногоВТАблицу_settings_tabels(@NonNull Integer ПолученныйотСервреаПубличныйID) throws ExecutionException, InterruptedException {
    long РезультатИзмененияВерсииДанныхПриЗаписиОрганизации = 0l;
        // TODO: 20.04.2021 определяем ели UUID или нет
        SQLiteCursor Курсор_УзнаемЕслиUUIDВТАблицеОрганизация = null;
                try {
                    String ТаблицаКоторуюнадоИзменитьВерсиюДанных="settings_tabels";
                        ///TODO ВСТАВКА НОВГО ТАБЕЛЯ В ТАБЛИЦУ
                        if (ПолученныйотСервреаПубличныйID > 0) {
                            ////////
      Class_GRUD_SQL_Operations class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного=new Class_GRUD_SQL_Operations(context);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКоторуюнадоИзменитьВерсиюДанных);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","user_update");
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного
                                    . concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update=?   AND user_update IS NOT NULL ");
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолученныйотСервреаПубличныйID);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
                            Курсор_УзнаемЕслиUUIDВТАблицеОрганизация= (SQLiteCursor)  class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    new GetData(context).getdata(class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                            Log.d(this.getClass().getName(), "GetData " +Курсор_УзнаемЕслиUUIDВТАблицеОрганизация );
                            int UUIDдлянастроуки = 0;
                                if (Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getCount() > 0) {
                                    Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.moveToFirst();
                                    //////todo А ТУТУ КОГДА ЕСТЬ ДАННЫЕ
                                    int IndexUUID = Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getColumnIndex("user_update");
                                    int UUIDОрншанизации = Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getInt(IndexUUID);
                                    Log.d(this.getClass().getName(), "IndexUUID " + IndexUUID);
                                    // TODO: 06.09.2021  когда данные не были получены
                                } else {
                                    ContentValues АдаптерВставкиПолученогоПубличногоID = new ContentValues();
                                    АдаптерВставкиПолученогоПубличногоID.put("user_update", ПолученныйотСервреаПубличныйID);
                                    //TODOверсия программы самой например 601
                                    //Object ВрсияПрограммы=BuildConfig.VERSION_CODE;
                                    PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                                    String version = pInfo.versionName;//Version Name
                                    Integer verCode = pInfo.versionCode;//Version Code
                                    // TODO: 29.12.2021
                                    АдаптерВставкиПолученогоПубличногоID.put("version_dsu1",  Integer.parseInt(verCode.toString()));
                                    Log.d(this.getClass().getName(), " uildConfig.VERSION_CODE ВрсияПрограммы  "
                                            + verCode.toString());
                                    ////TODO ДАТА
                                    String СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного=
                                            new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                    АдаптерВставкиПолученогоПубличногоID.put("date_update", СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного);
                                    АдаптерВставкиПолученогоПубличногоID.put("uuid", ПолученныйотСервреаПубличныйID);
                                    /////////
                                    Long РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ = 0L;
                                    РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ = new Class_GRUD_SQL_Operations(context).
                                            new ChangesVesionData(context).
                                            МетодПовышаемВерсииCurrentTable(ТаблицаКоторуюнадоИзменитьВерсиюДанных, context, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                                    АдаптерВставкиПолученогоПубличногоID.put("current_table", РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ);
                                    //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
                                    Long    Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels =
                                                ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера(ТаблицаКоторуюнадоИзменитьВерсиюДанных,
                                                АдаптерВставкиПолученогоПубличногоID);
                                    Log.d(this.getClass().getName(), "Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels "
                                            + Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels);
                                    // TODO: 02.05.2021

                                    if (Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels>0 ) {
                                        Class_GRUD_SQL_Operations             classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels =
                                        new Class_GRUD_SQL_Operations(context);
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКоторуюнадоИзменитьВерсиюДанных);
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");
                                        Log.d(context.getClass().getName(), "РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ "
                                                +РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ );
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",
                                                РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ);
                                        ///TODO РЕЗУЛЬТА изменения версии данных
                                Integer    Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels = (Integer)
                                            classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                new ChangesVesionData(context)
                                                    .changesvesiondata(classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                                        Log.d(this.getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels "
                                                +Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels );
                                    }
                                }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }


        return РезультатИзмененияВерсииДанныхПриЗаписиОрганизации;
    }


    // TODO: 09.04.2021 ПОЛУЧЕНИЕ ПОСЛЕ JSON ПОЛУЧАЕМ САМ ФАЙЛ APK
    // TODO: 09.04.2021 ПОЛУЧЕНИЕ ПОСЛЕ JSON ПОЛУЧАЕМ САМ ФАЙЛ APK
    public File УниверсальныйБуферAPKФайлаПОсСервера(String АдресЗагрузки, String НазваниеФайлаКоторыйНадоЗагрузить, Context context, String ИмяСервера, Integer ИмяПорта)
            throws IOException, ExecutionException, InterruptedException,
            TimeoutException, NoSuchAlgorithmException, KeyManagementException, InvalidKeyException, NoSuchPaddingException {
                File СамФайлAPKВнутри = null;
                String ОшибкаТекущегоМетода;
                try {
                    HttpURLConnection ПодключениеИнтернетДляЗагрузкеAPKФайла = null;
                    System.out.println("УниверсальныйБуферAPKФайловсСервера");
                    int РазмерФАйла = 0;
                    String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
                    String Adress_String = null;
                    Adress_String = СтрокаСвязиСсервером + АдресЗагрузки; /////"dsu1.glassfish/update_android_dsu1/output-metadata.json";+ "dsu1.glassfish/update_android_dsu1/app-release.apk"
                    Log.d(this.getClass().getName(), "Adress_String " + Adress_String);
                    Adress_String = Adress_String.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " Adress_String " + Adress_String);
                    URL Adress = null;
                    Adress = new URL(Adress_String);
                    ПодключениеИнтернетДляЗагрузкеAPKФайла = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("Content-Type", "application/octet-stream");
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("Connection", "Keep-Alive");
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("Accept-Language", "ru-RU");
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestMethod("GET"); ///GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setReadTimeout(2000); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setConnectTimeout(60000);//todo САМ ПОТОК ДАННЫХ(1000);
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setUseCaches(false);
                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(this.context);
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (this.context);
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО=null;
                    ///////
                    Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(this.context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                        ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                        ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                    }
                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " +ПубличноеИмяПользовательДлСервлета +
                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);
                    String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(this.context).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                    Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                    // TODO: 12.11.2021 ППЕРОБРАЗОВАНИЯ ПАРОЛЬЯ ЧЕРЕЗ BASE64 ПАРОЛЯ ВАРИАНТ 1
                    String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(this.context).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                    Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);
                    /////// TODO set login pasword
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("p_identifier",
                            ЗашифрованныйПароль);  //"dsu1getsession"
                    ////Dalvik/2.1.0 (Linux; U; Android 7.0; Android SDK built for x86 Build/NYC)
                    ///////посылаем сашифрованные хэдэры
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.setRequestProperty("identifier",
                            ЗашифрованныйЛогин  );  //"dsu1getsession"   ПубличноеИмяПользовательДлСервлета

                    ПодключениеИнтернетДляЗагрузкеAPKФайла.connect(); /////////////ТОЛЬКО СОЕДИНЕНИЕ
                    ПодключениеИнтернетДляЗагрузкеAPKФайла.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА
                    int lenghtOfFile = ПодключениеИнтернетДляЗагрузкеAPKФайла.getContentLength();
                    Log.i(this.getClass().getName(), " РАЗМЕР ПРИШЕЛШЕГО ФАЙЛА .apk lenghtOfFile "
                            +lenghtOfFile);
                    ////TODO И ЕСЛИ ПРИШЕЛ ОТ СЕРВЕРА ОТВЕТ ПОЛОЖИТЕЛЬНО ТО ТОГДА ЗАПУСКАМ ПРОЧТЕНИЯ ПОТОКА ПРИШЕДШЕГО С СЕРВЕРА
                    if (ПодключениеИнтернетДляЗагрузкеAPKФайла.getResponseCode() == 200 && lenghtOfFile>1) {
                        ByteArrayOutputStream buffer = null;
                        // TODO: 12.05.2021 запускаем если файл пришел
                        if (lenghtOfFile > 0) {
                            Log.e(this.getClass().getName(), " ПРИШЕЛ ФАЙЛ УСПЕХ ПРИШЛОЙ ФАЙЛ СЛУЖБА ЗАГРУЗКА ОБНОВЛЕНИЯ  ДЛИНА ФАЙЛА : " + lenghtOfFile);
                            InputStream input = null;
                            input = ПодключениеИнтернетДляЗагрузкеAPKФайла.getInputStream();
                            Log.i(this.getClass().getName(), " ПРИШЛОЙ ФАЙЛ СЛУЖБА ЗАГРУЗКА ОБНОВЛЕНИЯ  ДЛИНА ФАЙЛА : " + lenghtOfFile);
                            buffer = new ByteArrayOutputStream();
                            int nRead;
                            byte[] data = new byte[lenghtOfFile];///8192 //512
                            while ((nRead = input.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, nRead);
                                Log.d(context.getClass().getName(), "NEW NEW NEW .APK nRead "+nRead);
                            }
                        File ПутькФайлу = null;
                        if (Build.VERSION.SDK_INT >= 30) {
                            ПутькФайлу = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS);
                        } else {
                            ПутькФайлу = Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS);
                        }
                        СамФайлAPKВнутри = new File(ПутькФайлу, "/" + НазваниеФайлаКоторыйНадоЗагрузить);
                            if (!СамФайлAPKВнутри.getParentFile().mkdirs() ) {
                                СамФайлAPKВнутри.getParentFile().mkdirs();
                            }
                        if (СамФайлAPKВнутри.createNewFile()) {
                            Log.d(context.getClass().getName(), "Будущий файл успешно создалься , далее запись на диск новго APk файла ");
                            FileOutputStream fos = null;
                                fos = new FileOutputStream(СамФайлAPKВнутри);
                                buffer.writeTo(fos);

                            fos.flush();
                            buffer.flush();
                            buffer.close();
                            fos.close();
                        } else {
                            System.out.println(" Ошибка не создалься Будущий файл успешно создалься , далее запись на диск новго APk файла ");

                            Log.e(context.getClass().getName(), "Ошибка не создалься Будущий файл успешно создалься , далее запись на диск новго APk файла  СЛУЖБА ");
                        }
                    } else {
                        Log.e(this.getClass().getName(), "Ошибка не создалься Будущий файл успешно создалься , далее запись на диск новго APk файла  СЛУЖБА ");
                    }
                    }  else {
                        Log.e(this.getClass().getName(), " нулевой файл  ошибка ПРИШЛОЙ ФАЙЛ СЛУЖБА ЗАГРУЗКА ОБНОВЛЕНИЯ  ДЛИНА ФАЙЛА : " + lenghtOfFile);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    ОшибкаТекущегоМетода = ex.toString();
                    if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                            &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                        new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
        Log.i(this.getClass().getName(), " СамФайлAPKВнутри.getParentFile().mkdirs() " + СамФайлAPKВнутри.length()+
                " countDownLatchПолучаемВерсиюAPKФайласамаыверис ");
        //// todo get ASYNtASK
        return (File) СамФайлAPKВнутри;

    }




















    // TODO: 09.04.2021 метод получение УниверсальныйБуферAPKФайловсСервера обнвоения
    public int УниверсальныйБуферJSONВерсииПОсСервера(String АдресЗагрузки, Context context, String ИмяСервера, Integer ИмяПорта) throws IOException, ExecutionException, InterruptedException,
            TimeoutException, NoSuchAlgorithmException, KeyManagementException, InvalidKeyException, NoSuchPaddingException {
        StringBuffer БуферПолученнниеJOSNФайла = new StringBuffer();
                int ПолученнаяВерсияПОДЛяОбновления = 0;
                HttpURLConnection ПодключениеИнтернетДляJSONВерсииФайлаAPK = null;
                System.out.println("УниверсальныйБуферAPKФайловсСервера");
                Object ОшибкаТекущегоМетода;
                try {
                    String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
                    String Adress_String = null;
                    Adress_String = СтрокаСвязиСсервером + АдресЗагрузки; /////"dsu1.glassfish/update_android_dsu1/output-metadata.json";
                    Log.d(this.getClass().getName(), "Adress_String " + Adress_String);
                    Adress_String = Adress_String.replace(" ", "%20");
                    Log.d(this.getClass().getName(), " Adress_String " + Adress_String);
                    URL Adress = null;
                    Adress = new URL(Adress_String);
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ   ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("Content-Type", "application/json");
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("Connection", "Keep-Alive");
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("Accept-Language", "ru-RU");
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestMethod("GET"); ///GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setReadTimeout(30000); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setConnectTimeout(30000);//todo САМ ПОТОК ДАННЫХ(1000);
                 ПодключениеИнтернетДляJSONВерсииФайлаAPK.setUseCaches(false);
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(this.context);
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (this.context);
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО=null;
                              Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(this.context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                        ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                        ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                    }
                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " +ПубличноеИмяПользовательДлСервлета +
                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);
                    // TODO: 11.11.2021  ПЕРЕДОТПРАВКОЙ ШИФРУЕМ ДАННЫЕ \
                    String ЗашифрованныйЛогин=new Class_Encryption_Decryption_Login_Password(this.context).МетодПреобразованиеBase64Данных(ПубличноеИмяПользовательДлСервлета);
                    Log.d(this.getClass().getName(), " ЗашифрованныйЛогин  " + ЗашифрованныйЛогин);
                    String ЗашифрованныйПароль=new Class_Encryption_Decryption_Login_Password(this.context).МетодПреобразованиеBase64Данных(ПубличноеПарольДлСервлета);
                    Log.d(this.getClass().getName(), " ЗашифрованныйПароль  " + ЗашифрованныйПароль);
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("p_identifier",
                            ЗашифрованныйПароль);  //"dsu1getsession"
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.setRequestProperty("identifier",
                            ЗашифрованныйЛогин  );  //"dsu1getsession"   ПубличноеИмяПользовательДлСервлета
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.connect();
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА
                    int ДлинаФайлаJSONВерсииДанныхЧисло = ПодключениеИнтернетДляJSONВерсииФайлаAPK.getContentLength();
                    Log.w(this.getClass().getName(), "  ДлинаФайлаJSONВерсииДанныхЧисло  JSON ВЕРСИЯ ФАЙЛА НА СЕРВЕРА " + ДлинаФайлаJSONВерсииДанныхЧисло);
                    ////TODO И ЕСЛИ ПРИШЕЛ ОТ СЕРВЕРА ОТВЕТ ПОЛОЖИТЕЛЬНО ТО ТОГДА ЗАПУСКАМ ПРОЧТЕНИЯ ПОТОКА ПРИШЕДШЕГО С СЕРВЕРА
                    if (ПодключениеИнтернетДляJSONВерсииФайлаAPK.getResponseCode() == 200 && ДлинаФайлаJSONВерсииДанныхЧисло>0) {
                        //////тест шифрование
                        InputStream ПришелФайлAPKОбновление = ПодключениеИнтернетДляJSONВерсииФайлаAPK.getInputStream();
                        ///// todo получаем данные с сервера
                        //GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(ПодключениеИнтернетДляОтправкиНаСервер[0].getInputStream(),Deflater.BEST_COMPRESSION);
                        BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(ПришелФайлAPKОбновление, StandardCharsets.UTF_8));
                        int РазмерБуфера = 8192;////РидерОтСервераМетодаGET.toString().toCharArray().length*3; /////     8192;
                        int ФлагНекончильсяЛиПотокДанных = 0;
                        char[] СамБУфер = new char[РазмерБуфера];
                        // TODO: 02.06.202
                        do {
                            ФлагНекончильсяЛиПотокДанных = РидерОтСервераМетодаGET.read(СамБУфер);
                            if (ФлагНекончильсяЛиПотокДанных < 0) {
                                break;
                            }
                            БуферПолученнниеJOSNФайла.append(СамБУфер, 0, ФлагНекончильсяЛиПотокДанных);
                        } while (true);
                        String СодержимоеИзПришедшихТаблицДляКлиентаdd;
                        int СодержимоеИзПришедшихТабл = БуферПолученнниеJOSNФайла.lastIndexOf("versionCode");
                        int СодержимоеИзПришедшихТабл2 = БуферПолученнниеJOSNФайла.indexOf("versionName");
                        if (СодержимоеИзПришедшихТабл >= 0 && СодержимоеИзПришедшихТабл2 >= 0) {
                            СодержимоеИзПришедшихТаблицДляКлиентаdd = БуферПолученнниеJOSNФайла.substring(СодержимоеИзПришедшихТабл, СодержимоеИзПришедшихТабл2);
                            System.out.println("   СодержимоеИзПришедшихТаблицДляКлиентаdd  " + СодержимоеИзПришедшихТаблицДляКлиентаdd);
                            ПолученнаяВерсияПОДЛяОбновления = Integer.parseInt(СодержимоеИзПришедшихТаблицДляКлиентаdd.replaceAll("[^0-9]", ""));
                            System.out.println("   ПолученнаяВерсияПОДЛяОбновления  " + ПолученнаяВерсияПОДЛяОбновления);
                        }
                        Log.i(this.getClass().getName(), " БуферПолученнниеJOSNФайла" + БуферПолученнниеJOSNФайла.toString()
                                + " ПолученнаяВерсияПОДЛяОбновления " + ПолученнаяВерсияПОДЛяОбновления);
                        ///TODO закрываем метод получение данных
                        РидерОтСервераМетодаGET.close();
                        ПришелФайлAPKОбновление.close();
                    } else {
                        Log.i(this.getClass().getName(), "ПОТОК ПРИШЕЛ НУЛОВОЙ ОТ СЕРВЕРА JSON -информация " + ПодключениеИнтернетДляJSONВерсииФайлаAPK.getInputStream().available());
                    }
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    ///метод запись ошибок в таблицу
                    ОшибкаТекущегоМетода = ex.toString();
                    ПодключениеИнтернетДляJSONВерсииФайлаAPK.disconnect();
                    if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                            &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.ConnectException(.*)")
                            && !ОшибкаТекущегоМетода.toString().matches("(.*)FileNotFoundException(.*)"))  {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                        new Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }
                }
        Log.i(this.getClass().getName(), "ПолученнаяВерсияПОДЛяОбновления " + ПолученнаяВерсияПОДЛяОбновления);
        return (int) ПолученнаяВерсияПОДЛяОбновления;

    }






    ////TODO ДАННЫЙ МЕТОД ВЫЧИСЛЯЕТ НУЖНО ЛИ ЗАПОЛЯНТЬ ВЫХОДНИЕ ДНИ БУКВОЙ Б
    public ContentValues МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(@NonNull Context КонтекстДляРежимаИнтрента,
                                                                                   @NonNull Integer Месяц ,
                                                                                    @NonNull Integer Год ) {

        ContentValues РезультатВычисленияВыходныхДней = new ContentValues();

        try {


// create a Calendar for the 1st of the required month
            ///
            Log.d(КонтекстДляРежимаИнтрента.getClass().getName(), " Год  " + "--" + Год + " Месяц " + Месяц);/////



            Calendar calendar1 = Calendar.getInstance(new Locale("ru"));
            //TODO

      //   calendar.set(Calendar.DAY_OF_MONTH,calendar.);
            YearMonth yearMonthObject = YearMonth.of(Год, Месяц);

            int daysInMonth = yearMonthObject.lengthOfMonth()+1; //28

            SimpleDateFormat СозданияВычисляемВыходные=null;
            // TODO: 29.04.2022  int ИндексДней
            int ИндексДней;

            ///////TODO сам цикл который заполняет месяцами
            for ( ИндексДней=1;ИндексДней<daysInMonth;ИндексДней++) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                } else {

                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                }


                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (Год +"-"+Месяц+"-"+ИндексДней );

                // Then get the day of week from the Date based on specific locale.

            // TODO: 29.01.2022  отдельно только название дня
            String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );


                if (РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("сб")  ||
                        РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("вс")) {
                    System.out.println("выходные дни при созадни тбалея полльзователь разрешмил авторежим" + ИндексДней);

                    String ОбьединяемДеньсЦифровдЛЯвСТАВКИ = "d" + String.valueOf(ИндексДней);

                    РезультатВычисленияВыходныхДней.put(ОбьединяемДеньсЦифровдЛЯвСТАВКИ, "В");
                }

            }
// stop when we reach the start of the next month


            Log.d(this.getClass().getName(), "   РезультатВычисленияВыходныхДней  " + РезультатВычисленияВыходныхДней.valueSet() + " daysInMonth " + daysInMonth);


            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }

        return РезультатВычисленияВыходныхДней;
    }













    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеНазваниеТабеляНаОснованииСФО(Context КонтекстДляРежимаИнтрента, Integer ТекущееСФО) throws InterruptedException {
        //
                String ПолученоеНазваниеТабеляНаОснованииСФО = null;
                SQLiteCursor Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = null;
                Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО;
                try {
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО=new Class_GRUD_SQL_Operations(КонтекстДляРежимаИнтрента);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","cfo");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","_id = ?");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ТекущееСФО);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО= (SQLiteCursor)  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.
                            new GetData(КонтекстДляРежимаИнтрента).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО);
                    if (Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getCount() > 0) {
                        Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.moveToFirst();
                        ПолученоеНазваниеТабеляНаОснованииСФО = Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getString(0).trim();
                        Log.d(this.getClass().getName(), " ПолученоеНазваниеТабеляНаОснованииСФО  " + ПолученоеНазваниеТабеляНаОснованииСФО);
                    }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученоеНазваниеТабеляНаОснованииСФО;
    }




















    // TODO: 15.06.2021 метод вычислчет дни недели  в потоке для отправки и принятии d1,d2,d3

    boolean МетодКоторыйВычисляетЕслиДНИвПотоке(String ПараметрИмяТаблицыОтАндройдаPostВнутриДляПоиска, JSONObject БуферСтолбикиДляВставкиВнутриВнутриДляПосика) {


        boolean ЕслиТакойДень = false;
        try{
        ////
        StringBuffer БУферИзJSONВБУФЕРАНАЛИЗА = new StringBuffer(БуферСтолбикиДляВставкиВнутриВнутриДляПосика.toString());

        ArrayList<String> АрайЛистДниТабеля = new ArrayList<String>(Arrays.asList("d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13", "d14"
                , "d15", "d16", "d17", "d18", "d19", "d20", "d21", "d22", "d23", "d24", "d25", "d26", "d27", "d28", "d29", "d30", "d31"));


            //////////
            String ЗначенияИБуфераДляПосикаДней = БУферИзJSONВБУФЕРАНАЛИЗА.toString();


            // TODO: 15.06.2021

            for (String КлючИзАрайЛиста : АрайЛистДниТабеля) {

                System.out.println(КлючИзАрайЛиста);

                //
                ЕслиТакойДень = ЗначенияИБуфераДляПосикаДней.contains(КлючИзАрайЛиста);
                ///TODO ЕСЛИ СТРАБОТАЛО ТО ВЫХОДИМ ИЗ ЦИКЛА
                if (ЕслиТакойДень == true) {

                    System.out.println("   ЕслиТакойДень " + ЕслиТакойДень);
                    /////////////
                    break;

                }


            }


            ///
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ЕслиТакойДень;
    }

// TODO: 11.08.2021  ПОЛУЧЕНИЕ ЛОКАЛЬНОЙ ВЕРИСИ ДАНННЫХ ЧАТА
    // TODO: 10.08.2021  получение УВЕЛИЧИНОЙ ВЕРСИИ ДАННЫХ ДЛЯ ЧАТА
    protected Long МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер(String Текущаятаблицы, String ТекущаяяКолонкаТаблицы, Context context, String ИмяТаблицыОтАндройда_Локальноая) {
        Long ЗначениеДляПовышениеВерсии = 1l;
        SQLiteCursor Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер;
        try{
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер=new Class_GRUD_SQL_Operations(this.context);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",Текущаятаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки",ТекущаяяКолонкаТаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","name=? ");
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);

            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных= (SQLiteCursor)  class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.
                    new GetData(this.context).getdata(class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных );
            if(Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getCount()>0){
                Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.moveToFirst();
                ЗначениеДляПовышениеВерсии=Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getLong(0);
                Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            }
            Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.close();
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы Data_Chat TRIGGER"+ЗначениеДляПовышениеВерсии);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        // TODO: 06.09.2021 результат 
        return  ЗначениеДляПовышениеВерсии;
    }




    ///todo метод подсчёта сотрудниколв их ЧАСЫ
    public String МетодПосчётаЧасовСотрудниковВТабеле(Context КонтекстДляЧасов, long UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {
        ///////

        String ОбщееКоличествоЛюдейВТабелеТекущемВнутри = null;


        try {
            Cursor Курсор_ЗагружаемТабеляДляПодсчетаЧасов = new Class_MODEL_synchronized(КонтекстДляЧасов).
                    МетодЗагружетУжеготовыеТабеля(КонтекстДляЧасов, UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников, месяцДляПермещенияПоТабелю, годДляПермещенияПоТабелю);


            //TODO цикл ПЕРЕБОРКИ ДАННЫХ
            ОбщееКоличествоЛюдейВТабелеТекущемВнутри = String.valueOf(МетодПосчётаЧасовПоСотруднику(Курсор_ЗагружаемТабеляДляПодсчетаЧасов));

            Log.d(this.getClass().getName(), "  ОбщееКоличествоЛюдейВТабелеТекущемВнутри " + ОбщееКоличествоЛюдейВТабелеТекущемВнутри);

            Курсор_ЗагружаемТабеляДляПодсчетаЧасов.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return ОбщееКоличествоЛюдейВТабелеТекущемВнутри;
    }

    protected int МетодПосчётаЧасовПоСотруднику(Cursor курсор_ЗагружаемТабеляСозданный) {
        int СуммаЧасов = 0;
        if (курсор_ЗагружаемТабеляСозданный.getCount() > 0) {
            курсор_ЗагружаемТабеляСозданный.moveToFirst();
        }
        do {

            for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {

                int ИндексЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней);

                int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(ИндексЧассыСотрудника);

                СуммаЧасов = СуммаЧасов + ЧассыСотрудника;

                Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);

            }///TODO END FOR  ПО СТОЛБЦАМ БЕЖИМ

        } while (курсор_ЗагружаемТабеляСозданный.moveToNext());
        ////TODO ПРИСВАИВАЕМ ПОЛУЧЕННЫЕ ЧАСЫ ИЗ БАЗЫ УЖЕ ПЕРЕДЕМ ЕЕ НА АКТИВТИ
        ////todo и ставим курсор на место на первое
        курсор_ЗагружаемТабеляСозданный.moveToFirst();
        return СуммаЧасов;
    }

    ///todo являеться ли весь текст числом
    public boolean МетодОпределениеВселиЦифрыВстроке(String ВселиЦифрыВтексе) {
        boolean Результат = false;
        try {
            Long.parseLong(ВселиЦифрыВтексе);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}















