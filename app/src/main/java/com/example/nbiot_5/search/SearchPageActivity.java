package com.example.nbiot_5.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nbiot_5.R;
import com.example.nbiot_5.tables.DeviceSheetActivity;

public class SearchPageActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText msearchpage_edtxt;
    private TextView msearchpage_search;
    private ImageView msearchpage_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        initView();
    }

    private void initView(){
        msearchpage_edtxt = findViewById(R.id.searchpage_edtxt);
        msearchpage_search = findViewById(R.id.searchpage_search);
        msearchpage_return = (ImageView)findViewById(R.id.searchpage_return);
        msearchpage_search.setOnClickListener(this);
        msearchpage_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.searchpage_search:
                String modelNumber = msearchpage_edtxt.getText().toString();
                Log.d("haha", modelNumber);
                Intent intent = new Intent(this, DeviceSheetActivity.class);
                startActivity(intent);
                break;
            case R.id.searchpage_return:
                finish();
                Log.d("haha", "退出");//仍然能在控制台打印出这句
                break;

        }

    }
}
