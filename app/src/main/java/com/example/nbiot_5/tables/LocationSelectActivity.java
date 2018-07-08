package com.example.nbiot_5.tables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.nbiot_5.R;

import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import mlxy.utils.T;

public class LocationSelectActivity extends AppCompatActivity implements OnAddressSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_select);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.project_select_framelayout);

        AddressSelector selector = new AddressSelector(this);
        selector.setOnAddressSelectedListener(this);
//        selector.setAddressProvider(new TestAddressProvider());

        assert frameLayout != null;
        frameLayout.addView(selector.getView());

    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s =
                (province == null ? "" : province.name) + "#" +
                        (city == null ? "" :city.name) +
                        (county == null ? "" :county.name) +
                        (street == null ? "" :street.name);
        /*      String s =
                (province == null ? "" : province.name) +
                        (city == null ? "" : "\n" + city.name) +
                        (county == null ? "" : "\n" + county.name) +
                        (street == null ? "" : "\n" + street.name);*/

        //T.showShort(this, s);
        Intent intent = new Intent(LocationSelectActivity.this, ProjectListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("location", s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
