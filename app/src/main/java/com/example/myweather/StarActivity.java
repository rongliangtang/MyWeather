package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myweather.db.City;
import com.example.myweather.db.County;
import com.example.myweather.db.Star;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class StarActivity extends AppCompatActivity {

    private ListView star_list;

    //展示列表
    private List<String> dataList = new ArrayList<>();

    //关注列表
    private List<Star> starList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        star_list = (ListView)findViewById(R.id.star_list);


        queryStarList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList);
        star_list.setAdapter(adapter);
        star_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StarActivity.this,WeatherActivity.class);
                String weatherId = starList.get(position).getAdcode();
                intent.putExtra("weather_id",weatherId);
                startActivity(intent);
                finish();
            }
        });

    }

    //从数据库查询star列表
    private void queryStarList(){
        starList = LitePal.findAll(Star.class);
        for (Star star:starList){
            dataList.add(star.getName());
        }
    }


}