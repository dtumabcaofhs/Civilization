import Tiles.*;

public class DisplayTile {
    public static void display(Game window){
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GenerateTile.getValue(counter) == 0) {
                    Forest forest = new Forest(i,j);
                    forest.draw(window);
                    counter++;
                } else if (GenerateTile.getValue(counter) == 1) {
                    Plain plain = new Plain(i,j);
                    plain.draw(window);
                    counter++;
                } else if (GenerateTile.getValue(counter) == 2) {
                    Mountain mountain = new Mountain(i, j);
                    mountain.draw(window);
                    counter++;
                } else if (GenerateTile.getValue(counter) == 3){
                    City city = new City(i, j);
                    city.draw(window);
                    counter++;
                }
            }
        }
    }
}
