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
        g.setColor(Color.DARK_GRAY);
        for(CheckPoint point : track.getPoints()) {
            g.setStroke(new BasicStroke(40));
            g.drawLine(point.getPathX(), point.getPathY(), point.getNext().getPathX(), point.getNext().getPathY())
        }
        g.setStroke(new BasicStroke());
        for(Car car : track.getCars()) {
            g.setColor(car.getColor());
            g.fill(new Rectangle(car.getPosX(), car.getPosY(), 20, 20));
        }
    }
    public void restart() {
        while(track.tick()) {
            repaint();
        }
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        //
    }
}
