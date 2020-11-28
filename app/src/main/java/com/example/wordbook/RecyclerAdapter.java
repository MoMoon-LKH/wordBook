package com.example.wordbook;


import android.content.Context;
import android.content.Intent;
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
     private Context mContext;

    RecyclerAdapter(Context mContext){
        this.mContext = mContext;
    }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wordlist, parent, false);
                return new ItemViewHolder(view,viewType);

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
        Button btnStart;
        TextView textWordList;

        ItemViewHolder(View itemView, int viewType){
            super(itemView);
            textWordList = itemView.findViewById(R.id.textWordList);
            btnStart = itemView.findViewById(R.id.btnStart);

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,wordScreen.class);
                    intent.putExtra("pos",getAdapterPosition());
                    intent.putExtra("txt",textWordList.getText().toString());
                    mContext.startActivity(intent);
                }
            });

        }

        public void onBind(listData data) {
            textWordList.setText(data.getWordBook());
            btnStart.setText("시작");


        }


    }

}
