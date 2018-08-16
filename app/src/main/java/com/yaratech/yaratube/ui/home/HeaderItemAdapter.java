//package com.yaratech.yaratube.ui.home;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.yaratech.yaratube.R;
//import com.yaratech.yaratube.data.model.Headeritem;
//
//
//import java.util.List;
//
//import static com.yaratech.yaratube.utils.Constant.BASE_URL;
//
//public class HeaderItemAdapter extends RecyclerView.Adapter<HeaderItemAdapter.HomeItemViewHolder> {
//
//    private List<Headeritem> headeritems;
//
//    public void setHeaderItems(List<Headeritem> headeritems) {
//        this.headeritems = headeritems;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public HomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_header_content
//                , parent, false);
//        return new HomeItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HomeItemViewHolder holder, int position) {
//        holder.onBind(headeritems.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        if (headeritems == null)
//            return 0;
//        return headeritems.size();
//    }
//
//
//    public class HomeItemViewHolder extends RecyclerView.ViewHolder {
//
//
//        private ImageView mHeaderImage;
//        private String urlHeaderImage;
//
//        public HomeItemViewHolder(View itemView) {
//            super(itemView);
//            mHeaderImage = itemView.findViewById(R.id.image_view_header);
//
//        }
//
//        void onBind(Headeritem headeritem) {
//
//            urlHeaderImage = BASE_URL + '/' + headeritem.getFeatureAvatar().getXxhdpi();
//            Glide.with(itemView.getContext()).load(urlHeaderImage).into(mHeaderImage);
//        }
//    }
//}
