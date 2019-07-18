package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

public interface GenericGraphicsFacade {
     void drawImage(Image image, float x, float y, float imageWidth, float imageHeight);
     void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation);
     void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight);
     void drawString(String text, UnicodeFont font, float x, float y);
     void clip(float x, float y, float width, float height);
}
