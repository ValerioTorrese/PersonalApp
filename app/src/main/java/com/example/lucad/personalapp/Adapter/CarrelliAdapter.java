package com.example.lucad.personalapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucad.personalapp.Model.Attività;
import com.example.lucad.personalapp.R;

import java.util.ArrayList;

/**
 * Created by LucaD on 09/03/2017.
 */

public class CarrelliAdapter extends RecyclerView.Adapter<CarrelliAdapter.CarrelliVH> {
    ArrayList<Attività> attivitaOk = new ArrayList<>();


    public void setAttivitaOk(ArrayList<Attività> attivita) {
        for (int i = 0; i < attivita.size(); i++) {
            if (attivita.get(i).getTipo().equals("Carrelli")) {
                setAct(attivita.get(i));
                notifyDataSetChanged();
            }
        }
    }

    public void setAct(Attività attivita) {
        attivitaOk.add(attivita);
        notifyItemChanged(attivitaOk.size());
    }

    @Override
    public CarrelliAdapter.CarrelliVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrelli_item, parent, false);
        return new CarrelliVH(v);
    }

    @Override
    public void onBindViewHolder(CarrelliAdapter.CarrelliVH holder, int position) {
        Attività attività = attivitaOk.get(position);
        holder.titolo.setText(attività.getTipo());
        holder.data.setText(attività.getData());
        holder.descrizione.setText(attività.getAppunti());
    }

    @Override
    public int getItemCount() {
        return attivitaOk.size();
    }

    public class CarrelliVH extends RecyclerView.ViewHolder {
        TextView titolo, data, descrizione;

        public CarrelliVH(View itemView) {
            super(itemView);
            titolo = (TextView) itemView.findViewById(R.id.titolo_attivita);
            data = (TextView) itemView.findViewById(R.id.data_attivita);
            descrizione = (TextView) itemView.findViewById(R.id.descrizione_attivita);
        }
    }
}
