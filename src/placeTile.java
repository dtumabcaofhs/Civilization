import tiles.*;
import tiles.buildings.farm;
import tiles.buildings.laboratory;
import tiles.buildings.lumberyard;
import tiles.buildings.mine;

public class placeTile {
    public static tile savedOldTile;
    public static int savedTileIndex,savedTurn,savedWood,savedStone,savedWorkerNum;
    public static void placeMine(int i){
        tile oldTile = generateTile.tileList.get(i);
        mine mine = new mine(oldTile.row, oldTile.col);
        generateTile.tileList.set(i, mine);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.mine.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.wood;
        simulation.wood -= tiles.buildings.mine.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = game.turn;
    }

    public static void placeFarm(int i){
        tile oldTile = generateTile.tileList.get(i);
        farm farm = new farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        generateTile.tileList.set(i, farm);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.farm.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.wood;
        simulation.wood -= tiles.buildings.farm.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = game.turn;
    }
    public static void placeLaboratory(int i){
        tile oldTile = generateTile.tileList.get(i);
        laboratory laboratory = new laboratory(oldTile.row, oldTile.col);
        laboratory.enriched = oldTile.enriched;
        generateTile.tileList.set(i, laboratory);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.laboratory.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.stone;
        simulation.stone -= tiles.buildings.laboratory.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = game.turn;
    }
    public static void placeLumberyard(int i){
        tile oldTile = generateTile.tileList.get(i);
        lumberyard lumberyard = new lumberyard(oldTile.row, oldTile.col);
        lumberyard.enriched = oldTile.enriched;
        generateTile.tileList.set(i, lumberyard);
        savedWorkerNum = simulation.workerAmt;
        simulation.workerAmt-= tiles.buildings.lumberyard.workersNeeded;
        savedWood = simulation.wood;
        savedStone = simulation.stone;
        simulation.wood -= tiles.buildings.lumberyard.cost;
        savedOldTile = oldTile;
        savedTileIndex = i;
        savedTurn = game.turn;
    }

    public static void undoLast() {
        if(savedTileIndex >= 0 && savedTurn == game.turn) {
            generateTile.tileList.set(savedTileIndex, savedOldTile);
            simulation.workerAmt=savedWorkerNum;
            simulation.stone = savedStone;
            simulation.wood = savedWood;
            savedTurn = -1;
        }
    }
}