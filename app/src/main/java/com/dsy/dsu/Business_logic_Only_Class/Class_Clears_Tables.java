package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Tabels_Users_And_Passwords;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.crypto.NoSuchPaddingException;
public class Class_Clears_Tables {
    Context context;
      Handler handlerУдалениеТаблицПринудительно;
    CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private SharedPreferences preferences;
    // TODO: 24.02.2022
    public Class_Clears_Tables(Context context, Handler handlerУдалениеТаблицПринудительно) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.context = context;
        this.Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(context);
        this.handlerУдалениеТаблицПринудительно = handlerУдалениеТаблицПринудительно;
        preferences = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer ОчисткаТаблицДляПользователяЗапусксFaceApp(Context context,
                                                              CompletionService МенеджерПотоковВнутрений,
                                                              Activity activity)
            throws ExecutionException, InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда=    new PUBLIC_CONTENT(context).  МетодТОлькоЗаполенияНазваниямиТаблицДляОмена(context);
       // ИменаТаблицыОтАндройда.put("MODIFITATION_Client");
        // TODO: 28.09.2022  дополнительно только тут еще одна таблица
        ИменаТаблицыОтАндройда.add("successlogin");
        ИменаТаблицыОтАндройда.add("settings_tabels");
        ИменаТаблицыОтАндройда.add("errordsu1");
        // TODO: 24.02.2022
        final Integer[] РезультатДобавленияДатыВерсииПослеУдаленияФинал = {0};
        Class_GRUD_SQL_Operations class_grud_sql_operationsОчистакаталиц = new Class_GRUD_SQL_Operations(activity);
        ProgressDialog progressDialogДляУдалениеТаблиц;
        progressDialogДляУдалениеТаблиц = new ProgressDialog(activity);
        progressDialogДляУдалениеТаблиц.setTitle("Смена данных");
        progressDialogДляУдалениеТаблиц.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialogДляУдалениеТаблиц.setProgress(0);
        progressDialogДляУдалениеТаблиц.setCanceledOnTouchOutside(false);
        progressDialogДляУдалениеТаблиц.setMessage("в процессе...");
        progressDialogДляУдалениеТаблиц.show();
        МетодВизуальнгоотображенияХодаУдаления(progressDialogДляУдалениеТаблиц,activity);
        // TODO: 24.02.2022
        Thread ПотокДляПрогрессПриУдаленииТаблиц =    new Thread(new Runnable() {
            public void run() {
                Log.d(this.getClass().getName()," ИменаТаблицыОтАндройда   "+ИменаТаблицыОтАндройда);
        // TODO: 24.02.2022
                 ИменаТаблицыОтАндройда.stream()
                .filter(e->!e.equalsIgnoreCase("fio"))
                .filter(e->!e.equalsIgnoreCase("cfo"))
                .filter(e->!e.equalsIgnoreCase("organization"))
                .filter(e->!e.equalsIgnoreCase("depatment"))
                 .filter(e->!e.equalsIgnoreCase("region"))
                 .filter(e->!e.equalsIgnoreCase("nomen_vesov"))
                 .filter(e->!e.equalsIgnoreCase("type_materials"))
                         .forEachOrdered((текущаяТаблицаДляУдваления)-> {
              try {
              // TODO: 09.09.2021 DELETE УДАЛЕНИЕ ТАБЛИЦ ПЕРЕД УМЕНЫ ПОЛЬЗОВАТЕЛЯ
              Integer РезультатУдалениеДанных=
                      ОчисткаТаблицысЗаписьюВMODIFITATION_Client( текущаяТаблицаДляУдваления.toString().trim(),context,МенеджерПотоковВнутрений);

              Log.d(this.getClass().getName(), "РезультатУдалениеДанных " + РезультатУдалениеДанных+ " текущаяТаблицаДляУдваления " +текущаяТаблицаДляУдваления);
              // TODO: 09.09.2021  действие второе добалянеим дату


                  handlerУдалениеТаблицПринудительно.obtainMessage(РезультатУдалениеДанных,текущаяТаблицаДляУдваления).sendToTarget();
                  Message message=new Message();
                  message.obj=текущаяТаблицаДляУдваления;
                  handlerУдалениеТаблицПринудительно.sendMessageAtFrontOfQueue(message);
                 TimeUnit.MILLISECONDS.sleep(200);
                  Log.d(context.getClass().getName(), "messageудаление " +текущаяТаблицаДляУдваления);

              if (РезультатУдалениеДанных>0) {
                  // TODO: 21.02.2022
              if (РезультатУдалениеДанных>0) {
                  // TODO: 21.02.2022
                      РезультатДобавленияДатыВерсииПослеУдаленияФинал[0]++;
                  }
              }
                  // TODO: 21.02.2022
          } catch (SQLException | ExecutionException | InterruptedException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
          });
                // TODO: 24.02.2022

                progressDialogДляУдалениеТаблиц.dismiss();
                ////
                progressDialogДляУдалениеТаблиц.cancel();

                handlerУдалениеТаблицПринудительно.removeMessages(1);

                handlerУдалениеТаблицПринудительно.removeCallbacks(null);


                // TODO: 24.02.2022
                МетодПослеУдаленияТаблицЗапускаемСледуюЩеЗанимАктивтиИмяИПароль(activity);
        ///////////////////////
            }
            //////todo
        });

        // TODO: 24.02.2022

        ПотокДляПрогрессПриУдаленииТаблиц.start();
        // TODO: 24.02.2022


        // TODO: 26.10.2021

            ////
        return    РезультатДобавленияДатыВерсииПослеУдаленияФинал[0];
    }


    // TODO: 24.02.2022 метод визуального отобоажегия хода удаения
   void МетодВизуальнгоотображенияХодаУдаления(ProgressDialog progressDialogДляУдалениеТаблиц,
                                               Activity activity) {
       // TODO: 24.02.2022
       try{
       handlerУдалениеТаблицПринудительно = new Handler() {
           public void handleMessage(android.os.Message msg) {


                   // обновляем TextView
                   progressDialogДляУдалениеТаблиц.setMessage("Удаление таблицы ..."+msg.obj);
                   // TODO: 24.02.2022
                   Log.w(this.getClass().getName(), "  Удаление таблицы .. " +
                           "  doOnTerminate Синхронизация Данных с Web-сервера ДСУ-1 ? msg.what "
                           + msg.what+ " msg.obj " +msg.obj);

           };
       };
           // TODO: 24.02.2022
       } catch (Exception e) {
           e.printStackTrace();
///метод запись ошибок в таблицу
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread()
                   .getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new   Class_Generation_Errors(activity).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                   this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
    }

    // TODO: 24.02.2022

    private void МетодПослеУдаленияТаблицЗапускаемСледуюЩеЗанимАктивтиИмяИПароль(Activity activity) {

        Intent Интент_Меню=new Intent();
        try {

            handlerУдалениеТаблицПринудительно.post(new Runnable() {
                @Override
                public void run() {
                    // TODO: 24.02.2022


                    ////////
                    /// КакойРежимСинхрониазции = ИнтентКакаяПоСчетуСинхронизация.getStringExtra("РежимЗапускаСинхронизации");
                    Toast.makeText(activity, " Удаляемая таблица прошло успешно !!! " , Toast.LENGTH_SHORT).show();

                    Интент_Меню.putExtra("РежимЗапускаСинхронизации","ПовторныйЗапускСинхронизации");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("РежимЗапускаСинхронизации","ПовторныйЗапускСинхронизации");
                    editor.commit();

                    /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
                    Интент_Меню.setClass(activity, MainActivity_Tabels_Users_And_Passwords.class); //MainActivity_Visible_Async //MainActivity_Face_App

                    Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP

                    activity. startActivity(Интент_Меню);

                    ////TODO ДАННАЯ КОМАНДА ПЕРЕКРЫВАЕТ НЕ ЗАПУСКАЕМОЕ АКТИВТИ А АКТИВТИ КОТОРЕ ЕГО ЗАПУСТИЛО
                    activity. finish();


                    // TODO: 24.02.2022

                    Toast.makeText(activity,
                            " Успешное смена данных !!! "    , Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread()
                    .getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(activity).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    // TODO: 09.09.2021 delete data for tabels
    protected Integer ОчисткаТаблицысЗаписьюВMODIFITATION_Client(String ИмяТаблицы,Context context
            ,CompletionService МенеджерПотоковВнутрений) throws ExecutionException, InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//
        Integer РезультатУдалениеОчисткиТаблиц = 0;
        try {
            //
            Log.d(this.getClass().getName(), "  ИмяТаблицы " + ИмяТаблицы);
            // TODO: 09.09.2021 первое ДЕЙСТВИЕ УДАЛЕНИЕ ТАБЛИЦЫ
           Class_GRUD_SQL_Operations class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц=new Class_GRUD_SQL_Operations(context);
            // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ удаление данных
            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", ИмяТаблицы);
            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            РезультатУдалениеОчисткиТаблиц = (Integer) class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                    new DeleteData(context).deletedataAlltable(class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоковВнутрений,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            ///
            Log.d(context.getClass().getName(), " РезультатУдалениеОчисткиТаблиц" + "--" + РезультатУдалениеОчисткиТаблиц);/////
            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеОчисткиТаблиц;
    }





    // TODO: 09.09.2021  метод добалвние в таблице даты

    Integer МетодПослеУдаленияДобавляемДатуВерсииИОбнуляемВерсиюДАнныхвТаблицеMODIFITATION_Client(String ИмяТаблицы, CompletionService МенеджерПотоковВнутрений) {
        Integer ДобавлениеДатыПослеУдалниеТаблиц = 0;
        try {
            ContentValues contentValuesОчисткаТаблицДобавлениеДат = new ContentValues();
            contentValuesОчисткаТаблицДобавлениеДат.put("localversionandroid", "1900-01-10 00:00:00");
            contentValuesОчисткаТаблицДобавлениеДат.put("versionserveraandroid", "1900-01-10 00:00:00");
            contentValuesОчисткаТаблицДобавлениеДат.put("localversionandroid_version", 0);
            contentValuesОчисткаТаблицДобавлениеДат.put("versionserveraandroid_version", 0);
            Class_GRUD_SQL_Operations  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","name");
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",ИмяТаблицы);
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
            ////TODO КОНТЕЙНЕР ДЛЯ ОБНОВЛЕНИЯ
            class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(contentValuesОчисткаТаблицДобавлениеДат);
            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            ДобавлениеДатыПослеУдалниеТаблиц= (Integer)  class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.
                    new UpdateData(context).updatedata(class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    class_grud_sql_operationsПослеУдаленияДобавляемДатуВерсии.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоковВнутрений,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), " сработала ...  обнуление версии в MODIFITATION_Client для таблицы " + ИмяТаблицы+
                    " ДобавлениеДатыПослеУдалниеТаблиц  " +ДобавлениеДатыПослеУдалниеТаблиц);

        } catch (SQLException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ///////
        return ДобавлениеДатыПослеУдалниеТаблиц;
    }


}