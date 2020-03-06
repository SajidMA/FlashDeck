package com.example.flashdeck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

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
        final ImageView add = (ImageView) findViewById(R.id.add_question);
        final ImageView edit = (ImageView) findViewById(R.id.edit_question);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question", ((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("answer1", ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString());
                intent.putExtra("answer2", ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString());
                intent.putExtra("answer3", ((TextView) findViewById(R.id.flashcard_answer3)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        final TextView q1 = (TextView) findViewById(R.id.flashcard_question);
        final TextView a1 = (TextView) findViewById(R.id.flashcard_answer1);
        final TextView a2 = (TextView) findViewById(R.id.flashcard_answer2);
        final TextView a3 = (TextView) findViewById(R.id.flashcard_answer3);

        if (requestCode == 100 && resultCode == RESULT_OK)
        { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer1 = data.getExtras().getString("answer1");
            String answer2 = data.getExtras().getString("answer2");
            String answer3 = data.getExtras().getString("answer3");
            q1.setText(question);
            a1.setText(answer1);
            a2.setText(answer2);
            a3.setText(answer3);
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully created!", Snackbar.LENGTH_SHORT).show();
        }
    }
}