import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;


public class CastleDriver2 extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private User character;
    private UserProjectile pew;
    private UserProjectile pew2;
    private UserProjectile pew3;
    private boolean space; //tells whether or not space can be pressed depending on numPew
    private boolean onScreen1; //tells whether or not the respective pew is on the screen
    private boolean onScreen2;
    private boolean onScreen3;
    private Enemy2 enemy1, enemy2, enemy3, enemy4, enemy5;
    private EnemyProjectile2 ePew1, ePew2, ePew3, ePew4, ePew5;
    private int userHealth;
    private int mana;
    private boolean userEnemy1, userEnemy2, userEnemy3, userEnemy4, userEnemy5;
    private boolean userEPew1, userEPew2, userEPew3, userEPew4, userEPew5;
    private boolean pewEnemy, pew2Enemy, pew3Enemy;
    private boolean pewEnemy2, pew2Enemy2, pew3Enemy2;
    private boolean pewEnemy3, pew2Enemy3, pew3Enemy3;
    private boolean pewEnemy4, pew2Enemy4, pew3Enemy4;
    private boolean pewEnemy5, pew2Enemy5, pew3Enemy5;
    private long startTime, currTime, switchTime;
    private int numDefeated;
    private int screenMode;
    private boolean instructions;
    private boolean lost;
    
    //Default Constructor
    public CastleDriver2()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        character = new User();
        
        pew = new UserProjectile(character);
        pew2 = new UserProjectile(character);
        pew3 = new UserProjectile(character);
        space = true; //enables shooting
        onScreen1 = false;
        onScreen2 = false;
        onScreen3 = false;
        
        enemy1 = new Enemy2();
        enemy2 = new Enemy2();
        enemy3 = new Enemy2();
        enemy4 = new Enemy2();
        enemy5 = new Enemy2();
        
        //changing the spawn positions of the enemies so that they don't overlap each other
        enemy1.changeSpawn(enemy2, enemy3, enemy4, enemy5);
        enemy2.changeSpawn(enemy1, enemy3, enemy4, enemy5);
        enemy3.changeSpawn(enemy1, enemy2, enemy4, enemy5);
        enemy4.changeSpawn(enemy1, enemy2, enemy3, enemy5);
        enemy5.changeSpawn(enemy1, enemy2, enemy3, enemy4);
            
        ePew1 = enemy1.getEnemyProjectile2();
        ePew2 = enemy2.getEnemyProjectile2();
        ePew3 = enemy3.getEnemyProjectile2();
        ePew4 = enemy4.getEnemyProjectile2();
        ePew5 = enemy5.getEnemyProjectile2();
        
        userHealth = character.getHealth();
        mana = character.getMana();
        
        pewEnemy = false;
        pewEnemy2 = false;
        pewEnemy3 = false;
        pewEnemy4 = false;
        pewEnemy5 = false;
            
        pew2Enemy = false;
        pew2Enemy2 = false;
        pew2Enemy3 = false;
        pew2Enemy4 = false;
        pew2Enemy5 = false;
            
        pew3Enemy = false;
        pew3Enemy2 = false;
        pew3Enemy3 = false;
        pew3Enemy4 = false;
        pew3Enemy5 = false;
        
        userEnemy1 = character.detectCollision(enemy1);
        userEnemy2 = character.detectCollision(enemy2);
        userEnemy3 = character.detectCollision(enemy3);
        userEnemy4 = character.detectCollision(enemy4);
        userEnemy5 = character.detectCollision(enemy5);
        
        //userEPew variables corresponding to each enemy
        userEPew1 = character.detectCollision(ePew1);
        userEPew2 = character.detectCollision(ePew2);
        userEPew3 = character.detectCollision(ePew3);
        userEPew4 = character.detectCollision(ePew4);
        userEPew5 = character.detectCollision(ePew5);
        
        //time variables
        startTime = System.currentTimeMillis();
        currTime = 0;
        switchTime = 0;
        
        //number of enemies defeated
        numDefeated = 0;
        
        //setting the screen mode to the menu/title screen
        screenMode = 0;
        
        //setting instructions to false to indicate that the user hasn't pressed i to ask for instructions
        instructions = false;
        
        //setting lost to false to indicate that the user hasn't lost yet
        lost = false;
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //making the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //making sure the program can close
        gui.setTitle("Castle Runner"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

         /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
          */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        currTime = System.currentTimeMillis() - startTime;
        
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);
        
        if(screenMode == 0) //the menu/title screen!
        {
            if(key == 32)
            {
                switchTime = currTime;
                screenMode++;
            }
            else if(key == 73)
            {
                instructions = true;
            }
        }
        else if(screenMode == 2) //the game screen
        {
            mana = character.getMana();
            if(mana-5 < 0) //if the user has no more mana to spend, then they can't create any more bullets
                space = false; //so space is disabled
            else
                space = true;
        
            //moving the user-controlled rectangle using the aswd keys
            if(key == 87 && character.getY() >= 0)
            {
                character.moveUp();
            }
            else if(key == 83 && character.getY() + character.getHeight() <= HEIGHT)
            {
                character.moveDown();
            }
            else if(key == 65 && character.getX() >= 0)
            {
                character.moveLeft();
            }
            else if(key == 68 && character.getX() + character.getWidth() <= WIDTH)
            {
                character.moveRight();
            }
            else if(key == 32 && !onScreen1 && space) //enables the first projectile to be shot
            {
                pew = new UserProjectile(character);
                onScreen1 = true;
                character.minusMana();
            }
            else if(key == 32 && !onScreen2 && space) //enables the second projectile to be shot
            {
                pew2 = new UserProjectile(character);
                onScreen2 = true;
                character.minusMana();
            }
            else if(key == 32 && !onScreen3 && space) //enables the third projectile to be shot
            {
                pew3 = new UserProjectile(character);
                onScreen3 = true;
                character.minusMana();
            }
        }
    }   
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        currTime = System.currentTimeMillis() - startTime;
        
        //drawing a black rectangle to be the background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        //declaring fonts
        Font startup = new Font("Calibri", Font.PLAIN, 70);
        Font press = new Font(Font.MONOSPACED, Font.PLAIN, 25);
        
        if(screenMode == 0) //the menu/title screen!
        {
            //drawing a string to welcome the user
            g.setColor(Color.WHITE);
            
            g.setFont(startup);
            g.drawString("Castle Runner", 300, 220);
            
            g.setFont(press);
            g.drawString("press space to play", 357, 257);
            g.drawString("press i for instructions", 320, 287);
            
            if(instructions)
            {
                press = new Font(Font.MONOSPACED, Font.PLAIN, 17);
                g.setFont(press);
                g.drawString("how to play: aswd to move", 380, 315);
                g.drawString("objective: kill all 50 enemies! if they reach the left of the screen or kill you first, you lose.", 15, 335);
            }
        }
        else if(screenMode == 1) //in between menu & game mode
        {
            //creating a font for the countdown
            g.setColor(Color.WHITE);
            Font countdown = new Font("Arial", Font.PLAIN, 100);
            g.setFont(countdown);
            
            //timing the countdown
            if(switchTime + 1000 >= currTime)
                g.drawString("3", 480, 280);
            else if(switchTime + 2000 >= currTime)
                g.drawString("2", 480, 280);
            else if(switchTime + 3000 >= currTime)
                g.drawString("1", 480, 280);
            else if(switchTime + 4000 >= currTime && switchTime + 4000 <= currTime + 1000)
            {
                g.setFont(startup);
                g.drawString("START!", 410, 265);
            }
            else if(switchTime + 6000 >= currTime) //switching the screen mode to the game mode if the countdown is over
            {
                switchTime = 0;
                screenMode = 2;
            }
        }
        else if(screenMode == 2) //game mode
        {
            mana = character.getMana();
            currTime = System.currentTimeMillis() - startTime;
            userEnemy1 = character.detectCollision(enemy1);

            //determining if any of the user projectiles (pews) have collided with an enemy
            if(onScreen1) //if the pews are on the screen
            {
                pewEnemy = pew.detectCollision(enemy1);
                pewEnemy2 = pew.detectCollision(enemy2);
                pewEnemy3 = pew.detectCollision(enemy3);
                pewEnemy4 = pew.detectCollision(enemy4);
                pewEnemy5 = pew.detectCollision(enemy5);
            }
            else //otherwise they can't be true
            {
                pewEnemy = false;
                pewEnemy2 = false;
                pewEnemy3 = false;
                pewEnemy4 = false;
                pewEnemy5 = false;
            }
            
            if(onScreen2)
            {
                pew2Enemy = pew2.detectCollision(enemy1);
                pew2Enemy2 = pew2.detectCollision(enemy2);
                pew2Enemy3 = pew2.detectCollision(enemy3);
                pew2Enemy4 = pew2.detectCollision(enemy4);
                pew2Enemy5 = pew2.detectCollision(enemy5);
            }
            else
            {
                pew2Enemy = false;
                pew2Enemy2 = false;
                pew2Enemy3 = false;
                pew2Enemy4 = false;
                pew2Enemy5 = false;
            }
            
            if(onScreen3)
            {
                pew3Enemy = pew3.detectCollision(enemy1);
                pew3Enemy2 = pew3.detectCollision(enemy2);
                pew3Enemy3 = pew3.detectCollision(enemy3);
                pew3Enemy4 = pew3.detectCollision(enemy4);
                pew3Enemy5 = pew3.detectCollision(enemy5);
            }
            else
            {
                pew3Enemy = false;
                pew3Enemy2 = false;
                pew3Enemy3 = false;
                pew3Enemy4 = false;
                pew3Enemy5 = false;
            }
        
            //drawing the user projectiles (pew, pew2, pew3) and passing them g
            if(onScreen1) //if pew is on the screen (x is < WIDTH)
            {
                if(!pewEnemy && !pewEnemy2 && !pewEnemy3 && !pewEnemy4 && !pewEnemy5) ///if pew hasn't touched an enemy
                    pew.drawSelf(g);
                else
                {
                    pew.resetProjectile();
                    onScreen1 = false;
                }
            }
        
            if(onScreen2)
            {
                if(!pew2Enemy && !pew2Enemy2 && !pew2Enemy3 && !pew2Enemy4 && !pew2Enemy5)
                    pew2.drawSelf(g);
                else
                {
                    pew2.resetProjectile();
                    onScreen2 = false;
                }
            }
        
            if(onScreen3)
            {
                if(!pew3Enemy && !pew3Enemy2 && !pew3Enemy3 && !pew3Enemy4 && !pew3Enemy5)
                    pew3.drawSelf(g);
                else
                {
                    pew3.resetProjectile();
                    onScreen3 = false;
                }
            }
            
            //drawing the user and passing it g
            character.drawSelf(g);
        
            //drawing enemy1
            if(!pewEnemy && !pew2Enemy && !pew3Enemy) //if a user projectile hasn't touched the enemy
            {
                enemy1.drawSelf(g);
            }
            else
            {
                numDefeated++;
                if(numDefeated + 4 >= 50)
                    enemy1.resetSelf2(); //this puts the enemy off the screen when numDefeated approaches 50 so that they don't normally reset
                else
                {
                    enemy1.resetSelf();
                    enemy1.changeSpawn(enemy2, enemy3, enemy4, enemy5);
                }
            }
        
            //drawing enemy2
            if(!pewEnemy2 && !pew2Enemy2 && !pew3Enemy2) //if a user projectile hasn't touched the enemy
            {
                enemy2.drawSelf(g);
            }
            else
            {
                numDefeated++;
                if(numDefeated + 4 >= 50)
                    enemy2.resetSelf2();
                else
                {
                    enemy2.resetSelf();
                    enemy2.changeSpawn(enemy1, enemy3, enemy4, enemy5);
                }
            }
        
            //drawing enemy3
            if(!pewEnemy3 && !pew2Enemy3 && !pew3Enemy3)
            {
                enemy3.drawSelf(g);
            }
            else
            {
                numDefeated++;
                if(numDefeated + 4 >= 50)
                    enemy3.resetSelf2();
                else
                {
                    enemy3.resetSelf();
                    enemy3.changeSpawn(enemy1, enemy2, enemy4, enemy5);
                }
            }
        
            //drawing enemy4
            if(!pewEnemy4 && !pew2Enemy4 && !pew3Enemy4)
            {
                enemy4.drawSelf(g);
            }
            else
            {
                numDefeated++;
                if(numDefeated + 4 >= 50)
                    enemy4.resetSelf2();
                else
                {
                    enemy4.resetSelf();
                    enemy4.changeSpawn(enemy1, enemy2, enemy3, enemy5);
                }
            }
        
            //drawing enemy5
            if(!pewEnemy5 && !pew2Enemy5 && !pew3Enemy5)
            {
                enemy5.drawSelf(g);
            }
            else
            {
                numDefeated++;
                if(numDefeated + 4 >= 50)
                    enemy5.resetSelf2();
                else
                {
                    enemy5.resetSelf();
                    enemy5.changeSpawn(enemy1, enemy2, enemy3, enemy4);
                }
            }
        
            //drawing a string that states the number of enemies defeated
            g.setColor(Color.WHITE);
            Font showNumD = new Font("Calibri", Font.ITALIC, 30);
            g.setFont(showNumD);
            g.drawString("Number of enemies defeated: " + numDefeated, 20, 475);
        }
        else if(screenMode == 3) //ending screen
        {
            //drawing a string indicating the outcome of the game
            Font winLose = new Font("Arial", Font.BOLD, 70);
            g.setFont(winLose);
            
            //displaying different messages depending on the outcome of the game
            if(!lost) 
            {
                g.setColor(Color.GREEN);
                g.drawString("YOU WON!", 310, 230);
            }
            else
            {
                g.setColor(Color.RED);
                g.drawString("you lost... ):", 310, 230);
            }
            
            //thanking the user for playing
            g.setColor(Color.WHITE);
            g.setFont(press);
            g.drawString("thanks for playing!", 353, 270);
            Font me = new Font(Font.MONOSPACED, Font.PLAIN, 15);
            g.setFont(me);
            g.drawString("game made by stefani h.", 390, 300);
            g.drawString("thanks to mr. suriel for helping me! (:", 320, 315);
        }
        
    }
    public void loop()
    {
        if(screenMode == 2) //game mode
        {
            //setting the screen to the ending screen if the user won or lost
            if(numDefeated == 50 || lost)
            {
                screenMode = 3;
            }
            
            //getting the user's health
            userHealth = character.getHealth();
        
            currTime = System.currentTimeMillis() - startTime;
            //System.out.println(currTime);
        
            //getting the enemy projectiles
            ePew1 = enemy1.getEnemyProjectile2();
            ePew2 = enemy2.getEnemyProjectile2();
            ePew3 = enemy3.getEnemyProjectile2();
            ePew4 = enemy4.getEnemyProjectile2();
            ePew5 = enemy5.getEnemyProjectile2();
        
            //determining if the user has collided with an enemy
            userEnemy1 = character.detectCollision(enemy1);
            userEnemy2 = character.detectCollision(enemy2);
            userEnemy3 = character.detectCollision(enemy3);
            userEnemy4 = character.detectCollision(enemy4);
            userEnemy5 = character.detectCollision(enemy5);
            
            //determining if the user lost by checking if they reached 0 health or if they collided with an enemy
            if(userHealth == 0 || userEnemy1 || userEnemy2 || userEnemy3 || userEnemy4 || userEnemy5)
                lost = true;
            else if(enemy1.getX() == 0 || enemy2.getX() == 0 || enemy3.getX() == 0 || enemy4.getX() == 0 || enemy5.getX() == 0) //or if they reached the left side
                lost = true;
        
            //determining if the user has collided with any enemy projectiles (aka the ePews)
            userEPew1 = character.detectCollision(ePew1);
            userEPew2 = character.detectCollision(ePew2);
            userEPew3 = character.detectCollision(ePew3);
            userEPew4 = character.detectCollision(ePew4);
            userEPew5 = character.detectCollision(ePew5);

            //determining if any of the user projectiles (pews) have collided with an enemy
            if(onScreen1) //if the pews are on the screen
            {
                pewEnemy = pew.detectCollision(enemy1);
                pewEnemy2 = pew.detectCollision(enemy2);
                pewEnemy3 = pew.detectCollision(enemy3);
                pewEnemy4 = pew.detectCollision(enemy4);
                pewEnemy5 = pew.detectCollision(enemy5);
            }
            else //otherwise they can't be true
            {
                pewEnemy = false;
                pewEnemy2 = false;
                pewEnemy3 = false;
                pewEnemy4 = false;
                pewEnemy5 = false;
            }
            
            if(onScreen2)
            {
                pew2Enemy = pew2.detectCollision(enemy1);
                pew2Enemy2 = pew2.detectCollision(enemy2);
                pew2Enemy3 = pew2.detectCollision(enemy3);
                pew2Enemy4 = pew2.detectCollision(enemy4);
                pew2Enemy5 = pew2.detectCollision(enemy5);
            }
            else
            {
                pew2Enemy = false;
                pew2Enemy2 = false;
                pew2Enemy3 = false;
                pew2Enemy4 = false;
                pew2Enemy5 = false;
            }
            
            if(onScreen3)
            {
                pew3Enemy = pew3.detectCollision(enemy1);
                pew3Enemy2 = pew3.detectCollision(enemy2);
                pew3Enemy3 = pew3.detectCollision(enemy3);
                pew3Enemy4 = pew3.detectCollision(enemy4);
                pew3Enemy5 = pew3.detectCollision(enemy5);
            }
            else
            {
                pew3Enemy = false;
                pew3Enemy2 = false;
                pew3Enemy3 = false;
                pew3Enemy4 = false;
                pew3Enemy5 = false;
            }
        
            //making the user projectiles move
            if(onScreen1)
            {
                pew.shoot();
            }
            if(onScreen2)
            {
                pew2.shoot();
            }
            if(onScreen3)
            {
                pew3.shoot();
            }
        
            //resetting the pews if their x's > WIDTH or if they have collided with an enemy
            if(pew.getX() > WIDTH)
            {
                pew.resetProjectile();
                onScreen1 = false;
            }
            if(pew2.getX() > WIDTH)
            {
                pew2.resetProjectile();
                onScreen2 = false;
            }
            if(pew3.getX() > WIDTH)
            {
                pew3.resetProjectile();
                onScreen3 = false;
            }
        
            //enabling and disabling shooting (the space key) based on whether all three pews are on the screen
            if(onScreen1 && onScreen2 && onScreen3)
            {
                space = false;
            }
            else
            {
                space = true;
            }
        
            //timing the user regaining health & mana
            if(currTime%2500 <= 15)
            {
                character.addHealth();
            }
            if(currTime%1850 <= 15)
            {
                character.addMana();
            }
        
            //making the enemies move
            enemy1.move();
            enemy2.move();
            enemy3.move();
            enemy4.move();
            enemy5.move();
        }  
        
        //Do not write below this
        repaint();
    }
    
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) 
    {
    }
    public void keyReleased(KeyEvent e) 
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }
    
    public static void main(String[] args)
    {
        CastleDriver2 g = new CastleDriver2();
        g.start(60);
    }
}
