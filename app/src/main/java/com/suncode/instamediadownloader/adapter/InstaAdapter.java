package com.suncode.instamediadownloader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.suncode.instamediadownloader.R;

import java.util.ArrayList;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.InstaHolder> {

    private Context mContext;
    private ArrayList<String> mData;

    public InstaAdapter(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public InstaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_data, parent, false);
        return new InstaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstaHolder holder, int position) {
        String image = mData.get(position);

        Glide.with(mContext).load(image).into(holder.previewImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class InstaHolder extends RecyclerView.ViewHolder {
        final Button downloadButton;
        final ImageView previewImage;

        public InstaHolder(@NonNull View itemView) {
            super(itemView);

            downloadButton = itemView.findViewById(R.id.buttonDownloadItemList);
            previewImage = itemView.findViewById(R.id.imageViewListItem);
        }
    }
}
