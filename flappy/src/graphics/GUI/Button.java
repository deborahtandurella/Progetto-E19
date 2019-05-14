package graphics.GUI;

import graphics.Screen;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

public class Button extends MouseOverArea {
    public static final double BUTTON_WIDTH = 0.4;
    public static final double BUTTON_HEIGHT = 0.15;

    public Button(GameContainer container, Screen screen, Image image, double y, ComponentListener listener) {
        super(container,
                image.getScaledCopy((int)(BUTTON_WIDTH*screen.getWidth()), (int)(BUTTON_HEIGHT*screen.getHeight())),
                screen.getWidth()/2+screen.getOffsetX()-(int)(BUTTON_WIDTH*screen.getWidth())/2,
                (int) (y*screen.getHeight()+screen.getOffsetY()),
                (int)(BUTTON_WIDTH*screen.getWidth()),
                (int)(BUTTON_HEIGHT*screen.getHeight()), listener
        );
    }
}
