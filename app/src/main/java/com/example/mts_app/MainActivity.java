package com.example.mts_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    HorizontalScrollView scrollView;
    LinearLayout containter;
    BottomNavigationView menu;
    //public MenuItem btn_mojprofil, btn_angazujme, btn_pretragaljudi;

    Fragment[] frags;

    public MenuItem[] buttons;

    public DisplayMetrics dm = new DisplayMetrics();

    public int frag = 1; //id trenutnog fragmenta, izmedju 1 i 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (BottomNavigationView) findViewById(R.id.bottom_nav);
        relativeLayout = findViewById(R.id.main);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        containter = (LinearLayout) findViewById(R.id.container);
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);


        //Menu dugmad
        //btn_angazujme = menu.getMenu().getItem(1);
        //btn_pretragaljudi = menu.getMenu().getItem(0);
        //btn_mojprofil = menu.getMenu().getItem(2);
        buttons = new MenuItem[3];
        buttons[0] = menu.getMenu().getItem(0);
        buttons[1] = menu.getMenu().getItem(1);//glavna
        buttons[2] = menu.getMenu().getItem(2);

        frags = new Fragment[3];
        frags[0] = new AngazujMe();
        frags[1] = new PretragaLjudi();
        frags[2] = new MojProfil();

        buttons[frag].setChecked(true);
        FrameLayout []frameLayout = new FrameLayout[3];
        for(int i = 0;i<3; i++) {
            frameLayout[i] = new FrameLayout(getBaseContext());
        }
        frameLayout[0].setId(R.id.framel1);
        frameLayout[1].setId(R.id.framel2);
        frameLayout[2].setId(R.id.framel3);
        for (int i = 0; i<3;i++) {
            replaceFragment(frags[i], frameLayout[i]);
            containter.addView(frameLayout[i]);
        }


        //OnClick za dugmad u bottom baru - otvaraju fragmente
        buttons[1].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        buttons[0].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        buttons[2].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

    }

    void replaceFragment(Fragment fr, FrameLayout fl) {
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction = fmanager.beginTransaction();
        //ako nije ni toleft, ni toright, nece biti animacije
        ftransaction.replace(fl.getId(), fr);

        ftransaction.commit();
    }
}