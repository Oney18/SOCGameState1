package edu.up.cs301.soc;

import java.util.Random;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * SOCGameState
 *
 * This class represents an instance of the game's data.
 * Within it holds all the data for the game of Catan, as well as the methods
 * to interact with the data as a player would do so.
 *
 * @author Jordan Goldey
 * @author Matthew Schneider
 * @author Jarrett Oney
 *
 * @version Nov 2015
 * */
public class SOCGameState extends GameState {
    private int playersID; //ID of the player whose turn it is
    private int numPlayers; //number of players for this game
    private int[] scores; //The scores of each of the players
    private int die1; //the red die
    private int die2; //the yellow die
    private int robber; //where the robber is
    private Road[] roads; //all the road spots
    private Tile[] tiles; //all the tiles
    private Building[] buildings; //all the building spots
    private Hand[] hands; //the players' hands
    private boolean[] robberWasRolled; //denotes if a 7 was rolled before and player has reacted
    public static final int VICTORY_POINTS_TO_WIN = 5;

    //List of all roads adjacent to a given road
    public static final byte[][] roadToRoadAdjList = {{1, 6}, {0, 2, 7}, {1, 3, 7}, {2, 4, 8}, {3, 5, 8}, {4, 9}, {0, 10, 11}, {1, 2, 12, 13}, {3, 4, 14, 15}, {5, 16, 17}, {6, 11, 18}, {6, 10, 12, 19}, {7, 11, 13, 19}, {7, 12, 14, 20}, {8, 13, 15, 20}, {8, 14, 16, 21}, {9, 15, 17, 21}, {9, 16, 22}, {10, 23, 24}, {11, 12, 25, 26}, {13, 14, 27, 28}, {15, 16, 29, 30}, {17, 31, 32}, {18, 24, 33}, {18, 23, 25, 34}, {19, 24, 26, 34}, {19, 25, 27, 35}, {20, 26, 28, 35}, {20, 27, 29, 36}, {21, 28, 30, 36}, {21, 29, 31, 37}, {22, 30, 32, 37}, {22, 31, 38}, {23, 39}, {24, 25, 40, 41}, {26, 27, 42, 43}, {28, 29, 44, 45}, {30, 31, 46, 47}, {32, 48}, {33, 40, 49}, {34, 39, 41, 49}, {34, 40, 42, 50}, {35, 41, 43, 50}, {35, 42, 44, 51}, {36, 43, 45, 51}, {36, 44, 46, 52}, {37, 45, 47, 52}, {37, 46, 48, 53}, {38, 47, 53}, {39, 40, 54}, {41, 42, 55, 56}, {43, 44, 57, 58}, {45, 46, 59, 60}, {47, 48, 61}, {49, 55, 62}, {50, 54, 56, 62}, {50, 55, 57, 63}, {51, 56, 58, 63}, {51, 57, 59, 64}, {52, 58, 60, 64}, {52, 59, 61, 65}, {53, 60, 65}, {54, 55, 66}, {56, 57, 67, 68}, {58, 59, 69, 70}, {60, 61, 71}, {62, 67}, {63, 66, 68}, {63, 67, 69}, {64, 68, 70}, {64, 69, 71}, {65, 70}};

    //List of all the buildings adjacent to a given road
    public static final byte[][] roadToBuildingAdjList = {{0, 1}, {2, 1}, {2, 3}, {4, 3}, {4, 5}, {6, 5}, {8, 0}, {10, 2}, {12, 4}, {14, 6}, {7, 8}, {9, 8}, {9, 10}, {11, 10}, {11, 12}, {13, 12}, {13, 14}, {15, 14}, {17, 7}, {19, 9}, {21, 11}, {23, 13}, {25, 15}, {16, 17}, {18, 17}, {18, 19}, {20, 19}, {20, 21}, {22, 21}, {22, 23}, {24, 23}, {24, 25}, {26, 25}, {27, 16}, {29, 18}, {31, 20}, {33, 22}, {35, 24}, {37, 26}, {28, 27}, {28, 29}, {30, 29}, {30, 31}, {32, 31}, {32, 33}, {34, 33}, {34, 35}, {36, 35}, {36, 37}, {38, 28}, {40, 30}, {42, 32}, {44, 34}, {46, 36}, {39, 38}, {39, 40}, {41, 40}, {41, 42}, {43, 42}, {43, 44}, {45, 44}, {45, 46}, {47, 39}, {49, 41}, {51, 43}, {53, 45}, {48, 47}, {48, 49}, {50, 49}, {50, 51}, {52, 51}, {52, 53}};

    //List of all the roads adjacent to a given building
    public static final byte[][] buildingToRoadAdjList = {{0, 6}, {0, 1}, {1, 2, 7}, {2, 3}, {3, 4, 8}, {4, 5}, {5, 9}, {10, 18}, {10, 11, 6}, {11, 12, 19}, {12, 13, 7}, {13, 14, 20}, {14, 15, 8}, {15, 16, 21}, {16, 17, 9}, {17, 22}, {23, 33}, {23, 24, 18}, {24, 25, 34}, {25, 26, 19}, {26, 27, 35}, {27, 28, 20}, {28, 29, 36}, {29, 30, 21}, {30, 31, 37}, {31, 32, 22}, {32, 38}, {39, 33}, {39, 40, 49}, {40, 41, 34}, {41, 42, 50}, {42, 43, 35}, {43, 44, 51}, {44, 45, 36}, {45, 46, 52}, {46, 47, 37}, {47, 48, 53}, {48, 38}, {54, 49}, {54, 55, 62}, {55, 56, 50}, {56, 57, 63}, {57, 58, 51}, {58, 59, 64}, {59, 60, 52}, {60, 61, 65}, {61, 53}, {66, 62}, {66, 67}, {67, 68, 63}, {68, 69}, {69, 70, 64}, {70, 71}, {71, 65}};

    //List of all buildings adjacent to a given building
    public static final byte[][] buildingToBuildingAdjList = {{1, 8}, {0, 2}, {1, 3, 10}, {2, 4}, {3, 5, 12}, {4, 6}, {5, 14}, {8, 17}, {7, 9, 0}, {8, 10, 19}, {9, 11, 2}, {10, 12, 21}, {11, 13, 4}, {12, 14, 23}, {13, 15, 6}, {14, 25}, {17, 27}, {16, 18, 7}, {17, 19, 29}, {18, 20, 9}, {19, 21, 31}, {20, 22, 11}, {21, 23, 33}, {22, 24, 13}, {23, 25, 35}, {24, 26, 15}, {25, 37}, {28, 16}, {27, 29, 38}, {28, 30, 18}, {29, 31, 40}, {30, 32, 20}, {31, 33, 42}, {32, 34, 22}, {33, 35, 44}, {34, 36, 24}, {35, 37, 46}, {36, 26}, {39, 28}, {38, 40, 47}, {39, 41, 30}, {40, 42, 49}, {41, 43, 32}, {42, 44, 51}, {43, 45, 34}, {44, 46, 53}, {45, 36}, {48, 39}, {47, 49}, {48, 50, 41}, {49, 51}, {50, 52, 43}, {51, 53}, {52, 45}};

    //List of all tiles adjacent to a given building
    public static final byte[][] buildingToTileAdjList = {{0}, {0}, {0, 1}, {1}, {1, 2}, {2}, {2}, {3}, {0, 3}, {0, 3, 4}, {0, 1, 4}, {1, 4, 5}, {1, 2, 5}, {2, 5, 6}, {2, 6}, {6}, {7}, {3, 7}, {3, 7, 8}, {3, 4, 8}, {4, 8, 9}, {4, 5, 9}, {5, 9, 10}, {5, 6, 10}, {6, 10, 11}, {6, 11}, {11}, {7}, {7, 12}, {7, 8, 12}, {8, 12, 13}, {8, 9, 13}, {9, 13, 14}, {9, 10, 14}, {10, 14, 15}, {10, 11, 15}, {11, 15}, {11}, {12}, {12, 16}, {12, 13, 16}, {13, 16, 17}, {13, 14, 17}, {14, 17, 18}, {14, 15, 18}, {15, 18}, {15}, {16}, {16}, {16, 17}, {17}, {17, 18}, {18}, {18}};

    //List of all buildings adjacent to a given tile
    public static final byte[][] tileToBuildingAdjList = {{0, 1, 2, 8, 9, 10}, {2, 3, 4, 10, 11, 12}, {4, 5, 6, 12, 13, 14}, {7, 8, 9, 17, 18, 19}, {9, 10, 11, 19, 20, 21}, {11, 12, 13, 21, 22, 23}, {13, 14, 15, 23, 24, 25}, {16, 17, 18, 27, 28, 29}, {18, 19, 20, 29, 30, 31}, {20, 21, 22, 31, 32, 33}, {22, 23, 24, 33, 34, 35}, {24, 25, 26, 35, 36, 37}, {28, 29, 30, 38, 39, 40}, {30, 31, 32, 40, 41, 42}, {32, 33, 34, 42, 43, 44}, {34, 35, 36, 44, 45, 46}, {39, 40, 41, 47, 48, 49}, {41, 42, 43, 49, 50, 51}, {43, 44, 45, 51, 52, 53}};

    //List of the resource that each tile is
    public static final int[] tileTypes = {Tile.LUMBER, Tile.WOOL, Tile.WHEAT, Tile.BRICK, Tile.ORE, Tile.BRICK, Tile.WOOL, Tile.DESERT, Tile.LUMBER, Tile.WHEAT, Tile.LUMBER, Tile.WHEAT, Tile.BRICK, Tile.WOOL, Tile.WOOL, Tile.ORE, Tile.ORE, Tile.WHEAT, Tile.LUMBER};

    //List of the number tile on each tile
    public static final byte[] rollNums = {11, 12, 9, 4, 6, 5, 10, 0, 3, 11, 4, 8, 8, 10, 9, 3, 5, 2, 6};

    //Random variable for generating random numbers
    private Random rng = new Random();

    //Default constructor for the game state that, sets first player to player0, initializes all
    //players scores to 0, sets both dice values to 1, puts the robber in the desert, and creates all
    //the roads, tiles, buildings, and players hands.
    public SOCGameState(int numPlayers)
    {
        playersID = 0;
        this.numPlayers = numPlayers;
        scores = new int[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            scores[i] = 0;
        }
        die1 = 1;
        die2 = 1;
        robber = 7;

        //Initialize robberWasRolled array
        robberWasRolled = new boolean[numPlayers];
        for(int i = 0; i < robberWasRolled.length; i++)
        {
            robberWasRolled[i] = false;
        }

        //Initialize all roads on the board
        roads = new Road[72];
        for(int i = 0; i < roads.length; i++)
        {
            roads[i] = new Road(i, roadToRoadAdjList[i], roadToBuildingAdjList[i]);
        }

        //Initialize all tiles on the board
        tiles = new Tile[19];
        for(int i = 0; i < tiles.length; i++)
        {
            tiles[i] = new Tile(i, rollNums[i], tileTypes[i], tileToBuildingAdjList[i]);
        }

        //Initialize all buildings on the board
        buildings = new Building[54];
        for(int i = 0; i < buildings.length; i++)
        {
            buildings[i] = new Building(i, buildingToRoadAdjList[i], buildingToTileAdjList[i]);
        }

        //Initialize all the players hands
        hands = new Hand[numPlayers];
        for(int i = 0; i < hands.length; i++)
        {
            hands[i] = new Hand();
        }
    }

    //Constructor to set all instance variables to values passed in as parameters
    public SOCGameState(int ID, int numPlayers, int score0, int score1, int score2, int score3, int die1, int die2,
                        int robber, Road[] roads, Tile[] tiles, Building[] buildings, Hand[] hands,
                        boolean[] robberWasRolled)
    {
        this.playersID = ID;
        this.numPlayers = numPlayers;
        int[] scores = new int[numPlayers];
        scores[0] = score0;
        scores[1] = score1;
        scores[2] = score2;
        scores[3] = score3;
        this.die1 = die1;
        this.die2 = die2;
        this.robber = robber;
        this.roads = roads;
        this.tiles = tiles;
        this.buildings = buildings;
        this.hands = hands;
        this.robberWasRolled = robberWasRolled;
    }

    //Copy constructor to create an identical version of the given game state
    public SOCGameState(SOCGameState soc){
        this(soc.getPlayersID(), soc.getNumPlayers(), soc.getScore0(), soc.getScore1(), soc.getScore2(), soc.getScore3(),
                soc.getDie1(), soc.getDie2(), soc.getRobber(), soc.getRoads(), soc.getTiles(), soc.getBuildings(),
                soc.getHands(), soc.getRobberWasRolled());
    }

    //Method to return the player who's turn it currently is
    public int getPlayersID()
    {
        return playersID;
    }

    //Method to return the number of players in the game
    public int getNumPlayers()
    {
        return  numPlayers;
    }

    //Method to return the score of player 0
    public int getScore0()
    {
        return scores[0];
    }

    //Method to return the score of player 1
    public int getScore1()
    {
        return scores[1];
    }

    //Method to return the score of player 2
    public int getScore2()
    {
        return scores[2];
    }

    //Method to return the score of player 3
    public int getScore3()
    {
        return scores[3];
    }

    //Method to return the value of the first die
    public int getDie1()
    {
        return die1;
    }

    //Method to return the value of the second die
    public int getDie2()
    {
        return die2;
    }
    
    //Method to return the added value of the two dice
    public int getRoll()
    {
        return die1 + die2;
    }

    //Method to distribute a given resource to the buildings that are passed in
    public void distributeResources(byte[] buildList, int resource){
        for (byte j = 0; j < buildList.length; j++) {
            switch (buildings[buildList[j]].getTypeOfBuilding()) {
                case Building.SETTLEMENT: //settlement
                    givePlayersCards(hands[buildings[buildList[j]].getPlayer()], resource, 1);
                    break;
                case Building.CITY: //city
                    givePlayersCards(hands[buildings[buildList[j]].getPlayer()], resource, 2);
                    break;
                default: //empty
                    break;
            }
        }
    }

    //Method to put a given number of a given resource to a given players hand
    public void givePlayersCards(Hand playersHand, int resource, int amount){
        switch (resource){
            case Tile.BRICK:
                playersHand.addBrick(amount);
                break;
            case Tile.WHEAT:
                playersHand.addWheat(amount);
                break;
            case Tile.WOOL:
                playersHand.addWool(amount);
                break;
            case Tile.LUMBER:
                playersHand.addLumber(amount);
                break;
            case Tile.ORE:
                playersHand.addOre(amount);
                break;
            default: //empty
                break;
        }
    }

    //Method to handle rolling the dice and distributing resources
    public void roll()
    {
        //Set dice to random values
        die1 = rng.nextInt(6) + 1;
        die2 = rng.nextInt(6) + 1;

        if(die1 + die2 != 7) //not possible to give res on 7
        {
            for(byte i = 0; i < tiles.length; i++) //check all tiles for the roll number
            {
                if(tiles[i].getRollNumber() == die1 + die2 && i != robber) //a tile matches the roll, dispense res
                {
                    byte[] buildList = tileToBuildingAdjList[i]; //tile's adj spots
                    distributeResources(buildList,tiles[i].getResource());
                }
            }
        }
        else
        {
            for(int i = 0; i < robberWasRolled.length; i++)
            {
                robberWasRolled[i] = true;
            }
        }
    }

    //Method to move robber
    public boolean moveRobber(int spot)
    {

        robber = spot;
        byte[] adjList = tileToBuildingAdjList[spot];

        for(byte i = 0; i < adjList.length; i++)
        {
            if(buildings[adjList[i]].getPlayer() != Building.EMPTY &&
                    buildings[adjList[i]].getPlayer() != playersID &&
                    hands[buildings[adjList[i]].getPlayer()].getTotal() != 0)
            {
                int resourceToSteal = rng.nextInt(5)+1; //adds randomness to the resource selection
                for(int j = 0; j < 6; j++)
                {
                    int type = (j + resourceToSteal) % 6;
                    if(!hands[buildings[adjList[i]].getPlayer()].checkIfEmpty(type))
                    {
                        hands[buildings[adjList[i]].getPlayer()].stealResource(type);
                        hands[playersID].addResource(type);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Method to remove resources from a hand and to set robber rolled to false
    public void removeResources(int woodToLose, int sheepToLose, int wheatToLose, int brickToLose,
                                int rockToLose)
    {
        hands[playersID].removeLumber(woodToLose);
        hands[playersID].removeWool(sheepToLose);
        hands[playersID].removeWheat(wheatToLose);
        hands[playersID].removeBrick(brickToLose);
        hands[playersID].removeOre(rockToLose);
        robberWasRolled[playersID] = false;
    }

    //Method to return where the robber is located
    public int getRobber()
    {
        return robber;
    }

    //Method to return if a robber was rolled
    public boolean[] getRobberWasRolled()
    {
        return robberWasRolled;
    }

    //Method to return the list of roads
    public Road[] getRoads()
    {
        return roads;
    }

    //Method to return the list of tiles
    public Tile[] getTiles()
    {
        return tiles;
    }

    //Method to return the list of buildings
    public Building[] getBuildings()
    {
        return buildings;
    }

    //Method to return the list of all the players hands
    public Hand[] getHands()
    {
        return hands;
    }

    //Method to build a road in a given spot, returns true if the road is built, false otherwise
    public boolean buildRoad(int spot)
    {
        //If road is filled return false
        if(!roads[spot].isEmpty())
        {
            return false;
        }

        //If player doesn't have enough resources return false
        if(hands[playersID].getBrick() < 1 || hands[playersID].getLumber() < 1)
        {
            return false; //lacking resources!
        }

        //Make sure that the player building the road has another road adjacent to the spot they
        //want to build. If the player does, build road, set the road to not empty, remove
        //resources, and return true
        byte[] roadList = roadToRoadAdjList[spot];
        for(int i = 0; i < roadList.length; i++ )
        {
            if(roads[roadList[i]].getPlayer() == playersID)
            {
                roads[spot].setPlayer(playersID);
                roads[spot].setIsEmpty(false);
                hands[playersID].removeBrick(1);
                hands[playersID].removeLumber(1);
                return true;
            }
        }

        //Return false if the player doesn't have any adjacent roads and cannot build
        return false;
    }

    //Method to build a settlement in a given spot, returns true if the settlement is built, false
    //otherwise
    public boolean buildSettlement(int spot)
    {
        //If building spot is already filled return false
        if(!buildings[spot].isEmpty())
        {
            return false;
        }

        //If player doesn't have enough resources return false
        if(hands[playersID].getWheat() < 1 || hands[playersID].getWool() < 1 ||
                hands[playersID].getLumber() < 1 || hands[playersID].getBrick() < 1)
        {
            return false;
        }

        //Get list of adjacent buildings and roads to the current spot
        byte[] buildingAdjList = buildingToBuildingAdjList[spot];
        byte[] roadAdjList = buildingToRoadAdjList[spot];

        //Check to see if a building is too close, if so return false
        for(int i = 0; i < buildingAdjList.length; i++)
        {
            if(!buildings[buildingAdjList[i]].isEmpty())
            {
                return false;
            }
        }

        //Check to see if a road is next to the spot, if not return false
        for(int i = 0; i < roadAdjList.length; i++)
        {
            //If a adjacent road belong to the player they can build a settlement
            if(roads[roadAdjList[i]].getPlayer() == playersID)
            {
                //If the player can build, build the building and set the road to not empty
                buildings[spot].setIsEmpty(false);
                buildings[spot].setTypeOfBuilding(Building.SETTLEMENT);
                buildings[spot].setPlayer(playersID);

                //Remove resources
                hands[playersID].removeLumber(1);
                hands[playersID].removeBrick(1);
                hands[playersID].removeWheat(1);
                hands[playersID].removeWool(1);

                //Add a point to the player who built the settlement
                scores[playersID]++;

                //Return true
                return true;
            }
        }

        //If there was no adjacent road return false
        return false;

    }

    //Method to upgrade from a settlement to a city
    public boolean upgradeSettlement(int spot)
    {
        //Make sure the player owns the spot they want to upgrade and can be upgraded, if not return
        //false
        if(buildings[spot].getPlayer() != playersID || buildings[spot].getTypeOfBuilding() != Building.SETTLEMENT)
        {
            return false;
        }

        //Make sure the playyer has the correct number of resources, if not return false
        if(hands[playersID].getOre() < 3 || hands[playersID].getWheat() < 2)
        {
            return false; //lacking resources!
        }

        //Set the building to a city, remove resources
        buildings[spot].setTypeOfBuilding(Building.CITY);
        hands[playersID].removeOre(3);
        hands[playersID].removeWheat(2);

        //Add a point to the player who built the city
        scores[playersID]++;

        //Return true
        return true;
    }

    //Moves the turn to the next player in the rotation
    public void endTurn()
    {
        playersID = (playersID + 1) % numPlayers;
    }

    //USED FOR TESTING ONLY
    //Allows us to test features dependant on resources
    public void givePlayerResources(int player)
    {
        hands[player].addBrick(10);
        hands[player].addOre(10);
        hands[player].addWool(10);
        hands[player].addWheat(10);
        hands[player].addLumber(10);
    }

    //USED FOR TESTING ONLY
    //Allows us to set up roads to test methods dependant on roads
    public void generateRoad(int spot, int player)
    {
        roads[spot].setIsEmpty(false);
        roads[spot].setPlayer(player);
    }

    //USED FOR TESTING ONLY
    //Allows us to set up buildings to test methods dependant on buildign placement
    public void generateBuilding(int spot, int player, int type)
    {
        buildings[spot].setIsEmpty(false);
        buildings[spot].setPlayer(player);
        buildings[spot].setTypeOfBuilding(type);
    }

    //USED FOR TESTING ONLY
    //Allows us to set the dice, allows for testing of secodn part of roll method
    public void generateRoll(int die1, int die2)
    {

        this.die1 = die1;
        this.die2 = die2;

        if(die1 + die2 != 7) //what to do if not a 7
        {
            for(byte i = 0; i < tiles.length; i++) //check all tiles for the roll number
            {
                if(tiles[i].getRollNumber() == die1 + die2 && i != robber) //a tile matches the roll, dispense res
                {
                    byte[] buildList = tileToBuildingAdjList[i]; //tile's adj spots
                    distributeResources(buildList,tiles[i].getResource());
                }
            }
        }
        else
        {
            for(int i = 0; i < robberWasRolled.length; i++)
            {
                robberWasRolled[i] = true;
            }
        }
    }
}
