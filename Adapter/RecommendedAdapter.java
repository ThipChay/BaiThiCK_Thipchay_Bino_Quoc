package com.example.baithick_thipchay_bino_quoc.Activity.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baithick_thipchay_bino_quoc.Activity.Activity.ShowDetailActivity;
import com.example.baithick_thipchay_bino_quoc.Activity.Domain.FoodDomain;
import com.example.baithick_thipchay_bino_quoc.R;


import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    ArrayList<FoodDomain> RecommendedDomain;

    public RecommendedAdapter(ArrayList<FoodDomain> categoreDomains) {
        this.RecommendedDomain = categoreDomains;
    }

    @NonNull
    @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
          View inflate =LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhloder_recommended,parent,false);
          return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
    holder.title.setText(RecommendedDomain.get(position).getTitle());
    holder.fee.setText(String.valueOf(RecommendedDomain.get(position).getFee()));



    int drawableReourceId=holder.itemView.getContext().getResources()
            .getIdentifier(RecommendedDomain.get(position).getPic(),"drawable",
                    holder.itemView.getContext().getPackageName());
       Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.pic);
       holder.addbtn.setOnClickListener(view ->{
               Intent intent =new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
               intent.putExtra("object",RecommendedDomain.get(position));
               holder.itemView.getContext().startActivity(intent);

       });

    }
    @Override
    public int getItemCount(){
        return RecommendedDomain.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        ImageView addbtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.title);
            pic =itemView.findViewById(R.id.pic);
            fee =itemView.findViewById(R.id.fee);
            addbtn =itemView.findViewById(R.id.addbtn);

        }
    }
}