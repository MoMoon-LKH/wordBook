package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView wordImg, myImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wordImg = (ImageView) findViewById(R.id.wordImg);
        myImg = (ImageView) findViewById(R.id.myImg);


        wordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.wordImg) {
                    Intent intent = new Intent(getApplicationContext(),wordList.class);
                    startActivity(intent);
                }
            }
        });

        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.myImg) {
                    Intent intent = new Intent(getApplicationContext(), myWord.class);
                    startActivity(intent);
                }
            }
        });


    }
}