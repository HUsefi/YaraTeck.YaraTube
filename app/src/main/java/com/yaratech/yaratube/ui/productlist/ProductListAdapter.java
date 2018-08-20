package com.yaratech.yaratube.ui.productlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.MainActivity;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.OnProductItemClick;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> productLists = new ArrayList<>();
    private ItemClickListener mItemClickListener;
    private Context context;
    OnProductItemClick onProductItemClick;


    public ProductListAdapter(Context context, ItemClickListener mItemClickListener) {
        this.context = context;
        this.mItemClickListener = mItemClickListener;
    }

    public void setData(List<Product> productLists) {
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
        holder.onBind(productLists.get(position));
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewProductListAvatar;
        private TextView mTextViewProductListTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewProductListAvatar = itemView.findViewById(R.id.image_view_product);
            mTextViewProductListTitle = itemView.findViewById(R.id.text_view_product_name);

            itemView.setOnClickListener(this);

        }

        public void onBind(Product product) {
            String image_url = product.getFeatureAvatar().getXxhdpi();
            String title = product.getName();
            Glide.with(context).load(image_url).into(mImageViewProductListAvatar);
            mTextViewProductListTitle.setText(title);
        }


        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(productLists.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onItemClick(Product product);
    }

}
