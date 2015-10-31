package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigLocalGame extends LocalGame {

    private PigGameState gameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        gameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    public boolean canMove(int playerIdx) {
        return(playerIdx == gameState.getPlayersID());
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    public boolean makeMove(GameAction action) {

        if(this.canMove(this.getPlayerIdx(action.getPlayer()))) {
            if (action instanceof PigHoldAction) {
                gameState.hold();
                return true;
            } else if (action instanceof PigRollAction) {
                gameState.roll();
                return true;
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copy = new PigGameState(gameState);
        p.sendInfo(copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    public String checkIfGameOver() {
        if(gameState.getScore0() >= 50)
        {
            return "Player 1 wins!";
        }
        else if(gameState.getScore1() >= 50)
        {
            return "Player 2 wins!";
        }
        return null;
    }

}// class PigLocalGame
