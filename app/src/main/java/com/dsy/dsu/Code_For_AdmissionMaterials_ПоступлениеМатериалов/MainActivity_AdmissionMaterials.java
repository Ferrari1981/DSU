package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.R;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

public class MainActivity_AdmissionMaterials extends AppCompatActivity {
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_ДляПолучениеМатериалов;
    private LinearLayout activity_admissionmaterias_face,activity_admissionmaterias_down;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binderAsyns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_admission_materials);
        activity=this;
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().hide(); ///скрывать тул бар
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            fragmentManager = getSupportFragmentManager();
           activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_face);
         activity_admissionmaterias_down = (LinearLayout) findViewById(R.id.activity_admissionmaterias_down);
            activity_admissionmaterias_down.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
// Changes the height and width to the specified *pixels*
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);
                Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            if (data!=null) {
                binderAsyns=  (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) data.getBinder("binderAsyns");
            }
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials  binder  "+binder);
            // TODO: 27.09.2022  запускаем фрагмент получение материалов
            МетодЗапускФрагментаПриемМатериалов();
            // TODO: 04.11.2022 test
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).
                МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(),
                        Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    protected void МетодЗапускФрагментаПриемМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_ДляПолучениеМатериалов = new FragmentAdmissionMaterials();
            Bundle data=new Bundle();
            data.putBinder("binder",binder);
            data.putBinder("binderAsyns",binderAsyns);
            fragment_ДляПолучениеМатериалов.setArguments(data);
            fragmentTransaction.add(R.id.activity_admissionmaterias_face, fragment_ДляПолучениеМатериалов);//.layout.activity_for_fragemtb_history_tasks
            fragmentTransaction.commit();
            fragmentTransaction.show(fragment_ДляПолучениеМатериалов);
            Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов " + fragment_ДляПолучениеМатериалов);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    // TODO: 02.08.2022
    void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
        try{
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака",ПубличныйIDДляФрагмента);
            Intent intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle = new Intent(getApplicationContext(), Service_ДляЗапускаодноразовойСинхронизации.class);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(), " ПРОШЕЛ ЗАПУСК  метода МетодПовторногоЗапускаВсехWorkManager__ОДНОРАЗОВОЙСинхрониазцииданных()   " +
                    "   ПубличныйIDДляФрагмента "+
                    ПубличныйIDДляФрагмента);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            binderAsyns.getService().МетодЗапускаОдноразовойСинхронизацииИзСлужбы(getApplicationContext(),intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle);
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
    }
}