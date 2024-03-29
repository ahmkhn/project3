import javax.swing.*;

public class Tester {
    public static void main(String[] args) {
        // Create and display the game window and panels
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Car Racing Game");
            TrackPanel trackPanel = new TrackPanel();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(trackPanel);
            frame.pack();
            frame.setVisible(true);

            // Start the race
            trackPanel.restart();
        });
    }
}