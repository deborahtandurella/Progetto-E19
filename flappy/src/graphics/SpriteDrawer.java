package graphics;

import logic.gameElements.Bird;
import logic.gameElements.Coin;
import logic.gameElements.Heart;
import org.newdawn.slick.*;

import static logic.gameConstants.GameConstants.BIRD_SIZE;
import static logic.gameConstants.GameConstants.COIN_SIZE;
import static logic.gameConstants.GameConstants.HEART_SIZE;


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
        this.bird.draw((float)bird.getX(),(float)bird.getY(),(float)BIRD_SIZE,(float)BIRD_SIZE);
    }

    public void drawCoin(Coin coin, Graphics graphics){
        this.coin.draw((float)coin.getX(),(float)coin.getY(),(float)COIN_SIZE,(float)COIN_SIZE);
    }

    public void drawHeart(Heart heart, Graphics graphics){
        this.heart.draw((float)heart.getX(),(float)heart.getY(),(float)HEART_SIZE,(float)HEART_SIZE);
    }
}
