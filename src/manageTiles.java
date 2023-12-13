import tiles.*;
import tiles.buildings.*;
import tiles.terrain.forest;
import tiles.terrain.mountain;
import tiles.terrain.plain;

import java.util.ArrayList;

public class manageTiles {
    public static tile savedOldTile;
    public static int savedTileIndex = -1, savedDay,savedWood,savedStone,savedWorkerNum;

    public static ArrayList<tile> tileList = new ArrayList<>();

    public static boolean randomizeEnrichment() {
        double rand = Math.random();
        return rand < 0.2;
    }

    public static void generateTerrain() {
        tileList.clear();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int rand = (int)(Math.random()*3);
                if(row == 4 && col == 4){
                    rand = 3;
                }
                if (rand == 0) {
                    forest forest = new forest(row, col);
                    forest.enriched = randomizeEnrichment();
                    tileList.add(forest);
                } else if (rand == 1) {
                    plain plain = new plain(row, col);
                    plain.enriched = randomizeEnrichment();
                    tileList.add(plain);
                } else if (rand == 2) {
                    mountain mountain = new mountain(row, col);
                    mountain.enriched = randomizeEnrichment();
                    tileList.add(mountain);
                } else if (rand == 3){
                    city city = new city(row, col);
                    city.enriched = false;
                    tileList.add(city);
                }
            }
        }
    }
    
    public static void placeMine(int i){
        tile oldTile = tileList.get(i);
        mine mine = new mine(oldTile.row, oldTile.col);
        tileList.set(i, mine);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.mine.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.wood;
        simulation.wood -= tiles.buildings.mine.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = game.day;
    }

    public static void placeFarm(int i){
        tile oldTile = tileList.get(i);
        farm farm = new farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        tileList.set(i, farm);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.farm.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.wood;
        simulation.wood -= tiles.buildings.farm.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = game.day;
    }
    public static void placeLaboratory(int i){
        tile oldTile = tileList.get(i);
        laboratory laboratory = new laboratory(oldTile.row, oldTile.col);
        laboratory.enriched = oldTile.enriched;
        tileList.set(i, laboratory);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.laboratory.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.stone;
        simulation.stone -= tiles.buildings.laboratory.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = game.day;
    }
    public static void placeLumberyard(int i){
        tile oldTile = tileList.get(i);
        lumberyard lumberyard = new lumberyard(oldTile.row, oldTile.col);
        lumberyard.enriched = oldTile.enriched;
        tileList.set(i, lumberyard);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.lumberyard.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.stone;
        simulation.wood -= tiles.buildings.lumberyard.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = game.day;
    }
}