import java.awt.*;
import java.util.ArrayList;

public class Car {
    private Color color;
    private int id;
    private int speed;
    private CheckPoint current;
    private CheckPoint goal;
    private ArrayList<String> path;
    private int totalTime;
    private boolean finished;

    Car(int id, Color color, int speed, CheckPoint start) {
        this.id = id;
        this.color = color;
        this.speed = speed;
        this.current = start;
        this.goal = null;
        this.path = new ArrayList<>();
        this.totalTime = 0;
        this.finished = false;
    }

    int getCarID() {
        return id;
    }

    int getSpeed() {
        return speed;
    }

    ArrayList<String> getPath() {
        return path;
    }

    int getTotalTime() {
        return totalTime;
    }

    boolean isFinished() {
        return finished;
    }

    void setCheckpoint(CheckPoint point) {
        this.goal = point;
    }

    void move() {
        if (current != goal) {
            // Move towards the goal
            path.add(current.getId());
            totalTime += speed;
            current = getNextCheckpoint(current);
            if (current == goal) {
                finished = true;
                path.add(current.getId());
            }
        }
    }
    private CheckPoint getNextCheckpoint(CheckPoint current) {
        // Get the next checkpoint in the track
        // This method can be implemented according to the actual logic of the racing track
        // For simplicity, we just return the next checkpoint in the alphabetical order
        int currentIndex = current.getId().charAt(0) - 'A';
        return new CheckPoint(String.valueOf((char) ('A' + (currentIndex + 1) % 26)));
    }
}
