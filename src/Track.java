import java.util.ArrayList;
import java.util.Random;


public class Track {
    private ArrayList<CheckPoint> checkpoints;
    private ArrayList<Car> cars;
    private int gameTimer;

    public Track() {
        checkpoints = new ArrayList<>();
        cars = new ArrayList<>();
        gameTimer = 0;
    }

    public void add(Car c) {
        cars.add(c);
    }
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
    /*public void finish() {
        // Add logic for game finish
        for (Car car : cars) {
            //car.setTime(gameTimer);
        }
    }*/

    public String results() {
        StringBuilder result = new StringBuilder("Game Results:\n");
        Car winner = findWinner();
        for (Car car : cars) {//only id, time, and ranking need to be displayed
            //result.append("Car ").append(car.getCarID()).append(" path: ").append(car.getPath()).append("\n");
            result.append("Speed: ").append(car.getSpeed()).append("\n");
            result.append("Total Time: ").append(car.getTime()).append(" ticks\n\n");
        }
        result.append("Winner: Car ").append(winner.getCarID());
        return result.toString();
    }

    private Car findWinner() {//change to sort cars by the time they got
        Car winner = cars.get(0);
        for (Car car : cars) {
            if (car.getTime() < winner.getTime()) {
                winner = car;
            }
        }
        return winner;
    }

    public void generateTrack() {
        checkpoints.add(new CheckPoint(0, 100, 200));
        //add CheckPoints to the list, then set the next and previous pointers
        /*// Generate checkpoints (A, B, C, D, ...)
        for (char c = 'A'; c <= 'Z'; c++) {//we'll need to work on this
            CheckPoint checkpoint = new CheckPoint(String.valueOf(c));
            checkpoints.add(checkpoint);
        }*/
    }

    public CheckPoint getRandomCheckpoint() {
        Random random = new Random();
        return checkpoints.get(random.nextInt(checkpoints.size()));
    }

    //Getters
    public ArrayList<CheckPoint> getPoints() {
        return checkpoints;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}