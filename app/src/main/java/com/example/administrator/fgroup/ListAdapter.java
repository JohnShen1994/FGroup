package com.example.administrator.fgroup;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ListAdapter extends BaseAdapter {
    Context con;

    //public ImageView headImage_iv;
    //public ImageView pho_one;
    //public TextView name_tv;
    //public TextView text_tv;
    //public Button but_mes;
    //public GirdViews gv;
    //public View likelist;
    //public PopupWindow mPop;
    //public CommentListView commentlv;
    int clickposition;
    public  List<Boolean> whichlike =null ;
    private List<List<String>>talk_text ;
    private List<String> name ;
    private List<String> sharelist ;
    private List<Boolean> haveMusic ;
    private List<String> text ;
    private List<Integer> headpic ;
    private List<List<Integer>> pho ;
    List<Integer> pho_e ;
    String urltitle;

    View comment_choice;
    //View views ;
    LinearLayout web;
    View v;
    LayoutInflater inflater;
    ViewHolder viewHolder;
    Handler handler;
    ImageButton music_but;
    Boolean music_flag=false;
    Map<Integer,String> map;

    public ListAdapter(){

    }
    public ListAdapter(List<Boolean>haveMusic,List<String>share,List<Boolean>whichlike,List<List<String>>talk_text,List<Integer>headpic,List<String>text,List<List<Integer>>pho,Context con){
        this.whichlike = whichlike;
        this.haveMusic = haveMusic;
        this.name=MainActivity.name;
        this.headpic=MainActivity.headpic;
        this.text = MainActivity.text;
        this.talk_text = talk_text;
        sharelist= share;
        this.pho=pho;
        this.con=con;
        inflater = LayoutInflater.from(con);
    }
    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
          if(view==null){
              view = inflater.inflate(R.layout.listview_item,null);
              viewHolder = new ViewHolder();
              view.setTag(viewHolder);
              viewHolder.views=inflater.inflate(R.layout.pwindow, null);
              viewHolder.web= view.findViewById(R.id.webview);
              viewHolder.mainview = inflater.inflate(R.layout.activity_main,null);
              viewHolder. headImage_iv = (ImageView) view.findViewById(R.id.headImg);
              viewHolder.name_tv = (TextView) view.findViewById(R.id.lv_name);
              viewHolder.but_mes = (Button) view.findViewById(R.id.pinglun);
              viewHolder.share = (TextView) view.findViewById(R.id.share);
              viewHolder.text_tv = (TextView) view.findViewById(R.id.lv_text);
              viewHolder.pho_one = (ImageView) view.findViewById(R.id.photo);
              viewHolder.gv = (GirdViews) view.findViewById(R.id.girdview);
              viewHolder.music_but = (ImageButton) view.findViewById(R.id.music_but);
              viewHolder.like_choice = viewHolder.views.findViewById(R.id.like_ll);
              viewHolder.music = view.findViewById(R.id.music);
              viewHolder.likelist = view.findViewById(R.id.like_layout);
              for(int a=0;a<MainActivity.name.size();a++){
                  whichlike.add(false);
              }

          }else{
              viewHolder = (ViewHolder)view.getTag();
          }
        viewHolder.but_mes.setOnClickListener(new likeListener(viewHolder.but_mes,i));
        if(i<name.size()){
            viewHolder.name_tv.setText(MainActivity.name.get(i));
            viewHolder.text_tv.setText(MainActivity.text.get(i));
            viewHolder.headImage_iv.setImageResource(MainActivity.headpic.get(i));
        }
        viewHolder.but_mes.setOnClickListener(new likeListener(viewHolder.but_mes,i));
        for(int a=0;a<MainActivity.name.size();a++){
            whichlike.add(false);
        }
            if(haveMusic.get(i)){
                viewHolder.music.setVisibility(View.VISIBLE);
                viewHolder.music_but.setOnClickListener(new MusicListener());
            }else{
                viewHolder.music.setVisibility(View.GONE);
            }
            if(sharelist.get(i)!=null){
                if(map!=null&&map.get(i)!=null){
                    viewHolder.web.setVisibility(View.VISIBLE);
                    viewHolder.share.setText(map.get(i));
                }else{
                    new getTitle(i).start();
                    handler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if(msg.what==1){
                                Bundle b= msg.getData();
                                //int position = (int) b.get("position");
                                ListAdapter.this.notifyDataSetChanged();
                            }
                        }
                    };
                }
                viewHolder.web.setOnClickListener(new ShareListener(i));
            }else{
                viewHolder.web.setVisibility(View.GONE);
            }
        if(i<MainActivity.pho.size()){
            if(MainActivity.pho.get(i)!=null&&MainActivity.name.get(i)!="林世杰") {           // setAdapter 去显示girdview
                pho_e = MainActivity.pho.get(i);
                if (pho_e.size() == 1) {
                    viewHolder.pho_one.setVisibility(View.VISIBLE);
                    viewHolder.pho_one.setImageResource(pho_e.get(0));
                    viewHolder.pho_one.setOnClickListener(new OnePhotoClickListener(pho_e));
                } else {
                    viewHolder.gv.setVisibility(View.VISIBLE);
                    viewHolder.gv.setAdapter(new GirdViewAdapter(pho_e, con));
                    viewHolder.gv.setOnItemClickListener(new PhotoClickListener(pho_e));
                }
            }else{
                viewHolder.gv.setVisibility(View.GONE);
                viewHolder.pho_one.setVisibility(View.GONE);
            }
        }else{
            viewHolder.gv.setVisibility(View.GONE);
            viewHolder.pho_one.setVisibility(View.GONE);
        }


        if(whichlike.get(i)!=null){
            if(whichlike.get(i)){
                viewHolder.likelist.setVisibility(View.VISIBLE);
            }else{
                viewHolder.likelist.setVisibility(View.GONE);
            }
        }else{
        }

        return view;
    }
    class likeListener implements OnClickListener{                          // 点赞
        TextView liketext ;
        Button mes ;
        int click;
        View like_view;
        View input_view;
        public likeListener(Button mes,int positions){this.mes =mes;
            click= positions;}
        @Override
        public void onClick(View view) {
            clickposition =click;
            View contentView = LayoutInflater.from(con).inflate(R.layout.pwindow, null);
            liketext = (TextView) contentView.findViewById(R.id.like_tt);
            like_view = contentView.findViewById(R.id.like_ll);
            input_view = contentView.findViewById(R.id.comment_layout);

            like_view.setOnClickListener(new likeListeners(contentView,clickposition));
            int meswidth =mes.getWidth();
            int mesheight=mes.getHeight();
            if(viewHolder.mPop==null){
                int width = meswidth*4;
                int height= (int) (mesheight*1.5);
                boolean focusable = true;
                viewHolder.mPop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,height, focusable);
                viewHolder.mPop.setBackgroundDrawable(new ColorDrawable());
            }
            int x = -meswidth*7-20;
            int y = -mesheight-10;
            viewHolder.mPop.showAsDropDown(mes, x, y);
        }
    }

    class likeListeners implements OnClickListener{
        int i = 0;
        View v ;
        public likeListeners(View v ,int position ){
            this.v = v;
            i = position;
        }
        @Override
        public void onClick(View view) {
           // i=getPosition();
            if (!whichlike.get(clickposition)) {
                whichlike.set(clickposition, true);
            }else if(whichlike.get(clickposition)){
                whichlike.set(clickposition, false);
            }
            ListAdapter.this.notifyDataSetChanged();
            viewHolder.mPop.dismiss();

        }
    }
    class PhotoClickListener implements AdapterView.OnItemClickListener{
        List<Integer> photo;
        Intent it ;
        public PhotoClickListener(List<Integer>photo){
            this.photo = photo;
            it = new Intent(con,CheckPhoto.class);
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            it.putExtra("photo", photo.get(position));
            con.startActivity(it);
        }
    }
    class OnePhotoClickListener implements OnClickListener{
        List<Integer> photo;
        Intent it ;
        public OnePhotoClickListener(List<Integer>photo){
            this.photo = photo;
            it = new Intent(con,CheckPhoto.class);}
        @Override
        public void onClick(View view) {
            it.putExtra("photo", photo.get(0));
            con.startActivity(it);
        }
    }
    class getTitle extends Thread{
        int i ;
        public getTitle(int i ){
            this.i = i;
        }
        @Override
        public void run() {
            super.run();
            try {
                Document doc = Jsoup.connect(sharelist.get(i)).get();
                urltitle = doc.head().getElementsByTag("title").text();
                map = new HashMap<Integer,String>();
                map.put(i,urltitle);
                Message message=new Message();
                message.what=1;
                Bundle b = new Bundle();
                b.putInt("position",i);
                message.setData(b);
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class ShareListener implements OnClickListener{
        int i ;
        public ShareListener(int i){
            this.i = i;
            Log.i("click","bbbb");
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(sharelist.get(i)));
            Log.i("click","aaa");
            con.startActivity(intent);
        }
    }
    class MusicListener implements OnClickListener{
        MediaPlayer mp ;
        @Override
        public void onClick(View view) {
            mp = MediaPlayer.create(con, R.raw.dayu);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
            if(music_flag){
                mp.pause();
                Log.i("music", "pause");
                viewHolder.music_but.setImageResource(R.drawable.pause);
                music_flag = !music_flag;
            }else{
                mp.start();
                Log.i("music", "start");
                viewHolder.music_but.setImageResource(R.drawable.play);

            music_flag = !music_flag;
        }
        }
    }
    static class ViewHolder
    {
        public View music;
        public ImageButton music_but;
        public View web;
        public ImageView headImage_iv;
        public ImageView pho_one;
        public TextView name_tv;
        public TextView text_tv;
        public TextView like_tv;
        public Button but_mes;
        public GirdViews gv;
        public View likelist;
        public PopupWindow mPop;
        public CommentListView commentlv;
        public TextView share;
        public View mainview ;
        public View like_choice ;
        public View views;
    }
}
