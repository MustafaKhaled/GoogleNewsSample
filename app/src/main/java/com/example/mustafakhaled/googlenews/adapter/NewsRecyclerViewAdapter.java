package com.example.mustafakhaled.googlenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mustafakhaled.googlenews.R;
import com.example.mustafakhaled.googlenews.helper.MyApp;
import com.example.mustafakhaled.googlenews.models.NewResposeModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Mustafa Khaled on 9/16/2018.
 *
 */ public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.RecyclerViewItemHolder> {
    NewResposeModel modelArrayList;
    Context mContext;
    SimpleDateFormat format;
    Date formatedDate;
    int clickedPosition;
    boolean clicked;
    private String TAG = "NewsRecyclerViewAdapter";

    public class RecyclerViewItemHolder extends RecyclerView.ViewHolder{
        TextView articleSource,title,description,date;
        ImageView article_iamge,favorite;
        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            articleSource = (TextView) itemView.findViewById(R.id.source_text);
            title = (TextView) itemView.findViewById(R.id.title_text);
            description = (TextView) itemView.findViewById(R.id.desc_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            article_iamge = (ImageView) itemView.findViewById(R.id.imageView);
            favorite = (ImageView) itemView.findViewById(R.id.favorite);
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
    }

    public NewsRecyclerViewAdapter(NewResposeModel modelArrayList,Context mContext) {
        this.modelArrayList=modelArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.artical_list_item,parent,false);

        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewItemHolder holder, final int position) {
        Log.e(TAG,"onBindViewHolder(): onBind Invoked");
        try{
            formatedDate = format.parse(modelArrayList.getArticalModels().get(position).getPublishedAt().toString());
            holder.articleSource.setText(modelArrayList.getArticalModels().get(position).getSourceModel().getName());
            holder.date.setText(DateUtils.getRelativeTimeSpanString(formatedDate.getTime(), Calendar.getInstance().getTimeInMillis() ,DateUtils.DAY_IN_MILLIS));
            holder.description.setText(modelArrayList.getArticalModels().get(position).getDescription());
            holder.title.setText(modelArrayList.getArticalModels().get(position).getTitle());
            Glide.with(mContext)
                    .load(modelArrayList.getArticalModels().get(position).getUrlToImage())
                    .placeholder(R.drawable.news_placeholder)
                    .into(holder.article_iamge);
            Log.e(TAG,"Loved Or Not: "+ modelArrayList.getArticalModels().get(position).isLoved());
            holder.article_iamge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openLink(modelArrayList.getArticalModels().get(position).getUrl());
                }
            });
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openLink(modelArrayList.getArticalModels().get(position).getUrl());

                }
            });

            holder.description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openLink(modelArrayList.getArticalModels().get(position).getUrl());

                }
            });


            if(modelArrayList.getArticalModels().get(position).isLoved()){

                holder.favorite.setImageResource(R.drawable.ic_favorite_red_24dp);

            }
            else {
                holder.favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);

            }
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!modelArrayList.getArticalModels().get(position).isLoved()){
                        holder.favorite.setImageResource(R.drawable.ic_favorite_red_24dp);
                        MyApp myApp = new MyApp(mContext);
                        myApp.savePost(mContext,modelArrayList.getArticalModels().get(position));
                        modelArrayList.getArticalModels().get(position).setLoved(true);
                        //Change the tag text to check if this was saved before or not
                        holder.favorite.setTag(mContext.getString(R.string.favorite_tag));
                    }
                    else{
                        Toast.makeText(mContext,mContext.getString(R.string.added_to_Favorite_before),Toast.LENGTH_LONG).show();

                    }

//                    clickedPosition = position;
//                    clicked = true;
//                    notifyDataSetChanged();
   }
            });
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return modelArrayList.getArticalModels().size();
    }

    private void openLink(String url){
        Uri uri = Uri.parse(url);
        Intent intent  = new Intent(Intent.ACTION_VIEW,uri);
        mContext.startActivity(intent);
    }

}
