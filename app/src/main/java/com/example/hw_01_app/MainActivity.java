package com.example.hw_01_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ImageButton play_button;
    private ImageView Img_cards_panda;
    private ImageView Img_cards_lion;
    private TextView Vscore_panda;
    private TextView Vscore_lion;
    private int score_lion;
    private int score_panda;
    int iterations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("hw_01 app:", "onCreate");
        setContentView(R.layout.activity_main);

        initializeViews();
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_button.setVisibility(View.INVISIBLE);
                play();
            }
        });
    }

    /**
     *
    The play function creates 2 lists and fill them by initializeGame function.
    Then it starts the game.
     */
    private void play() {
        Img_cards_panda.setVisibility(View.VISIBLE);
        Img_cards_lion.setVisibility(View.VISIBLE);
        Vscore_panda.setVisibility(View.VISIBLE);
        Vscore_lion.setVisibility(View.VISIBLE);

        Deck myImageList = new Deck(52);
        Deck panda_cards = new Deck(myImageList.getSize()/2);
        Deck lion_cards = new Deck(myImageList.getSize()/2);

        initializeGame(myImageList,panda_cards,lion_cards);

        game(panda_cards,lion_cards);
    }

    /**
     * Shuffle and Divide the cards.
     * Initialize the scores.
     * @param myImageList - All card
     * @param panda_cards - Gets the panda cards
     * @param lion_cards - Gets the lion cards
     */
    private void initializeGame(Deck myImageList, Deck panda_cards, Deck lion_cards) {
        myImageList.addAllCards(this);
        myImageList.shuffle();
        myImageList.divideInto2(panda_cards,lion_cards);

        iterations=0;
        score_panda=0;
        score_lion=0;
        Vscore_lion.setText("0");
        Vscore_panda.setText("0");
    }

    /**
     *
     * @param panda_cards - list of 26 cards.
     * @param lion_cards - list of 26 cards.
    Game function make 26 iterations of covering card.
    It uses the handler to creating a delay between each iteration.
     When all 26 iterations is over, goes to second activity.
     */
    private void game(Deck panda_cards, Deck lion_cards) {
        final Handler handler = new Handler();
        int delay = 300;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                oneTurn(panda_cards, lion_cards);
                iterations++;
                if(iterations<26)
                    handler.postDelayed(this,delay);
                else openSecondActivity();
            }
        }, delay);
    }

    /**
     *
     *Do one turn, reveals card for each player and increase the score for the higher value.
     * @param panda_cards - Deck of panda player.
     * @param lion_cards - Deck of lion player.
     */
    private void oneTurn(Deck panda_cards, Deck lion_cards) {
        Card cardP = panda_cards.takeCard(0);
        Card cardL = lion_cards.takeCard(0);
        Img_cards_panda.setImageResource(cardP.getIdentifier());
        Img_cards_lion.setImageResource(cardL.getIdentifier());
        if (cardL.getValue() >= cardP.getValue()) {
            score_lion++;
            Vscore_lion.setText(score_lion+"");
        }
        if (cardP.getValue() >= cardL.getValue()) {
            score_panda++;
            Vscore_panda.setText(score_panda+"");
        }
    }

    /**
     *Creates inference to new Activity and saves the relevant data.
     */
    private void openSecondActivity() {
        Intent myIntent = new Intent(this, ScoreActivity.class);
        myIntent.putExtra(ScoreActivity.EXTRA_LION_SCORE,score_lion);
        myIntent.putExtra(ScoreActivity.EXTRA_PANDA_SCORE,score_panda);
        myIntent.putExtra(ScoreActivity.EXTRA_PANDA_ID,getResources().getIdentifier("pandapic", "drawable", getPackageName()));
        myIntent.putExtra(ScoreActivity.EXTRA_LION_ID,getResources().getIdentifier("lionpic", "drawable", getPackageName()));
        startActivity(myIntent);
        finish();
    }

    /**
     * Initialize all views for the start page.
     */
    private void initializeViews() {
        Img_cards_panda = findViewById(R.id.main_IMG_pandaCard);
        Img_cards_lion = findViewById(R.id.main_IMG_lionCard);
        Vscore_panda = findViewById(R.id.main_LBL_scorePanda);
        Vscore_panda.setVisibility(View.INVISIBLE);
        Vscore_lion = findViewById(R.id.main_LBL_scoreLion);
        Vscore_lion.setVisibility(View.INVISIBLE);
        play_button = findViewById(R.id.main_BTN_play);
        play_button.setVisibility(View.VISIBLE);
        play_button.bringToFront();
    }

    @Override
    protected void onStart() {
        Log.d("hw_01 app:", "onStart");
        super.onStart();

    }

    /**
     * Hide the status bar.
     */
    @Override
    protected void onResume() {
        Log.d("hw_01 app:", "onResume");
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)
            throw new AssertionError();
        else actionBar.hide();
    }

    @Override
    protected void onPause() {
        Log.d("hw_01 app:", "onPause");
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.d("hw_01 app:", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("hw_01 app:", "onDestroy");
        super.onDestroy();
    }
}