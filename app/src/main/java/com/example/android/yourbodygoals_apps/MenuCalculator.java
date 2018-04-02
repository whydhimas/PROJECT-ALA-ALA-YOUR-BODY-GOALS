package com.example.android.yourbodygoals_apps;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuCalculator extends AppCompatActivity {
    EditText mHeightCalc;
    EditText mWeightCalc;
    EditText mAgeCalc;
    Button mCountCalc;
    TextView mResultNumCalc;
    TextView mResultCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_calculator);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.GreenBright)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GreenDark));
        }

        mHeightCalc = (EditText) findViewById(R.id.et_height_calc);
        mWeightCalc = (EditText) findViewById(R.id.et_weight_calc);
        mAgeCalc = (EditText) findViewById(R.id.et_age_calc);
        mCountCalc = (Button) findViewById(R.id.btn_count_calc);
        mResultNumCalc = (TextView) findViewById(R.id.tv_calc_result_number);
        mResultCalc = (TextView) findViewById(R.id.et_result_caption);

        mCountCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHeight = mHeightCalc.getText().toString();
                String strWeight = mWeightCalc.getText().toString();

                if (strHeight != null && !"".equals(strHeight) && strWeight != null  &&  !"".equals(strWeight)) {
                    float heightValue = Float.parseFloat(strHeight) / 100;
                    float weightValue = Float.parseFloat(strWeight);

                    float bmi = weightValue / (heightValue * heightValue);

                    displayBMI(bmi);
                }
            }
        });
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        mResultNumCalc.setText(Float.toString(bmi));
        mResultCalc.setText(bmiLabel);
    }

}
