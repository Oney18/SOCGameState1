package edu.up.cs301.soc;

/**
 * Created by oney18 on 10/27/2015.
 *
 * Class used to keep track of how many cards a player has at any time
 */
public class Hand {
    private int wheats;
    private int sheep;
    private int wood;
    private int bricks;
    private int rocks;

    Hand()
    {
        wheats = 0;
        sheep = 0;
        wood = 0;
        bricks = 0;
        rocks = 0;
    }

    //used for 7 roll GUI
    public int getTotal()
    {
        return wheats + sheep + wood + bricks + rocks;
    }

    public int getWheats()
    {
        return wheats;
    }

    public int getSheep()
    {
        return sheep;
    }

    public int getWood()
    {
        return wood;
    }

    public int getBricks()
    {
        return bricks;
    }

    public int getRocks()
    {
        return rocks;
    }

    public void addWheat(int count)
    {
        this.wheats += count;
    }

    public void addSheep(int count)
    {
        this.sheep += count;
    }

    public void addWood(int count)
    {
        this.wood += count;
    }

    public void addBrick(int count)
    {
        this.bricks += count;
    }

    public void addRock(int count)
    {
        this.rocks += count;
    }

    public void removeWheat(int count)
    {
        this.wheats -= count;
    }

    public void removeSheep(int count)
    {
        this.sheep -= count;
    }

    public void removeWood(int count)
    {
        this.wood -= count;
    }

    public void removeBrick(int count)
    {
        this.bricks -= count;
    }

    public void removeRock(int count)
    {
        this.rocks -= count;
    }

    //used when a 7 is rolled
    public boolean checkIfEmpty(int type)
    {
        switch(type)
        {
            case Tile.LUMBER:
                return wood == 0;

            case Tile.ORE:
                return rocks == 0;

            case Tile.BRICK:
                return bricks == 0;

            case Tile.WOOL:
                return sheep == 0;

            case Tile.WHEAT:
                return wheats == 0;

            default:
                return false;
        }
    }

    //used when a 7 is rolled
    public boolean stealResource(int type)
    {
        switch(type)
        {
            case Tile.LUMBER:
                wood--;
                return true;

            case Tile.ORE:
                rocks--;
                return true;

            case Tile.BRICK:
                bricks--;
                return true;

            case Tile.WOOL:
                sheep--;
                return true;

            case Tile.WHEAT:
                wheats--;
                return true;

            default:
                return false;
        }
    }

    //used when a 7 is rolled
    public boolean addResource(int type)
    {
        switch(type)
        {
            case Tile.LUMBER:
                wood++;
                return true;

            case Tile.ORE:
                rocks++;
                return true;

            case Tile.BRICK:
                bricks++;
                return true;

            case Tile.WOOL:
                sheep++;
                return true;

            case Tile.WHEAT:
                wheats++;
                return true;

            default:
                return false;
        }
    }
}
