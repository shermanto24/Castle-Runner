import java.awt.Graphics;
import java.awt.Color;

public class Enemy2
{
    //instance variables
    private int x;
    private int y;
    private int type;
    private int vx;
    private int w;
    private int h;
    private Color col;
    private EnemyProjectile2 ep;
    private boolean random;
   
    //default constructor
    public Enemy2()
    {
        x = 1000;
        y = (int)(Math.random() * (420));
        type = (int)(Math.random() * (3 - 1 + 1) + 1);
        random = true;
        if(type == 1) //swordsman / melee
        {
            vx = 2;
            col = Color.BLUE;
            ep = null;//THIS MEANS HE WONT SHOOT
        }
        else
        {
            if(type == 2) //archer
            {
                col = Color.LIGHT_GRAY;
            }
            else //mage
            {
                col = Color.WHITE;
            }
            vx = 1;
            ep = new EnemyProjectile2(this);//this tells the projectile that this enemy is the one that owns said projectile
        }
        
        w = 40;
        h = 70;
    }
    
    //parameterized constructor
    public Enemy2(int t)
    {
        x = 1000;
        y = (int)(Math.random() * (420));
        type = t;
        random = false;
        if(type == 1) //swordsman / melee
        {
            vx = 2;
            col = Color.BLUE;
            ep = null;//THIS MEANS HE WONT SHOOT
        }
        else
        {
            if(type == 2) //archer
            {
                col = Color.LIGHT_GRAY;
            }
            else //mage
            {
                col = Color.WHITE;
            }
            vx = 1;
            ep = new EnemyProjectile2(this);
        }
        
        w = 40;
        h = 70;
    }
    
    //toString method
    public String toString()
    {
        String output;
        output = "x: " + x + "\ny: " + y + "\ntype: " + type + "\nx-velocity: " + vx;
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
        return w;
    }
    
    public int getHeight()
    {
        return h;
    }
    
    public int getType()
    {
        return type;
    }
    
    public EnemyProjectile2 getEnemyProjectile2()
    {
        return ep;
    }
    
    //drawSelf method
    public void drawSelf(Graphics g)
    {
        if(ep!=null)
            ep.drawSelf(g);
        
        g.setColor(col);
        g.fillRect(x,y,w,h);
    }

    //resets the position of the enemy
    public void resetSelf()
    {
        x = 1000;
        y = (int)(Math.random() * (420));

        if(type == 1)  //IF THE PREVIOUS TYPE WAS BLUE THEN GIVE IT A NEW PROJECTILE
            ep = new EnemyProjectile2(this);//this tells the projectile that this enemy is the one that owns said projectile

        if(random)
            type = (int)(Math.random() * (3 - 1 + 1) + 1); //THIS BRINGS 
        System.out.println("type = "+type + "\nrandom = " + random);
        if(type == 1) //swordsman / melee
        {
            System.out.println("blue");
            vx = 2;
            col = Color.BLUE;
            ep = null;//THIS MEANS HE WONT SHOOT
        }
        else
        {
            System.out.println("not blue");
            if(type == 2) //archer
            {
                col = Color.LIGHT_GRAY;
            }
            else //mage
            {
                col = Color.WHITE;
            }
            vx = 1;
        }
    }
    //move method
    public void move()
    {
        if(ep != null)
        {
            ep.shoot();
        }
        
        if(x > 0)
        {
            //System.out.println("moving forward " + x);
            x-=vx;
        }
    }
    
    //randomizes the y-coordinate
    public void randomY()
    {
        y = (int)(Math.random() * (420));
    }
    
    //changes the spawn of the enemy so that it doesn't overlap with the other enemies on the screen
    public void changeSpawn(Enemy2 enemy2, Enemy2 enemy3, Enemy2 enemy4, Enemy2 enemy5)
    {
        int y2 = enemy2.getY();
        int y3 = enemy3.getY();
        int y4 = enemy4.getY();
        int y5 = enemy5.getY();
        boolean two = y == y2 || y + h == y2 + h || y + h == y2 || y2 + h == y || (y >= y2 && y2 + h >= y && y + h >= y2 + h) || (y2 >= y && y + h >= y2 && y2 + h >= y + h);
        boolean three = y == y3 || y + h == y3 + h || y + h == y3 || y3 + h == y || (y >= y3 && y3 + h >= y && y + h >= y3 + h) || (y3 >= y && y + h >= y3 && y3 + h >= y + h);
        boolean four = y == y4 || y + h == y4 + h || y + h == y4 || y4 + h == y || (y >= y4 && y4 + h >= y && y + h >= y4 + h) || (y4 >= y && y + h >= y4 && y4 + h >= y + h);
        boolean five = y == y5 || y + h == y5 + h || y + h == y5 || y5 + h == y || (y >= y5 && y5 + h >= y && y + h >= y5 + h) || (y5 >= y && y + h >= y5 && y5 + h >= y + h);
        while(two || three || four || five)
        {
            randomY();
            two = y == y2 || y + h == y2 + h || y + h == y2 || y2 + h == y || (y >= y2 && y2 + h >= y && y + h >= y2 + h) || (y2 >= y && y + h >= y2 && y2 + h >= y + h);
            three = y == y3 || y + h == y3 + h || y + h == y3 || y3 + h == y || (y >= y3 && y3 + h >= y && y + h >= y3 + h) || (y3 >= y && y + h >= y3 && y3 + h >= y + h);
            four = y == y4 || y + h == y4 + h || y + h == y4 || y4 + h == y || (y >= y4 && y4 + h >= y && y + h >= y4 + h) || (y4 >= y && y + h >= y4 && y4 + h >= y + h);
            five = y == y5 || y + h == y5 + h || y + h == y5 || y5 + h == y || (y >= y5 && y5 + h >= y && y + h >= y5 + h) || (y5 >= y && y + h >= y5 && y5 + h >= y + h);
        }
    }
    
    public boolean resetSelf2()
    {
        x = -50;
        return true;
    }
}