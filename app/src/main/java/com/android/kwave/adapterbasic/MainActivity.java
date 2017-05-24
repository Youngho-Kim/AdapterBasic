package com.android.kwave.adapterbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;    // 스피너 객체 만들기
    String datas[] = {"선택하세요", "ListView","CustomList", "GridView", "RecycleView"};

    final int LIST = 1;
    final int CUSTOMLIST = 2;
    final int GRID = 3;
    final int RECYCLE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        // ListView에 데이터 만들기
        // 1. 데이터 정의
        // datas 정의
        // 2. 어탭터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        // 3. 뷰에 어댑터 연결
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("spinner", datas[position]);
                Intent intent;
                switch (position) {
                    case LIST:
                        intent = new Intent(MainActivity.this, list.class);
                        startActivity(intent);
                        break;
                    case GRID:
                        intent = new Intent(MainActivity.this, Grid.class);
                        startActivity(intent);
                        break;
                    case RECYCLE:
                        intent = new Intent(MainActivity.this, Recycle.class);
                        startActivity(intent);
                        break;
                    case CUSTOMLIST :
                        intent = new Intent(MainActivity.this, CustomList.class);
                        startActivity(intent);
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
