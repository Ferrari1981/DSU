package com.dsy.dsu.Code_Gson_Processing;

import android.util.Log;

public class SubClass_JSON_B_P_POST_1C<LinkedBlockingDeque>  {
    public Integer  StatusCommitPayFromAndroid ;
    public String DocumentCommitPayFromAndroid;

    // TODO: 25.10.2022 1c генерация 
    public SubClass_JSON_B_P_POST_1C(Integer statusCommitPayFromAndroid, String documentCommitPayFromAndroid) {
        StatusCommitPayFromAndroid = statusCommitPayFromAndroid;
        DocumentCommitPayFromAndroid = documentCommitPayFromAndroid;
        Log.d(this.getClass().getName(), "  SubClass_JSON_B_P_POST  DocumentCommitPayFromAndroid " + DocumentCommitPayFromAndroid);
    }
}
