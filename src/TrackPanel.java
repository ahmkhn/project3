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

        counter = 0;
        track = new Track();
        track.generateTrack();

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
        drawTrack(g);
        g.setColor(Color.BLACK);
        g.drawString("Enter numerical values between 0 and 255", 370, 45);
        g.drawString("to select the car's color.", 370, 60);
        g.drawString("Cars: " + counter, 225, 45);
    }
    private void drawTrack(Graphics2D g) {
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
    }

    //effectively runs the program/race
    public void restart() {
        //runs the race
        while(track.tick()) {//Each time the game updates
            Graphics g = this.getGraphics();
            drawTrack((Graphics2D) g);
            try {
                Thread.sleep(20);
            }
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        gameEvent.setText(track.results());
        //System.out.println(this.toString());
        track.reset();
        addCar.addActionListener(this);
        addCar.setBackground(Color.WHITE);
        start.addActionListener(this);
        start.setBackground(Color.WHITE);
        counter = 0;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        //button presses
        if(event.getSource().equals(addCar)) {
            try {
                int r = Integer.parseInt(redVal.getText());
                if(r < 0 || r > 255) {
                    throw new Exception();
                }
                int g = Integer.parseInt(greenVal.getText());
                if(g < 0 || g > 255) {
                    throw new Exception();
                }
                int b = Integer.parseInt(blueVal.getText());
                if(b < 0 || b > 255) {
                    throw new Exception();
                }
                counter++;
                track.addCar(new Car(counter, new Color(r, g, b), track.getRandomCheckpoint()));
                gameEvent.setText("Added a Car.");
                repaint();
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
                start.setBackground(Color.GRAY);
                start.removeActionListener(this);
                addCar.setBackground(Color.GRAY);
                addCar.removeActionListener(this);
                //System.out.println(this.toString());
                restart();
            }
        }
    }
    //getters
    public int getCounter() {
        return counter;
    }
    public Track getTrack() {
        return track;
    }
    /**
     * Compares this Object to another.
     * @param o Object to be compared
     * @return true if Object o is the same as this Object
     */
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(counter == ((TrackPanel) o).getCounter() && track.equals(((TrackPanel) o).getTrack())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a String containing information regarding the object's attributes.
     * @return String listing object's key attributes
     */
    @Override
    public String toString() {
        return "Current Cars: " + counter + "\n" + track.toString();
    }
}