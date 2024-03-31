import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackPanel extends JPanel implements ActionListener {
    //Attributes
    private int counter;
    private Track track;
    private JButton start, addCar;
    private JTextField redVal, greenVal, blueVal;
    private JTextArea gameEvent;
    //Methods
    public TrackPanel() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.GREEN);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        counter = 1;
        track = new Track();
        track.generateTrack();

        //topPanel.add(hint);
        redVal = new JTextField(4);
        redVal.setEditable(true);
        redVal.setBackground(Color.RED);
        topPanel.add(redVal);

        greenVal = new JTextField(4);
        greenVal.setEditable(true);
        greenVal.setBackground(Color.GREEN);
        topPanel.add(greenVal);

        blueVal = new JTextField(4);
        blueVal.setEditable(true);
        blueVal.setBackground(Color.BLUE);
        topPanel.add(blueVal);

        addCar = new JButton("Add Car");
        addCar.addActionListener(this);
        addCar.setBackground(Color.WHITE);
        topPanel.add(addCar);

        start = new JButton("Start");
        start.addActionListener(this);
        start.setBackground(Color.WHITE);
        topPanel.add(start);

        add(topPanel, BorderLayout.NORTH);

        gameEvent = new JTextArea("Add some cars and get ready to start.");
        gameEvent.setColumns(25);
        gameEvent.setEditable(false);
        add(gameEvent, BorderLayout.WEST);

        repaint();
    }
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g = (Graphics2D) page;
        //track.drawTrack(g);
        g.setColor(Color.DARK_GRAY);
        for(CheckPoint point : track.getPoints()) {
            g.setStroke(new BasicStroke(60));

            g.drawLine(point.getPathX(), point.getPathY(), point.getNext().getPathX(), point.getNext().getPathY());
        }
        g.setStroke(new BasicStroke());
        for(Car car : track.getCars()) {
            g.setColor(car.getColor());
            g.fill(new Rectangle(car.getPosX() - 10, car.getPosY() - 10, 20, 20));
        }
        g.setColor(Color.BLACK);
        g.drawString("Enter numerical values between 0 and 255", 370, 45);
        g.drawString("to select the car's color.", 370, 60);
        g.drawString("Cars: " + track.getCars().size(), 225, 45);
    }
    //effectively runs the program/race
    public void restart() {
        //runs the race
        while(track.tick()) {
            repaint();
            //need a delay here
        }
        gameEvent.setText(track.results());

        repaint();
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
            //System.out.println("|" + redVal.getText() + "," + greenVal.getText() + "," + blueVal.getText() + "|");
            try {
                int r = Integer.parseInt(redVal.getText());
                //System.out.println(r);
                if(r < 0 || r > 255) {
                    throw new Exception();
                }
                int g = Integer.parseInt(greenVal.getText());
                //System.out.println(g);
                if(g < 0 || g > 255) {
                    throw new Exception();
                }
                int b = Integer.parseInt(blueVal.getText());
                //System.out.println(b);
                if(b < 0 || b > 255) {
                    throw new Exception();
                }
                //why does it add 2?
                //track.addCar(new Car(counter, new Color(r, g, b), new CheckPoint(1, 500,500)));
                track.addCar(new Car(counter, new Color(r, g, b), track.getRandomCheckpoint()));
                counter++;
                gameEvent.setText("Added a Car.");
                repaint();//why?
                //System.out.println(track.getCars().size());
            }
            catch(Exception e) {
                gameEvent.setText("Could not add car.\nPlease enter numbers between 0 and 255.");
            }
        }
        else if(event.getSource().equals(start)) {
            if(track.getCars().size() < 1) {
                gameEvent.setText("Could not Start.\nPlease add at least one car.");
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
