package com.example.hw_01_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    public static final String EXTRA_LION_SCORE = "EXTRA_LION_SCORE";
    public static final String EXTRA_PANDA_SCORE = "EXTRA_PANDA_SCORE";
    public static final String EXTRA_PANDA_ID = "EXTRA_PANDA_ID";
    public static final String EXTRA_LION_ID = "EXTRA_BOTH_ID";

    private TextView winnerLBL;
    private ImageView winnerIMG;
    private TextView winnerSCORE;
    private TextView secondLBL;
    private TextView secondSCORE;
    private Button close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        initializeViews();
        setPage();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * Get data from the main activity.
     * Set the Views items according to the scores.
     */
    private void setPage() {
        int lionScore = getIntent().getIntExtra(EXTRA_LION_SCORE, -1);
        int pandaScore = getIntent().getIntExtra(EXTRA_PANDA_SCORE, -1);
        String name="";
        int score=lionScore;
        if(lionScore > pandaScore){
            name = "LION";
            winnerIMG.setImageResource(getIntent().getIntExtra(EXTRA_LION_ID,-1));
            secondLBL.setText("Second: Panda");
            secondSCORE.setText("Score:" +pandaScore);
        }else if (lionScore < pandaScore){
            name = "PANDA";
            score = pandaScore;
            winnerIMG.setImageResource(getIntent().getIntExtra(EXTRA_PANDA_ID,-1));
            secondLBL.setText("Secode: Lion");
            secondSCORE.setText("Score: "+lionScore);
        }else{
            name = "BOTH";
            secondLBL.setText("");
            secondSCORE.setText("");
        }
        winnerLBL.setText("The winner is "+name+" !");
        winnerSCORE.setText("score: " +score);

        setVisibleViews();
    }

    private void setVisibleViews() {
        winnerLBL.setVisibility(View.VISIBLE);
        winnerSCORE.setVisibility(View.VISIBLE);
        secondLBL.setVisibility(View.VISIBLE);
        secondSCORE.setVisibility(View.VISIBLE);
        winnerIMG.setVisibility(View.VISIBLE);
    }

    private void initializeViews() {
        winnerLBL = findViewById(R.id.score_LBL_winner);
        winnerLBL.setVisibility(View.INVISIBLE);
        winnerSCORE = findViewById(R.id.score_LBL_winnerScore);
        winnerSCORE.setVisibility(View.INVISIBLE);
        secondLBL = findViewById(R.id.score_LBL_second);
        secondLBL.setVisibility(View.INVISIBLE);
        secondSCORE = findViewById(R.id.score_LBL_secondScore);
        secondSCORE.setVisibility(View.INVISIBLE);
        winnerIMG = findViewById(R.id.score_IMG_winner);
        winnerIMG.setVisibility(View.INVISIBLE);
        close = findViewById(R.id.score_BTN_close);
    }


    @Override
    protected void onStart() {
        Log.d("hw_01 score activity:", "onStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("hw_01 score activity:", "onResume");
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
        Log.d("hw_01 score activity:", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("hw_01 score activity:", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("hw_01 score activity:", "onDestroy");
        super.onDestroy();
    }
}