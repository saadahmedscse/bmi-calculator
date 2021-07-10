package com.saaad.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kevalpatel2106.rulerpicker.RulerValuePicker;
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;

public class HomeActivity extends AppCompatActivity {

    private TextView gender_male_tv, gender_female_tv, gender;
    private ImageView gender_male_img, gender_female_img;
    private LinearLayout male, female;
    private Button calculate;
    private RulerValuePicker cm, kg;

    int Weight, Height;
    String Gender, BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gettingLayoutIDs();

        genderTask();
        pickValue();
        sendInformation();

        cm.selectValue(160);
        kg.selectValue(50);

    }

    private void calculateBMI(){
        double weight, height, meter, meterSquare, bmi;

        weight = (int) Weight;
        height = (int) Height;

        meter = height / 100;

        meterSquare = meter * meter;

        bmi = weight / meterSquare;

        String bmiValue = Double.toString(bmi);

        if (bmiValue.length()>2){
            BMI = bmiValue.substring(0, 5);
        }
        else {
            BMI = bmiValue;
        }
    }

    /**
     * ------------------------------------------------------PICK_VALUES_FROM_SCALE-------------------------------------------------
     */

    private void pickValue(){
        cm.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {
                Height = selectedValue;
            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {

            }
        });

        kg.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {
                Weight = selectedValue;
            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {

            }
        });
    }



    /**
     * ----------------------------------------------------------BUTTON_ACTIONS------------------------------------------------------
     */

    private void genderTask(){
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackgroundResource(R.drawable.dark_blue_background);
                gender_male_tv.setTextColor(getResources().getColor(R.color.colorWhite));
                gender_male_img.setImageResource(R.drawable.male_white);
                female.setBackgroundResource(R.drawable.light_violet_bg);
                gender_female_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gender_female_img.setImageResource(R.drawable.female);
                Gender = "Male";
                gender.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gender.setText("GENDER");
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackgroundResource(R.drawable.dark_blue_background);
                gender_female_tv.setTextColor(getResources().getColor(R.color.colorWhite));
                gender_female_img.setImageResource(R.drawable.female_white);
                male.setBackgroundResource(R.drawable.light_violet_bg);
                gender_male_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gender_male_img.setImageResource(R.drawable.male);
                Gender = "Female";
                gender.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gender.setText("GENDER");
            }
        });
    }

    private void sendInformation(){
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Gender == null){
                    gender.setTextColor(getResources().getColor(R.color.colorOrange));
                    gender.setText("DEFINE YOUR GENDER");
                }
                else {
                    calculateBMI();
                    Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
                    intent.putExtra("bmi", BMI);
                    intent.putExtra("gender", Gender);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        });
    }

    private void gettingLayoutIDs(){
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        gender = findViewById(R.id.gender_tv);

        gender_male_tv = findViewById(R.id.gender_male_tv);
        gender_female_tv = findViewById(R.id.gender_female_tv);
        gender_male_img = findViewById(R.id.gender_male_img);
        gender_female_img = findViewById(R.id.gender_female_img);

        cm = findViewById(R.id.centemeter);
        kg = findViewById(R.id.weight);

        calculate = findViewById(R.id.calculate);
    }
}
