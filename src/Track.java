import java.awt.*;
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
    public void add(Car c) {
        // Add car to the list
        cars.add(c);
    }
    //Updates the game state.
    public boolean tick() {//update frame
        gameTimer++;
        boolean result = false;
        for(Car car : cars) {
            if(car.run()) {
                car.setTime(gameTimer);//sets time as the car finishes
            }
            if(!car.isFinished()) {
                result = true;
                car.setTime(gameTimer);
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
        checkpoints.add(new CheckPoint(1, 100, 200));
        checkpoints.add(new CheckPoint(2, 300, 400));
        checkpoints.add(new CheckPoint(3, 500, 600));

        // Set the next and previous pointers for each CheckPoint
        for (int i = 0; i < checkpoints.size(); i++) {
            CheckPoint current = checkpoints.get(i);
            CheckPoint next = checkpoints.get((i + 1) % checkpoints.size()); // Next CheckPoint in circular manner
            CheckPoint previous = checkpoints.get((i - 1 + checkpoints.size()) % checkpoints.size()); // Previous CheckPoint in circular manner
            current.setNext(next);
            current.setPrevious(previous);
        }
    }


    //Retrieves a random checkpoint.
    public CheckPoint getRandomCheckpoint() {
        Random random = new Random();
        return checkpoints.get(random.nextInt(checkpoints.size()));
    }

    //Getters
    public ArrayList<CheckPoint> getPoints() {
        return checkpoints;
    }
    //Getters
    public ArrayList<Car> getCars() {
        return cars;
    }
}