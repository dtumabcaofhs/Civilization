import processing.core.PApplet;
import processing.core.PImage;

public class TitleScreen {
    static PImage logo;
    static int playX = 700, playY = 600, playW = 200, playH = 100;
    public static void draw(PApplet window) {
        //title
        window.fill(0);
        window.textSize(90);
        //window.text("Smurf Cat\n  Village", 560, 400);
        window.image(logo,400,382,819,135);

        //play button
        window.rect(playX, playY, playW, playH);
        window.fill(255,0,0);
        window.textSize(45);

        //play txt
        window.fill(255);
        if(window.mouseX >= playX && window.mouseX <= playX+playW && window.mouseY >= playY && window.mouseY <= playY+playH){
            window.fill(0,255,0);
        }
        window.text("Play", 760, 665);

        //credit
        window.fill(0);
        window.textSize(30);
        window.text("Developed by Dean T & Alan M", 580, 880);
    }
}

