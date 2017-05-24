package com.android.kwave.adapterbasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.android.kwave.adapterbasic.R.layout.activity_custom_list;

public class CustomList extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_custom_list);
        listView = (ListView) findViewById(R.id.listView1);

//        1. 데이터
        ArrayList<Data> datas = Loader.getData();

//        2. 어댑터
        CustomAdapter adapter = new CustomAdapter(datas, this);

//        3. 연결
        listView.setAdapter(adapter);
    }
}

class Loader {
    public static ArrayList<Data> getData() {
        ArrayList<Data> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Data data = new Data();
            data.setId(i + 1);
            data.setTitle(("타이틀 : " + i));
            result.add(data);
        }
        return result;
    }
}
// 역할 : Presenter
// M(데이터를 가져오고) -> C(가공을 하고) -> V(화면에 뿌려준다.)
// view에서는 사용자가 하는 반응은 입력으로 한다.
class CustomAdapter extends BaseAdapter { // BaseAdapter는 Adapter 의 기본이 되는 기능이 정의되어 있다.
    ArrayList<Data> datas;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<Data> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //xml을 class로 변환한다.
        // XML코드를 시스템에서 사용하기 위해서는 메모리에 올려야한다. => 객체로 만들어줘야함
        // 메모리에 올리는 방법은 클래스이어야함 + 이 클래스를 인스턴스로 만들어야함
        //  클래스를 만들면 클래스의 코드만 메모리에 올라가 있는 상태이므로 클래스를 인스턴스로 만들어야 객체로서 사용이 가능하다.
        // XNL을 클래스로 변환해주는 것 + 객체 생성까지 역할이 인플레이터
    }


    @Override
    public int getCount() {     // 사용하는 데이터의 총 개수를 리턴

        return datas.size();
    }

    @Override
    public Object getItem(int position) { // 데이터 클래스 하나를 리턴

        Log.e("Adapter", "getItem position =" +position);
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {   // 뷰의 아이디 값 리턴, // 대부분 인덱스가 그대로 리턴
        Log.e("Adapter", "getItem[Id] position =" +position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 아이템 뷰 하나를 리턴.
        Log.d("convertView", "convertView = " + convertView);

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.item_custom_list, null);
            // 뒤에 null은 거의 항상 null값이 입력되는데 Expandable list를 사용할 때만 사용한다.

            holder.no = (TextView) convertView.findViewById(R.id.txtNo);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        // 매줄에 해당되는 데이터를 꺼낸다.
        Data data = datas.get(position);

        holder.no.setText(data.getId() + "");
        holder.title.setText(data.getTitle());


        return convertView;
    }

    private class Holder {
        private TextView no;
        private TextView title;
    }
}


class Data {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}