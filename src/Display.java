import Tiles.*;

public class Display {
    public static void displayTile(Game window){
        for (int i = 0; i < 81; i++) {
            Tile newTile = GenerateTile.tileList.get(i);
            newTile.draw(window);
        }
        window.fill(0,0,55);
        window.rect(900,0,500,900);
        window.fill(150,75,0);
        window.rect(1400,0,200,900);
    }
    public static void displayUI(Game window, TileUI ui) {
        if(ui != null) {
            ui.draw(window);
        }
        window.text("Turn: "+Game.turn, 905, 30);
        //farm
        if(window.farmMode) {window.fill(0,255,0);
        } else {window.fill(255,0,0);}
        window.rect(1425, 25, 100, 100);
        //mine
        if(window.mineMode) {window.fill(0,255,0);
        } else {window.fill(255,0,0);}
        window.rect(1425,150,100,100);
        window.fill(0);
        window.text("Farm", 1450, 75);
        window.text("Mine", 1450, 200);

    }
}
