import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        // Lets create a new Window with JFrame and give
        // title Snake
        JFrame frame = new JFrame("Snake");
        // Set the Visibility
        frame.setVisible(true);
        // Set the Size
        frame.setSize(boardWidth, boardHeight);

        /*
         * Set Location Relative to null 
         * which will open the the window at the center
         * of the screen
         */
        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        // set closing operation of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
