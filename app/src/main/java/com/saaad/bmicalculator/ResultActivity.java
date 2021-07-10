package com.saaad.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private TextView BMI_TV;
    String BMI, GENDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        BMI_TV = findViewById(R.id.bmi_tv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BMI_TV.setText(BMI);
            }
        }, 200);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        BMI = i.getStringExtra("bmi");
        GENDER = i.getStringExtra("gender");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Back();
    }

    private void Back(){
        Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}
