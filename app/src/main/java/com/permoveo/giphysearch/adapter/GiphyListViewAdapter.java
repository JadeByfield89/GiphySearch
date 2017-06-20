package com.permoveo.giphysearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.permoveo.giphysearch.R;
import com.permoveo.giphysearch.model.Giph;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by byfieldj on 6/19/17.
 *
 * Adapter class for the scrolling list of GIFs that will be returned by Giphy's API
 */

public class GiphyListViewAdapter extends RecyclerView.Adapter<GiphyListViewAdapter.ViewHolder> {

    private ArrayList<Giph> mItems;


    public GiphyListViewAdapter(ArrayList<Giph> items) {
        this.mItems = items;
    }

    public void appendResults(ArrayList<Giph> results) {

        mItems.clear();
        mItems.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cell, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(holder.imageView.getContext()).load(mItems.get(position).getUrl()).fit().centerCrop().placeholder(R.drawable.placeholder_thumbnail).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivGif)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
