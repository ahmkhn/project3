import java.util.ArrayList;

public class Track {
    // Existing attributes
    private ArrayList<CheckPoint> points = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private int gameTimer;

    // Constructor
    public Track() {
        // Initialize points and link them in a circular manner for simplicity
    }

    // Existing and new methods
    public boolean tick() {//update frame
        gameTimer++;
        boolean result = false;
        for(Car car : cars) {
            car.run();//Car: method that compares its coordinates with those of
            //the next CheckPoint and moves toward it (ie if car's x > point's x, move in -x direction
            if(!car.isFinished()) {//Car: need getter for finished
                result = true;
            }
        }
        return result;//return true if at least one car has not finished, false if all finish
    }
    // New methods for accessing cars and checkpoints
    public ArrayList<CheckPoint> getPoints() {
        return points;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    // Method to add a car to the track
    public void addCar(Car car) {
        cars.add(car);
    }
}