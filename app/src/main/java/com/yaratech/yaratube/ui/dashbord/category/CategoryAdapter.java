package com.yaratech.yaratube.ui.dashbord.category;

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
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryList> categoryLists = new ArrayList<>();
    private ItemClickListener mItemClickListener;
    private Context context;

    public CategoryAdapter(Context context,ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        this.context = context;
    }
    public void setData(List<CategoryList> categoryLists){
        this.categoryLists = categoryLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_item, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String image_url = Constant.BASE_URL+categoryLists.get(position).getAvatar();
        String title = categoryLists.get(position).getTitle();
        Glide.with(context).load(image_url).into(holder.mImageViewCategoryAvatar);
        holder.mTextViewCategoryTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewCategoryAvatar;
        private TextView  mTextViewCategoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewCategoryAvatar = itemView.findViewById(R.id.category_avatar);
            mTextViewCategoryTitle = itemView.findViewById(R.id.category_title);

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(categoryLists.get(getAdapterPosition()));
        }
    }


    public interface ItemClickListener {
        void onItemClick(CategoryList categoryList);
    }


}


