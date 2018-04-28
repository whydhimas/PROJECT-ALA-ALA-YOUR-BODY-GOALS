package com.example.android.yourbodygoals_apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by Yosafat Dhimas on 27/04/2018.
 */

public class JenisOlahragaAdapter extends RecyclerView.Adapter<JenisOlahragaAdapter.ViewHolder>{
    private ArrayList<JenisOlahraga> mJenisOlahragaData;
    private Context mContext;

    JenisOlahragaAdapter (Context context, ArrayList<JenisOlahraga> jenisolahragaData){
        this.mJenisOlahragaData = jenisolahragaData;
        this.mContext = context;
    }

    @Override
    public JenisOlahragaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list2, parent, false));
    }

    @Override
    public void onBindViewHolder(JenisOlahragaAdapter.ViewHolder holder, int position) {
        JenisOlahraga currentMineral1 = mJenisOlahragaData.get(position);
        holder.bindTo(currentMineral1);
    }

    @Override
    public int getItemCount() {
        return mJenisOlahragaData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleText;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        public void bindTo(JenisOlahraga currentMineral) {
            mTitleText.setText(currentMineral.getJudulJO());
        }

        @Override
        public void onClick(View view) {
            JenisOlahraga currentMineral = mJenisOlahragaData.get(getAdapterPosition());

            Intent detailInt = new Intent(mContext, JenisOlahragaDetail.class);
            detailInt.putExtra("title2", currentMineral.getJudulJO());
            detailInt.putExtra("desc2", currentMineral.getDescJO());
            detailInt.putExtra("image_resource2", currentMineral.getImgJO());

            mContext.startActivity(detailInt);
        }
    }

    public void setFilter(ArrayList<JenisOlahraga> newList){
        mJenisOlahragaData = new ArrayList<>();
        mJenisOlahragaData.addAll(newList);
        notifyDataSetChanged();
    }
}
