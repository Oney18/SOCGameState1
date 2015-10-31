package edu.up.cs301.pig;

import edu.up.cs301.game.Game;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * Created by oney18 on 10/15/2015.
 */
public class PigComputerPlayer1 extends PigComputerPlayer
{
    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer1(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState myGameState;
        if (info instanceof PigGameState)
        {
            myGameState = (PigGameState) info;
        }
        else
        {
            return;
        }

        if(myGameState.getRunningScore()<=0) {
            game.sendAction(new PigRollAction(this));
            return;
        }
        if(myGameState.getScore1()+myGameState.getRunningScore()>=50)
        {
            game.sendAction(new PigHoldAction(this));
            return;
        }
        if(myGameState.getRunningScore()>=10)
        {
            game.sendAction(new PigHoldAction(this));
            return;
        }
        game.sendAction(new PigRollAction(this));
    }
}
