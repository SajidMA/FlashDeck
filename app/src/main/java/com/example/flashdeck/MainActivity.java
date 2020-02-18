package com.example.flashdeck;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView q1 = (TextView) findViewById(R.id.flashcard_question);
        final TextView a1 = (TextView) findViewById(R.id.flashcard_answer);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1.setVisibility(View.INVISIBLE);
                a1.setVisibility(View.VISIBLE);
            }
        });
    }
}
