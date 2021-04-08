package Model;

import java.util.ArrayList;

public class Player {

    /*Quando c'è da rimuovere in qualche modo le risorse questo metodo comunicando con la view permette di rimovere
      le risorse dai vari posti
      @result resources from the array are removed from the player resources
     */
    public void removeResources(ArrayList<Resource> needed)
    {
        for(Resource r: needed)
        {
/*

            if(View.askWhere(r).equals("strongbox"))
            {
                if(!strongBox.isAvaible(r))
                    notifySomehow();
                //Bla bla bla
                strongBox.remove(r);
            }

            if(View.askWhere(r).equals("warehouse"))
                warehouse.remove(r);

 */
        }
    }
}
