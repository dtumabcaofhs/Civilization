import people.Person;
import tiles.*;
import tiles.buildings.*;
import tiles.terrain.Forest;
import tiles.terrain.Mountain;
import tiles.terrain.Plain;
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
        window.text("Developed by Dean Tumabcao & Alan McWilliams", 450, 880);
    }

    public static void displayTile(Game window){
        for (int i = 0; i < ManageTiles.tileList.size(); i++) {
            Tile newTile = ManageTiles.tileList.get(i);
            newTile.draw(window, canBuildOnHover, Game.placeType, Game.canPlaceWorker);
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
        window.text("Place", buildWinX+50-window.textWidth("Place")/2, 35);
        if(Simulation.wood >= Farm.cost && Simulation.workerAmt >= Farm.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }

        if(Game.placeType instanceof Farm) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX, 45, 100, 100);
        if(Game.placeType instanceof Mine) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,170,100,100);
        if(Game.placeType instanceof Laboratory) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,295,100,100);
        if(Game.placeType instanceof Lumberyard) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,420,100,100);

        if(Simulation.wood >= Farm.cost) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Farm", buildWinX+50-window.textWidth("Farm")/2, 45+60);

        if(Simulation.wood >= Mine.cost && Simulation.workerAmt >= Mine.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Mine", buildWinX+50-window.textWidth("Mine")/2, 170+60);

        if(Simulation.stone >= Laboratory.cost && Simulation.workerAmt >= Laboratory.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lab.", buildWinX+50-window.textWidth("Lab.")/2, 295+60);

        if(Simulation.wood >= Lumberyard.cost && Simulation.workerAmt >= Lumberyard.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lumb.", buildWinX+50-window.textWidth("Lumb.")/2, 420+60);

        if(Game.canPlaceWorker) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,545,100,100);
        if(Simulation.workerAmt >= 0) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.textSize(25);
        window.text("Worker", buildWinX+50-window.textWidth("Worker")/2, 545+60);

        //reset button
        window.fill(255,0,0);
        window.rect(undoX, undoY, undoW, undoH);
        if(Game.canUndoTile() || Game.canUndoPerson()) {
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
        window.text("Day: "+ Game.day, 905, 30);
        window.fill(255);
        window.text("Day: ", 905, 30);

        if(Simulation.population > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Population: "+ Simulation.population, 905, 80);
        window.fill(255);
        window.text("Population: ", 905, 80);

        int nextFood = getNextFood();
        if(nextFood == 0){
            window.fill(255,0,0);
        }else{
            window.fill(0,255,0);
        }
        window.text("Food: "+ Simulation.food+"   Next Food: "+nextFood, 905, 130);
        window.fill(255);
        window.text("Food: "+ Simulation.food+"   Next Food: ", 905, 130);
        if(Simulation.food > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Food: "+ Simulation.food, 905, 130);
        window.fill(255);
        window.text("Food: ", 905, 130);


        if(Simulation.workerAmt > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Available Workers: "+ Simulation.workerAmt, 905, 180);
        window.fill(255);
        window.text("Available Workers: ", 905, 180);


        if(Simulation.wood > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Wood: "+ Simulation.wood, 905, 230);
        window.fill(255);
        window.text("Wood: ", 905, 230);


        if(Simulation.stone > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Stone: "+ Simulation.stone, 905, 280);
        window.fill(255);
        window.text("Stone: ", 905, 280);


        String type = "";
        if(tileIndex >= 0) {
            Tile selectedTile = ManageTiles.tileList.get(tileIndex);
            if (selectedTile instanceof Forest) {
                type = "Forest";
            } else if (selectedTile instanceof Plain) {
                type = "Plain";
            } else if (selectedTile instanceof Mountain) {
                type = "Mountain";
            } else if (selectedTile instanceof Village) {
                type = "Village";
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

        int cost = -1;
        String material = "";
        boolean enoughMaterials = false;
        if(Game.placeType != null) {
            if (Game.placeType instanceof Mine) {
                cost = Mine.cost;
                material = "Wood";
                window.fill(255, 0, 0);
            }
            if (Game.placeType instanceof Farm) {
                cost = Farm.cost;
                material = "Wood";
                window.fill(255, 0, 0);
            }
            if (Game.placeType instanceof Laboratory) {
                cost = Laboratory.cost;
                material = "Stone";
                window.fill(255, 0, 0);
            }
            if (Game.placeType instanceof Lumberyard) {
                cost = Lumberyard.cost;
                material = "Wood";
            }
            window.fill(255, 0, 0);
            if (material.equals("Wood")) {
                if (Simulation.wood >= cost) {
                    window.fill(0, 255, 0);
                    enoughMaterials = true;
                }
            }
            if (material.equals("Stone")) {
                if (Simulation.stone >= cost) {
                    window.fill(0, 255, 0);
                    enoughMaterials = true;
                }
            }
        }
        if (Game.canPlaceWorker) {
            if(Simulation.workerAmt >= 1){
                window.fill(0,255,0);
            }else{
                window.fill(255,0,0);
            }
            window.text("Cost: 1 Available Worker", 905, 650);
        } else if(cost >= 0) {
            window.text("Cost: " + cost + " " + material, 905, 650);
        }
        window.fill(255);
        window.text("Cost:", 905, 650);


        if (Game.isWorkerOnSelectedTile()) {
            if(Game.placeType != null) {
                window.fill(0, 255, 0);
            }else if(Game.canPlaceWorker) {
                window.fill(255,0,0);
            }else{
                window.fill(255);
            }
        } else {
            if(Game.placeType != null) {
                window.fill(255,0, 0);
            }else if(Game.canPlaceWorker) {
                window.fill(0,255,0);
            }else{
                window.fill(255);
            }
        }
        if (Game.isWorkerOnSelectedTile()) {
            window.text("Worker On Selected Tile: Yes", 905, 700);
        } else {
            window.text("Worker On Selected Tile: No", 905, 700);
        }
        window.fill(255);
        window.text("Worker On Selected Tile:", 905, 700);


        String placeableTiles = "";
        boolean enriched = false;
        if(Game.placeType instanceof Mine){
            placeableTiles = Mine.buildTxt;
            enriched = true;
        }if(Game.placeType instanceof Farm){
            placeableTiles = Farm.buildTxt;
        }if(Game.placeType instanceof Laboratory){
            placeableTiles = Laboratory.buildTxt;
            enriched = true;
        }if(Game.placeType instanceof Lumberyard){
            placeableTiles = Lumberyard.buildTxt;
        }
        if(!placeableTiles.isEmpty() || Game.canPlaceWorker) {
            if(enriched) {
                window.fill(255,255,0);
            }else{
                window.fill(200);
            }
            if(!placeableTiles.isEmpty()){
                window.text("Placeable in: " + placeableTiles, 905, 750);
            }else{
                window.text("Placeable in: Any Tile without a\nWorker on it", 905, 750);
            }
            window.fill(255);
            window.text("Placeable in: ", 905, 750);
        }


        boolean selectedPlaceableTile = false;
        if(tileIndex >= 0) {
            int buildableAreaNum = getBuildableAreaNum();
            if(buildableAreaNum > 0){
                selectedPlaceableTile = true;
            }
        }

        String placeType = "";
        if(Game.placeType != null) {
            if(Game.placeType instanceof Mine){
                placeType = "Mine";
            }if(Game.placeType instanceof Farm){
                placeType = "Farm";
            }if(Game.placeType instanceof Laboratory){
                placeType = "Laboratory";
            }if(Game.placeType instanceof Lumberyard){
                placeType = "Lumberyard";
            }

            if(Game.isWorkerOnSelectedTile() && enoughMaterials && selectedPlaceableTile) {
                canBuildOnHover = true;
                window.fill(0,255,0);
            }else{
                canBuildOnHover = false;
                window.fill(255,0,0);
            }
        }else{
            if((Game.canPlaceWorker)){
                placeType = "Worker";
                boolean notHoveringOverWorkerTile = true, hoveringOverATile = false;
                for (Person p : ManagePeople.personList) {
                    if(window.mouseOn(p.row * 100, p.col * 100, 100, 100)){
                        notHoveringOverWorkerTile = false;
                    }
                }
                for (Tile t : ManageTiles.tileList) {
                    if(window.mouseOn(t.x, t.y, 100, 100)){
                        hoveringOverATile = true;
                    }
                }
                if (Simulation.workerAmt > 0 && hoveringOverATile && notHoveringOverWorkerTile) {
                    canBuildOnHover = true;
                    window.fill(0, 255, 0);
                } else {
                    canBuildOnHover = false;
                    window.fill(255, 0, 0);
                }
            }
        }


        window.text("Place: "+placeType, 905, 600);
        window.fill(255);
        window.text("Place: ", 905, 600);
    }

    private static int getNextFood() {
        int nextFood = Simulation.food - (Simulation.population * Simulation.personFoodConsumption);
        if(nextFood < 0) {
            nextFood = 0;
        }
        int foodSourceCount = 0;
        for (int i = 0; i < ManageTiles.tileList.size(); i++) {
            Tile currTile = ManageTiles.tileList.get(i);
            if (currTile instanceof Farm) {
                foodSourceCount++;
            }
        }
        if(Simulation.population > 0){
            nextFood += foodSourceCount*30;
        }
        return nextFood;
    }

    private static int getBuildableAreaNum() {
        Tile selectedTile = ManageTiles.tileList.get(tileIndex);
        Tile[] buildableIn = {};
        if (Game.placeType instanceof Mine) {
            buildableIn = Mine.buildableIn;
        }
        if (Game.placeType instanceof Farm) {
            buildableIn = Farm.buildableIn;
        }
        if (Game.placeType instanceof Laboratory) {
            buildableIn = Laboratory.buildableIn;
        }
        if (Game.placeType instanceof Lumberyard) {
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
        return buildableAreaNum;
    }

    public static void displayPeople(Game window) {
        for (int i = 0; i < ManagePeople.personList.size(); i++) {
            ManagePeople.personList.get(i).draw(window);
        }
    }
}
