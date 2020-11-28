package com.example.wordbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordRecyclerAdapter extends RecyclerView.Adapter<WordRecyclerAdapter.ItemViewHolder> {
        private ArrayList<Word> words = new ArrayList<Word>();
        int i = 0;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my, parent, false);
        return  new ItemViewHolder(view,viewType);

    }

    void itemAdd(Word word){
        words.add(word);
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

        public void onBind(Word word) {
            engTxt.setText(word.getEngWord());
            krTxt.setText(word.getKrWord());

        }
    }
}
