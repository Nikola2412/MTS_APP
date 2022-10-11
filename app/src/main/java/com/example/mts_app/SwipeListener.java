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
                DisplayMetrics dm = new DisplayMetrics();
                mainActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

                try {
                    if(xdiff > dm.xdpi) { //swipe treba da bude barem inch po x
                        //desno
                        if (mainActivity.frag == 2) {

                            mainActivity.btn_mojprofil.setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[2], true, true);
                            mainActivity.frag = 3;
                        }
                        else if(mainActivity.frag==1)
                        {
                            mainActivity.btn_angazujme.setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[1],false,true);
                            mainActivity.frag = 2;
                        }
                    }
                    else if(xdiff< - dm.xdpi) { //swipe treba da bude barem inch po x
                        //levo
                        if(mainActivity.frag==2)
                        {
                            mainActivity.btn_pretragaljudi.setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[0],true,false);
                            mainActivity.frag=1;
                        }
                        else if(mainActivity.frag ==3)
                        {
                            mainActivity.btn_angazujme.setChecked(true);
                            mainActivity.replaceFragment(mainActivity.frags[1],true,false);
                            mainActivity.frag=2;
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