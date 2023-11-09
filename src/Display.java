import Tiles.*;
import Tiles.Buildings.*;
import Tiles.Terrain.Forest;
import Tiles.Terrain.Mountain;
import Tiles.Terrain.Plain;
import processing.core.PImage;

public class Display {
    static int undoX = 1400, undoY = 800, undoW = 200, undoH = 100;
    public static int buildWinX = 1450;
    static PImage logo;
    static int playX = 700, playY = 600, playW = 200, playH = 100;
    public static boolean canBuildOnHover = false;
    public static void titleScreen(Game window) {
        window.background(0);
        //title
        /*window.textSize(90);
        window.fill(0);
        window.text("Smurf Cat\n  Village", 560, 400);*/
        window.image(logo,400,382,819,135);

        //play button
        //window.rect(playX, playY, playW, playH);

        //play txt
        window.textSize(45);
        window.fill(255);
        if(window.mouseX >= playX && window.mouseX <= playX+playW && window.mouseY >= playY && window.mouseY <= playY+playH){
            window.fill(0,255,0);
        }
        window.text("Play", playX+60, playY+65);

        //credit
        window.fill(255);
        window.textSize(30);
        window.text("Developed by Dean T & Alan M", 580, 880);
    }

    public static void displayTile(Game window){
        for (int i = 0; i < GenerateTile.tileList.size(); i++) {
            Tile newTile = GenerateTile.tileList.get(i);
            newTile.draw(window, canBuildOnHover, Game.buildType);
        }
    }

    public static void displayBackground(Game window){
        //info window
        window.fill(0);
        window.rect(900,0,500,900);
        //build window
        window.fill(10);
        window.rect(1400,0,200,900);
    }

    public static void displayUI(Game window) {
        window.fill(255);
        window.text("Build", buildWinX+50-window.textWidth("Build")/2, 40);
        if(Simulation.wood >= Farm.cost && Simulation.workerAmt >= Farm.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        if(Game.buildType instanceof Mine) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,170,100,100);
        if(Game.buildType instanceof Farm) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX, 45, 100, 100);
        if(Game.buildType instanceof Laboratory) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,295,100,100);
        if(Game.buildType instanceof Lumberyard) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,420,100,100);

        if(Simulation.wood >= Farm.cost && Simulation.workerAmt >= Farm.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Farm", buildWinX+50-window.textWidth("Farm")/2, 105);

        if(Simulation.wood >= Mine.cost && Simulation.workerAmt >= Mine.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Mine", buildWinX+50-window.textWidth("Mine")/2, 230);

        if(Simulation.stone >= Laboratory.cost && Simulation.workerAmt >= Laboratory.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lab.", buildWinX+50-window.textWidth("Lab.")/2, 355);

        if(Simulation.wood >= Lumberyard.cost && Simulation.workerAmt >= Lumberyard.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lumb.", buildWinX+50-window.textWidth("Lumb.")/2, 480);

        //reset button
        window.fill(255,0,0);
        window.rect(undoX, undoY, undoW, undoH);
        if(BuildTile.savedTileIndex >= 0 && BuildTile.savedTurn == Game.turn) {
            window.fill(255);
        }else{
            window.fill(125);
        }
        window.textSize(50);
        window.text("Undo",undoX + ((float) undoW /2) - window.textWidth("Undo")/2,undoY + ((float) undoH /2) +20);
        window.textSize(30);
    }

    static int tileIndex = -1;
    public static void displayInfo(Game window) {
        window.fill(200);
        window.text("Day: "+Game.turn, 905, 30);
        window.fill(255);
        window.text("Day: ", 905, 30);

        if(Simulation.population > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Population: "+Simulation.population, 905, 80);
        window.fill(255);
        window.text("Population: ", 905, 80);

        int nextFood = Simulation.food - (Simulation.population * Simulation.personFoodConsumption);
        if(nextFood < 0) {
            nextFood = 0;
        }
        int foodSourceCount = 0;
        for (int i = 0; i < GenerateTile.tileList.size(); i++) {
            Tile currTile = GenerateTile.tileList.get(i);
            if (currTile instanceof Farm) {
                foodSourceCount++;
            }
        }
        if(Simulation.population > 0){
            nextFood += foodSourceCount*30;
        }if(nextFood == 0){
            window.fill(255,0,0);
        }else{
            window.fill(0,255,0);
        }
        window.text("Food: "+Simulation.food+"   Next food: "+nextFood, 905, 130);
        window.fill(255);
        window.text("Food: "+Simulation.food+"   Next food: ", 905, 130);
        if(Simulation.food > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Food: "+Simulation.food, 905, 130);
        window.fill(255);
        window.text("Food: ", 905, 130);


        if(Simulation.workerAmt > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Available Workers: "+Simulation.workerAmt, 905, 180);
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

        boolean enoughMaterials = false;
        int cost = -1;
        String material = "";
        if(Game.buildType instanceof Mine){
            cost = Mine.cost;
            material = "Wood";
            window.fill(255,0,0);
        }if(Game.buildType instanceof Farm){
            cost = Farm.cost;
            material = "Wood";
            window.fill(255,0,0);
        }if(Game.buildType instanceof Laboratory){
            cost = Laboratory.cost;
            material = "Stone";
            window.fill(255,0,0);
        }if(Game.buildType instanceof Lumberyard){
            cost = Lumberyard.cost;
            material = "Wood";
        }
        window.fill(255,0,0);
        if(material.equals("Wood")){
            if(Simulation.wood >= cost){
                window.fill(0, 255, 0);
                enoughMaterials = true;
            }
        }if(material.equals("Stone")){
            if(Simulation.stone >= cost){
                window.fill(0, 255, 0);
                enoughMaterials = true;
            }
        }

        if(cost >= 0) {
            window.text("Cost: " + cost + " " + material, 905, 650);
            window.fill(255);
            window.text("Cost:", 905, 650);
        }

        boolean enoughWorkers = false;
        int workersNeeded = -1;
        if(Game.buildType instanceof Mine){
            workersNeeded = Mine.workersNeeded;
        }if(Game.buildType instanceof Farm){
            workersNeeded = Farm.workersNeeded;
        }if(Game.buildType instanceof Laboratory){
            workersNeeded = Laboratory.workersNeeded;
        }if(Game.buildType instanceof Lumberyard){
            workersNeeded = Lumberyard.workersNeeded;
        }
        window.fill(255,0,0);
        if(Simulation.workerAmt >= workersNeeded) {
            enoughWorkers = true;
            window.fill(0, 255, 0);
        }

        if(workersNeeded >= 0) {
            window.text("Workers needed: " + workersNeeded, 905, 700);
            window.fill(255);
            window.text("Workers needed:", 905, 700);
        }

        String buildTiles = "";
        boolean enriched = false;
        if(Game.buildType instanceof Mine){
            buildTiles = Mine.buildTxt;
            enriched = true;
        }if(Game.buildType instanceof Farm){
            buildTiles = Farm.buildTxt;
        }if(Game.buildType instanceof Laboratory){
            buildTiles = Laboratory.buildTxt;
            enriched = true;
        }if(Game.buildType instanceof Lumberyard){
            buildTiles = Lumberyard.buildTxt;
        }
        if(!buildTiles.isEmpty()) {
            if(enriched) {
                window.fill(255,255,0);
                window.text("Buildable in: " + buildTiles, 905, 750);
            }else{
                window.fill(200);
                window.text("Buildable in: " + buildTiles, 905, 750);
            }
            window.fill(255);
            window.text("Buildable in: ", 905, 750);
        }


        boolean selectedBuildableTile = false;
        if(tileIndex >= 0) {
            Tile selectedTile = GenerateTile.tileList.get(tileIndex);
            Tile[] buildableIn = {};
            if (Game.buildType instanceof Mine) {
                buildableIn = Mine.buildableIn;
            }
            if (Game.buildType instanceof Farm) {
                buildableIn = Farm.buildableIn;
            }
            if (Game.buildType instanceof Laboratory) {
                buildableIn = Laboratory.buildableIn;
            }
            if (Game.buildType instanceof Lumberyard) {
                buildableIn = Lumberyard.buildableIn;
            }

            int buildableAreaNum = 0;
            for (Tile n : buildableIn) {
                if (selectedTile.getClass() == n.getClass()) {
                    if ((n.buildAreaEnrichmentNeed == 1 && selectedTile.enriched) || (n.buildAreaEnrichmentNeed == 2 && !selectedTile.enriched) || n.buildAreaEnrichmentNeed == 0) {
                        buildableAreaNum++;
                    }
                }
            }
            if(buildableAreaNum > 0){
                selectedBuildableTile = true;
            }
        }

        String buildType = "";
        if(Game.buildType != null) {
            buildType = Game.buildType.getClass().getSimpleName();
        }

        if(enoughWorkers && enoughMaterials && selectedBuildableTile) {
            canBuildOnHover = true;
            window.fill(0,255,0);
        }else{
            canBuildOnHover = false;
            window.fill(255,0,0);
        }
        window.text("Build: "+buildType, 905, 600);
        window.fill(255);
        window.text("Build: ", 905, 600);
    }
    public static void displayPeople(Game window) {
        for (int i = 0; i < GeneratePerson.personList.size(); i++) {
            GeneratePerson.personList.get(i).draw(window);
        }
    }
}
