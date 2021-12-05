package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
/**
 * Recycler view Adapter
 * @author ujas1
 * @version 4.0
 * @since 1.0
 */
public class CarbonAdapter extends RecyclerView.Adapter<CarbonAdapter.SoccerViewHolder> {

    private Context mContext;
    private ArrayList<CarbonDioxideModel> mCarbonList;
    private RecyclerViewClickListener listener;

    public CarbonAdapter(Context context, ArrayList<CarbonDioxideModel> CO2List, RecyclerViewClickListener mlistener)
    {
        mContext=context;
        mCarbonList=CO2List;
        listener=mlistener;
    }

    @NonNull
    @Override
    public SoccerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.carbon_item_view,parent,false);
        return new SoccerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SoccerViewHolder holder, int position) {
        CarbonDioxideModel currentItem = mCarbonList.get(position);
        String title = currentItem.getTitle();
        String pubdate = currentItem.getPubDate();
        holder.mTitle.setText(title);
        holder.mPubDate.setText(pubdate);
        Picasso.get().load(currentItem.getThumbnail()).resize(120, 60).into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mCarbonList.size();
    }

    public class SoccerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener
    {
        public TextView mTitle;
        public TextView mPubDate;
        public ImageView mThumbnail;
        public TextView mURL;
        public SoccerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.txt_carbon_title);
            mPubDate = itemView.findViewById(R.id.txt_carbon_pubdate);
            mThumbnail = (ImageView)itemView.findViewById(R.id.img_soccer_thumbnail);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
}
