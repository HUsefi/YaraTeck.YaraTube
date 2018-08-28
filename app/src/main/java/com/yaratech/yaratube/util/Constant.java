package com.yaratech.yaratube.util;

import android.content.Context;
import android.net.ConnectivityManager;


public class Constant {
    public static final String BASE_URL = "https://api.vasapi.click/";
    public static final String STORE_ID = "16";
    public static final String INTERNET_ERROR_MESSAGE = "لطفا اتصال به اینترنت را مجددا بررسی کنید.";

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo()
                != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9");
    }



}
