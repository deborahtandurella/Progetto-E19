package entitiyComponent.components.gameElements;

import entitiyComponent.components.GraphicComponent;
import entitiyComponent.components.LogicComponent;
import graphics.Screen;
import org.newdawn.slick.Graphics;

public abstract class GameElementGraphicComponent implements GraphicComponent {
    private LogicComponent logicComponent;
    private Graphics graphics;
    private Screen screen;
    @Override
    abstract public void render();
    public GameElementGraphicComponent(Graphics graphics, Screen screen) {
        this.graphics= graphics;
        this.screen=screen;
    }

    public void setLogicComponent(LogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }

    public LogicComponent getLogicComponent() {
        return logicComponent;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Screen getScreen() {
        return screen;
    }
}
