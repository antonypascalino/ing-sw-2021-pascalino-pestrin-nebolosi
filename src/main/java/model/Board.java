package model;

public class Board
{
    //references to all the classes mentioned below
  private WareHouse wareHouse;
  private StrongBox strongBox;
  private Slot slot;
  private TempBox tempBox;
  private FaithPath faithPath;

    public Board()
    {
        strongBox = new StrongBox();

    }
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }

    public Slot getSlot() {
        return slot;
    }

    public TempBox getTempBox() {
        return tempBox;
    }

    public FaithPath getFaithPath() {
        return faithPath;
    }
}
