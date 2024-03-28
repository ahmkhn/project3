import java.awt.*;

public class Car {
    // Existing attributes
    private Color color;
    private int id, speed;
    private CheckPoint current, goal;
    private int posX, posY;
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
        // Random speed for demonstration, could be improved or made dynamic
        speed = 5 + (int) (Math.random() * 10);
    }

    // New methods
    public void run() {//The car should move toward current.getNext(),
        //set current to current.getNext on entry, and check if that point is
        //the goal point, if so, the car is finished
        if (!finished) {
            // Calculate direction
            int dx = goal.getPathX() - posX;
            int dy = goal.getPathY() - posY;

            // Normalize direction
            double distance = Math.sqrt(dx * dx + dy * dy);
            double nx = dx / distance;
            double ny = dy / distance;

            // Move towards the goal
            posX += nx * speed;
            posY += ny * speed;

            // Check if reached goal
            if (goal.onPoint(posX, posY)) {
                current = goal;
                goal = goal.getPrevious(); // Update to next goal in circular manner
                if (goal == current) { // Simple way to check if finished, can be refined
                    finished = true;
                }
            }
        }
    }

    // Existing and new getters
    public int getCarSpeed() {
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

    public boolean isFinished() {
        return finished;
    }

    public void setCheckpoint(CheckPoint currentC, CheckPoint goalC) {
        this.current = currentC;
        this.goal = goalC;
    }
}