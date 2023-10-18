import Tiles.*;

public class BuildTile {
    public static void buildMine(int i){
        Tile oldTile = GenerateTile.tileList.get(i);
        Mine mine = new Mine(oldTile.row, oldTile.col);
        GenerateTile.tileList.set(i, mine);
        GenerateTile.tileList.get(i).value = 4;
    }
}
