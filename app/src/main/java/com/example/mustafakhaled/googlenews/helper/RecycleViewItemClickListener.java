package com.example.mustafakhaled.googlenews.helper;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/*
 * Created by Mustafa Khaled on 9/17/2018.
 *
 */ public class RecycleViewItemClickListener implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
