package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class FragmentCreateAdmissionmaterial extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСозданиеМатерила;
    private Handler handler;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentПолученыеМатериалов;
    private TextView textViewНазваниеФрагмента;
    private LinkedHashMap<String, Object> cursorConcurrentSkipListMap=new LinkedHashMap<>();
    private Animation animation;
    private Animation animationscroll;
    private  Integer ПубличныйIDДляФрагмента;
    private Cursor CursorДляОдногоМатериалаБышВесов;
    private Cursor CursorДляАвтомобиля;
    private Cursor CursorДляКонтрагента;
    private    Cursor CursorДляГруппаМатериалов;
    private   Cursor CursorДляЦФО;
    private  Object ВытаскиваемIDМатериаловИзСправочника;
    private  View view=null;
    private      AsyncTaskLoader<Object> asyncTaskLoader;
    private Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binderAsyns;
    private SharedPreferences preferencesМатериалы;
    private Boolean ФлагЧтоУжепервыйПрогоУжеПрошул=false;
    private  ScrollView scrollViewНовыйматериал;
    // TODO: 15.12.2022 получение материалов
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
      Bundle data=      getArguments();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
                binderAsyns=  (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) data.getBinder("binderAsyns");
            }
            preferencesМатериалы = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binder);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            view= inflater.inflate(R.layout.fragment_admission_materials, container, false);
            Log.d(this.getClass().getName(), "  onCreateView  view   "+view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            recyclerView = view.findViewById(R.id.RecyclerView);
            textViewНазваниеФрагмента = view.findViewById(R.id.TextView);
            textViewНазваниеФрагмента.setText("Новый Материал".toUpperCase());
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setShifting(false);
            bottomNavigationItemViewвыход.setIconSize(70);
            bottomNavigationItemViewвыход.setItemPosition(1);
            bottomNavigationItemViewвыход.setTitle("11");
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            progressBarСозданиеМатерила =  view.findViewById(R.id.ProgressBar);
            scrollViewНовыйматериал=  (ScrollView) view.findViewById(R.id.scrollview_new_materials);
            progressBarСозданиеМатерила.setVisibility(View.VISIBLE);
            cursorConcurrentSkipListMap.putIfAbsent("Создание Нового Материала",new Object());
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            animationscroll = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);
            // TODO: 19.10.2022 методы для фрагмета создание нового материалоа
            МетодHandlerCallBack();
            МетодИнициализацииRecycreView();
            МетоКликаПоКнопкеBack();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
             asyncTaskLoader=new AsyncTaskLoader<Object>(getContext()) {
                @Nullable
                @Override
                public Object loadInBackground() {
                    Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                    // TODO: 20.10.2022 #1
                    МетодПолучениеДанныхДляЦФО(intentДляПолучениеСправочкинов);
                    // TODO: 20.10.2022 #3
                    МетодПолучениеДляГруппыМатериалов(intentДляПолучениеСправочкинов);
                    // TODO: 20.10.2022 #4
                    МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов, 0);
                    // TODO: 20.10.2022 #5 автомобили
                    МетоПолучениеДанныхДляАвтомобилей(intentДляПолучениеСправочкинов, "");
                    // TODO: 20.10.2022 #6 контргаенты
                    МетоПолучениеДанныхДляКонтрагент(intentДляПолучениеСправочкинов, "");
                    // TODO: 03.11.2022  ПОСЛЕ ПОЛУЧЕННЫХ ДАННЫХ
                    Log.d(getContext().getClass().getName(), "\n" + " CursorДляЦФО "
                            + CursorДляЦФО + " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов +
                            " CursorДляАвтомобиля " + CursorДляАвтомобиля + " CursorДляКонтрагента " +CursorДляКонтрагента  + " CursorДляГруппаМатериалов " +CursorДляГруппаМатериалов );
                    return new Object();
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener<Object>() {
                @Override
                public void onLoadComplete(@NonNull Loader<Object> loader, @Nullable Object data) {
                    // TODO: 03.11.2022  запускаем после получение данных
                    asyncTaskLoader.reset();
                        onResume();
                        МетодДизайнПрограссБара();
                }
            });

            // TODO: 19.10.2022  слушатель после получение даннных в Курсом
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            МетодКпопкиЗначков();
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            МетодПерегрузкаRecyceView();
            Log.d(getContext().getClass().getName(), "\n" + " onResume ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void МетодПолучениеДанныхДляЦФО(Intent intentДляПолучениеСправочкинов) {
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
        CursorДляЦФО=     МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("cfo",intent ,"ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляЦФО " + CursorДляЦФО);
    }

    private void МетодПолучениеДляГруппыМатериалов(Intent intentДляПолучениеСправочкинов) {
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
        CursorДляГруппаМатериалов=      МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("type_materials",intent, "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляГруппаМатериалов " + CursorДляГруппаМатериалов);
    }

    private void МетоПолучениеДанныхДляОдногоМатериала(Intent intentДляПолучениеСправочкинов, @NonNull Integer ФильтрВесовых) {
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","материал");
        bundle.putString("ФильтрКолонок","nomen_vesov");
        bundle.putInt("ФильтрДляПоискаДляОдногоМатериалаВесовые",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляОдногоМатериалаБышВесов=          МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("nomen_vesov",intentДляПолучениеСправочкинов,
                "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов);
    }

    // TODO: 26.12.2022  автомобили 
    private void МетоПолучениеДанныхДляАвтомобилей(@NonNull Intent intentДляПолучениеСправочкинов,
                                                   @NonNull String ФильтрВесовых) {
        Bundle bundle=new Bundle();
        bundle.getString("ФильтрДляПоискаАвтомобили",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляАвтомобиля= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("track",intentДляПолучениеСправочкинов,
                "ПолучениеАвтомобильДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " CursorДляАвтомобиля " + CursorДляАвтомобиля);
    }
    // TODO: 26.12.2022  Контрагент
    private void МетоПолучениеДанныхДляКонтрагент(Intent intentДляПолучениеСправочкинов,
                                                  @NonNull String ФильтрВесовых) {
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","контрагенты");
        bundle.putString("ФильтрКолонок","company");
        bundle.getString("ФильтрДляПоискаКонтрагенты",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляКонтрагента= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("company",intentДляПолучениеСправочкинов,
                "ПолучениеКонтрагентовДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " \n" + "CursorДляКонтрагента " + CursorДляКонтрагента);
    }


    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетоКликаПоКнопкеBack(){
        try{
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        handler.postDelayed(()->{
                            МетодПереходаНаПредыдущыйФрагментBack();
                            Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов " + fragmentПолученыеМатериалов);
                        },500);
                        Log.d(this.getClass().getName(), "  v  " + v);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }}
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодПереходаНаПредыдущыйФрагментBack() {
        fragmentTransaction = fragmentManager.beginTransaction();
      //  fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
        Bundle data=new Bundle();
        data.putBinder("binder",binder);
        data.putBinder("binderAsyns",binderAsyns);
        fragmentПолученыеМатериалов.setArguments(data);
        fragmentTransaction.replace(R.id.activity_admissionmaterias_face, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
        fragmentTransaction.show(fragmentПолученыеМатериалов);
    }



    @Override
    public void onStop() {
        super.onStop();
        try{
      /*      if (serviceConnection!=null) {
                getContext().unbindService(serviceConnection);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
    private void МетодИнициализацииRecycreView() {
        try{
            Log.d(this.getClass().getName(), " recyclerView  "+recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorConcurrentSkipListMap);
            recyclerView.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодДизайнПрограссБара() {
        progressBarСозданиеМатерила.postDelayed(()->{
            progressBarСозданиеМатерила.setVisibility(View.INVISIBLE);
            progressBarСозданиеМатерила.setIndeterminate(true);
        },100);
    }

    // TODO: 02.08.2022
    protected   Cursor МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала(@NonNull String  ФлагКакаяТаблицаОбработки, @NonNull Intent intent,@NonNull String ФлагКакаяРаботаНужнаДляВыполнения){
        Cursor cursor = null;
        try{
            ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=intent.getExtras();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакаяРаботаНужнаДляВыполнения);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    // TODO: 15.10.2022
                    Log.d(this.getClass().getName(), "   cursor " + cursor);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }


    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView textViewcfo,marerialtextgroupmaterial,materialtext_onematerial_ves,valueavtomobil,valuekontragent;
        private TextInputEditText textipputcountassinmaterail,textipputmaretialttn,textipputmaretialttdata;
        private MaterialTextView textviewmaterialttn ,textviewmaterialttndata;
        private MaterialButton bottomcreateassionmaterial;
        private ArrayAdapter<String> АдапетерЦФО;
        private AlertDialog alertDialog;
        private  final ListView[] listViewДляЦФО = new ListView[1];
        private  Cursor cursorДляВсехМатериалов;

        // TODO: 28.10.2022
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииНовогоМатериалаCardView(itemView);
                Log.d(this.getClass().getName(), "   itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void МетодИнициализацииНовогоМатериалаCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                textViewcfo=itemView.findViewById(R.id.textViewcfo);
                marerialtextgroupmaterial=itemView.findViewById(R.id.marerialtextgroupmaterial);
                materialtext_onematerial_ves =itemView.findViewById(R.id.materialtext_onematerial_ves);
                textipputcountassinmaterail = itemView.findViewById(R.id.textipputcountassinmaterail);
                valueavtomobil = itemView.findViewById(R.id.valueavtomobil);
                valuekontragent = itemView.findViewById(R.id.valuekontragent);
                bottomcreateassionmaterial = itemView.findViewById(R.id.bottomcreateassionmaterial);
                // TODO: 07.12.2022 новые
                textipputmaretialttn = itemView.findViewById(R.id.textipputmaretialttn);
                textipputmaretialttdata  = itemView.findViewById(R.id.textipputmaretialttdata);
                textviewmaterialttn  = itemView.findViewById(R.id.textviewmaterialttn);
                textviewmaterialttndata = itemView.findViewById(R.id.textviewmaterialttndata);
                Boolean     ФлагДляСкрытыхМатериалов = preferencesМатериалы.getBoolean("ФлагДляСкрытыхМатериалов",false);
                LinearLayout.LayoutParams params= null;
                if (textipputcountassinmaterail!=null) {
                    params = (LinearLayout.LayoutParams)textipputcountassinmaterail.getLayoutParams();
                }
                if (ФлагДляСкрытыхМатериалов==true) {
                    if (textipputmaretialttn!=null) {
                        textipputmaretialttn.setVisibility(View.VISIBLE);
                        textipputmaretialttdata .setVisibility(View.VISIBLE);
                        textviewmaterialttndata.setVisibility(View.VISIBLE);
                        textviewmaterialttn.setVisibility(View.VISIBLE);
                        if (params!=null) {
                            params.setMargins(5,0,5,20);
                        }
                        textipputcountassinmaterail.setLayoutParams(params);
                    }
                } else {
                    if (textipputmaretialttn!=null) {
                        textipputmaretialttn.setVisibility(View.GONE);
                        textipputmaretialttdata .setVisibility(View.GONE);
                        textviewmaterialttndata.setVisibility(View.GONE);
                        textviewmaterialttn.setVisibility(View.GONE);
                        if (params!=null) {
                            params.setMargins(5,0,5,250);
                        }
                        textipputcountassinmaterail.setLayoutParams(params);
                    }
                }
                Log.d(this.getClass().getName(), " cardViewМатериал   " + bottomcreateassionmaterial);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }
    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LinkedHashMap<String, Object> cursorConcurrentSkipListMap;
        public MyRecycleViewAdapter(@NotNull LinkedHashMap<String, Object> cursorConcurrentSkipListMap) {
            this.cursorConcurrentSkipListMap = cursorConcurrentSkipListMap;
            if ( cursorConcurrentSkipListMap!=null) {
                Log.i(this.getClass().getName(), " cursor  " + cursorConcurrentSkipListMap.size());

            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                //  ХэшДааныеСтрока = (ConcurrentSkipListMap<String, String>) ArrayListДанныеОтСканироваиниеДивайсов.get(position);
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " holder  "+holder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
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
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewПолучениеМатериалов = null;
            try {
                if(asyncTaskLoader.isStarted()){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binder " +binder);
                }else {
                    if(CursorДляЦФО !=null){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_new_assitionmaterial_cardview_new2, parent, false);//todo simple_for_new_assitionmaterial_cardview1_test
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binder " +binder);
                } else {
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisl_sprachnikov, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  CursorДляЦФО " + CursorДляЦФО);
                }
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder + "  binder " +binder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binder " + binder);
                if (!asyncTaskLoader.isStarted()) {
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder);
                    МетодАнимации(holder);
                }
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binder " + binder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодАнимации(MyViewHolder holder) {
            try {
                  holder.bottomcreateassionmaterial.startAnimation(animationscroll);
                holder.textViewcfo.startAnimation(animation);
                holder.marerialtextgroupmaterial.startAnimation(animation);
                holder.materialtext_onematerial_ves.startAnimation(animation);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                if (!asyncTaskLoader.isStarted()) {
                    МетодДанныеЦФО(holder);
                    МетодДанныеГруппаМатериалов(holder);
                    МетодДанныеОдинМатериалВесовые(holder);
                    МетоднажатиеСозданиеМатериалов(holder);
                    // TODO: 19.12.2022  ДВА НОВЫХ МЕТОДА ттн И ДАТА ТТН
                    МетодТТН(holder);
                    МетодДатаТТН(holder);
                    // TODO: 23.12.2022  новые два поля Автомобиль и Котрагент
                    МетодДанныеАвтомобили(holder);
                    МетодДанныеКонтагент(holder);
                    МетодДанныеНастйрокаВводаКоличества(holder);

                    // TODO: 16.12.2022  указываем флаг что мы один раз прошли по строчкем
                    ФлагЧтоУжепервыйПрогоУжеПрошул=true;
                    Log.i(this.getClass().getName(), "    holder. ФдагЧтоУжеОдинРАзБылПервыйПроход "+  ФлагЧтоУжепервыйПрогоУжеПрошул+
                             " asyncTaskLoader.isStarted() " +asyncTaskLoader.isStarted());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и даты ТТН
        private void МетодДатаТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String УжеВыбраннаяТТН = preferencesМатериалы.getString("ПозицияВыбраногоТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                           holder.      textipputmaretialttdata.setText(УжеВыбраннаяТТН);
                }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и
        private void МетодТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String ВыбранаяДатаТТГУже = preferencesМатериалы.getString("НазваниеВыбраногоДатаТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                    holder. textipputmaretialttn.setText(ВыбранаяДатаТТГУже);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 19.10.2022 заполеения ЦФО ФРАГМЕНТ ПОЛУЧЕНИЕ МАТЕРИАЛОВ
        private void МетодДанныеЦФО(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул ==false){
                    Integer ПозицияВыбраногоЦФО=            preferencesМатериалы.getInt("ПозицияВыбраногоЦФО",0);
                    String НазваниеВыбраногоЦФО=            preferencesМатериалы.getString("НазваниеВыбраногоЦФО","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоЦФО);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоЦФО);
                    holder.textViewcfo.setTag(bundle);
                    holder.textViewcfo.setText(НазваниеВыбраногоЦФО);
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ ЦФО
                class ЦфоНовыйФильтДляЦФО extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляЦФО);
                    }
                }
                new ЦфоНовыйФильтДляЦФО().МетодЗапускаНовогоФильтра(holder.textViewcfo,holder,
                        "ЦФО","cfo","name");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодПоискаФильтр(@NonNull   SearchView searchViewДляНовогоЦФО,
                                       @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                       @NonNull MyViewHolder holder,
                                       @NonNull View v,
                                       @NonNull String ТаблицаДляФильтра,
                                       @NonNull MaterialTextView materialTextView) {
            try{

                searchViewДляНовогоЦФО.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                        Log.d(this.getClass().getName()," position");
                            if (hasFocus==true) {
                                // searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
                                if(materialTextView.getId()==holder.textViewcfo.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск цфо");
                                }
                                if(materialTextView.getId()==holder.marerialtextgroupmaterial.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск гр. материалов");
                                }
                                if(materialTextView.getId()==holder.materialtext_onematerial_ves.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск материал");
                                }
                            }
                        } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });



                searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                        Log.d(this.getClass().getName()," position");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                        Log.d(this.getClass().getName()," position");
                        Filter filter= simpleCursorAdapterЦФО.getFilter();
                        filter.filter(newText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return true;
                    }
                });
                simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        Log.d(this.getClass().getName()," position");
                        try{
                            if(materialTextView.getId()==holder.materialtext_onematerial_ves.getId()) {
                                Bundle bundleДляПосика = (Bundle) holder.marerialtextgroupmaterial.getTag();
                                Integer ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска = bundleДляПосика.getInt("ПолучаемIDЦфо");
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска);
                        }else if (materialTextView.getId()==holder. valueavtomobil.getId()){
                                Log.d(this.getClass().getName(), "   holder. valueavtomobil.getId() " + holder. valueavtomobil.getId());
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);

                        }else if (materialTextView.getId()==holder. valuekontragent.getId()){
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);
                            }else{
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра,constraint.toString(),0);
                            }
                            handler.post(()->{
                                simpleCursorAdapterЦФО.swapCursor(holder.cursorДляВсехМатериалов);
                                simpleCursorAdapterЦФО.notifyDataSetChanged();
                             holder.   listViewДляЦФО[0].setSelection(0);
                                if (holder.cursorДляВсехМатериалов.getCount()==0) {
                                    searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                    handler.postDelayed(() -> {
                                        searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                    }, 250);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return holder.cursorДляВсехМатериалов;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binder!=null) {
                    cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанныеИзПосика(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binder!=null) {
                    cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }

        // TODO: 02.08.2022
        protected   Cursor МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр,Integer ЗначениеГруппыМатериаловДляПосика){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                bundleДляПЕредачи.putInt("ЗначениеГруппыМатериаловДляПосика",ЗначениеГруппыМатериаловДляПосика);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binder!=null) {
                    cursor = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }


        // TODO: 19.10.2022 ГРУППА МАТЕРИАЛОВ
        private void МетодДанныеГруппаМатериалов(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоМатериалов=            preferencesМатериалы.getInt("ПозицияВыбраногоГруппыМатериалов",0);
                    String НазваниеВыбраногоМатериалов=            preferencesМатериалы.getString("НазваниеВыбраногоГруппыМатериалов","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоМатериалов);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоМатериалов);
                    holder.marerialtextgroupmaterial.setTag(bundle);
                    holder.marerialtextgroupmaterial.setText(НазваниеВыбраногоМатериалов);
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ группы МАТЕРИАЛОВ
                class ДляНовгоФимльтраГруппыМатериалов extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляГруппаМатериалов);
                    }
                }
                new ДляНовгоФимльтраГруппыМатериалов().МетодЗапускаНовогоФильтра(holder.marerialtextgroupmaterial, holder,"Группа материалов",
                        "type_materials","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ ОДИН МАТЕРИАЛ ПОСЛЕ ВСЕХ ВЕСОВЫЕ
        private void МетодДанныеОдинМатериалВесовые(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Одного МАТЕРАИАЛ ОДДИН БЫШИЙ ВЕСОВОЙ
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоГруппыМатериалов=            preferencesМатериалы.getInt("ПозицияВыбраногоМатериала",0);
                    String НазваниеВыбраногоГруппыМатериалов=            preferencesМатериалы.getString("НазваниеВыбраногоМатериала","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоГруппыМатериалов);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоГруппыМатериалов);
                    holder.materialtext_onematerial_ves.setTag(bundle);
                    holder.materialtext_onematerial_ves.setText(НазваниеВыбраногоГруппыМатериалов);
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ ЦФО
                class ОдинМатериаДляФильтраВышшаяВесовая extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляОдногоМатериалаБышВесов);
                    }
                }
                new ОдинМатериаДляФильтраВышшаяВесовая().МетодЗапускаНовогоФильтра(holder.materialtext_onematerial_ves,holder,"материал",
                        "nomen_vesov","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Автомобиля
        private void МетодДанныеАвтомобили(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомобили
                Boolean ФлагВыбиралУжеАвтомобилиИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеАвтомобилиИзСпинера==true && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоАвтомобили=            preferencesМатериалы.getInt("ПозицияВыбраногоАвтомобили",0);
                    String НазваниеВыбраногоАтомобиля=            preferencesМатериалы.getString("НазваниеВыбраногоАвтомобили","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоАвтомобили);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоАтомобиля);
                    holder.valueavtomobil.setTag(bundle);
                    holder.valueavtomobil.setText(НазваниеВыбраногоАтомобиля);
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомомиби
                class НовыйФильДляАвтомобили extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляАвтомобиля);
                    }
                }
                new НовыйФильДляАвтомобили().МетодЗапускаНовогоФильтра(holder.valueavtomobil,holder,"автомобиль","track","fullname");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Контагент
        private void МетодДанныеКонтагент(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Контрагенты
                Boolean ФлагВыбиралУжеКонтагенты=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеКонтагенты==true && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоКонтрагент=            preferencesМатериалы.getInt("ПозицияВыбраногоКонтрагент",0);
                    String НазваниеВыбраногоКонтрагент=            preferencesМатериалы.getString("НазваниеВыбраногоКонтрагент","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоКонтрагент);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоКонтрагент);
                    holder.valuekontragent.setTag(bundle);
                    holder.valuekontragent.setText(НазваниеВыбраногоКонтрагент);
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомомиби
                class НовыйФильДляКонтрагенты extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляКонтрагента);
                    }
                }
                new НовыйФильДляКонтрагенты().МетодЗапускаНовогоФильтра(holder.valuekontragent,holder,
                        "контрагент","company","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Контагент
        private void МетодДанныеНастйрокаВводаКоличества(@NonNull MyViewHolder holder) {
            try {

                // TODO: 23.01.2023
                KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        // TODO: 23.01.2023
                   if(isOpen==true){

                       scrollViewНовыйматериал.scrollBy(0, +500);
                    scrollViewНовыйматериал.fullScroll(View.FOCUS_DOWN);
                       scrollViewНовыйматериал.smoothScrollTo(0, 500);
                    scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom()-1);

                   }else{
                       scrollViewНовыйматериал.scrollBy(0, 0);
                       scrollViewНовыйматериал.fullScroll(View.FOCUS_UP);
                       scrollViewНовыйматериал.smoothScrollTo(0, 0);
                       scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom());
                   }
                        scrollViewНовыйматериал.refreshDrawableState();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 20.10.2022 нажатие на кнопку
        private  void МетоднажатиеСозданиеМатериалов(@NonNull MyViewHolder holder){
            try{
                holder. bottomcreateassionmaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(this.getClass().getName(), " МетоднажатиеСозданиеМатериалов  ");
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        Log.d(this.getClass().getName(), " binder " + binder);
                        handler.postDelayed(()->{
                            Intent intentСамоПолучениеНовогоМатериала=new Intent("СамоСозданиеНовогоМатериала");
                            // TODO: 21.10.2022  перед созданием нового материала получанеим Данные которые нужно вставить
                            Integer IDДляВставкиЦФО= 0;
                            if (   holder.textViewcfo.getText().length()>0) {
                                Bundle bundle=(Bundle)   holder.textViewcfo.getTag();
                                IDДляВставкиЦФО=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиГруппыматериалов= 0;
                            if (   holder.marerialtextgroupmaterial.length()>0) {
                                Bundle bundle=(Bundle)   holder.marerialtextgroupmaterial.getTag();
                                IDДляВставкиГруппыматериалов=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиОдногоматериала= 0;
                            if (    holder.materialtext_onematerial_ves.length()>0) {
                                Bundle bundle=(Bundle)   holder.materialtext_onematerial_ves.getTag();
                                IDДляВставкиОдногоматериала=      bundle.getInt("ПолучаемIDЦфо",0);
                            }

                            Float ВыбраноеЗначениеКоличествоМатериала= 0f;
                            if (holder.textipputcountassinmaterail.getText().length()>0) {
                                ВыбраноеЗначениеКоличествоМатериала = Float.parseFloat(holder.textipputcountassinmaterail.getText().toString());
                            }
                            String ВыбраноеТТНМатериала=new String();
                            if (holder.textipputmaretialttn.getText().length()>0) {
                                ВыбраноеТТНМатериала = holder.textipputmaretialttn.getText().toString();
                            }
                            String ВыбраноеТТНДата=new String();
                            if (holder.textipputmaretialttdata.getText().length()>0) {
                                ВыбраноеТТНДата = holder.textipputmaretialttdata.getText().toString();
                            }
                            // TODO: 27.12.2022 ДВА НОВЫХ ПОЛЯ АВТОМОБИЛИ И ИЛИ  КОНТРАГЕНТЫ
                            Integer ВыбраноеАвтомобили=0;
                            if (holder.valueavtomobil.getText().length()>0) {
                                Bundle bundleАвтомобили=  (Bundle) holder.valueavtomobil.getTag();
                                ВыбраноеАвтомобили=  bundleАвтомобили.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеАвтомобили " +ВыбраноеАвтомобили);

                            }
                            Integer ВыбраноеКонтагенты=0;
                            if (holder.valuekontragent.getText().length()>0) {
                                Bundle bundleКонтрагенты=  (Bundle) holder.valuekontragent.getTag();
                                ВыбраноеКонтагенты=  bundleКонтрагенты.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            }
                            Log.d(this.getClass().getName(), " IDДляВставкиМатериалы " +IDДляВставкиГруппыматериалов +
                                    " IDДляВставкиВесовые "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО+
                                    " ВыбраноеЗначениеКоличествоМатериала " +ВыбраноеЗначениеКоличествоМатериала+ " ВыбраноеТТНМатериала " +ВыбраноеТТНМатериала+
                                    " ВыбраноеАвтомобили " +ВыбраноеАвтомобили + " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            // TODO: 21.10.2022 проверка выбрали ли все ТРИ СПРАВОЧНИКА ДЛЯ СОЗДАНИЯ НОВОГО МАТЕРИАЛА
                            if(IDДляВставкиГруппыматериалов>0 && IDДляВставкиОдногоматериала>0
                                    &&  IDДляВставкиЦФО>0 && ВыбраноеЗначениеКоличествоМатериала>0f && ВыбраноеКонтагенты>0 ){
                                Bundle bundleДляСоздании=new Bundle();
                                bundleДляСоздании.putInt("cfo",IDДляВставкиЦФО);
                                bundleДляСоздании.putInt("type_material",IDДляВставкиГруппыматериалов);
                                bundleДляСоздании.putInt("nomen_vesov",IDДляВставкиОдногоматериала);
                                bundleДляСоздании.putFloat("count",ВыбраноеЗначениеКоличествоМатериала);
                                // TODO: 27.12.2022 для двух новых полей автомиби и контрагент
                                bundleДляСоздании.putString("ttn",ВыбраноеТТНМатериала);
                                bundleДляСоздании.putString("datattn",ВыбраноеТТНДата);
                                // TODO: 27.12.2022  два новых поля для создание материла и контрагент
                                bundleДляСоздании.putInt("tracks",ВыбраноеАвтомобили);
                                bundleДляСоздании.putInt("companys",ВыбраноеКонтагенты);
                                intentСамоПолучениеНовогоМатериала.putExtras(bundleДляСоздании);
                                Integer ХэшРезультататСозданияСозданиеНовогоМатериала =
                                        binder.getService().МетодCлужбыСозданиеНовогоМатериала(getContext(),intentСамоПолучениеНовогоМатериала);
                                // TODO: 21.10.2022  результат  создание нового материала
                                Log.d(this.getClass().getName(), " ХэшРезультататСозданияСозданиеНовогоМатериала  "+ХэшРезультататСозданияСозданиеНовогоМатериала+
                                        " ХэшРезультататСозданияСозданиеНовогоМатериала " +ХэшРезультататСозданияСозданиеНовогоМатериала);


                                if (ХэшРезультататСозданияСозданиеНовогоМатериала>0) {
                                    МетодПереходаНаПредыдущыйФрагментBack();
                                    // TODO: 17.11.2022 запоманаем выбраное цфо
                                    SharedPreferences.Editor editor = preferencesМатериалы.edit();
                                    editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
                                    // TODO: 09.12.2022 цфо
                                    editor.putInt("ПозицияВыбраногоЦФО",IDДляВставкиЦФО);
                                    editor.putString("НазваниеВыбраногоЦФО",holder.textViewcfo.getText().toString());
                                    // TODO: 09.12.2022 группа материалов
                                    editor.putInt("ПозицияВыбраногоГруппыМатериалов",IDДляВставкиГруппыматериалов);
                                    editor.putString("НазваниеВыбраногоГруппыМатериалов",holder.marerialtextgroupmaterial.getText().toString());
                                    // TODO: 09.12.2022 сам матералов
                                    editor.putInt("ПозицияВыбраногоМатериала",IDДляВставкиОдногоматериала);
                                    editor.putString("НазваниеВыбраногоМатериала",holder.materialtext_onematerial_ves.getText().toString());
                                    // TODO: 09.12.2022 два поля ТТН и ТТН ДАТА
                                    Boolean     ФлагДляСкрытыхМатериалов = preferencesМатериалы.getBoolean("ФлагДляСкрытыхМатериалов",false);
                                    if (ФлагДляСкрытыхМатериалов==true) {
                                        editor.putString("ПозицияВыбраногоТТН",holder.textipputmaretialttn.getText().toString());
                                        editor.putString("НазваниеВыбраногоДатаТТН",holder.textipputmaretialttdata.getText().toString());
                                    }
                                    // TODO: 27.12.2022  ДВА НОВЫХ ПОЛЯ МАТЕРИАЛОВ КОНТРАГЕНТ И АВТОМОБИЛЬ
                                    // TODO: 09.12.2022 Автомобиль запоминаем 
                                    editor.putInt("ПозицияВыбраногоАвтомобили",ВыбраноеАвтомобили);
                                    editor.putString("НазваниеВыбраногоАвтомобили",holder.valueavtomobil.getText().toString());
                                    // TODO: 09.12.2022 Автомобиль Контрогент
                                    editor.putInt("ПозицияВыбраногоКонтрагент",ВыбраноеКонтагенты);
                                    editor.putString("НазваниеВыбраногоКонтрагент",holder.valuekontragent .getText().toString());
                                    // TODO: 27.12.2022 запоминаем параметры
                                    editor.commit();
                                }else {
                                    Snackbar.make(v, "Материалал не добавился !!!" +
                                            " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                                }
                            }else {
                                Log.d(this.getClass().getName(), " IDДляВставкиГруппыматериалов " +IDДляВставкиГруппыматериалов +
                                        " IDДляВставкиОдногоматериала "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО);
                                Snackbar.make(v, "Выберете все три справочника и/или количество" +
                                        " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                            }
                            Log.d(this.getClass().getName(), "  v  " + v);
                        },250);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        @Override
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            int КоличесвоСтрок = 0;
            try {
                КоличесвоСтрок = 1;
                Log.d(this.getClass().getName(), " bilder "+ binder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return КоличесвоСтрок;
        }



        // TODO: 15.12.2022 класс для всех новый фильр дл я данных
        public class SubClassNewFilterSFOНовыйФильтДанных {
            MaterialTextView materialTextView;
            Cursor cursor;
            MyViewHolder holder;
            Boolean ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=false;
            protected SubClassNewFilterSFOНовыйФильтДанных(){
                Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО ");
            }
            protected   void МетодЗапускаНовогоФильтра( @NonNull MaterialTextView materialTextView,
                                                         @NonNull MyViewHolder holder,
                                                         @NonNull String ФлагРежимНовогоФильтра,
                                                         @NonNull String ТаблицаДляФильтра,
                                                        @NonNull String КакойСтолбикЗагружатьВSimpleAdapter) {
                this.materialTextView = materialTextView;
                this.cursor = cursor;
                this.holder = holder;
                // TODO: 16.12.2022
                materialTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation   animation1= AnimationUtils.loadAnimation(v.getContext(),R.anim.slide_in_row_newscanner1);
                        final MaterialButton[] materialButtonЗакрытьДиалог = new MaterialButton[1];
                        // TODO: 20.12.2022  оперделяем какой вид загружать когда выбран и когда не выбрана гурппа метарилов
                        Integer КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview;
                        if(materialTextView.getId()== holder.materialtext_onematerial_ves.getId() ){
                            if ( holder.marerialtextgroupmaterial.getText().length()==0) {
                                КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview_kogda_net_group;
                            //   Snackbar.make(v, "Вы не выбрали группу материалов !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                                ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=true;
                            }else{
                                ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=false;
                            }
                        }
                        // TODO: 28.12.2022 cursor
                        holder.alertDialog  = new MaterialAlertDialogBuilder(v.getContext()){
                            @NonNull
                            @Override
                            public MaterialAlertDialogBuilder setView(View view) {
                                try{
                                    // TODO: 14.12.2022
                                    materialButtonЗакрытьДиалог[0] =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                                // TODO: 19.12.2022 какой КУРСОР БУДЕТ ВЫПОЛНЯТЬСЯ
                                if (ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов==false) {
                                    SearchView searchViewДляНовогоЦФО= null;
                                        МетодКоторыйОперделянтТекуийКурсор(holder, cursor);
                                    Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО " + cursor);
                                    holder.  listViewДляЦФО[0] =    (ListView) view.findViewById(R.id.SearchViewList);
                                    if (holder.  listViewДляЦФО[0]!=null) {
                                    holder.   listViewДляЦФО[0].setTextFilterEnabled(true);
                                    searchViewДляНовогоЦФО = (SearchView) view.findViewById(R.id.searchview_newscanner);
                                    // TODO: 14.12.2022
                                    searchViewДляНовогоЦФО.setDrawingCacheBackgroundColor(Color.GRAY);
                                    searchViewДляНовогоЦФО.setDrawingCacheEnabled(true);
                                    int id = searchViewДляНовогоЦФО.getContext()
                                            .getResources()
                                            .getIdentifier("android:id/search_src_text", null, null);
                                    TextView textView = (TextView) searchViewДляНовогоЦФО.findViewById(id);
                                    textView.setTextColor(Color.rgb(0,102,102));
                                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                }

                                    ///TODO ГЛАВНЫЙ АДАПТЕР чата
                                    SimpleCursorAdapter    simpleCursorAdapterЦФО= new SimpleCursorAdapter(v.getContext(),  R.layout.simple_newspinner_dwonload_newfiltersearch,// R.layout.simple_newspinner_dwonload_cfo2,
                                            holder.cursorДляВсехМатериалов,
                                            new String[]{ КакойСтолбикЗагружатьВSimpleAdapter,"_id"},
                                            new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                                    SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder(){

                                        @Override
                                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                            switch (view.getId()) {
                                                case android.R.id.text1:
                                                    Log.d(this.getClass().getName()," position");
                                                    if (cursor.getCount()>0) {
                                                        try{
                                                            Integer ИндексНазваниеЦФО = cursor.getColumnIndex(КакойСтолбикЗагружатьВSimpleAdapter);///user_update  --old/// uuid
                                                            String  НазваниеЦФО = cursor.getString(ИндексНазваниеЦФО);
                                                            // TODO: 13.12.2022  производим состыковку
                                                            Integer   ИндексНазваниеЦфоID = cursor.getColumnIndex("_id");///user_update  --old/// uuid
                                                            Integer   ПолучаемIDЦфо = cursor.getInt(ИндексНазваниеЦфоID);
                                                            if (ПолучаемIDЦфо>0) {
                                                                Integer UUIDНазваниеЦФО = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                                Long UUIDЦФО = cursor.getLong(UUIDНазваниеЦФО);
                                                                Bundle bundle=new Bundle();
                                                                bundle.putInt("ПолучаемIDЦфо",ПолучаемIDЦфо);
                                                                bundle.putString("НазваниеЦФО",НазваниеЦФО);
                                                                bundle.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
                                                                ((MaterialTextView)view).setTag(bundle);
                                                            }
                                                            Log.d(this.getClass().getName()," НазваниеЦФО"+ НазваниеЦФО+
                                                                    " ПолучаемIDЦфо "+ПолучаемIDЦфо);
                                                            // TODO: 20.01.2022
                                                            Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеЦФО);
                                                            boolean ДлинаСтрокивСпиноре = НазваниеЦФО.length() >40;
                                                            if (ДлинаСтрокивСпиноре==true) {
                                                                StringBuffer sb = new StringBuffer(НазваниеЦФО);
                                                                sb.insert(40, System.lineSeparator());
                                                                НазваниеЦФО = sb.toString();
                                                                Log.d(v.getContext().getClass().getName(), " НазваниеЦФО " + "--" + НазваниеЦФО);/////
                                                            }
                                                            ((MaterialTextView)view).setText(НазваниеЦФО);
                                                            // TODO: 13.12.2022 слушатель
                                                            ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    try{
                                                                    ((MaterialTextView)view).startAnimation(animation1);
                                                                    Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                                    Integer IDЦфоДЛяПередачи=      bundle.getInt("ПолучаемIDЦфо",0);
                                                                    String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
                                                                    Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
                                                                    materialTextView.setTag(bundle);
                                                                    materialTextView.setText(НазваниеЦФО);
                                                                    materialTextView.refreshDrawableState();
                                                                    materialTextView.forceLayout();

                                                                    if (    materialTextView.getText().toString().length()==0) {
                                                                        Snackbar.make(view, " Вы не выбрали цфо !!! "
                                                                                , Snackbar.LENGTH_LONG).show();
                                                                        materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                                        materialTextView.setTextColor(Color.GRAY);
                                                                        Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                                    } else {
                                                                        materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                                        materialTextView.setTextColor(Color.BLACK);
                                                                        holder.alertDialog.dismiss();
                                                                        holder. alertDialog.cancel();
                                                                        Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());


                                                                        // TODO: 16.12.2022  для Второго Компонета ГРУППА МАТЕРИАЛОВ, после ВЫБЫБОРА ГУРППЫ МАТЕРИАЛОВ ДАЛЕЕ ИНИЦИАЛИЗУЕМ ОДИН МАТЕРИАЛОВ
                                                                        МетодДляГруппыМатериаловЗапускатьОдинМатериаловВесовой(bundle,holder);

                                                                    }
                                                                    Log.d(this.getClass().getName()," position");
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                                            this.getClass().getName(),
                                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                }
                                                                }

                                                            });
                                                            // TODO: 13.12.2022 филь
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                                    this.getClass().getName(),
                                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        }
                                                        return true;
                                                    } else {
                                                        Log.d(this.getClass().getName()," position");
                                                        return false;
                                                    }
                                            }
                                            return false;
                                        }
                                    };
                                    simpleCursorAdapterЦФО.setViewBinder(БиндингДляЦФО);
                                    holder.listViewДляЦФО[0].setAdapter(simpleCursorAdapterЦФО);
                                    holder.listViewДляЦФО[0].startAnimation(animation);
                                    holder.listViewДляЦФО[0].setSelection(0);
                                    holder.listViewДляЦФО[0].forceLayout();
                                    simpleCursorAdapterЦФО.notifyDataSetChanged();
                                    // TODO: 13.12.2022  Поиск и его слушель
                                    МетодПоискаФильтр(searchViewДляНовогоЦФО,simpleCursorAdapterЦФО,holder,v,ТаблицаДляФильтра,materialTextView);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                                return super.setView(view);
                                // TODO: 20.12.2022  тут конец выбеленого
                            }


                        }
                                .setTitle(ФлагРежимНовогоФильтра)
                                .setCancelable(false)
                                .setIcon( R.drawable.icon_newscannertwo)
                                .setView(getLayoutInflater().inflate( КакойИмменоВидЗагружатьДляНовогоПосика, null )).show();
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.copyFrom(   holder.alertDialog.getWindow().getAttributes());
                        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.gravity = Gravity.CENTER;
                        holder.alertDialog.getWindow().setAttributes(layoutParams);
                        // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
                        МетодЗакрытиеНовогоПосика(materialButtonЗакрытьДиалог);
                    }

                    private void МетодЗакрытиеНовогоПосика(MaterialButton[] materialButtonЗакрытьДиалог) {
                        materialButtonЗакрытьДиалог[0].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    Log.d(this.getClass().getName()," position");
                                    Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                                    materialTextView.setText("");
                                    materialTextView.refreshDrawableState();
                                    materialTextView.forceLayout();
                                    holder.alertDialog.dismiss();
                                    holder.alertDialog.cancel();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }
                });
            }
            // TODO: 19.12.2022  курсор текущий операйции  какой Спинер
            protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                try{
                holder.cursorДляВсехМатериалов = (Cursor)  cursor;
                    Log.d(this.getClass().getName(),"    holder.cursorДляВсехМатериалов"+   holder.cursorДляВсехМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }

            // TODO: 19.12.2022  курсор текущий операйции  какой Спинер
            protected void МетодКоторыйОперделянтТекуийКурсорТОлькоДляОдногОматериалаБывшейВсесовой(@NonNull MyViewHolder holder) {
                try{
                    Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                    if(      holder.cursorДляВсехМатериалов.getCount()==0){
                        Bundle bundle= (Bundle) holder.marerialtextgroupmaterial.getTag();
                        Integer ПолученеиеID=bundle.getInt("ПолучаемIDЦфо");
                        МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,ПолученеиеID);
                        Log.d(this.getClass().getName(),"    holder.cursorДляВсехМатериалов"+   holder.cursorДляВсехМатериалов
                                +  "holder.marerialtextgroupmaterial.getTag() "+holder.marerialtextgroupmaterial.getTag()+ "  vbinder.getService() " +binder.getService());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            // TODO: 16.12.2022  для Второго Компонета ГРУППА МАТЕРИАЛОВ, после ВЫБЫБОРА ГУРППЫ МАТЕРИАЛОВ ДАЛЕЕ ИНИЦИАЛИЗУЕМ ОДИН МАТЕРИАЛОВ
            protected void МетодДляГруппыМатериаловЗапускатьОдинМатериаловВесовой(Bundle bundle,@NonNull MyViewHolder holder) {
                try{
                Log.d(this.getClass().getName()," materialTextView.getId() "+materialTextView.getId()+
                        " holder.marerialtextgroupmaterial.getId()   "+ holder.marerialtextgroupmaterial.getId()  );
                if(materialTextView.getId()== holder.marerialtextgroupmaterial.getId() ){
                    Log.d(this.getClass().getName()," materialTextView.getId() "+materialTextView.getId()+
                            " holder.marerialtextgroupmaterial.getId()   "+ holder.marerialtextgroupmaterial.getId()+
                            "  bundle.getInt(\"ПолучаемIDЦфо\",0) " + bundle.getInt("ПолучаемIDЦфо",0));
                    // TODO: 24.10.2022 дополнительное получение весовых через фильтр Группы материалов
                    if (bundle.getInt("ПолучаемIDЦфо",0)>0) {
                            МетодПереопределенияСпинераВесовой(bundle.getInt("ПолучаемIDЦфо",0),holder);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }
            // TODO: 16.12.2022  третий вариант для ВЫбора Материала
            protected void МетодПереопределенияСпинераВесовой(@NonNull  Integer ВытаскиваемЗначениеIDдляФильтра, @NonNull MyViewHolder holder) {
                try{
                    Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                    МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,ВытаскиваемЗначениеIDдляФильтра);
                    Log.d(this.getClass().getName(), " cursor  " + cursor);
                    // TODO: 24.10.2022 перезапускаем Метод Спинера
                    holder.materialtext_onematerial_ves.setText("");
                    holder.materialtext_onematerial_ves.forceLayout();
                 ///   МетодДанныеОдинМатериалВесовые(holder);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
        }
        // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
    }










    private void МетодКпопкиЗначков()
            throws ExecutionException, InterruptedException {
        try {
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.requestLayout();
            bottomNavigationView.forceLayout();
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            recyclerView.refreshDrawableState();
            bottomNavigationView.refreshDrawableState();
            linearLayou.refreshDrawableState();
            linearLayou.requestLayout();
            linearLayou.forceLayout();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 19.10.2022  end
}
