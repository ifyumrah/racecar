import java.awt.*;

public class Jellyfish {
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;

    public boolean intersect;
    public boolean isCrashing;

    public Image pic;


    public Rectangle rec;

    public Jellyfish(int pXpos, int pYpos,int pDx,int pDy, Image picParameter) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = 50;
        height = 50;
        pic = picParameter;
        isAlive = true;
        intersect = false;
        isCrashing = false;
        rec = new Rectangle(xpos,ypos,height,width);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        rec = new Rectangle(xpos,ypos,height,width);
    }
//
//    public void wrap(){
//        if(xpos>1000){
//            xpos = 0;}
//
//        if(ypos>=500){
//            ypos = 0;
//        }
//        xpos = xpos + dx;
//        ypos = ypos + dy;
//        rec = new Rectangle(xpos,ypos,height,width);
//
//    }

    public void bounce() {
        if (ypos >= 650 || ypos<=0) {
            dy = -dy;
            dx = 1;
       }

        if (xpos > 950 || xpos<=0) {
            dx = -dx;
            dy = 1;
       }


        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,height,width);
    }}


//       public void bump()
//        {  if(rec.intersects(rec)){
//            System.out.println("bump");
//            dx = -dx;
//           dy = -dy;
//        }
//            xpos = xpos + dx;
//            ypos = ypos + dy;     rec = new Rectangle(xpos,ypos,height,width);
//
//}
//    }

