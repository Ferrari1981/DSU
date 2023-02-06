package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;


import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;



// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentAdmissionMaterials extends Fragment {
    private Integer ПубличныйIDДляФрагмента;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private LayoutAnimationController layoutAnimationController;
    private Animation animation;
    private  Handler handler;
    private  Cursor cursorНомерЦФО;
    private  Cursor cursorНомерМатериала;
    private  Cursor cursorСамиДанныеGroupBy;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binderAsyns;
    private Integer ТекущаяЦФО=0;
    private Integer ТекущаяНомерМатериала=0;
    private String ТекущаяИмяЦФО=new String();
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеНовогоМатериалов;
    private  TextView   textViewНазваниеФрагмента;
    private AsyncTaskLoader asyncTaskLoader;
    long start;
    long startДляОбноразвовной;

    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentAdmissionMaterials() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            Bundle data=      getArguments();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            if (data!=null) {
                binderAsyns=  (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) data.getBinder("binderAsyns");
            }
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials  binder  "+binder);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View    view=null;
        try {
            view= inflater.inflate(R.layout.fragment_admission_materials, container, false);
            this.container=container;
            Log.d(this.getClass().getName(), " onCreateView FragmentAdmissionMaterials" + view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            recyclerView = view.findViewById(R.id.RecyclerView);
            textViewНазваниеФрагмента = view.findViewById(R.id.TextView);
            textViewНазваниеФрагмента.setText("Поступление материалов".toUpperCase());
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(70);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
           // animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator1);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            //todo запуск методов в фрагменте
            МетодИнициализацииRecycreView();
            МетодHandlerCallBack();
            МетодВыходНаAppBack();
            МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
            МетодСлушательКурсора();
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
       start=     Calendar.getInstance().getTimeInMillis();
       startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();
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
    public void onStart() {
        super.onStart();
        try{
            asyncTaskLoader=new AsyncTaskLoader(getContext()) {
                @Nullable
                @Override
                public Object loadInBackground() {
                    МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеЦФО",0);
                    return cursorНомерЦФО;
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                    asyncTaskLoader.reset();
                    if (data.toString().length()>0) {
                        onResume();
                        // TODO: 03.11.2022  после получение данных перересует Экран
                        МетодДизайнПрограссБара();
                    }
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
    public void onResume() {
        super.onResume();
        try{
            МетодКпопкиЗначков(cursorНомерЦФО);
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
            МетодПерегрузкаRecyceView();
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
    public void onStop() {
        super.onStop();
        try{
            if (cursorНомерЦФО !=null) {
                cursorНомерЦФО.close();
            }
            if (cursorНомерМатериала !=null) {
                cursorНомерМатериала.close();
            }
            if (cursorСамиДанныеGroupBy !=null) {
                cursorСамиДанныеGroupBy.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //TODO метод делает callback с ответом на экран
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.requestLayout();
            bottomNavigationView.forceLayout();
            recyclerView.requestLayout();
            recyclerView.forceLayout();
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

    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            Log.d(this.getClass().getName(), "sqLiteCursor  " + cursorНомерЦФО);
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorНомерЦФО);
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




    private void МетодВыходНаAppBack() {
        try {
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        Intent Интент_BackВозвращаемАктивти = getActivity().getIntent();
                        Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_Face_App.class); // Т
                        Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle gameData = new Bundle();
                        gameData.putString("ФлагСтатусИзФрагментаСканирования", "ЗакрываетИзСканирования");
                        gameData.putBinder("binder", binder);
                        gameData.putBinder("binderAsyns", binderAsyns);
                        Интент_BackВозвращаемАктивти.putExtras(gameData);
                        Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии");
                        handler.postDelayed(()->{ startActivity(Интент_BackВозвращаемАктивти); },500);
                        Log.d(this.getClass().getName(), "  v  " + v);
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
            });
            bottomNavigationItemView2создать.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);
                        handler.postDelayed(()->{ МетодЗапускСозданиНовгоМатериалов();},500);
                        Log.d(this.getClass().getName(), "  v  " + v);
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
            });
            bottomNavigationItemView3обновить.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        МетодЗапускаАнимацииКнопок(v);
                            Integer ПубличныйIDДляФрагмента =
                                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
                        // TODO: 16.11.2022  запуск синхронизации однорозовая
                            МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);
                        Log.d(this.getClass().getName(), "  v  " + v);
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
    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_СозданиеНовогоМатериалов = new FragmentCreateAdmissionmaterial();
            Bundle data=new Bundle();
            data.putBinder("binder",binder);
            data.putBinder("binderAsyns",binderAsyns);
            fragment_СозданиеНовогоМатериалов.setArguments(data);
            fragmentTransaction.replace(R.id.activity_admissionmaterias_face, fragment_СозданиеНовогоМатериалов).commit();//.layout.activity_for_fragemtb_history_task
            fragmentTransaction.show(fragment_СозданиеНовогоМатериалов);
            Log.d(this.getClass().getName(), " fragment_СозданиеНовогоМатериалов " + fragment_СозданиеНовогоМатериалов);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 02.08.2022
    void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
        try{
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака",ПубличныйIDДляФрагмента);
            Intent intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle = new Intent(getContext(), Service_ДляЗапускаодноразовойСинхронизации.class);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(), " ПРОШЕЛ ЗАПУСК  метода МетодПовторногоЗапускаВсехWorkManager__ОДНОРАЗОВОЙСинхрониазцииданных()   " +
                    "   ПубличныйIDДляФрагмента "+
                    ПубличныйIDДляФрагмента);
            intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            binderAsyns.getService().МетодЗапускаОдноразовойСинхронизацииИзСлужбы(getActivity(),intentЗапускСлужюыыСинхрониазцииЧерезСлужбуBundle);
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
    }











    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетодКпопкиЗначков(@NonNull Cursor cursor)
            throws ExecutionException, InterruptedException {
        try {
            if (cursor!=null) {
                if (cursor.getCount()> 0) {
                    Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                } else {
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                }
            }else
            {
                bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
            }
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

    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    onStart();
                    onResume();
                    progressBarСканирование.setVisibility(View.INVISIBLE);
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
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(this.getClass().getName(), "onChanged ");
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

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
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

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
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

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeInserted ");
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

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeRemoved ");
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

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "     onItemRangeMoved ");
         /*                   recyclerView.getRecycledViewPool().clear();
                            recyclerView.getAdapter().notifyDataSetChanged();
                            recyclerView.refreshDrawableState();*/
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
                });
                recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                        Log.d(this.getClass().getName(), "     holder "+holder);
                        //   ContentProviderOperation.Builder builder = null;
                    }
                });
                // TODO: 15.10.2022  дополнительные слушатели

                recyclerView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return null;
                    }
                });
                // TODO: 17.10.2022 метод внешний вид нижних строчек
                recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {

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
                    public void onChildViewDetachedFromWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {
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
                });

                recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }
                });
                recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
                    @Override
                    public boolean onFling(int velocityX, int velocityY) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return false;
                    }
                });
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

    // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
    @SuppressLint("FragmentLiveDataObserve")
    void МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        try {
            String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
            String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
            LifecycleOwner lifecycleOwner =this ;
            LifecycleOwner lifecycleOwnerОбщая =this ;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            lifecycleOwnerОбщая.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });

            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {
                           if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                               Long CallBaskОтWorkManagerОдноразового =
                                       СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                               0l);
                               long end = Calendar.getInstance().getTimeInMillis();
                               long РазницаВоврмени=end-startДляОбноразвовной;
                               if (РазницаВоврмени>5000) {
                                   if (CallBaskОтWorkManagerОдноразового>0) {
                                       onStart();
                                       onResume();
                                       // TODO: 21.11.2022  запускаем удаление
                                   }
                                   Intent intentУдалениеСтатусаУдаленияТабеля=new Intent();
                                   intentУдалениеСтатусаУдаленияТабеля.setClass(getContext(), Service_For_Public.class);
                                   intentУдалениеСтатусаУдаленияТабеля.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                                   getActivity(). startService(intentУдалениеСтатусаУдаленияТабеля);
                                   WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);
                               }
                                    }
                                    progressBarСканирование.setVisibility(View.INVISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            });
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {
                                    if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                        long end = Calendar.getInstance().getTimeInMillis();
                                        Integer CallBaskОтWorkManageОбщая =
                                                СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWorkMananger", 0);
                                        Long РелультатОбщеегоWorkMAnger =
                                                СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("WorkManangerVipolil", 0l);
                                        long РазницаВоврмени=end-start;
                                        if (РазницаВоврмени>6000) {
                                            onStart();
                                            onResume();
                                        }
                                    }
                                       // WorkManager.getInstance(getContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая).getResult();
                                    progressBarСканирование.setVisibility(View.INVISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            });
            // TODO: 29.09.2021  конец синхрониазции по раписанию
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 02.08.2022
    protected   void МетодПолучениеДанныхДЛяПолучениеМатериалов(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов,@NonNull Integer ТекущаяЦФО ){
        try{
            ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                case "ПолучениеНомерМатериала":
                    bundleДляПЕредачи.putString("Таблица","view_taterials_group");//TODO сами данные
                    break;
            }
            bundleДляПЕредачи.putInt("ПубличныйIDДляФрагмента",ПубличныйIDДляФрагмента);
            bundleДляПЕредачи.putInt("ТекущаяЦФО",ТекущаяЦФО);
            bundleДляПЕредачи.putInt("ТекущаяНомерМатериала",ТекущаяНомерМатериала);
            bundleДляПЕредачи.putString("ФлагКакиеДанныеНужныПолучениеМатериалов",ФлагКакиеДанныеНужныПолучениеМатериалов);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакиеДанныеНужныПолучениеМатериалов);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    cursorНомерЦФО = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    if (cursorНомерЦФО.getCount() > 0) {
                        cursorНомерЦФО.moveToFirst();
                        // TODO: 15.10.2022
                        Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    }
                    break;
                case "ПолучениеНомерМатериала":
                    cursorНомерМатериала = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    if (cursorНомерМатериала.getCount() > 0) {
                        cursorНомерМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    }
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                    cursorСамиДанныеGroupBy = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    if (cursorСамиДанныеGroupBy.getCount() > 0) {
                        cursorСамиДанныеGroupBy.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодДизайнПрограссБара() {
        progressBarСканирование.postDelayed(()->{
            progressBarСканирование.setVisibility(View.INVISIBLE);
            progressBarСканирование.setIndeterminate(true);
        },1000);
    }

    private void МетодСлушательКурсора() {
        // TODO: 15.10.2022  слушатиель для курсора
        try {
            if (cursorНомерЦФО !=null) {
                cursorНомерЦФО.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }
                });
                // TODO: 15.10.2022
                cursorНомерЦФО.registerContentObserver(new ContentObserver(handler) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange, uri);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private TableLayout tableLayoutМатериалРодительная;
        private MaterialCardView cardViewМатериалРодительная;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
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
        private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                tableLayoutМатериалРодительная = itemView.findViewById(R.id.TableLayoutAdmissionLayoutInflater);
                cardViewМатериалРодительная = itemView.findViewById(R.id.CardviewassibAmaterial);
                Log.d(this.getClass().getName(), " cardViewМатериал   " + cardViewМатериалРодительная);
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
        private Cursor cursor;
        public MyRecycleViewAdapter(@NotNull Cursor cursor) {
            this.cursor = cursor;
            if ( cursor!=null) {
                if (cursor.getCount() > 0 ) {
                    Log.i(this.getClass().getName(), " cursor  " + cursor.getCount());
                }
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToPosition(position);
                        ТекущаяЦФО=        МетодВытаскиваемТекущийЦФО(cursor);    // TODO: 17.10.2022  метод который вытаскиваем Текущее Значение ЦФО для получение дальнейших данных
                        //  ХэшДааныеСтрока = (ConcurrentSkipListMap<String, String>) ArrayListДанныеОтСканироваиниеДивайсов.get(position);
                        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " cursor " + cursor);
                    }
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
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);
                }else {
                    if (cursor.getCount() > 0) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  sqLiteCursor.getCount()  " + cursor.getCount());
                    } else {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisl, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  sqLiteCursor.getCount()  " + cursor.getCount() );
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

        private Integer МетодВытаскиваемТекущийЦФО(@NonNull Cursor cursor) {
            try{
                Integer индексТекущаяЦФО =cursor.getColumnIndex("cfo");
                ТекущаяЦФО =cursor.getInt(индексТекущаяЦФО);
                // TODO: 19.10.2022 название ЦФО
                Integer индексТекущаяНазваниеЦФО =cursor.getColumnIndex("name_cfo");
                ТекущаяИмяЦФО =cursor.getString(индексТекущаяНазваниеЦФО);
                Log.i(this.getClass().getName(),  "  ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяИмяЦФО " +ТекущаяИмяЦФО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ТекущаяЦФО;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor);
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        МетодЗаполняемДаннымиПолучениеМАтериалов(holder, cursor);
                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                    } else {
                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                    }
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

        private void МетодАнимации(MyViewHolder holder) {
            try {
             //   holder.cardViewМатериалРодительная.startAnimation(animation);
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
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                if (cursor != null && holder.cardViewМатериалРодительная != null) {
                    // TODO: 18.10.2022 заполеняем данныими
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayoutМатериалРодительная);
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


        private void МетодДобавленеиЕлементоввRecycreView(@NonNull TableLayout tableLayoutРодительская) {
            try {
                // TODO: 07.11.2022   ВТОРОЙ ЭТАП ПОЛУЧАЕМ НОМЕР ЦФО
                if (binder!=null && cursor!=null && ТекущаяЦФО>0) {
                    // TODO: 03.11.2022 Второй Запрос Получем САМО Цифра Полученого Материла
                    МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеНомерМатериала",ТекущаяЦФО);
                }
                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursorНомерМатериала + " ТекущаяЦФО " +ТекущаяЦФО);

                // TODO: 18.10.2022 название ЦФО
                МетодДанныеНазваниеЦФО(tableLayoutРодительская);
                // TODO: 18.10.2022 дял линии
                МетодДанныеЛиния(tableLayoutРодительская);
                // TODO: 18.10.2022 Добавяем Названием Столбиков
                МетодДанныеНазваниеСтолбиков(tableLayoutРодительская);
                // TODO: 07.11.2022 сами данные
                        do{
                            // TODO: 07.11.2022  ТРЕТИЙ ЭТАП ПОЛУЧАЕМ  НОМЕР ДОКУМЕНТА
                         Integer ИндексМатериала=  cursorНомерМатериала.getColumnIndex("nomenvesov_zifra");
                         // Integer ИндексМатериала=  cursorНомерМатериала.getColumnIndex("nomen_vesov");
                            ТекущаяНомерМатериала=      cursorНомерМатериала.getInt(ИндексМатериала);
                            Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursor
                                    + " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);
                            // TODO: 07.11.2022 И ЗАПУСКАМ ФИЛЬНАЙ ТРЕИЙ ЭТАП ПОЛУЧЕНИЕ СГРУПИРОВАННЫХ ДАННЫХ
                            МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеСгрупированныеСамиДанные",ТекущаяЦФО);
                            Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorСамиДанныеGroupBy " + cursorСамиДанныеGroupBy
                                    + " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);
                            // TODO: 18.10.2022 Добавяем Сами Данные Получение материалов
                            МетодДанныеПолучениеМатериалов(tableLayoutРодительская,cursorСамиДанныеGroupBy);
                            // TODO: 09.12.2022 делалем дополнительно движение
                        }while (cursorНомерМатериала.moveToNext());
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

        private void МетодДанныеПолучениеМатериалов(@NonNull TableLayout tableLayoutРодительская , @NonNull Cursor cursorСамиДанныеGroupBy) {
            // TODO: 18.10.2022  ДАННЫЕ
            try {
                TableLayout tableLayoutДочернная=new TableLayout(getContext());
                            МетодВнутриСпинера(tableLayoutДочернная,tableLayoutРодительская,cursorСамиДанныеGroupBy);
                // TODO: 06.11.2022 данные цикл
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
        private void МетодВнутриСпинера(@NonNull TableLayout tableLayout ,@NonNull TableLayout tableLayoutРодительская, @NonNull Cursor cursorСамиДанныеGroupBy) {
            try{
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" + " cursorСамиДанныеGroupBy " +cursorСамиДанныеGroupBy);
                tableLayout= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
               // tableLayout.setAnimation(animation);
                TableRow rowПервыеДанные = (TableRow)   tableLayout.findViewById(R.id.TableData);
                TextView textView=  rowПервыеДанные.findViewById(R.id.textview1);
                String Материал= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("typematerial"))).orElse("");
                textView.setText(Материал.trim());
                TextView textView2=  rowПервыеДанные.findViewById(R.id.textview2);
                String Весовая= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                textView2.setText(Весовая.trim());
                Float Сумма= Optional.ofNullable(cursorСамиДанныеGroupBy.getFloat(cursorСамиДанныеGroupBy.getColumnIndex("moneys"))).orElse(0f);
                TextView textView3=  rowПервыеДанные.findViewById(R.id.textview3);
                textView3.setText(Сумма.toString());
                // TODO: 08.11.2022  заполеним данными Строчку ДляДальнейшего Использование
                Bundle data=new Bundle();
                data.putString("Материал",Материал);
                Integer Цфо= Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("cfo"))).orElse(0);
                data.putInt("Цфо",Цфо);
                Integer НомерВыбраногоМатериала=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov_zifra"))).orElse(0);
                data.putInt("НомерВыбраногоМатериала",НомерВыбраногоМатериала);
                String ВыбранныйМатериал=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                data.putString("ВыбранныйМатериал",ВыбранныйМатериал);
                data.putFloat("Сумма",Сумма);
                rowПервыеДанные.setTag(data);

                // TODO: 06.11.2022 удаление
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                tableLayout.removeViewInLayout(rowПервыеДанные);
                tableLayout.removeView(rowПервыеДанные);
                rowПервыеДанные.setId(new Random().nextInt());
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                rowПервыеДанные.startAnimation(animation);
                // TODO: 18.10.2022 добавляем  сами данные
                МетодДобаленияНовыхСтрокДанных(rowПервыеДанные, tableLayoutРодительская);
                // TODO: 19.10.2022
                // TODO: 08.11.2022 метод клика по строке Row
                Log.d(this.getClass().getName(), "МетодаКликаПоtableRow cursorСамиДанныеGroupBy  "+cursorСамиДанныеGroupBy );
                МетодаКликаПоtableRow(rowПервыеДанные);
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

        // TODO: 08.11.2022 метод перехода на дитализацию
        private void МетодаКликаПоtableRow(TableRow rowПервыеДанные) {
            try{
            rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                    Log.d(this.getClass().getName(), "МетодаКликаПоtableRow v  " + v+ " bundleПереходДетализацию "+bundleПереходДетализацию);
                    if (bundleПереходДетализацию != null) {
                        МетодЗапускаАнимацииКнопок((View) rowПервыеДанные);
                            // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                            fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            Fragment              fragmentAdmissionMaterialsDetailing = new FragmentAdmissionMaterialsDetailing();
                            bundleПереходДетализацию.putBinder("binder",binder);
                            bundleПереходДетализацию.putBinder("binderAsyns",binderAsyns);
                            fragmentAdmissionMaterialsDetailing.setArguments(bundleПереходДетализацию);
                            fragmentTransaction.replace(R.id.activity_admissionmaterias_face, fragmentAdmissionMaterialsDetailing);//.layout.activity_for_fragemtb_history_tasks
                            fragmentTransaction.commit();
                            fragmentTransaction.show(fragmentAdmissionMaterialsDetailing);
                            Log.d(this.getClass().getName(), " fragmentAdmissionMaterialsDetailing " + fragmentAdmissionMaterialsDetailing);
                        Log.d(this.getClass().getName(), "  v  " + v);
                    }
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

        private void МетодДанныеНазваниеСтолбиков(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutНазваниеСтобликов= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеНазваниеСтобликов = (TableRow)   tableLayoutНазваниеСтобликов.findViewById(R.id.TableHendlers);
                tableLayoutНазваниеСтобликов.recomputeViewAttributes(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeViewInLayout(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeView(rowПервыеНазваниеСтобликов);
                rowПервыеНазваниеСтобликов.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеСтобликов, tableLayoutРодительская);
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

        private void МетодДанныеЛиния(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutЛиния= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеЛиния = (TableRow)   tableLayoutЛиния.findViewById(R.id.TableRowLine);
                tableLayoutЛиния.recomputeViewAttributes(rowПервыеЛиния);
                tableLayoutЛиния.removeViewInLayout(rowПервыеЛиния);
                tableLayoutЛиния.removeView(rowПервыеЛиния);
                rowПервыеЛиния.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеЛиния, tableLayoutРодительская);
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

        private void МетодДанныеНазваниеЦФО(@NonNull TableLayout tableLayoutРодительская) {
            try{
                TableLayout  tableLayoutНазваниеЦФО= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
                TableRow rowПервыеНазваниеЦФО = (TableRow)   tableLayoutНазваниеЦФО.findViewById(R.id.TableRowNameCFO);
                TextView textViewНазваниеЦФО=  rowПервыеНазваниеЦФО.findViewById(R.id.textviewnameCFO);
                textViewНазваниеЦФО.setText(ТекущаяИмяЦФО.trim());
                tableLayoutНазваниеЦФО.recomputeViewAttributes(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeViewInLayout(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeView(rowПервыеНазваниеЦФО);
                rowПервыеНазваниеЦФО.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  название ЦФО
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеЦФО, tableLayoutРодительская);
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

        private void МетодДобаленияНовыхСтрокДанных(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
            try {
                tableLayoutРодительская.removeView(rowПервыеДанные);
                tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                tableLayoutРодительская.addView(rowПервыеДанные);
                tableLayoutРодительская.requestLayout();
                tableLayoutРодительская.refreshDrawableState();
                tableLayoutРодительская.forceLayout();
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
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        КоличесвоСтрок = cursor.getCount();
                        Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                    } else {
                        КоличесвоСтрок = 1;
                        Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                    }
                }else {
                    КоличесвоСтрок = 1;
                    Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor+"  bilder "+ binder);
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
            return КоличесвоСтрок;
        }
    }

}