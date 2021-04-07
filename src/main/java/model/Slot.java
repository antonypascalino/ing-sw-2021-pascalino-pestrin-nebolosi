package model;

import java.util.ArrayList;

public class Slot
{
    private ArrayList<DevCard> slots;

    public Slot()
    {
        ArrayList<DevCard> slots = new ArrayList<DevCard>();
        
    }

    //to do
    public void purchaseDevCard(DevCard card, int slot)
    {



    }

    public boolean checkSpace(DevCard card, int slot)
    {
        //checks if the slot is empty or not
        if(slots.get(slot) == null)
        {
            if (card.level == 1)
                return true;
            else
                return false;
        }
        else
        {
            //if it isn't, then checks card levels
            DevCard slotCard = slots.get(slot);

            if(card.level == slotCard.level-1)
                return true;
            else
                return false;
        }


    }

    public DevCard[3] getFrontCards()
    {

    }
}
