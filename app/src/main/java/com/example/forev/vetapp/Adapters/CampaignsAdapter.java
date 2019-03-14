package com.example.forev.vetapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.vetapp.Models.CampaignsModel;
import com.example.forev.vetapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CampaignsAdapter extends RecyclerView.Adapter<CampaignsAdapter.ViewHolder>{

    List<CampaignsModel> list;
    Context context;
    String shortText;

    public CampaignsAdapter(List<CampaignsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.campaigns_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        viewHolder.campaigns_title.setText(list.get(position).getTitle().toString());


        if(list.get(position).getText().length()>20)
        {
            shortText = list.get(position).getText().substring(0,20) + "...";
            viewHolder.campaigns_text.setText(shortText);
        }
        else
        {
            viewHolder.campaigns_text.setText(list.get(position).getText().toString());
        }

        Picasso.get().load(list.get(position).getImage()).into(viewHolder.campaigns_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView campaigns_title,campaigns_text;
        ImageView campaigns_image;

        public ViewHolder(View itemView) {
            super(itemView);

            campaigns_title = (TextView)itemView.findViewById(R.id.campaigns_title);
            campaigns_text = (TextView)itemView.findViewById(R.id.campaigns_text);
            campaigns_image = (ImageView)itemView.findViewById(R.id.campaigns_image);

        }
    }
}
