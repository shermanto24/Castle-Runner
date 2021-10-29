import java.awt.Graphics;
import java.awt.Color;

public class UserProjectile
{
    //instance variables
    private int x;
    private int y;
    private int diam;
    private int vx;
    private Color col;
    private User user;
    
    //parameterized constructor
    public UserProjectile(User u)
    {
        x = u.getX() + u.getWidth()/2;
        y = u.getY() + u.getHeight()/2;
        diam = 25;
        vx = 5;
        col = Color.MAGENTA;
        user = u;
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
    
    //drawSelf method (drawing an oval)
    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillOval(x, y, diam, diam/2);
    }
    
    //resetProjectile method (resets the position of the projectile)
    public void resetProjectile()
    {
        x = user.getX();
        y = user.getY() + user.getHeight()/2;
    }
    
    //shoot method (moves the projectile)
    public void shoot()
    {
        x+=vx;
    }
    
    //detectCollision method (tells if the projectile has made contact with an enemy)
    /*
    public boolean detectCollision(Enemy e)
    {
        boolean output = false;
        int eX = e.getX();
        int eY = e.getY();
        int eHeight = e.getHeight();
        if(x + diam >= eX && x <= eX && y + diam/4 >= eY && y + diam/4 <= eY + eHeight)
            output = true;
        else
            output = false;
        return output;
    }
    */
    
    //detectCollision method (tells if the projectile has made contact with an enemy)
    public boolean detectCollision(Enemy2 e)
    {
        boolean output = false;
        int eX = e.getX();
        int eY = e.getY();
        int eHeight = e.getHeight();
        if(x + diam >= eX && x <= eX && y + diam/4 >= eY && y + diam/4 <= eY + eHeight)
            output = true;
        else
            output = false;
        return output;
    }
}
