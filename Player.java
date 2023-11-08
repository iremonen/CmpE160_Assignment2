//İrem Önen 2022400279 16.04.2023
//This class contains only one method to draw the player. Also contains variables such as player's height, width etc.

import java.awt.event.KeyEvent;
public class Player {
    public static double playerPos = 8;
    public static double playerHeight = 1.25;
    public static double heightWidthRate = 37/27.0;
    public static double playerWidth = playerHeight/heightWidthRate;
    Player(){}
    public static void Player(){
        if (irem_onen.doesContinue){
            //If left key is pressed, player image is moved to left
            if (playerPos >= playerWidth / 2.0 && StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                playerPos = playerPos - 0.08;
            }
            //If right key is pressed, player image is moved to right
            if (playerPos <= 16.0 - playerWidth / 2.0 && StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                playerPos = playerPos + 0.08;
            }
        }
        //draws the player to canvas according to its updated x and y coordinates
        StdDraw.picture(playerPos, playerHeight / 2, "player_back.png", 1 / heightWidthRate, 1);
    }
}
