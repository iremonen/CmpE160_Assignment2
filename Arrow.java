//İrem Önen 2022400279 16.04.2023
//This class has two methods. One returns a boolean according to the activeness of the arrow. Other method draws the arrow and updates its x and y coordinates
import java.awt.event.KeyEvent;

public class Arrow {
    public static boolean isActive = false;
    public static double arrowY = -4;
    public static double arrowX;
    Arrow(){
    }
    public static boolean isActive(){
        //checks if space key is pressed and if arrow is already active to prevent creating multiple arrows
        if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !isActive){
            arrowY = -4;
            isActive = true;
            arrowX = Player.playerPos;
        }
        return isActive;
    }
    public static void shootArrow(){
        //if y coordinate of arrow has reached to 4 then it means arrowtip has reached to upper edge
        //if arrow is active and its head has not reached to the edge this code draws the arrow
        if (isActive && arrowY < 4){
            arrowY = arrowY + 0.18;
            StdDraw.picture(arrowX,arrowY,"arrow.png",0.18,10);
        }
        if(arrowY>=4)
            isActive = false;
}}
