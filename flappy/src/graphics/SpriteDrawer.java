package graphics;

import logic.SinglePlayer.Player;
import org.newdawn.slick.*;
import resources.FileKeys;
import resources.PathHandler;
import resources.PathKeys;

import static logic.gameConstants.GameConstants.*;


public class SpriteDrawer{
    private Image birdImage;
    private Image coinImage;
    private Image rocketImage;
    private Image heartImage;
    private Image lowerPipeImage;
    private Image upperPipeImage;
    private Image backgroundSingle;
    private SpriteSheet coinSheet;
    private Animation coinAnimation;
    private SpriteSheet birdSheet;
    private Animation birdAnimation;
    private SpriteSheet heartSheet;
    private Animation heartAnimation;
    private int screenWidth;
    private int screenHeight;
    private int offsetX;
    private int offsetY;
    private int lives;
    private int lifeBias;

    public SpriteDrawer(Screen screen){
        this.screenWidth = screen.getWidth();
        this.screenHeight = screen.getHeight();
        this.offsetX = screen.getOffsetX();
        this.offsetY = screen.getOffsetY();
        try {
            backgroundSingle = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES, PathKeys.BACKGROUND)).getScaledCopy(screenWidth,screenHeight);
            birdImage = new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES,PathKeys.BIRD)).getScaledCopy((int)(BIRD_WIDTH*screenWidth), (int)(BIRD_HEIGHT*screenHeight));
            heartImage = new Image("res/sprites/perks/heart_full.png").getScaledCopy((int) (HEART_SIZE*screenWidth), (int)(HEART_SIZE*screenHeight));
            coinImage = new Image("res/sprites/perks/onecoin.png").getScaledCopy((int)(COIN_SIZE*screenWidth), (int)(COIN_SIZE*screenHeight));
            lowerPipeImage= new Image(PathHandler.getInstance().getPath(FileKeys.SPRITES,PathKeys.PIPE)).getScaledCopy((int)(PIPE_WIDTH*screenWidth), (int)(PIPE_HEIGHT*screenHeight));
            upperPipeImage= lowerPipeImage.getFlippedCopy(false, true);

            coinSheet = new SpriteSheet("res/sprites/perks/Coin.png",32,32);
            coinAnimation = new Animation(coinSheet,100);

            birdSheet = new SpriteSheet("res/sprites/player/piccioneSheet.png",20,17);
            birdAnimation = new Animation(birdSheet,200);

            heartSheet = new SpriteSheet("res/sprites/perks/Items_Heart.png", 16, 16);
            heartAnimation = new Animation(heartSheet, 200);

            rocketImage = new Image("res/sprites/obstacles/rocket.png").getScaledCopy((int) (HEART_SIZE*screenWidth), (int)(HEART_SIZE*screenHeight));

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    public void drawBackgroundSingle(Graphics graphics){
        graphics.drawImage(backgroundSingle,offsetX,offsetY);
    }

    public void drawBird(float x, float y, Graphics graphics, double speedY){
        birdImage.setRotation( (float) 180*( (float) Math.atan2(speedY, JUMP_SPEED))/((float)Math.PI)) ;
        birdImage.setCenterOfRotation( (float)(BIRD_WIDTH*screenWidth)/2f,  (float)(BIRD_HEIGHT*screenHeight)/2f);
        birdImage.draw(offsetX + x*screenWidth, y*screenHeight+ offsetY, (float) BIRD_WIDTH*screenWidth, (float) BIRD_HEIGHT*screenHeight+ offsetY);

    }

    public void drawLives(Player player, Graphics graphics){
        lives = player.getHearts();
        lifeBias = screenWidth/15;
        for(int i = 0; i<lives;i++){
            graphics.drawImage(heartImage,offsetX + lifeBias,0.065f*screenHeight+offsetY);
            lifeBias += screenWidth/15;
        }
    }

    public void drawCoin(float x, float y, Graphics graphics){
        coinAnimation.draw(offsetX + x*screenWidth, y*screenHeight + offsetY, (float) COIN_SIZE*screenWidth, (float) COIN_SIZE*screenHeight);
    }

    public void drawRocket(float x, float y, Graphics graphics){
        rocketImage.draw(offsetX + x*screenWidth, y*screenHeight + offsetY, (float) ROCKET_SIZE*screenWidth, (float) ROCKET_SIZE*screenHeight);
    }

    public void drawHeart(float x, float y, Graphics graphics){
        heartAnimation.draw(offsetX + x*screenWidth, y*screenHeight + offsetY, (float) HEART_SIZE*screenWidth, (float) HEART_SIZE*screenHeight);
    }

    public void drawPipe(float x, float y, Graphics graphics){
        graphics.drawImage(upperPipeImage,offsetX + x*screenWidth,  offsetY + y*screenHeight - (float)PIPE_HEIGHT*screenHeight - 0.5f*((float)PIPE_FREE_SPACE)*screenHeight);
        graphics.drawImage(lowerPipeImage, offsetX + x*screenWidth, offsetY + y*screenHeight + 0.5f*((float)PIPE_FREE_SPACE)*screenHeight);

    }

    public void setBirdAlpha(float alpha){
        for (int i = 0; i<birdAnimation.getFrameCount();i++){
            birdAnimation.getImage(i).setAlpha(alpha);
        }
    }
}
