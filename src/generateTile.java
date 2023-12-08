import tiles.*;
import tiles.buildings.city;
import tiles.terrain.forest;
import tiles.terrain.mountain;
import tiles.terrain.plain;
import java.util.ArrayList;

public class generateTile {
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
        generatePerson.generateWorker(4,4);
    }
}
