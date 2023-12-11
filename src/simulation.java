import tiles.*;
import tiles.buildings.farm;
import tiles.buildings.lumberyard;
import tiles.buildings.mine;

public class simulation {
    public static int population;
    public static int food = 500, personFoodConsumption = 1, foodConsumption;
    public static int workerAmt;
    public static int wood;
    public static int stone;

    public static void setup(){
        population = 20;
        food = 500;
        personFoodConsumption = 1;
        workerAmt = population/2;
        wood = 20;
        stone = 20;
    }

    public static void simulateOneTick() {
        //int rand = (int) (Math.random() * 5 + 1);
        //population = (int)(population + (food - (population*0.1))) + rand;
        foodConsumption = population*personFoodConsumption;
        population += food - foodConsumption;
        workerAmt = population/2;
        food -= foodConsumption;
        if(food<0){
            food = 0;
        }

        if(population > 0) {
            wood += workerAmt/5;
            for (int i = 0; i < generateTile.tileList.size(); i++) {
                tile currTile = generateTile.tileList.get(i);
                if (currTile instanceof farm) {
                    food += 30;
                }
                if (currTile instanceof lumberyard) {
                    wood += 2;
                }
                if (currTile instanceof mine) {
                    stone += 2;
                }
            }
        }
        game.turn++;
    }
}
