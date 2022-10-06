package com.example.mts_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menu;
    MenuItem btn_mojprofil, btn_angazujme, btn_pretragaljudi;
    int frag; //id trenutnog fragmenta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //Menu dugmad
        btn_angazujme = menu.getMenu().getItem(0);
        btn_pretragaljudi = menu.getMenu().getItem(1);
        btn_mojprofil = menu.getMenu().getItem(2);

        Fragment angazujMe = new AngazujMe();
        Fragment pretragaLjudi = new PretragaLjudi();
        Fragment mojProfil = new MojProfil();

        replaceFragment(mojProfil, false, false);

        //ONClick otvara frament
        btn_angazujme.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(angazujMe, true, false);
                frag = 0;
                return false;
            }
        });

        btn_pretragaljudi.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                replaceFragment(new PretragaLjudi(), frag==2, frag==0);
                frag = 1;
                return false;
            }
        });

        btn_mojprofil.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                replaceFragment(mojProfil, false, true);
                frag = 2;
                return false;
            }
        });
    }

    void replaceFragment(Fragment fr, boolean toleft, boolean toright)
    {
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction = fmanager.beginTransaction();
        if(toleft)
            ftransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if(toright)
            ftransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        //ako nije ni toleft, ni toright, nece biti animacije
        ftransaction.replace(R.id.framelayout, fr);

        ftransaction.commit();
    }
}