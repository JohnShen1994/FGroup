package com.example.administrator.fgroup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/7/17.
 */
public class InputActivity extends AppCompatActivity{
    Toolbar toolbar;
    Button send;
    EditText et;
    ListAdapter la;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_input);
        toolbar = (Toolbar) this.findViewById(R.id.input_toolbar);
        send = (Button) this.findViewById(R.id.sendmes_but);
        et = (EditText) this.findViewById(R.id.et);
        toolbar.setNavigationIcon(R.drawable.back);
        la = new ListAdapter();
        addListener();

    }
    void addListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test", "yes");
                Intent it = new Intent();Intent intent = new Intent();
                intent.putExtra("change",false);
                InputActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = et.getText().toString();
                String str2 = str.replace(" ", "");
                if (str2.length()>0) {
                    Log.i("test","str:"+str);
                    MainActivity.name.add(0, "林世杰");
                    MainActivity.text.add(0, str);
                    MainActivity.headpic.add(0, R.drawable.touxiang);
                    MainActivity.pho.add(0, null);
                    MainActivity.share.add(0,null);
                    MainActivity.haveMusic.add(0,false);
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.con);
                    View v = inflater.inflate(R.layout.activity_main, null);
                    ListView list = (ListView) v.findViewById(R.id.listview);
                    Intent intent = new Intent();
                    intent.putExtra("change",true);
                    InputActivity.this.setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(InputActivity.this,"不能发送空白的朋友圈",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.i("test","houtui");
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
