import processing.core.PApplet;

public class Game extends PApplet {
    // TODO: declare game variables
    int population, food, workers, crime;
    boolean inGame;
    String societyType;

    public void settings() {
        size(800, 800);   // set the window size
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

            //title
            fill(0);
            textSize(90);
            text("Smurf Cat\n  Village", 180 ,300);

            //play button
            rect(300,500,200,100);
            fill(255);
            textSize(45);
            text("Play", 360 ,565);
            if(clickedOn(300,500,200,100)){
                inGame=true;
            }

            //credit
            fill(0);
            textSize(30);
            text("Developed by Dean & Alan", 200 ,780);
        }

        if(inGame){
            fill(0);
            textSize(100);
            text(societyType, 10 ,80);
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
