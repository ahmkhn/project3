import java.awt.*;

public class Car {
    // Existing attributes
    private Color color;
    private int id, speed;
    private CheckPoint current, goal;
    private int posX, posY, time;
    private boolean finished;

    // Existing constructor
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

    public void setCheckpoint(CheckPoint currentC, CheckPoint goalC) {
        this.current = currentC;
        this.goal = goalC;
    }

    public void setTime(int timer) {
        time = timer;
    }

}