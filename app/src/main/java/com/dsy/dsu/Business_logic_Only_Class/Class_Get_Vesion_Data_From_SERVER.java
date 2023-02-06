package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Class_Get_Vesion_Data_From_SERVER {

    Context contextДляПолучениеВерсииДанных;

    // TODO: 13.10.2021


    ///////TODO
    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;


    String    ПубличноеИмяПользовательДлСервлета=         new String();

    /////
    String      ПубличноеПарольДлСервлета=         new String();

    public Class_Get_Vesion_Data_From_SERVER(Context context) {

        contextДляПолучениеВерсииДанных=context;
///////TODO
           Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(contextДляПолучениеВерсииДанных);
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ

    // TODO: 04.08.2021  данный метод порлучаем фактичесоое количество строк СТРОК НА СЕРВЕРЕ



    StringBuffer УниверсальныйБуферПолучениеПоследуюВерсиюДанныхТОлькоДляЧатаОтМетодаHEAD(String Адресподключения,
                                                                                          int ПортПодключения,
                                                                                          String ТекущаяТаблиувДляПОлученияВерсииДанных,
                                                                                          Long РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер)
            throws IOException, ExecutionException, InterruptedException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {

        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        // AsyncTask AsyncTaskУнивермальныйДляОбмена=null;


        ///

        StringBuffer БуферПолученнниеДанныхФинальнойВерсииДанныхДляЧатаОтМетодаHEAD = new StringBuffer();


        System.out.println("УниверсальныйБуферПолучениеДанныхсСервера");
        ////////перевод перменных внутрь Анисакска
        ///////// ПРИ НУЛЕВОМ ЗАПУСКЕ ЛОВИМ ЭТОТ МОМЕНТ ДАТОЙ ИЗ ТАБЛИЦЫ АНДОЙД
        //////КОНЕЦ ЛОВЛИ НУЛОЙ ДАТЫ ТАБЛИЦЫ
        Object ОшибкаТекущегоМетода = new Object();

        HttpURLConnection ПодключениеПолученияДанныхсСервер = null;

        ////
        try {
            ////


            String Adress_String = new String();
            ////
            String Params = new String();


            //PUBLIC_CONTENT.ПубличныйАдресGlassFish = "http://tabel.dsu1.ru:8888/"; //http://80.66.149.58:8888   //http://tabel.dsu1.ru/
            ////192.168.254.40
            //  Adress_String = "http://tabel.dsu1.ru:8888/dsu1.glassfish/DSU1JsonServlet=";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58
            Adress_String = "http://"+Адресподключения+":"+ПортПодключения+"/dsu1.glassfish/DSU1JsonServlet?tablchats="+ТекущаяТаблиувДляПОлученияВерсииДанных+
                    "&current_chat_android="+РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер+"&" +
                    "Хотим%20Получить%20ID%20для%20Генерации%20%20UUID&ДатаНаДанныеВнутриПотока=" +
                    "&IDДляПолучениеКонткртнойНабораТаблиц=";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58
            ///Adress_String = "http://192.168.254.40:8080/dsu1.glassfish/DSU1JsonServlet?ИмяТаблицыОтАндройда=%20&КонкретнаяТаблицаВПотоке=&МакАдресТелефона=&ЗаданиеДляСервлетаВнутриПотока=Хотим%20Получить%20ID%20для%20Генерации%20%20UUID&ДатаНаДанныеВнутриПотока=&IDДляПолучениеКонткртнойНабораТаблиц=";///СТРОЧКА УКАЗЫВАЕТ НА КАКОЙ СЕРВЕЛ НА СЕРВЕР МЫ БУДЕМ СТУЧАТЬСЯ /// 80.66.149.58

            /// dsu1.glassfish/DSU1JsonServlet
            ///////////;
            Log.d(this.getClass().getName(), "Adress_String " + Adress_String);

            ////
            Adress_String = Adress_String.replace(" ", "%20");
            ///
            Log.d(this.getClass().getName(), " Adress_String " + Adress_String);


            // TODO: 25.05.2021  адереса

            URL Adress = new URL(Adress_String);
            //
            ПодключениеПолученияДанныхсСервер = (HttpURLConnection) (Adress).openConnection();/////САМ ФАЙЛ JSON C ДАННЫМИ
            ///


           // ПодключениеПолученияДанныхсСервер.setDoInput(true);

         //   ПодключениеПолученияДанныхсСервер.setDoOutput(true);
            ////////
            ПодключениеПолученияДанныхсСервер.setRequestProperty("Content-Type", "application/txt ;charset=UTF-8");

            ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");

            ПодключениеПолученияДанныхсСервер.setRequestProperty("Connection", "Keep-Alive");

            ПодключениеПолученияДанныхсСервер.setRequestProperty("Accept-Language", "ru-RU");

            ПодключениеПолученияДанныхсСервер.setRequestMethod("HEAD"); ///   HEAD GET //ПРОВЕРЯЕМ ЕСЛИ ПОДКЛЮЧЕНИЕ К СЕВРЛЕТУ НА СЕРВЕР ВЫБРАСЫВАЕМ

            ПодключениеПолученияДанныхсСервер.setReadTimeout(10000); //todo САМ ТАЙМАУТ ПОДКЛЮЧЕНИЕ(30000);

            ПодключениеПолученияДанныхсСервер.setConnectTimeout(10000);//todo САМ ПОТОК ДАННЫХ(1000); САМ STREAM

            ПодключениеПолученияДанныхсСервер.setUseCaches(true);
            //////////

            //TODO  ПУБЛИЧНЫЙ ЛОГИН и ПАРОЛЬ

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(contextДляПолучениеВерсииДанных);
            ///
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");


            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(contextДляПолучениеВерсииДанных);


            ///////
            SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(contextДляПолучениеВерсииДанных).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                ////
                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                /////
                ПубличноеИмяПользовательДлСервлета=         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0);

                /////
                ПубличноеПарольДлСервлета=           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1);



                Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета=         " + ПубличноеИмяПользовательДлСервлета+ " ПубличноеПарольДлСервлета" +ПубличноеПарольДлСервлета);
            }
            //////////
            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеИмяПользовательДлСервлета +
                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПарольДлСервлета);
            /////
            ПодключениеПолученияДанныхсСервер.setRequestProperty(ПубличноеИмяПользовательДлСервлета, ПубличноеПарольДлСервлета);



 /*           ПодключениеПолученияДанныхсСервер.setRequestProperty("tablchats", ТекущаяТаблиувДляПОлученияВерсииДанных);


            // TODO: 11.08.2021 поулчаем версию данных кльный чата

            ПодключениеПолученияДанныхсСервер.setRequestProperty("current_chat_android", String.valueOf(РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер));

*/


            ПодключениеПолученияДанныхсСервер.connect(); /////////////ТОЛЬКО СОЕДИНЕНИЕ

            // ПодключениеПолученияДанныхсСервер.getResponseCode();///ВАЖНАЯ КОМАНДА  СТУЧИТЬСЯ В СЕРВЛЕТ ECLIPSE СТУЧИМСЯ ВТОРОЙ РАЗ ЧТОБЫ ПОЛУЧИТЬ УЖЕ САМ JSON

            ПодключениеПолученияДанныхсСервер.getContent(); ////РЕАЛЬНОЕ ПОЛУЧЕНИЕ ДАННЫХ С ИНТРЕНЕТА





            // TODO: 11.08.2021 получениея версии чата версия данных CURRENT _CHAT


            Log.d(this.getClass().getName(), "ПодключениеПолученияДанныхсСервер.getHeaderField(current_chat_server) " + ПодключениеПолученияДанныхсСервер.getHeaderField("stream_current_chat_server"));


            // TODO: 11.08.2021 ВекрсияДанных с сервера на Сервера
            Long РезультатВерсииДанныхЧатаНаСервере=0l;

            РезультатВерсииДанныхЧатаНаСервере= Long.parseLong(ПодключениеПолученияДанныхсСервер.getHeaderField("stream_current_chat_server"));

            Log.d(this.getClass().getName(), "РезультатВерсииДанныхЧатаНаСервере " + РезультатВерсииДанныхЧатаНаСервере);



          /*  Log.d(this.getClass().getName(), "ПодключениеИнтернетДляОтправкиНаСервер.getContentLength() " + ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));

            Long РазмерПришедшегоПотока = Long.parseLong(ПодключениеПолученияДанныхсСервер.getHeaderField("stream_size"));

            Log.d(this.getClass().getName(), "РазмерПришедшегоПотока " + РазмерПришедшегоПотока);
*/


            ////TODO И ЕСЛИ ПРИШЕЛ ОТ СЕРВЕРА ОТВЕТ ПОЛОЖИТЕЛЬНО ТО ТОГДА ЗАПУСКАМ ПРОЧТЕНИЯ ПОТОКА ПРИШЕДШЕГО С СЕРВЕРА
            if (ПодключениеПолученияДанныхсСервер.getResponseCode() == 200 && РезультатВерсииДанныхЧатаНаСервере > 0) {
                //TODO шифровани
                // Log.d(this.getClass().getName(), "  ПолитикаРасшифровки  " +PUBLIC_CONTENT. ПолитикаРасшифровки);


                БуферПолученнниеДанныхФинальнойВерсииДанныхДляЧатаОтМетодаHEAD.append(РезультатВерсииДанныхЧатаНаСервере);
                ////
                Log.i(this.getClass().getName(), "БуферПолученнниеДанныхФинальнойВерсииДанныхДляЧатаОтМетодаHEAD " + БуферПолученнниеДанныхФинальнойВерсииДанныхДляЧатаОтМетодаHEAD.toString());
            }     ////TODO нет ответа от сервер или поток нулевой
            else {
                Log.i(this.getClass().getName(), "ПОТОК ПРИШЕЛ НУЛОВОЙ ОТ СЕРВЕРА  " + ПодключениеПолученияДанныхсСервер.getInputStream().available());
            }


            ПодключениеПолученияДанныхсСервер.disconnect();
/////////////////////////


            ///////
        } catch (IOException ex) {
            ex.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
            new   Class_Generation_Errors(contextДляПолучениеВерсииДанных).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

        //// todo get ASYNtASK
        return (StringBuffer) БуферПолученнниеДанныхФинальнойВерсииДанныхДляЧатаОтМетодаHEAD;


    }


}
