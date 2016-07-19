package com.example.administrator.fgroup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/7/15.
 */
public class CommentAdapter extends BaseAdapter {
    private List<String> talk_text;
    private TextView tv ;
    LayoutInflater inflater;
    public CommentAdapter(List<String>talk_text){
        this.talk_text =talk_text;
    }
    @Override
    public int getCount() {
        return talk_text.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =inflater.inflate(R.layout.commentlistview,null) ;
        Log.i("cad","设置了评论列表");
        Log.i("cad","评论列表:"+talk_text.get(i));
        tv = (TextView) view.findViewById(R.id.talk_text);
        tv.setText(talk_text.get(i));
        return view;
    }
}
