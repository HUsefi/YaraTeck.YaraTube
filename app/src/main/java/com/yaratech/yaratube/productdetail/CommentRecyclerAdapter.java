package com.yaratech.yaratube.productdetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;

import java.util.List;
import java.util.zip.Inflater;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder> {
    private List<Comment> commentList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextViewUserNameComment.setText(commentList.get(position).getUser());
        holder.mTextViewContentComment.setText(commentList.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
          TextView mTextViewUserNameComment,mTextViewContentComment;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewContentComment=itemView.findViewById(R.id.text_view_comment_content);
            mTextViewUserNameComment=itemView.findViewById(R.id.text_view_name_user_comment);

        }
    }

    void setData(List<Comment> commentList){
        this.commentList=commentList;
        notifyDataSetChanged();
    }
}
