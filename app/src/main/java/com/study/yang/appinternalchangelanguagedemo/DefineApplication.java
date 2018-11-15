package com.study.yang.appinternalchangelanguagedemo;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

public class DefineApplication extends Application {
    protected int currentPosition = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        //没有用
        registerActivityLifecycleCallbacks(callback);
    }

    ActivityLifecycleCallbacks callback = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            changeAppLanguage();
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            unregisterActivityLifecycleCallbacks(callback);
        }
    };

    public void changeAppLanguage() {
        String sta = LanguageStore.getLanguageLocal(this);
        if (sta != null && !"".equals(sta)) {
            Locale myLocale = new Locale(sta);
            switch (sta) {
                case "default":
                    myLocale = Locale.getDefault();
                    currentPosition = 0;
                    break;
                case "zh_CN":
                    myLocale = Locale.SIMPLIFIED_CHINESE;
                    currentPosition = 1;
                    break;
                case "zh_TW":
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
            res.updateConfiguration(conf, dm);
        }

    }
}
