package com.study.yang.appinternalchangelanguagedemo;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    protected int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeAppLanguage();
    }

    public void changeAppLanguage() {
        String sta = LanguageStore.getLanguageLocal(this);
        if (sta != null && !"".equals(sta)) {
            Locale myLocale = new Locale(sta);
            switch (sta) {
                case "default"://跟随系统
                    myLocale = Locale.getDefault();
                    currentPosition = 0;
                    break;
                case "zh_CN":
                    myLocale = Locale.SIMPLIFIED_CHINESE;
                    currentPosition = 1;
                    break;
                case "zh_TW": //自定义语言
                    myLocale = new Locale("zh", "TW");
                    currentPosition = 2;
                    break;
                case "en":
                    myLocale = Locale.ENGLISH;
                    currentPosition = 3;
                    break;
                case "zh_HK":
                    myLocale = new Locale("zh", "HK");
                    currentPosition = 4;
                    break;
            }
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            //更新配置
            res.updateConfiguration(conf, dm);
        }

    }
}
