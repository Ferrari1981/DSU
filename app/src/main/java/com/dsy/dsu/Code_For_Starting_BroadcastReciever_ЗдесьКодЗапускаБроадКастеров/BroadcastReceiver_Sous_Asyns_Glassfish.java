package com.dsy.dsu.Code_For_Starting_BroadcastReciever_ЗдесьКодЗапускаБроадКастеров;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_WORK_MANGER_DIRECT;
import com.dsy.dsu.Business_logic_Only_Class.SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish;

import java.util.Date;

public class BroadcastReceiver_Sous_Asyns_Glassfish extends BroadcastReceiver {
    public BroadcastReceiver_Sous_Asyns_Glassfish() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР BroadcastReceiver_Sous_Asyns_Glassfish   " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ BroadcastReceiver_Sous_Asyns_Glassfish   (intent.getAction()   СЛУЖБА " + new Date());
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
                Log.i(this.getClass().getName(), " Внутри Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::" +(intent.getAction().toString())+"\n"+
                        " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());
                    Integer ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish().МетодПолучениеяПубличногоID(context);
                    Log.i(this.getClass().getName(), " ВЫХОД ИЗ МЕТОДА     МетодЗапускаСинхоронизацииИзШироковещательногоПриёмника(context); " +
                            " BroadcastReceiver_Sous_Asyns_Glassfish (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::" +(intent.getAction().toString())+"\n"+
                            " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());
                        new Class_Generation_WORK_MANGER_DIRECT(context).МетодЗапускаСинхоронизацииИзШироковещательногоПриёмника(context,ПубличныйIDДляФрагмента);
                        Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА есть входящий пользолватель " +
                                "....Внутри BroadcastReceiver_Sous_Asyns_Glassfish  periodicWorkRequestСинхронизация.getId() ВходящиеПараментыБродКсстера "
                                + ПубличныйIDДляФрагмента+ "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;
            Log.e(context.getClass().getName(), " ОШИБКА В public class BroadcastReceiver_Sous_Asyns_Glassfish extends BroadcastReceiver {  " + " ОШИБКА ::" + e.toString());

        }
    }
}