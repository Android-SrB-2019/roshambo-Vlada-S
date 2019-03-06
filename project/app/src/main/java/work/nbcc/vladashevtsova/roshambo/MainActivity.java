package work.nbcc.vladashevtsova.roshambo;

//Author: Vlada Shevtsova
//Date:   05-Mar-2019

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView playerChoice;
    private ImageView gameChoice;
    private TextView result;
    Rochambo newGame = new Rochambo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        playerChoice = findViewById(R.id.player_choice);
        gameChoice = findViewById(R.id.game_choice );
        result = findViewById(R.id.result);
    }

    public void playerChoseRock(View view) {
        newGame.playerMakesMove( Rochambo.ROCK );
        playerChoice.setImageResource( R.drawable.rock );
        determineWinner();
    }

    public void playerChosePaper(View view) {
        newGame.playerMakesMove( Rochambo.PAPER );
        playerChoice.setImageResource( R.drawable.paper );
        determineWinner();
    }

    public void playerChoseScissors(View view) {
        newGame.playerMakesMove( Rochambo.SCISSOR );
        playerChoice.setImageResource( R.drawable.scissors );
        determineWinner();
    }

    public void determineWinner(){
        gameChoose();
        result.setText( newGame.winLoseOrDraw() );
        animate();
    }

    public void gameChoose(){
        if (newGame.getGameMove() == Rochambo.ROCK)
            gameChoice.setImageResource( R.drawable.rock  );
        else if (newGame.getGameMove() == Rochambo.PAPER)
            gameChoice.setImageResource( R.drawable.paper  );
        else if (newGame.getGameMove() == Rochambo.SCISSOR)
            gameChoice.setImageResource( R.drawable.scissors  );
    }

    public void animate(){
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerChoice,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(gameChoice,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
