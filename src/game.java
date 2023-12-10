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
    static int turn;
    static tile placeType;
    int holdNextTurnTimer;
    boolean holdingNextTurn = false;
    static boolean canPlaceWorker = false;
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
        icon = loadImage("images/icon.jpg");
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

        worker.img = loadImage("images/workerIcon.png");

        gameSetup();

        minim = new Minim(this);
        bgm = minim.loadFile("Audio/9 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm2 = minim.loadFile("Audio/10 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm3 = minim.loadFile("Audio/12 PM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm4 = minim.loadFile("Audio/Gangnam Style.mp3");

        chooseBGM();
    }

    public void gameSetup(){
        inGame = false;
        turn = 1;
        placeType = null;
        simulation.setup();
        generateTile.generateTerrain();
    }

    public void draw() {
        background(255);
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
                tile newTile = generateTile.tileList.get(i);
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
        }
    }

    public boolean mouseOn(int x, int y, int w, int h){
        return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
    }


    public void mouseClicked() {
        if(!inGame){
            textSize(45);
            inGame = mouseOn(display.playX, display.playY, (int) textWidth("Play") + 120, (int) (textAscent()+textDescent() + 120));
            textSize(30);
        }else{
            for (int i = 0; i < 81; i++) {
                tile newTile = generateTile.tileList.get(i);
                if (mouseOn(newTile.x, newTile.y, tile.w, tile.h)) {
                    if (newTile instanceof mountain && newTile.enriched && placeType instanceof mine && simulation.workerAmt >= mine.workersNeeded && simulation.wood >= mine.cost) {
                        placeTile.placeMine(i);
                    }
                    if (!(newTile instanceof city || newTile instanceof farm) && placeType instanceof farm && simulation.workerAmt >= farm.workersNeeded && simulation.wood >= farm.cost) {
                        placeTile.placeFarm(i);
                    }
                    if (!(newTile instanceof city || newTile instanceof laboratory) && newTile.enriched && placeType instanceof laboratory && simulation.workerAmt >= laboratory.workersNeeded && simulation.stone >= laboratory.cost) {
                        placeTile.placeLaboratory(i);
                    }
                    if (newTile instanceof forest && placeType instanceof lumberyard && simulation.workerAmt >= lumberyard.workersNeeded && simulation.wood >= lumberyard.cost) {
                        placeTile.placeLumberyard(i);
                    }
                }
            }
            if (mouseOn(display.buildWinX, 170, 100, 100)) {
                if(placeType instanceof mine){
                    placeType = null;
                }else{
                    placeType = new mine(0,0);
                    canPlaceWorker = false;
                }
            }
            if (mouseOn(display.buildWinX, 45, 100, 100)) {
                if(placeType instanceof farm){
                    placeType = null;
                }else{
                    placeType = new farm(0,0);
                    canPlaceWorker = false;
                }
            }
            if (mouseOn(display.buildWinX, 275, 100, 100)) {
                if(placeType instanceof laboratory){
                    placeType = null;
                }else{
                    placeType = new laboratory(0,0);
                    canPlaceWorker = false;
                }
            }
            if (mouseOn(display.buildWinX, 420, 100, 100)) {
                if(placeType instanceof lumberyard){
                    placeType = null;
                }else{
                    placeType = new lumberyard(0,0);
                    canPlaceWorker = false;
                }
            }
            if (mouseOn(display.buildWinX, 525, 100, 100)) {
                if(!canPlaceWorker){
                    placeType = null;
                    canPlaceWorker = true;
                }else{
                    canPlaceWorker = false;
                }
            }

            if (mouseOn(display.undoX, display.undoY, display.undoW, display.undoH) && placeTile.savedTileIndex >= 0 && placeTile.savedTurn == game.turn) {
                placeTile.undoLast();
            }
            if(game.canPlaceWorker) {
                placeWorker();
            }

            for (int i = 0; i < generatePerson.personList.size(); i++) {
                person newPerson = generatePerson.personList.get(i);
                if(mouseOn((newPerson.row*100)+30, (newPerson.col*100) + 30, 40, 40)) {
                    if(newPerson == generatePerson.selected) {
                        generatePerson.selected = null;
                    } else {
                        generatePerson.selected = newPerson;
                    }
                }
            }
            for (int i = 0; i < generatePerson.personList.size(); i++) {
                person person = generatePerson.personList.get(i);
                if(person == generatePerson.selected) {
                    person.r = 0;
                    person.g = 255;
                    person.b = 255;
                    if(mouseOn((person.row*100) + 100, person.col*100, 100, 100)) {
                        person.row++;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) + 100, (person.col*100) + 100, 100, 100)) {
                        person.row++;
                        person.col++;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100), (person.col*100) + 100, 100, 100)) {
                        person.col++;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, (person.col*100) + 100, 100, 100)) {
                        person.row--;
                        person.col++;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, person.col*100, 100, 100)) {
                        person.row--;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, (person.col*100) - 100, 100, 100)) {
                        person.row--;
                        person.col--;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100), (person.col*100) - 100, 100, 100)) {
                        person.col--;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) + 100, (person.col*100) - 100, 100, 100)) {
                        person.row++;
                        person.col--;
                        generatePerson.personList.set(i, person);
                        generatePerson.selected = null;
                    }
                }
                if(person != generatePerson.selected) {
                    person.r = 255;
                    person.g = 0;
                    person.b = 0;
                }
            }
        }
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
        }if(key == 's'){
            gameSetup();
        }
    }

    public void placeWorker(){
        boolean notHoveringOverWorkerTile = true, hoveringOverATile = false;
        for (person p : generatePerson.personList) {
            if(mouseOn(p.row * 100, p.col * 100, 100, 100)){
                notHoveringOverWorkerTile = false;
            }
        }
        for (tile t : generateTile.tileList) {
            if(mouseOn(t.x, t.y, 100, 100)){
                hoveringOverATile = true;
            }
        }
        if (simulation.workerAmt > 0 && hoveringOverATile && notHoveringOverWorkerTile) {
            generatePerson.generateWorker((int)mouseX/100,(int)mouseY/100);
            simulation.workerAmt--;
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

    public static void main(String[] args) {
        PApplet.main("game");
    }
}
