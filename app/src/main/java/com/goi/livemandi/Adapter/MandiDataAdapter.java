package com.goi.livemandi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.goi.livemandi.Models.Record;
import com.goi.livemandi.Models.Root;
import com.goi.livemandi.R;

import java.util.List;

public class MandiDataAdapter extends RecyclerView.Adapter {
    private final Activity activity;
    Root root;
    private List<Record> listdata;
    private Context ctx;

    public MandiDataAdapter(Root root, Context ctx, Activity activity) {
        this.root = root;
        this.listdata = root.getRecords();
        this.ctx = ctx;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.mandi_cardview,parent,false);
        MandiDataAdapter.MandiDataViewHolder CastPosterViewHolder=new MandiDataAdapter.MandiDataViewHolder(view);
        return CastPosterViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MandiDataAdapter.MandiDataViewHolder) holder).bindView(listdata.get(position));
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public interface OnFeedItemClickListener {
        void onEditOffer(View v, int position);
    }

    public class MandiDataViewHolder extends RecyclerView.ViewHolder
    {

        TextView state,district,market,variety,commodity,arrival_date,min_price,max_price,modal_price;

        MandiDataViewHolder(View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.state);
            district = itemView.findViewById(R.id.district);
            market = itemView.findViewById(R.id.market);
            commodity = itemView.findViewById(R.id.commodity);
            variety = itemView.findViewById(R.id.variety);
            arrival_date = itemView.findViewById(R.id.arrival_date);
            min_price = itemView.findViewById(R.id.min_price);
            max_price = itemView.findViewById(R.id.max_price);
            modal_price = itemView.findViewById(R.id.modal_price);

        }
        void bindView(Record record)
        {
            if(record != null) {
                state.setText("State: "+record.getState());
                district.setText("District: "+record.getDistrict());
                market.setText("Market: "+record.getMarket());
                commodity.setText("Commodity: "+record.getCommodity());
                variety.setText("Variety: "+record.getVariety());
                arrival_date.setText("Arrival Date: "+record.getArrival_date());
                min_price.setText("Min Price: "+record.getMin_price());
                max_price.setText("Max Price: "+record.getMax_price());
                modal_price.setText("Modal Price: "+record.getModal_price());
            }
        }
    }
}


