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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;

    SwipeLisener sl;
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

        sl = new SwipeLisener(relativeLayout, this);


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
        //btn_angazujme.setChecked(true);
        buttons[frag].setChecked(true);
        replaceFragment(frags[frag],false,false);

        //OnClick za dugmad u bottom baru - otvaraju fragmente
        buttons[1].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(frag == 0)
                    replaceFragment(frags[1],false,true);
                else
                    replaceFragment(frags[1],true,false);
                frag = 1;
                return false;
            }
        });

        buttons[0].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(frags[0],true,false);
                frag = 0;
                return false;
            }
        });

        buttons[2].setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(frags[2], true, true);
                frag = 2;
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
}