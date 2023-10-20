import Tiles.*;
import Tiles.Buildings.*;
import Tiles.Terrain.Forest;
import Tiles.Terrain.Mountain;
import Tiles.Terrain.Plain;

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
        if(Game.buildType instanceof Mine) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,170,100,100);
        //farm
        if(Game.buildType instanceof Farm) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440, 45, 100, 100);
        //lab
        if(Game.buildType instanceof Laboratory) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,295,100,100);
        //lumber
        if(Game.buildType instanceof Lumberyard) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(1440,420,100,100);

        //reset button
        window.fill(255,0,0);
        window.rect(undoX, undoY, undoW, undoH);
        if(BuildTile.savedInt >= 0 && BuildTile.savedTurn == Game.turn) {
            window.fill(255);
        }else{
            window.fill(125);
        }
        window.text("Undo",1460,858);

        window.fill(255);
        window.text("Build", 1455, 30);
        if(Simulation.wood >= Farm.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Farm", 1455, 105);
        if(Simulation.wood >= Mine.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Mine", 1455, 230);
        if(Simulation.stone >= Laboratory.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lab.", 1460, 355);
        if(Simulation.wood >= Lumberyard.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lumb.", 1445, 480);
    }

    static int tileIndex = -1;
    public static void displayInfo(Game window) {
        window.fill(200);
        window.text("Turn: "+Game.turn, 905, 30);
        window.fill(255);
        window.text("Turn: ", 905, 30);
        window.fill(200);
        window.text("Population: "+Simulation.population, 905, 80);
        window.fill(255);
        window.text("Population: ", 905, 80);
        if(Simulation.food >= 100){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Food: "+Simulation.food, 905, 130);
        window.fill(255);
        window.text("Food: ", 905, 130);
        if(Simulation.availWorkerAmt > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Available Workers: "+Simulation.availWorkerAmt, 905, 180);
        window.fill(255);
        window.text("Available Workers: ", 905, 180);
        if(Simulation.wood > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Wood: "+Simulation.wood, 905, 230);
        window.fill(255);
        window.text("Wood: ", 905, 230);
        if(Simulation.stone > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Stone: "+Simulation.stone, 905, 280);
        window.fill(255);
        window.text("Stone: ", 905, 280);

        String type = "";
        if(tileIndex >= 0) {
            Tile selectedTile = GenerateTile.tileList.get(tileIndex);
            if (selectedTile instanceof Forest) {
                type = "Forest";
            } else if (selectedTile instanceof Plain) {
                type = "Plain";
            } else if (selectedTile instanceof Mountain) {
                type = "Mountain";
            } else if (selectedTile instanceof City) {
                type = "City";
            } else if (selectedTile instanceof Mine) {
                type = "Mine";
            } else if (selectedTile instanceof Farm) {
                type = "Farm";
            } else if (selectedTile instanceof Laboratory) {
                type = "Laboratory";
            } else if (selectedTile instanceof Lumberyard) {
                type = "Lumberyard";
            }

            if(selectedTile.enriched) {
                window.fill(255,255,0);
                window.text("Selected Tile: Enriched " + type, 905, 380);
            }else{
                window.fill(200);
                window.text("Selected Tile: " + type, 905, 380);
            }
        }
        window.fill(255);
        window.text("Selected Tile: ", 905, 380);


        String buildType = "";
        if(Game.buildType instanceof Mine){
            buildType = "Mine";
        }if(Game.buildType instanceof Farm){
            buildType = "Farm";
        }if(Game.buildType instanceof Laboratory){
            buildType = "Laboratory";
        }if(Game.buildType instanceof Lumberyard){
            buildType = "Lumberyard";
        }
        window.fill(200);
        window.text("Build: "+buildType, 905, 600);
        window.fill(255);
        window.text("Build: ", 905, 600);

        int cost = -1;
        String material = "";
        if(Game.buildType instanceof Mine){
            cost = Mine.cost;
            material = "Wood";
            window.fill(255,0,0);
            if(Simulation.wood >= Mine.cost) {
                window.fill(0, 255, 0);
            }
        }if(Game.buildType instanceof Farm){
            cost = Farm.cost;
            material = "Wood";
            window.fill(255,0,0);
            if(Simulation.wood >= Farm.cost) {
                window.fill(0, 255, 0);
            }
        }if(Game.buildType instanceof Laboratory){
            cost = Laboratory.cost;
            material = "Stone";
            window.fill(255,0,0);
            if(Simulation.stone >= Laboratory.cost) {
                window.fill(0, 255, 0);
            }
        }if(Game.buildType instanceof Lumberyard){
            cost = Lumberyard.cost;
            material = "Wood";
            window.fill(255,0,0);
            if(Simulation.wood >= Lumberyard.cost) {
                window.fill(0, 255, 0);
            }
        }

        if(cost >= 0) {
            window.text("Cost: " + cost + " " + material, 905, 650);
            window.fill(255);
            window.text("Cost:", 905, 650);
        }

        if(Simulation.wood >= Lumberyard.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }

        String buildTiles = "";
        boolean enriched = false;
        if(Game.buildType instanceof Mine){
            buildTiles = Mine.buildableIn;
            enriched = true;
        }if(Game.buildType instanceof Farm){
            buildTiles = Farm.buildableIn;
        }if(Game.buildType instanceof Laboratory){
            buildTiles = Laboratory.buildableIn;
            enriched = true;
        }if(Game.buildType instanceof Lumberyard){
            buildTiles = Lumberyard.buildableIn;
        }
        if(!buildTiles.isEmpty()) {
            if(enriched) {
                window.fill(255,255,0);
                window.text("Buildable in: " + buildTiles, 905, 700);
            }else{
                window.fill(200);
                window.text("Buildable in: " + buildTiles, 905, 700);
            }
            window.fill(255);
            window.text("Buildable in: ", 905, 700);
        }
    }
    public static void displayPeople(Game window) {
        for (int i = 0; i < GeneratePerson.personList.size(); i++) {
            GeneratePerson.personList.get(i).draw(window);
        }
    }
}
