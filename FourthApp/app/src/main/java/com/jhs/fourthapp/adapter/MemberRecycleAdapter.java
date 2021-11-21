package com.jhs.fourthapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhs.fourthapp.R;
import com.jhs.fourthapp.data.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRecycleAdapter extends RecyclerView.Adapter<MemberRecycleAdapter.MemberHolder> {


    ArrayList<Member> listMember = new ArrayList<>();
    Context mContext;

    public class MemberHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvId;
        ImageView imgProfile;

        public MemberHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvItemName);
            tvId = itemView.findViewById(R.id.tvItemId);
            imgProfile = itemView.findViewById(R.id.imgItemProfile);
        }
    }

    public MemberRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lv_member_item, parent, false);
        MemberHolder holder = new MemberHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {

        holder.tvName.setText(listMember.get(position).getName());
        holder.tvId.setText(listMember.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return listMember.size();
    }

    public void addItem(Member member){

        listMember.add(member);
        notifyDataSetChanged();
    }


}
