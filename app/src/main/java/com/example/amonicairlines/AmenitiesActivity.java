package com.example.amonicairlines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AmenitiesActivity extends AppCompatActivity {
     private ListView lvService;
     ServiceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities);
        List<Service> services = null;
        try{
            XmlPullParserHandler parser = new XmlPullParserHandler();
            InputStream is = getAssets().open("data.xml");
            services = parser.parse(is);
            lvService = (ListView) findViewById(R.id.listview_services);
            //adapter
            adapter = new ServiceAdapter(this,R.layout.row_service,services){
                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);
                    if (position % 2 == 1){
                        view.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    }
                    return view;
                }
            };
            lvService.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackClick(View view) {
        super.onBackPressed();
    }
}