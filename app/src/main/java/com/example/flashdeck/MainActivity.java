package com.example.flashdeck;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.plattysoft.leonids.ParticleSystem;
import com.wajahatkarim3.roomexplorer.RoomExplorer;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    private Flashcard cardToEdit;

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
                View answerSideView = findViewById(R.id.flashcard_answer1);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                anim.setDuration(500);
                anim.start();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#e74c3c"));
                a3.setBackgroundColor(Color.parseColor("#229954"));
                View answerSideView = findViewById(R.id.flashcard_answer2);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                anim.setDuration(500);
                anim.start();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#f8c471"));
                a3.setBackgroundColor(Color.parseColor("#229954"));
                View answerSideView = findViewById(R.id.flashcard_answer3);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                anim.setDuration(500);
                anim.start();

                new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(findViewById(R.id.flashcard_answer3), 100);
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

                a1.setBackgroundColor(Color.parseColor("#f8c471"));
                a2.setBackgroundColor(Color.parseColor("#f8c471"));
                a3.setBackgroundColor(Color.parseColor("#f8c471"));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allFlashcards.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                    intent.putExtra("question", ((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                    intent.putExtra("answer1", ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString());
                    intent.putExtra("answer2", ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString());
                    intent.putExtra("answer3", ((TextView) findViewById(R.id.flashcard_answer3)).getText().toString());
                    cardToEdit = allFlashcards.get(currentCardDisplayedIndex);
                    MainActivity.this.startActivityForResult(intent, 200);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);

                    a1.setBackgroundColor(Color.parseColor("#f8c471"));
                    a2.setBackgroundColor(Color.parseColor("#f8c471"));
                    a3.setBackgroundColor(Color.parseColor("#f8c471"));
                }
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_answer1).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_answer2).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_answer3).startAnimation(leftOutAnim);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                        findViewById(R.id.flashcard_answer1).startAnimation(rightInAnim);
                        findViewById(R.id.flashcard_answer2).startAnimation(rightInAnim);
                        findViewById(R.id.flashcard_answer3).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                // advance our pointer index so we can show the next card
                //currentCardDisplayedIndex++;
                currentCardDisplayedIndex = MainActivity.this.getRandomNumber(0, allFlashcards.size()-1);

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                if(allFlashcards.size() > 0) {
                    // set the question and answer TextViews with data from the database
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                    a1.setBackgroundColor(Color.parseColor("#f8c471"));
                    a2.setBackgroundColor(Color.parseColor("#f8c471"));
                    a3.setBackgroundColor(Color.parseColor("#f8c471"));
                }
            }
        });

        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                if(currentCardDisplayedIndex < 1) { currentCardDisplayedIndex = 1; }

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

    // returns a random number between minNumber and maxNumber, inclusive.
    // for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
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
            currentCardDisplayedIndex++;
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

            cardToEdit.setQuestion(question);
            cardToEdit.setAnswer(answer3);
            cardToEdit.setWrongAnswer1(answer1);
            cardToEdit.setWrongAnswer2(answer2);
            flashcardDatabase.updateCard(cardToEdit);
            allFlashcards = flashcardDatabase.getAllCards();
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully edited!", Snackbar.LENGTH_SHORT).show();
        }
    }
}