//İrem Önen 2022400279 16.04.2023
//The variables in this class are called from the Environment. Because their values are dependent on time passed.

public class Bar {
    Bar(){}
    public static void displayBar(){
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(8, -0.5,"bar.png",16,1.3);
        StdDraw.setPenColor(255,Environment.yellowVal,0);
        //"yellowVal" is the green value that is changed by time to turn yellow to red with time
        //"xCenter" is the x coordinate of rectangle of that changes color according to time. "halfWidth" is half of its width.
        StdDraw.filledRectangle(Environment.xCenter,-0.4,Environment.halfWidth,0.3);
}}
