import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class User
{
    //declaring instance variables
    private int x;
    private int y;
    private int width;
    private int height;
    private Color col;
    private int health;
    private int mana;
    
    //default constructor
    public User()
    {
        x = 200;
        y = 200;
        width = 40;
        height = 70;
        col = Color.PINK;
        health = 100;
        mana = 100;
    }
    
    //parameterized constructor
    public User(int xCoor, int yCoor)
    {
        x = xCoor;
        y = yCoor;
        width = 40;
        height = 70;
        col = Color.PINK;
        health = 100;
        mana = 100;
    }
    
    //parameterized constructor 2
    public User(int xCoor, int yCoor, int w, int h, Color c)
    {
        x = xCoor;
        y = yCoor;
        width = w;
        height = h;
        col = c;
        health = 100;
        mana = 100;
    }
    
    //choosing how to print the instance variables with the toString method
    public String toString()
    {
        String output;
        output = "x-coor: " + x + "\ny-coor: " + y + "\nwidth: " + width + "\nheight: " + height + "color: " + col;
        return output;
    }
    
    //accessor methods
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getMana()
    {
        return mana;
    }
    
    //drawSelf method (drawing the rectangle as well as the String that shows the health)
    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillRect(x,y,width,height);
        Font showHealth = new Font("Arial", Font.PLAIN, 10);
        g.setFont(showHealth);
        if(health > 25)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        g.drawString(health + "",x,y);
        if(mana > 25)
            g.setColor(Color.CYAN);
        else
            g.setColor(Color.RED);
        g.drawString(mana + "",x + width-18,y);
    }
    
    //methods to make the user move
    public void moveUp()
    {
        y-=10;
    }
    
    public void moveDown()
    {
        y+=10;
    }
    
    public void moveLeft()
    {
        x-=10;
    }
    
    public void moveRight()
    {
        x+=10;
    }
    
    //addHealth method (adds health over time)
    public void addHealth()
    {
        if(health+5 >= 100 && health != 0)
        {
            health = 100;
        }
        else if(health < 100 && health != 0)
        {
            health+=5;
        }
    }
    
    //minusMana() (decreases mana over time)
    public void minusMana()
    {
        if(mana-5 < 0)
            mana = 0;
        else if(mana > 0)
            mana-=5;
    }
    //addMana() (adds mana over time)
    public void addMana()
    {
        if(mana+5 > 100)
        {
            mana = 100;
        }
        else if(mana < 100)
        {
            mana+=5;
        }
    }
    
    //==========================================================================
    //suriel methods
    //detectCollision method (tells if the user-controlled character has collided with an enemy of any type)
    public boolean detectCollision(Enemy2 e)
    {
        boolean output = false;
        int eX = e.getX();
        int eY = e.getY();
        int eHeight = e.getHeight();
        if(x + width >= eX && x <= eX && eY >= y && eY <= y + height)
        {
            output = true;
            health = 0;
        }
        return output;
    }
    
    //second detectCollision method (tells if an enemy projectile has collided with the user)
    public boolean detectCollision(EnemyProjectile2 e)
    {
        boolean output;
        if(e != null)
        {
            int eX = e.getX();
            int eY = e.getY();
            if(x + width >= eX && x <= eX + e.getDiam() && eY + e.getDiam()/4 >= y && eY <= y + height)
            {
                output = true;
                if(health-15 < 0)
                {
                    health = 0;
                    e.resetProjectile();
                }
                else
                {
                    health-=15;
                    System.out.println("USER HEALTH -15");
                    e.resetProjectile();
                }
            }
            else
            {
                output = false;
            }
        }
        else
        {
            output = false;
        }
        return output;
    }
}
