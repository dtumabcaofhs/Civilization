import processing.core.PApplet;

public class TitleScreen {

    public static void draw(Game window) {
        //title
        window.fill(0);
        window.textSize(90);
        window.text("Smurf Cat\n  Village", 180, 300);

        //play button
        window.rect(300, 500, 200, 100);
        window.fill(255);
        window.textSize(45);
        window.text("Play", 360, 565);
        if (window.clickedOn(300, 500, 200, 100)) {
            window.inGame = true;
        }

        //credit
        window.fill(0);
        window.textSize(30);
        window.text("Developed by Dean & Alan", 200, 780);
    }
}

