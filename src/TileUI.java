import Tiles.Tile;

public class TileUI {

    private int listIndex;
    public TileUI(int i) {
        this.listIndex = i;

    }
    public void draw(Game window) {
        Tile t = GenerateTile.tileList.get(listIndex);
        int tileValue = GenerateTile.tileList.get(listIndex).value;
        window.fill(255);
        if (tileValue == 0) {
            window.text("Type: Forest", 1000, 100);
        } else if (tileValue == 1) {
            window.text("Type: Plain", 1000, 100);
        } else if (tileValue == 2) {
            window.text("Type: Mountain", 1000, 100);
        } else if (tileValue == 3) {
            window.text("Type: City", 1000, 100);
        } else if (tileValue == 4) {
            window.text("Type: Mine", 1000, 100);
        }
        if(t.enriched && tileValue != 3) {
            window.text("Enriched Tile", 1000, 200);
        } else if (tileValue != 3){
            window.text("Normal Tile", 1000, 200); }
        }
}