package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.ExtraDeposit;
import it.polimi.ingsw.model.ExtraDepositLevel;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The type Change Resource player (it extends {@link Player}).
 * It's the player with the {@link ExtraDeposit} {@link LeaderCard}.
 */
public class ExtraDepositPlayer extends Player {
    private ArrayList<Resource> placeableRes; //the resource placeable in the added level
    private ArrayList<ExtraDepositLevel> extraDep;


    /**
     * Instantiates a new Extra deposit player.
     *
     * @param original     the original
     * @param placeableRes the placeable res
     */
    public ExtraDepositPlayer(Player original, Resource placeableRes) {
        this.placeableRes = new ArrayList<>();
        extraDep = new ArrayList<>();
        this.original = original;
        if (original instanceof ExtraDepositPlayer) {
            this.placeableRes.addAll(((ExtraDepositPlayer) original).placeableRes);
            extraDep.add(((ExtraDepositPlayer) original).getExtraDep().get(0));
            //Points to the basic
            this.original = original.original;
        }

        extraDep.add(new ExtraDepositLevel(placeableRes));
        this.placeableRes.add(placeableRes);

    }

    @Override
    public boolean checkLevel(int level) {
        return level <= extraDep.size() + 2 && level >= 0;
    }

    @Override
    public boolean checkSpace(Resource res, int level) {
        if (level >= 0 && level <= 2) {
            return getBoard().getWareHouse().checkSpace(level, res);
        } else if (level >= 3 && level <= extraDep.size() + 2) {
            if (extraDep.get(level - 3).getPlaceable().equals(res)) {
                return Collections.frequency(extraDep.get(level - 3).getResources(), Resource.EMPTY) >= 1;
            }
            return false;
        }
        return false;
    }

    @Override
    public void switchLevels(int originLevel, int destLevel) {

//from Warehouse...
        if (originLevel <= 2) {
            // ... to Warehouse
            if (destLevel <= 2) {
                original.switchLevels(originLevel, destLevel);
            }
            // ... to ExtraDep
            else {
                for (int i = 0; i < Math.min(original.getDeposits().get(originLevel).length, extraDep.get(destLevel - 3).getResources().size()); i++) {
                    extraDep.get(destLevel - 3).getResources().set(extraDep.get(destLevel - 3).getResources().size() - i - 1, original.getDeposits().get(originLevel)[original.getDeposits().get(originLevel).length - i - 1]);
                    original.getBoard().getWareHouse().getLevels().get(originLevel)[original.getBoard().getWareHouse().getLevels().get(originLevel).length - i - 1] = Resource.EMPTY;
                }
            }
        }
// form ExtraDep...
        else {
            // ... to Warehouse
            ArrayList<Resource> tmp = new ArrayList<>();
            tmp.addAll(extraDep.get(originLevel - 3).getResources());
            for (int i = 0; i < Math.min(original.getDeposits().get(destLevel).length, extraDep.get(originLevel - 3).getResources().size()); i++) {
                extraDep.get(originLevel - 3).getResources().set(i, original.getDeposits().get(destLevel)[i]);
                original.getBoard().getWareHouse().getLevels().get(destLevel)[i] = tmp.get(i);
            }
        }
    }

    private void add(Resource res, int level) {
        extraDep.get(level - 3).addResource(res);
    }

    private void remove(Resource res, int level) {
        extraDep.get(level).removeResource(res);
    }

    @Override
    public boolean checkSwitch(int originLevel, int destLevel) {
        //The player can't remove something the extra dep level but just put it in it or can't get from a level that he doesn't have

        //In case there are no extra deposits required
        if (originLevel > 3 + extraDep.size() || destLevel > 3 + extraDep.size()) {
            return false;
        }
        //From WareHouse...
        else if (originLevel <= 2) {
            //... to WareHouse
            if (destLevel <= 2) {
                return original.checkSwitch(originLevel, destLevel);
                //... to ExtraDep;
            } else {
                return (getDeposits().get(originLevel)[0] == extraDep.get(destLevel - 3).getPlaceable() && extraDep.get(destLevel - 3).getResources().contains(Resource.EMPTY));
            }
        }
        // From ExtraDep to WareHouse
        else {
            boolean isThere = false;
            for (int i = 0; i < original.getDeposits().size(); i++) {
                if (i != destLevel) {
                    if (Arrays.stream(original.getDeposits().get(i)).anyMatch(x -> x.equals(extraDep.get(originLevel - 3).getPlaceable()))) {
                        isThere = true;
                        break;
                    }
                }
            }
            return !isThere;
        }
    }

    @Override
    public void addResource(int level, Resource res) {
        if (level >= 0 && level <= 2) {
            original.getBoard().getWareHouse().addResource(level, res);
        } else {
            this.add(res, level);
        }
    }

    @Override
    public void removeResource(Resource res, String place) {
        switch (place) {
            case "strongbox":
                original.getBoard().getStrongBox().removeResource(res);
                break;
            case "warehouse":
                original.getBoard().getWareHouse().removeResource(res);
                break;
            case "extradeposit":
                for (ExtraDepositLevel extralevel : extraDep) {
                    if (extralevel.getPlaceable() == res) {
                        remove(res, extraDep.indexOf(extralevel));

                    }
                }
                break;
        }
    }

    @Override
    public ArrayList<Resource> getAllResources() {
        ArrayList<Resource> tmp = new ArrayList<>();
        tmp.addAll(this.getBoard().getStrongBox().getResources());
        tmp.addAll(this.getBoard().getWareHouse().getResources());
        for (ExtraDepositLevel extraLevel : extraDep) {
            tmp.addAll(extraLevel.getResources());
        }
        return tmp;
    }

    @Override
    public ArrayList<Resource[]> getDeposits() {
        ArrayList<Resource[]> deposits = new ArrayList<>();
        deposits.addAll(original.getBoard().getWareHouse().getArrayListWareHouse());

        for (ExtraDepositLevel e : extraDep) {
            Resource[] extraLevel = e.getResources().toArray(new Resource[2]);
            deposits.add(extraLevel);
        }
        return deposits;
    }

    /**
     * Gets extra dep.
     *
     * @return the extra dep
     */
    public ArrayList<ExtraDepositLevel> getExtraDep() {
        return extraDep;
    }
}



