import processing.core.PApplet;

public class TitleScreen {
    public static void draw(Game window) {
        //title
        window.fill(0);
        window.textSize(90);
        window.text("Smurf Cat\n  Village", 280, 400);

        //play button
        window.rect(400, 600, 200, 100);
        window.fill(255);
        window.textSize(45);
        window.text("Play", 360, 565);
        if (window.clickedOn(400, 600, 200, 100)) {
            window.inGame = true;
        }

        //credit
        window.fill(0);
        window.textSize(30);
        window.text("Developed by Dean & Alan", 300, 880);
    }
}

