import processing.core.PApplet;

public class Game extends PApplet {
    // TODO: declare game variables
    int population, food, workers, crime;
    boolean inGame;
    String societyType;

    public void settings() {
        size(900, 900);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        societyType = "Agrarian";
        inGame = false;
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(255);    // paint screen white
        if(!inGame){
            TitleScreen.draw(this);
        }

        if(inGame){
            //fill(0);
            //textSize(100);
            //text(societyType, 10 ,80);
            DisplayTile.display(this);
        }
    }

    public boolean clickedOn(int x, int y, int w, int h){
        if(mousePressed){
            if (mouseX >= x && mouseX <= x + w) {
                if(mouseY >= y && mouseY <= y + h){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
