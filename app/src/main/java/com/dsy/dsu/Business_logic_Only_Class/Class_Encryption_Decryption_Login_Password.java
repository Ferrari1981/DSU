package com.dsy.dsu.Business_logic_Only_Class;///////// TODO ПРОВЕРЯЕТ ЕСЛИ ПОДКЛЧБЕНИ В ИНТРЕНТУ

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class  Class_Encryption_Decryption_Login_Password{

    Context context;

    public Class_Encryption_Decryption_Login_Password(Context context) {
        this.context = context;
    }






    // TODO: 11.11.2021  login
    String МетодКешированиеДанных(@NotNull String ПубличноеИмяПользовательДлСервлета ){
        // TODO: 11.11.2021


        String  ПубличныйПарольДлСервлетаБайтыОтправляемЗашифрованным = new String();
        try{

            ////шифрование


            MessageDigest md = MessageDigest.getInstance("SHA-1");//SHA-512//MD5 //SHA-384 //SHA-256 //SHA-1

            byte[] md5sum = md.digest(ПубличноеИмяПользовательДлСервлета.getBytes(StandardCharsets.UTF_8));

            Log.d(this.getClass().getName(), "md5sum  " +md5sum.toString());

            BigInteger bigInt = new BigInteger(1, md5sum);

            ПубличныйПарольДлСервлетаБайтыОтправляемЗашифрованным =String.valueOf( bigInt.longValue());
        ///////
            Log.d(this.getClass().getName(), "ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным  " +ПубличныйПарольДлСервлетаБайтыОтправляемЗашифрованным);

          //  MessageDigest meassDs = MessageDigest.getInstance("SHA-1");

           // ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным=new String(ПубличноеИмяПользовательДлСервлетаБайты);
            // TODO: 11.11.2021

            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }


        return  ПубличныйПарольДлСервлетаБайтыОтправляемЗашифрованным;
    }





    // TODO: 11.11.2021  BASE64


    // TODO: 12.11.2021 BASE64

    // TODO: 11.11.2021  login
    public String МетодПреобразованиеBase64Данных(@NotNull String ПубличноеИмяПользовательДлСервлета) {
        // TODO: 11.11.2021

        String ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным = new String();
        try {

            Log.d(this.getClass().getName(), "ПубличноеИмяПользовательДлСервлета  " + ПубличноеИмяПользовательДлСервлета);


// encode with padding
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным = Base64.getEncoder().encodeToString(ПубличноеИмяПользовательДлСервлета.getBytes(StandardCharsets.UTF_8));
            }

            // TODO: 11.11.2021
            Log.d(this.getClass().getName(), "ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным  " +ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным);
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }
        return  ПубличноеИмяПользовательДлСервлетаБайтыОтправляемЗашифрованным;
    }


    // TODO: 11.11.2021  passsword

}