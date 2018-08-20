package com.yaratech.yaratube.ui.dashbord.home;

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
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.ArrayList;
import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder> {

    private List<Product> mProducts;
    private OnHomeItemClickListener mOnHomeItemClickListener;
    private List<ProductList> productLists = new ArrayList<>();
    private Context context;



    public void setProducts(List<Product> products) {
        this.mProducts = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_home_content
                , parent, false);
        return new HomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemViewHolder holder, int position) {
        holder.onBind(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        if (mProducts == null)
            return 0;
        return mProducts.size();
    }


    public class HomeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String mUrlImageProduct;
        private ImageView mImageProduct;
        private TextView mNameProduct;
        private TextView mProductDescription;


        public HomeItemViewHolder(View itemView) {
            super(itemView);

            mImageProduct = itemView.findViewById(R.id.image_view_product);
            mNameProduct = itemView.findViewById(R.id.text_view_product_name);
            mProductDescription = itemView.findViewById(R.id.text_view_product_description);

        }

        void onBind(Product product) {

            if (product.getAvatar() != null) {
                Glide.with(itemView.getContext()).load(product.getAvatar().getXxhdpi()).into(mImageProduct);
            }

            mNameProduct.setText(product.getName());
            mProductDescription.setText(product.getShortDescription());
        }


        @Override
        public void onClick(View view) {
            mOnHomeItemClickListener.getHomeProductItem(mProducts.get(getAdapterPosition()));
        }

    }
    public interface OnHomeItemClickListener{
        void getHomeProductItem(Product product);
    }

}
