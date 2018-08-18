package com.yaratech.yaratube.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductList;

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
        String image_url = productLists.get(position).getAvatar().getXxhdpi();
        String title = productLists.get(position).getName();
        Glide.with(context).load(image_url).into(holder.mImageViewProductListAvatar);
        holder.mTextViewProductListTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageViewProductListAvatar;
        private TextView mTextViewProductListTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewProductListAvatar = itemView.findViewById(R.id.image_view_product);
            mTextViewProductListTitle = itemView.findViewById(R.id.text_view_product_name);

            itemView.setOnClickListener((View.OnClickListener) this);

        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(productLists.get(getAdapterPosition()).getId());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int productId);
    }

}
