package com.yaratech.yaratube.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Homeitem;
import com.yaratech.yaratube.data.model.Store;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    public static final int HEADER = 0;
    public static final int HOME = 1;
    private Store mStore = new Store();
    private Context context;
    List<Homeitem> homeitems = new ArrayList<>();
    List<Headeritem> headeritems = new ArrayList<>();

    public HomeAdapter() {
    }

    private class HeaderListItemViewHolder extends HomeViewHolder {
        public HeaderListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class HomeListItemViewHolder extends HomeViewHolder {
        public HomeListItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_header,
                        parent, false);
                return new HeaderListItemViewHolder(view);
            default:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home,
                        parent, false);
                return new HeaderListItemViewHolder(view1);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setDateStore(Store store) {
        this.mStore = store;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mHeaderRecyclerView;
        RecyclerView mHomeRecyclerView;
        TextView mTextView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mHeaderRecyclerView=itemView.findViewById(R.id.recycler_view_header);
            mHomeRecyclerView=itemView.findViewById(R.id.recycler_view_home);
            mTextView=itemView.findViewById(R.id.text_view_home);
        }

        public void onBindViewHeaderList(int pos) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);
            mHomeRecyclerView.setLayoutManager(linearLayoutManager);
             HeaderItemAdapter  headerItemAdapter= new HeaderItemAdapter( headeritems);
            mHeaderRecyclerView.setAdapter(headerItemAdapter);
        }

        public void onBindViewHomeList(int pos) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);
            mHomeRecyclerView.setLayoutManager(linearLayoutManager);
            HeaderItemAdapter  headerItemAdapter= new HeaderItemAdapter( headeritems);
            mHeaderRecyclerView.setAdapter(headerItemAdapter);
        }






    }






    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return HEADER;
            default:
                return HOME;


        }
    }
}
