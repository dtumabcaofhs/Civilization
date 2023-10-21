import Tiles.*;
import Tiles.Buildings.Farm;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;

public class Simulation {
    public static int population = 20;
    public static int food = 500, personFoodConsumption = 1, foodConsumption;
    public static int workerAmt = population/2;
    public static int wood = 20;
    public static int stone = 20;

    public static void simulateOneTick() {
        //int rand = (int) (Math.random() * 5 + 1);
        //population = (int)(population + (food - (population*0.1))) + rand;
        foodConsumption = population*personFoodConsumption;
        population += (food - foodConsumption)/1;
        workerAmt = population/2;
        food -= foodConsumption;
        if(food<0){
            food = 0;
        }

        if(population > 0) {
            wood += workerAmt/5;
            for (int i = 0; i < GenerateTile.tileList.size(); i++) {
                Tile currTile = GenerateTile.tileList.get(i);
                if (currTile instanceof Farm) {
                    food += 30;
                }
                if (currTile instanceof Lumberyard) {
                    wood += 2;
                }
                if (currTile instanceof Mine) {
                    stone += 2;
                }
            }
        }
        Game.turn++;
    }
}
