import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A JPanel that displays a simulation of a racetrack.
 * Allows users to add Car objects of different colors to random points on the
 * track and watch them travel between checkpoints in real time.
 * @author Jonathan Murphy
 * @version 1.0
 */
public class TrackPanel extends JPanel implements ActionListener {
    //Attributes
    private int counter;
    private Track track;
    private JButton start, addCar;
    private JTextField redVal, greenVal, blueVal;
    private JTextArea gameEvent;
    //Methods
    /**
     * Constructor.
     * Creates a Track object and sets up the Panel's layout.
     */
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
    /**
     * Uses drawTrack to draw the track and cars.
     * Displays additional information on the canvas.
     * @param page Graphics on which contents are printed
     */
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
    /**
     * Draws the track graphic and car markers. Used to display cars' positions in real time.
     * Separate from paintComponent because it wouldn't work during the simulation.
     * @param g Graphics2D on which the track is drawn
     */
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

            // Arjenis Montenegro - Added a number on the cars for clarity of what car won
            // Use Car's method to get the ID text color
            g.setColor(car.getIdTextColor());
            // Set the font for the ID
            g.setFont(new Font("Arial", Font.BOLD, 12));
            // Draw the car ID
            String idString = Integer.toString(car.getCarID());
            int stringWidth = g.getFontMetrics().stringWidth(idString);
            int stringHeight = g.getFontMetrics().getHeight();
            g.drawString(idString, car.getPosX() - stringWidth / 2, car.getPosY() + stringHeight / 4);
        }
    }
    /**
     * The main part of the simulation.
     * Uses track.tick() to update the program over and over until the race is finished.
     * Displays the results and resets the simulation to allow users to start another.
     */
    public void restart() {
        // Arjenis Montenegro - Fixed the flickering
        new Thread(new Runnable() {
            public void run() {
                while (track.tick()) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    repaint();
                }
                gameEvent.setText(track.results());
                addCar.addActionListener(TrackPanel.this);
                addCar.setBackground(Color.WHITE);
                start.addActionListener(TrackPanel.this);
                start.setBackground(Color.WHITE);
            }
        }).start();
    }
    /**
     * Detects and executes inputs from buttons.
     * The addCar input takes numerical inputs between 0 and 255 from
     * redVal, greenVal, and blueVal and makes a color out of them;
     * It then adds a car of that color to the track at a random point.
     * The start button checks if there is at least one car on the track;
     * If so, it calls restart() to begin the simulation.
     * @param event input from button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        //button presses
        if(event.getSource().equals(addCar)) {
            //removed from restart to try and fix the cars disappearing and added here instead
            if (track.isRaceFinished()) {
                track.reset();
                counter = 0;
            }
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
                restart();
            }
        }
    }
    /**
     * Basic getter.
     * @return attribute int counter
     */
    public int getCounter() {
        return counter;
    }
    /**
     * Basic getter.
     * @return attribute Track track
     */
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