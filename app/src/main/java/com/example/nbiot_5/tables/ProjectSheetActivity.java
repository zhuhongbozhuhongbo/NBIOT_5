package com.example.nbiot_5.tables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nbiot_5.R;

/**
 * Tables包存储表格加载相关活动
 */
public class ProjectSheetActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mdevice_button_0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_sheet);
        init();
    }

    private void init(){
        mdevice_button_0 = (Button)findViewById(R.id.device_button_0);
        mdevice_button_0.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.device_button_0:
                Intent intent = new Intent(this, DeviceSheetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
