import Tiles.Buildings.Farm;
import Tiles.Buildings.Laboratory;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;
import Tiles.Terrain.Forest;
import Tiles.Terrain.Mountain;
import Tiles.Terrain.Plain;
import People.*;
import processing.core.PApplet;

//ADD "lib/AudioLib" as library to prevent errors.
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import Tiles.Tile;

public class Game extends PApplet {
    // TODO: declare game variables
    boolean inGame;
    static int turn = 1;
    static Tile buildType;
    static int lastTileIndex;
    int holdNextTurnTimer;
    boolean holdingNextTurn = false;
    Minim minim;
    AudioPlayer bgm,bgm2,bgm3,bgm4;
    public void settings() {
        size(1600, 900);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        surface.setTitle("Smurf Cat Village");
        inGame = false;
        GenerateTile.generateTiles();

        minim = new Minim(this);
        bgm = minim.loadFile("Audio/9 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm2 = minim.loadFile("Audio/10 AM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm3 = minim.loadFile("Audio/12 PM  Animal Crossing New Horizons Soundtrack.mp3");
        bgm4 = minim.loadFile("Audio/Gangnam Style.mp3");
        int x = (int)(Math.random()*10);
        if(x < 3){
            bgm.play();
        }else if(x < 6){
            bgm2.play();
        }else if(x < 9){
            bgm3.play();
        }else{
            bgm4.play();
        }
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(255);
        if(!inGame){
            TitleScreen.draw(this);
        }
        if(inGame){
            Display.displayTile(this);
            Display.displayUI(this);
            Display.displayInfo(this);
            Display.displayPeople(this);

            if(keyPressed && key == ' '){
                if(holdingNextTurn){
                    Simulation.simulateOneTick();
                }
                holdNextTurnTimer++;
                if(holdNextTurnTimer >= 50){
                    holdingNextTurn = true;
                }
            }else{
                holdingNextTurn = false;
                holdNextTurnTimer = 0;
            }
        }
    }

    public boolean clickedOn(int x, int y, int w, int h){
        return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
    }


    public void mouseClicked() {
        if(!inGame){
            inGame = clickedOn(TitleScreen.playX, TitleScreen.playY, 200, 100);
        }else{
            boolean tileIsClicked = false;
            for (int i = 0; i < 81; i++) {
                Tile newTile = GenerateTile.tileList.get(i);
                lastTileIndex = i;
                if (clickedOn(newTile.row * 100, newTile.col * 100, 100, 100)) {
                    tileIsClicked = true;
                    newTile.selected = true;
                    Display.tileIndex = i;
                    if (newTile instanceof Mountain && newTile.enriched && buildType instanceof Mine && Simulation.availWorkerAmt > 0 && Simulation.wood >= Mine.cost) {
                        BuildTile.buildMine(i);
                    }
                    if ((newTile instanceof Forest || newTile instanceof Plain || newTile instanceof Mountain) && buildType instanceof Farm && Simulation.wood >= Farm.cost && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildFarm(i);
                    }
                    if ((newTile instanceof Forest || newTile instanceof Plain || newTile instanceof Mountain) && newTile.enriched && buildType instanceof Laboratory && Simulation.stone >= Laboratory.cost && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildLaboratory(i);
                    }
                    if (newTile instanceof Forest && buildType instanceof Lumberyard && Simulation.wood >= Lumberyard.cost && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildLumberyard(i);
                    }
                }else{
                    newTile.selected = false;
                }
            }
            if(!tileIsClicked){
                Display.tileIndex = -1;
            }
            if (clickedOn(1450, 170, 100, 100)) {
                if(buildType instanceof Mine){
                    buildType = null;
                }else{
                    buildType = new Mine(0,0);
                }
            }
            if (clickedOn(1450, 45, 100, 100)) {
                if(buildType instanceof Farm){
                    buildType = null;
                }else{
                    buildType = new Farm(0,0);
                }
            }
            if (clickedOn(1425, 275, 100, 100)) {
                if(buildType instanceof Laboratory){
                    buildType = null;
                }else{
                    buildType = new Laboratory(0,0);
                }
            }
            if (clickedOn(1425, 420, 100, 100)) {
                if(buildType instanceof Lumberyard){
                    buildType = null;
                }else{
                    buildType = new Lumberyard(0,0);
                }
            }
            if (clickedOn(Display.undoX, Display.undoY, Display.undoW, Display.undoH) && BuildTile.savedInt >= 0 && BuildTile.savedTurn == Game.turn) {
                BuildTile.undoLast();
            }

            for (int i = 0; i < GeneratePerson.personList.size(); i++) {
                Person newPerson = GeneratePerson.personList.get(i);
                if(clickedOn((newPerson.row*100)+30, (newPerson.col*100) + 30, 40, 40)) {
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
                    if(clickedOn((person.row*100) + 100, person.col*100, 100, 100)) {
                        person.row++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100) + 100, (person.col*100) + 100, 100, 100)) {
                        person.row++;
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100), (person.col*100) + 100, 100, 100)) {
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100) - 100, (person.col*100) + 100, 100, 100)) {
                        person.row--;
                        person.col++;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100) - 100, person.col*100, 100, 100)) {
                        person.row--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100) - 100, (person.col*100) - 100, 100, 100)) {
                        person.row--;
                        person.col--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100), (person.col*100) - 100, 100, 100)) {
                        person.col--;
                        GeneratePerson.personList.set(i, person);
                        GeneratePerson.selected = null;
                    }
                    if(clickedOn((person.row*100) + 100, (person.col*100) - 100, 100, 100)) {
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
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
