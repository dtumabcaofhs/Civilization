import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import Tiles.Tile;

public class Game extends PApplet {
    // TODO: declare game variables
    boolean inGame;
    static int tick;
    boolean builderMode = false;
    TileUI save;

    Minim minim;
    AudioPlayer bg;

    public void settings() {
        size(1400, 900);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        surface.setTitle("Smurf Cat Village");
        inGame = false;
        GenerateTile.generateTiles();

        minim = new Minim(this);
        bg = minim.loadFile("Audio/y2mate.com - PSY  Gangnam Style Audio.mp3");
        bg.play();
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
            Display.displayUI(this, save);
        }
    }

    public boolean clickedOn(int x, int y, int w, int h){
        return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
    }


    public void mouseClicked() {
        if(!inGame){
            inGame = clickedOn(TitleScreen.playX, TitleScreen.playY, 200, 100);
        }
        for (int i = 0; i < 81; i++) {
            Tile newTile = GenerateTile.tileList.get(i);
            if(clickedOn(newTile.row*100, newTile.col*100,100,100)){
                save = new TileUI(i);
                if(GenerateTile.tileList.get(i).value == 2 && newTile.enriched && builderMode) {
                    BuildTile.buildMine(i);
                }
            }
        }
        if(clickedOn(950, 750,400,100)){
            builderMode = !builderMode;
        }
    }

    public void keyReleased(){
        if (key == ' ') {
            Simulation.simulateOneTick();
            tick++;
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
