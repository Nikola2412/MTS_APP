package com.example.mts_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    //privremeno
    //TextView tx;
    SwipeLisener sl;
    BottomNavigationView menu;
    MenuItem btn_mojprofil, btn_angazujme, btn_pretragaljudi;
    Fragment angazujMe,pretragaLjudi,mojProfil;
    int frag=2; //id trenutnog fragmenta
    // id od 1 do 3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (BottomNavigationView) findViewById(R.id.bottom_nav);
        relativeLayout = findViewById(R.id.main);
        //tx = findViewById(R.id.test);
        sl = new SwipeLisener(relativeLayout);
        //Menu dugmad

        btn_angazujme = menu.getMenu().getItem(1);
        btn_pretragaljudi = menu.getMenu().getItem(0);
        btn_mojprofil = menu.getMenu().getItem(2);

        angazujMe = new AngazujMe();
        pretragaLjudi = new PretragaLjudi();
        mojProfil = new MojProfil();
        btn_angazujme.setChecked(true);
        replaceFragment(angazujMe,false,false);
        //ONClick otvara frament
        btn_angazujme.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(frag == 1)
                    replaceFragment(angazujMe,false,true);
                else
                    replaceFragment(angazujMe,true,false);
                frag = 2;
                return false;
            }
        });

        btn_pretragaljudi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(pretragaLjudi,true,false);
                frag = 1;
                return false;
            }
        });

        btn_mojprofil.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(mojProfil, true, true);
                frag = 3;
                return false;
            }
        });
    }


    void replaceFragment(Fragment fr, boolean toleft, boolean toright) {
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction = fmanager.beginTransaction();
        if (toleft)
            ftransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (toright)
            ftransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        //ako nije ni toleft, ni toright, nece biti animacije
        ftransaction.replace(R.id.framelayout, fr);

        ftransaction.commit();
    }
    //iskreno nmp sta je ovo ali detektuje swipe heheh
    private class SwipeLisener implements View.OnTouchListener  {
        GestureDetector gs;


        SwipeLisener(View view) {


            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xdiff = e1.getX() - e2.getX();

                    try {
                        if(xdiff>0) {
                            //desno
                            //tx.setText("desno");
                            if (frag == 2) {

                                btn_mojprofil.setChecked(true);
                                replaceFragment(mojProfil, true, true);
                                frag = 3;
                            }
                            else if(frag==1)
                            {
                                btn_angazujme.setChecked(true);
                                replaceFragment(angazujMe,false,true);
                                frag = 2;
                            }
                        }
                        else if(xdiff<0) {
                            //levo
                            if(frag==2)
                            {
                                btn_pretragaljudi.setChecked(true);
                                replaceFragment(pretragaLjudi,true,false);
                                frag=1;
                            }
                            else if(frag ==3)
                            {
                                btn_angazujme.setChecked(true);
                                replaceFragment(angazujMe,true,false);
                                frag=2;
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
            gs = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gs.onTouchEvent(motionEvent);
        }
    }
}