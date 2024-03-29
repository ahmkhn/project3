package file;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Race Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);



        RacingTrack racingTrack = new RacingTrack();

        //Add station to racingTrack

        racingTrack.addStation(new Station(100, 100, "A"));
        racingTrack.addStation(new Station(300, 100, "B"));
        racingTrack.addStation(new Station(500, 100, "C"));
        racingTrack.addStation(new Station(100, 300, "D"));
        racingTrack.addStation(new Station(300, 300, "E"));
        racingTrack.addStation(new Station(500, 300, "F"));

        // Add car

        racingTrack.addRandomCar();
        racingTrack.addRandomCar();
        racingTrack.addRandomCar();


        frame.add(racingTrack);
        frame.setVisible(true);



    }
}
