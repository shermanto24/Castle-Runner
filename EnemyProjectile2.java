import java.awt.Graphics;
import java.awt.Color;

public class EnemyProjectile2
{
    //instance variables
    private int x;
    private int y;
    private int diam;
    private int vx;
    private Color col;
    private Enemy2 enemy;
    private long lastTimeReset;
    private boolean flying;
    
    //parameterized constructor
    public EnemyProjectile2(Enemy2 e)
    {
        x = e.getX() + e.getWidth()/2;
        y = e.getY() + e.getHeight()/2;
        diam = 25;
        vx = 5;
        col = Color.YELLOW;
        enemy = e;
        lastTimeReset =-1;
        flying=false;
    }
    
    //toString method
    public String toString()
    {
        String output;
        output = "x: " + x + "\ny: " + y + "\ndiam: " + diam + "\nx-velocity: " + vx;
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
    
    public int getDiam()
    {
        return diam;
    }
    
    public Enemy2 getEnemy()
    {
        return enemy;
    }
    
    //resetProjectile method (resets the position of the projectile)
    public void resetProjectile()
    {
        x = 5900;
        y = -100;
        lastTimeReset = System.currentTimeMillis();
        flying = false;
    }
    
    public void resetProjectile2()
    {
        x = enemy.getX();
        y = enemy.getY() + enemy.getHeight()/2;
        flying = true;
    }
    
    //drawSelf method (drawing an oval)
    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillOval(x, y, diam, diam/2);
    }
    
    //shoot method (moves the projectile)
    public void shoot()
    {
        //System.out.println("last time reset = " + lastTimeReset);
        if(x + diam > 0-diam && System.currentTimeMillis() > lastTimeReset + 2000)
        {
            if(flying == false)
                resetProjectile2();
            else
                x-=vx;
        }
        else if(x + diam <= 0-diam)
        {
            resetProjectile();
        }
    }
}