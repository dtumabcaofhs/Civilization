import processing.core.PApplet;
public class Game extends PApplet {
    // TODO: declare game variables
    boolean inGame;

    public void settings() {
        size(900, 900);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        inGame = false;
        GenerateTile.randomizeTiles();
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
            DisplayTile.display(this);
        }
    }

    public boolean clickedOn(int x, int y, int w, int h){
        if(mousePressed){
            return mouseY >= y && mouseY <= y + h && mouseX >= x && mouseX <= x + w;
        }
        return false;
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
