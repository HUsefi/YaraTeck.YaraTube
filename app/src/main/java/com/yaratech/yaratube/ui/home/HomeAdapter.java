package com.yaratech.yaratube.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import static android.support.constraint.Constraints.TAG;


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;
    public static final int HOME = 1;
    private Store mStore = new Store();
    private Context context;
    private List<Homeitem> homeitems;
    private List<Headeritem> headeritems;


    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setDateStore(Store store) {
        headeritems = store.getHeaderitem();
        homeitems = store.getHomeitem();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_header,
                        parent, false);
                return new HeaderViewHolder(view);
            default:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home,
                        parent, false);
                return new HomeViewHolder(view1);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if (viewType == HEADER) {
            ((HeaderViewHolder) holder).onBind();
        } else if (viewType == HOME)
            ((HomeViewHolder) holder).onBind(homeitems.get(position - 1));
    }


    @Override
    public int getItemCount() {
        if (headeritems == null && homeitems == null)
            return 0;
        else if (headeritems == null)
            return homeitems.size();
        else if (homeitems == null)
            return 1;
        return 1 + homeitems.size();
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

    class HomeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mHomeRecyclerView;
        private TextView productListName;

        HomeViewHolder(View itemView) {
            super(itemView);

            mHomeRecyclerView = itemView.findViewById(R.id.recycler_view_home);
            productListName = itemView.findViewById(R.id.product_List_Name);
        }

        void onBind(Homeitem homeitem) {

            mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                    false));

            HomeItemAdapter adapter = new HomeItemAdapter();
            adapter.setProducts(homeitem.getProducts());
            mHomeRecyclerView.setAdapter(adapter);
            productListName.setText(homeitem.getTitle());
        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mHeaderRecyclerView;

        HeaderViewHolder(View itemView) {
            super(itemView);

            mHeaderRecyclerView = itemView.findViewById(R.id.recycler_view_header);
        }

        void onBind() {

            mHeaderRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                    false));

            HeaderItemAdapter adapter = new HeaderItemAdapter();
            adapter.setHeaderItems(headeritems);
            mHeaderRecyclerView.setAdapter(adapter);
        }
    }


}
