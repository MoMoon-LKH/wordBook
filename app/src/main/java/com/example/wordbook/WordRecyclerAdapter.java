package com.example.wordbook;

import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordRecyclerAdapter extends RecyclerView.Adapter<WordRecyclerAdapter.ItemViewHolder> {
        private ArrayList<Word> words = new ArrayList<Word>();
        private ArrayList<String> selectPos = new ArrayList<>();
        boolean deleteChecked = false;


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my, parent, false);
        return  new ItemViewHolder(view,viewType);

    }

    void itemAdd(Word word){
        words.add(word);
    }

    void setDeleteChecked(boolean deleteChecked){
        this.deleteChecked = deleteChecked;
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.onBind(words.get(position));

        holder.itemView.setTag(words.get(position).getEngWord());
        if(selectPos.contains(words.get(position).getEngWord())){
            Log.d("view",""+words.get(position).getEngWord());
            holder.itemView.setBackgroundColor(Color.GRAY);
        }else
            holder.itemView.setBackgroundColor(Color.WHITE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = v.getTag().toString();
                if(deleteChecked && (selectPos.contains(word) == false)){
                    selectPos.add(word);
                }else if(deleteChecked){
                    selectPos.remove(word);
                }
                notifyDataSetChanged();
            }

        });

        if(deleteChecked == false){
            holder.itemView.setBackgroundColor(Color.WHITE);
            selectPos.clear();
        }

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
