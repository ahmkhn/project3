import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;


public class Track {
    private ArrayList<CheckPoint> checkpoints;
    private ArrayList<Car> cars;
    private int gameTimer;

    Track() {
        checkpoints = new ArrayList<>();
        cars = new ArrayList<>();
        gameTimer = 0;
    }

    public void add(Car c) {
        cars.add(c);
    }

    public boolean tick() {
        gameTimer++;
        boolean raceFinished = true;
        for (Car car : cars) {
            if (!car.isFinished()) {
                raceFinished = false;
                car.move();
            }
        }
        return raceFinished;
    }

    public void finish() {
        // Add logic for game finish
        for (Car car : cars) {
            car.setTotalTime(gameTimer);
        }
    }

    public String results() {
        StringBuilder result = new StringBuilder("Game Results:\n");
        Car winner = findWinner();
        for (Car car : cars) {
            result.append("Car ").append(car.getCarID()).append(" path: ").append(car.getPath()).append("\n");
            result.append("Speed: ").append(car.getSpeed()).append("\n");
            result.append("Total Time: ").append(car.getTotalTime()).append(" ticks\n\n");
        }
        result.append("Winner: Car ").append(winner.getCarID());
        return result.toString();
    }

    private Car findWinner() {
        Car winner = cars.get(0);
        for (Car car : cars) {
            if (car.getTotalTime() < winner.getTotalTime()) {
                winner = car;
            }
        }
        return winner;
    }

    void generateTrack() {
        // Generate checkpoints (A, B, C, D, ...)
        for (char c = 'A'; c <= 'Z'; c++) {
            CheckPoint checkpoint = new CheckPoint(String.valueOf(c));
            checkpoints.add(checkpoint);
        }
    }

    CheckPoint getRandomCheckpoint() {
        Random random = new Random();
        return checkpoints.get(random.nextInt(checkpoints.size()));
    }
    
}
