package com.example.flashdeck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.wajahatkarim3.roomexplorer.RoomExplorer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        Log.d("Main Activity", String.valueOf(allFlashcards.size()));

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
            ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
        }

        else {
            ((TextView) findViewById(R.id.flashcard_question)).setText("Add a card!");
            ((TextView) findViewById(R.id.flashcard_answer1)).setText("");
            ((TextView) findViewById(R.id.flashcard_answer2)).setText("");
            ((TextView) findViewById(R.id.flashcard_answer3)).setText("");
        }

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
                currentCardDisplayedIndex = allFlashcards.size();
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
                MainActivity.this.startActivityForResult(intent, 200);
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#f8c471"));
                a3.setBackgroundColor(Color.parseColor("#f8c471"));
            }
        });

        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                if (allFlashcards.size() > 0 && allFlashcards != null)
                {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex - 1).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex - 1).getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex - 1).getWrongAnswer2());
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex - 1).getAnswer());
                    a1.setBackgroundColor(Color.parseColor("#f8c471"));
                    a2.setBackgroundColor(Color.parseColor("#f8c471"));
                    a3.setBackgroundColor(Color.parseColor("#f8c471"));
                }

                else
                {
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Add a card!");
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText("");
                    a1.setBackgroundColor(Color.parseColor("#f8c471"));
                    a2.setBackgroundColor(Color.parseColor("#f8c471"));
                    a3.setBackgroundColor(Color.parseColor("#f8c471"));
                }
            }
        });

        findViewById(R.id.show_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomExplorer.show(MainActivity.this, AppDatabase.class, "flashcard-database");
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
            flashcardDatabase.insertCard(new Flashcard(question, answer1, answer2, answer3));
            allFlashcards = flashcardDatabase.getAllCards();
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully created!", Snackbar.LENGTH_SHORT).show();
        }

        if (requestCode == 200 && resultCode == RESULT_OK)
        { // this 200 needs to match the 200 we used when we called startActivityForResult!
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer1 = data.getExtras().getString("answer1");
            String answer2 = data.getExtras().getString("answer2");
            String answer3 = data.getExtras().getString("answer3");
            q1.setText(question);
            a1.setText(answer1);
            a2.setText(answer2);
            a3.setText(answer3);
            flashcardDatabase.updateCard(new Flashcard(question, answer1, answer2, answer3));
            allFlashcards = flashcardDatabase.getAllCards();
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully edited!", Snackbar.LENGTH_SHORT).show();
        }
    }
}