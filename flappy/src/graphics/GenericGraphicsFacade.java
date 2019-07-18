package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

/**
 * GenericGraphicsFacade mette a disposizione una serie di funzionalità grafiche
 */
public interface GenericGraphicsFacade {
     /**
      * Disegna un'immagine
      * @param image l'immagine
      * @param x la x dell'immagine
      * @param y la y dell'immagine
      * @param imageWidth la larghezza dell'immagine
      * @param imageHeight la altezza dell'immagine
      */
     void drawImage(Image image, float x, float y, float imageWidth, float imageHeight);
     /**
      * Disegna un'immagine ruotata
      * @param image l'immagine
      * @param x la x dell'immagine
      * @param y la y dell'immagine
      * @param imageWidth la larghezza dell'immagine
      * @param imageHeight la altezza dell'immagine
      * @param rotation l'angolo di rotazione
      */
     void drawRotatedImage(Image image, float x, float y, float imageWidth, float imageHeight, float rotation);
     /**
      * Disegna un'animazione
      * @param animation l'animazione
      * @param x la x dell'animazione
      * @param y la y dell'animazione
      * @param animationWidth la larghezza dell'animazione
      * @param animationHeight la altezza dell'animazione
      *
      */
     void drawAnimation(Animation animation, float x, float y, float animationWidth, float animationHeight);
     /**
      * Disegna una stringa
      * @param text la stringa da disegnare
      * @param font il font
      * @param x la x della stringa
      * @param y la y della stringa
      */
     void drawString(String text, UnicodeFont font, float x, float y);

     /**
      * Ritaglia ciò che viene disegnato fuori dal rettangolo specificato
      * @param x la x del rettangolo
      * @param y la y del rettangolo
      * @param width la larghezza del rettangolo
      * @param height l'altezza del rettangolo
      */
     void clip(float x, float y, float width, float height);
}
