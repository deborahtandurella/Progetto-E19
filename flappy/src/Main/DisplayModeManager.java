package Main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.Dimension;

public  class DisplayModeManager {
    public static DisplayMode getBiggestWithRatio(int widthRatio, int heightRatio) throws LWJGLException {
        DisplayMode bestMode = new DisplayMode(800, 600);       //risoluzione supportata da tutti gli schermi
        DisplayMode[] modes = Display.getAvailableDisplayModes();
        for (int i = 0; i < modes.length; i++) {
            if (modes[i].getWidth()>bestMode.getWidth()){
                if ((modes[i].getWidth()*heightRatio)/widthRatio==modes[i].getHeight()){
                    bestMode= modes[i];
                }
            }
        }
        return bestMode;
    }
}
