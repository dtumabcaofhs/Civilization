import tiles.*;
import tiles.buildings.*;
import tiles.terrain.*;
import people.*;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import processing.core.PImage;

public class game extends PApplet {
    // TODO: declare game variables
    Minim minim;
    AudioPlayer bgm,bgm2,bgm3,bgm4;
    PImage icon;
    boolean inGame;
    static int day;
    static tile placeType;
    int holdNextTurnTimer;
    boolean holdingNextTurn = false;
    static boolean canPlaceWorker = false;
    boolean canSelectWorker = true;
    boolean pressRegisters = true;
    static int staticMouseX, staticMouseY;
    public void settings() {
        size(1600, 900);   // set the window size
    }

    public void setup() {
        surface.setTitle("Civilization"); //App titlebar name
        surface.setResizable(true);
        if(displayWidth == 1920 && displayHeight == 1080) {
            surface.setLocation(1920/2-1600/2,(1080-100)/2-900/2);
        }
        surface.setFrameRate(240);
        icon = loadImage("images/CivilizationLogo.jpg");
        surface.setIcon(icon);

        display.logo = loadImage("images/logo.png");

        tile.enrichedImg = loadImage("images/enrichedIcon.png");

        city.img = loadImage("images/village.jpg");
        farm.img = loadImage("images/farm.jpg");
        mine.img = loadImage("images/mine.jpg");
        lumberyard.img = loadImage("images/lumberyard.jpg");

        forest.img = loadImage("images/forest.jpg");
        mountain.img = loadImage("images/mountain.jpg");
        plain.img = loadImage("images/plain.jpg");
        laboratory.img = loadImage("images/laboratory.jpg");

        worker.img = loadImage("images/workerIcon.png");

        minim = new Minim(this);
        bgm = minim.loadFile("Audio/9 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm2 = minim.loadFile("Audio/10 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm3 = minim.loadFile("Audio/12 PM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm4 = minim.loadFile("Audio/Gangnam Style.mp3");

        chooseBGM();

        gameSetup();
    }

    public void gameSetup(){
        inGame = false;
        day = 1;
        placeType = null;
        simulation.setup();
        manageTiles.generateTerrain();
    }

    public void draw() {
        background(255);
        staticMouseX = mouseX;
        staticMouseY = mouseY;
        if(!inGame){
            display.titleScreen(this);
        }
        if(inGame){
            display.displayBackground(this);
            display.displayTile(this);
            display.displayUI(this);
            display.displayInfo(this);
            display.displayPeople(this);

            speedTimeFeature();

            boolean hoveringOverTile = false;
            for (int i = 0; i < 81; i++) {
                tile newTile = manageTiles.tileList.get(i);
                if (mouseOn(newTile.x, newTile.y, tile.w, tile.h)) {
                    hoveringOverTile = true;
                    display.tileIndex = i;
                    newTile.hoveredOver = true;
                }else{
                    newTile.hoveredOver = false;
                }
            }
            if (!hoveringOverTile){
                display.tileIndex = -1;
            }

            System.out.println(managePeople.savedWorkerIndex);
        }
    }

    public boolean mouseOn(int x, int y, int w, int h){
        return staticMouseX >= x && staticMouseX <= x + w && staticMouseY >= y && staticMouseY <= y + h ;
    }


    public void mousePressed() {
        if(!inGame){
            textSize(45);
            inGame = mouseOn(display.playX, display.playY, (int) textWidth("Play") + 120, (int) (textAscent()+textDescent() + 120));
            textSize(30);
        }else{
            if(pressRegisters) {
                for (int i = 0; i < 81; i++) {
                    tile newTile = manageTiles.tileList.get(i);
                    if (mouseOn(newTile.x, newTile.y, tile.w, tile.h)) {
                        if (newTile instanceof mountain && newTile.enriched && placeType instanceof mine && isWorkerOnSelectedTile() && simulation.wood >= mine.cost) {
                            manageTiles.placeMine(i);
                        }
                        if (!(newTile instanceof city || newTile instanceof farm) && placeType instanceof farm && isWorkerOnSelectedTile() && simulation.wood >= farm.cost) {
                            manageTiles.placeFarm(i);
                        }
                        if (!(newTile instanceof city || newTile instanceof laboratory) && newTile.enriched && placeType instanceof laboratory && isWorkerOnSelectedTile() && simulation.stone >= laboratory.cost) {
                            manageTiles.placeLaboratory(i);
                        }
                        if (newTile instanceof forest && placeType instanceof lumberyard && isWorkerOnSelectedTile() && simulation.wood >= lumberyard.cost) {
                            manageTiles.placeLumberyard(i);
                        }
                    }
                }
                if (mouseOn(display.buildWinX, 170, 100, 100)) {
                    if (placeType instanceof mine) {
                        placeType = null;
                    } else {
                        placeType = new mine(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(display.buildWinX, 45, 100, 100)) {
                    if (placeType instanceof farm) {
                        placeType = null;
                    } else {
                        placeType = new farm(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(display.buildWinX, 275, 100, 100)) {
                    if (placeType instanceof laboratory) {
                        placeType = null;
                    } else {
                        placeType = new laboratory(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(display.buildWinX, 420, 100, 100)) {
                    if (placeType instanceof lumberyard) {
                        placeType = null;
                    } else {
                        placeType = new lumberyard(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(display.buildWinX, 525, 100, 100)) {
                    if (!canPlaceWorker) {
                        placeType = null;
                        canPlaceWorker = true;
                    } else {
                        canPlaceWorker = false;
                    }
                }

                if (mouseOn(display.undoX, display.undoY, display.undoW, display.undoH)) {
                    undoLast();
                }

                if (placeType == null && !canPlaceWorker && clickedInWorldView()){
                    if (canSelectWorker) {
                        managePeople.selectPerson(this);
                    } else {
                        managePeople.movePerson(this);
                    }
                }else{
                    if(managePeople.selected != null) {
                        managePeople.selected.selected = false;
                    }
                    managePeople.selected = null;
                }

                if(placeType == null && canPlaceWorker && clickedInWorldView()){
                    managePeople.generateWorker(mouseX/100,mouseY/100);
                }

                if (canPlaceWorker) {
                    for (tile t : manageTiles.tileList) {
                        boolean isOccupied = false;

                        for (person p : managePeople.personList) {
                            if (t.row == p.row && t.col == p.col) {
                                isOccupied = true;
                                break;
                            }
                        }

                        if (!isOccupied && mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                            // If the tile is not occupied and the mouse is on it, add a new worker
                            managePeople.generateWorker(t.row,t.col);
                        }
                    }}

                pressRegisters = false;
            }
        }
    }

    public void mouseReleased(){
        pressRegisters = true;
        canSelectWorker = managePeople.selected == null;
    }

    public void speedTimeFeature(){
        if(keyPressed && key == ' '){
            if(holdingNextTurn){
                simulation.simulateOneTick();
            }
            holdNextTurnTimer++;
            if(holdNextTurnTimer >= 10){
                holdingNextTurn = true;
            }
        }else{
            holdingNextTurn = false;
            holdNextTurnTimer = 0;
        }
    }

    public void keyReleased(){
        if (key == ' ' && !holdingNextTurn) {
            simulation.simulateOneTick();
        }if(key == 'r' || key == 'R'){
            gameSetup();
        }
    }

    public void chooseBGM(){
        if(!bgm.isPlaying() && !bgm2.isPlaying() && !bgm3.isPlaying() && !bgm4.isPlaying()) {
            int x = (int) (Math.random() * 10);
            if (x < 3) {
                bgm.rewind();
                bgm.play();
            } else if (x < 6) {
                bgm2.rewind();
                bgm2.play();
            } else if (x < 9) {
                bgm3.rewind();
                bgm3.play();
            } else {
                bgm4.rewind();
                bgm4.play();
            }
        }
    }

    public static void undoLast() {
        System.out.println("HEY");
        if(canUndoTile()){
            manageTiles.tileList.set(manageTiles.savedTileIndex, manageTiles.savedOldTile);
            simulation.workerAmt= manageTiles.savedWorkerNum;
            simulation.stone = manageTiles.savedStone;
            simulation.wood = manageTiles.savedWood;
            manageTiles.savedDay = -1;
        }
        if(canUndoPerson()){
            System.out.println("HI");
            managePeople.personList.remove(managePeople.savedWorkerIndex);
            simulation.workerAmt++;
            managePeople.savedDay = -1;
        }
    }

    public static boolean canUndoTile(){
        return manageTiles.savedTileIndex >= 0 && manageTiles.savedDay == game.day;
    }

    public static boolean canUndoPerson(){
        return managePeople.savedWorkerIndex >= 0 && managePeople.savedDay == game.day;
    }

    public static boolean isWorkerOnSelectedTile(){
        game instance =  new game();
        for(tile t : manageTiles.tileList){
            for(person p : managePeople.personList){
                if(t.row == p.row && t.col == p.col){
                    if(instance.mouseOn(t.row*100,t.col*100,100,100)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean clickedInWorldView(){
        boolean clickedInWorldView = false;
        for(tile t : manageTiles.tileList) {
            if (mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                clickedInWorldView = true;
            }
        }
        return clickedInWorldView;
    }

    public static void main(String[] args) {
        PApplet.main("game");
    }
}
