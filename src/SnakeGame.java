import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/*
 * This Class will Inherit the Jpanel
 */
public class SnakeGame extends JPanel implements ActionListener, KeyListener {

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

    // Snake
    Tile snakeHead;

    // Food 
    Tile food;

    // create random object
    Random random;

    // game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;

    SnakeGame(int boardWidth , int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // Set Prefered Size to new Dimension to given width and height
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        
        // set background color to black
        setBackground(Color.BLACK);

        // Listen to the key presses 
        addKeyListener(this);

        // in order to listen to key pressed 
        setFocusable(true);

        snakeHead = new Tile(5,5);

        food = new Tile(10,10);

        random = new Random();
        placefood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
        
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

        // Food
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize,tileSize, tileSize );

        // Snake 
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    /*
     * This function will just randomly place the food in 
     * X and Y coordinate
     */
     public void placefood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
     }

     public void move(){
        // Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
     }

     @Override
     public void actionPerformed(ActionEvent e){
        move();
        repaint();
     }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
       
    }

    // NO need of this method
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // NO need of this method
    @Override
    public void keyReleased(KeyEvent e) {
      
    }

     
}
