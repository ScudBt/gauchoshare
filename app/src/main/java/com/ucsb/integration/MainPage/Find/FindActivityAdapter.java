package com.ucsb.integration.MainPage.Find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ucsb.integration.MainPage.Listing.Product;
import com.ucsb.integration.R;

import java.util.ArrayList;
import java.util.List;

public class FindActivityAdapter extends RecyclerView.Adapter<FindActivityAdapter.Holderview> {

    private List<Product> proList;
    private Context context;

    public FindActivityAdapter(List<Product> proList, Context context) {
        this.proList = proList;
        this.context = context;
    }



    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.customitem, parent,false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holder, final int position) {
        if (!proList.get(position).getSold()) {
            holder.v_sold.setVisibility(View.INVISIBLE);
        } else {
            holder.v_sold.setVisibility(View.VISIBLE);
        }
        holder.v_name.setText(proList.get(position).getTitle());
        holder.v_desc.setText((proList.get(position).getDescription()));
        Picasso.get().load(proList.get(position).getImageURL()).into(holder.v_image);


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(context, "Click on " + proList.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return proList.size();
    }

    public void setfilter(List<Product> listitem){
        proList = new ArrayList<>();
        proList.addAll(listitem);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder{
        ImageView v_image;
        TextView v_name;
        TextView v_desc;
        ImageView v_sold;

        Holderview(View itemview){
            super(itemview);
            v_image = itemview.findViewById(R.id.pro_image);
            v_name = itemview.findViewById(R.id.pro_title);
            v_desc = itemview.findViewById(R.id.pro_desc);
            v_sold = itemview.findViewById(R.id.pro_sold);
        }


    }
}
