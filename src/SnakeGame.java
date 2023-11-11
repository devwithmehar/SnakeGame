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
    ArrayList<Tile> snakeBody;

    // Food 
    Tile food;

    // create random object
    Random random;

    // game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

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
        snakeBody = new ArrayList<Tile>();

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
       

        // Food
        g.setColor(Color.RED);
        //g.fillRect(food.x * tileSize, food.y * tileSize,tileSize, tileSize );
        g.fill3DRect(food.x * tileSize, food.y * tileSize,tileSize, tileSize, true );

        // Snake Head
        g.setColor(Color.green);
        //g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);
        // Snake Body
        for(int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
             g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Gave Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
        else{
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize );
        }
    }

    /*
     * This function will just randomly place the food in 
     * X and Y coordinate
     */
     public void placefood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
     }

     /*
      * This function will dectect the collision between
      * tile and food
      */
      public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
      }

     public void move(){
        // eat food 
        if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placefood();
        }

        // Snake Body
        for(int i = snakeBody.size() - 1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Game over Condition
        // 1st Condtion is if snake will collide with it's own body
        for(int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            // Collistion with snake head
            if(collision(snakeHead, snakePart)){
                gameOver = true;
            }  
        }

        // if snake touched the walls
        if(snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > boardWidth ||
            snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > boardHeight){
            gameOver = true;
        }
     }

     @Override
     public void actionPerformed(ActionEvent e){
        move();
        repaint();

        if(gameOver){
            gameLoop.stop();
        }
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
