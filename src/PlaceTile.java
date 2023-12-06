import Tiles.*;
import Tiles.Buildings.Farm;
import Tiles.Buildings.Laboratory;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;

public class PlaceTile {
    public static Tile savedOldTile;
    public static int savedTileIndex,savedTurn,savedWood,savedStone,savedWorkerNum;
    public static void buildMine(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        GenerateTile.tileList.set(i, mine);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-=Mine.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.wood;
        Simulation.wood -= Mine.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = Game.turn;
    }

    public static void buildFarm(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Farm farm = new Farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, farm);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-=Farm.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.wood;
        Simulation.wood -= Farm.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = Game.turn;
    }
    public static void buildLaboratory(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Laboratory laboratory = new Laboratory(oldTile.row, oldTile.col);
        laboratory.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, laboratory);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-=Laboratory.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.stone;
        Simulation.stone -= Laboratory.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = Game.turn;
    }
    public static void buildLumberyard(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Lumberyard lumberyard = new Lumberyard(oldTile.row, oldTile.col);
        lumberyard.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, lumberyard);
        savedWorkerNum = Simulation.workerAmt;
        Simulation.workerAmt-=Lumberyard.workersNeeded;
        savedWood = Simulation.wood;
        savedStone = Simulation.stone;
        Simulation.wood -= Lumberyard.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = Game.turn;
    }

    public static void undoLast() {
        if(savedTileIndex >= 0 && savedTurn == Game.turn) {
            GenerateTile.tileList.set(savedTileIndex, savedOldTile);
            Simulation.workerAmt=savedWorkerNum;
            Simulation.stone = savedStone;
            Simulation.wood = savedWood;
            savedTurn = -1;
        }
    }
}