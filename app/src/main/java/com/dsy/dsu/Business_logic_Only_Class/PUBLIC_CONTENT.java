package com.dsy.dsu.Business_logic_Only_Class;


import android.content.Context;
import android.os.Handler;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

;import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;


/////--------TODO В ДАННОМ КЛАССЕ СОБРАНЫ ВСЕ СТАТИЧЕСКИЕ ПЕРЕМЕННЫЕ ДЛЯ РАБОТЫ ВСЕГО ПРИЛОЖЕНИЯ DSU-1  ( И БОЛЬШНЕ В КЛАСЕ НИЧЕГО НЕТ )
public  class PUBLIC_CONTENT extends SubClassCreatingMainAllTables {
            //////////
            CompletableFuture completableFutureМенеджер;
                ScheduledFuture scheduledFuture;
                Context context;
                public CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда = new CopyOnWriteArrayList();
    public ArrayList<String> ИменаПроектовОтСервера = new ArrayList<String>(); ////список проектов
    ////ГЛАВНЫЙ СПИСОК ТАБЛИЦ ДЛЯ  ОБМЕНАМИ ДАННЫМИ ИЗ НЕГО БУДЕТ БРАТЬСЯ СПИСКО ТАБЛИЦ
    public Map<String, Long> ДатыТаблицыВерсииДанныхОтСервера = Collections.synchronizedMap(new LinkedHashMap<String, Long>());
           public    CompletionService МенеджерПотоков;
           public    CompletionService МенеджерМногоПотоков;
           public ExecutorService МенеджерПотоковСервис;
    public Handler.Callback callback;
    private   LinkedHashMap<Integer,String> МассивПортовСервера= new LinkedHashMap();
    // TODO: 10.11.2022
    public PUBLIC_CONTENT(Context context) {
        super(context);
        this.context=context;
      //////////todo  ГЛАВНЫЙ МЕНЕДЖЕР ПОТОКОВ ПРОЕКТА
        if (МенеджерПотоков==null) {
              //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
              // МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newWorkStealingPool());
        }
        if (МенеджерМногоПотоков==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
          //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newCachedThreadPool());
        }

        if (МенеджерПотоковСервис==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерПотоковСервис=  (ExecutorService  )  Executors.newSingleThreadExecutor();
        }
    }
// TODO: 27.08.2022 GSON
public Gson gson = new GsonBuilder()
        .registerTypeAdapter(Object.class, new JsonSerializer<Object>() {

            @Override
            public JsonElement serialize(Object arg0, Type arg1, JsonSerializationContext arg2) {
                // TODO Auto-generated method stub
                return null;
            }

        })
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        .serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create();


    private   String  СсылкаНаРежимСервера;
    public String getСсылкаНаРежимСервера() {
        СсылкаНаРежимСервера="dsu1.glassfish.atomic";//TODO РЕЛИЗ
        return СсылкаНаРежимСервера.trim();
    }


    public LinkedHashMap<Integer,String> getМассивПортовСервера() {

        МассивПортовСервера.putIfAbsent(8080,"192.168.254.40");// TODO: 10.11.2022 Debug
        МассивПортовСервера.putIfAbsent(8084,"192.168.254.40");// TODO: 10.11.2022 Debug
        МассивПортовСервера.putIfAbsent(8085,"192.168.254.40");// TODO: 10.11.2022 Debug

     /* МассивПортовСервера.putIfAbsent(8888,"tabel.dsu1.ru");// TODO: 10.11.2022 Релиз
       МассивПортовСервера.putIfAbsent(8889,"tabel.dsu1.ru");// TODO: 10.11.2022 Релиз
        МассивПортовСервера.putIfAbsent(8890,"tabel.dsu1.ru");// TODO: 10.11.2022 Релиз*/


        return МассивПортовСервера;
    }

    // TODO: 06.07.2022 Два Адреса Проекта Для Реальной РАбты И Для Отладки




   /* // TODO: 06.07.2022 Два ПОРТЫ Проекта Для Реальной РАбты И Для Отладки
    private Integer ПортСервераОтладка;
    public Integer getПортСервера() {
    ПортСервераОтладка=8080;//TODO ОТЛАДКА
     //ПортСервераОтладка=   8888;//TODO РЕЛИЗ
        return ПортСервераОтладка;
    }
*/

}
///////-------------------------TODO    КЛАСС ВСТАВКИ ОШИБОК------------------




