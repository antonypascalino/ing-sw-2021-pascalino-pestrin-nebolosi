package model;
import java.util.ArrayList;

public class Slot
{
    private ArrayList<DevCard[]> slots;

    //DevCard devCard;

    public Slot()
    {
        //Create three empty slot for three stacks
        slots = new ArrayList<DevCard[]>();
        DevCard[] tmp = new DevCard[3];
        slots.add(tmp);
        tmp = new DevCard[3];
        slots.add(tmp);
        tmp = new DevCard[3];
        slots.add(tmp);
    }

    public void purchaseCard(DevCard card, int slot)
    {
        //if checkSpace is true
        DevCard[] currSlot = slots.get(slot);

        //the card with the highest level will be at the end
        for(int i = 0; i < currSlot.length; i++)
        {
            if(currSlot[i] == null)
            {
                currSlot[i] = card;
                break;
            }
        }
    }

    public boolean checkSpace(DevCard card, int slot)
    {
        //gets the array of cards on the address "slot"
        DevCard[] currSlot = slots.get(slot);
        //current card in the selected slot
        DevCard currCard;

        //checks if the slot is empty or not
        if(card.level == 1)
        {
           for(int i = 0; i < currSlot.length; i++ )
           {
               if(currSlot[i] != null)
               {
                   return false;
               }
           }
           return true;
        }
        else
        {
            //checks slot card level
            for(int i = 0; i < currSlot.length; i++)
            {
                if(currSlot[i] != null)
                {
                    currCard = currSlot[i];
                    if(card.level == currCard.level+1)
                    {
                        return true;
                    }
                }

            }
            return false;
        }

    }

    public DevCard[] getFrontCards()
    {
        //looks at the last element of each slot
        DevCard[] currSlot;
        DevCard[] frontCards = new DevCard[3];
        for(int i = 0; i < slots.size(); i++)
        {
            //frontCards is updated if either there's a card or the index is null
            currSlot = slots.get(i);
            for (int j = 0; j < currSlot.length; j++)
            {
                //if one of the 3 indexes of frontCards is null it means that in a slot
                //there are no cards
                if(currSlot[j+1] == null)
                {
                    frontCards[j] = currSlot[j];
                }
            }
        }

        return frontCards;

    }

    public ArrayList<DevCard> getAllCards()
    {
        //searches through all the slots and returns all the cards
        ArrayList<DevCard> allCards = new ArrayList<DevCard>();
        DevCard[] currSlot;
        for (int i = 0; i< slots.size(); i++)
        {
            currSlot = slots.get(i);
            for (int j = 0; j < currSlot.length; j++)
                if(currSlot[j] != null)
                    allCards.add(currSlot[j]);
        }

        return allCards;

    }
}
