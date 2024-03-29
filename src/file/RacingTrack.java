package file;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class RacingTrack extends JPanel {
    private ArrayList<Station> stations;
    private ArrayList<Car> cars;


    public RacingTrack() {
        stations = new ArrayList<>();
        cars = new ArrayList<>();
    }

    // Add stop method

    public void addStation(Station station) {
        stations.add(station);
    }

    // Add car to random location method

    public void addRandomCar() {
        Random random = new Random();
        int index = random.nextInt(stations.size());
        Station station = stations.get(index);
        int x = station.getX();
        int y = station.getY();
        int width = 20;
        int height = 10;
        cars.add(new Car(Color.BLUE, x, y, width, height));
    }

    // How to move your car

    public void moveCars() {
        Random random = new Random();
        for (Car car : cars) {
            // Randomly move to the next stop

            int index = random.nextInt(stations.size());
            Station station = stations.get(index);
            car.setX(station.getX());
            car.setY(station.getY());
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Track drawing

        drawTrack(g);

        // Track station

        for (Station station : stations) {
            station.draw(g);
        }

        // Car drawing

        for (Car car : cars) {
            car.draw(g);
        }
    }

    //Track Draw method

    private void drawTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10)); // 두꺼운 선 설정
        g.setColor(Color.BLACK);
        g.drawLine(100, 100, 500, 100); // A to C
        g.drawLine(100, 300, 500, 300); // D to F
        g.drawLine(100, 100, 100, 300); // A to D
        g.drawLine(300, 100, 300, 300); // B to E
        g.drawLine(500, 100, 500, 300); // C to F
    }
}