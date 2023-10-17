import java.util.ArrayList;

public class GenerateTile {
    static
    ArrayList<Integer> tileValues = new ArrayList<>();
    public static void randomizeTiles() {
        for (int i = 0; i < 81; i++) {
            int rand = (int)(Math.random()*3);
            tileValues.add(rand);
        }
        tileValues.set(40, 3);
        for (int i = 0; i < GenerateTile.tileValues.size(); i++) {
            System.out.println(GenerateTile.tileValues.get(i));
        }
    }
    public static int getValue(int i) {
        return tileValues.get(i);
    }
}
