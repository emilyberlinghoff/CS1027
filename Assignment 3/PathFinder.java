/**
 * As per usual, my approach for designing the program was to read the assignment specifications, then start off writing
 * a half Java, half pseudocode program. Some parts were decently easy to code and others were a lot harder, like the
 * isDim method of this class and the T pop() and T pop(int K) of DLStack. The steps I took to testing the solution was
 * to start off testing using the provided TestStackMap.java file and then also create my own test file using different
 * test cases that I assumed would be tested. I created the PathFinder class to be able to find a path through a pyramid
 * map while utilizing the DLStack data structure to implement the pathfinding algorithm. Throughout the whole coding
 * process, I kept in mind that the goal was to find a path that includes all treasure chambers.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private final Map pyramidMap;
    private List<Chamber> allChambers; // List to store all the chambers encountered

    public PathFinder(String fileName) {
        try {
            this.pyramidMap = new Map(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error creating Map object.", e);
        }
        allChambers = new ArrayList<>(); // Initialize the list of chambers
    }

    public DLStack<Chamber> path() {
        DLStack<Chamber> stack = new DLStack<>();
        Chamber entrance = pyramidMap.getEntrance();
        stack.push(entrance);
        entrance.markPushed();
        allChambers.add(entrance); // Add the entrance to the list of chambers

        while (!stack.isEmpty()) {
            Chamber currentChamber = stack.peek();
            if (currentChamber.isTreasure() && pyramidMap.getNumTreasures() == getTreasureCount()) {
                break;
            }

            Chamber nextChamber = bestChamber(currentChamber);
            if (nextChamber != null) {
                stack.push(nextChamber);
                nextChamber.markPushed();
                allChambers.add(nextChamber); // Add the next chamber to the list of chambers
            } else {
                stack.pop();
            }
        }

        return stack;
    }

    public Map getMap() {
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {
        if (currentChamber == null || currentChamber.isSealed() || currentChamber.isLighted()) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            Chamber neighbor = currentChamber.getNeighbour(i);
            if (neighbor != null && neighbor.isLighted()) {
                return true;
            }
        }
        return false;
    }

    public Chamber bestChamber(Chamber currentChamber) {
        Chamber bestTreasureChamber = null;
        Chamber bestLightedChamber = null;
        Chamber bestDimChamber = null;

        for (int i = 0; i < 6; i++) {
            Chamber neighbor = currentChamber.getNeighbour(i);
            if (neighbor != null && !neighbor.isMarked()) {
                if (neighbor.isTreasure() && bestTreasureChamber == null) {
                    bestTreasureChamber = neighbor;
                } else if (neighbor.isLighted() && bestLightedChamber == null) {
                    bestLightedChamber = neighbor;
                } else if (isDim(neighbor) && bestDimChamber == null) {
                    bestDimChamber = neighbor;
                }
            }
        }

        if (bestTreasureChamber != null) {
            return bestTreasureChamber;
        } else if (bestLightedChamber != null) {
            return bestLightedChamber;
        } else {
            return bestDimChamber;
        }
    }

    // Helper method to count the number of treasure chambers in the map
    private int getTreasureCount() {
        int treasureCount = 0;
        for (Chamber chamber : allChambers) {
            if (chamber.isTreasure()) {
                treasureCount++;
            }
        }
        return treasureCount;
    }
}