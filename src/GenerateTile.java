import Tiles.*;

import java.util.ArrayList;

public class GenerateTile {
    public static ArrayList<Tile> tileList = new ArrayList<>();

    public static boolean randomizeEnrichment() {
        double rand = Math.random();
        return rand < 0.2;
    }

    public static void generateTiles() {
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
                    Plains plains = new Plains(row, col);
                    plains.enriched = randomizeEnrichment();
                    tileList.add(plains);
                } else if (rand == 2) {
                    Mountain mountain = new Mountain(row, col);
                    mountain.enriched = randomizeEnrichment();
                    tileList.add(mountain);
                } else if (rand == 3){
                    System.out.println("sc");
                    City city = new City(row, col);
                    city.enriched = false;
                    tileList.add(city);
                }
            }
        }
    }
}
