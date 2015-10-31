package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.BufferedReader;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
  /*  public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    *//**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     *//*
    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof PigGameState) {
            PigGameState myPigGameState = (PigGameState) info;
            playerScoreTextView.setText(""+myPigGameState.getScore0());
            oppScoreTextView.setText(""+myPigGameState.getScore1());
            turnTotalTextView.setText(""+myPigGameState.getRunningScore());

            switch (myPigGameState.getDie()) {
                case 2:
                    dieImageButton.setImageResource(R.drawable.face2);
                    break;
                case 3:
                    dieImageButton.setImageResource(R.drawable.face3);
                    break;
                case 4:
                    dieImageButton.setImageResource(R.drawable.face4);
                    break;
                case 5:
                    dieImageButton.setImageResource(R.drawable.face5);
                    break;
                case 6:
                    dieImageButton.setImageResource(R.drawable.face6);
                    break;
                default:
                    dieImageButton.setImageResource(R.drawable.face1);
                    break;
            }
        }

    }//receiveInfo

    *//**
     * this method gets called when the user clicks the '+' or '-' button. It
     * creates a new CounterMoveAction to return to the parent activity.
     *
     * @param button
     * 		the button that was clicked
     *//*
    public void onClick(View button) {
        if(button.equals(holdButton))
        {
            game.sendAction(new PigHoldAction(this));
        }
        else if(button.equals(dieImageButton))
        {
            game.sendAction(new PigRollAction(this));
        }

    }// onClick

    *//**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     *//*
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui



}// class CounterHumanPlayer

*/