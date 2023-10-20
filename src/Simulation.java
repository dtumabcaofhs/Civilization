import Tiles.*;
import Tiles.Buildings.Farm;
import Tiles.Buildings.Lumberyard;
import Tiles.Buildings.Mine;

public class Simulation {
    public static int population = 500;
    public static int maxWorkerAmt = 2;
    public static int availWorkerAmt;
    public static int food = 500;
    public static int wood = 20;
    public static int stone = 0;

    public static void simulateOneTick() {
        int rand = (int) (Math.random() * 5 + 1);
        availWorkerAmt = maxWorkerAmt;
        maxWorkerAmt = 2 + population/5000;
        population = (int)(population + (food - (population*0.1))) + rand;
        food -= (int)(population*0.1) - 200;
        if(food<0){
            food = 0;
        }
        for (int i = 0; i < GenerateTile.tileList.size(); i++) {
            Tile currTile = GenerateTile.tileList.get(i);
            if(currTile instanceof Farm) { food += 30; }
            if(currTile instanceof Lumberyard) { wood += 2; }
            if(currTile instanceof Mine){ stone += 2; }
        }
        Game.turn++;
    }
}
