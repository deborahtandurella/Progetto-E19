package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

public class Canvas {

    private Screen screen;
    private Graphics graphics;

    public Canvas(Screen screen, Graphics graphics){
        this.screen = screen;
        this.graphics = graphics;
    }

    public void drawImage(Image image, float x, float y, float imageWidth, float imageHeight){
        image.draw(screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(),
                imageWidth*screen.getWidth(), imageHeight * screen.getHeight());
    }
    public void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation){
        image.setRotation(rotation);
        image.setCenterOfRotation(imageWidth*screen.getWidth()/2f, imageHeight*screen.getHeight()/2f);
        drawImage(image, x, y, imageWidth, imageHeight);
    }
    public void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight){
        animation.draw(screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(), animationWidth*screen.getWidth(), animationHeight*screen.getHeight());
    }
    public void drawString(String text, UnicodeFont font, float x, float y){
        font.drawString(screen.getWidth()*x + screen.getOffsetX()- font.getWidth(text)/2f,
                screen.getOffsetY() +screen.getHeight()*y -font.getHeight(text)/2f,
                text);


    }
    public void clipScreen(){
        graphics.setWorldClip(screen.getOffsetX(), screen.getOffsetY(), screen.getWidth(), screen.getHeight());
    }
    public Screen getScreen() {
        return screen;
    }

}
