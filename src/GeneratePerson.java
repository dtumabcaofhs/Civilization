import People.*;

import java.util.ArrayList;

public class GeneratePerson {
    public static Person selected;
    static ArrayList<Person> personList = new ArrayList<>();
    public static void generateWorker(int row, int col) {
        Worker worker = new Worker(row, col);
        personList.add(worker);
    }
}
