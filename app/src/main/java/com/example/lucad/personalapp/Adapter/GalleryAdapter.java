package com.example.lucad.personalapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucad.personalapp.Model.Students;
import com.example.lucad.personalapp.R;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
/**
 * Created by LucaD on 08/03/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryVH> {
    ArrayList<Students> studenti=new ArrayList<>();
    public void setStudents(ArrayList<Students> student){
        studenti=student;
    }


    @Override
    public GalleryAdapter.GalleryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item,parent,false);
        return new GalleryVH(v);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.GalleryVH holder, int position) {
        holder.text.setText(studenti.get(position).getNome());
        Glide.with(holder.itemView.getContext())
                .load(studenti.get(position).getUrl())
                .placeholder(R.drawable.ic_library_books_black_24dp)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return studenti.size();
    }

    public class GalleryVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public GalleryVH(View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.image_gallery);
            text=(TextView) itemView.findViewById(R.id.image_name);

        }
    }
}
