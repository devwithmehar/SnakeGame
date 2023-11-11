import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/*
 * This Class will Inherit the Jpanel
 */
public class SnakeGame extends JPanel {
    int boardWidth;
    int boardHeight;

    SnakeGame(int boardWidth , int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
    }
}
