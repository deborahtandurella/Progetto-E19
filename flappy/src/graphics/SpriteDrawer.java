package graphics;

import logic.gameElements.Bird;
import org.newdawn.slick.*;

public class SpriteDrawer{
    private Image bird;
    private Image coin;
    private Image heart;
    private SpriteSheet coinSheet;
    private Animation coinAnimation;
    private SpriteSheet heartSheet;
    private Animation heartAnimation;

    public SpriteDrawer(){
        try {
            bird = new Image("res/bird.png");
            heart = new Image("res/heart_full.png");
            coin = new Image("res/onecoin.png");

            coinSheet = new SpriteSheet("res/Coin.png",32,32);
            coinAnimation = new Animation(coinSheet,100);

            heartSheet = new SpriteSheet("res/Items_Heart.png", 32, 32);
            heartAnimation = new Animation(heartSheet, 100);

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }





    public void drawBird(Bird bird, Graphics graphics){
        graphics.drawImage(bird.getX(), bird.getY(),);

    }
}
