package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
Class used if the player picks to use the default settings for leader and developer cards
 */
public class DefaultCreator {

    public static ArrayList<DevCard> produceDevCard() {

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
        return result;
    }
}
