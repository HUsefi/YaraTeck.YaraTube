package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    private List<ProductList> productLists = new ArrayList<>();
    private ItemClickListener mItemClickListener;
    private Context context;

    public ProductListAdapter(Context context,ItemClickListener mItemClickListener) {
        this.context = context;
        this.mItemClickListener = mItemClickListener;
    }
    public void setData(List<ProductList> productLists){
        this.productLists = productLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home_content, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String image_url = Constant.BASE_URL+productLists.get(position).getAvatar();
     //   String title = productLists.get(position).getTitle();
     //   Glide.with(context).load(image_url).into(holder.mImageViewCategoryAvatar);
//        holder.mTextViewCategoryTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        public ViewHolder(View itemView) {
            super(itemView);


        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(productLists.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onItemClick(ProductList productList);
    }

}
