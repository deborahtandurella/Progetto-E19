package Main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;



public  class DisplayModeManager {

    /**
     * Questa funzione individua fra le possibili risoluzioni dello schermo dell'utente che rispettano l'aspect ratio passato
     * come argomento quella più grande e che abbia frequencyRate di almeno 60Hz
     * @param widthRatio
     * @param heightRatio
     * @return bestDisplayMode
     * @throws LWJGLException
     */
    public static DisplayMode getBiggestWithRatio(int widthRatio, int heightRatio) throws LWJGLException {
        DisplayMode bestMode = new DisplayMode(800, 600);       //risoluzione supportata da tutti gli schermi

        DisplayMode[] modes = Display.getAvailableDisplayModes();
        for (DisplayMode mode : modes) {
            if (mode.getWidth() > bestMode.getWidth()) {
                if ((mode.getWidth() * heightRatio) / widthRatio == mode.getHeight() && mode.getFrequency() >= 60) {
                    bestMode = mode;
                }
            }
        }
        return bestMode;
    }
}
