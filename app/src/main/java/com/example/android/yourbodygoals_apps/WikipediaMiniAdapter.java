package com.example.android.yourbodygoals_apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yosafat Dhimas on 22/04/2018.
 */

public class WikipediaMiniAdapter extends RecyclerView.Adapter<WikipediaMiniAdapter.ViewHolder> {
    private ArrayList<WikipediaMini> mWikipediaData;
    private Context mContext;

    WikipediaMiniAdapter(Context context, ArrayList<WikipediaMini> wikipediaData){
        this.mWikipediaData = wikipediaData;
        this.mContext = context;
    }

        @Override
    public WikipediaMiniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(WikipediaMiniAdapter.ViewHolder holder, int position) {
        WikipediaMini currentMineral = mWikipediaData.get(position);
        holder.bindTo(currentMineral);
    }

    @Override
    public int getItemCount() {
        return mWikipediaData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleText;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        public void bindTo(WikipediaMini currentMineral) {
            mTitleText.setText(currentMineral.getJudul());
        }

        @Override
        public void onClick(View view) {
            WikipediaMini currentMineral = mWikipediaData.get(getAdapterPosition());

            Intent detailInt = new Intent(mContext, WikipediaMiniDetail.class);
            detailInt.putExtra("title", currentMineral.getJudul());
            detailInt.putExtra("desc", currentMineral.getDesc());
            detailInt.putExtra("image_resource", currentMineral.getImg());

            mContext.startActivity(detailInt);
        }
    }
}
