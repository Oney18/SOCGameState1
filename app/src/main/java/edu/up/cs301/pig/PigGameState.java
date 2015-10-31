package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by oney18 on 10/15/2015.
 */
public class PigGameState extends GameState {
    private int playersID; //ID of the player whose turn it is
    private int score0; //player 0's score
    private int score1; //player 1's score
    private int runningScore; //current score that would be won if hold is selected
    private int die; //die's current value
    private Random rng = new Random();

    public PigGameState()
    {
        playersID = 0;
        score0 = 0;
        score1 = 0;
        runningScore = 0;
        die = 1;
    }

    public PigGameState(int ID, int score0, int score1, int runScore, int die)
    {
        this.playersID = ID;
        this.score0 = score0;
        this.score1 = score1;
        this.runningScore = runScore;
        this.die = die;
    }

    public PigGameState(PigGameState p){
        this(p.getPlayersID(), p.getScore0(), p.getScore1(), p.getRunningScore(), p.getDie());
    }

    public int getPlayersID()
    {
        return playersID;
    }

    public int getScore0()
    {
        return score0;
    }

    public int getScore1()
    {
        return score1;
    }

    public int getRunningScore()
    {
        return runningScore;
    }

    public int getDie()
    {
        return die;
    }

    public void hold()
    {
        switch(this.getPlayersID())
        {
            case 0:
                score0 += runningScore;
                runningScore = 0;
                playersID = 1;
                break;

            case 1:
                score1 += runningScore;
                runningScore = 0;
                playersID = 0;
                break;
        }
    }

    public void roll()
    {
        die = rng.nextInt(6) + 1;

        if(die == 1)
        {
            runningScore = 0;
            playersID = 1 - playersID;
        }
        else
        {
            runningScore += die;
        }
    }
}
