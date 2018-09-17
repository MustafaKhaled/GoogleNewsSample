package com.example.mustafakhaled.googlenews.helper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mustafakhaled.googlenews.activities.MainActivity;
import com.example.mustafakhaled.googlenews.R;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public abstract class RecyclerViewPagination extends RecyclerView.OnScrollListener {
     private final String TAG = "RecyclerViewPagination";
    LinearLayoutManager mlinearLayoutManager;
    Context mContext;
    public RecyclerViewPagination(LinearLayoutManager layoutManager, Context context) {
        this.mlinearLayoutManager = layoutManager;
        this.mContext=context;
        Log.e(TAG,"Default Constructor: "+ "Parameters received");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(!isLoading()){
            if(mlinearLayoutManager.findLastCompletelyVisibleItemPosition()== mlinearLayoutManager.getItemCount()-1){

                loadMoreItems();
//                LayoutInflater vi = (LayoutInflater) mContext
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = vi.inflate(R.layout.activity_main,null,false);
//                ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
                ProgressBar progressBar = ((MainActivity) mContext).findViewById(R.id.main_progress);
                progressBar.setVisibility(View.VISIBLE);
                View view = ((MainActivity) mContext).findViewById(R.id.view);
                view.setVisibility(View.VISIBLE);
                Log.e(TAG,"onScroll(): Reach the end of List");
            }

        }
        else {
            Log.e(TAG,"onScroll(): isLoading is false");

        }

    }

    protected abstract void loadMoreItems();


    public abstract boolean isLoading();
}
