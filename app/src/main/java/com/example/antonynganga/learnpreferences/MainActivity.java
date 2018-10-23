package com.example.antonynganga.learnpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etProfession;
    private TextView txvName, txvProfession;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etProfession = findViewById(R.id.etProfession);

        txvName = findViewById(R.id.txvName);
        txvProfession = findViewById(R.id.txvProfession);

        pageLayout = findViewById(R.id.pageLayout);

        pageColorSwitch = findViewById(R.id.pageColorSwitch);
        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setPageColor(isChecked);
            }
        });

        // Load Data from Activity Level SharedPrefs
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean(Constants.KEY_PAGE_COLOR, false);
        pageColorSwitch.setChecked(isChecked);
    }

    private void setPageColor(boolean isChecked) { // Save data to Activity Level SharedPrefs

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_PAGE_COLOR, isChecked);
        editor.apply(); // editor.commit();

        pageLayout.setBackgroundColor(isChecked? Color.GREEN : Color.WHITE);
    }

    public void saveAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.KEY_NAME, etName.getText().toString());
        editor.putString(Constants.KEY_PROFESSION, etProfession.getText().toString());
        editor.putInt(Constants.KEY_PROF_ID, 287);

        editor.apply();
    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME,Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A");
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A");
        int prof_id = sharedPreferences.getInt(Constants.KEY_PROF_ID, 0);

        txvName.setText(name);
        String profStr = profession + " - " + prof_id;
        txvProfession.setText(profStr);
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void openThirdActivity(View view) {
        Intent intent = new Intent(this, ThirdActicity.class);
        startActivity(intent);
    }
}
