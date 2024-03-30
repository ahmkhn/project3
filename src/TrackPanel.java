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
        counter = 1;
        track = new Track();
        track.generateTrack();
        start = new JButton("Start");
        addCar = new JButton("Add Car");
        redVal = new JTextField(4);
        redVal.setEditable(true);
        redVal.setBackground(Color.RED);
        greenVal = new JTextField(4);
        greenVal.setEditable(true);
        greenVal.setBackground(Color.GREEN);
        blueVal = new JTextField(4);
        blueVal.setEditable(true);
        blueVal.setBackground(Color.BLUE);
        gameEvent = new JTextField("Add some cars and get ready to start.", 50);
        gameEvent.setEditable(false);
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
        addCar.setBackground(Color.WHITE);
        positionConst.gridx = 4;
        add(start);
        start.addActionListener(this);
        start.setBackground(Color.WHITE);
        positionConst.gridx = 5;
        add(gameEvent);
        repaint();
    }
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g = (Graphics2D) page;
        g.setColor(Color.DARK_GRAY);//basic idea for visual layout
        g.fill(new Rectangle(200, 200,40, 40));
        //can't get checkpoints properly
        redVal.setText(""  + track.getPoints().size());
        for(CheckPoint point : track.getPoints()) {//Track: need a getter for CheckPoint List
            //g.setStroke(new BasicStroke(40));
            //g.drawRect(200, 200,40, 40);
            g.fill(new Rectangle(200, 200,40, 40));
            //g.drawLine(point.getPathX(), point.getPathY(), point.getNext().getPathX(), point.getNext().getPathY());
        }
        //g.setStroke(new BasicStroke());
        for(Car car : track.getCars()) {//Track: need getter for Car list
            g.setColor(car.getColor());//Car: need getters for color and posX and posY
            g.fill(new Rectangle(car.getPosX() - 10, car.getPosY() - 10, 20, 20));
        }
        g.setColor(Color.BLACK);
        g.drawString("Enter numerical values between 0 and 255", 130, 45);
        g.drawString("to select the car's color.", 130, 60);
        g.drawString("Cars: " + track.getCars().size(), 20, 20);
    }
    //effectively runs the program/race
    public void restart() {
        //runs the race
        while(track.tick()) {
            repaint();
        }
        gameEvent.setText(track.results());
        //implement a call for the finish/results
        track.reset();
        addCar.addActionListener(this);
        addCar.setBackground(Color.WHITE);
        start.addActionListener(this);
        start.setBackground(Color.WHITE);
        counter = 1;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        //button presses
        if(event.getSource().equals(addCar)) {
            System.out.println("|" + redVal.getText() + "," + greenVal.getText() + "," + blueVal.getText() + "|");
            try {
                int r = Integer.parseInt(redVal.getText());
                System.out.println(r);
                if(r < 0 || r > 255) {
                    throw new Exception();
                }
                int g = Integer.parseInt(greenVal.getText());
                System.out.println(g);
                if(g < 0 || g > 255) {
                    throw new Exception();
                }
                int b = Integer.parseInt(blueVal.getText());
                System.out.println(b);
                if(b < 0 || b > 255) {
                    throw new Exception();
                }
                //why does it add 2?
                track.addCar(new Car(counter, new Color(r, g, b), new CheckPoint(1, 500,500)));
                //track.addCar(new Car(counter, new Color(r, g, b), track.getRandomCheckpoint())); getRandomCheckpoint doesn't work
                counter++;
                gameEvent.setText("Added a Car.");
                repaint();//why?
                System.out.println(track.getCars().size());
            }
            catch(Exception e) {
                gameEvent.setText("Could not add car, please enter numerical values between 0 and 255.");
            }
        }
        else if(event.getSource().equals(start)) {
            if(track.getCars().size() < 1) {
                gameEvent.setText("Could not Start. Please add at least one car.");
            }
            else {
                start.removeActionListener(this);
                addCar.setBackground(Color.GRAY);
                addCar.removeActionListener(this);
                start.setBackground(Color.GRAY);
                restart();
            }
        }
    }
}
