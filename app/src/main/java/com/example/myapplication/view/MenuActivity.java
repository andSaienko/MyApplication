package com.example.myapplication.view;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.kotlin_to_java.TestKotlinClass;

public class MenuActivity extends AppCompatActivity {
    private Button btWeb, btGame, btRecycler;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        TestKotlinClass ktTest = new TestKotlinClass();
        Log.d(TAG, "kt test = " + ktTest.getStringFromClass());

        btWeb = findViewById(R.id.btWebView);
        btWeb.setOnClickListener(view -> openWebView());

        btGame = findViewById(R.id.btGame);
        btGame.setOnClickListener(view -> openWheelGame());

        btRecycler = findViewById(R.id.btRecycler);
        btRecycler.setOnClickListener(view -> openRecycler());

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void openWebView() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    public void openWheelGame() {
        Intent intent = new Intent(this, WheelGameActivity.class);
        startActivity(intent);
    }

    public void openRecycler() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }
}