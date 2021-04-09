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
        wareHouse = new WareHouse();
        strongBox = new StrongBox();
        slot = new Slot();
        tempBox = new TempBox(strongBox);
        faithPath = new FaithPath();

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

    //remove resource and check resources methods needed here
}
