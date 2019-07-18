package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

/**
 *  La Canvas rappresenta il contesto grafico. Una Canvas è costituita da uno Screen, ovvero un rettangolo su cui disegnare ed una GenericGraphicsFacade da utilizzare per compiere le operazioni grafiche
 */
public class Canvas {

    private Screen screen;
    private GenericGraphicsFacade graphicsFacade;

    public Canvas(Screen screen, Graphics graphics){
        this.screen = screen;
        graphicsFacade = new SlickGraphicsFacade(graphics);
    }

    /**
     * Disegna un'immagine
     * @param image l'immagine
     * @param x la x dell'immagine rispetto alla x dello Screen (da 0 a 1)
     * @param y la y dell'immagine rispetto alla y dello Screen (da 0 a 1)
     * @param imageWidth la larghezza dell'immagine rispetto alla width dello Screen (da 0 a 1)
     * @param imageHeight la altezza dell'immagine rispetto alla width dello Screen (da 0 a 1)
     */
    public void drawImage(Image image, float x, float y, float imageWidth, float imageHeight){
        graphicsFacade.drawImage(image, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(),
                imageWidth*screen.getWidth(), imageHeight * screen.getHeight());
    }
    /**
     * Disegna un'immagine ruotata
     * @param image l'immagine
     * @param x la x dell'immagine rispetto alla x dello Screen (da 0 a 1)
     * @param y la y dell'immagine rispetto alla y dello Screen (da 0 a 1)
     * @param imageWidth la larghezza dell'immagine rispetto alla width dello Screen (da 0 a 1)
     * @param imageHeight la altezza dell'immagine rispetto alla width dello Screen (da 0 a 1)
     * @param rotation l'angolo di rotazione
     */
    public void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation){
        graphicsFacade.drawRotatedImage(image, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(),
                imageWidth*screen.getWidth(), imageHeight * screen.getHeight(), rotation);
    }

    /**
     * Disegna un'animazione
     * @param animation l'animazione
     * @param x la x dell'animazione rispetto alla x dello Screen (da 0 a 1)
     * @param y la y dell'animazione rispetto alla y dello Screen (da 0 a 1)
     * @param animationWidth la larghezza dell'animazione rispetto alla width dello Screen (da 0 a 1)
     * @param animationHeight la altezza dell'animazione rispetto alla width dello Screen (da 0 a 1)
     *
     */
    public void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight){
        graphicsFacade.drawAnimation(animation, screen.getOffsetX() + x*screen.getWidth(), screen.getOffsetY() + y*screen.getHeight(), animationWidth*screen.getWidth(), animationHeight*screen.getHeight());
    }

    /**
     * Disegna una stringa
     * @param text la stringa da disegnare
     * @param font il font
     * @param x la x della stringa rispetto alla x dello Screen (da 0 a 1)
     * @param y la y della stringa rispetto alla y dello Screen (da 0 a 1)
     */
    public void drawStringCentered(String text, UnicodeFont font, float x, float y){
        graphicsFacade.drawString(text, font, screen.getWidth()*x + screen.getOffsetX()- font.getWidth(text)/2f,
                screen.getOffsetY() +screen.getHeight()*y -font.getHeight(text)/2f);
    }
    /**
     * Disegna una stringa centrata orizzontalmente
     * @param text la stringa da disegnare
     * @param font il font
     * @param x la x della stringa rispetto alla x dello Screen (da 0 a 1)
     * @param y la y della stringa rispetto alla y dello Screen (da 0 a 1)
     */
    public void drawString(String text, UnicodeFont font, float x, float y){
        graphicsFacade.drawString(text, font,screen.getWidth()*x + screen.getOffsetX(),
                screen.getOffsetY() +screen.getHeight()*y -font.getHeight(text)/2f);

    }

    /**
     *  Ritaglia ciò che viene disegnato fuori dallo Screen
     */
    public void clipScreen(){
        graphicsFacade.clip(screen.getOffsetX(), screen.getOffsetY(), screen.getWidth(), screen.getHeight());
    }
    public Screen getScreen() {
        return screen;
    }

}
