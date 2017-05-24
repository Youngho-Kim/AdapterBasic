package com.android.kwave.adapterbasic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {

    TextView textView, back;            // 텍스트뷰 객체 생성
    // 리스트기 때문에 ArrayList로 데이터 생성
    ArrayList<String> datas = new ArrayList<>();

    public static final String DATA_KEY = "ListActivityData";       // 보내고 받을때 에러를 방지하기 위해 미리 상수화 시킴 "ListActivityData"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.textView5);     // 텍스트뷰 위젯과 텍스트뷰 변수와 연결
        back = (TextView) findViewById(R.id.back);               // 텍스트뷰 위젯과 텍스트뷰 변수와 연결
        // Activity에서 넘어온 값 처리하기
        //1. intent를 꺼낸다.
        Intent intent = getIntent();        // 인텐트 객체를 만들어 넘어온 인텐트를 꺼낸다.

        // 2. 값의 묶음인 bundle을 꺼낸다.
        Bundle bundle = intent.getExtras();     //
//        bundle.getString();  // 이름이 길면 에러가 날 수 있으므로 미리 상수화 해놓는다.
        String result ="";

        // 3. bundle이 있는지 유효성 검사를 한다.
        if(bundle != null){
            // 3-1. bundle이 있으면 값을 꺼내서 변수에 담는다.
            result = bundle.getString(DATA_KEY);
        }
             textView.setText(result);

            findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }
}
