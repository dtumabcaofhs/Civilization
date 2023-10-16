import Tiles.Forest;

public class DisplayTile {
    public void diplay(Game w){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Forest forest = new Forest(i, j);
                forest.draw();
            }
        }
    }
}
