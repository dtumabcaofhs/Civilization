import Tiles.Forest;

public class DisplayTile {
    public static void display(Game window){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Forest forest = new Forest(i, j);
                forest.draw(window);
            }
        }
    }
}
