import java.awt.*;

public class Player { public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public int stock;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.

    public boolean intersect;
    public boolean isCrashing;
    public boolean isStockFull;

    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;

    public Rectangle rec;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Player(int pXpos, int pYpos, int pDy,int pDx) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = 200;
        height = 200;
        isAlive = true;
        intersect = false;
        isCrashing = false;
        isStockFull = false;
        stock = 0;
        rec = new Rectangle(xpos,ypos,height,width);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(right == true){
            dx = 10;
        } else if (left == true) {
            dx = -10;
        } else {
            dx = 0;

            if(down == true){
                dy = 10;
            } else if (up == true) {
                dy = -10;
            } else {
                dy = 0;
            }

            if(xpos>1000-width){ // right
                xpos = 1000-width;
            }
            if(xpos < 0) { // left
                xpos = 0;
            }
            if(ypos>650-height){ // down
                ypos = 650-height;
            }
            if(ypos < 0) { // up
                ypos = 0;
            }
        }
        rec = new Rectangle(xpos,ypos,height,width);
    }

    public void wrap(){
        if(xpos>1000){
            xpos = 0;}

        if(ypos>=500){
            ypos = 0;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,height,width);

    }

//    public void bounce() {
//        if (ypos >= 650 || ypos<=0) {
//            dy = -dy;
//            dx = 1;
//        }
//
//        if (xpos > 950 || xpos<=0) {
//            dx = -dx;
//            dy = 1;
//        }
//
//
//        xpos = xpos + dx;
//        ypos = ypos + dy;
//        rec = new Rectangle(xpos,ypos,height,width);
//    }


//       public void bump()
//        {  if(rec.intersects(rec)){
//            System.out.println("bump");
//            dx = -dx;
//           dy = -dy;
//        }
//            xpos = xpos + dx;
//            ypos = ypos + dy;     rec = new Rectangle(xpos,ypos,height,width);

}
//    }

