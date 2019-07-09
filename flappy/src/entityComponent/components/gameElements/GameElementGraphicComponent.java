package entityComponent.components.gameElements;

import entityComponent.components.GraphicComponent;
import graphics.Canvas;

public abstract class GameElementGraphicComponent implements GraphicComponent {
    private GameElementLogicComponent logicComponent;

    private Canvas canvas;
    @Override
    abstract public void render();
    public GameElementGraphicComponent(Canvas canvas) {
        this.canvas=canvas;
    }

    public void setLogicComponent(GameElementLogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }

    public GameElementLogicComponent getLogicComponent() {
        return logicComponent;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
