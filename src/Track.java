import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


/**
 * Represents a racetrack for the car racing game.
 * @author Yunjin Seo
 */
public class Track {
    private ArrayList<CheckPoint> checkpoints;
    private ArrayList<Car> cars;
    private int gameTimer;

    /**
     * Initializes a new instance of the Track class.
     */
    public Track() {

        checkpoints = new ArrayList<>();
        cars = new ArrayList<>();
        gameTimer = 0;
    }
    /**
     * Adds a car to the racetrack.
     * @param c The car to be added
     */
    public void addCar(Car c) {
        cars.add(c);
    }
    /**
     * Updates the game and increments the game timer.
     * Calls run() to move and update each car,
     * assigning them a final time when they finish.
     * Checks if any Car is not finished and returns appropriately.
     * @return false if all Cars have finished
     * @author Jonathan Murphy (This Method)
     */
    public boolean tick() {
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
        return result;
    }
    /**
     * Constructs and returns a String containing the final time and ranking of each car.
     * @return String detailing the race's finish
     */
    public String results() {
        StringBuilder result = new StringBuilder("Game Results:\n");


        cars.sort(Comparator.comparingInt(Car::getTime));


        int rank = 1;
        for (Car car : cars) {
            result.append("Car ID: ").append(car.getCarID()).append(", Total Time: ").append(car.getTime()).append(", Rank: ").append(rank).append("\n");
            result.append("Path: ").append(car.getRoute()).append("\n");
            rank++;
        }


        Car winner = cars.get(0);
        result.append("Winner: Car ").append(winner.getCarID()).append(", Total Time: ").append(winner.getTime()).append("\n");
        result.append("Add a car to restart");//  Arjenis Montenegro - Added for clarity

        return result.toString();
    }

    /**
     * Generates the racetrack with predefined checkpoints and sets up their connections.
     * @author Yunjin Seo
     */
    public void generateTrack() {
        // Add CheckPoints to the list with appropriate coordinates
        checkpoints.add(new CheckPoint(1, 300, 200));
        checkpoints.add(new CheckPoint(2, 900, 200));
        checkpoints.add(new CheckPoint(3, 900, 600));
        checkpoints.add(new CheckPoint(4, 500, 600));
        checkpoints.add(new CheckPoint(5, 500, 400));
        checkpoints.add(new CheckPoint(6, 300, 400));


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
     * @author Jonathan Murphy (this Method)
     */
    public void reset() {
        cars = new ArrayList<>();
        gameTimer = 0;
    }

    /**
     * Retrieves a random checkpoint from the racetrack.
     * @return A random checkpoint
     * @author Yunjin Seo
     */
    public CheckPoint getRandomCheckpoint() {
        Random random = new Random();
        return checkpoints.get(random.nextInt(checkpoints.size()));
    }

    /**
     * Basic getter.
     * @return attribute int gameTimer
     */
    public int getGameTimer() {
        return gameTimer;
    }
    /**
     * Basic getter.
     * @return attribute ArrayList<CheckPoint> checkpoints
     */
    public ArrayList<CheckPoint> getPoints() {
        return checkpoints;
    }
    /**
     * Basic getter.
     * @return attribute ArrayList<Car> cars
     */
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