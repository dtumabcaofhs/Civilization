import Tiles.*;
import Tiles.Buildings.Farm;
import Tiles.Buildings.Laboratory;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;

public class BuildTile {
    public static Tile savedOldTile;
    public static int savedInt,savedTurn,savedWood,savedStone;
    public static void buildMine(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        GenerateTile.tileList.set(i, mine);
        Simulation.availWorkerAmt--;
        savedWood = Simulation.wood;
        Simulation.wood -= 30;
        savedOldTile = oldTile;
        savedInt = i;
        savedTurn = Game.turn;
    }

    public static void buildFarm(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Farm farm = new Farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, farm);
        Simulation.availWorkerAmt--;
        savedWood = Simulation.wood;
        Simulation.wood -= 10;
        savedOldTile = oldTile;
        savedInt = i;
        savedTurn = Game.turn;
    }
    public static void buildLaboratory(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Laboratory laboratory = new Laboratory(oldTile.row, oldTile.col);
        laboratory.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, laboratory);
        savedWood = Simulation.wood;
        savedStone = Simulation.stone;
        Simulation.stone -= 100;
        Simulation.availWorkerAmt--;
        savedOldTile = oldTile;
        savedInt = i;
        savedTurn = Game.turn;
    }
    public static void buildLumberyard(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Lumberyard lumberyard = new Lumberyard(oldTile.row, oldTile.col);
        lumberyard.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, lumberyard);
        Simulation.availWorkerAmt--;
        savedWood = Simulation.wood;
        Simulation.wood -= 8;
        savedOldTile = oldTile;
        savedInt = i;
        savedTurn = Game.turn;
    }
    public static void undoLast() {
        if(savedInt >= 0 && savedTurn == Game.turn) {
            GenerateTile.tileList.set(savedInt, savedOldTile);
            Simulation.availWorkerAmt++;
            Simulation.stone = savedStone;
            Simulation.wood = savedWood;
        }
    }
}