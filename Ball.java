//İrem Önen 2022400279 16.04.2023
//This class contains several methods to calculate the balls' properties such as their radius and maximum height that they can reach.
//Also, there is a method called motion that updates the balls x and y coordinates and draws them.

public class Ball {
    public double ballXPos;
    public double ballYPos;
    public static double heightMulti = 1.75;
    public static double radiusMulti = 2.0;
    public double currentHeight;
    public double currentRadius;
    public static double minPossibleRadius = 16.0*0.0175;
    public static double minPossibleHeight = (10.0/8.0)*1.4;
    public double ballLevel;
    public static double gravity = 0.000003*9;
    public  double vx;
    public double vy;
    Ball(int ballLevel, double ballXPos, double vx, double ballYPos) {
        this.ballLevel = ballLevel;
        this.ballXPos = ballXPos;
        this.vx = vx;
        this.ballYPos = ballYPos;
    }
    //calculates the maximum height a specified level of ball can reach
    public double getCurrentHeight(double ballLevel){
        currentHeight = minPossibleHeight*Math.pow(heightMulti,ballLevel);
        return currentHeight;
    }
    //calculates the radius of the ball according to its level
    public double getCurrentRadius(double ballLevel){
        currentRadius = minPossibleRadius*Math.pow(radiusMulti,ballLevel);
        return currentRadius;
    }
    public void motion(){
        //this checks whether the ball touched the right and left edge of the canvas. If it did, it changes the direction of its speed on x axis.
        if((ballXPos <= getCurrentRadius(ballLevel)) || (ballXPos >= 16 - getCurrentRadius(ballLevel))){
            vx = -vx;
        }
        ballXPos = ballXPos + vx;
        //this checks if the ball hit the ground. If it did, it calculates its maximum speed on the y-axis and assigns that value to its speed on that axis
        if(ballYPos <= getCurrentRadius(ballLevel) + 0.15){
            vy = Math.pow((2*getCurrentHeight(ballLevel))/gravity,0.5)*gravity;
        }
        //by time passes its speed on y axis decreases by duration time multiplied with gravity constant
        vy = vy - gravity*Environment.pauseDuration;
        ballYPos += vy*Environment.pauseDuration;

        StdDraw.picture(ballXPos,ballYPos,"ball.png",getCurrentRadius(ballLevel)*2,2*getCurrentRadius(ballLevel));
    }
}
