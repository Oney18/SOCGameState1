package edu.up.cs301.soc;

/**
 * Created by oney18 on 10/27/2015.
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
}
