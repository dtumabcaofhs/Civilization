import people.person;
import tiles.*;
import tiles.buildings.*;
import tiles.terrain.forest;
import tiles.terrain.mountain;
import tiles.terrain.plain;
import processing.core.PImage;

public class display {
    static int undoX = 1400, undoY = 800, undoW = 200, undoH = 100;
    public static int buildWinX = 1450;
    static PImage logo;
    static int playX = 700, playY = 600, playW = 200, playH = 100;
    public static boolean canBuildOnHover = false;
    public static void titleScreen(game window) {
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

    public static void displayTile(game window){
        for (int i = 0; i < generateTile.tileList.size(); i++) {
            tile newTile = generateTile.tileList.get(i);
            newTile.draw(window, canBuildOnHover, game.placeType);
        }
    }

    public static void displayBackground(game window){
        //info window
        window.fill(0);
        window.rect(900,0,500,900);
        //build window
        window.fill(10);
        window.rect(1400,0,200,900);
    }

    public static void displayUI(game window) {
        window.fill(255);
        window.text("Place", buildWinX+50-window.textWidth("Place")/2, 35);
        if(simulation.wood >= farm.cost && simulation.workerAmt >= farm.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }

        if(game.placeType instanceof farm) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX, 45, 100, 100);
        if(game.placeType instanceof mine) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,170,100,100);
        if(game.placeType instanceof laboratory) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,295,100,100);
        if(game.placeType instanceof lumberyard) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,420,100,100);

        if(simulation.wood >= farm.cost && simulation.workerAmt >= farm.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Farm", buildWinX+50-window.textWidth("Farm")/2, 45+60);

        if(simulation.wood >= mine.cost && simulation.workerAmt >= mine.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Mine", buildWinX+50-window.textWidth("Mine")/2, 170+60);

        if(simulation.stone >= laboratory.cost && simulation.workerAmt >= laboratory.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lab.", buildWinX+50-window.textWidth("Lab.")/2, 295+60);

        if(simulation.wood >= lumberyard.cost && simulation.workerAmt >= lumberyard.workersNeeded) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Lumb.", buildWinX+50-window.textWidth("Lumb.")/2, 420+60);

        if(game.canPlaceWorker) {window.fill(100,100,100);
        } else {window.fill(50,50,50);}
        window.rect(buildWinX,545,100,100);
        if(simulation.workerAmt >= 0) {
            window.fill(0, 255, 0);
        }else{
            window.fill(255,0,0);
        }
        window.textSize(25);
        window.text("Worker", buildWinX+50-window.textWidth("Worker")/2, 545+60);

        //reset button
        window.fill(255,0,0);
        window.rect(undoX, undoY, undoW, undoH);
        if(placeTile.savedTileIndex >= 0 && placeTile.savedTurn == game.turn) {
            window.fill(255);
        }else{
            window.fill(125);
        }
        window.textSize(50);
        window.text("Undo",undoX + ((float) undoW /2) - window.textWidth("Undo")/2,undoY + ((float) undoH /2) +20);
        window.textSize(30);
    }

    static int tileIndex = -1;
    public static void displayInfo(game window) {
        window.fill(200);
        window.text("Day: "+ game.turn, 905, 30);
        window.fill(255);
        window.text("Day: ", 905, 30);

        if(simulation.population > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Population: "+ simulation.population, 905, 80);
        window.fill(255);
        window.text("Population: ", 905, 80);

        int nextFood = simulation.food - (simulation.population * simulation.personFoodConsumption);
        if(nextFood < 0) {
            nextFood = 0;
        }
        int foodSourceCount = 0;
        for (int i = 0; i < generateTile.tileList.size(); i++) {
            tile currTile = generateTile.tileList.get(i);
            if (currTile instanceof farm) {
                foodSourceCount++;
            }
        }
        if(simulation.population > 0){
            nextFood += foodSourceCount*30;
        }if(nextFood == 0){
            window.fill(255,0,0);
        }else{
            window.fill(0,255,0);
        }
        window.text("Food: "+ simulation.food+"   Next food: "+nextFood, 905, 130);
        window.fill(255);
        window.text("Food: "+ simulation.food+"   Next food: ", 905, 130);
        if(simulation.food > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Food: "+ simulation.food, 905, 130);
        window.fill(255);
        window.text("Food: ", 905, 130);


        if(simulation.workerAmt > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Available Workers: "+ simulation.workerAmt, 905, 180);
        window.fill(255);
        window.text("Available Workers: ", 905, 180);


        if(simulation.wood > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Wood: "+ simulation.wood, 905, 230);
        window.fill(255);
        window.text("Wood: ", 905, 230);


        if(simulation.stone > 0){
            window.fill(0,255,0);
        }else{
            window.fill(255,0,0);
        }
        window.text("Stone: "+ simulation.stone, 905, 280);
        window.fill(255);
        window.text("Stone: ", 905, 280);


        String type = "";
        if(tileIndex >= 0) {
            tile selectedTile = generateTile.tileList.get(tileIndex);
            if (selectedTile instanceof forest) {
                type = "Forest";
            } else if (selectedTile instanceof plain) {
                type = "Plain";
            } else if (selectedTile instanceof mountain) {
                type = "Mountain";
            } else if (selectedTile instanceof city) {
                type = "City";
            } else if (selectedTile instanceof mine) {
                type = "Mine";
            } else if (selectedTile instanceof farm) {
                type = "Farm";
            } else if (selectedTile instanceof laboratory) {
                type = "Laboratory";
            } else if (selectedTile instanceof lumberyard) {
                type = "Lumberyard";
            }

            if(selectedTile.enriched) {
                window.fill(255,255,0);
                window.text("Selected tile: Enriched " + type, 905, 380);
            }else{
                window.fill(200);
                window.text("Selected tile: " + type, 905, 380);
            }
        }
        window.fill(255);
        window.text("Selected tile: ", 905, 380);

        boolean enoughMaterials = false;
        int cost = -1;
        String material = "";
        if(game.placeType instanceof mine){
            cost = mine.cost;
            material = "Wood";
            window.fill(255,0,0);
        }if(game.placeType instanceof farm){
            cost = farm.cost;
            material = "Wood";
            window.fill(255,0,0);
        }if(game.placeType instanceof laboratory){
            cost = laboratory.cost;
            material = "Stone";
            window.fill(255,0,0);
        }if(game.placeType instanceof lumberyard){
            cost = lumberyard.cost;
            material = "Wood";
        }
        window.fill(255,0,0);
        if(material.equals("Wood")){
            if(simulation.wood >= cost){
                window.fill(0, 255, 0);
                enoughMaterials = true;
            }
        }if(material.equals("Stone")){
            if(simulation.stone >= cost){
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
        if(game.placeType instanceof mine){
            workersNeeded = mine.workersNeeded;
        }if(game.placeType instanceof farm){
            workersNeeded = farm.workersNeeded;
        }if(game.placeType instanceof laboratory){
            workersNeeded = laboratory.workersNeeded;
        }if(game.placeType instanceof lumberyard){
            workersNeeded = lumberyard.workersNeeded;
        }
        window.fill(255,0,0);
        if(simulation.workerAmt >= workersNeeded) {
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
        if(game.placeType instanceof mine){
            buildTiles = mine.buildTxt;
            enriched = true;
        }if(game.placeType instanceof farm){
            buildTiles = farm.buildTxt;
        }if(game.placeType instanceof laboratory){
            buildTiles = laboratory.buildTxt;
            enriched = true;
        }if(game.placeType instanceof lumberyard){
            buildTiles = lumberyard.buildTxt;
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
            tile selectedTile = generateTile.tileList.get(tileIndex);
            tile[] buildableIn = {};
            if (game.placeType instanceof mine) {
                buildableIn = mine.buildableIn;
            }
            if (game.placeType instanceof farm) {
                buildableIn = farm.buildableIn;
            }
            if (game.placeType instanceof laboratory) {
                buildableIn = laboratory.buildableIn;
            }
            if (game.placeType instanceof lumberyard) {
                buildableIn = lumberyard.buildableIn;
            }

            int buildableAreaNum = 0;
            for (tile n : buildableIn) {
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

        String placeType = "";
        if(game.placeType != null) {
            if(game.placeType instanceof mine){
                placeType = "Mine";
            }if(game.placeType instanceof farm){
                placeType = "Farm";
            }if(game.placeType instanceof laboratory){
                placeType = "Laboratory";
            }if(game.placeType instanceof lumberyard){
                placeType = "Lumberyard";
            }

            if(enoughWorkers && enoughMaterials && selectedBuildableTile) {
                canBuildOnHover = true;
                window.fill(0,255,0);
            }else{
                canBuildOnHover = false;
                window.fill(255,0,0);
            }
        }else{
            if((game.canPlaceWorker)){
                placeType = "Worker";
                boolean notHoveringOverWorkerTile = true, hoveringOverATile = false;
                for (person p : generatePerson.personList) {
                    if(window.mouseOn(p.row * 100, p.col * 100, 100, 100)){
                        notHoveringOverWorkerTile = false;
                    }
                }
                for (tile t : generateTile.tileList) {
                    if(window.mouseOn(t.x, t.y, 100, 100)){
                        hoveringOverATile = true;
                    }
                }
                if (simulation.workerAmt > 0 && hoveringOverATile && notHoveringOverWorkerTile) {
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
    public static void displayPeople(game window) {
        for (int i = 0; i < generatePerson.personList.size(); i++) {
            generatePerson.personList.get(i).draw(window);
        }
    }
}