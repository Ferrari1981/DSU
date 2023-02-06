package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data_AssinaMaterial;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;


import org.jetbrains.annotations.NotNull;

import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;



// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentAdmissionMaterialsDetailing extends Fragment {
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
    private  Cursor cursorДетализацияМатериала;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private Integer ТекущаяЦФО=0;
    private Integer ТекущаяНомерМатериала=0;
    private Float  СуммаВыбраногоМатериала=0f;
    private String ВыбранныйМатериал=new String();
    private String РодительскийМатериал=new String();
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеНовогоМатериалов;
    private  TextView   textViewНазваниеФрагмента;
    private AsyncTaskLoader asyncTaskLoaderДетализация;
    private    Bundle data;
    private Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binderAsyns;
    long start;
    long startДляОбноразвовной;
    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentAdmissionMaterialsDetailing() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
             data=      getArguments();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
                ТекущаяЦФО= data.getInt("Цфо");
                ТекущаяНомерМатериала= data.getInt("НомерВыбраногоМатериала");
                РодительскийМатериал   =data.getString("Материал");
                СуммаВыбраногоМатериала=data.getFloat("Сумма");
                ВыбранныйМатериал =data.getString("ВыбранныйМатериал");
                    binderAsyns=  (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) data.getBinder("binderAsyns");

                // TODO: 10.11.2022
                start=     Calendar.getInstance().getTimeInMillis();
                startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();
            }
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterialsDetailing  binder  "+binder+
                    " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала "+ТекущаяНомерМатериала+
                    "ВыбранныйМатериал "+ВыбранныйМатериал+"СуммаВыбраногоМатериала "+СуммаВыбраногоМатериала);
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
            view= inflater.inflate(R.layout.fragment_admission_materials_detelizaziy, container, false);
            this.container=container;
            Log.d(this.getClass().getName(), " onCreateView FragmentAdmissionMaterialsDetailing" + view);
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
       //     textViewНазваниеФрагмента.setText(ВыбранныйМатериал.toUpperCase()+" ("+РодительскийМатериал.toLowerCase()+")");
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(50);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
           // bottomNavigationItemView3обновить.setVisibility(View.GONE);
            //todo запуск методов в фрагменте
            МетодИнициализацииRecycreView();
            МетодHandlerCallBack();
            МетодВыходНаAppBack();
            МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
            МетодСлушательКурсора();
            МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterialsDetailing  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
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
            asyncTaskLoaderДетализация =new AsyncTaskLoader(getContext()) {
                @Nullable
                @Override
                public Object loadInBackground() {
                    МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеНомерМатериалаДетализация",ТекущаяЦФО,ТекущаяНомерМатериала);
                    return cursorДетализацияМатериала;
                }
            };
            if (!asyncTaskLoaderДетализация.isStarted()) {
                asyncTaskLoaderДетализация.startLoading();
                asyncTaskLoaderДетализация.forceLoad();
                asyncTaskLoaderДетализация.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                        if (data!=null) {
                            asyncTaskLoaderДетализация.reset();
                            if (data.toString().length()>0) {
                                onResume();
                                // TODO: 03.11.2022  после получение данных перересует Экран
                                МетодДизайнПрограссБара();
                            }
                        }
                    }
                });
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

    @Override
    public void onResume() {
        super.onResume();
        try{
            МетодКпопкиЗначков(cursorДетализацияМатериала);
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
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
            if (cursorДетализацияМатериала !=null) {
                cursorДетализацияМатериала.close();
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
            bottomNavigationView.refreshDrawableState();
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            recyclerView.refreshDrawableState();
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
            Log.d(this.getClass().getName(), "cursorДетализацияМатериала " + cursorДетализацияМатериала);
            LinkedTransferQueue<String> linkedTransferQueueДетализация=new LinkedTransferQueue();
            linkedTransferQueueДетализация.offer("Делаем Детализацию");
            myRecycleViewAdapter = new MyRecycleViewAdapter(linkedTransferQueueДетализация);
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
                        Fragment      fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
                        Bundle data=new Bundle();
                        data.putBinder("binder",binder);
                        data.putBinder("binderAsyns",binderAsyns);
                        fragmentПолученыеМатериалов.setArguments(data);
                        fragmentTransaction = fragmentManager.beginTransaction();
                    //    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.activity_admissionmaterias_face, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
                        fragmentTransaction.show(fragmentПолученыеМатериалов);
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
                        handler.postDelayed(()->{
                                    Integer ПубличныйIDДляФрагмента =
                                            new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
                                    // TODO: 16.11.2022  запуск синхронизации однорозовая
                                    МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);},
                                500);
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
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
/*            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right
                    ,android. R.anim.slide_in_left,android. R.anim.slide_out_right);*/
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
            public boolean handleMessage(@NonNull Message msg) {
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
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            if (myRecycleViewAdapter!=null) {
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

                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
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
            }
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    if(oldScrollY<0){
                        handler.postDelayed(()->{   bottomNavigationView.setVisibility(View.GONE);
                            textViewНазваниеФрагмента.setVisibility(View.GONE);
                            },1000);
                    }else {
                        handler.postDelayed(()->{
                            bottomNavigationView.setVisibility(View.VISIBLE);
                            textViewНазваниеФрагмента.setVisibility(View.VISIBLE);
                            },1000);

                    }
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
    void МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        try {
            LifecycleOwner lifecycleOwner =this ;;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            // TODO: 03.08.2022 методы работы work manager
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                            Long CallBaskОтWorkManagerОдноразового =
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                            0l);
                            if (CallBaskОтWorkManagerОдноразового>0) {
                                long end = Calendar.getInstance().getTimeInMillis();
                                long РазницаВоврмени=end-startДляОбноразвовной;
                                if (РазницаВоврмени>2000) {
                                    onStart();
                                    onResume();
                                }
                                // TODO: 21.11.2022  запускаем удаление
                            }
                            Intent intentУдалениеСтатусаУдаленияТабеля=new Intent();
                            intentУдалениеСтатусаУдаленияТабеля.setClass(getContext(), Service_For_Public.class);
                            intentУдалениеСтатусаУдаленияТабеля.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                            getActivity(). startService(intentУдалениеСтатусаУдаленияТабеля);
                            WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);
                            Log.d(this.getClass().getName(), "  МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager " +
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().name()+  "CallBaskОтWorkManagerОдноразового "
                                    +CallBaskОтWorkManagerОдноразового);
                        }
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                        || СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.FAILED) == 0) {
                            МетодДизайнПрограссБара();
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
    protected   void МетодПолучениеДанныхДЛяПолучениеМатериалов(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов
            ,@NonNull Integer ТекущаяЦФО
    , @NonNull Integer ТекущаяНомерМатериала ){
        try{
            ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеНомерМатериалаДетализация":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");//TODO сами данные
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
                    cursorДетализацияМатериала = (Cursor) binder.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
                    if (cursorДетализацияМатериала.getCount() > 0) {
                        cursorДетализацияМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
                    }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
            if (cursorДетализацияМатериала !=null) {
                cursorДетализацияМатериала.registerDataSetObserver(new DataSetObserver() {
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
                cursorДетализацияМатериала.registerContentObserver(new ContentObserver(handler) {
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
        private LinkedTransferQueue<String> linkedTransferQueueДетализация;
        public MyRecycleViewAdapter(@NotNull LinkedTransferQueue<String> linkedTransferQueueДетализация) {
            this.linkedTransferQueueДетализация = linkedTransferQueueДетализация;
            if ( linkedTransferQueueДетализация!=null) {
                    Log.i(this.getClass().getName(), " linkedTransferQueueДетализация  " + linkedTransferQueueДетализация.peek());
            }
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " linkedTransferQueueДетализация " + linkedTransferQueueДетализация+
                        " cursorДетализацияМатериала "+cursorДетализацияМатериала);
                if (cursorДетализацияМатериала!=null) {
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder,cursorДетализацияМатериала);
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
                if(asyncTaskLoaderДетализация.isStarted()){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialovdetalizasia, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);
                }else {
                    if (cursorДетализацияМатериала.getCount() > 0) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursorДетализацияМатериала.getCount());
                    } else {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisldetalizasia, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursorДетализацияМатериала.getCount() );
                        textViewНазваниеФрагмента.setText("Материал");
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

        private void МетодАнимации(MyViewHolder holder) {
            try {
            //    holder.cardViewМатериалРодительная.startAnimation(animation);
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
                    // TODO: 18.10.2022 заполеняем данныими
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayoutМатериалРодительная);
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
                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursorДетализацияМатериала + " ТекущаяЦФО " +ТекущаяЦФО);
                cursorДетализацияМатериала.moveToFirst();
                // TODO: 18.10.2022 название ЦФО
                if (tableLayoutРодительская!=null) {
                    МетодДанныеНазваниеЦФО(tableLayoutРодительская,cursorДетализацияМатериала);
                    // TODO: 18.10.2022 дял линии
                    МетодДанныеЛиния(tableLayoutРодительская);
                    // TODO: 18.10.2022 Добавяем Названием Столбиков
                    МетодДанныеНазваниеСтолбиков(tableLayoutРодительская);
                }

                if (cursorДетализацияМатериала.getCount()>0) {
                    String НазваниеЦФОДляДетализации= Optional.ofNullable(cursorДетализацияМатериала.getString(cursorДетализацияМатериала.
                            getColumnIndex("name_cfo"))).orElse("");
                    textViewНазваниеФрагмента.setText(НазваниеЦФОДляДетализации.replace("\"", "").replace("\\n", "")
                            .replace("\\r", "").replace("\\", "")
                            .replace("\\t", "").toUpperCase().trim());
                    // TODO: 07.11.2022 сами данные
                            Integer ПозицияВнутриСТрочки=0;
                            do{
                                // TODO: 07.11.2022  ТРЕТИЙ ЭТАП ПОЛУЧАЕМ  НОМЕР ДОКУМЕНТА
                                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorНомерМатериала " + cursorДетализацияМатериала
                                        + " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);
                                // TODO: 18.10.2022 Добавяем Сами Данные Получение материалов
                                МетодДанныеПолучениеМатериалов(tableLayoutРодительская, cursorДетализацияМатериала,ПозицияВнутриСТрочки++);
                            }while (cursorДетализацияМатериала.moveToNext());
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

        private void МетодДанныеПолучениеМатериалов(@NonNull TableLayout tableLayoutРодительская , @NonNull Cursor cursorНомерМатериалаДетализация,@NonNull Integer ПозицияВнутриСТрочки) {
            // TODO: 18.10.2022  ДАННЫЕ
            try {
                TableLayout tableLayoutДочернная=new TableLayout(getContext());
                            МетодВнутриСпинера(tableLayoutДочернная,tableLayoutРодительская,cursorНомерМатериалаДетализация,ПозицияВнутриСТрочки);
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
        private void МетодВнутриСпинера(@NonNull TableLayout tableLayout ,@NonNull TableLayout tableLayoutРодительская, @NonNull Cursor cursorНомерМатериалаДетализация
        , @NonNull Integer ПозицияВнутриСТрочки) {
            try{
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии"
                        + " cursorНомерМатериалаДетализация " +cursorНомерМатериалаДетализация);
                tableLayout= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
               // tableLayout.setAnimation(animation);
                TableRow rowПервыеДанные = (TableRow)   tableLayout.findViewById(R.id.TableData);
                TextView textView=  rowПервыеДанные.findViewById(R.id.textview1);
                String Материал= Optional.ofNullable(cursorНомерМатериалаДетализация.getString(cursorНомерМатериалаДетализация.
                        getColumnIndex("nomenvesov"))).orElse("");
                textView.setText(Материал.trim());
                TextView textView2=  rowПервыеДанные.findViewById(R.id.textview2);
                Float Деньги= Optional.ofNullable(cursorНомерМатериалаДетализация.getFloat(cursorНомерМатериалаДетализация.
                        getColumnIndex("count"))).orElse(0f);
                textView2.setText(Деньги.toString());
                String Время= Optional.ofNullable(cursorНомерМатериалаДетализация.getString(cursorНомерМатериалаДетализация.
                        getColumnIndex("date_update"))).orElse("");
                Время=     new Class_Generation_Data_AssinaMaterial(getContext(),Время).ГлавнаяДатаИВремяОперацийСБазойДанных();

                TextView textView3=  rowПервыеДанные.findViewById(R.id.textview3);
                textView3.setText(Время.toString());
                Long UUIDВыбраныйМатериал= Optional.ofNullable(cursorНомерМатериалаДетализация.getLong(cursorНомерМатериалаДетализация.
                        getColumnIndex("uuid"))).orElse(0l);
                // TODO: 10.11.2022
                Bundle bundleДанныеДляСтрочки=new Bundle();
                bundleДанныеДляСтрочки.putLong("UUIDВыбраныйМатериал",UUIDВыбраныйМатериал);
                bundleДанныеДляСтрочки.putString("Материал",Материал);
                bundleДанныеДляСтрочки.putFloat("Деньги",Деньги);
                rowПервыеДанные.setTag(bundleДанныеДляСтрочки);
                // TODO: 06.11.2022 удаление
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                tableLayout.removeViewInLayout(rowПервыеДанные);
                tableLayout.removeView(rowПервыеДанные);
                rowПервыеДанные.setId(ПозицияВнутриСТрочки);
             //   rowПервыеДанные.setId(new Random().nextInt());
                rowПервыеДанные.startAnimation(animation);
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                // TODO: 18.10.2022 добавляем  сами данные
                МетодДобаленияНовыхСтрокДанных(rowПервыеДанные, tableLayoutРодительская);
                // TODO: 16.11.2022  слушатель Удаление строк
                МетодаКликаУдаленияМатериалаПоtableRow(rowПервыеДанные);
                // TODO: 19.10.2022 Редактирование Данных
              //  МетодаКликаРедактированиеМатериалаПоtableRow(rowПервыеДанные);
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


        // TODO: 08.11.2022 метод Удаление материала
        private void МетодаКликаУдаленияМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                        Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                +bundleПереходУдалениеМатериала);
                        if (bundleПереходУдалениеМатериала != null) {
                        Long UUIDДляУдаления= bundleПереходУдалениеМатериала.getLong("UUIDВыбраныйМатериал",0l);
                        String Материал= bundleПереходУдалениеМатериала.getString("Материал","");
                        Float Деньги= bundleПереходУдалениеМатериала.getFloat("Деньги",0f);
                            bundleПереходУдалениеМатериала.putString("selection","uuid=?");
                            Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                            Snackbar snackbar = Snackbar.make(v, "Text to display", Snackbar.LENGTH_LONG);
                            View view = snackbar .getView();
                            TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                            textView.setTextColor(Color.parseColor("#FF4500"));
                            textView.setText(Материал+" : "+Деньги+"");
                            snackbar
                                    .setAction("Удалить ? ", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try{
                                                progressBarСканирование.setVisibility(View.VISIBLE);
                                                    Intent intentДляУдалениеМатериалов=new Intent("УдалениеВыбранныеМатериалыДетализации");
                                                    intentДляУдалениеМатериалов.putExtras(bundleПереходУдалениеМатериала);
                                                    Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                                    Integer РезультатСменыСтатусаНАУдалнной=    binder.getService().МетодCлужбыУдалениеМатериалов(getContext(),intentДляУдалениеМатериалов);
                                                    Log.d(this.getClass().getName(), "  РезультатСменыСтатусаНАУдалнной  " + РезультатСменыСтатусаНАУдалнной);
                                                    if(РезультатСменыСтатусаНАУдалнной>0){
                                                        recyclerView.getAdapter().notifyItemRemoved(myViewHolder.getAdapterPosition());
                                                        onStart();
                                                        onResume();
                                                    }
                                                handler.postDelayed(()->{
                                                    progressBarСканирование.setVisibility(View.INVISIBLE);
                                                },200);
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
                                    }).setActionTextColor(Color.WHITE)
                                    .setTextColor(Color.GRAY)
                                    .setDuration(3000)
                                    .show();
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
        // TODO: 08.11.2022 метод редактирование
        private void МетодаКликаРедактированиеМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                        Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                +bundleПереходУдалениеМатериала);
                        if (bundleПереходУдалениеМатериала != null) {
                            Long UUIDДляУдаления= bundleПереходУдалениеМатериала.getLong("UUIDВыбраныйМатериал",0l);
                            String Материал= bundleПереходУдалениеМатериала.getString("Материал","");
                            Float Деньги= bundleПереходУдалениеМатериала.getFloat("Деньги",0f);
                            Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                            Snackbar snackbar = Snackbar.make(v, "Text to display", Snackbar.LENGTH_LONG);
                            View view = snackbar .getView();
                            TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                            TextView viewСохранеие = (TextView) view.findViewById(R.id.snackbar_action);
                            textView.setTextColor(Color.parseColor("#FF4500"));
                            textView.setText(Деньги.toString());
                            viewСохранеие.setText("dddddfgg");
                            viewСохранеие.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                }
                            });
                            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                            snackbar
                                    .setAction("Сохранить ? ", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                            binder.getService().onCreate();
                                        }
                                    }).setActionTextColor(Color.WHITE)
                                    .setTextColor(Color.GRAY)
                                    .setDuration(6000000)
                                    .show();
                        }
                        return true;
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
                TableLayout  tableLayoutНазваниеСтобликов= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);
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
                        .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);
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

        private void МетодДанныеНазваниеЦФО(@NonNull TableLayout tableLayoutРодительская,@NonNull Cursor cursorДетализацияМатериала) {
            try{
                TableLayout  tableLayoutНазваниеЦФО= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
                TableRow rowПервыеНазваниеЦФО = (TableRow)   tableLayoutНазваниеЦФО.findViewById(R.id.TableRowNameCFO);
                TextView textViewНазваниеЦФО=  rowПервыеНазваниеЦФО.findViewById(R.id.textviewnameCFO);
                // TODO: 10.11.2022  данные для название ЦФО
                String НазваниеЦФОДЛяДетализации= Optional.ofNullable(cursorДетализацияМатериала.getString(cursorДетализацияМатериала.
                        getColumnIndex("typematerial"))).orElse("");
                textViewНазваниеЦФО.setText(НазваниеЦФОДЛяДетализации.trim());
                tableLayoutНазваниеЦФО.recomputeViewAttributes(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeViewInLayout(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeView(rowПервыеНазваниеЦФО);
                rowПервыеНазваниеЦФО.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  название ЦФО
                //МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеЦФО, tableLayoutРодительская);
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
                if (tableLayoutРодительская!=null) {
                    tableLayoutРодительская.removeView(rowПервыеДанные);
                    tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                    tableLayoutРодительская.addView(rowПервыеДанные);
                    tableLayoutРодительская.requestLayout();
                    tableLayoutРодительская.refreshDrawableState();
                    tableLayoutРодительская.forceLayout();
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

        @Override
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            try {
                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedTransferQueueДетализация.size();
        }
    }

}