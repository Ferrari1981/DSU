package com.dsy.dsu.Code_For_Firebase_And_Provader;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;

import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import javax.inject.Inject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
  private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal class_generation_sendBroadcastReceiver_and_firebase_oneSignal;
    // TODO: 02.12.2021
    public MyFirebaseMessagingService() {
        super();
        try{
        Log.w(this.getClass().getName(), " MyFirebaseMessagingService ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  MyFirebaseMessagingService   metod onNewToken "+
                 " class_generation_sendBroadcastReceiver_and_firebase_oneSignal " +class_generation_sendBroadcastReceiver_and_firebase_oneSignal);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
    }

   @Override
    public void handleIntent(@NonNull Intent intent) {
       super.handleIntent(intent);
       Log.d(this.getClass().getName(), " handleIntent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
               " MyFirebaseMessagingService  protected Intent public void handleIntent");
    }
    @NonNull
    @Override
    protected Intent getStartCommandIntent(@NonNull Intent intent) {
        Log.d(this.getClass().getName(), " getStartCommandIntent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
                " MyFirebaseMessagingService  protected Intent getStartCommandIntent");
         return super.getStartCommandIntent(intent);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      super.onMessageReceived(remoteMessage);
        try{
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal   =    new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext());
        Log.d(this.getClass().getName(), " onMessageReceived ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!  " +
                " MyFirebaseMessagingService   metod onNewToken "+remoteMessage.getMessageId()+"\n"+
              "  remoteMessage.getMessageType() "+  remoteMessage.getMessageType()+"\n"+
                "  remoteMessage.getRawData() "+remoteMessage.getRawData()+"\n"+
                 " remoteMessage.getSenderId()  " +remoteMessage.getSenderId());
        // TODO: 02.12.2021  ПРИШЛИ ДАННЫЕ ОТ FIREBASE CLOUD  НОВО СООБЕЩЕНИ И ИХ АНАЛИЗИРУЕМ И ЕСЛИ СООБЩЕНИЕ НЕ ПУСТОЕ МЫ УДАЛЯЕМ НОВТИВИКАЦИО ВИСЕВШЕЕ И СОЩЗДАЕНМ НВОЕ ЕЛСИ НЕСТЬ СООБЩЕНИ ТЕСТ СООБЩЕНИЯ
     Map<String, String> Уведоелмение= remoteMessage.getData();
        for (Map.Entry<String, String> entry: Уведоелмение.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            Log.d(this.getClass().getName(), " key "+key+"\n"+
                    " value "+value+"\n" );
            Log.d(this.getClass().getName(),  "ПРИШЛО СООБЩЕНИЕ ОТ FIREBASE CLOUD И СМОТРИМ СОДЕРЖИМОЕ "+"\n"
                    +value+"\n"+ " key " +key+"\n"+
                    " entry.getValue() " +entry.getValue());
            // TODO: 21.12.2022  запускас ONESINGLE FIREBASE
         if (entry.getValue().matches("(.*)android(.*)")) {
                    Log.d(this.getClass().getName(),  " value ЕСЛИ ЕСТЬ СООБЩЕНИ НАПИСАННО ДРУГИМ ПОЛЬОВАТЛЕМ ТО УДАЛЯЕМ УВЕДОМЛЕНИ И СОЗАДЕМ НОВЫЙ СЛУЖЮА BRODCAST"+value+"\n"+ " key " +key);
             Integer  ПубличныйIDДляОдноразовойСинхрониазции=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
             Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента  ИЗ ВСЕХ ТАБЕЛЕЙ ПубличныйIDДляОдноразовойСинхрониазции " + ПубличныйIDДляОдноразовойСинхрониазции);
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.
                    МетодЗапускаетОДНОРАЗОВУЮСинхронизациюВнутриWorkManager(getApplicationContext(),ПубличныйIDДляОдноразовойСинхрониазции);
                    Log.d(this.getClass().getName(),  "   ЗАПУСК ОДНОРАЗОВОЙ СИНХРОНИАЗЦИИ" +
                            "      new  Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодПовторногоЗапускаВсехWorkManagerОДНОРАЗОВОЙСинхрониазцииданных()  " +
                            "ПОСЛЕ ЗАПУСКА БРОДКАСТЕРА ПОСЛЕ СИСТЕМНОГО УЕДОМДЕНИЯ КОТОРЫЙ И ЗАПУСТИЛ ЭТО  И СОЗАДЕМ НОВЫЙ СЛУЖЮА BRODCAST  FAREBASE " +
                            " МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),ПубличныйIDДляОдноразовойСинхрониазции"+value+"\n" );
                    break;
           }
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        ////
        Log.d(this.getClass().getName(), " onDeletedMessages ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseMessagingService   metod onNewToken " );
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);

        try{
        /////

/*        String КлючДляFirebaseNotification="2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";

   FirebaseMessaging.getInstance().subscribeToTopic("adroid");

            RemoteMessage rm = new RemoteMessage.Builder(КлючДляFirebaseNotification)
                    .setMessageId("myApp_" + + System.currentTimeMillis())
                    .addData("action", "chat")
                    .addData("destinataire", "ee")
                    .addData("emetteur","ee")
                    .setTtl(0)
                    .build();
            FirebaseMessaging.getInstance().send(rm);*/
        ////
        Log.d(this.getClass().getName(), " onMessageSent ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseMessagingService   metod onNewToken "+s.toString() );






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


    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
        Log.e(this.getClass().getName(), " onSendError  ОШИБКА  ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL " +
                "!!!!!!!!!!!!  MyFirebaseMessagingService   metod onNewToken e  "+e.toString()+"\n"+
                " s " +s.toString());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d(this.getClass().getName(), " onNewToken  ПРИШЛО СООБЩЕНИЕ УВЕДОМЛЕНИЯ  С САЙТА ONESIGNAL !!!!!!!!!!!!   MyFirebaseMessagingService   metod onNewToken "+s.toString());

    }


}
