package com.yangyuning.maoyan.movie.area;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.maoyan.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AreaActivity extends AppCompatActivity {
    private SideBar sideBar;
    private TextView textView;
    private ListView listView;

    private String[] nameStr = { "张山", "王丽", "河北", "赵信", "天气", "情侣", "恩爱", "x",
            "fe", "挨饿", "偶见", "希望", "而青春", "HTC", "必定", "热", "r3", "R*&",
            "1323", "%**" };
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<String> initialList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        sideBar = (SideBar) findViewById(R.id.sideBar);
        textView = (TextView) findViewById(R.id.textview);
        listView = (ListView) findViewById(R.id.listview);
        sideBar.setShowChooseText(textView);
        ArrayList<String> strList = new ArrayList<String>();
        for (String str : sideBar.b) {
            strList.add(str);

        }
        for (String str : nameStr) {
            String pinyin = PinYinUtils.convertWordGroup(str);
            char initial = pinyin.toUpperCase().charAt(0);
            if (!initialList.contains(String.valueOf(initial))) {
                Item item = new Item();
                item.setContent(String.valueOf(initial));
                item.setTitle(true);
                item.setPinyin(String.valueOf(initial).toLowerCase());
                itemList.add(item);
                initialList.add(String.valueOf(initial));
            }
            Item item = new Item();
            item.setContent(str);
            item.setTitle(false);
            item.setPinyin(pinyin.toLowerCase());
            itemList.add(item);
        }

        Collections.sort(itemList, new Comparator<Item>() {

            @Override
            public int compare(Item lhs, Item rhs) {
                // TODO Auto-generated method stub
                if (lhs.getPinyin().equals("#")) {
                    return -1;
                }
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
        listView.setAdapter(new ItemAdapter(itemList, this));
        sideBar.setOnTouchingChangedListener(new SideBar.OnTouchingChangedListener() {

            @Override
            public void onTouchingChanged(String s) {
                // TODO Auto-generated method stub
                Item item = new Item();
                item.setContent(s);
                item.setTitle(true);
                item.setPinyin(s.toLowerCase());
                for (int i = 0; i < itemList.size(); i++) {
                    if(itemList.get(i).getContent().equals(s)){
                        listView.setSelection(i);
                        return;
                    }
                }
//				Log.i("Log", itemList.indexOf(item)+"----"+s.toLowerCase());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(AreaActivity.this, itemList.get(position).getContent(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
