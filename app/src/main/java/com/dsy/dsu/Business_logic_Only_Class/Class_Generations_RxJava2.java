package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;

public class Class_Generations_RxJava2 {

    Activity contextДляКлассRxJava2;

    public Class_Generations_RxJava2(Activity activity) {

        contextДляКлассRxJava2=activity;
        ///

      /*  Observable<StringBuffer> observable = Observable.just(1);
        observable.subscribeOn(Schedulers.computation())
                .subscribe(
                        new Observer<StringBuffer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                // TODO: 02.09.2021
                                Log.d(Class_MODEL_synchronized.class.getName(), " PUBLIC_CONTENT.БуферРезуоотатРаботыБазыВсестеССервером " );
                            }

                            @Override
                            public void onNext(@NonNull StringBuffer stringBuffer) {

                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO: 02.09.2021
                                Log.d(Class_MODEL_synchronized.class.getName(), " PUBLIC_CONTENT.БуферРезуоотатРаботыБазыВсестеССервером " );
                            }

                            @Override
                            public void onComplete() {
                                // TODO: 02.09.2021
                                Log.d(Class_MODEL_synchronized.class.getName(), " PUBLIC_CONTENT.БуферРезуоотатРаботыБазыВсестеССервером " );

                                observable.subscribeOn(AndroidSchedulers.mainThread())
                                        .subscribe ((ddd)->{
                                            Log.d(Class_MODEL_synchronized.class.getName(), " PUBLIC_CONTENT.БуферРезуоотатРаботыБазыВсестеССервером " );

                                            // TODO: 24.09.2021

                                            Toast.makeText(contextДляКлассRxJava2, " Превый RXJAVA 2" , Toast.LENGTH_SHORT).show();


                                        }  );


                            }


                        });*/
    }



    //todo функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ

}
