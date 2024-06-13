import tiles.*;
import tiles.buildings.*;
import tiles.terrain.*;
import people.*;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import processing.core.PImage;

public class Game extends PApplet {
    // TODO: declare Game variables
    Minim minim;
    AudioPlayer bgm,bgm2,bgm3,bgm4;
    PImage icon;
    boolean inGame;
    static int day;
    static Tile placeType;
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
        surface.setTitle("Civilization "+Display.version); //App titlebar name
        surface.setResizable(true);
        if(displayWidth == 1920 && displayHeight == 1080) {
            surface.setLocation(1920/2-1600/2,(1080-100)/2-900/2);
        }
        surface.setFrameRate(240);
        icon = loadImage("images/civilizationLogo.png");
        surface.setIcon(icon);

        Display.logo = loadImage("images/logo.png");

        Tile.enrichedImg = loadImage("images/enrichedIcon.png");

        Village.img = loadImage("images/village.jpg");
        Farm.img = loadImage("images/farm.jpg");
        Mine.img = loadImage("images/mine.jpg");
        Lumberyard.img = loadImage("images/lumberyard.jpg");

        Forest.img = loadImage("images/forest.jpg");
        Mountain.img = loadImage("images/mountain.jpg");
        Plain.img = loadImage("images/plain.jpg");
        Laboratory.img = loadImage("images/laboratory.jpg");

        Worker.img = loadImage("images/worker.png");

        minim = new Minim(this);
        bgm = minim.loadFile("audio/9 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm2 = minim.loadFile("audio/10 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm3 = minim.loadFile("audio/12 PM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm4 = minim.loadFile("audio/Gangnam Style.mp3");

        gameSetup();
    }

    public void gameSetup(){
        inGame = false;
        day = 1;
        placeType = null;
        Simulation.setup();
        ManagePeople.personList.clear();
        ManageTiles.generateTerrain();
    }

    public void draw() {
        background(255);

        staticMouseX = mouseX;
        staticMouseY = mouseY;

        manageBGM();

        if(!inGame){
            Display.titleScreen(this);
        }
        if(inGame){
            Display.displayBackground(this);
            Display.displayTile(this);
            Display.displayUI(this);
            Display.displayInfo(this);
            Display.displayPeople(this);

            speedTimeFeature();

            boolean hoveringOverTile = false;
            for (int i = 0; i < 81; i++) {
                Tile newTile = ManageTiles.tileList.get(i);
                if (mouseOn(newTile.x, newTile.y, Tile.w, Tile.h)) {
                    hoveringOverTile = true;
                    Display.tileIndex = i;
                    newTile.hoveredOver = true;
                }else{
                    newTile.hoveredOver = false;
                }
            }
            if (!hoveringOverTile){
                Display.tileIndex = -1;
            }
        }
    }

    public boolean mouseOn(int x, int y, int w, int h){
        return staticMouseX >= x && staticMouseX <= x + w && staticMouseY >= y && staticMouseY <= y + h ;
    }


    public void mousePressed() {
        if(!inGame){
            textSize(45);
            inGame = mouseOn(Display.playX, Display.playY, (int) textWidth("Play") + 120, (int) (textAscent()+textDescent() + 120));
            textSize(30);
        }else{
            if(pressRegisters) {
                for (int i = 0; i < 81; i++) {
                    Tile newTile = ManageTiles.tileList.get(i);
                    if (mouseOn(newTile.x, newTile.y, Tile.w, Tile.h)) {
                        if (newTile instanceof Mountain && newTile.enriched && placeType instanceof Mine && isWorkerOnSelectedTile() && Simulation.wood >= Mine.cost) {
                            ManageTiles.placeMine(i);
                        }
                        if (!(newTile instanceof Village || newTile instanceof Farm) && placeType instanceof Farm && isWorkerOnSelectedTile() && Simulation.wood >= Farm.cost) {
                            ManageTiles.placeFarm(i);
                        }
                        if (!(newTile instanceof Village || newTile instanceof Laboratory) && newTile.enriched && placeType instanceof Laboratory && isWorkerOnSelectedTile() && Simulation.stone >= Laboratory.cost) {
                            ManageTiles.placeLaboratory(i);
                        }
                        if (newTile instanceof Forest && placeType instanceof Lumberyard && isWorkerOnSelectedTile() && Simulation.wood >= Lumberyard.cost) {
                            ManageTiles.placeLumberyard(i);
                        }
                    }
                }
                if (mouseOn(Display.buildWinX, 170, 100, 100)) {
                    if (placeType instanceof Mine) {
                        placeType = null;
                    } else {
                        placeType = new Mine(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(Display.buildWinX, 45, 100, 100)) {
                    if (placeType instanceof Farm) {
                        placeType = null;
                    } else {
                        placeType = new Farm(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(Display.buildWinX, 275, 100, 100)) {
                    if (placeType instanceof Laboratory) {
                        placeType = null;
                    } else {
                        placeType = new Laboratory(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(Display.buildWinX, 420, 100, 100)) {
                    if (placeType instanceof Lumberyard) {
                        placeType = null;
                    } else {
                        placeType = new Lumberyard(0, 0);
                        canPlaceWorker = false;
                    }
                }
                if (mouseOn(Display.buildWinX, 525, 100, 100)) {
                    if (!canPlaceWorker) {
                        placeType = null;
                        canPlaceWorker = true;
                    } else {
                        canPlaceWorker = false;
                    }
                }

                if (mouseOn(Display.undoX, Display.undoY, Display.undoW, Display.undoH)) {
                    undoLast();
                }

                if (placeType == null && !canPlaceWorker && clickedInWorldView()){
                    if (canSelectWorker) {
                        ManagePeople.selectPerson(this);
                    } else {
                        ManagePeople.movePerson(this);
                    }
                }else{
                    if(ManagePeople.selected != null) {
                        ManagePeople.selected.selected = false;
                    }
                    ManagePeople.selected = null;
                }


                if (canPlaceWorker && clickedInWorldView()) {
                    for (Tile t : ManageTiles.tileList) {
                        boolean isOccupied = false;

                        for (Person p : ManagePeople.personList) {
                            if (t.row == p.row && t.col == p.col) {
                                isOccupied = true;
                                break;
                            }
                        }

                        if (!isOccupied && mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                            // If the Tile is not occupied and the mouse is on it, add a new Worker
                            ManagePeople.generateWorker(t.row,t.col);
                        }
                    }
                }


                pressRegisters = false;
            }
        }
    }

    public void mouseReleased(){
        pressRegisters = true;
        canSelectWorker = ManagePeople.selected == null;
    }

    public void speedTimeFeature(){
        if(keyPressed && key == ' '){
            if(holdingNextTurn){
                Simulation.simulateOneTick();
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
            Simulation.simulateOneTick();
        }if(key == 'r' || key == 'R'){
            gameSetup();
        }
    }

    public void manageBGM(){
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
            ManageTiles.tileList.set(ManageTiles.savedTileIndex, ManageTiles.savedOldTile);
            Simulation.workerAmt= ManageTiles.savedWorkerNum;
            Simulation.stone = ManageTiles.savedStone;
            Simulation.wood = ManageTiles.savedWood;
            ManageTiles.savedDay = -1;
        }
        if(canUndoPerson()){
            System.out.println("HI");
            ManagePeople.personList.remove(ManagePeople.savedWorkerIndex);
            Simulation.workerAmt++;
            ManagePeople.savedDay = -1;
        }
    }

    public static boolean canUndoTile(){
        return ManageTiles.savedTileIndex >= 0 && ManageTiles.savedDay == Game.day;
    }

    public static boolean canUndoPerson(){
        return ManagePeople.savedWorkerIndex >= 0 && ManagePeople.savedDay == Game.day;
    }

    public static boolean isWorkerOnSelectedTile(){
        Game instance =  new Game();
        for(Tile t : ManageTiles.tileList){
            for(Person p : ManagePeople.personList){
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
        for(Tile t : ManageTiles.tileList) {
            if (mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                clickedInWorldView = true;
            }
        }
        return clickedInWorldView;
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
