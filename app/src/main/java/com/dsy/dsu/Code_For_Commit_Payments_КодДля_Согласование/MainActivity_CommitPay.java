package com.dsy.dsu.Code_For_Commit_Payments_КодДля_Согласование;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Tasks_КодДля_Задания.Fragment1_One_Tasks;
import com.dsy.dsu.R;
public class MainActivity_CommitPay extends FragmentActivity {
    private Activity activity;
    private FragmentManager fragmentManagerДляСогласование;
    private FragmentTransaction fragmentTransactionляСогласование;
    private Fragment fragment_дляСогласованиеПерваяКнопка;
    private Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            /*   setContentView(R.layout.activity_main_fragment1_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks*/
            setContentView(R.layout.activity_main_fisrt_for_commitpay);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks
            activity = this;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binderСогласования1C=  (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) data.getBinder("binderСогласования1C");
            }

            // TODO: 03.11.2022
         new SubClass_Only_ActivyMain_Commit_Buccess_Logic(getApplicationContext(),activity).МетодЗапускФрагментаСогласованиеСАктивти();
            Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляСогласование);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    private class SubClass_Only_ActivyMain_Commit_Buccess_Logic {
        //TODO
        public SubClass_Only_ActivyMain_Commit_Buccess_Logic(Context context, Activity activity) {
            // TODO: 10.03.2022
        }
    protected void МетодЗапускФрагментаСогласованиеСАктивти() {
        try{
         fragmentManagerДляСогласование = getSupportFragmentManager();
            fragmentTransactionляСогласование = fragmentManagerДляСогласование.beginTransaction();
            fragment_дляСогласованиеПерваяКнопка = new Fragment1_List_CommitPay();
            Bundle data=new Bundle();
            data.putBinder("binderСогласования1C",binderСогласования1C);
            fragment_дляСогласованиеПерваяКнопка.setArguments(data);
            fragmentTransactionляСогласование.replace(R.id.activity_main_fisrt_for_commitpays, fragment_дляСогласованиеПерваяКнопка).commit();//.layout.activity_for_fragemtb_history_tasks
            fragmentTransactionляСогласование.show(fragment_дляСогласованиеПерваяКнопка);
            Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляСогласование);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    }


}

