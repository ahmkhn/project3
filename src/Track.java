import java.util.ArrayList;

public class Track {
    //Attributes
    private ArrayList<CheckPoint> points;
    private ArrayList<Car> cars;
    private int gameTimer;
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
}
