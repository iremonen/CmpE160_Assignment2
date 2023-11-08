//İrem Önen 2022400279 16.04.2023
//This contains only one method "game()" which initially creates the canvas. Also, the three initial balls are created and stored in an arraylist
// After that there is while loop that always works, until the player presses to N key and the system exits.
//Within this while loop there is an if condition that checks whether there is more time and the correctness of "doesContinue"
//Within the if there is a for-each loop that checks for each ball if they touched to the arrow or the player. If the arrow touched to the ball, the ball is removed from
// copy arraylist and instead of it two new lesser level balls are added. Outside the for loop copy arraylist is assigned to original arraylist. If the ball touched to
//player then "doesContinue" is assigned to false.


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Environment {
    public static final double gameDuration = 40000;
    public static final double pauseDuration = 15;
    public static double xCenter = 8;
    public static double halfWidth = 8;
    public static int yellowVal = 255;
    Environment(){}
    public static void game(){
        //Initial balls are created and stored
        Ball b1 = new Ball(1, 16/3.0, -0.032,0.5);
        Ball b = new Ball(0,16/4.0,0.032,0.5);
        Ball b2 = new Ball(2,16/4.0,0.032,0.5);

        Arrow a = new Arrow();
        ArrayList<Ball> firstBalls = new ArrayList<>();
        firstBalls.add(b1);
        firstBalls.add(b);
        firstBalls.add(b2);

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800,500);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        //Before starting to display game the "startTime" is determined. In the first moment of the game the "currentTime" equals the "startTime".
        double startTime = System.currentTimeMillis();
        double currentTime = startTime;

        while(true){
            if (currentTime<startTime+gameDuration && irem_onen.doesContinue){
                //Frame by frame the copy arraylist is redeclared and assigned to original arraylist which is also updated frame by frame according to ball-arrow and player-ball interactions.
                ArrayList<Ball> copyBalls = new ArrayList<>();
                copyBalls.addAll(firstBalls);
                StdDraw.picture(8,5,"background.png",16,10);
                //If arrow is activated, it calls to method to draw it
                if(Arrow.isActive())
                    a.shootArrow();
                Bar.displayBar();//Animation method for time bar
                Player.Player();//Animation method for the player
                //For each ball that is present in the game momentarily,
                for(Ball e : firstBalls) {
                    e.motion();//Animation method for balls
                    //this if checks the closeness of x and y coordinates of balls and arrow separately
                    if (Math.abs(e.ballXPos - Arrow.arrowX) <= e.getCurrentRadius(e.ballLevel) && Math.abs(e.ballYPos - Arrow.arrowY) <= 5 && Arrow.isActive()) {
                        //In the next two lines the x and y coordinates of the arrow are changed so that it is inactivated if it hit a ball
                        Arrow.arrowY = 20;
                        Arrow.arrowX = -1;
                        //If the hit ball is level 1, it is removed from the arraylist and two new level 0 balls are created and stored in it.
                        if (e.ballLevel == 1) {
                            Ball s = new Ball(0, e.ballXPos, 0.032, e.ballYPos);
                            Ball s1 = new Ball(0, e.ballXPos, -0.032, e.ballYPos);
                            copyBalls.add(s);
                            copyBalls.add(s1);
                            copyBalls.remove(e);
                        }
                        //Same thing above is applied for level 2 balls.
                        else if (e.ballLevel == 2) {
                            Ball s = new Ball(1, e.ballXPos, 0.032, e.ballYPos);
                            Ball s1 = new Ball(1, e.ballXPos, -0.032, e.ballYPos);
                            copyBalls.add(s);
                            copyBalls.add(s1);
                            copyBalls.remove(e);
                        }
                        //If it was level 0, no need to create new balls, simply removing them
                        else
                            copyBalls.remove(e);
                        Arrow.isActive = false;
                    }
                    //Checking whether the balls touched the player. If so then "doesContinue" is assigned to false.
                    if (Math.pow((Math.pow(e.ballYPos - 1, 2)) + Math.pow(Math.abs(Player.playerPos - e.ballXPos) - Player.playerWidth/2.0, 2), 0.5) <= e.getCurrentRadius(e.ballLevel)) {
                        irem_onen.doesContinue = false;
                        break;
                    }
                }
                //Changing the original list to modified copy list
                firstBalls = copyBalls;
                //This checks whether all the balls are hit and the arraylist is empty.
                if (firstBalls.size() == 0)
                    irem_onen.doesContinue = false;

                //Updating the current time and changing the rgb green value, x coordinate of the time bar and width of it according to time passed.
                currentTime = System.currentTimeMillis();
                double timeDiff = currentTime - startTime;
                xCenter = 8 * ((gameDuration - timeDiff) / gameDuration);
                halfWidth = 8 * ((gameDuration - timeDiff) / gameDuration);
                yellowVal = (int) (255 * ((gameDuration - timeDiff) / gameDuration));

                StdDraw.show();
                StdDraw.pause((int) pauseDuration);
                StdDraw.clear();
            }
            //If "doesContinue" is false and "currentTime" exceeded the game duration, it stops the game and displays the game screen
            else if (!irem_onen.doesContinue || currentTime>=startTime+gameDuration) {
                StdDraw.picture(8, 5, "background.png", 16, 10);
                StdDraw.picture(8, -0.5,"bar.png",16,1.3);
                if(currentTime<startTime+gameDuration) {
                    Bar.displayBar();
                }
                Player.Player();
                StdDraw.picture(8, 10 / 2.18, "game_screen.png", 16 / 3.8, 10 / 4.0);
                Font font = new Font("Helvetica", Font.BOLD, 30);
                Font font1 = new Font("Helvetica", Font.ITALIC, 15);
                StdDraw.setFont(font);
                StdDraw.setPenColor(StdDraw.BLACK);
                //If the game ended because there is no ball left, then it is supposed to print "YOU WON".
                if (firstBalls.size() == 0)
                    StdDraw.text(8, 5, "YOU WON!");
                //Otherwise "GAME OVER"
                else
                    StdDraw.text(8, 5, "GAME OVER!");
                StdDraw.setFont(font1);
                StdDraw.text(8, 10 / 2.3, "To Replay Click “Y” ");
                StdDraw.text(8, 10 / 2.6, "To Quit Click “N”");
                StdDraw.show();

                //If the user pressed to Y key, then all the values below are assigned to their initial value, balls and the arraylist that contains them are recreated etc.
                if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                    Player.playerPos = 8;
                    Arrow.arrowY = -4;
                    Arrow.isActive = false;
                    firstBalls.clear();
                    b1.ballXPos = 16/3.0;
                    b1.ballYPos = 0.5;
                    b.ballXPos = 16/4.0;
                    b.ballYPos = 0.5;
                    b2.ballXPos = 16/4.0;
                    b2.ballYPos = 0.5;
                    b1.vx = -0.032;
                    b2.vx = 0.032;
                    b.vx = 0.032;
                    firstBalls.add(b1);
                    firstBalls.add(b);
                    firstBalls.add(b2);
                    xCenter = 8;
                    halfWidth = 8;
                    yellowVal = 255;
                    irem_onen.doesContinue = true;
                    startTime = System.currentTimeMillis();
                }
                //Otherwise the system exits.
                else if (StdDraw.isKeyPressed(KeyEvent.VK_N))
                    System.exit(0);
            }
            }
        }
        }