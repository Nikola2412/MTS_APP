package com.example.mts_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.Console;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    public HorizontalScrollView scrollView;
    LinearLayout containter;
    BottomNavigationView menu;
    FloatingActionButton applybtn_myprofile;

    Page[] pages;

    boolean Postoji_Scroll = false;

    public DisplayMetrics dm = new DisplayMetrics();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pages = new Page[3];
        menu = (BottomNavigationView) findViewById(R.id.bottom_nav);
        relativeLayout = findViewById(R.id.main);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        containter = (LinearLayout) findViewById(R.id.container);
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);


        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0;i<3; i++) {
            pages[i] = new Page(menu.getMenu().getItem(i),getBaseContext(),fm, this);
            containter.addView(pages[i].FrameLO);
        }

        //Ako bi se FOR unutar sledece funkcije izvrsio u onCreate, menu.getHeight() bi vratio 0, mora prvo da se iscrta
        menu.post(new Runnable() {
            @Override
            public void run() {
                //Posavlja se sirina i visina svakog frameLayout-a
                //Visina: visina ekrana - visina bottom menija - margina 20dp
                //Margina 20dp je podesena u XML pa zato da bude jednaka i sa donje strane; 20dp = 20/(160/dm.xdpi)
                for(int i = 0;i<3; i++) {
                    pages[i].FrameLO.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels,containter.getHeight()));
                    pages[i].FrameLO.setNestedScrollingEnabled(false);
                }

                //Početno prikazana stranica:
                PageManager.ScrollToPage_NoAnim(1,scrollView, dm);
                markButton(1);
                //--------------------------
            }
        });

        //Svaki put po podignutom kliku se podesava polozaj scroll-a, ako se dogodio scroll (Postoji_Scroll ?)
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP && Postoji_Scroll) {
                    int curr_page = 0;
                    curr_page = PageManager.ScrollToWholePage(scrollView,dm, curr_page);
                    markButton(curr_page);
                    Postoji_Scroll = false;
                }

                return false;
            }
        });
        //----------------------------

        //Ako je doslo do scroll-a po X postavlja se flag, da se ne bi postavljao polozaj scroll-a svaki put na TouchUp
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int xOld, int yOld) {
                Postoji_Scroll = (Math.abs(x-xOld) > 0);
            }
        });
        //----------------------------
    }

    void markButton(int n)
    {
        pages[n].OpenButton.setChecked(true);
    }
}

