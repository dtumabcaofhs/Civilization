import Tiles.*;

public class Display {
    static int undoX = 1400, undoY = 800, undoW = 200, undoH = 100;
    public static void displayTile(Game window){
        for (int i = 0; i < 81; i++) {
            Tile newTile = GenerateTile.tileList.get(i);
            newTile.draw(window);
        }
        //info window
        window.fill(0,0,55);
        window.rect(900,0,500,900);
        //build window
        window.fill(150,75,0);
        window.rect(1400,0,200,900);
    }
    public static void displayUI(Game window) {
        //mine
        if(window.buildType == 4) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,170,100,100);
        //farm
        if(window.buildType == 5) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440, 45, 100, 100);
        //lab
        if(window.buildType == 6) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,295,100,100);
        //lumber
        if(window.buildType == 7) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,420,100,100);

        //reset button
        window.fill(255,0,0);
        window.rect(undoX, undoY, undoW, undoH);
        if(BuildTile.savedTurn == Game.turn && Game.turn != 0) {
            window.fill(255);
        }else{
            window.fill(125);
        }
        window.text("Undo",1460,855);

        window.fill(255);
        window.text("Build", 1455, 30);
        window.text("Farm", 1455, 105);
        window.text("Mine", 1455, 230);
        window.text("Lab.", 1460, 355);
        window.text("Lumb.", 1445, 480);
    }

    static int tileIndex = -1;
    public static void displayInfo(Game window) {
        window.fill(255);
        window.text("Turn: "+Game.turn, 905, 30);
        window.text("Population: "+Simulation.population, 905, 70);
        window.text("Food: "+Simulation.food, 905, 110);
        window.text("Available Workers: "+Simulation.availWorkerAmt, 905, 150);
        window.text("Wood: "+Simulation.wood, 905, 190);
        window.text("Stone: "+Simulation.stone, 905, 230);
        if (tileIndex >= 0) {
            Tile t = GenerateTile.tileList.get(tileIndex);
            int tileValue = GenerateTile.tileList.get(tileIndex).value;
            String type = "";
            if (tileValue == 0) {
                type = "Forest";
            } else if (tileValue == 1) {
                type = "Plain";
            } else if (tileValue == 2) {
                type = "Mountain";
            } else if (tileValue == 3) {
                type = "City";
            } else if (tileValue == 4) {
                type = "Mine";
            } else if (tileValue == 5) {
                type = "Farm";
            }else if (tileValue == 6) {
                type = "Laboratory";
            } else if (tileValue == 7) {
                type = "Lumberyard";
            }
            window.text("Tile Type: "+type, 905, 270);
            int enrichTxtX = 905, enrichTxtY = 310;
            if (t.enriched && tileValue != 3) {
                window.fill(255);
                window.text("Enriched Tile", enrichTxtX, enrichTxtY);
                window.fill(255,255,0);
                window.text("Enriched", enrichTxtX, enrichTxtY);
            } else if (tileValue != 3) {
                window.text("Normal Tile", enrichTxtX, enrichTxtY);
            }
        }
    }
}
