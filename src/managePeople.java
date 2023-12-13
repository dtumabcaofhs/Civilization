import people.*;
import tiles.tile;

import java.util.ArrayList;

public class managePeople {
    public static person selected;
    static int savedWorkerIndex = -1, savedDay;
    static ArrayList<person> personList = new ArrayList<>();
    public static void generateWorker(int row, int col) {
        worker worker = new worker(row, col);
        savedDay = game.day;
        savedWorkerIndex = personList.size();
        personList.add(worker);
        simulation.workerAmt--;
    }

    public static void selectPerson(game window){
        //selects clicked worker and unselects other workers
        for (tile t : manageTiles.tileList) {
            for (person p : managePeople.personList) {
                if(t.row == p.row && t.col == p.col) {
                    if (window.mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                        if (managePeople.selected != p) {
                            p.selected = true;
                            managePeople.selected = p;
                        }
                    } else {
                        p.selected = false;
                    }
                }
            }
        }
    }

    public static void movePerson(game window){
        for (tile t : manageTiles.tileList) {
            for (int i = 0; i < personList.size(); i++) {
                person p = personList.get(i);
                if(t.row == p.row && t.col == p.col) {
                    if (selected == p) {

                        boolean workerOnTile = false;
                        for (tile t2 : manageTiles.tileList) {
                            for (person p2 : personList) {
                                if (t2.row == p2.row && t2.col == p2.col) {
                                    if((game.staticMouseX/100) == t2.row && (game.staticMouseY/100) == t2.col) {
                                        workerOnTile = true;
                                    }
                                }
                            }
                        }

                        if(!workerOnTile) {
                            if (window.mouseOn(t.row * 100, t.col * 100, 100, 100)) {
                                if (managePeople.selected == p) {
                                    p.selected = false;
                                    managePeople.selected = null;
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
