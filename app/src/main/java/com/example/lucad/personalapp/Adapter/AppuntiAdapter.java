package com.example.lucad.personalapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucad.personalapp.Activity.AppuntiActivity;
import com.example.lucad.personalapp.Model.Attività;
import com.example.lucad.personalapp.R;

import java.util.ArrayList;

/**
 * Created by LucaD on 05/03/2017.
 */

public class AppuntiAdapter extends RecyclerView.Adapter<AppuntiAdapter.AppuntiVH> {
    ArrayList<Attività> attivitaOK =new ArrayList<>();
    private static int posizione;
    public boolean ok;

    public void setAttivitaOK(ArrayList<Attività> attivita) {
        for(int i = 0; i< attivita.size(); i++){
        if(attivita.get(i).getTipo().equals("Cons. oggetti"))
        setAct(attivita.get(i));
        notifyDataSetChanged();}
    }

    public int getPosition(){
        return posizione;
    }

    public void deleteAct(int position){
        attivitaOK.remove(position);
        notifyItemChanged(position);
    }

    public Attività getAct(int position){
        return attivitaOK.get(position);
    }

    public void setAct(Attività act){
        attivitaOK.add(0,act);
        notifyItemInserted(0);
    }

    @Override
    public AppuntiAdapter.AppuntiVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.appunti_item,parent,false);
        return new AppuntiVH(v) ;
    }

    @Override
    public void onBindViewHolder(AppuntiAdapter.AppuntiVH holder, int position) {
        Attività activita= attivitaOK.get(position);
        if(activita.getTipo().equals("Cons. oggetti")){
            holder.titolo.setText(activita.getTipo());
            holder.dataScadenza.setText(activita.getData());
            holder.body.setText(activita.getAppunti());
        }
    }

    @Override
    public int getItemCount() {
        return attivitaOK.size();
    }

    public class AppuntiVH extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView titolo, dataScadenza,body;

        public AppuntiVH(View itemview){
            super(itemview);
            titolo=(TextView) itemview.findViewById(R.id.titolo_appunti);
            dataScadenza=(TextView) itemview.findViewById(R.id.data_appunti);
            body=(TextView) itemview.findViewById(R.id.descrizione_appunti);
            body.setOnLongClickListener(this);
            titolo.setOnLongClickListener(this);
            dataScadenza.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            posizione=getAdapterPosition();
            ok=true;
            System.out.println("ok");
            ((AppuntiActivity)v.getContext()).mActionMode= ((AppuntiActivity) v.getContext()).startSupportActionMode(((AppuntiActivity) v.getContext()).mActionModeCallback);
            v.setSelected(true);
            return ok;
                }
            }
        }

