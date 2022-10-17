package com.example.mts_app;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

class SwipeLisener implements View.OnTouchListener  {

    GestureDetector gs;


    public SwipeLisener(View view, MainActivity mainActivity) {



        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xdiff = e1.getX() - e2.getX();
                float ydiff = e1.getY() - e2.getY();

                try {
                    if(Math.abs(xdiff)<Math.abs(ydiff) || Math.abs(xdiff)< mainActivity.dm.xdpi)//uslovi kad nece
                        return true;
                    if(xdiff > 0)
                    { //swipe treba da bude barem inch po x
                        //desno
                        if(mainActivity.frag < mainActivity.buttons.length)
                        {
                            mainActivity.frag++;
                            mainActivity.buttons[mainActivity.frag].setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[mainActivity.frag],false,true);
                        }
                    }
                    else if(xdiff < 0) { //swipe treba da bude barem inch po x
                        //levo
                        if(mainActivity.frag>0)
                        {
                            mainActivity.frag--;
                            mainActivity.buttons[mainActivity.frag].setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[mainActivity.frag],true,false);
                        }
                    }
                    return true;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        };
        gs = new GestureDetector(mainActivity.getBaseContext(), listener);
        view.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gs.onTouchEvent(motionEvent);

    }
}