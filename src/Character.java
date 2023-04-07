import java.awt.*;

public class Character {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.

    public boolean intersect;
    public boolean isCrashing;

    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;

    public Rectangle rec;

    public Character(int pXpos, int pYpos, int pDy, int pDx, int pWidth, int pHeight) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = pWidth;
        height = pHeight;
        isAlive = true;
        intersect = false;
        isCrashing = false;
        rec = new Rectangle(xpos, ypos, height, width);
    }

    public void wrap() {
        if (xpos > 1000) {
            xpos = 0;
        }

        if (ypos >= 500) {
            ypos = 0;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, height, width);

    }
}
