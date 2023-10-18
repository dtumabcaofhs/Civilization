package Tiles;

public class Mine extends Tile{
    public Mine(int row, int col) {
        super(row, col);
        r = 255;
        g = 105;
        b = 105;
        value = 4;
        enriched = true;
    }
}
