package com.dsy.dsu.Code_For_Commit_Payments_КодДля_Согласование;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Get_Json_1C;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.dsy.dsu.Code_Gson_Processing.SubClass_JSON_B_P_GET_1C;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Fragment1_List_CommitPay extends Fragment {

    // TODO: 10.03.2022  согласования фрагмент
    private SubClassФрагмент1Согласование BisnesLogica1Согласование;
    private SubClassФрагмент1Согласование.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassФрагмент1Согласование.MyViewHolder myViewHolder;
    private JSONArray ARRAYJSONПолучениеДанныхОт1с=new JSONArray();
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private TextView textViewТекущаяЗадача;
    private Integer ПубличныйIDДляФрагмента;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationViewДляCommitPay;
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеНазад;
    private BottomNavigationItemView bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен;
    private JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView;
    private View viewДляПервойКнопкиСогласованиея = null;
    private RecyclerView recyclerViewСогласование1С;
    private ProgressBar progressBarCommitPay;
    private  Handler handlerСогаласование;
    private  Boolean  РезультатИзмененияСтатусаСогласованияОтказаИлиУспешноеСогласования;
    private   StringBuffer БуферПолученияДанныхОт1СДляСогласования=new StringBuffer();
    private Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;
    private Boolean ФлагПрошлаХотябыОднаПопыткаПолучитьДанные=false;
   private  Animation animationДляСогласования;
   private AsyncTaskLoader <Object> asyncTaskLoader;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        Bundle data=      getArguments();
        if (data!=null) {
            binderСогласования1C =  (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) data.getBinder("binderСогласования1C");
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///
    }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view);
            recyclerViewСогласование1С = viewДляПервойКнопкиСогласованиея.findViewById(R.id.recycleviewcommitpay);
            textViewТекущаяЗадача = view.findViewById(R.id.TextView);
            Log.d(this.getClass().getName(), "  Fragment1_One_Tasks  textViewТекущаяЗадача " + textViewТекущаяЗадача + " view " + view);
            textViewТекущаяЗадача.setText("Список документов".toUpperCase());
            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
            linearLayou = getActivity().findViewById(R.id.activity_main_fisrt_for_commitpays);
            bottomNavigationViewДляCommitPay = view.findViewById(R.id.bottomnavigationActivicommit);
            bottomNavigationViewДляCommitPay.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationКонкретноКнопкаКонтролируемыеНазад = bottomNavigationViewДляCommitPay.findViewById(R.id.id_commiback);
            bottomNavigationКонкретноКнопкаКонтролируемыеНазад.setTitle("Выйти");
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен= bottomNavigationViewДляCommitPay.findViewById(R.id.id_commitasync);
            bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен.setTitle("Обновить");
            progressBarCommitPay = view.findViewById(R.id.prograessbarcommitpaydown); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
             ///animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
            // animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_vibrator1);
             animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);//R.anim.layout_animal_commit
            //todo метод  ИНИЦИАЛИЗАЦИИ RECYCLEVIEW ДЛЯ АКТИВТИ ЗАДАЧИ МЕТОДЫ
            BisnesLogica1Согласование = new SubClassФрагмент1Согласование(getContext(), getActivity());
            BisnesLogica1Согласование.МетодИнициализацииRecycleViewДляЗадач();
            BisnesLogica1Согласование.МетодИнициализациHandlerCallBack();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
    }

    // TODO: 12.03.2022

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
// TODO: 14.03.2022
            viewДляПервойКнопкиСогласованиея = inflater.inflate(R.layout.activity_main_fragment1_for_commipay, container, false);
            // TODO: 12.03.2022
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                    "" + viewДляПервойКнопкиСогласованиея);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
        return viewДляПервойКнопкиСогласованиея;
    }


    @Override
    public void onStart() {
        super.onStart();
        try{
                ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
                БуферПолученияДанныхОт1СДляСогласования = BisnesLogica1Согласование.МетодПолучениеДанныхОт1сДляСогласования();
                Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента+ " БуферРезультатаПингаИПолучениеДанных "
                        + БуферПолученияДанныхОт1СДляСогласования+"БуферПолучениеДанныхОт1с " + ARRAYJSONПолучениеДанныхОт1с);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 12.03.2022  метод с бизнес логикой
    @Override
    public void onResume() {
        super.onResume();
        try {
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView  Fragment1_One_Tasks  onStart");
            BisnesLogica1Согласование.МетодЗаполенияRecycleViewДляЗадач();
            BisnesLogica1Согласование.МетодСозданиеНавигаторКнопок();
            BisnesLogica1Согласование.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(ARRAYJSONПолучениеДанныхОт1с);
            BisnesLogica1Согласование.МетодКпопкаВозвращениеНазадИзСогласованиии();
            BisnesLogica1Согласование.МетодКпопкаПринидительноОбмена();
            BisnesLogica1Согласование.МетодСлушательObserverДляRecycleView();
            BisnesLogica1Согласование. МетодПерегрузкаRecyceView();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        try{
            Log.d(this.getClass().getName(), " оonStop");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    // TODO: 10.03.2022 БИЗНЕС-КОД ПЕРЕНЕСЕН ИЗ АКТИВТИ
    private  class SubClassФрагмент1Согласование {
        Context context;
        Activity activity;
        public SubClassФрагмент1Согласование(Context context, Activity activity) {
            this.context = context;
            this.activity = activity;
            Log.d(this.getClass().getName(), "  public SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1(Context context, Activity activity)   " + context);
        }
        void МетодСлушательObserverДляRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                myRecycleViewAdapter.registerAdapterDataObserver(      new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(this.getClass().getName(), "onChanged ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try{
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try{
                            Log.d(this.getClass().getName(), "onItemRangeInserted ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try{
                            Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try{
                            Log.d(this.getClass().getName(), "     onItemRangeMoved ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 02.03.2022 выход

        private  void МетодКпопкаВозвращениеНазадИзСогласованиии()
                throws ExecutionException, InterruptedException {
            try {
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" );
                bottomNavigationКонкретноКнопкаКонтролируемыеНазад.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Интент_BackВозвращаемАктивти = new Intent();
                        Bundle data1C = new Bundle();
                        data1C.putBinder("binderСогласования1C", binderСогласования1C);
                        Интент_BackВозвращаемАктивти.putExtras(data1C);
                        Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_Face_App.class); // Т
                        Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" );
                        startActivity( Интент_BackВозвращаемАктивти);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.03.2022  принудительный обмен с 1с
        private  void МетодКпопкаПринидительноОбмена()
                throws ExecutionException, InterruptedException {
            // TODO: 02.03.2022
            try {
                // TODO: 02.03.2022
                Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
                // TODO: 09.03.2022
                bottomNavigationКонкретноКнопкаКонтролируемыеПринудительныйОбмен.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(this.getClass().getName(), "  принудительный обмен  МетодКпопкаВозвращениеНазадИзСогласованиии" );
                        handlerСогаласование.post(()->{
                            progressBarCommitPay.setVisibility(View.VISIBLE);
                        });

                            Message message = new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования",new StringBuffer("Операция успешна"));
                            message.setData(bundle);
                            handlerСогаласование.sendMessage(message);

                            Log.d(this.getClass().getName(), "  принудительный обмен конец БуферПолученияДанныхОт1СДляСогласования " +БуферПолученияДанныхОт1СДляСогласования);

                    }
                });
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 04.03.2022 прозвомжность Заполения RecycleView
        void МетодЗаполенияRecycleViewДляЗадач() {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  ARRAYJSONПолучениеДанныхОт1с  "
                        + ARRAYJSONПолучениеДанныхОт1с);
                myRecycleViewAdapter = new SubClassФрагмент1Согласование.MyRecycleViewAdapter(ARRAYJSONПолучениеДанныхОт1с);
                recyclerViewСогласование1С.setAdapter(myRecycleViewAdapter);
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerViewСогласование1С);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        // TODO: 04.03.2022 прозвомжность инициализации RecycleView
        void МетодИнициализацииRecycleViewДляЗадач() {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  БуферПолучениеДанныхОт1с  "
                        + ARRAYJSONПолучениеДанныхОт1с);
                Log.d(this.getClass().getName(), " БуферПолучениеДанныхОт1с " + ARRAYJSONПолучениеДанныхОт1с);
                recyclerViewСогласование1С = viewДляПервойКнопкиСогласованиея.findViewById(R.id.recycleviewcommitpay);
                recyclerViewСогласование1С.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewСогласование1С.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewСогласование1С.startAnimation(animationДляСогласования);
                // TODO: 28.02.2022
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerViewСогласование1С);;
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 05.03.2022 метод создание кнопок снизу навигатор
        void МетодСозданиеНавигаторКнопок() {
            try {
                // TODO: 05.03.2022
                bottomNavigationViewДляCommitPay.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // TODO: 09.03.2022
                        try {
                            // TODO: 09.03.2022
                            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
                            // TODO: 11.03.2022
                            // TODO: 11.03.2022
                            Log.d(this.getClass().getName(), "  bottomNavigationViewДляTasks " + bottomNavigationViewДляCommitPay + " fragment_ТекущийФрагмент " +
                                    fragment_ТекущийФрагментСогласованиеСписок);
                            // TODO: 09.03.2022 вешаем слушатель на конткеноую кнопку
                            Log.d(this.getClass().getName(), "  item.getItemId() " + item.getItemId());
                            // TODO: 09.03.2022
                            switch (item.getItemId()) {
                                // TODO: 14.03.2022
                                case R.id.id_taskHome:
                                    // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти
                                    Log.d(this.getClass().getName(), " R.id.id_taskHome отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляЗадачи  "
                                            + fragmentTransactionляЗадачи + " R.id.id_taskHome  item.getItemId() " + item.getItemId());
                                    ///
                                    // TODO: 10.03.2022
                                    item.setChecked(true);
                                    // TODO: 09.03.2022
                                       /* fragment_ТекущийФрагмент = new Fragment2_Create_CommitPay();
                                        // TODO: 11.03.2022
                                        fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_ТекущийФрагмент).commit();//.layout.activity_for_fragemtb_history_tasks
                                        // TODO: 10.03.2022
                                        fragmentTransactionляЗадачи.show(fragment_ТекущийФрагмент);
                                        // TODO: 10.03.2022
                                        Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
                                        // TODO: 10.03.2022

                                        // TODO: 15.03.2022
                                        // TODO: 14.03.2022
                                        //bottomNavigationКонкретноКнопкаСоздатьСейчас.setVisibility(View.GONE);
                                        // TODO: 09.03.2022
                                        bottomNavigationViewДляTasks.requestLayout();*/
                                    // TODO: 14.03.2022
                                    linearLayou.requestLayout();
                                    Log.d(this.getClass().getName(), " bottomNavigationViewДляTasks.getChildCount() " + bottomNavigationViewДляCommitPay.getChildCount());
                                    break;

                                // TODO: 09.03.2022////

                                default:
                                    // TODO: 09.03.2022
                                    // TODO: 09.03.2022
                                    Log.d(this.getClass().getName(), "  никакой не выбрали  item.getItemId() ");
                                    return false;
                            }
                            // TODO: 09.03.2022
                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            ///
                        }
                        return true;
                    }
                });
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 14.03.2022

        private void МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(@NonNull JSONArray БуферПолучениеДанныхОт1с)
                throws ExecutionException, InterruptedException {
            try {
                Log.d(this.getClass().getName(), "  БуферПолучениеДанныхОт1с.length() " + БуферПолучениеДанныхОт1с.length());
                    bottomNavigationViewДляCommitPay.getOrCreateBadge(R.id.id_commitasync).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationViewДляCommitPay.getOrCreateBadge(R.id.id_commitasync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationViewДляCommitPay.getOrCreateBadge(R.id.id_commitasync).setBackgroundColor(Color.RED);
                bottomNavigationViewДляCommitPay.requestLayout();
                bottomNavigationViewДляCommitPay.forceLayout();
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




















        // TODO: 15.03.2022  перенесееный код
        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
            // TODO: 28.02.2022
            private       TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
            // TODO: 13.03.2022
            private       TextView textViewКонтрагент,textViewЦФО,textViewДДС,TextViewНамелклатура;
            MaterialCardView materialCardViewCommit;
            // TODO: 29.03.2022

            // TODO: 01.03.2022
            private      MaterialButton КнопкаСогласованиеОтказ,КнопкаУспешноеСогласования;
            // TODO: 13.03.2022


            // TODO: 02.03.2022
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                try{
                // TODO: 02.03.2022
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                // TODO: 01.03.2022
                Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }

            // TODO: 14.03.2022
            private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
                try {
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                    textView1 = itemView.findViewById(R.id.text0_valuepay);
                    textView2 = itemView.findViewById(R.id.text1_valuepay);
                    textView3 = itemView.findViewById(R.id.text2_valuepay);
                    textView4 = itemView.findViewById(R.id.text3_valuepay);
                    textView5 = itemView.findViewById(R.id.text4_valuepay);
                    textView6 = itemView.findViewById(R.id.text5_valuepay);
                    textView7 = itemView.findViewById(R.id.text6_valuepay);
                    textViewКонтрагент = itemView.findViewById(R.id.text2_polamoneybudchetheader);
                    textViewЦФО = itemView.findViewById(R.id.text1_polamoneybudchetheader);
                    textViewДДС = itemView.findViewById(R.id.text3_polamoneybudchetheader);
                    TextViewНамелклатура = itemView.findViewById(R.id.text4_polamoneybudchetheader);
                    Log.d(this.getClass().getName(), " materialCardView  textView2 " + textView4+ "  materialCardViewCommit " +materialCardViewCommit);
                    materialCardViewCommit = itemView.findViewById(R.id.cardviewmatireacommitpay);
                    КнопкаСогласованиеОтказ  = itemView.findViewById(R.id.BottomFor_task_deseble_otkas_commitpay);
                    КнопкаУспешноеСогласования    = itemView.findViewById(R.id.BottomFor_task_complete_result_success_commitpay);
                    Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + materialCardViewCommit);
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                /////
            }
        } // TODO: 28.02.2022 конец  MyViewHolderДляЧата
        // TODO: 28.02.2022 конец  MyViewHolderДляЧата


        // TODO: 28.02.2022 ViewHolder


        class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
            JSONArray БуферПолучениеДанныхОт1с;
            public MyRecycleViewAdapter(@NotNull JSONArray БуферПолучениеДанныхОт1с) {
                // super();
                this.БуферПолучениеДанныхОт1с = БуферПолучениеДанныхОт1с;
                if (БуферПолучениеДанныхОт1с.length()>0 ) {
                    Log.i(this.getClass().getName(), " БуферПолучениеДанныхОт1с " + БуферПолучениеДанныхОт1с.length());
                }
            }


            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
                try {
                    if (БуферПолучениеДанныхОт1с.length()>0) {
                       // recyclerView.getRecycledViewPool().clear();
                        Object   СодержимоеИзПришедшихТаблицДляКлиента = БуферПолучениеДанныхОт1с.getString(position); // Here's
                        БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView = new JSONObject(СодержимоеИзПришедшихТаблицДляКлиента.toString());
                        Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView "
                                + БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView+
                                " ОбьектJSON " +БуферПолучениеДанныхОт1с);
                        Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView " + БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView
                                + " position " +position);
                    }
                    // TODO: 30.03.2022
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
                super.onBindViewHolder(holder, position, payloads);
            }







            @Override
            public void setHasStableIds(boolean hasStableIds) {
                super.setHasStableIds(hasStableIds);
            }

            @Override
            public void onViewRecycled(@NonNull MyViewHolder holder) {
                super.onViewRecycled(holder);
            }

            @Override
            public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
                return super.onFailedToRecycleView(holder);
            }

            @Override
            public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
                super.onViewAttachedToWindow(holder);
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

                recyclerView.removeAllViews();

                recyclerView.getRecycledViewPool().clear();
                super.onAttachedToRecyclerView(recyclerView);
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
                Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
                try {
                    // TODO: 30.03.2022
                    Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                    // TODO: 30.03.2022

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

                return super.getItemViewType(position);
            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewГлавныйВидДляRecyclleViewДляСогласования = null;
                try {
                    Log.i(this.getClass().getName(), "   БуферПолучениеДанныхОт1с" + БуферПолучениеДанныхОт1с+ " БуферПолученияДанныхОт1СДляСогласования  " +БуферПолученияДанныхОт1СДляСогласования);
                    if (asyncTaskLoader.isStarted()) {
                        viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_commitpay_cardview1empty_in_prossering, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляСогласования);

                    }else{
                        if (БуферПолучениеДанныхОт1с.length()>0) {
                            viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_commitpay_cardview1, parent, false);//todo old simple_for_takst_cardview1
                            Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляСогласования);
                        } else {
                            viewГлавныйВидДляRecyclleViewДляСогласования = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_commitpay_cardview1empty, parent, false);//todo old simple_for_takst_cardview1
                            Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewГлавныйВидДляRecyclleViewДляСогласования);
                            MaterialCardView materialCardViewКогдаНЕтДАнных=   viewГлавныйВидДляRecyclleViewДляСогласования.getRootView().findViewById(R.id.cardviewmatirealtask);
                        }
                    }
                    // TODO: 22.03.2022
                    myViewHolder = new SubClassФрагмент1Согласование.MyViewHolder(viewГлавныйВидДляRecyclleViewДляСогласования);
                    Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder);
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return myViewHolder;

            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                try {
                    Log.i(this.getClass().getName(), "   создание согласования"
                            + myViewHolder
                            + " БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView " +БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                    if (БуферПолучениеДанныхОт1с.length()>0 &&  asyncTaskLoader.isReset()) {
                        МетодБиндингаСозданиеНомерДокумента(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеСФО(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеОрганизация(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеКонтрагент(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеСумма(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеДДС(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодБиндингаСозданиеНомелклатура(holder, БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView);
                        МетодКпопкаОтказаСогласования(holder);
                        МетодКпопкаСогласованияУспешное(holder);
                        BisnesLogica1Согласование.МетодКпопкаСоЗачкомКраснымДополнительныйСтатус(ARRAYJSONПолучениеДанныхОт1с);

                    }else{
                        // TODO: 01.03.2022
                        Log.i(this.getClass().getName(), "  Нет данных для занрузки   subClassBuccessLogin_главныйКлассБизнесЛогикиФрагмент1Согласование. " +
                                " МетодРелистарцииЛокальногоБродкастераПослеСменыСтатусаСогласования(holder);  holder " +holder);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }
            }




            ///todo первый метод #1

            private void МетодБиндингаСозданиеНомерДокумента(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null && holder.textView1!=null ) {
                        String   ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("Ndoc");
                     /*   char[] chars = ПерваяСтрочкаЗначения.toCharArray();
                    //    char[] chars = "З".toCharArray();
                        // перебираем массив `char[]`, используя расширенный цикл for
                        for (char ch: chars) {
                            if(ch=='З')
                            System.out.print(ch);
                        }*/
                        Log.i(this.getClass().getName(), "  Ndoc   holder.КнопкаУспешноеСогласования " +  holder.КнопкаУспешноеСогласования.getTag()+
                                "     holder.КнопкаСогласованиеОтказ " +    holder.КнопкаСогласованиеОтказ.getTag() + " ПерваяСтрочкаЗначения " +ПерваяСтрочкаЗначения);
                        holder.textView1.setText(ПерваяСтрочкаЗначения);
                        holder.КнопкаУспешноеСогласования.setTag(ПерваяСтрочкаЗначения);
                        holder.КнопкаСогласованиеОтказ.setTag(ПерваяСтрочкаЗначения);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }

            ///todo первый метод #2

            private void МетодБиндингаСозданиеДДС(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&   holder.textView2!=null ) {
                        String ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("articleDDS");
                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  articleDDS ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView2.setText(ПерваяСтрочкаЗначения);
                        holder.textView2.requestLayout();
                        holder.textView2.forceLayout();
                        holder.textView2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView2.getHeight();
                                int width =  holder.textView2.getWidth();
                                holder.textViewДДС.setHeight( height);
                                holder.textViewДДС.requestLayout();
                                holder.textViewДДС.forceLayout();
                            }
                        });

                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }
            ///todo первый метод #3

            private void МетодБиндингаСозданиеКонтрагент(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&  holder.textView3!=null ) {
                        //TODO
                        String ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("counterparty");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView3.setText(ПерваяСтрочкаЗначения);
                        holder.textView3.requestLayout();
                        holder.textView3.forceLayout();
                        holder.textView3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView3.getHeight();
                                int width =  holder.textView3.getWidth();
                                holder.textViewКонтрагент.setHeight( height);
                                holder.textViewКонтрагент.requestLayout();
                                holder.textViewКонтрагент.forceLayout();
                            }
                        });

                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }




            ///todo первый метод #4

            private void МетодБиндингаСозданиеСФО(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    if (holder.textView4!=null && БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null ) {
                        //todo
                        String ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("CFO");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " CFO ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022

                        //TODo
                        holder.textView4.setText(ПерваяСтрочкаЗначения);
                        holder.textView4.requestLayout();
                        holder.textView4.forceLayout();
                        holder.textView4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                holder.textView4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height =  holder.textView4.getHeight();
                                int width =  holder.textView4.getWidth();
                                holder.textViewЦФО.setHeight( height);
                                holder.textViewЦФО.requestLayout();
                                holder.textViewЦФО.forceLayout();
                            }
                        });





                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }


            ///todo первый метод #7

            private void МетодБиндингаСозданиеНомелклатура(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {
                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                    String ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("nomenclature");
                    Log.i(this.getClass().getName(), "  nomenclature ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                    holder.textView5.setText(ПерваяСтрочкаЗначения);

                    holder.textView5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            holder.textView5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int height =  holder.textView5.getHeight();
                            int width =  holder.textView5.getWidth();
                            holder.TextViewНамелклатура.setHeight( height);
                            holder.TextViewНамелклатура.requestLayout();
                            holder.TextViewНамелклатура.forceLayout();
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }
            // TODO: 14.03.2022  Успешное Согласования

            private void МетодКпопкаСогласованияУспешное(@NonNull MyViewHolder holder)
                    throws ExecutionException, InterruptedException {
                try {
                    Log.d(this.getClass().getName(), "   КнопкаУспешноеСогласования    Успехх Согласования 2 " );
                    holder.КнопкаУспешноеСогласования.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(this.getClass().getName(), "  КнопкаУспешноеСогласования    НАЖАЛИ успехсогласования " );
                            v.animate().rotationX(40l);
                            handlerСогаласование.postDelayed(()->{
                            MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)     v;
                            String ПолученыйНомерДокусментаДляОтправки=      materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()   ;
                            Log.d(getContext().getClass().getName(), "ПолученыйНомерДокусментаДляОтправки " + ПолученыйНомерДокусментаДляОтправки+ " " +
                                    "   holder.КнопкаУспешноеСогласования " +  holder.КнопкаУспешноеСогласования.getTag()
                                    + " ПолученыйНомерДокусментаДляОтправки " +ПолученыйНомерДокусментаДляОтправки);
                            Intent notificationIntentДляСогласованияОтказИлиВЫполнил=new Intent();
                            notificationIntentДляСогласованияОтказИлиВЫполнил.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                            Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                            bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                            bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования", ПолученыйНомерДокусментаДляОтправки);
                            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 2);///TODO выполнил Согласования
                            notificationIntentДляСогласованияОтказИлиВЫполнил.putExtras(bundleДляПередачиВСлужбуСогласования);
                            Log.d(context.getClass().getName(), "notificationIntentДляСогласованияОтказИлиВЫполнил "
                                    + notificationIntentДляСогласованияОтказИлиВЫполнил+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования);
                            final StringBuffer[] БуферОтветПослеОперацийВставкиИлиОтказаСогласования = {new StringBuffer()};
                            // TODO: 19.07.2022
                                Completable completableОтправкаУспешногоСогласования    =  Completable.fromCallable(new Callable<Object>() {
                                            @Override
                                            public Object call() throws Exception {
                                                БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0] =
                                                        binderСогласования1C.getService().МетодЗапускаСогласованияВнутриСлужбы(notificationIntentДляСогласованияОтказИлиВЫполнил,getContext());
                                                Log.d(context.getClass().getName(), "notificationIntentДляСогласованияОтказИлиВЫполнил "
                                                        + notificationIntentДляСогласованияОтказИлиВЫполнил+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования+
                                                        " БуферОтветПослеОперацийВставкиИлиОтказаСогласования " + БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0]);
                                                return БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0];
                                            }
                                        }).subscribeOn(Schedulers.trampoline())
                                        .onErrorComplete(new Predicate<Throwable>() {
                                            @Override
                                            public boolean test(Throwable throwable) throws Throwable {
                                                Log.e(context.getClass().getName(), "     БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0];  throwable "  + throwable.getMessage());
                                                return false;
                                            }
                                        })
                                        .doOnComplete(new Action() {
                                            @Override
                                            public void run() throws Throwable {
                                                if (БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0].length()>0) {
                                                    Log.d(context.getClass().getName(), "     БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0];  " +
                                                            "заполенеи получего JSON  "  );
                                                }else{
                                                    Toast.makeText(context, "Операция согласование  не прошло !!! "    , Toast.LENGTH_SHORT).show();
                                                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                                }
                                                Log.d(this.getClass().getName(),
                                                        "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента+ " БуферРезультатаПингаИПолучениеДанных " + БуферПолученияДанныхОт1СДляСогласования);
                                            }
                                        }).doAfterTerminate(new Action() {
                                            @Override
                                            public void run() throws Throwable {
                                                Message message = new Message();
                                                Bundle bundle = new Bundle();
                                                message.setData(bundle);
                                                if (БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0].length()>0) {
                                                    recyclerViewСогласование1С.getAdapter().notifyItemRemoved( holder.getAdapterPosition());
                                                    bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования",new StringBuffer("Операция успешна"));
                                                    // TODO: 14.01.2022
                                                }else{
                                                    bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования",new StringBuffer("Нет"));
                                                }
                                                handlerСогаласование.sendMessage(message);
                                            }
                                        });
                                completableОтправкаУспешногоСогласования.subscribe();
                            // TODO: 26.12.2022  конец основгого кода
                                v.animate().rotationX(0);
                            },250);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            // TODO: 01.08.2022





//TODO вторая кнопка
            // TODO: 14.03.2022  отказа Согласования
            private void МетодКпопкаОтказаСогласования(@NonNull MyViewHolder holder)
                    throws ExecutionException, InterruptedException {
                try {
                    // TODO: 02.03.2022
                    Log.d(this.getClass().getName(), "  КнопкаСогласованиеОтказ    отказ 1  " );
                    holder. КнопкаСогласованиеОтказ.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.animate().rotationX(40l);
                            handlerСогаласование.postDelayed(()->{
                            Log.d(this.getClass().getName(), "  КнопкаСогласованиеОтказ    НАЖАЛИ НА ОТКАЗ СОГЛАСОВАНИЯ ");
                            MaterialButton materialButtonКнопкаУспешноеСогласования=  (MaterialButton)     v;
                            String ПолученыйНомерДокусментаДляОтправки=  materialButtonКнопкаУспешноеСогласования.getTag().toString().trim()    ;
                            Log.d(getContext().getClass().getName(), "ПолученыйНомерДокусментаДляОтправки " + ПолученыйНомерДокусментаДляОтправки+ " " +
                                    "   holder.КнопкаУспешноеСогласования " +  holder.КнопкаУспешноеСогласования.getTag());
                            Intent notificationIntentДляСогласованияОтказИлиВЫполнил=new Intent();
                            // TODO: 17.11.2021
                            notificationIntentДляСогласованияОтказИлиВЫполнил.setAction( "ЗапускаемСогласованиеОтказИлилУспешное");
                            Bundle bundleДляПередачиВСлужбуСогласования=new Bundle();
                            bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
                            bundleДляПередачиВСлужбуСогласования.putString("ПолученныйНомерДокументаСогласования",ПолученыйНомерДокусментаДляОтправки );
                            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 1);///TODO выполнил Согласования
                            notificationIntentДляСогласованияОтказИлиВЫполнил.putExtras(bundleДляПередачиВСлужбуСогласования);
                            Log.d(context.getClass().getName(), "notificationIntentДляСогласованияОтказИлиВЫполнил "
                                    + notificationIntentДляСогласованияОтказИлиВЫполнил+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования);
                            final StringBuffer[] БуферОтветПослеОперацийВставкиИлиОтказаСогласования = {new StringBuffer()};
                            // TODO: 19.07.2022

                                Completable completableОтказСоглаосания=     Completable.fromCallable(new Callable<StringBuffer>() {
                                            @Override
                                            public StringBuffer call() throws Exception {
                                                БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0] =
                                                        binderСогласования1C.getService().МетодЗапускаСогласованияВнутриСлужбы(notificationIntentДляСогласованияОтказИлиВЫполнил,getContext());
                                                Log.d(context.getClass().getName(), "notificationIntentДляСогласованияОтказИлиВЫполнил "
                                                        + notificationIntentДляСогласованияОтказИлиВЫполнил+ "bundleДляПередачиВСлужбуСогласования "+bundleДляПередачиВСлужбуСогласования+
                                                        " БуферОтветПослеОперацийВставкиИлиОтказаСогласования " + БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0]);
                                                return БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0];
                                            }
                                        }).subscribeOn(Schedulers.trampoline())
                                        .onErrorComplete(new Predicate<Throwable>() {
                                            @Override
                                            public boolean test(Throwable throwable) throws Throwable {
                                                return false;
                                            }
                                        })
                                        .doOnComplete(new Action() {
                                            @Override
                                            public void run() throws Throwable {
                                                Log.d(this.getClass().getName(), "     МетодПереЗаполенияArrayДанныхДляСогласования();  заполенеи получего JSON  "  );
                                                if (БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0].length()>0) {
                                                }else{
                                                    Toast.makeText(context, "Операция отказ не прошла !!! "    , Toast.LENGTH_SHORT).show();
                                                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                                }
                                                Log.d(this.getClass().getName(),
                                                        "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента+ " БуферРезультатаПингаИПолучениеДанных "
                                                                + БуферПолученияДанныхОт1СДляСогласования);
                                            }
                                        }).doAfterTerminate(new Action() {
                                            @Override
                                            public void run() throws Throwable {
                                                Message message = new Message();
                                                Bundle bundle=new Bundle();
                                                message.setData(bundle);
                                                if (БуферОтветПослеОперацийВставкиИлиОтказаСогласования[0].length()>0) {
                                                    recyclerViewСогласование1С.getAdapter().notifyItemRemoved( holder.getAdapterPosition());
                                                    bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования", new StringBuffer("Операция успешна"));
                                                }else {
                                                    bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования", new StringBuffer("Нет"));
                                                }
                                                handlerСогаласование.sendMessage(message);
                                            }
                                        });
                                completableОтказСоглаосания.subscribe();
                            // TODO: 26.12.2022
                                v.animate().rotationX(0);
                            },250);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }




            ///todo первый метод #3

            private void МетодБиндингаСозданиеОрганизация(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&   holder.textView6!=null ) {
                        //TODO
                        String ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getString("organization");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), " organization ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView6.setText(ПерваяСтрочкаЗначения);
                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

            ///todo первый метод #5








            private void МетодБиндингаСозданиеСумма(@NonNull MyViewHolder holder,@NonNull JSONObject БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView) {

                try {
                    // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1

                    if (БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView!=null &&  holder.textView7 !=null ) {
                        //TODO
                        Double ПерваяСтрочкаЗначения = БуферПолучениеДанныхОт1сДляЗаполенияВRereriviewView.getDouble("sum");
                        // TODO: 02.03.2022

                        // TODO: 02.03.2022
                        Log.i(this.getClass().getName(), "  sum ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                        // TODO: 28.02.2022
                        holder.textView7.setText(ПерваяСтрочкаЗначения.toString().replace(".",",")+"  "+ "(рублей)");
                    }

                    // TODO: 28.02.2022*/
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                }

            }

























            // TODO: 29.03.2022  слутаеть смены статуса







            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022

                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);

                return super.getItemId(position);

            }




            @Override
            public int getItemCount() {

                // TODO: 02.03.2022
                ////////
                int КоличесвоСтрок;
                if (БуферПолучениеДанныхОт1с.length()>0) {
                    //TODO
                    КоличесвоСтрок = БуферПолучениеДанныхОт1с.length();
                    //TODO
                    Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1с " + БуферПолучениеДанныхОт1с + " КоличесвоСтрок " +КоличесвоСтрок);
                } else {

                    КоличесвоСтрок=1;
                    Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1с " + БуферПолучениеДанныхОт1с + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
                }

                Log.d(this.getClass().getName(), "БуферПолучениеДанныхОт1с " + БуферПолучениеДанныхОт1с + " КоличесвоСтрок " +КоличесвоСтрок);


                // TODO: 28.02.2022
                return КоличесвоСтрок ;
            }
        }//TODO  конец два






        ////TODO пинг 1с

        JSONArray МетодПарсингаПолучениеДанныхОт1с(@NonNull StringBuffer БуферРезультатаПингаИПолучениеДанных) throws JSONException {
            //TODO
            JSONArray ФинальныйРезультатаПингаИПолучениеДанных=new JSONArray();
            LinkedBlockingDeque<SubClass_JSON_B_P_GET_1C> subClass_json_b_pGET =new LinkedBlockingDeque<SubClass_JSON_B_P_GET_1C>();
            try{
                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных " + БуферРезультатаПингаИПолучениеДанных);
                //TODO
                if (БуферРезультатаПингаИПолучениеДанных.length()>0) {
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                            .create();
                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных  " + БуферРезультатаПингаИПолучениеДанных);
                    //todo
                    if (!БуферРезультатаПингаИПолучениеДанных.toString().matches("(.*)не соответствует базе данных(.*)")) {
                        ФинальныйРезультатаПингаИПолучениеДанных=new JSONArray(БуферРезультатаПингаИПолучениеДанных.toString());
                    }
                    //TODO
                    Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных "
                            + БуферРезультатаПингаИПолучениеДанных+
                            " ФинальныйРезультатаПингаИПолучениеДанных " +ФинальныйРезультатаПингаИПолучениеДанных);
                }
                //todo generator json*/
                Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанныхДляGson "+
                        " ФинальныйРезультатаПингаИПолучениеДанных " +ФинальныйРезультатаПингаИПолучениеДанных);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return  ФинальныйРезультатаПингаИПолучениеДанных;
        }



        // TODO: 29.03.2022  метод регмстарцмии локального брод кастера доля смен задачи



        //TODO метод делает callback с ответом на экран
        private  void  МетодИнициализациHandlerCallBack(){
            try{
                Handler callback=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        Log.d(this.getClass().getName(), " " +
                                " Handler.CallBack onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView  " +
                                msg + " msg.getWhen() " + msg.what + "   myViewHolder.getLayoutPosition() " +  myViewHolder.getLayoutPosition());
                        Bundle bundle=      msg.getData();
                        StringBuffer БуферПолученияДанныхОт1СДляСогласования= (StringBuffer) bundle.getSerializable("БуферПолученияДанныхОт1СДляСогласования");
                        Log.d(this.getClass().getName(), "  БуферПолученияДанныхОт1СДляСогласования  " + БуферПолученияДанныхОт1СДляСогласования);

                        switch (БуферПолученияДанныхОт1СДляСогласования.toString().trim()){
                            case "Операция успешна":
                                onStart();
                                break;
                            case "Нет":
                                progressBarCommitPay.setVisibility(View.INVISIBLE);
                                break;
                        }
                        ФлагПрошлаХотябыОднаПопыткаПолучитьДанные=true;
                        onResume();
                        progressBarCommitPay.setVisibility(View.INVISIBLE);
                        msg.getTarget().removeMessages(msg.what);
                        return true;
                    }
                });
                        handlerСогаласование = callback;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 12.07.2022  метод получение данных от 1С для согласования
        private StringBuffer МетодПолучениеДанныхОт1сДляСогласования() {
            try {
                handlerСогаласование.post(()->{
                    progressBarCommitPay.setVisibility(View.VISIBLE);
                });
                asyncTaskLoader=new AsyncTaskLoader<Object>(getContext()) {
                    @Nullable
                    @Override
                    public Object loadInBackground() {
                        try{
                //TODO получаем данные для соглачования
                                //TODO ПЫТИАЕМСЯ ПОПОЛУЧИТЬ ДАННЫЕ С 1С
                               БуферПолученияДанныхОт1СДляСогласования =
                                        new Class_Get_Json_1C(getContext() ,"http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments")
                                                .МетодПингаИПОлучениеДанныхОт1сДляСогласования(getContext(),ПубличныйIDДляФрагмента);//ПубличныйIDДляФрагмента

                                Log.d(this.getClass().getName(), "  БуферПолученияДанныхОт1СДляСогласования  " +БуферПолученияДанныхОт1СДляСогласования);

                /*if(БуферПолученияДанныхОт1СДляСогласования.length()<3
                                      || БуферПолученияДанныхОт1СДляСогласования.toString().matches("(.*)ФИО с ID 116 не соответствует базе данных!(.*)")){
                                  БуферПолученияДанныхОт1СДляСогласования.append("[\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169274\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"Интал Авто\\\"\",\n" +
                                          "\"sum\": 15580,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169276\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"Интал Авто\\\"\",\n" +
                                          "\"sum\": 2370,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169385\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"Интал Авто\\\"\",\n" +
                                          "\"sum\": 5680,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169387\",\n" +
                                          "\"CFO\": \"Склад зап.частей Иваново\",\n" +
                                          "\"organization\": \"СОЮЗ АВТОДОР ООО\",\n" +
                                          "\"counterparty\": \"ООО \\\"НИЖБЕЛ\\\"\",\n" +
                                          "\"sum\": 3853.7,\n" +
                                          "\"articleDDS\": \"1.2.1.10. З/части на склад\",\n" +
                                          "\"nomenclature\": \"Авто запчасти\"\n" +
                                          "},\n" +
                                          "{\n" +
                                          "\"Ndoc\": \"000169115\",\n" +
                                          "\"CFO\": \"База НовоТалицы (Писцово) 2022\",\n" +
                                          "\"organization\": \"ООО \\\"ДЭП №17\\\"\",\n" +
                                          "\"counterparty\": \"ООО \\\"Ситилинк\\\"\",\n" +
                                          "\"sum\": 4298,\n" +
                                          "\"articleDDS\": \"2.2.04. Оргтехника\",\n" +
                                          "\"nomenclature\": \"Оргтехника\"\n" +
                                          "}\n" +
                                          "]");*/

                                  Log.d(this.getClass().getName(), "БуферРезультатаПингаИПолучениеДанных " + БуферПолученияДанныхОт1СДляСогласования);
                            /*  }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return БуферПолученияДанныхОт1СДляСогласования;
                    }
                };
                asyncTaskLoader.startLoading();
                asyncTaskLoader.forceLoad();
                asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Object>() {
                    @Override
                    public void onLoadComplete(@NonNull Loader<Object> loader, @Nullable Object data) {
                        try{
                            asyncTaskLoader.reset();
                        if(data!=null){
                            ARRAYJSONПолучениеДанныхОт1с=new JSONArray();
                            if (БуферПолученияДанныхОт1СДляСогласования.length()>0) {
                                ARRAYJSONПолучениеДанныхОт1с = BisnesLogica1Согласование.
                                        МетодПарсингаПолучениеДанныхОт1с(БуферПолученияДанныхОт1СДляСогласования);//ПубличныйIDДляФрагмента
                            }
                            Log.d(this.getClass().getName(), "  ARRAYJSONПолучениеДанныхОт1с  " +ARRAYJSONПолучениеДанныхОт1с+
                                    " БуферПолученияДанныхОт1СДляСогласования " +БуферПолученияДанныхОт1СДляСогласования);

                            //  onResume();
                            Message message = new Message();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("БуферПолученияДанныхОт1СДляСогласования",new StringBuffer("ПолучаемДаныее"));
                            message.setData(bundle);
                            handlerСогаласование.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  БуферПолученияДанныхОт1СДляСогласования;
        }
        private void МетодПерегрузкаRecyceView() {
            try {
                bottomNavigationViewДляCommitPay.requestLayout();
                bottomNavigationViewДляCommitPay.forceLayout();
                recyclerViewСогласование1С.requestLayout();
                recyclerViewСогласование1С.forceLayout();
                linearLayou.requestLayout();
                linearLayou.forceLayout();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }

}






























