package com.example.forev.vetapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forev.vetapp.Fragments.VacDetailFragment;
import com.example.forev.vetapp.Models.PetModel;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportCardAdapter extends RecyclerView.Adapter<ReportCardAdapter.ViewHolder>{

    List<PetModel> list;
    Context context;

    public ReportCardAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.reportcard_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.reportcardPetNameText.setText(list.get(position).getPetName().toString());
        viewHolder.reportcardPetInfoText.setText("Please click to see the past vaccines of "+list.get(position).getPetName().toString()+".");

        Picasso.get().load(list.get(position).getPetImage()).into(viewHolder.reportcardPetCircleImageView);

        viewHolder.viewCardReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeFragments changeFragments = new ChangeFragments(context);
                changeFragments.changeWithParameters(new VacDetailFragment(),list.get(position).getPetId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView reportcardPetNameText, reportcardPetInfoText;
        CircleImageView reportcardPetCircleImageView;
        CardView viewCardReport;

        public ViewHolder(View itemView) {
            super(itemView);

            reportcardPetNameText = (TextView)itemView.findViewById(R.id.reportcardPetNameText);
            reportcardPetInfoText = (TextView)itemView.findViewById(R.id.reportcardPetInfoText);
            reportcardPetCircleImageView = (CircleImageView)itemView.findViewById(R.id.reportcardPetCircleImageView);
            viewCardReport = (CardView)itemView.findViewById(R.id.viewCardReport);
        }
    }
}
