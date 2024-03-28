import java.util.ArrayList;

public class Track {
    //Attributes
    private ArrayList<CheckPoint> points;
    private ArrayList<Car> cars;
    private int gameTimer;
    public boolean tick() {
        gameTimer++;
        boolean result = false;
        for(Car car : cars) {
            car.run();
            if(!car.isFinished()) {
                result = true;
            }
        }
        return result;
    }
}
