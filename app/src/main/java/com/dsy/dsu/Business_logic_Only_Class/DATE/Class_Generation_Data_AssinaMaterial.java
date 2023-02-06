package com.dsy.dsu.Business_logic_Only_Class.DATE;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Class_Generation_Data_AssinaMaterial extends  Class_Generation_Data {
    Context context;
    String ВходящаяДатаМатериалы;

    public Class_Generation_Data_AssinaMaterial(Context context) {
        super(context);
    }
    public Class_Generation_Data_AssinaMaterial(Context context,String ВходящаяДатаМатериалы) {
        super(context);
        this.ВходящаяДатаМатериалы=ВходящаяДатаМатериалы;
    }
    @Override
    public String ГлавнаяДатаИВремяОперацийСБазойДанных() {
        super.ГлавнаяДатаИВремяОперацийСБазойДанных();
        String ФиналДата = null;
        try {
            Date     date = dateFormat.parse(ВходящаяДатаМатериалы);
// TODO: 10.11.2022
            dateFormat =new SimpleDateFormat("dd-MMM-yyyy", new Locale("ru"));//dd-MM-yyyy//// EEEE yyyy HH:mm  /////  dd MMMM yyyy HH:mm
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
           ФиналДата = dateFormat.format(date);
        Log.d(this.getClass().getName(), " ДАТА ВходящаяДатаМатериалы ВходящаяДатаМатериалы"+ВходящаяДатаМатериалы+
                " ФиналДата "+ФиналДата);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ФиналДата;
    }

}
