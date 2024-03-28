import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackPanel extends JPanel implements ActionListener {
    //Attributes
    private Track track;
    private JButton start, add;
    private JPanel redVal, greenVal, blueVal;
    //Methods
    public TrackPanel() {}
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g = (Graphics2D) page;
        g.setColor(Color.DARK_GRAY);//basic idea for visual layout
        for(CheckPoint point : track.getPoints()) {//Track: need a getter for CheckPoint List
            g.setStroke(new BasicStroke(40));
            g.drawLine(point.getPathX(), point.getPathY(), point.getNext().getPathX(), point.getNext().getPathY());
        }
        g.setStroke(new BasicStroke());
        for(Car car : track.getCars()) {//Track: need getter for Car list
            g.setColor(car.getColor());//Car: need getters for color and posX and posY
            g.fill(new Rectangle(car.getPosX(), car.getPosY(), 20, 20));
        }
    }
    //effectively runs the program/race
    public void restart() {
        //runs the race
        while(track.tick()) {
            repaint();
        }
        //implement a call for the finish/results
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        //button presses
    }
}
