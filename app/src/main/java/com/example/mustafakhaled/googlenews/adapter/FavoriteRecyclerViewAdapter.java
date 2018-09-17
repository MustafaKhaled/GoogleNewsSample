package com.example.mustafakhaled.googlenews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mustafakhaled.googlenews.R;
import com.example.mustafakhaled.googlenews.models.ArticalModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Mustafa Khaled on 9/17/2018.
 *
 */ public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.MyViewHolder> {
    SimpleDateFormat format;
    Date formatedDate;
    Context mContext;
    ArrayList<ArticalModel> modelArrayList;

    public FavoriteRecyclerViewAdapter(Context context, ArrayList<ArticalModel> modelArrayList) {
        this.mContext=context;
        this.modelArrayList=modelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_list_item_favorite,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try{
            formatedDate = format.parse(modelArrayList.get(position).getPublishedAt().toString());
            holder.articleSource.setText(modelArrayList.get(position).getSourceModel().getName());
            //Use getRelativeTimeSpanString to calculate the difference between current time and article publish timeing
            //by showing today, yesterday,
            holder.date.setText(DateUtils.getRelativeTimeSpanString(formatedDate.getTime(), Calendar.getInstance().getTimeInMillis() ,DateUtils.DAY_IN_MILLIS));
            holder.description.setText(modelArrayList.get(position).getDescription());
            holder.title.setText(modelArrayList.get(position).getTitle());
            Glide.with(mContext)
                    .load(modelArrayList.get(position).getUrlToImage())
                    .placeholder(R.drawable.news_placeholder)
                    .into(holder.article_iamge);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView articleSource,title,description,date;
        ImageView article_iamge;
        public MyViewHolder(View itemView) {
            super(itemView);
            articleSource = (TextView) itemView.findViewById(R.id.source_text);
            title = (TextView) itemView.findViewById(R.id.title_text);
            description = (TextView) itemView.findViewById(R.id.desc_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            article_iamge = (ImageView) itemView.findViewById(R.id.imageView);
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
    }
}
