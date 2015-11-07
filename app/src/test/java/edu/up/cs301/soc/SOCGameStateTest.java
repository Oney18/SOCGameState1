package edu.up.cs301.soc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by oney18 on 11/6/2015.
 */
public class SOCGameStateTest {

    @Test
    //see testRoll
    public void testDistributeResources() throws Exception {

    }

    @Test
    //see testRoll
    public void testGivePlayersCards() throws Exception {

    }

    @Test
    //Also tests distributeResources and givePlayersCards as both methods are called from roll only
    public void testRoll() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        int initRoll = soc.getRoll();

        while (initRoll == soc.getRoll())
        {
            soc.roll();
        }

        assertNotEquals(initRoll, soc.getRoll());

        //Tests the distributing features of roll
        soc.generateBuilding(22, 0, Building.CITY); //player 0 city at spot 22
        soc.generateBuilding(40, 1, Building.SETTLEMENT); //player 1 settlement at spot 40
        soc.generateBuilding(19, 2, Building.CITY); //player 2 settlement at spot 19
        soc.generateBuilding(11, 3, Building.SETTLEMENT); //player 3 city at spot 11

        soc.generateRoll(2, 3); //3 gets 1 brick, 0 gets 2 bricks, 1 gets 1 rock
        Hand[] testHand = soc.getHands();

        int[] testTots = new int[4]; //gets totals to make sure nothing else happened to other resources
        for(int i = 0; i < testTots.length; i++)
        {
            testTots[i] = testHand[i].getTotal();
        }

        assertEquals(testHand[0].getBricks(), 2);
        assertEquals(testTots[0], 2);
        assertEquals(testHand[3].getBricks(), 1);
        assertEquals(testTots[3], 1);
        assertEquals(testHand[1].getRocks(), 1);
        assertEquals(testTots[1], 1);


        soc.generateRoll(1, 2); //2 gets 2 wood
        testHand = soc.getHands();
        for(int i = 0; i < testTots.length; i++)
        {
            testTots[i] = testHand[i].getTotal();
        }
        //repeat tests to make sure nothing changed
        assertEquals(testHand[0].getBricks(), 2);
        assertEquals(testTots[0], 2);
        assertEquals(testHand[3].getBricks(), 1);
        assertEquals(testTots[3], 1);
        assertEquals(testHand[1].getRocks(), 1);
        assertEquals(testTots[1], 1);
        //new test(s)
        assertEquals(testHand[2].getWood(), 2);
        assertEquals(testTots[2], 2);

        soc.generateRoll(1, 1); //nobody is on the 2, nothing should change
        testHand = soc.getHands();
        for(int i = 0; i < testTots.length; i++)
        {
            testTots[i] = testHand[i].getTotal();
        }
        //repeats all tests to make sure nothing changed
        assertEquals(testHand[0].getBricks(), 2);
        assertEquals(testTots[0], 2);
        assertEquals(testHand[3].getBricks(), 1);
        assertEquals(testTots[3], 1);
        assertEquals(testHand[1].getRocks(), 1);
        assertEquals(testTots[1], 1);
        assertEquals(testHand[2].getWood(), 2);
        assertEquals(testTots[2], 2);

    }

    @Test
    public void testMoveRobber() throws Exception {

    }

    @Test
    public void testRemoveResources() throws Exception {
        SOCGameState soc = new SOCGameState(4);
        Hand[] testHands;
        //Player 0 now has 10 of everything
        soc.givePlayerResources(0);
        testHands = soc.getHands();
        assertEquals(testHands[0].getWood(), 10);
        assertEquals(testHands[0].getSheep(), 10);
        assertEquals(testHands[0].getWheats(), 10);
        assertEquals(testHands[0].getBricks(), 10);
        assertEquals(testHands[0].getRocks(), 10);
        assertEquals(testHands[0].getTotal(), 50);

        //Remove resources from 0's hand
        soc.removeResources(3, 4, 5, 6, 7);
        testHands = soc.getHands();

        assertEquals(testHands[0].getWood(), 7);
        assertEquals(testHands[0].getSheep(), 6);
        assertEquals(testHands[0].getWheats(), 5);
        assertEquals(testHands[0].getBricks(), 4);
        assertEquals(testHands[0].getRocks(), 3);
        assertEquals(testHands[0].getTotal(), 25);
    }

    @Test
    public void testBuildRoad() throws Exception {
        SOCGameState soc = new SOCGameState(4);
        Road[] testRoads;
        Hand[] testHands;

        soc.givePlayerResources(0);
        soc.buildRoad(35); //should be unable to as nothing exists on the board
        testRoads = soc.getRoads();
        testHands = soc.getHands();
        assertEquals(testRoads[35].isEmpty(), true); //nothing built in spot
        assertEquals(testHands[0].getTotal(), 50); //nothing should have been spent

        soc.generateBuilding(33, 0, Building.SETTLEMENT); //0 has a settlement at spot 33
        soc.generateRoad(44, 0); //0 has a road at spot 44, simulating starting conditions
        soc.buildRoad(51); //adjacent to spot 33, should be built

        testRoads = soc.getRoads();
        testHands = soc.getHands();
        assertEquals(testRoads[51].isEmpty(), false); //road in spot
        assertEquals(testHands[0].getTotal(), 48); //2 resources spent, 1 wood 1 brick
        assertEquals(testHands[0].getWood(), 9);
        assertEquals(testHands[0].getBricks(), 9);

        soc.generateRoad(57, 1); //1 has a road at 57, is adjacent to 51
        soc.buildRoad(57); //0 tries to build a road where 1 already has a ro


    }

    @Test
    public void testBuildSettlement() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        soc.givePlayerResources(0);

        //TODO: initialize conditions for where can build, i.e. add method to generate buildings in gamestate for now
    }

    @Test
    public void testUpgradeSettlement() throws Exception {
        SOCGameState soc = new SOCGameState(4);

        soc.givePlayerResources(0);

        //TODO: initialize conditions for where can build, i.e. add method to generate buildings in gamestate for now
    }

    @Test
    //Tests the passing of turns to players, uses 4 for this case
    public void testEndTurn() throws Exception {
        //Test turn progression for 4 players
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

        //Test turn progression for 3 players
        SOCGameState soc1 = new SOCGameState(3);

        assertEquals(soc1.getPlayersID(), 0);

        soc1.endTurn();
        assertEquals(soc1.getPlayersID(), 1);

        soc1.endTurn();
        assertEquals(soc1.getPlayersID(), 2);

        soc1.endTurn();
        assertEquals(soc1.getPlayersID(), 0);
    }
}