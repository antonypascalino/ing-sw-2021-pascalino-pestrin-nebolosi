package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.*;

import java.util.ArrayList;

public class ClientDefaultCreator {


    /**
     Class used if the player picks to use the default settings for leader and developer cards
     */


    /**
     * Static method used for generate an array List containing all the defaults
     * DevCard in the basic game settings
     *
     * @result An arrayList with the new cards
     */
    public static ArrayList<ClientDevCard> produceClientDevCard() {

        ArrayList<ClientDevCard> result = new ArrayList<ClientDevCard>();
        ArrayList<Resource> requires = new ArrayList<Resource>();
        ArrayList<Resource> produces = new ArrayList<Resource>();
        ArrayList<Resource> price = new ArrayList<Resource>();
        ClientDevCard tmp;

        //devP101
        requires.add(Resource.STONE);
        produces.add(Resource.FAITH);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP101", Colors.PURPLE, 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP102
        requires.add(Resource.GOLD);
        produces.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.SHIELD);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP102", Colors.PURPLE, 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP103
        requires.add(Resource.GOLD);
        requires.add(Resource.GOLD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP103", Colors.PURPLE, 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP104
        requires.add(Resource.GOLD);
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devP104", Colors.PURPLE, 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP201
        requires.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP201", Colors.PURPLE, 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP202
        requires.add(Resource.GOLD);
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP202", Colors.PURPLE, 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP203
        requires.add(Resource.STONE);
        requires.add(Resource.STONE);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP203", Colors.PURPLE, 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP204
        requires.add(Resource.STONE);
        produces.add(Resource.FAITH);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devP204", Colors.PURPLE, 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP301
        requires.add(Resource.STONE);
        requires.add(Resource.STONE);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP301", Colors.PURPLE, 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP302
        requires.add(Resource.STONE);
        requires.add(Resource.SHIELD);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP302", Colors.PURPLE, 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP303
        requires.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.STONE);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devP303", Colors.PURPLE, 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP304
        requires.add(Resource.GOLD);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devP304", Colors.PURPLE, 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG101
        requires.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG101", Colors.GREEN, 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG102
        requires.add(Resource.STONE);
        produces.add(Resource.SERVANT);
        price.add(Resource.STONE);
        price.add(Resource.SHIELD);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devG102", Colors.GREEN, 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devP103
        requires.add(Resource.SERVANT);
        requires.add(Resource.SERVANT);
        produces.add(Resource.GOLD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.STONE);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG103", Colors.GREEN, 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG104
        requires.add(Resource.STONE);
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devG104", Colors.GREEN, 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG201
        requires.add(Resource.STONE);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG201", Colors.GREEN, 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG202
        requires.add(Resource.SERVANT);
        requires.add(Resource.SHIELD);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG202", Colors.GREEN, 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG203
        requires.add(Resource.GOLD);
        requires.add(Resource.GOLD);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG203", Colors.GREEN, 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG204
        requires.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG204", Colors.GREEN, 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG301
        requires.add(Resource.SERVANT);
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG301", Colors.GREEN, 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG302
        requires.add(Resource.GOLD);
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devG302", Colors.GREEN, 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG303
        requires.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG303", Colors.GREEN, 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG304
        requires.add(Resource.STONE);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devG304", Colors.GREEN, 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY101
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY101", Colors.YELLOW, 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY102
        requires.add(Resource.SHIELD);
        produces.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.GOLD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devY102", Colors.YELLOW, 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY103
        requires.add(Resource.SHIELD);
        requires.add(Resource.SHIELD);
        produces.add(Resource.GOLD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY103", Colors.YELLOW, 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY104
        requires.add(Resource.GOLD);
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devY104", Colors.YELLOW, 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY201
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY201", Colors.YELLOW, 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY202
        requires.add(Resource.SHIELD);
        requires.add(Resource.STONE);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new ClientDevCard("devY202", Colors.YELLOW, 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY203
        requires.add(Resource.SHIELD);
        requires.add(Resource.SHIELD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY203", Colors.YELLOW, 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY204
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devY204", Colors.YELLOW, 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY301
        requires.add(Resource.SHIELD);
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY301", Colors.YELLOW, 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY302
        requires.add(Resource.SHIELD);
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        produces.add(Resource.GOLD);
        produces.add(Resource.GOLD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devY302", Colors.YELLOW, 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY303
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.SERVANT);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devY303", Colors.YELLOW, 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY304
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.STONE);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devY304", Colors.YELLOW, 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB101
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB101", Colors.BLUE, 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB102
        requires.add(Resource.SERVANT);
        produces.add(Resource.STONE);
        price.add(Resource.GOLD);
        price.add(Resource.SERVANT);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devB102", Colors.BLUE, 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB103
        requires.add(Resource.STONE);
        requires.add(Resource.STONE);
        produces.add(Resource.GOLD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB103", Colors.BLUE, 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB104
        requires.add(Resource.STONE);
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new ClientDevCard("devB104", Colors.BLUE, 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB201
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB201", Colors.BLUE, 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB202
        requires.add(Resource.GOLD);
        requires.add(Resource.STONE);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devB202", Colors.BLUE, 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB203
        requires.add(Resource.SERVANT);
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB203", Colors.BLUE, 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB204
        requires.add(Resource.SERVANT);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devB204", Colors.BLUE, 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB301
        requires.add(Resource.SERVANT);
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB301", Colors.BLUE, 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB302
        requires.add(Resource.GOLD);
        requires.add(Resource.SHIELD);
        produces.add(Resource.SERVANT);
        produces.add(Resource.SERVANT);
        produces.add(Resource.STONE);
        produces.add(Resource.STONE);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devB302", Colors.BLUE, 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB303
        requires.add(Resource.STONE);
        produces.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new ClientDevCard("devB303", Colors.BLUE, 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB304
        requires.add(Resource.SERVANT);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.SHIELD);
        produces.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);
        price.add(Resource.STONE);

        tmp = new ClientDevCard("devB304", Colors.BLUE, 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();
        return result;
    }

    /**
     * Static method used for generate an array List containing all the defaults
     * ClientLeaderCards in the basic game settings
     *
     * @result An arrayList with the new cards
     */
    public static ArrayList<ClientLeaderCard> produceClientLeaderCard() {
        ArrayList<ClientLeaderCard> result = new ArrayList<ClientLeaderCard>();
        //PROD01
        ClientExtraProd tmp = new ClientExtraProd(4, Colors.BLUE, Resource.SERVANT, "PROD01");
        result.add(tmp);

        //PROD02
        ClientExtraProd tmp2 = new ClientExtraProd(4, Colors.GREEN, Resource.GOLD, "PROD02");
        result.add(tmp2);

        //PROD03
        ClientExtraProd tmp3 = new ClientExtraProd(4, Colors.PURPLE, Resource.STONE, "PROD03");
        result.add(tmp3);

        //PROD03
        ClientExtraProd tmp4 = new ClientExtraProd(4, Colors.YELLOW, Resource.SHIELD, "PROD04");
        result.add(tmp4);

        //CNG01
        ClientChangeRes cng1 = new ClientChangeRes(5, Colors.PURPLE, Colors.GREEN, Resource.GOLD, "CNG01");
        result.add(cng1);

        //CNG02
        ClientChangeRes cng2 = new ClientChangeRes(5, Colors.BLUE, Colors.YELLOW, Resource.STONE, "CNG02");
        result.add(cng2);

        //CNG03
        ClientChangeRes cng3 = new ClientChangeRes(5, Colors.GREEN, Colors.PURPLE, Resource.SHIELD, "CNG03");
        result.add(cng3);

        //CNG04
        ClientChangeRes cng4 = new ClientChangeRes(5, Colors.YELLOW, Colors.BLUE, Resource.SERVANT, "CNG04");
        result.add(cng4);

        //DIS01
        ClientDiscount dsn1 = new ClientDiscount(Colors.YELLOW, Colors.PURPLE, Resource.GOLD, 2, "DIS01");
        result.add(dsn1);

        //DIS02
        ClientDiscount dsn2 = new ClientDiscount(Colors.YELLOW, Colors.GREEN, Resource.SERVANT, 2, "DIS02");
        result.add(dsn2);

        //DIS03
        ClientDiscount dsn3 = new ClientDiscount(Colors.BLUE, Colors.PURPLE, Resource.SHIELD, 2, "DIS03");
        result.add(dsn3);

        //DIS04
        ClientDiscount dsn4 = new ClientDiscount(Colors.GREEN, Colors.BLUE, Resource.STONE, 2, "DIS04");
        result.add(dsn4);

        //DEP01
        ClientExtraDep dep1 = new ClientExtraDep(3, Resource.STONE, Resource.SERVANT, "DEP01");
        result.add(dep1);

        //DEP02
        ClientExtraDep dep2 = new ClientExtraDep(3, Resource.SHIELD, Resource.GOLD, "DEP02");
        result.add(dep2);

        //DEP03
        ClientExtraDep dep3 = new ClientExtraDep(3, Resource.SERVANT, Resource.SHIELD, "DEP03");
        result.add(dep3);

        //DEP04
        ClientExtraDep dep4 = new ClientExtraDep(3, Resource.GOLD, Resource.STONE, "DEP04");
        result.add(dep4);

        return result;
    }

    public static ArrayList<BasicProduction> produceBasicProd() {
        ArrayList<BasicProduction> result = new ArrayList<BasicProduction>();
        ArrayList<Resource> requires = new ArrayList<Resource>();
        ArrayList<Resource> produces = new ArrayList<Resource>();
        BasicProduction tmp;

        requires.add(Resource.CHOICE);
        produces.add(Resource.CHOICE);
        produces.add(Resource.CHOICE);

        tmp = new BasicProduction("basicProd", Resource.cloneList(requires), Resource.cloneList(produces));
        result.add(tmp);

        requires.clear();
        produces.clear();

        return result;

    }

    public static ArrayList<Resource> getGameRes() {
        ArrayList<Resource> result = new ArrayList<Resource>();
        for (int i = 0; i < 2; i++)
            result.add(Resource.GOLD);
        for (int i = 0; i < 2; i++)
            result.add(Resource.SERVANT);
        for (int i = 0; i < 2; i++)
            result.add(Resource.EMPTY);
        for (int i = 0; i < 2; i++)
            result.add(Resource.FAITH);
        for (int i = 0; i < 2; i++)
            result.add(Resource.STONE);
        for (int i = 0; i < 3; i++)
            result.add(Resource.SHIELD);
        return result;
    }
}


