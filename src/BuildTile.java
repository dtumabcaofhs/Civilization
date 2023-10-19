import Tiles.*;

public class BuildTile {
    public static void buildMine(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        GenerateTile.tileList.set(i, mine);
        Simulation.availWorkerAmt--;
    }
    public static void buildFarm(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Farm farm = new Farm(oldTile.row, oldTile.col);
        farm.enriched = oldTile.enriched;
        GenerateTile.tileList.set(i, farm);
        Simulation.availWorkerAmt--;
    }
}
