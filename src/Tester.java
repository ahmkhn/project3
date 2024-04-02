import javax.swing.*;



public class Tester {
    public static void main(String[] args) {
        // Create and display the game window and panels
        JFrame frame = new JFrame();
        TrackPanel pane = new TrackPanel();
        frame.setTitle("Custom Car Racing Game");
        frame.getContentPane().add(pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}