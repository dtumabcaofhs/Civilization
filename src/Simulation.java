public class Simulation {
    public static int population = 500;
    public static int workerAmt = 2;
    public static double foodLevel = 1.1;
    public static double consumptionLevel = 1.0;

    public static void simulateOneTick() {
        System.out.println(population);
        population = (int)(population*(1.0 + (foodLevel-consumptionLevel)));
        consumptionLevel = population/500.0;
    }

}
