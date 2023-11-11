import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/*
 * This Class will Inherit the Jpanel
 */
public class SnakeGame extends JPanel {

    /*
     *  We will create the private class so that only
     *  SnakeGame can access this class
     */

     private class Tile {
        int x; 
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
     }

    int boardWidth;
    int boardHeight;

    // Define Tile size to 25px
    int tileSize = 25;

    Tile snakeHead;

    SnakeGame(int boardWidth , int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // Set Prefered Size to new Dimension to given width and height
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        
        // set background color to black
        setBackground(Color.BLACK);

        snakeHead = new Tile(5,5);
        
    }
    /*
     * super.paintComponent(g) is calling supper class
     * paintComponent method
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
  
    /*
     * This function is responsible to make grids of tile
     * responible for snake head color and size
     */
    public void draw(Graphics g){
        // Grid
        for(int i = 0; i < boardWidth/tileSize; i++){
            g.drawLine(i*tileSize,0 , i* tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        }

        // SNake 
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

}
