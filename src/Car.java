import java.awt.*;
/**
 * Represents a Car in a racing simulation. The Car is capable of navigating a track composed of a series of CheckPoints.
 * Movement, identification, and completion status are all included within this class.
 * @author Arjenis Montenegro
 * @version 1.0
 */
public class Car {
    // Existing attributes
    private Color color;
    private int id, speed, posX, posY, time;
    private CheckPoint current, goal;
    private boolean finished;
    private String route;

    /**
     * Constructs a Car object with an ID, color, and starting checkpoint.
     * The starting position is obtained from the starting checkpoint, and initial settings like speed and route are also established.
     * @param idNum The unique identifier for the car.
     * @param c The color of the car.
     * @param start The starting checkpoint for the car.
     */
    public Car(int idNum, Color c, CheckPoint start) {
        color = c;
        id = idNum;
        current = start;
        goal = start.getPrevious();
        finished = false;
        posX = current.getPathX();
        posY = current.getPathY();
        time = 9999;
        speed = 5 + (int) (Math.random() * 10);
        route = "" + current.getId();
    }

    /**
     * Advances the car towards the next checkpoint. This method calculates the next position based on current speed and direction.
     * It updates the current checkpoint upon arrival and checks if the car has reached its goal.
     * @return true if the car has reached the goal checkpoint, false otherwise.
     */
    public boolean run() {
        if (!finished) {

            int dx = current.getNext().getPathX() - posX;
            int dy = current.getNext().getPathY() - posY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double nx = dx / distance;
            double ny = dy / distance;

            posX += nx * speed;
            posY += ny * speed;

            if (current.getNext().onPoint(posX, posY)) {
                current = current.getNext();
                route += current.getId();
                if (current.equals(goal)) {
                    finished = true;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines the appropriate text color for the car's ID based on the brightness of the car's color.
     * This helps maintain the visibility of the ID against the car's color.
     * @return The color(black or white) that ensures the car's ID is visible against its background color.
     */
    public Color getIdTextColor() {
        double brightness = (0.299 * this.color.getRed() + 0.587 * this.color.getGreen() + 0.114 * this.color.getBlue()) / 255;
        if (brightness > 0.5) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    // Getters and Setters
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