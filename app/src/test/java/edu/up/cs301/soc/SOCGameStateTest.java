package edu.up.cs301.soc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by oney18 on 11/6/2015.
 */
public class SOCGameStateTest {

    @Test
    public void testDistributeResources() throws Exception {
        //see below
    }

    @Test
    // Also tests distributeResources as this method is called within distributeResources
    public void testGivePlayersCards() throws Exception {

    }

    @Test
    public void testRoll() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        int initRoll = soc.getRoll();

        while (initRoll == soc.getRoll())
        {
            soc.roll();
        }

        assertNotEquals(initRoll, soc.getRoll());
    }

    @Test
    public void testMoveRobber() throws Exception {

    }

    @Test
    public void testRemoveResources() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        //Player 0 now has 10 of everything
        soc.givePlayerResources();

        soc.removeResources(3, 4, 5, 6, 7);
        Hand[] testHands = soc.getHands();

        //TODO: finish this


    }

    @Test
    public void testBuildRoad() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        soc.givePlayerResources();

        //TODO: initialize conditions for where can build, i.e. add method to generate buildings in gamestate for now
    }

    @Test
    public void testBuildSettlement() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        soc.givePlayerResources();

        //TODO: initialize conditions for where can build, i.e. add method to generate buildings in gamestate for now
    }

    @Test
    public void testUpgradeSettlement() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        soc.givePlayerResources();

        //TODO: initialize conditions for where can build, i.e. add method to generate buildings in gamestate for now
    }

    @Test
    //Tests the passing of turns to players, uses 4 for this case
    public void testEndTurn() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        assertEquals(soc.getPlayersID(), 0);

        soc.endTurn();
        assertEquals(soc.getPlayersID(), 1);

        soc.endTurn();
        assertEquals(soc.getPlayersID(), 2);

        soc.endTurn();
        assertEquals(soc.getPlayersID(), 3);

        soc.endTurn();
        assertEquals(soc.getPlayersID(), 0);
    }
}