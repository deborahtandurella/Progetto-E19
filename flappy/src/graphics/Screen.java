package graphics;


import org.newdawn.slick.Image;

/**
 * Questa classe rappresenta uno schermo di gioco
 *
 * width: larghezza dello schermo
 * height: altezza dello schermo
 * offsetX: offset dello schermo sulle ascisse rispetto all'origine
 * offsetY: offset dello schermo sulle ordinate rispetto all'origine
 */
public class Screen {
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public Screen(int width, int height, int offsetX, int offsetY) {
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void drawImage(Image image, float x, float y, float imageWidth, float imageHeight){
        image.draw(offsetX + x*width, offsetY + y*width,
                imageWidth*width, imageHeight * height);
    }
    public void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation){
        image.setRotation(rotation);
        image.setCenterOfRotation(imageWidth*width/2f, imageHeight*height/2f);
        drawImage(image, x, y, imageWidth, imageHeight);
    }
}
