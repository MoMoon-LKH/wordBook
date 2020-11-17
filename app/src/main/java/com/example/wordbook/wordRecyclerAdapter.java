package com.example.wordbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class wordRecyclerAdapter extends RecyclerView.Adapter<wordRecyclerAdapter.ItemViewHolder> {
    private ArrayList<word> words = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myWord, parent, false);
        return  new ItemViewHolder(view,viewType);

    }

    void itemAdd(word words){

    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(words.get(position));

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView engTxt, krTxt;

        ItemViewHolder(View itemView, int viewType){
            super(itemView);
            engTxt = itemView.findViewById(R.id.engTxt);
            krTxt = itemView.findViewById(R.id.krTxt);

        }

        public void onBind(word words) {

        }


    }
}
