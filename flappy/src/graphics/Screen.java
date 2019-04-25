package graphics;


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
}
