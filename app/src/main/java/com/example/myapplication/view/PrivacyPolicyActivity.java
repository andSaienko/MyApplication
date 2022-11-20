package com.example.myapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.view.MenuActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {
    CheckBox checkAgreed;
    boolean check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy_activity);

        checkAgreed = findViewById(R.id.checkBoxAgree);

        TextView privacyRules = findViewById(R.id.privacyRules);
        privacyRules.setMovementMethod(new ScrollingMovementMethod());

        check = getAnswer();
        if (check) {
            openMenuActivity();
        }

        Button buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(view -> agreeCheck(checkAgreed.isChecked()));
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void agreeCheck(boolean b) {
        if (b) {
            openMenuActivity();
            check = true;
        }
    }

    public void saveAnswer() {
        SharedPreferences sp = getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("SAVED_ANSWER", check);
        editor.apply();
    }

    public Boolean getAnswer() {
        SharedPreferences sp = getSharedPreferences("SP_PREFS", MODE_PRIVATE);
        return sp.getBoolean("SAVED_ANSWER", check);
    }

    protected void onPause() {
        saveAnswer();
        super.onPause();
    }
}
