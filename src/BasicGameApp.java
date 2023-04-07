//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image BobPic;
    public Image jellyfPic;
    public Image patPic;
    public Image jamPic;
    public Image background;
    public Image gameOver;


    //Declare the objects used in the program
    //These are things that are made up of more than one variable type

    private Player Bob;
    private Jellyfish[] jellyf;

    private Character pat;

    private Character jam;


    private boolean winning = false;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        background = Toolkit.getDefaultToolkit().getImage("jfish.jpeg");
        gameOver = Toolkit.getDefaultToolkit().getImage("finish.jpeg");
        Bob = new Player(550, 300, 0, 0);
        BobPic = Toolkit.getDefaultToolkit().getImage("sbob.png");
        jellyfPic = Toolkit.getDefaultToolkit().getImage("fish.png");
        pat = new Character(700, 300, 0, 3, 100, 200);
        jam = new Character(pat.xpos, pat.ypos, 0, 0, 70, 70);
        jam.isAlive = false;
        jamPic = Toolkit.getDefaultToolkit().getImage("jam.png");
        patPic = Toolkit.getDefaultToolkit().getImage("pat.png");

        jellyf = new Jellyfish[5];
        for (int x = 0; x < 5; x++) {
            jellyf[x] = new Jellyfish((int) (400 * (Math.random())), (int) (500 * Math.random()), 3, 3, jellyfPic);
        }
    }

    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            checkIntersections();
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    public void moveThings() {
        Bob.move();
        pat.wrap();
        for (int x = 0; x < 5; x++) {
            jellyf[x].bounce();
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);


        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the astronaut

        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(BobPic, Bob.xpos, Bob.ypos, Bob.width, Bob.height, null);
        g.drawImage(patPic, pat.xpos, pat.ypos, pat.width, pat.height, null);
//        if ((Bob.isStockFull && jam.isAlive) = true){
            g.drawImage(jamPic, jam.xpos, jam.ypos, jam.width, jam.height, null);
//        }
        for (int x = 0; x < 5; x++) {
            if (jellyf[x].isAlive) {
                g.drawImage(jellyf[x].pic, jellyf[x].xpos, jellyf[x].ypos, jellyf[x].width, jellyf[x].height, null);
                g.draw(new Rectangle(jellyf[x].xpos, jellyf[x].ypos, jellyf[x].width, jellyf[x].height));
            }
        }

        g.dispose();


        bufferStrategy.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
//        System.out.println(keyCode);

        if (keyCode == 83) { // s
//            Bob.dy = Math.abs(Bob.dy);
            Bob.down = true;
        }

        if (keyCode == 87) { //w
//            Bob.dy = -Math.abs(Bob.dy);
            Bob.up = true;
        }

        if (keyCode == 65) { //a
//            Bob.dx = -Math.abs(Bob.dx);
            Bob.left = true;
        }
        if (keyCode == 68) { //d
            Bob.right = true;
        }
    }

    public void checkIntersections() {
        for (int x = 0; x < 5; x++) {
            if (Bob.rec.intersects(jellyf[x].rec) && Bob.stock < 5 && jellyf[x].isCrashing == false && jellyf[x].isAlive) {
                jellyf[x].isAlive = false;
                jellyf[x].isCrashing = true;
                //  if (Bob.rec.intersects(jellyf[4].rec)) {
                Bob.stock++;
                System.out.println("spongebob's stock is " + Bob.stock);
                if (Bob.stock == 5) {
                    Bob.isStockFull = true;
                    System.out.println("spongebob's stock is full");
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            Bob.right = false;
        }
        if (keyCode == 65) { // a
            Bob.left = false;
        }
        if (keyCode == 83) { // s
            Bob.down = false;
        }
        if (keyCode == 87) { // w
            Bob.up = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getX());
//        pat.xpos=e.getX();
//        pat.ypos=e.getY();

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println(e.getX());
    }
}

