package com.example.nbiot_5.tables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nbiot_5.R;
import com.example.nbiot_5.charts.ChartActivity;

/**
 * Tables包存储表格加载相关活动
 */
public class DeviceSheetActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mproject_button_0;
    private Button mproject_button_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_sheet);
        init();
    }

    private void init(){
        mproject_button_0 = findViewById(R.id.project_button_0);
        mproject_button_1 = findViewById(R.id.project_button_1);

        mproject_button_0.setOnClickListener(this);
        mproject_button_1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.project_button_0:
                Intent intent0 = new Intent(this, ProjectSheetActivity.class);
                startActivity(intent0);
                break;
            case R.id.project_button_1:
                Intent intent1 = new Intent(this, ChartActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
