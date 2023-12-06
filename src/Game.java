import Tiles.*;
import Tiles.Buildings.*;
import Tiles.Terrain.*;
import People.*;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import processing.core.PImage;

public class Game extends PApplet {
    // TODO: declare game variables
    Minim minim;
    AudioPlayer bgm,bgm2,bgm3,bgm4;
    PImage icon;
    boolean inGame;
    static int turn;
    static Tile buildType;
    int holdNextTurnTimer;
    boolean holdingNextTurn = false;
    static boolean workerButtState = false;
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
        icon = loadImage("Images/icon.jpg");
        surface.setIcon(icon);

        Display.logo = loadImage("Images/logo.png");

        City.img = loadImage("Images/village.jpg");
        Farm.img = loadImage("Images/farm.jpg");
        Mine.img = loadImage("Images/mine.jpg");
        Lumberyard.img = loadImage("Images/lumberyard.jpg");

        Forest.img = loadImage("Images/forest2.jpg");
        Mountain.img = loadImage("Images/mountain.jpg");
        Plain.img = loadImage("Images/plain.jpg");

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
        buildType = null;
        Simulation.setup();
        GenerateTile.generateTerrain();
    }

    public void draw() {
        background(255);
        if(!inGame){
            Display.titleScreen(this);
        }
        if(inGame){
            Display.displayBackground(this);
            Display.displayTile(this);
            Display.displayUI(this);
            Display.displayInfo(this);
            Display.displayPeople(this);

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

            boolean tileClicked = false;
            for (int i = 0; i < 81; i++) {
                Tile newTile = GenerateTile.tileList.get(i);
                if (mouseOn(newTile.x, newTile.y, Tile.w, Tile.h)) {
                    tileClicked = true;
                    Display.tileIndex = i;
                    newTile.selected = true;
                }else{
                    newTile.selected = false;
                }
            }
            if (!tileClicked){
                Display.tileIndex = -1;
            }
        }
    }

    public boolean mouseOn(int x, int y, int w, int h){
        return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
    }


    public void mouseClicked() {
        if(!inGame){
            textSize(45);
            inGame = mouseOn(Display.playX, Display.playY, (int) textWidth("Play") + 120, (int) (textAscent()+textDescent() + 120));
            textSize(30);
        }else{
            for (int i = 0; i < 81; i++) {
                Tile newTile = GenerateTile.tileList.get(i);
                if (mouseOn(newTile.x, newTile.y, Tile.w, Tile.h)) {
                    if (newTile instanceof Mountain && newTile.enriched && buildType instanceof Mine && Simulation.workerAmt >= Mine.workersNeeded && Simulation.wood >= Mine.cost) {
                        PlaceTile.buildMine(i);
                    }
                    if (!(newTile instanceof City || newTile instanceof Farm) && buildType instanceof Farm && Simulation.workerAmt >= Farm.workersNeeded && Simulation.wood >= Farm.cost) {
                        PlaceTile.buildFarm(i);
                    }
                    if (!(newTile instanceof City || newTile instanceof Laboratory) && newTile.enriched && buildType instanceof Laboratory && Simulation.workerAmt >= Laboratory.workersNeeded && Simulation.stone >= Laboratory.cost) {
                        PlaceTile.buildLaboratory(i);
                    }
                    if (newTile instanceof Forest && buildType instanceof Lumberyard && Simulation.workerAmt >= Lumberyard.workersNeeded && Simulation.wood >= Lumberyard.cost) {
                        PlaceTile.buildLumberyard(i);
                    }
                }
            }
            if (mouseOn(Display.buildWinX, 170, 100, 100)) {
                if(buildType instanceof Mine){
                    buildType = null;
                }else{
                    buildType = new Mine(0,0);
                    workerButtState = false;
                }
            }
            if (mouseOn(Display.buildWinX, 45, 100, 100)) {
                if(buildType instanceof Farm){
                    buildType = null;
                }else{
                    buildType = new Farm(0,0);
                    workerButtState = false;
                }
            }
            if (mouseOn(Display.buildWinX, 275, 100, 100)) {
                if(buildType instanceof Laboratory){
                    buildType = null;
                }else{
                    buildType = new Laboratory(0,0);
                    workerButtState = false;
                }
            }
            if (mouseOn(Display.buildWinX, 420, 100, 100)) {
                if(buildType instanceof Lumberyard){
                    buildType = null;
                }else{
                    buildType = new Lumberyard(0,0);
                    workerButtState = false;
                }
            }
            if (mouseOn(Display.buildWinX, 525, 100, 100)) {
                if(!workerButtState){
                    buildType = null;
                    workerButtState = true;
                }else{
                    workerButtState = false;
                }
            }
            if (mouseOn(Display.undoX, Display.undoY, Display.undoW, Display.undoH) && PlaceTile.savedTileIndex >= 0 && PlaceTile.savedTurn == Game.turn) {
                PlaceTile.undoLast();
            }

            for (int i = 0; i < GeneratePerson.personList.size(); i++) {
                Person newPerson = GeneratePerson.personList.get(i);
                if(mouseOn((newPerson.row*100)+30, (newPerson.col*100) + 30, 40, 40)) {
                    if(newPerson == GeneratePerson.selected) {
                        GeneratePerson.selected = null;
                    } else {
                        GeneratePerson.selected = newPerson;
                    }
                }
            }
            for (int i = 0; i < GeneratePerson.personList.size(); i++) {
                Person person = GeneratePerson.personList.get(i);
                if(person == GeneratePerson.selected) {
                    person.r = 0;
                    person.g = 255;
                    person.b = 255;
                    if(mouseOn((person.row*100) + 100, person.col*100, 100, 100)) {
                        person.row++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) + 100, (person.col*100) + 100, 100, 100)) {
                        person.row++;
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100), (person.col*100) + 100, 100, 100)) {
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, (person.col*100) + 100, 100, 100)) {
                        person.row--;
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, person.col*100, 100, 100)) {
                        person.row--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) - 100, (person.col*100) - 100, 100, 100)) {
                        person.row--;
                        person.col--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100), (person.col*100) - 100, 100, 100)) {
                        person.col--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(mouseOn((person.row*100) + 100, (person.col*100) - 100, 100, 100)) {
                        person.row++;
                        person.col--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                }
                if(person != GeneratePerson.selected) {
                    person.r = 255;
                    person.g = 0;
                    person.b = 0;
                }
            }
        }
    }

    public void keyReleased(){
        if (key == ' ' && !holdingNextTurn) {
            Simulation.simulateOneTick();
        }if(key == 's'){
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

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
