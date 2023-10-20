import Tiles.*;
import Tiles.Buildings.Farm;
import Tiles.Buildings.Laboratory;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;

public class BuildTile {
    public static Tile savedOldTile;
    public static int savedInt = -1,savedTurn;
    public static void buildMine(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        GenerateTile.tileList.set(i, mine);
        Simulation.availWorkerAmt--;
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
        Simulation.wood -= 8;
        savedOldTile = oldTile;
        savedInt = i;
        savedTurn = Game.turn;
    }
    public static void undoLast() {
        GenerateTile.tileList.set(savedInt, savedOldTile);
        Simulation.availWorkerAmt++;
    }
}