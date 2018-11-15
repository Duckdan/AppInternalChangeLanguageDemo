package com.study.yang.appinternalchangelanguagedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChangeLanguageActivity extends BaseActivity implements View.OnClickListener {

    private ArrayAdapter<String> arrayAdapter;
    private String[] languageCode = {"default", "zh_CN", "zh_TW", "en", "zh_HK"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        final String[] languageType = {getString(R.string.first_language), getString(R.string.second_language), getString(R.string.third_language), getString(R.string.fifth_language), getString(R.string.fifth_language)};
        TextView tvBack = (TextView) findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        TextView tvSave = (TextView) findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
        ListView lvLanguage = (ListView) findViewById(R.id.lv_language);


        arrayAdapter = new ArrayAdapter<String>(this, R.layout.language_list, languageType) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(ChangeLanguageActivity.this, R.layout.language_list, null);
                }
                TextView tvFellow = (TextView) convertView.findViewById(R.id.tv_fellow);
                tvFellow.setText(languageType[position]);
                CheckBox cb = convertView.findViewById(R.id.cb);
                cb.setChecked(currentPosition == position ? true : false);
                return convertView;
            }
        };
        lvLanguage.setAdapter(arrayAdapter);

        lvLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
                cb.setChecked(!cb.isChecked());
                currentPosition = position;
                arrayAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.tv_save:
                //存储当前语言代码
                LanguageStore.setLanguageLocal(ChangeLanguageActivity.this, languageCode[currentPosition]);
                //重启页面
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // 杀掉进程
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;
        }
    }
}
