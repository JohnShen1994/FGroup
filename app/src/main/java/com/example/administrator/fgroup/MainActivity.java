package com.example.administrator.fgroup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<List<String>>talk_text ;
    public static List<Boolean> haveMusic;
    public static List<String> name ;
    public static List<String> text ;
    public static List<Integer> headpic ;
    public static List<String> share ;
    public static List<List<Integer>> pho ;
    public static Context con;
    private Toolbar tb ;
    private ListView listview ;
    private View headview;
    private List<Boolean> whichlike =null ;
    ImageButton write;
    public static Boolean change = false;
    private List<Integer>pho_list_1;
    private List<Integer>pho_list_2;
    private List<Integer>pho_list_4;
    private List<Integer>pho_list_9;
    ListAdapter la ;
    long oldDown = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        con = MainActivity.this;
        init();
        setPho();
        setList();
        addListener();

    }
    void setPho(){
        pho_list_1 = new ArrayList<Integer>();
        pho_list_2 = new ArrayList<Integer>();
        pho_list_4 = new ArrayList<Integer>();
        pho_list_9 = new ArrayList<Integer>();
        pho_list_1.add(0,R.drawable.tx1);
        pho_list_2.add(0,R.drawable.tx1);pho_list_2.add(1,R.drawable.tx2);
        pho_list_4.add(0,R.drawable.tx1);pho_list_4.add(1,R.drawable.tx2);pho_list_4.add(2, R.drawable.tx3);pho_list_4.add(3, R.drawable.tx4);
        pho_list_9.add(0,R.drawable.tx1);pho_list_9.add(1, R.drawable.tx2);pho_list_9.add(2, R.drawable.tx3);
        pho_list_9.add(3,R.drawable.tx4);pho_list_9.add(4, R.drawable.tx5);pho_list_9.add(5, R.drawable.tx6);
        pho_list_9.add(6,R.drawable.tx7);pho_list_9.add(7, R.drawable.tx8);pho_list_9.add(8, R.drawable.tx9);
    }
    void setList(){
        name.add(0,"欧文");name.add(1,"库里");name.add(2,"科比");name.add(3,"艾弗森");name.add(4,"保罗");name.add(5,"周深");
        text.add(0,"我是欧文");text.add(1,"我是库里");text.add(2,"我是科比");text.add(3,"我是艾弗森");text.add(4,"我分享了一个网页");text.add(5,"大鱼海棠主题曲");
        headpic.add(0,R.drawable.tx1);headpic.add(1,R.drawable.tx2);headpic.add(2,R.drawable.tx3);headpic.add(3,R.drawable.tx4);headpic.add(4,R.drawable.tx1);headpic.add(5,R.drawable.tx1);
        pho.add(0,pho_list_1);pho.add(1, pho_list_2);pho.add(2, pho_list_4);pho.add(3,pho_list_9);
        share.add(0, null);share.add(1, null);share.add(2, null);share.add(3, null);
        share.add(4, "http://bbs.hupu.com/16790880.html");
        share.add(5, null);
        haveMusic.add(0,false);haveMusic.add(1,false);haveMusic.add(2,false);haveMusic.add(3,false);haveMusic.add(4,false);
        haveMusic.add(5,true);
    }
    void init(){
        haveMusic = new ArrayList<Boolean>();
        whichlike = new ArrayList<Boolean>();
        name = new ArrayList<String>();
        share = new ArrayList<String>();
        text = new ArrayList<String>();
        pho = new ArrayList<List<Integer>>();
        headpic = new ArrayList<Integer>();
        talk_text = new ArrayList<List<String>>();
        tb = (Toolbar) findViewById(R.id.toolbar);
        listview = (ListView) findViewById(R.id.listview);
        headview =getLayoutInflater().inflate(R.layout.headview,null);
        listview.addHeaderView(headview);
        la = new ListAdapter(haveMusic,share,whichlike,talk_text, headpic, text, pho, this);
        listview.setAdapter(la);
        write = (ImageButton) this.findViewById(R.id.write);
    }

    void addListener(){
        tb.setOnTouchListener(new View.OnTouchListener() {
            long nowDown;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        nowDown = System.currentTimeMillis();
                        if (nowDown - oldDown < 500) {     // 产生双击事件
                            listview.setSelection(0);
                        }
                }
                oldDown = nowDown;
                return false;
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(MainActivity.this,InputActivity.class);
                startActivityForResult(it,100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Boolean a = data.getBooleanExtra("change", false);
        if(a){
            whichlike.add(0,false);
            la.notifyDataSetChanged();
            listview.setSelection(0);

        }
    }

}
