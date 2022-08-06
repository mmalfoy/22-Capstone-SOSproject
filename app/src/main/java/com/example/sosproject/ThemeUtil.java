package com.example.sosproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtil {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";


    private static final String TAG = "ThemeUtil";

    public static void applyTheme(String themeColor){ // themeColor에 따라 모드 변경
        switch (themeColor){
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Log.d(TAG,"라이트 모드 적용되어있음");
                break;
            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Log.d(TAG,"다크 모드 적용되어있음");
                break;
        }
    }
    public static void modSave(Context context, String select_mod){ // 앱을 껐다 켰을때도 설정한 모드가 적용되도록
        SharedPreferences sp;
        sp = context.getSharedPreferences("mod",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mod",select_mod);
        editor.commit();
    }

    public static String modLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("mod",context.MODE_PRIVATE);
        String load_mod = sp.getString("mod","light");
        return load_mod;
    }

}
