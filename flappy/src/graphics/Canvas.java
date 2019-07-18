package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

public class Canvas {

    private Screen screen;
    private GenericGraphicsFacade graphicsFacade;

    public Canvas(Screen screen, Graphics graphics){
        this.screen = screen;
        graphicsFacade = new SlickGraphicsFacade(graphics);
    }

    public void drawImage(Image image, float x, float y, float imageWidth, float imageHeight){
        graphicsFacade.drawImage(image, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(),
                imageWidth*screen.getWidth(), imageHeight * screen.getHeight());
    }
    public void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation){
        graphicsFacade.drawRotatedImage(image, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(),
                imageWidth*screen.getWidth(), imageHeight * screen.getHeight(), rotation);
    }
    public void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight){
        graphicsFacade.drawAnimation(animation, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(), animationWidth*screen.getWidth(), animationHeight*screen.getHeight());
    }
    public void drawStringCentered(String text, UnicodeFont font, float x, float y){
        graphicsFacade.drawString(text, font, screen.getWidth()*x + screen.getOffsetX()- font.getWidth(text)/2f,
                screen.getOffsetY() +screen.getHeight()*y -font.getHeight(text)/2f);
    }
    public void drawString(String text, UnicodeFont font, float x, float y){
        graphicsFacade.drawString(text, font,screen.getWidth()*x + screen.getOffsetX(),
                screen.getOffsetY() +screen.getHeight()*y -font.getHeight(text)/2f);

    }
    public void clipScreen(){
        graphicsFacade.clip(screen.getOffsetX(), screen.getOffsetY(), screen.getWidth(), screen.getHeight());
    }
    public Screen getScreen() {
        return screen;
    }

}
