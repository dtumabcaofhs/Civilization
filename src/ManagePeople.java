import people.*;
import tiles.Tile;

import java.util.ArrayList;

public class ManagePeople {
    public static Person selected;
    static int savedWorkerIndex = -1, savedDay;
    static ArrayList<Person> personList = new ArrayList<>();
    public static void generateWorker(int row, int col) {
        Worker worker = new Worker(row, col);
        savedDay = Game.day;
        savedWorkerIndex = personList.size();
        personList.add(worker);
        Simulation.workerAmt--;
    }

    public static void selectPerson(Game window){
        //selects clicked Worker and unselects other workers
        for (Tile t : ManageTiles.tileList) {
            for (Person p : ManagePeople.personList) {
                if(t.row == p.row && t.col == p.col) {
                    if (window.mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                        if (ManagePeople.selected != p) {
                            p.selected = true;
                            ManagePeople.selected = p;
                        }
                    } else {
                        p.selected = false;
                    }
                }
            }
        }
    }

    public static void movePerson(Game window){
        for (Tile t : ManageTiles.tileList) {
            for (int i = 0; i < personList.size(); i++) {
                Person p = personList.get(i);
                if(t.row == p.row && t.col == p.col) {
                    if (selected == p) {

                        boolean workerOnTile = false;
                        for (Tile t2 : ManageTiles.tileList) {
                            for (Person p2 : personList) {
                                if (t2.row == p2.row && t2.col == p2.col) {
                                    if((Game.staticMouseX/100) == t2.row && (Game.staticMouseY/100) == t2.col) {
                                        workerOnTile = true;
                                    }
                                }
                            }
                        }

                        if(!workerOnTile) {
                            if (window.mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                                if (ManagePeople.selected == p) {
                                    p.selected = false;
                                    ManagePeople.selected = null;
                                }
                            }
                            boolean clickedSurroundingTiles = false;
                            if (window.mouseOn(t.row * 100 + 100, t.col * 100, 100, 100)) {
                                p.row++;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100 - 100, t.col * 100, 100, 100)) {//
                                p.row--;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100, t.col * 100 + 100, 100, 100)) {
                                p.col++;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100, t.col * 100 - 100, 100, 100)) {//
                                p.col--;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100 + 100, t.col * 100 + 100, 100, 100)) {
                                p.row++;
                                p.col++;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100 + 100, t.col * 100 - 100, 100, 100)) {
                                p.row++;
                                p.col--;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100 - 100, t.col * 100 - 100, 100, 100)) {
                                p.row--;
                                p.col--;
                                clickedSurroundingTiles = true;
                            }
                            if (window.mouseOn(t.row * 100 - 100, t.col * 100 + 100, 100, 100)) {
                                p.row--;
                                p.col++;
                                clickedSurroundingTiles = true;
                            }
                            if (clickedSurroundingTiles) {
                                p.selected = false;
                                personList.set(i, p);
                                selected = null;
                            }
                        }else{
                            p.selected = false;
                            selected = null;
                        }
                    }
                }
            }
        }
    }
}
