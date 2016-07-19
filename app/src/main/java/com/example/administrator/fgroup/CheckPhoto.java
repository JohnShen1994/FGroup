package com.example.administrator.fgroup;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CheckPhoto extends Activity implements View.OnTouchListener {
    private ImageView iv;
    private GestureDetector ges;
    Boolean isBig =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkphoto);
        iv = (ImageView) findViewById(R.id.checkphoto);

        ges = new GestureDetector(new gestureListener());
        ges.setOnDoubleTapListener(new doubleClick());
        Intent it = this.getIntent();
        int a = it.getIntExtra("photo",0);
        iv.setOnTouchListener(this);
        if(a!=0){
            iv.setImageResource(a);
        }

    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return ges.onTouchEvent(event);
    }
    private class gestureListener implements GestureDetector.OnGestureListener{
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            finish();
            return false;
        }
        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }
        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }
    private class doubleClick implements GestureDetector.OnDoubleTapListener{
        public doubleClick(){
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if(isBig){
                Matrix m = new Matrix();
                m.postScale(0.5f,0.5f);
                iv.setImageMatrix(m);
            }
            finish();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            isBig =!isBig;
            if(isBig){
                iv.setScaleType(ImageView.ScaleType.MATRIX);
                Matrix m = new Matrix();
                m.postScale(0.5f, 0.5f);
                iv.setImageMatrix(m);
            }else{
                Matrix m = new Matrix();
                m.postScale(2.0f, 2.0f);
                iv.setImageMatrix(m);

            }
            return true;
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return true;
        }
    }
}
