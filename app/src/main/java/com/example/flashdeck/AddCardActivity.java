package com.example.flashdeck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        final ImageView close = (ImageView) findViewById(R.id.close_activity);
        final ImageView save = (ImageView) findViewById(R.id.save_question);

        final String question = getIntent().getStringExtra("question");
        String answer1 = getIntent().getStringExtra("answer1");
        String answer2 = getIntent().getStringExtra("answer2");
        String answer3 = getIntent().getStringExtra("answer3");
        final EditText q1 = (EditText) findViewById(R.id.new_question);
        final EditText a1 = (EditText) findViewById(R.id.new_answer1);
        final EditText a2 = (EditText) findViewById(R.id.new_answer2);
        final EditText a3 = (EditText) findViewById(R.id.new_answer3);
        q1.setText(question);
        a1.setText(answer1);
        a2.setText(answer2);
        a3.setText(answer3);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((EditText) findViewById(R.id.new_question)).getText().toString().isEmpty() || ((EditText) findViewById(R.id.new_answer1)).getText().toString().isEmpty() || ((EditText) findViewById(R.id.new_answer2)).getText().toString().isEmpty() || ((EditText) findViewById(R.id.new_answer3)).getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Must enter all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra("question", ((EditText) findViewById(R.id.new_question)).getText().toString());
                data.putExtra("answer1", ((EditText) findViewById(R.id.new_answer1)).getText().toString());
                data.putExtra("answer2", ((EditText) findViewById(R.id.new_answer2)).getText().toString());
                data.putExtra("answer3", ((EditText) findViewById(R.id.new_answer3)).getText().toString());
                setResult(RESULT_OK, data);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
    }
}