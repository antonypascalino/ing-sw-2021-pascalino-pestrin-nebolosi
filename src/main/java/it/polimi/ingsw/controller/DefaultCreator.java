package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.card.*;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * Class used if the player picks to use the default settings for leader and developer cards.
 */
public class DefaultCreator {
    private static ArrayList<DevCard> allDevCards = new ArrayList<DevCard>();
    private static ArrayList<LeaderCard> allLeadersCards = new ArrayList<LeaderCard>();

    /**
     * Static method used for generate an array List containing all the defaults
     * DevCard in the basic game settings. Once the method is called for the first time
     * saves the cards in his attribute and the other calls will return the attribute.
     *
     * @return the array list containing all the {@link DevCard}s.
     */
    public static ArrayList<DevCard> produceDevCard() {

        if (allDevCards.size() != 0) return allDevCards;

        ArrayList<DevCard> result = new ArrayList<DevCard>();
        ArrayList<Resource> requires = new ArrayList<Resource>();
        ArrayList<Resource> produces = new ArrayList<Resource>();
        ArrayList<Resource> price = new ArrayList<Resource>();
        DevCard tmp;

        //devP101
        requires.add(Resource.STONE);
        produces.add(Resource.FAITH);
        price.add(Resource.SERVANT);
        price.add(Resource.SERVANT);

        tmp = new DevCard("devP101", "PURPLE", 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP102", "PURPLE", 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP103", "PURPLE", 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP104", "PURPLE", 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP201", "PURPLE", 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP202", "PURPLE", 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP203", "PURPLE", 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP204", "PURPLE", 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP301", "PURPLE", 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP302", "PURPLE", 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP303", "PURPLE", 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devP304", "PURPLE", 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devG101
        requires.add(Resource.GOLD);
        produces.add(Resource.FAITH);
        price.add(Resource.SHIELD);
        price.add(Resource.SHIELD);

        tmp = new DevCard("devG101", "GREEN", 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG102", "GREEN", 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG103", "GREEN", 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG104", "GREEN", 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG201", "GREEN", 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG202", "GREEN", 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG203", "GREEN", 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG204", "GREEN", 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG301", "GREEN", 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG302", "GREEN", 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG303", "GREEN", 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devG304", "GREEN", 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devY101
        requires.add(Resource.SERVANT);
        produces.add(Resource.FAITH);
        price.add(Resource.STONE);

        tmp = new DevCard("devY101", "YELLOW", 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY102", "YELLOW", 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY103", "YELLOW", 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY104", "YELLOW", 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY201", "YELLOW", 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY202", "YELLOW", 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY203", "YELLOW", 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY204", "YELLOW", 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY301", "YELLOW", 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY302", "YELLOW", 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY303", "YELLOW", 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devY304", "YELLOW", 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();

        //devB101
        requires.add(Resource.SHIELD);
        produces.add(Resource.FAITH);
        price.add(Resource.GOLD);
        price.add(Resource.GOLD);

        tmp = new DevCard("devB101", "BLUE", 1, 1, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB102", "BLUE", 1, 2, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB103", "BLUE", 1, 3, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB104", "BLUE", 1, 4, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB201", "BLUE", 2, 5, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB202", "BLUE", 2, 6, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB203", "BLUE", 2, 7, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB204", "BLUE", 2, 8, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB301", "BLUE", 3, 9, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB302", "BLUE", 3, 10, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB303", "BLUE", 3, 11, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
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

        tmp = new DevCard("devB304", "BLUE", 3, 12, Resource.cloneList(requires), Resource.cloneList(produces), Resource.cloneList(price));
        result.add(tmp);

        requires.clear();
        produces.clear();
        price.clear();
        allDevCards = result;
        return result;
    }

    /**
     * Static method used for generate an array List containing all the defaults
     * LeaderCards in the basic game settings
     *
     * @return the array list containing all the {@link LeaderCard}s.
     */
    public static ArrayList<LeaderCard> produceLeaderCard()
    {
        if (allLeadersCards.size() != 0) return allLeadersCards;

        ArrayList<LeaderCard> result = new ArrayList<LeaderCard>();
        //PROD01
        ExtraProd tmp = new ExtraProd(4, "Blue", Resource.SERVANT, "PROD01");
        result.add(tmp);

        //PROD02
        ExtraProd tmp2 = new ExtraProd(4, "Green", Resource.GOLD, "PROD02");
        result.add(tmp2);

        //PROD03
        ExtraProd tmp3 = new ExtraProd(4, "Purple", Resource.STONE, "PROD03");
        result.add(tmp3);

        //PROD03
        ExtraProd tmp4 = new ExtraProd(4, "Yellow", Resource.SHIELD, "PROD04");
        result.add(tmp4);

        //CNG01
        ChangeResource cng1 = new ChangeResource(5,"Purple", "Green", Resource.GOLD, "CNG01");
        result.add(cng1);

        //CNG02
        ChangeResource cng2 = new ChangeResource(5, "Blue", "Yellow", Resource.STONE, "CNG02");
        result.add(cng2);

        //CNG03
        ChangeResource cng3 = new ChangeResource(5, "Green","Purple", Resource.SHIELD, "CNG03");
        result.add(cng3);

        //CNG04
        ChangeResource cng4 = new ChangeResource(5,"Yellow", "Blue", Resource.SERVANT, "CNG04");
        result.add(cng4);

        //DIS01
        Discount dsn1 = new Discount("Yellow", "Purple", Resource.GOLD, 2, "DIS01");
        result.add(dsn1);

        //DIS02
        Discount dsn2 = new Discount("Yellow", "Green", Resource.SERVANT, 2, "DIS02");
        result.add(dsn2);

        //DIS03
        Discount dsn3 = new Discount("Blue", "Purple", Resource.SHIELD, 2, "DIS03");
        result.add(dsn3);

        //DIS04
        Discount dsn4 = new Discount("Green", "Blue", Resource.STONE, 2, "DIS04");
        result.add(dsn4);

        //DEP01
        ExtraDeposit dep1 = new ExtraDeposit(3, Resource.STONE, Resource.SERVANT, "DEP01");
        result.add(dep1);

        //DEP02
        ExtraDeposit dep2 = new ExtraDeposit(3, Resource.SHIELD, Resource.GOLD, "DEP02");
        result.add(dep2);

        //DEP03
        ExtraDeposit dep3 = new ExtraDeposit(3, Resource.SERVANT, Resource.SHIELD, "DEP03");
        result.add(dep3);

        //DEP04
        ExtraDeposit dep4 = new ExtraDeposit(3,Resource.GOLD,Resource.STONE, "DEP04");
        result.add(dep4);

        allLeadersCards = result;
        return result;
    }


    /**
     * Create all the {@link Resource}s needed during a game.
     *
     * @return the ArrayList containing all the {@link Resource}s
     */
    public static ArrayList<Resource> getGameRes() {
        ArrayList<Resource> result = new ArrayList<Resource>();
        for (int i = 0; i < 2; i++)
            result.add(Resource.GOLD);
        for (int i = 0; i < 2; i++)
            result.add(Resource.SERVANT);
        for (int i = 0; i < 4; i++)
            result.add(Resource.EMPTY);
        for (int i = 0; i < 1; i++)
            result.add(Resource.FAITH);
        for (int i = 0; i < 2; i++)
            result.add(Resource.STONE);
        for (int i = 0; i < 2; i++)
            result.add(Resource.SHIELD);
        return result;
    }

    /**
     * Receiving an devCardID returns the object {@link DevCard}
     *
     * @param cardID the card id
     * @return the dev from id
     */
    public static DevCard getDevFromID(String cardID) {
        for (DevCard card : allDevCards) {
            if (card.getCardID().equals(cardID)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Receiving an {@link LeaderCard}'s ID obtains the respective {@link LeaderCard} object
     *
     * @param cardID the ID of the {@link LeaderCard}.
     * @return the object {@link LeaderCard}.
     */
    public static LeaderCard getLeaderFromID(String cardID) {
        for (LeaderCard card : allLeadersCards) {
            if (card.getID().equals(cardID)) {
                return card;
            }
        }
        return null;
    }
}
