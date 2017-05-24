package com.android.kwave.adapterbasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Grid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        // 1. 데이터
//        ArrayList<Data> datas = new ArrayList<>();
//        datas = Loader.getData();
        ArrayList<Data> datas = Loader.getData();
        // 2. 어댑터
        GridAdapter adapter = new GridAdapter(datas,this);

        // 3. 연결
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}


// 역할 : Presenter
// M(데이터를 가져오고) -> C(가공을 하고) -> V(화면에 뿌려준다.)
// view에서는 사용자가 하는 반응은 입력으로 한다.
class GridAdapter extends BaseAdapter { // BaseAdapter는 Adapter 의 기본이 되는 기능이 정의되어 있다.
    ArrayList<Data> datas;
    Context context;
    LayoutInflater inflater;

    public GridAdapter(ArrayList<Data> datas, Context context) {
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
            // convertView가 사용되는 이유는 재사용성때문이다.
            // convertView와 holder의 수는 같이 만들어지며 화면상에서 convertView가 사라지면
            // 아예 없어지는 것이 아니라 아래로 내려가서 재사용된다.
            // 그래서 convertView가 Null이 되는 경우는 이 기능이 처음 실행이 될 때뿐이며
            // 이후에는 이미 값이 들어가 있기 때문에 이 곳을 사용하지 않는다.
            holder = new Holder();
            convertView = inflater.inflate(R.layout.item_custom_grid, null);
            // 뒤에 null은 거의 항상 null값이 입력되는데 Expandable list를 사용할 때만 사용한다.

            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.title = (TextView) convertView.findViewById(R.id.textView6);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        // 매줄에 해당되는 데이터를 꺼낸다.
        Data data = datas.get(position);

        // 이미지 세팅하기
        // 1. 이미지 suffix 만들기
        int suffix = (data.getId()%9)+1;        // % 뒤에 있는 숫자는 가져올 사진의 수
        // 2, 문자열로 리소스 아이디 가져오기
        int id = context.getResources().getIdentifier("girl"+ suffix, "mipmap",context.getPackageName());


         // 3. 리소스 아이디를 이미지뷰에 세팅하기
        holder.image.setImageResource(id);
                                          // 위젯 아이디   리소스 패키지

        holder.title.setText(data.getTitle());


        return convertView;
    }

    // Holder클래스는 itemlayout에 있는 위젯을 정의
    class Holder {
        private ImageView image;
        private TextView title;
    }

}
