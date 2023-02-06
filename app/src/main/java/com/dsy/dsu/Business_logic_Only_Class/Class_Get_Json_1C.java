package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;

import com.dsy.dsu.Code_Gson_Processing.SubClass_JSON_B_P_POST_1C;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.crypto.NoSuchPaddingException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



///////////--------------------------TODO ЭТО ТРЕТИЙ  КОНТРОЛЛЕР ТОЛЬКО ДЛЯ ПОЛУЧЕНИЯ  ТОЛЬКО JSON ПОЛЕЙ  И СКОЛЬКО ТАБЛИЦ НУЖНО БЕЗ  СИНХРОНИЗАЦИИИ---

public class Class_Get_Json_1C extends Class_MODEL_synchronized {
  private   Context context;
    private  String АдресСервера;
    public Class_Get_Json_1C(Context context, String АдресСервера) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        super(context);
        // super(context);
       this. context = context;
        this.АдресСервера = АдресСервера.trim();
        ////// конец  TODO созданеи шифрование
    }


////// TODO для согласования
public StringBuffer МетодПолучемJSONОт1СДляСогласования(@NonNull  Integer ПубличныйIDДляФрагмента, @NonNull String ТаблицыДляОбработки1С)
        throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
    StringBuffer stringBuffer = new StringBuffer();
    Dispatcher dispatcher=null;
    try {
        ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
        Log.e(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С);
        // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient    okHttpClientПолучаемДанныеОт1С = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("dsu1table", ТаблицыДляОбработки1С)
                                .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))//TODO old ПубличныйIDДляФрагмента   или 8
                                .header("Authorization",
                                        Credentials.basic("dsu1Admin", "dsu1Admin"));
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                }).connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
        // TODO: 25.10.2022 Диспечер
         dispatcher=  okHttpClientПолучаемДанныеОт1С.dispatcher();
        //
        ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
        Request requestGET = new Request.Builder().get().url(АдресСервера).build();
        Log.d(this.getClass().getName(), "  request  " + requestGET);
        // TODO  Call callGET = client.newCall(requestGET);
        okHttpClientПолучаемДанныеОт1С.newCall(requestGET).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                //TODO закрываем п отоки
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    stringBuffer.append(response.body().string());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                    Log.d(this.getClass().getName(), "  stringBuffer  " + stringBuffer.toString() + "  responseGet.code()" + response.code());
                }
            }
        });
        //TODO
        // TODO: 31.05.2022
        dispatcher.executorService().shutdown();
        Log.i(context.getClass().getName(), "stringBuffer" + stringBuffer);
        ///
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }finally {
        dispatcher.executorService().awaitTermination(40,TimeUnit.SECONDS);
        dispatcher.cancelAll();
    }
    return stringBuffer;
}


    // TODO: 07.07.2022  для лимита остатков
    public StringBuffer МетодПолучемJSONОт1СДляЛимитаМатериаловВторойЭтап(@NonNull  Integer ПубличныйIDДляФрагмента, @NonNull String ТаблицыДляОбработки1С,Integer СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        StringBuffer stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО = new StringBuffer();
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            Log.w(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                    + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С+  " СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно "+СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно);
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
      OkHttpClient      okHttpClientДляЛимитаМатериалов = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляОбработки1С)
                                    .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))
                                    .header("dsu1cfo",String.valueOf(СФООтправляемОпятьНа1сДляПолучениеДАнныхОбратно) )//ПубличныйIDДляФрагмента
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(1, TimeUnit.SECONDS)
                    .readTimeout(1, TimeUnit.SECONDS).build();
            //
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(АдресСервера).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClientДляЛимитаМатериалов.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                    //TODO закрываем п отоки
                    okHttpClientДляЛимитаМатериалов.dispatcher().executorService().shutdown();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО.append(response.body().string());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.d(this.getClass().getName(), "  stringBuffer  " + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО.toString() + "  responseGet.code()" + response.code());
                        //todo метод после получение данных запускам локальный бродкастер
                      //  new SubClass_CommitPayAfter_Для_СогласованияПосле().     МетодПослеСменыСтаусаСогласованЧерезЛокльныйБродКастерОтправимИзмениниянаАктивти(stringBuffer,contextGetClassNumberAllRowsJSON);
                        Log.i(context.getClass().getName(), "stringBuffer" + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО);
                    }
                    //TODO закрываем п отоки
                    okHttpClientДляЛимитаМатериалов.dispatcher().executorService().shutdown();
                }
            });
            //TODO
            // TODO: 31.05.2022
             while (!okHttpClientДляЛимитаМатериалов.dispatcher().executorService().isShutdown());
             //TODO
            Log.i(context.getClass().getName(), "stringBuffer" + stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО);
           // okHttpClient.dispatcher().executorService().awaitTermination(1,TimeUnit.MINUTES);
            okHttpClientДляЛимитаМатериалов.dispatcher().executorService().awaitTermination(20,TimeUnit.SECONDS);
            // TODO: 06.07.2022

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return stringBufferВторайШагПолучаемСамиДанныеНАОснованииЦФО;
    }



    ////// TODO ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET JSON только когда иы хотим узнать все строки json  по всем строкам мы запускаем этот код И ВСЕ !!!!

    public StringBuffer МетодПолучемJSONОт1СДляЛимитаМатериаловЭтапПервый(@NonNull  Integer ПубличныйIDДляФрагмента, @NonNull String ТаблицыДляОбработки1С)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        StringBuffer stringBufferМатериаловЭтапПервый = new StringBuffer();
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            Log.w(this.getClass().getName(), "   ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента
                    + " ТаблицыДляОбработки1С " +ТаблицыДляОбработки1С);
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
         OkHttpClient   okHttpClientЛиммитМатериаловЭтаппервый = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляОбработки1С)
                                    .header("dsu1user", String.valueOf(113))///TODO old      113           .header("dsu1user", String.valueOf(ПубличныйIDДляФрагмента))
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(1, TimeUnit.SECONDS)
                    .readTimeout(1, TimeUnit.SECONDS).build();
            //
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(АдресСервера).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            okHttpClientЛиммитМатериаловЭтаппервый.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                    //TODO закрываем п отоки
                    okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().shutdown();
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        stringBufferМатериаловЭтапПервый.append(response.body().string());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.d(this.getClass().getName(), "  stringBufferМатериаловЭтапПервый  " + stringBufferМатериаловЭтапПервый.toString() + "  responseGet.code()" + response.code());
                        //todo метод после получение данных запускам локальный бродкастер
                        //  new SubClass_CommitPayAfter_Для_СогласованияПосле().     МетодПослеСменыСтаусаСогласованЧерезЛокльныйБродКастерОтправимИзмениниянаАктивти(stringBuffer,contextGetClassNumberAllRowsJSON);
                        Log.i(context.getClass().getName(), "stringBufferМатериаловЭтапПервый" + stringBufferМатериаловЭтапПервый);
                    }
                    //TODO закрываем п отоки
                    okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().shutdown();
                }
            });
            //TODO
            // TODO: 31.05.2022
            while (!okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().isShutdown());
            //TODO
            Log.i(context.getClass().getName(), "stringBufferМатериаловЭтапПервый" + stringBufferМатериаловЭтапПервый);
            // okHttpClient.dispatcher().executorService().awaitTermination(1,TimeUnit.MINUTES);
            okHttpClientЛиммитМатериаловЭтаппервый.dispatcher().executorService().awaitTermination(30,TimeUnit.SECONDS);
            // TODO: 06.07.2022
            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
//TODO
        return stringBufferМатериаловЭтапПервый;
    }

























//TODO ВТОРОЙ МЕТОД ОТПРАВЛЯЕМ ДАННЫЕ  НА 1С POST()
    ////// TODO ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET JSON только когда иы хотим узнать все строки json  по всем строкам мы запускаем этот код И ВСЕ !!!!
    public StringBuffer МетодОтправляемJSONОт1С(
            String ПолученныйНомерДокументаСогласования
            , Integer ПередаемСтатусСогласования
            , String ТаблицыДляPOST,
            Integer ПубличныйIDДляCогласования)
            throws InterruptedException, ExecutionException, NoSuchAlgorithmException, IOException, KeyManagementException, TimeoutException {
        //TODO
        StringBuffer stringBufferотправкаНа1С = new StringBuffer();
        Log.d(this.getClass().getName(), "  ПолученныйНомерДокументаСогласования  " + ПолученныйНомерДокументаСогласования +
                " ПередаемСтатусСогласования " + ПередаемСтатусСогласования +
                " ТаблицыДляPOST " + ТаблицыДляPOST );
        Dispatcher dispatcher = null;
        try {
            ////todo ТОЛЬКО КОГДА НЕ ОТЛАДКА
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
         OkHttpClient   okHttpClientОтправкаСоглоавания = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("dsu1table", ТаблицыДляPOST)
                                    .header("dsu1user", String.valueOf(ПубличныйIDДляCогласования))
                                    .header("returnstatus", String.valueOf(ПередаемСтатусСогласования))
                                    .header("dsu1number", ПолученныйНомерДокументаСогласования)
                                    .header("Authorization",
                                            Credentials.basic("dsu1Admin", "dsu1Admin"));

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS).build();
                    dispatcher= okHttpClientОтправкаСоглоавания.dispatcher();

            //TODO POST () Генерируем JSON на отправку
            StringBuffer stringBufferСгенерированыйJSONДляОтпрвкиНа1С= МетодГенерацииJSONДляОтправкиЕго1С(ПолученныйНомерДокументаСогласования, ПередаемСтатусСогласования);
            RequestBody bodyДляОтправки = RequestBody.create(MediaType.parse("application/json; charset=utf-16"),stringBufferСгенерированыйJSONДляОтпрвкиНа1С.toString());

            Request requestPOST = new Request.Builder()
                    .post(bodyДляОтправки)
                    .url(АдресСервера).build();
            //   Call callPOST = client.newCall(requestPOST);
            okHttpClientОтправкаСоглоавания.newCall(requestPOST).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    try{
                    Log.d(this.getClass().getName(), "  post call  " + call + "  e" + e.toString());
                    ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                } catch (Exception ex) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    //TODO
                    try{
                    if (response.isSuccessful()) {
                        //TODO
                        stringBufferотправкаНа1С.append(response.body().string());
                        Log.d(this.getClass().getName(), "   stringBufferотправкаНа1С  " + stringBufferотправкаНа1С.toString() +
                                "  responsePOST.code()" + response.code());
                        ///TODO оттоепт обрабно о резульатате вставки статуса в 1с согласования
                        Log.i(context.getClass().getName(), "stringBuffer" + stringBufferотправкаНа1С);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }}
            });
            dispatcher.executorService().shutdown();
            Log.i(context.getClass().getName(), "stringBuffer" + stringBufferотправкаНа1С);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }finally {
            dispatcher.executorService().awaitTermination(1,TimeUnit.MINUTES);
            dispatcher.cancelAll();
        }
//TODO
        return stringBufferотправкаНа1С;
    }










    //TODO метод геренации JSON  пр помощи GSON и отправка ее на 1С

    private StringBuffer МетодГенерацииJSONДляОтправкиЕго1С( @NonNull String ПолученныйНомерДокументаСогласования, @NonNull Integer ПередаемСтатусСогласования) {
        StringBuffer stringBuffer_POST_1C=new StringBuffer();
        try{
        LinkedBlockingDeque<SubClass_JSON_B_P_POST_1C> subClass_json_b_p_post_1CS =new LinkedBlockingDeque<SubClass_JSON_B_P_POST_1C>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        subClass_json_b_p_post_1CS.addLast(new SubClass_JSON_B_P_POST_1C(ПередаемСтатусСогласования, ПолученныйНомерДокументаСогласования));
        //TODO генерация JSON полей
            stringBuffer_POST_1C.append(gson.toJson(subClass_json_b_p_post_1CS)) ;
            Log.d(this.getClass().getName(), "stringBuffer_POST_1C  " + stringBuffer_POST_1C.toString());
        Log.d(this.getClass().getName(), "  ПолученныйНомерДокументаСогласования  " + ПолученныйНомерДокументаСогласования +
                " subClass_json_b_p_post_1CS " + subClass_json_b_p_post_1CS);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  stringBuffer_POST_1C;
    }


// TODO: 07.07.2022  получения данных тольо для согласования
    public StringBuffer МетодПингаИПОлучениеДанныхОт1сДляСогласования(@NonNull Context context, @NonNull Integer ПубличныйIDДляФрагмента) throws JSONException {
        //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
       StringBuffer БуферПолученияДанныхОт1СДляСогласования = new StringBuffer();
        try{
            //todo test code  end
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента+ "  АдресСервера "+АдресСервера);
            /// ПубличныйIDДляФрагмента=8;
            //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
            БуферПолученияДанныхОт1СДляСогласования =
                    new Class_Get_Json_1C(context,АдресСервера)//TODO  "http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments"    //todo old debug "http://192.168.254.145/dsutest/hs/jsonto1c/listofdocuments"
                            .МетодПолучемJSONОт1СДляСогласования(ПубличныйIDДляФрагмента,"sog");// TODO: 21.06.2022  как пример 125  -это камиль
            // TODO: 26.05.2022
            Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных " + БуферПолученияДанныхОт1СДляСогласования);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
        }
        return БуферПолученияДанныхОт1СДляСогласования;
    }















}