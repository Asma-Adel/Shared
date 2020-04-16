package com.example.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     TextView textView;
     EditText editText;
     Button applyBtn , saveBtn;
     Switch switch1;
     String text;
     Boolean onOff;
     public static final String SHARED_PREF ="sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_view);
        editText = findViewById(R.id.ed_txt);
        applyBtn = findViewById(R.id.apply_btn);
        saveBtn = findViewById(R.id.save_btn);
        switch1 = findViewById(R.id.switch1);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        loadData();
        updateView();
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT , textView.getText().toString());
        editor.putBoolean(SWITCH1 , switch1.isChecked());

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF , MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT , "");
        onOff = sharedPreferences.getBoolean(SWITCH1 , false);
    }

    public void updateView(){
        textView.setText(text);
        switch1.setChecked(onOff);
    }
}
