package com.example.wordbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private ArrayList<listData> listData = new ArrayList<>(); //wordbook 이름 리스트

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wordlist,parent,false);
        return new ItemViewHolder(view);
    }

    void itemAdd(listData data){
         listData.add(data);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textWordList;
        private Button btnStart;

        ItemViewHolder(View itemView){
            super(itemView);
            textWordList = itemView.findViewById(R.id.textWordList);
            btnStart = itemView.findViewById(R.id.btnStart);
        }

        public void onBind(listData data) {
            textWordList.setText(data.getWordBook());
            btnStart.setText("시작");
        }
    }


}
