package com.example.flashdeck;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView q1 = (TextView) findViewById(R.id.flashcard_question);
        final TextView a1 = (TextView) findViewById(R.id.flashcard_answer1);
        final TextView a2 = (TextView) findViewById(R.id.flashcard_answer2);
        final TextView a3 = (TextView) findViewById(R.id.flashcard_answer3);
        final ImageView hide = (ImageView) findViewById(R.id.toggle_choices_hide);
        final ImageView show = (ImageView) findViewById(R.id.toggle_choices_show);


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setBackgroundColor(Color.parseColor("#e74c3c"));
                a2.setBackgroundColor(Color.parseColor("#f8c471"));
                a3.setBackgroundColor(Color.parseColor("#229954"));
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#e74c3c"));
                a3.setBackgroundColor(Color.parseColor("#229954"));
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#f8c471"));
                a3.setBackgroundColor(Color.parseColor("#229954"));
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setVisibility(View.INVISIBLE);
                a2.setVisibility(View.INVISIBLE);
                a3.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.INVISIBLE);
            }
        });
    }
}
