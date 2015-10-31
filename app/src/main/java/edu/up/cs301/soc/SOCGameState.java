package edu.up.cs301.soc;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by oney18 on 10/27/2015.
 */
public class SOCGameState extends GameState {
    private int playersID; //ID of the player whose turn it is
    private int score0; //player 0's score
    private int score1; //player 1's score
    private int score2; //player 2's score
    private int score3; //player 3's score
    private int die1; //the red die
    private int die2; //the yellow die
    private int robber; //where the robber is
    private Road[] roads; //all the road spots
    private Tile[] tiles; //all the tiles
    private Building[] buildings; //all the building spots
    private Hand[] hands; //the players' hands
    private int[][] roadRoadAdjacencyList = {{1, 6}, {0, 2, 7}, {1, 3, 7}, {2, 4, 8}, {3, 5, 8}, {4, 9}, {0, 10, 11}, {1, 2, 12, 13}, {3, 4, 14, 15}, {5, 16, 17}, {6, 11, 18}, {6, 10, 12, 19}, {7, 11, 13, 19}, {7, 12, 14, 20}, {8, 13, 15, 20}, {8, 14, 16, 21}, {9, 15, 17, 21}, {9, 16, 22}, {10, 23, 24}, {11, 12, 25, 26}, {13, 14, 27, 28}, {15, 16, 29, 30}, {17, 31, 32}, {18, 24, 33}, {18, 23, 25, 34}, {19, 24, 26, 34}, {19, 25, 27, 35}, {20, 26, 28, 35}, {20, 27, 29, 36}, {21, 28, 30, 36}, {21, 29, 31, 37}, {22, 30, 32, 37}, {22, 31, 38}, {23, 39}, {24, 25, 40, 41}, {26, 27, 42, 43}, {28, 29, 44, 45}, {30, 31, 46, 47}, {32, 48}, {33, 40, 49}, {34, 39, 41, 49}, {34, 40, 42, 50}, {35, 41, 43, 50}, {35, 42, 44, 51}, {36, 43, 45, 51}, {36, 44, 46, 52}, {37, 45, 47, 52}, {37, 46, 48, 53}, {38, 47, 53}, {39, 40, 54}, {41, 42, 55, 56}, {43, 44, 57, 58}, {45, 46, 59, 60}, {47, 48, 61}, {49, 55, 62}, {50, 54, 56, 62}, {50, 55, 57, 63}, {51, 56, 58, 63}, {51, 57, 59, 64}, {52, 58, 60, 64}, {52, 59, 61, 65}, {53, 60, 65}, {54, 55, 66}, {56, 57, 67, 68}, {58, 59, 69, 70}, {60, 61, 71}, {62, 67}, {63, 66, 68}, {63, 67, 69}, {64, 68, 70}, {64, 69, 71}, {65, 70}};
    private int[][] roadBuildingAdjacencyList = {{0, 1}, {2, 1}, {2, 3}, {4, 3}, {4, 5}, {6, 5}, {8, 0}, {10, 2}, {12, 4}, {14, 6}, {7, 8}, {9, 8}, {9, 10}, {11, 10}, {11, 12}, {13, 12}, {13, 14}, {15, 14}, {17, 7}, {19, 9}, {21, 11}, {23, 13}, {25, 15}, {16, 17}, {18, 17}, {18, 19}, {20, 19}, {20, 21}, {22, 21}, {22, 23}, {24, 23}, {24, 25}, {26, 25}, {27, 16}, {29, 18}, {31, 20}, {33, 22}, {35, 24}, {37, 26}, {28, 27}, {28, 29}, {30, 29}, {30, 31}, {32, 31}, {32, 33}, {34, 33}, {34, 35}, {36, 35}, {36, 37}, {38, 28}, {40, 30}, {42, 32}, {44, 34}, {46, 36}, {39, 38}, {39, 40}, {41, 40}, {41, 42}, {43, 42}, {43, 44}, {45, 44}, {45, 46}, {47, 39}, {49, 41}, {51, 43}, {53, 45}, {48, 47}, {48, 49}, {50, 49}, {50, 51}, {52, 51}, {52, 53}};

    private Random rng = new Random();

    public SOCGameState()
    {
        playersID = 0;
        score0 = 0;
        score1 = 0;
        score2 = 0;
        score3 = 0;
        die1 = 1;
        die2 = 1;
        robber = 9;

        //Initialize all roads on the board
        roads = new Road[72];
        for(int i = 0; i < roads.length; i++)
        {
            roads[i] = new Road(i, roadRoadAdjacencyList[i], roadBuildingAdjacencyList[i]);
        }

        //Initialize all tiles on the board
        tiles = new Tile[19];
        for(int i = 0; i < tiles.length; i++)
        {
            //tiles[i] = new Tile(i, );
        }

        //Initialize all buildings on the board
        buildings = new Building[54];
        for(int i = 0; i < buildings.length; i++)
        {
           // buildings[i] = new Building(i, );
        }

        //Initialize all the players hands
        hands = new Hand[4];
        for(int i = 0; i < hands.length; i++)
        {
            hands[i] = new Hand();
        }
    }

    public SOCGameState(int ID, int score0, int score1, int score2, int score3, int die1, int die2,
                        int robber, Road[] roads, Tile[] tiles, Building[] buildings, Hand[] hands)
    {
        this.playersID = ID;
        this.score0 = score0;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.die1 = die1;
        this.die2 = die2;
        this.roads = roads;
        this.tiles = tiles;
        this.buildings = buildings;
        this.hands = hands;
    }

    public SOCGameState(SOCGameState soc){
        this(soc.getPlayersID(), soc.getScore0(), soc.getScore1(), soc.getScore2(), soc.getScore3(),
                soc.getDie1(), soc.getDie2(), soc.getRobber(), soc.getRoads(), soc.getTiles(), soc.getBuildings(),
                soc.getHands());
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

    public int getScore2()
    {
        return score2;
    }

    public int getScore3()
    {
        return score3;
    }

    public int getDie1()
    {
        return die1;
    }

    public int getDie2()
    {
        return die2;
    }

    public void roll()
    {
        die1 = rng.nextInt(6) + 1;
        die2 = rng.nextInt(6) + 1;

        if(die1 + die2 == 7)
        {
            //do robber stuff
        }
    }

    public int getRobber()
    {
        return robber;
    }

    public Road[] getRoads()
    {
        return roads;
    }

    public Tile[] getTiles()
    {
        return tiles;
    }

    public Building[] getBuildings()
    {
        return buildings;
    }

    public Hand[] getHands()
    {
        return hands;
    }

    public void buildRoad(int spot)
    {
        if(!roads[spot].isEmpty())
        {
            return; //something is there!
        }
        if(hands[playersID].getBricks() < 1 || hands[playersID].getWood() < 1)
        {
            return; //lacking resources!
        }
    }

    public void buildSettlement(int spot)
    {
        if(!buildings[spot].isEmpty())
        {
            return; //something is there!
        }
        if(hands[playersID].getWheats() < 1 || hands[playersID].getSheep() < 1 || hands[playersID].getWood() < 1
                || hands[playersID].getBricks() < 1)
        {
            return; //lacking resources!
        }
    }
}
