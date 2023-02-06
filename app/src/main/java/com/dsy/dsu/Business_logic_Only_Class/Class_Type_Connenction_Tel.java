package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Class_Type_Connenction_Tel extends Class_GRUD_SQL_Operations {

    Context contextДляОпределенияКакойТИмПодключения=null;
//
    Activity activityКакойТИмПодключения;
    Class_GRUD_SQL_Operations classGrudSqlOperations;

    public Class_Type_Connenction_Tel(Context context) {
        super(context);

        contextДляОпределенияКакойТИмПодключения=context;
        //


    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 05.07.2021

    //// второй метод с современный
    public String МетодОпределяемКакойТипПодключениеWIFIилиMobile() {

        String КакойТипПодключения = new String();
        //
        try {

            // TODO: 02.09.2021  проверяем какое подключение
            //todo  сам код

            ConnectivityManager connManager = (ConnectivityManager) contextДляОпределенияКакойТИмПодключения.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (mWifi.isConnected()) {
                // Do whatever

                КакойТипПодключения="WIFI";

                Log.d(Class_MODEL_synchronized.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
            }else{
                // TODO: 29.09.2021

                NetworkInfo mMOBILE = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if (mMOBILE.isConnected()) {
                    // Do whatever

                    КакойТипПодключения="Mobile";

                    Log.d(Class_MODEL_synchronized.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
                }

            }


            ////////

            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextДляОпределенияКакойТИмПодключения).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return КакойТипПодключения;
    }


}
