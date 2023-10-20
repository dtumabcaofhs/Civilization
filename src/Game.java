import People.*;
import processing.core.PApplet;

//ADD "lib/AudioLib" as library to prevent errors.
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import Tiles.*;

public class Game extends PApplet {
    // TODO: declare game variables
    boolean inGame;
    static int turn;
    int buildType;
    static int lastTileIndex;
    Minim minim;
    AudioPlayer bg;
    public void settings() {
        size(1600, 900);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        surface.setTitle("Smurf Cat Village");
        inGame = false;
        GenerateTile.generateTiles();

        minim = new Minim(this);
        //bg = minim.loadFile("Audio/y2mate.com - PSY  Gangnam Style Audio.mp3");
        //bg.play();
        Simulation.simulateOneTick();
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
        }
    }

    public boolean clickedOn(int x, int y, int w, int h){
        return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
    }


    public void mouseClicked() {
        if(!inGame){
            inGame = clickedOn(TitleScreen.playX, TitleScreen.playY, 200, 100);
        }else{
            for (int i = 0; i < 81; i++) {
                Tile newTile = GenerateTile.tileList.get(i);
                lastTileIndex = i;
                if (clickedOn(newTile.row * 100, newTile.col * 100, 100, 100)) {
                    newTile.selected = true;
                    Display.tileIndex = i;
                    if (newTile.value == 2 && newTile.enriched && buildType == 4 && Simulation.availWorkerAmt > 0 && Simulation.wood >= 30) {
                        BuildTile.buildMine(i);
                    }
                    if (newTile.value < 3 && buildType == 5 && Simulation.wood >= 10 && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildFarm(i);
                    }
                    if (newTile.value < 3 && newTile.enriched && buildType == 6 && Simulation.stone >= 100 && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildLaboratory(i);
                    }
                    if (newTile.value == 0 && buildType == 7 && Simulation.wood >= 5 && Simulation.availWorkerAmt > 0) {
                        BuildTile.buildLumberyard(i);
                    }
                }else{
                    newTile.selected = false;
                }
            }
            if (clickedOn(1450, 170, 100, 100)) {
                if(buildType == 4){
                    buildType = 0;
                }else{
                    buildType = 4;
                }
            }
            if (clickedOn(1450, 45, 100, 100)) {
                if(buildType == 5){
                    buildType = 0;
                }else{
                    buildType = 5;
                }
            }
            if (clickedOn(1425, 275, 100, 100)) {
                if(buildType == 6){
                    buildType = 0;
                }else{
                    buildType = 6;
                }
            }
            if (clickedOn(1425, 420, 100, 100)) {
                if(buildType == 7){
                    buildType = 0;
                }else{
                    buildType = 7;
                }
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
            if (clickedOn(Display.undoX, Display.undoY, Display.undoW, Display.undoH) && turn != 0) {
                BuildTile.undoLast();
            }
        }
    }

    public void keyReleased(){
        if (key == ' ') {
            Simulation.simulateOneTick();
            turn++;
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
