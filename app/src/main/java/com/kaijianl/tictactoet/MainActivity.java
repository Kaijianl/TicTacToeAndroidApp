package com.kaijianl.tictactoet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // red = 0, yellow = 1
    int activePlayer = 0;

    int[] gameStates = {2,2,2,2,2,2,2,2,2};

    int[][] winners = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},
            {2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void dropin (View view){
        ImageView counter = (ImageView) view;
//        counter.animate().translationYBy(-1500f).setDuration(200);
        int pos = Integer.parseInt(view.getTag().toString());

        if (gameStates[pos] == 2) {
            gameStates[pos] = activePlayer;
            counter.setTranslationY(-1500f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500f).rotation(360f).setDuration(200);

            System.out.println("game"+Arrays.toString(gameStates));

            for (int[] winner: winners){
                if (gameStates[winner[0]] == (gameStates[winner[1]]) &&
                        gameStates[winner[0]] == (gameStates[winner[2]]) &&
                        gameStates[winner[0]] != 2){

                    String player = "red";
                    if (gameStates[winner[0]] == 1){
                        player = "yellow";
                    }
                    TextView whoWins = (TextView)findViewById(R.id.winnerMessage);
                    whoWins.setText(player + " wins !!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain);
                    layout.setAlpha(1f);

                }
            }
        }
    }

    public void restartfunc (View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain);
        layout.setAlpha(0f);
        activePlayer = 0;
        for (int i = 0; i < gameStates.length; i++){
            gameStates[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout2);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
