package com.example.mts_app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Iterator;

public class Page {
    public MenuItem OpenButton ;
    public Fragment Frag;
    public FrameLayout FrameLO;
    FragmentManager fragmentManager;

    public Page(MenuItem btn, Context BaseCtx, FragmentManager fm, MainActivity mainActivity)
    {
        OpenButton = btn;
        Frag = N_Frame == 0 ? new AngazujMe() : N_Frame==1 ? new PretragaLjudi() : new MojProfil();
        fragmentManager = fm;
        FrameLO = new FrameLayout(BaseCtx);
        FrameLO.setId(NextFrameID());
        PageManager.replaceFragment(Frag, FrameLO, fm);

        btn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            int n = N_Frame;
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PageManager.ScrollToPage(n-1, mainActivity.scrollView, mainActivity.dm);
                mainActivity.markButton(n-1);
                return true;
            }
        });
    }

    static int N_Frame = 0;
    public int NextFrameID() {
        N_Frame++;
       return N_Frame == 1 ? R.id.framel1 : N_Frame == 2? R.id.framel2 : R.id.framel3; //nije 012 nego 123 jer se prethodno uveca, tj. 1 znaci 0
    }
}

