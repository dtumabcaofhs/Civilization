import tiles.*;
import tiles.buildings.*;
import tiles.terrain.Forest;
import tiles.terrain.Mountain;
import tiles.terrain.Plain;

import java.util.ArrayList;

public class ManageTiles {
    public static Tile savedOldTile;
    public static int savedTileIndex = -1, savedDay,savedWood,savedStone,savedWorkerNum;

    public static ArrayList<Tile> tileList = new ArrayList<>();

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
                    Forest forest = new Forest(row, col);
                    forest.enriched = randomizeEnrichment();
                    tileList.add(forest);
                } else if (rand == 1) {
                    Plain plain = new Plain(row, col);
                    plain.enriched = randomizeEnrichment();
                    tileList.add(plain);
                } else if (rand == 2) {
                    Mountain mountain = new Mountain(row, col);
                    mountain.enriched = randomizeEnrichment();
                    tileList.add(mountain);
                } else if (rand == 3){
                    City city = new City(row, col);
                    city.enriched = false;
                    tileList.add(city);
                }
            }
        }
    }
    
    public static void placeMine(int i){
        Tile oldTile = tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        tileList.set(i, mine);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-= Mine.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.wood;
        Simulation.wood -= Mine.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = Game.day;
    }

    public static void placeFarm(int i){
        Tile oldTile = tileList.get(i);
        Farm farm = new Farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        tileList.set(i, farm);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-= Farm.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.wood;
        Simulation.wood -= Farm.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = Game.day;
    }
    public static void placeLaboratory(int i){
        Tile oldTile = tileList.get(i);
        Laboratory laboratory = new Laboratory(oldTile.row, oldTile.col);
        laboratory.enriched = oldTile.enriched;
        tileList.set(i, laboratory);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-= Laboratory.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.stone;
        Simulation.stone -= Laboratory.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = Game.day;
    }
    public static void placeLumberyard(int i){
        Tile oldTile = tileList.get(i);
        Lumberyard lumberyard = new Lumberyard(oldTile.row, oldTile.col);
        lumberyard.enriched = oldTile.enriched;
        tileList.set(i, lumberyard);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-= Lumberyard.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.stone;
        Simulation.wood -= Lumberyard.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedDay = Game.day;
    }
}