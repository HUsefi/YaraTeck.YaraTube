package com.yaratech.yaratube.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Homeitem;

import java.util.List;

public class HeaderItemAdapter extends RecyclerView.Adapter {


    public HeaderItemAdapter(List<Headeritem> headeritems) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HomeItemViewHolder extends RecyclerView.ViewHolder{
        public HomeItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
