import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackPanel extends JPanel implements ActionListener {
    //Attributes
    private int counter;
    private Track track;
    private JButton start, addCar;
    private JTextField redVal, greenVal, blueVal, gameEvent;
    //Methods
    public TrackPanel() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.GREEN);
        GridBagConstraints positionConst = new GridBagConstraints();
        counter = 0;
        track = new Track();
        start = new JButton("Start");
        addCar = new JButton("Add Car");
        redVal = new JTextField();
        redVal.setEditable(true);
        greenVal = new JTextField();
        greenVal.setEditable(true);
        blueVal = new JTextField();
        blueVal.setEditable(true);
        gameEvent = new JTextField();
        positionConst.insets = new Insets(5, 5, 5, 5);
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        add(redVal);
        positionConst.gridx = 1;
        add(greenVal);
        positionConst.gridx = 2;
        add(blueVal);
        positionConst.gridx = 3;
        add(addCar);
        addCar.addActionListener(this);
        positionConst.gridx = 4;
        add(start);
        start.addActionListener(this);
        positionConst.gridx = 5;
        add(gameEvent);
    }
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
        gameEvent.setText(track.results());
        //implement a call for the finish/results
    }
    /*private boolean colorCheck() {
        try {
            Integer
        }
    }*/
    @Override
    public void actionPerformed(ActionEvent event) {
        //button presses
        if(event.getSource().equals(start)) {
            //
        }
        else if(event.getSource().equals(addCar)) {
            //
        }
    }
}
