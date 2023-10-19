public class Simulation {
    public static int population = 500;
    public static int maxWorkerAmt = 2;
    public static int availWorkerAmt;
    public static double foodLevel = 1.1;

    public static void simulateOneTick() {
        availWorkerAmt = maxWorkerAmt;
        foodLevel = 1.1;
        System.out.println(population);
        population = (int)(population*foodLevel);
        for (int i = 0; i < GenerateTile.tileList.size(); i++) {
            if(GenerateTile.tileList.get(i).value == 5) { foodLevel += 0.05;
            }
        }
    }
}
