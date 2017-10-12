package com.example.joseph.connect3;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int counter1 = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive)
        {gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1000f);
        if(activePlayer==0)
        {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
            counter1++;

        }
        else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
            counter1++;
        }

        counter.animate().translationYBy(1000f).setDuration(300);


        for (int[] winningPosition:winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    &&gameState[winningPosition[0]]==gameState[winningPosition[2]]
                    &&gameState[winningPosition[0]]!=2){
                String winner= "RED";
                if (gameState[winningPosition[0]]==0){
                     winner = "YELLOW";
                }
                gameActive = false;
                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                TextView textView=(TextView)findViewById(R.id.winnerMessage);
                textView.setText(winner + " has won");
                GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
                gridLayout.animate().alpha(0).setDuration(2000);
                layout.bringToFront();
                layout.setVisibility(view.VISIBLE);


            }else if (counter1==9){
                String winner = "DRAW";
                gameActive = false;
                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                TextView textView=(TextView)findViewById(R.id.winnerMessage);
                textView.setText(winner);
                GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
                gridLayout.animate().alpha(0).setDuration(2000);
                layout.bringToFront();
                layout.setVisibility(view.VISIBLE);


            }

        }
    }}

    public void playAgain(View view){
        gameActive = true;
        counter1 =0;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
        gridLayout.animate().alpha(1).setDuration(2000);
        activePlayer = 0;
        for (int i =0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        for (int i = 0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
