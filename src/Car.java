import java.awt.*;

import java.awt.*;
/**
 * Sample Comment, describe class in a general sense
 * @author (people who worked on it)
 * @version 1.0 (optional, but I usually include just for fun)
 */
public class Car {
    // Existing attributes
    private Color color;
    private int id, speed, posX, posY, time;
    private CheckPoint current, goal;
    private boolean finished;
    private String route;

    // Existing constructor
    /**
     * Describe what the method does
     * @param idNum what are these parameters for
     * @param c
     * @param start
     */
    public Car(int idNum, Color c, CheckPoint start) {
        color = c;
        id = idNum;
        current = start;
        goal = start.getPrevious(); // Assuming circular track where start's previous is the last checkpoint
        finished = false;
        // Initializing position
        posX = current.getPathX();
        posY = current.getPathY();
        time = 9999;
        // Random speed for demonstration, could be improved or made dynamic
        speed = 5 + (int) (Math.random() * 10);
        route = "" + current.getId();
    }

    // New methods
    public boolean run() {//The car should move toward current.getNext(),
        //set current to current.getNext on entry, and check if that point is
        //the goal point, if so, the car is finished
        if (!finished) {
            // Calculate direction
            int dx = current.getNext().getPathX() - posX;
            int dy = current.getNext().getPathY() - posY;

            // Normalize direction
            double distance = Math.sqrt(dx * dx + dy * dy);
            double nx = dx / distance;
            double ny = dy / distance;

            // Move towards the goal
            posX += nx * speed;
            posY += ny * speed;

            // Check if reached goal
            if (current.getNext().onPoint(posX, posY)) {
                current = current.getNext();
                route += current.getId();
                //goal = goal.getPrevious(); // Update to next goal in circular manner
                if (current.equals(goal)) { // Simple way to check if finished, can be refined
                    finished = true;
                    return true;//return true when reaching the goal
                }
            }
        }
        return false;
    }

    // Existing and new getters
    public int getSpeed() {
        return this.speed;
    }

    public int getCarID() {
        return this.id;
    }

    public Color getColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getTime() {
        return time;
    }

    public boolean isFinished() {
        return finished;
    }

    public CheckPoint getGoal() {
        return goal;
    }

    public String getRoute() {
        String result = "(";
        for(int i = 0; i < route.length(); i++) {
            if(i >= route.length() - 1) {
                result += route.charAt(i);
            }
            else {
                result += route.charAt(i) + "->";
            }
        }
        return result + ")";
    }

    public void setCheckpoint(CheckPoint currentC, CheckPoint goalC) {
        this.current = currentC;
        this.goal = goalC;
    }

    public void setTime(int timer) {
        time = timer;
    }

    /**
     * Compares this Object to another.
     * @param o Object to be compared
     * @return true if Object o is the same as this Object
     */
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(id == ((Car) o).getCarID() && speed == ((Car) o).getSpeed() && color.equals(((Car) o).getColor()) && goal.equals(((Car) o).getGoal())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a String containing information regarding the object's attributes.
     * @return String listing object's key attributes
     */
    @Override
    public String toString() {
        String result = "Car ID: " + id + ", speed: " + speed + ", current position: (" + posX + ", " + posY +
                ").\nLast checkpoint: point " + current.getId() + ", goal: point " + goal.getId();
        if(finished) {
            return result + ". Finished.";
        }
        return result + ". Not finished.";
    }
}