package com.example.administrator.fgroup;

import android.app.ActionBar;
import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class GirdViewAdapter extends BaseAdapter {
    private List<Integer> pho;
    private Context con;
    private LayoutInflater  inflater;
    public GirdViewAdapter(List<Integer> pho,Context con){
        this.pho = pho;
        this.con = con;
        inflater = LayoutInflater.from(con);
    }
    @Override
    public int getCount() {
        return pho.size();

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
        ImageView iv;
        view = inflater.inflate(R.layout.girdviewitem,null);
        iv = (ImageView) view.findViewById(R.id.girdview_pho);
        iv.setImageResource(pho.get(i));
        return view;
    }
}
