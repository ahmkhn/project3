import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

// Represents a track for the car racing game.
public class Track {
    private ArrayList<CheckPoint> checkpoints;// List of checkpoints
    private ArrayList<Car> cars; // List of cars
    private int gameTimer; // Timer for the race

    // Initializes a new instance of the Track class.
    public Track() {
        // Initialization
        checkpoints = new ArrayList<>();
        cars = new ArrayList<>();
        gameTimer = 0;
    }

    //Adds a car to the race.
    public void addCar(Car c) {
        // Add car to the list
        cars.add(c);
    }
    /**
     * Updates the game and increments the game timer.
     * Calls run() to move and update each car,
     * assigning them a final time when they finish.
     * Checks if any Car is not finished and returns appropriately.
     * @return false if all Cars have finished
     * @author Jonathan Murphy
     */
    public boolean tick() {//update frame
        gameTimer++;
        boolean result = false;
        for(Car car : cars) {
            if(car.run()) {
                car.setTime(gameTimer);//sets time as the car finishes
            }
            if(!car.isFinished()) {
                result = true;
            }
        }
        return result;//return true if at least one car has not finished, false if all finish
    }
    public String results() {
        StringBuilder result = new StringBuilder("Game Results:\n");

        // Sort cars by their total time
        cars.sort(Comparator.comparingInt(Car::getTime));

        // Display each car's information
        int rank = 1;
        for (Car car : cars) {
            result.append("Car ID: ").append(car.getCarID()).append(", Total Time: ").append(car.getTime()).append(", Rank: ").append(rank).append("\n");
            result.append("Path: ").append(car.getRoute()).append("\n");
            rank++;
        }

        // Display the winner
        Car winner = cars.get(0);
        result.append("Winner: Car ").append(winner.getCarID()).append(", Total Time: ").append(winner.getTime()).append("\n");

        return result.toString();
    }

    //Generates the track and sets up checkpoints.
    public void generateTrack() {
        // Add CheckPoints to the list with appropriate coordinates
        checkpoints.add(new CheckPoint(1, 300, 200));
        checkpoints.add(new CheckPoint(2, 900, 200));
        checkpoints.add(new CheckPoint(3, 900, 600));
        checkpoints.add(new CheckPoint(4, 500, 600));
        checkpoints.add(new CheckPoint(5, 500, 400));
        checkpoints.add(new CheckPoint(6, 300, 400));

        // Set the next and previous pointers for each CheckPoint
        for (int i = 0; i < checkpoints.size(); i++) {
            CheckPoint current = checkpoints.get(i);
            CheckPoint next = checkpoints.get((i + 1) % checkpoints.size()); // Next CheckPoint in circular manner
            CheckPoint previous = checkpoints.get((i - 1 + checkpoints.size()) % checkpoints.size()); // Previous CheckPoint in circular manner
            current.setNext(next);
            current.setPrevious(previous);
        }
    }

    /**
     * Resets the Track by removing all cars and setting gameTimer back to 0.
     * @author Jonathan Murphy
     */
    public void reset() {
        cars = new ArrayList<>();
        gameTimer = 0;
    }

    //Retrieves a random checkpoint.
    public CheckPoint getRandomCheckpoint() {
        Random random = new Random();
        return checkpoints.get(random.nextInt(checkpoints.size()));
    }

    //Getters
    public int getGameTimer() {
        return gameTimer;
    }
    public ArrayList<CheckPoint> getPoints() {
        return checkpoints;
    }
    public ArrayList<Car> getCars() {
        return cars;
    }
    /**
     * Compares this Object to another.
     * @param o Object to be compared
     * @return true if Object o is the same as this Object
     */
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(gameTimer == ((Track) o).getGameTimer() && checkpoints.equals(((Track) o).getPoints()) && cars.equals(((Track) o).getCars())) {
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
        String result = "Current Game Time: " + gameTimer + "\nCheckpoints:\n";
        for(CheckPoint point : checkpoints) {
            result += point.toString() + "\n";
        }
        result += "Cars:\n";
        for(Car c : cars) {
            result += c.toString() + "\n";
        }
        return result;
    }
}