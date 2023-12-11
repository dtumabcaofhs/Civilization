import people.*;

import java.util.ArrayList;

public class generatePerson {
    public static person selected;
    static ArrayList<person> personList = new ArrayList<>();
    public static void generateWorker(int row, int col) {
        worker worker = new worker(row, col);
        personList.add(worker);
    }
}
