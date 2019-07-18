package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

/**
 * Implementazione di GenericGraphicsFacade per la libreria Slick2d
 */
public class SlickGraphicsFacade implements GenericGraphicsFacade {
    private Graphics graphics;

    public SlickGraphicsFacade(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void drawImage(Image image, float x, float y, float imageWidth, float imageHeight) {
        image.draw(x, y, imageWidth, imageHeight);
    }

    @Override
    public void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation) {
        image.setRotation(rotation);
        image.setCenterOfRotation(imageWidth/2f, imageHeight/2f);
        drawImage(image, x, y, imageWidth, imageHeight);
    }

    @Override
    public void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight) {
        animation.draw(x, y, animationWidth, animationHeight);
    }



    @Override
    public void drawString(String text, UnicodeFont font, float x, float y) {
        font.drawString(x ,y, text);
    }

    @Override
    public void clip(float x, float y, float width, float height) {
        graphics.setWorldClip(x, y, width, height);

    }
}
