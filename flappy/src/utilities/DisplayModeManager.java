package utilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public  class DisplayModeManager {

    /**
     * Questa funzione individua fra le possibili risoluzioni dello schermo dell'utente che rispettano l'aspect ratio passato
     * come argomento quella più grande e che abbia frequencyRate di almeno 60Hz
     * @param widthRatio
     * @param heightRatio
     * @param minFrequency la frequenza di display minima richiesta
     * @return bestDisplayMode la migliore risoluzione individuata
     * @throws LWJGLException
     */
    public static DisplayMode getBiggestWithRatio(int widthRatio, int heightRatio, int minFrequency) throws LWJGLException {
        DisplayMode bestMode = new DisplayMode(0, 0);
        DisplayMode[] modes = Display.getAvailableDisplayModes();
        for (DisplayMode mode : modes) {
            if (mode.getWidth() > bestMode.getWidth()) {
                if ((mode.getWidth() * heightRatio) / widthRatio == mode.getHeight() && mode.getFrequency() >= minFrequency) {  //se la DisplayMode presa in considerazione rispetta il ratio e ha una frequenza superiore a quella minima
                    bestMode = mode;
                }
            }
        }
        if (bestMode.getWidth()!=0)
            return bestMode;
        else
            return modes[0];  // se non è stata trovata una modalità soddisfacente restituisce la prima (dovrebbe essere quella utilizzata al momento)
    }
}
