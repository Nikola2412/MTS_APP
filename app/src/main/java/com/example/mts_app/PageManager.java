package com.example.mts_app;

import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PageManager {
    static void replaceFragment(Fragment fr, FrameLayout fl, FragmentManager fragmentManager) {
        FragmentTransaction ftransaction = fragmentManager.beginTransaction();
        ftransaction.replace(fl.getId(), fr);
        ftransaction.commit();
    }

    static void ScrollToPage(int n, HorizontalScrollView horizontalScrollView, DisplayMetrics dm) {
        horizontalScrollView.smoothScrollTo(n * dm.widthPixels, 0);
    }

    static int ScrollToWholePage(HorizontalScrollView horizontalScrollView, DisplayMetrics dm, int n_staro) {
        int n = (int)Math.round((double)horizontalScrollView.getScrollX() / (double)dm.widthPixels);

        horizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.smoothScrollTo(dm.widthPixels * n, 0);
            }
        });

        return n;
    }
}
