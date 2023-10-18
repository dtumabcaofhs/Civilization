import Tiles.*;

public class Display {
    public static void displayTile(Game window){
        for (int i = 0; i < 81; i++) {
            Tile newTile = GenerateTile.tileList.get(i);
            newTile.draw(window);
        }
        window.fill(0,0,55);
        window.rect(900,0,500,900);
    }
    public static void displayUI(Game window, TileUI ui) {
        if(ui != null) {
            ui.draw(window);
        }

        window.text("Turn: "+Game.tick, 905, 30);

        window.fill(0);
        window.rect(950, 750, 400, 100);
        window.fill(255);
        if(window.builderMode) {
            window.fill(0,255,0);
            window.text("Builder Mode: ON", 1020, 810);
        } else {
            window.fill(255,0,0);
            window.text("Builder Mode: OFF", 1020, 810);
        }
    }
}
