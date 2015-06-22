package com.diego.civpocket.logic;

/**
 * Created by diego on 22/06/2015.
 */
public class FirstScenario extends Scenario {

    public FirstScenario() {

        super(new Region[7]);

        _name = "A New World";

        _map[0] = new Region("1");
        _map[1] = new Region("2");
        _map[2] = new Region("3");
        _map[3] = new Region("4");
        _map[4] = new Region("5");
        _map[5] = new Region("7");
        _map[6] = new Region("8");

        try {
            getRegionByName("1").add(Biomes.Forest);//        Region 1: 1 Forest
            getRegionByName("2").add(Biomes.Dessert);//        Region 2: 1 Desert
            getRegionByName("3").add(Biomes.Dessert);//        Region 3: 1 Desert
            getRegionByName("4").add(Biomes.Dessert);//        Region 4: 1 Desert
            getRegionByName("5").add(Biomes.Mountain);//        Region 5: 1 Mountain
            getRegionByName("7").add(Biomes.Forest);//        Region 7: 1 Forest
            getRegionByName("8").add(Biomes.Mountain);//        Region 8: 1 Mountain, 1 Forest
            getRegionByName("8").add(Biomes.Forest);
        }
        catch (IllegalActionException e) {
            throw new RuntimeException("Wrong coding of the scenario!!!! Review the constructor for FirstSCenario!!!");
        }
    }

    public void setUp(Empire player) {
        if (_name.length() > 0) {
            player.sendSettlerTo(getRegionByName("5"));
        }
    }
}
