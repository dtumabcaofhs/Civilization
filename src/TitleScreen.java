public class TitleScreen {
    static int playX = 570, playY = 600, playW = 200, playH = 100;
    public static void draw(Game window) {
        //title
        window.fill(0);
        window.textSize(90);
        window.text("Smurf Cat\n  Village", 460, 400);

        //play button
        window.rect(playX, playY, playW, playH);
        window.fill(255,0,0);
        window.textSize(45);

        //play txt
        window.text("Play", 630, 665);

        //credit
        window.fill(0);
        window.textSize(30);
        window.text("Developed by Dean & Alan", 480, 880);
    }
}

