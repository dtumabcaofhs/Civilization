import java.util.ArrayList;

public class GenerateTile {
    static
    ArrayList<Integer> tileValues = new ArrayList<>();
    public static void randomizeTiles() {
        for (int i = 0; i < 64; i++) {
            int rand = (int)(Math.random()*3);
            tileValues.add(rand);
        }
    }
    public static int getValue(int i) {
        return tileValues.get(i);
    }
}
