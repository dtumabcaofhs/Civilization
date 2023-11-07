import Tiles.*;
import Tiles.Buildings.City;
import Tiles.Terrain.Forest;
import Tiles.Terrain.Mountain;
import Tiles.Terrain.Plain;
import java.util.ArrayList;

public class GenerateTile {
    public static ArrayList<Tile> tileList = new ArrayList<>();

    public static boolean randomizeEnrichment() {
        double rand = Math.random();
        return rand < 0.2;
    }

    public static void generateTerrain() {
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
        GeneratePerson.generateWorker(4,4);
    }
}
