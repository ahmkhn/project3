import java.awt.*;

public class Car {
    //Attributes
    private Color color;
    private int id, speed;
    private CheckPoint current, goal;
    private double posX, posY;
    private boolean finished;
    //Methods
    public Car(int idNum, Color c, CheckPoint start) {
        color = c;
        id = idNum;
        current = start;
        goal = start.getPrevious();
        finished = false;
    }
}
