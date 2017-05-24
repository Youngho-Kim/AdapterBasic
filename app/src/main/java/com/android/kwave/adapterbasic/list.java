package com.android.kwave.adapterbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.android.kwave.adapterbasic.Detail.DATA_KEY;

public class list extends AppCompatActivity {

    // 리스트 뷰 객체 만들기
        ListView listView;
    // List형식의 데이터 원본을 만들기 위해 ArrayList 생성
    ArrayList<String> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // List뷰를 연결
        listView = (ListView) findViewById(R.id.listView);

        // 1.  데이터
        for(int i=0;i<50;i++){
            datas.add("데이터 "+i);
        }
             // String 값을 가진 데이터를 넣어준다.  계산기를 했을 때는 미리 String[]이 있었기에
             // 그것을 넣어주면 됐지만 이번처럼 넣어줄 데이터가 미리 준비되지 않은 경우 ArrayList의
             // 타입에 맞춰서 데이터를 입력해준다.


        // 2.  어탭터
        // 데이터에 맞는 어댑터를 생성한다.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datas);
        // simple_list_item_1 은 하나의 텍스트 뷰로 구성된 레이아웃.

        // 3, 뷰 > 연결 > 어댑터
        listView.setAdapter(adapter);

        // 리스트뷰에 기능 넣기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Activity 에 값 전달하기

                // 1. 전달 받을 목적지 Intent 생성
                Intent intent = new Intent(list.this, Detail.class);

                // 2. PutExtra 로 값 입력
                intent.putExtra(DATA_KEY,datas.get(position));
                // 리스트뷰는 키 값으로 데이터를 찾는다.
                // 순서별로 데이터를 입력해준다.

                // 3. intent를 이용한 Activity 생성 요청
                startActivity(intent);
            }
        });
    }
}


































//
//public class list extends AppCompatActivity {
//
//    ListView listView;      // 리스트 뷰 객체 만들기
//
//    // List형식의 데이터 원본을 만들기 위해 ArrayList 생성
//    ArrayList<String> datas = new ArrayList<>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//
//        // List뷰를 연결
//        listView = (ListView) findViewById(R.id.listView);
//
//        // 1.  데이터
//        for(int i=0;i<5;i++){
//            datas.add("데이터"+i);
//            // String 값을 가진 데이터를 넣어준다.  계산기를 했을 때는 미리 String[]이 있었기에
//            // 그것을 넣어주면 됐지만 이번처럼 넣어줄 데이터가 미리 준비되지 않은 경우 ArrayList의
//            // 타입에 맞춰서 데이터를 입력해준다.
//        }
//        // 2.  어탭터
//        // 데이터에 맞는 어댑터를 생성한다.
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datas);
//        // simple_list_item_1 은 하나의 텍스트 뷰로 구성된 레이아웃.
//
//        // 3, 뷰 > 연결 > 어댑터
//        listView.setAdapter(adapter);
//
//        // 리스트뷰에 기능 넣기
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Activity 에 값 전달하기
//
//                // 1. 전달 받을 목적지 Intent 생성
//                Intent intent = new Intent(list.this, Detail.class);
////                String result = datas.get(position);
//
//                // 2. PutExtra 로 값 입력
//                intent.putExtra(DATA_KEY,datas.get(position));
//                // 리스트뷰는 키 값으로 데이터를 찾는다.
//                // 순서별로 데이터를 입력해준다.
//
//                // 3. intent를 이용한 Activity 생성 요청
//                startActivity(intent);
//            }
//        });
//    }
//}