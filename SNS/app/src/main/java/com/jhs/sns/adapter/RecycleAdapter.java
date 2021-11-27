package com.jhs.sns.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhs.sns.R;
import com.jhs.sns.data.Post;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    ArrayList<Post> listPost = new ArrayList<>();
    Context mContext;
    OnItemClickListener mListener = null;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvContent;
        TextView tvMemberName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.liTvTitle);
            tvContent = itemView.findViewById(R.id.litTvContent);
            tvMemberName = itemView.findViewById(R.id.liTvMemberName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){

                        mListener.onItemClick(position);
                    }
                }
            });

        }
    }

    public RecycleAdapter(Context context){
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = listPost.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvContent.setText(post.getContent());
        holder.tvMemberName.setText(post.getMemberName());

    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public void replaceItem(ArrayList<Post> list){
        listPost = new ArrayList<>();
        listPost.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener){
        this.mListener = listener;
    }

}
