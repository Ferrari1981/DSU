package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutionException;

public class Class_Sendiing_Errors {

    Context contextДляПосыланиеОшибок;
    ////

    public Class_Sendiing_Errors(Context context) {
        //
        contextДляПосыланиеОшибок=context;

    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ


    public void МетодПослываемОшибкиАдминистаторуПо(StringBuffer ЗаписьОшибковВстврочку, Activity activity,Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
        try {


            МетодУдаланиеОшибок();

            // TODO: 11.05.2021  почта для ошибок
            /// errorstimekeeping@gmail.com
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"errorsousabto@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "errorsousabto@gmail.com"+" ID пользователя чьи ошибки: " +ПубличноеIDПолученныйИзСервлетаДляUUID);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            i.putExtra(Intent.EXTRA_TEXT   , ЗаписьОшибковВстврочку.toString());

                /////TODO ВТОРОЙ ШАГ СИНХРОНИЗАЦИИ ПОЛУЧАЕМ СПИСОК ТАБЛИЦ КОТОРЫЕ НУЖНО  СИНХРОНИЗИРОВАТЬ 100% процентов , И ПРОВЕРМЯЕМ ЕСЛИ СВЯЗЬ С ИНТЕНТОМ
                    // TODO: 02.06.2022 код удаление ошибков после успешной отпавки ошибок на почту

                Handler.Callback callback=new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull android.os.Message msg) {
                        msg.getTarget().postDelayed(()->{
                            Toast.makeText(activity, "Отправляем...", Toast.LENGTH_LONG).show();
                        },2000);

                        return true;
                    }
                };

              Handler  handlerОшибки = new Handler(callback);


                Message message = new Message();
                Bundle bundle=new Bundle();
                handlerОшибки.sendMessage(message);
                activity. startActivity(Intent.createChooser(i, "Отправка ошибок..."));
                activity.finish();

            //ловим ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(contextДляПосыланиеОшибок).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            //закрытие классса и метода
        }


    }

    private void МетодУдаланиеОшибок() throws ExecutionException, InterruptedException {

        try {
        Class_GRUD_SQL_Operations class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                =new Class_GRUD_SQL_Operations(contextДляПосыланиеОшибок);
        // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ удаление данных
        class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "errordsu1");

        Integer       РезультатУдалениеОчисткиТаблиц = (Integer) class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                new DeleteData(contextДляПосыланиеОшибок)
                .deletedataAlltable(class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        new PUBLIC_CONTENT(contextДляПосыланиеОшибок).МенеджерПотоков,
                        new CREATE_DATABASE(contextДляПосыланиеОшибок).getССылкаНаСозданнуюБазу());
        // TODO: 02.06.2022 запуск отпарвки на почту ошибок

        Log.d(this.getClass().getName(), " РезультатУдалениеОчисткиТаблиц  " + РезультатУдалениеОчисткиТаблиц);
        // TODO: 02.06.2022  конец  код удаление ошибков после успешной отпавки ошибок на почту
        //
        ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(contextДляПосыланиеОшибок).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        //закрытие классса и метода
    }
    }
}


